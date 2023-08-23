package it.bz.prov.esiti.bobject;

import java.util.Date;

import it.bz.prov.esiti.entity.EsitoImpegniRMFITFERExtra;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta il singolo esito del controllo impegni RM FER e FIT
 * 
 * @author lattana
 *
 */

public class EsitoImpegniRMFITFERExtraBO {

	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _misura;
	private String _esito_oppab;
	private String _stato;
	private String _percRidFIT;
	private String _percRidFER;
	private String _percTotOPPAB;
	private String _note;
	private Date _dataInserimento;
	private String _userInserimento;
	private Date _dataModifica;
	private String _userModifica;
	private Date _data_contr;
	private String _negligenza;
	private String _esclusione;
	private String _reiterazione;
	private String _progr_accert_reiteraz;
	private String _inadempienza_grave;
	private String _portata;
	private String _gravita;
	private String _durata;
	private String _segnalazione;
	private String _approvazione;
	private String _portata_note;
	private String _gravita_note;
	private String _durata_note;
	private String _esclusione_note;
	private String _numero_decreto;
	private Date _data_decreto;
	
	
	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoImpegniRMFITFERExtraBO(){
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
		_userInserimento = "";
		_userModifica = "";
		_negligenza = "";
		_esclusione = "";
		_reiterazione = "";
		_segnalazione = "";
		_approvazione ="";
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
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esitoImpegniRMFITFERExtra
	 */
	public EsitoImpegniRMFITFERExtraBO(EsitoImpegniRMFITFERExtra esitoImpegniRMFITFERExtra){
		
		_cuaa = esitoImpegniRMFITFERExtra.get_cuaa();
		_domanda = esitoImpegniRMFITFERExtra.get_domanda();
		_campagna = esitoImpegniRMFITFERExtra.get_campagna();
		_misura = esitoImpegniRMFITFERExtra.get_misura();
		_esito_oppab = esitoImpegniRMFITFERExtra.get_esito_oppab();
		_stato = esitoImpegniRMFITFERExtra.get_stato();
		_percRidFIT = esitoImpegniRMFITFERExtra.get_percRidFIT();
		_percRidFER = esitoImpegniRMFITFERExtra.get_percRidFER();
		_percTotOPPAB = esitoImpegniRMFITFERExtra.get_percTotOPPAB();
		_note = esitoImpegniRMFITFERExtra.get_note();
		_dataModifica = esitoImpegniRMFITFERExtra.get_data_modifica();
		_userModifica = esitoImpegniRMFITFERExtra.get_user_modifica();
		_dataInserimento = esitoImpegniRMFITFERExtra.get_data_inserimento();
		_userInserimento = esitoImpegniRMFITFERExtra.get_user_inserimento();	
		_data_contr = esitoImpegniRMFITFERExtra.get_data_contr();
		_negligenza = esitoImpegniRMFITFERExtra.get_negligenza();
		_esclusione = esitoImpegniRMFITFERExtra.get_esclusione();
		_reiterazione = esitoImpegniRMFITFERExtra.get_reiterazione();
		_progr_accert_reiteraz = esitoImpegniRMFITFERExtra.get_progr_acc_reiteraz();
		_inadempienza_grave = esitoImpegniRMFITFERExtra.get_inadempienza_grave();
		_portata = esitoImpegniRMFITFERExtra.get_portata();
		_gravita = esitoImpegniRMFITFERExtra.get_gravita();
		_durata = esitoImpegniRMFITFERExtra.get_durata();
		_segnalazione = esitoImpegniRMFITFERExtra.get_segnalazione();
		_approvazione = esitoImpegniRMFITFERExtra.get_approvazione();
		_portata_note = esitoImpegniRMFITFERExtra.get_portata_note();
		_gravita_note = esitoImpegniRMFITFERExtra.get_gravita_note();
		_durata_note = esitoImpegniRMFITFERExtra.get_durata_note();
		_esclusione_note = esitoImpegniRMFITFERExtra.get_esclusione_note();
		_numero_decreto = esitoImpegniRMFITFERExtra.get_numero_decreto();
		_data_decreto = esitoImpegniRMFITFERExtra.get_data_decreto();
		
	}
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoImpegniBO
	 */
	public EsitoImpegniRMFITFERExtraBO clone()
	{
		EsitoImpegniRMFITFERExtraBO esitoImpegniRMFITFERExtra = new EsitoImpegniRMFITFERExtraBO();
		
		esitoImpegniRMFITFERExtra.set_cuaa(_cuaa);
		esitoImpegniRMFITFERExtra.set_domanda(_domanda);
		esitoImpegniRMFITFERExtra.set_campagna(_campagna);
		esitoImpegniRMFITFERExtra.set_misura(_misura);
		esitoImpegniRMFITFERExtra.set_esito_oppab(_esito_oppab);
		esitoImpegniRMFITFERExtra.set_stato(_stato);
		esitoImpegniRMFITFERExtra.set_percRidFIT(_percRidFIT);
		esitoImpegniRMFITFERExtra.set_percRidFER(_percRidFER);
		esitoImpegniRMFITFERExtra.set_percTotOPPAB(_percTotOPPAB);
		esitoImpegniRMFITFERExtra.set_note(_note);
		esitoImpegniRMFITFERExtra.set_userInserimento(_userInserimento);
		esitoImpegniRMFITFERExtra.set_dataInserimento(_dataInserimento);
		esitoImpegniRMFITFERExtra.set_userModifica(_userModifica);
		esitoImpegniRMFITFERExtra.set_dataModifica(_dataModifica);
		esitoImpegniRMFITFERExtra.set_data_contr(_data_contr);
		esitoImpegniRMFITFERExtra.set_negligenza(_negligenza);
		esitoImpegniRMFITFERExtra.set_esclusione(_esclusione);
		esitoImpegniRMFITFERExtra.set_reiterazione(_reiterazione);
		esitoImpegniRMFITFERExtra.set_progr_accert_reiteraz(_progr_accert_reiteraz);
		esitoImpegniRMFITFERExtra.set_inadempienza_grave(_inadempienza_grave);
		esitoImpegniRMFITFERExtra.set_portata(_portata);
		esitoImpegniRMFITFERExtra.set_gravita(_gravita);
		esitoImpegniRMFITFERExtra.set_durata(_durata);
		esitoImpegniRMFITFERExtra.set_segnalazione(_segnalazione);
		esitoImpegniRMFITFERExtra.set_approvazione(_approvazione);
		esitoImpegniRMFITFERExtra.set_portata_note(_portata_note);
		esitoImpegniRMFITFERExtra.set_gravita_note(_gravita_note);
		esitoImpegniRMFITFERExtra.set_durata_note(_durata_note);
		esitoImpegniRMFITFERExtra.set_esclusione_note(_esclusione_note);
		esitoImpegniRMFITFERExtra.set_numero_decreto(_numero_decreto);
		esitoImpegniRMFITFERExtra.set_data_decreto(_data_decreto);
		
		return esitoImpegniRMFITFERExtra;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esitoImpegni
	 */
	public void recovery(EsitoImpegniRMFITFERExtraBO esitoImpegniRMFITFERExtra)
	{
		_cuaa = esitoImpegniRMFITFERExtra.get_cuaa();
		_domanda = esitoImpegniRMFITFERExtra.get_domanda();
		_campagna = "" + esitoImpegniRMFITFERExtra.get_campagna();
		_misura = esitoImpegniRMFITFERExtra.get_misura();
		_esito_oppab = esitoImpegniRMFITFERExtra.get_esito_oppab();
		_stato = esitoImpegniRMFITFERExtra.get_stato();
		_percRidFIT = esitoImpegniRMFITFERExtra.get_percRidFIT();
		_percRidFER = esitoImpegniRMFITFERExtra.get_percRidFER();
		_percTotOPPAB = esitoImpegniRMFITFERExtra.get_percTotOPPAB();
		_note = esitoImpegniRMFITFERExtra.get_note();
		_userInserimento = esitoImpegniRMFITFERExtra.get_userInserimento();
		_dataInserimento = esitoImpegniRMFITFERExtra.get_dataInserimento();
		_userModifica = esitoImpegniRMFITFERExtra.get_userModifica();
		_dataModifica = esitoImpegniRMFITFERExtra.get_dataModifica();
		_data_contr = esitoImpegniRMFITFERExtra.get_data_contr();
		_negligenza = esitoImpegniRMFITFERExtra.get_negligenza();
		_esclusione = esitoImpegniRMFITFERExtra.get_esclusione();
		_reiterazione = esitoImpegniRMFITFERExtra.get_reiterazione();
		_progr_accert_reiteraz = esitoImpegniRMFITFERExtra.get_progr_accert_reiteraz();
		_inadempienza_grave = esitoImpegniRMFITFERExtra.get_inadempienza_grave();
		_portata = esitoImpegniRMFITFERExtra.get_portata();
		_gravita = esitoImpegniRMFITFERExtra.get_gravita();
		_durata = esitoImpegniRMFITFERExtra.get_durata();
		_segnalazione = esitoImpegniRMFITFERExtra.get_segnalazione();
		_approvazione = esitoImpegniRMFITFERExtra.get_approvazione();
		_portata_note = esitoImpegniRMFITFERExtra.get_portata_note();
		_gravita_note = esitoImpegniRMFITFERExtra.get_gravita_note();
		_durata_note = esitoImpegniRMFITFERExtra.get_durata_note();
		_esclusione_note = esitoImpegniRMFITFERExtra.get_esclusione_note();
		_numero_decreto = esitoImpegniRMFITFERExtra.get_numero_decreto();
		_data_decreto = esitoImpegniRMFITFERExtra.get_data_decreto();
			
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoImpegni
	 */
	public EsitoImpegniRMFITFERExtra getEntity()
	{
		EsitoImpegniRMFITFERExtra esito = new EsitoImpegniRMFITFERExtra();
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_misura(_misura);
		esito.set_esito_oppab(_esito_oppab);
		esito.set_stato(_stato);		
		esito.set_percRidFIT(_percRidFIT);
		esito.set_percRidFER(_percRidFER);
		esito.set_percTotOPPAB(_percTotOPPAB);

		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_data_contr(_data_contr);
		esito.set_negligenza(_negligenza);
		esito.set_esclusione(_esclusione);
		esito.set_reiterazione(_reiterazione);
		esito.set_progr_acc_reiteraz(_progr_accert_reiteraz);
		esito.set_inadempienza_grave(_inadempienza_grave);
		esito.set_portata(_portata);
		esito.set_gravita(_gravita);
		esito.set_durata(_durata);
		esito.set_segnalazione(_segnalazione);
		esito.set_approvazione(_approvazione);
		esito.set_portata_note(_portata_note);
		esito.set_gravita_note(_gravita_note);
		esito.set_durata_note(_durata_note);
		esito.set_esclusione_note(_esclusione_note);
		esito.set_numero_decreto(_numero_decreto);
		esito.set_data_decreto(_data_decreto);
		
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoImpegniRMFITFERExtra esito)
	{
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_misura(_misura);
		esito.set_esito_oppab(_esito_oppab);
		esito.set_stato(_stato);
		esito.set_percRidFIT(_percRidFIT);
		esito.set_percRidFER(_percRidFER);
		esito.set_percTotOPPAB(_percTotOPPAB);
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_data_contr(_data_contr);
		esito.set_negligenza(_negligenza);
		esito.set_esclusione(_esclusione);
		esito.set_reiterazione(_reiterazione);
		esito.set_progr_acc_reiteraz(_progr_accert_reiteraz);
		esito.set_inadempienza_grave(_inadempienza_grave);;
		esito.set_portata(_portata);
		esito.set_gravita(_gravita);
		esito.set_durata(_durata);
		esito.set_segnalazione(_segnalazione);
		esito.set_approvazione(_approvazione);
		esito.set_portata_note(_portata_note);
		esito.set_gravita_note(_gravita_note);
		esito.set_durata_note(_durata_note);
		esito.set_esclusione_note(_esclusione_note);
		esito.set_numero_decreto(_numero_decreto);
		esito.set_data_decreto(_data_decreto);
		
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
		_domanda = domanda;
	}
	
	public String get_campagna(){
		return "" + _campagna;
		
	}
	
	public void set_campagna(String campagna){
		if(campagna == null) campagna = "";
		_campagna = campagna.trim();
	}
	
	public String get_misura() {
		return _misura;
	}

	public void set_misura(String _misura) {
		this._misura = _misura;
	}

	public String get_esito_oppab() {
		return _esito_oppab == null ? "": _esito_oppab;
	}

	public void set_esito_oppab(String _esito_oppab) {
		this._esito_oppab = _esito_oppab;
	}
	
	public void set_stato(String _stato) {
		if(_stato == null) _stato = "";
		this._stato = _stato.trim();
	}

	public String get_stato() {
		return _stato;
	}

	public String get_percRidFIT() {
		return _percRidFIT;
	}

	public void set_percRidFIT(String _percRidFIT) {
		this._percRidFIT = _percRidFIT;
	}

	public String get_percRidFER() {
		return _percRidFER;
	}

	public void set_percRidFER(String _percRidFER) {
		this._percRidFER = _percRidFER;
	}

	public String get_percTotOPPAB() {
		return _percTotOPPAB;
	}

	public void set_percTotOPPAB(String _percTotOPPAB) {
		this._percTotOPPAB = _percTotOPPAB;
	}

	public void set_note(String _note) {
		if(_note == null) _note = "";
		this._note = _note.trim();
	}

	public String get_note() {
		return _note;
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

	public Date get_data_contr() {
		return _data_contr;
	}

	public void set_data_contr(Date _data_contr) {
		this._data_contr = _data_contr;
	}

	public String get_data_contrStr() {
		return Utils.getDateString(_data_contr);
	}
	
	public String get_negligenza() {
		return _negligenza;
	}

	public void set_negligenza(String _negligenza) {
		this._negligenza = _negligenza;
	}

	public String get_esclusione() {
		return _esclusione;
	}

	public void set_esclusione(String _esclusione) {
		this._esclusione = _esclusione;
	}

	public String get_reiterazione() {
		return _reiterazione;
	}

	public void set_reiterazione(String _reiterazione) {
		this._reiterazione = _reiterazione;
	}

	public String get_progr_accert_reiteraz() {
		return _progr_accert_reiteraz;
	}

	public void set_progr_accert_reiteraz(String _progr_accert_reiteraz) {
		if(_progr_accert_reiteraz == null || _progr_accert_reiteraz.equalsIgnoreCase("on"))
			_progr_accert_reiteraz="";
		this._progr_accert_reiteraz = _progr_accert_reiteraz;
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
		if(_portata == null || _portata.equalsIgnoreCase("on"))
			_portata="";
		this._portata = _portata;
	}

	public String get_gravita() {
		return _gravita;
	}

	public void set_gravita(String _gravita) {
		if(_gravita == null || _gravita.equalsIgnoreCase("on"))
			_gravita="";
		this._gravita = _gravita;
	}

	public String get_durata() {
		return _durata;
	}

	public void set_durata(String _durata) {
		if(_durata == null || _durata.equalsIgnoreCase("on"))
			_durata="";
		this._durata = _durata;
	}

	public String get_segnalazione() {
		return _segnalazione;
	}

	public void set_segnalazione(String _segnalazione) {
		this._segnalazione = _segnalazione;
	}

	public String get_approvazione() {
		return _approvazione;
	}

	public void set_approvazione(String _approvazione) {
		this._approvazione = _approvazione;
	}

	public String get_portata_note() {
		return _portata_note;
	}

	public void set_portata_note(String _portata_note) {
		this._portata_note = _portata_note;
	}

	public String get_gravita_note() {
		return _gravita_note;
	}

	public void set_gravita_note(String _gravita_note) {
		this._gravita_note = _gravita_note;
	}

	public String get_durata_note() {
		return _durata_note;
	}

	public void set_durata_note(String _durata_note) {
		this._durata_note = _durata_note;
	}

	public String get_esclusione_note() {
		return _esclusione_note;
	}

	public void set_esclusione_note(String _esclusione_note) {
		this._esclusione_note = _esclusione_note;
	}

	public String get_numero_decreto() {
		return _numero_decreto;
	}

	public void set_numero_decreto(String _numero_decreto) {
		this._numero_decreto = _numero_decreto;
	}
	
	public Date get_data_decreto() {
		return _data_decreto;
	}

	public void set_data_decreto(Date _data_decreto) {
		this._data_decreto = _data_decreto;
	}
	
	public String get_data_decretoString(){
		return Utils.getDateString(_data_decreto);
	}
	
}
