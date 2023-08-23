package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella delgli esiti uba
 * 
 * @author bpettazzoni
 *
 */


public class UBAAmmissibilitaPk implements Serializable{

	private static final long serialVersionUID = -1702773896682838435L;
	private String _domanda;
	private String _intervento;
	
	public UBAAmmissibilitaPk()
	{
		_domanda = "";
		_intervento = "";
	}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof UBAAmmissibilitaPk)
		{
			UBAAmmissibilitaPk uba = (UBAAmmissibilitaPk) oggetto;
			return (uba._intervento.equals(_intervento) && uba._domanda.equals(_domanda));
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

}

