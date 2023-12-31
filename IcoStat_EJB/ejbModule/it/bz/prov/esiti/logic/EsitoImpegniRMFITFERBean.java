package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.ElencoEsitoImpegniRMFITFERBO;
import it.bz.prov.esiti.bobject.EsitoImpegniRMFITFERBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.EsitoImpegniRMFITFER;
import it.bz.prov.esiti.entity.EsitoImpegniRMFITFERPk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.IEsitoImpegniRMFITFER;
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
 * Bean per la gestione back-end degli esiti impegni RM FIT e FER
 * 
 * @author lattana
 *
 */

@Stateful
@SuppressWarnings("unchecked")
public class EsitoImpegniRMFITFERBean implements IEsitoImpegniRMFITFER{
	
	private static String COL_CUAA =  "CUAA";
	private static String COL_DOMANDA_OPR =  "Domanda OPR";
	private static String COL_CAMPAGNA =  "Campagna";
	private static String COL_MISURA =  "Misura";
	private static String COL_ESITO_OPPAB =  "Esito OPPAB";
	private static String COL_STATO =  "Stato";
	private static String COL_PERC_RID_FIT =  "% riduzione FIT";
	private static String COL_PERC_RID_FER =  "% riduzione FER";
	private static String COL_PERC_TOTALE_OPPAB =  "% riduzione totale OPPAB";
	private static String COL_PERC_COMMISS_RIESAME =  "% riduzione commissione Riesame";
	private static String COL_STATO_POST_COMMISS_RIESAME =  "Stato post commissione Riesame";
	private static String COL_DATA_COMM_RIESAME =  "Data Commissione Riesame";
	private static String COL_DATA_CONTR = "Data del Controllo";
	private static String COL_ESCLUSIONE = "Esclusione";
	private static String COL_PROGR_ACCERT_ESCLUSIONE = "Progressivo Accertamento Esclusione";
	private static String COL_REITERAZIONE = "Reiterazione";
	private static String COL_PROGR_ACC_REITERAZ = "Progressivo Accertamento Reiterazione";
	private static String COL_PORTATA = "Portata";
	private static String COL_GRAVITA = "Gravit�";
	private static String COL_DURATA = "Durata";
	private static String COL_PORTATA_NOTE = "Note Portata";
	private static String COL_GRAVITA_NOTE = "Note Gravit�";
	private static String COL_DURATA_NOTE ="Note Durata";
	private static String COL_ESCLUSIONE_NOTE ="Note Esclusione";
	private static String COL_NOTE =  "Note";
	
