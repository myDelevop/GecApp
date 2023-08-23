package it.bz.prov.esiti.bobject;

import java.util.Date;

import it.bz.prov.esiti.entity.EsitoCondizionalitaAtto;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta il singolo esito di condizionalità per atto
 * 
 * @author bpettazzoni
 *
 */

public class EsitoCondizionalitaAttoBO {
	
	private String _id;
	private String _cuaa;
	private String _campagna;
	private String _cod_cond;
	private String _atto_contr;
	private String _resp_controllo;
	private String _infrazione;
	private Date _data_1_contr;
	private String _rich_az_corr;
	private String _az_corr_eseguita;
	private String _rich_imp_ripr;
	private String _imp_ripr_eseguito;
	private Date _data_2_contr;
	private String _esito_2_contr;
	private String _inadempienza;
	private String _negligenza;
	private String _intenzionalita;
	private String _progr_accert_intenz;
	private String _reiterazione;
	private String _progr_Accert_reit;
	private String _portata;
	private String _portata_note;
	private String _gravita;
	private String _gravita_note;
	private String _durata;
	private String _durata_note;
	private String _perc_rid;
	private String _ammonizione;
	private String _note;
	private String _tipoControllo;
	
	private String _intenzionalita_note;
	private String _segnalazione;
	private String _approvazione;
	
	private String _userCreazione;
	private Date _dataCreazione;
	private String _userModifica;
	private Date _dataModifica;
	
