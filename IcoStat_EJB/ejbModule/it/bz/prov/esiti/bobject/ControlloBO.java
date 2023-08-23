package it.bz.prov.esiti.bobject;

import java.util.Date;


import it.bz.prov.esiti.entity.Controllo;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta il generico controllo effettuato a campione
 * 
 * @author bpettazzoni
 *
 */


public class ControlloBO {

	
	private String _cuaa;
	private String _campagna;
	private Date _data_preavviso;
	private Date _data_inizio_controllo;
	private Date _data_fine_controllo;
	private String _controllore;
	private String _tipo_controllo;
	private Date _data_inserimento;
	private String _user_inserimento;
	private Date _data_modifica;
	private String _user_modifica;
	private String _tipoPreavviso;
	private String _personaPresente;
	private String _nomeDelegato;
	private String _numeroDocumento;
	
	/**************************************************************
	 * 			COSTRUTTORI
	 **************************************************************/
	
	/**
	 * Costruttore
	 */
	public ControlloBO()
	{
		_cuaa = "";
		_controllore = "";
		_tipo_controllo = "";
		_user_inserimento = "";
		_user_modifica = "";
		_campagna = "";
		_tipoPreavviso = "";
		_personaPresente = "";
		_nomeDelegato = "";
		_numeroDocumento = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param controllo
	 */
	public ControlloBO(Controllo controllo)
	{
		_cuaa = controllo.get_cuaa();
		_campagna = controllo.get_campagna();
		if(controllo.get_data_preavviso() != null) _data_preavviso = Utils.getDate(controllo.get_data_preavviso());
		if(controllo.get_data_inizio_controllo() != null) _data_inizio_controllo = Utils.getDate(controllo.get_data_inizio_controllo());
		if(controllo.get_data_fine_controllo() != null) _data_fine_controllo = Utils.getDate(controllo.get_data_fine_controllo());
		_controllore = controllo.get_controllore();
		_tipo_controllo = controllo.get_tipo_controllo();
		_user_inserimento = controllo.get_user_inserimento();
		_user_modifica = controllo.get_user_modifica();
		_data_modifica = controllo.get_data_modifica();
		_data_inserimento = controllo.get_data_inserimento();
		_tipoPreavviso = controllo.get_tipoPreavviso();
		_personaPresente = controllo.get_personaPresente();
		_nomeDelegato = controllo.get_nomeDelegato();
		_numeroDocumento = controllo.get_numeroDocumento();
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return ControlloBO
	 */
	public ControlloBO clone()
	{
		ControlloBO controllo = new ControlloBO();
		controllo.set_campagna(_campagna);
		controllo.set_controllore(_controllore);
		controllo.set_cuaa(_cuaa);
		controllo.set_data_fine_controllo(_data_fine_controllo);
		controllo.set_data_inizio_controllo(_data_inizio_controllo);
		controllo.set_data_inserimento(_data_inserimento);
		controllo.set_data_modifica(_data_modifica);
		controllo.set_data_preavviso(_data_preavviso);
		controllo.set_tipo_controllo(_tipo_controllo);
		controllo.set_user_inserimento(_user_inserimento);
		controllo.set_user_modifica(_user_modifica);
		controllo.set_tipoPreavviso(_tipoPreavviso);
		controllo.set_personaPresente(_personaPresente);
		controllo.set_nomeDelegato(_nomeDelegato);
		controllo.set_numeroDocumento(_numeroDocumento);
		return controllo;
	}
	
	/**
	 * fa rollback dell'oggetto con una versione precedente
	 * @param controllo
	 */
	public void recovery(ControlloBO controllo)
	{
		_campagna = controllo.get_campagna();
		_controllore = controllo.get_controllore();
		_cuaa = controllo.get_cuaa();
		_data_fine_controllo = controllo.get_data_fine_controllo();
		_data_inizio_controllo = controllo.get_data_inizio_controllo();
		_data_inserimento = controllo.get_data_inserimento();
		_data_modifica = controllo.get_data_modifica();
		_data_preavviso = controllo.get_data_preavviso();
		_tipo_controllo = controllo.get_tipo_controllo();
		_user_inserimento = controllo.get_user_inserimento();
		_user_modifica = controllo.get_user_modifica();
		_tipoPreavviso =controllo.get_tipoPreavviso();
		_personaPresente = controllo.get_personaPresente();
		_nomeDelegato = controllo.get_nomeDelegato();
		_numeroDocumento = controllo.get_numeroDocumento();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return Controllo
	 */
	public Controllo getEntity()
	{
		Controllo controllo = new Controllo();
		controllo.set_campagna(Integer.parseInt(_campagna));
		controllo.set_controllore(_controllore);
		controllo.set_cuaa(_cuaa);
		if(_data_fine_controllo != null) 
			controllo.set_data_fine_controllo(Utils.getDateString(_data_fine_controllo));
		if(_data_inizio_controllo != null) 
			controllo.set_data_inizio_controllo(Utils.getDateString(_data_inizio_controllo));
		controllo.set_data_inserimento( _data_inserimento);
		controllo.set_data_modifica(_data_modifica);
		if(_data_preavviso != null) 
			controllo.set_data_preavviso(Utils.getDateString(_data_preavviso));
		controllo.set_tipo_controllo(_tipo_controllo);
		controllo.set_user_inserimento(_user_inserimento);
		controllo.set_user_modifica(_user_modifica);
		controllo.set_tipoPreavviso(_tipoPreavviso);
		controllo.set_personaPresente(_personaPresente);
		controllo.set_nomeDelegato(_nomeDelegato);
		controllo.set_numeroDocumento(_numeroDocumento);
		return controllo;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param controllo
	 */
	public void setEntity(Controllo controllo)
	{
		controllo.set_campagna(Integer.parseInt(_campagna));
		controllo.set_controllore(_controllore);
		controllo.set_cuaa(_cuaa);
		if(_data_fine_controllo != null) controllo.set_data_fine_controllo(Utils.getDateString(_data_fine_controllo));
		if(_data_inizio_controllo != null) controllo.set_data_inizio_controllo(Utils.getDateString(_data_inizio_controllo));
		controllo.set_data_inserimento(_data_inserimento);
		controllo.set_data_modifica(_data_modifica);
		if(_data_preavviso != null) controllo.set_data_preavviso(Utils.getDateString(_data_preavviso));
		controllo.set_tipo_controllo(_tipo_controllo);
		controllo.set_user_inserimento(_user_inserimento);
		controllo.set_user_modifica(_user_modifica);
		controllo.set_tipoPreavviso(_tipoPreavviso);
		controllo.set_personaPresente(_personaPresente);
		controllo.set_nomeDelegato(_nomeDelegato);
		controllo.set_numeroDocumento(_numeroDocumento);
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
		this._campagna = _campagna.trim();
	}

	public String get_campagna() {
		return _campagna;
	}

	public void set_data_preavviso(Date _data_preavviso) {
		this._data_preavviso = _data_preavviso;
	}

	public Date get_data_preavviso() {
		return _data_preavviso;
	}
	
	public String get_data_preavvisoStr() {
		return Utils.getDateString(_data_preavviso);
	}

	public void set_data_inizio_controllo(Date _data_inizio_controllo) {
		this._data_inizio_controllo = _data_inizio_controllo;
	}

	public Date get_data_inizio_controllo() {
		return _data_inizio_controllo;
	}
	
	public String get_data_inizio_controlloStr() {
		return Utils.getDateString(_data_inizio_controllo);
	}

	public void set_data_fine_controllo(Date _data_fine_controllo) {
		this._data_fine_controllo = _data_fine_controllo;
	}

	public Date get_data_fine_controllo() {
		return _data_fine_controllo;
	}
	
	public String get_data_fine_controlloStr() {
		return Utils.getDateString(_data_fine_controllo);
	}

	public void set_controllore(String _controllore) {
		if(_controllore == null) _controllore = "";
		this._controllore = _controllore.trim();
	}

	public String get_controllore() {
		return _controllore;
	}

	public void set_tipo_controllo(String _tipo_controllo) {
		if(_tipo_controllo == null) _tipo_controllo = "";
		this._tipo_controllo = _tipo_controllo.trim();
	}

	public String get_tipo_controllo() {
		return _tipo_controllo;
	}

	public void set_data_inserimento(Date _data_inserimento) {
		this._data_inserimento = _data_inserimento;
	}

	public Date get_data_inserimento() {
		return _data_inserimento;
	}

	public void set_user_inserimento(String _user_inserimento) {
		if(_user_inserimento == null) _user_inserimento = "";
		this._user_inserimento = _user_inserimento.trim();
	}

	public String get_user_inserimento() {
		return _user_inserimento;
	}

	public void set_data_modifica(Date _data_modifica) {
		this._data_modifica = _data_modifica;
	}

	public Date get_data_modifica() {
		return _data_modifica;
	}

	public void set_user_modifica(String _user_modifica) {
		if(_user_modifica == null) _user_modifica = "";
		this._user_modifica = _user_modifica.trim();
	}

	public String get_user_modifica() {
		return _user_modifica;
	}

	public void set_tipoPreavviso(String _tipoPreavviso) {
		if(_tipoPreavviso == null) _tipoPreavviso = "";
		this._tipoPreavviso = _tipoPreavviso.trim();
	}

	public String get_tipoPreavviso() {
		return _tipoPreavviso;
	}

	public void set_personaPresente(String _personaPresente) {
		if(_personaPresente == null) _personaPresente = "";
		this._personaPresente = _personaPresente.trim();
	}

	public String get_personaPresente() {
		return _personaPresente;
	}

	public void set_nomeDelegato(String _nomeDelegato) {
		if(_nomeDelegato == null) _nomeDelegato = "";
		this._nomeDelegato = _nomeDelegato.trim();
	}

	public String get_nomeDelegato() {
		return _nomeDelegato;
	}

	public void set_numeroDocumento(String _numeroDocumento) {
		if(_numeroDocumento == null) _numeroDocumento = "";
		this._numeroDocumento = _numeroDocumento.trim();
	}

	public String get_numeroDocumento() {
		return _numeroDocumento;
	}

	

}
