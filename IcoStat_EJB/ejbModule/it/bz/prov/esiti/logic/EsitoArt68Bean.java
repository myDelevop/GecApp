package it.bz.prov.esiti.logic;

import it.bz.prov.esiti.bobject.DomandaBO;
import it.bz.prov.esiti.bobject.ElencoEsitoArt68BO;
import it.bz.prov.esiti.bobject.EsitoArt68BO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.Domanda;
import it.bz.prov.esiti.entity.EsitoArt68;
import it.bz.prov.esiti.entity.EsitoArt68Pk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.IEsitoArt68;
import it.bz.prov.esiti.util.CheckData;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.ExcelReader;
import it.bz.prov.esiti.util.ExcelWriter;
import it.bz.prov.esiti.util.FileExcel;
import it.bz.prov.esiti.util.Utils;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Bean per la gestione back-end degli esiti impegni
 * 
 * @author bpettazzoni
 * 
 */

@Stateful
@SuppressWarnings("unchecked")
public class EsitoArt68Bean implements IEsitoArt68 {

	private static String COL_CUAA = "CUAA";
	private static String COL_DOMANDA_OPR = "Domanda OPR";
	private static String COL_CAMPAGNA = "Campagna";
	private static String COL_INTERVENTO = "Intervento";
	private static String COL_DATA_ISPEZIONE = "Data Ispezione";
	private static String COL_ESITO = "Esito";
	private static String COL_IMP_RIDUZ = "Imp. Riduzione";
	private static String COL_STATO = "Stato";
	private static String COL_NOTE = "Note";

	private ElencoEsitoArt68BO elencoEsitiArt68 = new ElencoEsitoArt68BO();

	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;

	/**
	 * aggiunge un esito alla lista
	 * 
	 * @param esitoArt68
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsito(EsitoArt68BO esitoArt68) {
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(esitoArt68,
				Costanti.ACTION_INSERT);
		if (!result.get_result())
			return result;

		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			esitoArt68.set_userInserimento(Utils.getCurrentUser());
			esitoArt68.set_dataInserimento(Utils.todayDate());
			EsitoArt68 esitoEntity = esitoArt68.getEntity();
			_em.clear();
//			_em.getTransaction().begin();
			_em.persist(esitoEntity);
//			_em.getTransaction().commit();
			// inserimento in elenco
			elencoEsitiArt68.addEsito(esitoArt68);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(
					EsitoArt68Bean.class.getSimpleName() + " - aggiungiEsito: "
							+ e.getMessage());
			return result;
		}
	}

	/**
	 * modifica un esito alla lista
	 * 
	 * @param esitoArt68
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsito(EsitoArt68BO esitoArt68) {
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(esitoArt68,
				Costanti.ACTION_MODIFY);
		if (!result.get_result())
			return result;

		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			EsitoArt68Pk pk = new EsitoArt68Pk();
			pk.set_cuaa(esitoArt68.get_cuaa());
			pk.set_campagna(esitoArt68.get_campagna());
			pk.set_domanda(esitoArt68.get_domanda());
			pk.set_intervento(esitoArt68.get_intervento());
			_em.clear();
			// recupero l'oggetto su db
			EsitoArt68 esitoFind = _em.find(EsitoArt68.class, pk);
			// se la riga non é stata trovata su db
			if (esitoFind == null) {
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			esitoArt68.set_userModifica(Utils.getCurrentUser());
			esitoArt68.set_dataModifica(Utils.todayDate());
			esitoArt68.setEntity(esitoFind);
//			_em.getTransaction().commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			Utils.getLog().error(
					this.getClass().getSimpleName() + ": " + e.getMessage());
			e.printStackTrace();
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(
					EsitoArt68Bean.class.getSimpleName() + " - modificaEsito: "
							+ e.getMessage());
			return result;
		}
	}

	/**
	 * cancella un esito dalla lista
	 * 
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoArt68BO esitoArt68) {
		OperationResultBO result = new OperationResultBO();
		try {
			_em.clear();
			EsitoArt68Pk pk = new EsitoArt68Pk();
			pk.set_cuaa(esitoArt68.get_cuaa());
			pk.set_campagna(esitoArt68.get_campagna());
			pk.set_domanda(esitoArt68.get_domanda());
			pk.set_intervento(esitoArt68.get_intervento());
			EsitoArt68 esitoFind = _em.find(EsitoArt68.class, pk);
			if (esitoFind == null) {
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			_em.remove(esitoFind);
//			_em.getTransaction().commit();
			// cancellazione dall'elenco
			elencoEsitiArt68.remove(esitoArt68);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(
					EsitoArt68Bean.class.getSimpleName() + " - cancellaEsito: "
							+ e.getMessage());
			return result;
		}
	}

	public List<EsitoArt68BO> getElencoEsitoArt68() {
		return this.elencoEsitiArt68.get_listEsitoArt68();
	}

	public void setElencoEsitoArt68(final ElencoEsitoArt68BO elencoEsitiArt68) {
		this.elencoEsitiArt68 = elencoEsitiArt68;
	}

	/**
	 * carica i dati degli esiti impegni
	 */
	public void loadData() {
		Query q = _em.createNamedQuery("EsitoArt68.selectAll");
		_em.clear();
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoArt68> fornitura = q.getResultList();
		elencoEsitiArt68.set_listEsitoArt68(fornitura);
	}

