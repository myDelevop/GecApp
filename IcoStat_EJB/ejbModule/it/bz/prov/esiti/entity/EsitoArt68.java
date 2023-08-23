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
 * Entity che rappresenta dell'esito impegni
 * 
 * @author bpettazzoni
 * 
 */

@Entity
@IdClass(EsitoArt68Pk.class)
@Table(name = "OPP_ESITI_APP_ESITO_ART_68")
@NamedQueries({
		@NamedQuery(name = "EsitoArt68.selectAll", query = "SELECT ea FROM EsitoArt68 ea "
				+ "ORDER BY ea._cuaa, ea._campagna, ea._domanda, ea._intervento"),
/*
		@NamedQuery(name = "EsitoArt68.selectFilter", query = "SELECT ea FROM EsitoArt68 ea "
				+ "ORDER BY ea._cuaa, ea._campagna, ea._domanda, ea._intervento"),
*/
  @NamedQuery( name = "EsitoArt68.selectFilter", query =
	 "SELECT DISTINCT e FROM EsitoArt68 e, Azienda a, CampioneAggr c " +
	 "WHERE e._cuaa=a._cuaa AND e._domanda=c._domandaOpr AND e._campagna=c._campagna "
	 + "AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
	 "AND lower(e._cuaa) LIKE lower(:cuaa) " + "AND e._campagna LIKE :campagna " +
	 "AND c._misura LIKE :misura " + "AND c._domandaOpr LIKE :domanda " +
	 "AND (c._flagCampione is null OR c._flagCampione LIKE :campione) " +
	 "AND (c._flagExtraCampione is null OR c._flagExtraCampione LIKE :extraCampione) "
	 + "ORDER BY e._cuaa, e._campagna, e._domanda, e._intervento"),
	 
})
public class EsitoArt68 implements Serializable {

	private static final long serialVersionUID = 1021761692745812605L;

	@Id
	@Column(name = "INTERVENTO")
	private String _intervento;

	@Column(name = "ESITO")
	private String _esito;

	@Column(name = "IMPORTO_RIDUZIONE")
	private float _impRiduz;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;

	@Id
	@Column(name = "DOMANDA_OPR")
	private String _domanda;

	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;

	@Column(name = "NOTE")
	private String _note;

	@Column(name = "DATA_ISPEZIONE")
	private String _dataIspezione;

	@Column(name = "STATO")
	private String _stato;

	@Column(name = "DATA_INSERIMENTO")
	private Date _data_inserimento;

	@Column(name = "USER_INSERIMENTO")
	private String _user_inserimento;

	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;

	@Column(name = "USER_MODIFICA")
	private String _user_modifica;

	/**
	 * Costruttore
	 */
	public EsitoArt68() {
		_intervento = "";
		_esito = "";
		_impRiduz = 0;
		_cuaa = "";
		_domanda = "";
		_stato = "";
		_dataIspezione = "";
		_note = "";
		_user_inserimento = "";
		_user_modifica = "";
	}

	public String get_intervento() {
		return _intervento;
	}

	public void set_intervento(String intervento) {
		intervento = intervento == null ? "" : intervento;
		_intervento = intervento;
	}

	public String get_esito() {
		return _esito;
	}

	public void set_esito(String esito) {
		esito = esito == null ? "" : esito;
		_esito = esito;
	}

	public Float get_impRiduz() {
		return _impRiduz;
	}

	public void set_impRiduz(Float impRiduz) {
		_impRiduz = impRiduz;
	}

	public String get_cuaa() {
		return _cuaa;
	}

	public void set_cuaa(String cuaa) {
		cuaa = cuaa == null ? "" : cuaa;
		_cuaa = cuaa;
	}

	public String get_domanda() {
		return _domanda;
	}

	public void set_domanda(String domanda) {
		domanda = domanda == null ? "" : domanda;
		_domanda = domanda;
	}

	public String get_campagna() {
		return "" + _campagna;
	}

	public void set_campagna(int campagna) {
		_campagna = "" + campagna;
	}

	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

	public void set_dataIspezione(String _dataIspezione) {
		_dataIspezione = _dataIspezione == null ? "" : _dataIspezione;
		this._dataIspezione = _dataIspezione;
	}

	public String get_dataIspezione() {
		return _dataIspezione;
	}

	public void set_stato(String _stato) {
		_stato = _stato == null ? "" : _stato;
		this._stato = _stato;
	}

	public String get_stato() {
		return _stato;
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

}
