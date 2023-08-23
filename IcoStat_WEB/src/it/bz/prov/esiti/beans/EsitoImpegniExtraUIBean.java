package it.bz.prov.esiti.beans;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.openfaces.event.AjaxActionEvent;

import it.bz.prov.esiti.bobject.ElencoEsitoImpegniExtraBO;
import it.bz.prov.esiti.bobject.EsitoImpegniExtraBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.ibusiness.IEsitoImpegniExtra;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

/**
 * Bean UI (user interface) per la gestione degli esiti impegni
 * 
 * @author bpettazzoni
 *
 */
@ManagedBean(name = "esitoImpegniExtraBean")
@SessionScoped
public class EsitoImpegniExtraUIBean {
	@EJB
	private IEsitoImpegniExtra _esitoImpegniExtraEJBBean;
	private EsitoImpegniExtraBO _esitoImpegniExtra ;
	private EsitoImpegniExtraBO _selectedValue;
	private int _selectedValueIndex;
	private EsitoImpegniExtraBO _esitoImpegniExtraOld ; // serve per memorizzare la versione originale durante la modifica
	private String _action;
	private List<String> _listCampagna;
	private List<String> _listEsito;
	private List<String> _listIntervento;
	private List<String> _listSottointervento;
	private List<String> _listImpegno;
	private List<String> _listStato;
	private List<SelectItem> _listReiterazioneRadioButton;
	private List<SelectItem> _listProgrAccReitRadioButton;
	private List<SelectItem> _listEsclusioneRadioButton;
	private List<SelectItem> _listInadempienzaGraveRadioButton;
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
	private boolean _disableImportoRidRinunciaInsilato;
	
	/**
	 * costruttore
	 */
	public EsitoImpegniExtraUIBean()
	{
		_selectedValue = new EsitoImpegniExtraBO();
		_esitoImpegniExtraOld = new EsitoImpegniExtraBO();
		_esitoImpegniExtra = new EsitoImpegniExtraBO();
		_action= Costanti.ACTION_VIEW;
		_listCampagna = new Vector<String>();
		_listEsito = new ArrayList<String>();
		_listIntervento = new ArrayList<String>();
		_listSottointervento = new ArrayList<String>();
		_listImpegno = new ArrayList<String>();

		_listStato = new ArrayList<String>();
		_titoloPagina = "";
		_message = "";
		_messageVisible = false;
		_readOnly = false;
		_disablePercRid=false;
		_disableImportoRidRinunciaInsilato= false;
		
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

		_listEsclusioneRadioButton = new ArrayList<SelectItem>();
		_listEsclusioneRadioButton.add(new SelectItem("SI", "Si"));
		_listEsclusioneRadioButton.add(new SelectItem("NO", "No"));
		
		_listSegnalazioneRadioButton = new ArrayList<SelectItem>();
		_listSegnalazioneRadioButton.add(new SelectItem("SI", "Si"));
		_listSegnalazioneRadioButton.add(new SelectItem("NO", "No"));

		_listApprovazioneRadioButton = new ArrayList<SelectItem>();
		_listApprovazioneRadioButton.add(new SelectItem("SI", "Si"));
		_listApprovazioneRadioButton.add(new SelectItem("NO", "No"));
		
		_listInadempienzaGraveRadioButton = new ArrayList<SelectItem>();
		_listInadempienzaGraveRadioButton.add(new SelectItem("SI", "Si"));
		_listInadempienzaGraveRadioButton.add(new SelectItem("NO", "No"));

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
	}
	
