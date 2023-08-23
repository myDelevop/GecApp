package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella del controllo
 * 
 * @author bpettazzoni
 *
 */


public class ControlloPk implements Serializable {

	private static final long serialVersionUID = 1016855832517626198L;

	private String _cuaa;
//	private Azienda _azienda;
	private String _campagna;
	
	public ControlloPk(){}
	
//	public void set_azienda(Azienda _azienda) {
//		this._azienda = _azienda;
//	}
//	public Azienda get_azienda() {
//		return _azienda;
//	}
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
		if (oggetto instanceof ControlloPk)
		{
			ControlloPk controllo = (ControlloPk) oggetto;
			return (controllo._campagna.equals(_campagna) && 
					controllo._cuaa.equals(_cuaa));
		}
		return false;
	}

	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}

	public String get_cuaa() {
		return _cuaa;
	}
	
	
}
