package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella degli esiti impegni RM FIT E FER
 * 
 * @author lattana
 *
 */


public class EsitoImpegniRMFITFERPk implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -360221432058045246L;
	private String _domanda;
	private String _misura;
	
public EsitoImpegniRMFITFERPk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof EsitoImpegniRMFITFERPk)
		{
			EsitoImpegniRMFITFERPk esito = (EsitoImpegniRMFITFERPk) oggetto;
			return (esito._domanda.equals(_domanda) && esito._misura.equals(_misura));
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
	
	public void set_misura(String _misura) {
		this._misura = _misura;
	}
	public String get_misura() {
		return _misura;
	}
	
}
