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
@IdClass(EsitoImpegniPk.class)
@Table(name = "OPP_ESITI_APP_ESITO_IMPEGNI")
@NamedQueries({
		@NamedQuery(
				name = "EsitoImpegni.selectAll",
				query = "SELECT ei FROM EsitoImpegni ei " +
						"ORDER BY ei._cuaa, ei._campagna, ei._domanda, ei._intervento, ei._sottointervento"),
		@NamedQuery(
				name = "EsitoImpegni.selectFilter",
				query = "SELECT DISTINCT e FROM EsitoImpegni e, Azienda a, CampioneAggr c " +
				"WHERE e._cuaa=a._cuaa AND e._domanda=c._domandaOpr AND e._campagna=c._campagna " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(e._cuaa) LIKE lower(:cuaa) " +
				"AND e._campagna LIKE :campagna " +
				"AND c._misura LIKE :misura " +
				"AND c._domandaOpr LIKE :domanda " +
				"AND (c._flagCampione is null OR c._flagCampione LIKE :campione) " +
				"AND (c._flagExtraCampione is null OR c._flagExtraCampione LIKE :extraCampione) " +
				"ORDER BY e._cuaa, e._campagna, e._domanda, e._intervento, e._sottointervento"),
})
public class EsitoImpegni implements Serializable {

	private static final long serialVersionUID = -2600004220053109658L;
	
	@Column(name = "ID")
	private int _id;
	
	@Id
	@Column(name = "INTERVENTO")
	private String _intervento;
	
	@Id
	@Column(name = "SOTTOINTERVENTO")
	private String _sottointervento;
	
	@Column(name = "ESITO")
	private String _esito;
	
	@Column(name = "PERC_RID_SOTTOINTERVENTO")
	private String _percRid;

	@Column(name = "CUAA")
	private String _cuaa;

	@Id
	@Column(name = "DOMANDA_OPR")
	private String _domanda;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Id
	@Column(name = "CONTROLLO_ESTIVO")
	private String _controllo_estivo;
	
	@Column(name = "NOTE")
	private String _note;

	@Column(name = "DATA_CONTROLLO")
	private String _dataControllo;
	
	@Column(name = "STATO")
	private String _stato;
	
	@Column(name = "INADEMP_GRAVE")
	private String _inadempienza_grave;

	@Column(name = "REITERAZIONE")
	private String _reiterazione;

	@Column(name = "PROGR_REITERAZIONE")
	private String _progrReiterazione;
	
	@Column(name = "CARICO_ALPEGGIO")
	private String _caricoAlpeggio;
	
	@Column(name = "PERC_RID_COMMISS_RIESAME")
	private String _percRidCommissRiesame;
	
	@Column(name = "STATO_POST_COMMISS_RIESAME")
	private String _statoPostCommissRiesame;
	
	@Column(name = "DATA_COMMISS_RIESAME")
	private String _dataCommissRiesame;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _data_inserimento;
	
	@Column(name = "USER_INSERIMENTO")
	private String _user_inserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;
	
	@Column(name = "USER_MODIFICA")
	private String _user_modifica;

	@Column(name = "ESITO_PREMIO_TRASPORTO")
	private String _esitoPremioTrasporto;

	@Column(name = "PERC_RID_PREMIO_TRASPORTO")
	private String _percRidPremioTrasporto;

	@Column(name = "ESITO_RINUNCIA_INSILATO")
	private String _esitoRinunciaInsilato;

	@Column(name = "IMPORTO_RID_RINUNCIA_INSILATO")
	private Float _importoRidRinunciaInsilato;
	
	@Column(name = "PERC_RID_CARICO_BESTIAME")
	private String _perc_rid_carico_bestiame;
	
	@Column(name = "ESITO_TRASFORMAZIONE_LATTE")
	private String _esito_trasformazione_latte;
	
