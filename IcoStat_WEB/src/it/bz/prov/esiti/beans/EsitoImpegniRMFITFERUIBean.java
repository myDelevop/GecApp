package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.bobject.ElencoEsitoImpegniRMFITFERBO;
import it.bz.prov.esiti.bobject.EsitoImpegniRMFITFERBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.ibusiness.IEsitoImpegniRMFITFER;
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
 * Bean UI (user interface) per la gestione degli esiti impegni RM FIT e FER
 * 
 * @author lattana
 *
 */
@ManagedBean(name = "esitoImpegniRMFITFERBean")
@SessionScoped
public class EsitoImpegniRMFITFERUIBean {
	@EJB
	private IEsitoImpegniRMFITFER _esitoImpegniRMFITFEREJBBean;
	private EsitoImpegniRMFITFERBO _esitoImpegni ;
	private EsitoImpegniRMFITFERBO _selectedValue;
	private int _selectedValueIndex;
	private EsitoImpegniRMFITFERBO _esitoImpegniOld ; // serve per memorizzare la versione originale durante la modifica
	private String _action;
	private List<String> _listCampagna;
	private List<String> _listEsito;
	private List<String> _listMisura;
	private List<String> _listStato;
	private List<SelectItem> _listNegligenzaRadioButton;
	private List<SelectItem> _listEsclusioneRadioButton;
	private List<SelectItem> _listProgrAccEsclusioneRadioButton;
	private List<SelectItem> _listReiterazioneRadioButton;
	private List<SelectItem> _listProgrAccReitRadioButton;
	private List<SelectItem> _listPortataRadioButton;
	private List<SelectItem> _listGravitaRadioButton;
	private List<SelectItem> _listDurataRadioButton;
	private List<SelectItem> _listSegnalazioneRadioButton;
	private List<SelectItem> _listApprovazioneRadioButton;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	private boolean _disablePercRid;
	
	/**
	 * costruttore
	 */
	public EsitoImpegniRMFITFERUIBean()
	{
//		_esitoImpegniRMFITFEREJBBean = new EsitoImpegniRMFITFERBean();
		_selectedValue = new EsitoImpegniRMFITFERBO();
		_esitoImpegniOld = new EsitoImpegniRMFITFERBO();
		_esitoImpegni = new EsitoImpegniRMFITFERBO();
		_action= Costanti.ACTION_VIEW;
		_listCampagna = new Vector<String>();
		_listEsito = new ArrayList<String>();
		_listMisura = new ArrayList<String>();
		_listStato = new ArrayList<String>();
		
		_listNegligenzaRadioButton = new ArrayList<SelectItem>();
		_listNegligenzaRadioButton.add(new SelectItem("SI", "Si"));
		_listNegligenzaRadioButton.add(new SelectItem("NO", "No"));

		_listEsclusioneRadioButton = new ArrayList<SelectItem>();
		_listEsclusioneRadioButton.add(new SelectItem("SI", "Si"));
		_listEsclusioneRadioButton.add(new SelectItem("NO", "No"));

		_listProgrAccEsclusioneRadioButton = new ArrayList<SelectItem>();
		_listProgrAccEsclusioneRadioButton.add(new SelectItem("1", "1"));
		_listProgrAccEsclusioneRadioButton.add(new SelectItem("2", "2"));
		_listProgrAccEsclusioneRadioButton.add(new SelectItem("3", "3"));
		_listProgrAccEsclusioneRadioButton.add(new SelectItem("4", "4"));
		_listProgrAccEsclusioneRadioButton.add(new SelectItem("5", "5"));
		_listProgrAccEsclusioneRadioButton.add(new SelectItem("", "Vuoto"));

		_listReiterazioneRadioButton = new ArrayList<SelectItem>();
		_listReiterazioneRadioButton.add(new SelectItem("SI", "Si"));
		_listReiterazioneRadioButton.add(new SelectItem("NO", "No"));

		_listProgrAccReitRadioButton = new ArrayList<SelectItem>();
		_listProgrAccReitRadioButton.add(new SelectItem("1", "1"));
		_listProgrAccReitRadioButton.add(new SelectItem("2", "2"));
		_listProgrAccReitRadioButton.add(new SelectItem("3", "3"));
		_listProgrAccReitRadioButton.add(new SelectItem("4", "4"));
		_listProgrAccReitRadioButton.add(new SelectItem("5", "5"));
		_listProgrAccReitRadioButton.add(new SelectItem("", "Vuoto"));

		_listSegnalazioneRadioButton = new ArrayList<SelectItem>();
		_listSegnalazioneRadioButton.add(new SelectItem("SI", "Si"));
		_listSegnalazioneRadioButton.add(new SelectItem("NO", "No"));

		_listApprovazioneRadioButton = new ArrayList<SelectItem>();
		_listApprovazioneRadioButton.add(new SelectItem("SI", "Si"));
		_listApprovazioneRadioButton.add(new SelectItem("NO", "No"));
		
		_listPortataRadioButton = new ArrayList<SelectItem>();
		_listPortataRadioButton.add(new SelectItem("1", "1"));
		_listPortataRadioButton.add(new SelectItem("3", "3"));	
		_listPortataRadioButton.add(new SelectItem("5", "5"));
		_listPortataRadioButton.add(new SelectItem("", "Vuoto"));	

		_listGravitaRadioButton = new ArrayList<SelectItem>();
		_listGravitaRadioButton.add(new SelectItem("1", "1"));
		_listGravitaRadioButton.add(new SelectItem("3", "3"));	
		_listGravitaRadioButton.add(new SelectItem("5", "5"));
		_listGravitaRadioButton.add(new SelectItem("", "Vuoto"));	

		_listDurataRadioButton = new ArrayList<SelectItem>();
		_listDurataRadioButton.add(new SelectItem("1", "1"));
		_listDurataRadioButton.add(new SelectItem("3", "3"));	
		_listDurataRadioButton.add(new SelectItem("5", "5"));
		_listDurataRadioButton.add(new SelectItem("", "Vuoto"));	
				
		_titoloPagina = "";
		_message = "";
		_messageVisible = false;
		_readOnly = false;
		_disablePercRid= false;
	}
	
