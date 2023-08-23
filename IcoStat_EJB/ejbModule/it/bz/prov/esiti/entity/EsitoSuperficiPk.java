package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella degli esiti a superficie
 * 
 * @author bpettazzoni
 *
 */


public class EsitoSuperficiPk implements Serializable{
	
	private static final long serialVersionUID = -2350265826251990874L;

	private String _domanda;
	private String _intervento;
	private String _sottointervento;
	
	public EsitoSuperficiPk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof EsitoSuperficiPk)
		{
			EsitoSuperficiPk esito = (EsitoSuperficiPk) oggetto;
			return (esito.get_domanda().equals(_domanda) &&
					esito._intervento.equals(_intervento) && esito._sottointervento.equals(_sottointervento));
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
	public void set_intervento(String _intervento) {
		this._intervento = _intervento;
	}
	public String get_intervento() {
		return _intervento;
	}
	public void set_sottointervento(String _sottointervento) {
		this._sottointervento = _sottointervento;
	}
	public String get_sottointervento() {
		return _sottointervento;
	}

}
