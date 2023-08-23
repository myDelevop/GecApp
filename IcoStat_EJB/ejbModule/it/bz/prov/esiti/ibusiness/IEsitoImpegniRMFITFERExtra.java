package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.ElencoEsitoImpegniRMFITFERExtraBO;
import it.bz.prov.esiti.bobject.EsitoImpegniExtraBO;
import it.bz.prov.esiti.bobject.EsitoImpegniRMFITFERExtraBO;
import it.bz.prov.esiti.bobject.OperationResultBO;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;






import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione degli esiti impegni
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IEsitoImpegniRMFITFERExtra {

	/**
	 * aggiunge un esito alla lista
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsito(EsitoImpegniRMFITFERExtraBO esitoImpegni);
	
	/**
	 * modifica un esito alla lista
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsito(EsitoImpegniRMFITFERExtraBO esitoImpegni);
	
	/**
	 * cancella un esito dalla lista
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoImpegniRMFITFERExtraBO esitoImpegni);

    public List<EsitoImpegniRMFITFERExtraBO> getElencoEsitoImpegni();

    public void setElencoEsitoImpegni(final ElencoEsitoImpegniRMFITFERExtraBO elencoEsitoImpegni);
    
    
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData();
	
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna();
	
	/**
	 * restituisce la lista di tutte le misure presenti
	 * @return List<String>
	 */
	public List<String> getListMisura();
	
	/**
	 * restituisce la lista delle misure presenti per una specifica campagna
	 * @return List<String>
	 */
	public List<String> getListMisuraForCampagna(String campagna);
	
	
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica indicata
	 * @param valoriAnagrafica
	 * @return List<String>
	 */
	public List<String> getListaValori(String valoriAnagrafica);
	
	/**
	 * restituisce la lista di tutti gli interventi presenti
	 * @return List<String>
	 */
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList);
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream);
	
	/**
	 * permette l'inserimento dei dati da file excel
	 * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
	 */
	public boolean insertFromFile(String pathFile, List<String> listLog);
	
	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList();
	
	/**
	 * restituisce la lista di tutte le domande per il cuaa passato come parametro
	 * @param cuaa
	 * @param campagna
	 * @return List<String>
	 */
	public List<String> getListDomande(String cuaa, String campagna);
	
	/**
	 * restituisce la lista di tutte le domande PSR per il cuaa passato come parametro
	 * @param cuaa
	 * @param campagna
	 * @return List<String>
	 */
	public List<String> getListDomandePSR(String cuaa, String campagna);
	
	
	/**
	 * Restituisce la lista dei warnings a partire dall'oggetto BO esitoImpegni
	 * @param esitoImpegni 
	 * @return List<String>
	 * */
	public List<String> checkForWarningsEsitoImpegniExtra(EsitoImpegniRMFITFERExtraBO esitoImpegni);
}




