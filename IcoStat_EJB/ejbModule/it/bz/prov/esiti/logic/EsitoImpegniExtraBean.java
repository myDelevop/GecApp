package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.ElencoEsitoImpegniExtraBO;
import it.bz.prov.esiti.bobject.EsitoImpegniExtraBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.EsitoImpegniExtra;
import it.bz.prov.esiti.entity.EsitoImpegniExtraPk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.IEsitoImpegniExtra;
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
public class EsitoImpegniExtraBean implements IEsitoImpegniExtra{
	
	private static String COL_CUAA						 = "CUAA";
	private static String COL_DOMANDA                    = "Domanda";
	private static String COL_CAMPAGNA                   = "Campagna";
	private static String COL_INTERVENTO                 = "Intervento";
	private static String COL_SOTTOINTERVENTO            = "Sottointervento";
	private static String COL_ESITO                      = "Esito";
	private static String COL_IMPEGNO                    = "Impegno";
	private static String COL_PERCRID                    = "Perc. Riduzione";
	private static String COL_STATO                      = "Stato";
	private static String COL_ESITORINUNCIAINSILATO      = "Esito Rinuncia Insilato";
	private static String COL_IMPORTORIDRINUNCIAINSILATO = "Importo Riduzione Rinuncia Insilato";
	private static String COL_ESCLUSIONE                 = "Esclusione";
	private static String COL_ESCLUSIONENOTE             = "Note Esclusione";
	private static String COL_REITERAZIONE               = "Reiterazione";
	private static String COL_PROGR_ACCERT_REITERAZ      = "Prg. Accertamento Reiterazione";
	private static String COL_INADEM_GRAVE 				 = "Inadempienza Grave";
	private static String COL_PORTATA                    = "Portata";
	private static String COL_GRAVITA                    = "Gravita";
	private static String COL_DURATA                     = "Durata";
	private static String COL_SEGNALAZIONE               = "Segnalazione";
	private static String COL_APPROVAZIONE               = "Approvazione";
	private static String COL_PORTATA_NOTE               = "Note Portata";
	private static String COL_GRAVITA_NOTE               = "Note Gravita";
	private static String COL_DURATA_NOTE                = "Note Durata";
	private static String COL_NUMERO_DECRETO             = "Numero Decreto";
	private static String COL_DATA_DECRETO               = "Data Decreto";
	private static String COL_NOTE                       = "Note";
	private static String COL_DATA_CONTROLLO             = "Data Controllo";
	
