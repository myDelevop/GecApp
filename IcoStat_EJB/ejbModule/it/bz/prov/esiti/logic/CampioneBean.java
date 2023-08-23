package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.CampioneBO;
import it.bz.prov.esiti.bobject.ElencoCampioneBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.Campione;
import it.bz.prov.esiti.entity.CampionePk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.ICampione;
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
 * Bean per la gestione back-end del campione
 * 
 * @author bpettazzoni
 *
 */

@Stateful
@SuppressWarnings("unchecked")
public class CampioneBean implements ICampione {
	
	private static String COL_CUAA = "CUAA";
	private static String COL_DOMANDA_OPR = "Domanda OPR";
	private static String COL_CAMPAGNA = "Campagna";
	private static String COL_CATEGORIA = "Categoria Campione";
	private static String COL_TIPO_CAMPIONE = "Tipo Campione";
	private static String COL_DATA_CAMPIONE = "Data Campione";
	private static String COL_ORIGINE_CAMPIONE = "Origine Campione";
	private static String COL_BOVINI= "Bovini";
	private static String COL_OVICAPRINI = "Ovicaprini";	
	private static String COL_DOMINIO_CAMP = "Dominio campione di condizionalità";
	private static String COL_STATO_DOMANDA_OPPAB = "Stato Domanda OPPAB";	
	private static String COL_NOTE = "Note";
	
