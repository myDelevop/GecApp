package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.bobject.ElencoEsitoSuperficiBO;
import it.bz.prov.esiti.bobject.EsitoSuperficiBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.ibusiness.IEsitoSuperfici;
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
import javax.faces.model.SelectItem;

import org.openfaces.event.AjaxActionEvent;

/**
 * Bean UI (user interface) per la gestione degli esiti a superficie
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "esitoSupBean")
@SessionScoped
public class EsitoSuperficiUIBean {
	@EJB
	private IEsitoSuperfici _esitoSuperficiEJBBean;
	private EsitoSuperficiBO _esitoSuperfici;
	private EsitoSuperficiBO _esitoSuperficiOld; // serve per memorizzare la versione originale durante la modifica
	private EsitoSuperficiBO _selectedValue;
	private String _action;
	private int _selectedValueIndex;
	private List<String> _listCampagna;
	private List<String> _listStatoAzione;
	private List<SelectItem> _listSanzioneAnnullata;
	@SuppressWarnings("unused")
	private List<String> _listEsito;
	private List<SelectItem> _listCartellino_Giallo;
	private List<String> _listInterventi;
	private List<String> _listSottointerventi;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	
	
	/**
	 * costruttore
	 */
	public EsitoSuperficiUIBean()
	{
//		_esitoSuperficiEJBBean = new EsitoSuperficiBean();
		_action = Costanti.ACTION_VIEW;
		_esitoSuperfici = new EsitoSuperficiBO();
		_selectedValue = new EsitoSuperficiBO();
		_listCampagna = new Vector<String>();
		_listStatoAzione = new ArrayList<String>();
		_esitoSuperficiOld = new EsitoSuperficiBO();
		_titoloPagina = "";
		_message = "";
		_messageVisible = false;
		_listSanzioneAnnullata = new ArrayList<SelectItem>();
		_listInterventi = new ArrayList<String>();
		_listSottointerventi = new ArrayList<String>();
		_listEsito = new ArrayList<String>();
		_listCartellino_Giallo = new ArrayList<SelectItem>();

		_listSanzioneAnnullata.add(new SelectItem("SI","Si"));
		_listSanzioneAnnullata.add(new SelectItem("NO","No"));
		
		_listCartellino_Giallo.add(new SelectItem("SI","Si"));
		_listCartellino_Giallo.add(new SelectItem("NO","No"));

	}
	
	/**
	 * aggiunge un esito alla lista
	 */
	public void aggiungiEsito()
	{
		_action = Costanti.ACTION_INSERT;
		_esitoSuperfici = new EsitoSuperficiBO();
		_titoloPagina = "Creazione nuovo esito superfici";
		_readOnly = false;
		
		// se nel box di ricerca sono inseriti cuaa o campagna si pre-compila la form di inserimento
		FacesContext context = FacesContext.getCurrentInstance();
		SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
		String cuaa= searchBean.getCuaa();
		String campagna= searchBean.getCampagna();
		// per evitare di prendere un cuaa che é una substring (anche così la stringa potrebbe non 
		//corrispondere a un cuaa, ma é una richiesta di Lars)
		if(cuaa.length() > 10 )	{
			if(campagna.equals("")) _esitoSuperfici.set_cuaa(cuaa);
			if(!campagna.equals("")){
				List<String> result = _esitoSuperficiEJBBean.getListDomande(cuaa, campagna);
				if(result.size() == 0){
					_message = "Nessuna domanda presente per il CUAA e Campagna indicati nella form di ricerca";
					_messageVisible = true;
				}
				else 
				{
					_esitoSuperfici.set_cuaa(cuaa);
					_esitoSuperfici.set_campagna(campagna);
				}
			}
		}
	}
	
	/**
	 * modifica un esito alla lista
	 */
	public void modificaEsito()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_domanda().equals("") || _selectedValueIndex == -1) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_action = Costanti.ACTION_MODIFY;
		if(_selectedValue.get_cuaa().equals("")) return;
		_selectedValue = _esitoSuperficiEJBBean.getElencoEsitoSuperfici().get(_selectedValueIndex);
		_esitoSuperfici = _selectedValue;
		_esitoSuperficiOld = _esitoSuperfici.clone();
		_titoloPagina = "Modifica esito superfici";
		_readOnly = true;
	}
	
	/**
	 * cancella un esito dalla lista
	 */
	public void cancellaEsito()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_domanda().equals("") || _selectedValueIndex == -1) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_selectedValue = _esitoSuperficiEJBBean.getElencoEsitoSuperfici().get(_selectedValueIndex);
		_esitoSuperfici = _selectedValue;
		OperationResultBO result = _esitoSuperficiEJBBean.cancellaEsito(_esitoSuperfici);
		if(result.get_result()) {
			_esitoSuperfici = new EsitoSuperficiBO();
			_selectedValue = new EsitoSuperficiBO();
			_esitoSuperficiOld = new EsitoSuperficiBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	public String importaEsito()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UploadUIBean uploadBean = (UploadUIBean) context.getApplication().evaluateExpressionGet(context, "#{uploadBean}", Object.class);
		uploadBean.set_titoloPagina("Esito Superfici - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_SUP);
		uploadBean.set_ejbBean(_esitoSuperficiEJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";
	}
	
	/**
	 * esporta i dati
	 */
	public void esportaEsito()
	{
		String filename = Costanti.FILE_ESITO_SUPERFICI;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_esitoSuperficiEJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	
	/**
	 * salva un esito nella lista
	 */
	public void salvaEsito()
	{
		OperationResultBO result = null;
		if(_action.equals(Costanti.ACTION_INSERT)){
			result = _esitoSuperficiEJBBean.aggiungiEsito(_esitoSuperfici);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _esitoSuperficiEJBBean.modificaEsito(_esitoSuperfici);
		}
		if(result.get_result()) _action= Costanti.ACTION_VIEW;
		_message = result.get_message();
		_messageVisible = true;
	}
	
	/**
	 * annulla la creazione di un nuovo esito
	 */
	public void annullaEsito()
	{
		if(_esitoSuperficiOld != null) _esitoSuperfici.recovery(_esitoSuperficiOld);
		_action =Costanti.ACTION_VIEW;
	}
	
	
	/**
	 * funzione che setta l'intervento a partire dalla campagna
	 * @param e
	 */
	public void settingIntervento(AjaxActionEvent e){
		String campagna = _esitoSuperfici.get_campagna();
		_listInterventi = _esitoSuperficiEJBBean.getListIntervento(campagna);

	}
	
	/**
	 * funzione che setta il sottointervento a partire dalla campagna
	 * @param e
	 */
	public void settingSottoIntervento(AjaxActionEvent e){
		String campagna = _esitoSuperfici.get_campagna();
		_listSottointerventi = _esitoSuperficiEJBBean.getListSottointervento(campagna);

	}
	
	/**
	 * funzione che setta la misura a partire dall'intervento
	 * @param e
	 */
	public void settingMisura(AjaxActionEvent e){
		int index = _esitoSuperfici.get_intervento().indexOf('.');
		if(index != -1)
			_esitoSuperfici.set_misura(_esitoSuperfici.get_intervento().substring(0, index));
		else {
			if(		_esitoSuperfici.get_intervento().equals("BPS1") ||
					_esitoSuperfici.get_intervento().equals("026") ||
					_esitoSuperfici.get_intervento().equals("027") ||
					_esitoSuperfici.get_intervento().equals("008") ||
					_esitoSuperfici.get_intervento().equals("300") ||
					_esitoSuperfici.get_intervento().equals("310") ||
					_esitoSuperfici.get_intervento().equals("311") ||
					_esitoSuperfici.get_intervento().equals("312") ||
					_esitoSuperfici.get_intervento().equals("313") ||
					_esitoSuperfici.get_intervento().equals("315") ||
					_esitoSuperfici.get_intervento().equals("316") ||
					_esitoSuperfici.get_intervento().equals("318") ||
					_esitoSuperfici.get_intervento().equals("320") ||
					_esitoSuperfici.get_intervento().equals("321") ||
					_esitoSuperfici.get_intervento().equals("322") 
					)
				_esitoSuperfici.set_misura("DU");
			else
				_esitoSuperfici.set_misura(_esitoSuperfici.get_intervento());
		}
	}
	
	/**
	 * funzione che setta il refresh del valore della domanda in _esitoImpegni
	 * @param e
	 */
	public void settingDomanda(AjaxActionEvent e){
	}
	
	/**
	 * funzione che setta il sottointervento per la 10.1 e la 10.3
	 * @param e
	 */
	public void settingSottoInterventoSelectIntervento(AjaxActionEvent e){
		if(_esitoSuperfici.get_intervento().equals("10.1")) {
			if(_esitoSuperficiEJBBean.hasSottointervento(_esitoSuperfici.get_domanda(), _esitoSuperfici.get_intervento(), "10.1/b")) {
				_esitoSuperfici.set_sottointervento("10.1/b");
			} else {
				_esitoSuperfici.set_sottointervento("10.1/a");
			}
			return;
		}
		if(_esitoSuperfici.get_intervento().equals("10.3")) {
			if(_esitoSuperficiEJBBean.hasSottointervento(_esitoSuperfici.get_domanda(), _esitoSuperfici.get_intervento(), "10.3/b")) {
				_esitoSuperfici.set_sottointervento("10.3/b");
			} else {
				_esitoSuperfici.set_sottointervento("10.3/a");
			}
			return;
		}
	}
	
    public List<EsitoSuperficiBO> getElencoEsitoSuperfici()
    {
        return _esitoSuperficiEJBBean.getElencoEsitoSuperfici();
    }

    public void setElencoEsitoSuperfici(final ElencoEsitoSuperficiBO elencoEsitoSup)
    {
        _esitoSuperficiEJBBean.setElencoEsitoSuperfici(elencoEsitoSup);
    }
    
    /**
	 * carica i dati degli esiti superficie 
	 */
	public void loadData()
	{
		_esitoSuperficiEJBBean.loadData();
	}

	/**
	 * @param _esitoSuperfici the _esitoSuperfici to set
	 */
	public void set_esitoSuperfici(EsitoSuperficiBO _esitoSuperfici) {
		this._esitoSuperfici = _esitoSuperfici;
	}

	/**
	 * @return the _esitoSuperfici
	 */
	public EsitoSuperficiBO get_esitoSuperfici() {
		return _esitoSuperfici;
	}

	/**
	 * @param _selectedValue the _selectedValue to set
	 */
	public void set_selectedValue(EsitoSuperficiBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	/**
	 * @return the _selectedValue
	 */
	public EsitoSuperficiBO get_selectedValue() {
		return _selectedValue;
	}

	/**
	 * @param _action the _action to set
	 */
	public void set_action(String _action) {
		this._action = _action;
	}

	/**
	 * @return the _action
	 */
	public String get_action() {
		return _action;
	}

	/**
	 * @param _listCampagna the _listCampagna to set
	 */
	public void set_listCampagna(Vector<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	/**
	 * @return the _listCampagna
	 */
	public List<String> get_listCampagna() {
		if(_listCampagna.size() ==0 )
			_listCampagna =  _esitoSuperficiEJBBean.getListCampagna();
		return _listCampagna;
	}

	/**
	 * @param _listStato the _listStato to set
	 */
	public void set_listStatoAzione(List<String> _listStatoAzione) {
		this._listStatoAzione = _listStatoAzione;
	}

	/**
	 * @return the _listStato
	 */
	public List<String> get_listStatoAzione() {
		if(_listStatoAzione.size() ==0)
			_listStatoAzione = _esitoSuperficiEJBBean.getListaValori(Costanti.ANAGR_STATO_AZIONE);
		return _listStatoAzione;
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
    	_esitoSuperficiEJBBean.filter(parametersList);
    }
    
    /**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new EsitoSuperficiBO();
		_esitoSuperfici = new EsitoSuperficiBO();
		_esitoSuperficiOld = new EsitoSuperficiBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_esitoSuperficiEJBBean.clearList();
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

	public void set_listSanzioneAnnullata(List<SelectItem> _listSanzioneAnnullata) {
		this._listSanzioneAnnullata = _listSanzioneAnnullata;
	}

	public List<SelectItem> get_listSanzioneAnnullata() {
		return _listSanzioneAnnullata;
	}

	public void set_listInterventi(List<String> _listInterventi) {
		this._listInterventi = _listInterventi;
	}

	public List<String> get_listInterventi() {
		if(_listInterventi.size() == 0) 
			_listInterventi = _esitoSuperficiEJBBean.getListIntervento();
		return _listInterventi;
	}

	public void set_listSottointerventi(List<String> _listSottointerventi) {
		this._listSottointerventi = _listSottointerventi;
	}

	public List<String> get_listSottointerventi() {
		if(_listSottointerventi.size() == 0) 
			_listSottointerventi = _esitoSuperficiEJBBean.getListSottointervento();
		return _listSottointerventi;
	}

	public void set_readOnly(boolean _readOnly) {
		this._readOnly = _readOnly;
	}

	public boolean is_readOnly() {
		return _readOnly;
	}

	public void set_selectedValueIndex(int _selectedValueIndex) {
		this._selectedValueIndex = _selectedValueIndex;
	}

	public int get_selectedValueIndex() {
		return _selectedValueIndex;
	}

	public List<String> get_listEsito() {
		return _listEsito = _esitoSuperficiEJBBean.getListaValori(Costanti.ANAGR_ESITO);
	}

	public void set_listEsito(List<String> _listEsito) {
		this._listEsito = _listEsito;
	}

	public List<SelectItem> get_listCartellino_Giallo() {
		return _listCartellino_Giallo;
	}

	public void set_listCartellino_Giallo(List<SelectItem> _listCartellino_Giallo) {
		this._listCartellino_Giallo = _listCartellino_Giallo;
	}

}
