package it.bz.prov.esiti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Entity che rappresenta il controllo di ammissibilità zootecnia per bovini
 * 
 * @author bpettazzoni
 *
 */

@Entity
@IdClass(EsitoZootecniaControlloAmmBovPk.class)
@Table(name = "OPP_ESITI_APP_ZOOT_CTR_AMM_BOV")
public class EsitoZootecniaControlloAmmBov implements Serializable {

	private static final long serialVersionUID = 5211792059816333390L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;

	@Id
	@Column(name = "DOMANDA")
	private String _domanda;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Column(name = "CAPI_ANAGRAFE")
	private String _capiAnagrafe;

	@Column(name = "CAPI_REGISTRO")
	private String _capiRegistro;
	
	@Column(name = "CAPI_PRESENTI")
	private String _capiPresenti;

	@Column(name = "FLG_REGISTRO_ASSENTE")
	private String _flgRegistroAssente;
	
	@Column(name = "FLG_INOSSERVANZA_NORME")
	private String _flgInosservanzaNorme;

	@Column(name = "CAPI_NON_CONFORMI_TOT")
	private String _capiNonConformiTot;

	@Column(name = "CAPI_NON_CONFORMI_TIT_SPEC")
	private String _capiNonConformiTitSpec;

	@Column(name = "CAPI_NON_CONFORMI_ART68_INT162")
	private String _capiNonConformiArt68Int162;
	
	@Column(name = "CAPI_NON_CONFORMI_ART68_INT163")
	private String _capiNonConformiArt68Int163;
	
	@Column(name = "DICHIARAZIONI_PRODUTTORE")
	private String _dichiarazioniProduttore;
	
	@Column(name = "DICHIARAZIONI_CONTROLLORE")
	private String _dichiarazioniControllore;
	
	@Column(name = "FLG_ANOMALIE")
	private String _flgAnomalie;
	
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
	
	@Column(name = "CAPI_RICHIESTI_INT_310")
	private String _capi_richiesti_int_310;
	
	@Column(name = "CAPI_RICHIESTI_INT_311")
	private String _capi_richiesti_int_311;
	
	@Column(name = "CAPI_RICHIESTI_INT_313")
	private String _capi_richiesti_int_313;
	
	@Column(name = "CAPI_RICHIESTI_INT_315")
	private String _capi_richiesti_int_315;
	
	@Column(name = "CAPI_RICHIESTI_INT_316")
	private String _capi_richiesti_int_316;
	
	@Column(name = "CAPI_RICHIESTI_INT_318")
	private String _capi_richiesti_int_318;
	
	@Column(name = "CAPI_RICHIESTI_INT_322")
	private String _capi_richiesti_int_322;
	
	@Column(name = "CAPI_IRREG_INT_310")
	private String _capi_irreg_int_310;
	
	@Column(name = "CAPI_IRREG_INT_311")
	private String _capi_irreg_int_311;
	
	@Column(name = "CAPI_IRREG_INT_313")
	private String _capi_irreg_int_313;
	
	@Column(name = "CAPI_IRREG_INT_315")
	private String _capi_irreg_int_315;
	
	@Column(name = "CAPI_IRREG_INT_316")
	private String _capi_irreg_int_316;
	
	@Column(name = "CAPI_IRREG_INT_318")
	private String _capi_irreg_int_318;
	
	@Column(name = "CAPI_IRREG_INT_322")
	private String _capi_irreg_int_322;
	
