package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella delle anagrafiche 
 * 
 * @author bpettazzoni
 *
 */


public class ValoreAnagraficaPk implements Serializable{

	private static final long serialVersionUID = -687194003197830270L;
	private String _campo;
	private String _valore;
	private int _campagnaFineVal;
	private int _campagnaInizioVal;
	
	public ValoreAnagraficaPk()
	{
		set_campo("");
		set_valore("");
	}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof ValoreAnagraficaPk)
		{
			ValoreAnagraficaPk va = (ValoreAnagraficaPk) oggetto;
			return (va.get_campo().equals(get_campo()) && va.get_valore().equals(get_valore()) && 
					va.get_campagnaFineVal() == get_campagnaFineVal() && va.get_campagnaInizioVal() == get_campagnaInizioVal());
		}
		return false;
	}
	 
	public int hashCode()
	{
		return super.hashCode();
	}

	public void set_campo(String _campo) {
		this._campo = _campo;
	}

	public String get_campo() {
		return _campo;
	}

	public void set_valore(String _valore) {
		this._valore = _valore;
	}

	public String get_valore() {
		return _valore;
	}

	public void set_campagnaFineVal(int _campagnaFineVal) {
		this._campagnaFineVal = _campagnaFineVal;
	}

	public int get_campagnaFineVal() {
		return _campagnaFineVal;
	}

	public void set_campagnaInizioVal(int _campagnaInizioVal) {
		this._campagnaInizioVal = _campagnaInizioVal;
	}

	public int get_campagnaInizioVal() {
		return _campagnaInizioVal;
	}


}
