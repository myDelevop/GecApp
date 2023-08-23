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

import it.bz.prov.esiti.bobject.CampioneBO;
import it.bz.prov.esiti.bobject.ElencoEsitoCondizionalitaAttoBO;
import it.bz.prov.esiti.bobject.ElencoEsitoCondizionalitaBO;
import it.bz.prov.esiti.bobject.EsitoCondizionalitaAttoBO;
import it.bz.prov.esiti.bobject.EsitoCondizionalitaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.entity.AttoCondizionalita;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.Campione;
import it.bz.prov.esiti.entity.Controllo;
import it.bz.prov.esiti.entity.ControlloPk;
import it.bz.prov.esiti.entity.EsitoCondizionalita;
import it.bz.prov.esiti.entity.EsitoCondizionalitaAtto;
import it.bz.prov.esiti.entity.EsitoCondizionalitaAttoPk;
import it.bz.prov.esiti.entity.EsitoCondizionalitaPk;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.ICondizionalita;
import it.bz.prov.esiti.util.CheckData;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.ExcelReader;
import it.bz.prov.esiti.util.ExcelWriter;
import it.bz.prov.esiti.util.FileExcel;
import it.bz.prov.esiti.util.Utils;

/**
 * Bean per la gestione back-end delgli esiti di condizionalità
 * 
 * @author bpettazzoni
 *
 */

@Stateful
@SuppressWarnings("unchecked")
public class CondizionalitaBean implements ICondizionalita {
	
	private static String COL_CUAA = "CUAA";
	private static String COL_CAMPAGNA = "Campagna";
//	private static String COL_DATA_CAMPIONE = "Data Campione";
	private static String COL_DATA_CONTROLLO = "Data Controllo";
	private static String COL_ESITO_FINALE = "Esito Finale";
	private static String COL_PERC_RID_PSR = "Perc. Riduzione PSR";
	private static String COL_PERC_RID_DU = "Perc. Riduzione DU";
	private static String COL_STATO = "Stato";
	private static String COL_NOTE = "Note";
	private static String COL_NUMERO_DECRETO = "Numero Decreto";
	private static String COL_DATA_DECRETO = "Data Decreto";
	private static String COL_NOTE_DECRETO = "Note Decreto";
	
	private static String COL_COD_COND = "Codice Cond.";
	private static String COL_ATTO_COND = "Atto Cond.";
	private static String COL_RESP_CONTROLLO = "Responsabile Controllo";
	private static String COL_INFRAZIONE = "Infrazione";
	private static String COL_DATA_1_CONTR = "Data 1 Controllo";
	private static String COL_RICH_AZ_CORR = "Richiesta Azione Correttiva";
	private static String COL_AZ_CORR_ESEG = "Azione Correttiva Eseguita";
	private static String COL_RICH_IMP_RIPR = "Richiesto Impegno di Ripristino";
	private static String COL_IMP_RIPR_ESEG = "Impegno di Ripristino Eseguito";
	private static String COL_DATA_2_CONTR = "Data 2 Controllo";
	private static String COL_ESITO_2_CONTR = "Esito 2 Controllo";
	private static String COL_INADEMPIENZA = "Inadempienza";
	private static String COL_NEGLIGENZA = "Negligenza";
	private static String COL_INTENZIONALITA = "Intenzionalità";
	private static String COL_PROGR_ACC_INTENZ = "Progr. Accertamento Intenzionalità";
	private static String COL_REITERAZIONE = "Reiterazione";
	private static String COL_PROGR_ACC_REIT = "Progr. Accertamento Reiterazione";
	private static String COL_PORTATA = "Portata";
	private static String COL_PORTATA_NOTE = "Portata Note";
	private static String COL_GRAVITA = "Gravita";
	private static String COL_GRAVITA_NOTE = "Gravita Note";
	private static String COL_DURATA = "Durata";
	private static String COL_DURATA_NOTE = "Durata Note";
	private static String COL_PERC_RID = "Perc. Riduzione";
	private static String COL_AMMONIZIONE = "Ammonizione";
	private static String COL_TIPO_CONTROLLO = "Tipo Controllo";
	private static String COL_A1 = "A1";
	private static String COL_A2 = "A2";
	private static String COL_A3 = "A3";
	private static String COL_A4 = "A4";
	private static String COL_A5 = "A5";
	private static String COL_A6 = "A6";
	private static String COL_A7 = "A7";
	private static String COL_A8 = "A8";
	private static String COL_B9 = "B9";
	private static String COL_B10 = "B10";
	private static String COL_B11 = "B11";
	private static String COL_B12 = "B12";
	private static String COL_B13 = "B13";
	private static String COL_B14 = "B14";
	private static String COL_B15 = "B15";
	private static String COL_C16 = "C16";
	private static String COL_C17 = "C17";
	private static String COL_C18 = "C18";
	private static String COL_FER = "FER";
	private static String COL_FIT = "FIT";
	private static String COL_1_1 = "1_1";
	private static String COL_1_2 = "1_2";
	private static String COL_1_3 = "1_3";
	private static String COL_2_1 = "2_1";
	private static String COL_2_2 = "2_2";
	private static String COL_3_1 = "3_1";
	private static String COL_4_1 = "4_1";
	private static String COL_4_2 = "4_2";
	private static String COL_4_3 = "4_3";
	private static String COL_4_4 = "4_4";
	private static String COL_4_6 = "4_6";
	private static String COL_5_1 = "5_1";
	private static String COL_5_2 = "5_2";
	private static String COL_CGO1 = "CGO1";
	private static String COL_BCAA1 = "BCAA1";
	private static String COL_BCAA2 = "BCAA2";
	private static String COL_BCAA3 = "BCAA3";
	private static String COL_BCAA4 = "BCAA4";
	private static String COL_BCAA5 = "BCAA5";
	private static String COL_BCAA6 = "BCAA6";
	private static String COL_CGO2 = "CGO2";
	private static String COL_CGO3 = "CGO3";
	private static String COL_BCAA7 = "BCAA7";
	private static String COL_CGO4 = "CGO4";
	private static String COL_CGO5 = "CGO5";
	private static String COL_CGO6 = "CGO6";
	private static String COL_CGO7 = "CGO7";
	private static String COL_CGO8 = "CGO8";
	private static String COL_CGO9 = "CGO9";
	private static String COL_CGO10 = "CGO10";
	private static String COL_CGO11 = "CGO11";
	private static String COL_CGO12 = "CGO12";
	private static String COL_CGO13 = "CGO13";
	private static String COL_BCAA8 = "BCAA8";
	private static String COL_ATTO = "ATTO";
	private static String COL_INTENZIONALITA_NOTE ="Note Intenzionalità";
	private static String COL_SEGNALAZIONE = "Segnalazione";
	private static String COL_APPROVAZIONE = "Approvazione";
	
	private ElencoEsitoCondizionalitaAttoBO _elencoEsitoAtto = new ElencoEsitoCondizionalitaAttoBO();;
	private ElencoEsitoCondizionalitaBO _elencoEsito = new ElencoEsitoCondizionalitaBO();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	
	/***************************************************************************************
	 * 			GESTIONE ESITI PER ATTO
	 ***************************************************************************************/
	
	
	/**
	 * aggiunge un esito alla lista
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsitoAtto(EsitoCondizionalitaAttoBO esito)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsertAtto(esito, Costanti.ACTION_INSERT, Costanti.LAYOUT_COND_ATTO_ESITO);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			esito.set_userCreazione(Utils.getCurrentUser());
			esito.set_dataCreazione(Utils.todayDate());
			EsitoCondizionalitaAtto esitoEntity = esito.getEntity();
			_em.clear();
//			_em.getTransaction().begin();
			_em.persist(esitoEntity);
//			_em.getTransaction().commit();
			// inserimento nell'elenco
			_elencoEsitoAtto.addEsitoAtto(esito);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(CondizionalitaBean.class.getSimpleName() + " - aggiungiEsitoAtto: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * modifica un esito alla lista
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsitoAtto(EsitoCondizionalitaAttoBO esito)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsertAtto(esito, Costanti.ACTION_MODIFY, Costanti.LAYOUT_COND_ATTO_ESITO);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			EsitoCondizionalitaAttoPk pk = new EsitoCondizionalitaAttoPk();
			pk.set_campagna(esito.get_campagna());
			pk.set_cuaa(esito.get_cuaa());
			pk.set_atto_contr(esito.get_atto_contr());
			pk.set_cod_cond(esito.get_cod_cond());
			pk.set_tipoControllo(esito.get_tipoControllo());
			_em.clear();
			EsitoCondizionalitaAtto esitoFind = _em.find(EsitoCondizionalitaAtto.class, pk);
			// se la riga non é stata trovata su db
			if(esitoFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			esito.set_userModifica(Utils.getCurrentUser());
			esito.set_dataModifica(Utils.todayDate());
			esito.setEntity(esitoFind);
//			_em.getTransaction().commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(CondizionalitaBean.class.getSimpleName() + " - modificaEsitoAtto: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * cancella un esito dalla lista
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsitoAtto(EsitoCondizionalitaAttoBO esito)
	{
		OperationResultBO result = new OperationResultBO();
		try {
			_em.clear();
			EsitoCondizionalitaAttoPk pk = new EsitoCondizionalitaAttoPk();
			pk.set_cuaa(esito.get_cuaa());
			pk.set_campagna(esito.get_campagna());
			pk.set_cod_cond(esito.get_cod_cond());
			pk.set_atto_contr(esito.get_atto_contr());
			pk.set_tipoControllo(esito.get_tipoControllo());

			EsitoCondizionalitaAtto esitoAttoFind = _em.find(EsitoCondizionalitaAtto.class, pk);
			if(esitoAttoFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			_em.remove(esitoAttoFind);
//			_em.getTransaction().commit();
			// cancellazione dall'elenco
			_elencoEsitoAtto.remove(esito);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(CondizionalitaBean.class.getSimpleName() + " - cancellaEsitoAtto: " + e.getMessage());
			return result;
		}	
	}
	
	/**
     * Carica i dati in tabella
     */
    public void loadDataAtto()
    {
    	_em.clear();
    	Query q = _em.createNamedQuery("EsitoCondizionalitaAtto.selectAll");
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoCondizionalitaAtto> fornitura = q.getResultList();
		_elencoEsitoAtto.set_listCondEsitoAtto(fornitura);
    }

