package it.bz.prov.esiti.bobject;

/**
 * Business entity che rappresenta i dettagli della domanda unica
 * 
 * @author bpettazzoni
 *
 */

public class DettagliDUBO {
	private String _impRich;
	private String _impLiq;
	
	/**
	 * Costruttore
	 */
	public DettagliDUBO(){
		_impRich = "";
		_impLiq = "";
	}

	public void set_impRich(String _impRich) {
		if(_impRich == null) _impRich = "";
		this._impRich = _impRich.trim();
	}

	public String get_impRich() {
		return _impRich;
	}

	public void set_impLiq(String _impLiq) {
		if(_impLiq == null) _impLiq = "";
		this._impLiq = _impLiq.trim();
	}

	public String get_impLiq() {
		return _impLiq;
	}
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return DettagliDUBO
	 */
	public DettagliDUBO clone()
	{
		DettagliDUBO dettaglioDU = new DettagliDUBO();
		dettaglioDU.set_impLiq(_impLiq);
		dettaglioDU.set_impRich(_impRich);
		return dettaglioDU;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param dettaglioDU
	 */
	public void recovery(DettagliDUBO dettaglioDU)
	{
		_impLiq = dettaglioDU.get_impLiq();
		_impRich = dettaglioDU.get_impRich();
	}
	

}
