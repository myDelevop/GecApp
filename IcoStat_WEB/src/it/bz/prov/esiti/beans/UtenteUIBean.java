package it.bz.prov.esiti.beans;

import it.bz.prov.esiti.bobject.ElencoUtentiBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.UtenteBO;
import it.bz.prov.esiti.ibusiness.IUtente;
import it.bz.prov.esiti.login.LoginBean;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 * gestione delle informazioni relative agli utenti
 * @author bpettazzoni
 *
 */
@ManagedBean(name = "usersBean")
@SessionScoped
public class UtenteUIBean {

	@EJB
	private IUtente _utenteEJBBean; 
//	private ElencoUtenti elencoUtenti;
	private boolean actionAdd = false;
	private String userLength = "";
	private String userNotFound = "";
	
	private boolean _isValidate;
	private boolean _messageVisible ;
	private String _message;
	private UtenteBO _utente = null;
	private String _userLogged;
	
	
	/**
	 * costruttore
	 */
	public UtenteUIBean()
	{
		//_utenteEJBBean = new UtenteBean();
		_messageVisible = false;
		_message = "";
		_isValidate = false;
		
		// al momento questo non funziona... forse va solo con SSO
//		FacesContext context = FacesContext.getCurrentInstance();
//	    LoginBean loginBean = (LoginBean) context.getApplication().evaluateExpressionGet(context, "#{loginBean}", Object.class);
//	    _userLogged = loginBean.getUsername();
	    //System.out.println("---- user_logged:" + _userLogged);
	}

	/**
	 * restituisce se l'utente non é stato trovato nel sistema
	 * @return String
	 */
	public synchronized String getUserNotFound() {
		return userNotFound;
	}

	/**
	 * imposta se l'utente non é stato trovato nel sistema
	 * @param userNotFound
	 */
	public synchronized void setUserNotFound(String userNotFound) {
		this.userNotFound = userNotFound;
	}

	/**
	 * restituisce la dimensione del nome utente
	 * @return String
	 */
	public synchronized String getUserLength() {
		return userLength;
	}

	/**
	 * imposta la dimensione del nome utente
	 * @param userLength
	 */
	public synchronized void setUserLength(String userLength) {
		this.userLength = userLength;
	}

	/**
	 * aggiunge un utente alla lista.
	 * Serve solo per aggiungere a livello grafico una riga vuota. 
	 * non fa il salvataggio su db
	 * @return String
	 */
	public String aggiungiUtente()
	{
		if(!_isValidate && actionAdd && _utente.get_username().equals(""))
		{
			_messageVisible = true;
			_message = "Inserire uno username valido";
			return "";
		}
		else if(!_isValidate && actionAdd && !_utente.get_username().equals(""))
		{
			_messageVisible = true;
			_message = "E' necessario eseguire la validazione dell'utente";
			return "";
		}
		else if(_isValidate && actionAdd)
			{
				_messageVisible = true;
				_message = "Per aggiungere un nuovo utente é necessario salvare le modifiche";
				return "";
			}
		
		//controllo se già esiste un utente vuoto, e in questo caso non aggiungo nulla
		userNotFound = "";
		userLength = "";
		UtenteBO u = _utenteEJBBean.getElencoUtenti().getUtente("");
		if(u!=null){
			userLength = "Completare l'inserimento corrente prima di aggiungere un nuovo utente";
			return null;
		}
		else
		{
			UtenteBO utente = new UtenteBO();
			utente.set_username("");
			utente.set_password("");
			utente.set_completeName("");
			utente.set_isModified(true);
			utente.set_roleUser(true);
			_utenteEJBBean.getElencoUtenti().addUtente(utente);
			_utente = utente;
			actionAdd = true;
			return null;
		}
	}
	
	/**
	 * cancella un utente dalla lista
	 * @return String
	 */
	public String cancellaUtente(UtenteBO utente)
	{
		// se la variabile _utente é diversa da null e l'utente selezionato é lo stesso in editing 
		// posso fare la cancellazione, altrimenti no
		boolean isUtenteAutenticato = _utente != null && _utente.get_username().equals(utente.get_username());
		if(actionAdd && !isUtenteAutenticato) 
		{
			_messageVisible = true;
			_message = "Prima di cancellare l'utente terminare l'operazione in corso";
			return "";
		}
		
		if(Utils.getCurrentUser().equals(utente.get_username()))
		{
			_messageVisible = true;
			_message = "L'utente non può cancellare se stesso dal portale.";
			return "";
		}
			 
		userLength = "";
		userNotFound = "";
		if(actionAdd)
		{
			//se siamo in modalità aggiunta, rimuovo l'utente in editing
			if(!_isValidate)
			{
				_utenteEJBBean.getElencoUtenti().removeUtente(utente);
				_utenteEJBBean.getElencoUtenti().removeUtente("");
				actionAdd = false;
			}
			else _utenteEJBBean.cancellaUtente(utente);
			
		}
		else
		{
			_utenteEJBBean.cancellaUtente(utente);
		}
		return null;
	}
	
	/**
	 * salva l'utente inserito
	 * @param utente é l'utente da salvare
	 * @return String
	 */
	public void salvaUtente(UtenteBO utente)
	{
		if(utente.get_username().equals("") || utente.get_completeName().equals("")){
			_message = "Non é possibile salvare l'utente. Compilare entrambi i campi";
			_messageVisible = true;
			return ;
		}
		OperationResultBO res = _utenteEJBBean.aggiungiUtente(utente, _userLogged);
		_message = res.get_message();
		_messageVisible = true;
		// ricarico i dati da db così non ci sono problemi di bean in sessione con dati sporchi
		_utenteEJBBean.loadData();
		actionAdd = false;
	}
	
