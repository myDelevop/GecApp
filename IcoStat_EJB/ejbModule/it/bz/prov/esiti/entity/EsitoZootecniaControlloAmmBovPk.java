package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella sul controllo zootecnia ammissibilità bovini
 * 
 * @author bpettazzoni
 *
 */


public class EsitoZootecniaControlloAmmBovPk implements Serializable {

	private static final long serialVersionUID = 3324230199494531087L;

	private String _domanda;
	private String _cuaa;
	private String _campagna;
	
public EsitoZootecniaControlloAmmBovPk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof EsitoZootecniaControlloAmmBovPk)
		{
			EsitoZootecniaControlloAmmBovPk controllo = (EsitoZootecniaControlloAmmBovPk) oggetto;
			return (controllo._domanda.equals(_domanda) &&
					controllo._cuaa.equals(_cuaa) && controllo._campagna.equals(_campagna));
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

	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}

	public String get_cuaa() {
		return _cuaa;
	}

	public void set_campagna(String _campagna) {
		this._campagna = _campagna;
	}

	public String get_campagna() {
		return _campagna;
	}
}
