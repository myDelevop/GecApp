package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.CampioneBO;
import it.bz.prov.esiti.bobject.ControlloBO;
import it.bz.prov.esiti.bobject.ElencoControlloBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.Controllo;
import it.bz.prov.esiti.entity.ControlloPk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.IControllo;
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
 * Bean per la gestione back-end dei dati relativi ai controlli
 * 
 * @author bpettazzoni
 *
 */

@Stateful
@SuppressWarnings("unchecked")
public class ControlloBean implements IControllo {
	
	private static String COL_CUAA = "CUAA";
	private static String COL_CAMPAGNA = "Campagna";
	private static String COL_DATA_PREAVVISO = "Data Preavviso";
	private static String COL_DATA_INIZIO_CONTROLLO = "Data inizio Controllo";
	private static String COL_DATA_FINE_CONTROLLO = "Data Fine Controllo";
	private static String COL_CONTROLLORE = "Controllore";
	private static String COL_TIPO_CONTROLLO = "Tipo Controllo";
	private static String COL_TIPO_PREAVVISO = "Tipo Preavviso";
	private static String COL_PERSONA_PRESENTE = "Persona Presente";
	private static String COL_NOME_DELEGATO = "Nome Delegato";
	private static String COL_NUM_DOC_DELEGATO = "Numero Documento Delegato";
	
