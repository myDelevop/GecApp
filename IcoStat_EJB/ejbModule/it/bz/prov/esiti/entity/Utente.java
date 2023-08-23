package it.bz.prov.esiti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity che rappresenta l'utente
 * 
 * @author bpettazzoni
 *
 */


@Entity
@Table(name = "OPP_ESITI_APP_UTENTI")
@NamedQueries({
		@NamedQuery(
				name = "Utente.selectAll",
				query = "SELECT u FROM Utente u ORDER BY u._nomeCompleto"),
})
public class Utente implements Serializable {

	private static final long serialVersionUID = -7583797148931519368L;

	@Id
	@Column(name = "USERNAME")
	private String _username;
	
	@Column(name = "NOME_COMPLETO")
	private String _nomeCompleto;
	
	@Column(name = "ROLE_ADMIN_USER")
	private String _roleAdminUser; 	
	
	@Column(name = "ROLE_ADMIN_DESCRIPTION")
	private String _roleAdminDescription;
	
	@Column(name = "ROLE_USER")
	private String _roleUser;
	
	@Column(name = "ROLE_WR_IMP")
	private String _roleWrImp;
		
	@Column(name = "ROLE_WR_COND")
	private String _roleWrCond;
	
	@Column(name = "ROLE_WR_SUP")
	private String _roleWrSup;
	
	@Column(name = "ROLE_WR_ACQUE")
	private String _roleWrAcque;
	
	@Column(name = "ROLE_WR_CAMP")
	private String _roleWrCamp;
	
	@Column(name = "ROLE_WR_UBA")
	private String _roleWrUba;
	
	@Column(name = "USER_CREAZIONE")
	private String _userCreazione;
		
	@Column(name = "DATA_CREAZIONE")
	private Date _dataCreazione;
	
	@Column(name = "USER_MODIFICA")
	private String _userModifica;
	
	@Column(name = "DATA_MODIFICA")
	private Date _dataModifica;

	@Column(name = "ROLE_WR_ZOOT")
	private String _roleWrZoot;
	
	@Column(name = "ROLE_WR_RMFITFER")
	private String _roleWrRMFITFER;
	
	/**
	 * costruttore
	 */
	public Utente()
	{
		_username = "";
		_nomeCompleto = "";
		_roleAdminUser = ""; 	
		_roleAdminDescription = "";
		_roleUser = "";
		_roleWrImp = "";
		_roleWrCond = "";
		_roleWrSup = "";
		_roleWrAcque = "";
		_roleWrCamp = "";
		_roleWrUba = "";
		_userCreazione = "";
		_userModifica = "";
		_roleWrZoot = "";
		_roleWrRMFITFER = "";
	}

	public void set_username(String _username) {
		_username = _username == null ? "" : _username;
		this._username = _username;
	}

	public String get_username() {
		return _username;
	}

	public void set_nomeCompleto(String _nomeCompleto) {
		_nomeCompleto = _nomeCompleto == null ? "" : _nomeCompleto;
		this._nomeCompleto = _nomeCompleto;
	}

	public String get_nomeCompleto() {
		return _nomeCompleto;
	}

	public void set_roleAdminUser(String _roleAdminUser) {
		_roleAdminUser = _roleAdminUser == null ? "" : _roleAdminUser;
		this._roleAdminUser = _roleAdminUser;
	}

	public String get_roleAdminUser() {
		return _roleAdminUser;
	}

	public void set_roleAdminDescription(String _roleAdminDescription) {
		_roleAdminDescription = _roleAdminDescription == null ? "" : _roleAdminDescription;
		this._roleAdminDescription = _roleAdminDescription;
	}

	public String get_roleAdminDescription() {
		return _roleAdminDescription;
	}

	public void set_roleUser(String _roleUser) {
		_roleUser = _roleUser == null ? "" : _roleUser;
		this._roleUser = _roleUser;
	}

	public String get_roleUser() {
		return _roleUser;
	}

	public void set_roleWrImp(String _roleWrImp) {
		_roleWrImp = _roleWrImp == null ? "" : _roleWrImp;
		this._roleWrImp = _roleWrImp;
	}

	public String get_roleWrImp() {
		return _roleWrImp;
	}

	public void set_roleWrCond(String _roleWrCond) {
		_roleWrCond = _roleWrCond == null ? "" : _roleWrCond;
		this._roleWrCond = _roleWrCond;
	}

	public String get_roleWrCond() {
		return _roleWrCond;
	}

	public void set_roleWrSup(String _roleWrSup) {
		_roleWrSup = _roleWrSup == null ? "" : _roleWrSup;
		this._roleWrSup = _roleWrSup;
	}

	public String get_roleWrSup() {
		return _roleWrSup;
	}

	public void set_roleWrAcque(String _roleWrAcque) {
		_roleWrAcque = _roleWrAcque == null ? "" : _roleWrAcque;
		this._roleWrAcque = _roleWrAcque;
	}

	public String get_roleWrAcque() {
		return _roleWrAcque;
	}

	public void set_roleWrCamp(String _roleWrCamp) {
		_roleWrCamp = _roleWrCamp == null ? "" : _roleWrCamp;
		this._roleWrCamp = _roleWrCamp;
	}

	public String get_roleWrCamp() {
		return _roleWrCamp;
	}

	public void set_roleWrUba(String _roleWrUba) {
		_roleWrUba = _roleWrUba == null ? "" : _roleWrUba;
		this._roleWrUba = _roleWrUba;
	}

	public String get_roleWrUba() {
		return _roleWrUba;
	}

	public void set_userCreazione(String _userCreazione) {
		_userCreazione = _userCreazione == null ? "" : _userCreazione;
		this._userCreazione = _userCreazione;
	}

	public String get_userCreazione() {
		return _userCreazione;
	}

	public void set_dataCreazione(Date _dataCreazione) {
		this._dataCreazione = _dataCreazione;
	}

	public Date get_dataCreazione() {
		return _dataCreazione;
	}

	public void set_userModifica(String _userModifica) {
		_userModifica = _userModifica == null ? "" : _userModifica;
		this._userModifica = _userModifica;
	}

	public String get_userModifica() {
		return _userModifica;
	}

	public void set_dataModifica(Date _dataModifica) {
		this._dataModifica = _dataModifica;
	}

	public Date get_dataModifica() {
		return _dataModifica;
	}

	public void set_roleWrZoot(String _roleWrZoot) {
		_roleWrZoot = _roleWrZoot == null ? "" : _roleWrZoot;
		this._roleWrZoot = _roleWrZoot;
	}

	public String get_roleWrZoot() {
		return _roleWrZoot;
	}

	public String get_roleWrRMFITFER() {
		return _roleWrRMFITFER;
	}

	public void set_roleWrRMFITFER(String _roleWrRMFITFER) {
		_roleWrRMFITFER = _roleWrRMFITFER == null ? "" : _roleWrRMFITFER;
		this._roleWrRMFITFER = _roleWrRMFITFER;
	}
	
	
}
