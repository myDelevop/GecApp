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
 * Entity che rappresenta dell'esito impegni RM FIT e FER
 * 
 * @author lattana
 *
 */

@Entity
@IdClass(EsitoImpegniRMFITFERExtraPk.class)
@Table(name = "OPP_ESITI_APP_ESITO_FITFER_EXT")
@NamedQueries({
		@NamedQuery(
				name = "EsitoImpegniRMFITFERExtra.selectAll",
				query = "SELECT ei FROM EsitoImpegniRMFITFERExtra ei " +
						"ORDER BY ei._cuaa, ei._campagna, ei._domanda, ei._misura"),
		@NamedQuery(
						name = "EsitoImpegniRMFITFERExtra.selectFilter",
						query = "SELECT DISTINCT e FROM EsitoImpegniRMFITFERExtra e, Azienda a, CampioneAggr c " +
						"WHERE e._cuaa=a._cuaa AND e._domanda=c._domandaOpr AND e._campagna=c._campagna " + 
						"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
						"AND lower(e._cuaa) LIKE lower(:cuaa) " +
						"AND e._campagna LIKE :campagna " +
						"AND c._misura LIKE :misura " +
						"AND c._domandaOpr LIKE :domanda " +
						"AND (c._flagCampione is null OR c._flagCampione LIKE :campione) " +
						"AND (c._flagExtraCampione is null OR c._flagExtraCampione LIKE :extraCampione) " +
						"ORDER BY e._cuaa, e._campagna, e._domanda, e._misura"),
})
public class EsitoImpegniRMFITFERExtra implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9109363152506026955L;

	@Column(name = "CUAA")
	private String _cuaa;
	
	@Id
	@Column(name = "DOMANDA_OPR")
	private String _domanda;
	
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Id
	@Column(name = "MISURA")
	private String _misura;
	
	@Column(name = "ESITO_OPPAB")
	private String _esito_oppab;
	
	@Column(name = "STATO")
	private String _stato;
		
	@Column(name = "PERC_RID_FIT")
	private String _percRidFIT;
	
	@Column(name = "PERC_RID_FER")
	private String _percRidFER;

	@Column(name = "PERC_TOTALE_OPPAB")
	private String _percTotOPPAB;

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

	@Column(name = "DATA_CONTR")
	private Date _data_contr;
	
	@Column(name = "NEGLIGENZA")
	private String _negligenza;
	
	@Column(name = "ESCLUSIONE")
	private String _esclusione;
	
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
	
	@Column(name = "ESCLUSIONE_NOTE")
	private String _esclusione_note;
	
	@Column(name = "NUMERO_DECRETO")
	private String _numero_decreto;
	
	@Column(name = "DATA_DECRETO")
	private Date _data_decreto;
	
	/**
	 * Costruttore
	 */
	public EsitoImpegniRMFITFERExtra(){
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_misura = "";
		_esito_oppab = "";
		_stato = "";
		_percRidFIT = "";
		_percRidFER = "";
		_percTotOPPAB = "";
		_note = "";
		_user_inserimento = "";
		_user_modifica = "";
		_negligenza = "";
		_esclusione = "";
		_reiterazione = "";
		_segnalazione = "";
		_approvazione= "";
		_progr_accert_reiteraz = "";
		_inadempienza_grave = "";
		_portata = "";
		_gravita = "";
		_durata = "";
		_portata_note = "";
		_gravita_note = "";
		_durata_note = "";
		_numero_decreto = "";
		_data_decreto = null;
		_esclusione_note = "";
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
	
	public String get_misura() {
		return _misura;
	}

	public void set_misura(String _misura) {
		_misura = _misura == null ? "" : _misura;
		this._misura = _misura;
	}

	public String get_esito_oppab() {
		return _esito_oppab;
	}

	public void set_esito_oppab(String _esito_oppab) {
		_esito_oppab = _esito_oppab == null ? "" : _esito_oppab;
		this._esito_oppab = _esito_oppab;
	}
	
	public void set_stato(String _stato) {
		_stato = _stato == null ? "" : _stato;
		this._stato = _stato;
	}

	public String get_stato() {
		return _stato;
	}

	public String get_percRidFIT() {
		return _percRidFIT;
	}

	public void set_percRidFIT(String _percRidFIT) {
		_percRidFIT = _percRidFIT == null ? "" : _percRidFIT;
		this._percRidFIT = _percRidFIT;
	}

	public String get_percRidFER() {
		return _percRidFER;
	}

	public void set_percRidFER(String _percRidFER) {
		_percRidFER = _percRidFER == null ? "" : _percRidFER;
		this._percRidFER = _percRidFER;
	}

	public String get_percTotOPPAB() {
		return _percTotOPPAB;
	}

	public void set_percTotOPPAB(String _percTotOPPAB) {
		_percTotOPPAB = _percTotOPPAB == null ? "" : _percTotOPPAB;
		this._percTotOPPAB = _percTotOPPAB;
	}
	
	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}

	public String get_note() {
		return _note;
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

	public Date get_data_contr() {
		return _data_contr;
	}

	public void set_data_contr(Date _data_contr) {
		this._data_contr = _data_contr;
	}

	public String get_negligenza() {
		return _negligenza;
	}

	public void set_negligenza(String _negligenza) {
		_negligenza = _negligenza == null ? "" : _negligenza;
		this._negligenza = _negligenza;
	}

	public String get_esclusione() {
		return _esclusione;
	}

	public void set_esclusione(String _esclusione) {
		_esclusione = _esclusione == null ? "" : _esclusione;
		this._esclusione = _esclusione;
	}


	public String get_reiterazione() {
		return _reiterazione;
	}

	public void set_reiterazione(String _reiterazione) {
		_reiterazione = _reiterazione == null ? "" : _reiterazione;
		this._reiterazione = _reiterazione;
	}

	public String get_progr_acc_reiteraz() {
		return _progr_accert_reiteraz;
	}

	public void set_progr_acc_reiteraz(String _progr_acc_reiteraz) {
		_progr_acc_reiteraz = _progr_acc_reiteraz == null || _progr_acc_reiteraz.equalsIgnoreCase("on") 
				? "" : _progr_acc_reiteraz;
		this._progr_accert_reiteraz = _progr_acc_reiteraz;
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
		_portata = _portata == null || _portata.equalsIgnoreCase("on") ? "" : _portata;
		this._portata = _portata;
	}

	public String get_gravita() {
		return _gravita;
	}

	public void set_gravita(String _gravita) {
		_gravita = _gravita == null || _gravita.equalsIgnoreCase("on") ? "" : _gravita;
		this._gravita = _gravita;
	}

	public String get_durata() {
		return _durata;
	}

	public void set_durata(String _durata) {
		_durata = _durata == null || _durata.equalsIgnoreCase("on") ? "" : _durata;
		this._durata = _durata;
	}

	public String get_segnalazione() {
		return _segnalazione;
	}

	public void set_segnalazione(String _segnalazione) {
		_segnalazione = _segnalazione == null ? "" : _segnalazione;
		this._segnalazione = _segnalazione;
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

	public String get_esclusione_note() {
		return _esclusione_note;
	}

	public void set_esclusione_note(String _esclusione_note) {
		_esclusione_note = _esclusione_note == null ? "" : _esclusione_note;
		this._esclusione_note = _esclusione_note;
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
}