	/**************************************************************
	 * 			COSTRUTTORI
	 **************************************************************/
	
	
	/**
	 * Costruttore
	 */
	public EsitoCondizionalitaAttoBO()
	{
		_id = "";
		_cuaa = "";
		_campagna = "";
		_cod_cond = "";
		_atto_contr = "";
		_resp_controllo = "";
		_infrazione = "";
//		_data_1_contr = "";
		_rich_az_corr = "";
		_az_corr_eseguita = "";
		_rich_imp_ripr = "";
		_imp_ripr_eseguito = "";
//		_data_2_contr = "";
		_esito_2_contr = "";
		_inadempienza = "";
		_negligenza = "";
		_intenzionalita = "";
		_progr_accert_intenz = "";
		_reiterazione = "";
		_progr_Accert_reit = "";
		_portata = "";
		_portata_note = "";
		_gravita = "";
		_gravita_note = "";
		_durata = "";
		_durata_note = "";
		_ammonizione = "";
		_note = "";
		_perc_rid = "";
		_tipoControllo= "";
		_userCreazione = "";
		_userModifica = "";
		_intenzionalita_note = "";
		_segnalazione = "";
		_approvazione = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esitoCondAtto
	 */
	public EsitoCondizionalitaAttoBO(EsitoCondizionalitaAtto esitoCondAtto)
	{
		_cuaa = esitoCondAtto.get_cuaa();
		_campagna = esitoCondAtto.get_campagna();
		_cod_cond = esitoCondAtto.get_cod_cond();
		_atto_contr = esitoCondAtto.get_atto_contr();
		_resp_controllo = esitoCondAtto.get_resp_controllo();
		_infrazione = esitoCondAtto.get_infrazione();
		if(esitoCondAtto.get_data_1_contr() != null) _data_1_contr = Utils.getDate(esitoCondAtto.get_data_1_contr());
		_rich_az_corr = esitoCondAtto.get_rich_az_corr();
		_az_corr_eseguita = esitoCondAtto.get_az_corr_eseguita();
		_rich_imp_ripr = esitoCondAtto.get_rich_imp_ripr();
		_imp_ripr_eseguito = esitoCondAtto.get_imp_ripr_eseguito();
		if(esitoCondAtto.get_data_2_contr() != null) _data_2_contr = Utils.getDate(esitoCondAtto.get_data_2_contr());
		_esito_2_contr = esitoCondAtto.get_esito_2_contr();
		_inadempienza = esitoCondAtto.get_inadempienza();
		_negligenza = esitoCondAtto.get_negligenza();
		_intenzionalita = esitoCondAtto.get_intenzionalita();
		_progr_accert_intenz = esitoCondAtto.get_progr_accert_intenz();
		_reiterazione = esitoCondAtto.get_reiterazione();
		_progr_Accert_reit = esitoCondAtto.get_progr_Accert_reit();
		_portata = esitoCondAtto.get_portata();
		_portata_note = esitoCondAtto.get_portata_note();
		_gravita = esitoCondAtto.get_gravita();
		_gravita_note = esitoCondAtto.get_gravita_note();
		_durata = esitoCondAtto.get_durata();
		_durata_note = esitoCondAtto.get_durata_note();
		_ammonizione = esitoCondAtto.get_ammonizione();
		_note = esitoCondAtto.get_note();
		_perc_rid = esitoCondAtto.get_perc_rid();
		_tipoControllo = esitoCondAtto.get_tipoControllo();
		_userCreazione = esitoCondAtto.get_user_inserimento();
		_dataCreazione = esitoCondAtto.get_data_inserimento();
		_userModifica = esitoCondAtto.get_user_modifica();
		_dataModifica = esitoCondAtto.get_data_modifica();
		_intenzionalita_note = esitoCondAtto.get_intenzionalita_note();
		_segnalazione = esitoCondAtto.get_segnalazione();
		_approvazione = esitoCondAtto.get_approvazione();
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoCondizionalitaAttoBO
	 */
	public EsitoCondizionalitaAttoBO clone()
	{
		EsitoCondizionalitaAttoBO esito = new EsitoCondizionalitaAttoBO();
		esito.set_ammonizione(_ammonizione);
		esito.set_atto_contr(_atto_contr);
		esito.set_az_corr_eseguita(_az_corr_eseguita);
		esito.set_campagna(_campagna);
		esito.set_cod_cond(_cod_cond);
		esito.set_cuaa(_cuaa);
		esito.set_data_1_contr(_data_1_contr);
		esito.set_data_2_contr(_data_2_contr);
		esito.set_durata(_durata);
		esito.set_durata_note(_durata_note);
		esito.set_esito_2_contr(_esito_2_contr);
		esito.set_gravita(_gravita);
		esito.set_gravita_note(_gravita_note);
		esito.set_id(_id);
		esito.set_imp_ripr_eseguito(_imp_ripr_eseguito);
		esito.set_inadempienza(_inadempienza);
		esito.set_infrazione(_infrazione);
		esito.set_intenzionalita(_intenzionalita);
		esito.set_negligenza(_negligenza);
		esito.set_note(_note);
		esito.set_perc_rid(_perc_rid);
		esito.set_portata(_portata);
		esito.set_portata_note(_portata_note);
		esito.set_progr_accert_intenz(_progr_accert_intenz);
		esito.set_progr_Accert_reit(_progr_Accert_reit);
		esito.set_reiterazione(_reiterazione);
		esito.set_resp_controllo(_resp_controllo);
		esito.set_rich_az_corr(_rich_az_corr);
		esito.set_rich_imp_ripr(_rich_imp_ripr);
		esito.set_tipoControllo(_tipoControllo);
		esito.set_userCreazione(_userCreazione);
		esito.set_dataCreazione(_dataCreazione);
		esito.set_userModifica(_userModifica);
		esito.set_dataModifica(_dataModifica);
		esito.set_intenzionalita_note(_intenzionalita_note);
		esito.set_segnalazione(_segnalazione);
		esito.set_approvazione(_approvazione);
		return esito;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esitoCond
	 */
	public void recovery(EsitoCondizionalitaAttoBO esitoCond)
	{
		_ammonizione = esitoCond.get_ammonizione();
		_atto_contr = esitoCond.get_atto_contr();
		_az_corr_eseguita = esitoCond.get_az_corr_eseguita();
		_campagna = esitoCond.get_campagna();
		_cod_cond = esitoCond.get_cod_cond();
		_cuaa = esitoCond.get_cuaa();
		_data_1_contr = esitoCond.get_data_1_contr();
		_data_2_contr = esitoCond.get_data_2_contr();
		_durata = esitoCond.get_durata();
		_durata_note = esitoCond.get_durata_note();
		_esito_2_contr = esitoCond.get_esito_2_contr();
		_gravita = esitoCond.get_gravita();
		_gravita_note = esitoCond.get_gravita_note();
		_id = esitoCond.get_id();
		_imp_ripr_eseguito = esitoCond.get_imp_ripr_eseguito();
		_inadempienza = esitoCond.get_inadempienza();
		_infrazione = esitoCond.get_infrazione();
		_intenzionalita = esitoCond.get_intenzionalita();
		_negligenza = esitoCond.get_negligenza();
		_note = esitoCond.get_note();
		_perc_rid = esitoCond.get_perc_rid();
		_portata = esitoCond.get_portata();
		_portata_note = esitoCond.get_portata_note();
		_progr_accert_intenz = esitoCond.get_progr_accert_intenz();
		_progr_Accert_reit = esitoCond.get_progr_Accert_reit();
		_reiterazione = esitoCond.get_reiterazione();
		_resp_controllo = esitoCond.get_resp_controllo();
		_rich_az_corr = esitoCond.get_rich_az_corr();
		_rich_imp_ripr = esitoCond.get_rich_imp_ripr();
		_tipoControllo = esitoCond.get_tipoControllo();
		_userCreazione = esitoCond.get_userCreazione();
		_userModifica = esitoCond.get_userModifica();
		_dataCreazione = esitoCond.get_dataCreazione();
		_dataModifica = esitoCond.get_dataModifica();
		_intenzionalita_note = esitoCond.get_intenzionalita_note();
		_segnalazione = esitoCond.get_segnalazione();
		_approvazione = esitoCond.get_approvazione();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoCondizionalitaAtto
	 */
	public EsitoCondizionalitaAtto getEntity()
	{
		EsitoCondizionalitaAtto esito = new EsitoCondizionalitaAtto();
		esito.set_ammonizione(_ammonizione);
		esito.set_atto_contr(_atto_contr);
		esito.set_az_corr_eseguita(_az_corr_eseguita);
		esito.set_campagna(_campagna);
		esito.set_cod_cond(_cod_cond);
		esito.set_cuaa(_cuaa);
		esito.set_data_1_contr(Utils.getDateString(_data_1_contr));
		esito.set_data_2_contr(Utils.getDateString(_data_2_contr));
		esito.set_durata(_durata);
		esito.set_durata_note(_durata_note);
		esito.set_esito_2_contr(_esito_2_contr);
		esito.set_gravita(_gravita);
		esito.set_gravita_note(_gravita_note);
		esito.set_imp_ripr_eseguito(_imp_ripr_eseguito);
		esito.set_inadempienza(_inadempienza);
		esito.set_infrazione(_infrazione);
		esito.set_intenzionalita(_intenzionalita);
		esito.set_negligenza(_negligenza);
		esito.set_note(_note);
		esito.set_perc_rid(_perc_rid);
		esito.set_portata(_portata);
		esito.set_portata_note(_portata_note);
		esito.set_progr_accert_intenz(_progr_accert_intenz);
		esito.set_progr_Accert_reit(_progr_Accert_reit);
		esito.set_reiterazione(_reiterazione);
		esito.set_resp_controllo(_resp_controllo);
		esito.set_rich_az_corr(_rich_az_corr);
		esito.set_rich_imp_ripr(_rich_imp_ripr);
		esito.set_tipoControllo(_tipoControllo);
		esito.set_user_inserimento(_userCreazione);
		esito.set_user_modifica(_userModifica);
		esito.set_data_inserimento(_dataCreazione);
		esito.set_data_modifica(_dataModifica);
		esito.set_intenzionalita_note(_intenzionalita_note);
		esito.set_segnalazione(_segnalazione);
		esito.set_approvazione(_approvazione);
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoCondizionalitaAtto esito)
	{
		esito.set_ammonizione(_ammonizione);
		esito.set_atto_contr(_atto_contr);
		esito.set_az_corr_eseguita(_az_corr_eseguita);
		esito.set_campagna(_campagna);
		esito.set_cod_cond(_cod_cond);
		esito.set_cuaa(_cuaa);
		esito.set_data_1_contr(Utils.getDateString(_data_1_contr));
		esito.set_data_2_contr(Utils.getDateString(_data_2_contr));
		esito.set_durata(_durata);
		esito.set_durata_note(_durata_note);
		esito.set_esito_2_contr(_esito_2_contr);
		esito.set_gravita(_gravita);
		esito.set_gravita_note(_gravita_note);
		esito.set_imp_ripr_eseguito(_imp_ripr_eseguito);
		esito.set_inadempienza(_inadempienza);
		esito.set_infrazione(_infrazione);
		esito.set_intenzionalita(_intenzionalita);
		esito.set_negligenza(_negligenza);
		esito.set_note(_note);
		esito.set_perc_rid(_perc_rid);
		esito.set_portata(_portata);
		esito.set_portata_note(_portata_note);
		esito.set_progr_accert_intenz(_progr_accert_intenz);
		esito.set_progr_Accert_reit(_progr_Accert_reit);
		esito.set_reiterazione(_reiterazione);
		esito.set_resp_controllo(_resp_controllo);
		esito.set_rich_az_corr(_rich_az_corr);
		esito.set_rich_imp_ripr(_rich_imp_ripr);
		esito.set_tipoControllo(_tipoControllo);
		esito.set_user_inserimento(_userCreazione);
		esito.set_user_modifica(_userModifica);
		esito.set_data_inserimento(_dataCreazione);
		esito.set_data_modifica(_dataModifica);		
		esito.set_intenzionalita_note(_intenzionalita_note);
		esito.set_segnalazione(_segnalazione);
		esito.set_approvazione(_approvazione);
	}
	
	
	/**************************************************************
	 * 			GETTER E SETTER
	 **************************************************************/
	
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_id() {
		return _id;
	}
	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa.trim();
	}
	public String get_cuaa() {
		return _cuaa;
	}
	public void set_campagna(String _campagna) {
		this._campagna = _campagna.trim();
	}
	public String get_campagna() {
		return _campagna;
	}
	public void set_cod_cond(String _cod_cond) {
		if(_cod_cond == null) _cod_cond = "";
		this._cod_cond = _cod_cond.trim();
	}
	public String get_cod_cond() {
		return _cod_cond;
	}
	public void set_atto_contr(String _atto_contr) {
		if(_atto_contr == null) _atto_contr = "";
		this._atto_contr = _atto_contr.trim();
	}
	public String get_atto_contr() {
		return _atto_contr;
	}
	public void set_resp_controllo(String _resp_controllo) {
		if(_resp_controllo == null) _resp_controllo = "";
		this._resp_controllo = _resp_controllo.trim();
	}
	public String get_resp_controllo() {
		return _resp_controllo;
	}
	public void set_infrazione(String _infrazione) {
		if(_infrazione == null) _infrazione = "";
		this._infrazione = _infrazione.trim();
	}
	public String get_infrazione() {
		return _infrazione;
	}
	public void set_data_1_contr(Date _data_1_contr) {
		this._data_1_contr = _data_1_contr;
	}
	public Date get_data_1_contr() {
		return _data_1_contr;
	}
	public String get_data_1_contrStr() {
		return Utils.getDateString(_data_1_contr);
	}
	public void set_rich_az_corr(String _rich_az_corr) {
		if(_rich_az_corr == null) _rich_az_corr = "";
		this._rich_az_corr = _rich_az_corr.trim();
	}
	public String get_rich_az_corr() {
		return _rich_az_corr;
	}
	public void set_az_corr_eseguita(String _az_corr_eseguita) {
		if(_az_corr_eseguita == null) _az_corr_eseguita = "";
		this._az_corr_eseguita = _az_corr_eseguita.trim();
	}
	public String get_az_corr_eseguita() {
		return _az_corr_eseguita;
	}
	public void set_rich_imp_ripr(String _rich_imp_ripr) {
		if(_rich_imp_ripr == null) _rich_imp_ripr = "";
		this._rich_imp_ripr = _rich_imp_ripr.trim();
	}
	public String get_rich_imp_ripr() {
		return _rich_imp_ripr;
	}
	public void set_imp_ripr_eseguito(String _imp_ripr_eseguito) {
		if(_imp_ripr_eseguito == null) _imp_ripr_eseguito = "";
		this._imp_ripr_eseguito = _imp_ripr_eseguito.trim();
	}
	public String get_imp_ripr_eseguito() {
		return _imp_ripr_eseguito;
	}
	public void set_data_2_contr(Date _data_2_contr) {
		this._data_2_contr = _data_2_contr;
	}
	public Date get_data_2_contr() {
		return _data_2_contr;
	}
	public String get_data_2_contrStr() {
		return Utils.getDateString(_data_2_contr);
	}
	public void set_esito_2_contr(String _esito_2_contr) {
		if(_esito_2_contr == null) _esito_2_contr = "";
		this._esito_2_contr = _esito_2_contr.trim();
	}
	public String get_esito_2_contr() {
		return _esito_2_contr;
	}
	public void set_inadempienza(String _inadempienza) {
		if(_inadempienza == null) _inadempienza = "";
		this._inadempienza = _inadempienza.trim();
	}
	public String get_inadempienza() {
		return _inadempienza;
	}
	public void set_negligenza(String _negligenza) {
		if(_negligenza == null) _negligenza = "";
		this._negligenza = _negligenza.trim();
	}
	public String get_negligenza() {
		return _negligenza;
	}
	public void set_intenzionalita(String _intenzionalita) {
		if(_intenzionalita == null) _intenzionalita = "";
		this._intenzionalita = _intenzionalita.trim();
	}
	public String get_intenzionalita() {
		return _intenzionalita;
	}
	public void set_progr_accert_intenz(String _progr_accert_intenz) {
		if(_progr_accert_intenz == null || _progr_accert_intenz.equalsIgnoreCase("on")) 
			_progr_accert_intenz = "";
		this._progr_accert_intenz = _progr_accert_intenz.trim();
	}
	public String get_progr_accert_intenz() {
		return _progr_accert_intenz;
	}
	public void set_reiterazione(String _reiterazione) {
		if(_reiterazione == null) _reiterazione = "";
		this._reiterazione = _reiterazione.trim();
	}
	public String get_reiterazione() {
		return _reiterazione;
	}
	public void set_progr_Accert_reit(String _progr_Accert_reit) {
		if(_progr_Accert_reit == null || _progr_Accert_reit.equalsIgnoreCase("on")) 
			_progr_Accert_reit = "";
		this._progr_Accert_reit = _progr_Accert_reit.trim();
	}
	public String get_progr_Accert_reit() {
		return _progr_Accert_reit;
	}
	public void set_portata(String _portata) {
		if(_portata == null || _portata.equalsIgnoreCase("on")) 
			_portata = "";
		this._portata = _portata.trim();
	}
	public String get_portata() {
		return _portata;
	}
	public void set_gravita(String _gravita) {
		if(_gravita == null || _gravita.equalsIgnoreCase("on"))
			_gravita = "";
		this._gravita = _gravita.trim();
	}
	public String get_gravita() {
		return _gravita;
	}
	public void set_durata(String _durata) {
		if(_durata == null || _durata.equalsIgnoreCase("on")) 
			_durata = "";
		this._durata = _durata.trim();
	}
	public String get_durata() {
		return _durata;
	}
	public void set_ammonizione(String _ammonizione) {
		if(_ammonizione == null) _ammonizione = "";
		this._ammonizione = _ammonizione.trim();
	}
	public String get_ammonizione() {
		return _ammonizione;
	}
	public void set_note(String _note) {
		if(_note == null) _note = "";
		this._note = _note.trim();
	}
	public String get_note() {
		return _note;
	}

	public void set_perc_rid(String _perc_rid) {
		if(_perc_rid == null) _perc_rid = "";
		this._perc_rid = _perc_rid.trim();
	}

	public String get_perc_rid() {
		return _perc_rid;
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


	public void set_tipoControllo(String _tipoControllo) {
		this._tipoControllo = _tipoControllo;
	}

	public String get_tipoControllo() {
		return _tipoControllo;
	}

	public void set_portata_note(String _portata_note) {
		this._portata_note = _portata_note;
	}

	public String get_portata_note() {
		return _portata_note;
	}

	public void set_gravita_note(String _gravita_note) {
		this._gravita_note = _gravita_note;
	}

	public String get_gravita_note() {
		return _gravita_note;
	}

	public void set_durata_note(String _durata_note) {
		this._durata_note = _durata_note;
	}

	public String get_durata_note() {
		return _durata_note;
	}

	public String get_intenzionalita_note() {
		return _intenzionalita_note;
	}

	public void set_intenzionalita_note(String _intenzionalita_note) {
		this._intenzionalita_note = _intenzionalita_note;
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

	
}
