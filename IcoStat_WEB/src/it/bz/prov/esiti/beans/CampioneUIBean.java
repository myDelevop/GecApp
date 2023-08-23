package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.bobject.CampioneBO;
import it.bz.prov.esiti.bobject.ElencoCampioneBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.ibusiness.ICampione;
import it.bz.prov.esiti.util.Costanti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.openfaces.event.AjaxActionEvent;


/**
 * Bean UI (user interface) per la gestione dei campioni
 * 
 * @author bpettazzoni
 *
 */
@ManagedBean(name = "campioneBean")
@SessionScoped
public class CampioneUIBean {
	@EJB
	private ICampione _campioneEJBBean;
	private String _action;
	private int _selectedValueIndex;
	private CampioneBO _selectedValue;
	private CampioneBO _campione;
	private CampioneBO _campioneOld;
	private String _titoloPagina;
	private List<String> _listCampagna;
	private List<String> _listTipoCampione;
	private List<String> _listDominioCond;
	private List<String> _listCategoriaCampione;
	private List<String> _listStatoDomandaOPPAB;
	private List<String> _listOrigineCampione;
	private List<String> _listBovini;
	private List<String> _listOvicaprini;
	private List<String> _listDomande;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	private boolean _disableBoviniOvicaprini;
	private boolean _disableDominioCond;
	private boolean _disableDomanda;
	
	/**
	 * costruttore
	 */
	public CampioneUIBean()
	{
//		_campioneEJBBean = new CampioneBean();
		_listCampagna = new ArrayList<String>();
		_action = Costanti.ACTION_VIEW;
		_selectedValue = new CampioneBO();
		_campione = new CampioneBO();
		_campioneOld = new CampioneBO();
		_titoloPagina = "";
		_listTipoCampione = new ArrayList<String>();
		_listDominioCond = new ArrayList<String>();
		_listCategoriaCampione = new ArrayList<String>();
		_listStatoDomandaOPPAB = new ArrayList<String>();
		_listOrigineCampione = new ArrayList<String>();
		_listBovini = new ArrayList<String>();
		_listOvicaprini = new ArrayList<String>();
		_listDomande = new ArrayList<String>();
		_message = "";
		_messageVisible = false;
		_readOnly = false;
		_disableBoviniOvicaprini= false;
		_disableDominioCond = false;
		_disableDomanda = false;
	}
	
	/**
	 * aggiunge un campione alla lista
	 */
	public void aggiungiCampione() 
	{
		set_action(Costanti.ACTION_INSERT);
		_campione = new CampioneBO();
		_titoloPagina = "Creazione nuova domanda a campione";
		_listDomande = new ArrayList<String>();
		_readOnly = false;
		
		// se nel box di ricerca sono inseriti cuaa o campagna si pre-compila la form di inserimento
		FacesContext context = FacesContext.getCurrentInstance();
		SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
		String cuaa= searchBean.getCuaa();
		String campagna= searchBean.getCampagna();
		// per evitare di prendere un cuaa che é una substring (anche così la stringa potrebbe non 
		//corrispondere a un cuaa, ma é una richiesta di Lars)
		if(cuaa.length() > 10 )	{
			if(campagna.equals("")) _campione.set_cuaa(cuaa);
			if(!campagna.equals("")){
				List<String> result = _campioneEJBBean.getListDomande(cuaa, campagna);
				if(result.size() == 0){
					_message = "Nessuna domanda presente per il CUAA e Campagna indicati nella form di ricerca";
					_messageVisible = true;
				}
				else 
				{
					_listDomande = result;
					_campione.set_cuaa(cuaa);
					_campione.set_campagna(campagna);
				}
			}
		}
		_campione.set_dominioCampCond(Costanti.VAL_CAMP_DOMINIO_CAMP_PER_NO_VET);
	}
	
