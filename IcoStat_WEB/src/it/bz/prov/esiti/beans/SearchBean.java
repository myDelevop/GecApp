package it.bz.prov.esiti.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;

import org.primefaces.event.SelectEvent;

import it.bz.prov.esiti.bobject.AziendaBO;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.IAzienda;
import it.bz.prov.esiti.util.Costanti;


/**
 * Bean per la gestione del pannello di ricerca
 * @author bpettazzoni
 *
 */
@SuppressWarnings("unchecked")
@ManagedBean(name = "searchBean")
@SessionScoped
public class SearchBean {
	private String cuaa = "";
	private String campagna = "";
	private String ragioneSociale = "";
	private String campione = "";
	private String numeroDomanda = "";
	private String misura = "";
	private String segnalazione = "";
	private List<String> campioneValues;
	private List<String> cuaaValues;
	private List<String> ragioneSocialeValues;
	private List<String> misuraValues;
	private List<String> campagnaValues;
	private List<String> segnalazioneValues;
	private String _messageData = "";
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	@EJB
	private IAzienda _aziendaBean;
	
	/**
     * costruttore
     */
    public SearchBean()
    {    	
    	setCuaa("");
    	setCampagna("");
    	setRagioneSociale("");
    	setCampione("");
    	setNumeroDomanda("");
    	setMisura("");
    	setSegnalazione("");
    	// imposto il messaggio di default da visualizzare nella tabella quando si deve effettuare un filtraggio per vedere i dati
    	_messageData = Costanti.MSG_NO_DATA_DEFAULT;
    }
    
    @PostConstruct
    private void init() {
    	// inizializzazione di tutti i possibili valori della combo box    	
    	cuaaValues = new ArrayList<String>();
    	ragioneSocialeValues = new ArrayList<String>();
    	campioneValues = getListaValori(Costanti.ANAGR_CAMPIONE);
    	misuraValues = getListMisura();
    	campagnaValues = getListCampagna();
    	segnalazioneValues = getListaValori(Costanti.ANAGR_SEGNALAZIONE);    	
    }
	
