package it.bz.prov.esiti.bobject;

import java.util.Date;

import it.bz.prov.esiti.entity.Campione;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta la domanda a campione
 * 
 * @author bpettazzoni
 *
 */

public class CampioneBO {
	
	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _categoria;
	private String _tipo;
	private Date _dataCampione;
	private String _dominioCampCond;
	private String _statoDomandaOPPAB;
	private String _note;
	private String _origineCampione;
	private String _bovini;
	private String _ovicaprini;
	private Date _dataInserimento;
	private String _userInserimento;
	private Date _dataModifica;
	private String _userModifica;
	
	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
	
	/**
	 * costruttore
	 */
	public CampioneBO(){
		_cuaa = "";
		_domanda = "";
		_campagna= "";
		_categoria = "";
		_tipo = "";
		_dominioCampCond = "";
		_statoDomandaOPPAB = "";
		_note = "";
		_origineCampione = "";
		_bovini = "";
		_ovicaprini = "";
		_userInserimento = "";
		_userModifica= "";
	}

	/**
	 * costruttore con parametri
	 */
	public CampioneBO(Campione campione){
		_cuaa = campione.get_cuaa();
		_domanda = campione.get_domanda();
		_campagna = "" + campione.get_campagna();
		_categoria = campione.get_categoria();
		_tipo = campione.get_tipo();
		_dataCampione = campione.get_dataCampione();
		_dominioCampCond = campione.get_dominioCampCond();
		_statoDomandaOPPAB = campione.get_statoDomandaOPPAB();
		_note = campione.get_note();
		_origineCampione = campione.get_origineCampione();
		_bovini = campione.get_bovini();
		_ovicaprini = campione.get_ovicaprini();
		_userInserimento = campione.get_userInserimento();
		_dataInserimento = campione.get_dataInserimento();
		_userModifica = campione.get_userModifica();
		_dataModifica = campione.get_dataModifica();
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return CampioneBO
	 */
	public CampioneBO clone()
	{
		CampioneBO campione = new CampioneBO();
		campione.set_campagna(_campagna);
		campione.set_cuaa(_cuaa);
		campione.set_domanda(_domanda);
		campione.set_categoria(_categoria);
		campione.set_tipo(_tipo);
		campione.set_dataCampione(_dataCampione);
		campione.set_dominioCampCond(_dominioCampCond);
		campione.set_statoDomandaOPPAB(_statoDomandaOPPAB);
		campione.set_note(_note);
		campione.set_origineCampione(_origineCampione);
		campione.set_bovini(_bovini);
		campione.set_ovicaprini(_ovicaprini);
		campione.set_userInserimento(_userInserimento);
		campione.set_dataInserimento(_dataInserimento);
		campione.set_userModifica(_userModifica);
		campione.set_dataModifica(_dataModifica);
		return campione;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param campione
	 */
	public void recovery(CampioneBO campione) {
		_cuaa = campione.get_cuaa();
		_domanda = campione.get_domanda();
		_campagna = campione.get_campagna();
		_categoria = campione.get_categoria();
		_tipo = campione.get_tipo();
		_dataCampione = campione.get_dataCampione();
		_dominioCampCond = campione.get_dominioCampCond();
		_statoDomandaOPPAB = campione.get_statoDomandaOPPAB();
		_note = campione.get_note();
		_origineCampione = campione.get_origineCampione();
		_bovini = campione.get_bovini();
		_ovicaprini = campione.get_ovicaprini();
		_userInserimento = campione.get_userInserimento();
		_dataInserimento = campione.get_dataInserimento();
		_userModifica = campione.get_userModifica();
		_dataModifica = campione.get_dataModifica();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return Campione
	 */
	public Campione getEntity()
	{
		Campione campione = new Campione();
		campione.set_campagna(_campagna);
		campione.set_cuaa(_cuaa);
		campione.set_domanda(_domanda);
		campione.set_categoria(_categoria);
		campione.set_tipo(_tipo);
		campione.set_dataCampione(_dataCampione);
		campione.set_dominioCampCond(_dominioCampCond);
		campione.set_statoDomandaOPPAB(_statoDomandaOPPAB);
		campione.set_note(_note);
		campione.set_origineCampione(_origineCampione);
		campione.set_bovini(_bovini);
		campione.set_ovicaprini(_ovicaprini);
		campione.set_userInserimento(_userInserimento);
		campione.set_dataInserimento(_dataInserimento);
		campione.set_userModifica(_userModifica);
		campione.set_dataModifica(_dataModifica);
		return campione;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param campione
	 */
	public void setEntity(Campione campione)
	{
		campione.set_campagna(_campagna);
		campione.set_cuaa(_cuaa);
		campione.set_domanda(_domanda);
		campione.set_categoria(_categoria);
		campione.set_tipo(_tipo);
		campione.set_dataCampione(_dataCampione);
		campione.set_dominioCampCond(_dominioCampCond);
		campione.set_statoDomandaOPPAB(_statoDomandaOPPAB);
		campione.set_note(_note);
		campione.set_origineCampione(_origineCampione);
		campione.set_bovini(_bovini);
		campione.set_ovicaprini(_ovicaprini);
		campione.set_userInserimento(_userInserimento);
		campione.set_dataInserimento(_dataInserimento);
		campione.set_userModifica(_userModifica);
		campione.set_dataModifica(_dataModifica);
	}
	
	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/
	
	
	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa.trim();
	}

	public String get_cuaa() {
		return _cuaa;
	}

	public void set_domanda(String _domanda) {
		this._domanda = _domanda.trim();
	}

	public String get_domanda() {
		return _domanda;
	}

	public void set_campagna(String _campagna) {
		this._campagna = _campagna.trim();
	}

	public String get_campagna() {
		return _campagna;
	}

	public void set_note(String _note) {
		if(_note != null) this._note = _note.trim();
	}

	public String get_note() {
		return _note;
	}

	public void set_categoria(String _categoria) {
		this._categoria = _categoria;
	}

	public String get_categoria() {
		return _categoria;
	}

	public void set_tipo(String _tipo) {
		this._tipo = _tipo;
	}

	public String get_tipo() {
		return _tipo;
	}

	public void set_dataCampione(Date _dataCampione) {
		this._dataCampione = _dataCampione;
	}

	public Date get_dataCampione() {
		return _dataCampione;
	}
	
	public String get_dataCampioneStr() {
		return Utils.getDateString(_dataCampione);
	}

	public void set_dominioCampCond(String _dominioCampCond) {
		this._dominioCampCond = _dominioCampCond;
	}

	public String get_dominioCampCond() {
		return _dominioCampCond;
	}

	public void set_statoDomandaOPPAB(String _statoDomandaOPPAB) {
		this._statoDomandaOPPAB = _statoDomandaOPPAB;
	}

	public String get_statoDomandaOPPAB() {
		return _statoDomandaOPPAB;
	}

	public void set_origineCampione(String _origineCampione) {
		this._origineCampione = _origineCampione;
	}

	public String get_origineCampione() {
		return _origineCampione;
	}

	public void set_bovini(String _bovini) {
		this._bovini = _bovini;
	}

	public String get_bovini() {
		return _bovini;
	}

	public void set_ovicaprini(String _ovicaprini) {
		this._ovicaprini = _ovicaprini;
	}

	public String get_ovicaprini() {
		return _ovicaprini;
	}

	public void set_dataInserimento(Date _dataInserimento) {
		this._dataInserimento = _dataInserimento;
	}

	public Date get_dataInserimento() {
		return _dataInserimento;
	}
	
	public String get_dataInserimentoStr() {
		return Utils.getDateString(_dataInserimento);
	}

	public void set_userInserimento(String _userInserimento) {
		this._userInserimento = _userInserimento;
	}

	public String get_userInserimento() {
		return _userInserimento;
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

	public void set_userModifica(String _userModifica) {
		this._userModifica = _userModifica;
	}

	public String get_userModifica() {
		if(_userModifica == null || _userModifica.equals("") ) return _userInserimento;
		else return _userModifica;
	}



}
