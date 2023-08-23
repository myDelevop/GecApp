package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.AttoCondizionalita;

/**
 * Business entity che rappresenta l'anagrafica del singolo atto di condizionalità. 
 * 
 * @author bpettazzoni
 *
 */

public class AttoCondizionalitaBO {

	private String _codCond;
	private String _attoCond;
	private String _attoDescr;
	private int _campagnaInizio;
	private int _campagnaFine;
	private String _flg_valida;
	
	
	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
	
	
	public AttoCondizionalitaBO()
	{
		_codCond = "";
		_attoCond = "";
		_attoDescr = "";
		_flg_valida="";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto jpa e lo trasforma in un business object (BO)
	 * @param atto
	 */
	public AttoCondizionalitaBO(AttoCondizionalita atto)
	{
		_attoCond = atto.get_attoCond();
		_codCond = atto.get_codCond();
		_attoDescr = atto.get_atto_descr();
		_flg_valida=atto.get_flg_valida();
		_campagnaInizio = atto.get_campagnaInizio();
		_campagnaFine = atto.get_campagnaFine();
	}
	
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return AttoCondizionalitaBO
	 */
	public AttoCondizionalitaBO clone()
	{
		AttoCondizionalitaBO atto = new AttoCondizionalitaBO();
		atto.set_attoCond(_attoCond);
		atto.set_codCond(_codCond);
		atto.set_attoDescr(_attoDescr);
		atto.set_campagnaInizio(_campagnaInizio);
		atto.set_campagnaFine(_campagnaFine);
		atto.set_flg_valida(_flg_valida);
		return atto;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param atto
	 */
	public void recovery(AttoCondizionalitaBO atto)
	{
		_codCond = atto.get_codCond();
		_attoCond = atto.get_attoCond();
		_attoDescr = atto.get_attoDescr();
		_flg_valida=atto.get_flg_valida();
		_campagnaInizio = atto.get_campagnaInizio();
		_campagnaFine = atto.get_campagnaFine();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return AttoCondizionalita
	 */
	public AttoCondizionalita getEntity()
	{
		AttoCondizionalita atto = new AttoCondizionalita();
		atto.set_attoCond(_attoCond);
		atto.set_codCond(_codCond);
		atto.set_atto_descr(_attoDescr);
		atto.set_campagnaInizio(_campagnaInizio);
		atto.set_campagnaFine(_campagnaFine);
		atto.set_flg_valida(_flg_valida);
		return atto;
	}
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param atto
	 */
	public void setEntity(AttoCondizionalita atto)
	{
		atto.set_attoCond(_attoCond);
		atto.set_codCond(_codCond);
		atto.set_atto_descr(_attoDescr);
		atto.set_campagnaInizio(_campagnaInizio);
		atto.set_campagnaFine(_campagnaFine);
		atto.set_flg_valida(_flg_valida);
	}

	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/

	public void set_codCond(String _codCond) {
		this._codCond = _codCond;
	}

	public String get_codCond() {
		return _codCond;
	}

	public void set_attoCond(String _attoCond) {
		this._attoCond = _attoCond;
	}

	public String get_attoCond() {
		return _attoCond;
	}

	public void set_attoDescr(String _attoDescr) {
		this._attoDescr = _attoDescr;
	}

	public String get_attoDescr() {
		return _attoDescr;
	}

	public int get_campagnaInizio() {
		return _campagnaInizio;
	}

	public void set_campagnaInizio(int _campagnaInizio) {
		this._campagnaInizio = _campagnaInizio;
	}

	public int get_campagnaFine() {
		return _campagnaFine;
	}

	public void set_campagnaFine(int _campagnaFine) {
		this._campagnaFine = _campagnaFine;
	}

	public String get_flg_valida() {
		return _flg_valida;
	}

	public void set_flg_valida(String _flg_valida) {
		this._flg_valida = _flg_valida;
	}

}
