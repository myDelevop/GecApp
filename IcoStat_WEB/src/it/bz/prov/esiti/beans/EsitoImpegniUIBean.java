package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.bobject.ElencoEsitoImpegniBO;
import it.bz.prov.esiti.bobject.EsitoImpegniBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.ibusiness.IEsitoImpegni;
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
 * Bean UI (user interface) per la gestione degli esiti impegni
 * 
 * @author bpettazzoni
 *
 */
@ManagedBean(name = "esitoImpegniBean")
@SessionScoped
public class EsitoImpegniUIBean {
	@EJB
	private IEsitoImpegni _esitoImpegniEJBBean;
	private EsitoImpegniBO _esitoImpegni ;
	private EsitoImpegniBO _selectedValue;
	private int _selectedValueIndex;
	private EsitoImpegniBO _esitoImpegniOld ; // serve per memorizzare la versione originale durante la modifica
	private String _action;
	private List<String> _listCampagna;
	private List<String> _listEsito;
	private List<String> _listIntervento;
	private List<String> _listSottointervento;
	private List<String> _listStato;
	private List<SelectItem> _listInadempienzaGraveRadioButton;
	private List<SelectItem> _listReiterazioneRadioButton;
	private List<SelectItem> _listProgrReiterazioneRadioButton;
	private List<SelectItem> _listControlloEstivoRadioButton;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	private boolean _disablePercRidOPPAB;
	private boolean _disablePercRidPremioTrasporto;
	private boolean _disableImportoRidRinunciaInsilato;
	
	/**
	 * costruttore
	 */
	public EsitoImpegniUIBean()
	{
//		_esitoImpegniEJBBean = new EsitoImpegniBean();
		_selectedValue = new EsitoImpegniBO();
		_esitoImpegniOld = new EsitoImpegniBO();
		_esitoImpegni = new EsitoImpegniBO();
		_action= Costanti.ACTION_VIEW;
		_listCampagna = new Vector<String>();
		_listEsito = new ArrayList<String>();
		_listIntervento = new ArrayList<String>();
		_listSottointervento = new ArrayList<String>();
		_listStato = new ArrayList<String>();
		
		_listInadempienzaGraveRadioButton = new ArrayList<SelectItem>();
		_listInadempienzaGraveRadioButton.add(new SelectItem("SI", "Si"));
		_listInadempienzaGraveRadioButton.add(new SelectItem("NO", "No"));

		_listReiterazioneRadioButton = new ArrayList<SelectItem>();
		_listReiterazioneRadioButton.add(new SelectItem("SI", "Si"));
		_listReiterazioneRadioButton.add(new SelectItem("NO", "No"));

		_listProgrReiterazioneRadioButton = new ArrayList<SelectItem>();
		_listProgrReiterazioneRadioButton = new ArrayList<SelectItem>();
		_listProgrReiterazioneRadioButton.add(new SelectItem("1", "1"));
		_listProgrReiterazioneRadioButton.add(new SelectItem("2", "2"));
		_listProgrReiterazioneRadioButton.add(new SelectItem("3", "3"));
		_listProgrReiterazioneRadioButton.add(new SelectItem("4", "4"));
		_listProgrReiterazioneRadioButton.add(new SelectItem("5", "5"));
		_listProgrReiterazioneRadioButton.add(new SelectItem("", "Vuoto"));
		_titoloPagina = "";
		_message = "";
		_messageVisible = false;
		_readOnly = false;
		_disablePercRidOPPAB=false;
		_disablePercRidPremioTrasporto= false;
		_disableImportoRidRinunciaInsilato= false;
		
		_listControlloEstivoRadioButton = new ArrayList<SelectItem>();
		_listControlloEstivoRadioButton.add(new SelectItem("SI", "Si"));
		_listControlloEstivoRadioButton.add(new SelectItem("NO", "No"));

	}
	
