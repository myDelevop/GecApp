package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.ElencoEsitoSuperficiBO;
import it.bz.prov.esiti.bobject.EsitoSuperficiBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.EsitoSuperfici;
import it.bz.prov.esiti.entity.EsitoSuperficiPk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.IEsitoSuperfici;
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
 * Bean per la gestione back-end degli esiti a superficie
 * 
 * @author bpettazzoni
 *
 */

@Stateful
@SuppressWarnings("unchecked")
public class EsitoSuperficiBean implements IEsitoSuperfici {
	
	private ElencoEsitoSuperficiBO elencoEsitoSup = new ElencoEsitoSuperficiBO();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	private static String COL_CUAA = "CUAA";
	private static String COL_DOMANDA_OPR = "Domanda OPR";
	private static String COL_CAMPAGNA = "Campagna";
	private static String COL_INTERVENTO = "Intervento";
	private static String COL_SOTTOINTERVENTO = "Sottointervento";
//	private static String COL_STATO_AZIONE = "Stato Azione";
//	private static String COL_GRUPPO_COLTURA = "Gruppo Coltura";
	private static String COL_SUP_DICHIARATA = "Sup. Dichiarata Netta";
	private static String COL_SUP_ACCERTATA = "Sup. Accertata Netta";
	private static String COL_DIFFERENZA = "Differenza";
	private static String COL_SCOSTAMENTO_PERCENTUALE = "Scostamento percentuale";
//	private static String COL_ESITO = "Esito";
	private static String COL_SANZIONE = "Sanzione";
	private static String COL_SANZIONE_MQ = "Sanzione mq";
	private static String COL_CARTELLINO_GIALLO = "Cartellino Giallo";
//	private static String COL_SANZIONE_ANNULLATA = "Sanzione Annullata";
	private static String COL_NOTE = "Note";
	private static String COL_DATA_CONTROLLO = "Data Controllo";
	private static String COL_EFFETTO = "Effetto";

	
	/**
	 * aggiunge un esito alla lista
	 * @param esitoSuperfici
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsito(EsitoSuperficiBO esitoSuperfici)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(esitoSuperfici, Costanti.ACTION_INSERT);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			esitoSuperfici.set_userInserimento(Utils.getCurrentUser());
			esitoSuperfici.set_dataInserimento(Utils.todayDate());
			EsitoSuperfici esitoEntity = esitoSuperfici.getEntity();
			esitoEntity.set_changed("S");
			_em.clear();
//			_em.getTransaction().begin();
			_em.persist(esitoEntity);
//			_em.getTransaction().commit();
			// inserimento in elenco
			elencoEsitoSup.addEsito(esitoSuperfici);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoSuperficiBean.class.getSimpleName() + " - aggiungiEsito: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * modifica un esito alla lista
	 * @param esitoSuperfici
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsito(EsitoSuperficiBO esitoSuperfici)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(esitoSuperfici, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			EsitoSuperficiPk pk = new EsitoSuperficiPk();
			pk.set_domanda(esitoSuperfici.get_domanda());
			pk.set_intervento(esitoSuperfici.get_intervento());
			pk.set_sottointervento(esitoSuperfici.get_sottointervento());
			_em.clear();
			EsitoSuperfici esitoFind = _em.find(EsitoSuperfici.class, pk);
			if(esitoFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			esitoSuperfici.set_userModifica(Utils.getCurrentUser());
			esitoSuperfici.set_dataModifica(Utils.todayDate());
			esitoSuperfici.setEntity(esitoFind);
			esitoFind.set_changed("S");		
//			_em.getTransaction().commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoSuperficiBean.class.getSimpleName() + " - modificaEsito: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * cancella un esito dalla lista
	 * @param esitoSuperfici
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoSuperficiBO esitoSuperfici)
	{
		OperationResultBO result = new OperationResultBO();
		try {
			_em.clear();
			EsitoSuperficiPk pk = new EsitoSuperficiPk();
			pk.set_domanda(esitoSuperfici.get_domanda());
			pk.set_intervento(esitoSuperfici.get_intervento());
			pk.set_sottointervento(esitoSuperfici.get_sottointervento());
			EsitoSuperfici esitoFind = _em.find(EsitoSuperfici.class, pk);
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
			elencoEsitoSup.remove(esitoSuperfici);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoSuperficiBean.class.getSimpleName() + " - cancellaEsito: " + e.getMessage());
			return result;
		}
	}

    public List<EsitoSuperficiBO> getElencoEsitoSuperfici()
    {
        return this.elencoEsitoSup.get_listEsitoSuperfici();
    }

    public void setElencoEsitoSuperfici(final ElencoEsitoSuperficiBO elencoEsitoSup)
    {
        this.elencoEsitoSup = elencoEsitoSup;
    }
    
    /**
	 * carica i dati degli esiti superficie 
	 */
	public void loadData()
	{
		Query q = _em.createNamedQuery("EsitoSuperfici.selectAll");
		_em.clear();
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoSuperfici> fornitura = q.getResultList();
		elencoEsitoSup.set_listEsitoSuperfici(fornitura);
	}
	
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna(){
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.distinctCampagna");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica richiesta
	 * @param nomeAnagrafica
	 * @return List<String>
	 */
	public List<String> getListaValori(String nomeAnagrafica)
	{
		_em.clear();
		Query q = _em.createNamedQuery("ValoreAnagrafica.selectAnagrafica");
		q.setParameter("campo", nomeAnagrafica);
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
	 * restituisce la lista di tutti gli interventi presenti per una specifica campagna
	 * @return List<String>
	 */
	public List<String> getListIntervento(String campagna){
		Query q = _em.createNamedQuery("Sottointervento.InterventoForCampagna");
		q.setParameter("campagna", campagna);
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
	 * restituisce la lista di tutti i sottointerventi presenti per una specifica campagna
	 * @return List<String>
	 */
	public List<String> getListSottointervento(String campagna){
		Query q = _em.createNamedQuery("Sottointervento.SottointerventoForCampagna");
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
    	Query q = _em.createNamedQuery("EsitoSuperfici.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);		
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoSuperfici> fornitura = q.getResultList();
		elencoEsitoSup.set_listEsitoSuperfici(fornitura);
    }
	
	/**
	 * permette l'inserimento dei dati da file excel
	 * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
	 */
    public boolean insertFromFile(String pathFile, List<String> listLog) {
    	//1. estraggo il file excel
    	FileExcel fileExcel = ExcelReader.readFile(pathFile);
    	if(fileExcel == null)
    	{
    		listLog.add("ERROR: ci sono stati problemi nella lettura del file temporaneo");
    		return false;
    	}
    	
    	//2. verifico la presenza di tutte le intestazioni corrette
    	boolean check = true;
    	LinkedHashMap<Integer, String> listaColonna = EsitoSuperficiBean.getListaIntestazioni();
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
    	ArrayList<EsitoSuperficiBO> listaEsiti = new ArrayList<EsitoSuperficiBO>(); 
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
    	for (EsitoSuperficiBO esito : listaEsiti) {
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
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream)
	{	
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = getListaIntestazioni();
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (EsitoSuperficiBO esito : elencoEsitoSup.get_listEsitoSuperfici()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, esito.get_cuaa());
			riga.put(1, esito.get_domanda());
			riga.put(2, esito.get_campagna());
			riga.put(3, esito.get_dataControlloStr());
			riga.put(4, esito.get_intervento());
			riga.put(5, esito.get_sottointervento());
			riga.put(6, esito.get_supDichiarata());
			riga.put(7, esito.get_supAccertata());
			riga.put(8, esito.get_differenza());
			riga.put(9, esito.get_scostPerc());
			riga.put(10, esito.get_sanzioneAnnullata());
			riga.put(11, esito.get_cartellino_giallo());
			riga.put(12, esito.get_sanzione());
			riga.put(13, esito.get_effetto());
			riga.put(14, esito.get_note());
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
		elencoEsitoSup = new ElencoEsitoSuperficiBO();
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
    private OperationResultBO checkPreInsert(EsitoSuperficiBO esito, String action)
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
    	_em.clear();
    	q1 = _em.createNamedQuery("Sottointervento.domandaHasInterventoAndSottointerventoo");
		q1.setParameter("domanda", esito.get_domanda());
		q1.setParameter("intervento", esito.get_intervento());
		q1.setParameter("sottointervento", esito.get_sottointervento());

		sottointEntity = q1.getResultList();

		if(sottointEntity == null || sottointEntity.size()==0)		
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_SOTTOINTERVENTO_MANCANTI);
			return result;
		}
		
    	// 9. domanda, intervento e sottointervento non devono essere già presenti in tabella (é la pk) 
		//(da fare solo per insert, no per modify)
		if(action.equals(Costanti.ACTION_INSERT))
		{
			EsitoSuperficiPk pk = new EsitoSuperficiPk();
			pk.set_domanda(esito.get_domanda());
			pk.set_intervento(esito.get_intervento());
			pk.set_sottointervento(esito.get_sottointervento());
			_em.clear();
			EsitoSuperfici esitoFind = _em.find(EsitoSuperfici.class, pk);
			if(esitoFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_SOTTOINTERVENTO_PRESENTI);
				return result;
			}
		}
		
		//10. sup dichiarata deve essere un valore numerico
		if(esito.get_supDichiarata() != null && !esito.get_supDichiarata().equals(""))
			if(!CheckData.checkFloat(esito.get_supDichiarata()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_SUP_DICH_NUMERIC_VALUE);
				return result;
			}
		
		//11. sup accertata deve essere un valore numerico
		if(esito.get_supAccertata() != null && !esito.get_supAccertata().equals(""))
			if(!CheckData.checkFloat(esito.get_supAccertata()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_SUP_ACC_NUMERIC_VALUE);
				return result;
			}	
		
		//12. differenza deve essere un valore numerico
		if(esito.get_differenza() != null && !esito.get_differenza().equals(""))
			if(!CheckData.checkFloat(esito.get_differenza()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DIFF_NUMERIC_VALUE);
				return result;
			}
		
		//13. perc scost deve essere un valore numerico
		if(esito.get_scostPerc() != null && !esito.get_scostPerc().equals(""))
			if(!CheckData.checkFloat(esito.get_scostPerc()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_PERC_SCOST_NUMERIC_VALUE);
				return result;
			}
		
		//14. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(esito.get_note()))
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
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel é l'oggetto che contiene i sati letti da file
     * @param lista 
     * @param logList
     * @return boolean 
     */
    private static boolean getListFromExcel(FileExcel fileExcel, ArrayList<EsitoSuperficiBO> lista, List<String> logList)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	List<String> lstDomande = new ArrayList<String>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		EsitoSuperficiBO esito = new EsitoSuperficiBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { esito.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DOMANDA_OPR)) { esito.set_domanda(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { esito.set_campagna(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DATA_CONTROLLO)) {
					if(valore != null && !valore.equals(""))
					{
						if(CheckData.checkStringDateFormat(valore))
							esito.set_dataControllo(Utils.getDate(valore)); 
						else 
						{
							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " data controllo nel formato errato");
							return false;
						}
					}
					continue;
				}
				if(intestazione.equalsIgnoreCase(COL_INTERVENTO)) { esito.set_intervento(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_SOTTOINTERVENTO)) { esito.set_sottointervento(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_SUP_DICHIARATA)) { esito.set_supDichiarata(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_SUP_ACCERTATA)) { esito.set_supAccertata(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DIFFERENZA)) { esito.set_differenza(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_SCOSTAMENTO_PERCENTUALE)) { esito.set_scostPerc(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_SANZIONE)) { esito.set_sanzioneAnnullata(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CARTELLINO_GIALLO)) { esito.set_cartellino_giallo(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_SANZIONE_MQ)) { esito.set_sanzione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_EFFETTO)) { esito.set_effetto(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NOTE)) { esito.set_note(valore); continue; }
				
			}
			
			//Autoimpostazione della Misura a partire dall'intervento.
			int index = esito.get_intervento().indexOf('.');
			if(index != -1)
				esito.set_misura(esito.get_intervento().substring(0, index));
			else 
				esito.set_misura(esito.get_intervento());
			
			//Autoimpostazione della Differenza a partire dalle superfici.
			if(esito.get_differenza().equals("")) {
				Float diff = Float.parseFloat(esito.get_supDichiarata().replace(",", ".")) - Float.parseFloat(esito.get_supAccertata().replace(",", "."));
				esito.set_differenza(diff.toString());
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
     * prepara la lista di tutte le intestazioni delle colonne presenti nel file
     * @return LinkedHashMap<Integer, String>
     */
    private static LinkedHashMap<Integer, String> getListaIntestazioni()
    {
    	LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
    	intestazione.put(0, COL_CUAA                   	);
		intestazione.put(1, COL_DOMANDA_OPR             );
		intestazione.put(2, COL_CAMPAGNA           		);
		intestazione.put(3, COL_DATA_CONTROLLO			);		
		intestazione.put(4, COL_INTERVENTO             	);
		intestazione.put(5, COL_SOTTOINTERVENTO        	);
		intestazione.put(6, COL_SUP_DICHIARATA         	);
		intestazione.put(7, COL_SUP_ACCERTATA          	);
		intestazione.put(8, COL_DIFFERENZA             	);
		intestazione.put(9, COL_SCOSTAMENTO_PERCENTUALE	);
		intestazione.put(10, COL_SANZIONE				);
		intestazione.put(11, COL_CARTELLINO_GIALLO      );
		intestazione.put(12, COL_SANZIONE_MQ			);
		intestazione.put(13, COL_EFFETTO				);
		intestazione.put(14, COL_NOTE                   );
		
    	return intestazione;
    }
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di fare la import
     * @param listaEsiti
     * @return OperationResultBO
     */
    private OperationResultBO checkForImport(List<EsitoSuperficiBO> listaEsiti)
    {
		//Riutilizzo questa costante perchè contiene già i valori SI/NO
    	List<String> lista_valori_cartellino_giallo = getListaValori(Costanti.ANAGR_AZIONE_CORRETTIVA_RICHIESTA);
//    	List<String> lista_valori_esito = getListaValori(Costanti.ANAGR_ESITO);
		List<String> lista_sanzione_annullata = getListaValori(Costanti.ANAGR_SANZIONE_ANNULLATA);
		
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (EsitoSuperficiBO esito : listaEsiti) {
			
			// esito Sanzione Annullata
			if(!esito.get_sanzioneAnnullata().equals("") && !lista_sanzione_annullata.contains(esito.get_sanzioneAnnullata()))
			{
				result.set_result(false);
				result.set_message("ERROR: Sanzione - valore non in anagrafica");
				break;
			}
			
			// esito Cartellino Giallo
			if(!esito.get_cartellino_giallo().equals("") && !lista_valori_cartellino_giallo.contains(esito.get_cartellino_giallo()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito Cartellino Giallo - valore non in anagrafica");
				break;
			}
			
		}
    	return result;    	
    }

    /**
     * esegue l'inserimento di tutte le righe in una sola transazione
     * @param listaEsiti
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivo(ArrayList<EsitoSuperficiBO> listaEsiti, List<String> listLog)
    {
    	try {			
			_em.clear();			
			for (EsitoSuperficiBO esito : listaEsiti) {
				EsitoSuperficiPk pk = new EsitoSuperficiPk();
				pk.set_domanda(esito.get_domanda());
				pk.set_intervento(esito.get_intervento());
				pk.set_sottointervento(esito.get_sottointervento());
				EsitoSuperfici esitoFind = _em.find(EsitoSuperfici.class, pk);
				// se la riga é stata trovata su db la cancello 
				if(esitoFind != null) _em.remove(esitoFind);
				
				// inserisco la riga
				EsitoSuperfici esitoEntity = esito.getEntity();
				Utils.getLog().info(EsitoSuperficiBean.class.getSimpleName() + " - inserimentoMassivo: dopo getEntity");
				esitoEntity.set_user_inserimento(Utils.getCurrentUser());
				esitoEntity.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoEntity);
			}			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(EsitoSuperficiBean.class.getSimpleName() + " - inserimentoMassivo: " + e.getMessage());
	    	return false;
		}
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
