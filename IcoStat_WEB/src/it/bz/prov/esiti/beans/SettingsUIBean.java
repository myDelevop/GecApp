package it.bz.prov.esiti.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped; 

import it.bz.prov.esiti.ibusiness.ISettings;
import it.bz.prov.esiti.logic.SettingsBean;

@ManagedBean(name = "settingsBean")
@SessionScoped
public class SettingsUIBean {
	
	private ISettings _settingsEJBBean;
	
	public SettingsUIBean() {}
	
	@PostConstruct
	private void initSettingsUI() {
		_settingsEJBBean = new SettingsBean();
	}

	public String get_ldapServerUrl() {
		return _settingsEJBBean.get_ldapServerUrl();
	}

	public String get_ldapSearchScope() {
		return _settingsEJBBean.get_ldapSearchScope();
	}

	public String get_ldapFilter() {
		return _settingsEJBBean.get_ldapFilter();
	}

	public String get_ldapCompleteName() {
		return _settingsEJBBean.get_ldapCompleteName();
	}

	public String get_ldapDomain() {
		return _settingsEJBBean.get_ldapDomain();
	}

	public String get_tmp_folder() {	
		return _settingsEJBBean.get_tmp_folder();
	}

	public String get_log_folder() {
		return _settingsEJBBean.get_log_folder();
	}

	public boolean get_enableLogin() {
		return _settingsEJBBean.get_enableLogin();
	}

}
