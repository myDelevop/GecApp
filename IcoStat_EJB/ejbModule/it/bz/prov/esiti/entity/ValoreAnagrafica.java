package it.bz.prov.esiti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity che rappresenta un valore di un'anagrafica 
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(ValoreAnagraficaPk.class)
@Table(name = "OPP_ESITI_APP_ANAGRAFICHE")
@NamedQueries({
		@NamedQuery(
				name = "ValoreAnagrafica.selectAnagrafica",
				query = "SELECT va FROM ValoreAnagrafica va " +
						"WHERE va._campo = :campo AND va._campagnaFineVal = 9999 " +
						"ORDER BY va._valore"),
		@NamedQuery(
				name = "ValoreAnagrafica.selectDistinctCampi",
				query = "SELECT DISTINCT va._campo FROM ValoreAnagrafica va WHERE va._campagnaFineVal = 9999 ORDER BY va._campo"),
})
public class ValoreAnagrafica implements Serializable {
	
	private static final long serialVersionUID = -8383604749006651374L;

	@Id
	@Column(name = "VALORE")
	private String _valore;
	
	@Id
	@Column(name = "CAMPO")
	private String _campo;
	
	@Id
	@Column(name = "CAMPAGNA_INIZIO_VAL")
	private int _campagnaInizioVal;
	
	@Id
	@Column(name = "CAMPAGNA_FINE_VAL")
	private int _campagnaFineVal;
	
	@Column(name = "NOTE")
	private String _note;
	
	@Column(name = "USER_INSERIMENTO")
	private String _userInserimento;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _dataInserimento;
	
	@Column(name = "USER_MODIFICA")
	private String _userModifica;
	
	@Column(name = "DATA_MODIFICA")
	private Date _dataModifica;
	
	
	/**
	 * costruttore
	 */
	public ValoreAnagrafica()
	{
		_valore = "";
		_campo = "";
		_note = "";
		_userInserimento = "";
		_userModifica = "";
	}
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return ValoreAnagrafica
	 */
	public ValoreAnagrafica clone()
	{
		ValoreAnagrafica va = new ValoreAnagrafica();
		va.set_campo(_campo);
		va.set_valore(_valore);
		va.set_note(_note);
		va.set_campagnaInizioVal(_campagnaInizioVal);
		va.set_campagnaFineVal(_campagnaFineVal);
		va.set_userInserimento(_userInserimento);
		va.set_userModifica(_userModifica);
		va.set_dataInserimento(_dataInserimento);
		va.set_dataModifica(_dataModifica);
		return va;
	}

	public void set_valore(String _valore) {
		_valore = _valore == null ? "" : _valore;
		this._valore = _valore;
	}

	public String get_valore() {
		return _valore;
	}

	public void set_campagnaInizioVal(int _campagnaInizioVal) {
		this._campagnaInizioVal = _campagnaInizioVal;
	}

	public int get_campagnaInizioVal() {
		return _campagnaInizioVal;
	}

	public void set_campagnaFineVal(int _campagnaFineVal) {
		this._campagnaFineVal = _campagnaFineVal;
	}

	public int get_campagnaFineVal() {
		return _campagnaFineVal;
	}

	public void set_campo(String _campo) {
		_campo = _campo == null ? "" : _campo;
		this._campo = _campo;
	}

	public String get_campo() {
		return _campo;
	}

	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

	public void set_userInserimento(String _userInserimento) {
		_userInserimento = _userInserimento == null ? "" : _userInserimento;
		this._userInserimento = _userInserimento;
	}

	public String get_userInserimento() {
		return _userInserimento;
	}

	public void set_dataInserimento(Date _dataInserimento) {
		this._dataInserimento = _dataInserimento;
	}

	public Date get_dataInserimento() {
		return _dataInserimento;
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

}
