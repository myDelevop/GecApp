package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.bobject.ControlloBO;
import it.bz.prov.esiti.bobject.ElencoControlloBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.ibusiness.IControllo;
import it.bz.prov.esiti.util.Costanti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

import org.openfaces.event.AjaxActionEvent;

/**
 * Bean UI (user interface) per la gestione delle informazioni relative al controllo
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "controlloBean")
@SessionScoped
public class ControlloUIBean implements ValueChangeListener{
	@EJB
	private IControllo _controlloEJBBean;
	private List<String> _listCampagna;
	private List<String> _listTipoControllo;
	private List<String> _listCuaa;
	private List<String> _listTipoPreavviso;
	private List<String> _listPersonaPresente;
	private String _action;
	private ControlloBO _selectedValue;
	private ControlloBO _controllo;
	private ControlloBO _controlloOld;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	
	/**
	 * costruttore
	 */
	public ControlloUIBean()
	{
//		_controlloEJBBean = new ControlloBean();
		_listCampagna = new ArrayList<String>();
		_listTipoControllo = new ArrayList<String>();
		_listCuaa = new ArrayList<String>();
		_listTipoPreavviso = new ArrayList<String>();
		_listPersonaPresente = new ArrayList<String>();
		_action = Costanti.ACTION_VIEW;
		_selectedValue = new ControlloBO();
		_controllo = new ControlloBO();
		_controlloOld = new ControlloBO();
		_titoloPagina = "";
		_messageVisible = false;
		_message = "";
		_readOnly = false;
	}
	
	/**
	 * aggiunge un controllo alla lista
	 */
	public void aggiungiControllo() 
	{
		_action = Costanti.ACTION_INSERT;
		_controllo = new ControlloBO();
		_titoloPagina = "Creazione nuovo controllo";
		_readOnly = false;
				
		// se nel box di ricerca sono inseriti cuaa o campagna si pre-compila la form di inserimento
		FacesContext context = FacesContext.getCurrentInstance();
		SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
		String cuaa= searchBean.getCuaa();
		String campagna= searchBean.getCampagna();
		// per evitare di prendere un cuaa che é una substring (anche così la stringa potrebbe non 
		//corrispondere a un cuaa, ma é una richiesta di Lars)
		if(cuaa.length() > 10 )	{
			if(campagna.equals("")) _controllo.set_cuaa(cuaa);
			if(!campagna.equals("")){
				List<String> result = _controlloEJBBean.getListDomande(cuaa, campagna);
				if(result.size() == 0){
					_message = "Nessuna domanda presente per il CUAA e Campagna indicati nella form di ricerca";
					_messageVisible = true;
				}
				else 
				{
					_controllo.set_cuaa(cuaa);
					_controllo.set_campagna(campagna);
				}
			}
		}
	}
	
	/**
	 * modifica un controllo alla lista
	 */
	public void modificaControllo()
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
		_controllo = _selectedValue;
		_controlloOld = _controllo.clone();
		_titoloPagina = "Modifica controllo";
		_readOnly = true;
	}
	
	/**
	 * cancella un controllo dalla lista
	 */
	public void cancellaControllo()
	{
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_cuaa().equals("")) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_controllo = _selectedValue;
		OperationResultBO result = _controlloEJBBean.cancellaControllo(_controllo); 
		if(result.get_result()) {
			_controllo = new ControlloBO();
			_selectedValue = new ControlloBO();
			_controlloOld = new ControlloBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * salva un controllo nella lista
	 */
	public void salvaControllo()
	{
		OperationResultBO result = new OperationResultBO();
		if(_action.equals(Costanti.ACTION_INSERT)){
			result = _controlloEJBBean.aggiungiControllo(_controllo);
		}
		else if(_action.equals(Costanti.ACTION_MODIFY)){
			result = _controlloEJBBean.modificaControllo(_controllo);
		}
		if(result.get_result()) _action = Costanti.ACTION_VIEW;
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * annulla la creazione di un nuovo controllo
	 */
	public void annullaControllo()
	{
		if(_controlloOld != null) _controllo.recovery(_controlloOld);
		_action = Costanti.ACTION_VIEW;
	}
	
	/**
	 * importazione dati
	 */
	public String importaControllo()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UploadUIBean uploadBean = (UploadUIBean) context.getApplication().evaluateExpressionGet(context, "#{uploadBean}", Object.class);
		uploadBean.set_titoloPagina("Controllo - Importazione dati da file");
		uploadBean.set_sourcePage(Costanti.TABELLA_CONTROLLO);
		uploadBean.set_ejbBean(_controlloEJBBean);
		uploadBean.clearValues();
		return "/stat/uploadForm.xhtml";
	}
	
	/**
	 * esportazione dati
	 */
	public void esportaControllo()
	{
		String filename = Costanti.FILE_CONTROLLO;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_controlloEJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	/**
	 * funzione che aggiorna la data di fine controllo in base a quella inserita come inizio controllo
	 * @param e
	 */
	public void aggiornaDataFineControllo(AjaxActionEvent e){
		_controllo.set_data_fine_controllo(_controllo.get_data_inizio_controllo());
	}
    
    
    /**
     * Carica i dati in tabella
     */
    public void loadData()
    {
    	_controlloEJBBean.loadData();
    }

	public void set_listCampagna(Vector<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	public List<String> get_listCampagna() {
		if(_listCampagna.size()==0)
			_listCampagna = _controlloEJBBean.getListCampagna();
		return _listCampagna;
	}

	public void set_action(String _action) {
		this._action = _action;
	}

	public String get_action() {
		return _action;
	}
	
	public void set_selectedValue(ControlloBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public ControlloBO get_selectedValue() {
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
    	_controlloEJBBean.filter(parametersList);
    }

	public void set_controlloEJBBean(IControllo _controlloEJBBean) {
		this._controlloEJBBean = _controlloEJBBean;
	}

	public IControllo get_controlloEJBBean() {
		return _controlloEJBBean;
	}

	public void set_controllo(ControlloBO _controllo) {
		this._controllo = _controllo;
	}

	public ControlloBO get_controllo() {
		return _controllo;
	}

	public void set_controlloOld(ControlloBO _controlloOld) {
		this._controlloOld = _controlloOld;
	}

	public ControlloBO get_controlloOld() {
		return _controlloOld;
	}
		
	public List<ControlloBO> getElencoControllo()
    {
        return _controlloEJBBean.getElencoControllo();
    }

    public void setElencoControllo(final ElencoControlloBO elencoControllo)
    {
        _controlloEJBBean.setElencoControllo(elencoControllo);
    }

	public void set_listTipoControllo(List<String> _listTipoControllo) {
		this._listTipoControllo = _listTipoControllo;
	}

	public List<String> get_listTipoControllo() {
		_listTipoControllo = _controlloEJBBean.getListaValori(Costanti.ANAGR_TIPO_CONTROLLO);
		return _listTipoControllo;
	}

	public void set_listCuaa(List<String> _listCuaa) {
		this._listCuaa = _listCuaa;
	}

	public List<String> get_listCuaa() {
		return _listCuaa;
	}
	
	public void set_campagna(String _campagna) {
		_controllo.set_campagna(_campagna);
	}
	
	public String get_campagna() {
		return _controllo.get_campagna();
	}

	@Override
	public void processValueChange(ValueChangeEvent arg0)
			throws AbortProcessingException {
		_controllo.set_campagna(arg0.getNewValue().toString());
		_listCuaa = _controlloEJBBean.getListCuaa(arg0.getNewValue().toString());	
	}
	
	/**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new ControlloBO();
		_controllo = new ControlloBO();
		_controlloOld = new ControlloBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_controlloEJBBean.clearList();
    }
	
	/**
     * verifica se il cuaa esiste nel sistema 
     * @param context
     * @param component
     * @param value
     * @return boolean
     */
    public boolean canInsertCuaa(FacesContext context, UIComponent component, Object value)
    {
    	boolean existCuaa = _controlloEJBBean.existCuaa(value.toString());
    	return existCuaa; 
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

	public void set_listTipoPreavviso(List<String> _listTipoPreavviso) {
		this._listTipoPreavviso = _listTipoPreavviso;
	}

	public List<String> get_listTipoPreavviso() {
		_listTipoPreavviso = _controlloEJBBean.getListaValori(Costanti.ANAGR_TIPO_PREAVVISO);
		return _listTipoPreavviso;
	}

	public void set_listPersonaPresente(List<String> _listPersonaPresente) {
		this._listPersonaPresente = _listPersonaPresente;
	}

	public List<String> get_listPersonaPresente() {
		_listPersonaPresente = _controlloEJBBean.getListaValori(Costanti.ANAGR_PERSONA_PRESENTE);
		return _listPersonaPresente;
	}

	public void set_readOnly(boolean _readOnly) {
		this._readOnly = _readOnly;
	}

	public boolean is_readOnly() {
		return _readOnly;
	}

	
	
}