    /**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFileAtto(OutputStream stream)
	{	
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = getListaIntestazioniAtto();
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (EsitoCondizionalitaAttoBO esito : _elencoEsitoAtto.get_listCondEsitoAtto()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, esito.get_cuaa());
			riga.put(1, esito.get_campagna());
			riga.put(2, esito.get_cod_cond());
			riga.put(3, esito.get_atto_contr());
			riga.put(4, esito.get_resp_controllo());
			riga.put(5, esito.get_infrazione());
			riga.put(6, esito.get_data_1_contrStr());
			riga.put(7, esito.get_rich_az_corr());
			riga.put(8, esito.get_az_corr_eseguita());
			riga.put(9, esito.get_rich_imp_ripr());
			riga.put(10, esito.get_imp_ripr_eseguito());
			riga.put(11, esito.get_data_2_contrStr());
			riga.put(12, esito.get_esito_2_contr());
			riga.put(13, esito.get_inadempienza());
			riga.put(14, esito.get_negligenza());
			riga.put(15, esito.get_intenzionalita());
			riga.put(16, esito.get_progr_accert_intenz());
			riga.put(17, esito.get_intenzionalita_note());
			riga.put(18, esito.get_reiterazione());
			riga.put(19, esito.get_progr_Accert_reit());
			riga.put(20, esito.get_portata());
			riga.put(21, esito.get_gravita());
			riga.put(22, esito.get_durata());
			riga.put(23, esito.get_perc_rid());
			riga.put(24, esito.get_ammonizione());
			riga.put(25, esito.get_segnalazione());
			riga.put(26, esito.get_approvazione());
			riga.put(27, esito.get_note());
			riga.put(28, esito.get_tipoControllo());
			riga.put(29, esito.get_portata_note());
			riga.put(30, esito.get_gravita_note());
			riga.put(31, esito.get_durata_note());
			righe.add(riga);
		}
		
		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}
    
	
	/***************************************************************************************
	 * 			GESTIONE ESITI PER AZIENDA
	 ***************************************************************************************/
	
	
	/**
	 * aggiunge un esito alla lista
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsito(EsitoCondizionalitaBO esito)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(esito, Costanti.ACTION_INSERT);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			esito.set_userCreazione(Utils.getCurrentUser());
			esito.set_dataCreazione(Utils.todayDate());
			EsitoCondizionalita esitoEntity = esito.getEntity();
			_em.clear();
//			_em.getTransaction().begin();
			_em.persist(esitoEntity);
//			_em.getTransaction().commit();
			// aggiunta nell'elenco
			_elencoEsito.addEsito(esito);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			Utils.getLog().error(CondizionalitaBean.class.getSimpleName()  + " - aggiungiEsito errore: " + e.getMessage());
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			return result;
		}
	}
	
	/**
	 * modifica un esito alla lista
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsito(EsitoCondizionalitaBO esito)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(esito, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			EsitoCondizionalitaPk pk = new EsitoCondizionalitaPk();
			pk.set_campagna(esito.get_campagna());
			pk.set_cuaa(esito.get_cuaa());
			_em.clear();
			// recupero l'oggetto su db
			EsitoCondizionalita esitoFind = _em.find(EsitoCondizionalita.class, pk);
			// se la riga non é stata trovata su db
			if(esitoFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			// imposto dati di modifica
			esito.set_userModifica(Utils.getCurrentUser());
			esito.set_dataModifica(Utils.todayDate());
			esito.setEntity(esitoFind);			
//			_em.getTransaction().commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(CondizionalitaBean.class.getSimpleName() + " - modificaEsito: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * cancella un esito dalla lista
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoCondizionalitaBO esito)
	{	
		OperationResultBO result = new OperationResultBO();
		try {
			_em.clear();
			EsitoCondizionalitaPk pk = new EsitoCondizionalitaPk();
			pk.set_cuaa(esito.get_cuaa());
			pk.set_campagna(esito.get_campagna());
			EsitoCondizionalita esitoFind = _em.find(EsitoCondizionalita.class, pk);
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
			_elencoEsito.remove(esito);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(CondizionalitaBean.class.getSimpleName() + " - cancellaEsito: " + e.getMessage());
			return result;
		}	
	}
	

	/***************************************************************************************
	 * 			TRASVERSALI
	 ***************************************************************************************/
	

	/**
     * Carica i dati in tabella
     */
    public void loadData()
    {
    	Query q = _em.createNamedQuery("EsitoCondizionalita.selectAll");
    	_em.clear();
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoCondizionalita> fornitura = q.getResultList();
		_elencoEsito.set_listCondEsito(fornitura);
		// se ci sono delle date controllo mancanti si vanno a leggere dalla tabella del controllo
		/* GIMS 24/09/2019 - Problema con l'inserimento della data di controllo in fase di modifica. Non si capisce se si trova a DB o meno l'informazione
		for (EsitoCondizionalitaBO esito : _elencoEsito.get_listCondEsito()) {
			if(esito.get_dataControlloStr().equals(""))
				esito.set_dataControllo(Utils.getDate(getDataControllo(esito.get_cuaa(), esito.get_campagna())));
		}
		*/
    }

    /**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFileAzienda(OutputStream stream)
	{		
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = getListaIntestazioniAzienda();
		
//		Query q = _em.createNamedQuery("EsitoCondizionalita.selectAll");
//    	_em.clear();
//		q.setMaxResults(Costanti.QUERY_TOP_K);
//		List<EsitoCondizionalita> fornitura = q.getResultList();
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
//		for (EsitoCondizionalita esito : fornitura) {
		for (EsitoCondizionalitaBO esito : _elencoEsito.get_listCondEsito()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, esito.get_cuaa());
			riga.put(1, esito.get_campagna());
//			riga.put(2, esito.get_dataCampione());
//			riga.put(3, esito.get_dataControllo());
//			riga.put(2, esito.get_dataCampioneStr());
			riga.put(2, esito.get_dataControlloStr());
			riga.put(3, esito.get_esitoFinale());
			riga.put(4, esito.get_ridPercPSR());
			riga.put(5, esito.get_ridPercDU());
			riga.put(6, esito.get_stato());
			riga.put(7, esito.get_note());
			riga.put(8, esito.get_numero_decreto());
			riga.put(9, esito.get_data_decretoStr());
			riga.put(10, esito.get_note_decreto());
			righe.add(riga);
		}
		
		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}
	
	/**
	 * esporta il layout del file excel da importare
	 * @param stream é lo stream su cui scrivere il file
	 * @param tipoLayout é il tipo di layout da esportare
	 */
	public void exportLayout(OutputStream stream, String tipoLayout)
	{
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = null;
		
		// in base al layout sceto genero l'instestazione
		if(tipoLayout.equals(Costanti.LAYOUT_COND_ATTO_ESITO)) intestazione = getListaIntestazioniAtto();
		else if(tipoLayout.equals(Costanti.LAYOUT_COND_ATTO_CONTR)) intestazione = getListaIntestazioniMatrAttiContr();	
		else if(tipoLayout.equals(Costanti.LAYOUT_COND_ATTO_CONTR_VET)) intestazione = getListaIntestazioniAttiContrVet();
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
			
		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}
	
	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList()
	{
		_elencoEsito = new ElencoEsitoCondizionalitaBO();
		_elencoEsitoAtto = new ElencoEsitoCondizionalitaAttoBO();
	}
	
