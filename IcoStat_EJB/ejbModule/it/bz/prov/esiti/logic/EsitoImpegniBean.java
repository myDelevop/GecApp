package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.ElencoEsitoImpegniBO;
import it.bz.prov.esiti.bobject.EsitoImpegniBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.EsitoImpegni;
import it.bz.prov.esiti.entity.EsitoImpegniPk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.IEsitoImpegni;
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
public class EsitoImpegniBean implements IEsitoImpegni{
	
	private static String COL_CUAA =  "CUAA";
	private static String COL_DOMANDA_OPR =  "Domanda OPR";
	private static String COL_CAMPAGNA =  "Campagna";
	private static String COL_INTERVENTO =  "Intervento";
	private static String COL_SOTTOINTERVENTO =  "Sub-Intervento";
//	private static String COL_DATA_CONTROLLO =  "Data Controllo";
	private static String COL_ESITO_OPPAB =  "Esito OPPAB";
	private static String COL_PERC_RID_OPPAB =  "Perc. riduzione OPPAB";
	private static String COL_STATO =  "Stato";
	private static String COL_INADEM_GRAVE = "Inadempienza Grave";
	private static String COL_REITERAZIONE = "Riterazione";
	private static String COL_PROGR_REITERAZIONE = "Progr. Reiterazione";
	private static String COL_PERC_RID_COMM =  "Perc. riduzione Commissione di riesame";
	private static String COL_STATO_POST_COMM =  "Stato post commissione";
	private static String COL_DATA_COMM =  "Data Commissione di riesame";
	private static String COL_ESITO_PREMIO_TRASPORTO =  "Esito premio Trasporto Latte";
	private static String COL_PERC_RID_TRASPORTO =  "% Riduzione Premio Trasporto Latte";
	private static String COL_ESITO_RINUNCIA_INSILATO =  "Esito Rinuncia Insilato";
	private static String COL_IMP_RID_RINUNCIA_INSILATO =  "Importo Riduzione Rinuncia Insilato";
	private static String COL_CONTROLLO_ESTIVO =  "Controllo Estivo";
	private static String COL_PERC_RID_CARICO_BESTIAME =  "Perc. Riduzione Carico Bestiame";
	private static String COL_ESITO_TRANSFORMAZIONE_LATTE =  "Esito Trasformazione Latte";
//	private static String COL_CARICO_ALPEGGIO =  "Carico di alpeggio";
	private static String COL_NOTE =  "Note";
	