	/**
     * gestione dell'operazione di ricerca
     * @return String
     * @throws IOException
     * @throws ServletException
     */
    public String doSearch()
    {
    	// controllo che tutti i paramatri non siano null
    	if(cuaa.equals("") && ragioneSociale.equals("") && numeroDomanda.equals("") &&
    			misura.equals("") && campagna.equals("") && campione.equals("") && segnalazione.equals(""))
    	{
    		return "";
    	}
    	
    	setParametersForSearch();
    	
    	HashMap<String, String> parametersList = new HashMap<String, String>();
    	parametersList.put(Costanti.SEARCH_CUAA, this.getCuaa().trim());
    	parametersList.put(Costanti.SEARCH_RAG_SOC, this.getRagioneSociale().trim());
    	parametersList.put(Costanti.SEARCH_DOMANDA, this.getNumeroDomanda().trim());
    	parametersList.put(Costanti.SEARCH_MISURA, this.getMisura());
    	parametersList.put(Costanti.SEARCH_CAMPAGNA, this.getCampagna());
    	parametersList.put(Costanti.SEARCH_CAMPIONE, this.getCampione());
    	parametersList.put(Costanti.SEARCH_SEGNALAZIONE, this.getSegnalazione()); 
    	
    	FacesContext context = FacesContext.getCurrentInstance();
	    TPBean tpBean = (TPBean) context.getApplication().evaluateExpressionGet(context, "#{TPBean}", Object.class);
	    int indexTab = tpBean.getSelectedIndex();
	    switch(indexTab)
	    {
	    	case TPBean.TAB_AZIENDA: 
	    	{
	    		AziendaUIBean aziendaBean = (AziendaUIBean) context.getApplication().evaluateExpressionGet(context, "#{aziendaBean}", Object.class);
	    		aziendaBean.filter(parametersList);
	    		break;	
	    	}
	    	case TPBean.TAB_DOMANDA:
	    	{
	    		DomandaUIBean domandaBean = (DomandaUIBean) context.getApplication().evaluateExpressionGet(context, "#{domandaBean}", Object.class);
	    		domandaBean.filter(parametersList);
	    		break;
	    	}
	    	case TPBean.TAB_CAMPIONE:
	    	{
	    		CampioneUIBean campioneBean = (CampioneUIBean) context.getApplication().evaluateExpressionGet(context, "#{campioneBean}", Object.class);
	    		campioneBean.filter(parametersList);
	    		ControlloUIBean controlloBean = (ControlloUIBean) context.getApplication().evaluateExpressionGet(context, "#{controlloBean}", Object.class);
	    		controlloBean.filter(parametersList);
	    		break;
	    	}
	    	case TPBean.TAB_CONDIZIONALITA:
	    	{
	    		CondizionalitaUIBean condBean = (CondizionalitaUIBean) context.getApplication().evaluateExpressionGet(context, "#{esitoCondBean}", Object.class);
	    		
	    		if(condBean.get_datiDaVisualizzare().equals(Costanti.DATI_COND_ESITI))
	    		{
	    			condBean.filter(parametersList);
	    		}
	    		else if(condBean.get_datiDaVisualizzare().equals(Costanti.DATI_COND_MATR_AMM_IDEALE))
	    		{
	    			MatrAmmIdealeUIBean matrAmmBean = (MatrAmmIdealeUIBean)context.getApplication().evaluateExpressionGet(context, "#{matrAmmIdealeBean}", Object.class);
	    			matrAmmBean.filter(parametersList);
	    		}
	    		break;
	    	}
	    	case TPBean.TAB_IMPEGNI:
	    	{
	    		EsitoImpegniUIBean esitoImpegniBean = (EsitoImpegniUIBean) context.getApplication().evaluateExpressionGet(context, "#{esitoImpegniBean}", Object.class);
	    		EsitoImpegniExtraUIBean esitoImpegniExtraBean = (EsitoImpegniExtraUIBean) context.getApplication().evaluateExpressionGet(context, "#{esitoImpegniExtraBean}", Object.class);
	    		esitoImpegniBean.filter(parametersList);
	    		esitoImpegniExtraBean.filter(parametersList);
	    		break;
	    	}
	    	case TPBean.TAB_RMFITFER:{
	    		EsitoImpegniRMFITFERUIBean esitoImpegniRMFITFERBean = (EsitoImpegniRMFITFERUIBean) context.getApplication().evaluateExpressionGet(context, "#{esitoImpegniRMFITFERBean}", Object.class);
	    		esitoImpegniRMFITFERBean.filter(parametersList);
	    		EsitoImpegniRMFITFERExtraUIBean esitoImpegniRMFITFERExtraBean = (EsitoImpegniRMFITFERExtraUIBean) context.getApplication().evaluateExpressionGet(context, "#{esitoImpegniRMFITFERExtraBean}", Object.class);
	    		esitoImpegniRMFITFERExtraBean.filter(parametersList);
	    		break;
	    	}
	    	case TPBean.TAB_SUPERFICI:
	    	{
	    		EsitoSuperficiUIBean esitoSupBean = (EsitoSuperficiUIBean) context.getApplication().evaluateExpressionGet(context, "#{esitoSupBean}", Object.class);
	    		esitoSupBean.filter(parametersList);
	    		break;
	    	}
	    	case TPBean.TAB_UBA:
	    	{
	    		UbaUIBean ubaBean = (UbaUIBean) context.getApplication().evaluateExpressionGet(context, "#{ubaBean}", Object.class);
	    		ubaBean.filter(parametersList);
	    		UBAAmmissibilitaUIBean ubaAmmBean = (UBAAmmissibilitaUIBean) context.getApplication().evaluateExpressionGet(context, "#{ubaAmmBean}", Object.class);
	    		ubaAmmBean.filter(parametersList);
	    		break;
	    	}
	    	case TPBean.TAB_TUTELA_ACQUE:
	    	{
	    		TutelaAcqueUIBean tutelaAcqueBean = (TutelaAcqueUIBean) context.getApplication().evaluateExpressionGet(context, "#{tutelaAcqueBean}", Object.class);
	    		tutelaAcqueBean.filter(parametersList);
	    		break;
	    	}
	    	case TPBean.TAB_ZOOTECNIA:
	    	{
	    		EsitoZootecniaUIBean esitoZootBean = (EsitoZootecniaUIBean) context.getApplication().evaluateExpressionGet(context, "#{esitoZootBean}", Object.class);
	    		esitoZootBean.filter(parametersList);
	    		break;
	    	}
	    	//tab eliminata
//	    	case TPBean.TAB_ART68:
//	    	{
//	    		EsitoArt68UIBean esitoArt68Bean = (EsitoArt68UIBean) context.getApplication().evaluateExpressionGet(context, "#{esitoArt68Bean}", Object.class);
//	    		esitoArt68Bean.filter(parametersList);
//	    		break;
//	    	}
	    	default: break;
	    }
	    
	    // imposto il messaggio di default da visualizzare nell'oggetto tabella quando non ci sono i dati
	    _messageData = Costanti.MSG_NO_DATA_FIND;
    	clearEmptyParameters();
        return null;
    	
    }
    
