package it.bz.prov.esiti.beans;

import it.bz.prov.esiti.bobject.MatrAmmIdealeBO;
import it.bz.prov.esiti.ibusiness.IMatrAmmIdeale;
import it.bz.prov.esiti.util.Costanti;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Bean UI (user interface) per la gestione dei capi allevati
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "matrAmmIdealeBean")
@SessionScoped
public class MatrAmmIdealeUIBean {
	@EJB
	private IMatrAmmIdeale _matrEJBBean;
	private String _action;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	
	
	/**
	 * costruttore
	 */
	public MatrAmmIdealeUIBean()
	{
//		_matrEJBBean = new MatrAmmIdealeBean();
		_action = Costanti.ACTION_VIEW;
		_titoloPagina = "";
		_messageVisible = false;
		_message = "";
		_readOnly = false;
	}
	
	
	/**
	 * esportazione dati
	 */
	public void esportaMatr()
	{
		String filename = Costanti.FILE_MATR_AMM_IDEALE;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_matrEJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	public List<MatrAmmIdealeBO> get_elencoMatrAmmIdeale()
    {
    	return _matrEJBBean.getElencoMatrAmmIdeale().get_listMatrAmmIdeale();
    }
    
    
    /**
     * Carica i dati in tabella
     */
    public void loadData()
    {
    	_matrEJBBean.loadData();
    }
    
    /**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_matrEJBBean.clearList();
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
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
    public void filter(HashMap<String, String> parametersList){
    	_matrEJBBean.filter(parametersList);
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

	public void set_matrEJBBean(IMatrAmmIdeale _matrEJBBean) {
		this._matrEJBBean = _matrEJBBean;
	}

	public IMatrAmmIdeale get_matrEJBBean() {
		return _matrEJBBean;
	}

	
	
}
