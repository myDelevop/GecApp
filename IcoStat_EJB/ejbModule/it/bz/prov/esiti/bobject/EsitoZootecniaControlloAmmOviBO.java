package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.EsitoZootecniaControlloAmmOvi;
import it.bz.prov.esiti.util.Utils;
import java.util.Date;


/**
 * Business entity che rappresenta i dati del controllo di ammissibilità zootecnia per i ovicaprini
 * 
 * @author bpettazzoni
 *
 */

public class EsitoZootecniaControlloAmmOviBO {

	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _capiAnagrafeTot;
	private String _capiAnagrafeM;
	private String _capiAnagrafeF;
	private String _capiRegistroTot;
	private String _capiRegistroM;
	private String _capiRegistroF;
	private String _capiAziendaTot;
	private String _capiAziendaM;
	private String _capiAziendaF;
	private String _flgInosservanzaNorme;
	private String _capiNonConformiTot;
	private String _capiNonConformiM;
	private String _capiNonConformiPecoreCapre;
	private String _flgAnomalie;
	private String _flgRegAziAssNonComp;
	private String _flgRegAziNonCompSitSod; 
	private String _flgRegAziComp;
	private String _dichiarazioniProduttore;
	private String _dichiarazioniControllore;
	private String _note;
	private Date _dataInserimento;
	private String _userInserimento;
	private Date _dataModifica;
	private String _userModifica;
	private String _capi_anom_amm_scarico_bdn;
	
	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoZootecniaControlloAmmOviBO(){
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
		_userInserimento = "";
		_userModifica = "";
		_capi_anom_amm_scarico_bdn = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esito
	 */
	public EsitoZootecniaControlloAmmOviBO(EsitoZootecniaControlloAmmOvi esito){
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_campagna = esito.get_campagna();
		_capiAnagrafeTot = esito.get_capiAnagrafeTot();
		_capiAnagrafeM = esito.get_capiAnagrafeM();
		_capiAnagrafeF = esito.get_capiAnagrafeF();
		_capiRegistroTot = esito.get_capiRegistroTot();
		_capiRegistroM = esito.get_capiRegistroM();
		_capiRegistroF = esito.get_capiRegistroF();
		_capiAziendaTot = esito.get_capiAziendaTot();
		_capiAziendaM = esito.get_capiAziendaM();
		_capiAziendaF = esito.get_capiAziendaF();
		_flgInosservanzaNorme = esito.get_flgInosservanzaNorme();
		_capiNonConformiTot = esito.get_capiNonConformiTot();
		_capiNonConformiM = esito.get_capiNonConformiM();
		_capiNonConformiPecoreCapre = esito.get_capiNonConformiPecoreCapre();
		_flgAnomalie = esito.get_flgAnomalie();
		_flgRegAziAssNonComp = esito.get_flgRegAziAssNonComp();
		_flgRegAziNonCompSitSod = esito.get_flgRegAziNonCompSitSod(); 
		_flgRegAziComp = esito.get_flgRegAziComp();
		_dichiarazioniProduttore = esito.get_dichiarazioniProduttore();
		_dichiarazioniControllore = esito.get_dichiarazioniControllore();
		_note = esito.get_note();
		_dataModifica = esito.get_data_modifica();
		_userModifica = esito.get_user_modifica();
		_dataInserimento = esito.get_data_inserimento();
		_userInserimento = esito.get_user_inserimento();
		_capi_anom_amm_scarico_bdn = esito.get_capi_anom_amm_scarico_bdn();
	}
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoZootecniaControlloAmmOviBO
	 */
	public EsitoZootecniaControlloAmmOviBO clone()
	{
		EsitoZootecniaControlloAmmOviBO esito = new EsitoZootecniaControlloAmmOviBO();
		esito.set_campagna(_campagna);
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);		
		esito.set_capiAnagrafeTot(_capiAnagrafeTot);
		esito.set_capiAnagrafeM(_capiAnagrafeM);
		esito.set_capiAnagrafeF(_capiAnagrafeF);
		esito.set_capiRegistroTot(_capiRegistroTot);
		esito.set_capiRegistroM(_capiRegistroM);
		esito.set_capiRegistroF(_capiRegistroF);
		esito.set_capiAziendaTot(_capiAziendaTot);
		esito.set_capiAziendaM(_capiAziendaM);
		esito.set_capiAziendaF(_capiAziendaF);
		esito.set_flgInosservanzaNorme(_flgInosservanzaNorme);
		esito.set_capiNonConformiTot(_capiNonConformiTot);
		esito.set_capiNonConformiM(_capiNonConformiM);
		esito.set_capiNonConformiPecoreCapre(_capiNonConformiPecoreCapre);
		esito.set_flgAnomalie(_flgAnomalie);
		esito.set_flgRegAziAssNonComp(_flgRegAziAssNonComp);
		esito.set_flgRegAziNonCompSitSod(_flgRegAziNonCompSitSod); 
		esito.set_flgRegAziComp(_flgRegAziComp);
		esito.set_dichiarazioniProduttore(_dichiarazioniProduttore);
		esito.set_dichiarazioniControllore(_dichiarazioniControllore);	
		esito.set_note(_note);
		esito.set_userInserimento(_userInserimento);
		esito.set_dataInserimento(_dataInserimento);
		esito.set_userModifica(_userModifica);
		esito.set_dataModifica(_dataModifica);
		esito.set_capi_anom_amm_scarico_bdn(_capi_anom_amm_scarico_bdn);
		return esito;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esito
	 */
	public void recovery(EsitoZootecniaControlloAmmOviBO esito)
	{
		_campagna = "" + esito.get_campagna();
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_capiAnagrafeTot = esito.get_capiAnagrafeTot();
		_capiAnagrafeM = esito.get_capiAnagrafeM();
		_capiAnagrafeF = esito.get_capiAnagrafeF();
		_capiRegistroTot = esito.get_capiRegistroTot();
		_capiRegistroM = esito.get_capiRegistroM();
		_capiRegistroF = esito.get_capiRegistroF();
		_capiAziendaTot = esito.get_capiAziendaTot();
		_capiAziendaM = esito.get_capiAziendaM();
		_capiAziendaF = esito.get_capiAziendaF();
		_flgInosservanzaNorme = esito.get_flgInosservanzaNorme();
		_capiNonConformiTot = esito.get_capiNonConformiTot();
		_capiNonConformiM = esito.get_capiNonConformiM();
		_capiNonConformiPecoreCapre = esito.get_capiNonConformiPecoreCapre();
		_flgAnomalie = esito.get_flgAnomalie();
		_flgRegAziAssNonComp = esito.get_flgRegAziAssNonComp();
		_flgRegAziNonCompSitSod = esito.get_flgRegAziNonCompSitSod(); 
		_flgRegAziComp = esito.get_flgRegAziComp();
		_dichiarazioniProduttore = esito.get_dichiarazioniProduttore();
		_dichiarazioniControllore = esito.get_dichiarazioniControllore();
		_note = esito.get_note();
		_userInserimento = esito.get_userInserimento();
		_dataInserimento = esito.get_dataInserimento();
		_userModifica = esito.get_userModifica();
		_dataModifica = esito.get_dataModifica();
		_capi_anom_amm_scarico_bdn = esito.get_capi_anom_amm_scarico_bdn();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoZootecniaControlloAmmOvi
	 */
	public EsitoZootecniaControlloAmmOvi getEntity()
	{
		EsitoZootecniaControlloAmmOvi esito = new EsitoZootecniaControlloAmmOvi();
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		if(!_capiAnagrafeTot.equals("")) esito.set_capiAnagrafeTot(Integer.parseInt(_capiAnagrafeTot));
		if(!_capiAnagrafeM.equals("")) esito.set_capiAnagrafeM(Integer.parseInt(_capiAnagrafeM));
		if(!_capiAnagrafeF.equals("")) esito.set_capiAnagrafeF(Integer.parseInt(_capiAnagrafeF));
		if(!_capiRegistroTot.equals("")) esito.set_capiRegistroTot(Integer.parseInt(_capiRegistroTot));
		if(!_capiRegistroM.equals("")) esito.set_capiRegistroM(Integer.parseInt(_capiRegistroM));
		if(!_capiRegistroF.equals("")) esito.set_capiRegistroF(Integer.parseInt(_capiRegistroF));
		if(!_capiAziendaTot.equals("")) esito.set_capiAziendaTot(Integer.parseInt(_capiAziendaTot));
		if(!_capiAziendaM.equals("")) esito.set_capiAziendaM(Integer.parseInt(_capiAziendaM));
		if(!_capiAziendaF.equals("")) esito.set_capiAziendaF(Integer.parseInt(_capiAziendaF));
		esito.set_flgInosservanzaNorme(_flgInosservanzaNorme);
		if(!_capiNonConformiTot.equals("")) esito.set_capiNonConformiTot(Integer.parseInt(_capiNonConformiTot));
		if(!_capiNonConformiM.equals("")) esito.set_capiNonConformiM(Integer.parseInt(_capiNonConformiM));
		if(!_capiNonConformiPecoreCapre.equals("")) esito.set_capiNonConformiPecoreCapre(Integer.parseInt(_capiNonConformiPecoreCapre));
		esito.set_flgAnomalie(_flgAnomalie);
		esito.set_flgRegAziAssNonComp(_flgRegAziAssNonComp);
		esito.set_flgRegAziNonCompSitSod(_flgRegAziNonCompSitSod); 
		esito.set_flgRegAziComp(_flgRegAziComp);
		esito.set_dichiarazioniProduttore(_dichiarazioniProduttore);
		esito.set_dichiarazioniControllore(_dichiarazioniControllore);
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_capi_anom_amm_scarico_bdn(_capi_anom_amm_scarico_bdn);
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoZootecniaControlloAmmOvi esito)
	{
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		if(!_capiAnagrafeTot.equals("")) esito.set_capiAnagrafeTot(Integer.parseInt(_capiAnagrafeTot));
		if(!_capiAnagrafeM.equals("")) esito.set_capiAnagrafeM(Integer.parseInt(_capiAnagrafeM));
		if(!_capiAnagrafeF.equals("")) esito.set_capiAnagrafeF(Integer.parseInt(_capiAnagrafeF));
		if(!_capiRegistroTot.equals("")) esito.set_capiRegistroTot(Integer.parseInt(_capiRegistroTot));
		if(!_capiRegistroM.equals("")) esito.set_capiRegistroM(Integer.parseInt(_capiRegistroM));
		if(!_capiRegistroF.equals("")) esito.set_capiRegistroF(Integer.parseInt(_capiRegistroF));
		if(!_capiAziendaTot.equals("")) esito.set_capiAziendaTot(Integer.parseInt(_capiAziendaTot));
		if(!_capiAziendaM.equals("")) esito.set_capiAziendaM(Integer.parseInt(_capiAziendaM));
		if(!_capiAziendaF.equals("")) esito.set_capiAziendaF(Integer.parseInt(_capiAziendaF));
		esito.set_flgInosservanzaNorme(_flgInosservanzaNorme);
		if(!_capiNonConformiTot.equals("")) esito.set_capiNonConformiTot(Integer.parseInt(_capiNonConformiTot));
		if(!_capiNonConformiM.equals("")) esito.set_capiNonConformiM(Integer.parseInt(_capiNonConformiM));
		if(!_capiNonConformiPecoreCapre.equals("")) esito.set_capiNonConformiPecoreCapre(Integer.parseInt(_capiNonConformiPecoreCapre));
		esito.set_flgAnomalie(_flgAnomalie);
		esito.set_flgRegAziAssNonComp(_flgRegAziAssNonComp);
		esito.set_flgRegAziNonCompSitSod(_flgRegAziNonCompSitSod); 
		esito.set_flgRegAziComp(_flgRegAziComp);
		esito.set_dichiarazioniProduttore(_dichiarazioniProduttore);
		esito.set_dichiarazioniControllore(_dichiarazioniControllore);
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
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
		return _campagna;
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

	public void set_capiAnagrafeTot(String _capiAnagrafeTot) {
		this._capiAnagrafeTot = _capiAnagrafeTot;
	}

	public String get_capiAnagrafeTot() {
		return _capiAnagrafeTot;
	}

	public void set_capiAnagrafeM(String _capiAnagrafeM) {
		this._capiAnagrafeM = _capiAnagrafeM;
	}

	public String get_capiAnagrafeM() {
		return _capiAnagrafeM;
	}

	public void set_capiAnagrafeF(String  _capiAnagrafeF) {
		this._capiAnagrafeF = _capiAnagrafeF;
	}

	public String get_capiAnagrafeF() {
		return _capiAnagrafeF;
	}

	public void set_capiRegistroTot(String _capiRegistroTot) {
		this._capiRegistroTot = _capiRegistroTot;
	}

	public String get_capiRegistroTot() {
		return _capiRegistroTot;
	}

	public void set_capiRegistroM(String _capiRegistroM) {
		this._capiRegistroM = _capiRegistroM;
	}

	public String get_capiRegistroM() {
		return _capiRegistroM;
	}

	public void set_capiRegistroF(String _capiRegistroF) {
		this._capiRegistroF = _capiRegistroF;
	}

	public String get_capiRegistroF() {
		return _capiRegistroF;
	}

	public void set_capiAziendaTot(String _capiAziendaTot) {
		this._capiAziendaTot = _capiAziendaTot;
	}

	public String get_capiAziendaTot() {
		return _capiAziendaTot;
	}

	public void set_capiAziendaM(String _capiAziendaM) {
		this._capiAziendaM = _capiAziendaM;
	}

	public String get_capiAziendaM() {
		return _capiAziendaM;
	}

	public void set_capiAziendaF(String _capiAziendaF) {
		this._capiAziendaF = _capiAziendaF;
	}

	public String get_capiAziendaF() {
		return _capiAziendaF;
	}

	public void set_flgInosservanzaNorme(String _flgInosservanzaNorme) {
		this._flgInosservanzaNorme = _flgInosservanzaNorme;
	}

	public String get_flgInosservanzaNorme() {
		return _flgInosservanzaNorme;
	}

	public void set_capiNonConformiTot(String  _capiNonConformiTot) {
		this._capiNonConformiTot = _capiNonConformiTot;
	}

	public String get_capiNonConformiTot() {
		return _capiNonConformiTot;
	}

	public void set_capiNonConformiM(String _capiNonConformiM) {
		this._capiNonConformiM = _capiNonConformiM;
	}

	public String get_capiNonConformiM() {
		return _capiNonConformiM;
	}

	public void set_capiNonConformiPecoreCapre(String _capiNonConformiPecoreCapre) {
		this._capiNonConformiPecoreCapre = _capiNonConformiPecoreCapre;
	}

	public String get_capiNonConformiPecoreCapre() {
		return _capiNonConformiPecoreCapre;
	}

	public void set_flgAnomalie(String _flgAnomalie) {
		this._flgAnomalie = _flgAnomalie;
	}

	public String get_flgAnomalie() {
		return _flgAnomalie;
	}

	public void set_flgRegAziAssNonComp(String _flgRegAziAssNonComp) {
		this._flgRegAziAssNonComp = _flgRegAziAssNonComp;
	}

	public String get_flgRegAziAssNonComp() {
		return _flgRegAziAssNonComp;
	}

	public void set_flgRegAziNonCompSitSod(String _flgRegAziNonCompSitSod) {
		this._flgRegAziNonCompSitSod = _flgRegAziNonCompSitSod;
	}

	public String get_flgRegAziNonCompSitSod() {
		return _flgRegAziNonCompSitSod;
	}

	public void set_flgRegAziComp(String _flgRegAziComp) {
		this._flgRegAziComp = _flgRegAziComp;
	}

	public String get_flgRegAziComp() {
		return _flgRegAziComp;
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

	public void set_note(String _note) {
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

	public String get_capi_anom_amm_scarico_bdn() {
		return _capi_anom_amm_scarico_bdn;
	}

	public void set_capi_anom_amm_scarico_bdn(String _capi_anom_amm_scarico_bdn) {
		this._capi_anom_amm_scarico_bdn = _capi_anom_amm_scarico_bdn;
	}
	
}