	private ElencoCampioneBO elencoCampione = new ElencoCampioneBO();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	
	/***************************************************************************************
	 * 			GESTIONE DATI CAMPIONE
	 ***************************************************************************************/
	
	
	/**
	 * aggiunge un campione alla lista
	 * @param campione
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiCampione(CampioneBO campione) 
	{
		OperationResultBO result = new OperationResultBO();
		
		// se il campione é di condizionalità non sarà presente la domanda e va inserita in automatico. 
		// si crea una riga per ogni domanda
		if(campione.get_categoria().startsWith("COND"))
		{
			ArrayList<String> listDomande = (ArrayList<String>) getListDomande(campione.get_cuaa(), campione.get_campagna());
			if(listDomande != null && listDomande.size() != 0){
				try {
					_em.clear();
					for (String domanda : listDomande) {
						CampioneBO campioneBO = campione.clone();
						campioneBO.set_domanda(domanda);
						campioneBO.set_userInserimento(Utils.getCurrentUser());
						campioneBO.set_dataInserimento(Utils.todayDate());
						// controlli lato server pre-inserimento
						result = checkPreInsert(campioneBO, Costanti.ACTION_INSERT);
						if(!result.get_result()) return result;
						Campione campioneEntity = campioneBO.getEntity();
//						_em.getTransaction().begin();
						_em.persist(campioneEntity);
						_em.flush();
//						_em.getTransaction().commit();
						// inserimento nell'elenco
						elencoCampione.addEsito(campioneBO);
					}
					// preparazione risultato
					result.set_result(true);
					result.set_message(Costanti.MSG_INSERT_OK);		
				} catch (Exception e) {
					// preparazione risultato
					result.set_result(false);
					result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
					Utils.getLog().error(CampioneBean.class.getSimpleName() + " - aggiungiCampione: " + e.getMessage());
				}
			}
			else {
				result.set_result(false);
				result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			}
		}
		else {
			// controlli lato server pre-inserimento
			result = checkPreInsert(campione, Costanti.ACTION_INSERT);
			if(!result.get_result()) return result;
			
			// se i controlli sono andati bene procedo con l'inserimento
			result = new OperationResultBO();
			try {
				campione.set_userInserimento(Utils.getCurrentUser());
				campione.set_dataInserimento(Utils.todayDate());
				Campione campioneEntity = campione.getEntity();
//				_em.getTransaction().begin();
				_em.persist(campioneEntity);
//				_em.getTransaction().commit();
				// inserimento nell'elenco
				elencoCampione.addEsito(campione);
				// preparazione risultato
				result.set_result(true);
				result.set_message(Costanti.MSG_INSERT_OK);
			} catch (Exception e) {
				// preparazione risultato
				result.set_result(false);
				result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
				Utils.getLog().error(CampioneBean.class.getSimpleName() + " - aggiungiCampione: " + e.getMessage());
			}
		}
		return result;
	}

	
	/**
	 * modifica un campione alla lista
	 * @param campione
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaCampione(CampioneBO campione)
	{		
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(campione, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			_em.clear();
			CampionePk pk = new CampionePk();
			pk.set_cuaa(campione.get_cuaa());
			pk.set_domanda(campione.get_domanda());
			pk.set_campagna(campione.get_campagna());
			pk.set_categoria(campione.get_categoria());
			Campione campioneFind = _em.find(Campione.class, pk);
			// se la riga non é stata trovata su db
			if(campioneFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();	
			campione.set_userModifica(Utils.getCurrentUser());
			campione.set_dataModifica(Utils.todayDate());
			campione.setEntity(campioneFind);
//			_em.getTransaction().commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(CampioneBean.class.getSimpleName() + " - modificaCampione: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * cancella un campione dalla lista
	 * @param campione
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaCampione(CampioneBO campione)
	{
		OperationResultBO result = new OperationResultBO();
		try {
			_em.clear();
			CampionePk pk = new CampionePk();
			pk.set_cuaa(campione.get_cuaa());
			pk.set_domanda(campione.get_domanda());
			pk.set_campagna(campione.get_campagna());
			pk.set_categoria(campione.get_categoria());
			Campione campioneFind = _em.find(Campione.class, pk);
			if(campioneFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			_em.remove(campioneFind);
//			_em.getTransaction().commit();
			// cancellazione dall'elenco
			elencoCampione.remove(campione);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(CampioneBean.class.getSimpleName() + " - cancellaCampione: " + e.getMessage());
			return result;
		}
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
     * Carica i dati in tabella
     */
    public void loadData()
    {
    	_em.clear();
    	Query q = _em.createNamedQuery("Campione.selectAll");
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<Campione> fornitura = q.getResultList();
		elencoCampione.set_listCampione(fornitura);
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
	 * restituisce la lista di tutte le domande per il cuaa passato come parametro
	 * @param cuaa
	 * @param campagna
	 * @return List<String>
	 */
	public List<String> getListDomande(String cuaa, String campagna){
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.domandeForCuaa");
		q.setParameter("cuaa", cuaa);
		q.setParameter("campagna", campagna);
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
	 * restituisce la lista con la domanda PSR per il cuaa e campagna passati come parametro
	 * @param cuaa
	 * @param campagna
	 * @return List<String>
	 */
	public List<String> getListDomandePSR(String cuaa, String campagna){
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.domandeForCuaaPSR");
		q.setParameter("cuaa", cuaa);
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
    	Query q = _em.createNamedQuery("Campione.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);	
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<Campione> fornitura = q.getResultList();
		elencoCampione.set_listCampione(fornitura);
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
		for (CampioneBO campione : elencoCampione.get_listCampione()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, campione.get_cuaa());
			riga.put(1, campione.get_domanda());
			riga.put(2, campione.get_campagna());
			riga.put(3, campione.get_categoria());
			riga.put(4, campione.get_tipo());
			riga.put(5, campione.get_dataCampioneStr());
			riga.put(6, campione.get_dominioCampCond());
			riga.put(7, campione.get_statoDomandaOPPAB());
			riga.put(8, campione.get_origineCampione());
			riga.put(9, campione.get_bovini());
			riga.put(10, campione.get_ovicaprini());
			riga.put(11, campione.get_note());
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
		elencoCampione = new ElencoCampioneBO();
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
    	LinkedHashMap<Integer, String> listaColonna = CampioneBean.getListaIntestazioni();
    	// verifica della presenza delle colonne
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
    	
    	//3. creazione della lista dei campioni
    	ArrayList<CampioneBO> listaCampione = new ArrayList<CampioneBO>();
    	boolean load_file = getListFromExcel(fileExcel, listaCampione, listLog);
    	if(listaCampione.size() ==0)
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
    	for (CampioneBO campione : listaCampione) {
			// utilizzo ACTION_MODIFY perchè così non fa la verifica sulla pk esistente
    		OperationResultBO result = checkPreInsert(campione, Costanti.ACTION_MODIFY);
    		
			// se il check ha trovato degli errori su questa riga
			if(!result.get_result()) 
			{
				complete_result = false;
				listLog.add("ERROR: domanda:" + campione.get_domanda() + " - errore: " + result.get_message());
				return false;
			}
			else // se il primo gruppo di check é andato bene
			{
				result = checkForImport(campione);
				// se il check ha trovato degli errori su questa riga
				if(!result.get_result()) 
				{
					complete_result = false;
					listLog.add("ERROR: domanda:" + campione.get_domanda() + " - errore: " + result.get_message());
					return false;
				}
			}
		}
    	
    	//5. inserimento dati
    	complete_result = inserimentoMassivo(listaCampione, listLog);
    	
    	if(complete_result) {
    		listLog.add("INFO: upload dei dati terminato con successo. Numero righe contenute nel file: "+ listaCampione.size());
    		return true;
    	}
    	else {
    		listLog.add("ERROR: l'operazione di inserimento dati non é terminata correttamente.");
    		return false;
    	}
    	
    }
	
	/***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	
	
	/**
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update)
     * @param campione
     * @param action
     * @return OperationResultBO
     */
    private OperationResultBO checkPreInsert(CampioneBO campione, String action)
    {
    	OperationResultBO result = new OperationResultBO();
    	
    	//1. campagna valorizzata (non nulla e non uguale a spazio)
    	if(!CheckData.checkString(campione.get_campagna()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMPAGNA);
			return result;
		}
    	
    	//2. CUAA valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(campione.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA);
			return result;
		}
    	
    	//3. CUAA con lunghezza compresa tra 11 e 16 caratteri
    	if(!CheckData.checkCuaaLength(campione.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_LENGTH);
			return result;
		}
    	
    	// 4. cuaa deve essere presente in anagrafica
		_em.clear();
		Azienda aziendaFind = _em.find(Azienda.class, campione.get_cuaa());
		if(aziendaFind == null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_MANCANTE);
			return result;
		}
		
		//5. domanda non nulla
		if(!CheckData.checkString(campione.get_domanda()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_NULLA);
			return result;
		}
		
		//6. domanda esistente in anagrafica
		_em.clear();
		//Domanda domandaFind = _em.find(Domanda.class, campione.get_domanda());
		Query q = _em.createNamedQuery("Domanda.domandaForIDCuaaCampagna");
		q.setParameter("idDomanda", campione.get_domanda());
		q.setParameter("cuaa", campione.get_cuaa());
		q.setParameter("campagna", campione.get_campagna());
		List<String> domandaFind = q.getResultList();
				
		if(domandaFind == null || domandaFind.size()==0)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_MANCANTE);
			return result;
		}
    	
