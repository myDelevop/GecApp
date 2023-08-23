package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.EsitoSuperfici;
import it.bz.prov.esiti.util.Utils;

import java.util.Date;

/**
 * Business entity che rappresenta il singolo esito del controllo a superficie
 * 
 * @author bpettazzoni
 *
 */

public class EsitoSuperficiBO {
	
	private int _id;
	private String _cuaa;
	private String _domanda;
	private String _domandaAgea;
	private String _misura;
	private String _campagna;
	private String _intervento;
	private String _sottointervento;
	private String _statoAzione;
	private String _gruppoColtura;
	private String _supDichiarata;
	private String _supAccertata;
	private String _differenza;
	private String _scostPerc;
	private String _sanzioneAnnullata;
	private Date _dataModifica;
	private String _userModifica;
	private Date _dataInserimento;
	private String _userInserimento;
	private String _note;
	private String _cartellino_giallo;
	private String _esito;
	private Date _dataControllo;
	private String _effetto;
	private String _sanzione;
	
	
	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
	
	/**
	 * costruttore
	 */
	public EsitoSuperficiBO(){
		_cuaa = "";
		_domanda = "";
		_domandaAgea = "";
		_misura = "";
		_campagna = "";
		_intervento = "";
		_sottointervento = "";
		_statoAzione = "";
		_gruppoColtura = "";
		_note = "";
		_scostPerc= "";
		_supDichiarata = "";
		_supAccertata = "";
		_differenza = "";
		_sanzioneAnnullata = "";
		_userModifica = "";
		_userInserimento = "";
		_cartellino_giallo = "";
		_esito = "";
		_dataControllo = null;
		_effetto = "";
		_sanzione = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esitoSuperfici
	 */
	public EsitoSuperficiBO(EsitoSuperfici esitoSuperfici){
		_cuaa = esitoSuperfici.get_cuaa();
		_domanda = esitoSuperfici.get_domanda();
		_domandaAgea = esitoSuperfici.get_domandaAgea();
		_misura = esitoSuperfici.get_misura();
		_campagna = esitoSuperfici.get_campagna();
		_intervento = esitoSuperfici.get_intervento();
		_sottointervento = esitoSuperfici.get_sottointervento();
		_statoAzione = esitoSuperfici.get_statoAzione();
		_gruppoColtura = esitoSuperfici.get_gruppoColtura();
		_note = esitoSuperfici.get_note();
		_scostPerc= esitoSuperfici.get_scostPerc();
		_supDichiarata = esitoSuperfici.get_supDichiarata();
		_supAccertata = esitoSuperfici.get_supAccertata();
		_differenza = esitoSuperfici.get_differenza();
		_sanzioneAnnullata = esitoSuperfici.get_sanzioneAnnullata();
		_dataModifica = esitoSuperfici.get_data_modifica();
		_userModifica = esitoSuperfici.get_user_modifica();
		_userInserimento = esitoSuperfici.get_user_inserimento();
		_dataInserimento = esitoSuperfici.get_data_inserimento();
		_cartellino_giallo = esitoSuperfici.get_cartellino_giallo();
		_esito = esitoSuperfici.get_esito();
		_dataControllo = esitoSuperfici.get_dataControllo();
		_effetto = esitoSuperfici.get_effetto();
		_sanzione = esitoSuperfici.get_sanzione();
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoSuperficiBO
	 */
	public EsitoSuperficiBO clone()
	{
		EsitoSuperficiBO esito = new EsitoSuperficiBO();
		esito._campagna = _campagna;
		esito._cuaa = _cuaa;
		esito._differenza = _differenza;
		esito._domanda = _domanda;
		esito._domandaAgea = _domandaAgea;
		esito._misura = _misura;
		esito._gruppoColtura = _gruppoColtura;
		esito._id = _id;
		esito._intervento = _intervento;
		esito._note = _note;
		esito._scostPerc = _scostPerc;
		esito._sottointervento = _sottointervento;
		esito._statoAzione = _statoAzione;
		esito._supAccertata = _supAccertata;
		esito._supDichiarata = _supDichiarata;
		esito._sanzioneAnnullata = _sanzioneAnnullata;
		esito._userInserimento = _userInserimento;
		esito._dataInserimento = _dataInserimento;
		esito._userModifica = _userModifica;
		esito._dataModifica = _dataModifica;
		esito._cartellino_giallo = _cartellino_giallo;
		esito._esito = _esito;
		esito._dataControllo = _dataControllo;
		esito._effetto = _effetto;
		esito._sanzione = _sanzione;
		return esito;
	}
	
	/**
	 * fa rol back dell'oggetto con una version precedente
	 * @param esitoSuperfici
	 */
	public void recovery(EsitoSuperficiBO esito)
	{
		_campagna = esito._campagna;
		_cuaa = esito._cuaa;
		_differenza = esito._differenza;
		_domanda = esito._domanda;
		_domandaAgea = esito._domandaAgea;
		_misura = esito._misura;
		_gruppoColtura = esito._gruppoColtura ;
		_id = esito._id ;
		_intervento = esito._intervento ;
		_note = esito._note ;
		_scostPerc = esito._scostPerc ;
		_sottointervento = esito._sottointervento ;
		_statoAzione = esito._statoAzione ;
		_supAccertata = esito._supAccertata ;
		_supDichiarata = esito._supDichiarata ;
		_sanzioneAnnullata = esito._sanzioneAnnullata;
		_userInserimento = esito._userInserimento;
		_dataInserimento = esito._dataInserimento;
		_userModifica = esito._userModifica;
		_dataModifica = esito._dataModifica;
		_cartellino_giallo = esito._cartellino_giallo;
		_esito = esito._esito;
		_dataControllo = esito._dataControllo;
		_effetto = esito._effetto;
		_sanzione = esito._sanzione;
	}
	
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoSuperfici
	 */
	public EsitoSuperfici getEntity()
	{
		EsitoSuperfici esitoSup = new EsitoSuperfici();
		esitoSup.set_campagna(_campagna);
		esitoSup.set_cuaa(_cuaa);		
		esitoSup.set_domanda(_domanda);
		esitoSup.set_domandaAgea(_domandaAgea);
		esitoSup.set_misura(_misura);
		esitoSup.set_gruppoColtura(_gruppoColtura);
		esitoSup.set_id(_id);
		esitoSup.set_intervento(_intervento);
		esitoSup.set_note(_note);
		esitoSup.set_sottointervento(_sottointervento);
		esitoSup.set_statoAzione(_statoAzione);
		if(_supAccertata != null && !_supAccertata.equals("")) 
			esitoSup.set_supAccertata(Float.parseFloat(_supAccertata));
		if(_supDichiarata != null && !_supDichiarata.equals("")) 
			esitoSup.set_supDichiarata(Float.parseFloat(_supDichiarata));
		if(_differenza != null && !_differenza.equals("")) 
			esitoSup.set_differenza(Float.parseFloat(_differenza));
		if(_scostPerc != null && !_scostPerc.equals("")) 
			esitoSup.set_scostPerc(Float.parseFloat(_scostPerc));
		esitoSup.set_sanzioneAnnullata(_sanzioneAnnullata);
		esitoSup.set_user_inserimento(_userInserimento);
		esitoSup.set_data_inserimento(_dataInserimento);
		esitoSup.set_user_modifica(_userModifica);
		esitoSup.set_data_modifica(_dataModifica);
		esitoSup.set_cartellino_giallo(_cartellino_giallo);
		esitoSup.set_esito(_esito);
		esitoSup.set_dataControllo(_dataControllo);
		esitoSup.set_effetto(_effetto);
		if(_sanzione != null && !_sanzione.equals("")) 
			esitoSup.set_sanzione(Float.parseFloat(_sanzione));
		return esitoSup;
	}
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esitoSup
	 */
	public void setEntity(EsitoSuperfici esitoSup)
	{
		esitoSup.set_campagna(_campagna);
		esitoSup.set_cuaa(_cuaa);
		esitoSup.set_domanda(_domanda);
		esitoSup.set_domandaAgea(_domandaAgea);
		esitoSup.set_misura(_misura);
		esitoSup.set_gruppoColtura(_gruppoColtura);
		esitoSup.set_id(_id);
		esitoSup.set_intervento(_intervento);
		esitoSup.set_note(_note);
		esitoSup.set_sottointervento(_sottointervento);
		esitoSup.set_statoAzione(_statoAzione);
		esitoSup.set_sanzioneAnnullata(_sanzioneAnnullata);
		if(_supAccertata != null && !_supAccertata.equals("")) 
			esitoSup.set_supAccertata(Float.parseFloat(_supAccertata));
		if(_supDichiarata != null && !_supDichiarata.equals("")) 
			esitoSup.set_supDichiarata(Float.parseFloat(_supDichiarata));
		if(_differenza != null && !_differenza.equals("")) 
			esitoSup.set_differenza(Float.parseFloat(_differenza));
		if(_scostPerc != null && !_scostPerc.equals("")) 
			esitoSup.set_scostPerc(Float.parseFloat(_scostPerc));
		esitoSup.set_user_inserimento(_userInserimento);
		esitoSup.set_data_inserimento(_dataInserimento);
		esitoSup.set_user_modifica(_userModifica);
		esitoSup.set_data_modifica(_dataModifica);
		esitoSup.set_cartellino_giallo(_cartellino_giallo);
		esitoSup.set_esito(_esito);
		esitoSup.set_dataControllo(_dataControllo);
		esitoSup.set_effetto(_effetto);
		if(_sanzione != null && !_sanzione.equals("")) 
			esitoSup.set_sanzione(Float.parseFloat(_sanzione));
	}
	
	
	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/
	
	
	public void set_id(int id){
		_id = id;
	}
	
	public int get_id(){
		return _id ;
	}
	
	public void set_cuaa(String cuaa){
		_cuaa = cuaa;
	}
	
	public String get_cuaa(){
		return _cuaa ;
	}
	
	public void set_domanda(String domanda){
		if(domanda == null) domanda = "";
		_domanda = domanda;
	}
	
	public String get_domanda(){
		return _domanda ;
	}
	
	public void set_domandaAgea(String domandaAgea){
		this._domandaAgea = domandaAgea;
	}
	
	public String get_domandaAgea(){
		return _domandaAgea;
	}
	
	public void set_misura(String misura){
		this._misura = misura;
	}
	
	public String get_misura(){
		return this._misura;
	}
	
	public void set_campagna(String campagna){
		if(campagna == null) campagna = "";
		_campagna = campagna;
	}
	
	public String get_campagna(){
		return _campagna ;
	}
	
	public void set_intervento(String intervento){
		if(intervento == null) intervento = "";
		_intervento=intervento;
	}
	
	public String get_intervento(){
		return _intervento ;
	}
	
	public void set_sottointervento(String sottointervento){
		if(sottointervento == null) sottointervento = "";
		_sottointervento = sottointervento;
	}
	
	public String get_sottointervento(){
		return _sottointervento ;
	}
	
	public void set_statoAzione(String statoAzione){
		if(statoAzione == null) statoAzione = "";
		_statoAzione = statoAzione;
	}
	
	public String get_statoAzione(){
		return _statoAzione ;
	}
	
	public void set_gruppoColtura(String gruppoColtura){
		if(gruppoColtura == null) gruppoColtura = "";
		_gruppoColtura = gruppoColtura;
	}
	
	public String get_gruppoColtura(){
		return _gruppoColtura;
	}
	
	public void set_note(String note){
		if(note == null) note = "";
		_note = note;
	}
	
	public String get_note(){
		return _note ;
	}
	
	public void set_supDichiarata(String supDichiarata){
		if(supDichiarata == null) supDichiarata = "";
		_supDichiarata =supDichiarata.replace(",", ".");
	}
	
	public String get_supDichiarata(){
		return _supDichiarata;
	}
	
	public void set_supAccertata(String supAccertata){
		if(supAccertata == null) supAccertata = "";
		_supAccertata = supAccertata.replace(",", ".");
	}
	
	public String get_supAccertata(){
		return _supAccertata;
	}
	
	public void set_differenza(String differenza){
		if(differenza == null) differenza = "";
		_differenza = differenza.replace(",", ".");
	}
	
	public String get_differenza(){
		return _differenza;
	}
	
	public void set_scostPerc(String scostPerc){
		if(scostPerc == null) scostPerc = "";
		_scostPerc = scostPerc.replace(",", "."); 
	}
	
	public String get_scostPerc(){
		return _scostPerc;
	}
	
	
	public void set_sanzioneAnnullata(String _sanzioneAnnullata) {
		if(_sanzioneAnnullata == null) _sanzioneAnnullata = "";
		this._sanzioneAnnullata = _sanzioneAnnullata;
	}

	public String get_sanzioneAnnullata() {
		return _sanzioneAnnullata;
	}
	
	public void set_dataModifica(Date _dataModifica){
		this._dataModifica = _dataModifica;
	}
	
	public Date get_dataModifica(){
		if(_userModifica == null || _userModifica.equals("") ) return _dataInserimento;
		else return _dataModifica;
	}
	
	public String get_dataModificaStr(){
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

	public String get_cartellino_giallo() {
		return _cartellino_giallo;
	}

	public void set_cartellino_giallo(String _cartellino_giallo) {
		this._cartellino_giallo = _cartellino_giallo;
	}

	public String get_esito() {
		return _esito;
	}

	public void set_esito(String _esito) {
		this._esito = _esito;
	}

	public Date get_dataControllo() {
		return _dataControllo;
	}

	public void set_dataControllo(Date _dataControllo) {
		this._dataControllo = _dataControllo;
	}

	public String get_dataControlloStr() {
		return Utils.getDateString(_dataControllo);
	}
	
	public String get_effetto() {
		return _effetto;
	}

	public void set_effetto(String _effetto) {
		this._effetto = _effetto;
	}

	public String get_sanzione() {
		return _sanzione;
	}

	public void set_sanzione(String _sanzione) {
		if(_sanzione == null) _sanzione = "";
		this._sanzione = _sanzione.replace(",", ".");
	}
}