	private ElencoEsitoImpegniExtraBO elencoEsitoImpegni = new ElencoEsitoImpegniExtraBO();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	
	/**
	 * aggiunge un esito alla lista
	 * @param EsitoImpegniExtra
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsito(EsitoImpegniExtraBO EsitoImpegniExtra)
	{
		OperationResultBO result = checkPreInsert(EsitoImpegniExtra, Costanti.ACTION_INSERT);
		if(!result.get_result()) return result;
		
		result = new OperationResultBO();
		try {
			EsitoImpegniExtra.set_user_inserimento(Utils.getCurrentUser());
			EsitoImpegniExtra.set_data_inserimento(Utils.todayDate());
			EsitoImpegniExtra esitoEntity = EsitoImpegniExtra.getEntity();
			_em.clear();
			_em.persist(esitoEntity);
			// inserimento in elenco
			elencoEsitoImpegni.addEsito(EsitoImpegniExtra);
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoImpegniExtraBean.class.getSimpleName() + " - aggiungiEsito: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * modifica un esito alla lista
	 * @param EsitoImpegniExtra
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsito(EsitoImpegniExtraBO EsitoImpegniExtra)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(EsitoImpegniExtra, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			EsitoImpegniExtraPk pk = new EsitoImpegniExtraPk();
			pk.set_domanda(EsitoImpegniExtra.get_domanda());
			pk.set_intervento(EsitoImpegniExtra.get_intervento());
			pk.set_sottointervento(EsitoImpegniExtra.get_sottointervento());
			pk.set_impegno(EsitoImpegniExtra.get_impegno());
			_em.clear();
			// recupero l'oggetto su db
			EsitoImpegniExtra esitoFind = _em.find(EsitoImpegniExtra.class, pk);
			// se la riga non é stata trovata su db
			if(esitoFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
			EsitoImpegniExtra.set_user_modifica(Utils.getCurrentUser()); 
			EsitoImpegniExtra.set_data_modifica(Utils.todayDate());
			EsitoImpegniExtra.setEntity(esitoFind);
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
			Utils.getLog().error(EsitoImpegniExtraBean.class.getSimpleName() + " - modificaEsito: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * cancella un esito dalla lista
	 * @param EsitoImpegniExtra
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoImpegniExtraBO EsitoImpegniExtra)
	{
		OperationResultBO result = new OperationResultBO();
		try {
			_em.clear();
			EsitoImpegniExtraPk pk = new EsitoImpegniExtraPk();
			pk.set_domanda(EsitoImpegniExtra.get_domanda());
			pk.set_intervento(EsitoImpegniExtra.get_intervento());
			pk.set_sottointervento(EsitoImpegniExtra.get_sottointervento());
			pk.set_impegno(EsitoImpegniExtra.get_impegno());
			EsitoImpegniExtra esitoFind = _em.find(EsitoImpegniExtra.class, pk);
			if(esitoFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
			_em.remove(esitoFind);
			// cancellazione dall'elenco
			elencoEsitoImpegni.remove(EsitoImpegniExtra);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoImpegniExtraBean.class.getSimpleName() + " - cancellaEsito: " + e.getMessage());
			return result;
		}
	}

    public List<EsitoImpegniExtraBO> getElencoEsitoImpegni()
    {
        return this.elencoEsitoImpegni.get_listEsitoImpegni();
    }

    public void setElencoEsitoImpegni(final ElencoEsitoImpegniExtraBO elencoEsitoImpegni)
    {
        this.elencoEsitoImpegni = elencoEsitoImpegni;
    }
    
    
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData()
	{
		Query q = _em.createNamedQuery("EsitoImpegniExtra.selectAll");
		_em.clear();
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoImpegniExtra> fornitura = q.getResultList();
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
	 * restituisce la lista di tutti i sottointerventi presenti
	 * @return List<String>
	 */
	public List<String> getListImpegno(){
		// da valutare se mettere anche gli impegni a DB.. per il momento di default lasciamo "XXX"
		List<String> fornitura = new ArrayList<String>();
		//fornitura.add("XXX");
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
	 * restituisce la lista di tutti gli interventi presenti per una specifica campagna
	 * @return List<String>
	 */
	public List<String> getListImpegnoForIntAndSottoInt (String intervento, String sottoIntervento) {
		// da valutare se fisicizzare queste informazioni nel database
		List<String> fornitura = new ArrayList<String>();
		
		switch(intervento) {
			case "10.4":
				switch(sottoIntervento) {
					case "10.4.1":
						fornitura.add("trasformazione");
						fornitura.add("concimi");
						fornitura.add("drenaggi");
						fornitura.add("sfalcio");
						fornitura.add("periodo sfalcio");
						break;
					case "10.4.2":
						fornitura.add("trasformazione");
						fornitura.add("concimi");
						fornitura.add("sfalcio");
						break;
					case "10.4.3":
						fornitura.add("drenaggi");
						fornitura.add("pascolo/concimi");
						fornitura.add("sfalcio");
						fornitura.add("periodo sfalcio");
						break;
					case "10.4.4":
						fornitura.add("trasformazione");
						fornitura.add("concimi");
						fornitura.add("sfalcio");
						fornitura.add("ramaglia");
						break;
					case "10.4.5":
						fornitura.add("spianamenti");
						fornitura.add("concimi");
						fornitura.add("sfalcio");
						fornitura.add("ramaglia");
						break;
					case "10.4.6":
						fornitura.add("spianamenti");
						fornitura.add("concimi");
						fornitura.add("ramaglia");
						break;
					case "10.4.7":
						fornitura.add("spianamenti");
						fornitura.add("concimi");
						fornitura.add("ramaglia");
						break;
					case "10.4.8":
						fornitura.add("drenaggio");
						fornitura.add("fertilizzante");
						break;
					case "10.4.9":
						fornitura.add("larghezza");
						fornitura.add("fertilizzanti");
						fornitura.add("periodo sfalcio");
						break;
					default:
						break;
				}
				break;
			case "10.1":
				fornitura.add("carico bestiame minimo");
				fornitura.add("carico bestiame massimo");
				fornitura.add("sfalcio");
				fornitura.add("erbicidi");
				fornitura.add("concimi");
				fornitura.add("insilamento (premio aggiuntivo)");
				break;
			case "10.2":
				fornitura.add("mantenimento UBA");
				fornitura.add("carico bestiame minimo");
				fornitura.add("carico bestiame massimo");
				break;
			case "10.3":
				fornitura.add("divieto pascolo");
				fornitura.add("registro malga");
				fornitura.add("concimi");
				fornitura.add("lavorazione terreno");
				fornitura.add("sorveglianza");
				fornitura.add("animali minimi (premio aggiuntivo)");
				break;
			case "11.1":
				switch(sottoIntervento) {
					case "11.1.1":
						fornitura.add("carico bestiame minimo");
						fornitura.add("carico bestiame massimo");
						fornitura.add("sfalcio");
						break;
					case "11.1.2": case "11.1.3":
						fornitura.add("coltivazione");
						break;
					default:
						break;
				}
				break;
			case "11.2":
				switch(sottoIntervento) {
				case "11.2.1":
					fornitura.add("carico bestiame minimo");
					fornitura.add("carico bestiame massimo");
					fornitura.add("sfalcio");
					break;
				case "11.2.2": case "11.2.3":
					fornitura.add("coltivazione");
					break;
				default:
					break;
			}
			break;
			case "13":
				fornitura.add("carico bestiame massimo");
				fornitura.add("sfalcio");
				fornitura.add("pascolo");
				fornitura.add("proseguimento attività");
				fornitura.add("distanza minima (premio aggiuntivo)");
				fornitura.add("discordanza distanza (premio aggiuntivo)");
				break;
			default: 
				break;
		}
		
		//fornitura.add("XXX");
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
		return fornitura;
	}
	
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList)
    {
		_em.clear();
    	Query q = _em.createNamedQuery("EsitoImpegniExtra.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);		
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoImpegniExtra> fornitura = q.getResultList();
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
		for (EsitoImpegniExtraBO esito : elencoEsitoImpegni.get_listEsitoImpegni()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0  ,  esito.get_cuaa());
			riga.put(1  ,  esito.get_campagna());
			riga.put(2  ,  esito.get_domanda());
			riga.put(3  ,  esito.get_intervento());
			riga.put(4  ,  esito.get_sottointervento());
			riga.put(5  ,  esito.get_impegno());	
			riga.put(6  ,  esito.get_data_controlloStr());	
			riga.put(7  ,  esito.get_esito());
			riga.put(8  ,  esito.get_percRid());
			riga.put(9  ,  esito.get_stato());
			riga.put(10 ,  esito.get_esitoRinunciaInsilato());
			riga.put(11 ,  esito.get_importoRidRinunciaInsilato());
			riga.put(12 ,  esito.get_esclusione());
			riga.put(13 ,  esito.get_esclusioneNote());
			riga.put(14 ,  esito.get_reiterazione());
			riga.put(15 ,  esito.get_progr_accert_reiteraz());
			riga.put(16 ,  esito.get_inadempienza_grave());
			riga.put(17 ,  esito.get_portata());
			riga.put(18 ,  esito.get_portata_note());
			riga.put(19 ,  esito.get_gravita());
			riga.put(20 ,  esito.get_gravita_note());
			riga.put(21 ,  esito.get_durata());
			riga.put(22 ,  esito.get_durata_note());
			riga.put(23 ,  esito.get_segnalazione());
			riga.put(24 ,  esito.get_approvazione());
			riga.put(25 ,  esito.get_numero_decreto());
			riga.put(26 ,  esito.get_data_decretoString());
			riga.put(27 ,  esito.get_note());
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
		elencoEsitoImpegni = new ElencoEsitoImpegniExtraBO();
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
    	LinkedHashMap<Integer, String> listaColonna = EsitoImpegniExtraBean.getListaIntestazioni();
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
    	ArrayList<EsitoImpegniExtraBO> listaEsiti = new ArrayList<EsitoImpegniExtraBO>(); 
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
    	for (EsitoImpegniExtraBO esito : listaEsiti) {
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
    private OperationResultBO checkPreInsert(EsitoImpegniExtraBO esito, String action)
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
		
		//6. Data del Controllo non nulla e che sia valorizzata secondo il formato dd/MM/yyyy
		if(!CheckData.checkStringDateFormat(esito.get_data_controlloStr())) {
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DATA_NULLA);
			return result;
		}		
		
		//7. domanda esistente in anagrafica
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
		
		//8. intervento valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_intervento()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_INTERVENTO_NULLO);
			return result;
		}
    	
    	//9. sottointervento valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_sottointervento()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_SOTTOINTERVENTO_NULLO);
			return result;
		}

    	
    	//9. impegno valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_impegno()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_IMPEGNO_NULLO);
			return result;
		}

    	//10. la domanda indicata ha l'intervento e il sottointervento specificati
    	_em.clear();
    	Query q1 = _em.createNamedQuery("Sottointervento.domandaHasInterventoAndSottointervento");
		q1.setParameter("domanda", esito.get_domanda());
		q1.setParameter("intervento", esito.get_intervento());
		q1.setParameter("sottointervento", esito.get_sottointervento());
		List<String> sottointEntity = q1.getResultList();
				
		if(sottointEntity == null || sottointEntity.size()==0)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_SOTTOINTERVENTO_MANCANTI);
			return result;
		}
    	
    	// 11. domanda, intervento e sottointervento non devono essere già presenti in tabella (é la pk) 
		//(da fare solo per insert, no per modify)
		if(action.equals(Costanti.ACTION_INSERT))
		{
			EsitoImpegniExtraPk pk = new EsitoImpegniExtraPk();
			pk.set_domanda(esito.get_domanda());
			pk.set_intervento(esito.get_intervento());
			pk.set_sottointervento(esito.get_sottointervento());
			pk.set_impegno(esito.get_impegno());
			_em.clear();
			EsitoImpegniExtra esitoFind = _em.find(EsitoImpegniExtra.class, pk);
			if(esitoFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DOMANDA_INTERVENTO_SOTTOINTERVENTO_PRESENTI);
				return result;
			}
		}
		
		//12. percentuale di riduzione OPPAB deve essere un valore numerico
		if(esito.get_percRid()!= null && !esito.get_percRid().isEmpty())
			if(!CheckData.checkFloat(esito.get_percRid()))
	    	{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
				return result;
			}
		
		//13. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(esito.get_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
		
		//16a. importo riduzione rinuncia insilato valorizzato se esito negativo
		if(esito.get_importoRidRinunciaInsilato().isEmpty() && esito.get_esitoRinunciaInsilato().equals("NEGATIVO"))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_IMPORTO_RID_RINUNCIA_INSILATO_EMPTY);
			return result;
		}
		
		//16b. importo riduzione rinuncia insilato deve essere un valore numerico
		if(!esito.get_importoRidRinunciaInsilato().isEmpty())
			if(!CheckData.checkFloat(esito.get_importoRidRinunciaInsilato().toString()))
	    	{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_IMPORTO_RID_RINUNCIA_INSILATO);
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
    private OperationResultBO checkForImport(List<EsitoImpegniExtraBO> listaImpegni)
    {
    	// controllo che il valore dei flag sia uguale a quello in anagrafica
    	List<String> lista_valori_stato = getListaValori(Costanti.ANAGR_STATO);
    	List<String> lista_valori_esito = getListaValori(Costanti.ANAGR_ESITO);
    	List<String> lista_valori_impegno = getListaValori(Costanti.ANAGR_IMPEGNO);
		List<String> lista_durata = getListaValori(Costanti.ANAGR_DURATA);
    	List<String> lista_gravita = getListaValori(Costanti.ANAGR_GRAVITA);
		List<String> lista_inadem_grave = getListaValori(Costanti.ANAGR_INADEMPIENZA_GRAVE);
		List<String> lista_portata = getListaValori(Costanti.ANAGR_PORTATA);
    	List<String> lista_progr_acc = getListaValori(Costanti.ANAGR_PROGRESSIVO_ACCERTAMENTO);
    	List<String> lista_reiterazione = getListaValori(Costanti.ANAGR_REITERAZIONE);
		//Riutilizzo questa costante perchè contiene già i valori SI/NO
		List<String> lista_segnalazione = getListaValori(Costanti.ANAGR_AZIONE_CORRETTIVA_RICHIESTA);
		
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (EsitoImpegniExtraBO esito : listaImpegni) {
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

			// impegno
			if(!esito.get_impegno().isEmpty() && !lista_valori_impegno.contains(esito.get_impegno()))
			{
				result.set_result(false);
				result.set_message("ERROR: Impegno - valore non in anagrafica");
				break;
			}

			
			// esito Rinuncia Insilato
			if(!esito.get_esitoRinunciaInsilato().isEmpty() && !lista_valori_esito.contains(esito.get_esito()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito Rinuncia Insilato - valore non in anagrafica");
				break;
			}
			
			// importo riduzione Rinuncia Insilato non valorizzato quando esito negativo 
			if(esito.get_esitoRinunciaInsilato().equals("NEGATIVO") && 
					esito.get_importoRidRinunciaInsilato().isEmpty())
			{
				result.set_result(false);
				result.set_message("ERROR: Importo Riduzione Rinuncia Insilato - valore mancante per esito negativo");
				break;
			}
			
			// importo riduzione Rinuncia Insilato valorizzato quando esito non negativo 
			if(!esito.get_esitoRinunciaInsilato().equals("NEGATIVO") && !esito.get_importoRidRinunciaInsilato().isEmpty())
			{
				result.set_result(false);
				result.set_message("ERROR: Importo Riduzione Rinuncia Insilato - non deve essere valorizzato se esito diverso da negativo");
				break;
			}
			// esclusione
		    if(!esito.get_esclusione().isEmpty() && 
		    			!lista_segnalazione.contains(esito.get_esclusione()))
		    {
		    	result.set_result(false);
		    	result.set_message("ERROR: esclusione - valore non in anagrafica");
		    	break;
		    }
			// reiterazione
		    if(!esito.get_reiterazione().isEmpty() && 
		    			!lista_reiterazione.contains(esito.get_reiterazione()))
		    {
		    	result.set_result(false);
		    	result.set_message("ERROR: reiterazione - valore non in anagrafica");
		    	break;
		    }
		    // progr. accert. reiterazione
		    if(!esito.get_progr_accert_reiteraz().isEmpty() && 
		    			!lista_progr_acc.contains(esito.get_progr_accert_reiteraz()))
		    {
		    	result.set_result(false);
		    	result.set_message("ERROR: progr. accert. reiterazione - valore non in anagrafica");
		    	break;
		    }
		    
		    // inademp. grave
		    if(!esito.get_inadempienza_grave().isEmpty() && 
		    			!lista_inadem_grave.contains(esito.get_inadempienza_grave()))
		    {
		    	result.set_result(false);
		    	result.set_message("ERROR: Inadempienza grave - valore non in anagrafica");
		    	break;
		    }

		    // portata
		    if(!esito.get_portata().isEmpty() && 
		    			!lista_portata.contains(esito.get_portata()))
		    {
		    	result.set_result(false);
		    	result.set_message("ERROR: portata - valore non in anagrafica");
		    	break;
		    }
		    
		    // gravita
		    if(!esito.get_gravita().isEmpty() && 
		    			!lista_gravita.contains(esito.get_gravita()))
		    {
		    	result.set_result(false);
		    	result.set_message("ERROR: gravita - valore non in anagrafica");
		    	break;
		    }
		    // durata
		    if(!esito.get_durata().isEmpty() && 
		    			!lista_durata.contains(esito.get_durata()))
		    {
		    	result.set_result(false);
		    	result.set_message("ERROR: durata - valore non in anagrafica");
		    	break;
		    }
		    // Segnalazione
		    if(!esito.get_segnalazione().isEmpty() && 
		    			!lista_segnalazione.contains(esito.get_segnalazione()))
		    {
		    	result.set_result(false);
		    	result.set_message("ERROR: Segnalazione - valore non in anagrafica");
		    	break;
		    }
		    // Approvazione
		    if(!esito.get_approvazione().isEmpty() && 
		    			!lista_segnalazione.contains(esito.get_approvazione()))
		    {
		    	result.set_result(false);
		    	result.set_message("ERROR: Approvazione - valore non in anagrafica");
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
    	intestazione.put(0 , COL_CUAA                      );
    	intestazione.put(1 , COL_CAMPAGNA                  );
    	intestazione.put(2 , COL_DOMANDA                   );
    	intestazione.put(3 , COL_INTERVENTO                );
    	intestazione.put(4 , COL_SOTTOINTERVENTO	       );
    	intestazione.put(5 , COL_IMPEGNO			       );
    	intestazione.put(6 , COL_DATA_CONTROLLO			   );
    	intestazione.put(7 , COL_ESITO                     );
    	intestazione.put(8 , COL_PERCRID                   );
    	intestazione.put(9 , COL_STATO                     );
    	intestazione.put(10 , COL_ESITORINUNCIAINSILATO     );
    	intestazione.put(11, COL_IMPORTORIDRINUNCIAINSILATO);
    	intestazione.put(12, COL_ESCLUSIONE                );
    	intestazione.put(13, COL_ESCLUSIONENOTE            );
    	intestazione.put(14, COL_REITERAZIONE              );
    	intestazione.put(15, COL_PROGR_ACCERT_REITERAZ     );
    	intestazione.put(16, COL_INADEM_GRAVE              );
    	intestazione.put(17, COL_PORTATA                   );
    	intestazione.put(18, COL_PORTATA_NOTE              );
    	intestazione.put(19, COL_GRAVITA                   );
    	intestazione.put(20, COL_GRAVITA_NOTE              );
    	intestazione.put(21, COL_DURATA                    );
    	intestazione.put(22, COL_DURATA_NOTE               );
    	intestazione.put(23, COL_SEGNALAZIONE              );
    	intestazione.put(24, COL_APPROVAZIONE              );
    	intestazione.put(25, COL_NUMERO_DECRETO            );
    	intestazione.put(26, COL_DATA_DECRETO              );
		intestazione.put(27, COL_NOTE					   );
		
    	return intestazione;
    }
    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel é l'oggetto che contiene i sati letti da file
     * @param lista 
     * @param logList
     * @return boolean 
     */
    private static boolean getListFromExcel(FileExcel fileExcel, ArrayList<EsitoImpegniExtraBO> lista, List<String> logList)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	List<String> lstDomande = new ArrayList<String>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		EsitoImpegniExtraBO esito = new EsitoImpegniExtraBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA                   	)) { esito.set_cuaa                   	 (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_DOMANDA                    )) { esito.set_domanda                   (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA                   )) { esito.set_campagna                  (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_INTERVENTO                 )) { esito.set_intervento                (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_SOTTOINTERVENTO	        )) { esito.set_sottointervento	         (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_IMPEGNO			        )) { esito.set_impegno			         (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_ESITO                      )) { esito.set_esito                     (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_PERCRID                    )) { if(valore != null && !valore.isEmpty()) esito.set_percRid                   (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_STATO                      )) { esito.set_stato                     (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_ESITORINUNCIAINSILATO      )) { esito.set_esitoRinunciaInsilato     (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_IMPORTORIDRINUNCIAINSILATO )) { if(valore != null && !valore.isEmpty()) esito.set_importoRidRinunciaInsilato(valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_ESCLUSIONE                 )) { esito.set_esclusione                (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_ESCLUSIONENOTE             )) { esito.set_esclusioneNote            (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_REITERAZIONE               )) { esito.set_reiterazione              (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_PROGR_ACCERT_REITERAZ      )) { esito.set_progr_accert_reiteraz     (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_INADEM_GRAVE				)) { esito.set_inadempienza_grave		 (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_PORTATA                    )) { esito.set_portata                   (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_PORTATA_NOTE               )) { esito.set_portata_note              (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_GRAVITA                    )) { esito.set_gravita                   (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_GRAVITA_NOTE               )) { esito.set_gravita_note              (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_DURATA                     )) { esito.set_durata                    (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_DURATA_NOTE                )) { esito.set_durata_note               (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_SEGNALAZIONE               )) { esito.set_segnalazione              (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_APPROVAZIONE               )) { esito.set_approvazione              (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_NUMERO_DECRETO             )) { esito.set_numero_decreto            (valore) ; continue;}
				if(intestazione.equalsIgnoreCase(COL_DATA_DECRETO				)) 
				{ 
					if(valore != null && !valore.isEmpty())
					{
						if(CheckData.checkStringDateFormat(valore))
							esito.set_data_decreto(Utils.getDate(valore)); 
						else 
						{
							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " data decreto nel formato errato");
							return false;
						}
					}
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_DATA_CONTROLLO				)) 
				{ 
					if(valore != null && !valore.isEmpty())
					{
						if(CheckData.checkStringDateFormat(valore))
							esito.set_data_controllo(Utils.getDate(valore)); 
						else 
						{
							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " data controllo nel formato errato");
							return false;
						}
					}
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_NOTE             			)) { esito.set_note            			(valore) ; continue;}
			}
			
			// controllo per evitare di prendere righe vuote
			if(!esito.get_cuaa().isEmpty() && !esito.get_campagna().isEmpty()){
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
    private boolean inserimentoMassivo(ArrayList<EsitoImpegniExtraBO> listaEsiti, List<String> listLog)
    {
    	try {			
			_em.clear();
			
			for (EsitoImpegniExtraBO esito : listaEsiti) {
				EsitoImpegniExtraPk pk = new EsitoImpegniExtraPk();
				pk.set_domanda(esito.get_domanda());
				pk.set_intervento(esito.get_intervento());
				pk.set_sottointervento(esito.get_sottointervento());
				pk.set_impegno(esito.get_impegno());
				EsitoImpegniExtra esitoFind = _em.find(EsitoImpegniExtra.class, pk);
				// se la riga é stata trovata su db la cancello 
				if(esitoFind != null) _em.remove(esitoFind);
				
				// inserisco la riga
				EsitoImpegniExtra esitoEntity = esito.getEntity();
				esitoEntity.set_user_inserimento(Utils.getCurrentUser());
				esitoEntity.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoEntity);
			}			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(EsitoImpegniExtraBean.class.getSimpleName() + " - inserimentoMassivo: " + e.getMessage());
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
	
	public List<String> getListDomandePSR(String cuaa, String campagna) {
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.domandeForCuaaPSR");
		q.setParameter("cuaa", cuaa);
		q.setParameter("campagna", campagna);
		List<String> result = q.getResultList();
		return result;
	}

	@Override
	public List<String> checkForWarningsEsitoImpegniExtra(EsitoImpegniExtraBO esitoImpegni) {
		List<String> warnings = new ArrayList<String>();
		
		// 1 - WARNING
		try {
			if(esitoImpegni.get_reiterazione().equalsIgnoreCase("SI") && esitoImpegni.get_progr_accert_reiteraz().isEmpty())
				warnings.add(Costanti.REITER_SI_PROGR_REIT_VUOTO);			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.REITER_SI_PROGR_REIT_VUOTO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 2 - WARNING
		try {
			if(esitoImpegni.get_reiterazione().equalsIgnoreCase("NO") && !esitoImpegni.get_progr_accert_reiteraz().isEmpty())
				warnings.add(Costanti.REITER_NO_PROGR_REIT_VALORIZ);
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.REITER_NO_PROGR_REIT_VALORIZ
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 3 - WARNING
		try {
			Integer progrReit = 0;
			
			if(!esitoImpegni.get_progr_accert_reiteraz().isEmpty())
				progrReit = Integer.parseInt(esitoImpegni.get_progr_accert_reiteraz());

			if(progrReit >=2 && (
					!esitoImpegni.get_portata().isEmpty() ||
					!esitoImpegni.get_portata_note().isEmpty() ||
					!esitoImpegni.get_gravita().isEmpty() ||
					!esitoImpegni.get_gravita_note().isEmpty() ||
					!esitoImpegni.get_durata().isEmpty() ||
					!esitoImpegni.get_durata_note().isEmpty() )) {
				warnings.add(Costanti.PROGR_REIT_MAGG2_AND_PORT_GRAV_DUR_VALORIZ);
			} 			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.PROGR_REIT_MAGG2_AND_PORT_GRAV_DUR_VALORIZ
					+ " - " + e.getMessage());
			e.printStackTrace();
		}
		
		// 4 - WARNING
		try {
			if(esitoImpegni.get_esclusione().equalsIgnoreCase("SI") && esitoImpegni.get_esclusioneNote().isEmpty()) {
				warnings.add(Costanti.ESCL_SI_NOTE_NO);
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.ESCL_SI_NOTE_NO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}
		
		// 5 - WARNING
		try {
			if(esitoImpegni.get_esclusione().equalsIgnoreCase("NO") && !esitoImpegni.get_esclusioneNote().isEmpty()) {
				warnings.add(Costanti.ESCL_NO_NOTE_SI);	
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.ESCL_NO_NOTE_SI
					+ " - " + e.getMessage());
			e.printStackTrace();
		}
		
		// 6 - WARNING
		try {
			if(esitoImpegni.get_esclusione().equalsIgnoreCase("SI") && (
					!esitoImpegni.get_portata().isEmpty() ||
					!esitoImpegni.get_portata_note().isEmpty() ||
					!esitoImpegni.get_gravita().isEmpty() ||
					!esitoImpegni.get_gravita_note().isEmpty() ||
					!esitoImpegni.get_durata().isEmpty() ||
					!esitoImpegni.get_durata_note().isEmpty() )) {
				warnings.add(Costanti.ESCL_SI_AND_PORT_GRAV_DUR_VALORIZ);
			} 
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.ESCL_SI_AND_PORT_GRAV_DUR_VALORIZ
					+ " - " + e.getMessage());
			e.printStackTrace();
		}
		
		
		// 7 - WARNING
		try {
			if(esitoImpegni.get_percRid().isEmpty()) {
				warnings.add(Costanti.PERC_RID_EMPTY);
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.PERC_RID_EMPTY
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 8 - WARNING
		try {
			if(esitoImpegni.get_approvazione().equalsIgnoreCase("NO") && !esitoImpegni.get_numero_decreto().isEmpty()) {
				warnings.add(Costanti.APPR_NO_NUM_DECRETO_SI);
			}			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.APPR_NO_NUM_DECRETO_SI
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 9 - WARNING
		try {
			if(esitoImpegni.get_approvazione().equalsIgnoreCase("NO") && esitoImpegni.get_data_decreto() != null) {
				warnings.add(Costanti.APPR_NO_DATA_DECRETO_SI);
			}			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.APPR_NO_DATA_DECRETO_SI
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 10 - WARNING
		try {
			if(esitoImpegni.get_note().isEmpty()) {
				warnings.add(Costanti.NOTE_VUOTE);				
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.NOTE_VUOTE
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 11 - WARNING
		try {
			if(esitoImpegni.get_inadempienza_grave().equalsIgnoreCase("SI") && esitoImpegni.get_reiterazione().equalsIgnoreCase("NO")) {
				warnings.add(Costanti.INADEM_SI_REIT_NO);
			}			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.INADEM_SI_REIT_NO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 12 - WARNING
		try {
			Integer portata = 0;
			Integer gravita = 0;
			Integer durata = 0;
			
			if(!esitoImpegni.get_portata().isEmpty())
				portata = Integer.parseInt(esitoImpegni.get_portata());
			if(!esitoImpegni.get_gravita().isEmpty())
				gravita = Integer.parseInt(esitoImpegni.get_gravita());
			if(!esitoImpegni.get_durata().isEmpty())
				durata = Integer.parseInt(esitoImpegni.get_durata());

			System.out.println(portata+" "+gravita+" "+durata);
			if(esitoImpegni.get_inadempienza_grave().equalsIgnoreCase("SI") && (
					portata != 5 || gravita != 5 || durata != 5)) {
				warnings.add(Costanti.INADEM_SI_PORT_GRAV_DUR_NO_5);
			} 

		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.INADEM_SI_PORT_GRAV_DUR_NO_5
					+ " - " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return warnings;
	}
}
