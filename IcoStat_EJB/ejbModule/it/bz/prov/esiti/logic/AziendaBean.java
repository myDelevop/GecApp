package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.AziendaBO;
import it.bz.prov.esiti.bobject.ElencoAziendeBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.ibusiness.IAzienda;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.ExcelWriter;
import it.bz.prov.esiti.util.Utils;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

/**
 * Bean per la gestione back-end delle aziende
 * 
 * @author bpettazzoni
 *
 */

@Stateful
@SuppressWarnings("unchecked")
public class AziendaBean implements IAzienda{
	
	private ElencoAziendeBO elencoAziende = new ElencoAziendeBO();

	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	private static final Logger _log = Logger.getLogger(AziendaBean.class);

	/**
     * Carica i dati in tabella
     */
    public void loadData()
    {
    	_em.clear();
    	Query q = _em.createNamedQuery("Azienda.selectAll");
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<Azienda> fornitura = q.getResultList();
		_log.info("Letto "+fornitura.size());
		HashMap<String, AziendaBO> lista = new HashMap<String, AziendaBO>();
		for (Azienda azienda : fornitura) {
			lista.put(azienda.get_cuaa(), new AziendaBO(azienda));
		}
		elencoAziende.set_listAziende(lista);
    }
 
    
    /**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
    public void filter(HashMap<String, String> parametersList)
    {
    	_em.clear();
    	Query q ;
    	// definizione della query in base ai parametri selezionati
    	if(filterByAziendaAttribute(parametersList)) // caso di selezione solo per cuaa o ragione sociale
    	{
    		q = _em.createNamedQuery("Azienda.selectFilterOnlyAzienda");
    		setQueryParameterAzienda(parametersList, q);
    	}
    	else if(filterByAziendaDomandaAttribute(parametersList))
    	{
    		q = _em.createNamedQuery("Azienda.selectFilterAziendaDomanda");
    		setQueryParameterAziendaDomanda(parametersList, q);
    	}
    	else // caso in cui vengono selezionati dati da domanda o campione
    	{
    		q = _em.createNamedQuery("Azienda.selectFilter");
    		// imposto i parametri della query
    		Utils.setQueryParameter(parametersList, q);	
    	}    	
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<Azienda> fornitura = q.getResultList();
		HashMap<String, AziendaBO> lista = new HashMap<String, AziendaBO>();
		for (Azienda azienda : fornitura) {
			lista.put(azienda.get_cuaa(), new AziendaBO(azienda));
		}
		elencoAziende.set_listAziende(lista);
    }
    
    /**
     * verifica la presenza di un cuaa su DB
     * @param cuaa
     * @return boolean
     */
    public boolean existCuaa(String cuaa)
    {
    	_em.clear();
    	Azienda azienda = _em.find(Azienda.class, cuaa);
    	if(azienda == null) return false; // il cuaa non esiste
    	else return true;
    }
    
    /**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream)
	{
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
		intestazione.put(0, "CUAA");
		intestazione.put(1, "Ragione Sociale");
		intestazione.put(2, "Provincia");
		intestazione.put(3, "Sorgente");
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (AziendaBO azienda : elencoAziende.get_listAziende()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, azienda.get_cuaa());
			riga.put(1, azienda.get_ragioneSociale());
			riga.put(2, azienda.get_provincia());
			riga.put(3, azienda.get_sorgente());
			righe.add(riga);
		}
		
		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}
	
	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList()
	{
		elencoAziende = new ElencoAziendeBO();
	}
	
	 /***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
	
	
	public List<AziendaBO> getElencoAziende()
    {
        return this.elencoAziende.get_listAziende();
    }

    public void setElencoAziende(final ElencoAziendeBO elencoAziende)
    {
        this.elencoAziende = elencoAziende;
    }
    
    /***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	    
    
    /**
     * verifica se gli unici filtri settati sono quelli dell'azienda 
     * (serve per poter applicare una query dedicata)
     * @param parametersList
     * @return
     */
    private boolean filterByAziendaAttribute(HashMap<String, String> parametersList)
    {
    	for (String key : parametersList.keySet()) {
    		// se il valore é diverso da % e la chiave é diversa da cuaa e ragione sociale
			if(!parametersList.get(key).equals("%") && 
					!key.equals(Costanti.SEARCH_CUAA) && !key.equals(Costanti.SEARCH_RAG_SOC))
				return false;
		}
    	return true;
    }
    
