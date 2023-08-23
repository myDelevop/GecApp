package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.DomandaBO;
import it.bz.prov.esiti.bobject.ElencoEsitoArt68BO;
import it.bz.prov.esiti.bobject.EsitoArt68BO;
import it.bz.prov.esiti.bobject.OperationResultBO;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;


import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione degli esiti impegni
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IEsitoArt68 {

	/**
	 * aggiunge un esito alla lista
	 * @param esitoArt68
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiEsito(EsitoArt68BO esitoArt68);
	
	/**
	 * modifica un esito alla lista
	 * @param esitoArt68
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaEsito(EsitoArt68BO esitoArt68);
	
	/**
	 * cancella un esito dalla lista
	 * @param esitoImpegni
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoArt68BO esitoArt68);

    public List<EsitoArt68BO> getElencoEsitoArt68();

    public void setElencoEsitoArt68(final ElencoEsitoArt68BO elencoEsitoArt68);
    
    
    /**
	 * carica i dati degli esiti Art 68
	 */
	public void loadData();
	
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna();
	
	
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica indicata
	 * @param valoriAnagrafica
	 * @return List<String>
	 */
	public List<String> getListaValori(String valoriAnagrafica);

	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList);
	
	/**
     * Restituisce la domanda DU relativa al cuaa e alla campagna indicati
     * @param cuaa
     * @param campagna
     */
	public DomandaBO getDomandaDU(String cuaa, String campagna);
	
	
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




