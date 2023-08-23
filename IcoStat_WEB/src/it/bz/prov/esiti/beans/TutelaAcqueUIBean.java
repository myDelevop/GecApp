package it.bz.prov.esiti.beans;

import it.bz.prov.esiti.bobject.ElencoTutelaAcqueBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.TutelaAcqueBO;
import it.bz.prov.esiti.ibusiness.ITutelaAcque;
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

/**
 * Bean UI (user interface) per la gestione degli esiti di tutela acque
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "tutelaAcqueBean")
@SessionScoped
public class TutelaAcqueUIBean {
	@EJB
	private ITutelaAcque _tutelaAcqueEJBBean;
	private String _action;
	private TutelaAcqueBO _tutelaAcque;
	private TutelaAcqueBO _selectedValue = null;
	private TutelaAcqueBO _tutelaAcqueOld; // serve per memorizzare la versione originale durante la modifica
	private List<String> _listEsitoCaricoBestTA;
	private List<String> _listEsitoCaricoBestPSR;
	private List<String> _listCampagna;
	private List<String> _listStato;
//	private List<String> _listCond;
	private List<String> _listCapacitaStoccaggi;
	private List<String> _listStoccaggiInFunzione;
	private List<String> _listSecondoContrStoccaggio;
	private List<String> _listEsitoStoccaggi;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	
	
	/**
	 * costruttore
	 */
	public TutelaAcqueUIBean()
	{
//		_tutelaAcqueEJBBean = new TutelaAcqueBean();
		_action = Costanti.ACTION_VIEW;
		_tutelaAcque = new TutelaAcqueBO();
		_listEsitoCaricoBestTA = new ArrayList<String>();
		_listEsitoCaricoBestPSR = new ArrayList<String>();
		_listCampagna = new Vector<String>();
		_selectedValue = new TutelaAcqueBO();
		_listStato = new ArrayList<String>();
//		_listCond = new ArrayList<String>();
		_listCapacitaStoccaggi = new ArrayList<String>();
		_listStoccaggiInFunzione = new ArrayList<String>();
		_listSecondoContrStoccaggio = new ArrayList<String>();
		_listEsitoStoccaggi = new ArrayList<String>();
		_tutelaAcqueOld = new TutelaAcqueBO();
		_titoloPagina = "";
		_message = "";
		_messageVisible = false;
		_readOnly = false;
	}
	
	/**
	 * aggiunge una tutelaAcque alla lista
	 */
	public void aggiungiTutelaAcque()
	{
		_action = Costanti.ACTION_INSERT;
		_tutelaAcque = new TutelaAcqueBO();
		_titoloPagina = "Creazione nuovo esito tutela acque";
		_readOnly = false;
		
		// se nel box di ricerca sono inseriti cuaa o campagna si pre-compila la form di inserimento
		FacesContext context = FacesContext.getCurrentInstance();
		SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
		String cuaa= searchBean.getCuaa();
		String campagna= searchBean.getCampagna();
		// per evitare di prendere un cuaa che é una substring (anche così la stringa potrebbe non 
		//corrispondere a un cuaa, ma é una richiesta di Lars)
		if(cuaa.length()>10) _tutelaAcque.set_cuaa(cuaa);
		if(!campagna.equals("")) _tutelaAcque.set_campagna(campagna);
	}
	
	/**
	 * modifica un elemento della lista
	 */
	public void modificaTutelaAcque()
	{ 
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_cuaa().equals("")) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_action = Costanti.ACTION_MODIFY;
		if(_selectedValue.get_cuaa().equals("")) return; 
		_tutelaAcque = _selectedValue;
		_tutelaAcqueOld = _tutelaAcque.clone();
		_titoloPagina = "Modifica esito tutela acque";
		_readOnly = true;
	}
	
	
	/**
	 * cancella un elemento della lista
	 */
	public void cancellaTutelaAcque()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_cuaa().equals("")) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA;  
			return;
		}
		_tutelaAcque = _selectedValue;
		OperationResultBO result = _tutelaAcqueEJBBean.cancellaTutelaAcque(_tutelaAcque);
		if(result.get_result()) {
			_tutelaAcque = new TutelaAcqueBO();
			_selectedValue = new TutelaAcqueBO();
			_tutelaAcqueOld = new TutelaAcqueBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * salva l'oggetto inserito nel database
	 */
	public void salvaTutelaAcque()
	{
		OperationResultBO result = null;
		if(_action.equals(Costanti.ACTION_INSERT)){
			result = _tutelaAcqueEJBBean.aggiungiTutelaAcque(_tutelaAcque);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _tutelaAcqueEJBBean.modificaTutelaAcque(_tutelaAcque);
		}
		if(result.get_result()) _action= Costanti.ACTION_VIEW;
		_message = result.get_message();
		_messageVisible = true;
	}
	
	/**
	 * annulla la creazione di una nuova riga
	 */
	public void annullaTutelaAcque()
	{
		if(_tutelaAcqueOld != null) _tutelaAcque.recovery(_tutelaAcqueOld);
		_action = Costanti.ACTION_VIEW;
	}
	
	/**
	 * importazione dei dati 
	 */
	public String importaTutelaAcque()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UploadUIBean uploadBean = (UploadUIBean) context.getApplication().evaluateExpressionGet(context, "#{uploadBean}", Object.class);
		uploadBean.set_titoloPagina("Tutela Acque - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_ESITO_TUTELA_ACQUE);
		uploadBean.set_ejbBean(_tutelaAcqueEJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";
	}
	
	/**
	 * esportazione dei dati 
	 */
	public void esportaTutelaAcque()
	{
		String filename = Costanti.FILE_TUTELA_ACQUE;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_tutelaAcqueEJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}

    public List<TutelaAcqueBO> getElencoTutelaAcque()
    {
        return _tutelaAcqueEJBBean.getElencoTutelaAcque();
    }

    public void setElencoTutelaAcque(final ElencoTutelaAcqueBO elencoTutelaAcque)
    {
        _tutelaAcqueEJBBean.setElencoTutelaAcque(elencoTutelaAcque);
    }
    
    /**
	 * carica i dati della tutela delle acque 
	 */
	public void loadData()
	{
		_tutelaAcqueEJBBean.loadData();
	}

	public void set_action(String _action) {
		this._action = _action;
	}

	public String get_action() {
		return _action;
	}

	public void set_tutelaAcque(TutelaAcqueBO _tutelaAcque) {
		this._tutelaAcque = _tutelaAcque;
	}

	public TutelaAcqueBO get_tutelaAcque() {
		return _tutelaAcque;
	}

	public void set_listCampagna(Vector<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	public List<String> get_listCampagna() {
		if(_listCampagna.size()==0)
			_listCampagna = _tutelaAcqueEJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_selectedValue(TutelaAcqueBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public TutelaAcqueBO get_selectedValue() {
		return _selectedValue;
	}

	public void set_listEsitoCaricoBestTA(List<String> _listEsitoCaricoBestTA) {
		this._listEsitoCaricoBestTA = _listEsitoCaricoBestTA;
	}

	public List<String> get_listEsitoCaricoBestTA() {
		if(_listEsitoCaricoBestTA.size()==0)
			_listEsitoCaricoBestTA = _tutelaAcqueEJBBean.getListaValori(Costanti.ANAGR_ESITO);
		return _listEsitoCaricoBestTA;
	}
	
	public void set_listEsitoCaricoBestPSR(List<String> _listEsitoCaricoBestPSR) {
		this._listEsitoCaricoBestPSR = _listEsitoCaricoBestPSR;
	}

	public List<String> get_listEsitoCaricoBestPSR() {
		if(_listEsitoCaricoBestPSR.size()==0)
			_listEsitoCaricoBestPSR = _tutelaAcqueEJBBean.getListaValori(Costanti.ANAGR_ESITO);
		return _listEsitoCaricoBestPSR;
	}

	public void set_listStato(List<String> _listStato) {
		this._listStato = _listStato;
	}

	public List<String> get_listStato() {
		if(_listStato.size() == 0)
			_listStato = _tutelaAcqueEJBBean.getListaValori(Costanti.ANAGR_STATO);
		return _listStato;
	}

//	public void set_listCond(List<String> _listCond) {
//		this._listCond = _listCond;
//	}
//
//	public List<String> get_listCond() {
//		if(_listCond.size() == 0)
//			_listCond =  _tutelaAcqueEJBBean.getListaValori(Costanti.ANAGR_TUTELA_ACQUE_CONDIZIONALITA);
//		return _listCond;
//	}

	public void set_listCapacitaStoccaggi(List<String> _listCapacitaStoccaggi) {
		this._listCapacitaStoccaggi = _listCapacitaStoccaggi;
	}

	public List<String> get_listCapacitaStoccaggi() {
		if(_listCapacitaStoccaggi.size() == 0)
			_listCapacitaStoccaggi = _tutelaAcqueEJBBean.getListaValori(Costanti.ANAGR_TUTELA_ACQUE_CAPACITA_STOCCAGGI);
		return _listCapacitaStoccaggi;
	}

	public void set_listStoccaggiInFunzione(List<String> _listStoccaggiInFunzione) {
		this._listStoccaggiInFunzione = _listStoccaggiInFunzione;
	}

	public List<String> get_listStoccaggiInFunzione() {
		if(_listStoccaggiInFunzione.size() == 0)
			_listStoccaggiInFunzione = _tutelaAcqueEJBBean.getListaValori(Costanti.ANAGR_TUTELA_ACQUE_STOCCAGGI_IN_FUNZIONE);
		return _listStoccaggiInFunzione;
	}
	
	public void set_listSecondoContrStoccaggio(List<String> _listSecondoContrStoccaggio) {
		this._listSecondoContrStoccaggio = _listSecondoContrStoccaggio;
	}

	public List<String> get_listSecondoContrStoccaggio() {
		if(_listSecondoContrStoccaggio.size()==0)
			_listSecondoContrStoccaggio = _tutelaAcqueEJBBean.getListaValori(Costanti.ANAGR_TUTELA_ACQUE_SECONDO_CONTROLLO);
		return _listSecondoContrStoccaggio;
	}
	
	public void set_listEsitoStoccaggi(List<String> _listEsitoStoccaggi) {
		this._listEsitoStoccaggi = _listEsitoStoccaggi;
	}

	public List<String> get_listEsitoStoccaggi() {
		if(_listEsitoStoccaggi.size()==0)
			_listEsitoStoccaggi = _tutelaAcqueEJBBean.getListaValori(Costanti.ANAGR_TUTELA_ACQUE_ESITO_STOCCAGGI);
		return _listEsitoStoccaggi;
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
		_tutelaAcqueEJBBean.filter(parametersList);
    }
	
	/**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new TutelaAcqueBO();
		_tutelaAcque = new TutelaAcqueBO();
		_tutelaAcqueOld = new TutelaAcqueBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_tutelaAcqueEJBBean.clearList();
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
}
