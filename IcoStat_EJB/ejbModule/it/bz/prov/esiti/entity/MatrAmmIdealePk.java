package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key per la tabella dei capi allevati
 * 
 * @author bpettazzoni
 *
 */


public class MatrAmmIdealePk implements Serializable{
	private static final long serialVersionUID = -3317500460300872579L;
	private String _cuaa;
	private String _campagna;
	
	public MatrAmmIdealePk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof MatrAmmIdealePk)
		{
			MatrAmmIdealePk matrAmmIdeale = (MatrAmmIdealePk) oggetto;
			return (matrAmmIdeale.get_cuaa().equals(_cuaa) && matrAmmIdeale.get_campagna().equals(_campagna));
		}
		return false;
	}
	 
	public int hashCode()
	{
		return super.hashCode();
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
