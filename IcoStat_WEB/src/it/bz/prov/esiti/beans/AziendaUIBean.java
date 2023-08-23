package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.bobject.AziendaBO;
import it.bz.prov.esiti.bobject.ElencoAziendeBO;
import it.bz.prov.esiti.ibusiness.IAzienda;
import it.bz.prov.esiti.util.Costanti;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Bean UI (user interface) per la gestione delle aziende
 * 
 * @author bpettazzoni
 *
 */
@ManagedBean(name = "aziendaBean")
@SessionScoped
public class AziendaUIBean {
	@EJB
	private IAzienda _aziendaEJBBean;
	private AziendaBO _azienda;
	private AziendaBO _selectedValue;
	private String _action;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	/**
	 * costruttore
	 */
	public AziendaUIBean()
	{
//		_aziendaEJBBean = new AziendaBean();
		_azienda = new AziendaBO();
		_selectedValue = new AziendaBO();
		_action = Costanti.ACTION_VIEW;
		// visto che é il primo tab carico i dati
		//_aziendaEJBBean.loadData();
		_titoloPagina = "";
		_messageVisible = false;
		_message = "";
		_readOnly = false;
	}
	
	
	/**
	 * esportazione dati
	 */
	public void esportaAzienda()
	{
		String filename = Costanti.FILE_AZIENDA;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_aziendaEJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}

    public List<AziendaBO> getElencoAziende()
    {
    	return _aziendaEJBBean.getElencoAziende();
    }

    public void setElencoAziende(final ElencoAziendeBO elencoAziende)
    {
    	_aziendaEJBBean.setElencoAziende(elencoAziende);
    }
    
    /**
     * Carica i dati in tabella
     */
    public void loadData()
    {
    	_aziendaEJBBean.loadData();
    }
    
    /**
     * verifica se il cuaa é già presente nel sistema (da valutare solo in caso di aggiunta, non di modifica)
     * @param context
     * @param component
     * @param value
     * @return boolean
     */
    public boolean canInsertCuaa(FacesContext context, UIComponent component, Object value)
    {
    	// se siamo in modifica non devo fare il test
    	if(_action.equals(Costanti.ACTION_MODIFY)) return true;
    	boolean existCuaa = _aziendaEJBBean.existCuaa(value.toString());
    	return !existCuaa; // se il cuaa esiste non posso inserirlo (logica contraria)
    }
    
    /**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
    public void filter(HashMap<String, String> parametersList){
    	_aziendaEJBBean.filter(parametersList);
    }
    
    /**
     * cancella i valori delle selezioni correnti
     */
    public void clearSelectedValue()
    {
    	_azienda = new AziendaBO();
    	_selectedValue = new AziendaBO();
    }
    
    /**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_aziendaEJBBean.clearList();
    }
    
    /*******************************************************************/
    /*							GETTER E SETTER 					   */
    /*******************************************************************/

	public void set_azienda(AziendaBO _azienda) {
		this._azienda = _azienda;
	}

	public AziendaBO get_azienda() {
		return _azienda;
	}

	public void set_selectedValue(AziendaBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public AziendaBO get_selectedValue() {
		return _selectedValue;
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

	public void set_messageVisible(boolean _messageVisible) {
		this._messageVisible = _messageVisible;
	}

	public boolean get_messageVisible() {
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
