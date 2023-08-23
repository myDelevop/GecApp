package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella degli esiti di condizionalità per atto
 * 
 * @author bpettazzoni
 *
 */


public class EsitoCondizionalitaAttoPk implements Serializable {

	private static final long serialVersionUID = -3905578009694305973L;
	
//	private Azienda _azienda;
	private String _cuaa;
	private String _campagna;
	private String _cod_cond;
	private String _atto_contr;	
	private String _tipoControllo;

	
	public EsitoCondizionalitaAttoPk(){}	

	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof EsitoCondizionalitaAttoPk)
		{
			EsitoCondizionalitaAttoPk esito = (EsitoCondizionalitaAttoPk) oggetto;
			return (esito._campagna.equals(_campagna) && esito._cuaa.equals(_cuaa) && 
					esito._cod_cond.equals(_cod_cond) && esito._atto_contr.equals(_atto_contr)
					&& esito._tipoControllo.equals(_tipoControllo));
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
	public void set_cod_cond(String _cod_cond) {
		this._cod_cond = _cod_cond;
	}
	public String get_cod_cond() {
		return _cod_cond;
	}
	public void set_atto_contr(String _atto_contr) {
		this._atto_contr = _atto_contr;
	}
	public String get_atto_contr() {
		return _atto_contr;
	}

	public String get_tipoControllo() {
		return _tipoControllo;
	}

	public void set_tipoControllo(String _tipoControllo) {
		this._tipoControllo = _tipoControllo;
	}
		
//	public void set_azienda(Azienda _azienda) {
//		this._azienda = _azienda;
//	}
//
//	public Azienda get_azienda() {
//		return _azienda;
//	}

}
