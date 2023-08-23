package it.bz.prov.esiti.bobject;

import java.util.Date;

import it.bz.prov.esiti.entity.UBAAmmissibilita;
import it.bz.prov.esiti.util.Utils;

public class UBAAmmissibilitaBO {
	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _intervento;
	private String _esito;
	private String _stato;
	private String _ubaRich;
	private String _ubaAcc;
	private String _ubaIrreg;
	private String _percRid;
	private String _note;
	private Date   _dataModifica;
	private String _userModifica;
	private Date   _dataInserimento;
	private String _userInserimento;
	
	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
	
	public UBAAmmissibilitaBO() {
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_intervento = "";
		_esito = "";
		_stato = "";
		_ubaRich = "";
		_ubaAcc = "";
		_ubaIrreg = "";
		_percRid = "";
		_note = "";
		_dataModifica = null;
		_userModifica = "";
		_dataInserimento = null;
		_userInserimento = "";
	}
	
	public UBAAmmissibilitaBO(UBAAmmissibilita uba) {
		_cuaa            = uba.get_cuaa           ();
		_domanda         = uba.get_domanda        ();
		_campagna        = uba.get_campagna       ();
		_intervento      = uba.get_intervento     ();
		_esito           = uba.get_esito          ();
		_stato           = uba.get_stato          ();
		_ubaRich         = uba.get_ubaRich        ();
		_ubaAcc          = uba.get_ubaAcc         ();
		_ubaIrreg        = uba.get_ubaIrreg       ();
		_percRid         = uba.get_percRid        ();
		_note            = uba.get_note           ();
		_dataModifica    = uba.get_data_modifica   ();
		_userModifica    = uba.get_user_modifica   ();
		_dataInserimento = uba.get_data_inserimento();
		_userInserimento = uba.get_user_inserimento();
	}
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return UbaBO
	 */
	public UBAAmmissibilitaBO clone()
	{
		UBAAmmissibilitaBO uba = new UBAAmmissibilitaBO();
		uba.set_cuaa           (_cuaa);
		uba.set_domanda        (_domanda);
		uba.set_campagna       (_campagna);
		uba.set_intervento     (_intervento);
		uba.set_esito          (_esito);
		uba.set_stato          (_stato);
		uba.set_ubaRich        (_ubaRich);
		uba.set_ubaAcc         (_ubaAcc);
		uba.set_ubaIrreg       (_ubaIrreg);
		uba.set_percRid        (_percRid);
		uba.set_note           (_note);
		uba.set_dataModifica   (_dataModifica);
		uba.set_userModifica   (_userModifica);
		uba.set_dataInserimento(_dataInserimento);
		uba.set_userInserimento(_userInserimento);
		return uba;
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return Uba
	 */
	public void recovery(UBAAmmissibilitaBO uba) {
		_cuaa            = uba.get_cuaa           ();
		_domanda         = uba.get_domanda        ();
		_campagna        = uba.get_campagna       ();
		_intervento      = uba.get_intervento     ();
		_esito           = uba.get_esito          ();
		_stato           = uba.get_stato          ();
		_ubaRich         = uba.get_ubaRich        ();
		_ubaAcc          = uba.get_ubaAcc         ();
		_ubaIrreg        = uba.get_ubaIrreg       ();
		_percRid         = uba.get_percRid        ();
		_note            = uba.get_note           ();
		_dataModifica    = uba.get_dataModifica   ();
		_userModifica    = uba.get_userModifica   ();
		_dataInserimento = uba.get_dataInserimento();
		_userInserimento = uba.get_userInserimento();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return Uba
	 */
	public UBAAmmissibilita getUbaEntity()
	{
		UBAAmmissibilita uba = new UBAAmmissibilita();
		uba.set_cuaa           (_cuaa);
		uba.set_domanda        (_domanda);
		if(_campagna!= null && !_campagna.equals(""))uba.set_campagna       (Integer.parseInt(_campagna));
		uba.set_intervento     (_intervento);
		uba.set_esito          (_esito);
		uba.set_stato          (_stato);
		if(_ubaRich != null && !_ubaRich.equals("")) uba.set_ubaRich        (Float.parseFloat(_ubaRich));
		if(_ubaAcc != null && !_ubaAcc.equals(""))uba.set_ubaAcc         (Float.parseFloat(_ubaAcc));
		if(_ubaIrreg != null && !_ubaIrreg.equals(""))uba.set_ubaIrreg       (Float.parseFloat(_ubaIrreg));
		if(_percRid != null && !_percRid.equals(""))uba.set_percRid        (Float.parseFloat(_percRid));
		uba.set_note           (_note);
		uba.set_data_modifica   (_dataModifica);
		uba.set_user_modifica   (_userModifica);
		uba.set_data_inserimento(_dataInserimento);
		uba.set_user_inserimento(_userInserimento);

		return uba;
		
	}
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param uba
	 */
	public void setEntity(UBAAmmissibilita uba){
		uba.set_cuaa           (_cuaa);
		uba.set_domanda        (_domanda);
		if(_campagna!= null && !_campagna.equals(""))uba.set_campagna       (Integer.parseInt(_campagna));
		uba.set_intervento     (_intervento);
		uba.set_esito          (_esito);
		uba.set_stato          (_stato);
		if(_ubaRich != null && !_ubaRich.equals("")) uba.set_ubaRich        (Float.parseFloat(_ubaRich));
		if(_ubaAcc != null && !_ubaAcc.equals(""))uba.set_ubaAcc         (Float.parseFloat(_ubaAcc));
		if(_ubaIrreg != null && !_ubaIrreg.equals(""))uba.set_ubaIrreg       (Float.parseFloat(_ubaIrreg));
		if(_percRid != null && !_percRid.equals(""))uba.set_percRid        (Float.parseFloat(_percRid));
		uba.set_note           (_note);
		uba.set_data_modifica   (_dataModifica);
		uba.set_user_modifica   (_userModifica);
		uba.set_data_inserimento(_dataInserimento);
		uba.set_user_inserimento(_userInserimento);
	}
	
	/************************************************************************
	 * 				GETTER E SETTER
	 ***********************************************************************/
	
	public String get_cuaa() {
		return _cuaa;
	}

	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}

	public String get_domanda() {
		return _domanda;
	}

	public void set_domanda(String _domanda) {
		this._domanda = _domanda;
	}

	public String get_campagna() {
		return _campagna;
	}

	public void set_campagna(String _campagna) {
		this._campagna = _campagna;
	}

	public String get_intervento() {
		return _intervento;
	}

	public void set_intervento(String _intervento) {
		this._intervento = _intervento;
	}

	public String get_esito() {
		return _esito;
	}

	public void set_esito(String _esito) {
		this._esito = _esito;
	}

	public String get_stato() {
		return _stato;
	}

	public void set_stato(String _stato) {
		this._stato = _stato;
	}

	public String get_ubaRich() {
		return _ubaRich;
	}

	public void set_ubaRich(String _ubaRich) {
		_ubaRich = _ubaRich == null ? "" : _ubaRich;
		this._ubaRich = _ubaRich.replace(",", ".");
	}

	public String get_ubaAcc() {
		return _ubaAcc;
	}

	public void set_ubaAcc(String _ubaAcc) {
		_ubaAcc = _ubaAcc == null ? "" : _ubaAcc;
		this._ubaAcc = _ubaAcc.replace(",", ".");
	}

	public String get_ubaIrreg() {
		return _ubaIrreg;
	}

	public void set_ubaIrreg(String _ubaIrreg) {
		_ubaIrreg = _ubaIrreg == null ? "" : _ubaIrreg;
		this._ubaIrreg = _ubaIrreg.replace(",", ".");
	}

	public String get_percRid() {
		return _percRid;
	}

	public void set_percRid(String _percRid) {
		_percRid = _percRid == null ? "" : _percRid;
		this._percRid = _percRid.replace(",", ".");
	}

	public String get_note() {
		return _note;
	}

	public void set_note(String _note) {
		this._note = _note;
	}

	public Date get_dataModifica() {
		return _dataModifica;
	}

	public String get_dataModificaStr(){
		if(_userModifica == null || _userModifica.equals("") ) return Utils.getDateString(_dataInserimento);
		else return Utils.getDateString(_dataModifica);
	}
	
	public void set_dataModifica(Date _dataModifica) {
		this._dataModifica = _dataModifica;
	}

	public String get_userModifica() {
		if(_userModifica == null || _userModifica.equals("") ) return _userInserimento;
		else return _userModifica;
	}

	public void set_userModifica(String _userModifica) {
		this._userModifica = _userModifica;
	}

	public Date get_dataInserimento() {
		return _dataInserimento;
	}

	public void set_dataInserimento(Date _dataInserimento) {
		this._dataInserimento = _dataInserimento;
	}

	public String get_userInserimento() {
		return _userInserimento;
	}

	public void set_userInserimento(String _userInserimento) {
		this._userInserimento = _userInserimento;
	}

}