	private ElencoEsitoImpegniRMFITFERBO elencoEsitoImpegni = new ElencoEsitoImpegniRMFITFERBO();
	
//	@PersistenceContext(unitName = "jpa_definition")
//	private EntityManager _em = Persistence.createEntityManagerFactory("jpa_definition").createEntityManager();
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	
	/**
	 * aggiunge un esito alla lista
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsito(EsitoImpegniRMFITFERBO esitoImpegni)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(esitoImpegni, Costanti.ACTION_INSERT);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			esitoImpegni.set_userInserimento(Utils.getCurrentUser());
			esitoImpegni.set_dataInserimento(Utils.todayDate());
			EsitoImpegniRMFITFER esitoEntity = esitoImpegni.getEntity();
//			esitoEntity.set_user_inserimento(Utils.getCurrentUser());
//			esitoEntity.set_data_inserimento(Utils.todayDate());
			_em.clear();
//			_em.getTransaction().begin();
			_em.persist(esitoEntity);
//			_em.getTransaction().commit();
			// inserimento in elenco
			elencoEsitoImpegni.addEsito(esitoImpegni);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoImpegniRMFITFERBean.class.getSimpleName() + " - aggiungiEsito: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * modifica un esito alla lista
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsito(EsitoImpegniRMFITFERBO esitoImpegni)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(esitoImpegni, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			EsitoImpegniRMFITFERPk pk = new EsitoImpegniRMFITFERPk();
			pk.set_domanda(esitoImpegni.get_domanda());
			pk.set_misura(esitoImpegni.get_misura());
			
			_em.clear();
			// recupero l'oggetto su db
			EsitoImpegniRMFITFER esitoFind = _em.find(EsitoImpegniRMFITFER.class, pk);
			// se la riga non � stata trovata su db
			if(esitoFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			esitoImpegni.set_userModifica(Utils.getCurrentUser()); 
			esitoImpegni.set_dataModifica(Utils.todayDate());
			esitoImpegni.setEntity(esitoFind);
//			_em.getTransaction().commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			Utils.getLog().error(this.getClass().getSimpleName() + ": " + e.getMessage());
			e.printStackTrace();
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoImpegniRMFITFERBean.class.getSimpleName() + " - modificaEsito: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * cancella un esito dalla lista
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoImpegniRMFITFERBO esitoImpegni)
	{
		OperationResultBO result = new OperationResultBO();
		try {
			_em.clear();
			EsitoImpegniRMFITFERPk pk = new EsitoImpegniRMFITFERPk();
			pk.set_domanda(esitoImpegni.get_domanda());
			pk.set_misura(esitoImpegni.get_misura());
			
			EsitoImpegniRMFITFER esitoFind = _em.find(EsitoImpegniRMFITFER.class, pk);
			if(esitoFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			_em.remove(esitoFind);
//			_em.getTransaction().commit();
			// cancellazione dall'elenco
			elencoEsitoImpegni.remove(esitoImpegni);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoImpegniRMFITFERBean.class.getSimpleName() + " - cancellaEsito: " + e.getMessage());
			return result;
		}
	}

    public List<EsitoImpegniRMFITFERBO> getElencoEsitoImpegni()
    {
        return this.elencoEsitoImpegni.get_listEsitoImpegniRMFITFER();
    }

    public void setElencoEsitoImpegni(final ElencoEsitoImpegniRMFITFERBO elencoEsitoImpegni)
    {
        this.elencoEsitoImpegni = elencoEsitoImpegni;
    }
    
    
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData()
	{
		Query q = _em.createNamedQuery("EsitoImpegniRMFITFER.selectAll");
		_em.clear();
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoImpegniRMFITFER> fornitura = q.getResultList();
		elencoEsitoImpegni.set_listEsitoImpegni(fornitura);		
	}
	
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna(){
		Query q = _em.createNamedQuery("Domanda.distinctCampagna");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica indicata
	 * @param valoriAnagrafica
	 * @return List<String>
	 */
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
	
