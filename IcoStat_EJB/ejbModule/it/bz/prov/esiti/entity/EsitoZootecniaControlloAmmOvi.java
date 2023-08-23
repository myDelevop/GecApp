package it.bz.prov.esiti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Entity che rappresenta il controllo di ammissibilità zootecnia per ovicaprini
 * 
 * @author bpettazzoni
 *
 */

@Entity
@IdClass(EsitoZootecniaControlloAmmOviPk.class)
@Table(name = "OPP_ESITI_APP_ZOOT_CTR_AMM_OVI")
public class EsitoZootecniaControlloAmmOvi implements Serializable {

	private static final long serialVersionUID = -2181054116448619558L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;

	@Id
	@Column(name = "DOMANDA")
	private String _domanda;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Column(name = "CAPI_ANAGRAFE_TOT")
	private String _capiAnagrafeTot;
	
	@Column(name = "CAPI_ANAGRAFE_M")
	private String _capiAnagrafeM;
	
	@Column(name = "CAPI_ANAGRAFE_F")
	private String _capiAnagrafeF;
	
	@Column(name = "CAPI_REGISTRO_TOT")
	private String _capiRegistroTot;
	
	@Column(name = "CAPI_REGISTRO_M")
	private String _capiRegistroM;

	@Column(name = "CAPI_REGISTRO_F")
	private String _capiRegistroF;
	
	@Column(name = "CAPI_AZIENDA_TOT")
	private String _capiAziendaTot;
	
	@Column(name = "CAPI_AZIENDA_M")
	private String _capiAziendaM;
	
	@Column(name = "CAPI_AZIENDA_F")
	private String _capiAziendaF;
	
	@Column(name = "FLG_INOSSERVANZA_NORME")
	private String _flgInosservanzaNorme;
	
	@Column(name = "CAPI_NON_CONFORMI_TOT")
	private String _capiNonConformiTot;
	
	@Column(name = "CAPI_NON_CONFORMI_M")
	private String _capiNonConformiM;

	@Column(name = "CAPI_NON_CONFORMI_PECORE_CAPRE")
	private String _capiNonConformiPecoreCapre;
	
	@Column(name = "FLG_ANOMALIE")
	private String _flgAnomalie;

	@Column(name = "FLG_REG_AZI_ASS_NON_COMP")
	private String _flgRegAziAssNonComp;
	
	@Column(name = "FLG_REG_AZI_NON_COMP_SIT_SOD")
	private String _flgRegAziNonCompSitSod; 
	
	@Column(name = "FLG_REG_AZI_COMP")
	private String _flgRegAziComp;
	
	@Column(name = "DICHIARAZIONI_PRODUTTORE")
	private String _dichiarazioniProduttore;
	
	@Column(name = "DICHIARAZIONI_CONTROLLORE")
	private String _dichiarazioniControllore;
	
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
	
	@Column(name = "CAPI_ANOM_AMM_SCARICO_BDN")
	private String _capi_anom_amm_scarico_bdn;
	
