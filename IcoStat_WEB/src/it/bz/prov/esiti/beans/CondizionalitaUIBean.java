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

import it.bz.prov.esiti.bobject.CampioneBO;
import it.bz.prov.esiti.bobject.ElencoEsitoCondizionalitaAttoBO;
import it.bz.prov.esiti.bobject.ElencoEsitoCondizionalitaBO;
import it.bz.prov.esiti.bobject.EsitoCondizionalitaAttoBO;
import it.bz.prov.esiti.bobject.EsitoCondizionalitaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.ibusiness.ICondizionalita;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

/**
 * Bean UI (user interface) per la gestione degli esiti di condizionalità
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "esitoCondBean")
@SessionScoped
public class CondizionalitaUIBean {
	@EJB
	private ICondizionalita _condizionalitaEJBBean;
	private EsitoCondizionalitaAttoBO _esitoAtto;
	private EsitoCondizionalitaAttoBO _selectedValueAtto;
	private EsitoCondizionalitaAttoBO _esitoAttoOld;
	private EsitoCondizionalitaBO _esito;
	private EsitoCondizionalitaBO _selectedValue;
	private EsitoCondizionalitaBO _esitoOld;
	private String _action;
	private List<String> _listCampagna;
	private List<String> _listEsito;
	private List<String> _listStato;
	private List<String> _listRespControllo;
	private List<String> _listAzioneCorrEseg;
	private List<String> _listImpegnoRipristinoEseg;
	private List<String> _listCodCond;
	private List<String> _listAttoContr;
	private List<String> _listTipoControllo;
	private List<SelectItem> _listRadioButton;
	private List<SelectItem> _listRadioButtonRespControllo;
	private List<SelectItem> _listRadioButton135;	
	private List<SelectItem> _listRadioButtonProgrAcc;
	private List<SelectItem> _listSegnalazioneRadioButton;
	private List<SelectItem> _listApprovazioneRadioButton;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private String _datiDaVisualizzare;
	private List<String> _listDatiDaVisualizzare;
	private boolean _readOnly;
	private String _showMessageDataControllo;
	private String _exportLayoutSelection;
	private boolean _exportLayoutVisible;
	private List<SelectItem> _listLayout;
	private List<String> _listElementRenderInfrazione;
	private String _disableComponent;
	private int _selectedValueIndex;
	private int _selectedValueAttoIndex;
	
	
	/**
	 * costruttore
	 */
	public CondizionalitaUIBean()
	{
//		_condizionalitaEJBBean = new CondizionalitaBean();
		_esitoAtto = new EsitoCondizionalitaAttoBO();
		_selectedValueAtto = new EsitoCondizionalitaAttoBO();
		_esitoAttoOld = new EsitoCondizionalitaAttoBO();
		_action = Costanti.ACTION_VIEW;
		_esito = new EsitoCondizionalitaBO();
		_esitoOld = new EsitoCondizionalitaBO();
		_selectedValue = new  EsitoCondizionalitaBO();
		_listCampagna = new Vector<String>();
		_listEsito = new ArrayList<String>();
		_listStato = new ArrayList<String>();
		_listRespControllo = new ArrayList<String>();
		_listAzioneCorrEseg = new ArrayList<String>();
		_listImpegnoRipristinoEseg = new ArrayList<String>();
		_listCodCond = new ArrayList<String>();
		_listAttoContr = new ArrayList<String>();
		_listTipoControllo = new ArrayList<String>();
		
		_listRadioButton = new ArrayList<SelectItem>();
		_listRadioButton.add(new SelectItem("SI", "Si"));
		_listRadioButton.add(new SelectItem("NO", "No"));
		
		_listRadioButton135 = new ArrayList<SelectItem>();
		_listRadioButton135.add(new SelectItem("1", "1"));
		_listRadioButton135.add(new SelectItem("3", "3"));	
		_listRadioButton135.add(new SelectItem("5", "5"));
		_listRadioButton135.add(new SelectItem("", "Vuoto"));		
		
		_listRadioButtonRespControllo = new ArrayList<SelectItem>();
		_listRadioButtonRespControllo.add(new SelectItem("OP", "OP"));
		_listRadioButtonRespControllo.add(new SelectItem("SSVV", "SSVV"));	
		
		_listRadioButtonProgrAcc = new ArrayList<SelectItem>();
		_listRadioButtonProgrAcc.add(new SelectItem("1", "1"));
		_listRadioButtonProgrAcc.add(new SelectItem("2", "2"));
		_listRadioButtonProgrAcc.add(new SelectItem("3", "3"));
		_listRadioButtonProgrAcc.add(new SelectItem("4", "4"));
		_listRadioButtonProgrAcc.add(new SelectItem("5", "5"));
		_listRadioButtonProgrAcc.add(new SelectItem("", "Vuoto"));
		
		_titoloPagina = "";
		_message = "";
		_messageVisible = false;
		_datiDaVisualizzare = Costanti.DATI_COND_ESITI;
		_listDatiDaVisualizzare = new ArrayList<String>();
		_listDatiDaVisualizzare.add(Costanti.DATI_COND_ESITI);
		_listDatiDaVisualizzare.add(Costanti.DATI_COND_MATR_AMM_IDEALE);
		_readOnly = false;
		_showMessageDataControllo = "none";
		_exportLayoutVisible = false;
		_listLayout = new ArrayList<SelectItem>();
		_listLayout.add(new SelectItem(Costanti.LAYOUT_COND_ATTO_ESITO));
		_listLayout.add(new SelectItem(Costanti.LAYOUT_COND_ATTO_CONTR));
		_listLayout.add(new SelectItem(Costanti.LAYOUT_COND_ATTO_CONTR_VET));
		_exportLayoutSelection = _listLayout.get(0).getValue().toString();
		_disableComponent="false";
		
		// lista degli elementi grafici che devono essere renderizzati quando si cambio il campo infrazione
		_listElementRenderInfrazione = new ArrayList<String>();
		//_listElementRenderInfrazione.add("respControllo");
		_listElementRenderInfrazione.add("richAzCorr");
		_listElementRenderInfrazione.add("selectAzCorrEsegCondAtto");
		_listElementRenderInfrazione.add("impRiprRich");
		_listElementRenderInfrazione.add("selectImpRiprEsegCondAtto");
		_listElementRenderInfrazione.add("inadempienza");
		_listElementRenderInfrazione.add("negligenza");
		_listElementRenderInfrazione.add("intenzionalita");
		_listElementRenderInfrazione.add("reiterazione");
		_listElementRenderInfrazione.add("ammonizione");
		_listElementRenderInfrazione.add("selectTipoControlloCondAtto");
		_listElementRenderInfrazione.add("autodichGasolio");
		_listElementRenderInfrazione.add("autodichTrattNonChimico");
		_listElementRenderInfrazione.add("portata");
		_listElementRenderInfrazione.add("portataNote");
		_listElementRenderInfrazione.add("gravita");
		_listElementRenderInfrazione.add("gravitaNote");
		_listElementRenderInfrazione.add("durata");
		_listElementRenderInfrazione.add("durataNote");
		_listElementRenderInfrazione.add("progrAccReit");
		_listElementRenderInfrazione.add("progrAccIntenz");
		_listElementRenderInfrazione.add("codViolazIntenzImp");
		_listElementRenderInfrazione.add("percRid");
		_listElementRenderInfrazione.add("selectEsito2ContrCondAtto");
		_listElementRenderInfrazione.add("data2Controllo");
		_listElementRenderInfrazione.add("approvazione_cond_atto");
		_listElementRenderInfrazione.add("segnalazione_cond_Atto");
		_listElementRenderInfrazione.add("IntenzNote_cond_atto");

		
		_listSegnalazioneRadioButton = new ArrayList<SelectItem>();
		_listSegnalazioneRadioButton.add(new SelectItem("SI", "Si"));
		_listSegnalazioneRadioButton.add(new SelectItem("NO", "No"));

		_listApprovazioneRadioButton = new ArrayList<SelectItem>();
		_listApprovazioneRadioButton.add(new SelectItem("SI", "Si"));
		_listApprovazioneRadioButton.add(new SelectItem("NO", "No"));
		
	}

	/*************************************************************************
	 * 		GESTIONE ESITO PER ATTO
	 *************************************************************************/
	
	/**
	 * aggiunge un esito per atto di condizionalita alla lista
	 */
	public void aggiungiEsitoAtto()
	{
		_action = Costanti.ACTION_INSERT_ATTO;
		_esitoAtto = new EsitoCondizionalitaAttoBO();
		_titoloPagina = "Creazione nuovo esito di condizionalità per atto";
		_readOnly = false;
		
		// se nel box di ricerca sono inseriti cuaa o campagna si pre-compila la form di inserimento
		FacesContext context = FacesContext.getCurrentInstance();
		SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
		String cuaa= searchBean.getCuaa();
		String campagna= searchBean.getCampagna();
		// per evitare di prendere un cuaa che é una substring (anche così la stringa potrebbe non 
		//corrispondere a un cuaa, ma é una richiesta di Lars)
		if(cuaa.length() > 10 )	{
			if(campagna.equals("")) _esitoAtto.set_cuaa(cuaa);
			if(!campagna.equals("")){
				List<String> result = _condizionalitaEJBBean.getListDomande(cuaa, campagna);
				if(result.size() == 0){
					_message = "Nessuna domanda presente per il CUAA e Campagna indicati nella form di ricerca";
					_messageVisible = true;
				}
				else 
				{
					_esitoAtto.set_cuaa(cuaa);
					_esitoAtto.set_campagna(campagna);
				}
			}
		}
	}
	
	/**
	 * modifica un esito di un atto nella lista
	 */
	public void modificaEsitoAtto()
	{
		// se non é stato selezionato nulla
		if(_selectedValueAtto == null || _selectedValueAtto.get_cuaa().equals("")) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_action = Costanti.ACTION_MODIFY_ATTO;
		if(_selectedValueAtto.get_cuaa().equals("")) return;
		_esitoAtto = _selectedValueAtto;		
		_esitoAttoOld = _esitoAtto.clone();
		if(_esitoAtto.get_data_1_contr() == null) {
			aggiornaDati(null);
			if(_esitoAtto.get_data_1_contr() != null) {
				_showMessageDataControllo = "inline";
			}
		} else _showMessageDataControllo = "none";
		_titoloPagina = "Modifica esito di condizionalità per atto";
		// se non c'è infrazione disabilita i suoi componenti grafici al caricamento della pagina
		if(_esitoAtto.get_infrazione().equalsIgnoreCase("no")) _disableComponent="true";
		_readOnly = true;
	}
	
	/**
	 * cancella un esito di un atto dalla lista
	 */
	public void cancellaEsitoAtto()
	{
		// se non é stato selezionato nulla
		if(_selectedValueAtto == null || _selectedValueAtto.get_cuaa().equals("") || _selectedValueAttoIndex == -1) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_selectedValueAtto = _condizionalitaEJBBean.get_elencoEsitoAtto().get_listCondEsitoAtto().get(_selectedValueAttoIndex);
		_esitoAtto = _selectedValueAtto;
		OperationResultBO result = _condizionalitaEJBBean.cancellaEsitoAtto(_esitoAtto);
		if(result.get_result()) {
			_esitoAtto = new EsitoCondizionalitaAttoBO();
			_selectedValueAtto = new EsitoCondizionalitaAttoBO();
			_esitoAttoOld = new EsitoCondizionalitaAttoBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * salva un esito di un atto di condizionalità nella lista
	 */
	public void salvaEsitoAtto()
	{
		OperationResultBO result = null;
		if(_action.equals(Costanti.ACTION_INSERT_ATTO)){
			result = _condizionalitaEJBBean.aggiungiEsitoAtto(_esitoAtto);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY_ATTO)){
			result = _condizionalitaEJBBean.modificaEsitoAtto(_esitoAtto);
		}
		if(result.get_result()) _action=Costanti.ACTION_VIEW;
		_message = result.get_message();
		_messageVisible = true;
	}
	
	/**
	 * annulla la creazione di un esito per atto
	 */
	public void annullaEsitoAtto()
	{
		if(_esitoAttoOld != null) _esitoAtto.recovery(_esitoAttoOld);
		_action = Costanti.ACTION_VIEW;
	}
	
	/**
	 * esportazione dati
	 */
	public void esportaEsitoAtto()
	{
		String filename = Costanti.FILE_ESITO_COND_ATTO;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_condizionalitaEJBBean.exportFileAtto(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	/**
	 * importazione dati
	 */
	public String importaEsitoAtto()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UploadUIBean uploadBean = (UploadUIBean) context.getApplication().evaluateExpressionGet(context, "#{uploadBean}", Object.class);
		uploadBean.set_titoloPagina("Esiti di condizionalità per atto - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_COND_ATTO);
		uploadBean.set_ejbBean(_condizionalitaEJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";
	}
	
	/**
	 * visualizza il popup per l'esportazione del layout
	 */
	public void visualizzaMenuExport()
	{
		_exportLayoutVisible = true;
	}
	
	/**
	 * visualizza il popup per la scelta dell'importazione del file
	 */
	public void esportaLayout()
	{
		String filename = "";
		if(_exportLayoutSelection.equals(_listLayout.get(0).getValue().toString())) filename = Costanti.FILE_ESITO_COND_ATTO;
		else if(_exportLayoutSelection.equals(_listLayout.get(1).getValue().toString())) filename = Costanti.FILE_ESITO_COND_MATR;
		else if(_exportLayoutSelection.equals(_listLayout.get(2).getValue().toString())) filename = Costanti.FILE_ESITO_COND_ATTO_VET;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_condizionalitaEJBBean.exportLayout(externalContext.getResponseOutputStream(), _exportLayoutSelection);
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	    _exportLayoutVisible = false;
	}
	
	/**
	 * chiude il popup per la scelta dell'importazione del file
	 */
	public void chiudiEsportaLayout()
	{
		_exportLayoutVisible = false;
	}
	
	
	/*************************************************************************
	 * 		GESTIONE ESITO PER AZIENDA
	 *************************************************************************/
	
	/**
	 * aggiunge un esito di condizionalita alla lista
	 */
	public void aggiungiEsito()
	{
		_action = Costanti.ACTION_INSERT;
		_esito = new EsitoCondizionalitaBO();
		_titoloPagina = "Creazione nuovo esito di condizionalità per azienda";
		_readOnly = false;
		
		// se nel box di ricerca sono inseriti cuaa o campagna si pre-compila la form di inserimento
		FacesContext context = FacesContext.getCurrentInstance();
		SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
		String cuaa= searchBean.getCuaa();
		String campagna= searchBean.getCampagna();
		// per evitare di prendere un cuaa che é una substring (anche così la stringa potrebbe non 
		//corrispondere a un cuaa, ma é una richiesta di Lars)
		if(cuaa.length() > 10 )	{
			if(campagna.equals("")) _esito.set_cuaa(cuaa);
			if(!campagna.equals("")){
				List<String> result = _condizionalitaEJBBean.getListDomande(cuaa, campagna);
				if(result.size() == 0){
					_message = "Nessuna domanda presente per il CUAA e Campagna indicati nella form di ricerca";
					_messageVisible = true;
				}
				else 
				{
					_esito.set_cuaa(cuaa);
					_esito.set_campagna(campagna);
				}
			}
		}
	}
	
	/**
	 * modifica un esito nella lista
	 */
	public void modificaEsito()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_cuaa().equals("")|| _selectedValueIndex == -1) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_action = Costanti.ACTION_MODIFY;
		if(_selectedValue.get_cuaa().equals("")) return ;
		
		_esito = _condizionalitaEJBBean.get_elencoEsito().get_listCondEsito().get(_selectedValueIndex);
		_esitoOld = _esito.clone();
		if(_esito.get_dataControllo() == null) {
			aggiornaDati(null);
			if(_esito.get_dataControllo() != null) {
				_showMessageDataControllo = "inline";
			}
		} else _showMessageDataControllo = "none";

		_titoloPagina = "Modifica esito di condizionalità per azienda";
		_readOnly = true;
	}
	
	/**
	 * cancella un esito dalla lista
	 */
	public void cancellaEsito()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_cuaa().equals("")) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_selectedValue = _condizionalitaEJBBean.get_elencoEsito().get_listCondEsito().get(_selectedValueIndex);
		_esito = _selectedValue;
		OperationResultBO result = _condizionalitaEJBBean.cancellaEsito(_esito);
		if(result.get_result()) {
			_esito = new EsitoCondizionalitaBO();
			_selectedValue = new EsitoCondizionalitaBO();
			_esitoOld = new EsitoCondizionalitaBO();
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
			result = _condizionalitaEJBBean.aggiungiEsito(_esito);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _condizionalitaEJBBean.modificaEsito(_esito);
		}
		if(result.get_result()) _action=Costanti.ACTION_VIEW;
		_message = result.get_message();
		_messageVisible = true;
		_showMessageDataControllo = "none";
	}
	
	/**
	 * annulla la creazione di un esito 
	 */
	public void annullaEsito()
	{
		if(_esitoOld != null) _esito.recovery(_esitoOld);
		_action = Costanti.ACTION_VIEW;
	}
	
	/**
	 * esportazione dati
	 */
	public void esportaEsito()
	{
		String filename = Costanti.FILE_ESITO_COND_AZIENDA;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_condizionalitaEJBBean.exportFileAzienda(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	/**
	 * importazione dati
	 */
	public String importaEsito()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UploadUIBean uploadBean = (UploadUIBean) context.getApplication().evaluateExpressionGet(context, "#{uploadBean}", Object.class);
		uploadBean.set_titoloPagina("Esiti condizionalità per azienda - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_COND_AZIENDA);
		uploadBean.set_ejbBean(_condizionalitaEJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";
	}
	
	/*************************************************************************
	 * 		GESTIONE GENERICA
	 *************************************************************************/
	
	 /**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new EsitoCondizionalitaBO();
		_selectedValueAtto = new EsitoCondizionalitaAttoBO();
		_esito = new EsitoCondizionalitaBO();
		_esitoOld = new EsitoCondizionalitaBO();
		_esitoAtto = new EsitoCondizionalitaAttoBO();
		_esitoAttoOld = new EsitoCondizionalitaAttoBO();
	}
	
	/**
     * Carica i dati in tabella
     */
    public void loadData()
    {
    	_condizionalitaEJBBean.loadData();
    	_condizionalitaEJBBean.loadDataAtto();
    }
    
    /**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_condizionalitaEJBBean.clearList();
    }
	

    /**
     * gestione del bottone della list box per la visualizzazione dei dati
     */
    public void visualizzaDati()
    {
    	FacesContext context = FacesContext.getCurrentInstance();
    	SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
    	boolean search = searchBean.isSearchActive();
		if(search) searchBean.doSearch();
		else 
		{
			// ATTENZIONE: I bean non sono già caricati in sessione ma vengono caricati la prima volta che si richiama il metodo evaluateExpressionGet
			if(_datiDaVisualizzare.equals(Costanti.DATI_COND_MATR_AMM_IDEALE)) 
			{
				context.getApplication().evaluateExpressionGet(context, "#{matrAmmIdealeBean}", Object.class);
				//matrBean.loadData();
			}
		}
    }
	

	/**
	 * funzione che aggiorna i dati in base alla presenza di cuaa e campagna
	 * @param e
	 */
	public void aggiornaDati(AjaxActionEvent e){
		if(_action.equals(Costanti.ACTION_INSERT) || _action.equals(Costanti.ACTION_MODIFY)){
			if(!_esito.get_cuaa().equals("") && !_esito.get_campagna().equals("")) {
				String data = _condizionalitaEJBBean.getDataControllo(_esito.get_cuaa(), _esito.get_campagna());
				_esito.set_dataControllo(Utils.getDate(data));
			}
		}
		else if(_action.equals(Costanti.ACTION_INSERT_ATTO) || _action.equals(Costanti.ACTION_MODIFY_ATTO)){
			if(!_esitoAtto.get_cuaa().equals("") && !_esitoAtto.get_campagna().equals("")) {
				String data = _condizionalitaEJBBean.getDataControllo(_esitoAtto.get_cuaa(), _esitoAtto.get_campagna());
				_esitoAtto.set_data_1_contr(Utils.getDate(data));
							
				List<CampioneBO> campione = _condizionalitaEJBBean.getListCampioneCondExtra(_esitoAtto.get_cuaa(), _esitoAtto.get_campagna());
				if(campione.size()>0) {
					_esitoAtto.set_resp_controllo(Costanti.VAL_RESP_CONTR_OP);
					_esitoAtto.set_tipoControllo(Costanti.VAL_CAMP_DOMINIO_CAMP_ALTRI_CONTR);
				}
			}
		}				
	}
	
	/**
	 * Funzione che aggiorna la percentuale di riduzione della DU in base a quella del PSR.
	 * @param e
	 */
	public void aggiornaPercRidDU(AjaxActionEvent e){
		_esito.set_ridPercDU(_esito.get_ridPercPSR());
	}
	
	/**
	 * funzione che definisce la lista di atti controllati a partire dalla campagna
	 * @param e
	 */
	public void settingAttoContr(AjaxActionEvent e){
		String campagna = _esitoAtto.get_campagna();
		List<String> listAttoCond = _condizionalitaEJBBean.getListAttoContrForCampagna(campagna);
		set_listAttoContr(listAttoCond);
	}
	
	
		/**
	 * funzione che definisce la lista dei codici condizionalità a partire dalla campagna
	 * @param e
	 */
	public void settingCodiceCond(AjaxActionEvent e){
		String campagna = _esitoAtto.get_campagna();
		List<String> listCodCond = _condizionalitaEJBBean.getListCodCondForCampagna(campagna);
		set_listCodCond(listCodCond);
	}
	
	
	/**
	 * funzione che calcola il codice condizionalità dato un atto
	 * @param e
	 */
	public void attoChanged(AjaxActionEvent e){		
		// calcola in automatico il codice di condizionalità
		String codiceCod = _condizionalitaEJBBean.getCodiceCond(_esitoAtto.get_atto_contr());
		_esitoAtto.set_cod_cond(codiceCod);
	}
	
	/**
	 * funzione che compila diversi dati a default quando viene selezionato il valore SI su infrazione
	 * @param e
	 */
	public void infrazioneCompilaDatiDefault(AjaxActionEvent e){		
		// calcola in automatico il codice di condizionalità
		String infrazione = _esitoAtto.get_infrazione();
		if(infrazione.equalsIgnoreCase("SI")){
			// Modifica richiesta da Rainer in punto 2.3.2 2017 V1.5 
			//_esitoAtto.set_resp_controllo("OP");
			_esitoAtto.set_rich_az_corr("NO");
			_esitoAtto.set_az_corr_eseguita("NON RICHIESTA");
			_esitoAtto.set_rich_imp_ripr("NO");
			_esitoAtto.set_imp_ripr_eseguito("NON RICHIESTO");
			_esitoAtto.set_inadempienza("NO");
			_esitoAtto.set_negligenza("SI");
			_esitoAtto.set_intenzionalita("NO");
			_esitoAtto.set_reiterazione("NO");
			_esitoAtto.set_ammonizione("NO");
			_esitoAtto.set_segnalazione("SI");
			_esitoAtto.set_approvazione("NO");
			_esitoAtto.set_tipoControllo("1-Controlli campione di condizionalita");		
			_disableComponent="false";
		}
		else { // quando vale NO
//			_esitoAtto.set_resp_controllo(""); questo lo lasciamo valorizzato perchè é comunque necessario
//			_esitoAtto.set_tipoControllo(""); questo lo lasciamo valorizzato perchè é comunque necessario
			_esitoAtto.set_rich_az_corr("");
			_esitoAtto.set_az_corr_eseguita("");
			_esitoAtto.set_rich_imp_ripr("");
			_esitoAtto.set_imp_ripr_eseguito("");			
			_esitoAtto.set_data_2_contr(null);
			_esitoAtto.set_esito_2_contr("");
			_esitoAtto.set_inadempienza("");
			_esitoAtto.set_negligenza("");
			_esitoAtto.set_intenzionalita("");
			_esitoAtto.set_intenzionalita_note("");
			_esitoAtto.set_progr_accert_intenz("");
			_esitoAtto.set_reiterazione("");
			_esitoAtto.set_progr_Accert_reit("");
			_esitoAtto.set_portata("");
			_esitoAtto.set_portata_note("");
			_esitoAtto.set_gravita("");
			_esitoAtto.set_gravita_note("");
			_esitoAtto.set_durata("");
			_esitoAtto.set_durata_note("");
			_esitoAtto.set_perc_rid("");
			_esitoAtto.set_ammonizione("");
			_esitoAtto.set_segnalazione("");
			_esitoAtto.set_approvazione("");
			_disableComponent="true";
		}
	}

	/**
	 * questa funzione non fa nulla, serve per aggiornare i render del bean
	 * @param e
	 */
	public void updateRender(AjaxActionEvent e) {
		return;
	}

	
	public void selezioneRespControllo(AjaxActionEvent e) {
		// ROCL valorizzare alcuni campi se si OP come Resp Controllo
		if(this._esitoAtto.get_resp_controllo().equals(Costanti.VAL_RESP_CONTR_OP)) {
			this._esitoAtto.set_tipoControllo(Costanti.VAL_CAMP_DOMINIO_CAMP_ALTRI_CONTR);
		} 
		return;
	}
	
	public void selezioneIntenzionalita(AjaxActionEvent e) {
		// ROCL valorizzare alcuni campi se si OP come Resp Controllo
		if(this._esitoAtto.get_intenzionalita().equals(Costanti.VAL_SI)) {
			this._esitoAtto.set_perc_rid(Costanti.VAL_20);
		} 
		return;
	}
	
    public void esitoCondAttoCheckForWarnings() { 
    	try {
        	FacesContext facesContext = FacesContext.getCurrentInstance();
        	List<String> warnings = _condizionalitaEJBBean.esitoCondAttoCheckForWarnings(this._esitoAtto);
        	
        	for(String warn:warnings) {
            	facesContext.addMessage("esitoCondAttoWarningMessages"
            			, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", warn));    		
        	}    		
    	} catch(Exception e) {
    		Utils.getLog().error(EsitoImpegniExtraUIBean.class.toString() + " - Errore durante il checkForWarnings: " + e.getMessage());
    		e.printStackTrace();
    	}
    }

	
	/***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
	

    public List<EsitoCondizionalitaAttoBO> get_elencoEsitoAtto()
    {
    	return _condizionalitaEJBBean.get_elencoEsitoAtto().get_listCondEsitoAtto();
    }

    public void set_elencoEsitoAtto(final ElencoEsitoCondizionalitaAttoBO elencoCondizionalitaEsitoAtto)
    {
    	_condizionalitaEJBBean.set_elencoEsitoAtto(elencoCondizionalitaEsitoAtto);
    }
    
    public List<EsitoCondizionalitaBO> get_elencoEsito()
    {
    	return _condizionalitaEJBBean.get_elencoEsito().get_listCondEsito();
    }

    public void set_elencoEsito(final ElencoEsitoCondizionalitaBO elencoCondizionalitaEsito)
    {
    	_condizionalitaEJBBean.set_elencoEsito(elencoCondizionalitaEsito);
    }

	public void set_selectedValueAtto(EsitoCondizionalitaAttoBO _selectedValue) {
		this._selectedValueAtto = _selectedValue;
	}

	public EsitoCondizionalitaAttoBO get_selectedValueAtto() {
		return _selectedValueAtto;
	}

	public void set_action(String _action) {
		this._action = _action;
	}

	public String get_action() {
		return _action;
	}

	public void set_esitoAtto(EsitoCondizionalitaAttoBO _esitoAtto) {
		this._esitoAtto = _esitoAtto;
	}

	public EsitoCondizionalitaAttoBO get_esitoAtto() {
		return _esitoAtto;
	}

	public void set_selectedValue(EsitoCondizionalitaBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public EsitoCondizionalitaBO get_selectedValue() {
		return _selectedValue;
	}

	public void set_esito(EsitoCondizionalitaBO _esito) {
		this._esito = _esito;
	}

	public EsitoCondizionalitaBO get_esito() {
		return _esito;
	}

	public void set_listCampagna(Vector<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	public List<String> get_listCampagna() {
		if(_listCampagna.size()==0)
			_listCampagna = _condizionalitaEJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_listEsito(List<String> _listEsito) {
		this._listEsito = _listEsito;
	}

	public List<String> get_listEsito() {
		_listEsito = _condizionalitaEJBBean.getListaValori(Costanti.ANAGR_ESITO);
		return _listEsito;
	}

	public void set_listStato(List<String> _listStato) {
		this._listStato = _listStato;
	}

	public List<String> get_listStato() {
		_listStato = _condizionalitaEJBBean.getListaValori(Costanti.ANAGR_STATO);
		return _listStato;
	}

	public void set_listRespControllo(List<String> _listRespControllo) {
		this._listRespControllo = _listRespControllo;
	}

	public List<String> get_listRespControllo() {
		_listRespControllo = _condizionalitaEJBBean.getListaValori(Costanti.ANAGR_RESPONSABILE_CONTROLLO);
		return _listRespControllo;
	}

	public void set_listAzioneCorrEseg(List<String> _listAzioneCorrEseg) {
		this._listAzioneCorrEseg = _listAzioneCorrEseg;
	}

	public List<String> get_listAzioneCorrEseg() {
		_listAzioneCorrEseg = _condizionalitaEJBBean.getListaValori(Costanti.ANAGR_AZIONE_CORRETTIVA_ESEGUITA);
		return _listAzioneCorrEseg;
	}

	public void set_listImpegnoRipristinoEseg(
			List<String> _listImpegnoRipristinoEseg) {
		this._listImpegnoRipristinoEseg = _listImpegnoRipristinoEseg;
	}

	public List<String> get_listImpegnoRipristinoEseg() {
		_listImpegnoRipristinoEseg = _condizionalitaEJBBean.getListaValori(Costanti.ANAGR_IMPEGNO_RIPRISTINO_ESEGUITO);
		return _listImpegnoRipristinoEseg;
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
    	_condizionalitaEJBBean.filter(parametersList);
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

	public void set_listCodCond(List<String> _listCodCond) {
		this._listCodCond = _listCodCond;
	}

	public List<String> get_listCodCond() {
		if(_listCodCond.size() == 0)
			_listCodCond = _condizionalitaEJBBean.getListaCodCond();
		return _listCodCond;
	}

	public void set_listAttoContr(List<String> _listAttoContr) {
		this._listAttoContr = _listAttoContr;
	}

	public List<String> get_listAttoContr() {
		if(_listAttoContr.size() == 0)
				_listAttoContr = _condizionalitaEJBBean.getListaAttoCond();
		return _listAttoContr;
	}

	public void set_datiDaVisualizzare(String _datiDaVisualizzare) {
		this._datiDaVisualizzare = _datiDaVisualizzare;
	}

	public String get_datiDaVisualizzare() {
		return _datiDaVisualizzare;
	}

	public void set_listDatiDaVisualizzare(List<String> _listDatiDaVisualizzare) {
		this._listDatiDaVisualizzare = _listDatiDaVisualizzare;
	}

	public List<String> get_listDatiDaVisualizzare() {
		return _listDatiDaVisualizzare;
	}

	public void set_readOnly(boolean _readOnly) {
		this._readOnly = _readOnly;
	}

	public boolean is_readOnly() {
		return _readOnly;
	}

	public void set_exportLayoutSelection(String _exportLayoutSelection) {
		this._exportLayoutSelection = _exportLayoutSelection;
	}

	public String get_exportLayoutSelection() {
		return _exportLayoutSelection;
	}

	public void set_exportLayoutVisible(boolean _exportLayoutVisible) {
		this._exportLayoutVisible = _exportLayoutVisible;
	}

	public boolean is_exportLayoutVisible() {
		return _exportLayoutVisible;
	}

	public void set_listLayout(List<SelectItem> _listLayout) {
		this._listLayout = _listLayout;
	}

	public List<SelectItem> get_listLayout() {
		return _listLayout;
	}

	public void set_listTipoControllo(List<String> _listTipoControllo) {
		this._listTipoControllo = _listTipoControllo;
	}

	public List<String> get_listTipoControllo() {
		_listTipoControllo = _condizionalitaEJBBean.getListaValori(Costanti.ANAGR_TIPO_CONTROLLO_COND);
		return _listTipoControllo;
	}

	public void set_listRadioButton(List<SelectItem> _listRadioButton) {
		this._listRadioButton = _listRadioButton;
	}

	public List<SelectItem> get_listRadioButton() {
		return _listRadioButton;
	}

	public void set_listRadioButtonRespControllo(
			List<SelectItem> _listRadioButtonRespControllo) {
		this._listRadioButtonRespControllo = _listRadioButtonRespControllo;
	}

	public List<SelectItem> get_listRadioButtonRespControllo() {
		return _listRadioButtonRespControllo;
	}

	public void set_listRadioButton135(List<SelectItem> _listRadioButton135) {
		this._listRadioButton135 = _listRadioButton135;
	}

	public List<SelectItem> get_listRadioButton135() {
		return _listRadioButton135;
	}

	public void set_listRadioButtonProgrAcc(List<SelectItem> _listRadioButtonProgrAcc) {
		this._listRadioButtonProgrAcc = _listRadioButtonProgrAcc;
	}

	public List<SelectItem> get_listRadioButtonProgrAcc() {
		return _listRadioButtonProgrAcc;
	}

	public void set_listElementRenderInfrazione(
			List<String> _listElementRenderInfrazione) {
		this._listElementRenderInfrazione = _listElementRenderInfrazione;
	}

	public List<String> get_listElementRenderInfrazione() {
		return _listElementRenderInfrazione;
	}

	public void set_disableComponent(String _disableComponent) {
		this._disableComponent = _disableComponent;
	}

	public String get_disableComponent() {
		return _disableComponent;
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

	public void set_selectedValueIndex(int _selectedValueIndex) {
		this._selectedValueIndex = _selectedValueIndex;
	}

	public int get_selectedValueIndex() {
		return _selectedValueIndex;
	}

	public int get_selectedValueAttoIndex() {
		return _selectedValueAttoIndex;
	}

	public void set_selectedValueAttoIndex(int _selectedValueAttoIndex) {
		this._selectedValueAttoIndex = _selectedValueAttoIndex;
	}

	public String get_showMessageDataControllo() {
		return _showMessageDataControllo;
	}

	public void set_showMessageDataControllo(String _showMessageDataControllo) {
		this._showMessageDataControllo = _showMessageDataControllo;
	}

}
