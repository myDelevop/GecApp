package it.bz.prov.esiti.authentication;


import it.bz.prov.esiti.util.Utils;

import org.springframework.security.ldap.search.LdapUserSearch;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import org.springframework.util.Assert;

import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;

import javax.naming.directory.SearchControls;



/**
 * Override della classe Spring di default LdapUserSearch; rispetto all'originale,
 * è stato modificato il solo metodo searchForUser(username) per adattarlo
 * alle specifiche dell'applicazione
 * @author Robert Sanders
 * @author Luke Taylor
 * @version $Id: FilterBasedLdapUserSearch.java 2816 2008-03-27 14:41:11Z luke_t $
 *
 * @see SearchControls
 */
public class OppabLdapFilterUserSearch implements LdapUserSearch {
    //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(OppabLdapFilterUserSearch.class);
    
    /**
     * Log personalizzato dell'applicazione
     */
    private static final org.jboss.logging.Logger oppabLogger = Utils.getLog();

    //~ Instance fields ================================================================================================

    private ContextSource contextSource;

    /**
     * The LDAP SearchControls object used for the search. Shared between searches so shouldn't be modified
     * once the bean has been configured.
     */
    private SearchControls searchControls = new SearchControls();

    /** Context name to search in, relative to the base of the configured ContextSource. */
    private String searchBase = "";

    /**
     * The filter expression used in the user search. This is an LDAP search filter (as defined in 'RFC 2254')
     * with optional arguments. See the documentation for the <tt>search</tt> methods in {@link
     * javax.naming.directory.DirContext DirContext} for more information.
     *
     * <p>In this case, the username is the only parameter.</p>
     *  Possible examples are:
     *  <ul>
     *      <li>(uid={0}) - this would search for a username match on the uid attribute.</li>
     *  </ul>
     */
    private String searchFilter;

    //~ Constructors ===================================================================================================

    @SuppressWarnings("deprecation")
	public OppabLdapFilterUserSearch(String searchBase, String searchFilter, BaseLdapPathContextSource contextSource) {
        Assert.notNull(contextSource, "contextSource must not be null");
        Assert.notNull(searchFilter, "searchFilter must not be null.");
        Assert.notNull(searchBase, "searchBase must not be null (an empty string is acceptable).");

        this.searchFilter = searchFilter;
        this.contextSource = contextSource;
        this.searchBase = searchBase;

        //System.out.println("FilterBasedLdapUserSearch: searchBase " + searchBase);
        //System.out.println("FilterBasedLdapUserSearch: searchFilter " + searchFilter);
        //System.out.println("FilterBasedLdapUserSearch: contextSource " + contextSource.getBaseLdapPathAsString());

        setSearchSubtree(true);

        if (searchBase.length() == 0) {
        	oppabLogger.info(this.getClass().getSimpleName() + ": Non è stato specificato un nodo base di ricerca." +
        			"La ricerca utente verrà eseguita a partire dalla radice " + contextSource.getBaseLdapPath());
            logger.info("SearchBase not set. Searches will be performed from the root: "
                + contextSource.getBaseLdapPath());
        }
    }

    //~ Methods ========================================================================================================

    /**
     * Metodo di ricerca dell'utente sull'albero ldap.
     * Il metodo è stato modificato inserendo un OppabLdapTemplate (classe custom)
     * per consentire la connessione a ldap utilizzando le credenziali dell'utente che sta accedendo
     * 
     * @param username the username to search for.
     *
     * @return An LdapUserDetails object containing the details of the located user's directory entry
     *
     * @throws UsernameNotFoundException if no matching entry is found.
     */
    public DirContextOperations searchForUser(String username) {
        if (logger.isDebugEnabled()) {
            logger.debug("Searching for user '" + username + "', with user search " + this);
        }
        
        oppabLogger.info(this.getClass().getSimpleName() + ": Ricerca in corso dell'utente " + username + ", con user search " + this);
        
        OppabLdapTemplate template = new OppabLdapTemplate(contextSource);

        template.setSearchControls(searchControls);        
        
        if(username.contains("\\"))
        	username = username.split("\\\\")[1];

        try {

            return template.searchForSingleEntry(searchBase, searchFilter, new String[] {username});

        } catch (IncorrectResultSizeDataAccessException notFound) {
            if (notFound.getActualSize() == 0) {
            	oppabLogger.warn(this.getClass().getSimpleName() + ": Utente " + username + " non trovato");
                throw new UsernameNotFoundException("User " + username + " not found in directory.");
            }
            // Search should never return multiple results if properly configured, so just rethrow
            throw notFound;
        }
    }

    /**
     * Sets the corresponding property on the {@link SearchControls} instance used in the search.
     *
     * @param deref the derefLinkFlag value as defined in SearchControls..
     */
    public void setDerefLinkFlag(boolean deref) {
        searchControls.setDerefLinkFlag(deref);
    }

    /**
     * If true then searches the entire subtree as identified by context, if false (the default) then only
     * searches the level identified by the context.
     *
     * @param searchSubtree true the underlying search controls should be set to SearchControls.SUBTREE_SCOPE
     * rather than SearchControls.ONELEVEL_SCOPE.
     */
    public void setSearchSubtree(boolean searchSubtree) {
        searchControls.setSearchScope(searchSubtree ? SearchControls.SUBTREE_SCOPE : SearchControls.ONELEVEL_SCOPE);
    }

    /**
     * The time to wait before the search fails; the default is zero, meaning forever.
     *
     * @param searchTimeLimit the time limit for the search (in milliseconds).
     */
    public void setSearchTimeLimit(int searchTimeLimit) {
        searchControls.setTimeLimit(searchTimeLimit);
    }
    
	/**
	 * Specifies the attributes that will be returned as part of the search.
	 *<p>
	 * null indicates that all attributes will be returned.
	 * An empty array indicates no attributes are returned.
	 *
	 * @param attrs An array of attribute names identifying the attributes that
	 * 		    will be returned. Can be null.
	 */
	public void setReturningAttributes(String[] attrs) {
	    searchControls.setReturningAttributes(attrs);
	}

	/**
	 * Override del metodo toString
	 */
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("[ searchFilter: '").append(searchFilter).append("', ");
        sb.append("searchBase: '").append(searchBase).append("'");
        sb.append(", scope: ")
          .append(searchControls.getSearchScope() == SearchControls.SUBTREE_SCOPE ? "subtree" : "single-level, ");
        sb.append(", searchTimeLimit: ").append(searchControls.getTimeLimit());
        sb.append(", derefLinkFlag: ").append(searchControls.getDerefLinkFlag()).append(" ]");
        return sb.toString();
    }
}