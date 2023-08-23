package it.bz.prov.esiti.beans;

import it.bz.prov.esiti.bobject.DomandaBO;
import it.bz.prov.esiti.bobject.ElencoEsitoArt68BO;
import it.bz.prov.esiti.bobject.EsitoArt68BO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.ibusiness.IEsitoArt68;
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
 * Bean UI (user interface) per la gestione degli esiti impegni
 * 
 * @author bpettazzoni
 * 
 */
@ManagedBean(name = "esitoArt68Bean")
@SessionScoped
public class EsitoArt68UIBean {
	@EJB
	private IEsitoArt68 _esitoArt68EJBBean;
	private EsitoArt68BO _esitoArt68;
	private EsitoArt68BO _selectedValue;
	private int _selectedValueIndex;
	private EsitoArt68BO _esitoArt68Old; // serve per memorizzare la versione
											// originale durante la modifica
	private String _action;
	private List<String> _listCampagna;
	private List<String> _listEsito;
	private List<String> _listIntervento;
	private List<String> _listStato;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;

	/**
	 * costruttore
	 */
	public EsitoArt68UIBean() {
//		_esitoArt68EJBBean = new EsitoArt68Bean();
		_selectedValue = new EsitoArt68BO();
		_esitoArt68Old = new EsitoArt68BO();
		_esitoArt68 = new EsitoArt68BO();
		_action = Costanti.ACTION_VIEW;
		_listCampagna = new Vector<String>();
		_listEsito = new ArrayList<String>();
		_listIntervento = new ArrayList<String>();
		_listIntervento.add("170");
		_listIntervento.add("177");
		_listStato = new ArrayList<String>();
		_titoloPagina = "";
		_message = "";
		_messageVisible = false;
		_readOnly = false;
	}

	/**
	 * aggiunge un esito alla lista
	 */
	public void aggiungiEsito() {
		_action = Costanti.ACTION_INSERT;
		_esitoArt68 = new EsitoArt68BO();
		_titoloPagina = "Creazione nuovo esito impegni";
		_readOnly = false;

	}

	/**
	 * modifica un esito alla lista
	 */
	public void modificaEsito() {
		// se non é stato selezionato nulla
		if (_selectedValue == null || _selectedValue.get_domanda().equals("")
				|| _selectedValueIndex == -1) {
			_messageVisible = true;
			_message = Costanti.MSG_NO_RIGA_SELEZIONATA;
			return;
		}
		_action = Costanti.ACTION_MODIFY;
		if (_selectedValue.get_cuaa().equals(""))
			return;
		_selectedValue = _esitoArt68EJBBean.getElencoEsitoArt68().get(
				_selectedValueIndex);
		_esitoArt68 = _selectedValue;
		_esitoArt68Old = _esitoArt68.clone();
		_titoloPagina = "Modifica esito articolo 68";
		_readOnly = true;
		// imposta l'abilitazione delle textbox in base ai valori degli esiti
	}

	/**
	 * cancella un esito dalla lista
	 */
	public void cancellaEsito() {
		// se non é stato selezionato nulla
		if (_selectedValue == null || _selectedValue.get_domanda().equals("")
				|| _selectedValueIndex == -1) {
			_messageVisible = true;
			_message = Costanti.MSG_NO_RIGA_SELEZIONATA;
			return;
		}
		_selectedValue = _esitoArt68EJBBean.getElencoEsitoArt68().get(
				_selectedValueIndex);
		_esitoArt68 = _selectedValue;
		OperationResultBO result = _esitoArt68EJBBean
				.cancellaEsito(_esitoArt68);
		if (result.get_result()) {
			_esitoArt68 = new EsitoArt68BO();
			_selectedValue = new EsitoArt68BO();
			_esitoArt68Old = new EsitoArt68BO();
		}
		// visualizzo il messaggio a video
		_messageVisible = true;
		_message = result.get_message();
	}

	/**
	 * salva un esito nella lista
	 */
	public void salvaEsito() {
		OperationResultBO result = null;
		if (_action.equals(Costanti.ACTION_INSERT)) {
			result = _esitoArt68EJBBean.aggiungiEsito(_esitoArt68);
		} else if (_action.equals(Costanti.ACTION_MODIFY)) {
			result = _esitoArt68EJBBean.modificaEsito(_esitoArt68);
		}
		if (result.get_result())
			_action = Costanti.ACTION_VIEW;
		_message = result.get_message();
		_messageVisible = true;
	}

