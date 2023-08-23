package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.ElencoUbaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.UbaBO;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione degli esiti uba
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IUba {
	
	/**
	 * aggiunge una uba alla lista
	 * @param uba
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiUba(UbaBO uba);
	
	/**
	 * modifica un elemento della lista
	 * @param uba
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaUba(UbaBO uba);
	
	/**
	 * cancella un elemento della lista
	 * @param uba
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaUba(UbaBO uba);

    public List<UbaBO> getElencoUba();

    public void setElencoUba(final ElencoUbaBO uba);
    
    /**
	 * carica i dati degli esiti UBA 
	 */
	public void loadData();
	
	
	/**
	 * restituisce la lista dei valori distinti per l'esito
	 * @return List<String>
	 */
	public List<String> getListaValoriEsito();
	
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

}
