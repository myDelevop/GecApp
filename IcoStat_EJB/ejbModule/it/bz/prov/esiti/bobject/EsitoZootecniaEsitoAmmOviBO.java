package it.bz.prov.esiti.bobject;


import java.util.Date;
import it.bz.prov.esiti.entity.EsitoZootecniaEsitoAmmOvi;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta Esito del controllo di ammissibilità zootecnia per i Bovini
 * 
 * @author bpettazzoni
 *
 */

public class EsitoZootecniaEsitoAmmOviBO {

	private String _cuaa;
	private String _domanda;
	private String _campagna;	
	private String _esitoControlloTitoliSpeciali;
	private String _percRidControlloTitoliSpeciali;
	private String _esitoControlloArt68Int168;
	private String _percRidControlloArt68Int168;
	private String _sanzFlgApplPercRid;	
	private String _sanzPercRid;	
	private String _sanzFlgApplPercRidDoppia;
	private String _sanzPercRidDoppia;
	private String _sanzFlgEsclusione;
	private String _sanzPercRidEsclusione;
	private String _note;
	private Date _dataModifica;
	private String _userModifica;
	private Date _dataInserimento;
	private String _userInserimento;
	private String _sanzFlgEsclusioneUlteriore;
	private String _flgAmmControlloTitoliSpeciali;	
	private String _esi_contr_320; 
	private String _perc_rid_320; 
	private String _flg_appl_perc_rid_320; 
	private String _perc_rid_esi_320; 
	private String _flg_2x_perc_rid_320; 
	private String _perc_rid_2x_320; 
	private String _flg_esc_pag_320; 
	private String _perc_rid_esc_pag_320; 
	private String _flg_ult_esc_pag_320;
	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoZootecniaEsitoAmmOviBO(){
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_esitoControlloTitoliSpeciali = "";
		_percRidControlloTitoliSpeciali= "";
		_esitoControlloArt68Int168 = "";
		_percRidControlloArt68Int168= "";
		_sanzFlgApplPercRid = "";
		_sanzFlgApplPercRidDoppia = "";
		_sanzFlgEsclusione = "";
		_sanzPercRid = "";
		_sanzPercRidDoppia = "";
		_sanzPercRidEsclusione = "";
		_note = "";
		_userInserimento = "";
		_userModifica = "";
		_sanzFlgEsclusioneUlteriore = "";
		_flgAmmControlloTitoliSpeciali="";
		
		_esi_contr_320 = ""; 
		_perc_rid_320 = ""; 
		_flg_appl_perc_rid_320 = ""; 
		_perc_rid_esi_320 = ""; 
		_flg_2x_perc_rid_320 = ""; 
		_perc_rid_2x_320 = ""; 
		_flg_esc_pag_320 = ""; 
		_perc_rid_esc_pag_320 = ""; 
		_flg_ult_esc_pag_320 = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esito
	 */
	public EsitoZootecniaEsitoAmmOviBO(EsitoZootecniaEsitoAmmOvi esito){
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_campagna = esito.get_campagna();
		_esitoControlloTitoliSpeciali = esito.get_esitoControlloTitoliSpeciali();
		_percRidControlloTitoliSpeciali = esito.get_percRidControlloTitoliSpeciali();
		_esitoControlloArt68Int168 = esito.get_esitoControlloArt68Int168();
		_percRidControlloArt68Int168 = esito.get_percRidControlloArt68Int168();
		_sanzFlgApplPercRid = esito.get_sanzFlgApplPercRid();
		_sanzFlgApplPercRidDoppia = esito.get_sanzFlgApplPercRidDoppia();
		_sanzFlgEsclusione = esito.get_sanzFlgEsclusione();
		_sanzPercRid = esito.get_sanzPercRid();
		_sanzPercRidDoppia = esito.get_sanzPercRidDoppia();
		_sanzPercRidEsclusione = esito.get_sanzPercRidEsclusione();	
		_note = esito.get_note();
		_dataModifica = esito.get_data_modifica();
		_userModifica = esito.get_user_modifica();
		_dataInserimento = esito.get_data_inserimento();
		_userInserimento = esito.get_user_inserimento();
		_sanzFlgEsclusioneUlteriore = esito.get_sanzFlgEsclusioneUlteriore();
		_flgAmmControlloTitoliSpeciali=esito.get_flgAmmControlloTitoliSpeciali();
		_esi_contr_320         = esito.get_esi_contr_320(); 
		_perc_rid_320          = esito.get_perc_rid_320(); 
		_flg_appl_perc_rid_320 = esito.get_flg_appl_perc_rid_320();
		_perc_rid_esi_320      = esito.get_perc_rid_esi_320(); 
		_flg_2x_perc_rid_320   = esito.get_flg_2x_perc_rid_320(); 
		_perc_rid_2x_320       = esito.get_perc_rid_2x_320(); 
		_flg_esc_pag_320       = esito.get_flg_esc_pag_320(); 
		_perc_rid_esc_pag_320  = esito.get_perc_rid_esc_pag_320(); 
		_flg_ult_esc_pag_320   = esito.get_flg_ult_esc_pag_320();
	}
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoZootecniaEsitoAmmBovBO
	 */
	public EsitoZootecniaEsitoAmmOviBO clone()
	{
		EsitoZootecniaEsitoAmmOviBO esito = new EsitoZootecniaEsitoAmmOviBO();
		esito.set_campagna(_campagna);
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_esitoControlloTitoliSpeciali(_esitoControlloTitoliSpeciali);
		esito.set_percRidControlloTitoliSpeciali(_percRidControlloTitoliSpeciali);
		esito.set_esitoControlloArt68Int168(_esitoControlloArt68Int168);
		esito.set_percRidControlloArt68Int168(_percRidControlloArt68Int168);
		esito.set_sanzFlgApplPercRid(_sanzFlgApplPercRid);
		esito.set_sanzFlgApplPercRidDoppia(_sanzFlgApplPercRidDoppia);
		esito.set_sanzFlgEsclusione(_sanzFlgEsclusione);
		esito.set_sanzPercRid(_sanzPercRid);
		esito.set_sanzPercRidDoppia(_sanzPercRidDoppia);
		esito.set_sanzPercRidEsclusione(_sanzPercRidEsclusione);
		esito.set_note(get_note());
		esito.set_userInserimento(_userInserimento);
		esito.set_dataInserimento(_dataInserimento);
		esito.set_userModifica(_userModifica);
		esito.set_dataModifica(_dataModifica);
		esito.set_sanzFlgEsclusioneUlteriore(_sanzFlgEsclusioneUlteriore);
		esito.set_flgAmmControlloTitoliSpeciali(_flgAmmControlloTitoliSpeciali);
		esito.set_esi_contr_320        (_esi_contr_320);         
		esito.set_perc_rid_320         (_perc_rid_320);          
		esito.set_flg_appl_perc_rid_320(_flg_appl_perc_rid_320); 
		esito.set_perc_rid_esi_320     (_perc_rid_esi_320);      
		esito.set_flg_2x_perc_rid_320  (_flg_2x_perc_rid_320);   
		esito.set_perc_rid_2x_320      (_perc_rid_2x_320);       
		esito.set_flg_esc_pag_320      (_flg_esc_pag_320);       
		esito.set_perc_rid_esc_pag_320 (_perc_rid_esc_pag_320);  
		esito.set_flg_ult_esc_pag_320  (_flg_ult_esc_pag_320); 
		return esito;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esito
	 */
	public void recovery(EsitoZootecniaEsitoAmmOviBO esito)
	{
		_campagna = "" + esito.get_campagna();
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_percRidControlloTitoliSpeciali = esito.get_percRidControlloTitoliSpeciali();
		_esitoControlloTitoliSpeciali = esito.get_esitoControlloTitoliSpeciali();
		_esitoControlloArt68Int168 = esito.get_esitoControlloArt68Int168();
		_percRidControlloArt68Int168 = esito.get_percRidControlloArt68Int168();
		_sanzFlgApplPercRid = esito.get_sanzFlgApplPercRid();
		_sanzFlgApplPercRidDoppia = esito.get_sanzFlgApplPercRidDoppia();
		_sanzFlgEsclusione = esito.get_sanzFlgEsclusione();
		_sanzPercRid = esito.get_sanzPercRid();
		_sanzPercRidDoppia = esito.get_sanzPercRidDoppia();
		_sanzPercRidEsclusione = esito.get_sanzPercRidEsclusione();
		_note = esito.get_note();
		_userInserimento = esito.get_userInserimento();
		_dataInserimento = esito.get_dataInserimento();
		_userModifica = esito.get_userModifica();
		_dataModifica = esito.get_dataModifica();
		_sanzFlgEsclusioneUlteriore = esito.get_sanzFlgEsclusioneUlteriore();
		_esi_contr_320         = esito.get_esi_contr_320(); 
		_perc_rid_320          = esito.get_perc_rid_320(); 
		_flg_appl_perc_rid_320 = esito.get_flg_appl_perc_rid_320();
		_perc_rid_esi_320      = esito.get_perc_rid_esi_320(); 
		_flg_2x_perc_rid_320   = esito.get_flg_2x_perc_rid_320(); 
		_perc_rid_2x_320       = esito.get_perc_rid_2x_320(); 
		_flg_esc_pag_320       = esito.get_flg_esc_pag_320(); 
		_perc_rid_esc_pag_320  = esito.get_perc_rid_esc_pag_320(); 
		_flg_ult_esc_pag_320   = esito.get_flg_ult_esc_pag_320();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoZootecniaEsitoAmmOvi
	 */
	public EsitoZootecniaEsitoAmmOvi getEntity()
	{
		EsitoZootecniaEsitoAmmOvi esito = new EsitoZootecniaEsitoAmmOvi();
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_esitoControlloTitoliSpeciali(_esitoControlloTitoliSpeciali);
		esito.set_percRidControlloTitoliSpeciali(_percRidControlloTitoliSpeciali);
		esito.set_esitoControlloArt68Int168(_esitoControlloArt68Int168);
		esito.set_percRidControlloArt68Int168(_percRidControlloArt68Int168);
		esito.set_sanzFlgApplPercRid(_sanzFlgApplPercRid);
		esito.set_sanzFlgApplPercRidDoppia(_sanzFlgApplPercRidDoppia);
		esito.set_sanzFlgEsclusione(_sanzFlgEsclusione);
		esito.set_sanzPercRid(_sanzPercRid);
		esito.set_sanzPercRidDoppia(_sanzPercRidDoppia);
		esito.set_sanzPercRidEsclusione(_sanzPercRidEsclusione);
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_sanzFlgEsclusioneUlteriore(_sanzFlgEsclusioneUlteriore);
		esito.set_flgAmmControlloTitoliSpeciali(_flgAmmControlloTitoliSpeciali);
		esito.set_esi_contr_320        (_esi_contr_320);         
		esito.set_perc_rid_320         (_perc_rid_320);          
		esito.set_flg_appl_perc_rid_320(_flg_appl_perc_rid_320); 
		esito.set_perc_rid_esi_320     (_perc_rid_esi_320);      
		esito.set_flg_2x_perc_rid_320  (_flg_2x_perc_rid_320);   
		esito.set_perc_rid_2x_320      (_perc_rid_2x_320);       
		esito.set_flg_esc_pag_320      (_flg_esc_pag_320);       
		esito.set_perc_rid_esc_pag_320 (_perc_rid_esc_pag_320);  
		esito.set_flg_ult_esc_pag_320  (_flg_ult_esc_pag_320); 
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoZootecniaEsitoAmmOvi esito)
	{
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_esitoControlloTitoliSpeciali(_esitoControlloTitoliSpeciali);
		esito.set_percRidControlloTitoliSpeciali(_percRidControlloTitoliSpeciali);
		esito.set_esitoControlloArt68Int168(_esitoControlloArt68Int168);
		esito.set_percRidControlloArt68Int168(_percRidControlloArt68Int168);
		esito.set_sanzFlgApplPercRid(_sanzFlgApplPercRid);
		esito.set_sanzFlgApplPercRidDoppia(_sanzFlgApplPercRidDoppia);
		esito.set_sanzFlgEsclusione(_sanzFlgEsclusione);
		esito.set_sanzPercRid(_sanzPercRid);
		esito.set_sanzPercRidDoppia(_sanzPercRidDoppia);
		esito.set_sanzPercRidEsclusione(_sanzPercRidEsclusione);
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_sanzFlgEsclusioneUlteriore(_sanzFlgEsclusioneUlteriore);
		esito.set_flgAmmControlloTitoliSpeciali(_flgAmmControlloTitoliSpeciali);
		esito.set_esi_contr_320        (_esi_contr_320);         
		esito.set_perc_rid_320         (_perc_rid_320);          
		esito.set_flg_appl_perc_rid_320(_flg_appl_perc_rid_320); 
		esito.set_perc_rid_esi_320     (_perc_rid_esi_320);      
		esito.set_flg_2x_perc_rid_320  (_flg_2x_perc_rid_320);   
		esito.set_perc_rid_2x_320      (_perc_rid_2x_320);       
		esito.set_flg_esc_pag_320      (_flg_esc_pag_320);       
		esito.set_perc_rid_esc_pag_320 (_perc_rid_esc_pag_320);  
		esito.set_flg_ult_esc_pag_320  (_flg_ult_esc_pag_320); 
		
	}
	
	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/
	
	public String get_cuaa(){
		return _cuaa;
	}
	
	public void set_cuaa(String cuaa){
		_cuaa= cuaa.trim();
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

	public void set_esitoControlloTitoliSpeciali(String _esitoControlloTitoliSpeciali) {
		this._esitoControlloTitoliSpeciali = _esitoControlloTitoliSpeciali;
	}

	public String get_esitoControlloTitoliSpeciali() {
		return _esitoControlloTitoliSpeciali;
	}

	public void set_percRidControlloTitoliSpeciali(String _percRidControlloTitoliSpeciali) {
		this._percRidControlloTitoliSpeciali = _percRidControlloTitoliSpeciali;
	}

	public String get_percRidControlloTitoliSpeciali() {
		return _percRidControlloTitoliSpeciali;
	}

	public void set_esitoControlloArt68Int168(String _esitoControlloArt68Int168) {
		this._esitoControlloArt68Int168 = _esitoControlloArt68Int168;
	}

	public String get_esitoControlloArt68Int168() {
		return _esitoControlloArt68Int168;
	}

	public void set_percRidControlloArt68Int168(String _percRidControlloArt68Int168) {
		this._percRidControlloArt68Int168 = _percRidControlloArt68Int168;
	}

	public String get_percRidControlloArt68Int168() {
		return _percRidControlloArt68Int168;
	}

	public void set_sanzFlgApplPercRid(String _sanzFlgApplPercRid) {
		this._sanzFlgApplPercRid = _sanzFlgApplPercRid;
	}

	public String get_sanzFlgApplPercRid() {
		return _sanzFlgApplPercRid;
	}

	public void set_sanzPercRid(String _sanzPercRid) {
		this._sanzPercRid = _sanzPercRid;
	}

	public String get_sanzPercRid() {
		return _sanzPercRid;
	}

	public void set_sanzFlgApplPercRidDoppia(String _sanzFlgApplPercRidDoppia) {
		this._sanzFlgApplPercRidDoppia = _sanzFlgApplPercRidDoppia;
	}

	public String get_sanzFlgApplPercRidDoppia() {
		return _sanzFlgApplPercRidDoppia;
	}

	public void set_sanzPercRidDoppia(String _sanzPercRidDoppia) {
		this._sanzPercRidDoppia = _sanzPercRidDoppia;
	}

	public String get_sanzPercRidDoppia() {
		return _sanzPercRidDoppia;
	}

	public void set_sanzFlgEsclusione(String _sanzFlgEsclusione) {
		this._sanzFlgEsclusione = _sanzFlgEsclusione;
	}

	public String get_sanzFlgEsclusione() {
		return _sanzFlgEsclusione;
	}

	public void set_sanzPercRidEsclusione(String _sanzPercRidEsclusione) {
		this._sanzPercRidEsclusione = _sanzPercRidEsclusione;
	}

	public String get_sanzPercRidEsclusione() {
		return _sanzPercRidEsclusione;
	}

	public void set_note(String _note) {
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

	public void set_sanzFlgEsclusioneUlteriore(
			String _sanzFlgEsclusioneUlteriore) {
		this._sanzFlgEsclusioneUlteriore = _sanzFlgEsclusioneUlteriore;
	}

	public String get_sanzFlgEsclusioneUlteriore() {
		return _sanzFlgEsclusioneUlteriore;
	}

	public void set_flgAmmControlloTitoliSpeciali(
			String _flgAmmControlloTitoliSpeciali) {
		this._flgAmmControlloTitoliSpeciali = _flgAmmControlloTitoliSpeciali;
	}

	public String get_flgAmmControlloTitoliSpeciali() {
		return _flgAmmControlloTitoliSpeciali;
	}

	public String get_esi_contr_320() {
		return _esi_contr_320;
	}

	public void set_esi_contr_320(String _esi_contr_320) {
		this._esi_contr_320 = _esi_contr_320;
	}

	public String get_perc_rid_320() {
		return _perc_rid_320;
	}

	public void set_perc_rid_320(String _perc_rid_320) {
		this._perc_rid_320 = _perc_rid_320;
	}

	public String get_flg_appl_perc_rid_320() {
		return _flg_appl_perc_rid_320;
	}

	public void set_flg_appl_perc_rid_320(String _flg_appl_perc_rid_320) {
		this._flg_appl_perc_rid_320 = _flg_appl_perc_rid_320;
	}

	public String get_perc_rid_esi_320() {
		return _perc_rid_esi_320;
	}

	public void set_perc_rid_esi_320(String _perc_rid_esi_320) {
		this._perc_rid_esi_320 = _perc_rid_esi_320;
	}

	public String get_flg_2x_perc_rid_320() {
		return _flg_2x_perc_rid_320;
	}

	public void set_flg_2x_perc_rid_320(String _flg_2x_perc_rid_320) {
		this._flg_2x_perc_rid_320 = _flg_2x_perc_rid_320;
	}

	public String get_perc_rid_2x_320() {
		return _perc_rid_2x_320;
	}

	public void set_perc_rid_2x_320(String _perc_rid_2x_320) {
		this._perc_rid_2x_320 = _perc_rid_2x_320;
	}

	public String get_flg_esc_pag_320() {
		return _flg_esc_pag_320;
	}

	public void set_flg_esc_pag_320(String _flg_esc_pag_320) {
		this._flg_esc_pag_320 = _flg_esc_pag_320;
	}

	public String get_perc_rid_esc_pag_320() {
		return _perc_rid_esc_pag_320;
	}

	public void set_perc_rid_esc_pag_320(String _perc_rid_esc_pag_320) {
		this._perc_rid_esc_pag_320 = _perc_rid_esc_pag_320;
	}

	public String get_flg_ult_esc_pag_320() {
		return _flg_ult_esc_pag_320;
	}

	public void set_flg_ult_esc_pag_320(String _flg_ult_esc_pag_320) {
		this._flg_ult_esc_pag_320 = _flg_ult_esc_pag_320;
	}
	
}
