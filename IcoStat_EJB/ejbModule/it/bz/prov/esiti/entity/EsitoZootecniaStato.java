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
 * Entity che rappresenta l'esito complessivo per zootecnia.
 * 
 * @author bpettazzoni
 *
 */

@Entity
@IdClass(EsitoZootecniaStatoPk.class)
@Table(name = "OPP_ESITI_APP_ZOOT_STATO")
@NamedQueries({
		@NamedQuery(
				name = "EsitoZootecniaStato.selectAll",
				query = "SELECT zs FROM EsitoZootecniaStato zs " +
						"ORDER BY zs._cuaa, zs._campagna, zs._domanda"),
		@NamedQuery(
				name = "EsitoZootecniaStato.selectFilter",
				query = "SELECT DISTINCT e FROM EsitoZootecniaStato e, Azienda a, CampioneAggr c " +
				"WHERE e._cuaa=a._cuaa AND e._domanda=c._domandaOpr AND e._campagna=c._campagna " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(e._cuaa) LIKE lower(:cuaa) " +
				"AND e._campagna LIKE :campagna " +
				"AND c._misura LIKE :misura " +
				"AND c._domandaOpr LIKE :domanda " +
				"AND (c._flagCampione is null OR c._flagCampione LIKE :campione) " +
				"AND (c._flagExtraCampione is null OR c._flagExtraCampione LIKE :extraCampione) " +
				"ORDER BY e._cuaa, e._campagna, e._domanda"),
})
public class EsitoZootecniaStato implements Serializable {

	private static final long serialVersionUID = -2600004220053109658L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;

	@Id
	@Column(name = "DOMANDA")
	private String _domanda;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Column(name = "CONTR_AMM_BOVINI")
	private String _contrAmmBovini;
	
	@Column(name = "ESITO_AMM_BOVINI")
	private String _esitoAmmBovini;
	
	@Column(name = "CONTR_AMM_OVICAPRINI")
	private String _contrAmmOvicaprini;
	
	@Column(name = "ESITO_AMM_OVICAPRINI")
	private String _esitoAmmOvicaprini;
	
	@Column(name = "CONTR_COND")
	private String _contrCond;
	
	@Column(name = "PERSONA_PRESENTE")
	private String _personaPresente;
	
	@Column(name = "PERSONA_PRESENTE_RUOLO")
	private String _personaPresenteRuolo;
	
	@Column(name = "PERSONA_PRESENTE_DOC")
	private String _personaPresenteDocumento;
	
	@Column(name = "DOCUMENTAZIONE_CONTROLLO")
	private String _documentazioneControllo;
	
	@Column(name = "FLG_REGISTRO_BDN")
	private String _flgRegistroBDN;
	
	@Column(name = "FLG_FOTO_REGISTRO_STALLA")
	private String _flgFotoRegistroStalla;
	
	@Column(name = "FLG_ALTRO")
	private String _flgAltro;
	
	@Column(name = "NOTE")
	private String _note;
	
	@Column(name = "FLG_FIRMA_PRODUTTORE")
	private String _flgFirmaProduttore;
	
	@Column(name = "FLG_CONTROLLORE")
	private String _flgFirmaControllore;	
	
	@Column(name = "DATA_CONTROLLO")
	private Date _dataControllo;
	
	@Column(name = "NOME_CONTROLLORE")
	private String _nomeControllore;
		
	@Column(name = "DATA_CREAZIONE")
	private Date _data_inserimento;
	
	@Column(name = "USER_CREAZIONE")
	private String _user_inserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;
	
	@Column(name = "USER_MODIFICA")
	private String _user_modifica;
	
	@Column(name = "STATO_COMPILAZIONE")
	private String _statoCompilazione;
	
	/**
	 * Costruttore
	 */
	public EsitoZootecniaStato(){
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_contrAmmBovini="";
		_contrAmmOvicaprini = "";
		_contrCond = "";
		_esitoAmmBovini = "";
		_esitoAmmOvicaprini = "";
		_personaPresente = "";
		_personaPresenteDocumento = "";
		_personaPresenteRuolo = "";		
		_documentazioneControllo = "";
		_flgRegistroBDN = "";
		_flgFotoRegistroStalla = "";
		_flgAltro = "";
		_note = "";
		_flgFirmaProduttore = "";
		_flgFirmaControllore = "";
		_nomeControllore = "";
		_user_inserimento = "";
		_user_modifica = "";
		_statoCompilazione="";
	}
	
	public String get_cuaa(){
		return _cuaa;
	}
	
	public void set_cuaa(String cuaa){
		cuaa = cuaa == null ? "" : cuaa;
		_cuaa= cuaa;
	}
	
	public String get_domanda(){
		if(_domanda == null) return "";
		return _domanda;
	}
	
	public void set_domanda(String domanda){
		domanda = domanda == null ? "" : domanda;
		_domanda= domanda;
	}
	
	public String get_campagna(){
		return "" + _campagna;
	}
	
	public void set_campagna(int campagna){
		_campagna = "" + campagna;
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
		if(_user_inserimento == null) return "";
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
		if(_user_modifica == null) return "";
		return _user_modifica;
	}


	public void set_contrAmmBovini(String _contrAmmBovini) {
		_contrAmmBovini = _contrAmmBovini == null ? "" : _contrAmmBovini;
		this._contrAmmBovini = _contrAmmBovini;
	}


	public String get_contrAmmBovini() {
		if(_contrAmmBovini == null) return "";
		return _contrAmmBovini;
	}


