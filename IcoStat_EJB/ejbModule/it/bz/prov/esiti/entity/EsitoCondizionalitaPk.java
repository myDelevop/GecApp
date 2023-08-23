package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella degli esiti di condizionalità per azienda
 * 
 * @author bpettazzoni
 *
 */


public class EsitoCondizionalitaPk implements Serializable{

	private static final long serialVersionUID = 3205842438365759991L;

	private String _cuaa;
//	private Azienda _azienda;
	private String _campagna;
	
	public EsitoCondizionalitaPk(){}

	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof EsitoCondizionalitaPk)
		{
			EsitoCondizionalitaPk esito = (EsitoCondizionalitaPk) oggetto;
			return (esito._campagna.equals(_campagna) && esito._cuaa.equals(_cuaa) );
		}
		return false;
	}
	 
	public int hashCode()
	{
		return super.hashCode();
	}
	
	public void set_campagna(String _campagna) {
		this._campagna = _campagna;
	}
	public String get_campagna() {
		return _campagna;
	}

	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}

	public String get_cuaa() {
		return _cuaa;
	}


}
