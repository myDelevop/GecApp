package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.ElencoEsitoSuperficiBO;
import it.bz.prov.esiti.bobject.EsitoSuperficiBO;
import it.bz.prov.esiti.bobject.OperationResultBO;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione degli esiti superficie
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IEsitoSuperfici {
	
	/**
	 * aggiunge un esito alla lista
	 * @param esitoSuperfici
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsito(EsitoSuperficiBO esitoSuperfici);
	
	/**
	 * modifica un esito alla lista
	 * @param esitoSuperfici
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsito(EsitoSuperficiBO esitoSuperfici);
	
	/**
	 * cancella un esito dalla lista
	 * @param esitoSuperfici
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoSuperficiBO esitoSuperfici);

    public List<EsitoSuperficiBO> getElencoEsitoSuperfici();

    public void setElencoEsitoSuperfici(final ElencoEsitoSuperficiBO elencoEsitoSup);
    
    /**
	 * carica i dati degli esiti superficie 
	 */
	public void loadData();
	
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna();
	
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica richiesta
	 * @param nomeAnagrafica
	 * @return List<String>
	 */
	public List<String> getListaValori(String nomeAnagrafica);
	
	/**
	 * restituisce la lista di tutti gli interventi presenti
	 * @return List<String>
	 */
	public List<String> getListIntervento();
	
	/**
	 * restituisce la lista di tutti i sottointerventi presenti
	 * @return List<String>
	 */
	public List<String> getListSottointervento();

	/**
	 * restituisce la lista di tutti gli interventi presenti per una specifica campagna
	 * @return List<String>
	 */
	public List<String> getListIntervento(String campagna);
	
	/**
	 * restituisce la lista di tutti i sottointerventi presenti per una specifica campagna
	 * @return List<String>
	 */
	public List<String> getListSottointervento(String campagna);

	
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
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList();
	
	/**
	 * permette l'inserimento dei dati da file excel
	 * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
	 */
    public boolean insertFromFile(String pathFile, List<String> listLog);
    
    /**
     * Permette di restituire un valore booleano per sapere se la domanda possiede la coppia di intervento e sottointervento indicati
     * @param esito
     * @return
     */
    public boolean hasSottointervento(String domanda, String inte, String sottoInte);
    
	/**
	 * restituisce la lista di tutte le domande per il cuaa passato come parametro
	 * @param cuaa
	 * @param campagna
	 * @return List<String>
	 */
	public List<String> getListDomande(String cuaa, String campagna);
	
}