	public void set_esitoAmmBovini(String _esitoAmmBovini) {
		_esitoAmmBovini = _esitoAmmBovini == null ? "" : _esitoAmmBovini;
		this._esitoAmmBovini = _esitoAmmBovini;
	}


	public String get_esitoAmmBovini() {
		if(_esitoAmmBovini == null) return "";
		return _esitoAmmBovini;
	}


	public void set_contrAmmOvicaprini(String _contrAmmOvicaprini) {
		_contrAmmOvicaprini = _contrAmmOvicaprini == null ? "" : _contrAmmOvicaprini;
		this._contrAmmOvicaprini = _contrAmmOvicaprini;
	}


	public String get_contrAmmOvicaprini() {
		if(_contrAmmOvicaprini == null) return "";
		return _contrAmmOvicaprini;
	}


	public void set_esitoAmmOvicaprini(String _esitoAmmOvicaprini) {
		_esitoAmmOvicaprini = _esitoAmmOvicaprini == null ? "" : _esitoAmmOvicaprini;
		this._esitoAmmOvicaprini = _esitoAmmOvicaprini;
	}


	public String get_esitoAmmOvicaprini() {
		if(_esitoAmmOvicaprini == null) return "";
		return _esitoAmmOvicaprini;
	}


	public void set_contrCond(String _contrCond) {
		_contrCond = _contrCond == null ? "" : _contrCond;
		this._contrCond = _contrCond;
	}


	public String get_contrCond() {
		if(_contrCond == null) return "";
		return _contrCond;
	}

	public void set_personaPresente(String _personaPresente) {
		_personaPresente = _personaPresente == null ? "" : _personaPresente;
		this._personaPresente = _personaPresente;
	}

	public String get_personaPresente() {
		if(_personaPresente == null) return "";
		return _personaPresente;
	}

	public void set_personaPresenteRuolo(String _personaPresenteRuolo) {
		_personaPresenteRuolo = _personaPresenteRuolo == null ? "" : _personaPresenteRuolo;
		this._personaPresenteRuolo = _personaPresenteRuolo;
	}

	public String get_personaPresenteRuolo() {
		if(_personaPresenteRuolo == null) return "";
		return _personaPresenteRuolo;
	}

	public void set_personaPresenteDocumento(String _personaPresenteDocumento) {
		_personaPresenteDocumento = _personaPresenteDocumento == null ? "" : _personaPresenteDocumento;
		this._personaPresenteDocumento = _personaPresenteDocumento;
	}

	public String get_personaPresenteDocumento() {
		if(_personaPresenteDocumento == null) return "";
		return _personaPresenteDocumento;
	}

	public void set_documentazioneControllo(String _documentazioneControllo) {
		_documentazioneControllo = _documentazioneControllo == null ? "" : _documentazioneControllo;
		this._documentazioneControllo = _documentazioneControllo;
	}

	public String get_documentazioneControllo() {
		if(_documentazioneControllo == null) return "";
		return _documentazioneControllo;
	}

	public void set_flgRegistroBDN(String _flgRegistroBDN) {
		_flgRegistroBDN = _flgRegistroBDN == null ? "" : _flgRegistroBDN;
		this._flgRegistroBDN = _flgRegistroBDN;
	}

	public String get_flgRegistroBDN() {
		if(_flgRegistroBDN == null) return "";
		return _flgRegistroBDN;
	}

	public void set_flgFotoRegistroStalla(String _flgFotoRegistroStalla) {
		_flgFotoRegistroStalla = _flgFotoRegistroStalla == null ? "" : _flgFotoRegistroStalla;
		this._flgFotoRegistroStalla = _flgFotoRegistroStalla;
	}

	public String get_flgFotoRegistroStalla() {
		if(_flgFotoRegistroStalla == null) return "";
		return _flgFotoRegistroStalla;
	}

	public void set_flgAltro(String _flgAltro) {
		_flgAltro = _flgAltro == null ? "" : _flgAltro;
		this._flgAltro = _flgAltro;
	}

	public String get_flgAltro() {
		if(_flgAltro == null) return "";
		return _flgAltro;
	}

	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}

	public String get_note() {
		if(_note == null) return "";
		return _note;
	}

	public void set_flgFirmaProduttore(String _flgFirmaProduttore) {
		_flgFirmaProduttore = _flgFirmaProduttore == null ? "" : _flgFirmaProduttore;
		this._flgFirmaProduttore = _flgFirmaProduttore;
	}

	public String get_flgFirmaProduttore() {
		if(_flgFirmaProduttore == null) return "";
		return _flgFirmaProduttore;
	}

	public void set_flgFirmaControllore(String _flgFirmaControllore) {
		_flgFirmaControllore = _flgFirmaControllore == null ? "" : _flgFirmaControllore;
		this._flgFirmaControllore = _flgFirmaControllore;
	}

	public String get_flgFirmaControllore() {
		if(_flgFirmaControllore == null) return "";
		return _flgFirmaControllore;
	}

	public void set_dataControllo(Date _dataControllo) {
		this._dataControllo = _dataControllo;
	}

	public Date get_dataControllo() {
		return _dataControllo;
	}

	public void set_nomeControllore(String _nomeControllore) {
		_nomeControllore = _nomeControllore == null ? "" : _nomeControllore;
		this._nomeControllore = _nomeControllore;
	}

	public String get_nomeControllore() {
		if(_nomeControllore == null) return "";
		return _nomeControllore;
	}

	public void set_statoCompilazione(String _statoCompilazione) {
		_statoCompilazione = _statoCompilazione == null ? "" : _statoCompilazione;
		this._statoCompilazione = _statoCompilazione;
	}

	public String get_statoCompilazione() {
		return _statoCompilazione;
	}

	
	
}
