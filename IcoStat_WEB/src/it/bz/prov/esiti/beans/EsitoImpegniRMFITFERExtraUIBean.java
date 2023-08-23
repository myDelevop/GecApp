package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.bobject.ElencoEsitoImpegniRMFITFERExtraBO;
import it.bz.prov.esiti.bobject.EsitoImpegniRMFITFERExtraBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.ibusiness.IEsitoImpegniRMFITFERExtra;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

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

/**
 * Bean UI (user interface) per la gestione degli esiti impegni RM FIT e FER Extracampione
 * 
 * @author GIMS
 *
 */
@ManagedBean(name = "esitoImpegniRMFITFERExtraBean")
@SessionScoped
public class EsitoImpegniRMFITFERExtraUIBean {
	@EJB
	private IEsitoImpegniRMFITFERExtra _esitoImpegniRMFITFERExtraEJBBean;
	private EsitoImpegniRMFITFERExtraBO _esitoImpegni ;
	private EsitoImpegniRMFITFERExtraBO _selectedValue;
	private int _selectedValueIndex;
	private EsitoImpegniRMFITFERExtraBO _esitoImpegniOld ; // serve per memorizzare la versione originale durante la modifica
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
	
	/**
	 * costruttore
	 */
	public EsitoImpegniRMFITFERExtraUIBean()
	{
//		_esitoImpegniRMFITFEREJBBean = new EsitoImpegniRMFITFERBean();
		_selectedValue = new EsitoImpegniRMFITFERExtraBO();
		_esitoImpegniOld = new EsitoImpegniRMFITFERExtraBO();
		_esitoImpegni = new EsitoImpegniRMFITFERExtraBO();
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
		_esitoImpegni = new EsitoImpegniRMFITFERExtraBO();
		_titoloPagina = "Creazione nuovo esito impegni RM FIT e FER Extracampione";
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
				List<String> result = _esitoImpegniRMFITFERExtraEJBBean.getListDomande(cuaa, campagna);
				if(result.size() == 0){
					_message = "Nessuna domanda presente per il CUAA e Campagna indicati nella form di ricerca";
					_messageVisible = true;
				}
				else 
				{
					_esitoImpegni.set_cuaa(cuaa);
					_esitoImpegni.set_campagna(campagna);
				}
				List<String> resultPSR = this._esitoImpegniRMFITFERExtraEJBBean.getListDomandePSR(cuaa, campagna);
				if(resultPSR.size() == 1) {
					this._esitoImpegni.set_domanda(resultPSR.get(0));
					//return;
				}
			}
		}
		
		_esitoImpegni.set_inadempienza_grave("NO");
		_esitoImpegni.set_segnalazione("SI");
		_esitoImpegni.set_esclusione("NO");
		_esitoImpegni.set_reiterazione("NO");
		_esitoImpegni.set_approvazione("NO");
		_esitoImpegni.set_esito_oppab("NEGATIVO");
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
		_selectedValue= _esitoImpegniRMFITFERExtraEJBBean.getElencoEsitoImpegni().get(_selectedValueIndex);
		_esitoImpegni = _selectedValue;
		_esitoImpegniOld = _esitoImpegni.clone();
		_titoloPagina = "Modifica esito impegni RM FIT e FER Extracampione";
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
		_selectedValue= _esitoImpegniRMFITFERExtraEJBBean.getElencoEsitoImpegni().get(_selectedValueIndex);
		_esitoImpegni = _selectedValue;
		OperationResultBO result = _esitoImpegniRMFITFERExtraEJBBean.cancellaEsito(_esitoImpegni); 
		if(result.get_result()) {
			_esitoImpegni = new EsitoImpegniRMFITFERExtraBO();
			_selectedValue = new EsitoImpegniRMFITFERExtraBO();
			_esitoImpegniOld = new EsitoImpegniRMFITFERExtraBO();
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
			result = _esitoImpegniRMFITFERExtraEJBBean.aggiungiEsito(_esitoImpegni);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _esitoImpegniRMFITFERExtraEJBBean.modificaEsito(_esitoImpegni);
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
		uploadBean.set_titoloPagina("Esito RM FIT/FER Extracampione - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_RMFITFER_EXTRACAMPIONE);
		uploadBean.set_ejbBean(_esitoImpegniRMFITFERExtraEJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";
	}
	
	/**
	 * esportazione dati
	 */
	public void esportaEsito()
	{
		String filename = Costanti.FILE_ESITO_RMFITFER_EXTRA;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_esitoImpegniRMFITFERExtraEJBBean.exportFile(externalContext.getResponseOutputStream());
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
			_esitoImpegni.set_esclusione("");
			_esitoImpegni.set_esclusione_note("");
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
		_listMisura = _esitoImpegniRMFITFERExtraEJBBean.getListMisuraForCampagna(campagna);
		_listMisura.add(0, "");
	}
	
	public void updateNumDomandaPSR(AjaxActionEvent e) {
		/* ROCL 05/05/2020: 
		Ci viene chiesto di Valorizzare automaticamente numero della Domanda (PSR) non appena l'utente 
		inserisce CUAA e Campagna. Metodo chiamato sia dall'onChange di CUAA che dall'onChange di Campagna
		*/
		this._esitoImpegni.set_domanda(new String());
		
		String cuaa = this._esitoImpegni.get_cuaa();
		String campagna = this._esitoImpegni.get_campagna();
		
		if (!cuaa.equals("") && !campagna.equals("")) {
			List<String> result = this._esitoImpegniRMFITFERExtraEJBBean.getListDomandePSR(cuaa, campagna);

			if(result.size() == 1) {
				this._esitoImpegni.set_domanda(result.get(0));
				return;
			}
		}
				
		return;
	}
	
	/**
	 * questa funzione non fa nulla, serve per aggiornare i render del bean
	 * @param e
	 */
	public void updateRender(AjaxActionEvent e) {
		return;
	}

	public void selezioneEsclusione(AjaxActionEvent e) {
		// ROCL %FER 100 se Esclusione SI
		
		Integer percFIT = 0;
		Integer percFER = Integer.parseInt(Costanti.VAL_100);

		if(this._esitoImpegni.get_esclusione().equals(Costanti.VAL_SI)) {
			this._esitoImpegni.set_percRidFER(Costanti.VAL_100);
		} 
		
		if(!_esitoImpegni.get_percRidFIT().isEmpty())
			percFIT = Integer.parseInt(_esitoImpegni.get_percRidFIT());

		Integer percTOT = percFER + percFIT;
		
		if (percTOT>100)
			percTOT = 100;
		
		this._esitoImpegni.set_percTotOPPAB(String.valueOf(percTOT));

		return;
	}

	
	public void selezioneRiduzioneFitFer(AjaxActionEvent e) {
		// ROCL importare Perc. TOT come Perc. FIT + Perc. FER
		Integer percFER = 0;
		Integer percFIT = 0;
		
		
		if(!_esitoImpegni.get_percRidFER().isEmpty())
			percFER = Integer.parseInt(_esitoImpegni.get_percRidFER());
		if(!_esitoImpegni.get_percRidFIT().isEmpty())
			percFIT = Integer.parseInt(_esitoImpegni.get_percRidFIT());
		
		Integer percTOT = percFER + percFIT;
		
		if (percTOT > 100)
			percTOT = 100;

		if (percTOT > 0) {
			this._esitoImpegni.set_percTotOPPAB(String.valueOf(percTOT));
		}

		return;
	}
	
    public void checkForWarningsRMFITFEREsitoImpegniExtra() { 
    	try {
        	FacesContext facesContext = FacesContext.getCurrentInstance();
        	List<String> warnings = _esitoImpegniRMFITFERExtraEJBBean.checkForWarningsEsitoImpegniExtra(this._esitoImpegni);
        	
        	for(String warn:warnings) {
            	facesContext.addMessage("esitoImpegniRMFITFERExtraBeanWarningMessages"
            			, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", warn));    		
        	}    		
    	} catch(Exception e) {
    		Utils.getLog().error(EsitoImpegniExtraUIBean.class.toString() + " - Errore durante il checkForWarnings: " + e.getMessage());
    		e.printStackTrace();
    	}
    }
	
    public List<EsitoImpegniRMFITFERExtraBO> getElencoEsitoImpegni()
    {
        return _esitoImpegniRMFITFERExtraEJBBean.getElencoEsitoImpegni();
    }

    public void setElencoEsitoImpegni(final ElencoEsitoImpegniRMFITFERExtraBO elencoEsitoImpegni)
    {
    	_esitoImpegniRMFITFERExtraEJBBean.setElencoEsitoImpegni(elencoEsitoImpegni);
    }
    
    
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData()
	{
		_esitoImpegniRMFITFERExtraEJBBean.loadData();
	}

	public void set_esitoImpegni(EsitoImpegniRMFITFERExtraBO _esitoImpegni) {
		this._esitoImpegni = _esitoImpegni;
	}

	public EsitoImpegniRMFITFERExtraBO get_esitoImpegni() {
		return _esitoImpegni;
	}

	public void set_selectedValue(EsitoImpegniRMFITFERExtraBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public EsitoImpegniRMFITFERExtraBO get_selectedValue() {
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
			_listCampagna = _esitoImpegniRMFITFERExtraEJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_listEsito(List<String> _listEsito) {
		this._listEsito = _listEsito;
	}

	public List<String> get_listEsito() {
		_listEsito = _esitoImpegniRMFITFERExtraEJBBean.getListaValori(Costanti.ANAGR_ESITO);
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
    	_esitoImpegniRMFITFERExtraEJBBean.filter(parametersList);
    }
    
    /**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new EsitoImpegniRMFITFERExtraBO();
		_esitoImpegni = new EsitoImpegniRMFITFERExtraBO();
		_esitoImpegniOld = new EsitoImpegniRMFITFERExtraBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_esitoImpegniRMFITFERExtraEJBBean.clearList();
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
			_listMisura = _esitoImpegniRMFITFERExtraEJBBean.getListMisura();
		return _listMisura;
	}

	public void set_listStato(List<String> _listStato) {
		this._listStato = _listStato;
	}

	public List<String> get_listStato() {
		_listStato = _esitoImpegniRMFITFERExtraEJBBean.getListaValori(Costanti.ANAGR_STATO);
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

	public List<SelectItem> get_listEsclusioneRadioButton() {
		return _listEsclusioneRadioButton;
	}

	public void set_listEsclusioneRadioButton(
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