	/**
	 * restituisce la lista di tutte le misure presenti
	 * @return List<String>
	 */
	public List<String> getListMisura(){
		Query q = _em.createNamedQuery("Sottointervento.distinctMisura");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
	 * restituisce la lista delle misure presenti per una specifica campagna
	 * @return List<String>
	 */
	public List<String> getListMisuraForCampagna(String campagna){
		Query q = _em.createNamedQuery("Sottointervento.MisuraForCampagnaWithoutDU");
		q.setParameter("campagna", campagna);
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
    	Query q = _em.createNamedQuery("EsitoImpegniRMFITFER.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);		
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoImpegniRMFITFER> fornitura = q.getResultList();
		elencoEsitoImpegni.set_listEsitoImpegni(fornitura);
    }
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream � lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream)
	{
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = getListaIntestazioni();
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (EsitoImpegniRMFITFERBO esito : elencoEsitoImpegni.get_listEsitoImpegniRMFITFER()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, esito.get_cuaa());
			riga.put(1, esito.get_domanda());
			riga.put(2, esito.get_campagna());
			riga.put(3, esito.get_misura());
			riga.put(4, esito.get_esito_oppab());
			riga.put(5, esito.get_stato());
			riga.put(6, esito.get_percRidFIT());
			riga.put(7, esito.get_percRidFER());
			riga.put(8, esito.get_percTotOPPAB());
			riga.put(9, esito.get_percCommissRiesame());	
			riga.put(10, esito.get_statoPostCommissRiesame());
			riga.put(11, esito.get_dataCommissRiesameStr());
			riga.put(12, esito.get_data_contrStr());
			riga.put(13, esito.get_esclusione());
			riga.put(14, esito.get_progr_accert_esclusione());
			riga.put(15, esito.get_esclusione_note());
			riga.put(16, esito.get_reiterazione());
			riga.put(17, esito.get_progr_accert_reiteraz());
			riga.put(18, esito.get_portata());
			riga.put(19, esito.get_portata_note());
			riga.put(20, esito.get_gravita());
			riga.put(21, esito.get_gravita_note());
			riga.put(22, esito.get_durata());
			riga.put(23, esito.get_durata_note());
			riga.put(24, esito.get_note());
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
		elencoEsitoImpegni = new ElencoEsitoImpegniRMFITFERBO();
	}
	
	
	/**
	 * permette l'inserimento dei dati da file excel
	 * @param pathFile � il percorso del file temporaneo salvato
	 * @param listLog � la lista dei log da visualizzare
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
    	LinkedHashMap<Integer, String> listaColonna = EsitoImpegniRMFITFERBean.getListaIntestazioni();
    	for (String colonna : listaColonna.values()) {
			boolean result = fileExcel.hasColonna(colonna);
			if(!result)
			{
				listLog.add("ERROR: La colonna " + colonna + " non � presente nel file");
				check = false;
			}
		}
    	if(!check) return false;
    	else listLog.add("INFO: I controlli di coerenza sulle colonne del file hanno dato esito positivo");
    	
    	//3. creazione della lista di esiti
    	ArrayList<EsitoImpegniRMFITFERBO> listaEsiti = new ArrayList<EsitoImpegniRMFITFERBO>(); 
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
    	for (EsitoImpegniRMFITFERBO esito : listaEsiti) {
    		// utilizzo ACTION_MODIFY perch� cos� non fa la verifica sulla pk esistente
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
    	else listLog.add("ERROR: l'operazione di inserimento dati non � terminata correttamente.");
    	return complete_result;
    }
    
	/***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	
    
	/**
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update)
     * @param esito
     * @param action
     * @return OperationResultBO
     */
    private OperationResultBO checkPreInsert(EsitoImpegniRMFITFERBO esito, String action)
    {
    	OperationResultBO result = new OperationResultBO();
    	
    	//1. campagna valorizzata (non nulla e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_campagna()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMPAGNA);
			return result;
		}
    	
    	//2. CUAA valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA);
			return result;
		}
    	
    	//3. CUAA con lunghezza compresa tra 11 e 16 caratteri
    	if(!CheckData.checkCuaaLength(esito.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_LENGTH);
			return result;
		}
    	
