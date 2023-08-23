package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella degli esiti impegni RM FIT E FER Extracampione
 * 
 * @author GIMS
 *
 */


public class EsitoImpegniRMFITFERExtraPk implements Serializable {

	private static final long serialVersionUID = 7776445913822350203L;
	private String _domanda;
	private String _misura;
	
public EsitoImpegniRMFITFERExtraPk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof EsitoImpegniRMFITFERExtraPk)
		{
			EsitoImpegniRMFITFERExtraPk esito = (EsitoImpegniRMFITFERExtraPk) oggetto;
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