	/**
	 * aggiunge un esito alla lista
	 */
	public void aggiungiEsito()
	{
		_action = Costanti.ACTION_INSERT;
		_esitoImpegniExtra = new EsitoImpegniExtraBO();
		_titoloPagina = "Creazione nuovo esito impegni extracampione";
		_readOnly = false;
		_disableImportoRidRinunciaInsilato= true;
		_disablePercRid= true;
		// svuoto le liste così quando vado in aggiungi vengono riepite da capo
		_listIntervento.clear();
		_listSottointervento.clear();
		_listImpegno.clear();
		
		// se nel box di ricerca sono inseriti cuaa o campagna si pre-compila la form di inserimento
		FacesContext context = FacesContext.getCurrentInstance();
		SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
		String cuaa= searchBean.getCuaa();
		String campagna= searchBean.getCampagna();
		// per evitare di prendere un cuaa che é una substring (anche così la stringa potrebbe non 
		//corrispondere a un cuaa, ma é una richiesta di Lars)
		if(cuaa.length() > 10 )	{
			if(campagna.equals("")) _esitoImpegniExtra.set_cuaa(cuaa);
			if(!campagna.equals("")){
				List<String> result = _esitoImpegniExtraEJBBean.getListDomande(cuaa, campagna);
				if(result.size() == 0){
					_message = "Nessuna domanda presente per il CUAA e Campagna indicati nella form di ricerca";
					_messageVisible = true;
				}
				else 
				{
					_esitoImpegniExtra.set_cuaa(cuaa);
					_esitoImpegniExtra.set_campagna(campagna);
				}
				List<String> resultPSR = this._esitoImpegniExtraEJBBean.getListDomandePSR(cuaa, campagna);
				if(resultPSR.size() == 1) {
					this._esitoImpegniExtra.set_domanda(resultPSR.get(0));
					//return;
				}
			}
		}
		//Segnalazione di default a NO per campione
		_esitoImpegniExtra.set_inadempienza_grave("NO");
		_esitoImpegniExtra.set_esito("NEGATIVO");
		_esitoImpegniExtra.set_segnalazione("SI");
		_esitoImpegniExtra.set_esclusione("NO");
		_esitoImpegniExtra.set_approvazione("NO");
		_esitoImpegniExtra.set_reiterazione("NO");
		disablePercRid(null);
		
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
		_selectedValue = _esitoImpegniExtraEJBBean.getElencoEsitoImpegni().get(_selectedValueIndex);
		_esitoImpegniExtra = _selectedValue;
		_esitoImpegniExtraOld = _esitoImpegniExtra.clone();
		_titoloPagina = "Modifica esito impegni extracampione";
		_readOnly = true;
		// imposta l'abilitazione delle textbox in base ai valori degli esiti
		disableImpRidRinunciaInsilato(null);
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
		_selectedValue= _esitoImpegniExtraEJBBean.getElencoEsitoImpegni().get(_selectedValueIndex);
		_esitoImpegniExtra = _selectedValue;
		OperationResultBO result = _esitoImpegniExtraEJBBean.cancellaEsito(_esitoImpegniExtra); 
		if(result.get_result()) {
			_esitoImpegniExtra = new EsitoImpegniExtraBO();
			_selectedValue = new EsitoImpegniExtraBO();
			_esitoImpegniExtraOld = new EsitoImpegniExtraBO();
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
			result = _esitoImpegniExtraEJBBean.aggiungiEsito(_esitoImpegniExtra);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _esitoImpegniExtraEJBBean.modificaEsito(_esitoImpegniExtra);
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
		if(_esitoImpegniExtraOld != null) _esitoImpegniExtra.recovery(_esitoImpegniExtraOld);
		_action = Costanti.ACTION_VIEW;
	}
	
	/**
	 * importazione dati
	 */
	public String importaEsito()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UploadUIBean uploadBean = (UploadUIBean) context.getApplication().evaluateExpressionGet(context, "#{uploadBean}", Object.class);
		uploadBean.set_titoloPagina("Esito Impegni Extracampione - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_IMPEGNI_EXTRACAMPIONE);
		uploadBean.set_ejbBean(_esitoImpegniExtraEJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";
	}
	
	/**
	 * esportazione dati
	 */
	public void esportaEsito()
	{
		String filename = Costanti.FILE_ESITO_IMPEGNI_EXTRA;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_esitoImpegniExtraEJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	/**
	 * questa funzione non fa nulla, serve per aggiornare i render del bean
	 * @param e
	 */
	public void updateRender(AjaxActionEvent e) {
	}

	/**
	 * funzione che definisce l'abilitazione grafica delle textbox per l'inserimento delle percentuali di riduzione
	 * in base all'esito selezionato
	 * @param e
	 */
	public void disablePercRid(AjaxActionEvent e){	
		if(_esitoImpegniExtra.get_esito() != null && _esitoImpegniExtra.get_esito().equals("POSITIVO")) {
			_disablePercRid= true;
			_esitoImpegniExtra.set_percRid("");
			_esitoImpegniExtra.set_esclusione("");
			_esitoImpegniExtra.set_reiterazione("");
			_esitoImpegniExtra.set_progr_accert_reiteraz("");
			_esitoImpegniExtra.set_portata("");
			_esitoImpegniExtra.set_portata_note("");
			_esitoImpegniExtra.set_gravita("");
			_esitoImpegniExtra.set_gravita_note("");
			_esitoImpegniExtra.set_durata("");
			_esitoImpegniExtra.set_durata_note("");
		}
		else {
			_disablePercRid = false; 
		}
	}
	
	/**
	 * funzione che definisce l'abilitazione grafica delle textbox per l'inserimento dell'importo di riduzione
	 * in base all'esito selezionato per la rinuncia dell'insilato
	 * @param e
	 */
	public void disableImpRidRinunciaInsilato(AjaxActionEvent e){	
		if(_esitoImpegniExtra.get_esitoRinunciaInsilato().equals("POSITIVO")) {
			_disableImportoRidRinunciaInsilato= true;
			_esitoImpegniExtra.set_importoRidRinunciaInsilato("");
		}
		else _disableImportoRidRinunciaInsilato = false; 
	}
	
	/**
	 * funzione che setta l'intervento a partire dalla campagna
	 * @param e
	 */
	public void settingIntervento(AjaxActionEvent e){
		String campagna = _esitoImpegniExtra.get_campagna();
		_listIntervento = _esitoImpegniExtraEJBBean.getListInterventoForCampagna(campagna);

	}
	
	public void settingImpegno(AjaxActionEvent e) {
		String intervento = _esitoImpegniExtra.get_intervento();
		String sottoIntervento = _esitoImpegniExtra.get_sottointervento();
		
		_listImpegno = _esitoImpegniExtraEJBBean.getListImpegnoForIntAndSottoInt(intervento, sottoIntervento);

	}
	
	/**
	 * funzione che setta l'intervento a partire dalla campagna
	 * @param e
	 */
	public void settingSottoIntervento(AjaxActionEvent e){
		String campagna = _esitoImpegniExtra.get_campagna();
		_listSottointervento = _esitoImpegniExtraEJBBean.getListSottointerventoForCampagna(campagna);
	}
	
    public void checkForWarningsEsitoImpegniExtra() { 
    	try {
        	FacesContext facesContext = FacesContext.getCurrentInstance();
        	List<String> warnings = _esitoImpegniExtraEJBBean.checkForWarningsEsitoImpegniExtra(this._esitoImpegniExtra);
        	
        	for(String warn:warnings) {
            	facesContext.addMessage("esitoImpegniExtraBeanWarningMessages"
            			, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", warn));    		
        	}    		
    	} catch(Exception e) {
    		Utils.getLog().error(EsitoImpegniExtraUIBean.class.toString() + " - Errore durante il checkForWarnings: " + e.getMessage());
    		e.printStackTrace();
    	}
    }

	/**
	 * funzione che setta il sottointervento per la 10.1 e la 10.3
	 * @param e
	 */
	public void settingSottoInterventoSelectIntervento(AjaxActionEvent e){
		if(_esitoImpegniExtra.get_intervento().equals("10.1")) {
			if(_esitoImpegniExtraEJBBean.hasSottointervento(_esitoImpegniExtra.get_domanda(), _esitoImpegniExtra.get_intervento(), "10.1/b")) {
				_esitoImpegniExtra.set_sottointervento("10.1/b");
			} else {
				_esitoImpegniExtra.set_sottointervento("10.1/a");
			}
			return;
		}
		if(_esitoImpegniExtra.get_intervento().equals("10.3")) {
			if(_esitoImpegniExtraEJBBean.hasSottointervento(_esitoImpegniExtra.get_domanda(), _esitoImpegniExtra.get_intervento(), "10.3/b")) {
				_esitoImpegniExtra.set_sottointervento("10.3/b");
			} else {
				_esitoImpegniExtra.set_sottointervento("10.3/a");
			}
			return;
		}
		if(_esitoImpegniExtra.get_intervento().equals("13")) {
				_esitoImpegniExtra.set_sottointervento("13");
			return;
		}
	}
	
    public List<EsitoImpegniExtraBO> getElencoEsitoImpegni()
    {
        return _esitoImpegniExtraEJBBean.getElencoEsitoImpegni();
    }

    public void setElencoEsitoImpegni(final ElencoEsitoImpegniExtraBO elencoEsitoImpegni)
    {
    	_esitoImpegniExtraEJBBean.setElencoEsitoImpegni(elencoEsitoImpegni);
    }
    
    
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData()
	{
		_esitoImpegniExtraEJBBean.loadData();
	}

	public void set_esitoImpegni(EsitoImpegniExtraBO _esitoImpegni) {
		this._esitoImpegniExtra = _esitoImpegni;
	}

	public EsitoImpegniExtraBO get_esitoImpegni() {
		return _esitoImpegniExtra;
	}

	public void set_selectedValue(EsitoImpegniExtraBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public EsitoImpegniExtraBO get_selectedValue() {
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
			_listCampagna = _esitoImpegniExtraEJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_listEsito(List<String> _listEsito) {
		this._listEsito = _listEsito;
	}

	public List<String> get_listEsito() {
		_listEsito = _esitoImpegniExtraEJBBean.getListaValori(Costanti.ANAGR_ESITO);
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
    	_esitoImpegniExtraEJBBean.filter(parametersList);
    }
    
    /**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new EsitoImpegniExtraBO();
		_esitoImpegniExtra = new EsitoImpegniExtraBO();
		_esitoImpegniExtraOld = new EsitoImpegniExtraBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_esitoImpegniExtraEJBBean.clearList();
    }
    
    public void updateNumDomandaPSR(AjaxActionEvent e) {
		/* ROCL 05/05/2020: 
		Ci viene chiesto di Valorizzare automaticamente numero della Domanda (PSR) non appena l'utente 
		inserisce CUAA e Campagna. Metodo chiamato sia dall'onChange di CUAA che dall'onChange di Campagna
		*/
		this._esitoImpegniExtra.set_domanda(new String());
		
		String cuaa = this._esitoImpegniExtra.get_cuaa();
		String campagna = this._esitoImpegniExtra.get_campagna();
		
		if (!cuaa.equals("") && !campagna.equals("")) {
			List<String> result = this._esitoImpegniExtraEJBBean.getListDomandePSR(cuaa, campagna);

			if(result.size() == 1) {
				this._esitoImpegniExtra.set_domanda(result.get(0));
				return;
			}
		}
		return;
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
				_listIntervento = _esitoImpegniExtraEJBBean.getListIntervento();
		return _listIntervento;
	}

	public void set_listSottointervento(List<String> _listSottointervento) {
		this._listSottointervento = _listSottointervento;
	}

	public List<String> get_listSottointervento() {
		if(_listSottointervento.size() == 0)
				_listSottointervento = _esitoImpegniExtraEJBBean.getListSottointervento();
		return _listSottointervento;
	}


	public List<String> get_listImpegno() {
		if(_listImpegno.size() == 0)
			_listImpegno = _esitoImpegniExtraEJBBean.getListImpegno();
		return _listImpegno;
	}

	public void set_listImpegno(List<String> _listImpegno) {
		this._listImpegno = _listImpegno;
	}

	public void set_listStato(List<String> _listStato) {
		this._listStato = _listStato;
	}

	public List<String> get_listStato() {
		_listStato = _esitoImpegniExtraEJBBean.getListaValori(Costanti.ANAGR_STATO);
		return _listStato;
	}

	public IEsitoImpegniExtra get_esitoImpegniExtraEJBBean() {
		return _esitoImpegniExtraEJBBean;
	}

	public void set_esitoImpegniExtraEJBBean(IEsitoImpegniExtra _esitoImpegniExtraEJBBean) {
		this._esitoImpegniExtraEJBBean = _esitoImpegniExtraEJBBean;
	}

	public EsitoImpegniExtraBO get_esitoImpegniExtra() {
		return _esitoImpegniExtra;
	}

	public void set_esitoImpegniExtra(EsitoImpegniExtraBO _esitoImpegniExtra) {
		this._esitoImpegniExtra = _esitoImpegniExtra;
	}

	public EsitoImpegniExtraBO get_esitoImpegniExtraOld() {
		return _esitoImpegniExtraOld;
	}

	public void set_esitoImpegniExtraOld(EsitoImpegniExtraBO _esitoImpegniExtraOld) {
		this._esitoImpegniExtraOld = _esitoImpegniExtraOld;
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

	public void set_disablePercRid(boolean _disablePercRidOPPAB) {
		this._disablePercRid = _disablePercRidOPPAB;
	}

	public boolean get_disablePercRid() {
		return _disablePercRid;
	}

	public void set_disableImportoRidRinunciaInsilato(
			boolean _disableImportoRidRinunciaInsilato) {
		this._disableImportoRidRinunciaInsilato = _disableImportoRidRinunciaInsilato;
	}

	public boolean is_disableImportoRidRinunciaInsilato() {
		return _disableImportoRidRinunciaInsilato;
	}

	public List<SelectItem> get_listReiterazioneRadioButton() {
		return _listReiterazioneRadioButton;
	}

	public void set_listReiterazioneRadioButton(List<SelectItem> _listReiterazioneRadioButton) {
		this._listReiterazioneRadioButton = _listReiterazioneRadioButton;
	}

	public List<SelectItem> get_listProgrAccReitRadioButton() {
		return _listProgrAccReitRadioButton;
	}

	public void set_listProgrAccReitRadioButton(List<SelectItem> _listProgrAccReitRadioButton) {
		this._listProgrAccReitRadioButton = _listProgrAccReitRadioButton;
	}

	
	public List<SelectItem> get_listInadempienzaGraveRadioButton() {
		return _listInadempienzaGraveRadioButton;
	}

	public void set_listInadempienzaGraveRadioButton(List<SelectItem> _listInadempienzaGraveRadioButton) {
		this._listInadempienzaGraveRadioButton = _listInadempienzaGraveRadioButton;
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

	public void set_listSegnalazioneRadioButton(List<SelectItem> _listSegnalazioneRadioButton) {
		this._listSegnalazioneRadioButton = _listSegnalazioneRadioButton;
	}

	public List<SelectItem> get_listApprovazioneRadioButton() {
		return _listApprovazioneRadioButton;
	}

	public void set_listApprovazioneRadioButton(List<SelectItem> _listApprovazioneRadioButton) {
		this._listApprovazioneRadioButton = _listApprovazioneRadioButton;
	}

	public List<SelectItem> get_listEsclusioneRadioButton() {
		return _listEsclusioneRadioButton;
	}

	public void set_listEsclusioneRadioButton(List<SelectItem> _listEsclusioneRadioButton) {
		this._listEsclusioneRadioButton = _listEsclusioneRadioButton;
	}
	

}
