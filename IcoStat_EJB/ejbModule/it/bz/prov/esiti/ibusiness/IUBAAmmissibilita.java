package it.bz.prov.esiti.ibusiness;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import it.bz.prov.esiti.bobject.ElencoUBAAmmissibilitaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.UBAAmmissibilitaBO;

@Local
public interface IUBAAmmissibilita {
	
	/**
	 * aggiunge una uba alla lista
	 * @param uba
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiUba(UBAAmmissibilitaBO uba);
	
	/**
	 * modifica un elemento della lista
	 * @param uba
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaUba(UBAAmmissibilitaBO uba);
	
	/**
	 * cancella un elemento della lista
	 * @param uba
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaUba(UBAAmmissibilitaBO uba);

    public List<UBAAmmissibilitaBO> getElencoUba();

    public void setElencoUba(final ElencoUBAAmmissibilitaBO uba);
    
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
    
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica indicata
	 * @param valoriAnagrafica
	 * @return List<String>
	 */
	public List<String> getListaValori(String valoriAnagrafica);

}

