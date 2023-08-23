package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.ibusiness.IFileUpload;
import it.bz.prov.esiti.util.Costanti;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

//import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * Bean UI (user interface) per la gestione dell'operazione di upload
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "uploadBean")
@SessionScoped
public class UploadUIBean {
	private String _titoloPagina;
	private Part _uploadedFile;
	private List<String> _logList;
	private int _sourcePage;
	@EJB
	private IFileUpload _fileUploadEJBBean;
	private String _esitoComplessivo;
	private String _styleEsitoComplessivo;
	private String _message;
	private boolean _messageVisible;
	private Object _ejbBean;
	
	/**
	 * costruttore
	 */
	public UploadUIBean()
	{
		_titoloPagina = "";
		_logList = new ArrayList<String>();
		_logList.add("INFO: L'operazione di import potrebbe richiedere qualche minuto.");
//		_fileUploadEJBBean = new FileUploadBean();
		_esitoComplessivo = "NON DISPONIBILE";
		_styleEsitoComplessivo = "color:black";
		_message = "";
		_messageVisible = false;
		_ejbBean = null;
	}
	
	/**
	 * esecuzione dell'operazione di upload
	 */
	public void executeUpload()
	{
		_fileUploadEJBBean.set_uploadedFile(_uploadedFile);
		_fileUploadEJBBean.set_sourcePage(_sourcePage);
		_fileUploadEJBBean.set_ejbBean(_ejbBean);
		// cancellazione della lista dei log
		_logList.clear();
		// esecuzione dell'upload
		 boolean result = _fileUploadEJBBean.executeUploadFile(_logList);
		if(result)
		{
			_esitoComplessivo = "SUCCESSO";
			_styleEsitoComplessivo = "color:green;";
		}
		else 
		{
			_esitoComplessivo = "FALLITO";
			_styleEsitoComplessivo = "color:red;";
		}
	}
	
	/**
	 * ritorna alla pagina di gestione
	 * @return String é il nome della pagina a cui voglio andare (riferito al faces_navigation)
	 */
	public String goBack()
	{
		// tornando alla pagina precedente viene fatto il reload dei dati in memoria dal DB
		FacesContext context = FacesContext.getCurrentInstance();
		SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
		searchBean.doSearch();
		
		return "index.xhtml";
	}
	
	/**
	 * esporta su excel i dati di log
	 * @return
	 */
	public void esportaLog()
	{
		String filename = Costanti.FILE_LOG;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_fileUploadEJBBean.exportFile(_logList, externalContext.getResponseOutputStream());
		} catch (IOException e) {
			set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			set_messageVisible(true);
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	/**
	 * pulisce i valori nell'interfaccia grafica
	 */
	public void clearValues()
	{
		_logList.clear();
		_logList.add("INFO: L'operazione di import potrebbe richiedere qualche minuto.");
		_esitoComplessivo = "NON DISPONIBILE";
		_styleEsitoComplessivo = "color:black";
	}
	
    
    /*******************************************************************/
    /*							GETTER E SETTER 					   */
    /*******************************************************************/


	public void set_titoloPagina(String _titoloPagina) {
		this._titoloPagina = _titoloPagina;
	}

	public String get_titoloPagina() {
		return _titoloPagina;
	}


	public void set_uploadedFile(Part _uploadedFile) {
		this._uploadedFile = _uploadedFile;
	}


	public Part get_uploadedFile() {
		return _uploadedFile;
	}

	public void set_logList(List<String> _logList) {
		this._logList = _logList;
	}

	public List<String> get_logList() {
		return _logList;
	}

	public void set_sourcePage(int _sourcePage) {
		this._sourcePage = _sourcePage;
	}

	public int get_sourcePage() {
		return _sourcePage;
	}

	public void set_esitoComplessivo(String _esitoComplessivo) {
		this._esitoComplessivo = _esitoComplessivo;
	}

	public String get_esitoComplessivo() {
		return _esitoComplessivo;
	}

	public void set_styleEsitoComplessivo(String _styleEsitoComplessivo) {
		this._styleEsitoComplessivo = _styleEsitoComplessivo;
	}

	public String get_styleEsitoComplessivo() {
		return _styleEsitoComplessivo;
	}

	public void set_message(String _message) {
		this._message = _message;
	}

	public String get_message() {
		return _message;
	}

	public void set_messageVisible(boolean _messageVisible) {
		this._messageVisible = _messageVisible;
	}

	public boolean is_messageVisible() {
		return _messageVisible;
	}

	public void set_ejbBean(Object _ejbBean) {
		this._ejbBean = _ejbBean;
	}

	public Object get_ejbBean() {
		return _ejbBean;
	}


}
