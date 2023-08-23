package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.ElencoTutelaAcqueBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.TutelaAcqueBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.TutelaAcque;
import it.bz.prov.esiti.entity.TutelaAcquePk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.ITutelaAcque;
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
 * Bean per la gestione back-end degli esiti sui controlli di tutela acque
 * 
 * @author bpettazzoni
 *
 */

@Stateful
@SuppressWarnings("unchecked")
public class TutelaAcqueBean implements ITutelaAcque {
	
	private static String COL_CUAA = "CUAA";
	private static String COL_CAMPAGNA = "Campagna";
//	private static String COL_COND = "Condizionalità";
//	private static String COL_ALTITUDINE_AZIENDA = "Altitudine Azienda";
	private static String COL_PUNTI_ALTITUDINE = "Punti di Altitudine";
	private static String COL_UBA_ACC = "Uba Accertate TA";
	private static String COL_UBA_ACC_PSR = "Uba Accertate PSR";
//	private static String COL_CARICO_BESTIAME_MAX_TA = "Carico Bestiame max TA";
	private static String COL_CARICO_ALPEGGIO_PSR = "Carico alpeggio PSR";
	private static String COL_CARICO_BESTIAME_ATTUALE_TA = "Carico bestiame attuale TA";
	private static String COL_CARICO_BESTIAME_ATTUALE_PSR = "Carico bestiame attuale PSR";
//	private static String COL_UBA_ECCESSIVE_TA = "UBA Eccessive TA";
//	private static String COL_TOLL_CARICO_BEST_MIN_PSR = "Tolleranza carico bestiame minimo PSR";
	private static String COL_TOLL_CARICO_BEST_MAX_PSR = "Tolleranza carico bestiame massimo PSR";
	private static String COL_TOLL_CARICO_BEST_MAX_TA = "Tolleranza carico bestiame massimo TA";
	private static String COL_CAPACITA_STOCCAGGI = "Capacità stoccaggi";
	private static String COL_STOCCAGGI_IN_FUNZIONE = "Stoccaggi tenuti in funzione";
	private static String COL_ESITO_STOCCAGGI = "Esito stoccaggi";
	private static String COL_DICHIARAZIONI_PRESENTI = "Dichiarazioni presenti";
	private static String COL_ESITO_CARICO_BESTIAME_TA = "Esito carico bestiame TA";
	private static String COL_ESITO_CARICO_BESTIAME_PSR = "Esito carico bestiame PSR";
	private static String COL_AVG_ANN_CARICO_BEST = "Media annuale carico bestiame";
	private static String COL_STATO = "Stato";
//	private static String COL_SECONDO_CONTR_STOCCAGGIO = "Secondo controllo stoccaggio";
//	private static String COL_DATA_SECONDO_CONTR_STOCCAGGIO = "Data secondo controllo stoccaggio";
	private static String COL_NOTE = "Note";	
	
