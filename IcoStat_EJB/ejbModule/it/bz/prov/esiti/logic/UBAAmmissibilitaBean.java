package it.bz.prov.esiti.logic;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.bz.prov.esiti.bobject.ElencoUBAAmmissibilitaBO;
import it.bz.prov.esiti.bobject.EsitoImpegniRMFITFERExtraBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.UBAAmmissibilitaBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.Domanda;
import it.bz.prov.esiti.entity.DomandaPk;
import it.bz.prov.esiti.entity.EsitoImpegniRMFITFERExtra;
import it.bz.prov.esiti.entity.EsitoImpegniRMFITFERExtraPk;
import it.bz.prov.esiti.entity.UBAAmmissibilita;
import it.bz.prov.esiti.entity.UBAAmmissibilitaPk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.IUBAAmmissibilita;
import it.bz.prov.esiti.util.CheckData;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.ExcelReader;
import it.bz.prov.esiti.util.ExcelWriter;
import it.bz.prov.esiti.util.FileExcel;
import it.bz.prov.esiti.util.Utils;

@Stateful
public class UBAAmmissibilitaBean implements IUBAAmmissibilita {

	private static String COL_CUAA            = "CUAA";
	private static String COL_DOMANDA         = "Domanda";
	private static String COL_CAMPAGNA        = "Campagna";
	private static String COL_INTERVENTO      = "Intervento";
	private static String COL_ESITO           = "Esito";
	private static String COL_STATO           = "Stato";
	private static String COL_UBARICH         = "UBA Richieste";
	private static String COL_UBAACC          = "UBA Accertate";
	private static String COL_UBAIRREG        = "UBA Irregolari";
	private static String COL_PERCRID         = "% Riduzione";
	private static String COL_NOTE            = "Note";
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	private ElencoUBAAmmissibilitaBO _elencoUBAAmm = new ElencoUBAAmmissibilitaBO();
	
	/**
	 * aggiunge una uba alla lista
	 * @param uba
	 * @return OperationResultBO
	 */
	@Override
	public OperationResultBO aggiungiUba(UBAAmmissibilitaBO uba) {
		// controlli lato server pre-inserimento
				OperationResultBO result = checkPreInsert(uba, Costanti.ACTION_INSERT);
				if(!result.get_result()) return result;
				
				// se i controlli sono andati bene procedo con l'inserimento
				result = new OperationResultBO();
				try {
					uba.set_userInserimento(Utils.getCurrentUser());
					uba.set_dataInserimento(Utils.todayDate());
					UBAAmmissibilita ubaEntity = uba.getUbaEntity();
					_em.clear();
					_em.persist(ubaEntity);
					// inserimento in elenco
					_elencoUBAAmm.addEsito(uba);
					// preparazione risultato
					result.set_result(true);
					result.set_message(Costanti.MSG_INSERT_OK);
					return result;
				} catch (Exception e) {
					e.printStackTrace();
					// preparazione risultato
					result.set_result(false);
					result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
					Utils.getLog().error(UBAAmmissibilitaBean.class.getSimpleName() + " - aggiungiUba: " + e.getMessage());
					return result;
				}
	}

