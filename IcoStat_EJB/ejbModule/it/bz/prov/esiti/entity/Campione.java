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
 * Entity che rappresenta la domanda a campione con i relativi dati del campione
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(CampionePk.class)
@Table(name = "OPP_ESITI_APP_CAMPIONE")
@NamedQueries({
		@NamedQuery(
				name = "Campione.selectAll",
				query = "SELECT c FROM Campione c ORDER BY c._cuaa, c._campagna, c._domanda"),
		@NamedQuery(
				name = "Campione.selectFilter",
				query = "SELECT distinct c FROM Campione c, Azienda a, CampioneAggr d " +
				"WHERE c._cuaa=a._cuaa AND c._domanda=d._domandaOpr AND c._campagna=d._campagna " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(c._cuaa) LIKE lower(:cuaa) " +
				"AND c._campagna LIKE :campagna " +
				"AND d._misura LIKE :misura " +
				"AND d._domandaOpr LIKE :domanda " +
				"AND (d._flagCampione is null OR d._flagCampione LIKE :campione) " +
				"AND (d._flagExtraCampione is null OR d._flagExtraCampione LIKE :extraCampione) " +
				"ORDER BY c._cuaa, c._campagna, c._domanda"),
		@NamedQuery(
				name = "Campione.selectCampioneForCuaaCampagna",
				query = "SELECT distinct c._cuaa, c._campagna FROM Campione c " +
						"WHERE c._cuaa= :cuaa " +
						"and c._campagna= :campagna"),
		@NamedQuery(
				name = "Campione.selectCampioneCondExtraForCuaaCampagna",
				query = "SELECT distinct c " + 
						"FROM Campione c " +
						"WHERE c._cuaa= :cuaa " +
						"and c._campagna= :campagna " +
						"and c._categoria = 'COND EXTRA CAMPIONE'"),
		@NamedQuery(
				name = "Campione.domForCuaaCampagnaMisuraCampArt68",
				query = "SELECT distinct d " +
						"FROM Campione c, Domanda d " +
						"WHERE c._campagna=d._campagna " +
						"AND c._domanda=d._idDomanda " +
						"AND c._cuaa=d._cuaa " +
						"AND d._campagna= :campagna " +
						"AND d._cuaa= :cuaa " + 
						"AND d._misura= :misura"),		
})
public class Campione implements Serializable {
	
	private static final long serialVersionUID = -1752111293611455339L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Id
	@Column(name = "DOMANDA_OPR")
	private String _domanda;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Id
	@Column(name = "CATEGORIA_CAMPIONE")
	private String _categoria;
	
	@Column(name = "TIPO_CAMPIONE")
	private String _tipo;
	
	@Column(name = "DATA_CAMPIONE")
	private Date _dataCampione;
	
	@Column(name = "DOMINIO_CAMP_COND")
	private String _dominioCampCond;
	
	@Column(name = "STATO_DOMANDA_OPPAB")
	private String _statoDomandaOPPAB;
		
	@Column(name = "NOTE")
	private String _note;
	
	@Column(name = "ORIGINE_CAMPIONE")
	private String _origineCampione;
	
	@Column(name = "BOVINI")
	private String _bovini;
	
	@Column(name = "OVICAPRINI")
	private String _ovicaprini;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _dataInserimento;
	
	@Column(name = "USER_INSERIMENTO")
	private String _userInserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _dataModifica;
	
	@Column(name = "USER_MODIFICA")
	private String _userModifica;
	
	/**
	 * costruttore
	 */
	public Campione(){
		_cuaa = "";
		_domanda = "";
		_campagna= "";
		_categoria = "";
		_tipo = "";
		_dominioCampCond = "";
		_statoDomandaOPPAB = "";
		_note = "";
		_origineCampione = "";
		_bovini = "";
		_ovicaprini = "";
		_userInserimento = "";
		_userModifica= "";
	}

	public void set_cuaa(String _cuaa) {
		_cuaa = _cuaa == null ? "" : _cuaa;
		this._cuaa = _cuaa;
	}

