package it.bz.prov.esiti.authorization;

import it.bz.prov.esiti.beans.SettingsUIBean;
import it.bz.prov.esiti.util.Utils;

import java.util.Hashtable;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.security.core.context.SecurityContextHolder;

public class OppabLdapUserSearch {

	private static String _contextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
	private static String _ldapServerUrl = "";
	private static String _principal = "";
	private static String _credential = "";
	private static String _searchScope = "";
	private static String _filter = "";
	private static String _domain = "";
	private static String _completeName = "";

	/**
	 * ricerca dell'utente
	 * @param username
	 * @return String
	 */
	public static String searchForUser(String username){
		
		readParameters();
		DirContext ctx = null;

		Hashtable<String, String>env = new Hashtable<String, String>();
		env.put("java.naming.ldap.version", "3");
		env.put(Context.REFERRAL, "follow");
		env.put(Context.INITIAL_CONTEXT_FACTORY, _contextFactory);
		env.put(Context.PROVIDER_URL, _ldapServerUrl);
		env.put(Context.SECURITY_AUTHENTICATION, authenticationType());
		env.put(Context.SECURITY_PRINCIPAL, _principal);
		env.put(Context.SECURITY_CREDENTIALS, _credential);
		
		Attributes attributes = null;
		String[] sAttributesToRetrieve = searchAttributes();
		SearchControls ctls = null;
		SearchResult sr = null;
		NamingEnumeration<SearchResult> ne = null;
		String completeName = null;
		try{
			ctx = new InitialDirContext(env);
			if (sAttributesToRetrieve.length > 0) {
				ctls = new SearchControls();
				ctls.setReturningAttributes(sAttributesToRetrieve);
				ctls.setSearchScope(searchScope());
				String filter = "(" + _filter + "=" + username + ")";
				ne = ctx.search(_searchScope, filter, ctls);

				sr = (SearchResult) ne.next();
				attributes = sr.getAttributes();
			}

			NamingEnumeration<? extends Attribute>nn = attributes.getAll();
			Attribute attribute = null;
			while(nn.hasMoreElements()){
				attribute = (Attribute)nn.next();
//				System.out.println("attribute a:"+attribute.getID()+" - value:"+attribute.get());

				if(attribute.getID().equals(_completeName)){ // name
					completeName = (String)attribute.get();
				}
			}
		}
		catch (Throwable e) {
			return null;
		}

		return completeName;
	}

	/**
	 * Tipo di autenticazione (default: simple)
	 */
	private static String authenticationType() {
		return "simple";
	}

	/**
	 * attributi di ricerca
	 * @return String[] 
	 */
	private static String[] searchAttributes() {
		String[] a = new String[1];
		a[0] = _completeName;
		return a;
	}

	/**
	 * scope di ricerca
	 * @return int
	 */
	private static int searchScope() {
		return SearchControls.SUBTREE_SCOPE;
	}
	
	/**
	 * lettura dei parametri dal file stat.properties per la connessione a ldap
	 */
	private static void readParameters(){
		try
		{		
			FacesContext context = FacesContext.getCurrentInstance();
		    SettingsUIBean settingsBean = (SettingsUIBean) context.getApplication().evaluateExpressionGet(context, "#{settingsBean}", Object.class);
		    
		    _ldapServerUrl = settingsBean.get_ldapServerUrl();
		    _domain = settingsBean.get_ldapDomain();
		    _searchScope = settingsBean.get_ldapSearchScope();
		    _filter = settingsBean.get_ldapFilter();
		    _completeName = settingsBean.get_ldapCompleteName();
		    
			String username = Utils.getCurrentUser();
			
			if(!username.contains("\\"))
        		username = _domain + "\\" + username;
			
			_principal = username;
        	_credential = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getCredentials());
		}
		catch(Exception e){
			System.out.println("Lettura parametri fallita");
		}
	}
}