	/**
	 * modifica un elemento della lista
	 * @param uba
	 * @return OperationResultBO
	 */
	@Override
	public OperationResultBO modificaUba(UBAAmmissibilitaBO uba) {
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(uba, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			UBAAmmissibilitaPk pk = new UBAAmmissibilitaPk();
			pk.set_intervento(uba.get_intervento());
			pk.set_domanda(uba.get_domanda());
			_em.clear();
			UBAAmmissibilita ubaFind = _em.find(UBAAmmissibilita.class, pk);
			// se la riga non é stata trovata su db
			if(ubaFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
			uba.set_userModifica(Utils.getCurrentUser());
			uba.set_dataModifica(Utils.todayDate());
			uba.setEntity(ubaFind);	
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(UBAAmmissibilitaBean.class.getSimpleName() + " - modificaUba: " + e.getMessage());
			return result;
		}
	}

	/**
	 * cancella un elemento della lista
	 * @param uba
	 * @return OperationResultBO
	 */
	@Override
	public OperationResultBO cancellaUba(UBAAmmissibilitaBO uba) {
		OperationResultBO result = new OperationResultBO();
		try {			
			_em.clear();
			UBAAmmissibilitaPk pk = new UBAAmmissibilitaPk();
			pk.set_domanda(uba.get_domanda());
			pk.set_intervento(uba.get_intervento());
			UBAAmmissibilita ubaFind = _em.find(UBAAmmissibilita.class, pk);
			// se la riga non é stata trovata su db
			if(ubaFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
			_em.remove(ubaFind);
			// cancellazione dall'elenco
			_elencoUBAAmm.remove(uba);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(UBAAmmissibilitaBean.class.getSimpleName() + " - cancellaUba: " + e.getMessage());
			return result;
		}
	}

	@Override
	public List<UBAAmmissibilitaBO> getElencoUba() {
		return this._elencoUBAAmm.get_listUba();
	}

	@Override
	public void setElencoUba(ElencoUBAAmmissibilitaBO uba) {
		this._elencoUBAAmm = uba;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadData() {
		Query q = _em.createNamedQuery("UBAAMM.selectAll");
		_em.clear();
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<UBAAmmissibilita> fornitura = q.getResultList();
		_elencoUBAAmm.set_listUba(fornitura);
	}

	/**
	 * restituisce la lista dei valori distinti per l'esito
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getListaValoriEsito()
	{
		_em.clear();
		Query q = _em.createNamedQuery("ValoreAnagrafica.selectAnagrafica");
		q.setParameter("campo", "ESITO");
		List<ValoreAnagrafica> fornitura = q.getResultList();
		List<String> lista = new ArrayList<String>();
		for (ValoreAnagrafica valoreAnagrafica : fornitura) {
			lista.add(valoreAnagrafica.get_valore());
		}
		lista.add(0, "");
		return lista;
	}

	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getListCampagna() {
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.distinctCampagna");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}

	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	@SuppressWarnings("unchecked")
	@Override
	public void filter(HashMap<String, String> parametersList) {
		try {
		_em.clear();
    	Query q = _em.createNamedQuery("UBAAMM.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);		
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<UBAAmmissibilita> fornitura = q.getResultList();
		_elencoUBAAmm.set_listUba(fornitura);
		} catch(Exception e) {
			Utils.getLog().warn("UBAAmmissibilita.filter: " + e.toString());
		}
	}

	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	@Override
	public void exportFile(OutputStream stream) {
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = UBAAmmissibilitaBean.getListaIntestazioni();
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (UBAAmmissibilitaBO uba : _elencoUBAAmm.get_listUba()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, uba.get_cuaa());
			riga.put(1, uba.get_campagna());
			riga.put(2, uba.get_domanda());
			riga.put(3, uba.get_intervento());
			riga.put(4, uba.get_esito());
			riga.put(5, uba.get_stato());
			riga.put(6, uba.get_ubaRich());
			riga.put(7, uba.get_ubaAcc());
			riga.put(8, uba.get_ubaIrreg());
			riga.put(9, uba.get_percRid());
			riga.put(10, uba.get_note());
			righe.add(riga);
		}
		
		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}

	@Override
	public void clearList() {
		_elencoUBAAmm = new ElencoUBAAmmissibilitaBO();
	}

	/**
	 * permette l'inserimento dei dati da file excel
	 * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
	 */
	@Override
    public boolean insertFromFile(String pathFile, List<String> listLog)
    {
    	//1. estraggo il file excel
    	FileExcel fileExcel = ExcelReader.readFile(pathFile);
    	if(fileExcel == null)
    	{
    		listLog.add("ERROR: ci sono stati problemi nella lettura del file temporaneo");
    		return false;
    	}
    	
    	//2. verifico la presenza di tutte le intestazioni corrette
    	boolean check = true;
    	LinkedHashMap<Integer, String> listaColonna = UBAAmmissibilitaBean.getListaIntestazioni();
    	for (String colonna : listaColonna.values()) {
			boolean result = fileExcel.hasColonna(colonna);
			if(!result)
			{
				listLog.add("ERROR: La colonna " + colonna + " non é presente nel file");
				check = false;
			}
		}
    	if(!check) return false;
    	else listLog.add("INFO: I controlli di coerenza sulle colonne del file hanno dato esito positivo");
    	
    	//3. creazione della lista di esiti
    	ArrayList<UBAAmmissibilitaBO> listaEsiti = new ArrayList<UBAAmmissibilitaBO>(); 
    	boolean load_file = getListFromExcel(fileExcel, listaEsiti, listLog);
    	if(listaEsiti.size() ==0)
    	{
			listLog.add("ERROR: il file non contiene righe di dato");
			return false;
		}
    	if(!load_file)
    	{
			listLog.add("ERROR: i dati non sono stati caricati causa errore nel formato");
			return false;
		}
    	
    	//4. verifica correttezza valori    	
    	boolean complete_result = true;
    	for (UBAAmmissibilitaBO esito : listaEsiti) {
    		// utilizzo ACTION_MODIFY perchè così non fa la verifica sulla pk esistente
			OperationResultBO result = checkPreInsert(esito, Costanti.ACTION_MODIFY);
			// se il check ha trovato degli errori su questa riga
			if(!result.get_result()) 
			{
				complete_result = false;
				listLog.add("ERROR: domanda:" + esito.get_domanda() + " - errore: " + result.get_message());
			}			
		}
    	//5. verifiche non fatte per inserimetno da maschera
    	OperationResultBO result = checkForImport(listaEsiti);
    	if(!result.get_result()) 
		{
			complete_result = false;
			listLog.add(result.get_message());
		}
    	
    	if(complete_result) listLog.add("INFO: Verifica coerenza valori delle righe terminata con successo");
    	else 
    	{
    		listLog.add("ERROR: Terminata l'operazione. I dati non sono stati importati.");
    		return false; // termino l'esecuzione del metodo
    	}
    	
    	//6. inserimento dati
    	complete_result = inserimentoMassivo(listaEsiti, listLog);
    	    	
    	if(complete_result) listLog.add("INFO: upload dei dati terminato con successo. Numero righe contenute nel file: "+ listaEsiti.size());
    	else listLog.add("ERROR: l'operazione di inserimento dati non é terminata correttamente.");
    	
    	
    	return complete_result;
    }
	
	/**
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update)
     * @param uba
     * @param action
     * @return OperationResultBO
     */
    @SuppressWarnings("unchecked")
	private OperationResultBO checkPreInsert(UBAAmmissibilitaBO uba, String action)
    {
    	OperationResultBO result = new OperationResultBO();
    	
    	//1. campagna valorizzata (non nulla e non uguale a spazio)
    	if(!CheckData.checkString(uba.get_campagna()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMPAGNA);
			return result;
		}
    	
    	//2. CUAA valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(uba.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA);
			return result;
		}
    	
    	//3. CUAA con lunghezza compresa tra 11 e 16 caratteri
    	if(!CheckData.checkCuaaLength(uba.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_LENGTH);
			return result;
		}
    	
    	// 4. cuaa deve essere presente in anagrafica
		_em.clear();
		Azienda aziendaFind = _em.find(Azienda.class, uba.get_cuaa());
		if(aziendaFind == null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_MANCANTE);
			return result;
		}
		
		//5. domanda non nulla
		if(!CheckData.checkString(uba.get_domanda()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_NULLA);
			return result;
		}
		
		//6. domanda esistente in anagrafica
		_em.clear();
		//Domanda domandaFind = _em.find(Domanda.class, uba.get_domanda());
		
		//LOAT gestione della chiave primaria composta
		DomandaPk domPk = new DomandaPk();
		domPk.set_idDomanda(uba.get_domanda());
		
		if(Integer.parseInt(uba.get_campagna()) < 2015)
			domPk.set_misura(uba.get_intervento().substring(0,3));
		else 
			domPk.set_misura(uba.get_intervento().substring(0,2));
		Domanda domandaFind = _em.find(Domanda.class, domPk);
		if(domandaFind == null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_MANCANTE);
			return result;
		}
		else 
		{
			//6.A la domanda deve essere di misura 214
			if(!domandaFind.get_misura().equals("214") && !domandaFind.get_misura().equals("10"))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DOMANDA_MISURA_ERRATA);
				return result;
			}
		}
		
		//7. check valore uba richieste
		if(!uba.get_ubaRich().equals(""))
			if(!CheckData.checkFloat(uba.get_ubaRich()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_UBA_DICH);
				return result;
			}
		
		//8. check valore uba accertate
		if(!uba.get_ubaAcc().equals(""))
			if(!CheckData.checkFloat(uba.get_ubaAcc()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_UBA_ACC);
				return result;
			}
		
		//9. check valore uba differenza
		if(!uba.get_ubaIrreg().equals(""))
			if(!CheckData.checkFloat(uba.get_ubaIrreg()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_UBA_IRREG);
				return result;
			}
    	
    	// 10. domanda e intervento non devono essere già presenti in tabella (é la pk) 
		//(da fare solo per insert, no per modify)
		if(action.equals(Costanti.ACTION_INSERT))
		{
			UBAAmmissibilitaPk pk = new UBAAmmissibilitaPk();
			pk.set_domanda(uba.get_domanda());
			pk.set_intervento(uba.get_intervento());
			_em.clear();
			UBAAmmissibilita ubaFind = _em.find(UBAAmmissibilita.class, pk);
			if(ubaFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_PRESENTI);
				return result;
			}
		}
		
		//11. verifica se la domanda ha l'intervento 214.2
		_em.clear();
    	Query q = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
		// imposto i parametri della query
    	q.setParameter("domanda", uba.get_domanda());
		q.setParameter("intervento", uba.get_intervento());
		List<String> fornitura = q.getResultList();
		if(fornitura== null || fornitura.size() == 0)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_MANCANTE);
			return result;
		}
		
		//14. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(uba.get_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
		
		// se arrivo qui ha superato i controlli
		result.set_result(true);
    	return result;
    }
    
    private OperationResultBO checkForImport(List<UBAAmmissibilitaBO> listaImpegniUBA) {
    	// controllo che il valore dei flag sia uguale a quello in anagrafica
    	List<String> lista_valori_stato = getListaValori(Costanti.ANAGR_STATO);
    	List<String> lista_valori_esito = getListaValori(Costanti.ANAGR_ESITO);

    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (UBAAmmissibilitaBO esito : listaImpegniUBA) {
    		// stato
			if(!esito.get_stato().isEmpty() && !lista_valori_stato.contains(esito.get_stato()))
			{
				result.set_result(false);
				result.set_message("ERROR: Stato - valore non in anagrafica");
				break;
			}
			// esito OPPAB
			if(!esito.get_esito().isEmpty() && !lista_valori_esito.contains(esito.get_esito()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito - valore non in anagrafica");
				break;
			}
		}
    	return result;    	
    }

    
    /**
     * prepara la lista di tutte le intestazioni delle colonne presenti nel file
     * @return LinkedHashMap<Integer, String>
     */
    private static LinkedHashMap<Integer, String> getListaIntestazioni()
    {
    	LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
    	intestazione.put(0, COL_CUAA);
		intestazione.put(1, COL_CAMPAGNA);
		intestazione.put(2, COL_DOMANDA);
		intestazione.put(3, COL_INTERVENTO);
		intestazione.put(4, COL_ESITO);
		intestazione.put(5, COL_STATO);
		intestazione.put(6, COL_UBARICH);
		intestazione.put(7, COL_UBAACC);
		intestazione.put(8, COL_UBAIRREG);
		intestazione.put(9, COL_PERCRID);
		intestazione.put(10, COL_NOTE);
    	return intestazione;
    }

    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel é l'oggetto che contiene i sati letti da file
     * @param lista 
     * @param logList
     * @return boolean 
     */
	private static boolean getListFromExcel(FileExcel fileExcel, ArrayList<UBAAmmissibilitaBO> lista, List<String> logList)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	List<String> lstDomande = new ArrayList<String>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		UBAAmmissibilitaBO esito = new UBAAmmissibilitaBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { esito.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { esito.set_campagna(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DOMANDA)) { esito.set_domanda(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_INTERVENTO)) { esito.set_intervento(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ESITO)) { esito.set_esito(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_STATO)) { esito.set_stato(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_UBARICH)) { esito.set_ubaRich(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_UBAACC)) { esito.set_ubaAcc(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_UBAIRREG)) { esito.set_ubaIrreg(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERCRID)) { esito.set_percRid(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NOTE)) { esito.set_note(valore); continue; }
			}
			
			// controllo per evitare di prendere righe vuote
			if(!esito.get_cuaa().equals("") && !esito.get_campagna().equals("")){
				lista.add(esito);
				// popolamento delle liste utilizzate per dettagliare il log dell'applicazione
				if(lstCuaa.isEmpty()){
					lstCuaa.add(esito.get_cuaa());
				}else if(!lstCuaa.contains(esito.get_cuaa())) lstCuaa.add(esito.get_cuaa());
				if(lstDomande.isEmpty()){
					lstDomande.add(esito.get_domanda());
				}else if(!lstDomande.contains(esito.get_domanda())) lstDomande.add(esito.get_domanda());
			}
		}
    	logList.add("INFO: Numero CUAA presenti nel file: "+ lstCuaa.size());
    	logList.add("INFO: Numero domande presenti nel file: "+lstDomande.size());
    	return true;
    }
	
	/**
     * esegue l'inserimento di tutte le righe in una sola transazione
     * @param listaEsiti
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivo(ArrayList<UBAAmmissibilitaBO> listaEsitiUBA, List<String> listLog)
    {
    	try {			
			_em.clear();
//			_em.getTransaction().begin();
			
			for (UBAAmmissibilitaBO esito : listaEsitiUBA) {
				UBAAmmissibilitaPk pk = new UBAAmmissibilitaPk();
				pk.set_domanda(esito.get_domanda());
				pk.set_intervento(esito.get_intervento());
				
				UBAAmmissibilita esitoFind = _em.find(UBAAmmissibilita.class, pk);
				// se la riga é stata trovata su db la cancello 
				if(esitoFind != null) _em.remove(esitoFind);
				
				// inserisco la riga
				UBAAmmissibilita esitoEntity = esito.getUbaEntity();
				Utils.getLog().info(UBAAmmissibilitaBean.class.getSimpleName() + " - inserimentoMassivo: dopo getEntity");
				esitoEntity.set_user_inserimento(Utils.getCurrentUser());
				esitoEntity.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoEntity);
			}
			
//			_em.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(UBAAmmissibilitaBean.class.getSimpleName() + " - inserimentoMassivo: " + e.getMessage());
	    	return false;
		}
    }

    
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica indicata
	 * @param valoriAnagrafica
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<String> getListaValori(String valoriAnagrafica)
	{
		_em.clear();
		Query q = _em.createNamedQuery("ValoreAnagrafica.selectAnagrafica");
		q.setParameter("campo", valoriAnagrafica);
		List<ValoreAnagrafica> fornitura = q.getResultList();
		List<String> lista = new ArrayList<String>();
		for (ValoreAnagrafica valoreAnagrafica : fornitura) {
			lista.add(valoreAnagrafica.get_valore());
		}
		lista.add(0, "");
		return lista;
	}
}