	/**
	 * restituisce la data del controllo, se presente nella tabella dei controlli
	 * @param cuaa
	 * @param campagna
	 * @return String é la data ottenuta
	 */
	public String getDataControllo(String cuaa, String campagna) {
		_em.clear();
		ControlloPk controlloPK = new ControlloPk();
		controlloPK.set_campagna(campagna);
		controlloPK.set_cuaa(cuaa);
		Controllo controlloFind = _em.find(Controllo.class, controlloPK);
		if(controlloFind != null){
			String data = controlloFind.get_data_inizio_controllo();
			if(data == null) return "";
			else return data;
		}
		else 
			return "";
	}
	
    
	/***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
	

	public void set_elencoEsitoAtto(ElencoEsitoCondizionalitaAttoBO _elencoEsitoAtto) {
		this._elencoEsitoAtto = _elencoEsitoAtto;
	}

	public ElencoEsitoCondizionalitaAttoBO get_elencoEsitoAtto() {
		return _elencoEsitoAtto;
	}

	public void set_elencoEsito(ElencoEsitoCondizionalitaBO _elencoEsito) {
		this._elencoEsito = _elencoEsito;
	}

	public ElencoEsitoCondizionalitaBO get_elencoEsito() {
		return _elencoEsito;
	}
	
	
	/***************************************************************************************
	 * 			UTILITY
	 ***************************************************************************************/
	
	
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
		//lista.add(0, "");
		return lista;
	}
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList)
    {
		_em.clear();
		// per gli esiti di condizionalita per azienda
    	Query q = _em.createNamedQuery("EsitoCondizionalita.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);		
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoCondizionalita> fornitura = q.getResultList();
		_elencoEsito.set_listCondEsito(fornitura);
		
		// per gli esiti di condizionalita per atto
    	q = _em.createNamedQuery("EsitoCondizionalitaAtto.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);		
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoCondizionalitaAtto> lista = q.getResultList();
		_elencoEsitoAtto.set_listCondEsitoAtto(lista);
		
		// se ci sono delle date controllo mancanti si vanno a leggere dalla tabella del controllo
		/* GIMS 24/09/2019 - Vedi commento su loadData()
		for (EsitoCondizionalitaBO esito : _elencoEsito.get_listCondEsito()) {
			if(esito.get_dataControlloStr().equals(""))
				esito.set_dataControllo(Utils.getDate(getDataControllo(esito.get_cuaa(), esito.get_campagna())));
		}
		*/
    }
	
	/**
	 * restituisce la lista dei valori distinti di atti di condizionalità per campagna
	 * @return List<String>
	 */
	public List<String> getListAttoContrForCampagna(String campagna){
		_em.clear();
		int anno_campagna = Integer.parseInt(campagna);
		Query q = _em.createNamedQuery("AttoCondizionalita.selectAttoNormaForCampagna");
		q.setParameter("campagna", anno_campagna);
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
	 * restituisce la lista dei valori distinti di atti di condizionalità per campagna
	 * @return List<String>
	 */
	public List<String> getListCodCondForCampagna(String campagna){
		_em.clear();
		int anno_campagna = Integer.parseInt(campagna);
		Query q = _em.createNamedQuery("AttoCondizionalita.selectCodCondForCampagna");
		q.setParameter("campagna", anno_campagna);
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	/**
	 * restituisce la lista dei valori distinti di codici di condizionalità
	 * @return List<String>
	 */
	public List<String> getListaCodCond()
	{
		_em.clear();
		Query q = _em.createNamedQuery("AttoCondizionalita.selectDistinctCodCond");
		List<String> fornitura = q.getResultList();
		fornitura.add(0, "");
		return fornitura;
	}
	
	/**
	 * restituisce la lista dei valori distinti di atti di condizionalità per 
	 * il codice di condizionalità selezionato
	 * @param codCond
	 * @return List<String>
	 */
	public List<String> getListaAttoCond(String codCond)
	{
		_em.clear();
		Query q = _em.createNamedQuery("AttoCondizionalita.selectDistinctAttoNormaForCodice");
		q.setParameter("codice", codCond);
		List<String> fornitura = q.getResultList();
		fornitura.add(0, "");
		return fornitura;
	}
	
	/**
	 * restituisce la lista dei valori distinti di atti di condizionalità
	 * @return List<String>
	 */
	public List<String> getListaAttoCond()
	{
		_em.clear();
		Query q = _em.createNamedQuery("AttoCondizionalita.selectDistinctAttoNorma");
		List<String> fornitura = q.getResultList();
		fornitura.add(0, "");
		return fornitura;
	}
	
	/**
	 * restituisce il codice di condizionalità dell'atto indicato
	 * @param attoCond
	 * @return String
	 */
	public String getCodiceCond(String attoCond){
		_em.clear();
		Query q = _em.createNamedQuery("AttoCondizionalita.selectCodiceFromAtto");
		q.setParameter("atto", attoCond);
		List<String> fornitura = q.getResultList();
		if(fornitura.size() == 0) return "";
		else return fornitura.get(0);
	}
	

	/**
	 * permette l'inserimento dei dati da file excel
	 * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @param tabella é la tabella per cui viene fatto l'inserimento
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
	 */
    public boolean insertFromFile(String pathFile, List<String> listLog, int tabella)
    {
    	//1. estraggo il file excel
    	FileExcel fileExcel = ExcelReader.readFile(pathFile);
    	if(fileExcel == null)
    	{
    		listLog.add("ERROR: ci sono stati problemi nella lettura del file temporaneo");
    		return false;
    	}
    	
    	boolean complete_result = false;
    	if(tabella == Costanti.TABELLA_ESITO_COND_AZIENDA) 
    		complete_result = importEsitoCondAzienda(pathFile, listLog, fileExcel);
    	else if(tabella == Costanti.TABELLA_ESITO_COND_ATTO)
    		complete_result = importEsitoCondAtto(pathFile, listLog, fileExcel);     	
    	
    	if(complete_result) listLog.add("INFO: upload dei dati terminato con successo");
    	else listLog.add("ERROR: l'operazione di inserimento dati non é terminata correttamente.");
    	return complete_result;
    }
    
    /**
     * importazione dei dati per il file degli esiti per aziende
     * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @param fileExcel é il file che contiene i dati
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
     */
    private boolean importEsitoCondAzienda(String pathFile, List<String> listLog, FileExcel fileExcel)
    {
    	//2. verifico la presenza di tutte le intestazioni corrette
    	boolean check = true;
    	LinkedHashMap<Integer, String> listaColonna = new LinkedHashMap<Integer, String>();
		listaColonna = CondizionalitaBean.getListaIntestazioniAzienda();
		// controllo delle singole colonne
		for (String colonna : listaColonna.values()) {
			boolean res = fileExcel.hasColonna(colonna);
			if(!res)
			{
				listLog.add("ERROR: La colonna " + colonna + " non é presente nel file");
				check = false;
			}
		}   	
    	if(!check) return false;
    	else listLog.add("INFO: I controlli di coerenza sulle colonne del file hanno dato esito positivo");
    	
    	//3. creazione della lista di esito
    	ArrayList<EsitoCondizionalitaBO> listaEsitoAzi = new ArrayList<EsitoCondizionalitaBO>();
		boolean res = getListFromExcel(fileExcel, listaEsitoAzi, listLog);
		if(listaEsitoAzi.size() ==0)
    	{
			listLog.add("ERROR: il file non contiene righe di dato");
			return false;
		}
		if(!res)
    	{
			listLog.add("ERROR: i dati non sono stati caricati causa errore nel formato");
			return false;
		}
//    		//3.a verifica del numero di righe
//        	if(listaEsitoAzi.size()>Costanti.IMPORT_NUM_ROW_MAX)
//        	{
//    			listLog.add("ERROR: il file può contenere al massimo " + Costanti.IMPORT_NUM_ROW_MAX + "righe da importare");
//    			return false;
//        	}

    	//4. verifica correttezza valori    	
    	boolean complete_result = true;
		for (EsitoCondizionalitaBO esito : listaEsitoAzi) {
    		// utilizzo ACTION_MODIFY perchè così non fa la verifica sulla pk esistente
			OperationResultBO result = checkPreInsert(esito, Costanti.ACTION_MODIFY);
			// se il check ha trovato degli errori su questa riga
			if(!result.get_result()) 
			{
				complete_result = false;
				listLog.add("ERROR: CUAA:" + esito.get_cuaa() + " - errore: " + result.get_message());
			}			
		}
		
		//5. verifiche non fatte su insert da maschera
		OperationResultBO result = checkForImport(listaEsitoAzi);
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
    	complete_result = inserimentoMassivo(listaEsitoAzi, listLog);
    	return complete_result;
    }
    
    /**
     * importazione dei dati per il file degli esiti per aziende
     * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @param fileExcel é il file che contiene i dati
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
     */
    private boolean importEsitoCondAtto(String pathFile, List<String> listLog, FileExcel fileExcel)
    {
    	//2. verifico la presenza di tutte le intestazioni corrette
    	boolean check_esito = true;
    	boolean check_matr = false;
    	boolean check_vet = false;
    	String tipo_file_atto = "";
    	LinkedHashMap<Integer, String> listaColonna = new LinkedHashMap<Integer, String>();
    	
		// ci sono 3 possibili file che vengono importati: identificazione del file giusto
		// A) file con gli esiti 
		listaColonna = CondizionalitaBean.getListaIntestazioniAtto();
		for (String colonna : listaColonna.values()) {
			boolean res = fileExcel.hasColonna(colonna);
			if(!res) { check_esito = false; break; }
		}
		if(check_esito) tipo_file_atto = Costanti.LAYOUT_COND_ATTO_ESITO;
		
		// B) file con matrice di ammissibilità reale (controlli fatti) 
		if(!check_esito) {
			check_matr = true;
			listaColonna = CondizionalitaBean.getListaIntestazioniMatrAttiContr();
    		for (String colonna : listaColonna.values()) {
    			boolean result = fileExcel.hasColonna(colonna);
    			if(!result) { check_matr = false; break; }
    		}
		}
		if(check_matr) tipo_file_atto = Costanti.LAYOUT_COND_ATTO_CONTR;
		
		// C) file con i controlli fatti dai vet
		if(!check_esito && !check_matr) {
			check_vet = true;
			listaColonna = CondizionalitaBean.getListaIntestazioniAttiContrVet();
    		for (String colonna : listaColonna.values()) {
    			boolean result = fileExcel.hasColonna(colonna);
    			if(!result) { check_vet = false; break; }
    		}
		}
		if(check_vet) tipo_file_atto = Costanti.LAYOUT_COND_ATTO_CONTR_VET;
		
		if(!check_esito && !check_matr && !check_vet) {
			listLog.add("ERROR: Il file non corrisponde a nessun layout previsto");
			return false;
		}
    	
    	if(!check_esito && !check_matr && !check_vet) return false;
    	else listLog.add("INFO: I controlli di coerenza sulle colonne del file hanno dato esito positivo");
    	
    	//3. creazione della lista di esito
    	ArrayList<EsitoCondizionalitaAttoBO> listaEsitoAtto = new ArrayList<EsitoCondizionalitaAttoBO>();
    	boolean res = false;
    	if(tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_ESITO))
    		res = getListAttoFromExcel(fileExcel, listaEsitoAtto, listLog);
    	else if(tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_CONTR))
    		res = getListAttoFromExcelMatrice(fileExcel, listaEsitoAtto, listLog);
    	else if(tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_CONTR_VET))
    		res = getListAttoFromExcelVet(fileExcel, listaEsitoAtto, listLog);
		if(listaEsitoAtto.size() ==0)
    	{
			listLog.add("ERROR: il file non contiene righe di dato");
			return false;
		}
		if(!res)
    	{
			listLog.add("ERROR: i dati non sono stati caricati causa errore nel formato");
			return false;
		}
//    		//3.a verifica del numero di righe
//        	if(listaEsitoAtto.size()>Costanti.IMPORT_NUM_ROW_MAX)
//        	{
//    			listLog.add("ERROR: il file può contenere al massimo " + Costanti.IMPORT_NUM_ROW_MAX + "righe da importare");
//    			return false;
//        	}
    	
    	//4. verifica correttezza valori 
    	boolean complete_result = true;
    	
		for (EsitoCondizionalitaAttoBO esito : listaEsitoAtto) {
    		// utilizzo ACTION_MODIFY perchè così non fa la verifica sulla pk esistente
			OperationResultBO result = checkPreInsertAtto(esito, Costanti.ACTION_MODIFY, tipo_file_atto);
			// se il check ha trovato degli errori su questa riga
			if(!result.get_result()) 
			{
				complete_result = false;
				listLog.add("ERROR: CUAA:" + esito.get_cuaa() + " - errore: " + result.get_message());
				return false;
			}			
		}
		
		//5. verifiche non fatte su insert da maschera
		if(tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_ESITO) || tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_CONTR_VET))
		{
			OperationResultBO result = null;
			if(tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_ESITO))
				result = checkForImportAtto(listaEsitoAtto);
			else if(tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_CONTR_VET))
				result = checkForImportAttoVet(listaEsitoAtto);
			
			if(!result.get_result()) 
			{
				complete_result = false;
				listLog.add(result.get_message());
			}				
		}
		if(complete_result) listLog.add("INFO: Verifica coerenza valori delle righe terminata con successo. Numero righe contenute nel file: "+ listaEsitoAtto.size());
    	else 
    	{
    		listLog.add("ERROR: Terminata l'operazione. I dati non sono stati importati.");
    		return false; // termino l'esecuzione del metodo
    	}
		
//		
//		if(tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_ESITO))
//		{
//			OperationResultBO result = checkForImportAtto(listaEsitoAtto);
//			if(!result.get_result()) 
//			{
//				complete_result = false;
//				listLog.add(result.get_message());
//			}	
//			if(complete_result) listLog.add("INFO: Verifica coerenza valori delle righe terminata con successo. Numero righe contenute nel file: "+ listaEsitoAtto.size());
//	    	else 
//	    	{
//	    		listLog.add("ERROR: Terminata l'operazione. I dati non sono stati importati.");
//	    		return false; // termino l'esecuzione del metodo
//	    	}
//		}
    	    	
    	// 6. inserimento massivo
		if(tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_ESITO))
			complete_result = inserimentoMassivoAtto(listaEsitoAtto, listLog);
		else if(tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_CONTR))
			complete_result = inserimentoMassivoAttoMatrice(listaEsitoAtto, listLog);
		else if(tipo_file_atto.equals(Costanti.LAYOUT_COND_ATTO_CONTR_VET))
			complete_result = inserimentoMassivoAttoControlliVet(listaEsitoAtto, listLog);
		
    	return complete_result;
    }
	
	/***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update)
     * per la gestione della condizionalità per atto
     * @param esito
     * @param action
     * @param tipoImport é il tipo di file da importare
     * @return OperationResultBO
     */
    private OperationResultBO checkPreInsertAtto(EsitoCondizionalitaAttoBO esito, String action, String tipoImport)
    {
    	OperationResultBO result = new OperationResultBO();
    	    	
    	//1. CUAA valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA);
			return result;
		}
    	
    	//2. CUAA con lunghezza compresa tra 11 e 16 caratteri
    	if(!CheckData.checkCuaaLength(esito.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_LENGTH);
			return result;
		}
    	
    	// 3. cuaa deve essere presente in anagrafica
		_em.clear();
		Azienda aziendaFind = _em.find(Azienda.class, esito.get_cuaa());
		if(aziendaFind == null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_MANCANTE);
			return result;
		}
		
		//4. campagna valorizzata (non nulla e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_campagna()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMPAGNA);
			return result;
		}
    	
