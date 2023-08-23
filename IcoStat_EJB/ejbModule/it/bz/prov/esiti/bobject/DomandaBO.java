package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.Domanda;

/**
 * Business entity che rappresenta la domanda  
 * 
 * @author bpettazzoni
 *
 */

public class DomandaBO {

	private String _idDomanda;
	private String _idDomandaAgea;
	private String _campagna;
	private String _misura;
	private String _cuaa;
	private String _impRich;
	private String _impLiq;
	
	
	public DomandaBO(){
		_idDomanda = "";
		_campagna = "";
		_misura = "";
		_cuaa = "";
		_idDomandaAgea = "";
		_impLiq = "";
		_impRich = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param domanda
	 */
	public DomandaBO(Domanda domanda){
		_idDomanda = domanda.get_idDomanda();
		_campagna = domanda.get_campagna();
		_misura = domanda.get_misura();
		_cuaa = domanda.get_cuaa();
		_idDomandaAgea = domanda.get_idDomandaAgea();
		_impLiq = domanda.get_impLiq();
		_impRich = domanda.get_impRich();
	}
	
	public DomandaBO(String id){
		set_idDomanda(id);
	}

	/**
	 * @param _idDomanda the _idDomanda to set
	 */
	public void set_idDomanda(String _idDomanda) {
		this._idDomanda = _idDomanda.trim();
	}

	/**
	 * @return the _idDomanda
	 */
	public String get_idDomanda() {
		return _idDomanda;
	}
	
	public void set_campagna(String campagna){
		_campagna = campagna.trim();
	}
	
	public String get_campagna(){
		return _campagna;
	}
	
	public void set_misura(String misura){
		if(misura == null) misura = "";
		_misura = misura.trim();
	}
	
	public String get_misura(){
		return _misura;
	}
	
	public void set_cuaa(String cuaa){
		_cuaa = cuaa.trim();
	}
	
	public String get_cuaa(){
		return _cuaa;
	}

	public void set_idDomandaAgea(String _idDomandaAgea) {
		if(_idDomandaAgea == null) _idDomandaAgea = "";
		this._idDomandaAgea = _idDomandaAgea.trim();
	}

	public String get_idDomandaAgea() {
		return _idDomandaAgea;
	}

	public void set_impRich(String _impRich) {
		if(_impRich == null) _impRich = "";
		this._impRich = _impRich.replace(".", ",");
	}

	public String get_impRich() {
		return _impRich;
	}

	public void set_impLiq(String _impLiq) {
		if(_impLiq == null) _impLiq = "";
		this._impLiq = _impLiq.replace(".", ",");
	}

	public String get_impLiq() {
		return _impLiq;
	}
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return DomandaBO
	 */
	public DomandaBO clone()
	{
		DomandaBO domanda = new DomandaBO();
		domanda.set_campagna(_campagna);
		domanda.set_cuaa(_cuaa);
		domanda.set_idDomanda(_idDomanda);
		domanda.set_idDomandaAgea(_idDomandaAgea);
		domanda.set_impLiq(_impLiq);
		domanda.set_impRich(_impRich);
		domanda.set_misura(_misura);
		return domanda;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param domanda
	 */
	public void recovery(DomandaBO domanda)
	{
		_campagna = domanda.get_campagna();
		_cuaa = domanda.get_cuaa();
		_idDomanda = domanda.get_idDomanda();
		_idDomandaAgea = domanda.get_idDomandaAgea();
		_impLiq = domanda.get_impLiq();
		_impRich = domanda.get_impRich();
		_misura = domanda.get_misura();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return Domanda
	 */
	public Domanda getEntity()
	{
		Domanda domanda = new Domanda();
		domanda.set_campagna(_campagna);
//		domanda.get_azienda().set_cuaa(_cuaa);
		domanda.set_cuaa(_cuaa);
		domanda.set_idDomanda(_idDomanda);
		domanda.set_idDomandaAgea(_idDomandaAgea);
		domanda.set_impLiq(_impLiq);
		domanda.set_impRich(_impRich);
		domanda.set_misura(_misura);
		return domanda;
	}
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param domanda
	 */
	public void setEntity(Domanda domanda)
	{
		domanda.set_campagna(_campagna);
		domanda.set_cuaa(_cuaa);
		domanda.set_idDomanda(_idDomanda);
		domanda.set_idDomandaAgea(_idDomandaAgea);
		domanda.set_impLiq(_impLiq);
		domanda.set_impRich(_impRich);
		domanda.set_misura(_misura);
	}
}
