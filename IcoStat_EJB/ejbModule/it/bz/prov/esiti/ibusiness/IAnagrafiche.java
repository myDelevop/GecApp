package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.AttoCondizionalitaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.ValoreAnagraficaBO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione delle anagrafiche
 * 
 * @author bpettazzoni
 *
 */


@Local
public interface IAnagrafiche {
	
	
	public void setListAnagrafiche(final List<String> listAnagrafiche) ;

	public List<String> getListAnagrafiche() ;
	
	/********************************************************************
	 * 	OPERAZIONI SULL'ANAGRAFICA BASE
	 ********************************************************************/
	
	/**
	 * visualizza i valori dell'anagrafica selezionata
	 * @param anagrafica
	 * @return List<ValoreAnagraficaBO>
	 */
	public List<ValoreAnagraficaBO> visualizzaValori(String anagrafica);
	
	/**
	 * aggiunge un nuovo valore alla lista
	 * @param valoreAnagrafica
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiValore(ValoreAnagraficaBO valoreAnagrafica);
	
	/**
	 * modifica un valore nella lista. 
	 * Se viene cambiata la campagna di inizio validità la riga viene storicizzata su database, 
	 * in modo da mantenere le informazioni valide per le campagne precedenti
	 * @param valoreAnagrafica
	 * @param valoreAnagraficaOld
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaValore(ValoreAnagraficaBO valoreAnagrafica, ValoreAnagraficaBO valoreAnagraficaOld);

	/**
	 * cancella un valore nella lista
	 * @param valoreAnagrafica
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaValore(ValoreAnagraficaBO valoreAnagrafica);
	
	/********************************************************************
	 * 	OPERAZIONI SULL'ANAGRAFICA DI CODICI-ATTI DI CONDIZIONALITA
	 ********************************************************************/
	
	/**
	 * visualizza i valori dell'anagrafica dei codici e degli atti di condizionalità
	 * @return List<AttoCondizionalitaBO>
	 */
	public List<AttoCondizionalitaBO> visualizzaCodiciAttiCond();
	
	/**
	 * aggiunge un nuovo atto all'anagrafica di codici e atti di condizionalità
	 * @param atto
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiAttoCond(AttoCondizionalitaBO atto);
	
	/**
	 * modifica un valore nella lista di atti di condizionalita
	 * Se viene cambiato il valore dell'atto, visto che é PK viene cancellata la riga vecchia e inserita la riga 
	 * nuova con i dati di inserimento (user e data) vecchi, in modo da rendere l'idea di una riga modificata
	 * e non di una riga nuova
	 * @param atto
	 * @param attoOld
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaAttoCond(AttoCondizionalitaBO atto, AttoCondizionalitaBO attoOld);
	
	/**
	 * cancella un valore dell'anagrafica degli atti di condizionalità
	 * @param atto
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaAttoCond(AttoCondizionalitaBO atto);
	
	
	 /***************************************************************************************
	 * 			METODI UTILITY
	 ***************************************************************************************/

	
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna();

}
