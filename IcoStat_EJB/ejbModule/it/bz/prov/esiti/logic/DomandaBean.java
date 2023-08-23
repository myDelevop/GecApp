package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.DomandaBO;
import it.bz.prov.esiti.bobject.ElencoDomandeBO;
import it.bz.prov.esiti.bobject.ElencoSottointerventiBO;
import it.bz.prov.esiti.entity.Domanda;
import it.bz.prov.esiti.entity.Sottointervento;
import it.bz.prov.esiti.ibusiness.IDomanda;
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

/**
 * Bean per la gestione back-end delle domande
 * 
 * @author bpettazzoni
 *
 */

@Stateful
@SuppressWarnings("unchecked")
public class DomandaBean implements IDomanda{
	
	private static String COL_CUAA= "CUAA";
	private static String COL_DOMANDA_OPR = "Domanda OPR";
	private static String COL_DOMANDA_AGEA = "Domanda AGEA";
	private static String COL_CAMPAGNA = "Campagna";
	private static String COL_MISURA = "Misura";
	
	private ElencoDomandeBO elencoDomande = new ElencoDomandeBO();
	private ElencoSottointerventiBO _elencoSottoint = new ElencoSottointerventiBO();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
		
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna(){
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.distinctCampagna");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	
    public List<DomandaBO> getElencoDomande()
    {
        return this.elencoDomande.get_listDomande();
    }

    public void setElencoDomande(final ElencoDomandeBO elencoDomande)
    {
        this.elencoDomande = elencoDomande;
    }
	
	/**
	 * consente la visualizzazione dei dettagli
	 * @param domanda
	 */
	public void visualizzaDettagli(DomandaBO domanda)
	{
		_em.clear();
		if(!domanda.get_misura().equals("DU")){
			Query q = _em.createNamedQuery("Sottointervento.selectAllForDomanda");
	    	q.setParameter("domanda", domanda.get_idDomanda());
//			ArrayList<Sottointervento> fornitura = (ArrayList<Sottointervento>) q.getResultList();
	    	ArrayList<Object[]> fornitura = (ArrayList<Object[]>) q.getResultList();
	    	ArrayList<Sottointervento> lista = new ArrayList<Sottointervento>();
	    	for (Object[] riga : fornitura) {
	    		// evito l'errore di indexOutOfBound (anche se non dovrebbe capitare) 
	    		if(riga.length!=4) break;
				String cod_intervento = riga[0].toString();
				String des_intervento = riga[1].toString();
				String cod_sottointervento = riga[2].toString();
				String des_sottointervento = riga[3].toString();
				Sottointervento s = new Sottointervento();
				s.set_domanda(domanda.get_idDomanda());
				s.set_codiceIntervento(cod_intervento);
				s.set_descrizioneIntervento(des_intervento);
				s.set_codiceSottointervento(cod_sottointervento);
				s.set_descrizioneSottointervento(des_sottointervento);
				lista.add(s);
			}
			_elencoSottoint.set_listSottoint(lista);
		}
		else _elencoSottoint = new ElencoSottointerventiBO(); // lo svuoto se é una DU
	}
	
	
	/**
	 * carica i dati delle domande 
	 */
	public void loadData()
	{
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.selectAll");
    	_em.clear();
    	q.setMaxResults(Costanti.QUERY_TOP_K);
		ArrayList<Domanda> fornitura = (ArrayList<Domanda>) q.getResultList();
		for (Domanda domanda : fornitura) {
			elencoDomande.addDomanda(new DomandaBO(domanda));
		}
	}
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList)
    {
    	Query q  = null;
		_em.clear();
	
		// se non si filtra su campione e segnalazione utilizzo una query che non va in join con il campione
		// lo faccio perchè nella query viene fuori un full join e non un left join, quindi
		// non riesco a visualizzare le domande che non sono a campione
		if(parametersList.get(Costanti.SEARCH_CAMPIONE).equals("%") && 
				parametersList.get(Costanti.SEARCH_SEGNALAZIONE).equals("%") )
		{
			q = _em.createNamedQuery("Domanda.selectFilterNoCampione");
			// imposto i parametri della query
			setQueryParameter(parametersList, q);
		}
		else
		{
			q = _em.createNamedQuery("Domanda.selectFilter");
			// imposto i parametri della query
			Utils.setQueryParameter(parametersList, q);
		}
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<Domanda> fornitura = q.getResultList();
		elencoDomande.set_listDomande(fornitura);
    }
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream)
	{
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = getListaIntestazioni();
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (DomandaBO domanda : elencoDomande.get_listDomande()) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, domanda.get_cuaa());
			riga.put(1, domanda.get_idDomanda());
			riga.put(2, domanda.get_idDomandaAgea());
			riga.put(3, domanda.get_campagna());
			riga.put(4, domanda.get_misura());
			righe.add(riga);
		}
		
		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}

	public void set_elencoSottoint(ElencoSottointerventiBO _elencoSottoint) {
		this._elencoSottoint = _elencoSottoint;
	}

	public ElencoSottointerventiBO get_elencoSottoint() {
		return _elencoSottoint;
	}
	
	
    /**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList()
	{
		elencoDomande = new ElencoDomandeBO();
	}
	
	/***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	
        
    /**
     * sulla base dei parametri di ricerca inseriti imposta i parametri nella query di ricerca
     * Imposta solo i parametri di ricerca per azeinda e domanda, no campione
     * @param parametersList
     * @param q
     */
	private void setQueryParameter(HashMap<String, String> parametersList, Query q) {
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
			q.setParameter("misura", parametersList.get("misura") );
//			q.setParameter("misura", "%" + parametersList.get("misura") + "%"); // commentato perchè deve cercare la stringa esatta e non un like
		else 
			q.setParameter("misura", "%");
		
		if(parametersList.containsKey("campagna") && !parametersList.get("campagna").equals("%"))
			q.setParameter("campagna", "%" + parametersList.get("campagna") + "%");
		else 
			q.setParameter("campagna", "%");
	}

	
	/**
     * prepara la lista di tutte le intestazioni delle colonne presenti nel file
     * @return LinkedHashMap<Integer, String>
     */
    private static LinkedHashMap<Integer, String> getListaIntestazioni()
    {
    	LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
    	intestazione.put(0, COL_CUAA);
		intestazione.put(1, COL_DOMANDA_OPR);
		intestazione.put(2, COL_DOMANDA_AGEA);
		intestazione.put(3, COL_CAMPAGNA);
		intestazione.put(4, COL_MISURA);
    	return intestazione;
    }
    
    
}
