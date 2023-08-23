package it.bz.prov.esiti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Entity che rappresenta l'esito del controllo di ammissibilità zootecnia per ovicaprini
 * 
 * @author bpettazzoni
 *
 */

@Entity
@IdClass(EsitoZootecniaEsitoAmmOviPk.class)
@Table(name = "OPP_ESITI_APP_ZOOT_ESI_AMM_OVI")
public class EsitoZootecniaEsitoAmmOvi implements Serializable {

	private static final long serialVersionUID = 8325269350530007000L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;

	@Id
	@Column(name = "DOMANDA")
	private String _domanda;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Column(name = "ESITO_CONTR_TIT_SPEC")
	private String _esitoControlloTitoliSpeciali;
	
	@Column(name = "PERC_RID_CONTR_TIT_SPEC")
	private String _percRidControlloTitoliSpeciali;
	
	@Column(name = "ESITO_CONTR_ART68_INT168")
	private String _esitoControlloArt68Int168;
	
	@Column(name = "PERC_RID_CONTR_ART68_INT168")
	private String _percRidControlloArt68Int168;
	
	@Column(name = "SANZ_FLG_APPL_PERC_RID")
	private String _sanzFlgApplPercRid;	

	@Column(name = "SANZ_PERC_RID")
	private String _sanzPercRid;	

	@Column(name = "SANZ_FLG_APPL_PERC_RID_DOPPIA")
	private String _sanzFlgApplPercRidDoppia;

	@Column(name = "SANZ_PERC_RID_DOPPIA")
	private String _sanzPercRidDoppia;

	@Column(name = "SANZ_FLG_ESCLUSIONE")
	private String _sanzFlgEsclusione;

	@Column(name = "SANZ_PERC_RID_ESCLUSIONE")
	private String _sanzPercRidEsclusione;
	
	@Column(name = "NOTE")
	private String _note;
	
	@Column(name = "DATA_CREAZIONE")
	private Date _data_inserimento;
	
	@Column(name = "USER_CREAZIONE")
	private String _user_inserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;
	
	@Column(name = "USER_MODIFICA")
	private String _user_modifica;
	
	@Column(name = "SANZ_FLG_ESCLUSIONE_ULTERIORE")
	private String _sanzFlgEsclusioneUlteriore;
	
	@Column(name = "FLG_AMM_CONTR_TIT_SPEC")
	private String _flgAmmControlloTitoliSpeciali;
	
	@Column(name = "ESI_CONTR_320")
	private String _esi_contr_320;
	
	@Column(name = "PERC_RID_320")
	private String _perc_rid_320;
	
	@Column(name = "FLG_APPL_PERC_RID_320")
	private String _flg_appl_perc_rid_320;
	
	@Column(name = "PERC_RID_ESI_320")
	private String _perc_rid_esi_320;
	
	@Column(name = "FLG_2X_PERC_RID_320")
	private String _flg_2x_perc_rid_320;
	
	@Column(name = "PERC_RID_2X_320")
	private String _perc_rid_2x_320;
	
	@Column(name = "FLG_ESC_PAG_320")
	private String _flg_esc_pag_320;
	
	@Column(name = "PERC_RID_ESC_PAG_320")
	private String _perc_rid_esc_pag_320;
	
