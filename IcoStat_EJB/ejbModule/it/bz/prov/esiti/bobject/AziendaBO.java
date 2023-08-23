package it.bz.prov.esiti.bobject;

import javax.persistence.Column;

import it.bz.prov.esiti.entity.Azienda;

/**
 * Business entity che rappresenta la singola azienda
 * 
 * @author bpettazzoni
 *
 */

public class AziendaBO {

	private String _cuaa;
	private String _ragioneSociale;
	private String _provincia;
	private String _localita;
	private String _sorgente;
	private String _indirizzo;
	private String _cap;
	private String _pec;
	
	
	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
	
	
	public AziendaBO()
	{
		_cuaa = "";
		_ragioneSociale = "";
		_sorgente = "";
		_provincia = "";
		_localita = "";
		_indirizzo = "";
		_cap = "";
		_pec = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto jpa e lo trasforma in un business object (BO)
	 * @param azienda
	 */
	public AziendaBO(Azienda azienda)
	{
		_cuaa = azienda.get_cuaa();
		_ragioneSociale = azienda.get_ragioneSociale();
		_sorgente = azienda.get_sorgente();
		_provincia = azienda.get_provincia();
		_localita = azienda.get_localita();
		_indirizzo = azienda.get_indirizzo();
		_cap = azienda.get_cap();
		_pec = azienda.get_pec();
		
	}
	
	public AziendaBO(String cuaa, String ragioneSociale){
		this._cuaa = cuaa;
		this._ragioneSociale = ragioneSociale;
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return AziendaBO
	 */
	public AziendaBO clone()
	{
		AziendaBO azienda = new AziendaBO();
		azienda.set_cuaa(_cuaa);
		azienda.set_ragioneSociale(_ragioneSociale);
		azienda.set_provincia(_provincia);
		azienda.set_sorgente(_sorgente);
		azienda.set_localita(_localita);
		azienda.set_indirizzo(_indirizzo);
		azienda.set_cap(_cap);
		azienda.set_pec(_pec);
		return azienda;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param azienda
	 */
	public void recovery(AziendaBO azienda)
	{
		_cuaa = azienda.get_cuaa();
		_provincia = azienda.get_provincia();
		_localita = azienda.get_localita();
		_ragioneSociale = azienda.get_ragioneSociale();
		_sorgente = azienda.get_sorgente();
		_indirizzo = azienda.get_indirizzo();
		_cap = azienda.get_cap();
		_pec = azienda.get_pec();
		
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return Azienda
	 */
	public Azienda getEntity()
	{
		Azienda azienda = new Azienda();
		azienda.set_cuaa(_cuaa);
		azienda.set_ragioneSociale(_ragioneSociale);
		azienda.set_provincia(_provincia);
		azienda.set_localita(_localita);
		azienda.set_sorgente(_sorgente);
		azienda.set_indirizzo(_indirizzo);
		azienda.set_cap(_cap);
		azienda.set_pec(_pec);
		return azienda;
	}
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param azienda
	 */
	public void setEntity(Azienda azienda)
	{
		azienda.set_cuaa(_cuaa);
		azienda.set_ragioneSociale(_ragioneSociale);
		azienda.set_provincia(_provincia);
		azienda.set_localita(_localita);
		azienda.set_sorgente(_sorgente);
		azienda.set_indirizzo(_indirizzo);
		azienda.set_cap(_cap);
		azienda.set_pec(_pec);
	}

	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/
	

	public synchronized String get_cuaa() {
		return _cuaa;
	}

	public synchronized String get_ragioneSociale() {
		return _ragioneSociale;
	}
	
	public synchronized void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa.trim();
	}

	public synchronized void set_ragioneSociale(String _ragioneSociale) {
		this._ragioneSociale = _ragioneSociale.trim();
	}
	
	public synchronized String get_sorgente() {
		return _sorgente;
	}
	
	public synchronized void set_sorgente(String sorgente) {
		this._sorgente = sorgente.trim();
	}

	public void set_provincia(String _provincia) {
		this._provincia = _provincia;
	}

	public String get_provincia() {
		return _provincia;
	}
	
	public void set_localita(String _localita){
		this._localita = _localita;
	}
	public String get_localita(){
		return _localita;
	}

	public String get_indirizzo() {
		return _indirizzo;
	}

	public void set_indirizzo(String _indirizzo) {
		this._indirizzo = _indirizzo;
	}

	public String get_cap() {
		return _cap;
	}

	public void set_cap(String _cap) {
		this._cap = _cap;
	}

	public String get_pec() {
		return _pec;
	}

	public void set_pec(String _pec) {
		this._pec = _pec;
	}
	
}