	/**
	 * Costruttore
	 */
	public EsitoZootecniaControlloAmmOvi(){
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_capiAnagrafeTot = "";
		_capiAnagrafeM = "";
		_capiAnagrafeF = "";
		_capiRegistroTot = "";
		_capiRegistroM = "";
		_capiRegistroF = "";
		_capiAziendaTot = "";
		_capiAziendaM = "";
		_capiAziendaF = "";
		_flgInosservanzaNorme = "";
		_capiNonConformiTot = "";
		_capiNonConformiM = "";
		_capiNonConformiPecoreCapre = "";
		_flgAnomalie = "";
		_flgRegAziAssNonComp = "";
		_flgRegAziNonCompSitSod = ""; 
		_flgRegAziComp = "";
		_dichiarazioniProduttore = "";
		_dichiarazioniControllore = "";
		_note = "";
		_user_inserimento = "";
		_user_modifica = "";
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

	public void set_capiAnagrafeTot(int _capiAnagrafeTot) {
		this._capiAnagrafeTot = "" + _capiAnagrafeTot;
	}

	public String get_capiAnagrafeTot() {
		if(_capiAnagrafeTot == null) return "";
		return _capiAnagrafeTot;
	}

	public void set_capiAnagrafeM(int _capiAnagrafeM) {
		this._capiAnagrafeM = "" + _capiAnagrafeM;
	}

	public String get_capiAnagrafeM() {
		if(_capiAnagrafeM == null) return "";
		return _capiAnagrafeM;
	}

	public void set_capiAnagrafeF(int  _capiAnagrafeF) {
		this._capiAnagrafeF = "" + _capiAnagrafeF;
	}

	public String get_capiAnagrafeF() {
		if(_capiAnagrafeF == null) return "";
		return _capiAnagrafeF;
	}

	public void set_capiRegistroTot(int _capiRegistroTot) {
		this._capiRegistroTot = "" + _capiRegistroTot;
	}

	public String get_capiRegistroTot() {
		if(_capiRegistroTot == null) return "";
		return _capiRegistroTot;
	}

	public void set_capiRegistroM(int _capiRegistroM) {
		this._capiRegistroM = "" + _capiRegistroM;
	}

	public String get_capiRegistroM() {
		if(_capiRegistroM == null) return "";
		return _capiRegistroM;
	}

	public void set_capiRegistroF(int _capiRegistroF) {
		this._capiRegistroF = "" + _capiRegistroF;
	}

	public String get_capiRegistroF() {
		if(_capiRegistroF == null) return "";
		return _capiRegistroF;
	}

	public void set_capiAziendaTot(int _capiAziendaTot) {
		this._capiAziendaTot = "" + _capiAziendaTot;
	}

	public String get_capiAziendaTot() {
		if(_capiAziendaTot == null) return "";
		return _capiAziendaTot;
	}

	public void set_capiAziendaM(int _capiAziendaM) {
		this._capiAziendaM = "" + _capiAziendaM;
	}

	public String get_capiAziendaM() {
		if(_capiAziendaM == null) return "";
		return _capiAziendaM;
	}

	public void set_capiAziendaF(int _capiAziendaF) {
		this._capiAziendaF = "" + _capiAziendaF;
	}

	public String get_capiAziendaF() {
		if(_capiAziendaF == null) return "";
		return _capiAziendaF;
	}

	public void set_flgInosservanzaNorme(String _flgInosservanzaNorme) {
		_flgInosservanzaNorme = _flgInosservanzaNorme == null ? "" : _flgInosservanzaNorme;
		this._flgInosservanzaNorme = _flgInosservanzaNorme;
	}

	public String get_flgInosservanzaNorme() {
		if(_flgInosservanzaNorme == null) return "";
		return _flgInosservanzaNorme;
	}

	public void set_capiNonConformiTot(int  _capiNonConformiTot) {
		this._capiNonConformiTot = "" + _capiNonConformiTot;
	}

	public String get_capiNonConformiTot() {
		if(_capiNonConformiTot == null) return "";
		return _capiNonConformiTot;
	}

	public void set_capiNonConformiM(int _capiNonConformiM) {
		this._capiNonConformiM = "" + _capiNonConformiM;
	}

	public String get_capiNonConformiM() {
		if(_capiNonConformiM == null) return "";
		return _capiNonConformiM;
	}

	public void set_capiNonConformiPecoreCapre(int _capiNonConformiPecoreCapre) {
		this._capiNonConformiPecoreCapre = "" + _capiNonConformiPecoreCapre;
	}

	public String get_capiNonConformiPecoreCapre() {
		if(_capiNonConformiPecoreCapre == null) return "";
		return _capiNonConformiPecoreCapre;
	}

	public void set_flgAnomalie(String _flgAnomalie) {
		_flgAnomalie = _flgAnomalie == null ? "" : _flgAnomalie;
		this._flgAnomalie = _flgAnomalie;
	}

	public String get_flgAnomalie() {
		if(_flgAnomalie == null) return "";
		return _flgAnomalie;
	}

	public void set_flgRegAziAssNonComp(String _flgRegAziAssNonComp) {
		_flgRegAziAssNonComp = _flgRegAziAssNonComp == null ? "" : _flgRegAziAssNonComp;
		this._flgRegAziAssNonComp = _flgRegAziAssNonComp;
	}

	public String get_flgRegAziAssNonComp() {
		if(_flgRegAziAssNonComp == null) return "";
		return _flgRegAziAssNonComp;
	}

	public void set_flgRegAziNonCompSitSod(String _flgRegAziNonCompSitSod) {
		_flgRegAziNonCompSitSod = _flgRegAziNonCompSitSod == null ? "" : _flgRegAziNonCompSitSod;
		this._flgRegAziNonCompSitSod = _flgRegAziNonCompSitSod;
	}

	public String get_flgRegAziNonCompSitSod() {
		if(_flgRegAziNonCompSitSod == null) return "";
		return _flgRegAziNonCompSitSod;
	}

	public void set_flgRegAziComp(String _flgRegAziComp) {
		_flgRegAziComp = _flgRegAziComp == null ? "" : _flgRegAziComp;
		this._flgRegAziComp = _flgRegAziComp;
	}

	public String get_flgRegAziComp() {
		if(_flgRegAziComp == null) return "";
		return _flgRegAziComp;
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

	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}

	public String get_note() {
		if(_note == null) return "";
		return _note;
	}

	public String get_capi_anom_amm_scarico_bdn() {
		return _capi_anom_amm_scarico_bdn;
	}

	public void set_capi_anom_amm_scarico_bdn(String _capi_anom_amm_scarico_bdn) {
		_capi_anom_amm_scarico_bdn = _capi_anom_amm_scarico_bdn == null ? "" : _capi_anom_amm_scarico_bdn;
		this._capi_anom_amm_scarico_bdn = _capi_anom_amm_scarico_bdn;
	}
	
}
