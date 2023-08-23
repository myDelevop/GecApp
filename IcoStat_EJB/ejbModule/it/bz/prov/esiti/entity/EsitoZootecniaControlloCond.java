package it.bz.prov.esiti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Entity che rappresenta il controllo di condizionalità zootecnia
 * 
 * @author bpettazzoni
 *
 */

@Entity
@IdClass(EsitoZootecniaControlloCondPk.class)
@Table(name = "OPP_ESITI_APP_ZOOT_CTR_COND")
public class EsitoZootecniaControlloCond implements Serializable {

	private static final long serialVersionUID = -8112229339025659617L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;

	@Id
	@Column(name = "DOMANDA")
	private String _domanda;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Column(name = "FLG_AZI_NON_REG_BDN")
	private String _flgAziNonRegBDN;
	
	@Column(name = "FLG_REG_AZI_ASS")
	private String _flgRegAziAss;

	@Column(name = "FLG_REG_AZI_NON_CONFORME")
	private String _flgRegAziNonConforme;

	@Column(name = "FLG_OVI_SUI_NO_MARC_DOC")
	private String _flgOviSuiNoMarcDoc;
	
	@Column(name = "FLG_BOV_BUF_OVI_NO_REG")
	private String _flgBovBufOviNoReg;
	
	@Column(name = "FLG_BOV_BUF_OVI_IDENT_NO_CONF")
	private String _flgBovBufOviIdentNoConf;	

	@Column(name = "FLG_OVI_SUI_MARC_NO_CONF")
	private String _flgOviSuiMarcNoConf;
	
	@Column(name = "FLG_BOV_BUF_NO_PASS_MARCHI_DOC")
	private String _flgBovBufNoPassMarchiDoc;
	
	@Column(name = "FLG_ANOMALIE")
	private String _flgAnomalie;
		
	@Column(name = "CAPI_ANOMALI_BOVINI")
	private String _capiAnomaliBovini;
	
	@Column(name = "CAPI_ANOMALI_SUINI")
	private String _capiAnomaliSuini;
	
	@Column(name = "CAPI_ANOMALI_OVINI")
	private String _capiAnomaliOvini;
	
	@Column(name = "CAPI_CONSISTENZA_BOVINI")
	private String _capiConsistenzaBovini;

	@Column(name = "CAPI_CONSISTENZA_SUINI")
	private String _capiConsistenzaSuini;

	@Column(name = "CAPI_CONSISTENZA_OVINI")
	private String _capiConsistenzaOvini;
	
	@Column(name = "CAPI_CONTROLLATI_BOVINI")
	private String _capiControllatiBovini;

	@Column(name = "CAPI_CONTROLLATI_SUINI")
	private String _capiControllatiSuini;

	@Column(name = "CAPI_CONTROLLATI_OVINI")
	private String _capiControllatiOvini;
	
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
	
	/**
	 * Costruttore
	 */
	public EsitoZootecniaControlloCond(){
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_flgAziNonRegBDN = "";
		_flgRegAziAss = "";
		_flgRegAziNonConforme = "";
		_flgOviSuiNoMarcDoc = "";
		_flgBovBufOviNoReg = "";
		_flgBovBufOviIdentNoConf = "";
		_flgOviSuiMarcNoConf = "";
		_flgBovBufNoPassMarchiDoc = "";
		_flgAnomalie = "";
		_capiAnomaliBovini = "";
		_capiAnomaliSuini = "";
		_capiAnomaliOvini = "";
		_capiConsistenzaBovini = "";
		_capiConsistenzaSuini = "";
		_capiConsistenzaOvini = "";
		_capiControllatiBovini = "";
		_capiControllatiSuini = "";
		_capiControllatiOvini = "";
		_note = "";
		_user_inserimento = "";
		_user_modifica = "";
	}
	
	public String get_cuaa(){
		return _cuaa;
	}
	
	public void set_cuaa(String cuaa){
		cuaa = cuaa == null ? "" : cuaa;
		_cuaa= cuaa;
	}
	
