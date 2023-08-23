package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key per la tabella del sottointervento
 * 
 * @author bpettazzoni
 *
 */


public class SottointerventoPk implements Serializable {

	private static final long serialVersionUID = -8316564033671644511L;
	private String _domanda;
	private String _codiceIntervento;
	private String _codiceSottointervento;
	
public SottointerventoPk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof SottointerventoPk)
		{
			SottointerventoPk sott = (SottointerventoPk) oggetto;
			return (sott._domanda.equals(_domanda) &&
					sott._codiceIntervento.equals(_codiceIntervento) && 
					sott._codiceSottointervento.equals(_codiceSottointervento));
		}
		return false;
	}
	 
	public int hashCode()
	{
		return super.hashCode();
	}
	
	public void set_domanda(String _domanda) {
		this._domanda = _domanda;
	}
	public String get_domanda() {
		return _domanda;
	}

	public void set_codiceIntervento(String _codiceIntervento) {
		this._codiceIntervento = _codiceIntervento;
	}

	public String get_codiceIntervento() {
		return _codiceIntervento;
	}

	public void set_codiceSottointervento(String _codiceSottointervento) {
		this._codiceSottointervento = _codiceSottointervento;
	}

	public String get_codiceSottointervento() {
		return _codiceSottointervento;
	}
	
}
