package it.bz.prov.esiti.beans;

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

import it.bz.prov.esiti.bobject.ElencoUBAAmmissibilitaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.UBAAmmissibilitaBO;
import it.bz.prov.esiti.ibusiness.IUBAAmmissibilita;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

@ManagedBean(name = "ubaAmmBean")
@SessionScoped
public class UBAAmmissibilitaUIBean {

	@EJB
	private IUBAAmmissibilita _ubaAmmEJBBean;
	private String _action;
	private UBAAmmissibilitaBO _uba;
	private UBAAmmissibilitaBO _ubaOld; // serve per memorizzare la versione originale durante la modifica
	private List<String> _listEsito;
	private List<String> _listCampagna;
	private UBAAmmissibilitaBO _selectedValue = null;
	private int _selectedIndex;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	private List<String> _listStato;
	private boolean _disablePercRid;
	
	public UBAAmmissibilitaUIBean() {
		_action = Costanti.ACTION_VIEW;
		_listCampagna = new Vector<String>();
		_listEsito = new ArrayList<String>();
		_listStato = new ArrayList<String>();
		_selectedValue = new UBAAmmissibilitaBO();
		_ubaOld = new UBAAmmissibilitaBO();
		_titoloPagina= "";
		_message = "";
		_messageVisible = false;
		_readOnly = false;
		_disablePercRid = false;
	}
	
	/**
	 * aggiunge una uba alla lista
	 */
	public void aggiungiUba()
	{
		_action = Costanti.ACTION_INSERT;
		_titoloPagina = "Creazione nuovo esito UBA Ammissibilità";
		_uba = new UBAAmmissibilitaBO();
		_uba.set_esito("POSITIVO");
		_uba.set_stato("NON DEFINITIVO");
		_disablePercRid = true;
		_readOnly = false;
	}
	
