package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.CampioneBO;
import it.bz.prov.esiti.bobject.ElencoCampioneBO;
import it.bz.prov.esiti.bobject.OperationResultBO;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione dei campioni
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface ICampione {

	
	/***************************************************************************************
	 * 			GESTIONE DATI CAMPIONE
	 ***************************************************************************************/
		
	/**
	 * aggiunge un campione alla lista
	 * @param campione
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiCampione(CampioneBO campione) ;
	
	/**
	 * modifica un campione alla lista
	 * @param campione
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaCampione(CampioneBO campione);
	
	/**
	 * cancella un campione dalla lista
	 * @param campione
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaCampione(CampioneBO campione);

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
	
	/**
	 * restituisce la lista con la domanda PSR per il cuaa e campagna passati come parametro
	 * @param cuaa
	 * @param campagna
	 * @return List<String>
	 */
	public List<String> getListDomandePSR(String cuaa, String campagna);
	
	/***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
	
	public List<CampioneBO> getElencoCampione();

    public void setElencoCampione(final ElencoCampioneBO elencoCampione);
    
	/**
	 * permette l'inserimento dei dati da file excel
	 * @param pathFile é il percorso del file temporaneo salvato
	 * @param listLog é la lista dei log da visualizzare
	 * @return boolean vale true se l'operazione ha avuto successo, false altrimenti
	 */
    public boolean insertFromFile(String pathFile, List<String> listLog);
    
}
