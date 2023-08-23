package it.bz.prov.esiti.bobject;


import java.util.Date;



import it.bz.prov.esiti.entity.EsitoZootecniaStato;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta il riassunto del singolo esito del controllo di zootecnia
 * 
 * @author bpettazzoni
 *
 */

public class EsitoZootecniaStatoBO {

	private String _cuaa;
	private String _domanda;
	private String _campagna;	
	private String _contrAmmBovini;
	private String _esitoAmmBovini;
	private String _contrAmmOvicaprini;
	private String _esitoAmmOvicaprini;
	private String _contrCond;	
	private String _personaPresente;
	private String _personaPresenteRuolo;
	private String _personaPresenteDocumento;
	private String _documentazioneControllo;
	private String _flgRegistroBDN;
	private String _flgFotoRegistroStalla;
	private String _flgAltro;
	private String _note;
	private String _flgFirmaProduttore;
	private String _flgFirmaControllore;		
	private String _nomeControllore;
	private Date _dataControllo; // da aggiungere nelle funzioni di clone, ecc.. 
	private Date _dataModifica;
	private String _userModifica;
	private Date _dataInserimento;
	private String _userInserimento;
	private String _flg310;
	private String _flg311;
	private String _flg313;
	private String _flg315;
	private String _flg316;
	private String _flg318;
	private String _flg320;
	private String _flag322;
	private String _statoCompilazione;
	
	
	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoZootecniaStatoBO(){
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_contrAmmBovini = "";
		_contrAmmOvicaprini = "";
		_contrCond = "";
		_esitoAmmBovini = "";
		_esitoAmmOvicaprini = "";
		_personaPresente = "";
		_personaPresenteDocumento = "";
		_personaPresenteRuolo = "";
		_documentazioneControllo = "";
		_flgRegistroBDN = "";
		_flgFotoRegistroStalla = "";
		_flgAltro = "";
		_note = "";
		_flgFirmaProduttore = "";
		_flgFirmaControllore = "";
		_nomeControllore = "";
		_userInserimento = "";
		_userModifica = "";
		_flg310 = "";
		_flg311 = "";
		_flg313 = "";
		_flg315 = "";
		_flg316 = "";
		_flg318 = "";
		_flg320 = "";
		_flag322 = "";
		_statoCompilazione="";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esitoImpegni
	 */
	public EsitoZootecniaStatoBO(EsitoZootecniaStato esito){
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_campagna = esito.get_campagna();
		_contrAmmBovini = esito.get_contrAmmBovini();
		_contrAmmOvicaprini = esito.get_contrAmmOvicaprini();
		_contrCond = esito.get_contrCond();
		_esitoAmmBovini = esito.get_esitoAmmBovini();
		_esitoAmmOvicaprini = esito.get_esitoAmmOvicaprini();
		_personaPresente = esito.get_personaPresente();
		_personaPresenteDocumento = esito.get_personaPresenteDocumento();
		_personaPresenteRuolo = esito.get_personaPresenteRuolo();
		_documentazioneControllo = esito.get_documentazioneControllo();
		if(esito.get_flgRegistroBDN() != null) _flgRegistroBDN = esito.get_flgRegistroBDN();
		if(esito.get_flgFotoRegistroStalla() != null) _flgFotoRegistroStalla = esito.get_flgFotoRegistroStalla();
		if(esito.get_flgAltro() != null) _flgAltro = esito.get_flgAltro();
		_note = esito.get_note();
		if(esito.get_flgFirmaProduttore() != null) _flgFirmaProduttore = esito.get_flgFirmaProduttore();
		if(esito.get_flgFirmaControllore() != null) _flgFirmaControllore = esito.get_flgFirmaControllore();		
		_nomeControllore = esito.get_nomeControllore();
		if(esito.get_dataControllo() != null) _dataControllo = esito.get_dataControllo();
		_dataModifica = esito.get_data_modifica();
		_userModifica = esito.get_user_modifica();
		_dataInserimento = esito.get_data_inserimento();
		_userInserimento = esito.get_user_inserimento();
		_statoCompilazione= esito.get_statoCompilazione();
		// non vengono settati i flag degli interventi perchè non provengono dall'entity
	}
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoImpegniBO
	 */
	public EsitoZootecniaStatoBO clone()
	{
		EsitoZootecniaStatoBO esito = new EsitoZootecniaStatoBO();
		esito.set_campagna(_campagna);
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_contrAmmBovini(_contrAmmBovini);
		esito.set_contrAmmOvicaprini(_contrAmmOvicaprini);
		esito.set_contrCond(_contrCond);
		esito.set_esitoAmmBovini(_esitoAmmBovini);
		esito.set_esitoAmmOvicaprini(_esitoAmmOvicaprini);
		esito.set_personaPresente(_personaPresente);
		esito.set_personaPresenteDocumento(_personaPresenteDocumento);
		esito.set_personaPresenteRuolo(_personaPresenteRuolo);		
		esito.set_documentazioneControllo(_documentazioneControllo);
		esito.set_flgRegistroBDN(_flgRegistroBDN);
		esito.set_flgFotoRegistroStalla(_flgFotoRegistroStalla);
		esito.set_flgAltro(_flgAltro);
		esito.set_note(_note);
		esito.set_flgFirmaProduttore(_flgFirmaProduttore);
		esito.set_flgFirmaControllore(_flgFirmaControllore);	
		if(_dataControllo != null) esito.set_dataControllo(_dataControllo);
		esito.set_nomeControllore(_nomeControllore);
		esito.set_userInserimento(_userInserimento);
		esito.set_dataInserimento(_dataInserimento);
		esito.set_userModifica(_userModifica);
		esito.set_dataModifica(_dataModifica);
		esito.set_flg310(_flg310);
		esito.set_flg311(_flg311);
		esito.set_flg313(_flg313);
		esito.set_flg315(_flg315);
		esito.set_flg316(_flg316);
		esito.set_flg318(_flg318);
		esito.set_flg320(_flg320);
		esito.set_flag322(_flag322);
		esito.set_statoCompilazione(_statoCompilazione);
		return esito;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esitoImpegni
	 */
	public void recovery(EsitoZootecniaStatoBO esito)
	{
		_campagna = "" + esito.get_campagna();
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_contrAmmBovini = esito.get_contrAmmBovini();
		_contrAmmOvicaprini = esito.get_contrAmmOvicaprini();
		_contrCond = esito.get_contrCond();
		_esitoAmmBovini = esito.get_esitoAmmBovini();
		_esitoAmmOvicaprini = esito.get_esitoAmmOvicaprini();
		_personaPresente = esito.get_personaPresente();
		_personaPresenteDocumento = esito.get_personaPresenteDocumento();
		_personaPresenteRuolo = esito.get_personaPresenteRuolo();
		_documentazioneControllo = esito.get_documentazioneControllo();
		if(esito.get_flgRegistroBDN() != null) _flgRegistroBDN = esito.get_flgRegistroBDN();
		if(esito.get_flgFotoRegistroStalla() != null) _flgFotoRegistroStalla = esito.get_flgFotoRegistroStalla();
		if(esito.get_flgAltro() != null) _flgAltro = esito.get_flgAltro();
		_note = esito.get_note();
		if(esito.get_flgFirmaProduttore() != null) _flgFirmaProduttore = esito.get_flgFirmaProduttore();
		if(esito.get_flgFirmaControllore() != null) _flgFirmaControllore = esito.get_flgFirmaControllore();		
		_nomeControllore = esito.get_nomeControllore();
		if(esito.get_dataControllo() != null) _dataControllo = esito.get_dataControllo();		
		_userInserimento = esito.get_userInserimento();
		_dataInserimento = esito.get_dataInserimento();
		_userModifica = esito.get_userModifica();
		_dataModifica = esito.get_dataModifica();
		_flg310 = esito.get_flg310();
		_flg311 = esito.get_flg311();
		_flg313 = esito.get_flg313();
		_flg315 = esito.get_flg315();
		_flg316 = esito.get_flg316();
		_flg318 = esito.get_flg318();
		_flg320 = esito.get_flg320();
		_flag322 = esito.get_flag322();
		_statoCompilazione= esito.get_statoCompilazione();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoZootecniaStato
	 */
	public EsitoZootecniaStato getEntity()
	{
		EsitoZootecniaStato esito = new EsitoZootecniaStato();
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_contrAmmBovini(_contrAmmBovini);
		esito.set_contrAmmOvicaprini(_contrAmmOvicaprini);
		esito.set_contrCond(_contrCond);
		esito.set_esitoAmmBovini(_esitoAmmBovini);
		esito.set_esitoAmmOvicaprini(_esitoAmmOvicaprini);	
		esito.set_personaPresente(_personaPresente);
		esito.set_personaPresenteDocumento(_personaPresenteDocumento);
		esito.set_personaPresenteRuolo(_personaPresenteRuolo);
		esito.set_documentazioneControllo(_documentazioneControllo);
		esito.set_flgRegistroBDN(_flgRegistroBDN);
		esito.set_flgFotoRegistroStalla(_flgFotoRegistroStalla);
		esito.set_flgAltro(_flgAltro);
		esito.set_note(_note);
		esito.set_flgFirmaProduttore(_flgFirmaProduttore);
		esito.set_flgFirmaControllore(_flgFirmaControllore);
		if(_dataControllo != null) esito.set_dataControllo(_dataControllo);
		esito.set_nomeControllore(_nomeControllore);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_statoCompilazione(_statoCompilazione);
		// non viene impostata la parte dei flag sugli interventi perchè lato entity non servono
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoZootecniaStato esito)
	{
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		esito.set_contrAmmBovini(_contrAmmBovini);
		esito.set_contrAmmOvicaprini(_contrAmmOvicaprini);
		esito.set_contrCond(_contrCond);
		esito.set_esitoAmmBovini(_esitoAmmBovini);
		esito.set_esitoAmmOvicaprini(_esitoAmmOvicaprini);
		esito.set_personaPresente(_personaPresente);
		esito.set_personaPresenteDocumento(_personaPresenteDocumento);
		esito.set_personaPresenteRuolo(_personaPresenteRuolo);
		esito.set_documentazioneControllo(_documentazioneControllo);
		esito.set_flgRegistroBDN(_flgRegistroBDN);
		esito.set_flgFotoRegistroStalla(_flgFotoRegistroStalla);
		esito.set_flgAltro(_flgAltro);
		esito.set_note(_note);
		esito.set_flgFirmaProduttore(_flgFirmaProduttore);
		esito.set_flgFirmaControllore(_flgFirmaControllore);
		if(_dataControllo != null) esito.set_dataControllo(_dataControllo);
		esito.set_nomeControllore(_nomeControllore);
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_statoCompilazione(_statoCompilazione);
		// non viene impostata la parte dei flag sugli interventi perchè lato entity non servono
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

	public void set_contrAmmBovini(String _contrAmmBovini) {
		this._contrAmmBovini = _contrAmmBovini;
	}

	public String get_contrAmmBovini() {
		return _contrAmmBovini;
	}

	public void set_esitoAmmBovini(String _esitoAmmBovini) {
		this._esitoAmmBovini = _esitoAmmBovini;
	}

	public String get_esitoAmmBovini() {
		return _esitoAmmBovini;
	}

	public void set_contrAmmOvicaprini(String _contrAmmOvicaprini) {
		this._contrAmmOvicaprini = _contrAmmOvicaprini;
	}

	public String get_contrAmmOvicaprini() {
		return _contrAmmOvicaprini;
	}

	public void set_esitoAmmOvicaprini(String _esitoAmmOvicaprini) {
		this._esitoAmmOvicaprini = _esitoAmmOvicaprini;
	}

	public String get_esitoAmmOvicaprini() {
		return _esitoAmmOvicaprini;
	}

	public void set_contrCond(String _contrCond) {
		this._contrCond = _contrCond;
	}

	public String get_contrCond() {
		return _contrCond;
	}

	public void set_personaPresente(String _personaPresente) {
		this._personaPresente = _personaPresente;
	}

	public String get_personaPresente() {
		return _personaPresente;
	}

	public void set_personaPresenteRuolo(String _personaPresenteRuolo) {
		this._personaPresenteRuolo = _personaPresenteRuolo;
	}

	public String get_personaPresenteRuolo() {
		return _personaPresenteRuolo;
	}

	public void set_personaPresenteDocumento(String _personaPresenteDocumento) {
		this._personaPresenteDocumento = _personaPresenteDocumento;
	}

	public String get_personaPresenteDocumento() {
		return _personaPresenteDocumento;
	}

	public void set_dataControllo(Date _dataControllo) {
		this._dataControllo = _dataControllo;
	}

	public Date get_dataControllo() {
		return _dataControllo;
	}
	
	public String get_dataControlloStr() {
		return Utils.getDateString(_dataControllo);
	}

	public void set_documentazioneControllo(String _documentazioneControllo) {
		this._documentazioneControllo = _documentazioneControllo;
	}

	public String get_documentazioneControllo() {
		return _documentazioneControllo;
	}

	public void set_flgRegistroBDN(String _flgRegistroBDN) {
		this._flgRegistroBDN = _flgRegistroBDN;
	}

	public String get_flgRegistroBDN() {
		return _flgRegistroBDN;
	}

	public void set_flgFotoRegistroStalla(String _flgFotoRegistroStalla) {
		this._flgFotoRegistroStalla = _flgFotoRegistroStalla;
	}

	public String get_flgFotoRegistroStalla() {
		return _flgFotoRegistroStalla;
	}

	public void set_flgAltro(String _flgAltro) {
		this._flgAltro = _flgAltro;
	}

	public String get_flgAltro() {
		return _flgAltro;
	}

	public void set_note(String _note) {
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

	public void set_flgFirmaProduttore(String _flgFirmaProduttore) {		
		this._flgFirmaProduttore = _flgFirmaProduttore;
	}

	public String get_flgFirmaProduttore() {
		return _flgFirmaProduttore;
	}

	public void set_flgFirmaControllore(String _flgFirmaControllore) {
		this._flgFirmaControllore = _flgFirmaControllore;
	}

	public String get_flgFirmaControllore() {
		return _flgFirmaControllore;
	}

	public void set_nomeControllore(String _nomeControllore) {
		this._nomeControllore = _nomeControllore;
	}

	public String get_nomeControllore() {
		return _nomeControllore;
	}

	public void set_statoCompilazione(String _statoCompilazione) {
		this._statoCompilazione = _statoCompilazione;
	}

	public String get_statoCompilazione() {
		return _statoCompilazione;
	}

	public String get_flg310() {
		return _flg310;
	}

	public void set_flg310(String _flg310) {
		this._flg310 = _flg310;
	}

	public String get_flg311() {
		return _flg311;
	}

	public void set_flg311(String _flg311) {
		this._flg311 = _flg311;
	}

	public String get_flg313() {
		return _flg313;
	}

	public void set_flg313(String _flg313) {
		this._flg313 = _flg313;
	}

	public String get_flg315() {
		return _flg315;
	}

	public void set_flg315(String _flg315) {
		this._flg315 = _flg315;
	}

	public String get_flg316() {
		return _flg316;
	}

	public void set_flg316(String _flg316) {
		this._flg316 = _flg316;
	}

	public String get_flg318() {
		return _flg318;
	}

	public void set_flg318(String _flg318) {
		this._flg318 = _flg318;
	}

	public String get_flag322() {
		return _flag322;
	}

	public void set_flag322(String _flag322) {
		this._flag322 = _flag322;
	}

	public String get_flg320() {
		return _flg320;
	}

	public void set_flg320(String _flg320) {
		this._flg320 = _flg320;
	}	
	
}