	/**
	 * aggiunge un esito alla lista
	 */
	public void aggiungiEsito()
	{
		_action = Costanti.ACTION_INSERT;
		_esitoImpegni = new EsitoImpegniBO();
		_esitoImpegni.set_controllo_estivo("NO");
		_titoloPagina = "Creazione nuovo esito impegni";
		_readOnly = false;
		_disableImportoRidRinunciaInsilato= true;
		_disablePercRidOPPAB= true;
		_disablePercRidPremioTrasporto= true;
		// svuoto le liste così quando vado in aggiungi vengono riepite da capo
		_listIntervento.clear();
		_listSottointervento.clear();
		
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
				List<String> result = _esitoImpegniEJBBean.getListDomande(cuaa, campagna);
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
		//Segnalazione di default a NO per campione
		_esitoImpegni.set_segnalazione("NO");
		
		_esitoImpegni.set_inadempienza_grave("NO");
		_esitoImpegni.set_reiterazione("NO");
		_esitoImpegni.set_progrReiterazione("");
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
		_selectedValue = _esitoImpegniEJBBean.getElencoEsitoImpegni().get(_selectedValueIndex);
		_esitoImpegni = _selectedValue;
		_esitoImpegniOld = _esitoImpegni.clone();
		_titoloPagina = "Modifica esito impegni";
		_readOnly = true;
		// imposta l'abilitazione delle textbox in base ai valori degli esiti
		disableImpRidRinunciaInsilato(null);
		disablePercRidOPPAB(null);
		disablePercRidPremioTrasporto(null);
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
		_selectedValue= _esitoImpegniEJBBean.getElencoEsitoImpegni().get(_selectedValueIndex);
		_esitoImpegni = _selectedValue;
		OperationResultBO result = _esitoImpegniEJBBean.cancellaEsito(_esitoImpegni); 
		if(result.get_result()) {
			_esitoImpegni = new EsitoImpegniBO();
			_selectedValue = new EsitoImpegniBO();
			_esitoImpegniOld = new EsitoImpegniBO();
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
			result = _esitoImpegniEJBBean.aggiungiEsito(_esitoImpegni);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _esitoImpegniEJBBean.modificaEsito(_esitoImpegni);
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
		uploadBean.set_titoloPagina("Esito Impegni - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_IMPEGNI);
		uploadBean.set_ejbBean(_esitoImpegniEJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";
	}
	
	/**
	 * esportazione dati
	 */
	public void esportaEsito()
	{
		String filename = Costanti.FILE_ESITO_IMPEGNI;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_esitoImpegniEJBBean.exportFile(externalContext.getResponseOutputStream());
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
	public void disablePercRidOPPAB(AjaxActionEvent e){	
		if(_esitoImpegni.get_esito() != null && _esitoImpegni.get_esito().equals("POSITIVO")) {
			_disablePercRidOPPAB= true;
			_esitoImpegni.set_percRid("");
			_esitoImpegni.set_percRidCommissRiesame("");
		}
		else {
			_disablePercRidOPPAB = false; 
		}
	}
	
	/**
	 * funzione che definisce l'abilitazione grafica delle textbox per l'inserimento della percentuale di riduzione
	 * in base all'esito selezionato per il premio trasporto
	 * @param e
	 */
	public void disablePercRidPremioTrasporto(AjaxActionEvent e){	
		if(_esitoImpegni.get_esitoPremioTrasporto().equals("POSITIVO")) {
			_disablePercRidPremioTrasporto= true;
			_esitoImpegni.set_percRidPremioTrasporto("");
		}
		else _disablePercRidPremioTrasporto = false; 
	}
	
	
	/**
	 * funzione che definisce l'abilitazione grafica delle textbox per l'inserimento dell'importo di riduzione
	 * in base all'esito selezionato per la rinuncia dell'insilato
	 * @param e
	 */
	public void disableImpRidRinunciaInsilato(AjaxActionEvent e){	
		if(_esitoImpegni.get_esitoRinunciaInsilato().equals("POSITIVO")) {
			_disableImportoRidRinunciaInsilato= true;
			_esitoImpegni.set_importoRidRinunciaInsilato("");
		}
		else _disableImportoRidRinunciaInsilato = false; 
	}
	
	/**
	 * funzione che setta l'intervento a partire dalla campagna
	 * @param e
	 */
	public void settingIntervento(AjaxActionEvent e){
		String campagna = _esitoImpegni.get_campagna();
		_listIntervento = _esitoImpegniEJBBean.getListInterventoForCampagna(campagna);

	}
	
	/**
	 * funzione che setta l'intervento a partire dalla campagna
	 * @param e
	 */
	public void settingSottoIntervento(AjaxActionEvent e){
		String campagna = _esitoImpegni.get_campagna();
		_listSottointervento = _esitoImpegniEJBBean.getListSottointerventoForCampagna(campagna);

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
		if(_esitoImpegni.get_intervento().equals("10.1")) {
			if(_esitoImpegniEJBBean.hasSottointervento(_esitoImpegni.get_domanda(), _esitoImpegni.get_intervento(), "10.1/b")) {
				_esitoImpegni.set_sottointervento("10.1/b");
			} else {
				_esitoImpegni.set_sottointervento("10.1/a");
			}
			return;
		}
		if(_esitoImpegni.get_intervento().equals("10.3")) {
			if(_esitoImpegniEJBBean.hasSottointervento(_esitoImpegni.get_domanda(), _esitoImpegni.get_intervento(), "10.3/b")) {
				_esitoImpegni.set_sottointervento("10.3/b");
			} else {
				_esitoImpegni.set_sottointervento("10.3/a");
			}
			return;
		}
	}
	
//	/**
//	 * metodo per gestire l'aggiornamento della lista dei sottointerventi
//	 * alla selezione dell'intervento
//	 * @param arg0
//	 * @throws AbortProcessingException
//	 */
//	public void interventoChanged(ValueChangeEvent arg0){
//		try {
//			String intervento = arg0.getNewValue().toString();
//			if (intervento.equals("")) return;
//			_esitoImpegni.set_intervento(intervento);
//			_listSottointervento = _esitoImpegniEJBBean.getListSottointervento(intervento);
//		} catch (Exception e) {
//		}
//	}
	
	
    public List<EsitoImpegniBO> getElencoEsitoImpegni()
    {
        return _esitoImpegniEJBBean.getElencoEsitoImpegni();
    }

    public void setElencoEsitoImpegni(final ElencoEsitoImpegniBO elencoEsitoImpegni)
    {
    	_esitoImpegniEJBBean.setElencoEsitoImpegni(elencoEsitoImpegni);
    }
    
    
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData()
	{
		_esitoImpegniEJBBean.loadData();
	}

	public void set_esitoImpegni(EsitoImpegniBO _esitoImpegni) {
		this._esitoImpegni = _esitoImpegni;
	}

	public EsitoImpegniBO get_esitoImpegni() {
		return _esitoImpegni;
	}

	public void set_selectedValue(EsitoImpegniBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public EsitoImpegniBO get_selectedValue() {
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
			_listCampagna = _esitoImpegniEJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_listEsito(List<String> _listEsito) {
		this._listEsito = _listEsito;
	}

	public List<String> get_listEsito() {
		_listEsito = _esitoImpegniEJBBean.getListaValori(Costanti.ANAGR_ESITO);
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
    	_esitoImpegniEJBBean.filter(parametersList);
    }
    
    /**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new EsitoImpegniBO();
		_esitoImpegni = new EsitoImpegniBO();
		_esitoImpegniOld = new EsitoImpegniBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_esitoImpegniEJBBean.clearList();
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

	public void set_listIntervento(List<String> _listIntervento) {
		this._listIntervento = _listIntervento;
	}

	public List<String> get_listIntervento() {
		if(_listIntervento.size() == 0)
				_listIntervento = _esitoImpegniEJBBean.getListIntervento();
		return _listIntervento;
	}

	public void set_listSottointervento(List<String> _listSottointervento) {
		this._listSottointervento = _listSottointervento;
	}

	public List<String> get_listSottointervento() {
		if(_listSottointervento.size() == 0)
				_listSottointervento = _esitoImpegniEJBBean.getListSottointervento();
		return _listSottointervento;
	}

	public void set_listStato(List<String> _listStato) {
		this._listStato = _listStato;
	}

	public List<SelectItem> get_listInadempienzaGraveRadioButton() {
		return _listInadempienzaGraveRadioButton;
	}

	public void set_listInadempienzaGraveRadioButton(List<SelectItem> _listInadempienzaGraveRadioButton) {
		this._listInadempienzaGraveRadioButton = _listInadempienzaGraveRadioButton;
	}

	public List<SelectItem> get_listReiterazioneRadioButton() {
		return _listReiterazioneRadioButton;
	}

	public void set_listReiterazioneRadioButton(List<SelectItem> _listReiterazioneRadioButton) {
		this._listReiterazioneRadioButton = _listReiterazioneRadioButton;
	}

	public List<SelectItem> get_listProgrReiterazioneRadioButton() {
		return _listProgrReiterazioneRadioButton;
	}

	public void set_listProgrReiterazioneRadioButton(List<SelectItem> _listProgrReiterazioneRadioButton) {
		this._listProgrReiterazioneRadioButton = _listProgrReiterazioneRadioButton;
	}

	public List<String> get_listStato() {
		_listStato = _esitoImpegniEJBBean.getListaValori(Costanti.ANAGR_STATO);
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

	public void set_disablePercRidOPPAB(boolean _disablePercRidOPPAB) {
		this._disablePercRidOPPAB = _disablePercRidOPPAB;
	}

	public boolean get_disablePercRidOPPAB() {
		return _disablePercRidOPPAB;
	}

	public void set_disablePercRidPremioTrasporto(
			boolean _disablePercRidPremioTrasporto) {
		this._disablePercRidPremioTrasporto = _disablePercRidPremioTrasporto;
	}

	public boolean is_disablePercRidPremioTrasporto() {
		return _disablePercRidPremioTrasporto;
	}

	public void set_disableImportoRidRinunciaInsilato(
			boolean _disableImportoRidRinunciaInsilato) {
		this._disableImportoRidRinunciaInsilato = _disableImportoRidRinunciaInsilato;
	}

	public boolean is_disableImportoRidRinunciaInsilato() {
		return _disableImportoRidRinunciaInsilato;
	}

	public List<SelectItem> get_listControlloEstivoRadioButton() {
		return _listControlloEstivoRadioButton;
	}

	public void set_listControlloEstivoRadioButton(
			List<SelectItem> _listControlloEstivoRadioButton) {
		this._listControlloEstivoRadioButton = _listControlloEstivoRadioButton;
	}
	
	
}
