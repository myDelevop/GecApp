package it.bz.prov.esiti.beans;

import it.bz.prov.esiti.ibusiness.ISettings;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Bean UI (user interface) per la gestione del layout grafico
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "layoutBean")
@SessionScoped
public class LayoutUIBean {
	private String _pathImage;
	private String _hostname;
	private String _pathLogServer;
	private String _pathLogGES;
	@EJB
	private ISettings _settingsBean;
	
	public LayoutUIBean(){}
	
	@PostConstruct
	private void initLayoutUIBean() {
	    String logFolder = _settingsBean.get_log_folder();
	    _pathImage = _settingsBean.get_Environment().equals("PROD") ? Costanti.HEADER_PRO : Costanti.HEADER_DEV;
		_pathLogGES = logFolder + "OPPAB_GESTIONE_ESITI.log";
		_pathLogServer = logFolder + "server.log";
		_hostname = Utils.getHostName().toLowerCase();		
	}
	
	public String get_pathImage() {
		return _pathImage;
	}

	public String get_hostname() {
		return _hostname;
	}

	public String get_pathLogServer() {
		return _pathLogServer;
	}

	public String get_pathLogGES() {
		return _pathLogGES;
	}

	public boolean get_enableLogin() {
		return _settingsBean.get_enableLogin();
	}

}
