package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.EsitoZootecniaControlloAmmBov;
import it.bz.prov.esiti.util.Utils;

import java.util.Date;

/**
 * Business entity che rappresenta i dati del controllo di ammissibilità zootecnia per i Bovini
 * 
 * @author bpettazzoni
 *
 */
public class EsitoZootecniaControlloAmmBovBO {

	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _capiAnagrafe;
	private String _capiRegistro;
	private String _capiPresenti;
	private String _flgRegistroAssente;
	private String _flgInosservanzaNorme;
	private String _capiNonConformiTot;
	private String _capiNonConformiTitSpec;
	private String _capiNonConformiArt68Int162;
	private String _capiNonConformiArt68Int163;
	private String _dichiarazioniProduttore;
	private String _dichiarazioniControllore;
	private String _flgAnomalie;
	private String _note;
	private Date _dataInserimento;
	private String _userInserimento;
	private Date _dataModifica;
	private String _userModifica;
	private String _capi_richiesti_int_310;
	private String _capi_richiesti_int_311;
	private String _capi_richiesti_int_313;
	private String _capi_richiesti_int_315;
	private String _capi_richiesti_int_316;
	private String _capi_richiesti_int_318;
	private String _capi_richiesti_int_322;
	private String _capi_irreg_int_310;
	private String _capi_irreg_int_311;
	private String _capi_irreg_int_313;
	private String _capi_irreg_int_315;
	private String _capi_irreg_int_316;
	private String _capi_irreg_int_318;
	private String _capi_irreg_int_322;
	private String _capi_anom_amm_scarico_bdn;

	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoZootecniaControlloAmmBovBO(){
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
		_userInserimento = "";
		_userModifica = "";
		_capi_richiesti_int_310 = "";
		_capi_richiesti_int_311 = "";
		_capi_richiesti_int_313 = "";
		_capi_richiesti_int_315 = "";
		_capi_richiesti_int_316 = "";
		_capi_richiesti_int_318 = "";
		_capi_richiesti_int_322 = "";
		_capi_irreg_int_310	    = "";
		_capi_irreg_int_311	    = "";
		_capi_irreg_int_313	    = "";
		_capi_irreg_int_315	    = "";
		_capi_irreg_int_316	    = "";
		_capi_irreg_int_318	    = "";
		_capi_irreg_int_322	    = "";
		_capi_anom_amm_scarico_bdn = "";

	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esito
	 */
	public EsitoZootecniaControlloAmmBovBO(EsitoZootecniaControlloAmmBov esito){
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_campagna = esito.get_campagna();		
		_capiAnagrafe = esito.get_capiAnagrafe();
		_capiRegistro = esito.get_capiRegistro();
		_capiPresenti = esito.get_capiPresenti();
		_flgRegistroAssente = esito.get_flgRegistroAssente();
		_flgInosservanzaNorme = esito.get_flgInosservanzaNorme();
		_capiNonConformiTot = esito.get_capiNonConformiTot();
		_capiNonConformiTitSpec = esito.get_capiNonConformiTitSpec();
		_capiNonConformiArt68Int162 = esito.get_capiNonConformiArt68Int162();
		_capiNonConformiArt68Int163 = esito.get_capiNonConformiArt68Int163();
		_dichiarazioniProduttore = esito.get_dichiarazioniProduttore();
		_dichiarazioniControllore = esito.get_dichiarazioniControllore();
		_flgAnomalie = esito.get_flgAnomalie();	
		_note = esito.get_note();
		_dataModifica = esito.get_data_modifica();
		_userModifica = esito.get_user_modifica();
		_dataInserimento = esito.get_data_inserimento();
		_userInserimento = esito.get_user_inserimento();
		_capi_richiesti_int_310 = esito.get_capi_richiesti_int_310();
	    _capi_richiesti_int_311 = esito.get_capi_richiesti_int_311();
	    _capi_richiesti_int_313 = esito.get_capi_richiesti_int_313();
	    _capi_richiesti_int_315 = esito.get_capi_richiesti_int_315();
	    _capi_richiesti_int_316 = esito.get_capi_richiesti_int_316();
	    _capi_richiesti_int_318 = esito.get_capi_richiesti_int_318();
	    _capi_richiesti_int_322 = esito.get_capi_richiesti_int_322();
	    _capi_irreg_int_310	    = esito.get_capi_irreg_int_310	  ();
	    _capi_irreg_int_311	    = esito.get_capi_irreg_int_311	  ();
	    _capi_irreg_int_313	    = esito.get_capi_irreg_int_313	  ();
	    _capi_irreg_int_315	    = esito.get_capi_irreg_int_315	  ();
	    _capi_irreg_int_316	    = esito.get_capi_irreg_int_316	  ();
	    _capi_irreg_int_318	    = esito.get_capi_irreg_int_318	  ();
	    _capi_irreg_int_322	    = esito.get_capi_irreg_int_322	  ();
	    _capi_anom_amm_scarico_bdn = esito.get_capi_anom_amm_scarico_bdn();
	}
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoZootecniaControlloAmmBovBO
	 */
	public EsitoZootecniaControlloAmmBovBO clone()
	{
		EsitoZootecniaControlloAmmBovBO esito = new EsitoZootecniaControlloAmmBovBO();
		esito.set_campagna(_campagna);
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);		
		esito.set_capiAnagrafe(_capiAnagrafe);
		esito.set_capiRegistro(_capiRegistro);
		esito.set_capiPresenti(_capiPresenti);
		esito.set_flgRegistroAssente(_flgRegistroAssente);
		esito.set_flgInosservanzaNorme(_flgInosservanzaNorme);
		esito.set_capiNonConformiTot(_capiNonConformiTot);
		esito.set_capiNonConformiTitSpec(_capiNonConformiTitSpec);
		esito.set_capiNonConformiArt68Int162(_capiNonConformiArt68Int162);
		esito.set_capiNonConformiArt68Int163(_capiNonConformiArt68Int163);
		esito.set_dichiarazioniControllore(_dichiarazioniControllore);
		esito.set_dichiarazioniProduttore(_dichiarazioniProduttore);
		esito.set_flgAnomalie(_flgAnomalie);
		esito.set_note(_note);
		esito.set_userInserimento(_userInserimento);
		esito.set_dataInserimento(_dataInserimento);
		esito.set_userModifica(_userModifica);
		esito.set_dataModifica(_dataModifica);
		esito.set_capi_richiesti_int_310 (_capi_richiesti_int_310);
	    esito.set_capi_richiesti_int_311 (_capi_richiesti_int_311);
	    esito.set_capi_richiesti_int_313 (_capi_richiesti_int_313);
	    esito.set_capi_richiesti_int_315 (_capi_richiesti_int_315);
	    esito.set_capi_richiesti_int_316 (_capi_richiesti_int_316);
	    esito.set_capi_richiesti_int_318 (_capi_richiesti_int_318);
	    esito.set_capi_richiesti_int_322 (_capi_richiesti_int_322);
	    esito.set_capi_irreg_int_310	 (_capi_irreg_int_310	 );     
	    esito.set_capi_irreg_int_311	 (_capi_irreg_int_311	 );     
	    esito.set_capi_irreg_int_313	 (_capi_irreg_int_313	 );     
	    esito.set_capi_irreg_int_315	 (_capi_irreg_int_315	 );     
	    esito.set_capi_irreg_int_316	 (_capi_irreg_int_316	 );     
	    esito.set_capi_irreg_int_318	 (_capi_irreg_int_318	 );     
	    esito.set_capi_irreg_int_322	 (_capi_irreg_int_322	 ); 
	    esito.set_capi_anom_amm_scarico_bdn(_capi_anom_amm_scarico_bdn);
		return esito;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esito
	 */
	public void recovery(EsitoZootecniaControlloAmmBovBO esito)
	{
		_campagna = "" + esito.get_campagna();
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_capiAnagrafe = esito.get_capiAnagrafe();
		_capiRegistro = esito.get_capiRegistro();
		_capiPresenti = esito.get_capiPresenti();
		_flgRegistroAssente = esito.get_flgRegistroAssente();
		_flgInosservanzaNorme = esito.get_flgInosservanzaNorme();
		_capiNonConformiTot = esito.get_capiNonConformiTot();
		_capiNonConformiTitSpec = esito.get_capiNonConformiTitSpec();
		_capiNonConformiArt68Int162 = esito.get_capiNonConformiArt68Int162();
		_capiNonConformiArt68Int163 = esito.get_capiNonConformiArt68Int163();
		_dichiarazioniProduttore = esito.get_dichiarazioniProduttore();
		_dichiarazioniControllore = esito.get_dichiarazioniControllore();
		_flgAnomalie = esito.get_flgAnomalie();
		_note = esito.get_note();
		_userInserimento = esito.get_userInserimento();
		_dataInserimento = esito.get_dataInserimento();
		_userModifica = esito.get_userModifica();
		_dataModifica = esito.get_dataModifica();
		_capi_richiesti_int_310 = esito.get_capi_richiesti_int_310();
	    _capi_richiesti_int_311 = esito.get_capi_richiesti_int_311();
	    _capi_richiesti_int_313 = esito.get_capi_richiesti_int_313();
	    _capi_richiesti_int_315 = esito.get_capi_richiesti_int_315();
	    _capi_richiesti_int_316 = esito.get_capi_richiesti_int_316();
	    _capi_richiesti_int_318 = esito.get_capi_richiesti_int_318();
	    _capi_richiesti_int_322 = esito.get_capi_richiesti_int_322();
	    _capi_irreg_int_310	    = esito.get_capi_irreg_int_310	  ();
	    _capi_irreg_int_311	    = esito.get_capi_irreg_int_311	  ();
	    _capi_irreg_int_313	    = esito.get_capi_irreg_int_313	  ();
	    _capi_irreg_int_315	    = esito.get_capi_irreg_int_315	  ();
	    _capi_irreg_int_316	    = esito.get_capi_irreg_int_316	  ();
	    _capi_irreg_int_318	    = esito.get_capi_irreg_int_318	  ();
	    _capi_irreg_int_322	    = esito.get_capi_irreg_int_322	  ();
	    _capi_anom_amm_scarico_bdn = esito.get_capi_anom_amm_scarico_bdn();

	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoZootecniaControlloAmmBov
	 */
	public EsitoZootecniaControlloAmmBov getEntity()
	{
		EsitoZootecniaControlloAmmBov esito = new EsitoZootecniaControlloAmmBov();
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		if(!_capiAnagrafe.equals("")) esito.set_capiAnagrafe(Integer.parseInt(_capiAnagrafe));
		if(!_capiRegistro.equals("")) esito.set_capiRegistro(Integer.parseInt(_capiRegistro));
		if(!_capiPresenti.equals("")) esito.set_capiPresenti(Integer.parseInt(_capiPresenti));
		esito.set_flgRegistroAssente(_flgRegistroAssente);
		esito.set_flgInosservanzaNorme(_flgInosservanzaNorme);
		if(!_capiNonConformiTot.equals("")) esito.set_capiNonConformiTot(Integer.parseInt(_capiNonConformiTot));
		if(!_capiNonConformiTitSpec.equals("")) esito.set_capiNonConformiTitSpec(Integer.parseInt(_capiNonConformiTitSpec));
		if(!_capiNonConformiArt68Int162.equals("")) esito.set_capiNonConformiArt68Int162(Integer.parseInt(_capiNonConformiArt68Int162));
		if(!_capiNonConformiArt68Int163.equals("")) esito.set_capiNonConformiArt68Int163(Integer.parseInt(_capiNonConformiArt68Int163));
		esito.set_dichiarazioniControllore(_dichiarazioniControllore);
		esito.set_dichiarazioniProduttore(_dichiarazioniProduttore);
		esito.set_flgAnomalie(_flgAnomalie);
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_capi_richiesti_int_310 (_capi_richiesti_int_310);
	    esito.set_capi_richiesti_int_311 (_capi_richiesti_int_311);
	    esito.set_capi_richiesti_int_313 (_capi_richiesti_int_313);
	    esito.set_capi_richiesti_int_315 (_capi_richiesti_int_315);
	    esito.set_capi_richiesti_int_316 (_capi_richiesti_int_316);
	    esito.set_capi_richiesti_int_318 (_capi_richiesti_int_318);
	    esito.set_capi_richiesti_int_322 (_capi_richiesti_int_322);
	    esito.set_capi_irreg_int_310	 (_capi_irreg_int_310	 );     
	    esito.set_capi_irreg_int_311	 (_capi_irreg_int_311	 );     
	    esito.set_capi_irreg_int_313	 (_capi_irreg_int_313	 );     
	    esito.set_capi_irreg_int_315	 (_capi_irreg_int_315	 );     
	    esito.set_capi_irreg_int_316	 (_capi_irreg_int_316	 );     
	    esito.set_capi_irreg_int_318	 (_capi_irreg_int_318	 );     
	    esito.set_capi_irreg_int_322	 (_capi_irreg_int_322	 );  
	    esito.set_capi_anom_amm_scarico_bdn(_capi_anom_amm_scarico_bdn);
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoZootecniaControlloAmmBov esito)
	{
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		if(!_capiAnagrafe.equals("")) esito.set_capiAnagrafe(Integer.parseInt(_capiAnagrafe));
		if(!_capiRegistro.equals("")) esito.set_capiRegistro(Integer.parseInt(_capiRegistro));
		if(!_capiPresenti.equals("")) esito.set_capiPresenti(Integer.parseInt(_capiPresenti));
		esito.set_flgRegistroAssente(_flgRegistroAssente);
		esito.set_flgInosservanzaNorme(_flgInosservanzaNorme);
		if(!_capiNonConformiTot.equals("")) esito.set_capiNonConformiTot(Integer.parseInt(_capiNonConformiTot));
		if(!_capiNonConformiTitSpec.equals("")) esito.set_capiNonConformiTitSpec(Integer.parseInt(_capiNonConformiTitSpec));
		if(!_capiNonConformiArt68Int162.equals("")) esito.set_capiNonConformiArt68Int162(Integer.parseInt(_capiNonConformiArt68Int162));
		if(!_capiNonConformiArt68Int163.equals("")) esito.set_capiNonConformiArt68Int163(Integer.parseInt(_capiNonConformiArt68Int163));
		esito.set_dichiarazioniControllore(_dichiarazioniControllore);
		esito.set_dichiarazioniProduttore(_dichiarazioniProduttore);
		esito.set_flgAnomalie(_flgAnomalie);
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_capi_richiesti_int_310 (_capi_richiesti_int_310);
	    esito.set_capi_richiesti_int_311 (_capi_richiesti_int_311);
	    esito.set_capi_richiesti_int_313 (_capi_richiesti_int_313);
	    esito.set_capi_richiesti_int_315 (_capi_richiesti_int_315);
	    esito.set_capi_richiesti_int_316 (_capi_richiesti_int_316);
	    esito.set_capi_richiesti_int_318 (_capi_richiesti_int_318);
	    esito.set_capi_richiesti_int_322 (_capi_richiesti_int_322);
	    esito.set_capi_irreg_int_310	 (_capi_irreg_int_310	 );     
	    esito.set_capi_irreg_int_311	 (_capi_irreg_int_311	 );     
	    esito.set_capi_irreg_int_313	 (_capi_irreg_int_313	 );     
	    esito.set_capi_irreg_int_315	 (_capi_irreg_int_315	 );     
	    esito.set_capi_irreg_int_316	 (_capi_irreg_int_316	 );     
	    esito.set_capi_irreg_int_318	 (_capi_irreg_int_318	 );     
	    esito.set_capi_irreg_int_322	 (_capi_irreg_int_322	 );  
	    esito.set_capi_anom_amm_scarico_bdn(_capi_anom_amm_scarico_bdn);
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
		if(campagna == null) campagna = "";
		_campagna = campagna.trim();
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

	public void set_capiAnagrafe(String _capiAnagrafe) {
		this._capiAnagrafe = _capiAnagrafe;
	}

	public String get_capiAnagrafe() {
		return _capiAnagrafe;
	}

	public void set_capiRegistro(String _capiRegistro) {
		this._capiRegistro = _capiRegistro;
	}

	public String get_capiRegistro() {
		return _capiRegistro;
	}

	public void set_capiPresenti(String _capiPresenti) {
		this._capiPresenti = _capiPresenti;
	}

	public String get_capiPresenti() {
		return _capiPresenti;
	}

	public void set_flgRegistroAssente(String _flgRegistroAssente) {
		this._flgRegistroAssente = _flgRegistroAssente;
	}

	public String get_flgRegistroAssente() {
		return _flgRegistroAssente;
	}

	public void set_flgInosservanzaNorme(String _flgInosservanzaNorme) {
		this._flgInosservanzaNorme = _flgInosservanzaNorme;
	}

	public String get_flgInosservanzaNorme() {
		return _flgInosservanzaNorme;
	}

	public void set_capiNonConformiTot(String _capiNonConformiTot) {
		this._capiNonConformiTot = _capiNonConformiTot;
	}

	public String get_capiNonConformiTot() {
		return _capiNonConformiTot;
	}

	public void set_capiNonConformiTitSpec(String _capiNonConformiTitSpec) {
		this._capiNonConformiTitSpec = _capiNonConformiTitSpec;
	}

	public String get_capiNonConformiTitSpec() {
		return _capiNonConformiTitSpec;
	}

	public void set_capiNonConformiArt68Int162(String _capiNonConformiArt68Int162) {
		this._capiNonConformiArt68Int162 = _capiNonConformiArt68Int162;
	}

	public String get_capiNonConformiArt68Int162() {
		return _capiNonConformiArt68Int162;
	}

	public void set_capiNonConformiArt68Int163(String _capiNonConformiArt68Int163) {
		this._capiNonConformiArt68Int163 = _capiNonConformiArt68Int163;
	}

	public String get_capiNonConformiArt68Int163() {
		return _capiNonConformiArt68Int163;
	}

	public void set_dichiarazioniProduttore(String _dichiarazioniProduttore) {
		this._dichiarazioniProduttore = _dichiarazioniProduttore;
	}

	public String get_dichiarazioniProduttore() {
		return _dichiarazioniProduttore;
	}

	public void set_dichiarazioniControllore(String _dichiarazioniControllore) {
		this._dichiarazioniControllore = _dichiarazioniControllore;
	}

	public String get_dichiarazioniControllore() {
		return _dichiarazioniControllore;
	}

	public void set_flgAnomalie(String _flgAnomalie) {
		this._flgAnomalie = _flgAnomalie;
	}

	public String get_flgAnomalie() {
		return _flgAnomalie;
	}

	public void set_note(String _note) {
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

	public String get_capi_richiesti_int_313() {
		return _capi_richiesti_int_313;
	}

	public void set_capi_richiesti_int_313(String _capi_richiesti_int_313) {
		this._capi_richiesti_int_313 = _capi_richiesti_int_313;
	}

	public String get_capi_irreg_int_313() {
		return _capi_irreg_int_313;
	}

	public void set_capi_irreg_int_313(String _capi_irreg_int_313) {
		this._capi_irreg_int_313 = _capi_irreg_int_313;
	}

	public String get_capi_richiesti_int_322() {
		return _capi_richiesti_int_322;
	}

	public void set_capi_richiesti_int_322(String _capi_richiesti_int_322) {
		this._capi_richiesti_int_322 = _capi_richiesti_int_322;
	}

	public String get_capi_irreg_int_322() {
		return _capi_irreg_int_322;
	}

	public void set_capi_irreg_int_322(String _capi_irreg_int_322) {
		this._capi_irreg_int_322 = _capi_irreg_int_322;
	}

	public String get_capi_richiesti_int_310() {
		return _capi_richiesti_int_310;
	}

	public void set_capi_richiesti_int_310(String _capi_richiesti_int_310) {
		this._capi_richiesti_int_310 = _capi_richiesti_int_310;
	}

	public String get_capi_richiesti_int_311() {
		return _capi_richiesti_int_311;
	}

	public void set_capi_richiesti_int_311(String _capi_richiesti_int_311) {
		this._capi_richiesti_int_311 = _capi_richiesti_int_311;
	}

	public String get_capi_richiesti_int_315() {
		return _capi_richiesti_int_315;
	}

	public void set_capi_richiesti_int_315(String _capi_richiesti_int_315) {
		this._capi_richiesti_int_315 = _capi_richiesti_int_315;
	}

	public String get_capi_richiesti_int_316() {
		return _capi_richiesti_int_316;
	}

	public void set_capi_richiesti_int_316(String _capi_richiesti_int_316) {
		this._capi_richiesti_int_316 = _capi_richiesti_int_316;
	}

	public String get_capi_richiesti_int_318() {
		return _capi_richiesti_int_318;
	}

	public void set_capi_richiesti_int_318(String _capi_richiesti_int_318) {
		this._capi_richiesti_int_318 = _capi_richiesti_int_318;
	}

	public String get_capi_irreg_int_310() {
		return _capi_irreg_int_310;
	}

	public void set_capi_irreg_int_310(String _capi_irreg_int_310) {
		this._capi_irreg_int_310 = _capi_irreg_int_310;
	}

	public String get_capi_irreg_int_311() {
		return _capi_irreg_int_311;
	}

	public void set_capi_irreg_int_311(String _capi_irreg_int_311) {
		this._capi_irreg_int_311 = _capi_irreg_int_311;
	}

	public String get_capi_irreg_int_315() {
		return _capi_irreg_int_315;
	}

	public void set_capi_irreg_int_315(String _capi_irreg_int_315) {
		this._capi_irreg_int_315 = _capi_irreg_int_315;
	}

	public String get_capi_irreg_int_316() {
		return _capi_irreg_int_316;
	}

	public void set_capi_irreg_int_316(String _capi_irreg_int_316) {
		this._capi_irreg_int_316 = _capi_irreg_int_316;
	}

	public String get_capi_irreg_int_318() {
		return _capi_irreg_int_318;
	}

	public void set_capi_irreg_int_318(String _capi_irreg_int_318) {
		this._capi_irreg_int_318 = _capi_irreg_int_318;
	}

	public String get_capi_anom_amm_scarico_bdn() {
		return _capi_anom_amm_scarico_bdn;
	}

	public void set_capi_anom_amm_scarico_bdn(String _capi_anom_amm_scarico_bdn) {
		this._capi_anom_amm_scarico_bdn = _capi_anom_amm_scarico_bdn;
	}
	
}
