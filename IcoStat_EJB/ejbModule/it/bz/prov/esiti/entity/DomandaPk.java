package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella della domanda
 * 
 * @author bpettazzoni
 *
 */


public class DomandaPk implements Serializable {

	private static final long serialVersionUID = 2834509160815463082L;
	private String _idDomanda;
	private String _misura;
	
	public DomandaPk(){}
	
	public int hashCode()
	{
		return super.hashCode();
	}

	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof DomandaPk)
		{
			DomandaPk domanda = (DomandaPk) oggetto;
			return (domanda._idDomanda.equals(_idDomanda) && 
					domanda._misura.equals(_misura));
		}
		return false;
	}



	public String get_idDomanda() {
		return _idDomanda;
	}
	public void set_idDomanda(String _idDomanda) {
		this._idDomanda = _idDomanda;
	}
	public String get_misura() {
		return _misura;
	}
	public void set_misura(String _misura) {
		this._misura = _misura;
	}

	
}