	public String get_cuaa() {
		return _cuaa == null ? _cuaa = "" : _cuaa;
	}

	public void set_domanda(String _domanda) {
		_domanda = _domanda == null ? "" : _domanda;
		this._domanda = _domanda;
	}

	public String get_domanda() {
		return _domanda == null ? _domanda = "" : _domanda;
	}

	public void set_campagna(String _campagna) {
		_campagna = _campagna == null ? "" : _campagna;
		this._campagna = _campagna;
	}

	public String get_campagna() {
		return _campagna == null ? _campagna = "" : _campagna;
	}

	public void set_note(String _note) {
		if(_note != null) this._note = _note;
	}

	public String get_note() {
		return _note == null ? _note = "" : _note;
	}

	public void set_categoria(String _categoria) {
		_categoria = _categoria == null ? "" : _categoria;
		this._categoria = _categoria;
	}

	public String get_categoria() {
		return _categoria == null ? _categoria = "" : _categoria;
	}

	public void set_tipo(String _tipo) {
		_tipo = _tipo == null ? "" : _tipo;
		this._tipo = _tipo;
	}

	public String get_tipo() {
		return _tipo == null ? _tipo = "" : _tipo;

	}

	public void set_dominioCampCond(String _dominioCampCond) {
		_dominioCampCond = _dominioCampCond == null ? "" : _dominioCampCond;
		this._dominioCampCond = _dominioCampCond;
	}

	public String get_dominioCampCond() {
		return _dominioCampCond == null ? _dominioCampCond = "" : _dominioCampCond;
	}

	public void set_statoDomandaOPPAB(String _statoDomandaOPPAB) {
		_statoDomandaOPPAB = _statoDomandaOPPAB == null ? "" : _statoDomandaOPPAB;
		this._statoDomandaOPPAB = _statoDomandaOPPAB;
	}

	public String get_statoDomandaOPPAB() {
		return _statoDomandaOPPAB == null ? _statoDomandaOPPAB = "" : _statoDomandaOPPAB;
	}

	public void set_origineCampione(String _origineCampione) {
		_origineCampione = _origineCampione == null ? "" : _origineCampione;
		this._origineCampione = _origineCampione;
	}

	public String get_origineCampione() {
		return _origineCampione == null ? _origineCampione = "" : _origineCampione;
	}

	public void set_bovini(String _bovini) {
		_bovini = _bovini == null ? "" : _bovini;
		this._bovini = _bovini;
	}

	public String get_bovini() {
		return _bovini == null ? _bovini = "" : _bovini;
	}

	public void set_ovicaprini(String _ovicaprini) {
		_ovicaprini = _ovicaprini == null ? "" : _ovicaprini;
		this._ovicaprini = _ovicaprini;
	}

	public String get_ovicaprini() {
		return _ovicaprini == null ? _ovicaprini = "" : _ovicaprini;
	}

	public void set_dataInserimento(Date _dataInserimento) {
		this._dataInserimento = _dataInserimento;
	}

	public Date get_dataInserimento() {
		return _dataInserimento;
	}

	public void set_userInserimento(String _userInserimento) {
		_userInserimento = _userInserimento == null ? "" : _userInserimento;
		this._userInserimento = _userInserimento;
	}

	public String get_userInserimento() {
		return _userInserimento == null ? _userInserimento = "" : _userInserimento;
	}

	public void set_dataModifica(Date _dataModifica) {
		this._dataModifica = _dataModifica;
	}

	public Date get_dataModifica() {
		return _dataModifica;
	}

	public void set_userModifica(String _userModifica) {
		_userModifica = _userModifica == null ? "" : _userModifica;
		this._userModifica = _userModifica;
	}

	public String get_userModifica() {
		return _userModifica == null ? _userModifica = "" : _userModifica;
	}

	public void set_dataCampione(Date _dataCampione) {
		this._dataCampione = _dataCampione;
	}

	public Date get_dataCampione() {
		return _dataCampione;
	}

	

}
