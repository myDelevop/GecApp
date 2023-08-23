package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella della tutela acque
 * 
 * @author bpettazzoni
 *
 */


public class TutelaAcquePk implements Serializable{

	private static final long serialVersionUID = -3934394858602645186L;
	
	private String _cuaa;
	private String _campagna;
	
	public TutelaAcquePk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof TutelaAcquePk)
		{
			TutelaAcquePk tutelaAcque = (TutelaAcquePk) oggetto;
			return (tutelaAcque._campagna == _campagna && tutelaAcque._cuaa.equals(_cuaa));
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