	/**
	 * modifica un elemento della lista
	 */
	public void modificaUba()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_domanda().equals("") || _selectedIndex == -1) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_action = Costanti.ACTION_MODIFY;
		if(_selectedValue.get_cuaa().equals("")) return;
		_selectedValue = _ubaAmmEJBBean.getElencoUba().get(_selectedIndex);
		_uba = _selectedValue;
		_ubaOld = _uba.clone();
		_titoloPagina = "Modifica esito UBA Ammissibilità";
		_readOnly = true;
	}
	
	/**
	 * cancella un elemento della lista
	 */
	public void cancellaUba()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_domanda().equals("")) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_selectedValue = _ubaAmmEJBBean.getElencoUba().get(_selectedIndex);
		_uba = _selectedValue;
		OperationResultBO result = _ubaAmmEJBBean.cancellaUba(_uba);
		if(result.get_result()) {
			_uba= new UBAAmmissibilitaBO();
			_selectedValue = new UBAAmmissibilitaBO();
			_ubaOld = new UBAAmmissibilitaBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * salva l'oggetto inserito nel database
	 */
	public void salvaUba()
	{
		OperationResultBO result = null;
		if(_action.equals(Costanti.ACTION_INSERT)){
			result = _ubaAmmEJBBean.aggiungiUba(_uba);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _ubaAmmEJBBean.modificaUba(_uba);
		}
		if(result.get_result()) _action= Costanti.ACTION_VIEW;
		_message = result.get_message();
		_messageVisible = true;
	}
	
	/**
	 * annulla la creazione di una nuova uba o la modifica di un esito uba già esistente
	 */
	public void annullaUba()
	{
		if(_ubaOld != null) _uba.recovery(_ubaOld);
		_action = Costanti.ACTION_VIEW;
	}
	
	/**
	 * esporta i dati
	 */
	public void esportaUba()
	{
	    String filename = Costanti.FILE_UBA_AMM;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_ubaAmmEJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	/**
	 * importa i dati
	 */
	public String importaUba() {
		FacesContext context = FacesContext.getCurrentInstance();
		UploadUIBean uploadBean = (UploadUIBean) context.getApplication().evaluateExpressionGet(context, "#{uploadBean}", Object.class);
		uploadBean.set_titoloPagina("UBA Ammissiblità - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_UBA_AMM);
		uploadBean.set_ejbBean(_ubaAmmEJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";	
	}
	
	/**
	 * funzione che calcola la differenza di uba ad ogni modifica dei due valori
	 * @param e
	 */
	public void calcolaDifferenza(AjaxActionEvent e){
		float uba_dich = 0;
		float uba_acc = 0;
		float uba_diff = 0;
		
		try {
			uba_dich = Float.parseFloat(_uba.get_ubaRich());
		} catch (Exception e2) { uba_dich = 0; }

		try {
			uba_acc = Float.parseFloat(_uba.get_ubaAcc());
		} catch (Exception e2) { uba_acc = 0;}
		uba_diff = uba_dich-uba_acc;
		_uba.set_ubaIrreg(Utils.roundNumber(uba_diff));
	}
	
	/**
	 * funzione che setta l'intervento a partire dalla campagna
	 * @param e
	 */
	public void settingIntervento(AjaxActionEvent e){
		int campagna = Integer.parseInt(_uba.get_campagna());
		if(campagna < 2015)
			_uba.set_intervento("214.2");
		else 
			_uba.set_intervento("10.2");
	}
	
	/**
	 * funzione che definisce l'abilitazione grafica delle textbox per l'inserimento delle percentuali di riduzione
	 * in base all'esito selezionato
	 * @param e
	 */
	public void disablePercRid(AjaxActionEvent e){	
		if(_uba.get_esito() != null && _uba.get_esito().equals("POSITIVO")) _disablePercRid= true;
		else _disablePercRid = false; 
		_uba.set_percRid("");
	}
	
    public List<UBAAmmissibilitaBO> getElencoUba()
    {
        return _ubaAmmEJBBean.getElencoUba();
    }

    public void setElencoUba(final ElencoUBAAmmissibilitaBO uba)
    {
    	_ubaAmmEJBBean.setElencoUba(uba);
    }
    
    /**
	 * carica i dati degli esiti UBA 
	 */
	public void loadData()
	{
		_ubaAmmEJBBean.loadData();
	}

	public void set_action(String _action) {
		this._action = _action;
	}

	public String get_action() {
		return _action;
	}

	public void set_uba(UBAAmmissibilitaBO _uba) {
		this._uba = _uba;
	}

	public UBAAmmissibilitaBO get_uba() {
		return _uba;
	}

	public void set_listEsito(List<String> _listEsito) {
		this._listEsito = _listEsito;
	}

	public List<String> get_listEsito() {
		if(_listEsito == null || _listEsito.size()==0)
			_listEsito = _ubaAmmEJBBean.getListaValoriEsito();
		return _listEsito;
	}

	public void set_listCampagna(Vector<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	public List<String> get_listCampagna() {
		if(_listCampagna.size()==0)
			_listCampagna = _ubaAmmEJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_selectedValue(UBAAmmissibilitaBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public UBAAmmissibilitaBO get_selectedValue() {
		return _selectedValue;
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
    	_ubaAmmEJBBean.filter(parametersList);
    }
    
    /**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new UBAAmmissibilitaBO();
		_uba = new UBAAmmissibilitaBO();
		_ubaOld = new UBAAmmissibilitaBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_ubaAmmEJBBean.clearList();
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

	public void set_readOnly(boolean _readOnly) {
		this._readOnly = _readOnly;
	}

	public boolean is_readOnly() {
		return _readOnly;
	}
	
	public List<String> get_listStato() {
		_listStato = _ubaAmmEJBBean.getListaValori(Costanti.ANAGR_STATO);
		return _listStato;
	}
	
	public void set_ListStato(List<String> _listStato) {
		this._listStato = _listStato;
	}

	public int get_selectedIndex() {
		return _selectedIndex;
	}

	public void set_selectedIndex(int _selectedIndex) {
		this._selectedIndex = _selectedIndex;
	}

	public boolean is_disablePercRid() {
		return _disablePercRid;
	}

	public void set_disablePercRid(boolean _disablePercRid) {
		this._disablePercRid = _disablePercRid;
	}
	
	
}
