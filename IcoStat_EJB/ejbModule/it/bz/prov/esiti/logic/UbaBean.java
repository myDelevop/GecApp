package it.bz.prov.esiti.logic;

/**
 * Bean per la gestione back-end degli esiti sui controlli uba
 * 
 * @author bpettazzoni
 *
 */

import it.bz.prov.esiti.bobject.ElencoUbaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.UbaBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.Domanda;
import it.bz.prov.esiti.entity.DomandaPk;
import it.bz.prov.esiti.entity.Uba;
import it.bz.prov.esiti.entity.UbaPk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.IUba;
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

@Stateful
@SuppressWarnings("unchecked")
public class UbaBean implements IUba {
	
	private static String COL_CUAA = "CUAA";
	private static String COL_DOMANDA = "Domanda OPR";
	private static String COL_CAMPAGNA = "Campagna";
	private static String COL_INTERVENTO = "Intervento";
	private static String COL_ESITO = "Esito";
	private static String COL_UBA_RICH = "UBA Richieste";
	private static String COL_UBA_ACC = "UBA Accertate";
	private static String COL_DIFFERENZA = "Differenza";
	private static String COL_CAPI_RICH = "Capi Richiesti";
	private static String COL_CAPI_ACC = "Capi Accertati";
	private static String COL_NOTE = "Note";
	
