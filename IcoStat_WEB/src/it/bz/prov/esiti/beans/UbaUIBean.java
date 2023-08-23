package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.bobject.ElencoUbaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.UbaBO;
import it.bz.prov.esiti.ibusiness.IUba;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

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
 * Bean UI (user interface) per la gestione degli esiti uba
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "ubaBean")
@SessionScoped
public class UbaUIBean {
	@EJB
	private IUba _ubaEJBBean;
	private String _action;
	private UbaBO _uba;
	private UbaBO _ubaOld; // serve per memorizzare la versione originale durante la modifica
	private List<String> _listEsito;
	private List<String> _listCampagna;
	private UbaBO _selectedValue = null;
	private int _selectedIndex;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	
	
	
	/**
	 * costruttore
	 */
	public UbaUIBean()
	{
		_action = Costanti.ACTION_VIEW;
		_listCampagna = new Vector<String>();
		_listEsito = new ArrayList<String>();
		_selectedValue = new UbaBO();
		_ubaOld = new UbaBO();
		_titoloPagina= "";
		_message = "";
		_messageVisible = false;
		_readOnly = false;
	}
	
	 /**
	 * aggiunge una uba alla lista
	 */
	public void aggiungiUba()
	{
		_action = Costanti.ACTION_INSERT;
		_titoloPagina = "Creazione nuovo esito UBA";
		_uba = new UbaBO();
		//_uba.set_intervento("214.2");
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
		_selectedValue = _ubaEJBBean.getElencoUba().get(_selectedIndex);
		_uba = _selectedValue;
		_ubaOld = _uba.clone();
		_titoloPagina = "Modifica esito UBA";
		_readOnly = true;
	}
	
	/**
	 * cancella un elemento della lista
	 */
	public void cancellaUba()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_domanda().equals("") || _selectedIndex == -1) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_selectedValue = _ubaEJBBean.getElencoUba().get(_selectedIndex);
		_uba = _selectedValue;
		OperationResultBO result = _ubaEJBBean.cancellaUba(_uba);
		if(result.get_result()) {
			_uba= new UbaBO();
			_selectedValue = new UbaBO();
			_ubaOld = new UbaBO();
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
			result = _ubaEJBBean.aggiungiUba(_uba);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _ubaEJBBean.modificaUba(_uba);
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
	    String filename = Costanti.FILE_UBA;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_ubaEJBBean.exportFile(externalContext.getResponseOutputStream());
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
	public String importaUba()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UploadUIBean uploadBean = (UploadUIBean) context.getApplication().evaluateExpressionGet(context, "#{uploadBean}", Object.class);
		uploadBean.set_titoloPagina("UBA - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_UBA);
		uploadBean.set_ejbBean(_ubaEJBBean);
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
		_uba.set_ubaDiff(Utils.roundNumber(uba_diff));
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
	

    public List<UbaBO> getElencoUba()
    {
        return _ubaEJBBean.getElencoUba();
    }

    public void setElencoUba(final ElencoUbaBO uba)
    {
    	_ubaEJBBean.setElencoUba(uba);
    }
    
    /**
	 * carica i dati degli esiti UBA 
	 */
	public void loadData()
	{
		_ubaEJBBean.loadData();
	}

	public void set_action(String _action) {
		this._action = _action;
	}

	public String get_action() {
		return _action;
	}

	public void set_uba(UbaBO _uba) {
		this._uba = _uba;
	}

	public UbaBO get_uba() {
		return _uba;
	}

	public void set_listEsito(List<String> _listEsito) {
		this._listEsito = _listEsito;
	}

	public List<String> get_listEsito() {
		if(_listEsito == null || _listEsito.size()==0)
			_listEsito = _ubaEJBBean.getListaValoriEsito();
		return _listEsito;
	}

	public void set_listCampagna(Vector<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	public List<String> get_listCampagna() {
		if(_listCampagna.size()==0)
			_listCampagna = _ubaEJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_selectedValue(UbaBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public UbaBO get_selectedValue() {
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
    	_ubaEJBBean.filter(parametersList);
    }
    
    /**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new UbaBO();
		_uba = new UbaBO();
		_ubaOld = new UbaBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_ubaEJBBean.clearList();
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

	public int get_selectedIndex() {
		return _selectedIndex;
	}

	public void set_selectedIndex(int _selectedIndex) {
		this._selectedIndex = _selectedIndex;
	}
	
	


}