	private ElencoControlloBO elencoControllo = new ElencoControlloBO();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	
	/***************************************************************************************
	 * 			GESTIONE DATI CONTROLLO
	 ***************************************************************************************/
	
	
	/**
	 * aggiunge un controllo alla lista
	 * @param controllo
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiControllo(ControlloBO controllo) 
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(controllo, Costanti.ACTION_INSERT);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			controllo.set_user_inserimento(Utils.getCurrentUser());
			controllo.set_data_inserimento(Utils.todayDate());
			_em.clear();
			Controllo controlloEntity = controllo.getEntity();
//			_em.getTransaction().begin();
			_em.persist(controlloEntity);
//			_em.getTransaction().commit();
			// inserimento in elenco
			elencoControllo.addControllo(controllo);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(ControlloBean.class.getSimpleName() + " - aggiungiControllo: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * modifica un controllo alla lista
	 * @param controllo
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaControllo(ControlloBO controllo)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(controllo, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
	
		result = new OperationResultBO();
		try {
			ControlloPk pk = new ControlloPk();
			pk.set_campagna(controllo.get_campagna());
			pk.set_cuaa(controllo.get_cuaa());
			_em.clear();
			Controllo controlloFind = _em.find(Controllo.class, pk);
			// se la riga non é stata trovata su db
			if(controlloFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();	
			controllo.set_user_modifica(Utils.getCurrentUser());
			controllo.set_data_modifica(Utils.todayDate());		
			controllo.setEntity(controlloFind);
//			_em.getTransaction().commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(ControlloBean.class.getSimpleName() + " - modificaControllo: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * cancella un controllo dalla lista
	 * @param controllo
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaControllo(ControlloBO controllo)
	{
		OperationResultBO result = new OperationResultBO();
		try {
			ControlloPk pk = new ControlloPk();
			pk.set_campagna(controllo.get_campagna());
			pk.set_cuaa(controllo.get_cuaa());
			_em.clear();
			Controllo controlloFind = _em.find(Controllo.class, pk);
			if(controlloFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			if(!_em.contains(controlloFind)) {
				Object b = _em.merge(controlloFind);
				_em.remove(b);
			}
			else
			_em.remove(controlloFind);
//			_em.getTransaction().commit();
			// cancello il controllo dalla lista
			elencoControllo.remove(controllo);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(ControlloBean.class.getSimpleName() + " - cancellaControllo: " + e.getMessage());
			return result;
		}
	}
	
	 /**
     * Carica i dati in tabella
     */
    public void loadData()
    {
    	_em.clear();
    	Query q = _em.createNamedQuery("Controllo.selectAll");
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<Controllo> fornitura = q.getResultList();
		elencoControllo.set_listControlli(fornitura);
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
	 * restituisce la lista di tutti i cuaa presenti per una certa campagna
	 * @param campagna
	 * @return List<String>
	 */
	public List<String> getListCuaa(String campagna){
		_em.clear();
		Query q = _em.createNamedQuery("Azienda.selectForCampagna");
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
    	Query q = _em.createNamedQuery("Controllo.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);	
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<Controllo> fornitura = q.getResultList();
		elencoControllo.set_listControlli(fornitura);
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
     * verifica la presenza di un cuaa su DB
     * @param cuaa
     * @return boolean
     */
    public boolean existCuaa(String cuaa)
    {
    	_em.clear();
    	Azienda azienda = _em.find(Azienda.class, cuaa);
    	if(azienda == null) return false; // il cuaa non esiste
    	else return true;
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
		for (ControlloBO controllo : elencoControllo.get_listControlli()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, controllo.get_cuaa());
			riga.put(1, controllo.get_campagna());
			riga.put(2, controllo.get_data_inizio_controlloStr());
			riga.put(3, controllo.get_data_fine_controlloStr());
			riga.put(4, controllo.get_controllore());
			riga.put(5, controllo.get_tipo_controllo());
			riga.put(6, controllo.get_data_preavvisoStr());
			riga.put(7, controllo.get_tipoPreavviso());
			riga.put(8, controllo.get_personaPresente());
			riga.put(9, controllo.get_nomeDelegato());
			riga.put(10, controllo.get_numeroDocumento());
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
		elencoControllo = new ElencoControlloBO();
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
    	LinkedHashMap<Integer, String> listaColonna = ControlloBean.getListaIntestazioni();
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
    	
    	//3. creazione della lista di uba
    	ArrayList<ControlloBO> listaControllo = new ArrayList<ControlloBO>();
    	boolean load_file = getListFromExcel(fileExcel, listaControllo, listLog);
    	if(listaControllo.size() ==0)
    	{
			listLog.add("ERROR: il file non contiene righe di dato");
			return false;
		}
    	if(!load_file)
    	{
			listLog.add("ERROR: i dati non sono stati caricati causa errore nel formato");
			return false;
		}
    	System.out.println("insertFromFile: lista Controlli creata");
    	
//    	//3.a verifica del numero di righe
//    	if(listaControllo.size()>Costanti.IMPORT_NUM_ROW_MAX)
//    	{
//			listLog.add("ERROR: il file può contenere al massimo " + Costanti.IMPORT_NUM_ROW_MAX + "righe da importare");
//			return false;
//    	}
    	
    	//4. verifica correttezza valori   
    	boolean complete_result = true;
    	for (ControlloBO controllo : listaControllo) {
    		// utilizzo ACTION_MODIFY perchè così non fa la verifica sulla pk esistente
			OperationResultBO result = checkPreInsert(controllo, Costanti.ACTION_MODIFY);
			// se il check ha trovato degli errori su questa riga
			if(!result.get_result()) 
			{
				complete_result = false;
				listLog.add("ERROR: CUAA:" + controllo.get_cuaa() + " - errore: " + result.get_message());
			}			
		}
    	if(complete_result) listLog.add("INFO: Verifica coerenza valori delle righe terminata con successo");
    	else 
    	{
    		listLog.add("ERROR: Terminata l'operazione. I dati non sono stati importati.");
    		return false; // termino l'esecuzione del metodo
    	}
    	
    	//5. verifiche non fatte per inserimetno da maschera
    	OperationResultBO result = checkForImport(listaControllo);
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
    	complete_result = inserimentoMassivo(listaControllo, listLog);
    	
    	System.out.println("insertFromFile: FINE");
    	
    	if(complete_result) listLog.add("INFO: upload dei dati terminato con successo. Numero righe contenute nel file: "+ listaControllo.size());
    	else listLog.add("ERROR: l'operazione di inserimento dati non é terminata correttamente.");
    	return true;
    }
    
    /***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update)
     * @param controllo
     * @param action
     * @return OperationResultBO
     */
    private OperationResultBO checkPreInsert(ControlloBO controllo, String action)
    {
    	OperationResultBO result = new OperationResultBO();
    	
    	//1. campagna valorizzata (non nulla e non uguale a spazio)
    	if(!CheckData.checkString(controllo.get_campagna()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMPAGNA);
			return result;
		}
    	
    	//2. CUAA valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(controllo.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA);
			return result;
		}
    	
    	//3. tipo controllo valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(controllo.get_tipo_controllo()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_TIPO_CONTROLLO);
			return result;
		}
    	
    	//4. CUAA con lunghezza compresa tra 11 e 16 caratteri
    	if(!CheckData.checkCuaaLength(controllo.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_LENGTH);
			return result;
		}
    	
    	//5. cuaa e campagna devono già essere presenti nel campione
    	// non ha senso un controllo se l'azinda non é a campione per quella campagna
    	_em.clear();
    	Query q = _em.createNamedQuery("Campione.selectCampioneForCuaaCampagna");
		// imposto i parametri della query
    	q.setParameter("cuaa", controllo.get_cuaa());
    	q.setParameter("campagna", controllo.get_campagna());
		List<CampioneBO> fornitura = q.getResultList();
		if(fornitura == null || fornitura.size()==0)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_NO_CAMPIONE);
			return result;
		}
    	