	private ElencoTutelaAcqueBO elencoTutelaAcque = new ElencoTutelaAcqueBO();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	
	/**
	 * aggiunge una tutelaAcque alla lista
	 * @param tutelaAcque
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiTutelaAcque(TutelaAcqueBO tutelaAcque)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(tutelaAcque, Costanti.ACTION_INSERT);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			tutelaAcque.set_data_inserimento(Utils.todayDate());
			tutelaAcque.set_user_inserimento(Utils.getCurrentUser());
			TutelaAcque tutelaAcqueEntity = tutelaAcque.getEntity();
			_em.clear();
//			_em.getTransaction().begin();
			_em.persist(tutelaAcqueEntity);
//			_em.getTransaction().commit();
			// inserimento in elenco
			elencoTutelaAcque.addTutelaAcque(tutelaAcque);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(TutelaAcqueBean.class.getSimpleName() + " - aggiungiTutelaAcque: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * modifica un elemento della lista
	 * @param tutelaAcque
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaTutelaAcque(TutelaAcqueBO tutelaAcque)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(tutelaAcque, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			TutelaAcquePk pk = new TutelaAcquePk();
			pk.set_campagna(tutelaAcque.get_campagna());
			pk.set_cuaa(tutelaAcque.get_cuaa());
			_em.clear();
			TutelaAcque tutelaAcqueFind = _em.find(TutelaAcque.class, pk);
			if(tutelaAcqueFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			tutelaAcque.set_user_modifica(Utils.getCurrentUser());
			tutelaAcque.set_data_modifica(Utils.todayDate());
			tutelaAcque.setEntity(tutelaAcqueFind);
//			_em.getTransaction().commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(TutelaAcqueBean.class.getSimpleName() + " - modificaTutelaAcque: " + e.getMessage());
			e.printStackTrace();
			return result;
		}
	}
	
	/**
	 * cancella un elemento della lista
	 * @param tutelaAcque
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaTutelaAcque(TutelaAcqueBO tutelaAcque)
	{
		OperationResultBO result = new OperationResultBO();
		try {
			_em.clear();
			TutelaAcquePk pk = new TutelaAcquePk();
			pk.set_cuaa(tutelaAcque.get_cuaa());
			pk.set_campagna(tutelaAcque.get_campagna());
			TutelaAcque tutelaFind = _em.find(TutelaAcque.class, pk);
			if(tutelaFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			_em.remove(tutelaFind);
//			_em.getTransaction().commit();
			// cancellazione dall'elenco
			elencoTutelaAcque.remove(tutelaAcque);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(TutelaAcqueBean.class.getSimpleName() + " - cancellaTutelaAcque: " + e.getMessage());
			return result;
		}	
	}

    public List<TutelaAcqueBO> getElencoTutelaAcque()
    {
        return this.elencoTutelaAcque.get_listTutelaAcque();
    }

    public void setElencoTutelaAcque(final ElencoTutelaAcqueBO elencoTutelaAcque)
    {
        this.elencoTutelaAcque = elencoTutelaAcque;
    }
    
    /**
	 * carica i dati della tutela delle acque 
	 */
	public void loadData()
	{
		Query q = _em.createNamedQuery("TutelaAcque.selectAll");
		_em.clear();
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<TutelaAcque> fornitura = q.getResultList();
		elencoTutelaAcque.set_listTutelaAcque(fornitura);
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
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList)
    {
		_em.clear();
    	Query q;
		if(filterByAziendaAttribute(parametersList)) // seleziona solo su azienda
		{
			q = _em.createNamedQuery("TutelaAcque.selectFilterOnlyAzienda");
			setQueryParameterAzienda(parametersList, q);
		}
		else 
		{
			q = _em.createNamedQuery("TutelaAcque.selectFilter");
			Utils.setQueryParameter(parametersList, q);
		}	
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<TutelaAcque> fornitura = q.getResultList();
		elencoTutelaAcque.set_listTutelaAcque(fornitura);
    }
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream)
	{		
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione =  getListaIntestazioni();		
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (TutelaAcqueBO ta : elencoTutelaAcque.get_listTutelaAcque()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, ta.get_cuaa());
			riga.put(1, ta.get_campagna());
//			riga.put(2, ta.get_condizionalita());
//			riga.put(3, ta.get_altitudine_azienda());
			riga.put(2, ta.get_punti_altitudine());
			riga.put(3, ta.get_uba_acc());
			riga.put(4, ta.get_uba_acc_psr());
//			riga.put(5, ta.get_carico_bestiame_max_ta());
			riga.put(5, ta.get_carico_alpeggio_psr());
			riga.put(6, ta.get_carico_bestiame_attuale_ta());
			riga.put(7, ta.get_carico_bestiame_attuale_psr());
//			riga.put(9, ta.get_uba_eccessive_ta());
//			riga.put(10, ta.get_tolleranza_car_best_min_psr());
			riga.put(8, ta.get_tolleranza_car_best_max_ta());
			riga.put(9, ta.get_tolleranza_car_best_max_psr());
			riga.put(10, ta.get_capacita_stoccaggi());
			riga.put(11, ta.get_stoccaggi_tenuti_in_funzione());
			riga.put(12, ta.get_esito_stoccaggi());
			riga.put(13, ta.get_dichiarazioni_presenti());
			riga.put(14, ta.get_esito_carico_bestiame_ta());
			riga.put(15, ta.get_esito_carico_bestiame_psr());
			riga.put(16, ta.get_avg_ann_carico_best());
			riga.put(17, ta.get_stato());
//			riga.put(18, ta.get_secondo_contr_stoccaggio());
//			riga.put(19, ta.get_data_secondo_contr_stoccaggioStr());
			riga.put(18, ta.get_note());

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
		elencoTutelaAcque = new ElencoTutelaAcqueBO();
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
    	LinkedHashMap<Integer, String> listaColonna = TutelaAcqueBean.getListaIntestazioni();
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
    	
    	//3. creazione della lista di tutela acque
    	ArrayList<TutelaAcqueBO> listaTutela = getListFromExcel(fileExcel, listLog);
    	if(listaTutela.size() ==0)
    	{
			listLog.add("ERROR: il file non contiene righe di dato");
			return false;
		}
    	
    	//4. verifica correttezza valori   
    	boolean complete_result = true;
    	for (TutelaAcqueBO tutela : listaTutela) {
    		// utilizzo ACTION_MODIFY perchè così non fa la verifica sulla pk esistente
			OperationResultBO result = checkPreInsert(tutela, Costanti.ACTION_MODIFY);
			// se il check ha trovato degli errori su questa riga
			if(!result.get_result()) 
			{
				complete_result = false;
				listLog.add("ERROR: CUAA:" + tutela.get_cuaa() + " - errore: " + result.get_message());
			}			
		}
    	
    	//5. verifiche ulteriori non fatte nella insert normale
    	OperationResultBO result = checkForImport(listaTutela);
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
    	complete_result = inserimentoMassivo(listaTutela, listLog);
    	    	
    	if(complete_result) listLog.add("INFO: upload dei dati terminato con successo. Numero righe contenute nel file: "+ listaTutela.size());
    	else listLog.add("ERROR: l'operazione di inserimento dati non é terminata correttamente.");
    	return true;
    }
	
	/***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update)
     * @param tutelaAcque
     * @param action
     * @return OperationResultBO
     */
    private OperationResultBO checkPreInsert(TutelaAcqueBO tutelaAcque, String action)
    {
    	OperationResultBO result = new OperationResultBO();
    	
    	//1. campagna valorizzata (non nulla e non uguale a spazio)
    	if(!CheckData.checkString(tutelaAcque.get_campagna()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMPAGNA);
			return result;
		}
    	
    	//2. CUAA valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(tutelaAcque.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA);
			return result;
		}
    	
    	//3. CUAA con lunghezza compresa tra 11 e 16 caratteri
    	if(!CheckData.checkCuaaLength(tutelaAcque.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_LENGTH);
			return result;
		}
    	
    	// 4. cuaa deve essere presente in anagrafica
		_em.clear();
		Azienda aziendaFind = _em.find(Azienda.class, tutelaAcque.get_cuaa());
		if(aziendaFind == null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_MANCANTE);
			return result;
		}
    	
    	// 5. cuaa e campagna non devono essere già presenti in tabella (é la pk) 
		//(da fare solo per insert, no per modify)
		if(action.equals(Costanti.ACTION_INSERT))
		{
			TutelaAcquePk pk = new TutelaAcquePk();
			pk.set_cuaa(tutelaAcque.get_cuaa());
			pk.set_campagna(tutelaAcque.get_campagna());
			_em.clear();
			TutelaAcque tutelaFind = _em.find(TutelaAcque.class, pk);
			if(tutelaFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_CUAA_CAMPAGNA_PRESENTI);
				return result;
			}
		}
		
		//6. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(tutelaAcque.get_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
		
