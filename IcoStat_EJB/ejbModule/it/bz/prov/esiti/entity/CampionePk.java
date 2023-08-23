package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella del controllo
 * 
 * @author bpettazzoni
 *
 */


public class CampionePk implements Serializable {

	private static final long serialVersionUID = 1016855832517626198L;

	private String _cuaa;
	private String _domanda; 
	private String _campagna;
	private String _categoria;
	
	
	public CampionePk(){}
	
	public void set_campagna(String _campagna) {
		this._campagna = _campagna;
	}
	public String get_campagna() {
		return _campagna;
	}

	public int hashCode()
	{
		return super.hashCode();
	}

	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof CampionePk)
		{
			CampionePk campione = (CampionePk) oggetto;
			return (campione._campagna.equals(_campagna) && campione._cuaa.equals(_cuaa) && 
					campione._domanda.equals(_domanda) && campione._categoria.equals(_categoria));
		}
		return false;
	}

	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}

	public String get_cuaa() {
		return _cuaa;
	}

	public void set_domanda(String _domanda) {
		this._domanda = _domanda;
	}

	public String get_domanda() {
		return _domanda;
	}

	public void set_categoria(String _categoria) {
		this._categoria = _categoria;
	}

	public String get_categoria() {
		return _categoria;
	}
	
	
}