	/**
	 * restituisce la lista di tutte le campagne presenti
	 * 
	 * @return List<String>
	 */
	public List<String> getListCampagna() {
		Query q = _em.createNamedQuery("Domanda.distinctCampagna");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}

	/**
	 * restituisce la lista dei valori distinti per l'anagrafica indicata
	 * 
	 * @param valoriAnagrafica
	 * @return List<String>
	 */
	public List<String> getListaValori(String valoriAnagrafica) {
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


	/**
	 * Filtra i dati in base ai parametri di ricerca
	 * 
	 * @param parametersList
	 */
	public void filter(HashMap<String, String> parametersList) {
		_em.clear();
		Query q = _em.createNamedQuery("EsitoArt68.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoArt68> fornitura = q.getResultList();
		elencoEsitiArt68.set_listEsitoArt68(fornitura);
	}

	public DomandaBO getDomandaDU(String cuaa, String campagna) {
		_em.clear();
		Query q = _em
				.createNamedQuery("Campione.domForCuaaCampagnaMisuraCampArt68");
		// imposto i parametri della query
		q.setParameter("cuaa", cuaa);
		q.setParameter("campagna", campagna);
		q.setParameter("misura", "DU");
		List<Domanda> fornitura = q.getResultList();
		if (fornitura.size() > 0)
			return new DomandaBO(fornitura.get(0));
		else
			return null;
	}

	/**
	 * esporta i dati visualizzati su file excel
	 * 
	 * @param stream
	 *            é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream) {
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = getListaIntestazioni();

		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer, String>>();
		for (EsitoArt68BO esito : elencoEsitiArt68.get_listEsitoArt68()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, esito.get_cuaa());
			riga.put(1, esito.get_domanda());
			riga.put(2, esito.get_campagna());
			riga.put(3, esito.get_intervento());
			riga.put(4, esito.get_dataIspezioneStr());
			riga.put(5, esito.get_stato());
			riga.put(6, String.valueOf(esito.get_impRiduz()));
			riga.put(7, esito.get_esito());
			riga.put(8, esito.get_note());
			righe.add(riga);			
		}

		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}

	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList() {
		elencoEsitiArt68 = new ElencoEsitoArt68BO();
	}
	
	/**
	 * permette l'inserimento dei dati da file excel
	 * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
	 */
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
    	LinkedHashMap<Integer, String> listaColonna = EsitoArt68Bean.getListaIntestazioni();
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
    	ArrayList<EsitoArt68BO> listaEsiti = new ArrayList<EsitoArt68BO>(); 
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
    	for (EsitoArt68BO esito : listaEsiti) {
    		// utilizzo ACTION_MODIFY perchè così non fa la verifica sulla pk esistente
			OperationResultBO result = checkPreInsert(esito, Costanti.ACTION_MODIFY);
			// se il check ha trovato degli errori su questa riga
			if(!result.get_result()) 
			{
				complete_result = false;
				listLog.add("ERROR: domanda:" + esito.get_domanda() + " - errore: " + result.get_message());
			}			
		}
    	//5. verifiche non fatte per inserimento da maschera
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

	/***************************************************************************************
	 * METODI PRIVATI
	 ***************************************************************************************/

	/**
	 * Effettua dei controlli sulla coerenza dei dati prima di eseguire la
	 * scrittura su db (insert o update)
	 * 
	 * @param esitoArt68
	 * @param action
	 * @return OperationResultBO
	 */
	private OperationResultBO checkPreInsert(EsitoArt68BO esitoArt68, String action) {
		OperationResultBO result = new OperationResultBO();

		// 1. campagna valorizzata (non nulla e non uguale a spazio)
		if (!CheckData.checkString(esitoArt68.get_campagna())) {
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMPAGNA);
			return result;
		}

		// 2. CUAA valorizzato (non nullo e non uguale a spazio)
		if (!CheckData.checkString(esitoArt68.get_cuaa())) {
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA);
			return result;
		}

		// 3. CUAA con lunghezza compresa tra 11 e 16 caratteri
		if (!CheckData.checkCuaaLength(esitoArt68.get_cuaa())) {
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_LENGTH);
			return result;
		}

