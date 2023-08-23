package it.bz.prov.esiti.ibusiness;

import it.bz.prov.esiti.bobject.DomandaBO;
import it.bz.prov.esiti.bobject.ElencoEsitoZootecniaBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaControlloAmmBovBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaControlloAmmOviBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaControlloCondBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaEsitoAmmBovBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaEsitoAmmOviBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaStatoBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.entity.EsitoZootecniaEsitoAmmBov;

import java.util.HashMap;
import java.util.List;


import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione degli esiti zootecnia
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IEsitoZootecnia {    
    
	/************************************************************************
	 * 				METODI GENERICI
	 ***********************************************************************/
    
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData();
	

	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList);
	
	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList();
	
	
	/**
	 * trova la domanda DU a partire dal CUAA e dalla campagna
	 * @param cuaa
	 * @param campagna
	 * @return DomandaBO
	 */
	public DomandaBO getDomandaDU(String cuaa, String campagna);
	

	/************************************************************************
	 * 				ESITO E DATI CONTROLLO
	 ***********************************************************************/
	
	
	/**
	 * salva i dati relativi all'esito
	 * @param esito
	 * @param azione : valore numerico che indica l'azione da cui proviene la richiesta 
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaEsito(EsitoZootecniaStatoBO esito, int azione);
	
	/**
	 * cancella i dati relativi all'esito
	 * @param esito 
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoZootecniaStatoBO esito);
	
	/**
	 * recupera dal database la riga relativa ai dati registrati per i dati generici dell'esito
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaStatoBO getStato(String cuaa, String domanda, String campagna);
	

	/**
	 * restituisce lo stato dell'esito relativo alla posizione data dall'indice nella lista
	 * @param index
	 * @return EsitoZootecniaStatoBO
	 */
	public EsitoZootecniaStatoBO getStatoFromIndex(int index);
		

	/************************************************************************
	 * 				CONTROLLO AMM BOVINI
	 ***********************************************************************/
	

	/**
	 * recupera dal database la riga relativa ai dati registrati per il controllo di ammissibilità bovini
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaControlloAmmBovBO getControlloAmmBovini(String cuaa, String domanda, String campagna);
	
	/**
	 * salva su database i dati relativi ai controlli sui bovini
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaControlloAmmBovini(EsitoZootecniaControlloAmmBovBO esito);
	
	/**
	 * cancella su database i dati del controllo di ammissibilità bovini
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaControlloAmmBovini(String cuaa, String domanda, String campagna);
	
	
	/************************************************************************
	 * 				ESITO AMM BOVINI
	 ***********************************************************************/
	

	/**
	 * recupera dal database la riga relativa ai dati registrati per gli esiti del controllo di ammissibilità bovini
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaEsitoAmmBovBO getEsitoAmmBovini(String cuaa, String domanda, String campagna, String intervento);
	
	/**
	 * salva su database l'esito e la sanzione dei controlli sui bovini
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaEsitoAmmBovini(EsitoZootecniaEsitoAmmBovBO esito);
	
	/**
	 * cancella su database i dati dell'esito del controllo di ammissibilità bovini
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsitoAmmBovini(String cuaa, String domanda, String campagna);
	
	
	/**
	 * cancella su database i dati dell'esito dell'intervento per ammissibilità bovini
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @param intervento
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsitoIntAmmBovini(String cuaa, String domanda, int campagna, String intervento);

	
	/************************************************************************
	 * 				CONTROLLO AMM OVICAPRINI
	 ***********************************************************************/
	
	
	/**
	 * recupera dal database la riga relativa ai dati registrati per il controllo di ammissibilità ovicaprini
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return EsitoZootecniaControlloAmmOviBO
	 */
	public EsitoZootecniaControlloAmmOviBO getControlloAmmOvi(String cuaa, String domanda, String campagna);
	
	/**
	 * salva su database i dati relativi al controllo sugli ovicaprini
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaControlloAmmOvicaprini(EsitoZootecniaControlloAmmOviBO esito);
	
	/**
	 * cancella su database i dati del controllo di ammissibilità ovicaprini
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaControlloAmmOvicaprini(String cuaa, String domanda, String campagna);
	

	/************************************************************************
	 * 				ESITO AMM OVICAPRINI
	 ***********************************************************************/
	

	/**
	 * recupera dal database la riga relativa ai dati registrati per gli esiti del controllo di ammissibilità ovicaprini
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaEsitoAmmOviBO getEsitoAmmOvi(String cuaa, String domanda, String campagna);
	
	/**
	 * salva su database i dati di esito e sanzioni relativi al controllo sugli ovicaprini
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaEsitoAmmOvicaprini(EsitoZootecniaEsitoAmmOviBO esito);
	
	/**
	 * cancella su database i dati dell'esito del controllo di ammissibilità ovicaprini
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsitoAmmOvicaprini(String cuaa, String domanda, String campagna);
	
	
	public EsitoZootecniaEsitoAmmBov getEsitoBovFromOvi(String campagna, String cuaa, String domanda);

	/************************************************************************
	 * 				CONTROLLO COND
	 ***********************************************************************/
		

	/**
	 * recupera dal database la riga relativa ai dati registrati per il controllo di condizionalità
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaControlloCondBO getControlloCond(String cuaa, String domanda, String campagna);
	
	/**
	 * salva su database i dati del controllo di condizionalità zootecnia
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaControlloCond(EsitoZootecniaControlloCondBO esito);
	
	/**
	 * cancella su database i dati del controllo di condizionalità zootecnia
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaControlloCond(String cuaa, String domanda, String campagna);
	
	
	/************************************************************************
	 * 				GETTER E SETTER
	 ***********************************************************************/
	
	public List<EsitoZootecniaStatoBO> getElencoEsitoZoot();

    public void setElencoEsitoZoot(final ElencoEsitoZootecniaBO elencoEsito);
    
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
	
}




