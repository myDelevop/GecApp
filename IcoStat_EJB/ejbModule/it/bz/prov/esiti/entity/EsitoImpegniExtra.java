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
@IdClass(EsitoImpegniExtraPk.class)
@Table(name = "OPP_ESITI_APP_ESITO_IMP_EXT")
@NamedQueries({
		@NamedQuery(
				name = "EsitoImpegniExtra.selectAll",
				query = "SELECT ei FROM EsitoImpegniExtra ei " +
						"ORDER BY ei._cuaa, ei._campagna, ei._domanda, ei._intervento, ei._sottointervento"),
		@NamedQuery(
				name = "EsitoImpegniExtra.selectFilter",
				query = "SELECT DISTINCT e FROM EsitoImpegniExtra e, Azienda a, CampioneAggr c " +
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
public class EsitoImpegniExtra implements Serializable {

	private static final long serialVersionUID = -6185347539804630738L;
	
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Id
	@Column(name = "DOMANDA_OPR")
	private String _domanda;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Id
	@Column(name = "INTERVENTO")
	private String _intervento;
	
	@Id
	@Column(name = "SOTTOINTERVENTO")
	private String _sottointervento;
	
	@Id
	@Column(name = "IMPEGNO")
	private String _impegno;


	
	@Column(name = "ESITO")
	private String _esito;
	
	@Column(name = "PERC_RID")
	private Float _percRid;
	
	@Column(name = "STATO")
	private String _stato;
	
	@Column(name = "ESITO_RINUNCIA_INSILATO")
	private String _esitoRinunciaInsilato;

	@Column(name = "IMPORTO_RID_RINUNCIA_INSILATO")
	private Float _importoRidRinunciaInsilato;

	@Column(name = "ESCLUSIONE")
	private String _esclusione;
	
	@Column(name = "ESCLUSIONE_NOTE")
	private String _esclusioneNote;
	
	@Column(name = "REITERAZIONE")
	private String _reiterazione;
	
	@Column(name = "PROGR_ACC_REITERAZ")
	private String _progr_accert_reiteraz;
	
	@Column(name = "INADEMP_GRAVE")
	private String _inadempienza_grave;

	@Column(name = "PORTATA")
	private String _portata;
	
	@Column(name = "GRAVITA")
	private String _gravita;
	
	@Column(name = "DURATA")
	private String _durata;
	
	@Column(name = "SEGNALAZIONE")
	private String _segnalazione;
	
	@Column(name = "APPROVAZIONE")
	private String _approvazione;
	
	@Column(name = "PORTATA_NOTE")
	private String _portata_note;
	
	@Column(name = "GRAVITA_NOTE")
	private String _gravita_note;
	
	@Column(name = "DURATA_NOTE")
	private String _durata_note;
	
	@Column(name = "NUMERO_DECRETO")
	private String _numero_decreto;
	
	@Column(name = "DATA_DECRETO")
	private Date _data_decreto;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _data_inserimento;
	
	@Column(name = "USER_INSERIMENTO")
	private String _user_inserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;
	
	@Column(name = "USER_MODIFICA")
	private String _user_modifica;
	
	@Column(name = "NOTE")
	private String _note;
	
	@Id
	@Column(name= "DATA_CONTROLLO")
	private Date _data_controllo;
	
	/**
	 * Costruttore
	 */
	public EsitoImpegniExtra(){
		_cuaa = ""; 
		_domanda = ""; 
		_campagna = ""; 
		_intervento = ""; 
		_sottointervento = ""; 
		_impegno = ""; 
		_esito = ""; 
		_stato = ""; 
		_esitoRinunciaInsilato = ""; 
		_esclusione = ""; 
		_esclusioneNote = ""; 
		_reiterazione = ""; 
		_progr_accert_reiteraz = ""; 
		_inadempienza_grave = "";
		_portata = ""; 
		_gravita = ""; 
		_durata = ""; 
		_segnalazione = ""; 
		_approvazione = ""; 
		_portata_note = ""; 
		_gravita_note = ""; 
		_durata_note = ""; 
		_numero_decreto = ""; 
		_data_decreto = null; 
		_data_inserimento = null; 
		_user_inserimento = ""; 
		_data_modifica = null; 
		_user_modifica = ""; 
		_note = ""; 
		_data_controllo = null;
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
	
	public void set_sottointervento(String sottoIntervento) {
		sottoIntervento = sottoIntervento == null ? "" : sottoIntervento;
		_sottointervento = sottoIntervento;
	}

	public String get_impegno() {
		return _impegno;
	}

	public void set_impegno(String impegno) {
		impegno = impegno == null ? "" : impegno;
		this._impegno = impegno;
	}

	public String get_esito(){
		return _esito;
	}
	
	public void set_esito(String esito){
		esito = esito == null ? "" : esito;
		_esito = esito;
	}
	
	public Float get_percRid(){
		return this._percRid;
	}
	
	public void set_percRid(Float percRid){
		this._percRid = percRid;
	}
	
	public String get_percRidStr() {
		String value = "";
		if(_percRid != null) value = "" + _percRid;
		return value;
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

	public String get_segnalazione() {
		return _segnalazione;
	}

	public void set_segnalazione(String _segnalazione) {
		_segnalazione = _segnalazione == null ? "" : _segnalazione;
		this._segnalazione = _segnalazione;
	}

	public String get_esclusione() {
		return _esclusione;
	}

	public void set_esclusione(String _esclusione) {
		_esclusione = _esclusione == null ? "" : _esclusione;
		this._esclusione = _esclusione;
	}

	public String get_esclusioneNote() {
		return _esclusioneNote;
	}

	public void set_esclusioneNote(String _esclusioneNote) {
		_esclusioneNote = _esclusioneNote == null ? "" : _esclusioneNote;
		this._esclusioneNote = _esclusioneNote;
	}

	public String get_reiterazione() {
		return _reiterazione;
	}

	public void set_reiterazione(String _reiterazione) {
		_reiterazione = _reiterazione == null ? "" : _reiterazione;
		this._reiterazione = _reiterazione;
	}

	public String get_progr_accert_reiteraz() {
		return _progr_accert_reiteraz;
	}

	public void set_progr_accert_reiteraz(String _progr_accert_reiteraz) {
		_progr_accert_reiteraz = _progr_accert_reiteraz == null ? "" : _progr_accert_reiteraz;
		this._progr_accert_reiteraz = _progr_accert_reiteraz;
	}

	public String get_inadempienza_grave() {
		return _inadempienza_grave;
	}

	public void set_inadempienza_grave(String _inadempienza_grave) {
		this._inadempienza_grave = _inadempienza_grave;
	}

	public String get_portata() {
		return _portata;
	}

	public void set_portata(String _portata) {
		_portata = _portata == null ? "" : _portata;
		this._portata = _portata;
	}

	public String get_gravita() {
		return _gravita;
	}

	public void set_gravita(String _gravita) {
		_gravita = _gravita == null ? "" : _gravita;
		this._gravita = _gravita;
	}

	public String get_durata() {
		return _durata;
	}

	public void set_durata(String _durata) {
		_durata = _durata == null ? "" : _durata;
		this._durata = _durata;
	}

	public String get_approvazione() {
		return _approvazione;
	}

	public void set_approvazione(String _approvazione) {
		_approvazione = _approvazione == null ? "" : _approvazione;
		this._approvazione = _approvazione;
	}

	public String get_portata_note() {
		return _portata_note;
	}

	public void set_portata_note(String _portata_note) {
		_portata_note = _portata_note == null ? "" : _portata_note;
		this._portata_note = _portata_note;
	}

	public String get_gravita_note() {
		return _gravita_note;
	}

	public void set_gravita_note(String _gravita_note) {
		_gravita_note = _gravita_note == null ? "" : _gravita_note;
		this._gravita_note = _gravita_note;
	}

	public String get_durata_note() {
		return _durata_note;
	}

	public void set_durata_note(String _durata_note) {
		_durata_note = _durata_note == null ? "" : _durata_note;
		this._durata_note = _durata_note;
	}

	public String get_numero_decreto() {
		return _numero_decreto;
	}

	public void set_numero_decreto(String _numero_decreto) {
		_numero_decreto = _numero_decreto == null ? "" : _numero_decreto;
		this._numero_decreto = _numero_decreto;
	}

	public Date get_data_decreto() {
		return _data_decreto;
	}

	public void set_data_decreto(Date _data_decreto) {
		this._data_decreto = _data_decreto;
	}

	public Date get_data_controllo() {
		return _data_controllo;
	}

	public void set_data_controllo(Date _data_controllo) {
		this._data_controllo = _data_controllo;
	}	
}

