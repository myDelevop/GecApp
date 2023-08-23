package it.bz.prov.esiti.logic;

import java.io.InputStream;
import java.util.Properties;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.bz.prov.esiti.entity.Settings;
import it.bz.prov.esiti.ibusiness.ISettings;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

@Stateful
public class SettingsBean implements ISettings {
	
	private String _ldapServerUrl = "";
	private String _ldapSearchScope = "";
	private String _ldapFilter = "";
	private String _ldapCompleteName = "";
	private String _ldapDomain = "";
	private String _tmp_folder = "";
	private String _log_folder = "";
	private boolean _enableLogin = true;
	private String _environment = "";
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	

	public SettingsBean() {
		try {
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Costanti.PROPERTIES_FILE);
			Properties path = new Properties();
			path.load(inputStream);
			_ldapServerUrl = path.getProperty(Costanti.SET_LDAP_SERVER_URL);
			_ldapSearchScope = path.getProperty(Costanti.SET_LDAP_SEARCH_SCOPE);
			_ldapFilter = path.getProperty(Costanti.SET_LDAP_SEARCH_FILTER);
			_ldapCompleteName = path.getProperty(Costanti.SET_LDAP_COMPLETE_NAME);
			_ldapDomain = path.getProperty(Costanti.SET_LDAP_DOMAIN);
			_log_folder= path.getProperty("file.jboss.log_folder.ico") + "//";
		}catch(Exception e) {
			_ldapServerUrl = "";
			_ldapSearchScope = "";
			_ldapFilter = "";
			_ldapCompleteName = "";
			_ldapDomain = "";
			_log_folder="";
		}
	}

	public String get_ldapServerUrl() {
		return _ldapServerUrl;
	}

	public String get_ldapSearchScope() {
		return _ldapSearchScope;
	}

	public String get_ldapFilter() {
		return _ldapFilter;
	}

	public String get_ldapCompleteName() {
		return _ldapCompleteName;
	}

	public String get_ldapDomain() {
		return _ldapDomain;
	}

	public String get_tmp_folder() {	
		_tmp_folder = System.getProperty("java.io.tmpdir");
		return _tmp_folder;
	}

	public String get_log_folder() {		
		try {
			Query q = _em.createNamedQuery("Settings.filter");
			q.setParameter("chiave", "LOG_FOLDER");
			Settings s = (Settings) q.getSingleResult();
			if (s != null ) _log_folder =s.get_valore();  
			return _log_folder;
		} catch(Exception e) {
			_log_folder = "";
			Utils.getLog().error(SettingsBean.class.getSimpleName()  + " - get_log_folder errore: " + e.getMessage());
			return _log_folder;
		}
	}

	public boolean get_enableLogin() {
		try {
			Query q = _em.createNamedQuery("Settings.filter");
			q.setParameter("chiave", "DISABLE_LOGIN");
			Settings s = (Settings) q.getSingleResult();
			if((s.get_valore() != null && s.get_valore().equals("N")) || (s.get_valore() == null)) _enableLogin = true;
			else if(s.get_valore() != null && s.get_valore().equals("S"))_enableLogin = false;
			return _enableLogin;
		}
		catch(Exception e) {
			_enableLogin = true;
			Utils.getLog().error(SettingsBean.class.getSimpleName()  + " - get_enableLogin errore: " + e.getMessage());
			return _enableLogin;
		}
	}

	@Override
	public String get_Environment() {
		try {
			Query q = _em.createNamedQuery("Settings.filter");
			q.setParameter("chiave", "ENVIRONMENT");
			Settings s = (Settings) q.getSingleResult();
			if(s.get_valore() != null) _environment = s.get_valore();
			else _environment = "";
			return _environment;
		}
		catch(Exception e) {
			_environment = "";
			Utils.getLog().error(SettingsBean.class.getSimpleName()  + " - get_Environment errore: " + e.getMessage());
			return _environment;
		}
	}

}