	public String get_domanda(){
		if( _domanda == null) return "";
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
		if( _user_inserimento == null) return "";
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

	public void set_flgAziNonRegBDN(String _flgAziNonRegBDN) {
		_flgAziNonRegBDN = _flgAziNonRegBDN == null ? "" : _flgAziNonRegBDN;
		this._flgAziNonRegBDN = _flgAziNonRegBDN;
	}

	public String get_flgAziNonRegBDN() {
		if(_flgAziNonRegBDN == null) return "";
		return _flgAziNonRegBDN;
	}

	public void set_flgRegAziAss(String _flgRegAziAss) {
		_flgRegAziAss = _flgRegAziAss == null ? "" : _flgRegAziAss;
		this._flgRegAziAss = _flgRegAziAss;
	}

	public String get_flgRegAziAss() {
		if(_flgRegAziAss == null) return "";
		return _flgRegAziAss;
	}

	public void set_flgRegAziNonConforme(String _flgRegAziNonConforme) {
		_flgRegAziNonConforme = _flgRegAziNonConforme == null ? "" : _flgRegAziNonConforme;
		this._flgRegAziNonConforme = _flgRegAziNonConforme;
	}

	public String get_flgRegAziNonConforme() {
		if(_flgRegAziNonConforme == null) return "";
		return _flgRegAziNonConforme;
	}

	public void set_flgOviSuiNoMarcDoc(String _flgOviSuiNoMarcDoc) {
		_flgOviSuiNoMarcDoc = _flgOviSuiNoMarcDoc == null ? "" : _flgOviSuiNoMarcDoc;
		this._flgOviSuiNoMarcDoc = _flgOviSuiNoMarcDoc;
	}

	public String get_flgOviSuiNoMarcDoc() {
		if(_flgOviSuiNoMarcDoc == null) return "";
		return _flgOviSuiNoMarcDoc;
	}

	public void set_flgBovBufOviNoReg(String _flgBovBufOviNoReg) {
		_flgBovBufOviNoReg = _flgBovBufOviNoReg == null ? "" : _flgBovBufOviNoReg;
		this._flgBovBufOviNoReg = _flgBovBufOviNoReg;
	}

	public String get_flgBovBufOviNoReg() {
		if(_flgBovBufOviNoReg == null) return "";
		return _flgBovBufOviNoReg;
	}

	public void set_flgBovBufOviIdentNoConf(String _flgBovBufOviIdentNoConf) {
		_flgBovBufOviIdentNoConf = _flgBovBufOviIdentNoConf == null ? "" : _flgBovBufOviIdentNoConf;
		this._flgBovBufOviIdentNoConf = _flgBovBufOviIdentNoConf;
	}

	public String get_flgBovBufOviIdentNoConf() {
		if(_flgBovBufOviIdentNoConf == null) return "";
		return _flgBovBufOviIdentNoConf;
	}

	public void set_flgBovBufNoPassMarchiDoc(String _flgBovBufNoPassMarchiDoc) {
		_flgBovBufNoPassMarchiDoc = _flgBovBufNoPassMarchiDoc == null ? "" : _flgBovBufNoPassMarchiDoc;
		this._flgBovBufNoPassMarchiDoc = _flgBovBufNoPassMarchiDoc;
	}

	public String get_flgBovBufNoPassMarchiDoc() {
		if(_flgBovBufNoPassMarchiDoc == null) return "";
		return _flgBovBufNoPassMarchiDoc;
	}

	public void set_flgAnomalie(String _flgAnomalie) {
		_flgAnomalie = _flgAnomalie == null ? "" : _flgAnomalie;
		this._flgAnomalie = _flgAnomalie;
	}

	public String get_flgAnomalie() {
		if(_flgAnomalie == null) return "";
		return _flgAnomalie;
	}

	public void set_capiAnomaliBovini(int _capiAnomaliBovini) {
		this._capiAnomaliBovini = "" + _capiAnomaliBovini;
	}

	public String get_capiAnomaliBovini() {
		if(_capiAnomaliBovini == null) return "";
		return _capiAnomaliBovini;
	}

	public void set_capiAnomaliSuini(int _capiAnomaliSuini) {
		this._capiAnomaliSuini = "" + _capiAnomaliSuini;
	}

	public String get_capiAnomaliSuini() {
		if(_capiAnomaliSuini == null) return "";
		return _capiAnomaliSuini;
	}

	public void set_capiAnomaliOvini(int _capiAnomaliOvini) {
		this._capiAnomaliOvini = "" + _capiAnomaliOvini;
	}

	public String get_capiAnomaliOvini() {
		if(_capiAnomaliOvini == null) return "";
		return _capiAnomaliOvini;
	}

	public void set_capiConsistenzaBovini(int _capiConsistenzaBovini) {
		this._capiConsistenzaBovini = "" + _capiConsistenzaBovini;
	}

	public String get_capiConsistenzaBovini() {
		if(_capiConsistenzaBovini == null) return "";
		return _capiConsistenzaBovini;
	}

	public void set_capiConsistenzaSuini(int _capiConsistenzaSuini) {
		this._capiConsistenzaSuini = "" + _capiConsistenzaSuini;
	}

	public String get_capiConsistenzaSuini() {
		if(_capiConsistenzaSuini == null) return "";
		return _capiConsistenzaSuini;
	}

	public void set_capiConsistenzaOvini(int _capiConsistenzaOvini) {
		this._capiConsistenzaOvini = "" + _capiConsistenzaOvini;
	}

	public String get_capiConsistenzaOvini() {
		if(_capiConsistenzaOvini == null) return "";
		return _capiConsistenzaOvini;
	}

	public void set_capiControllatiBovini(int _capiControllatiBovini) {
		this._capiControllatiBovini = "" + _capiControllatiBovini;
	}

	public String get_capiControllatiBovini() {
		if(_capiControllatiBovini == null) return "";
		return _capiControllatiBovini;
	}

	public void set_capiControllatiSuini(int _capiControllatiSuini) {
		this._capiControllatiSuini = "" + _capiControllatiSuini;
	}

	public String get_capiControllatiSuini() {
		if(_capiControllatiSuini == null) return "";
		return _capiControllatiSuini;
	}

	public void set_capiControllatiOvini(int _capiControllatiOvini) {
		this._capiControllatiOvini = "" + _capiControllatiOvini;
	}

	public String get_capiControllatiOvini() {
		if(_capiControllatiOvini == null) return "";
		return _capiControllatiOvini;
	}

	public void set_flgOviSuiMarcNoConf(String _flgOviSuiMarcNoConf) {
		_flgOviSuiMarcNoConf = _flgOviSuiMarcNoConf == null ? "" : _flgOviSuiMarcNoConf;
		this._flgOviSuiMarcNoConf = _flgOviSuiMarcNoConf;
	}

	public String get_flgOviSuiMarcNoConf() {
		if(_flgOviSuiMarcNoConf == null) return "";
		return _flgOviSuiMarcNoConf;
	}

	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}

	public String get_note() {
		if(_note == null) return "";
		return _note;
	}

	
}
