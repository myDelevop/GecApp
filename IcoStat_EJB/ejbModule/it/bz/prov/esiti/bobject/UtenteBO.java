package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.Utente;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

import java.util.Date;
import java.util.Vector;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Business entity che rappresenta il singolo utente
 * 
 * @author bpettazzoni
 *
 */
public class UtenteBO {

	private String _username = "";
    private String _password = "";
    private String _completeName = "";
    private boolean _isModified = false;
    private Vector<GrantedAuthority> roleList = new Vector<GrantedAuthority>();
    private boolean _roleAdminUser; 	
	private boolean _roleUser;
	private boolean _roleAdminDescription;
	private boolean _roleWrImp;
	private boolean _roleWrCond;
	private boolean _roleWrSup;
	private boolean _roleWrAcque;
	private boolean _roleWrCamp;
	private boolean _roleWrUba;
	private boolean _roleWrZoot;
	private boolean _roleWrRMFITFER;
	private Date _dataModifica;
	private String _userModifica;
	private Date _dataInserimento;
	private String _userInserimento;


	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
    
    /**
	 * Costruttore senza parametri.
	 */
	public UtenteBO()
	{
		_username = "";
	    _password = "";
	    _completeName = "";
	    _isModified = false;
	    _roleAdminUser = false;
		_roleUser = true;
		_roleWrAcque = false;
		_roleWrCamp = false;
		_roleWrCond = false;
		_roleWrImp = false;
		_roleWrSup = false;
		_roleWrUba = false;
		_roleWrZoot = false;
		_roleWrRMFITFER = false;
		_roleAdminDescription = false;
		set_userModifica("");
		set_userInserimento("");
	}
	
	
    /**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param utente
	 */
	public UtenteBO(Utente utente)
	{
		_completeName = utente.get_nomeCompleto();
		_username = utente.get_username();
		
		if(utente.get_roleAdminUser().equals("S")) _roleAdminUser = true;
		if(utente.get_roleUser().equals("S")) _roleUser = true;
		
		if(utente.get_roleWrAcque().equals("S")) _roleWrAcque = true;
		if(utente.get_roleWrCamp().equals("S")) _roleWrCamp = true;
		if(utente.get_roleWrCond().equals("S")) _roleWrCond = true;
		if(utente.get_roleWrImp().equals("S")) _roleWrImp = true;
		if(utente.get_roleWrSup().equals("S")) _roleWrSup = true;
		if(utente.get_roleWrUba().equals("S")) _roleWrUba = true;
		if(utente.get_roleWrZoot().equals("S")) _roleWrZoot = true;
		if(utente.get_roleWrRMFITFER().equals("S")) _roleWrRMFITFER = true;
		if(utente.get_roleAdminDescription().equals("S")) _roleAdminDescription = true;
		
		// setting delle SimpleGrantedAuthority
		if(utente.get_roleAdminDescription().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_ADMIN_DESCRIPTION));
		if(utente.get_roleAdminUser().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_ADMIN_USER));
		if(utente.get_roleUser().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_USER));
		if(utente.get_roleWrAcque().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_WR_ACQUE));
		if(utente.get_roleWrCamp().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_WR_CAMP));
		if(utente.get_roleWrCond().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_WR_COND));
		if(utente.get_roleWrImp().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_WR_IMP));
		if(utente.get_roleWrSup().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_WR_SUP));
		if(utente.get_roleWrUba().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_WR_UBA));
		if(utente.get_roleWrZoot().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_WR_ZOOT));
		if(utente.get_roleWrRMFITFER().equals("S")) 
			roleList.add(new SimpleGrantedAuthority(Costanti.ROLE_WR_RMFITFER));
		
		_userInserimento= utente.get_userCreazione();
		_userModifica= utente.get_userModifica();
		_dataInserimento = utente.get_dataCreazione();
		_dataModifica = utente.get_dataModifica();
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
    
	public synchronized boolean hasRole(String roleName){
		for(GrantedAuthority ga : roleList){
			if(ga.getAuthority().equals(roleName))
				return true;
		}
		return false;
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return Utente
	 */
	public Utente getEntity()
	{
		Utente utente = new Utente();
		utente.set_username(_username);
		utente.set_nomeCompleto(_completeName);
		
		if(_roleUser) utente.set_roleUser("S");		
		else utente.set_roleUser("N");
		if(_roleAdminUser) utente.set_roleAdminUser("S");
		else utente.set_roleAdminUser("N");		
		if(_roleAdminDescription) utente.set_roleAdminDescription("S");
		else utente.set_roleAdminDescription("N");
		if(_roleWrAcque) utente.set_roleWrAcque("S");
		else utente.set_roleWrAcque("N");
		if(_roleWrCamp) utente.set_roleWrCamp("S");
		else utente.set_roleWrCamp("N");
		if(_roleWrCond) utente.set_roleWrCond("S");
		else utente.set_roleWrCond("N");
		if(_roleWrImp) utente.set_roleWrImp("S");
		else utente.set_roleWrImp("N");
		if(_roleWrSup) utente.set_roleWrSup("S");
		else utente.set_roleWrSup("N");
		if(_roleWrUba) utente.set_roleWrUba("S");
		else utente.set_roleWrUba("N");
		if(_roleWrZoot) utente.set_roleWrZoot("S");
		else utente.set_roleWrZoot("N");
		if(_roleWrRMFITFER) utente.set_roleWrRMFITFER("S");
		else utente.set_roleWrRMFITFER("N");
		
		utente.set_userCreazione(_userInserimento);
		utente.set_userModifica(_userModifica);
		utente.set_dataCreazione(_dataInserimento);
		utente.set_dataModifica(_dataModifica);
		
		return utente;
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @param utente
	 */
	public void setEntity(Utente utente)
	{
		utente.set_username(_username);
		utente.set_nomeCompleto(_completeName);
		if(_roleUser) utente.set_roleUser("S");
		else utente.set_roleUser("N");
		if(_roleAdminUser) utente.set_roleAdminUser("S");
		else utente.set_roleAdminUser("N");
		if(_roleAdminDescription) utente.set_roleAdminDescription("S");
		else utente.set_roleAdminDescription("N");
		if(_roleWrAcque) utente.set_roleWrAcque("S");
		else utente.set_roleWrAcque("N");
		if(_roleWrCamp) utente.set_roleWrCamp("S");
		else utente.set_roleWrCamp("N");
		if(_roleWrCond) utente.set_roleWrCond("S");
		else utente.set_roleWrCond("N");
		if(_roleWrImp) utente.set_roleWrImp("S");
		else utente.set_roleWrImp("N");
		if(_roleWrSup) utente.set_roleWrSup("S");
		else utente.set_roleWrSup("N");
		if(_roleWrUba) utente.set_roleWrUba("S");
		else utente.set_roleWrUba("N");
		if(_roleWrZoot) utente.set_roleWrZoot("S");
		else utente.set_roleWrZoot("N");
		if(_roleWrRMFITFER) utente.set_roleWrRMFITFER("S");
		else utente.set_roleWrRMFITFER("N");
		
		utente.set_userCreazione(_userInserimento);
		utente.set_userModifica(_userModifica);
		utente.set_dataCreazione(_dataInserimento);
		utente.set_dataModifica(_dataModifica);
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @param utente
	 * return boolean vale true se l'utente é stato modificato, false altrimenti
	 */
	public boolean isModify(Utente utente)
	{
		boolean change_role_user = false;
		boolean change_role_admin = false;
		boolean change_role_admin_description = false;
		boolean change_role_wr_acque = false;
		boolean change_role_wr_camp = false;
		boolean change_role_wr_cond = false;
		boolean change_role_wr_imp = false;
		boolean change_role_wr_sup = false;
		boolean change_role_wr_uba = false;
		boolean change_role_wr_zoot = false;
		boolean change_role_wr_rmfitfer = false;

				
		// role user
		if((utente.get_roleUser().equals("S") && _roleUser) || (utente.get_roleUser().equals("N") && !_roleUser)) 
			change_role_user = false;
		else change_role_user = true; 
		
		if((utente.get_roleAdminDescription().equals("S") && _roleAdminDescription) || (utente.get_roleAdminDescription().equals("N") && !_roleAdminDescription))
			change_role_admin_description = false;
		else change_role_admin_description = true; 
		
		if((utente.get_roleAdminUser().equals("S") && _roleAdminUser) || (utente.get_roleAdminUser().equals("N") && !_roleAdminUser))
			change_role_admin = false;
		else change_role_admin = true; 
		
		if((utente.get_roleWrAcque().equals("S") && _roleWrAcque) || (utente.get_roleWrAcque().equals("N") && !_roleWrAcque))
			change_role_wr_acque = false;
		else change_role_wr_acque = true; 
		
		if((utente.get_roleWrCamp().equals("S") && _roleWrCamp) || (utente.get_roleWrCamp().equals("N") && !_roleWrCamp))
			change_role_wr_camp = false;
		else change_role_wr_camp = true;
		
		if((utente.get_roleWrCond().equals("S") && _roleWrCond) || (utente.get_roleWrCond().equals("N") && !_roleWrCond))
			change_role_wr_cond = false;
		else change_role_wr_cond = true;
		
		if((utente.get_roleWrImp().equals("S") && _roleWrImp) || (utente.get_roleWrImp().equals("N") && !_roleWrImp))
			change_role_wr_imp = false;
		else change_role_wr_imp = true;
		
		if((utente.get_roleWrSup().equals("S") && _roleWrSup) || (utente.get_roleWrSup().equals("N") && !_roleWrSup))
			change_role_wr_sup = false;
		else change_role_wr_sup = true;
		
		if((utente.get_roleWrUba().equals("S") && _roleWrUba) || (utente.get_roleWrUba().equals("N") && !_roleWrUba))
			change_role_wr_uba = false;
		else change_role_wr_uba = true;
		
		if((utente.get_roleWrZoot().equals("S") && _roleWrZoot) || (utente.get_roleWrZoot().equals("N") && !_roleWrZoot))
			change_role_wr_zoot = false;
		else change_role_wr_zoot = true;
		
		if((utente.get_roleWrRMFITFER().equals("S") && _roleWrRMFITFER) || (utente.get_roleWrRMFITFER().equals("N") && !_roleWrRMFITFER))
			change_role_wr_rmfitfer = false;
		else change_role_wr_rmfitfer = true;
		
		if(!change_role_user && !change_role_admin && !change_role_admin_description && !change_role_wr_acque &&
				!change_role_wr_camp && !change_role_wr_cond && !change_role_wr_imp && !change_role_wr_sup &&
				!change_role_wr_uba && !change_role_wr_zoot && !change_role_wr_rmfitfer) return false;
		else return true;
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
    public synchronized String get_password() {
		return _password;
	}
	public synchronized void set_password(String _password) {
		this._password = _password;
	}    
	public synchronized String get_username() {
		return _username;
	}
	public synchronized void set_username(String _username) {
		this._username = _username;
	}
	public synchronized String get_completeName() {
		return _completeName;
	}
	public synchronized void set_completeName(String _completeName) {
		this._completeName = _completeName;
	}
	public synchronized boolean is_isModified() {
		return _isModified;
	}
	public synchronized void set_isModified(boolean _isModified) {
		this._isModified = _isModified;
	}
	
	public void set_roleAdminUser(boolean _roleAdminUser) {
		this._roleAdminUser = _roleAdminUser;
	}


	public boolean get_roleAdminUser() {
		return _roleAdminUser;
	}


	public void set_roleUser(boolean _roleUser) {
		this._roleUser = _roleUser;
	}


	public boolean get_roleUser() {
		return _roleUser;
	}


	public void set_roleWrImp(boolean _roleWrImp) {
		this._roleWrImp = _roleWrImp;
	}


	public boolean get_roleWrImp() {
		return _roleWrImp;
	}


	public void set_roleWrCond(boolean _roleWrCond) {
		this._roleWrCond = _roleWrCond;
	}


	public boolean get_roleWrCond() {
		return _roleWrCond;
	}


	public void set_roleWrSup(boolean _roleWrSup) {
		this._roleWrSup = _roleWrSup;
	}


	public boolean get_roleWrSup() {
		return _roleWrSup;
	}


	public void set_roleWrAcque(boolean _roleWrAcque) {
		this._roleWrAcque = _roleWrAcque;
	}


	public boolean get_roleWrAcque() {
		return _roleWrAcque;
	}


	public void set_roleWrCamp(boolean _roleWrCamp) {
		this._roleWrCamp = _roleWrCamp;
	}


	public boolean get_roleWrCamp() {
		return _roleWrCamp;
	}


	public void set_roleWrUba(boolean _roleWrUba) {
		this._roleWrUba = _roleWrUba;
	}


	public boolean get_roleWrUba() {
		return _roleWrUba;
	}


	public void set_roleWrZoot(boolean _roleWrZoot) {
		this._roleWrZoot = _roleWrZoot;
	}


	public boolean get_roleWrZoot() {
		return _roleWrZoot;
	}

	public void set_roleWrRMFITFER(boolean _roleWrRMFITFER) {
		this._roleWrRMFITFER = _roleWrRMFITFER;
	}


	public boolean get_roleWrRMFITFER() {
		return _roleWrRMFITFER;
	}

	public void set_roleAdminDescription(boolean _roleAdminDescription) {
		this._roleAdminDescription = _roleAdminDescription;
	}


	public boolean get_roleAdminDescription() {
		return _roleAdminDescription;
	}


	public void setRoleList(Vector<GrantedAuthority> roleList) {
		this.roleList = roleList;
	}


	public Vector<GrantedAuthority> getRoleList() {
		return roleList;
	}


	public void set_dataModifica(Date _dataModifica) {
		this._dataModifica = _dataModifica;
	}


	public Date get_dataModifica() {
		return _dataModifica;
	}
	
	public String get_dataModificaStr(){
		return Utils.getDateString(_dataModifica);
	}


	public void set_userModifica(String _userModifica) {
		this._userModifica = _userModifica;
	}


	public String get_userModifica() {
		return _userModifica;
	}


	public void set_dataInserimento(Date _dataInserimento) {
		this._dataInserimento = _dataInserimento;
	}


	public Date get_dataInserimento() {
		return _dataInserimento;
	}
	
	public String get_dataInserimentoStr(){
		return Utils.getDateString(_dataInserimento);
	}


	public void set_userInserimento(String _userInserimento) {
		this._userInserimento = _userInserimento;
	}


	public String get_userInserimento() {
		return _userInserimento;
	}
}
