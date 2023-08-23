package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.DomandaBO;
import it.bz.prov.esiti.bobject.ElencoDomandeBO;
import it.bz.prov.esiti.bobject.ElencoSottointerventiBO;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione delle domande
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IDomanda {
	
    public List<DomandaBO> getElencoDomande();

    public void setElencoDomande(final ElencoDomandeBO elencoDomande);

	
	/**
	 * consente la visualizzazione dei dettagli
	 * @param domanda
	 */
	public void visualizzaDettagli(DomandaBO domanda);
	
	/**
	 * carica i dati delle domande 
	 */
	public void loadData();
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList);

	public void set_elencoSottoint(ElencoSottointerventiBO _elencoSottoint) ;

	public ElencoSottointerventiBO get_elencoSottoint();
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream);
	
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna();
	
	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList();

}