		// 4. cuaa deve essere presente in anagrafica
		_em.clear();
		Azienda aziendaFind = _em.find(Azienda.class, esitoArt68.get_cuaa());
		if (aziendaFind == null) {
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_MANCANTE);
			return result;
		}

		// 5. domanda non nulla
		if (!CheckData.checkString(esitoArt68.get_domanda())) {
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_NULLA);
			return result;
		}

		// 6. domanda esistente in anagrafica
		_em.clear();
		//Domanda domandaFind = _em.find(Domanda.class, esitoArt68.get_domanda());
		Query q = _em.createNamedQuery("Domanda.findDomanda");
		q.setParameter("idDomanda", esitoArt68.get_domanda());
		List<String> domandaFind = q.getResultList();
				
		if(domandaFind == null || domandaFind.size()==0) {
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_MANCANTE);
			return result;
		}

		// 7. intervento valorizzato (non nullo e non uguale a spazio)
		if (!CheckData.checkString(esitoArt68.get_intervento())) {
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_INTERVENTO_NULLO);
			return result;
		}

		// 9. la domanda indicata ha l'intervento e il sottointervento
		// specificati
//		_em.clear();
//		SottointerventoPk pkSott = new SottointerventoPk();
//		pkSott.set_domanda(esitoArt68.get_domanda());
//		pkSott.set_codiceIntervento(esitoArt68.get_intervento());

		// 10. domanda, intervento non devono essere già
		// presenti in tabella (é la pk)
		// (da fare solo per insert, no per modify)
		if (action.equals(Costanti.ACTION_INSERT)) {
			EsitoArt68Pk pk = new EsitoArt68Pk();
			pk.set_cuaa(esitoArt68.get_cuaa());
			pk.set_campagna(esitoArt68.get_campagna());
			pk.set_domanda(esitoArt68.get_domanda());
			pk.set_intervento(esitoArt68.get_intervento());
			_em.clear();
			EsitoArt68 esitoFind = _em.find(EsitoArt68.class, pk);
			if (esitoFind != null) {
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_PRESENTI);
				return result;
			}
		}

		// 14. le note devono avere dimensione massima di 500 caratteri
		if (!CheckData.checkNoteSize(esitoArt68.get_note())) {
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}

		// 15. controllo se la domanda è a campione
		DomandaBO domandaCampione = getDomandaDU(esitoArt68.get_cuaa(),esitoArt68.get_campagna());
		if (domandaCampione == null) {
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DOMANDA_NON_A_CAMPIONE);
				return result;
		}

		// se arrivo qui ha superato i controlli
		result.set_result(true);
		return result;
	}
	
	
	 /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel é l'oggetto che contiene i sati letti da file
     * @param lista 
     * @param logList
     * @return boolean 
     */
    private boolean getListFromExcel(FileExcel fileExcel, ArrayList<EsitoArt68BO> lista, List<String> logList)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	List<String> lstDomande = new ArrayList<String>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		EsitoArt68BO esito = new EsitoArt68BO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { esito.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DOMANDA_OPR)) { esito.set_domanda(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { esito.set_campagna(valore.replace(".0", "")); continue; }
				if(intestazione.equalsIgnoreCase(COL_INTERVENTO)) { esito.set_intervento(valore.replace(".0", "")); continue; }
				if(intestazione.equalsIgnoreCase(COL_STATO)) { esito.set_stato(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_IMP_RIDUZ)) { 
					if(valore != null && !valore.equals(""))						
						if(CheckData.checkFloat(valore))
							esito.set_impRiduz(Utils.getFloat(valore));
						else 
						{
							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " importo riduzione nel formato errato");
							return false;
						}
					 continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_ESITO)) { esito.set_esito(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DATA_ISPEZIONE)) 
				{ 
					if(valore != null && !valore.equals(""))
					{
						if(CheckData.checkStringDateFormat(valore))
							esito.set_dataIspezione(Utils.getDate(valore)); 
						else 
						{
							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " data ispezione nel formato errato");
							return false;
						}
					}
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_NOTE)) { esito.set_note(valore); continue; }
				
				// se la domanda non é stata specificata va letta dal campione
				if(esito.get_domanda().equals("")){
					DomandaBO domanda = getDomandaDU(esito.get_cuaa(), esito.get_campagna());
					esito.set_domanda(domanda.get_idDomanda());
				}			
				
			}
			// controllo per evitare di prendere righe vuote
			if(!esito.get_cuaa().equals("") || !esito.get_campagna().equals("") || !esito.get_domanda().equals("")){
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
     * Effettua dei controlli sulla coerenza dei dati prima di fare la import
     * @param listaArt68
     * @return OperationResultBO
     */
    private OperationResultBO checkForImport(List<EsitoArt68BO> listaArt68)
    {
    	// controllo che il valore dei flag sia uguale a quello in anagrafica
    	List<String> lista_valori_stato = getListaValori(Costanti.ANAGR_STATO);
    	List<String> lista_valori_esito = getListaValori(Costanti.ANAGR_ESITO);
    	// indicazione degli interventi ammessi
    	List<String> lista_valori_intervento = new ArrayList<String>();
    	lista_valori_intervento.add("170");
    	lista_valori_intervento.add("177");
    	    	
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (EsitoArt68BO esito : listaArt68) {
    		// stato
			if(!esito.get_stato().equals("") && !lista_valori_stato.contains(esito.get_stato()))
			{
				result.set_result(false);
				result.set_message("ERROR: Stato - valore non in anagrafica");
				break;
			}
			
			// esito
			if(!esito.get_esito().equals("") && !lista_valori_esito.contains(esito.get_esito()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito - valore non in anagrafica");
				break;
			}
			
			// intervento
			if(!esito.get_intervento().equals("") && !lista_valori_intervento.contains(esito.get_intervento()))
			{
				result.set_result(false);
				result.set_message("ERROR: Intervento - valore non in anagrafica");
				break;
			}
		
		}
    	return result;    	
    }

	/**
	 * prepara la lista di tutte le intestazioni delle colonne presenti nel file
	 * 
	 * @return LinkedHashMap<Integer, String>
	 */
	private static LinkedHashMap<Integer, String> getListaIntestazioni() {
		LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
		intestazione.put(0, COL_CUAA);
		intestazione.put(1, COL_DOMANDA_OPR);
		intestazione.put(2, COL_CAMPAGNA);
		intestazione.put(3, COL_INTERVENTO);
		intestazione.put(4, COL_DATA_ISPEZIONE);
		intestazione.put(5, COL_STATO);
		intestazione.put(6, COL_IMP_RIDUZ);
		intestazione.put(7, COL_ESITO);
		intestazione.put(8, COL_NOTE);
		return intestazione;
	}
	
	
	 /**
     * esegue l'inserimento di tutte le righe in una sola transazione
     * @param listaEsiti
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivo(ArrayList<EsitoArt68BO> listaEsiti, List<String> listLog)
    {
    	try {			
			_em.clear();
//			_em.getTransaction().begin();
			
			for (EsitoArt68BO esito : listaEsiti) {
				EsitoArt68Pk pk = new EsitoArt68Pk();
				pk.set_cuaa(esito.get_cuaa());
				pk.set_domanda(esito.get_domanda());
				pk.set_intervento(esito.get_intervento());
				pk.set_campagna(esito.get_campagna());
				EsitoArt68 esitoFind = _em.find(EsitoArt68.class, pk);
				// se la riga é stata trovata su db la cancello 
				if(esitoFind != null) _em.remove(esitoFind);
				
				// inserisco la riga
				EsitoArt68 esitoEntity = esito.getEntity();
				Utils.getLog().info(EsitoArt68Bean.class.getSimpleName() + " - inserimentoMassivo: dopo getEntity");
				esitoEntity.set_user_inserimento(Utils.getCurrentUser());
				esitoEntity.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoEntity);
			}
			
//			_em.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(EsitoImpegniBean.class.getSimpleName() + " - inserimentoMassivo: " + e.getMessage());
	    	return false;
		}
    }

}
