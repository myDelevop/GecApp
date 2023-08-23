package it.bz.prov.esiti.bobject;


import java.util.Date;
import it.bz.prov.esiti.entity.EsitoImpegniRMFITFER;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta il singolo esito del controllo impegni RM FER e FIT
 * 
 * @author lattana
 *
 */

public class EsitoImpegniRMFITFERBO {

	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _misura;
	private String _esito_oppab;
	private String _stato;
	private String _percRidFIT;
	private String _percRidFER;
	private String _percTotOPPAB;
	private String _percCommissRiesame;
	private String _statoPostCommissRiesame;
	private Date _dataCommissRiesame;
	private String _note;
	private Date _dataInserimento;
	private String _userInserimento;
	private Date _dataModifica;
	private String _userModifica;
	private Date _data_contr;
	private String _esclusione;
	private String _progr_accert_esclusione;
	private String _reiterazione;
	private String _progr_accert_reiteraz;
	private String _portata;
	private String _gravita;
	private String _durata;
	private String _segnalazione;
	private String _portata_note;
	private String _gravita_note;
	private String _durata_note;
	private String _esclusione_note;
	
	
	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoImpegniRMFITFERBO(){
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_misura = "";
		_esito_oppab = "";
		_stato = "";
		_percRidFIT = "";
		_percRidFER = "";
		_percTotOPPAB = "";
		_percCommissRiesame = "";
		_statoPostCommissRiesame = "";
		_note = "";
		_userInserimento = "";
		_userModifica = "";
		_esclusione = "";
		_reiterazione = "";
		_segnalazione = "";
		_progr_accert_esclusione = "";
		_progr_accert_reiteraz = "";
		_portata = "";
		_gravita = "";
		_durata = "";
		_portata_note = "";
		_gravita_note = "";
		_durata_note = "";
		_esclusione_note = "";
		
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esitoImpegniRMFITFER
	 */
	public EsitoImpegniRMFITFERBO(EsitoImpegniRMFITFER esitoImpegniRMFITFER){
		
		_cuaa = esitoImpegniRMFITFER.get_cuaa();
		_domanda = esitoImpegniRMFITFER.get_domanda();
		_campagna = esitoImpegniRMFITFER.get_campagna();
		_misura = esitoImpegniRMFITFER.get_misura();
		_esito_oppab = esitoImpegniRMFITFER.get_esito_oppab();
		_stato = esitoImpegniRMFITFER.get_stato();
		_percRidFIT = esitoImpegniRMFITFER.get_percRidFIT();
		_percRidFER = esitoImpegniRMFITFER.get_percRidFER();
		_percTotOPPAB = esitoImpegniRMFITFER.get_percTotOPPAB();
		_percCommissRiesame = esitoImpegniRMFITFER.get_percCommissRiesame();
		_statoPostCommissRiesame = esitoImpegniRMFITFER.get_statoPostCommissRiesame();
		if(esitoImpegniRMFITFER.get_dataCommissRiesame() != null) 
			_dataCommissRiesame = Utils.getDate(esitoImpegniRMFITFER.get_dataCommissRiesame());
		_note = esitoImpegniRMFITFER.get_note();
		_dataModifica = esitoImpegniRMFITFER.get_data_modifica();
		_userModifica = esitoImpegniRMFITFER.get_user_modifica();
		_dataInserimento = esitoImpegniRMFITFER.get_data_inserimento();
		_userInserimento = esitoImpegniRMFITFER.get_user_inserimento();	
		_data_contr = esitoImpegniRMFITFER.get_data_contr();
		_esclusione = esitoImpegniRMFITFER.get_esclusione();
		_reiterazione = esitoImpegniRMFITFER.get_reiterazione();
		_progr_accert_esclusione = esitoImpegniRMFITFER.get_progr_accert_esclusione();
		_progr_accert_reiteraz = esitoImpegniRMFITFER.get_progr_acc_reiteraz();
		_portata = esitoImpegniRMFITFER.get_portata();
		_gravita = esitoImpegniRMFITFER.get_gravita();
		_durata = esitoImpegniRMFITFER.get_durata();
		_segnalazione = esitoImpegniRMFITFER.get_segnalazione();
		_portata_note = esitoImpegniRMFITFER.get_portata_note();
		_gravita_note = esitoImpegniRMFITFER.get_gravita_note();
		_durata_note = esitoImpegniRMFITFER.get_durata_note();
		_esclusione_note = esitoImpegniRMFITFER.get_esclusione_note();
		
	}
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoImpegniBO
	 */
	public EsitoImpegniRMFITFERBO clone()
	{
		EsitoImpegniRMFITFERBO esitoImpegniRMFITFER = new EsitoImpegniRMFITFERBO();
		
		esitoImpegniRMFITFER.set_cuaa(_cuaa);
		esitoImpegniRMFITFER.set_domanda(_domanda);
		esitoImpegniRMFITFER.set_campagna(_campagna);
		esitoImpegniRMFITFER.set_misura(_misura);
		esitoImpegniRMFITFER.set_esito_oppab(_esito_oppab);
		esitoImpegniRMFITFER.set_stato(_stato);
		esitoImpegniRMFITFER.set_percRidFIT(_percRidFIT);
		esitoImpegniRMFITFER.set_percRidFER(_percRidFER);
		esitoImpegniRMFITFER.set_percTotOPPAB(_percTotOPPAB);
		esitoImpegniRMFITFER.set_percCommissRiesame(_percCommissRiesame);
		esitoImpegniRMFITFER.set_statoPostCommissRiesame(_statoPostCommissRiesame);
		esitoImpegniRMFITFER.set_dataCommissRiesame(_dataCommissRiesame);
		esitoImpegniRMFITFER.set_note(_note);
		esitoImpegniRMFITFER.set_userInserimento(_userInserimento);
		esitoImpegniRMFITFER.set_dataInserimento(_dataInserimento);
		esitoImpegniRMFITFER.set_userModifica(_userModifica);
		esitoImpegniRMFITFER.set_dataModifica(_dataModifica);
		esitoImpegniRMFITFER.set_data_contr(_data_contr);
		esitoImpegniRMFITFER.set_esclusione(_esclusione);
		esitoImpegniRMFITFER.set_progr_accert_esclusione(_progr_accert_esclusione);
		esitoImpegniRMFITFER.set_reiterazione(_reiterazione);
		esitoImpegniRMFITFER.set_progr_accert_reiteraz(_progr_accert_reiteraz);
		esitoImpegniRMFITFER.set_portata(_portata);
		esitoImpegniRMFITFER.set_gravita(_gravita);
		esitoImpegniRMFITFER.set_durata(_durata);
		esitoImpegniRMFITFER.set_segnalazione(_segnalazione);
		esitoImpegniRMFITFER.set_portata_note(_portata_note);
		esitoImpegniRMFITFER.set_gravita_note(_gravita_note);
		esitoImpegniRMFITFER.set_durata_note(_durata_note);
		esitoImpegniRMFITFER.set_esclusione_note(_esclusione_note);
		
		return esitoImpegniRMFITFER;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esitoImpegni
	 */
	public void recovery(EsitoImpegniRMFITFERBO esitoImpegniRMFITFER)
	{
		_cuaa = esitoImpegniRMFITFER.get_cuaa();
		_domanda = esitoImpegniRMFITFER.get_domanda();
		_campagna = "" + esitoImpegniRMFITFER.get_campagna();
		_misura = esitoImpegniRMFITFER.get_misura();
		_esito_oppab = esitoImpegniRMFITFER.get_esito_oppab();
		_stato = esitoImpegniRMFITFER.get_stato();
		_percRidFIT = esitoImpegniRMFITFER.get_percRidFIT();
		_percRidFER = esitoImpegniRMFITFER.get_percRidFER();
		_percTotOPPAB = esitoImpegniRMFITFER.get_percTotOPPAB();
		_percCommissRiesame = esitoImpegniRMFITFER.get_percCommissRiesame();
		_statoPostCommissRiesame = esitoImpegniRMFITFER.get_statoPostCommissRiesame();
		_dataCommissRiesame = esitoImpegniRMFITFER.get_dataCommissRiesame();
		_note = esitoImpegniRMFITFER.get_note();
		_userInserimento = esitoImpegniRMFITFER.get_userInserimento();
		_dataInserimento = esitoImpegniRMFITFER.get_dataInserimento();
		_userModifica = esitoImpegniRMFITFER.get_userModifica();
		_dataModifica = esitoImpegniRMFITFER.get_dataModifica();
		_data_contr = esitoImpegniRMFITFER.get_data_contr();
		_esclusione = esitoImpegniRMFITFER.get_esclusione();
		_progr_accert_esclusione = esitoImpegniRMFITFER.get_progr_accert_esclusione();
		_reiterazione = esitoImpegniRMFITFER.get_reiterazione();
		_progr_accert_reiteraz = esitoImpegniRMFITFER.get_progr_accert_reiteraz();
		_portata = esitoImpegniRMFITFER.get_portata();
		_gravita = esitoImpegniRMFITFER.get_gravita();
		_durata = esitoImpegniRMFITFER.get_durata();
		_segnalazione = esitoImpegniRMFITFER.get_segnalazione();
		_portata_note = esitoImpegniRMFITFER.get_portata_note();
		_gravita_note = esitoImpegniRMFITFER.get_gravita_note();
		_durata_note = esitoImpegniRMFITFER.get_durata_note();
		_esclusione_note = esitoImpegniRMFITFER.get_esclusione_note();
			
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoImpegni
	 */
	public EsitoImpegniRMFITFER getEntity()
	{
		EsitoImpegniRMFITFER esito = new EsitoImpegniRMFITFER();
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_misura(_misura);
		esito.set_esito_oppab(_esito_oppab);
		esito.set_stato(_stato);		
		esito.set_percRidFIT(_percRidFIT);
		esito.set_percRidFER(_percRidFER);
		esito.set_percTotOPPAB(_percTotOPPAB);
		esito.set_percCommissRiesame(_percCommissRiesame);
		esito.set_statoPostCommissRiesame(_statoPostCommissRiesame);
		if(_dataCommissRiesame != null) esito.set_dataCommissRiesame(Utils.getDateString(_dataCommissRiesame));
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_data_contr(_data_contr);
		esito.set_esclusione(_esclusione);
		esito.set_progr_accert_esclusione(_progr_accert_esclusione);
		esito.set_reiterazione(_reiterazione);
		esito.set_progr_acc_reiteraz(_progr_accert_reiteraz);
		esito.set_portata(_portata);
		esito.set_gravita(_gravita);
		esito.set_durata(_durata);
		esito.set_segnalazione(_segnalazione);
		esito.set_portata_note(_portata_note);
		esito.set_gravita_note(_gravita_note);
		esito.set_durata_note(_durata_note);
		esito.set_esclusione_note(_esclusione_note);
		
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoImpegniRMFITFER esito)
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
		esito.set_percCommissRiesame(_percCommissRiesame);
		esito.set_statoPostCommissRiesame(_statoPostCommissRiesame);
		if(_dataCommissRiesame != null) esito.set_dataCommissRiesame(Utils.getDateString(_dataCommissRiesame));
		esito.set_note(_note);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_data_contr(_data_contr);
		esito.set_esclusione(_esclusione);
		esito.set_progr_accert_esclusione(_progr_accert_esclusione);
		esito.set_reiterazione(_reiterazione);
		esito.set_progr_acc_reiteraz(_progr_accert_reiteraz);
		esito.set_portata(_portata);
		esito.set_gravita(_gravita);
		esito.set_durata(_durata);
		esito.set_segnalazione(_segnalazione);
		esito.set_portata_note(_portata_note);
		esito.set_gravita_note(_gravita_note);
		esito.set_durata_note(_durata_note);
		esito.set_esclusione_note(_esclusione_note);
		
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

	public String get_percCommissRiesame() {
		return _percCommissRiesame;
	}

	public void set_percCommissRiesame(String _percCommissRiesame) {
		if(_percCommissRiesame == null) _percCommissRiesame = "";
		this._percCommissRiesame = _percCommissRiesame.replace(",", ".");;
	}


	public void set_statoPostCommissRiesame(String _statoPostCommissRiesame) {
		if(_statoPostCommissRiesame == null) _statoPostCommissRiesame = "";
		this._statoPostCommissRiesame = _statoPostCommissRiesame.trim();
	}

	public String get_statoPostCommissRiesame() {
		return _statoPostCommissRiesame;
	}

	public void set_dataCommissRiesame(Date _dataCommissRiesame) {
		this._dataCommissRiesame = _dataCommissRiesame;
	}

	public Date get_dataCommissRiesame() {
		return _dataCommissRiesame;
	}
	
	public String get_dataCommissRiesameStr() {
		return Utils.getDateString(_dataCommissRiesame);
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

	public String get_esclusione() {
		return _esclusione;
	}

	public void set_esclusione(String _esclusione) {
		this._esclusione = _esclusione;
	}

	public String get_progr_accert_esclusione() {
		return _progr_accert_esclusione;
	}

	public void set_progr_accert_esclusione(String _progr_accert_esclusione) {
		this._progr_accert_esclusione = _progr_accert_esclusione;
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
		this._progr_accert_reiteraz = _progr_accert_reiteraz;
	}

	public String get_portata() {
		return _portata;
	}

	public void set_portata(String _portata) {
		this._portata = _portata;
	}

	public String get_gravita() {
		return _gravita;
	}

	public void set_gravita(String _gravita) {
		this._gravita = _gravita;
	}

	public String get_durata() {
		return _durata;
	}

	public void set_durata(String _durata) {
		this._durata = _durata;
	}

	public String get_segnalazione() {
		return _segnalazione;
	}

	public void set_segnalazione(String _segnalazione) {
		this._segnalazione = _segnalazione;
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
	
}