	@Column(name = "FLG_ULT_ESC_PAG_320")
	private String _flg_ult_esc_pag_320;
	
	
	/**
	 * Costruttore
	 */
	public EsitoZootecniaEsitoAmmOvi(){
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_esitoControlloTitoliSpeciali="";
		_percRidControlloTitoliSpeciali = "";
		_esitoControlloArt68Int168 = "";
		_percRidControlloArt68Int168 = "";
		_sanzFlgApplPercRid = "";
		_sanzFlgApplPercRidDoppia = "";
		_sanzFlgEsclusione = "";
		_sanzPercRid = "";
		_sanzPercRidDoppia = "";
		_sanzPercRidEsclusione = "";
		_note = "";
		_user_inserimento = "";
		_user_modifica = "";
		_sanzFlgEsclusioneUlteriore = "";
		_flgAmmControlloTitoliSpeciali= "";
		
		_esi_contr_320 = ""; 
		_perc_rid_320 = ""; 
		_flg_appl_perc_rid_320 = ""; 
		_perc_rid_esi_320 = ""; 
		_flg_2x_perc_rid_320 = ""; 
		_perc_rid_2x_320 = ""; 
		_flg_esc_pag_320 = ""; 
		_perc_rid_esc_pag_320 = ""; 
		_flg_ult_esc_pag_320 = "";
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

	public void set_esitoControlloTitoliSpeciali(String _esitoControlloTitoliSpeciali) {
		_esitoControlloTitoliSpeciali = _esitoControlloTitoliSpeciali == null ? "" : _esitoControlloTitoliSpeciali;
		this._esitoControlloTitoliSpeciali = _esitoControlloTitoliSpeciali;
	}

	public String get_esitoControlloTitoliSpeciali() {
		if(_esitoControlloTitoliSpeciali == null) return "";
		return _esitoControlloTitoliSpeciali;
	}

	public void set_percRidControlloTitoliSpeciali(
			String _percRidControlloTitoliSpeciali) {
		_percRidControlloTitoliSpeciali = _percRidControlloTitoliSpeciali == null ? "" : _percRidControlloTitoliSpeciali;
		this._percRidControlloTitoliSpeciali = _percRidControlloTitoliSpeciali;
	}

	public String get_percRidControlloTitoliSpeciali() {
		if(_percRidControlloTitoliSpeciali == null) return "";
		return _percRidControlloTitoliSpeciali;
	}

	public void set_esitoControlloArt68Int168(String _esitoControlloArt68Int168) {
		_esitoControlloArt68Int168 = _esitoControlloArt68Int168 == null ? "" : _esitoControlloArt68Int168;
		this._esitoControlloArt68Int168 = _esitoControlloArt68Int168;
	}

	public String get_esitoControlloArt68Int168() {
		if(_esitoControlloArt68Int168 == null) return "";
		return _esitoControlloArt68Int168;
	}

	public void set_percRidControlloArt68Int168(String _percRidControlloArt68Int168) {
		_percRidControlloArt68Int168 = _percRidControlloArt68Int168 == null ? "" : _percRidControlloArt68Int168;
		this._percRidControlloArt68Int168 = _percRidControlloArt68Int168;
	}

	public String get_percRidControlloArt68Int168() {
		if(_percRidControlloArt68Int168 == null) return "";
		return _percRidControlloArt68Int168;
	}

	public void set_sanzFlgApplPercRid(String _sanzFlgApplPercRid) {
		_sanzFlgApplPercRid = _sanzFlgApplPercRid == null ? "" : _sanzFlgApplPercRid;
		this._sanzFlgApplPercRid = _sanzFlgApplPercRid;
	}

	public String get_sanzFlgApplPercRid() {
		if(_sanzFlgApplPercRid == null) return "";
		return _sanzFlgApplPercRid;
	}

	public void set_sanzPercRid(String _sanzPercRid) {
		_sanzPercRid = _sanzPercRid == null ? "" : _sanzPercRid;
		this._sanzPercRid = _sanzPercRid;
	}

	public String get_sanzPercRid() {
		if(_sanzPercRid == null) return "";
		return _sanzPercRid;
	}

	public void set_sanzFlgApplPercRidDoppia(String _sanzFlgApplPercRidDoppia) {
		_sanzFlgApplPercRidDoppia = _sanzFlgApplPercRidDoppia == null ? "" : _sanzFlgApplPercRidDoppia;
		this._sanzFlgApplPercRidDoppia = _sanzFlgApplPercRidDoppia;
	}

	public String get_sanzFlgApplPercRidDoppia() {
		if(_sanzFlgApplPercRidDoppia == null) return "";
		return _sanzFlgApplPercRidDoppia;
	}

	public void set_sanzPercRidDoppia(String _sanzPercRidDoppia) {
		_sanzPercRidDoppia = _sanzPercRidDoppia == null ? "" : _sanzPercRidDoppia;
		this._sanzPercRidDoppia = _sanzPercRidDoppia;
	}

	public String get_sanzPercRidDoppia() {
		if(_sanzPercRidDoppia == null) return "";
		return _sanzPercRidDoppia;
	}

	public void set_sanzFlgEsclusione(String _sanzFlgEsclusione) {
		_sanzFlgEsclusione = _sanzFlgEsclusione == null ? "" : _sanzFlgEsclusione;
		this._sanzFlgEsclusione = _sanzFlgEsclusione;
	}

	public String get_sanzFlgEsclusione() {
		if(_sanzFlgEsclusione == null) return "";
		return _sanzFlgEsclusione;
	}

	public void set_sanzPercRidEsclusione(String _sanzPercRidEsclusione) {
		_sanzPercRidEsclusione = _sanzPercRidEsclusione == null ? "" : _sanzPercRidEsclusione;
		this._sanzPercRidEsclusione = _sanzPercRidEsclusione;
	}

	public String get_sanzPercRidEsclusione() {
		if(_sanzPercRidEsclusione == null) return "";
		return _sanzPercRidEsclusione;
	}

	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}