//		//7. l'altitudine azienda deve essere un valore numerico
//		if(!tutelaAcque.get_altitudine_azienda().equals(""))
//			if(!CheckData.checkFloat(tutelaAcque.get_altitudine_azienda()))
//	    	{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_ALTITUDINE_NUMERIC_VALUE);
//				return result;
//			}
//		
//		//8. il valore di uba accertate TA deve essere numerico 
//		if(!tutelaAcque.get_uba_acc().equals(""))
//			if(!CheckData.checkFloat(tutelaAcque.get_uba_acc()))
//	    	{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_UBA_ACC);
//				return result;
//			}
//		
//		//9. il valore di carico bestiame massimo TA deve essere numerico 
//		if(!tutelaAcque.get_carico_bestiame_max_ta().equals(""))
//			if(!CheckData.checkFloat(tutelaAcque.get_carico_bestiame_max_ta()))
//	    	{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_CAR_BEST_MAX_NUMERIC_VALUE);
//				return result;
//			}
//		
//		//10. il valore del carico alpeggio deve essere numerico 
//		if(!tutelaAcque.get_carico_alpeggio_psr().equals(""))
//			if(!CheckData.checkFloat(tutelaAcque.get_carico_alpeggio_psr()))
//	    	{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_CARICO_ALPEGGIO_NUMERIC_VALUE);
//				return result;
//			}
//		
//		//11. il valore del carico bestiame attuale deve essere numerico 
//		if(!tutelaAcque.get_carico_bestiame_attuale_psr().equals(""))
//			if(!CheckData.checkFloat(tutelaAcque.get_carico_bestiame_attuale_psr()))
//	    	{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_CAR_BEST_ATT_NUMERIC_VALUE);
//				return result;
//			}
//		
//		//12. il valore del carico bestiame attuale deve essere numerico 
//		if(!tutelaAcque.get_carico_bestiame_attuale_ta().equals(""))
//			if(!CheckData.checkFloat(tutelaAcque.get_carico_bestiame_attuale_ta()))
//	    	{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_CAR_BEST_ATT_NUMERIC_VALUE);
//				return result;
//			}
//		
//		//13. il valore delle uba eccessive deve essere numerico 
//		if(!tutelaAcque.get_uba_eccessive_ta().equals(""))
//			if(!CheckData.checkFloat(tutelaAcque.get_uba_eccessive_ta()))
//	    	{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_UBA_ECC_NUMERIC_VALUE);
//				return result;
//			}
//		
//		//14. il valore della tolleranza minima deve essere numerico 
//		if(!tutelaAcque.get_tolleranza_car_best_min_psr().equals(""))
//			if(!CheckData.checkFloat(tutelaAcque.get_tolleranza_car_best_min_psr()))
//	    	{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_TOLL_CAR_BEST_NUMERIC_VALUE);
//				return result;
//			}
//		
//		//15. il valore della tolleranza massima deve essere numerico 
//		if(!tutelaAcque.get_tolleranza_car_best_max_psr().equals(""))
//			if(!CheckData.checkFloat(tutelaAcque.get_tolleranza_car_best_max_psr()))
//	    	{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_TOLL_CAR_BEST_NUMERIC_VALUE);
//				return result;
//			}
//		
		// se arrivo qui ha superato i controlli
		result.set_result(true);
    	return result;
    }
	
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di fare la import
     * @param listaTutela
     * @return OperationResultBO
     */
    private OperationResultBO checkForImport(List<TutelaAcqueBO> listaTutela)
    {
    	// controllo che il valore dei falg sia uguale a quello in anagrafica
    	List<String> lista_valori_stocc = getListaValori(Costanti.ANAGR_TUTELA_ACQUE_STOCCAGGI_IN_FUNZIONE);
    	List<String> lista_valori_cap_stoc = getListaValori(Costanti.ANAGR_TUTELA_ACQUE_CAPACITA_STOCCAGGI);
//    	List<String> lista_valori_cond = getListaValori(Costanti.ANAGR_TUTELA_ACQUE_CONDIZIONALITA);
    	List<String> lista_valori_stato = getListaValori(Costanti.ANAGR_STATO);
    	List<String> lista_valori_esito = getListaValori(Costanti.ANAGR_ESITO);
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (TutelaAcqueBO tutela : listaTutela) {
    		// stato
			if(!tutela.get_stato().equals("") && !lista_valori_stato.contains(tutela.get_stato()))
			{
				result.set_result(false);
				result.set_message("ERROR: Stato - valore non in anagrafica");
				break;
			}
			// stoccaggi in funzione
			if(!tutela.get_stoccaggi_tenuti_in_funzione().equals("") && 
					!lista_valori_stocc.contains(tutela.get_stoccaggi_tenuti_in_funzione()))
			{
				result.set_result(false);
				result.set_message("ERROR: Stoccaggi tenuti in funzione - valore non in anagrafica");
				break;
			}
			// esito carico bestiame TA
			if(!tutela.get_esito_carico_bestiame_ta().equals("") && 
					!lista_valori_esito.contains(tutela.get_esito_carico_bestiame_ta()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito carico bestiame TA - valore non in anagrafica");
				break;
			}
			// esito carico bestiame PSR
			if(!tutela.get_esito_carico_bestiame_psr().equals("") && 
					!lista_valori_esito.contains(tutela.get_esito_carico_bestiame_psr()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito carico bestiame PSR - valore non in anagrafica");
				break;
			}
			// capacità stoccaggi
			if(!tutela.get_capacita_stoccaggi().equals("") && 
					!lista_valori_cap_stoc.contains(tutela.get_capacita_stoccaggi()))
			{
				result.set_result(false);
				result.set_message("ERROR: Capacità stoccaggi - valore non in anagrafica");
				break;
			}
			// condizionalita
//			if(!tutela.get_condizionalita().equals("") && 
//					!lista_valori_cond.contains(tutela.get_condizionalita()))
//			{
//				result.set_result(false);
//				result.set_message("ERROR: Condizionalità - valore non in anagrafica");
//				break;
//			}
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
//		intestazione.put(2, COL_COND);
//		intestazione.put(3, COL_ALTITUDINE_AZIENDA);
		intestazione.put(2, COL_PUNTI_ALTITUDINE);
		intestazione.put(3, COL_UBA_ACC);
		intestazione.put(4, COL_UBA_ACC_PSR);
//		intestazione.put(5, COL_CARICO_BESTIAME_MAX_TA);
		intestazione.put(5, COL_CARICO_ALPEGGIO_PSR);
		intestazione.put(6, COL_CARICO_BESTIAME_ATTUALE_TA);
		intestazione.put(7, COL_CARICO_BESTIAME_ATTUALE_PSR);
//		intestazione.put(9, COL_UBA_ECCESSIVE_TA);
//		intestazione.put(10, COL_TOLL_CARICO_BEST_MIN_PSR);
		intestazione.put(8, COL_TOLL_CARICO_BEST_MAX_TA);
		intestazione.put(9, COL_TOLL_CARICO_BEST_MAX_PSR);
		intestazione.put(10, COL_CAPACITA_STOCCAGGI);
		intestazione.put(11, COL_STOCCAGGI_IN_FUNZIONE);
		intestazione.put(12, COL_ESITO_STOCCAGGI);
		intestazione.put(13, COL_DICHIARAZIONI_PRESENTI);
		intestazione.put(14, COL_ESITO_CARICO_BESTIAME_TA);
		intestazione.put(15, COL_ESITO_CARICO_BESTIAME_PSR);
		intestazione.put(16, COL_AVG_ANN_CARICO_BEST);
		intestazione.put(17, COL_STATO);
//		intestazione.put(18, COL_SECONDO_CONTR_STOCCAGGIO);
//		intestazione.put(19, COL_DATA_SECONDO_CONTR_STOCCAGGIO);
		intestazione.put(18, COL_NOTE);
    	return intestazione;
    }
    
    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel é l'oggetto che contiene i ati letti da file
     * @return ArrayList<TutelaAcqueBO>
     */
    private static ArrayList<TutelaAcqueBO> getListFromExcel(FileExcel fileExcel, List<String> logList)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	ArrayList<TutelaAcqueBO> lista = new ArrayList<TutelaAcqueBO>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		TutelaAcqueBO tutela = new TutelaAcqueBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { tutela.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { tutela.set_campagna(valore); continue; }
//				if(intestazione.equalsIgnoreCase(COL_COND)) { tutela.set_condizionalita(valore); continue; }
//				if(intestazione.equalsIgnoreCase(COL_ALTITUDINE_AZIENDA)) { tutela.set_altitudine_azienda(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PUNTI_ALTITUDINE)) { tutela.set_punti_altitudine(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_UBA_ACC)) { tutela.set_uba_acc(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_UBA_ACC_PSR)) { tutela.set_uba_acc_psr(valore); continue; }
//				if(intestazione.equalsIgnoreCase(COL_CARICO_BESTIAME_MAX_TA)) { tutela.set_carico_bestiame_max_ta(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CARICO_BESTIAME_ATTUALE_TA)) { tutela.set_carico_bestiame_attuale_ta(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CARICO_BESTIAME_ATTUALE_PSR)) { tutela.set_carico_bestiame_attuale_psr(valore); continue; }
//				if(intestazione.equalsIgnoreCase(COL_UBA_ECCESSIVE_TA)) { tutela.set_uba_eccessive_ta(valore); continue; }
//				if(intestazione.equalsIgnoreCase(COL_TOLL_CARICO_BEST_MIN_PSR)) { tutela.set_tolleranza_car_best_min_psr(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_TOLL_CARICO_BEST_MAX_TA)) { tutela.set_tolleranza_car_best_max_ta(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_TOLL_CARICO_BEST_MAX_PSR)) { tutela.set_tolleranza_car_best_max_psr(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAPACITA_STOCCAGGI)) { tutela.set_capacita_stoccaggi(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_STOCCAGGI_IN_FUNZIONE)) { tutela.set_stoccaggi_tenuti_in_funzione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ESITO_STOCCAGGI)) { tutela.set_esito_stoccaggi(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DICHIARAZIONI_PRESENTI)) { tutela.set_dichiarazioni_presenti(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ESITO_CARICO_BESTIAME_TA)) { tutela.set_esito_carico_bestiame_ta(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ESITO_CARICO_BESTIAME_PSR)) { tutela.set_esito_carico_bestiame_psr(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_AVG_ANN_CARICO_BEST)) { tutela.set_avg_ann_carico_best(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_STATO)) { tutela.set_stato(valore); continue; }
//				if(intestazione.equalsIgnoreCase(COL_SECONDO_CONTR_STOCCAGGIO)) { tutela.set_secondo_contr_stoccaggio(valore); continue; }
//				if(intestazione.equalsIgnoreCase(COL_DATA_SECONDO_CONTR_STOCCAGGIO)) 
//				{ 
//					if(valore != null && !valore.equals(""))
//					{
//						if(CheckData.checkStringDateFormat(valore))
//							tutela.set_data_secondo_contr_stoccaggio(Utils.getDate(valore)); 
//						else 
//						{
//							logList.add("ERROR: CUAA:" + tutela.get_data_secondo_contr_stoccaggioStr() + " data 1 controllo nel formato errato");
							//return false;
//						}
//					}
//					continue; 
//				}
				if(intestazione.equalsIgnoreCase(COL_NOTE)) { tutela.set_note(valore); continue; }
			}
			// controllo per evitare di prendere righe vuote
			if(!tutela.get_cuaa().equals("") && !tutela.get_campagna().equals("")){
				lista.add(tutela);
				// popolamento delle liste utilizzate per dettagliare il log dell'applicazione
				if(lstCuaa.isEmpty()){
					lstCuaa.add(tutela.get_cuaa());
				}else if(!lstCuaa.contains(tutela.get_cuaa())) lstCuaa.add(tutela.get_cuaa());
			}
		}
    	
    	return lista;
    }


    /**
     * esegue l'inserimento di tutte le righe in una sola transazione
     * @param listaTutela
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivo(ArrayList<TutelaAcqueBO> listaTutela, List<String> listLog)
    {
    	try {			
			_em.clear();
//			_em.getTransaction().begin();
			
			for (TutelaAcqueBO tutela : listaTutela) {
				TutelaAcquePk pk = new TutelaAcquePk();
				pk.set_cuaa(tutela.get_cuaa());
				pk.set_campagna(tutela.get_campagna());
				TutelaAcque tutelaFind = _em.find(TutelaAcque.class, pk);
				// se la riga é stata trovata su db la cancello 
				if(tutelaFind != null) _em.remove(tutelaFind);
				
				// inserisco la riga
				TutelaAcque tutelaEntity = tutela.getEntity();
				tutelaEntity.set_user_inserimento(Utils.getCurrentUser());
				tutelaEntity.set_data_inserimento(Utils.todayDate());
				_em.persist(tutelaEntity);
			}
			
//			_em.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(TutelaAcqueBean.class.getSimpleName() + " - inserimentoMassivo: " + e.getMessage());
	    	return false;
		}
    }

    
    /**
     * verifica se gli unici filtri settati sono quelli dell'azienda (escludo quindi il campione e la domanda)
     * (serve per poter applicare una query dedicata)
     * @param parametersList
     * @return
     */
    private boolean filterByAziendaAttribute(HashMap<String, String> parametersList)
    {
    	for (String key : parametersList.keySet()) {
    		// se il valore é diverso da % e la chiave é diversa da cuaa e ragione sociale
			if(!parametersList.get(key).equals("%") && 
					!key.equals(Costanti.SEARCH_CUAA) && !key.equals(Costanti.SEARCH_RAG_SOC))
				return false;
		}
    	return true;
    }


    /**
     * sulla base dei parametri di ricerca inseriti imposta i parametri nella query di ricerca
     * Imposta solo i parametri per azienda 
     * @param parametersList
     * @param q
     */
	public static void setQueryParameterAzienda(HashMap<String, String> parametersList, Query q) {		
		if(parametersList.containsKey("cuaa") && !parametersList.get("cuaa").equals("%")) 
			q.setParameter("cuaa", "%" + parametersList.get("cuaa") + "%");
		else 
			q.setParameter("cuaa", "%");
		
		if(parametersList.containsKey("ragione_sociale") && !parametersList.get("ragione_sociale").equals("%")) 
			q.setParameter("ragione_sociale", "%" + parametersList.get("ragione_sociale") + "%");
		else 
			q.setParameter("ragione_sociale", "%");
				
		if(parametersList.containsKey("campagna") && !parametersList.get("campagna").equals("%"))
			q.setParameter("campagna", "%" + parametersList.get("campagna") + "%");
		else 
			q.setParameter("campagna", "%");
	}



}
