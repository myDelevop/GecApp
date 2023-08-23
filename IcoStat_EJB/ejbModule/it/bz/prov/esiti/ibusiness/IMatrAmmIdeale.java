package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.ElencoMatrAmmIdealeBO;

import java.io.OutputStream;
import java.util.HashMap;
import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione dei capi allevati
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IMatrAmmIdeale {

	
	/***************************************************************************************
	 * 			GESTIONE DATI 
	 ***************************************************************************************/
		
//	/**
//	 * aggiunge un elemento alla lista
//	 * @param capo
//	 * @return OperationResultBO
//	 */
//	public OperationResultBO aggiungiCapo(CapoAllevatoBO capo) ;
	
//	/**
//	 * modifica un elemento della lista
//	 * @param capo
//	 * @return OperationResultBO
//	 */
//	public OperationResultBO modificaCapo(CapoAllevatoBO capo);
	
//	/**
//	 * cancella un elemento dalla lista
//	 * @param capo
//	 * @return OperationResultBO
//	 */
//	public OperationResultBO cancellaCapo(CapoAllevatoBO capo);

	/**
     * Carica i dati in tabella
     */
    public void loadData();
    
//    /**
//	 * restituisce la lista di tutte le campagne presenti
//	 * @return List<String>
//	 */
//	public List<String> getListCampagna();
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList);
	
//	/**
//	 * restituisce la lista dei valori distinti per l'anagrafica richiesta
//	 * @param nomeAnagrafica
//	 * @return List<String>
//	 */
//	public List<String> getListaValori(String nomeAnagrafica);
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream);
	
	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList();
	
	
	/***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
	
	public ElencoMatrAmmIdealeBO getElencoMatrAmmIdeale() ;

}