	private ElencoUbaBO elencoUba = new ElencoUbaBO();
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	
	/**
	 * aggiunge una uba alla lista
	 * @param uba
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiUba(UbaBO uba)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(uba, Costanti.ACTION_INSERT);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			uba.set_userInserimento(Utils.getCurrentUser());
			uba.set_dataInserimento(Utils.todayDate());
			Uba ubaEntity = uba.getUbaEntity();
			_em.clear();
//			_em.getTransaction().begin();
			_em.persist(ubaEntity);
//			_em.getTransaction().commit();
			// inserimento in elenco
			elencoUba.addEsito(uba);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(UbaBean.class.getSimpleName() + " - aggiungiUba: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * modifica un elemento della lista
	 * @param uba
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaUba(UbaBO uba)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(uba, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			UbaPk pk = new UbaPk();
			pk.set_intervento(uba.get_intervento());
			pk.set_domanda(uba.get_domanda());
			_em.clear();
			Uba ubaFind = _em.find(Uba.class, pk);
			// se la riga non é stata trovata su db
			if(ubaFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			uba.set_userModifica(Utils.getCurrentUser());
			uba.set_dataModifica(Utils.todayDate());
			uba.setEntity(ubaFind);	
//			_em.getTransaction().commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(UbaBean.class.getSimpleName() + " - modificaUba: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * cancella un elemento della lista
	 * @param uba
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaUba(UbaBO uba)
	{
		OperationResultBO result = new OperationResultBO();
		try {			
			_em.clear();
			UbaPk pk = new UbaPk();
			pk.set_domanda(uba.get_domanda());
			pk.set_intervento(uba.get_intervento());
			Uba ubaFind = _em.find(Uba.class, pk);
			// se la riga non é stata trovata su db
			if(ubaFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			_em.remove(ubaFind);
//			_em.getTransaction().commit();
			// cancellazione dall'elenco
			elencoUba.remove(uba);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(UbaBean.class.getSimpleName() + " - cancellaUba: " + e.getMessage());
			return result;
		}		
	} 

    public List<UbaBO> getElencoUba()
    {
        return this.elencoUba.get_listUba();
    }

    public void setElencoUba(final ElencoUbaBO uba)
    {
        this.elencoUba = uba;
    }
    
    /**
	 * carica i dati degli esiti UBA 
	 */
	public void loadData()
	{
		Query q = _em.createNamedQuery("UBA.selectAll");
		_em.clear();
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<Uba> fornitura = q.getResultList();
		elencoUba.set_listUba(fornitura);
	}
	
	
	/**
	 * restituisce la lista dei valori distinti per l'esito
	 * @return List<String>
	 */
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
	public List<String> getListCampagna()
	{
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.distinctCampagna");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList)
    {    	
		_em.clear();
    	Query q = _em.createNamedQuery("UBA.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);		
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<Uba> fornitura = q.getResultList();
		elencoUba.set_listUba(fornitura);
    }
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream)
	{
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = UbaBean.getListaIntestazioni();
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (UbaBO uba : elencoUba.get_listUba()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, uba.get_cuaa());
			riga.put(1, uba.get_domanda());
			riga.put(2, uba.get_campagna());
			riga.put(3, uba.get_intervento());
			riga.put(4, uba.get_esito());
			riga.put(5, uba.get_ubaRich());
			riga.put(6, uba.get_ubaAcc());
			riga.put(7, uba.get_ubaDiff());
			riga.put(8, uba.get_capiRich());
			riga.put(9, uba.get_capiAcc());
			riga.put(10, uba.get_note());
			righe.add(riga);
		}
		
		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}
	
	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList()
	{
		elencoUba = new ElencoUbaBO();
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
    	System.out.println("insertFromFile: File Estratto");
    	
    	//2. verifico la presenza di tutte le intestazioni corrette
    	boolean check = true;
    	LinkedHashMap<Integer, String> listaColonna = UbaBean.getListaIntestazioni();
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
    	
    	System.out.println("insertFromFile: intestazioni corrette");
    	
    	//3. creazione della lista di uba
    	ArrayList<UbaBO> listaUba = getListFromExcel(fileExcel);
    	if(listaUba.size() ==0)
    	{
			listLog.add("ERROR: il file non contiene righe di dato");
			return false;
		}
    	System.out.println("insertFromFile: lista uba creata");
    	
    	//3.a verifica del numero di righe
    	if(listaUba.size()>Costanti.IMPORT_NUM_ROW_MAX)
    	{
			listLog.add("ERROR: il file può contenere al massimo " + Costanti.IMPORT_NUM_ROW_MAX + "righe da importare");
			return false;
    	}
    	
    	//4. verifica correttezza valori    	
    	//5. verifica regole complesse
    	boolean complete_result = true;
    	for (UbaBO ubaBO : listaUba) {
    		// utilizzo ACTION_MODIFY perchè così non fa la verifica sulla pk esistente
			OperationResultBO result = checkPreInsert(ubaBO, Costanti.ACTION_MODIFY);
			// se il check ha trovato degli errori su questa riga
			if(!result.get_result()) 
			{
				complete_result = false;
				listLog.add("ERROR: domanda:" + ubaBO.get_domanda() + " - errore: " + result.get_message());
			}			
		}
    	if(complete_result) listLog.add("INFO: Verifica coerenza valori delle righe terminata con successo");
    	else 
    	{
    		listLog.add("ERROR: Terminata l'operazione. I dati non sono stati importati.");
    		return false; // termino l'esecuzione del metodo
    	}
    	System.out.println("insertFromFile: verifiche sui dati completate");
    	
    	//6. inserimento dati
    	complete_result = inserimentoMassivo(listaUba, listLog);
    	
    	System.out.println("insertFromFile: FINE");
    	
    	if(complete_result) listLog.add("INFO: upload dei dati terminato con successo. Numero righe contenute nel file: "+ listaUba.size());
    	else listLog.add("ERROR: l'operazione di inserimento dati non é terminata correttamente.");
    	return true;
    }
	
	
	/***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update)
     * @param uba
     * @param action
     * @return OperationResultBO
     */
    private OperationResultBO checkPreInsert(UbaBO uba, String action)
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
		if(!uba.get_ubaDiff().equals(""))
			if(!CheckData.checkFloat(uba.get_ubaDiff()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_UBA_DIFF);
				return result;
			}
    	
