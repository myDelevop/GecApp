package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.ValoreAnagrafica;

/**
 * Business entity che rappresenta il singolo valore di anagrafica
 * 
 * @author bpettazzoni
 *
 */

public class ValoreAnagraficaBO {
	
	private String _valore;
	private String _campo;
	private int _campagnaInizioVal;
	private int _campagnaFineVal;
	private String _note;
	
	
	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
	
	
	/**
	 * costruttore senza parametri
	 */
	public ValoreAnagraficaBO()
	{
		_valore = "";
		_campo = "";
		_note = "";
	}
	
	/**
	 * costruttore con parametri
	 * @param va é l'oggetto entity
	 */
	public ValoreAnagraficaBO(ValoreAnagrafica va)
	{
		_campo = va.get_campo();
		_valore = va.get_valore();
		_note = va.get_note();
		_campagnaInizioVal = va.get_campagnaInizioVal();
		_campagnaFineVal = va.get_campagnaFineVal();
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return ValoreAnagraficaBO
	 */
	public ValoreAnagraficaBO clone()
	{
		ValoreAnagraficaBO va = new ValoreAnagraficaBO();
		va.set_campo(_campo);
		va.set_valore(_valore);
		va.set_campagnaInizioVal(_campagnaInizioVal);
		va.set_campagnaFineVal(_campagnaFineVal);
		va.set_note(_note);
		return va;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param va
	 */
	public void recovery(ValoreAnagraficaBO va)
	{
		_campo = va.get_campo();
		_valore = va.get_valore();
		_note = va.get_note();
		_campagnaInizioVal = va.get_campagnaInizioVal();
		_campagnaFineVal = va.get_campagnaFineVal();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return ValoreAnagrafica
	 */
	public ValoreAnagrafica getEntity()
	{
		ValoreAnagrafica va = new ValoreAnagrafica();
		va.set_campo(_campo);
		va.set_valore(_valore);
		va.set_note(_note);
		va.set_campagnaInizioVal(_campagnaInizioVal);
		va.set_campagnaFineVal(_campagnaFineVal);
		return va;
	}
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param valoreAnagrafica
	 */
	public void setEntity(ValoreAnagrafica valoreAnagrafica)
	{
		valoreAnagrafica.set_campo(_campo);
		valoreAnagrafica.set_valore(_valore);
		valoreAnagrafica.set_note(_note);
		valoreAnagrafica.set_campagnaInizioVal(_campagnaInizioVal);
		valoreAnagrafica.set_campagnaFineVal(_campagnaFineVal);
	}
	
	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/
	

	public void set_valore(String _valore) {
		this._valore = _valore;
	}

	public String get_valore() {
		return _valore;
	}

	public void set_campagnaInizioVal(int _campagnaInizioVal) {
		this._campagnaInizioVal = _campagnaInizioVal;
	}

	public int get_campagnaInizioVal() {
		return _campagnaInizioVal;
	}

	public void set_campagnaFineVal(int _campagnaFineVal) {
		this._campagnaFineVal = _campagnaFineVal;
	}

	public int get_campagnaFineVal() {
		return _campagnaFineVal;
	}

	public void set_campo(String _campo) {
		this._campo = _campo;
	}

	public String get_campo() {
		return _campo;
	}

	public void set_note(String _note) {
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

}
