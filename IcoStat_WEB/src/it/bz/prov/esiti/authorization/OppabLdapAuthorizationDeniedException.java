package it.bz.prov.esiti.authorization;

import org.springframework.security.core.AuthenticationException;

/**
 * Estensione della classe astratta Spring AuthenticationException
 * per gestire il caso di utente autenticato da ldap ma senza ruoli definiti
 * @author sbertozzi
 */
public class OppabLdapAuthorizationDeniedException extends AuthenticationException {

	private static final long serialVersionUID = 1993071726491674784L;

	/**
	 * Override del costruttore definito in AuthenticationException
	 * @param msg
	 */
	public OppabLdapAuthorizationDeniedException(String msg) {
		super(msg);
	}
	
	/**
	 * Override del costruttore definito in AuthenticationException
	 * @param msg
	 * @param t
	 */
	public OppabLdapAuthorizationDeniedException(String msg, Throwable t) {
	    super(msg, t);
	}
	
	/**
	 * Override del costruttore definito in AuthenticationException
	 * @param msg
	 * @param extraInformation
	 */
	/*
	Non esiste più in questa versione di Spring-security
	protected OppabLdapAuthorizationDeniedException(String msg, Object extraInformation) {
	    super(msg, extraInformation);
	}
	*/
}