	/**
	 * modifica un campione alla lista
	 */
	public void modificaCampione()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_domanda().equals("")) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		set_action(Costanti.ACTION_MODIFY);
		if(_selectedValue.get_cuaa().equals("")) return;
		_campione = _selectedValue;
		_campioneOld = _campione.clone();
		_titoloPagina = "Modifica domanda a campione";
		_readOnly = true;
		// setta i flag di abilitazione delle listbox
		selezioneCategoria(null);
		//disabilito la modifica della domanda indipendentemente da quello che viene fatto
		// nel metodo sopra perchè la domanda non deve essere modificabile
		_disableDomanda = true;
	}
	
	/**
	 * cancella un campione dalla lista
	 */
	public void cancellaCampione()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_domanda().equals("")) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_campione = _selectedValue;
		OperationResultBO result = _campioneEJBBean.cancellaCampione(_campione); 
		if(result.get_result()) {
			_campione = new CampioneBO();
			_selectedValue = new CampioneBO();
			_campioneOld = new CampioneBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * salva un campione nella lista
	 */
	public void salvaCampione()
	{
		OperationResultBO result = null;
		if(_action.equals(Costanti.ACTION_INSERT)){
			result = _campioneEJBBean.aggiungiCampione(_campione);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _campioneEJBBean.modificaCampione(_campione);
		}
		if(result.get_result()) _action=Costanti.ACTION_VIEW;
		_message = result.get_message();
		_messageVisible = true;
	}
	
	/**
	 * annulla la creazione di un nuovo campione
	 */
	public void annullaCampione()
	{
		if(_campioneOld != null) _campione.recovery(_campioneOld);
		_action = Costanti.ACTION_VIEW;
		// reset flag
		_disableBoviniOvicaprini= false;
		_disableDominioCond = false;
		_disableDomanda = false;
		
	}
	
	/**
	 * importazione dati
	 */
	public String importaCampione()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UploadUIBean uploadBean = (UploadUIBean) context.getApplication().evaluateExpressionGet(context, "#{uploadBean}", Object.class);
		uploadBean.set_titoloPagina("Campione - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_CAMPIONE);
		uploadBean.set_ejbBean(_campioneEJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";	
	}
	
	
	/**
	 * esportazione dati
	 */
	public void esportaCampione()
	{
		String filename = Costanti.FILE_CAMPIONE;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_campioneEJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	
	/**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new CampioneBO();
		_campioneOld = new CampioneBO();
		_campione = new CampioneBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_campioneEJBBean.clearList();
    }
    
    /**
	 * funzione che setta dei valori in base a cosa si deve visualizzare
	 * @param e
	 */
	public void selezioneDomande(AjaxActionEvent e){
		// se cuaa e campagna non sono entrambi valorizzati non si fa nulla
		if(_campione.get_campagna().equals("") || _campione.get_cuaa().equals("")) return;
		_listDomande = _campioneEJBBean.getListDomande(_campione.get_cuaa(), _campione.get_campagna());		
	}
    
	/**
	 * funzione che setta il refresh del valore dominio di condizionalita
	 * @param e
	 */
	public void refreshDominioCond(AjaxActionEvent e){
	}
	
    /**
	 * funzione che setta dei valori in base a cosa si deve visualizzare
	 * @param e
	 */
	public void selezioneCategoria(AjaxActionEvent e){
		// campi da abilitare solo per AMM ZOOTECNIA
		if(_campione.get_categoria().equals(Costanti.VAL_CAMP_CAT_AMM_ZOOT)) _disableBoviniOvicaprini = false;
		else _disableBoviniOvicaprini = true;
		
		// gestione abilitazione/disabilitazione per condizionalità
		if(_campione.get_categoria().equals(Costanti.VAL_CAMP_CAT_COND_SUP)  ||
				_campione.get_categoria().equals(Costanti.VAL_CAMP_CAT_COND_ZOOT) ||
				_campione.get_categoria().equals(Costanti.VAL_CAMP_CAT_COND_EXTRA) )
		{
			_disableDomanda = true;
			_disableDominioCond = false;
			// Commentata per seganalazione BUG grafico da parte di Rainer Giovannelli in data 04/05/2017 16:08
			//this.popolaDominioCampioneCondSup();
		} 
		else if(_campione.get_categoria() == null || _campione.get_categoria().equals("")) {
			_disableDomanda = false;
			_disableDominioCond = false;
			_disableBoviniOvicaprini= false;
		}
		else {
			_disableDomanda = false;
			_disableDominioCond = true;
			_campione.set_dominioCampCond("");
		}
		
		if(_campione.get_categoria().equals(Costanti.VAL_CAMP_CAT_AMM_IMP)){
			List<String> result = _campioneEJBBean.getListDomandePSR(_campione.get_cuaa(), _campione.get_campagna());
			_listDomande = result;
		}
		
		
	// ROCL valorizzare alcuni campi se si imposta COND EXTRA CAMPIONE o COND ZOOTECNIA
		if(_campione.get_categoria().equals(Costanti.VAL_CAMP_CAT_COND_EXTRA)) {
			_campione.set_tipo(Costanti.VAL_CAMP_TIPO_CAMP_NO_CAMPIONE);
			_campione.set_dominioCampCond(Costanti.VAL_CAMP_DOMINIO_CAMP_NO_CAMPIONE);
		} else if (_campione.get_categoria().equals(Costanti.VAL_CAMP_CAT_COND_ZOOT)) {
			_campione.set_statoDomandaOPPAB(Costanti.VAL_CAMP_STATO_DOMANDA_OPPAB_A_CAMPIONE);
			_campione.set_origineCampione(Costanti.VAL_CAMP_ORIGINE_CAMP_SEG_VET);
			_campione.set_dominioCampCond(Costanti.VAL_CAMP_DOMINIO_CAMP_PER_NO_VET);			
		}
	}
	
    /**
	 * funzione che setta dei valori in base a cosa si deve visualizzare
	 * 
	 */
	public void selezioneOrigineCampione(AjaxActionEvent e){
		// Commentata per seganalazione BUG grafico da parte di Rainer Giovannelli in data 04/05/2017 16:08
		if(_campione.get_categoria().equals(Costanti.VAL_CAMP_CAT_COND_SUP) && 
				(_campione.get_origineCampione().equals(Costanti.VAL_CAMP_ORIG_DA_ROMA) || 
						_campione.get_origineCampione().equals(Costanti.VAL_CAMP_ORIG_AGG) || 
						_campione.get_origineCampione() == null)
			&& _campione.get_campagna() != null 
			&& !_campione.get_campagna().equals("") 
			&& _campione.get_cuaa() != null
			&& !_campione.get_cuaa().equals("")
				){
			_campione.set_dominioCampCond(Costanti.VAL_CAMP_DOMINIO_CAMP_PER_NO_VET);
		}
		if(_campione.get_origineCampione() != null && _campione.get_origineCampione().equals(Costanti.VAL_CAMP_ORIG_CAMP_VET_DA_DU)) {
			_campione.set_dominioCampCond(Costanti.VAL_CAMP_DOMINIO_CAMP_NO_CAMPIONE);
		}
	}
	
    public List<CampioneBO> getElencoCampione()
    {
        return _campioneEJBBean.getElencoCampione();
    }

    public void setElencoCampione(final ElencoCampioneBO elencoCampione)
    {
        _campioneEJBBean.setElencoCampione(elencoCampione);
    }
    
    /**
     * Carica i dati in tabella
     */
    public void loadData()
    {
    	_campioneEJBBean.loadData();
    }

	public void set_listCampagna(Vector<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	public List<String> get_listCampagna() {
		if(_listCampagna.size()==0)
			_listCampagna = _campioneEJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_action(String _action) {
		this._action = _action;
	}

	public String get_action() {
		return _action;
	}
	
	public void set_selectedValue(CampioneBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public CampioneBO get_selectedValue() {
		return _selectedValue;
	}
	
	public void set_campione(CampioneBO _campione) {
		this._campione = _campione;
	}

	public CampioneBO get_campione() {
		return _campione;
	}

	public void set_titoloPagina(String _titoloPagina) {
		this._titoloPagina = _titoloPagina;
	}

	public String get_titoloPagina() {
		return _titoloPagina;
	}
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
    public void filter(HashMap<String, String> parametersList){
    	_campioneEJBBean.filter(parametersList);
    }

	public void set_listTipoCampione(List<String> _listTipoCampione) {
		this._listTipoCampione = _listTipoCampione;
	}

	public List<String> get_listTipoCampione() {
		_listTipoCampione = _campioneEJBBean.getListaValori(Costanti.ANAGR_TIPO_CAMPIONE);
		return _listTipoCampione;
	}

	public void set_messageVisible(boolean _messageVisible) {
		this._messageVisible = _messageVisible;
	}

	public boolean is_messageVisible() {
		return _messageVisible;
	}

	public void set_message(String _message) {
		this._message = _message;
	}

	public String get_message() {
		return _message;
	}

	public void set_selectedValueIndex(int _selectedValueIndex) {
		this._selectedValueIndex = _selectedValueIndex;
	}

	public int get_selectedValueIndex() {
		return _selectedValueIndex;
	}

	public void set_listDominioCond(List<String> _listDominioCond) {
		this._listDominioCond = _listDominioCond;
	}

	public List<String> get_listDominioCond() {
		_listDominioCond = _campioneEJBBean.getListaValori(Costanti.ANAGR_DOMINIO_CAMPIONE);
		return _listDominioCond;
	}

	public void set_readOnly(boolean _readOnly) {
		this._readOnly = _readOnly;
	}

	public boolean is_readOnly() {
		return _readOnly;
	}

	public void set_listCategoriaCampione(List<String> _listCategoriaCampione) {
		this._listCategoriaCampione = _listCategoriaCampione;
	}

	public List<String> get_listCategoriaCampione() {
		_listCategoriaCampione = _campioneEJBBean.getListaValori(Costanti.ANAGR_CAMP_CATEGORIA);
		return _listCategoriaCampione;
	}

	public void set_listStatoDomandaOPPAB(List<String> _listStatoDomandaOPPAB) {
		this._listStatoDomandaOPPAB = _listStatoDomandaOPPAB;
	}

	public List<String> get_listStatoDomandaOPPAB() {
		_listStatoDomandaOPPAB = _campioneEJBBean.getListaValori(Costanti.ANAGR_CAMP_STATO_DOMANDA_OPPAB);
		return _listStatoDomandaOPPAB;
	}

	public void set_listOrigineCampione(List<String> _listOrigineCampione) {
		this._listOrigineCampione = _listOrigineCampione;
	}

	public List<String> get_listOrigineCampione() {
		_listOrigineCampione = _campioneEJBBean.getListaValori(Costanti.ANAGR_CAMP_ORIGINE);
		return _listOrigineCampione;
	}

	public void set_listBovini(List<String> _listBovini) {
		this._listBovini = _listBovini;
	}

	public List<String> get_listBovini() {
		_listBovini = _campioneEJBBean.getListaValori(Costanti.ANAGR_CAMP_BOVINI);
		return _listBovini;
	}

	public void set_listOvicaprini(List<String> _listOvicaprini) {
		this._listOvicaprini = _listOvicaprini;
	}

	public List<String> get_listOvicaprini() {
		_listOvicaprini = _campioneEJBBean.getListaValori(Costanti.ANAGR_CAMP_OVICAPRINI);
		return _listOvicaprini;
	}

	public void set_disableBoviniOvicaprini(boolean _disableBoviniOvicaprini) {
		this._disableBoviniOvicaprini = _disableBoviniOvicaprini;
	}

	public boolean is_disableBoviniOvicaprini() {
		return _disableBoviniOvicaprini;
	}

	public void set_disableDominioCond(boolean _disableDominioCond) {
		this._disableDominioCond = _disableDominioCond;
	}

	public boolean is_disableDominioCond() {
		return _disableDominioCond;
	}

	public void set_disableDomanda(boolean _disableDomanda) {
		this._disableDomanda = _disableDomanda;
	}

	public boolean is_disableDomanda() {
		return _disableDomanda;
	}

	public void set_listDomande(List<String> _listDomande) {
		this._listDomande = _listDomande;
	}

	public List<String> get_listDomande() {
		return _listDomande;
	}

}
