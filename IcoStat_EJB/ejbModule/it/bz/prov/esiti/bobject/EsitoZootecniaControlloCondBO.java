package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.EsitoZootecniaControlloCond;
import it.bz.prov.esiti.util.Utils;
import java.util.Date;

/**
 * Business entity che rappresenta i dati del controllo di condizionalità zootecnia
 * 
 * @author bpettazzoni
 *
 */

public class EsitoZootecniaControlloCondBO {

	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _flgAziNonRegBDN;
	private String _flgRegAziAss;
	private String _flgRegAziNonConforme;
	private String _flgOviSuiNoMarcDoc;
	private String _flgBovBufOviNoReg;
	private String _flgBovBufOviIdentNoConf;
	private String _flgOviSuiMarcNoConf;
	private String _flgBovBufNoPassMarchiDoc;
	private String _flgAnomalie;
	private String _capiAnomaliBovini;
	private String _capiAnomaliSuini;
	private String _capiAnomaliOvini;
	private String _capiConsistenzaBovini;
	private String _capiConsistenzaSuini;
	private String _capiConsistenzaOvini;
	private String _capiControllatiBovini;
	private String _capiControllatiSuini;
	private String _capiControllatiOvini;
	private String _note;
	private Date _dataInserimento;
	private String _userInserimento;
	private Date _dataModifica;
	private String _userModifica;
	
	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoZootecniaControlloCondBO(){
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
		_userInserimento = "";
		_userModifica = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esito
	 */
	public EsitoZootecniaControlloCondBO(EsitoZootecniaControlloCond esito){
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_campagna = esito.get_campagna();		
		_flgAziNonRegBDN = esito.get_flgAziNonRegBDN();
		_flgRegAziAss = esito.get_flgRegAziAss();
		_flgRegAziNonConforme = esito.get_flgRegAziNonConforme();
		_flgOviSuiNoMarcDoc = esito.get_flgOviSuiNoMarcDoc();
		_flgBovBufOviNoReg = esito.get_flgBovBufOviNoReg();
		_flgBovBufOviIdentNoConf = esito.get_flgBovBufOviIdentNoConf();
		_flgOviSuiMarcNoConf = esito.get_flgOviSuiMarcNoConf();
		_flgBovBufNoPassMarchiDoc = esito.get_flgBovBufNoPassMarchiDoc();
		_flgAnomalie = esito.get_flgAnomalie();
		_capiAnomaliBovini = esito.get_capiAnomaliBovini();
		_capiAnomaliSuini = esito.get_capiAnomaliSuini();
		_capiAnomaliOvini = esito.get_capiAnomaliOvini();
		_capiConsistenzaBovini = esito.get_capiConsistenzaBovini();
		_capiConsistenzaSuini = esito.get_capiConsistenzaSuini();
		_capiConsistenzaOvini = esito.get_capiConsistenzaOvini();
		_capiControllatiBovini = esito.get_capiControllatiBovini();
		_capiControllatiSuini = esito.get_capiControllatiSuini();
		_capiControllatiOvini = esito.get_capiControllatiOvini();
		_note = esito.get_note();
		_dataModifica = esito.get_data_modifica();
		_userModifica = esito.get_user_modifica();
		_dataInserimento = esito.get_data_inserimento();
		_userInserimento = esito.get_user_inserimento();
	}
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoZootecniaControlloCondBO
	 */
	public EsitoZootecniaControlloCondBO clone()
	{
		EsitoZootecniaControlloCondBO esito = new EsitoZootecniaControlloCondBO();
		esito.set_campagna(_campagna);
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);		
		esito.set_flgAziNonRegBDN(_flgAziNonRegBDN);
		esito.set_flgRegAziAss(_flgRegAziAss);
		esito.set_flgRegAziNonConforme(_flgRegAziNonConforme);
		esito.set_flgOviSuiNoMarcDoc(_flgOviSuiNoMarcDoc);
		esito.set_flgBovBufOviNoReg(_flgBovBufOviNoReg);
		esito.set_flgBovBufOviIdentNoConf(_flgBovBufOviIdentNoConf);
		esito.set_flgOviSuiMarcNoConf(_flgOviSuiMarcNoConf);
		esito.set_flgBovBufNoPassMarchiDoc(_flgBovBufNoPassMarchiDoc);
		esito.set_flgAnomalie(_flgAnomalie);
		esito.set_capiAnomaliBovini(_capiAnomaliBovini);
		esito.set_capiAnomaliSuini(_capiAnomaliSuini);
		esito.set_capiAnomaliOvini(_capiAnomaliOvini);
		esito.set_capiConsistenzaBovini(_capiConsistenzaBovini);
		esito.set_capiConsistenzaSuini(_capiConsistenzaSuini);
		esito.set_capiConsistenzaOvini(_capiConsistenzaOvini);
		esito.set_capiControllatiBovini(_capiControllatiBovini);
		esito.set_capiControllatiSuini(_capiControllatiSuini);
		esito.set_capiControllatiOvini(_capiControllatiOvini);	
		esito.set_note(_note);
		esito.set_userInserimento(_userInserimento);
		esito.set_dataInserimento(_dataInserimento);
		esito.set_userModifica(_userModifica);
		esito.set_dataModifica(_dataModifica);
		return esito;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esito
	 */
	public void recovery(EsitoZootecniaControlloCondBO esito)
	{
		_campagna = "" + esito.get_campagna();
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_flgAziNonRegBDN = esito.get_flgAziNonRegBDN();
		_flgRegAziAss = esito.get_flgRegAziAss();
		_flgRegAziNonConforme = esito.get_flgRegAziNonConforme();
		_flgOviSuiNoMarcDoc = esito.get_flgOviSuiNoMarcDoc();
		_flgBovBufOviNoReg = esito.get_flgBovBufOviNoReg();
		_flgBovBufOviIdentNoConf = esito.get_flgBovBufOviIdentNoConf();
		_flgOviSuiMarcNoConf = esito.get_flgOviSuiMarcNoConf();
		_flgBovBufNoPassMarchiDoc = esito.get_flgBovBufNoPassMarchiDoc();
		_flgAnomalie = esito.get_flgAnomalie();
		_capiAnomaliBovini = esito.get_capiAnomaliBovini();
		_capiAnomaliSuini = esito.get_capiAnomaliSuini();
		_capiAnomaliOvini = esito.get_capiAnomaliOvini();
		_capiConsistenzaBovini = esito.get_capiConsistenzaBovini();
		_capiConsistenzaSuini = esito.get_capiConsistenzaSuini();
		_capiConsistenzaOvini = esito.get_capiConsistenzaOvini();
		_capiControllatiBovini = esito.get_capiControllatiBovini();
		_capiControllatiSuini = esito.get_capiControllatiSuini();
		_capiControllatiOvini = esito.get_capiControllatiOvini();
		_note = esito.get_note();
		_userInserimento = esito.get_userInserimento();
		_dataInserimento = esito.get_dataInserimento();
		_userModifica = esito.get_userModifica();
		_dataModifica = esito.get_dataModifica();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoZootecniaControlloCond
	 */
	public EsitoZootecniaControlloCond getEntity()
	{
		EsitoZootecniaControlloCond esito = new EsitoZootecniaControlloCond();
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_flgAziNonRegBDN(_flgAziNonRegBDN);
		esito.set_flgRegAziAss(_flgRegAziAss);
		esito.set_flgRegAziNonConforme(_flgRegAziNonConforme);
		esito.set_flgOviSuiNoMarcDoc(_flgOviSuiNoMarcDoc);
		esito.set_flgBovBufOviNoReg(_flgBovBufOviNoReg);
		esito.set_flgBovBufOviIdentNoConf(_flgBovBufOviIdentNoConf);
		esito.set_flgOviSuiMarcNoConf(_flgOviSuiMarcNoConf);
		esito.set_flgBovBufNoPassMarchiDoc(_flgBovBufNoPassMarchiDoc);
		esito.set_flgAnomalie(_flgAnomalie);
		if(!_capiAnomaliBovini.equals("")) esito.set_capiAnomaliBovini(Integer.parseInt(_capiAnomaliBovini));
		if(!_capiAnomaliSuini.equals("")) esito.set_capiAnomaliSuini(Integer.parseInt(_capiAnomaliSuini));
		if(!_capiAnomaliOvini.equals("")) esito.set_capiAnomaliOvini(Integer.parseInt(_capiAnomaliOvini));
		if(!_capiConsistenzaBovini.equals("")) esito.set_capiConsistenzaBovini(Integer.parseInt(_capiConsistenzaBovini));
		if(!_capiConsistenzaSuini.equals("")) esito.set_capiConsistenzaSuini(Integer.parseInt(_capiConsistenzaSuini));
		if(!_capiConsistenzaOvini.equals("")) esito.set_capiConsistenzaOvini(Integer.parseInt(_capiConsistenzaOvini));
		if(!_capiControllatiBovini.equals("")) esito.set_capiControllatiBovini(Integer.parseInt(_capiControllatiBovini));
		if(!_capiControllatiSuini.equals("")) esito.set_capiControllatiSuini(Integer.parseInt(_capiControllatiSuini));
		if(!_capiControllatiOvini.equals("")) esito.set_capiControllatiOvini(Integer.parseInt(_capiControllatiOvini));	
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoZootecniaControlloCond esito)
	{
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_flgAziNonRegBDN(_flgAziNonRegBDN);
		esito.set_flgRegAziAss(_flgRegAziAss);
		esito.set_flgRegAziNonConforme(_flgRegAziNonConforme);
		esito.set_flgOviSuiNoMarcDoc(_flgOviSuiNoMarcDoc);
		esito.set_flgBovBufOviNoReg(_flgBovBufOviNoReg);
		esito.set_flgBovBufOviIdentNoConf(_flgBovBufOviIdentNoConf);
		esito.set_flgOviSuiMarcNoConf(_flgOviSuiMarcNoConf);
		esito.set_flgBovBufNoPassMarchiDoc(_flgBovBufNoPassMarchiDoc);
		esito.set_flgAnomalie(_flgAnomalie);
		if(!_capiAnomaliBovini.equals("")) esito.set_capiAnomaliBovini(Integer.parseInt(_capiAnomaliBovini));
		if(!_capiAnomaliSuini.equals("")) esito.set_capiAnomaliSuini(Integer.parseInt(_capiAnomaliSuini));
		if(!_capiAnomaliOvini.equals("")) esito.set_capiAnomaliOvini(Integer.parseInt(_capiAnomaliOvini));
		if(!_capiConsistenzaBovini.equals("")) esito.set_capiConsistenzaBovini(Integer.parseInt(_capiConsistenzaBovini));
		if(!_capiConsistenzaSuini.equals("")) esito.set_capiConsistenzaSuini(Integer.parseInt(_capiConsistenzaSuini));
		if(!_capiConsistenzaOvini.equals("")) esito.set_capiConsistenzaOvini(Integer.parseInt(_capiConsistenzaOvini));
		if(!_capiControllatiBovini.equals("")) esito.set_capiControllatiBovini(Integer.parseInt(_capiControllatiBovini));
		if(!_capiControllatiSuini.equals("")) esito.set_capiControllatiSuini(Integer.parseInt(_capiControllatiSuini));
		if(!_capiControllatiOvini.equals("")) esito.set_capiControllatiOvini(Integer.parseInt(_capiControllatiOvini));
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
	}
	
	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/
	
	
	public String get_cuaa(){
		return _cuaa;
	}
	
	public void set_cuaa(String cuaa){
		_cuaa= cuaa;
	}
	
	public String get_domanda(){
		return _domanda;
	}
	
	public void set_domanda(String domanda){
		_domanda= domanda;
	}
	
	public String get_campagna(){
		return "" + _campagna;
	}
	
	public void set_campagna(String campagna){
		_campagna = campagna;
	}

	public void set_dataModifica(Date _dataModifica) {
		this._dataModifica = _dataModifica;
	}

	public Date get_dataModifica() {
		if(_userModifica == null || _userModifica.equals("") ) return _dataInserimento;
		else return _dataModifica;
	}
	
	public String get_dataModificaStr() {
		if(_userModifica == null || _userModifica.equals("") ) return Utils.getDateString(_dataInserimento);
		else return Utils.getDateString(_dataModifica);
	}
	
	public void set_userModifica(String _userModifica){
		this._userModifica = _userModifica;
	}
	
	public String get_userModifica(){
		if(_userModifica == null || _userModifica.equals("") ) return _userInserimento;
		else return _userModifica;
	}

	public void set_dataInserimento(Date _dataInserimento) {
		this._dataInserimento = _dataInserimento;
	}

	public Date get_dataInserimento() {
		return _dataInserimento;
	}

	public void set_userInserimento(String _userInserimento) {
		this._userInserimento = _userInserimento;
	}

	public String get_userInserimento() {
		return _userInserimento;
	}

	public void set_flgAziNonRegBDN(String _flgAziNonRegBDN) {
		this._flgAziNonRegBDN = _flgAziNonRegBDN;
	}

	public String get_flgAziNonRegBDN() {
		return _flgAziNonRegBDN;
	}

	public void set_flgRegAziAss(String _flgRegAziAss) {
		this._flgRegAziAss = _flgRegAziAss;
	}

	public String get_flgRegAziAss() {
		return _flgRegAziAss;
	}

	public void set_flgRegAziNonConforme(String _flgRegAziNonConforme) {
		this._flgRegAziNonConforme = _flgRegAziNonConforme;
	}

	public String get_flgRegAziNonConforme() {
		return _flgRegAziNonConforme;
	}

	public void set_flgOviSuiNoMarcDoc(String _flgOviSuiNoMarcDoc) {
		this._flgOviSuiNoMarcDoc = _flgOviSuiNoMarcDoc;
	}

	public String get_flgOviSuiNoMarcDoc() {
		return _flgOviSuiNoMarcDoc;
	}

	public void set_flgBovBufOviNoReg(String _flgBovBufOviNoReg) {
		this._flgBovBufOviNoReg = _flgBovBufOviNoReg;
	}

	public String get_flgBovBufOviNoReg() {
		return _flgBovBufOviNoReg;
	}

	public void set_flgBovBufOviIdentNoConf(String _flgBovBufOviIdentNoConf) {
		this._flgBovBufOviIdentNoConf = _flgBovBufOviIdentNoConf;
	}

	public String get_flgBovBufOviIdentNoConf() {
		return _flgBovBufOviIdentNoConf;
	}

	public void set_flgBovBufNoPassMarchiDoc(String _flgBovBufNoPassMarchiDoc) {
		this._flgBovBufNoPassMarchiDoc = _flgBovBufNoPassMarchiDoc;
	}

	public String get_flgBovBufNoPassMarchiDoc() {
		return _flgBovBufNoPassMarchiDoc;
	}

	public void set_flgAnomalie(String _flgAnomalie) {
		this._flgAnomalie = _flgAnomalie;
	}

	public String get_flgAnomalie() {
		return _flgAnomalie;
	}

	public void set_capiAnomaliBovini(String _capiAnomaliBovini) {
		this._capiAnomaliBovini = _capiAnomaliBovini;
	}

	public String get_capiAnomaliBovini() {
		return _capiAnomaliBovini;
	}

	public void set_capiAnomaliSuini(String _capiAnomaliSuini) {
		this._capiAnomaliSuini = _capiAnomaliSuini;
	}

	public String get_capiAnomaliSuini() {
		return _capiAnomaliSuini;
	}

	public void set_capiAnomaliOvini(String _capiAnomaliOvini) {
		this._capiAnomaliOvini = _capiAnomaliOvini;
	}

	public String get_capiAnomaliOvini() {
		return _capiAnomaliOvini;
	}

	public void set_capiConsistenzaBovini(String _capiConsistenzaBovini) {
		this._capiConsistenzaBovini = _capiConsistenzaBovini;
	}

	public String get_capiConsistenzaBovini() {
		return _capiConsistenzaBovini;
	}

	public void set_capiConsistenzaSuini(String _capiConsistenzaSuini) {
		this._capiConsistenzaSuini = _capiConsistenzaSuini;
	}

	public String get_capiConsistenzaSuini() {
		return _capiConsistenzaSuini;
	}

	public void set_capiConsistenzaOvini(String _capiConsistenzaOvini) {
		this._capiConsistenzaOvini = _capiConsistenzaOvini;
	}

	public String get_capiConsistenzaOvini() {
		return _capiConsistenzaOvini;
	}

	public void set_capiControllatiBovini(String _capiControllatiBovini) {
		this._capiControllatiBovini = _capiControllatiBovini;
	}

	public String get_capiControllatiBovini() {
		return _capiControllatiBovini;
	}

	public void set_capiControllatiSuini(String _capiControllatiSuini) {
		this._capiControllatiSuini = _capiControllatiSuini;
	}

	public String get_capiControllatiSuini() {
		return _capiControllatiSuini;
	}

	public void set_capiControllatiOvini(String _capiControllatiOvini) {
		this._capiControllatiOvini = _capiControllatiOvini;
	}

	public String get_capiControllatiOvini() {
		return _capiControllatiOvini;
	}

	public void set_flgOviSuiMarcNoConf(String _flgOviSuiMarcNoConf) {
		this._flgOviSuiMarcNoConf = _flgOviSuiMarcNoConf;
	}

	public String get_flgOviSuiMarcNoConf() {
		return _flgOviSuiMarcNoConf;
	}

	public void set_note(String _note) {
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

	
}
