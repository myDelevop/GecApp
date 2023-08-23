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
 * Entity che rappresenta il singolo controllo effettuato in loco
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(ControlloPk.class)
@Table(name = "OPP_ESITI_APP_CONTROLLO")
@NamedQueries({
		@NamedQuery(
				name = "Controllo.selectAll",
				query = "SELECT c FROM Controllo c ORDER BY c._cuaa, c._campagna"),
		@NamedQuery(
				name = "Controllo.selectFilter",
				query = "SELECT distinct c FROM Controllo c, Azienda a, CampioneAggr ca " +
				"WHERE c._cuaa=a._cuaa AND c._cuaa=ca._cuaa AND c._campagna=ca._campagna " +
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(c._cuaa) LIKE lower(:cuaa) " +
				"AND ca._campagna LIKE :campagna " +
				"AND ca._misura LIKE :misura " +
				"AND ca._domandaOpr LIKE :domanda " +
				"AND (ca._flagCampione is null OR ca._flagCampione LIKE :campione) " +
				"AND (ca._flagExtraCampione is null OR ca._flagExtraCampione LIKE :extraCampione) " +
				"ORDER BY c._cuaa, c._campagna"),
})
public class Controllo implements Serializable {

	private static final long serialVersionUID = 2311478326987201645L;
	
	@Id
	@Column(name = "CUAA")
	private String _cuaa;

	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Column(name = "DATA_PREAVVISO")
	private String _data_preavviso;
	
	@Column(name = "DATA_INIZIO_CONTROLLO")
	private String _data_inizio_controllo;
	
	@Column(name = "DATA_FINE_CONTROLLO")
	private String _data_fine_controllo;
	
	@Column(name = "CONTROLLORE")
	private String _controllore;
	
	@Column(name = "TIPO_CONTROLLO")
	private String _tipo_controllo;
	
	@Column(name = "TIPO_PREAVVISO")
	private String _tipoPreavviso;
	
	@Column(name = "PERSONA_PRESENTE")
	private String _personaPresente;
	
	@Column(name = "NOME_DELEGATO")
	private String _nomeDelegato;
	
	@Column(name = "NUMERO_DOCUMENTO_DELEGATO")
	private String _numeroDocumento;
		
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
	public Controllo()
	{
		_cuaa = "";
		_data_preavviso = "";
		_data_inizio_controllo = "";
		_data_fine_controllo = "";
		_controllore = "";
		_tipo_controllo = "";
		_user_inserimento = "";
		_user_modifica = "";
		_tipoPreavviso = "";
		_personaPresente = "";
		_nomeDelegato = "";
		_numeroDocumento = "";
			
		
	}
	
	public void set_cuaa(String _cuaa) {
		_cuaa = _cuaa == null ? "" : _cuaa;
		this._cuaa = _cuaa;
	}

	public String get_cuaa() {
		return _cuaa;
	}

	public void set_campagna(int _campagna) {
		this._campagna = "" + _campagna;
	}

	public String get_campagna() {
		return "" + _campagna;
	}

	public void set_data_preavviso(String _data_preavviso) {
		this._data_preavviso = _data_preavviso;
	}

	public String get_data_preavviso() {
		return _data_preavviso;
	}

	public void set_data_inizio_controllo(String _data_inizio_controllo) {
		_data_inizio_controllo = _data_inizio_controllo == null ? "" : _data_inizio_controllo;
		this._data_inizio_controllo = _data_inizio_controllo;
	}

	public String get_data_inizio_controllo() {
		return _data_inizio_controllo;
	}

	public void set_data_fine_controllo(String _data_fine_controllo) {
		_data_fine_controllo = _data_fine_controllo == null ? "" : _data_fine_controllo;
		this._data_fine_controllo = _data_fine_controllo;
	}

	public String get_data_fine_controllo() {
		return _data_fine_controllo;
	}

	public void set_controllore(String _controllore) {
		_controllore = _controllore == null ? "" : _controllore;
		this._controllore = _controllore;
	}

	public String get_controllore() {
		return _controllore;
	}

	public void set_tipo_controllo(String _tipo_controllo) {
		_tipo_controllo = _tipo_controllo == null ? "" : _tipo_controllo;
		this._tipo_controllo = _tipo_controllo;
	}

	public String get_tipo_controllo() {
		return _tipo_controllo;
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

	public void set_tipoPreavviso(String _tipoPreavviso) {
		_tipoPreavviso = _tipoPreavviso == null ? "" : _tipoPreavviso;
		this._tipoPreavviso = _tipoPreavviso;
	}

	public String get_tipoPreavviso() {
		return _tipoPreavviso;
	}

	public void set_personaPresente(String _personaPresente) {
		_personaPresente = _personaPresente == null ? "" : _personaPresente;
		this._personaPresente = _personaPresente;
	}

	public String get_personaPresente() {
		return _personaPresente;
	}

	public void set_nomeDelegato(String _nomeDelegato) {
		_nomeDelegato = _nomeDelegato == null ? "" : _nomeDelegato;
		this._nomeDelegato = _nomeDelegato;
	}

	public String get_nomeDelegato() {
		return _nomeDelegato;
	}

	public void set_numeroDocumento(String _numeroDocumento) {
		_numeroDocumento = _numeroDocumento == null ? "" : _numeroDocumento;
		this._numeroDocumento = _numeroDocumento;
	}

	public String get_numeroDocumento() {
		return _numeroDocumento;
	}


}