	@Column(name = "CAPI_ANOM_AMM_SCARICO_BDN")
	private String _capi_anom_amm_scarico_bdn;
	/**
	 * Costruttore
	 */
	public EsitoZootecniaControlloAmmBov(){
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_capiAnagrafe = "";
		_capiRegistro = "";
		_capiPresenti = "";
		_flgRegistroAssente = "";
		_flgInosservanzaNorme = "";
		_capiNonConformiTot = "";
		_capiNonConformiTitSpec = "";
		_capiNonConformiArt68Int162 = "";
		_capiNonConformiArt68Int163 = "";
		_dichiarazioniProduttore = "";
		_dichiarazioniControllore = "";
		_flgAnomalie = "";
		_note = "";
		_user_inserimento = "";
		_user_modifica = "";
		_capi_richiesti_int_310 = "";
		_capi_richiesti_int_311 = "";
		_capi_richiesti_int_313= "";
		_capi_richiesti_int_315 = "";
		_capi_richiesti_int_316 = "";
		_capi_richiesti_int_318 = "";
		_capi_richiesti_int_322 = "";
		_capi_irreg_int_310 = "";
		_capi_irreg_int_311 = "";
		_capi_irreg_int_313 = "";
		_capi_irreg_int_315 = "";
		_capi_irreg_int_316 = "";
		_capi_irreg_int_318 = "";
		_capi_irreg_int_322 = "";
		_capi_anom_amm_scarico_bdn = "";
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

	public void set_capiAnagrafe(int _capiAnagrafe) {
		this._capiAnagrafe = "" +_capiAnagrafe;
	}

	public String get_capiAnagrafe() {
		if(_capiAnagrafe == null) return "";
		return _capiAnagrafe;
	}

	public void set_capiRegistro(int _capiRegistro) {
		this._capiRegistro = "" + _capiRegistro;
	}

	public String get_capiRegistro() {
		if(_capiRegistro == null) return "";
		return _capiRegistro;
	}

	public void set_capiPresenti(int _capiPresenti) {
		this._capiPresenti = "" +_capiPresenti;
	}

	public String get_capiPresenti() {
		if(_capiPresenti == null) return "";
		return _capiPresenti;
	}

	public void set_flgRegistroAssente(String _flgRegistroAssente) {
		_flgRegistroAssente = _flgRegistroAssente == null ? "" : _flgRegistroAssente;
		this._flgRegistroAssente = _flgRegistroAssente;
	}

	public String get_flgRegistroAssente() {
		if(_flgRegistroAssente == null) return "";
		return _flgRegistroAssente;
	}

	public void set_flgInosservanzaNorme(String _flgInosservanzaNorme) {
		_flgInosservanzaNorme = _flgInosservanzaNorme == null ? "" : _flgInosservanzaNorme;
		this._flgInosservanzaNorme = _flgInosservanzaNorme;
	}

	public String get_flgInosservanzaNorme() {
		if(_flgInosservanzaNorme == null) return "";
		return _flgInosservanzaNorme;
	}

	public void set_capiNonConformiTot(int _capiNonConformiTot) {
		this._capiNonConformiTot = "" + _capiNonConformiTot;
	}

	public String get_capiNonConformiTot() {
		if(_capiNonConformiTot == null) return "";
		return _capiNonConformiTot;
	}

	public void set_capiNonConformiTitSpec(int _capiNonConformiTitSpec) {
		this._capiNonConformiTitSpec = "" + _capiNonConformiTitSpec;
	}

	public String get_capiNonConformiTitSpec() {
		if(_capiNonConformiTitSpec == null) return "";
		return _capiNonConformiTitSpec;
	}

	public void set_capiNonConformiArt68Int162(int _capiNonConformiArt68Int162) {
		this._capiNonConformiArt68Int162 = "" + _capiNonConformiArt68Int162;
	}

	public String get_capiNonConformiArt68Int162() {
		if(_capiNonConformiArt68Int162 == null) return "";
		return _capiNonConformiArt68Int162;
	}

	public void set_capiNonConformiArt68Int163(int _capiNonConformiArt68Int163) {
		this._capiNonConformiArt68Int163 = "" + _capiNonConformiArt68Int163;
	}

	public String get_capiNonConformiArt68Int163() {
		if(_capiNonConformiArt68Int163 == null) return "";
		return _capiNonConformiArt68Int163;
	}

	public void set_dichiarazioniProduttore(String _dichiarazioniProduttore) {
		_dichiarazioniProduttore = _dichiarazioniProduttore == null ? "" : _dichiarazioniProduttore;
		this._dichiarazioniProduttore = _dichiarazioniProduttore;
	}

	public String get_dichiarazioniProduttore() {
		if(_dichiarazioniProduttore == null) return "";
		return _dichiarazioniProduttore;
	}

	public void set_dichiarazioniControllore(String _dichiarazioniControllore) {
		_dichiarazioniControllore = _dichiarazioniControllore == null ? "" : _dichiarazioniControllore;
		this._dichiarazioniControllore = _dichiarazioniControllore;
	}

	public String get_dichiarazioniControllore() {
		if(_dichiarazioniControllore == null) return "";
		return _dichiarazioniControllore;
	}

	public void set_flgAnomalie(String _flgAnomalie) {
		_flgAnomalie = _flgAnomalie == null ? "" : _flgAnomalie;
		this._flgAnomalie = _flgAnomalie;
	}

	public String get_flgAnomalie() {
		if(_flgAnomalie == null) return "";
		return _flgAnomalie;
	}

	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}

	public String get_note() {
		if(_note == null) return "";
		return _note;
	}

	public String get_capi_richiesti_int_313() {
		return _capi_richiesti_int_313;
	}

	public void set_capi_richiesti_int_313(String _capi_richiesti_int_313) {
		_capi_richiesti_int_313 = _capi_richiesti_int_313 == null ? "" : _capi_richiesti_int_313;
		this._capi_richiesti_int_313 = _capi_richiesti_int_313;
	}

	public String get_capi_irreg_int_313() {
		return _capi_irreg_int_313;
	}

	public void set_capi_irreg_int_313(String _capi_irreg_int_313) {
		_capi_irreg_int_313 = _capi_irreg_int_313 == null ? "" : _capi_irreg_int_313;
		this._capi_irreg_int_313 = _capi_irreg_int_313;
	}

	public String get_capi_richiesti_int_322() {
		return _capi_richiesti_int_322;
	}

