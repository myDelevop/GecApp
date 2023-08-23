package it.bz.prov.esiti.bobject;


import java.util.Date;

import it.bz.prov.esiti.entity.EsitoImpegni;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta il singolo esito del controllo impegni
 * 
 * @author bpettazzoni
 *
 */

public class EsitoImpegniBO {

	private int _id;
	private String _intervento;
	private String _sottointervento;
	private String _esito;
	private String _percRid;
	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _note;
	private Date _dataControllo;
	private String _stato;
	private String _inadempienza_grave;
	private String _reiterazione;
	private String _progrReiterazione;
//	private String _caricoAlpeggio;
	private String _percRidCommissRiesame;
	private String _statoPostCommissRiesame;
	private Date _dataCommissRiesame;
	private Date _dataModifica;
	private String _userModifica;
	private Date _dataInserimento;
	private String _userInserimento;	
	private String _esitoPremioTrasporto;
	private String _percRidPremioTrasporto;
	private String _esitoRinunciaInsilato;
	private String _importoRidRinunciaInsilato;
	private String _controllo_estivo;
	private String _perc_rid_carico_bestiame;
	private String _esito_trasformazione_latte;
	private String _segnalazione;


	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoImpegniBO(){
		_intervento = "";
		_sottointervento = "";
		_esito = "";
		_percRid = "";
		_cuaa = "";
		_domanda = "";
		_stato = "";
		_inadempienza_grave = "";
		_reiterazione = "";
		_progrReiterazione = "";
		_note = "";
		_campagna = "";
//		_caricoAlpeggio = "";
		_percRidCommissRiesame = "";
		_statoPostCommissRiesame = "";
		_userInserimento = "";
		_userModifica = "";
		_esitoPremioTrasporto= "";
		_percRidPremioTrasporto = "";
		_esitoRinunciaInsilato = "";
		_importoRidRinunciaInsilato= "";
		_controllo_estivo = "";
		_perc_rid_carico_bestiame = "";
		_esito_trasformazione_latte = "";
		_segnalazione = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esitoImpegni
	 */
	public EsitoImpegniBO(EsitoImpegni esitoImpegni){
		_intervento = esitoImpegni.get_intervento();
		_sottointervento = esitoImpegni.get_sottointervento();
		_esito = esitoImpegni.get_esito();
		_percRid = esitoImpegni.get_percRid();
		_cuaa = esitoImpegni.get_cuaa();
		_domanda = esitoImpegni.get_domanda();
		_stato = esitoImpegni.get_stato();
		_inadempienza_grave = esitoImpegni.get_inadempienza_grave();
		_reiterazione = esitoImpegni.get_reiterazione();
		_progrReiterazione = esitoImpegni.get_progrReiterazione();
		if(esitoImpegni.get_dataControllo() != null) _dataControllo = Utils.getDate(esitoImpegni.get_dataControllo());
		_note = esitoImpegni.get_note();
		_campagna = esitoImpegni.get_campagna();
//		_caricoAlpeggio = esitoImpegni.get_caricoAlpeggio();
		_percRidCommissRiesame = esitoImpegni.get_percRidCommissRiesame();
		_statoPostCommissRiesame = esitoImpegni.get_statoPostCommissRiesame();
		if(esitoImpegni.get_dataCommissRiesame() != null) 
			_dataCommissRiesame = Utils.getDate(esitoImpegni.get_dataCommissRiesame());
		_dataModifica = esitoImpegni.get_data_modifica();
		_userModifica = esitoImpegni.get_user_modifica();
		_dataInserimento = esitoImpegni.get_data_inserimento();
		_userInserimento = esitoImpegni.get_user_inserimento();		
		_esitoPremioTrasporto = esitoImpegni.get_esitoPremioTrasporto();
		_percRidPremioTrasporto = esitoImpegni.get_percRidPremioTrasporto();
		_esitoRinunciaInsilato = esitoImpegni.get_esitoRinunciaInsilato();
		_importoRidRinunciaInsilato = esitoImpegni.get_importoRidRinunciaInsilatoStr();
		_controllo_estivo = esitoImpegni.get_controllo_estivo();
		_perc_rid_carico_bestiame = esitoImpegni.get_perc_rid_carico_bestiame();
		_esito_trasformazione_latte = esitoImpegni.get_esito_trasnformazione_latte();
		_segnalazione = esitoImpegni.get_segnalazione();
		
	}
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoImpegniBO
	 */
	public EsitoImpegniBO clone()
	{
		EsitoImpegniBO esitoImpegni = new EsitoImpegniBO();
		esitoImpegni.set_campagna(_campagna);
		esitoImpegni.set_cuaa(_cuaa);
		esitoImpegni.set_dataControllo(_dataControllo);
		esitoImpegni.set_domanda(_domanda);
		esitoImpegni.set_esito(_esito);
		esitoImpegni.set_id(_id);
		esitoImpegni.set_intervento(_intervento);
		esitoImpegni.set_note(_note);
		esitoImpegni.set_percRid(_percRid);
		esitoImpegni.set_sottointervento(_sottointervento);
		esitoImpegni.set_stato(_stato);
		esitoImpegni.set_inadempienza_grave(_inadempienza_grave);
		esitoImpegni.set_reiterazione(_reiterazione);
		esitoImpegni.set_progrReiterazione(_progrReiterazione);
//		esitoImpegni.set_caricoAlpeggio(_caricoAlpeggio);
		esitoImpegni.set_percRidCommissRiesame(_percRidCommissRiesame);
		esitoImpegni.set_statoPostCommissRiesame(_statoPostCommissRiesame);
		esitoImpegni.set_dataCommissRiesame(_dataCommissRiesame);
		esitoImpegni.set_userInserimento(_userInserimento);
		esitoImpegni.set_dataInserimento(_dataInserimento);
		esitoImpegni.set_userModifica(_userModifica);
		esitoImpegni.set_dataModifica(_dataModifica);
		esitoImpegni.set_esitoPremioTrasporto(_esitoPremioTrasporto);
		esitoImpegni.set_percRidPremioTrasporto(_percRidPremioTrasporto);
		esitoImpegni.set_esitoRinunciaInsilato(_esitoRinunciaInsilato);
		esitoImpegni.set_importoRidRinunciaInsilato(_importoRidRinunciaInsilato);
		esitoImpegni.set_controllo_estivo(_controllo_estivo);
		esitoImpegni.set_perc_rid_carico_bestiame(_perc_rid_carico_bestiame);
		esitoImpegni.set_esito_trasformazione_latte(_esito_trasformazione_latte);
		esitoImpegni.set_segnalazione(_segnalazione);
		return esitoImpegni;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esitoImpegni
	 */
	public void recovery(EsitoImpegniBO esitoImpegni)
	{
		_campagna = "" + esitoImpegni.get_campagna();
		_cuaa = esitoImpegni.get_cuaa();
		_dataControllo = esitoImpegni.get_dataControllo();
		_domanda = esitoImpegni.get_domanda();
		_esito = esitoImpegni.get_esito();
		_id = esitoImpegni.get_id();
		_intervento = esitoImpegni.get_intervento();
		_note = esitoImpegni.get_note();
		_percRid = esitoImpegni.get_percRid();
		_sottointervento = esitoImpegni.get_sottointervento();
		_stato = esitoImpegni.get_stato();
		_inadempienza_grave = esitoImpegni.get_inadempienza_grave();
		_reiterazione = esitoImpegni.get_reiterazione();
		_progrReiterazione = esitoImpegni.get_progrReiterazione();
		_percRidCommissRiesame = esitoImpegni.get_percRidCommissRiesame();
		_statoPostCommissRiesame = esitoImpegni.get_statoPostCommissRiesame();
		_dataCommissRiesame = esitoImpegni.get_dataCommissRiesame();
//		_caricoAlpeggio = esitoImpegni.get_caricoAlpeggio();
		_userInserimento = esitoImpegni.get_userInserimento();
		_dataInserimento = esitoImpegni.get_dataInserimento();
		_userModifica = esitoImpegni.get_userModifica();
		_dataModifica = esitoImpegni.get_dataModifica();
		_esitoPremioTrasporto = esitoImpegni.get_esitoPremioTrasporto();
		_percRidPremioTrasporto = esitoImpegni.get_percRidPremioTrasporto();
		_esitoRinunciaInsilato = esitoImpegni.get_esitoRinunciaInsilato();
		_importoRidRinunciaInsilato = esitoImpegni.get_importoRidRinunciaInsilato();
		_controllo_estivo = esitoImpegni.get_controllo_estivo();
		_perc_rid_carico_bestiame = esitoImpegni.get_perc_rid_carico_bestiame();
		_esito_trasformazione_latte = esitoImpegni.get_esito_trasformazione_latte();
		_segnalazione = esitoImpegni.get_segnalazione();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoImpegni
	 */
	public EsitoImpegni getEntity()
	{
		EsitoImpegni esito = new EsitoImpegni();
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		if(_dataControllo != null) esito.set_dataControllo(Utils.getDateString(_dataControllo));
		esito.set_domanda(_domanda);
		esito.set_esito(_esito);
		esito.set_id(_id);
		esito.set_intervento(_intervento);
		esito.set_note(_note);
		esito.set_percRid(_percRid);
		esito.set_sottointervento(_sottointervento);
		esito.set_stato(_stato);
		esito.set_inadempienza_grave(_inadempienza_grave);
		esito.set_reiterazione(_reiterazione);
		esito.set_progrReiterazione(_progrReiterazione);
//		esito.set_caricoAlpeggio(_caricoAlpeggio);
		esito.set_percRidCommissRiesame(_percRidCommissRiesame);
		esito.set_statoPostCommissRiesame(_statoPostCommissRiesame);
		if(_dataCommissRiesame != null) esito.set_dataCommissRiesame(Utils.getDateString(_dataCommissRiesame));
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_esitoPremioTrasporto(_esitoPremioTrasporto);
		esito.set_percRidPremioTrasporto(_percRidPremioTrasporto);
		esito.set_esitoRinunciaInsilato(_esitoRinunciaInsilato);
		if(!_importoRidRinunciaInsilato.equals("")){
			esito.set_importoRidRinunciaInsilato(Float.parseFloat(_importoRidRinunciaInsilato.replace(",", ".")));
		}
		esito.set_controllo_estivo(_controllo_estivo);
		esito.set_perc_rid_carico_bestiame(_perc_rid_carico_bestiame);
		esito.set_esito_trasnformazione_latte(_esito_trasformazione_latte);
		esito.set_segnalazione(_segnalazione);
		
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoImpegni esito)
	{
		esito.set_campagna(Integer.parseInt(_campagna));
		esito.set_cuaa(_cuaa);
		if(_dataControllo != null) esito.set_dataControllo(Utils.getDateString(_dataControllo));
		esito.set_domanda(_domanda);
		esito.set_esito(_esito);
		esito.set_id(_id);
		esito.set_intervento(_intervento);
		esito.set_note(_note);
		esito.set_percRid(_percRid);
		esito.set_sottointervento(_sottointervento);
		esito.set_stato(_stato);
		esito.set_inadempienza_grave(_inadempienza_grave);
		esito.set_reiterazione(_reiterazione);
		esito.set_progrReiterazione(_progrReiterazione);
//		esito.set_caricoAlpeggio(_caricoAlpeggio);
		esito.set_percRidCommissRiesame(_percRidCommissRiesame);
		esito.set_statoPostCommissRiesame(_statoPostCommissRiesame);
		if(_dataCommissRiesame != null) esito.set_dataCommissRiesame(Utils.getDateString(_dataCommissRiesame));
		esito.set_user_inserimento(_userInserimento);
		esito.set_data_inserimento(_dataInserimento);
		esito.set_user_modifica(_userModifica);
		esito.set_data_modifica(_dataModifica);
		esito.set_esitoPremioTrasporto(_esitoPremioTrasporto);
		esito.set_percRidPremioTrasporto(_percRidPremioTrasporto);
		esito.set_esitoRinunciaInsilato(_esitoRinunciaInsilato);
		if(!_importoRidRinunciaInsilato.equals(""))
			esito.set_importoRidRinunciaInsilato(Float.parseFloat(_importoRidRinunciaInsilato.replace(",", ".")));
		else 
			esito.set_importoRidRinunciaInsilato(null);
		esito.set_controllo_estivo(_controllo_estivo);
		esito.set_perc_rid_carico_bestiame(_perc_rid_carico_bestiame);
		esito.set_esito_trasnformazione_latte(_esito_trasformazione_latte);
		esito.set_segnalazione(_segnalazione);
		
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
	
	public String get_sottointervento(){
		return _sottointervento;
	}
	
	public void set_sottointervento(String sottoIntervento){
		if(sottoIntervento == null) sottoIntervento = "";
		_sottointervento = sottoIntervento.trim();
	}
	
	public String get_esito(){
		return _esito;
	}
	
	public void set_esito(String esito){
		if(esito == null) esito = "";
		_esito = esito.trim();
	}
	
	public String get_percRid(){
		return _percRid;
	}
	
	public void set_percRid(String percRid){
		if(percRid == null) percRid = "";
		_percRid = percRid.replace(",", ".");
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

	public void set_dataControllo(Date _dataControllo) {
		this._dataControllo = _dataControllo;
	}

	public Date get_dataControllo() {
		return _dataControllo;
	}
	
	public String get_dataControlloStr() {
		return Utils.getDateString(_dataControllo);
	}

	public void set_stato(String _stato) {
		if(_stato == null) _stato = "";
		this._stato = _stato.trim();
	}

	public String get_stato() {
		return _stato;
	}

	public String get_inadempienza_grave() {
		return _inadempienza_grave;
	}

	public void set_inadempienza_grave(String _inadempienza_grave) {
		this._inadempienza_grave = _inadempienza_grave;
	}

	public String get_reiterazione() {
		return _reiterazione;
	}

	public void set_reiterazione(String _reiterazione) {
		this._reiterazione = _reiterazione;
	}

	public String get_progrReiterazione() {
		return _progrReiterazione;
	}

	public void set_progrReiterazione(String progrReiterazione) {
		if(progrReiterazione == null) progrReiterazione = "";
		_progrReiterazione = progrReiterazione.trim();
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_id() {
		return _id;
	}
	
//	public void set_caricoAlpeggio(String _caricoAlpeggio) {
//		if(_caricoAlpeggio == null) _caricoAlpeggio = "";
//		this._caricoAlpeggio = _caricoAlpeggio.replace(",", ".");
//	}

//	public String get_caricoAlpeggio() {
//		return _caricoAlpeggio;
//	}

	public void set_percRidCommissRiesame(String _percRidCommissRiesame) {
		if(_percRidCommissRiesame == null) _percRidCommissRiesame = "";
		this._percRidCommissRiesame = _percRidCommissRiesame.replace(",", ".");;
	}

	public String get_percRidCommissRiesame() {
		return _percRidCommissRiesame;
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

	public void set_esitoPremioTrasporto(String _esitoPremioTrasporto) {
		this._esitoPremioTrasporto = _esitoPremioTrasporto;
	}

	public String get_esitoPremioTrasporto() {
		return _esitoPremioTrasporto;
	}

	public void set_percRidPremioTrasporto(String _percRidPremioTrasporto) {
		this._percRidPremioTrasporto = _percRidPremioTrasporto;
	}

	public String get_percRidPremioTrasporto() {
		return _percRidPremioTrasporto;
	}

	public void set_esitoRinunciaInsilato(String _esitoRinunciaInsilato) {
		this._esitoRinunciaInsilato = _esitoRinunciaInsilato;
	}

	public String get_esitoRinunciaInsilato() {
		return _esitoRinunciaInsilato;
	}

	public void set_importoRidRinunciaInsilato(String _importoRidRinunciaInsilato) {
		this._importoRidRinunciaInsilato = _importoRidRinunciaInsilato;
	}

	public String get_importoRidRinunciaInsilato() {
		return _importoRidRinunciaInsilato;
	}

	public String get_controllo_estivo() {
		return _controllo_estivo;
	}

	public void set_controllo_estivo(String _controllo_estivo) {
		this._controllo_estivo = _controllo_estivo;
	}

	public String get_perc_rid_carico_bestiame() {
		return _perc_rid_carico_bestiame;
	}

	public void set_perc_rid_carico_bestiame(String _perc_rid_carico_bestiame) {
		this._perc_rid_carico_bestiame = _perc_rid_carico_bestiame;
	}

	public String get_esito_trasformazione_latte() {
		return _esito_trasformazione_latte;
	}

	public void set_esito_trasformazione_latte(
			String _esito_trasnformazione_latte) {
		this._esito_trasformazione_latte = _esito_trasnformazione_latte;
	}

	public String get_segnalazione() {
		return _segnalazione;
	}

	public void set_segnalazione(String _segnalazione) {
		this._segnalazione = _segnalazione;
	}
	
}
