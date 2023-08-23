package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella delgli esiti uba
 * 
 * @author bpettazzoni
 *
 */


public class UbaPk implements Serializable{

	private static final long serialVersionUID = -3890637589485671080L;
	
//	private String _cuaa;
	private String _domanda;
//	private Domanda _domanda;
	private String _intervento;
	
	public UbaPk()
	{
//		_cuaa= "";
		_domanda = "";
//		_domanda = new Domanda();
		_intervento = "";
	}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof UbaPk)
		{
			UbaPk uba = (UbaPk) oggetto;
			return (uba._intervento.equals(_intervento) && uba._domanda.equals(_domanda));
		}
		return false;
	}
	 
	public int hashCode()
	{
		return super.hashCode();
	}

//	public void set_cuaa(String _cuaa) {
//		this._cuaa = _cuaa;
//	}
//
//	public String get_cuaa() {
//		return _cuaa;
//	}

	public void set_domanda(String _domanda) {
		this._domanda = _domanda;
//		this._domanda.set_idDomanda(_domanda);
	}

	public String get_domanda() {
		return _domanda;
//		return _domanda.get_idDomanda();
	}

//	public void set_campagna(int _campagna) {
//		this._campagna = _campagna;
//	}
//
//	public int get_campagna() {
//		return _campagna;
//	}

	public void set_intervento(String _intervento) {
		this._intervento = _intervento;
	}

	public String get_intervento() {
		return _intervento;
	}

}