    /**
     * Metodo che restituisce se ci sono parametri di ricerca attivi nella maschera
     * @return
     */
    public boolean isSearchActive()
    {
    	if(getCuaa().equals("") && getRagioneSociale().equals("") && getCampagna().equals("")
    			&& getCampione().equals("") && getMisura().equals("") && getNumeroDomanda().equals("")
    			&& getSegnalazione().equals(""))
    		return false;
    	else return true;
    }
    
    /**
     * gestione dell'operazione di cancellazione della ricerca  visualizzazione di tutti i risultati
     * @throws IOException
     * @throws ServletException
     */
    public void viewAll() throws IOException, ServletException
    {
    	cuaa = "";
    	ragioneSociale = "";
    	numeroDomanda = "";
    	misura = "";
    	campagna = "";
    	campione = "";
    	segnalazione = "";    	
//    	doSearch();
    	// imposto il messaggio di default da visualizzare nell'oggetto tabella quando si deve effettuare un filtraggio per vedere i dati
    	_messageData = Costanti.MSG_NO_DATA_DEFAULT;
    	// cancellazione del contenuto delle tabelle nel tab visualizzato
    	FacesContext context = FacesContext.getCurrentInstance();
	    TPBean tpBean = (TPBean) context.getApplication().evaluateExpressionGet(context, "#{TPBean}", Object.class);
    	tpBean.clearDataVisualized();
    }
    
    /**
     * imposta i parametri con il % per la ricerca in caso di valori nulli
     */
    private void setParametersForSearch(){
    	if(cuaa.equals("")) setCuaa("%");
    	if(campagna.equals("")) setCampagna("%");
    	if(ragioneSociale.equals("")) setRagioneSociale("%");
    	if(campione.equals("")) setCampione("%");
    	if(numeroDomanda.equals("")) setNumeroDomanda("%");
    	if(misura.equals("")) setMisura("%");
    	if(segnalazione.equals("")) setSegnalazione("%");
    }
    
    /**
     * pulisce i parametri impostati con il % per la ricerca in caso di valori nulli
     */
    private void clearEmptyParameters(){
    	if(cuaa.equals("%")) setCuaa("");
    	if(campagna.equals("%")) setCampagna("");
    	if(ragioneSociale.equals("%")) setRagioneSociale("");
    	if(campione.equals("%")) setCampione("");
    	if(numeroDomanda.equals("%")) setNumeroDomanda("");
    	if(misura.equals("%")) setMisura("");
    	if(segnalazione.equals("%")) setSegnalazione("");
    }
  
    public String getCuaa()
    {
        return this.cuaa != null ? this.cuaa : "";
    }
    
    public void setCuaa(final String cuaa)
    {
        this.cuaa = cuaa != null ? cuaa : "";
    }
    
    public void setCUAAFromValues(SelectEvent<String> e) {
    	String cuaa = e.getObject();
    	if(cuaa != null)
    		setCuaa(cuaa);
    }
    
    public String getCampagna()
    {
        return this.campagna;
    }

    public void setCampagna(final String campagna)
    {
        this.campagna = campagna;
    }
    
    public String getRagioneSociale()
    {
        return this.ragioneSociale;
    }

    public void setRagioneSociale(final String ragioneSociale)
    {
        this.ragioneSociale = ragioneSociale != null ? ragioneSociale : "";
    }
    
    public void setRagioneSocialeFromValues(SelectEvent<String> e) {
    	String ragioneSociale = e.getObject();
    	if(ragioneSociale != null)
    		setRagioneSociale(ragioneSociale);
    }
    
