package it.bz.prov.esiti.bobject;


import java.util.Date;

import it.bz.prov.esiti.entity.EsitoArt68;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta il singolo esito del controllo impegni
 * 
 * @author bpettazzoni
 *
 */

public class EsitoArt68BO {

	private String _intervento;
	private String _esito;
	private float _impRiduz;
	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _note;
	private Date _dataIspezione;
	private String _stato;
	private Date _dataModifica;
	private String _userModifica;
	private Date _dataInserimento;
	private String _userInserimento;	

	
	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoArt68BO(){
		_intervento = "";
		_esito = "";
		_impRiduz = 0;
		_cuaa = "";
		_domanda = "";
		_stato = "";
		_note = "";
		_campagna = "";
		_userInserimento = "";
		_userModifica = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esitoImpegni
	 */
	public EsitoArt68BO(EsitoArt68 esitoArt68){
		_intervento = esitoArt68.get_intervento();
		_esito = esitoArt68.get_esito();
		_impRiduz = esitoArt68.get_impRiduz();
		_cuaa = esitoArt68.get_cuaa();
		_domanda = esitoArt68.get_domanda();
		_stato = esitoArt68.get_stato();
		if(esitoArt68.get_dataIspezione() != null) _dataIspezione = Utils.getDate(esitoArt68.get_dataIspezione());
		_note = esitoArt68.get_note();
		_campagna = esitoArt68.get_campagna();
		_dataModifica = esitoArt68.get_data_modifica();
		_userModifica = esitoArt68.get_user_modifica();
		_dataInserimento = esitoArt68.get_data_inserimento();
		_userInserimento = esitoArt68.get_user_inserimento();		
	
	}
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoImpegniBO
	 */
	public EsitoArt68BO clone()
	{
		EsitoArt68BO esitoArt68 = new EsitoArt68BO();
		esitoArt68.set_campagna(_campagna);
		esitoArt68.set_cuaa(_cuaa);
		esitoArt68.set_dataIspezione(_dataIspezione);
		esitoArt68.set_domanda(_domanda);
		esitoArt68.set_esito(_esito);
		esitoArt68.set_intervento(_intervento);
		esitoArt68.set_note(_note);
		esitoArt68.set_stato(_stato);
		esitoArt68.set_userInserimento(_userInserimento);
		esitoArt68.set_dataInserimento(_dataInserimento);
		esitoArt68.set_userModifica(_userModifica);
		esitoArt68.set_dataModifica(_dataModifica);
		return esitoArt68;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esitoImpegni
	 */
	public void recovery(EsitoArt68BO esitoArt68)
	{
		_campagna = "" + esitoArt68.get_campagna();
		_cuaa = esitoArt68.get_cuaa();
		_dataIspezione = esitoArt68.get_dataIspezione();
		_domanda = esitoArt68.get_domanda();
		_esito = esitoArt68.get_esito();
		_intervento = esitoArt68.get_intervento();
		_note = esitoArt68.get_note();
		_impRiduz = esitoArt68.get_impRiduz();
		_stato = esitoArt68.get_stato();
		_userInserimento = esitoArt68.get_userInserimento();
		_dataInserimento = esitoArt68.get_dataInserimento();
		_userModifica = esitoArt68.get_userModifica();
		_dataModifica = esitoArt68.get_dataModifica();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoImpegni
	 */
	public EsitoArt68 getEntity()
	{
		EsitoArt68 esito = new EsitoArt68();
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		if(_dataIspezione != null) esito.set_dataIspezione(Utils.getDateString(_dataIspezione));
		esito.set_domanda(_domanda);
		esito.set_esito(_esito);
		esito.set_intervento(_intervento);
		esito.set_note(_note);
		esito.set_impRiduz(_impRiduz);
		esito.set_stato(_stato);
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
	public void setEntity(EsitoArt68 esito)
	{
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		if(_dataIspezione != null) esito.set_dataIspezione(Utils.getDateString(_dataIspezione));
		esito.set_domanda(_domanda);
		esito.set_esito(_esito);
		esito.set_intervento(_intervento);
		esito.set_note(_note);
		esito.set_impRiduz(_impRiduz);
		esito.set_stato(_stato);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
	}
	
	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/

	public String get_intervento(){
		return _intervento;
	}
	
	public void set_intervento(String intervento){
		if(intervento == null) intervento = "";
		_intervento = intervento.trim();
	}
	
	public String get_esito(){
		return _esito;
	}
	
	public void set_esito(String esito){
		if(esito == null) esito = "";
		_esito = esito.trim();
	}
	
	public float get_impRiduz(){
		return _impRiduz;
	}
	
	public void set_impRiduz(float impRiduz){
		_impRiduz = impRiduz;
	}
	
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

	public void set_note(String _note) {
		if(_note == null) _note = "";
		this._note = _note.trim();
	}

	public String get_note() {
		return _note;
	}

	public void set_dataIspezione(Date _dataIspezione) {
		this._dataIspezione = _dataIspezione;
	}

	public Date get_dataIspezione() {
		return _dataIspezione;
	}
	
	public String get_dataIspezioneStr() {
		return Utils.getDateString(_dataIspezione);
	}

	public void set_stato(String _stato) {
		if(_stato == null) _stato = "";
		this._stato = _stato.trim();
	}

	public String get_stato() {
		return _stato;
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

}
