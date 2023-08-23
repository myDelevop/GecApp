package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.ElencoTutelaAcqueBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.TutelaAcqueBO;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione degli esiti sulla tutela acque
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface ITutelaAcque {
	
	/**
	 * aggiunge una tutelaAcque alla lista
	 * @param tutelaAcque
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiTutelaAcque(TutelaAcqueBO tutelaAcque);
	
	/**
	 * modifica un elemento della lista
	 * @param tutelaAcque
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaTutelaAcque(TutelaAcqueBO tutelaAcque);
	
	/**
	 * cancella un elemento della lista
	 * @param tutelaAcque
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaTutelaAcque(TutelaAcqueBO tutelaAcque);

    public List<TutelaAcqueBO> getElencoTutelaAcque();

    public void setElencoTutelaAcque(final ElencoTutelaAcqueBO elencoTutelaAcque);
    
    /**
	 * carica i dati della tutela delle acque 
	 */
	public void loadData();
	
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica richiesta
	 * @param nomeAnagrafica
	 * @return List<String>
	 */
	public List<String> getListaValori(String nomeAnagrafica);
	
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