    	// 6. cuaa e campagna non devono essere già presenti in tabella (é la pk) 
		//(da fare solo per insert, no per modify)
		if(action.equals(Costanti.ACTION_INSERT))
		{
			ControlloPk pk = new ControlloPk();
			pk.set_campagna(controllo.get_campagna());
			pk.set_cuaa(controllo.get_cuaa());
			_em.clear();
			Controllo controlloFind = _em.find(Controllo.class, pk);
			if(controlloFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_CUAA_CAMPAGNA_PRESENTI);
				return result;
			}
		}
		
		// 7. la data di fine controllo deve essere uguale o successiva alla data di inizio controllo
		if(controllo.get_data_inizio_controllo() != null && controllo.get_data_fine_controllo() != null)
			if(!CheckData.checkOrderDate(controllo.get_data_inizio_controllo(), controllo.get_data_fine_controllo()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DATA_INIZIO_FINE_CONTROLLO);
				return result;
			}
		
		// 8. cuaa deve essere presente in anagrafica
		_em.clear();
		Azienda aziendaFind = _em.find(Azienda.class, controllo.get_cuaa());
		if(aziendaFind == null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_MANCANTE);
			return result;
		}
    	
		// se arrivo qui ha superato i controlli
		result.set_result(true);
    	return result;
    }
    
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di fare la import
     * @param listaControlli
     * @return OperationResultBO
     */
    private OperationResultBO checkForImport(List<ControlloBO> listaControlli)
    {

    	List<String> lista_valori_tipo_controllo = getListaValori(Costanti.ANAGR_TIPO_CONTROLLO);
    	List<String> lista_valori_tipo_preavviso = getListaValori(Costanti.ANAGR_TIPO_PREAVVISO);
    	List<String> lista_valori_persona_presente = getListaValori(Costanti.ANAGR_PERSONA_PRESENTE);
    	
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (ControlloBO esito : listaControlli) {
    		// tipo controllo
			if(!esito.get_tipo_controllo().equals("") && !lista_valori_tipo_controllo.contains(esito.get_tipo_controllo()))
			{
				result.set_result(false);
				result.set_message("ERROR: Tipo Controllo - valore non in anagrafica");
				break;
			}
			
			// tipo preavviso
			if(!esito.get_tipoPreavviso().equals("") && !lista_valori_tipo_preavviso.contains(esito.get_tipoPreavviso()))
			{
				result.set_result(false);
				result.set_message("ERROR: Tipo Preavviso - valore non in anagrafica");
				break;
			}
			
			// tipo preavviso
			if(!esito.get_personaPresente().equals("") && !lista_valori_persona_presente.contains(esito.get_personaPresente()))
			{
				result.set_result(false);
				result.set_message("ERROR: Persona Presente - valore non in anagrafica");
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
		intestazione.put(2, COL_DATA_INIZIO_CONTROLLO);
		intestazione.put(3, COL_DATA_FINE_CONTROLLO);
		intestazione.put(4, COL_CONTROLLORE);
		intestazione.put(5, COL_TIPO_CONTROLLO);
		intestazione.put(6, COL_DATA_PREAVVISO);
		intestazione.put(7, COL_TIPO_PREAVVISO);
		intestazione.put(8, COL_PERSONA_PRESENTE);
		intestazione.put(9, COL_NOME_DELEGATO);
		intestazione.put(10, COL_NUM_DOC_DELEGATO);
    	return intestazione;
    }
	
    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel é l'oggetto che contiene i ati letti da file
     * @param lista
     * @param logList
     * @return boolean
     */
    private static boolean getListFromExcel(FileExcel fileExcel, ArrayList<ControlloBO> lista, List<String> logList)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		ControlloBO controllo = new ControlloBO();
    		
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);		
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { controllo.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { controllo.set_campagna(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DATA_PREAVVISO)) 
				{
					if(valore != null && !valore.equals(""))
					{
						if(CheckData.checkStringDateFormat(valore))
							controllo.set_data_preavviso(Utils.getDate(valore));
						else 
						{
							logList.add("ERROR: CUAA:" + controllo.get_cuaa() + " data preavviso nel formato errato");
							return false;
						}
					}
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_DATA_INIZIO_CONTROLLO)) 
				{ 
					if(valore != null && !valore.equals(""))
					{
						if(CheckData.checkStringDateFormat(valore))
							controllo.set_data_inizio_controllo(Utils.getDate(valore)); 
						else 
						{
							logList.add("ERROR: CUAA:" + controllo.get_cuaa() + " data inizio controllo nel formato errato");
							return false;
						}
					}
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_DATA_FINE_CONTROLLO)) 
				{ 
					if(valore != null && !valore.equals(""))
					{
						if(CheckData.checkStringDateFormat(valore))
							controllo.set_data_fine_controllo(Utils.getDate(valore)); 
						else 
						{
							logList.add("ERROR: CUAA:" + controllo.get_cuaa() + " data fine controllo nel formato errato");
							return false;
						}
					}
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_CONTROLLORE)) { controllo.set_controllore(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_TIPO_CONTROLLO)) { controllo.set_tipo_controllo(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERSONA_PRESENTE)) { controllo.set_personaPresente(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NOME_DELEGATO)) { controllo.set_nomeDelegato(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NUM_DOC_DELEGATO)) { controllo.set_numeroDocumento(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_TIPO_PREAVVISO)) { controllo.set_tipoPreavviso(valore); continue; }
			}
			// controllo per evitare di prendere righe vuote
			if(!controllo.get_cuaa().equals("") && !controllo.get_campagna().equals("")){
				lista.add(controllo);
				// popolamento delle liste utilizzate per dettagliare il log dell'applicazione
				if(lstCuaa.isEmpty()){
					lstCuaa.add(controllo.get_cuaa());
				}else if(!lstCuaa.contains(controllo.get_cuaa())) lstCuaa.add(controllo.get_cuaa());
			}
		}
    	
    	logList.add("INFO: Numero CUAA presenti nel file: "+ lstCuaa.size());
    	return true;
    }
	
    
    /**
     * esegue l'inserimento di tutte le righe in una sola transazione
     * @param listaControllo
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivo(ArrayList<ControlloBO> listaControllo, List<String> listLog)
    {
    	try {			
			_em.clear();
//			_em.getTransaction().begin();
			
			for (ControlloBO controllo : listaControllo) {
				ControlloPk pk = new ControlloPk();
				pk.set_cuaa(controllo.get_cuaa());
				pk.set_campagna(controllo.get_campagna());
				Controllo controlloFind = _em.find(Controllo.class, pk);
				// se la riga é stata trovata su db la cancello 
				if(controlloFind != null) _em.remove(controlloFind);
				
				//se la data di fine controllo non é valorizzata prende quella di inizio controllo
				if(controllo.get_data_fine_controlloStr().equals("") && !controllo.get_data_inizio_controlloStr().equals(""))
					controllo.set_data_fine_controllo(controllo.get_data_inizio_controllo());
				
				// inserisco la riga
				Controllo controlloEntity = controllo.getEntity();
				controlloEntity.set_user_inserimento(Utils.getCurrentUser());
				controlloEntity.set_data_inserimento(Utils.todayDate());
				_em.persist(controlloEntity);
			}
			
//			_em.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(ControlloBean.class.getSimpleName() + " - inserimentoMassivo: " + e.getMessage());
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
	
	/***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
	
	public void setElencoControllo(ElencoControlloBO elencoControllo) {
		this.elencoControllo = elencoControllo;
	}

	public List<ControlloBO> getElencoControllo() {
		return elencoControllo.get_listControlli();
	}
	
	
}