	/**
	 * annulla la creazione di un nuovo esito
	 */
	public void annullaEsito() {
		if (_esitoArt68Old != null)
			_esitoArt68.recovery(_esitoArt68Old);
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
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_ART_68);
		uploadBean.set_ejbBean(_esitoArt68EJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";
	}
	
	/**
	 * esportazione dati
	 */
	public void esportaEsito()
	{
		String filename = Costanti.FILE_ESITO_ART_68;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_esitoArt68EJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}

	/**
	 * funzione che definisce l'abilitazione grafica delle textbox per
	 * l'inserimento delle percentuali di riduzione in base all'esito
	 * selezionato
	 * 
	 * @param e
	 */
	public void disablePercRidOPPAB(AjaxActionEvent e) {
		if (_esitoArt68.get_esito().equals("POSITIVO")) {
			// _disablePercRidOPPAB= true;
		} else {
			// _disablePercRidOPPAB = false;
		}
	}

	

	/**
	 * funzione che trova la domanda DU a partire da cuaa e campagna
	 * @param e
	 */
	public void findDomanda(AjaxActionEvent e){
		DomandaBO domandaDU = _esitoArt68EJBBean.getDomandaDU(_esitoArt68.get_cuaa(), _esitoArt68.get_campagna());
		if(domandaDU == null) {
			_esitoArt68.set_domanda("");
			return;
		}
		_esitoArt68.set_domanda(domandaDU.get_idDomanda());
	}
	
	
	
	// /**
	// * metodo per gestire l'aggiornamento della lista dei sottointerventi
	// * alla selezione dell'intervento
	// * @param arg0
	// * @throws AbortProcessingException
	// */
	// public void interventoChanged(ValueChangeEvent arg0){
	// try {
	// String intervento = arg0.getNewValue().toString();
	// if (intervento.equals("")) return;
	// _esitoImpegni.set_intervento(intervento);
	// _listSottointervento =
	// _esitoImpegniEJBBean.getListSottointervento(intervento);
	// } catch (Exception e) {
	// }
	// }

	public List<EsitoArt68BO> getElencoEsitoArt68() {
		return _esitoArt68EJBBean.getElencoEsitoArt68();
	}

	public void setElencoEsitoArt68(final ElencoEsitoArt68BO elencoEsitoArt68) {
		_esitoArt68EJBBean.setElencoEsitoArt68(elencoEsitoArt68);
	}

	/**
	 * carica i dati degli esiti impegni
	 */
	public void loadData() {
		_esitoArt68EJBBean.loadData();
	}

	public void set_esitoArt68(EsitoArt68BO _esitoArt68) {
		this._esitoArt68 = _esitoArt68;
	}

	public EsitoArt68BO get_esitoArt68() {
		return _esitoArt68;
	}

	public void set_selectedValue(EsitoArt68BO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public EsitoArt68BO get_selectedValue() {
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
		if (_listCampagna.size() == 0)
			_listCampagna = _esitoArt68EJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_listEsito(List<String> _listEsito) {
		this._listEsito = _listEsito;
	}

	public List<String> get_listEsito() {
		_listEsito = _esitoArt68EJBBean.getListaValori(Costanti.ANAGR_ESITO);
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
	 * 
	 * @param parametersList
	 */
	public void filter(HashMap<String, String> parametersList) {
		_esitoArt68EJBBean.filter(parametersList);
	}

	/**
	 * cancella i valori delle selezioni correnti
	 */
	public void clearSelectedValue() {
		_selectedValue = new EsitoArt68BO();
		_esitoArt68 = new EsitoArt68BO();
		_esitoArt68Old = new EsitoArt68BO();
	}

	/**
	 * cancella i dati visualizzati
	 */
	public void clearData() {
		_esitoArt68EJBBean.clearList();
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
		return _listIntervento;
	}

	public void set_listStato(List<String> _listStato) {
		this._listStato = _listStato;
	}

	public List<String> get_listStato() {
		_listStato = _esitoArt68EJBBean.getListaValori(Costanti.ANAGR_STATO);
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

}