	/**
	 * esporta in excel la lista degli utenti con i permessi associati
	 */
	public void esportaListaUtenti()
	{
		String filename = Costanti.FILE_LISTA_UTENTI;
	    // Prepare response.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			_utenteEJBBean.exportFile(externalContext.getResponseOutputStream());
		} catch (IOException e) {
			_message = Costanti.MSG_OPERAZIONE_NON_ESEGUITA;
			_messageVisible = true;
		}

	    // Inform JSF that response is completed and it thus doesn't have to navigate.
	    facesContext.responseComplete();
	}
	
	/**
	 * carica i dati degli utenti 
	 */
	public void loadData()
	{
		_utenteEJBBean.loadData();
	}

	/**
	 * 
	 * @return
	 */
    public List<UtenteBO> getElencoUtenti()
    {
    	// ottenimento utente loggato
		if(_userLogged == null || _userLogged.equals("")){
			FacesContext context = FacesContext.getCurrentInstance();
		    LoginBean loginBean = (LoginBean) context.getApplication().evaluateExpressionGet(context, "#{loginBean}", Object.class);
		    _userLogged = loginBean.getUsername();
		}
    	// se size=0 significa che siamo al primo caricamento della pagina o che la tabella é vuota (improbabile)
    	if(_utenteEJBBean.getElencoUtenti().size()==0) loadData();
        return _utenteEJBBean.getElencoUtenti().get_listUtenti();
    }

    /**
     * imposta l'elenco degli utenti
     * @param elencoUtenti
     */
    public void setElencoUtenti(final ElencoUtentiBO elencoUtenti)
    {
    	_utenteEJBBean.setElencoUtenti(elencoUtenti);
    }
    
    /**
     * ricerca l'utente su ldap
     * @param utente
     * @return
     */
//    public String searchUserInLdap(UtenteBO utente){
//    	
//    	System.out.println("-----searchUserInLdap: " + utente.get_username());
//    	
//    	userNotFound = "";
//    	// controllo che l'utente non sia già presente nel sistema
//    	if(_utenteEJBBean.getElencoUtenti().getUtente(utente.get_username()) != null)
//    	{
//    		_messageVisible = true;
//    		_message = "L'utente é già presente in tabella";
//    		return "";
//    	}
//    	_isValidate = true;
//    	Utils.getLog().info(UtenteUIBean.class + ": Searching for user " + utente.get_username());
//    	String cn = OppabLdapUserSearch.searchForUser(utente.get_username());
//    	if(cn!=null && !cn.equals("")){
//    		utente.set_completeName(cn.replace(",", ""));
//    		_utenteEJBBean.aggiungiUtente(utente, _userLogged);
//    	}
//    	else {
//    		userNotFound = "Utente non trovato";
//    		utente.set_username("");
//    	}
//    	userLength = "";
//    	return null;
//    }
    
    public String checked(UtenteBO utente)
    {
    	utente.set_isModified(true);
    	_utenteEJBBean.getElencoUtenti().removeUtente(utente.get_username());
    	_utenteEJBBean.getElencoUtenti().addUtente(utente);
    	return null;
    }
    
    /**
	 * modifica gli utenti della lista prendendo i valori dalla pagina di admin
	 * @return String
	 */
    public String updateUtenti(){
    	
    	if(actionAdd){
    		_messageVisible = true;
    		_message = "prima di procedere salvare l'utente che si sta creando";
    		return "";
    	}
    	
    	_utenteEJBBean.modificaUtenti(_userLogged);
    	
		
//    	List<UtenteBO> list = this.getElencoUtenti();
//    	for(int i=0; i<list.size(); i++)
//    	{
//    		UtenteBO u = list.get(i);
////    		u.synchronizeRolesWithArray();
//    		//u.set_isModified(true);
//    	}
//    	if(actionAdd)
//    	{
//    		//se siamo in modalità aggiunta, devo aggiornare l'utente in editing dentro elencoUtenti, perché
//    		//è stato salvato dentro la struttura dati con username vuoto: lo tolgo e lo rimetto in versione aggiornata
//    		UtenteBO u = _utenteEJBBean.getElencoUtenti().getUtente("");
//    		if(u!=null)
//    		{
//    			_utenteEJBBean.getElencoUtenti().removeUtente("");
//    			_utenteEJBBean.getElencoUtenti().addUtente(u);
//    			actionAdd = false;
//    		}
//    	}
//    	_utenteEJBBean.modificaUtenti();
    	userLength = "";
    	userNotFound = "";
    	actionAdd = false;
    	_isValidate = false;
    	_utente = null;
    	return null;
    }
    
    
    /**
     * indica se la riga dell'utente corrisponde all'utente loggato
     * in questo caso viene disabilitato il componente grafico epr fare in modo che non possa cambiare i suoi diritti
     * @return boolean vale true é l'utente passato come parametro é quello loggato, false altrimenti
     */
    public boolean isUserLogged(UtenteBO utente){
//    	Utils.getLog().info("---- user riga:" + utente.get_username() + "_userLogged:" + _userLogged);
    	if(utente.get_username().equals(_userLogged)) return true;
    	else return false;
    }
    
    
    /**
     * trova l'utente a partire dallo username
     * @param username
     * @return UtenteBO. Vale null se l'utente non é stato trovato in tabella utenti
     */
    public UtenteBO findUtente(String username)
    {
    	return _utenteEJBBean.findUtente(username);
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
	
	

}