	private ElencoEsitoImpegniBO elencoEsitoImpegni = new ElencoEsitoImpegniBO();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	
	/**
	 * aggiunge un esito alla lista
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsito(EsitoImpegniBO esitoImpegni)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(esitoImpegni, Costanti.ACTION_INSERT);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			esitoImpegni.set_userInserimento(Utils.getCurrentUser());
			esitoImpegni.set_dataInserimento(Utils.todayDate());
			EsitoImpegni esitoEntity = esitoImpegni.getEntity();
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
			Utils.getLog().error(EsitoImpegniBean.class.getSimpleName() + " - aggiungiEsito: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * modifica un esito alla lista
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsito(EsitoImpegniBO esitoImpegni)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(esitoImpegni, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			EsitoImpegniPk pk = new EsitoImpegniPk();
			pk.set_domanda(esitoImpegni.get_domanda());
			pk.set_intervento(esitoImpegni.get_intervento());
			pk.set_sottointervento(esitoImpegni.get_sottointervento());
			pk.set_controllo_estivo(esitoImpegni.get_controllo_estivo());
			_em.clear();
			// recupero l'oggetto su db
			EsitoImpegni esitoFind = _em.find(EsitoImpegni.class, pk);
			// se la riga non é stata trovata su db
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
			Utils.getLog().error(EsitoImpegniBean.class.getSimpleName() + " - modificaEsito: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * cancella un esito dalla lista
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoImpegniBO esitoImpegni)
	{
		OperationResultBO result = new OperationResultBO();
		try {
			_em.clear();
			EsitoImpegniPk pk = new EsitoImpegniPk();
			pk.set_domanda(esitoImpegni.get_domanda());
			pk.set_intervento(esitoImpegni.get_intervento());
			pk.set_sottointervento(esitoImpegni.get_sottointervento());
			pk.set_controllo_estivo(esitoImpegni.get_controllo_estivo());
			EsitoImpegni esitoFind = _em.find(EsitoImpegni.class, pk);
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
			Utils.getLog().error(EsitoImpegniBean.class.getSimpleName() + " - cancellaEsito: " + e.getMessage());
			return result;
		}
	}

    public List<EsitoImpegniBO> getElencoEsitoImpegni()
    {
        return this.elencoEsitoImpegni.get_listEsitoImpegni();
    }

    public void setElencoEsitoImpegni(final ElencoEsitoImpegniBO elencoEsitoImpegni)
    {
        this.elencoEsitoImpegni = elencoEsitoImpegni;
    }
    
    
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData()
	{
		Query q = _em.createNamedQuery("EsitoImpegni.selectAll");
		_em.clear();
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoImpegni> fornitura = q.getResultList();
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
	 * restituisce la lista di tutti gli interventi presenti
	 * @return List<String>
	 */
	public List<String> getListIntervento(){
		Query q = _em.createNamedQuery("Sottointervento.distinctIntervento");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
	 * restituisce la lista di tutti i sottointerventi presenti
	 * @return List<String>
	 */
	public List<String> getListSottointervento(){
		Query q = _em.createNamedQuery("Sottointervento.distinctSottointervento");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
	 * restituisce la lista di tutti i sottointerventi presenti per un certo intervento
	 * @param intervento
	 * @return List<String>
	 */
	public List<String> getListSottointervento(String intervento){
		Query q = _em.createNamedQuery("Sottointervento.distinctSottointerventoForIntervento");
		q.setParameter("intervento", intervento);
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
	 * restituisce la lista di tutti gli interventi presenti per una specifica campagna
	 * @return List<String>
	 */
	public List<String> getListInterventoForCampagna(String campagna){
		Query q = _em.createNamedQuery("Sottointervento.InterventoForCampagnaWithoutDU");
		q.setParameter("campagna", campagna);
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
	 * restituisce la lista di tutti i sottointerventi presenti per una specifica campagna
	 * @return List<String>
	 */
	public List<String> getListSottointerventoForCampagna(String campagna){
		Query q = _em.createNamedQuery("Sottointervento.SottointerventoForCampagnaWithoutDU");
		q.setParameter("campagna", campagna);
		List<String> fornitura = q.getResultList();
		List<String> fornitura2 = new ArrayList<String>();
		if(fornitura.indexOf("10.2") == -1) {
			for(int i = 0; i < fornitura.size(); i++) {
				if(fornitura.get(i).length() >= 4)
					fornitura2.add(fornitura.get(i).substring(0, 4));
			}
			int i = fornitura2.indexOf("10.2");
			if(i > -1) fornitura.add(i, "10.2");
		}
		return fornitura;
	}
	
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList)
    {
		_em.clear();
    	Query q = _em.createNamedQuery("EsitoImpegni.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);		
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoImpegni> fornitura = q.getResultList();
		elencoEsitoImpegni.set_listEsitoImpegni(fornitura);
    }
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream)
	{
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = getListaIntestazioni();
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (EsitoImpegniBO esito : elencoEsitoImpegni.get_listEsitoImpegni()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, esito.get_cuaa());
			riga.put(1, esito.get_domanda());
			riga.put(2, esito.get_campagna());
			riga.put(3, esito.get_intervento());
			riga.put(4, esito.get_sottointervento());		
			riga.put(5, esito.get_controllo_estivo());
//			riga.put(5, esito.get_dataControlloStr());
			riga.put(6, esito.get_stato());
			riga.put(7, esito.get_inadempienza_grave());
			riga.put(8, esito.get_reiterazione());
			riga.put(9, esito.get_progrReiterazione());
			riga.put(10, esito.get_percRid());
			riga.put(11, esito.get_esito());
			riga.put(12, esito.get_percRidCommissRiesame());	
			riga.put(13, esito.get_statoPostCommissRiesame());
			riga.put(14, esito.get_dataCommissRiesameStr());
//			riga.put(15, esito.get_caricoAlpeggio());	
			riga.put(15, esito.get_esitoPremioTrasporto());
			riga.put(16, esito.get_percRidPremioTrasporto());
			riga.put(17, esito.get_esitoRinunciaInsilato());
			riga.put(18, esito.get_importoRidRinunciaInsilato());
			riga.put(19, esito.get_perc_rid_carico_bestiame());
			riga.put(20, esito.get_esito_trasformazione_latte());
			riga.put(21, esito.get_note());
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
		elencoEsitoImpegni = new ElencoEsitoImpegniBO();
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
    	LinkedHashMap<Integer, String> listaColonna = EsitoImpegniBean.getListaIntestazioni();
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
    	ArrayList<EsitoImpegniBO> listaEsiti = new ArrayList<EsitoImpegniBO>(); 
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
    	for (EsitoImpegniBO esito : listaEsiti) {
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
    private OperationResultBO checkPreInsert(EsitoImpegniBO esito, String action)
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
		
		//7. intervento valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_intervento()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_INTERVENTO_NULLO);
			return result;
		}
    	
    	//8. sottointervento valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_sottointervento()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_SOTTOINTERVENTO_NULLO);
			return result;
		}
		
    	//9. la domanda indicata ha l'intervento e il sottointervento specificati
    	Query q1;
    	List<String> sottointEntity;
    	if(esito.get_intervento().equals("10.2")) {
    		_em.clear();
    		q1 = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
    		q1.setParameter("domanda", esito.get_domanda());
    		q1.setParameter("intervento", esito.get_intervento());
    		sottointEntity = q1.getResultList();
    	}
    	else {
    		_em.clear();
        	q1 = _em.createNamedQuery("Sottointervento.domandaHasInterventoAndSottointerventoo");
    		//q1.setParameter("domanda", esito.get_domanda());
    		//q1.setParameter("intervento", esito.get_intervento());
    		//q1.setParameter("sottointervento", esito.get_sottointervento());
    		sottointEntity = q1.getResultList();
    	}

		if(sottointEntity == null || sottointEntity.size()==0)		
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_SOTTOINTERVENTO_MANCANTI);
			return result;
		}
    	
    	// 10. domanda, intervento e sottointervento non devono essere già presenti in tabella (é la pk) 
		//(da fare solo per insert, no per modify)
		if(action.equals(Costanti.ACTION_INSERT))
		{
			EsitoImpegniPk pk = new EsitoImpegniPk();
			pk.set_domanda(esito.get_domanda());
			pk.set_intervento(esito.get_intervento());
			pk.set_sottointervento(esito.get_sottointervento());
			pk.set_controllo_estivo(esito.get_controllo_estivo());
			_em.clear();
			EsitoImpegni esitoFind = _em.find(EsitoImpegni.class, pk);
			if(esitoFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_SOTTOINTERVENTO_PRESENTI);
				return result;
			}
		}
		
