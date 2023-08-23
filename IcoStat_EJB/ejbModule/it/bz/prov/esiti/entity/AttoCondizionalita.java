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
 * Entity che rappresenta l'anagrafica dell'atto di condizionalità 
 * 
 * @author bpettazzoni
 *
 */


@Entity
@Table(name = "OPP_ESITI_APP_CODICI_COND")
@NamedQueries({
		@NamedQuery(
				name = "AttoCondizionalita.selectAll",
				query = "SELECT ac FROM AttoCondizionalita ac ORDER BY ac._flg_valida desc, ac._codCond, _attoCond"),
		@NamedQuery(
				name = "AttoCondizionalita.selectDistinctCodCond",
				query = "SELECT DISTINCT ac._codCond FROM AttoCondizionalita ac WHERE ac._flg_valida='S' ORDER BY  ac._codCond"),
		@NamedQuery(
				name = "AttoCondizionalita.selectDistinctAttoNorma",
				query = "SELECT DISTINCT ac._attoCond FROM AttoCondizionalita ac WHERE ac._flg_valida='S' ORDER BY  ac._attoCond"),
		@NamedQuery(
				name = "AttoCondizionalita.selectDistinctAttoNormaForCodice",
				query = "SELECT DISTINCT ac._attoCond FROM AttoCondizionalita ac " +
						"WHERE ac._codCond= :codice " +
						"ORDER BY  ac._attoCond"),
		@NamedQuery(
				name = "AttoCondizionalita.findAttoCodice",
				query = "SELECT DISTINCT ac._attoCond FROM AttoCondizionalita ac " +
						"WHERE ac._codCond= :codice " +
						"AND ac._attoCond= :atto"),						
		@NamedQuery(
				name = "AttoCondizionalita.selectCodiceFromAtto",
				query = "SELECT DISTINCT ac._codCond FROM AttoCondizionalita ac " +
						"WHERE ac._attoCond= :atto"),
		@NamedQuery(
				name = "AttoCondizionalita.selectAttoNormaForCampagna",
				query = "SELECT DISTINCT ac._attoCond FROM AttoCondizionalita ac " +
						"WHERE (ac._campagnaInizio <= :campagna " +
						"AND ac._campagnaFine >= :campagna)"),
		@NamedQuery(
				name = "AttoCondizionalita.selectCodCondForCampagna",
				query = "SELECT DISTINCT ac._codCond FROM AttoCondizionalita ac " +
						"WHERE (ac._campagnaInizio <= :campagna " +
						"AND ac._campagnaFine >= :campagna)"),
})
public class AttoCondizionalita implements Serializable {
	
	private static final long serialVersionUID = 6859915745819341871L;

	@Column(name = "COD_COND")
	private String _codCond;
	
	@Id
	@Column(name = "ATTO_NORMA")
	private String _attoCond;
	
	@Column(name = "DESCR_ATTO")
	private String _atto_descr;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _data_inserimento;
	
	@Column(name = "USER_INSERIMENTO")
	private String _user_inserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;
	
	@Column(name = "USER_MODIFICA")
	private String _user_modifica;

	@Column(name = "CAMPAGNA_INIZIO")
	private int _campagnaInizio;

	@Column(name = "CAMPAGNA_FINE")
	private int _campagnaFine;

	@Column(name = "FLG_VALIDA")
	private String _flg_valida;
	
	
	public AttoCondizionalita()
	{
		_attoCond = "";
		_codCond = "";
		_user_inserimento = "";
		_user_modifica = "";
		_atto_descr = "";
		_flg_valida = "";
	}
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return AttoCondizionalita
	 */
	public AttoCondizionalita clone()
	{
		AttoCondizionalita atto =  new AttoCondizionalita();
		atto.set_codCond(_codCond);
		atto.set_attoCond(_attoCond);
		atto.set_data_inserimento(_data_inserimento);
		atto.set_data_modifica(_data_modifica);
		atto.set_user_inserimento(_user_inserimento);
		atto.set_user_modifica(_user_modifica);
		atto.set_atto_descr(_atto_descr);
		atto.set_campagnaInizio(_campagnaInizio);
		atto.set_campagnaFine(_campagnaFine);
		atto.set_flg_valida(_flg_valida);
		return atto;
	}

	public void set_codCond(String _codCond) {
		_codCond = _codCond == null ? "" : _codCond;
		this._codCond = _codCond;
	}

	public String get_codCond() {
		return _codCond;
	}

	public void set_attoCond(String _attoCond) {
		_attoCond = _attoCond == null ? "" : _attoCond;
		this._attoCond = _attoCond;
	}

	public String get_attoCond() {
		return _attoCond;
	}

	public void set_data_inserimento(Date _data_inserimento) {
		this._data_inserimento = _data_inserimento;
	}

	public Date get_data_inserimento() {
		return _data_inserimento;
	}

	public void set_user_inserimento(String _user_inserimento) {
		_user_inserimento = _user_inserimento == null ? "" : _user_inserimento;
		this._user_inserimento = _user_inserimento;
	}

	public String get_user_inserimento() {
		return _user_inserimento;
	}

	public void set_data_modifica(Date _data_modifica) {
		this._data_modifica = _data_modifica;
	}

	public Date get_data_modifica() {
		return _data_modifica;
	}

	public void set_user_modifica(String _user_modifica) {
		_user_modifica = _user_modifica == null ? "" : _user_modifica;
		this._user_modifica = _user_modifica;
	}

	public String get_user_modifica() {
		return _user_modifica;
	}

	public void set_atto_descr(String _atto_descr) {
		_atto_descr = _atto_descr == null ? "" : _atto_descr;
		this._atto_descr = _atto_descr;
	}

	public String get_atto_descr() {
		return _atto_descr;
	}

	public int get_campagnaInizio() {
		return _campagnaInizio;
	}

	public void set_campagnaInizio(int _campagnaInizio) {
		this._campagnaInizio = _campagnaInizio;
	}

	public int get_campagnaFine() {
		return _campagnaFine;
	}

	public void set_campagnaFine(int _campagnaFine) {
		this._campagnaFine = _campagnaFine;
	}

	public String get_flg_valida() {
		return _flg_valida;
	}

	public void set_flg_valida(String _flg_valida) {
		_flg_valida = _flg_valida == null ? "" : _flg_valida;
		this._flg_valida = _flg_valida;
	}
	

}
