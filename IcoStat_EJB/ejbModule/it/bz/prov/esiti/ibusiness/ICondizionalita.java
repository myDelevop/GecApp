package it.bz.prov.esiti.ibusiness;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import it.bz.prov.esiti.bobject.CampioneBO;
import it.bz.prov.esiti.bobject.ElencoEsitoCondizionalitaAttoBO;
import it.bz.prov.esiti.bobject.ElencoEsitoCondizionalitaBO;
import it.bz.prov.esiti.bobject.EsitoCondizionalitaAttoBO;
import it.bz.prov.esiti.bobject.EsitoCondizionalitaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione degli esiti di condizionalità
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface ICondizionalita {
	
	/***************************************************************************************
	 * 			METODI TRASVERSALI
	 ***************************************************************************************/
	
	
	/**
	 * esporta il layout del file excel da importare
	 * @param stream é lo stream su cui scrivere il file
	 * @param tipoLayout é il tipo di layout da esportare
	 */
	public void exportLayout(OutputStream stream, String tipoLayout);
	
	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList();
	
	/**
	 * restituisce la data del controllo, se presente nella tabella dei controlli
	 * @param cuaa
	 * @param campagna
	 * @return String é la data ottenuta
	 */
	public String getDataControllo(String cuaa, String campagna);
	
	
	/***************************************************************************************
	 * 			GESTIONE ESITI PER ATTO
	 ***************************************************************************************/
	
	
	/**
	 * aggiunge un esito alla lista
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsitoAtto(EsitoCondizionalitaAttoBO esito);
	
	/**
	 * modifica un esito alla lista
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsitoAtto(EsitoCondizionalitaAttoBO esito);
	
	/**
	 * cancella un esito dalla lista
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsitoAtto(EsitoCondizionalitaAttoBO esito);
	
	/**
     * Carica i dati in tabella
     */
    public void loadDataAtto();
    
    /**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFileAtto(OutputStream stream);
    
	
	/***************************************************************************************
	 * 			GESTIONE ESITI PER AZIENDA
	 ***************************************************************************************/
	
	/**
	 * aggiunge un esito alla lista
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsito(EsitoCondizionalitaBO esito);
	
	/**
	 * modifica un esito alla lista
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsito(EsitoCondizionalitaBO esito);
	
	/**
	 * cancella un esito dalla lista
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoCondizionalitaBO esito);

    
	/**
     * Carica i dati in tabella
     */
    public void loadData();
    
    /**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFileAzienda(OutputStream stream);
    
    
    /***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
	

	public void set_elencoEsitoAtto(ElencoEsitoCondizionalitaAttoBO _elencoEsitoAtto) ;

	public ElencoEsitoCondizionalitaAttoBO get_elencoEsitoAtto() ;

	public void set_elencoEsito(ElencoEsitoCondizionalitaBO _elencoEsito) ;
	
	public ElencoEsitoCondizionalitaBO get_elencoEsito() ;
	
	
	/***************************************************************************************
	 * 			UTILITY
	 ***************************************************************************************/
	
	
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna();
	
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica indicata
	 * @param valoriAnagrafica
	 * @return List<String>
	 */
	public List<String> getListaValori(String valoriAnagrafica);
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList);
	
	/**
	 * restituisce la lista dei valori distinti di atti di condizionalità per campagna
	 * @return List<String>
	 */
	public List<String> getListAttoContrForCampagna(String campagna);
	
	/**
	 * restituisce la lista dei valori distinti di codici di condizionalità per campagna
	 * @return List<String>
	 */
	public List<String> getListCodCondForCampagna(String campagna);
	
	/**
	 * restituisce la lista dei valori distinti di codici di condizionalità
	 * @return List<String>
	 */
	public List<String> getListaCodCond();
	
	/**
	 * restituisce la lista dei valori distinti di atti di condizionalità
	 * @return List<String>
	 */
	public List<String> getListaAttoCond();
	
	/**
	 * restituisce la lista dei valori distinti di atti di condizionalità per 
	 * il codice di condizionalità selezionato
	 * @param codCond
	 * @return List<String>
	 */
	public List<String> getListaAttoCond(String codCond);
	
	/**
	 * restituisce il codice di condizionalità dell'atto indicato
	 * @param attoCond
	 * @return String
	 */
	public String getCodiceCond(String attoCond);
	
	/**
	 * restituisce la lista di tutte le domande per il cuaa passato come parametro
	 * @param cuaa
	 * @param campagna
	 * @return List<String>
	 */
	public List<String> getListDomande(String cuaa, String campagna);

	
	/**
	 * Restituisce le righe dei campioni COND EXTRA per per il cuaa e la campagna passati come parametri
	 * @param cuaa
	 * @param campagna
	 * @return List<String>
	 */
	public List<CampioneBO> getListCampioneCondExtra(String cuaa, String campagna);

	
	/**
	 * permette l'inserimento dei dati da file excel
	 * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @param tabella é la tabella per cui viene fatto l'inserimento
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
	 */
    public boolean insertFromFile(String pathFile, List<String> listLog, int tabella);
    
    
    /**
	 * Restituisce la lista dei warnings a partire dall'oggetto BO esitoCondAtto
	 * @param esitoCondAtto 
	 * @return List<String>
	 **/
	public List<String> esitoCondAttoCheckForWarnings(EsitoCondizionalitaAttoBO esitoCondAtto);
	
}