	@Column(name = "SEGNALAZIONE")
	private String _segnalazione;
	/**
	 * Costruttore
	 */
	public EsitoImpegni(){
		_intervento = "";
		_sottointervento = "";
		_esito = "";
		_percRid = "";
		_cuaa = "";
		_domanda = "";
//		_domanda = new Domanda();
		_stato = "";
		_inadempienza_grave = "";
		_reiterazione = "";
		_progrReiterazione = "";
		_dataControllo = "";
		_note = "";
		_user_inserimento = "";
		_user_modifica = "";
		_caricoAlpeggio = "";
		_percRidCommissRiesame = "";
		_dataCommissRiesame = "";
		_statoPostCommissRiesame = "";
		_esitoPremioTrasporto="";
		_percRidPremioTrasporto = "";
		_esitoRinunciaInsilato= "";
		_controllo_estivo = "";
		_perc_rid_carico_bestiame = "";
		_esito_trasformazione_latte = "";
		_segnalazione = "";
	}

	public String get_intervento(){
		return _intervento;
	}
	
	public void set_intervento(String intervento){
		intervento = intervento == null ? "" : intervento;
		_intervento = intervento;
	}
	
	public String get_sottointervento(){
		return _sottointervento;
	}
	
	public void set_sottointervento(String sottoIntervento){
		sottoIntervento = sottoIntervento == null ? "" : sottoIntervento;
		_sottointervento = sottoIntervento;
	}
	
	public String get_esito(){
		return _esito;
	}
	
	public void set_esito(String esito){
		esito = esito == null ? "" : esito;
		_esito = esito;
	}
	
	public String get_percRid(){
		if(_percRid== null)_percRid="";
		return _percRid;
	}
	
	public void set_percRid(String percRid){
		if(percRid== null) _percRid="";
		else _percRid = percRid;
	}
	
	public String get_cuaa(){
		return _cuaa;
	}
	
	public void set_cuaa(String cuaa){
		cuaa = cuaa == null ? "" : cuaa;
		_cuaa= cuaa;
	}
	
