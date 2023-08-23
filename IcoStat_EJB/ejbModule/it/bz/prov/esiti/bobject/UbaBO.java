package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.Uba;
import it.bz.prov.esiti.util.Utils;

import java.util.Date;

/**
 * Business entity che rappresenta l'esito sul controllo delle uba
 * 
 * @author bpettazzoni
 *
 */
public class UbaBO {
	
	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _intervento;
	private String _esito;
	private String _ubaRich;
	private String _ubaAcc;
	private String _ubaDiff;
	private String _capiRich;
	private String _capiAcc;
	private String _note;
	private Date _dataModifica;
	private String _userModifica;
	private Date _dataInserimento;
	private String _userInserimento;
	
	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
	
	
	/**
	 * costruttore
	 */
	public UbaBO(){
		_cuaa = "";
		_domanda = "";
		_intervento = "";
		_esito = "";
		_note = "";
		_campagna = "";
		_ubaRich = "";
		_ubaAcc = "";
		_ubaDiff = "";
		_capiRich = "";
		_capiAcc = "";
		_userInserimento = "";
		_userModifica = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param uba
	 */
	public UbaBO(Uba uba)
	{
		_cuaa = uba.get_cuaa();
		_domanda = uba.get_domanda();
		_intervento = uba.get_intervento();
		_esito = uba.get_esito();
		_note = uba.get_note();
		_campagna = uba.get_campagna();
		_ubaRich = uba.get_ubaRich();
		_ubaAcc = uba.get_ubaAcc();
		_ubaDiff = uba.get_ubaDiff();
		_capiRich = uba.get_capiRich();
		_capiAcc = uba.get_capiAcc();
		_dataModifica = uba.get_data_modifica();
		_userModifica = uba.get_user_modifica();
		_userInserimento  = uba.get_user_inserimento();
		_dataInserimento = uba.get_data_inserimento();
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return UbaBO
	 */
	public UbaBO clone()
	{
		UbaBO uba = new UbaBO();
		uba.set_cuaa(_cuaa);
		uba.set_campagna(_campagna);
		uba.set_domanda(_domanda);
		uba.set_esito(_esito);
		uba.set_intervento(_intervento);
		uba.set_note(_note);
		uba.set_ubaAcc(_ubaAcc);
		uba.set_ubaDiff(_ubaDiff);
		uba.set_ubaRich(_ubaRich);
		uba.set_capiAcc(_capiAcc);
		uba.set_capiRich(_capiRich);
		uba.set_userInserimento(_userInserimento);
		uba.set_dataInserimento(_dataInserimento);
		uba.set_userModifica(_userModifica);
		uba.set_dataModifica(_dataModifica);
		return uba;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param uba
	 */
	public void recovery(UbaBO uba)
	{
		_campagna = uba.get_campagna();
		_cuaa = uba.get_cuaa();
		_domanda = uba.get_domanda();
		_esito = uba.get_esito();
		_intervento = uba.get_intervento();
		_note = uba.get_note();
		_ubaAcc = uba.get_ubaAcc();
		_ubaDiff = uba.get_ubaDiff();
		_ubaRich = uba.get_ubaRich();
		_capiRich = uba.get_capiRich();
		_capiAcc = uba.get_capiAcc();
		_userInserimento = uba.get_userInserimento();
		_dataInserimento = uba.get_dataInserimento();
		_userModifica = uba.get_userModifica();
		_dataModifica = uba.get_dataModifica();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return Uba
	 */
	public Uba getUbaEntity()
	{
		Uba uba = new Uba();
		uba.set_campagna(Integer.parseInt(_campagna));
		uba.set_cuaa(_cuaa);
		uba.set_domanda(_domanda);
		uba.set_esito(_esito);
		uba.set_intervento(_intervento);
		uba.set_note(_note);
		if(_ubaAcc != null && !_ubaAcc.equals("")) uba.set_ubaAcc(Float.parseFloat(_ubaAcc));
		if(_ubaDiff != null && !_ubaDiff.equals("")) uba.set_ubaDiff(Float.parseFloat(_ubaDiff));
		if(_ubaRich != null && !_ubaRich.equals("")) uba.set_ubaRich(Float.parseFloat(_ubaRich));
		if(_capiRich != null && !_capiRich.equals("")) uba.set_capiRich(Float.parseFloat(_capiRich));
		if(_capiAcc != null && !_capiAcc.equals("")) uba.set_capiAcc(Float.parseFloat(_capiAcc));
		uba.set_user_inserimento(_userInserimento);
		uba.set_data_inserimento(_dataInserimento);
		uba.set_user_modifica(_userModifica);
		uba.set_data_modifica(_dataModifica);
		return uba;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param uba
	 */
	public void setEntity(Uba uba)
	{
		uba.set_campagna(Integer.parseInt(_campagna));
		uba.set_cuaa(_cuaa);
		uba.set_domanda(_domanda);
		uba.set_esito(_esito);
		uba.set_intervento(_intervento);
		uba.set_note(_note);
		if(_ubaAcc != null && !_ubaAcc.equals("")) uba.set_ubaAcc(Float.parseFloat(_ubaAcc));
		if(_ubaDiff != null && !_ubaDiff.equals("")) uba.set_ubaDiff(Float.parseFloat(_ubaDiff));
		if(_ubaRich != null && !_ubaAcc.equals("")) uba.set_ubaRich(Float.parseFloat(_ubaRich));
		if(_capiRich != null && !_capiRich.equals("")) uba.set_capiRich(Float.parseFloat(_capiRich));
		if(_capiAcc != null && !_capiAcc.equals("")) uba.set_capiAcc(Float.parseFloat(_capiAcc));
		uba.set_user_inserimento(_userInserimento);
		uba.set_data_inserimento(_dataInserimento);
		uba.set_user_modifica(_userModifica);
		uba.set_data_modifica(_dataModifica);
	}
	
	
	/************************************************************************
	 * 				GETTER E SETTER
	 ***********************************************************************/
	
	
	public void set_cuaa(String cuaa){
		if(_cuaa == null) _cuaa = "";
		_cuaa = cuaa.trim();
	}
	
	public String get_cuaa(){
		return _cuaa ;
	}
	
	public void set_domanda(String domanda){
		if(domanda == null) domanda = "";
		_domanda = domanda.trim();
	}
	
	public String get_domanda(){
		return _domanda ;
	}
	
	public void set_campagna(String campagna){
		if(campagna == null) campagna = "";
		_campagna = campagna.trim();
	}
	
	public String get_campagna(){
		return _campagna ;
	}
	
	public void set_intervento(String intervento){
		if(intervento == null) intervento = "";
		_intervento=intervento.trim();
	}
	
	public String get_intervento(){
		return _intervento ;
	}
	
	public void set_note(String note){
		if(note == null) note = "";
		_note = note.trim();
	}
	
	public String get_note(){
		return _note ;
	}


	public void set_esito(String _esito) {
		if(_esito == null) _esito = "";
		this._esito = _esito.trim();
	}


	public String get_esito() {
		return _esito;
	}


	public void set_ubaRich(String _ubaRich) {
		if(_ubaRich == null) _ubaRich = "";
		this._ubaRich = _ubaRich.replace(",", ".");
	}

	public String get_ubaRich() {
		return _ubaRich;
	}


	public void set_ubaAcc(String _ubaAcc) {
		if(_ubaAcc == null) _ubaAcc = "";
		this._ubaAcc = _ubaAcc.replace(",", ".");
	}


	public String get_ubaAcc() {
		return "" + _ubaAcc;
	}


	public void set_ubaDiff(String _ubaDiff) {
		if(_ubaDiff == null) _ubaDiff = "";
		this._ubaDiff = _ubaDiff.replace(",", ".");
	}


	public String get_ubaDiff() {
		return _ubaDiff;
	}

	public void set_capiRich(String _capiRich) {
		if(_capiRich == null) _capiRich = "";
		this._capiRich = _capiRich.replace(",", ".");
	}

	public String get_capiRich() {
		return _capiRich;
	}

	public void set_capiAcc(String _capiAcc) {
		if(_capiAcc == null) _capiAcc = "";
		this._capiAcc = _capiAcc.replace(",", ".");
	}

	public String get_capiAcc() {
		return _capiAcc;
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
}

