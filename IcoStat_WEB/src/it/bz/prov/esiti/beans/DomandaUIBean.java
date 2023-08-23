package it.bz.prov.esiti.beans;

import it.bz.prov.esiti.bobject.DomandaBO;
import it.bz.prov.esiti.bobject.ElencoDomandeBO;
import it.bz.prov.esiti.bobject.SottointerventoBO;
import it.bz.prov.esiti.entity.Sottointervento;
import it.bz.prov.esiti.ibusiness.IDomanda;
import it.bz.prov.esiti.util.Costanti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Bean UI (user interface) per la gestione delle domande
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "domandaBean")
@SessionScoped
public class DomandaUIBean {
	@EJB
	private IDomanda _domandaEJBBean;	
	@SuppressWarnings("unused")
	private ArrayList<Sottointervento> _listSottointerventi; 
	private DomandaBO _selectedValue;
	private DomandaBO _domanda;
	private String _visualizzaDettagli;
	private boolean _messageVisible;
	private String _message;
	private String _action;
	private String _titoloPagina;
	private List<String> _listCampagna;
	
	/**
	 * costruttore	
	 */
	public DomandaUIBean()
	{
		//_domandaEJBBean = new DomandaBean();
		_listSottointerventi = new ArrayList<Sottointervento>();
		_selectedValue = new DomandaBO();
		_domanda = new DomandaBO();
		_visualizzaDettagli = "";
		_message = "";
		_messageVisible = false;
		_action = Costanti.ACTION_VIEW;
		_titoloPagina = "";
		_listCampagna = new ArrayList<String>();
	}
	
	
	/**
	 * esportazione dati
	 */
	public void esportaDomanda()
	{
		String filename = Costanti.FILE_DOMANDA;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_domandaEJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
    public List<DomandaBO> getElencoDomande()
    {
        return _domandaEJBBean.getElencoDomande();
    }

    public void setElencoDomande(final ElencoDomandeBO elencoDomande)
    {
        _domandaEJBBean.setElencoDomande(elencoDomande);
    }

	public void set_selectedValue(DomandaBO selectedValue) {
		if(selectedValue == null) return;
		this._selectedValue = selectedValue;
	}

	public DomandaBO get_selectedValue() {
		return _selectedValue;
	}
	
	/**
	 * consente la visualizzazione dei dettagli
	 */
	public void visualizzaDettagli()
	{
		String id_domanda = _selectedValue.get_idDomanda();
		if(id_domanda.equals("")) return;
		_domandaEJBBean.visualizzaDettagli(_selectedValue);
		_visualizzaDettagli = "SI";
	}

	public void set_visualizzaDettagli(String _visualizzaDettagli) {
		this._visualizzaDettagli = _visualizzaDettagli;
	}

	public String get_visualizzaDettagli() {
		return _visualizzaDettagli;
	}
	
	/**
	 * carica i dati delle domande 
	 */
	public void loadData()
	{
		_domandaEJBBean.loadData();
	}
	
	/**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new DomandaBO();
		_domanda = new DomandaBO();
	}
	

	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_domandaEJBBean.clearList();
    }
	
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList){
    	_domandaEJBBean.filter(parametersList);
    	// cancelliamo eventuali dati di una navigazione precedente
    	clearSelectedValue();
    }
	

	public void set_listSottointerventi(ArrayList<Sottointervento> _listSottointerventi) {
		this._listSottointerventi = _listSottointerventi;
	}

	public ArrayList<SottointerventoBO> get_listSottointerventi() {
		return _domandaEJBBean.get_elencoSottoint().get_listSottoint();
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

	public void set_domanda(DomandaBO _domanda) {
		this._domanda = _domanda;
	}

	public DomandaBO get_domanda() {
		return _domanda;
	}

	public void set_action(String _action) {
		this._action = _action;
	}

	public String get_action() {
		return _action;
	}

	public void set_titoloPagina(String _titoloPagina) {
		this._titoloPagina = _titoloPagina;
	}

	public String get_titoloPagina() {
		return _titoloPagina;
	}

	public void set_listCampagna(List<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	public List<String> get_listCampagna() {
		if(_listCampagna.size() ==0) _listCampagna = _domandaEJBBean.getListCampagna();
		return _listCampagna;
	}

}