    public String getCampione()
    {
        return this.campione;
    }

    public void setCampione(final String campione)
    {
        this.campione = campione;
    }

    public String getNumeroDomanda()
    {
        return this.numeroDomanda;
    }

    public void setNumeroDomanda(final String numeroDomanda)
    {
        this.numeroDomanda = numeroDomanda;
    }
    
    public String getMisura()
    {
        return this.misura;
    }

    public void setMisura(final String misura)
    {
        this.misura = misura;
    }
    
    public String getSegnalazione()
    {
        return this.segnalazione;
    }
    
    public void setSegnalazione(final String segnalazione)
    {
        this.segnalazione = segnalazione;
    }
    
    public List<String> getCampioneValues()
    {
        return this.campioneValues;
    }
    
    public void setCampioneValues(final List<String> campioneValues)
    {
        this.campioneValues = campioneValues;
    }
    
    /**
	 * restituisce la lista dei valori distinti per l'anagrafica richiesta
	 * @param nomeAnagrafica
	 * @return List<String>
	 */
	private List<String> getListaValori(String nomeAnagrafica)
	{
		Query q = _em.createNamedQuery("ValoreAnagrafica.selectAnagrafica");
		q.setParameter("campo", nomeAnagrafica);
		List<ValoreAnagrafica> fornitura = (ArrayList<ValoreAnagrafica>) q.getResultList();
		ArrayList<String> lista = new ArrayList<String>();
		for (ValoreAnagrafica valoreAnagrafica : fornitura) {
			lista.add(valoreAnagrafica.get_valore());
		}
		lista.add(0, "");
		return lista;
	}


	public void setMisuraValues(List<String> misuraValues) {
		this.misuraValues = misuraValues;
	}


	public List<String> getMisuraValues() {
		return misuraValues;
	}


	public void setCampagnaValues(List<String> campagnaValues) {
		this.campagnaValues = campagnaValues;
	}


	public List<String> getCampagnaValues() {
		return campagnaValues;
	}
	
	/**
	 * restituisce la lista di tutte le campagne
	 * @return List<String>
	 */
	private List<String> getListCampagna()
	{
		Query q = _em.createNamedQuery("Domanda.distinctCampagna");
		List<String> fornitura = (ArrayList<String>) q.getResultList();
		fornitura.add(0, "");
		return fornitura;
	}

	/**
	 * restituisce la lista di tutte le misure
	 * @return List<String>
	 */
	private List<String> getListMisura()
	{
		
		Query q = _em.createNamedQuery("Domanda.distinctMisura");
		List<String> fornitura = (ArrayList<String>) q.getResultList();
		fornitura.add(0, "");
		return fornitura;
	}


	public void setSegnalazioneValues(List<String> segnalazioneValues) {
		this.segnalazioneValues = segnalazioneValues;
	}


	public List<String> getSegnalazioneValues() {
		return segnalazioneValues;
	}


	public void set_messageData(String _messageData) {
		this._messageData = _messageData;
	}


	public String get_messageData() {
		return _messageData;
	}

	 public List<String> getCuaaValues() {
		return cuaaValues;
	}

	public List<String> getCuaaValues(String q) {
		List<AziendaBO> ris = _aziendaBean.getListaAziendeByPartialCUAA(q);
		this.cuaaValues.clear();
		for(AziendaBO a : ris) this.cuaaValues.add(a.get_cuaa());
		return this.cuaaValues;
	}
		
	public void setCuaaValues(List<String> cuaaValues) {
		this.cuaaValues = cuaaValues;
	}

	public List<String> getRagioneSocialeValues() {
		return ragioneSocialeValues;
	}

	public List<String> getRagioneSocialeValues(String q) {
		List<AziendaBO> ris = _aziendaBean.getListaAziendeByPartialDenominazione(q);
		this.ragioneSocialeValues.clear();
		for(AziendaBO a : ris) this.ragioneSocialeValues.add(a.get_ragioneSociale());
		return this.ragioneSocialeValues;
	}
	
	public void setRagioneSocialeValues(List<String> ragioneSocialeValues) {
		this.ragioneSocialeValues = ragioneSocialeValues;
	}
}
