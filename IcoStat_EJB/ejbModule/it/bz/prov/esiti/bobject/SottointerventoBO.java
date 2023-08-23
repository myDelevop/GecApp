package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.Sottointervento;

/**
 * Business entity che rappresenta il singolo sottointervento per una data domanda 
 * 
 * @author bpettazzoni
 *
 */

public class SottointerventoBO {
	
	private String _domanda;
	private String _codiceIntervento;
	private String _descrizioneIntervento;
	private String _codiceSottointervento;
	private String _descrizioneSottointervento;
	private String _campagna;
	private String _misura;
	
	/**
	 * Costruttore
	 */
	public SottointerventoBO() {
		_codiceIntervento = "";
		_descrizioneIntervento = "";
		_codiceSottointervento = "";
		_descrizioneSottointervento = "";
		_domanda = "";
		_campagna= "";
		_misura="";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param sottointervento
	 */
	public SottointerventoBO(Sottointervento sottointervento) {
		_codiceIntervento = sottointervento.get_codiceIntervento();
		_descrizioneIntervento = sottointervento.get_descrizioneIntervento();
		_codiceSottointervento = sottointervento.get_codiceSottointervento();
		_descrizioneSottointervento = sottointervento.get_descrizioneSottointervento();
		_domanda = sottointervento.get_domanda();
		_misura= sottointervento.get_misura();
		_campagna= sottointervento.get_campagna();
	}

	public void set_codiceIntervento(String _codiceIntervento) {
		this._codiceIntervento = _codiceIntervento;
	}

	public String get_codiceIntervento() {
		return _codiceIntervento;
	}

	public void set_descrizioneIntervento(String _descrizioneIntervento) {
		this._descrizioneIntervento = _descrizioneIntervento;
	}

	public String get_descrizioneIntervento() {
		return _descrizioneIntervento;
	}

	public void set_codiceSottointervento(String _codiceSottointervento) {
		this._codiceSottointervento = _codiceSottointervento;
	}

	public String get_codiceSottointervento() {
		return _codiceSottointervento;
	}

	public void set_descrizioneSottointervento(
			String _descrizioneSottointervento) {
		this._descrizioneSottointervento = _descrizioneSottointervento;
	}

	public String get_descrizioneSottointervento() {
		return _descrizioneSottointervento;
	}

	public void set_domanda(String _domanda) {
		this._domanda = _domanda;
	}

	public String get_domanda() {
		return _domanda;
	}

	public String get_campagna() {
		return _campagna;
	}

	public void set_campagna(String _campagna) {
		this._campagna = _campagna;
	}

	public String get_misura() {
		return _misura;
	}

	public void set_misura(String _misura) {
		this._misura = _misura;
	}	

}