    	// 4. cuaa deve essere presente in anagrafica
		_em.clear();
		Azienda aziendaFind = _em.find(Azienda.class, esito.get_cuaa());
		if(aziendaFind == null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_MANCANTE);
			return result;
		}
		
		//5. domanda non nulla
		if(!CheckData.checkString(esito.get_domanda()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_NULLA);
			return result;
		}
		
		//6. domanda esistente in anagrafica
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.findDomanda");
		q.setParameter("idDomanda", esito.get_domanda());
		List<String> domandaFind = q.getResultList();
				
		if(domandaFind == null || domandaFind.size()==0)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_MANCANTE);
			return result;
		}
		
		//7. misura valorizzata (non nulla e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_misura()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_MISURA_NULLA);
			return result;
		}
    	
    	
    	//9. la domanda indicata ha la misura specificata
    	_em.clear();
    	Query q1 = _em.createNamedQuery("Domanda.misuraForDomanda");
		q1.setParameter("idDomanda", esito.get_domanda());
		q1.setParameter("misura", esito.get_misura());
		List<String> domandaEntity = q1.getResultList();
				
		if(domandaEntity == null || domandaEntity.size()==0)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_MISURA_NON_CORRETTA);
			return result;
		}
    	
    	// 10. domanda e misura non devono essere gi� presenti in tabella (� la pk) 
		//(da fare solo per insert, no per modify)
		if(action.equals(Costanti.ACTION_INSERT))
		{
			EsitoImpegniRMFITFERPk pk = new EsitoImpegniRMFITFERPk();
			pk.set_domanda(esito.get_domanda());
			pk.set_misura(esito.get_misura());
			_em.clear();
			EsitoImpegniRMFITFER esitoFind = _em.find(EsitoImpegniRMFITFER.class, pk);
			if(esitoFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DOMANDA_MISURA_PRESENTI);
				return result;
			}
		}
		
		//11. percentuale di riduzione totale OPPAB deve essere un valore numerico
		if(esito.get_percTotOPPAB()!= null && !esito.get_percTotOPPAB().equals(""))
			if(!CheckData.checkFloat(esito.get_percTotOPPAB()))
	    	{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
				return result;
			}
		
		//12. percentuale di riduzione commissione di riesame deve essere un valore numerico
		if(esito.get_percCommissRiesame()!= null && !esito.get_percCommissRiesame().equals(""))
			if(!CheckData.checkFloat(esito.get_percCommissRiesame()))
	    	{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
				return result;
			}
		
		//13. percentuale di riduzione FIT deve essere un valore numerico
				if(esito.get_percRidFIT()!= null && !esito.get_percRidFIT().equals(""))
					if(!CheckData.checkFloat(esito.get_percRidFIT()))
			    	{
						result.set_result(false);
						result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
						return result;
					}
				
		//14. percentuale di riduzione FER deve essere un valore numerico
				if(esito.get_percRidFER()!= null && !esito.get_percRidFER().equals(""))
					if(!CheckData.checkFloat(esito.get_percRidFER()))
			    	{
						result.set_result(false);
						result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
						return result;
					}
				
				
		//15. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(esito.get_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
		/* EMAIL CHRISTINE KLOTZ DEL 11/04/2017
    	//16. data primo controllo non nulla
    	if(!CheckData.checkString(esito.get_data_contrStr()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DATA_CONTROLLO_IMPEGNI_RMFITFER_NULLO);
			return result;
		}
    	*/
    	//17. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(esito.get_esclusione_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
		
		//18. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(esito.get_portata_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
		
		//19. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(esito.get_gravita_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
		
		//20. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(esito.get_durata_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
    			
		// se arrivo qui ha superato i controlli
		result.set_result(true);
    	return result;
    }

    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di fare la import
     * @param listaImpegni
     * @return OperationResultBO
     */
    private OperationResultBO checkForImport(List<EsitoImpegniRMFITFERBO> listaImpegni)
    {
    	// controllo che il valore dei flag sia uguale a quello in anagrafica
    	List<String> lista_valori_stato = getListaValori(Costanti.ANAGR_STATO);
    	List<String> lista_valori_esito = getListaValori(Costanti.ANAGR_ESITO);
    	List<String> lista_esclusione = getListaValori(Costanti.ANAGR_INTENZIONALITA);
    	List<String> lista_progr_acc = getListaValori(Costanti.ANAGR_PROGRESSIVO_ACCERTAMENTO);
    	List<String> lista_reiterazione = getListaValori(Costanti.ANAGR_REITERAZIONE);
		List<String> lista_durata = getListaValori(Costanti.ANAGR_DURATA);
    	List<String> lista_gravita = getListaValori(Costanti.ANAGR_GRAVITA);
		List<String> lista_portata = getListaValori(Costanti.ANAGR_PORTATA);
		//Riutilizzo questa costante perch� contiene gi� i valori SI/NO
		List<String> lista_segnalazione = getListaValori(Costanti.ANAGR_AZIONE_CORRETTIVA_RICHIESTA);
		
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (EsitoImpegniRMFITFERBO esito : listaImpegni) {
    		// stato
			if(!esito.get_stato().equals("") && !lista_valori_stato.contains(esito.get_stato()))
			{
				result.set_result(false);
				result.set_message("ERROR: Stato - valore non in anagrafica");
				break;
			}
			// stato post commissione
			if(!esito.get_statoPostCommissRiesame().equals("") && 
					!lista_valori_stato.contains(esito.get_statoPostCommissRiesame()))
			{
				result.set_result(false);
				result.set_message("ERROR: Stato post commissione - valore non in anagrafica");
				break;
			}
			// esito OPPAB
			if(!esito.get_esito_oppab().equals("") && !lista_valori_esito.contains(esito.get_esito_oppab()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito OPPAB - valore non in anagrafica");
				break;
			}

			// Esclusione
			if(!esito.get_esclusione().equals("") && 
						!lista_esclusione.contains(esito.get_esclusione()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esclusione - valore non in anagrafica");
				break;
			}
			// progr. accert. Esclusione
			if(!esito.get_progr_accert_esclusione().equals("") && 
						!lista_progr_acc.contains(esito.get_progr_accert_esclusione()))
			{
				result.set_result(false);
				result.set_message("ERROR: progr. accert. Esclusione - valore non in anagrafica");
				break;
			}
			// reiterazione
			if(!esito.get_reiterazione().equals("") && 
						!lista_reiterazione.contains(esito.get_reiterazione()))
			{
				result.set_result(false);
				result.set_message("ERROR: reiterazione - valore non in anagrafica");
				break;
			}
			// progr. accert. reiterazione
			if(!esito.get_progr_accert_reiteraz().equals("") && 
						!lista_progr_acc.contains(esito.get_progr_accert_reiteraz()))
			{
				result.set_result(false);
				result.set_message("ERROR: progr. accert. reiterazione - valore non in anagrafica");
				break;
			}
			// portata
			if(!esito.get_portata().equals("") && 
						!lista_portata.contains(esito.get_portata()))
			{
				result.set_result(false);
				result.set_message("ERROR: portata - valore non in anagrafica");
				break;
			}
			// gravita
			if(!esito.get_gravita().equals("") && 
						!lista_gravita.contains(esito.get_gravita()))
			{
				result.set_result(false);
				result.set_message("ERROR: gravita - valore non in anagrafica");
				break;
			}
			// durata
			if(!esito.get_durata().equals("") && 
						!lista_durata.contains(esito.get_durata()))
			{
				result.set_result(false);
				result.set_message("ERROR: durata - valore non in anagrafica");
				break;
			}
			// Segnalazione
			if(!esito.get_segnalazione().equals("") && 
						!lista_segnalazione.contains(esito.get_segnalazione()))
			{
				result.set_result(false);
				result.set_message("ERROR: Segnalazione - valore non in anagrafica");
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
		intestazione.put(1, COL_DOMANDA_OPR);
		intestazione.put(2, COL_CAMPAGNA);
		intestazione.put(3, COL_MISURA);
		intestazione.put(4, COL_ESITO_OPPAB);
		intestazione.put(5, COL_STATO);
		intestazione.put(6, COL_PERC_RID_FIT);
		intestazione.put(7, COL_PERC_RID_FER);
		intestazione.put(8, COL_PERC_TOTALE_OPPAB);
		intestazione.put(9, COL_PERC_COMMISS_RIESAME);
		intestazione.put(10, COL_STATO_POST_COMMISS_RIESAME);
		intestazione.put(11, COL_DATA_COMM_RIESAME);		
		intestazione.put(12, COL_DATA_CONTR);
		intestazione.put(13, COL_ESCLUSIONE); 
		intestazione.put(14, COL_PROGR_ACCERT_ESCLUSIONE);
		intestazione.put(15, COL_ESCLUSIONE_NOTE);
		intestazione.put(16, COL_REITERAZIONE); 
		intestazione.put(17, COL_PROGR_ACC_REITERAZ); 
		intestazione.put(18, COL_PORTATA); 
		intestazione.put(19, COL_PORTATA_NOTE);
		intestazione.put(20, COL_GRAVITA);
		intestazione.put(21, COL_GRAVITA_NOTE); 
		intestazione.put(22, COL_DURATA); 
		intestazione.put(23, COL_DURATA_NOTE);
		intestazione.put(24, COL_NOTE);
		
    	return intestazione;
    }
    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel � l'oggetto che contiene i sati letti da file
     * @param lista 
     * @param logList
     * @return boolean 
     */
    private static boolean getListFromExcel(FileExcel fileExcel, ArrayList<EsitoImpegniRMFITFERBO> lista, List<String> logList)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	List<String> lstDomande = new ArrayList<String>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		EsitoImpegniRMFITFERBO esito = new EsitoImpegniRMFITFERBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { esito.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DOMANDA_OPR)) { esito.set_domanda(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { esito.set_campagna(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_MISURA)) { esito.set_misura(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ESITO_OPPAB)) { esito.set_esito_oppab(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_STATO)) { esito.set_stato(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_RID_FIT)) { esito.set_percRidFIT(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_RID_FER)) { esito.set_percRidFER(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_TOTALE_OPPAB)) { esito.set_percTotOPPAB(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_COMMISS_RIESAME)) { esito.set_percCommissRiesame(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_STATO_POST_COMMISS_RIESAME)) { esito.set_statoPostCommissRiesame(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DATA_COMM_RIESAME)) 
				{ 
					if(valore != null && !valore.equals(""))
					{
						if(CheckData.checkStringDateFormat(valore))
							esito.set_dataCommissRiesame(Utils.getDate(valore)); 
						else 
						{
							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " data commissione di riesame nel formato errato");
							return false;
						}
					}
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_DATA_CONTR)) 
				{ 
					if(valore != null && !valore.equals(""))
					{
						if(CheckData.checkStringDateFormat(valore))
							esito.set_data_contr(Utils.getDate(valore)); 
						else 
						{
							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " data del controllo nel formato errato");
							return false;
						}
					}
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_ESCLUSIONE)) { esito.set_esclusione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PROGR_ACCERT_ESCLUSIONE)) { esito.set_progr_accert_esclusione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_REITERAZIONE)) { esito.set_reiterazione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PROGR_ACC_REITERAZ)) { esito.set_progr_accert_reiteraz(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PORTATA)) { esito.set_portata(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_GRAVITA)) { esito.set_gravita(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DURATA)) { esito.set_durata(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PORTATA_NOTE)) { esito.set_portata_note(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_GRAVITA_NOTE)) { esito.set_gravita_note(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DURATA_NOTE)) { esito.set_durata_note(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ESCLUSIONE_NOTE)) { esito.set_esclusione_note(valore); continue; }
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
			
			//Segnalazione di default a NO per campione
			esito.set_segnalazione("NO");
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
    private boolean inserimentoMassivo(ArrayList<EsitoImpegniRMFITFERBO> listaEsiti, List<String> listLog)
    {
    	try {			
			_em.clear();
//			_em.getTransaction().begin();
			
			for (EsitoImpegniRMFITFERBO esito : listaEsiti) {
				EsitoImpegniRMFITFERPk pk = new EsitoImpegniRMFITFERPk();
				pk.set_domanda(esito.get_domanda());
				pk.set_misura(esito.get_misura());
				
				EsitoImpegniRMFITFER esitoFind = _em.find(EsitoImpegniRMFITFER.class, pk);
				// se la riga � stata trovata su db la cancello 
				if(esitoFind != null) _em.remove(esitoFind);
				
				// inserisco la riga
				EsitoImpegniRMFITFER esitoEntity = esito.getEntity();
				Utils.getLog().info(EsitoImpegniRMFITFERBean.class.getSimpleName() + " - inserimentoMassivo: dopo getEntity");
				esitoEntity.set_user_inserimento(Utils.getCurrentUser());
				esitoEntity.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoEntity);
			}
			
//			_em.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(EsitoImpegniRMFITFERBean.class.getSimpleName() + " - inserimentoMassivo: " + e.getMessage());
	    	return false;
		}
    }

	public List<String> getListDomande(String cuaa, String campagna)
	{
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.domandeForCuaa");
		q.setParameter("cuaa", cuaa);
		q.setParameter("campagna", campagna);
		List<String> result = q.getResultList();
		return result;
	}

}