		//7. categoria deve essere presente
		if(campione.get_categoria().equals(""))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMP_CATEGORIA);
			return result;
		}
		
		//8. origine campione deve essere presente
		if(campione.get_origineCampione().equals(""))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMP_ORIGINE);
			return result;
		}	
		
		//9. tipo campione non nulla
		if(!CheckData.checkString(campione.get_tipo()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMP_TIPO);
			return result;
		}
		
		//10. data campione non nulla
		if(!CheckData.checkString(campione.get_dataCampioneStr()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMP_DATA);
			return result;
		}
		
		//11. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(campione.get_note()))
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
     * @param campione
     * @return OperationResultBO
     */
    private OperationResultBO checkForImport(CampioneBO campione)
    {
    	// controllo che il valore dei flag sia uguale a quello in anagrafica    	
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
		// stato domanda OPPAB
    	List<String> lista_valori_stato_domanda = getListaValori(Costanti.ANAGR_CAMP_STATO_DOMANDA_OPPAB);
		if(!campione.get_statoDomandaOPPAB().equals("") && !lista_valori_stato_domanda.contains(campione.get_statoDomandaOPPAB()))
		{
			result.set_result(false);
			result.set_message("Stato Domanda OPPAB - valore non in anagrafica");
			return result;
		}
		// bovini
		List<String> lista_valori_bovini = getListaValori(Costanti.ANAGR_CAMP_BOVINI);
		if(!campione.get_bovini().equals("") && !lista_valori_bovini.contains(campione.get_bovini()))
		{
			result.set_result(false);
			result.set_message("Bovini - valore non in anagrafica");
			return result;
		}
		
		//bovini presenti solo se categoria campione é amm zootecnia
		if(!campione.get_bovini().equals("") && !campione.get_categoria().equals("AMM ZOOTECNIA"))
		{
			result.set_result(false);
			result.set_message("Bovini - il valore non deve essere presente per una categoria diversa da AMM ZOOTECNIA");
			return result;
		}
		
		// ovicaprini
		List<String> lista_valori_ovicaprini = getListaValori(Costanti.ANAGR_CAMP_OVICAPRINI);
		if(!campione.get_ovicaprini().equals("") && 
				!lista_valori_ovicaprini.contains(campione.get_ovicaprini()))
		{
			result.set_result(false);
			result.set_message("Ovicaprini - valore non in anagrafica");
			return result;
		}	
		
		//ovicaprini presenti solo se categoria campione é amm zootecnia
		if(!campione.get_ovicaprini().equals("") && !campione.get_categoria().equals("AMM ZOOTECNIA"))
		{
			result.set_result(false);
			result.set_message("Ovicaprini - il valore non deve essere presente per una categoria diversa da AMM ZOOTECNIA");
			return result;
		}
				
		// categoria
		List<String> lista_valori_categoria = getListaValori(Costanti.ANAGR_CAMP_CATEGORIA);
		if(!campione.get_categoria().equals("") && !lista_valori_categoria.contains(campione.get_categoria()))
		{
			result.set_result(false);
			result.set_message("Categoria - valore non in anagrafica");
			return result;
		}		
		// origine campione
		List<String> lista_valori_origine = getListaValori(Costanti.ANAGR_CAMP_ORIGINE);
		if(!campione.get_origineCampione().equals("") && !lista_valori_origine.contains(campione.get_origineCampione()))
		{
			result.set_result(false);
			result.set_message("Origine Campione - valore non in anagrafica");
			return result;
		}
		// dominio campione
		List<String> lista_valori_dominio = getListaValori(Costanti.ANAGR_DOMINIO_CAMPIONE);
		if(!campione.get_dominioCampCond().equals("") && !lista_valori_dominio.contains(campione.get_dominioCampCond()))
		{
			result.set_result(false);
			result.set_message("Dominio Campione Condizionalita - valore non in anagrafica");
			return result;
		}
		
		// dominio campione presente solo per le categorie di condizionalità
		if(!campione.get_dominioCampCond().equals("") && !campione.get_categoria().equals("COND SUPERFICI")  && !campione.get_categoria().equals("COND ZOOTECNIA")
			 && !campione.get_categoria().equals("COND EXTRA CAMPIONE"))
		{
			result.set_result(false);
			result.set_message("Dominio Campione Condizionalita - il valore non deve essere presente per le categorie diverse da quelle di condizionalità");
			return result;
		}
		
		// tipo campione
		List<String> lista_valori_tipo = getListaValori(Costanti.ANAGR_TIPO_CAMPIONE);
		if(!campione.get_tipo().equals("") && !lista_valori_tipo.contains(campione.get_tipo()))
		{
			result.set_result(false);
			result.set_message("Tipo Campione - valore non in anagrafica");
			return result;
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
		intestazione.put(3, COL_CATEGORIA);
		intestazione.put(4, COL_TIPO_CAMPIONE);
		intestazione.put(5, COL_DATA_CAMPIONE);
		intestazione.put(6, COL_DOMINIO_CAMP);
		intestazione.put(7, COL_STATO_DOMANDA_OPPAB);
		intestazione.put(8, COL_ORIGINE_CAMPIONE);
		intestazione.put(9, COL_BOVINI);
		intestazione.put(10, COL_OVICAPRINI);		
		intestazione.put(11, COL_NOTE);
    	return intestazione;
    }
        
    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel é l'oggetto che contiene i ati letti da file
     * @param lista é la lista da riempire
     * @param logList é la lista dei log
     * @return boolean
     */
    private boolean getListFromExcel(FileExcel fileExcel, ArrayList<CampioneBO> lista, List<String> logList)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		CampioneBO campione = new CampioneBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { campione.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DOMANDA_OPR)) { campione.set_domanda(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { campione.set_campagna(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CATEGORIA)) { campione.set_categoria(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_TIPO_CAMPIONE)) { campione.set_tipo(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DATA_CAMPIONE)) { 
					if(!valore.equals("")) campione.set_dataCampione(Utils.getDate(valore)); 
					continue;
				}
				if(intestazione.equalsIgnoreCase(COL_DOMINIO_CAMP)) { campione.set_dominioCampCond(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_STATO_DOMANDA_OPPAB)) { campione.set_statoDomandaOPPAB(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ORIGINE_CAMPIONE)) { campione.set_origineCampione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_BOVINI)) { campione.set_bovini(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_OVICAPRINI)) { campione.set_ovicaprini(valore); continue; }				
				if(intestazione.equalsIgnoreCase(COL_NOTE)) { campione.set_note(valore); continue; }
			}
			// controllo per evitare di prendere righe vuote
			if(campione.get_cuaa().equals("") || campione.get_campagna().equals("")) continue;	
			
			// se é un campione di condizionalità E
			//se non é stato indicato il dominio del campione e nella matrice di ammissibilità
			// il cuaa ha almeno un atto vet ci mettiamo il codice 2, altrimenti l'1
			if( campione.get_categoria().startsWith("COND") && 
					( campione.get_dominioCampCond() == null || campione.get_dominioCampCond().isEmpty())){				
				campione.set_dominioCampCond("2 - un sottoinsieme tra tutti gli atti/norme applicabili all azienda");
			}
			
			// se il campione é di condizionalità non sarà presente la domanda e va inserita in automatico. 
			// si crea una riga per ogni domanda
			if(campione.get_categoria().startsWith("COND") && campione.get_domanda().equals(""))
			{
				ArrayList<String> listDomande = (ArrayList<String>) getListDomande(campione.get_cuaa(), campione.get_campagna());
				for (String domanda : listDomande) {
					CampioneBO campioneBO = campione.clone();
					campioneBO.set_domanda(domanda);
					// aggiungo alla lista
					lista.add(campioneBO);
					
//					System.out.println("-------------- domanda:" + campioneBO.get_domanda() + " data campioneBO:" + campioneBO.get_dataCampioneStr() + 
//							 " data campione:" + campione.get_dataCampioneStr());
				}				
			}
			else lista.add(campione);
			
			// popolamento delle liste utilizzate per dettagliare il log dell'applicazione
			if(lstCuaa.isEmpty()){ lstCuaa.add(campione.get_cuaa()); } 
			else if(!lstCuaa.contains(campione.get_cuaa())) lstCuaa.add(campione.get_cuaa());
		}
    	logList.add("INFO: Numero CUAA presenti nel file: "+ lstCuaa.size());
    	return true;
    }


    /**
     * esegue l'inserimento di tutte le righe in una sola transazione
     * @param listaCampione
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivo(ArrayList<CampioneBO> listaCampione, List<String> listLog)
    {
    	try {			
			_em.clear();
//			_em.getTransaction().begin();
			
			for (CampioneBO campione : listaCampione) {
				CampionePk pk = new CampionePk();
				pk.set_cuaa(campione.get_cuaa());
				pk.set_campagna(campione.get_campagna());
				pk.set_domanda(campione.get_domanda());
				pk.set_categoria(campione.get_categoria());
				Campione campioneFind = _em.find(Campione.class, pk);
				// se la riga é stata trovata su db la aggiorno
				if(campioneFind != null) {
					campioneFind.set_tipo(campione.get_tipo());
					campioneFind.set_dataCampione(campione.get_dataCampione());
					campioneFind.set_dominioCampCond(campione.get_dominioCampCond());
					campioneFind.set_statoDomandaOPPAB(campione.get_statoDomandaOPPAB());
					campioneFind.set_origineCampione(campione.get_origineCampione());
					campioneFind.set_bovini(campione.get_bovini());
					campioneFind.set_ovicaprini(campione.get_ovicaprini());
					campioneFind.set_note(campione.get_note());
					campioneFind.set_userModifica(Utils.getCurrentUser());
					campioneFind.set_dataModifica(Utils.todayDate());	
					_em.persist(campioneFind);
				}
				else {
					// inserisco la nuova riga
					Campione campioneEntity = campione.getEntity();
					campioneEntity.set_userInserimento(Utils.getCurrentUser());
					campioneEntity.set_dataInserimento(Utils.todayDate());
					_em.persist(campioneEntity);
				}				
			}			
//			_em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			Utils.getLog().error(CampioneBean.class.getSimpleName() + " - inserimentoMassivo: " + e.getMessage());
	    	return false;
		}
    }  
    
	/***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
	
	public List<CampioneBO> getElencoCampione()
    {
        return this.elencoCampione.get_listCampione();
    }

    public void setElencoCampione(final ElencoCampioneBO elencoCampione)
    {
        this.elencoCampione = elencoCampione;
    }
	
}