	public String get_note() {
		if(_note == null) return "";
		return _note;
	}

	public void set_sanzFlgEsclusioneUlteriore(String _sanzFlgEsclusioneUlteriore) {
		_sanzFlgEsclusioneUlteriore = _sanzFlgEsclusioneUlteriore == null ? "" : _sanzFlgEsclusioneUlteriore;
		this._sanzFlgEsclusioneUlteriore = _sanzFlgEsclusioneUlteriore;
	}

	public String get_sanzFlgEsclusioneUlteriore() {
		if(_sanzFlgEsclusioneUlteriore == null) return "";
		return _sanzFlgEsclusioneUlteriore;
	}

	public void set_flgAmmControlloTitoliSpeciali(
			String _flgAmmControlloTitoliSpeciali) {
		_flgAmmControlloTitoliSpeciali = _flgAmmControlloTitoliSpeciali == null ? "" : _flgAmmControlloTitoliSpeciali;
		this._flgAmmControlloTitoliSpeciali = _flgAmmControlloTitoliSpeciali;
	}

	public String get_flgAmmControlloTitoliSpeciali() {
		return _flgAmmControlloTitoliSpeciali;
	}

	public String get_esi_contr_320() {
		return _esi_contr_320;
	}

	public void set_esi_contr_320(String _esi_contr_320) {
		_esi_contr_320 = _esi_contr_320 == null ? "" : _esi_contr_320;
		this._esi_contr_320 = _esi_contr_320;
	}

	public String get_perc_rid_320() {
		return _perc_rid_320;
	}

	public void set_perc_rid_320(String _perc_rid_320) {
		_perc_rid_320 = _perc_rid_320 == null ? "" : _perc_rid_320;
		this._perc_rid_320 = _perc_rid_320;
	}

	public String get_flg_appl_perc_rid_320() {
		return _flg_appl_perc_rid_320;
	}

	public void set_flg_appl_perc_rid_320(String _flg_appl_perc_rid_320) {
		_flg_appl_perc_rid_320 = _flg_appl_perc_rid_320 == null ? "" : _flg_appl_perc_rid_320;
		this._flg_appl_perc_rid_320 = _flg_appl_perc_rid_320;
	}

	public String get_perc_rid_esi_320() {
		return _perc_rid_esi_320;
	}

	public void set_perc_rid_esi_320(String _perc_rid_esi_320) {
		_perc_rid_esi_320 = _perc_rid_esi_320 == null ? "" : _perc_rid_esi_320;
		this._perc_rid_esi_320 = _perc_rid_esi_320;
	}

	public String get_flg_2x_perc_rid_320() {
		return _flg_2x_perc_rid_320;
	}

	public void set_flg_2x_perc_rid_320(String _flg_2x_perc_rid_320) {
		_flg_2x_perc_rid_320 = _flg_2x_perc_rid_320 == null ? "" : _flg_2x_perc_rid_320;
		this._flg_2x_perc_rid_320 = _flg_2x_perc_rid_320;
	}

	public String get_perc_rid_2x_320() {
		return _perc_rid_2x_320;
	}

	public void set_perc_rid_2x_320(String _perc_rid_2x_320) {
		_perc_rid_2x_320 = _perc_rid_2x_320 == null ? "" : _perc_rid_2x_320;
		this._perc_rid_2x_320 = _perc_rid_2x_320;
	}

	public String get_flg_esc_pag_320() {
		return _flg_esc_pag_320;
	}

	public void set_flg_esc_pag_320(String _flg_esc_pag_320) {
		_flg_esc_pag_320 = _flg_esc_pag_320 == null ? "" : _flg_esc_pag_320;
		this._flg_esc_pag_320 = _flg_esc_pag_320;
	}

	public String get_perc_rid_esc_pag_320() {
		return _perc_rid_esc_pag_320;
	}

	public void set_perc_rid_esc_pag_320(String _perc_rid_esc_pag_320) {
		_perc_rid_esc_pag_320 = _perc_rid_esc_pag_320 == null ? "" : _perc_rid_esc_pag_320;
		this._perc_rid_esc_pag_320 = _perc_rid_esc_pag_320;
	}

	public String get_flg_ult_esc_pag_320() {
		return _flg_ult_esc_pag_320;
	}

	public void set_flg_ult_esc_pag_320(String _flg_ult_esc_pag_320) {
		_flg_ult_esc_pag_320 = _flg_ult_esc_pag_320 == null ? "" : _flg_ult_esc_pag_320;
		this._flg_ult_esc_pag_320 = _flg_ult_esc_pag_320;
	}

	
	
}