	/**
	 * aggiunge un esito alla lista
	 */
	public void aggiungiEsito()
	{
		_action = Costanti.ACTION_INSERT;
		_esitoImpegni = new EsitoImpegniRMFITFERBO();
		_titoloPagina = "Creazione nuovo esito impegni RM FIT e FER";
		_readOnly = false;
		_disablePercRid= false;
		// la svuoto così quando viene richiamata viene impostata a campi pieni
		_listMisura.clear();
		
		// se nel box di ricerca sono inseriti cuaa o campagna si pre-compila la form di inserimento
		FacesContext context = FacesContext.getCurrentInstance();
		SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
		String cuaa= searchBean.getCuaa();
		String campagna= searchBean.getCampagna();
		// per evitare di prendere un cuaa che é una substring (anche così la stringa potrebbe non 
		//corrispondere a un cuaa, ma é una richiesta di Lars)
		if(cuaa.length() > 10 )	{
			if(campagna.equals("")) _esitoImpegni.set_cuaa(cuaa);
			if(!campagna.equals("")){
				List<String> result = _esitoImpegniRMFITFEREJBBean.getListDomande(cuaa, campagna);
				if(result.size() == 0){
					_message = "Nessuna domanda presente per il CUAA e Campagna indicati nella form di ricerca";
					_messageVisible = true;
				}
				else 
				{
					_esitoImpegni.set_cuaa(cuaa);
					_esitoImpegni.set_campagna(campagna);
				}
			}
		}
		_esitoImpegni.set_segnalazione("NO");
		_esitoImpegni.set_esclusione("NO");
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
		_selectedValue= _esitoImpegniRMFITFEREJBBean.getElencoEsitoImpegni().get(_selectedValueIndex);
		_esitoImpegni = _selectedValue;
		_esitoImpegniOld = _esitoImpegni.clone();
		_titoloPagina = "Modifica esito impegni RM FIT e FER";
		_readOnly = true;	
		// eseguo il setting textbox in base al valore 
		disablePercRid(null);
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
		_selectedValue= _esitoImpegniRMFITFEREJBBean.getElencoEsitoImpegni().get(_selectedValueIndex);
		_esitoImpegni = _selectedValue;
		OperationResultBO result = _esitoImpegniRMFITFEREJBBean.cancellaEsito(_esitoImpegni); 
		if(result.get_result()) {
			_esitoImpegni = new EsitoImpegniRMFITFERBO();
			_selectedValue = new EsitoImpegniRMFITFERBO();
			_esitoImpegniOld = new EsitoImpegniRMFITFERBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * salva un esito nella lista
	 */
	public void salvaEsito()
	{
		OperationResultBO result = null;
		if(_action.equals(Costanti.ACTION_INSERT)){
			result = _esitoImpegniRMFITFEREJBBean.aggiungiEsito(_esitoImpegni);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _esitoImpegniRMFITFEREJBBean.modificaEsito(_esitoImpegni);
		}
		if(result.get_result()) _action=Costanti.ACTION_VIEW;
		_message = result.get_message();
		_messageVisible = true;
	}
	
	/**
	 * annulla la creazione di un nuovo esito
	 */
	public void annullaEsito()
	{
		if(_esitoImpegniOld != null) _esitoImpegni.recovery(_esitoImpegniOld);
		_action = Costanti.ACTION_VIEW;
	}
	
	
	/** 
	 * importazione dati
	 */
	public String importaEsito()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UploadUIBean uploadBean = (UploadUIBean) context.getApplication().evaluateExpressionGet(context, "#{uploadBean}", Object.class);
		uploadBean.set_titoloPagina("Esito RM FIT/FER - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_RMFITFER);
		uploadBean.set_ejbBean(_esitoImpegniRMFITFEREJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";
	}
	
	/**
	 * esportazione dati
	 */
	public void esportaEsito()
	{
		String filename = Costanti.FILE_ESITO_RMFITFER;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_esitoImpegniRMFITFEREJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	
	/**
	 * funzione che definisce l'abilitazione grafica delle textbox per l'inserimento delle percentuali di riduzione
	 * in base all'esito selezionato
	 * @param e
	 */
	public void disablePercRid(AjaxActionEvent e){	
		if(_esitoImpegni.get_esito_oppab().equals("POSITIVO")) {
			_disablePercRid= true;
			_esitoImpegni.set_percRidFER("");
			_esitoImpegni.set_percRidFIT("");
			_esitoImpegni.set_percTotOPPAB("");
			_esitoImpegni.set_percCommissRiesame("");
			_esitoImpegni.set_esclusione("");
			_esitoImpegni.set_esclusione_note("");
			_esitoImpegni.set_progr_accert_esclusione("");
			_esitoImpegni.set_progr_accert_reiteraz("");
			_esitoImpegni.set_reiterazione("");
			_esitoImpegni.set_portata("");
			_esitoImpegni.set_portata_note("");
			_esitoImpegni.set_gravita("");
			_esitoImpegni.set_gravita_note("");
			_esitoImpegni.set_durata("");
			_esitoImpegni.set_durata_note("");			
		}
		else {
			_disablePercRid= false;
		}
	}
	
	/**
	 * funzione che setta la misura a partire dalla campagna
	 * @param e
	 */
	public void settingMisura(AjaxActionEvent e){
		String campagna = _esitoImpegni.get_campagna();
		_listMisura = _esitoImpegniRMFITFEREJBBean.getListMisuraForCampagna(campagna);
		_listMisura.add(0, "");

	}
	
    public List<EsitoImpegniRMFITFERBO> getElencoEsitoImpegni()
    {
        return _esitoImpegniRMFITFEREJBBean.getElencoEsitoImpegni();
    }

    public void setElencoEsitoImpegni(final ElencoEsitoImpegniRMFITFERBO elencoEsitoImpegni)
    {
    	_esitoImpegniRMFITFEREJBBean.setElencoEsitoImpegni(elencoEsitoImpegni);
    }
    
    
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData()
	{
		_esitoImpegniRMFITFEREJBBean.loadData();
	}

	public void set_esitoImpegni(EsitoImpegniRMFITFERBO _esitoImpegni) {
		this._esitoImpegni = _esitoImpegni;
	}

	public EsitoImpegniRMFITFERBO get_esitoImpegni() {
		return _esitoImpegni;
	}

	public void set_selectedValue(EsitoImpegniRMFITFERBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public EsitoImpegniRMFITFERBO get_selectedValue() {
		return _selectedValue;
	}

	public void set_action(String _action) {
		this._action = _action;
	}

	public String get_action() {
		return _action;
	}

	public void set_listCampagna(Vector<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	public List<String> get_listCampagna() {
		if(_listCampagna.size()==0)
			_listCampagna = _esitoImpegniRMFITFEREJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_listEsito(List<String> _listEsito) {
		this._listEsito = _listEsito;
	}

	public List<String> get_listEsito() {
		_listEsito = _esitoImpegniRMFITFEREJBBean.getListaValori(Costanti.ANAGR_ESITO);
		return _listEsito;
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
    	_esitoImpegniRMFITFEREJBBean.filter(parametersList);
    }
    
    /**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new EsitoImpegniRMFITFERBO();
		_esitoImpegni = new EsitoImpegniRMFITFERBO();
		_esitoImpegniOld = new EsitoImpegniRMFITFERBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_esitoImpegniRMFITFEREJBBean.clearList();
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

	public void set_listMisura(List<String> _listMisura) {
		this._listMisura = _listMisura;
	}

	public List<String> get_listMisura() {
		if(_listMisura.size() == 0)
			_listMisura = _esitoImpegniRMFITFEREJBBean.getListMisura();
		return _listMisura;
	}

	public void set_listStato(List<String> _listStato) {
		this._listStato = _listStato;
	}

	public List<String> get_listStato() {
		_listStato = _esitoImpegniRMFITFEREJBBean.getListaValori(Costanti.ANAGR_STATO);
		return _listStato;
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

	public boolean is_disablePercRid() {
		return _disablePercRid;
	}

	public void set_disablePercRid(boolean _disablePercRid) {
		this._disablePercRid = _disablePercRid;
	}

	public List<SelectItem> get_listNegligenzaRadioButton() {
		return _listNegligenzaRadioButton;
	}

	public void set_listNegligenzaRadioButton(
			List<SelectItem> _listNegligenzaRadioButton) {
		this._listNegligenzaRadioButton = _listNegligenzaRadioButton;
	}

	public List<SelectItem> get_listIntenzionalitaRadioButton() {
		return _listEsclusioneRadioButton;
	}

	public void set_listIntenzionalitaRadioButton(
			List<SelectItem> _listIntenzionalitaRadioButton) {
		this._listEsclusioneRadioButton = _listIntenzionalitaRadioButton;
	}

	public List<SelectItem> get_listProgrAccEsclusioneRadioButton() {
		return _listProgrAccEsclusioneRadioButton;
	}

	public void set_listProgrAccEsclusioneRadioButton(
			List<SelectItem> _listProgrAccIntenzRadioButton) {
		this._listProgrAccEsclusioneRadioButton = _listProgrAccIntenzRadioButton;
	}

	public List<SelectItem> get_listReiterazioneRadioButton() {
		return _listReiterazioneRadioButton;
	}

	public void set_listReiterazioneRadioButton(
			List<SelectItem> _listReiterazioneRadioButton) {
		this._listReiterazioneRadioButton = _listReiterazioneRadioButton;
	}

	public List<SelectItem> get_listProgrAccReitRadioButton() {
		return _listProgrAccReitRadioButton;
	}

	public void set_listProgrAccReitRadioButton(
			List<SelectItem> _listProgrAccReitRadioButton) {
		this._listProgrAccReitRadioButton = _listProgrAccReitRadioButton;
	}

	public List<SelectItem> get_listPortataRadioButton() {
		return _listPortataRadioButton;
	}

	public void set_listPortataRadioButton(List<SelectItem> _listPortataRadioButton) {
		this._listPortataRadioButton = _listPortataRadioButton;
	}

	public List<SelectItem> get_listGravitaRadioButton() {
		return _listGravitaRadioButton;
	}

	public void set_listGravitaRadioButton(List<SelectItem> _listGravitaRadioButton) {
		this._listGravitaRadioButton = _listGravitaRadioButton;
	}

	public List<SelectItem> get_listDurataRadioButton() {
		return _listDurataRadioButton;
	}

	public void set_listDurataRadioButton(List<SelectItem> _listDurataRadioButton) {
		this._listDurataRadioButton = _listDurataRadioButton;
	}

	public List<SelectItem> get_listSegnalazioneRadioButton() {
		return _listSegnalazioneRadioButton;
	}

	public void set_listSegnalazioneRadioButton(
			List<SelectItem> _listSegnalazioneRadioButton) {
		this._listSegnalazioneRadioButton = _listSegnalazioneRadioButton;
	}

	public List<SelectItem> get_listApprovazioneRadioButton() {
		return _listApprovazioneRadioButton;
	}

	public void set_listApprovazioneRadioButton(
			List<SelectItem> _listApprovazioneRadioButton) {
		this._listApprovazioneRadioButton = _listApprovazioneRadioButton;
	}

}