	public void set_capi_richiesti_int_322(String _capi_richiesti_int_322) {
		_capi_richiesti_int_322 = _capi_richiesti_int_322 == null ? "" : _capi_richiesti_int_322;
		this._capi_richiesti_int_322 = _capi_richiesti_int_322;
	}

	public String get_capi_irreg_int_322() {
		return _capi_irreg_int_322;
	}

	public void set_capi_irreg_int_322(String _capi_irreg_int_322) {
		_capi_irreg_int_322 = _capi_irreg_int_322 == null ? "" : _capi_irreg_int_322;
		this._capi_irreg_int_322 = _capi_irreg_int_322;
	}

	public String get_capi_richiesti_int_310() {
		return _capi_richiesti_int_310;
	}

	public void set_capi_richiesti_int_310(String _capi_richiesti_int_310) {
		_capi_richiesti_int_310 = _capi_richiesti_int_310 == null ? "" : _capi_richiesti_int_310;
		this._capi_richiesti_int_310 = _capi_richiesti_int_310;
	}

	public String get_capi_richiesti_int_311() {
		return _capi_richiesti_int_311;
	}

	public void set_capi_richiesti_int_311(String _capi_richiesti_int_311) {
		_capi_richiesti_int_311 = _capi_richiesti_int_311 == null ? "" : _capi_richiesti_int_311;
		this._capi_richiesti_int_311 = _capi_richiesti_int_311;
	}

	public String get_capi_richiesti_int_315() {
		return _capi_richiesti_int_315;
	}

	public void set_capi_richiesti_int_315(String _capi_richiesti_int_315) {
		_capi_richiesti_int_315 = _capi_richiesti_int_315 == null ? "" : _capi_richiesti_int_315;
		this._capi_richiesti_int_315 = _capi_richiesti_int_315;
	}

	public String get_capi_richiesti_int_316() {
		return _capi_richiesti_int_316;
	}

	public void set_capi_richiesti_int_316(String _capi_richiesti_int_316) {
		_capi_richiesti_int_316 = _capi_richiesti_int_316 == null ? "" : _capi_richiesti_int_316;
		this._capi_richiesti_int_316 = _capi_richiesti_int_316;
	}

	public String get_capi_richiesti_int_318() {
		return _capi_richiesti_int_318;
	}

	public void set_capi_richiesti_int_318(String _capi_richiesti_int_318) {
		_capi_richiesti_int_318 = _capi_richiesti_int_318 == null ? "" : _capi_richiesti_int_318;
		this._capi_richiesti_int_318 = _capi_richiesti_int_318;
	}

	public String get_capi_irreg_int_310() {
		return _capi_irreg_int_310;
	}

	public void set_capi_irreg_int_310(String _capi_irreg_int_310) {
		_capi_irreg_int_310 = _capi_irreg_int_310 == null ? "" : _capi_irreg_int_310;
		this._capi_irreg_int_310 = _capi_irreg_int_310;
	}

	public String get_capi_irreg_int_311() {
		return _capi_irreg_int_311;
	}

	public void set_capi_irreg_int_311(String _capi_irreg_int_311) {
		_capi_irreg_int_311 = _capi_irreg_int_311 == null ? "" : _capi_irreg_int_311;
		this._capi_irreg_int_311 = _capi_irreg_int_311;
	}

	public String get_capi_irreg_int_315() {
		return _capi_irreg_int_315;
	}

	public void set_capi_irreg_int_315(String _capi_irreg_int_315) {
		_capi_irreg_int_315 = _capi_irreg_int_315 == null ? "" : _capi_irreg_int_315;
		this._capi_irreg_int_315 = _capi_irreg_int_315;
	}

	public String get_capi_irreg_int_316() {
		return _capi_irreg_int_316;
	}

	public void set_capi_irreg_int_316(String _capi_irreg_int_316) {
		_capi_irreg_int_316 = _capi_irreg_int_316 == null ? "" : _capi_irreg_int_316;
		this._capi_irreg_int_316 = _capi_irreg_int_316;
	}

	public String get_capi_irreg_int_318() {
		return _capi_irreg_int_318;
	}

	public void set_capi_irreg_int_318(String _capi_irreg_int_318) {
		_capi_irreg_int_318 = _capi_irreg_int_318 == null ? "" : _capi_irreg_int_318;
		this._capi_irreg_int_318 = _capi_irreg_int_318;
	}

	public String get_capi_anom_amm_scarico_bdn() {
		return _capi_anom_amm_scarico_bdn;
	}

	public void set_capi_anom_amm_scarico_bdn(String _capi_anom_amm_scarico_bdn) {
		_capi_anom_amm_scarico_bdn = _capi_anom_amm_scarico_bdn == null ? "" : _capi_anom_amm_scarico_bdn;
		this._capi_anom_amm_scarico_bdn = _capi_anom_amm_scarico_bdn;
	}

}