//    	//5. codice condizionalità valorizzato (non nullo e non uguale a spazio)
//    	if(!CheckData.checkString(esito.get_cod_cond()))
//    	{
//			result.set_result(false);
//			result.set_message(Costanti.MSG_CHECK_COD_COND_NULLO);
//			return result;
//		}
    	
    	//6. atto condizionalità valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_atto_contr()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_ATTO_COND_NULLO);
			return result;
		}
    	
    	//7. responsabile controllo non nullo
    	if(!CheckData.checkString(esito.get_resp_controllo()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_RESP_CONTROLLO_NULLO);
			return result;
		}
    	
    	//8. data primo controllo non nulla
    	if(!CheckData.checkString(esito.get_data_1_contrStr()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_DATA_1_CONTROLLO_NULLO);
			return result;
		}
    	
    	//9. infrazione non nulla
    	if(!CheckData.checkString(esito.get_infrazione()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_INFRAZIONE_NULLO);
			return result;
		}
    	
    	//10. cuaa, campagna, cod. cond. e atto, tipo controllo non devono essere già presenti in tabella (é la pk)
		//(da fare solo per insert, no per modify)
		if(action.equals(Costanti.ACTION_INSERT))
		{
			EsitoCondizionalitaAttoPk pk = new EsitoCondizionalitaAttoPk();
			pk.set_cuaa(esito.get_cuaa());
			pk.set_campagna(esito.get_campagna());
			pk.set_cod_cond(esito.get_cod_cond());
			pk.set_atto_contr(esito.get_atto_contr());
			pk.set_tipoControllo(esito.get_tipoControllo());
			
			_em.clear();
			EsitoCondizionalitaAtto esitoFind = _em.find(EsitoCondizionalitaAtto.class, pk);
			if(esitoFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_CUAA_CAMPAGNA_COD_COND_ATTO_PRESENTI);
				return result;
			}
		}
		
		//11. se esiste la data del secondo controllo, verificare che sia successiva al primo
		if(esito.get_data_2_contr() != null)
			if(!CheckData.checkOrderDate(esito.get_data_1_contr(), esito.get_data_2_contr()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_DATA_2_CONTROLLO_ERRATA);
				return result;
			}
		
//		//12. coerenza tra codice e atto di condizionalità
//		if(tipoImport.equals(Costanti.LAYOUT_COND_ATTO_ESITO))
//		{
//			// eseguo questo controllo solo quando sto importando il file con gli esiti negativi per atto
//			_em.clear();
//			Query q = _em.createNamedQuery("AttoCondizionalita.findAttoCodice");
//			q.setParameter("codice", esito.get_cod_cond());
//			q.setParameter("atto", esito.get_atto_contr());
//			List<String> fornitura = q.getResultList();
//			if(fornitura == null || fornitura.size() ==0)
//			{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_ATTO_COND);
//				return result;
//			}
//		}		
		
		//13. la percentuale di riduzione deve essere un valore numerico corretto
		if(!esito.get_perc_rid().equals(""))
			if(!CheckData.checkFloat(esito.get_perc_rid()))
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
				return result;
			}
		
		//14. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(esito.get_note()))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_NOTE_TOO_LONG);
			return result;
		}
		
		//15. se l'atto é FER o FIT verificare che il cuaa per la campagna inserita abbia presentato 214
		// regola per il vecchio PSR, non vale più
		if(esito.get_atto_contr().equals(COL_FER) || esito.get_atto_contr().equals(COL_FIT))
		{
			_em.clear();
			Query q = _em.createNamedQuery("Domanda.has214");
			q.setParameter("campagna", esito.get_campagna());
			q.setParameter("cuaa", esito.get_cuaa());
			List<String> fornitura = q.getResultList();
			// se non ci sono occorrenze significa che il cuaa non ha 214
			if(fornitura.size() == 0) {
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_PRESENZA_214);
				return result;
			}
		}
		
		//16. tipo controllo popolato
		if(esito.get_tipoControllo().equals(""))
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_COND_TIPO_CONTROLLO);
			return result;
		}
		
    	//17. le note devono avere dimensione massima di 500 caratteri
		if(!CheckData.checkNoteSize(esito.get_intenzionalita_note()))
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
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update)
     * per la gestione della condizionalità per azienda
     * @param esito
     * @param action
     * @return OperationResultBO
     */
    private OperationResultBO checkPreInsert(EsitoCondizionalitaBO esito, String action)
    {
    	
    	OperationResultBO result = new OperationResultBO();
    	    	
    	//1. CUAA valorizzato (non nullo e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA);
			return result;
		}
    	
    	//2. CUAA con lunghezza compresa tra 11 e 16 caratteri
    	if(!CheckData.checkCuaaLength(esito.get_cuaa()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_LENGTH);
			return result;
		}
    	
    	// 3. cuaa deve essere presente in anagrafica
		_em.clear();
		Azienda aziendaFind = _em.find(Azienda.class, esito.get_cuaa());
		if(aziendaFind == null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CUAA_MANCANTE);
			return result;
		}
		
		//4. campagna valorizzata (non nulla e non uguale a spazio)
    	if(!CheckData.checkString(esito.get_campagna()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMPAGNA);
			return result;
		}
    	
    	//5. percentuale di riduzione per du e psr deve essere un valore numerico se non nullo
    	if(!esito.get_ridPercDU().equals(""))
    		if(!CheckData.checkFloat(esito.get_ridPercDU()))
    		{
    			result.set_result(false);
    			result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
    			return result;
    		}
    	if(!esito.get_ridPercPSR().equals(""))
    		if(!CheckData.checkFloat(esito.get_ridPercPSR()))
    		{
    			result.set_result(false);
    			result.set_message(Costanti.MSG_CHECK_PER_RID_NUMERIC_VALUE);
    			return result;
    		}
    	
    	// 7. cuaa e campagna non devono essere già presenti in tabella (é la pk) 
		//(da fare solo per insert, no per modify)
		if(action.equals(Costanti.ACTION_INSERT))
		{
			EsitoCondizionalitaPk pk = new EsitoCondizionalitaPk();
			pk.set_cuaa(esito.get_cuaa());
			pk.set_campagna(esito.get_campagna());
			_em.clear();
			EsitoCondizionalita esitoFind = _em.find(EsitoCondizionalita.class, pk);
			if(esitoFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_CUAA_CAMPAGNA_PRESENTI);
				return result;
			}
		}
		
		//8. cuaa e campagna devono essere presenti nel campione
//		_em.clear();
//		Query q = _em.createNamedQuery("Campione.selectCampioneForCuaaCampagna");
//		q.setParameter("cuaa", esito.get_cuaa());
//		q.setParameter("campagna", esito.get_campagna());
//		List<String> fornitura = q.getResultList();
//		if(fornitura == null || fornitura.size()==0)
//		{
//			result.set_result(false);
//			result.set_message(Costanti.MSG_CHECK_CUAA_NO_CAMPIONE);
//			return result;
//		}
		
		//9. se data controllo e data campione sono valorizzate, la data del campione 
		//   deve essere precedente alla data del controllo
