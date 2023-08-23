package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella degli esiti impegni
 * 
 * @author bpettazzoni
 *
 */


public class EsitoImpegniExtraPk implements Serializable {

	private static final long serialVersionUID = -5552172012942279403L;

	private String _domanda;
	private String _intervento;
	private String _sottointervento;
	private String _impegno;
	
public EsitoImpegniExtraPk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof EsitoImpegniPk)
		{
			EsitoImpegniExtraPk esito = (EsitoImpegniExtraPk) oggetto;
			return (esito._domanda.equals(_domanda) &&
					esito._intervento.equals(_intervento) && 
					esito._sottointervento.equals(_sottointervento) &&
					esito._impegno.equals(_impegno)				
					);
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
	
	public void set_intervento(String _intervento) {
		this._intervento = _intervento;
	}
	public String get_intervento() {
		return _intervento;
	}
	public void set_sottointervento(String _sottointervento) {
		this._sottointervento = _sottointervento;
	}
	public String get_sottointervento() {
		return _sottointervento;
	}

	public String get_impegno() {
		return _impegno;
	}

	public void set_impegno(String _impegno) {
		this._impegno = _impegno;
	}
}
