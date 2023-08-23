package it.bz.prov.esiti.bobject;

import java.util.Date;
import it.bz.prov.esiti.entity.EsitoCondizionalita;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta il singolo esito di condizionalità per azienda
 * 
 * @author bpettazzoni
 *
 */
public class EsitoCondizionalitaBO {
	
	private String _cuaa;
	private String _campagna;
//	private Date _dataCampione;
	private Date _dataControllo;
	private String _esitoFinale;
	private String _ridPercDU;
	private String _ridPercPSR;
	private String _stato;
	private String _note;
	private String _userCreazione;
	private Date _dataCreazione;
	private String _userModifica;
	private Date _dataModifica;
	private String _numero_decreto;
	private Date _data_decreto;
	private String _note_decreto;
	
	/**************************************************************
	 * 			COSTRUTTORI
	 **************************************************************/
	
	/**
	 * costruttore
	 */
	public EsitoCondizionalitaBO()
	{
		_cuaa = "";
		_campagna = "";
		_esitoFinale = "";
		_stato = "";
		_note = "";
		_ridPercDU = "";
		_ridPercPSR = "";
		_userModifica = "";
		_userCreazione = "";
		_numero_decreto="";
		_note_decreto= "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esitoCondizionalita
	 */
	public EsitoCondizionalitaBO(EsitoCondizionalita esitoCondizionalita)
	{
		_cuaa = esitoCondizionalita.get_cuaa();
		_campagna = esitoCondizionalita.get_campagna();
//		if(esitoCondizionalita.get_dataCampione() != null) 
//			_dataCampione = Utils.getDate(esitoCondizionalita.get_dataCampione());
		if(esitoCondizionalita.get_dataControllo() != null) 
			_dataControllo = Utils.getDate(esitoCondizionalita.get_dataControllo());
		_esitoFinale = esitoCondizionalita.get_esitoFinale();
		_stato = esitoCondizionalita.get_stato();
		_note = esitoCondizionalita.get_note();
		_ridPercDU = esitoCondizionalita.get_ridPercDU();
		_ridPercPSR = esitoCondizionalita.get_ridPercPSR();
		_userCreazione=esitoCondizionalita.get_user_inserimento();
		_dataCreazione = esitoCondizionalita.get_data_inserimento();
		_userModifica=esitoCondizionalita.get_user_modifica();
		_dataModifica = esitoCondizionalita.get_data_modifica();
		_numero_decreto= esitoCondizionalita.get_numero_decreto();
		_note_decreto=esitoCondizionalita.get_note_decreto();
		_data_decreto = esitoCondizionalita.get_data_decreto();
		
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/

	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoCondizionalitaBO
	 */
	public EsitoCondizionalitaBO clone()
	{
		EsitoCondizionalitaBO esito = new EsitoCondizionalitaBO();
		esito.set_campagna(_campagna);
		esito.set_cuaa(_cuaa);
//		esito.set_dataCampione(_dataCampione);
		esito.set_dataControllo(_dataControllo);
		esito.set_esitoFinale(_esitoFinale);
		esito.set_note(_note);
		esito.set_ridPercDU(_ridPercDU);
		esito.set_ridPercPSR(_ridPercPSR);
		esito.set_stato(_stato);
		esito.set_userCreazione(_userCreazione);
		esito.set_dataCreazione(_dataCreazione);
		esito.set_userModifica(_userModifica);
		esito.set_dataModifica(_dataModifica);
		esito.set_numero_decreto(_numero_decreto);
		esito.set_note_decreto(_note_decreto);
		esito.set_data_decreto(_data_decreto);
		return esito;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esitoCond
	 */
	public void recovery(EsitoCondizionalitaBO esitoCond)
	{
		_campagna = esitoCond.get_campagna();
		_cuaa = esitoCond.get_cuaa();
//		_dataCampione = esitoCond.get_dataCampione();
		_dataControllo = esitoCond.get_dataControllo();
		_esitoFinale = esitoCond.get_esitoFinale();
		_note = esitoCond.get_note();
		_ridPercDU = esitoCond.get_ridPercDU();
		_ridPercPSR = esitoCond.get_ridPercPSR();
		_stato = esitoCond.get_stato();
		_userCreazione= esitoCond.get_userCreazione();
		_dataCreazione = esitoCond.get_dataCreazione();
		_userModifica= esitoCond.get_userModifica();
		_dataModifica = esitoCond.get_dataModifica();
		_numero_decreto= esitoCond.get_numero_decreto();
		_data_decreto = esitoCond.get_data_decreto();
		_note_decreto=esitoCond.get_note_decreto();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoCondizionalita
	 */
	public EsitoCondizionalita getEntity()
	{
		EsitoCondizionalita esito = new EsitoCondizionalita();
		esito.set_campagna(_campagna);
		esito.set_cuaa(_cuaa);
//		esito.set_dataCampione(Utils.getDateString(_dataCampione));
		esito.set_dataControllo(Utils.getDateString(_dataControllo));
		esito.set_esitoFinale(_esitoFinale);
		esito.set_note(_note);
		esito.set_ridPercDU(_ridPercDU);
		esito.set_ridPercPSR(_ridPercPSR);
		esito.set_stato(_stato);
		esito.set_user_inserimento(_userCreazione);
		esito.set_user_modifica(_userModifica);
		esito.set_data_inserimento(_dataCreazione);
		esito.set_data_modifica(_dataModifica);
		esito.set_numero_decreto(_numero_decreto);
		esito.set_note_decreto(_note_decreto);
		esito.set_data_decreto(_data_decreto);
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoCondizionalita esito)
	{
		esito.set_campagna(_campagna);
		esito.set_cuaa(_cuaa);
//		esito.set_dataCampione(Utils.getDateString(_dataCampione));
		esito.set_dataControllo(Utils.getDateString(_dataControllo));
		esito.set_esitoFinale(_esitoFinale);
		esito.set_note(_note);
		esito.set_ridPercDU(_ridPercDU);
		esito.set_ridPercPSR(_ridPercPSR);
		esito.set_stato(_stato);
		esito.set_user_inserimento(_userCreazione);
		esito.set_data_inserimento(_dataCreazione);
		esito.set_user_inserimento(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_numero_decreto(_numero_decreto);
		esito.set_note_decreto(_note_decreto);
		esito.set_data_decreto(_data_decreto);
	}
	
	/**************************************************************
	 * 			GETTER E SETTER
	 **************************************************************/
	
	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa.trim();
	}
	public String get_cuaa() {
		return _cuaa;
	}
	public void set_campagna(String _campagna) {
		if(_campagna == null) _campagna = "";
		this._campagna = _campagna.trim();
	}
	public String get_campagna() {
		return _campagna;
	}
//	public void set_dataCampione(Date _dataCampione) {
//		this._dataCampione = _dataCampione;
//	}
//	public Date get_dataCampione() {
//		return _dataCampione;
//	}
//	public String get_dataCampioneStr() {
//		return Utils.getDateString(_dataCampione);
//	}
	public void set_dataControllo(Date _dataControllo) {
		this._dataControllo = _dataControllo;
	}
	public Date get_dataControllo() {
		return _dataControllo;
	}
	public String get_dataControlloStr() {
		return Utils.getDateString(_dataControllo);
	}
	public void set_esitoFinale(String _esitoFinale) {
		if(_esitoFinale == null) _esitoFinale = "";
		this._esitoFinale = _esitoFinale.trim();
	}
	public String get_esitoFinale() {
		return _esitoFinale;
	}
	public void set_stato(String _stato) {
		if(_stato == null) _stato = "";
		this._stato = _stato.trim();
	}
	public String get_stato() {
		return _stato;
	}
	public void set_note(String _note) {
		if(_note == null) _note = "";
		this._note = _note.trim();
	}
	public String get_note() {
		return _note;
	}

	public void set_ridPercDU(String _ridPercDU) {
		if(_ridPercDU == null) _ridPercDU = "";
		this._ridPercDU = _ridPercDU.replace(",", ".");
	}


	public String get_ridPercDU() {
		return _ridPercDU;
	}


	public void set_ridPercPSR(String _ridPercPSR) {
		if(_ridPercPSR == null) _ridPercPSR = "";
		this._ridPercPSR = _ridPercPSR.replace(",", ".");
	}


	public String get_ridPercPSR() {
		return _ridPercPSR;
	}
	
	public void set_userModifica(String _userModifica) {
		this._userModifica = _userModifica;
	}

	public String get_userModifica() {
		if( _userModifica == null || _userModifica.equals("")) return _userCreazione;
		else return _userModifica;
	}

	public void set_dataModifica(Date _dataModifica) {
		this._dataModifica = _dataModifica;
	}

	public Date get_dataModifica() {
		if(_userModifica == null || _userModifica.equals("") ) return _dataCreazione;
		else return _dataModifica;
	}
	
	public String get_dataModificaStr() {
		if(_userModifica == null || _userModifica.equals("") ) return Utils.getDateString(_dataCreazione);
		else return Utils.getDateString(_dataModifica);
	}

	public void set_userCreazione(String _userCreazione) {
		this._userCreazione = _userCreazione;
	}

	public String get_userCreazione() {
		return _userCreazione;
	}

	public void set_dataCreazione(Date _dataCreazione) {
		this._dataCreazione = _dataCreazione;
	}

	public Date get_dataCreazione() {
		return _dataCreazione;
	}
	
	public String get_dataCreazioneStr() {
		return Utils.getDateString(_dataCreazione);
	}

	public void set_numero_decreto(String _numero_decreto) {
		this._numero_decreto = _numero_decreto;
	}

	public String get_numero_decreto() {
		return _numero_decreto;
	}

	public void set_data_decreto(Date _data_decreto) {
		this._data_decreto = _data_decreto;
	}

	public Date get_data_decreto() {
		return _data_decreto;
	}
	
	public String get_data_decretoStr() {
		return Utils.getDateString(_data_decreto);
	}

	public void set_note_decreto(String _note_decreto) {
		this._note_decreto = _note_decreto;
	}

	public String get_note_decreto() {
		return _note_decreto;
	}	
	
	
}