//		if(esito.get_dataCampione() != null && esito.get_dataControllo() != null)
//			if(!CheckData.checkOrderDate(esito.get_dataCampione(), esito.get_dataControllo()))
//			{
//				result.set_result(false);
//				result.set_message(Costanti.MSG_CHECK_DATA_CONTROLLO);
//				return result;
//			}
		
		//10. le note devono avere dimensione massima di 500 caratteri
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
     * prepara la lista di tutte le intestazioni delle colonne presenti nel file
     * @return LinkedHashMap<Integer, String>
     */
    private static LinkedHashMap<Integer, String> getListaIntestazioniAzienda()
    {
    	LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
    	intestazione.put(0, COL_CUAA);
		intestazione.put(1, COL_CAMPAGNA);
		intestazione.put(2, COL_DATA_CONTROLLO);
		intestazione.put(3, COL_ESITO_FINALE);
		intestazione.put(4, COL_PERC_RID_PSR);
		intestazione.put(5, COL_PERC_RID_DU);
		intestazione.put(6, COL_STATO);
		intestazione.put(7, COL_NOTE);
		intestazione.put(8, COL_NUMERO_DECRETO);
		intestazione.put(9, COL_DATA_DECRETO);
		intestazione.put(10, COL_NOTE_DECRETO);
    	return intestazione;
    }
    
    
    /**
     * prepara la lista di tutte le intestazioni delle colonne presenti nel file
     * @return LinkedHashMap<Integer, String>
     */
    private static LinkedHashMap<Integer, String> getListaIntestazioniAtto()
    {
    	LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
    	intestazione.put(0, COL_CUAA);
		intestazione.put(1, COL_CAMPAGNA);
		intestazione.put(2, COL_COD_COND);
		intestazione.put(3, COL_ATTO_COND);
		intestazione.put(4, COL_RESP_CONTROLLO);
		intestazione.put(5, COL_INFRAZIONE);
		intestazione.put(6, COL_DATA_1_CONTR);
		intestazione.put(7, COL_RICH_AZ_CORR);
		intestazione.put(8, COL_AZ_CORR_ESEG);
		intestazione.put(9, COL_RICH_IMP_RIPR);
		intestazione.put(10, COL_IMP_RIPR_ESEG);
		intestazione.put(11, COL_DATA_2_CONTR);
		intestazione.put(12, COL_ESITO_2_CONTR);
		intestazione.put(13, COL_INADEMPIENZA);
		intestazione.put(14, COL_NEGLIGENZA);
		intestazione.put(15, COL_INTENZIONALITA);
		intestazione.put(16, COL_PROGR_ACC_INTENZ);
		intestazione.put(17, COL_INTENZIONALITA_NOTE);
		intestazione.put(18, COL_REITERAZIONE);
		intestazione.put(19, COL_PROGR_ACC_REIT);
		intestazione.put(20, COL_PORTATA);
		intestazione.put(21, COL_GRAVITA);
		intestazione.put(22, COL_DURATA);
		intestazione.put(23, COL_PERC_RID);
		intestazione.put(24, COL_AMMONIZIONE);
		intestazione.put(25, COL_SEGNALAZIONE);
		intestazione.put(26, COL_APPROVAZIONE);
		intestazione.put(27, COL_NOTE);
		intestazione.put(28, COL_TIPO_CONTROLLO);
		intestazione.put(29, COL_PORTATA_NOTE);	
		intestazione.put(30, COL_GRAVITA_NOTE);	
		intestazione.put(31, COL_DURATA_NOTE);	
    	return intestazione;
    }
    
    /**
     * prepara la lista di tutte le intestazioni delle colonne presenti nel file
     * @return LinkedHashMap<Integer, String>
     */
    private static LinkedHashMap<Integer, String> getListaIntestazioniMatrAttiContr()
    {
    	LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
    	intestazione.put(0, COL_CUAA);
		intestazione.put(1, COL_CAMPAGNA);
		intestazione.put(2, COL_DATA_1_CONTR);
		intestazione.put(3, COL_1_1);
		intestazione.put(4, COL_1_2);
		intestazione.put(5, COL_1_3);
		intestazione.put(6, COL_2_1);
		intestazione.put(7, COL_2_2);
		intestazione.put(8, COL_3_1);
		intestazione.put(9, COL_4_1);
		intestazione.put(10, COL_4_2);
		intestazione.put(11, COL_4_3);
		intestazione.put(12, COL_4_4);
		intestazione.put(13, COL_4_6);
		intestazione.put(14, COL_5_1);
		intestazione.put(15, COL_5_2);
		intestazione.put(16, COL_A1);
		intestazione.put(17, COL_A2);
		intestazione.put(18, COL_A3);
		intestazione.put(19, COL_A4);
		intestazione.put(20, COL_A5);
		intestazione.put(21, COL_A6);
		intestazione.put(22, COL_A7);
		intestazione.put(23, COL_A8);
		intestazione.put(24, COL_B9);
		intestazione.put(25, COL_B10);
		intestazione.put(26, COL_B11);
		intestazione.put(27, COL_B12);	
		intestazione.put(28, COL_B13);	
		intestazione.put(29, COL_B14);
		intestazione.put(30, COL_B15);
		intestazione.put(31, COL_C16);
		intestazione.put(32, COL_C17);
		intestazione.put(33, COL_C18);
		intestazione.put(34, COL_FER);
		intestazione.put(35, COL_FIT);
		intestazione.put(36, COL_CGO1);
		intestazione.put(37, COL_BCAA1);
		intestazione.put(38, COL_BCAA2);
		intestazione.put(39, COL_BCAA3);
		intestazione.put(40, COL_BCAA4);
		intestazione.put(41, COL_BCAA5);
		intestazione.put(42, COL_BCAA6);
		intestazione.put(43, COL_CGO2);
		intestazione.put(44, COL_CGO3);
		intestazione.put(45, COL_BCAA7);
		intestazione.put(46, COL_CGO4);
		intestazione.put(47, COL_CGO5);
		intestazione.put(48, COL_CGO6);
		intestazione.put(49, COL_CGO7);
		intestazione.put(50, COL_CGO8);
		intestazione.put(51, COL_CGO9);
		intestazione.put(52, COL_CGO10);
		intestazione.put(53, COL_CGO11);
		intestazione.put(54, COL_CGO12);
		intestazione.put(55, COL_CGO13);
		intestazione.put(56, COL_BCAA8);
    	return intestazione;
    }
    
    
    /**
     * prepara la lista di tutte le intestazioni delle colonne presenti nel file
     * @return LinkedHashMap<Integer, String>
     */
    private static LinkedHashMap<Integer, String> getListaIntestazioniAttiContrVet()
    {
    	LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
    	intestazione.put(0, COL_CUAA);
		intestazione.put(1, COL_CAMPAGNA);
		intestazione.put(2, COL_ATTO);
		intestazione.put(3, COL_DATA_1_CONTR);
		intestazione.put(4, COL_TIPO_CONTROLLO);
    	return intestazione;
    }
    
    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * @param fileExcel é l'oggetto che contiene i ati letti da file
     * @param lista
     * @param logList
     * @return boolean 
     */
    private static boolean getListFromExcel(FileExcel fileExcel, ArrayList<EsitoCondizionalitaBO> lista, List<String> logList)
    {
    	List<String> lstCuaa = new ArrayList<String>();
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		EsitoCondizionalitaBO esito = new EsitoCondizionalitaBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { esito.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { esito.set_campagna(valore); continue; }
//				if(intestazione.equalsIgnoreCase(COL_DATA_CAMPIONE)) 
//				{ 
//					if(valore != null && !valore.equals(""))
//					{
//						if(CheckData.checkStringDateFormat(valore))
//							esito.set_dataCampione(Utils.getDate(valore)); 
//						else 
//						{
//							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " data campione nel formato errato");
//							return false;
//						}
//					}
//					continue; 
//				}
				if(intestazione.equalsIgnoreCase(COL_DATA_CONTROLLO)) 
				{ 
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
				if(intestazione.equalsIgnoreCase(COL_ESITO_FINALE)) { esito.set_esitoFinale(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_RID_DU)) { esito.set_ridPercDU(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_RID_PSR)) { esito.set_ridPercPSR(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_STATO)) { esito.set_stato(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NOTE)) { esito.set_note(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NOTE_DECRETO)) { esito.set_note_decreto(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NUMERO_DECRETO)) { esito.set_numero_decreto(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DATA_DECRETO)) 
				{ 
					if(valore != null && !valore.equals(""))
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
			}
			// controllo per evitare di prendere righe vuote
			if(!esito.get_cuaa().equals("") && !esito.get_campagna().equals("")){
				lista.add(esito);
				// popolamento delle liste utilizzate per dettagliare il log dell'applicazione
				if(lstCuaa.isEmpty()){
					lstCuaa.add(esito.get_cuaa());
				}else if(!lstCuaa.contains(esito.get_cuaa())) lstCuaa.add(esito.get_cuaa());
			}
		}
    	
    	logList.add("INFO: Numero CUAA presenti nel file: "+ lstCuaa.size());
    	return true;
    }
    
    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * Il file excel che viene letto é quello che contiene gli esiti per atto, quindi gestisce le informazioni 
     * nel caso di esito negativo
     * @param fileExcel é l'oggetto che contiene i ati letti da file
     * @param lista é la lista da riempire
     * @param logList é la lista dei log
     * @return boolean
     */
    private boolean getListAttoFromExcel(FileExcel fileExcel,  ArrayList<EsitoCondizionalitaAttoBO> lista, List<String> logList)
    {
    	
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		EsitoCondizionalitaAttoBO esito = new EsitoCondizionalitaAttoBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { esito.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { esito.set_campagna(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_COD_COND)) { esito.set_cod_cond(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_ATTO_COND)) { esito.set_atto_contr(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_RESP_CONTROLLO)) { esito.set_resp_controllo(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_INFRAZIONE)) { esito.set_infrazione(valore.toUpperCase()); continue; }
				if(intestazione.equalsIgnoreCase(COL_TIPO_CONTROLLO)) { esito.set_tipoControllo(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DATA_1_CONTR)) 
				{ 
					if(valore != null && !valore.equals(""))
					{
						if(CheckData.checkStringDateFormat(valore))
							esito.set_data_1_contr(Utils.getDate(valore)); 
						else 
						{
							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " data 1 controllo nel formato errato");
							return false;
						}
					}
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_RICH_AZ_CORR)) { esito.set_rich_az_corr(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_AZ_CORR_ESEG)) { esito.set_az_corr_eseguita(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_RICH_IMP_RIPR)) { esito.set_rich_imp_ripr(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_IMP_RIPR_ESEG)) { esito.set_imp_ripr_eseguito(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DATA_2_CONTR)) 
				{ 
					if(valore != null && !valore.equals(""))
					{
						if(CheckData.checkStringDateFormat(valore))
							esito.set_data_2_contr(Utils.getDate(valore)); 
						else 
						{
							logList.add("ERROR: CUAA:" + esito.get_cuaa() + " data 2 controllo nel formato errato");
							return false;
						}
					}
					continue; 
				}
				if(intestazione.equalsIgnoreCase(COL_ESITO_2_CONTR)) { esito.set_esito_2_contr(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_INADEMPIENZA)) { esito.set_inadempienza(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NEGLIGENZA)) { esito.set_negligenza(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_INTENZIONALITA)) { esito.set_intenzionalita(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PROGR_ACC_INTENZ)) { esito.set_progr_accert_intenz(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_REITERAZIONE)) { esito.set_reiterazione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PROGR_ACC_REIT)) { esito.set_progr_Accert_reit(valore.replace(".0","")); continue; }
				if(intestazione.equalsIgnoreCase(COL_PORTATA)) { esito.set_portata(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_GRAVITA)) { esito.set_gravita(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DURATA)) { esito.set_durata(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PERC_RID)) { esito.set_perc_rid(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_AMMONIZIONE)) { esito.set_ammonizione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_NOTE)) { esito.set_note(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_PORTATA_NOTE)) { esito.set_portata_note(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_GRAVITA_NOTE)) { esito.set_gravita_note(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_DURATA_NOTE)) { esito.set_durata_note(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_INTENZIONALITA_NOTE)) { esito.set_intenzionalita_note(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_SEGNALAZIONE)) { esito.set_segnalazione(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_APPROVAZIONE)) { esito.set_approvazione(valore); continue; }
			}
			
			// se nel file non é idnicato il codice di condizionalità lo identifico
			if(esito.get_cod_cond() == null || esito.get_cod_cond().equals("")) esito.set_cod_cond(getCodiceCond(esito.get_atto_contr()));
			
			// controllo per evitare di prendere righe vuote
			if(!esito.get_cuaa().equals("") && !esito.get_campagna().equals(""))
				lista.add(esito);
		}
    	return true;
    }
    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * Il file excel che viene letto é quello che contiene la matrice degli atti controllati
     * @param fileExcel é l'oggetto che contiene i ati letti da file
     * @param lista é la lista da riempire
     * @param logList é la lista dei log
     * @return boolean
     */
    private boolean getListAttoFromExcelMatrice(FileExcel fileExcel,  ArrayList<EsitoCondizionalitaAttoBO> lista, List<String> logList)
    {
    	// ottengo gli atti di condizionalità con i relativi codici
    	_em.clear();
    	Query q = _em.createNamedQuery("AttoCondizionalita.selectAll");
		List<AttoCondizionalita> fornitura = q.getResultList();
		HashMap<String, String> atti = new HashMap<String, String>();
		for (AttoCondizionalita attoCondizionalita : fornitura) {
			atti.put(attoCondizionalita.get_attoCond(), attoCondizionalita.get_codCond());
		}   
		// creo una lista che contenga tutti gli atti (che rappresentano le diverse colonne per cui devo fare un inserimento
		List<String> lista_atti_file = new ArrayList<String>();
		lista_atti_file.add(COL_1_1);
		lista_atti_file.add(COL_1_2);
		lista_atti_file.add(COL_1_3);
		lista_atti_file.add(COL_2_1);
		lista_atti_file.add(COL_2_2);
		lista_atti_file.add(COL_3_1);
		lista_atti_file.add(COL_4_1);
		lista_atti_file.add(COL_4_2);
		lista_atti_file.add(COL_4_3);
		lista_atti_file.add(COL_4_4);
		lista_atti_file.add(COL_4_6);
		lista_atti_file.add(COL_5_1);
		lista_atti_file.add(COL_5_2);
		lista_atti_file.add(COL_A1);
		lista_atti_file.add(COL_A2);
		lista_atti_file.add(COL_A3);
		lista_atti_file.add(COL_A4);
		lista_atti_file.add(COL_A5);
		lista_atti_file.add(COL_A6);
		lista_atti_file.add(COL_A7);
		lista_atti_file.add(COL_A8);
		lista_atti_file.add(COL_B9);
		lista_atti_file.add(COL_B10);
		lista_atti_file.add(COL_B11);
		lista_atti_file.add(COL_B12);
		lista_atti_file.add(COL_B13);
		lista_atti_file.add(COL_B14);
		lista_atti_file.add(COL_B15);
		lista_atti_file.add(COL_C16);
		lista_atti_file.add(COL_C17);
		lista_atti_file.add(COL_C18);
		lista_atti_file.add(COL_FER);
		lista_atti_file.add(COL_FIT);
		lista_atti_file.add(COL_CGO1);
		lista_atti_file.add(COL_BCAA1);
		lista_atti_file.add(COL_BCAA2);
		lista_atti_file.add(COL_BCAA3);
		lista_atti_file.add(COL_BCAA4);
		lista_atti_file.add(COL_BCAA5);
		lista_atti_file.add(COL_BCAA6);
		lista_atti_file.add(COL_CGO2);
		lista_atti_file.add(COL_CGO3);
		lista_atti_file.add(COL_BCAA7);
		lista_atti_file.add(COL_CGO4);
		lista_atti_file.add(COL_CGO5);
		lista_atti_file.add(COL_CGO6);
		lista_atti_file.add(COL_CGO7);
		lista_atti_file.add(COL_CGO8);
		lista_atti_file.add(COL_CGO9);
		lista_atti_file.add(COL_CGO10);
		lista_atti_file.add(COL_CGO11);
		lista_atti_file.add(COL_CGO12);
		lista_atti_file.add(COL_CGO13);
		lista_atti_file.add(COL_BCAA8);
		
		// per ogni riga
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{    		
    		String cuaa="";
    		String campagna = "";
    		String data_controllo="";
    		// per ogni colonna
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { cuaa = valore; continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { campagna = valore.replace(".0", ""); continue; }
				if(intestazione.equalsIgnoreCase(COL_DATA_1_CONTR)) { 
					if(!valore.equals("")) data_controllo=valore; continue; 
				}
				if(lista_atti_file.contains(intestazione.toUpperCase())) {
					valore = valore.replaceAll("\\s",""); // elimino gli spazi vuoti
					if(valore == null || valore.equals("")) continue;
					EsitoCondizionalitaAttoBO esito = new EsitoCondizionalitaAttoBO();
					esito.set_cuaa(cuaa);
					esito.set_campagna(campagna);
					if(!data_controllo.equals("")) esito.set_data_1_contr(Utils.getDate(data_controllo));
					esito.set_atto_contr(intestazione.replace("_", ".")); 
					esito.set_cod_cond(atti.get(esito.get_atto_contr()));
					esito.set_resp_controllo(Costanti.VAL_RESP_CONTR_OP);
					esito.set_infrazione(Costanti.VAL_NO);
					// controllo per evitare di prendere righe vuote
					if(!esito.get_cuaa().equals("") && !esito.get_campagna().equals(""))
						lista.add(esito);
				}
			}
		}
    	return true;
    }


    
    /**
     * a partire dai dati del file excel (oggetto, non file fisico) crea la lista degli oggetti
     * Il file excel che viene letto é quello che contiene atti controllati dai veterinari
     * @param fileExcel é l'oggetto che contiene i ati letti da file
     * @param lista é la lista da riempire
     * @param logList é la lista dei log
     * @return boolean
     */
    private boolean getListAttoFromExcelVet(FileExcel fileExcel,  ArrayList<EsitoCondizionalitaAttoBO> lista, List<String> logList)
    {
    	// ottengo gli atti di condizionalità con i relativi codici
    	_em.clear();
    	Query q = _em.createNamedQuery("AttoCondizionalita.selectAll");
		List<AttoCondizionalita> fornitura = q.getResultList();
		HashMap<String, String> atti = new HashMap<String, String>();
		for (AttoCondizionalita attoCondizionalita : fornitura) {
			atti.put(attoCondizionalita.get_attoCond(), attoCondizionalita.get_codCond());
		}    	    	
		
    	for (LinkedHashMap<Integer, String> riga : fileExcel.getRighe()) 
    	{
    		EsitoCondizionalitaAttoBO esito = new EsitoCondizionalitaAttoBO();
			for (Integer chiave : fileExcel.getIntestazione().keySet()) {
				String intestazione = fileExcel.getIntestazione().get(chiave);
				String valore = riga.get(chiave);
				if(valore == null) continue;
				if(intestazione.equalsIgnoreCase(COL_CUAA)) { esito.set_cuaa(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_CAMPAGNA)) { esito.set_campagna(valore.replace(".0", "")); continue; } 
				if(intestazione.equalsIgnoreCase(COL_ATTO)) { esito.set_atto_contr(valore); continue; }
				if(intestazione.equalsIgnoreCase(COL_TIPO_CONTROLLO)) { esito.set_tipoControllo(valore); continue; }				
				try {
					if(intestazione.equalsIgnoreCase(COL_DATA_1_CONTR)) { 
						if(!valore.equals("")) esito.set_data_1_contr(Utils.getDate(valore)); continue; 
					}
				} catch (NumberFormatException e) {
					logList.add("ERROR: Errore nel formato delle date");
					return false;
				}
			}
			esito.set_resp_controllo(Costanti.VAL_RESP_CONTR_SSVV);
			esito.set_infrazione(Costanti.VAL_NO);
			esito.set_cod_cond(atti.get(esito.get_atto_contr()));
			// controllo per evitare di prendere righe vuote
			if(!esito.get_cuaa().equals("") && !esito.get_campagna().equals("")) lista.add(esito);
		}
    	return true;
    }
    
    /**
     * esegue l'inserimento di tutte le righe in una sola transazione
     * @param listaEsito
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivo(ArrayList<EsitoCondizionalitaBO> listaEsito, List<String> listLog)
    {
    	try {			
			_em.clear();
//			_em.getTransaction().begin();
			
			for (EsitoCondizionalitaBO esito : listaEsito) {
				
				EsitoCondizionalitaPk pk = new EsitoCondizionalitaPk();
				pk.set_cuaa(esito.get_cuaa());
				pk.set_campagna(esito.get_campagna());
				EsitoCondizionalita esitoFind = _em.find(EsitoCondizionalita.class, pk);
				
				// se la riga é stata trovata su db si valuta l'esito
				if(esitoFind != null) {
					if(esitoFind.get_esitoFinale() == null || (esitoFind.get_esitoFinale().equals("POSITIVO") && esito.get_esitoFinale().equals("NEGATIVO"))){
						//  caricare la riga e generare warning nel log
						_em.remove(esitoFind);
						// se la data del controllo é vuota si cerca 
						if(esito.get_dataControlloStr().equals("")) {
							ControlloPk contrPK = new ControlloPk();
							contrPK.set_cuaa(esito.get_cuaa());
							contrPK.set_campagna(esito.get_campagna());
							Controllo controlloFind = _em.find(Controllo.class, contrPK);
							if(controlloFind != null && !controlloFind.get_data_inizio_controllo().equals(""))
								esito.set_dataControllo(Utils.getDate(controlloFind.get_data_inizio_controllo()));
						}						
						// inserisco la riga
						EsitoCondizionalita esitoEntity = esito.getEntity();
						esitoEntity.set_user_inserimento(Utils.getCurrentUser());
						esitoEntity.set_data_inserimento(Utils.todayDate());
						_em.persist(esitoEntity);		
						listLog.add("WARNING: CUAA " + esito.get_cuaa() + " - esito precedente: POSITIVO - esito attuale: NEGATIVO. Riga caricata");
					}
					else 						
						if(esitoFind.get_esitoFinale().equals("POSITIVO") && esito.get_esitoFinale().equals("POSITIVO") || 
							esitoFind.get_esitoFinale().equals("NEGATIVO") && esito.get_esitoFinale().equals("NEGATIVO") ||
							esitoFind.get_esitoFinale().equals("NEGATIVO") && esito.get_esitoFinale().equals("POSITIVO")){
							// non caricare la riga e generare warning nel log
							listLog.add("WARNING: CUAA " + esito.get_cuaa() + " - esito precedente: " + esitoFind.get_esitoFinale() + 
									" - esito attuale: " + esito.get_esitoFinale() + ". Riga non caricata");
						}					
				}	
				else {	
					// inserisco la riga
					EsitoCondizionalita esitoEntity = esito.getEntity();
					esitoEntity.set_user_inserimento(Utils.getCurrentUser());
					esitoEntity.set_data_inserimento(Utils.todayDate());
					_em.persist(esitoEntity);	
				} 
					
			}
			
//			_em.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(CondizionalitaBean.class.getSimpleName() + " - inserimentoMassivo: " + e.getMessage());
	    	return false;
		}
    }
    
    /**
     * esegue l'inserimento di tutte le righe in una sola transazione
     * @param listaEsito
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivoAtto(ArrayList<EsitoCondizionalitaAttoBO> listaEsito, List<String> listLog)
    {
    	try {			
			//_em.clear();
//			_em.getTransaction().begin();
			
			for (EsitoCondizionalitaAttoBO esito : listaEsito) {
				EsitoCondizionalitaAttoPk pk = new EsitoCondizionalitaAttoPk();
				pk.set_cuaa(esito.get_cuaa());
				pk.set_campagna(esito.get_campagna());
				pk.set_cod_cond(esito.get_cod_cond());
				pk.set_atto_contr(esito.get_atto_contr());
				pk.set_tipoControllo(esito.get_tipoControllo());

				EsitoCondizionalitaAtto esitoFind = _em.find(EsitoCondizionalitaAtto.class, pk);
				
				// se sul DB c'è infrazione SI e sul file da importare c'è infrazione NO, non si fa nulla e si prosegue 
				if(esitoFind != null && 
						esitoFind.get_infrazione().equalsIgnoreCase("SI") &
						esito.get_infrazione().equalsIgnoreCase("NO")) break;
				
				// cancellazione della riga da DB
				if(esitoFind != null) _em.remove(esitoFind);
								
				// inserisco la riga
				EsitoCondizionalitaAtto esitoEntity = esito.getEntity();
				esitoEntity.set_user_inserimento(Utils.getCurrentUser());
				esitoEntity.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoEntity);
			}
			
//			_em.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(CondizionalitaBean.class.getSimpleName() + " - inserimentoMassivoAtto: " + e.getMessage());
	    	return false;
		}
    }
    
    /**
     * esegue l'inserimento di tutte le righe in una sola transazione per import del file con la matrice dei controlli fatti
     * @param listaEsito
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivoAttoMatrice(ArrayList<EsitoCondizionalitaAttoBO> listaEsito, List<String> listLog)
    {
    	try {			
			//_em.clear();
//			_em.getTransaction().begin();
			
			for (EsitoCondizionalitaAttoBO esito : listaEsito) {
				EsitoCondizionalitaAttoPk pk = new EsitoCondizionalitaAttoPk();
				pk.set_cuaa(esito.get_cuaa());
				pk.set_campagna(esito.get_campagna());
				pk.set_cod_cond(esito.get_cod_cond());
				pk.set_atto_contr(esito.get_atto_contr());
				pk.set_tipoControllo(esito.get_tipoControllo());

				EsitoCondizionalitaAtto esitoFind = _em.find(EsitoCondizionalitaAtto.class, pk);
				
				// l'esito viene inserito solo se non esiste, perchè si genera a partire dalla matrice di ammissibilità
				// e quindi non é necessario fare il controllo sull'infrazione
				if(esitoFind != null) { // l'atto esiste già
					if(!esito.get_data_1_contrStr().equals(""))
						esitoFind.set_data_1_contr(esito.get_data_1_contrStr());
					// imposto dati di modifica
					esitoFind.set_user_modifica(Utils.getCurrentUser());
					esitoFind.set_data_modifica(Utils.todayDate());
				}
				else {
					// inserisco la riga
					EsitoCondizionalitaAtto esitoEntity = esito.getEntity();
					esitoEntity.set_user_inserimento(Utils.getCurrentUser());
					esitoEntity.set_data_inserimento(Utils.todayDate());
					_em.persist(esitoEntity);
				}						
			}			
//			_em.getTransaction().commit();			
			return true;
		} catch (Exception e) {
			Utils.getLog().error(CondizionalitaBean.class.getSimpleName() + " - inserimentoMassivoAttoMatrice: " + e.getMessage());
	    	return false;
		}
    }
    
    /**
     * esegue l'inserimento di tutte le righe in una sola transazione per import del file con la matrice dei controlli fatti
     * @param listaEsito
     * @param listLog
     * @return boolean vale true se operazione terminata con successo, false altrimenti
     */
    private boolean inserimentoMassivoAttoControlliVet(ArrayList<EsitoCondizionalitaAttoBO> listaEsito, List<String> listLog)
    {
    	try {			
			//_em.clear();
//			_em.getTransaction().begin();
			
			for (EsitoCondizionalitaAttoBO esito : listaEsito) {
				EsitoCondizionalitaAttoPk pk = new EsitoCondizionalitaAttoPk();
				pk.set_cuaa(esito.get_cuaa());
				pk.set_campagna(esito.get_campagna());
				pk.set_cod_cond(esito.get_cod_cond());
				pk.set_atto_contr(esito.get_atto_contr());
				pk.set_tipoControllo(esito.get_tipoControllo());

				EsitoCondizionalitaAtto esitoFind = _em.find(EsitoCondizionalitaAtto.class, pk);
				// se la riga é stata trovata su db la cancello 
				if(esitoFind != null) { // se l'esito é già presente
					// caso 1: esito_old = OP - esito_new=SSVV
					if(esitoFind.get_resp_controllo().equals(Costanti.VAL_RESP_CONTR_OP) && 
							esito.get_resp_controllo().equals(Costanti.VAL_RESP_CONTR_SSVV))
					{
						// se ci sono due esiti per lo stesso atto con due diversi responsabili del controllo vince OPPAB
						continue;
					}
					// caso 2: esito_old = SSVV - esito_new=SSVV
					else if(esitoFind.get_resp_controllo().equals(Costanti.VAL_RESP_CONTR_SSVV) && 
								esito.get_resp_controllo().equals(Costanti.VAL_RESP_CONTR_SSVV))
					{
						if(!esito.get_data_1_contrStr().equals(""))
						{
							esitoFind.set_data_1_contr(esito.get_data_1_contrStr());
							esitoFind.set_tipoControllo(esito.get_tipoControllo());
							// imposto dati di modifica
							esitoFind.set_user_modifica(Utils.getCurrentUser());
							esitoFind.set_data_modifica(Utils.todayDate());
						}
						
					}
					// caso 3: esito_old = SSVV - esito_new=OP
					else if(esitoFind.get_resp_controllo().equals(Costanti.VAL_RESP_CONTR_SSVV) && 
							esito.get_resp_controllo().equals(Costanti.VAL_RESP_CONTR_OP))
					{
						// se ci sono due esiti per lo stesso atto con due diversi responsabili del controllo vince OPPAB
						continue;
					}
				}
				else { // l'esito non é presente
					// inserisco la riga
					EsitoCondizionalitaAtto esitoEntity = esito.getEntity();
					esitoEntity.set_user_inserimento(Utils.getCurrentUser());
					esitoEntity.set_data_inserimento(Utils.todayDate());
					_em.persist(esitoEntity);
				}						
			}			
//			_em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			Utils.getLog().error(CondizionalitaBean.class.getSimpleName() + " - inserimentoMassivoAttoVet: " + e.getMessage());
	    	return false;
		}
    }
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di fare la import
     * @param listaEsito
     * @return OperationResultBO
     */
    private OperationResultBO checkForImport(List<EsitoCondizionalitaBO> listaEsito)
    {
    	// controllo che il valore dei falg sia uguale a quello in anagrafica
    	List<String> lista_valori_esito = getListaValori(Costanti.ANAGR_ESITO);
    	List<String> lista_valori_stato = getListaValori(Costanti.ANAGR_STATO);
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (EsitoCondizionalitaBO esito : listaEsito) {
    		// stato
			if(!esito.get_stato().equals("") && !lista_valori_stato.contains(esito.get_stato()))
			{
				result.set_result(false);
				result.set_message("ERROR: Stato - valore non in anagrafica");
				break;
			}
			// esito
			if(!esito.get_esitoFinale().equals("") && !lista_valori_esito.contains(esito.get_esitoFinale()))
			{
				result.set_result(false);
				result.set_message("ERROR: Esito Finale - valore non in anagrafica");
				break;
			}
		}
    	return result;    	
    }
    
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di fare la import
     * @param listaEsito
     * @return OperationResultBO
     */
    private OperationResultBO checkForImportAtto(List<EsitoCondizionalitaAttoBO> listaEsito)
    {
    	// controllo che il valore dei falg sia uguale a quello in anagrafica
    	List<String> lista_ammonizione = getListaValori(Costanti.ANAGR_AMMONIZIONE);
    	List<String> lista_az_corr_eseg = getListaValori(Costanti.ANAGR_AZIONE_CORRETTIVA_ESEGUITA);
    	List<String> lista_az_corr_rich = getListaValori(Costanti.ANAGR_AZIONE_CORRETTIVA_RICHIESTA);
    	List<String> lista_imp_ripr_eseg = getListaValori(Costanti.ANAGR_IMPEGNO_RIPRISTINO_ESEGUITO);
    	List<String> lista_imp_ripr_rich = getListaValori(Costanti.ANAGR_IMPEGNO_RIPRISTINO_RICHIESTO);
    	List<String> lista_durata = getListaValori(Costanti.ANAGR_DURATA);
    	List<String> lista_portata = getListaValori(Costanti.ANAGR_PORTATA);
    	List<String> lista_gravita = getListaValori(Costanti.ANAGR_GRAVITA);
    	List<String> lista_negligenza = getListaValori(Costanti.ANAGR_NEGLIGENZA);
    	List<String> lista_inadempienza = getListaValori(Costanti.ANAGR_INADEMPIENZA);
    	List<String> lista_intenz = getListaValori(Costanti.ANAGR_INTENZIONALITA);
    	List<String> lista_progr_acc = getListaValori(Costanti.ANAGR_PROGRESSIVO_ACCERTAMENTO);
    	List<String> lista_reiterazione = getListaValori(Costanti.ANAGR_REITERAZIONE);
    	List<String> lista_resp_contr = getListaValori(Costanti.ANAGR_RESPONSABILE_CONTROLLO);    	
    	List<String> lista_esito = getListaValori(Costanti.ANAGR_ESITO);
    	List<String> lista_tipo_controllo = getListaValori(Costanti.ANAGR_TIPO_CONTROLLO_COND);  
    	//Riutilizzo questa costante perchè contiene già i valori SI/NO
    	List<String> lista_segnalazione = getListaValori(Costanti.ANAGR_AZIONE_CORRETTIVA_RICHIESTA);
    			
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (EsitoCondizionalitaAttoBO esito : listaEsito) {
    		// ammonizione
			if(!esito.get_ammonizione().equals("") && !lista_ammonizione.contains(esito.get_ammonizione()))
			{
				result.set_result(false);
				result.set_message("ERROR: Ammonizione - valore non in anagrafica");
				break;
			}
//			 atto_contr
			List<String> lista_atti = getListaAttoCond(); 
			if(!esito.get_atto_contr().equals("") && !lista_atti.contains(esito.get_atto_contr()))
			{
				result.set_result(false);
				result.set_message("ERROR: Atto controllato - valore non in anagrafica");
				break;
			}
			// Autodichiarazione trattamento non chimico

			// Azione correttiva richiesta
			if(!esito.get_rich_az_corr().equals("") && 
						!lista_az_corr_rich.contains(esito.get_rich_az_corr()))
			{
				result.set_result(false);
				result.set_message("ERROR: Azione correttiva richiesta - valore non in anagrafica");
				break;
			}
			// Azione correttiva eseguita
			if(!esito.get_az_corr_eseguita().equals("") && 
						!lista_az_corr_eseg.contains(esito.get_az_corr_eseguita()))
			{
				result.set_result(false);
				result.set_message("ERROR: Azione correttiva eseguita - valore non in anagrafica");
				break;
			}
			
			// Impegno ripristino eseguito
			if(!esito.get_imp_ripr_eseguito().equals("") && 
						!lista_imp_ripr_eseg.contains(esito.get_imp_ripr_eseguito()))
			{
				result.set_result(false);
				result.set_message("ERROR: Impegno ripristino eseguito - valore non in anagrafica");
				break;
			}
			// Impegno ripristino richiesto
			if(!esito.get_rich_imp_ripr().equals("") && 
						!lista_imp_ripr_rich.contains(esito.get_rich_imp_ripr()))
			{				
				result.set_result(false);
				result.set_message("ERROR: Impegno ripristino richiesto - valore non in anagrafica");
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
			// negligenza
			if(!esito.get_negligenza().equals("") && 
						!lista_negligenza.contains(esito.get_negligenza()))
			{
				result.set_result(false);
				result.set_message("ERROR: negligenza - valore non in anagrafica");
				break;
			}
			// inadempienza
			if(!esito.get_inadempienza().equals("") && 
						!lista_inadempienza.contains(esito.get_inadempienza()))
			{
				result.set_result(false);
				result.set_message("ERROR: inadempienza - valore non in anagrafica");
				break;
			}
			// intenzionalità
			if(!esito.get_intenzionalita().equals("") && 
						!lista_intenz.contains(esito.get_intenzionalita()))
			{
				result.set_result(false);
				result.set_message("ERROR: intenzionalita - valore non in anagrafica");
				break;
			}
			// progr. accert. Intenzionalità
			if(!esito.get_progr_accert_intenz().equals("") && 
						!lista_progr_acc.contains(esito.get_progr_accert_intenz()))
			{
				result.set_result(false);
				result.set_message("ERROR: progr. accert. Intenzionalità - valore non in anagrafica");
				break;
			}
			// progr. accert. reiterazione
			if(!esito.get_progr_Accert_reit().equals("") && 
						!lista_progr_acc.contains(esito.get_progr_Accert_reit()))
			{
				result.set_result(false);
				result.set_message("ERROR: progr. accert. reiterazione - valore non in anagrafica");
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
			// responsabile controllo
			if(!esito.get_resp_controllo().equals("") && 
						!lista_resp_contr.contains(esito.get_resp_controllo()))
			{
				result.set_result(false);
				result.set_message("ERROR: responsabile controllo - valore non in anagrafica");
				break;
			}
			// esito 2 controllo
			if(!esito.get_esito_2_contr().equals("") && 
						!lista_esito.contains(esito.get_esito_2_contr()))
			{
				result.set_result(false);
				result.set_message("ERROR: esito 2 controllo - valore non in anagrafica");
				break;
			}
			// tipo controllo
			if(!esito.get_tipoControllo().equals("") && 
						!lista_tipo_controllo.contains(esito.get_tipoControllo()))
			{
				result.set_result(false);
				result.set_message("ERROR: tipo controllo - valore non in anagrafica");
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
			// Approvazione
			if(!esito.get_approvazione().equals("") && 
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
     * Effettua dei controlli sulla coerenza dei dati prima di fare la import
     * @param listaEsito
     * @return OperationResultBO
     */
    private OperationResultBO checkForImportAttoVet(List<EsitoCondizionalitaAttoBO> listaEsito)
    {
    	List<String> lista_tipo_controllo = getListaValori(Costanti.ANAGR_TIPO_CONTROLLO_COND);  
    	
    	OperationResultBO result = new OperationResultBO();
    	result.set_result(true);
    	for (EsitoCondizionalitaAttoBO esito : listaEsito) {
    		
//			 atto_contr
			List<String> lista_atti = getListaAttoCond(); 
			if(!esito.get_atto_contr().equals("") && !lista_atti.contains(esito.get_atto_contr()))
			{
				result.set_result(false);
				result.set_message("ERROR: Atto controllato - valore non in anagrafica");
				break;
			}
			
			// tipo controllo
			if(!esito.get_tipoControllo().equals("") && 
						!lista_tipo_controllo.contains(esito.get_tipoControllo()))
			{
				result.set_result(false);
				result.set_message("ERROR: tipo controllo - valore non in anagrafica");
				break;
			}	
		}
    	return result;    	
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
	
	@Override
	public List<String> esitoCondAttoCheckForWarnings(EsitoCondizionalitaAttoBO esitoCondAtto) {
		List<String> warnings = new ArrayList<String>();
		
		// 1 - WARNING
		try {
			if(esitoCondAtto.get_resp_controllo().equalsIgnoreCase("SSVV") && 
					!esitoCondAtto.get_tipoControllo().equalsIgnoreCase("1-Controlli campione di condizionalita")) {
				warnings.add(Costanti.RESP_CONTR_SSVV_TIPO_CONTR_NO_CAMPCOND);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.RESP_CONTR_SSVV_TIPO_CONTR_NO_CAMPCOND
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 2 - WARNING
		try {
			if(esitoCondAtto.get_resp_controllo().equalsIgnoreCase("OP") && 
					(!esitoCondAtto.get_tipoControllo().equalsIgnoreCase("2-Controlli campione di ammissibilita") &&
							!esitoCondAtto.get_tipoControllo().equalsIgnoreCase("4-Altri controlli"))) {
				warnings.add(Costanti.RESP_CONTR_OP_TIPO_CONTR_NO_CAMPCOND_OR_ALTRICONTR);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.RESP_CONTR_OP_TIPO_CONTR_NO_CAMPCOND_OR_ALTRICONTR
					+ " - " + e.getMessage());
			e.printStackTrace();
		}


		// 3 - WARNING
		try {
			if(!esitoCondAtto.get_az_corr_eseguita().equalsIgnoreCase("NON RICHIESTA") && 
					esitoCondAtto.get_data_2_contrStr().isEmpty()) {
				warnings.add(Costanti.AZ_CORR_NON_RICHIESTA_NO_DATA_2CONTROLLO);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.AZ_CORR_NON_RICHIESTA_NO_DATA_2CONTROLLO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		
		// 4 - WARNING
		try {
			if(!esitoCondAtto.get_imp_ripr_eseguito().equalsIgnoreCase("NON RICHIESTO") && 
					esitoCondAtto.get_data_2_contrStr().isEmpty()) {
				warnings.add(Costanti.IM_RIPR_ESEGUITO_NON_RICHIESTA_NO_DATA_2CONTROLLO);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.IM_RIPR_ESEGUITO_NON_RICHIESTA_NO_DATA_2CONTROLLO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		
		// 5 - WARNING
		try {
			if(esitoCondAtto.get_negligenza().equalsIgnoreCase("SI") && 
					esitoCondAtto.get_intenzionalita().equalsIgnoreCase("SI")) {
				warnings.add(Costanti.NEGL_SI_INT_SI);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.NEGL_SI_INT_SI
					+ " - " + e.getMessage());
			e.printStackTrace();
		}
		
		
		// 6 - WARNING
		try {
			if(esitoCondAtto.get_negligenza().equalsIgnoreCase("NO") && 
					esitoCondAtto.get_intenzionalita().equalsIgnoreCase("NO")) {
				warnings.add(Costanti.NEGL_NO_INT_NO);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.NEGL_NO_INT_NO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}
		
		// 7 - WARNING
		try {
			if(esitoCondAtto.get_negligenza().equalsIgnoreCase("NO") && 
					esitoCondAtto.get_inadempienza().equalsIgnoreCase("SI")) {
				warnings.add(Costanti.NEGL_NO_INADEMP_MIN_SI);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.NEGL_NO_INADEMP_MIN_SI
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 8 - WARNING
		try {
			if(!esitoCondAtto.get_az_corr_eseguita().equalsIgnoreCase("NON RICHIESTA") && 
					esitoCondAtto.get_inadempienza().equalsIgnoreCase("NO")) {
				warnings.add(Costanti.AZ_CORR_NO_NON_RICHIESTA_INADEMP_MIN_NO);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.AZ_CORR_NO_NON_RICHIESTA_INADEMP_MIN_NO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}


		// 9 - WARNING
		try {
			if(esitoCondAtto.get_az_corr_eseguita().equalsIgnoreCase("NON RICHIESTA") && 
					esitoCondAtto.get_inadempienza().equalsIgnoreCase("SI")) {
				warnings.add(Costanti.AZ_CORR_NON_RICHIESTA_INADEMP_MIN_SI);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.AZ_CORR_NON_RICHIESTA_INADEMP_MIN_SI
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 10 - WARNING
		try {
			if(esitoCondAtto.get_ammonizione().equalsIgnoreCase("SI") && 
					esitoCondAtto.get_reiterazione().equalsIgnoreCase("NO")) {
				warnings.add(Costanti.AMM_SI_REIT_NO);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.AMM_SI_REIT_NO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 11 - WARNING
		try {
			Integer portata = 0;
			Integer gravita = 0;
			Integer durata = 0;
			
			if(!esitoCondAtto.get_portata().isEmpty())
				portata = Integer.parseInt(esitoCondAtto.get_portata());
			if(!esitoCondAtto.get_gravita().isEmpty())
				gravita = Integer.parseInt(esitoCondAtto.get_gravita());
			if(!esitoCondAtto.get_durata().isEmpty())
				durata = Integer.parseInt(esitoCondAtto.get_durata());

			if(esitoCondAtto.get_inadempienza().equalsIgnoreCase("SI") && 
					(portata != 1 || gravita != 1 || durata != 1)) {
				warnings.add(Costanti.INADMIN_SI_AND_PORT_GRAV_DUR_DIV_1);							
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.INADMIN_SI_AND_PORT_GRAV_DUR_DIV_1
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 12 - WARNING
		try {
			if(esitoCondAtto.get_reiterazione().equalsIgnoreCase("SI") && esitoCondAtto.get_progr_Accert_reit().isEmpty())
				warnings.add(Costanti.REITER_SI_PROGR_REIT_VUOTO);			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.REITER_SI_PROGR_REIT_VUOTO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}
		
		// 13 - WARNING
		try {
			if(esitoCondAtto.get_reiterazione().equalsIgnoreCase("NO") && !esitoCondAtto.get_progr_Accert_reit().isEmpty())
				warnings.add(Costanti.REITER_NO_PROGR_REIT_VALORIZ);
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.REITER_NO_PROGR_REIT_VALORIZ
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 14 - WARNING
		try {
			Integer progrReit = 0;
			
			if(!esitoCondAtto.get_progr_Accert_reit().isEmpty())
				progrReit = Integer.parseInt(esitoCondAtto.get_progr_Accert_reit());

			if(progrReit >=2 && (
					!esitoCondAtto.get_portata().isEmpty() ||
					!esitoCondAtto.get_portata_note().isEmpty() ||
					!esitoCondAtto.get_gravita().isEmpty() ||
					!esitoCondAtto.get_gravita_note().isEmpty() ||
					!esitoCondAtto.get_durata().isEmpty() ||
					!esitoCondAtto.get_durata_note().isEmpty() )) {
				warnings.add(Costanti.PROGR_REIT_MAGG2_AND_PORT_GRAV_DUR_VALORIZ);
			} 			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.PROGR_REIT_MAGG2_AND_PORT_GRAV_DUR_VALORIZ
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 15 - WARNING
		try {
			if(esitoCondAtto.get_intenzionalita().equalsIgnoreCase("SI") && esitoCondAtto.get_progr_accert_intenz().isEmpty())
				warnings.add(Costanti.INTENZ_SI_PROGR_ACCERT_INTENZ_VUOTO);			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.INTENZ_SI_PROGR_ACCERT_INTENZ_VUOTO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 16 - WARNING
		try {
			if(esitoCondAtto.get_intenzionalita().equalsIgnoreCase("NO") && !esitoCondAtto.get_progr_accert_intenz().isEmpty())
				warnings.add(Costanti.INTENZ_NO_PROGR_ACCERT_INTENZ_NON_VUOTO);			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.INTENZ_NO_PROGR_ACCERT_INTENZ_NON_VUOTO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		
		// 17 - WARNING
		try {
			if(esitoCondAtto.get_intenzionalita().equalsIgnoreCase("SI") && esitoCondAtto.get_intenzionalita_note().isEmpty())
				warnings.add(Costanti.INTENZ_SI_NOTE_VUOTO);			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.INTENZ_SI_NOTE_VUOTO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		
		// 18 - WARNING
		try {
			if(esitoCondAtto.get_intenzionalita().equalsIgnoreCase("NO") && !esitoCondAtto.get_intenzionalita_note().isEmpty())
				warnings.add(Costanti.INTENZ_NO_NOTE_NON_VUOTO);			
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.INTENZ_NO_NOTE_NON_VUOTO
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		// 19 - WARNING
		try {
			if(esitoCondAtto.get_intenzionalita().equalsIgnoreCase("SI") && (
					!esitoCondAtto.get_portata().isEmpty() ||
					!esitoCondAtto.get_portata_note().isEmpty() ||
					!esitoCondAtto.get_gravita().isEmpty() ||
					!esitoCondAtto.get_gravita_note().isEmpty() ||
					!esitoCondAtto.get_durata().isEmpty() ||
					!esitoCondAtto.get_durata_note().isEmpty() )) {
				warnings.add(Costanti.INTENZ_SI_AND_PORT_GRAV_DUR_VALORIZ);
			} 
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.INTENZ_SI_AND_PORT_GRAV_DUR_VALORIZ
					+ " - " + e.getMessage());
			e.printStackTrace();
		}
		
		
		// 20 - WARNING
		try {
			if(esitoCondAtto.get_perc_rid().isEmpty()) {
				warnings.add(Costanti.PERC_RID_EMPTY);
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.PERC_RID_EMPTY
					+ " - " + e.getMessage());
			e.printStackTrace();
		}

		
		// 21 - WARNING
		try {
			if(esitoCondAtto.get_note().isEmpty()) {
				warnings.add(Costanti.NOTE_VUOTE);				
			}
		} catch (Exception e) {
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + 
					" Errore nel checkForWarnings durante elaborazione del warning " 
					+ Costanti.NOTE_VUOTE
					+ " - " + e.getMessage());
			e.printStackTrace();
		}
		
		return warnings;
	}

	/**
	 * Restituisce le righe dei campioni COND EXTRA per per il cuaa e la campagna passati come parametri
	 * @param cuaa
	 * @param campagna
	 * @return List<String>
	 */
	@Override
	public List<CampioneBO> getListCampioneCondExtra(String cuaa, String campagna) {
		List<CampioneBO> result = new ArrayList<CampioneBO>();
		
		_em.clear();
		Query q = _em.createNamedQuery("Campione.selectCampioneCondExtraForCuaaCampagna");
		q.setParameter("campagna", campagna);
		q.setParameter("cuaa", cuaa);
		List<Campione> fornitura = q.getResultList();
		
		for(Campione c:fornitura) {
			result.add(new CampioneBO(c));
		}		

		return result;
	}
}
