package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.ControlloBO;
import it.bz.prov.esiti.bobject.ElencoControlloBO;
import it.bz.prov.esiti.bobject.OperationResultBO;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione delle informazioni 
 * generiche sul controllo in loco
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IControllo {
	    
	
	/***************************************************************************************
	 * 			GESTIONE DATI CONTROLLO
	 ***************************************************************************************/

	/**
	 * aggiunge un controllo alla lista
	 * @param controllo
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiControllo(ControlloBO controllo) ;
	
	/**
	 * modifica un controllo alla lista
	 * @param controllo
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaControllo(ControlloBO controllo);
	
	/**
	 * cancella un controllo dalla lista
	 * @param controllo
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaControllo(ControlloBO controllo);
	
	/**
     * Carica i dati in tabella
     */
    public void loadData();
    
    /**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna();
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList);
	
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica richiesta
	 * @param nomeAnagrafica
	 * @return List<String>
	 */
	public List<String> getListaValori(String nomeAnagrafica);
	
	/**
	 * restituisce la lista di tutti i cuaa presenti per una certa campagna
	 * @param campagna
	 * @return List<String>
	 */
	public List<String> getListCuaa(String campagna);
	
	/**
     * verifica la presenza di un cuaa su DB
     * @param cuaa
     * @return boolean
     */
    public boolean existCuaa(String cuaa);
    
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
	 * restituisce la lista di tutte le domande per il cuaa passato come parametro
	 * @param cuaa
	 * @param campagna
	 * @return List<String>
	 */
	public List<String> getListDomande(String cuaa, String campagna);
	
	
	/***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
    
    public void setElencoControllo(ElencoControlloBO elencoControllo) ;

	public List<ControlloBO> getElencoControllo();
	
	/**
	 * permette l'inserimento dei dati da file excel
	 * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
	 */
    public boolean insertFromFile(String pathFile, List<String> listLog);
}