    /**
     * verifica se gli unici filtri settati sono quelli dell'azienda 
     * (serve per poter applicare una query dedicata)
     * @param parametersList
     * @return
     */
    private boolean filterByAziendaDomandaAttribute(HashMap<String, String> parametersList)
    {
    	for (String key : parametersList.keySet()) {
    		// se il valore é diverso da % e la chiave é diversa da cuaa e ragione sociale
			if(!parametersList.get(key).equals("%") && !key.equals(Costanti.SEARCH_CUAA) &&
					!key.equals(Costanti.SEARCH_RAG_SOC) && !key.equals(Costanti.SEARCH_DOMANDA) &&
					!key.equals(Costanti.SEARCH_CAMPAGNA) && !key.equals(Costanti.SEARCH_CAMPAGNA))
				return false;
		}
    	return true;
    }
    
    
    /**
     * imposta il cuaa e la ragione sociale come parametri di ricerca
     * @param parametersList
     * @param q
     */
	private void setQueryParameterAzienda(HashMap<String, String> parametersList, Query q) {
		
		if(parametersList.containsKey("cuaa") && !parametersList.get("cuaa").equals("%")) 
			q.setParameter("cuaa", "%" + parametersList.get("cuaa") + "%");
		else 
			q.setParameter("cuaa", "%");
		
		if(parametersList.containsKey("ragione_sociale") && !parametersList.get("ragione_sociale").equals("%")) 
			q.setParameter("ragione_sociale", "%" + parametersList.get("ragione_sociale") + "%");
		else 
			q.setParameter("ragione_sociale", "%");
	}  
	
	/**
     * sulla base dei parametri di ricerca inseriti imposta i parametri nella query di ricerca
     * Imposta solo i parametri per azienda e domanda
     * @param parametersList
     * @param q
     */
	public static void setQueryParameterAziendaDomanda(HashMap<String, String> parametersList, Query q) {
		if(parametersList.containsKey("domanda") && !parametersList.get("domanda").equals("%")) 
			q.setParameter("domanda", "%" + parametersList.get("domanda") + "%");
		else 
			q.setParameter("domanda", "%");
		
		if(parametersList.containsKey("cuaa") && !parametersList.get("cuaa").equals("%")) 
			q.setParameter("cuaa", "%" + parametersList.get("cuaa") + "%");
		else 
			q.setParameter("cuaa", "%");
		
		if(parametersList.containsKey("ragione_sociale") && !parametersList.get("ragione_sociale").equals("%")) 
			q.setParameter("ragione_sociale", "%" + parametersList.get("ragione_sociale") + "%");
		else 
			q.setParameter("ragione_sociale", "%");
		
		if(parametersList.containsKey("misura") && !parametersList.get("misura").equals("%")) 
			q.setParameter("misura", "%" + parametersList.get("misura") + "%");
		else 
			q.setParameter("misura", "%");
		
		if(parametersList.containsKey("campagna") && !parametersList.get("campagna").equals("%"))
			q.setParameter("campagna", "%" + parametersList.get("campagna") + "%");
		else 
			q.setParameter("campagna", "%");
	}
   
	@Override
	public List<AziendaBO> getListaAziendeByPartialCUAA(String partialCUAA) {
		List<AziendaBO> ris = new ArrayList<AziendaBO>();
		try {
			Query q = _em.createNamedQuery("Azienda.selectByPartialCUAA");
			q.setParameter("partialCUAA", "%" + partialCUAA + "%");
			List<Azienda> fornitura = q.getResultList();
			for(Azienda a: fornitura) 
				ris.add(new AziendaBO(a));
			return ris;
		} catch (Exception e) {
			Utils.getLog().error(AziendaBean.class.getSimpleName() + " - " + e.getMessage() );
			return ris;
		}
	}


	@Override
	public List<AziendaBO> getListaAziendeByPartialDenominazione(String partialDenominazione) {
		List<AziendaBO> ris = new ArrayList<AziendaBO>();
		try {
			Query q = _em.createNamedQuery("Azienda.selectByPartialDenominazione");
			q.setParameter("partialDenominazione", "%" + partialDenominazione + "%");
			List<Azienda> fornitura = q.getResultList();
			for(Azienda a: fornitura) 
				ris.add(new AziendaBO(a));
			return ris;
		} catch (Exception e) {
			Utils.getLog().error(AziendaBean.class.getSimpleName() + " - " + e.getMessage() );
			return ris;
		}
	}
}
