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
 * Entity che rappresenta l'esito di condizionalità per azienda 
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(EsitoCondizionalitaPk.class)
@Table(name = "OPP_ESITI_APP_ESITO_COND")
@NamedQueries({
		@NamedQuery(
				name = "EsitoCondizionalita.selectAll",
				query = "SELECT ec FROM EsitoCondizionalita ec ORDER BY ec._cuaa, ec._campagna"),
		@NamedQuery(
				name = "EsitoCondizionalita.selectFilter",
				query = "SELECT DISTINCT e FROM EsitoCondizionalita e, Azienda a, CampioneAggr c " +
				"WHERE e._cuaa=a._cuaa AND e._cuaa=c._cuaa AND e._campagna=c._campagna " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(e._cuaa) LIKE lower(:cuaa) " +
				"AND e._campagna LIKE :campagna " +
				"AND c._misura LIKE :misura " +
				"AND c._domandaOpr LIKE :domanda " +
				"AND (c._flagCampione is null OR c._flagCampione LIKE :campione) " +
				"AND (c._flagExtraCampione is null OR c._flagExtraCampione LIKE :extraCampione) " +
				"ORDER BY e._cuaa"),
})
public class EsitoCondizionalita implements Serializable {
	
	private static final long serialVersionUID = 7282512091934024523L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
//	@Column(name = "DATA_CAMPIONE")
//	private String _dataCampione;
	
	@Column(name = "DATA_CONTROLLO")
	private String _dataControllo;
	
	@Column(name = "ESITO_FINALE")
	private String _esitoFinale;
	
	@Column(name = "RID_PERC_DU")
	private String _ridPercDU;
	
	@Column(name = "RID_PERC_PSR")
	private String _ridPercPSR;
	
	@Column(name = "STATO")
	private String _stato;
	
	@Column(name = "NOTE")
	private String _note;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _data_inserimento;
	
	@Column(name = "USER_INSERIMENTO")
	private String _user_inserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;
	
	@Column(name = "USER_MODIFICA")
	private String _user_modifica;
	
	@Column(name = "NUMERO_DECRETO")
	private String _numero_decreto;
	
	@Column(name = "DATA_DECRETO")
	private Date _data_decreto;

	@Column(name = "NOTE_DECRETO")
	private String _note_decreto;
	
	
	/**
	 * costruttore
	 */
	public EsitoCondizionalita()
	{
		_cuaa = "";
		_campagna = "";
//		_dataCampione = "";
		_dataControllo = "";
		_esitoFinale = "";
		_stato = "";
		_note = "";
		_ridPercDU = "";
		_ridPercPSR = "";
		_user_inserimento = "";
		_user_modifica = "";
		_note_decreto="";
		_numero_decreto="";
	}
	
	
	public void set_cuaa(String _cuaa) {
		_cuaa = _cuaa == null ? "" : _cuaa;
		this._cuaa = _cuaa;
	}
	public String get_cuaa() {
		return _cuaa;
	}
	public void set_campagna(String _campagna) {
		_campagna = _campagna == null ? "" : _campagna;
		this._campagna = _campagna;
	}
	public String get_campagna() {
		return _campagna;
	}
	public void set_dataControllo(String _dataControllo) {
		_dataControllo = _dataControllo == null ? "" : _dataControllo;
		this._dataControllo = _dataControllo;
	}
	public String get_dataControllo() {
		return _dataControllo;
	}
	public void set_esitoFinale(String _esitoFinale) {
		_esitoFinale = _esitoFinale == null ? "" : _esitoFinale;
		this._esitoFinale = _esitoFinale;
	}
	public String get_esitoFinale() {
		return _esitoFinale;
	}
	public void set_stato(String _stato) {
		_stato = _stato == null ? "" : _stato;
		this._stato = _stato;
	}
	public String get_stato() {
		return _stato;
	}
	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}
	public String get_note() {
		return _note;
	}


	public void set_ridPercDU(String _ridPercDU) {
		if(!_ridPercDU.equals("null")) this._ridPercDU = _ridPercDU;
	}


	public String get_ridPercDU() {
		return _ridPercDU;
	}


	public void set_ridPercPSR(String _ridPercPSR) {
		_ridPercPSR = _ridPercPSR == null ? "" : _ridPercPSR;
		if(!_ridPercPSR.equals("null")) this._ridPercPSR = _ridPercPSR;
	}


	public String get_ridPercPSR() {
		return _ridPercPSR;
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
		if(!_user_modifica.equals("null")) this._user_modifica = _user_modifica;
	}


	public String get_user_modifica() {
		return _user_modifica;
	}


	public void set_numero_decreto(String _numero_decreto) {
		_numero_decreto = _numero_decreto == null ? "" : _numero_decreto;
		this._numero_decreto = _numero_decreto;
	}


	public String get_numero_decreto() {
		return _numero_decreto;
	}


	public void set_data_decreto(Date _data_decreto) {
		this._data_decreto = _data_decreto;
	}


	public Date get_data_decreto() {
		return _data_decreto;
	}


	public void set_note_decreto(String _note_decreto) {
		_note_decreto = _note_decreto == null ? "" : _note_decreto;
		this._note_decreto = _note_decreto;
	}


	public String get_note_decreto() {
		return _note_decreto;
	}

	
	
}
