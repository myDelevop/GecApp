package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.AziendaBO;
import it.bz.prov.esiti.bobject.ElencoAziendeBO;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione delle aziende
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IAzienda {
	
    public List<AziendaBO> getElencoAziende();

    public void setElencoAziende(final ElencoAziendeBO elencoAziende);
    
    /**
     * Carica i dati in tabella
     */
    public void loadData();
    
    /**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
    public void filter(HashMap<String, String> parametersList);
    
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
	 * Ritorna una lista di aziende dove il valore di partialCUAA è contenuto nella stringa a DB.
	 * @param partialCUAA
	 * @return
	 */
	public List<AziendaBO> getListaAziendeByPartialCUAA(String partialCUAA);
	
	/**
	 * Ritorna una lista di aziende dove il valore di partialDenominazione è contenuto nella stringa a DB.
	 * @param partialDenominazione
	 * @return
	 */
	public List<AziendaBO> getListaAziendeByPartialDenominazione(String partialDenominazione);
}