    	// 10. domanda e intervento non devono essere già presenti in tabella (é la pk) 
		//(da fare solo per insert, no per modify)
		if(action.equals(Costanti.ACTION_INSERT))
		{
			UbaPk pk = new UbaPk();
			pk.set_domanda(uba.get_domanda());
			pk.set_intervento(uba.get_intervento());
			_em.clear();
			Uba ubaFind = _em.find(Uba.class, pk);
			if(ubaFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_PRESENTI);
				return result;
			}
		}
		
		//11. verifica se la domanda ha quell'intervento in domanda
		_em.clear();
    	Query q = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
    	
		// imposto i parametri della query
    	q.setParameter("domanda", uba.get_domanda());
		q.setParameter("intervento", uba.get_intervento());
		
		List<String> fornitura = q.getResultList();
		if(fornitura== null || fornitura.size() == 0 )
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_MANCANTE);
			return result;
		}
		
		//12. check valore capi richiesti 
		if(!uba.get_capiRich().equals(""))
			if(!CheckData.checkFloat(uba.get_capiRich()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_CAPI_DICH);
				return result;
			}
		
		//13. check valore capi accertati 
		if(!uba.get_capiAcc().equals(""))
			if(!CheckData.checkFloat(uba.get_capiAcc()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_CAPI_ACC);
				return result;
			}
		
		//14. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(uba.get_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
		
		//15. l'intervento deve essere 10.2 o 214.2
		if(!uba.get_intervento().equals("214.4") && !uba.get_intervento().equals("10.2"))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_MANCANTE);
			return result;
		}
		
		// se arrivo qui ha superato i controlli
		result.set_result(true);
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
		intestazione.put(1, COL_DOMANDA);
		intestazione.put(2, COL_CAMPAGNA);
		intestazione.put(3, COL_INTERVENTO);
		intestazione.put(4, COL_ESITO);
		intestazione.put(5, COL_UBA_RICH);
		intestazione.put(6, COL_UBA_ACC);
		intestazione.put(7, COL_DIFFERENZA);
		intestazione.put(8, COL_CAPI_RICH);
		intestazione.put(9, COL_CAPI_ACC);
		intestazione.put(10, COL_NOTE);
    	return intestazione;
    }
    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel é l'oggetto che contiene i ati letti da file
     * @return ArrayList<UbaBO>
     */
    private static ArrayList<UbaBO> getListFromExcel(FileExcel fileExcel)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	ArrayList<UbaBO> lista = new ArrayList<UbaBO>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		UbaBO uba = new UbaBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { uba.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DOMANDA)) { uba.set_domanda(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { uba.set_campagna(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_INTERVENTO)) { uba.set_intervento(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ESITO)) { uba.set_esito(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_UBA_RICH)) { uba.set_ubaRich(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_UBA_ACC)) { uba.set_ubaAcc(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DIFFERENZA)) { uba.set_ubaDiff(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAPI_RICH)) { uba.set_capiRich(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAPI_ACC)) { uba.set_capiAcc(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NOTE)) { uba.set_note(valore); continue; }
			}
			// controllo per evitare di prendere righe vuote
			if(!uba.get_cuaa().equals("") && !uba.get_campagna().equals("")){
				lista.add(uba);
				// popolamento delle liste utilizzate per dettagliare il log dell'applicazione
				if(lstCuaa.isEmpty()){
					lstCuaa.add(uba.get_cuaa());
				}else if(!lstCuaa.contains(uba.get_cuaa())) lstCuaa.add(uba.get_cuaa());
			}
		}
    	
    	return lista;
    }


    /**
     * esegue l'inserimento di tutte le righe in una sola transazione
     * @param listaUba
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivo(ArrayList<UbaBO> listaUba, List<String> listLog)
    {
    	try {			
			_em.clear();
//			_em.getTransaction().begin();
			
			for (UbaBO uba : listaUba) {
				UbaPk pk = new UbaPk();
				pk.set_domanda(uba.get_domanda());
				pk.set_intervento(uba.get_intervento());
				Uba ubaFind = _em.find(Uba.class, pk);
				// se la riga é stata trovata su db la cancello 
				if(ubaFind != null) _em.remove(ubaFind);
				
				// inserisco la riga
				Uba ubaEntity = uba.getUbaEntity();
				ubaEntity.set_user_inserimento(Utils.getCurrentUser());
				ubaEntity.set_data_inserimento(Utils.todayDate());
				_em.persist(ubaEntity);
			}
			
//			_em.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(UbaBean.class.getSimpleName() + " - inserimentoMassivo: " + e.getMessage());
	    	return false;
		}
    }
}