		//11. percentuale di riduzione OPPAB deve essere un valore numerico
		if(esito.get_percRid()!= null && !esito.get_percRid().equals(""))
			if(!CheckData.checkFloat(esito.get_percRid()))
	    	{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
				return result;
			}
		
		//12. percentuale di riduzione commissione di riesame deve essere un valore numerico
		if(!esito.get_percRidCommissRiesame().equals(""))
			if(!CheckData.checkFloat(esito.get_percRidCommissRiesame()))
	    	{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
				return result;
			}
		
		//13. carico di alpeggio deve essere un valore numerico
//		if(!esito.get_caricoAlpeggio().equals(""))
//			if(!CheckData.checkFloat(esito.get_caricoAlpeggio()))
//	    	{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_CARICO_ALPEGGIO_NUMERIC_VALUE);
//				return result;
//			}
		
		//14. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(esito.get_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
		
		//15a. perc riduzione premio trasporto valorizzata se esito negativo
		if(esito.get_percRidPremioTrasporto().equals("") && esito.get_esitoPremioTrasporto().equals("NEGATIVO")){
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_PERC_RID_PREMIO_TRASPORTO_EMPTY);
			return result;
		}
		
		//15b. perc riduzione premio trasporto deve essere un valore numerico
		if(!esito.get_percRidPremioTrasporto().equals(""))
			if(!CheckData.checkFloat(esito.get_percRidPremioTrasporto()))
	    	{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_PERC_RID_PREMIO_TRASPORTO);
				return result;
			}
		
		//16a. importo riduzione rinuncia insilato valorizzato se esito negativo
		if(esito.get_importoRidRinunciaInsilato().equals("") && esito.get_esitoRinunciaInsilato().equals("NEGATIVO"))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_IMPORTO_RID_RINUNCIA_INSILATO_EMPTY);
			return result;
		}
		
		//16b. importo riduzione rinuncia insilato deve essere un valore numerico
		if(!esito.get_importoRidRinunciaInsilato().equals(""))
			if(!CheckData.checkFloat(esito.get_importoRidRinunciaInsilato().toString()))
	    	{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_IMPORTO_RID_RINUNCIA_INSILATO);
				return result;
			}
		
		//17. percentuale di riduzione carico bestiame deve essere un valore numerico
		if(esito.get_perc_rid_carico_bestiame()!= null && !esito.get_perc_rid_carico_bestiame().equals(""))
			if(!CheckData.checkFloat(esito.get_perc_rid_carico_bestiame()))
	    	{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
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
    private OperationResultBO checkForImport(List<EsitoImpegniBO> listaImpegni)
    {
    	// controllo che il valore dei flag sia uguale a quello in anagrafica
    	List<String> lista_valori_stato = getListaValori(Costanti.ANAGR_STATO);
		List<String> lista_inadem_grave = getListaValori(Costanti.ANAGR_INADEMPIENZA_GRAVE);
		List<String> lista_reiterazione = getListaValori(Costanti.ANAGR_REITERAZIONE);
		List<String> lista_progr_reiterazione = getListaValori(Costanti.ANAGR_PROGR_REITERAZIONE);
    	List<String> lista_valori_esito = getListaValori(Costanti.ANAGR_ESITO);

		//Riutilizzo questa costante perchè contiene già i valori SI/NO
		List<String> lista_segnalazione = getListaValori(Costanti.ANAGR_AZIONE_CORRETTIVA_RICHIESTA);
		
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (EsitoImpegniBO esito : listaImpegni) {
    		// stato
			if(!esito.get_stato().equals("") && !lista_valori_stato.contains(esito.get_stato()))
			{
				result.set_result(false);
				result.set_message("ERROR: Stato - valore non in anagrafica");
				break;
			}

    		// inademp. grave
			if(!esito.get_inadempienza_grave().equals("") && !lista_inadem_grave.contains(esito.get_inadempienza_grave()))
			{
				result.set_result(false);
				result.set_message("ERROR: Inadempienza Grave - valore non in anagrafica");
				break;
			}

    		// reiterazione
			if(!esito.get_reiterazione().equals("") && !lista_reiterazione.contains(esito.get_reiterazione()))
			{
				result.set_result(false);
				result.set_message("ERROR: Reiterazione - valore non in anagrafica");
				break;
			}

    		// progr. reiterazione
			if(!esito.get_progrReiterazione().equals("") && !lista_progr_reiterazione.contains(esito.get_progrReiterazione()))
			{
				result.set_result(false);
				result.set_message("ERROR: Progr. Reitarazione - valore non in anagrafica");
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
			if(!esito.get_esito().equals("") && !lista_valori_esito.contains(esito.get_esito()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito OPPAB - valore non in anagrafica");
				break;
			}
			
			// esito Premio Trasporto
			if(!esito.get_esitoPremioTrasporto().equals("") && !lista_valori_esito.contains(esito.get_esito()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito Premio Trasporto - valore non in anagrafica");
				break;
			}
			
			// perc. rid. Premio Trasporto non valorizzato se esito negativo
			if(esito.get_esitoPremioTrasporto().equals("NEGATIVO") &&
					esito.get_percRidPremioTrasporto().equals(""))
			{
				result.set_result(false);
				result.set_message("ERROR: Perc. Rid. Premio Trasporto - valore mancante per esito negativo");
				break;
			}
			
			// esito premio trasporto latte
			if(!esito.get_esitoPremioTrasporto().equals("") && !lista_valori_esito.contains(esito.get_esitoPremioTrasporto()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito premio trasporto latte - valore non in anagrafica");
				break;
			}
			
			// perc. rid. Premio Trasporto valorizzato se esito non negativo
			if(!esito.get_esitoPremioTrasporto().equals("NEGATIVO") && !esito.get_percRidPremioTrasporto().equals(""))
			{
				result.set_result(false);
				result.set_message("ERROR: Perc. Rid. Premio Trasporto - non deve essere valorizzato se esito diverso da negativo");
				break;
			}
			
			// esito Rinuncia Insilato
			if(!esito.get_esitoRinunciaInsilato().equals("") && !lista_valori_esito.contains(esito.get_esitoRinunciaInsilato()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito Rinuncia Insilato - valore non in anagrafica");
				break;
			}
			
			// importo riduzione Rinuncia Insilato non valorizzato quando esito negativo 
			if(esito.get_esitoRinunciaInsilato().equals("NEGATIVO") && 
					esito.get_importoRidRinunciaInsilato().equals(""))
			{
				result.set_result(false);
				result.set_message("ERROR: Importo Riduzione Rinuncia Insilato - valore mancante per esito negativo");
				break;
			}
			
			// importo riduzione Rinuncia Insilato valorizzato quando esito non negativo 
			if(!esito.get_esitoRinunciaInsilato().equals("NEGATIVO") && 
					!esito.get_importoRidRinunciaInsilato().equals(""))
			{
				result.set_result(false);
				result.set_message("ERROR: Importo Riduzione Rinuncia Insilato - non deve essere valorizzato se esito diverso da negativo");
				break;
			}
			
			// Controllo Estivo
			if(!esito.get_controllo_estivo().equals("") && 
						!lista_segnalazione.contains(esito.get_controllo_estivo()))
			{
				result.set_result(false);
				result.set_message("ERROR: Controllo Estivo - valore non in anagrafica");
				break;
			}
			
			// esito trasformazione latte
			if(!esito.get_esito_trasformazione_latte().equals("") && !lista_valori_esito.contains(esito.get_esito_trasformazione_latte()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito trasformazione latte - valore non in anagrafica");
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
		intestazione.put(3, COL_INTERVENTO);
		intestazione.put(4, COL_SOTTOINTERVENTO);
//		intestazione.put(5, COL_DATA_CONTROLLO);
		intestazione.put(5, COL_CONTROLLO_ESTIVO);
		intestazione.put(6, COL_STATO);
		intestazione.put(7, COL_INADEM_GRAVE);
		intestazione.put(8, COL_REITERAZIONE);
		intestazione.put(9, COL_PROGR_REITERAZIONE);
		intestazione.put(10, COL_PERC_RID_OPPAB);
		intestazione.put(11, COL_ESITO_OPPAB);
		intestazione.put(12, COL_PERC_RID_COMM);
		intestazione.put(13, COL_STATO_POST_COMM);
		intestazione.put(14, COL_DATA_COMM);
//		intestazione.put(11, COL_CARICO_ALPEGGIO);
		intestazione.put(15, COL_ESITO_PREMIO_TRASPORTO);
		intestazione.put(16, COL_PERC_RID_TRASPORTO);
		intestazione.put(17, COL_ESITO_RINUNCIA_INSILATO);
		intestazione.put(18, COL_IMP_RID_RINUNCIA_INSILATO);
		intestazione.put(19, COL_PERC_RID_CARICO_BESTIAME);
		intestazione.put(20, COL_ESITO_TRANSFORMAZIONE_LATTE);
		intestazione.put(21, COL_NOTE);
		
    	return intestazione;
    }
    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel é l'oggetto che contiene i sati letti da file
     * @param lista 
     * @param logList
     * @return boolean 
     */
    private static boolean getListFromExcel(FileExcel fileExcel, ArrayList<EsitoImpegniBO> lista, List<String> logList)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	List<String> lstDomande = new ArrayList<String>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		EsitoImpegniBO esito = new EsitoImpegniBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { esito.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DOMANDA_OPR)) { esito.set_domanda(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { esito.set_campagna(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_INTERVENTO)) { esito.set_intervento(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_SOTTOINTERVENTO)) { esito.set_sottointervento(valore); continue; }
//				if(intestazione.equalsIgnoreCase(COL_DATA_CONTROLLO)) 
//				{ 
//					if(valore != null && !valore.equals(""))
//					{
//						if(CheckData.checkStringDateFormat(valore))
//							esito.set_dataControllo(Utils.getDate(valore)); 
//						else 
//						{
//							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " data controllo nel formato errato");
//							return false;
//						}
//					}	
//					continue; 
//				}
				if(intestazione.equalsIgnoreCase(COL_STATO)) { esito.set_stato(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_INADEM_GRAVE)) { esito.set_inadempienza_grave(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_REITERAZIONE)) { esito.set_reiterazione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PROGR_REITERAZIONE)) { esito.set_progrReiterazione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_RID_OPPAB)) { esito.set_percRid(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ESITO_OPPAB)) { esito.set_esito(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_RID_COMM)) { esito.set_percRidCommissRiesame(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_STATO_POST_COMM)) { esito.set_statoPostCommissRiesame(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DATA_COMM)) 
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
//				if(intestazione.equalsIgnoreCase(COL_CARICO_ALPEGGIO)) { esito.set_caricoAlpeggio(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NOTE)) { esito.set_note(valore); continue; }
				
				if(intestazione.equalsIgnoreCase(COL_ESITO_PREMIO_TRASPORTO)) { esito.set_esitoPremioTrasporto(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_RID_TRASPORTO)) { esito.set_percRidPremioTrasporto(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ESITO_RINUNCIA_INSILATO)) { esito.set_esitoRinunciaInsilato(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_IMP_RID_RINUNCIA_INSILATO)) { esito.set_importoRidRinunciaInsilato(valore); 
					//if(valore != null && !valore.equals("")) esito.set_importoRidRinunciaInsilato(valore); 
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_CONTROLLO_ESTIVO)) { esito.set_controllo_estivo(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_RID_CARICO_BESTIAME)) { esito.set_perc_rid_carico_bestiame(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ESITO_TRANSFORMAZIONE_LATTE)) { esito.set_esito_trasformazione_latte(valore); continue; }
				
				
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
    private boolean inserimentoMassivo(ArrayList<EsitoImpegniBO> listaEsiti, List<String> listLog)
    {
    	try {			
			_em.clear();
//			_em.getTransaction().begin();
			
			for (EsitoImpegniBO esito : listaEsiti) {
				EsitoImpegniPk pk = new EsitoImpegniPk();
				pk.set_domanda(esito.get_domanda());
				pk.set_intervento(esito.get_intervento());
				pk.set_sottointervento(esito.get_sottointervento());
				pk.set_controllo_estivo(esito.get_controllo_estivo());
				EsitoImpegni esitoFind = _em.find(EsitoImpegni.class, pk);
				// se la riga é stata trovata su db la cancello 
				if(esitoFind != null) _em.remove(esitoFind);
				
				// inserisco la riga
				EsitoImpegni esitoEntity = esito.getEntity();
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

	public List<String> getListDomande(String cuaa, String campagna)
	{
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.domandeForCuaa");
		q.setParameter("cuaa", cuaa);
		q.setParameter("campagna", campagna);
		List<String> result = q.getResultList();
		return result;
	}
	
	public boolean hasSottointervento(String domanda, String inte, String sottoInte) {
		boolean cond = false;
    	Query q1;
    	List<String> sottointEntity;
		_em.clear();
    	q1 = _em.createNamedQuery("Sottointervento.domandaHasInterventoAndSottointervento");
		q1.setParameter("domanda", domanda);
		q1.setParameter("intervento", inte);
		q1.setParameter("sottointervento", sottoInte);
		sottointEntity = q1.getResultList();
    	cond = sottointEntity == null || sottointEntity.size() == 0 ? false : true;
		return cond;
	}

}
