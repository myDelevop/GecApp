package it.bz.prov.esiti.entity;

import java.io.Serializable;
import java.util.Date;

import it.bz.prov.esiti.util.Utils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity che rappresenta l'esito a superfice
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(EsitoSuperficiPk.class)
@Table(name = "OPP_ESITI_APP_ESITO_SUP")
@NamedQueries({
		@NamedQuery(
				name = "EsitoSuperfici.selectAll",
				query = "SELECT es FROM EsitoSuperfici es"),
		@NamedQuery(
				name = "EsitoSuperfici.selectFilter",
				query = "SELECT DISTINCT e FROM EsitoSuperfici e, Azienda a, CampioneAggr c " +
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
public class EsitoSuperfici implements Serializable {
	
	private static final long serialVersionUID = -8249855712735876202L;
	
	@Column(name = "ID")
	private int _id;
	
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Id
	@Column(name = "DOMANDA_OPR")
	private String _domanda;
	
	@Id
	@Column(name = "DOMANDA_AGEA")
	private String _domandaAgea;
	
	@Id
	@Column(name = "MISURA")
	private String _misura;
	
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Id
	@Column(name = "INTERVENTO")
	private String _intervento;
	
	@Id
	@Column(name = "SOTTOINTERVENTO")
	private String _sottointervento;
	
	@Column(name = "STATO_AZIONE")
	private String _statoAzione;

	@Column(name = "GRUPPO_COLTURA")
	private String _gruppoColtura;
	
	@Column(name = "SUP_DICHIARATA")
	private Float _supDichiarata;
	
	@Column(name = "SUP_ACCERTATA")
	private Float _supAccertata;
	
	@Column(name = "DIFFERENZA")
	private Float _differenza;
	
	@Column(name = "SCOST_PERC")
	private Float _scostPerc;
	
	@Column(name = "SANZIONE_ANNULLATA")
	private String _sanzioneAnnullata;
	
	@Column(name = "NOTE")
	private String _note;
	
	@Column(name = "CHANGED")
	private String _changed;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _data_inserimento;
	
	@Column(name = "USER_INSERIMENTO")
	private String _user_inserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;
	
	@Column(name = "USER_MODIFICA")
	private String _user_modifica;
	
	@Column(name = "CARTELLINO_GIALLO")
	private String _cartellino_giallo;
	
	@Column(name= "ESITO")
	private String _esito;
	
	@Column(name="DATA_CONTROLLO")
	private Date _dataControllo;
	
	@Column(name="EFFETTO")
	private String _effetto;
	
	@Column(name = "SANZIONE")
	private Float _sanzione;
	/**
	 * costruttore
	 */
	public EsitoSuperfici(){
		_cuaa = "";
		_domanda = "";
//		_domanda = new Domanda();
		_domandaAgea = "";
		_misura = "";
		_campagna = "";
		_intervento = "";
		_sottointervento = "";
		_statoAzione = "";
		_gruppoColtura = "";
		_note = "";
		_user_inserimento = "";
		_user_modifica = "";
		_changed = "";
		_sanzioneAnnullata = "";
		_cartellino_giallo = "";
		_esito = "";
		_dataControllo = null;
		_effetto = "";
		_sanzione = null;
	}
	
	
	public void set_id(int id){
		_id = id;
	}
	
	public int get_id(){
		return _id ;
	}
	
	public void set_cuaa(String cuaa){
		cuaa = cuaa == null ? "" : cuaa;
		_cuaa = cuaa;
	}
	
	public String get_cuaa(){
		return _cuaa ;
	}
	
	public void set_domanda(String domanda){
		domanda = domanda == null ? "" : domanda;
		_domanda = domanda;
	}
	
	public String get_domanda(){
		return _domanda;
	}
	
	public void set_domandaAgea(String domandaAgea){
		domandaAgea = domandaAgea == null ? "" : domandaAgea;
		_domandaAgea = domandaAgea;
	}
	
	public String get_domandaAgea(){
		return _domandaAgea;
	}
	
	public void set_misura(String misura){
		misura = misura == null ? "" : misura;
		_misura = misura;
	}
	
	public String get_misura(){
		return _misura;
	}
	
	public void set_campagna(String campagna){
		campagna = campagna == null ? "" : campagna;
		_campagna = campagna;
	}
	
	public String get_campagna(){
		return _campagna ;
	}
	
	public void set_intervento(String intervento){
		intervento = intervento == null ? "" : intervento;
		_intervento=intervento;
	}
	
	public String get_intervento(){
		return _intervento ;
	}
	
	public void set_sottointervento(String sottointervento){
		sottointervento = sottointervento == null ? "" : sottointervento;
		_sottointervento = sottointervento;
	}
	
	public String get_sottointervento(){
		return _sottointervento ;
	}
	
	public void set_statoAzione(String statoAzione){
		statoAzione = statoAzione == null ? "" : statoAzione;
		_statoAzione = statoAzione;
	}
	
	public String get_statoAzione(){
		return _statoAzione ;
	}
	
	public void set_gruppoColtura(String gruppoColtura){
		gruppoColtura = gruppoColtura == null ? "" : gruppoColtura;
		_gruppoColtura = gruppoColtura;
	}
	
	public String get_gruppoColtura(){
		return _gruppoColtura;
	}
	
	public void set_note(String note){
		note = note == null ? "" : note;
		_note = note;
	}
	
	public String get_note(){
		return _note ;
	}
	
	public void set_supDichiarata(Float supDichiarata){
		_supDichiarata =supDichiarata;
	}
	
	public String get_supDichiarata(){
		String value = "";
		if(_supDichiarata != null) value = "" + _supDichiarata;
		return value;
	}
	
	public void set_supAccertata(Float supAccertata){
		_supAccertata = supAccertata;
	}
	
	public String get_supAccertata(){
		String value = "";
		if(_supAccertata != null) value = "" + _supAccertata;
		return value;
	}
	
	public void set_differenza(Float differenza){
		_differenza = differenza;
	}
	
	public String get_differenza(){
		String value = "";
		if(_differenza != null) value = "" + _differenza;
		return value;
	}
	
	public void set_scostPerc(float scostPerc){
		_scostPerc = scostPerc; 
	}
	
	public String get_scostPerc(){
		String value = "";
		if(_scostPerc != null) value = Utils.roundNumber(_scostPerc);
		 return value;
		
	}
	
	public EsitoSuperfici clone()
	{
		EsitoSuperfici esito = new EsitoSuperfici();
		esito._campagna = _campagna;
		esito._cuaa = _cuaa;
		esito._differenza = _differenza;
		esito._domanda = _domanda;
		esito._domandaAgea = _domandaAgea;
		esito._misura = _misura;
		esito._gruppoColtura = _gruppoColtura;
		esito._id = _id;
		esito._intervento = _intervento;
		esito._note = _note;
		esito._scostPerc = _scostPerc;
		esito._sottointervento = _sottointervento;
		esito._statoAzione = _statoAzione;
		esito._supAccertata = _supAccertata;
		esito._supDichiarata = _supDichiarata;
		esito._sanzioneAnnullata = _sanzioneAnnullata;
		esito._cartellino_giallo = _cartellino_giallo;
		esito._esito = _esito;
		esito._sanzione = _sanzione;
		return esito;
	}
	
	/**
	 * fa rol back dell'oggetto con una version precedente
	 * @param esitoSuperfici
	 */
	public void recovery(EsitoSuperfici esito)
	{
		_campagna = esito._campagna;
		_cuaa = esito._cuaa;
		_differenza = esito._differenza;
		_domanda = esito._domanda;
		_domandaAgea = esito._domandaAgea;
		_misura = esito._misura;
		_gruppoColtura = esito._gruppoColtura ;
		_id = esito._id ;
		_intervento = esito._intervento ;
		_note = esito._note ;
		_scostPerc = esito._scostPerc ;
		_sottointervento = esito._sottointervento ;
		_statoAzione = esito._statoAzione ;
		_supAccertata = esito._supAccertata ;
		_supDichiarata = esito._supDichiarata ;
		_sanzioneAnnullata = esito._sanzioneAnnullata;
		_cartellino_giallo = esito._cartellino_giallo;
		_esito = esito._esito;
		_sanzione = esito._sanzione;
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


	public void set_changed(String _changed) {
		_changed = _changed == null ? "" : _changed;
		this._changed = _changed;
	}


	public String get_changed() {
		return _changed;
	}


	public void set_sanzioneAnnullata(String _sanzioneAnnullata) {
		_sanzioneAnnullata = _sanzioneAnnullata == null ? "" : _sanzioneAnnullata;
		this._sanzioneAnnullata = _sanzioneAnnullata;
	}


	public String get_sanzioneAnnullata() {
		return _sanzioneAnnullata;
	}


	public String get_cartellino_giallo() {
		return _cartellino_giallo;
	}


	public void set_cartellino_giallo(String _cartellino_giallo) {
		_cartellino_giallo = _cartellino_giallo == null ? "" : _cartellino_giallo;
		this._cartellino_giallo = _cartellino_giallo;
	}


	public String get_esito() {
		return _esito;
	}


	public void set_esito(String _esito) {
		_esito = _esito == null ? "" : _esito;
		this._esito = _esito;
	}


	public String get_effetto() {
		return _effetto;
	}


	public void set_effetto(String _effetto) {
		_effetto = _effetto == null ? "" : _effetto;
		this._effetto = _effetto;
	}


	public Date get_dataControllo() {
		return _dataControllo;
	}


	public void set_dataControllo(Date _data_controllo) {
		this._dataControllo = _data_controllo;
	}


	public String get_sanzione() {
		String value = "";
		if(_sanzione != null) value = "" + _sanzione;
		return value;
	}


	public void set_sanzione(Float _sanzione) {
		this._sanzione = _sanzione;
	}
	
}