	public String get_domanda(){
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

	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

	public void set_dataControllo(String _dataControllo) {
		_dataControllo = _dataControllo == null ? "" : _dataControllo;
		this._dataControllo = _dataControllo;
	}

	public String get_dataControllo() {
		return _dataControllo;
	}

	
	public String get_inadempienza_grave() {
		return _inadempienza_grave;
	}

	public void set_inadempienza_grave(String _inadempienza_grave) {
		this._inadempienza_grave = _inadempienza_grave;
	}

	public String get_reiterazione() {
		return _reiterazione;
	}

	public void set_reiterazione(String _reiterazione) {
		this._reiterazione = _reiterazione;
	}

	public String get_progrReiterazione() {
		return _progrReiterazione;
	}

	public void set_progrReiterazione(String _progrReiterazione) {
		_progrReiterazione = _progrReiterazione == null ? "" : _progrReiterazione;
		this._progrReiterazione = _progrReiterazione;
	}

	public void set_stato(String _stato) {
		_stato = _stato == null ? "" : _stato;
		this._stato = _stato;
	}

	public String get_stato() {
		return _stato;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_id() {
		return _id;
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

	public void set_caricoAlpeggio(String _caricoAlpeggio) {
		_caricoAlpeggio = _caricoAlpeggio == null ? "" : _caricoAlpeggio;
		this._caricoAlpeggio = _caricoAlpeggio;
	}

	public String get_caricoAlpeggio() {
		return _caricoAlpeggio;
	}

	public void set_percRidCommissRiesame(String _percRidCommissRiesame) {
		if(_percRidCommissRiesame == null) this._percRidCommissRiesame="";
		else this._percRidCommissRiesame = _percRidCommissRiesame;
	}

	public String get_percRidCommissRiesame() {
		if(_percRidCommissRiesame==null) _percRidCommissRiesame="";
		return _percRidCommissRiesame;
	}

	public void set_statoPostCommissRiesame(String _statoPostCommissRiesame) {
		_statoPostCommissRiesame = _statoPostCommissRiesame == null ? "" : _statoPostCommissRiesame;
		this._statoPostCommissRiesame = _statoPostCommissRiesame;
	}

	public String get_statoPostCommissRiesame() {
		return _statoPostCommissRiesame;
	}

	public void set_dataCommissRiesame(String _dataCommissRiesame) {
		_dataCommissRiesame = _dataCommissRiesame == null ? "" : _dataCommissRiesame;
		this._dataCommissRiesame = _dataCommissRiesame;
	}

	public String get_dataCommissRiesame() {
		return _dataCommissRiesame;
	}

	public void set_esitoPremioTrasporto(String _esitoPremioTrasporto) {
		if(_esitoPremioTrasporto==null) this._esitoPremioTrasporto="";
		else this._esitoPremioTrasporto = _esitoPremioTrasporto;
	}

	public String get_esitoPremioTrasporto() {
		if(_esitoPremioTrasporto==null) _esitoPremioTrasporto="";
		return _esitoPremioTrasporto;
	}

	public void set_percRidPremioTrasporto(String _percRidPremioTrasporto) {
		if(_percRidPremioTrasporto == null) this._percRidPremioTrasporto ="";
		else this._percRidPremioTrasporto = _percRidPremioTrasporto;
	}

	public String get_percRidPremioTrasporto() {
		if(_percRidPremioTrasporto== null) _percRidPremioTrasporto="";
		return _percRidPremioTrasporto;
	}

	public void set_esitoRinunciaInsilato(String _esitoRinunciaInsilato) {
		if(_esitoRinunciaInsilato==null) this._esitoRinunciaInsilato="";
		else this._esitoRinunciaInsilato = _esitoRinunciaInsilato;
	}

	public String get_esitoRinunciaInsilato() {
		if(_esitoRinunciaInsilato == null) _esitoRinunciaInsilato="";
		return _esitoRinunciaInsilato;
	}

	public void set_importoRidRinunciaInsilato(Float _importoRidRinunciaInsilato) {
		this._importoRidRinunciaInsilato = _importoRidRinunciaInsilato;
	}

	public Float get_importoRidRinunciaInsilato() {
		return _importoRidRinunciaInsilato;
	}
	
	public String get_importoRidRinunciaInsilatoStr() {
		String value = "";
		if(_importoRidRinunciaInsilato != null) value = "" + _importoRidRinunciaInsilato;
		return value;
	}

	public String get_controllo_estivo() {
		return _controllo_estivo;
	}

	public void set_controllo_estivo(String _controllo_estivo) {
		_controllo_estivo = _controllo_estivo == null ? "" : _controllo_estivo;
		this._controllo_estivo = _controllo_estivo;
	}

	public String get_perc_rid_carico_bestiame() {
		return _perc_rid_carico_bestiame;
	}

	public void set_perc_rid_carico_bestiame(String _perc_rid_carico_bestiame) {
		_perc_rid_carico_bestiame = _perc_rid_carico_bestiame == null ? "" : _perc_rid_carico_bestiame;
		this._perc_rid_carico_bestiame = _perc_rid_carico_bestiame;
	}

	public String get_esito_trasnformazione_latte() {
		return _esito_trasformazione_latte;
	}

	public void set_esito_trasnformazione_latte(
			String _esito_trasnformazione_latte) {
		_esito_trasnformazione_latte = _esito_trasnformazione_latte == null ? "" : _esito_trasnformazione_latte;
		this._esito_trasformazione_latte = _esito_trasnformazione_latte;
	}

	public String get_segnalazione() {
		return _segnalazione;
	}

	public void set_segnalazione(String _segnalazione) {
		_segnalazione = _segnalazione == null ? "" : _segnalazione;
		this._segnalazione = _segnalazione;
	}

	
	
}
