package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella degli esiti impegni
 * 
 * @author bpettazzoni
 *
 */


public class EsitoImpegniPk implements Serializable {

	private static final long serialVersionUID = 8710974090862985176L;

//	private Domanda _domanda;
	private String _domanda;
	private String _intervento;
	private String _sottointervento;
	private String _controllo_estivo;
	
public EsitoImpegniPk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof EsitoImpegniPk)
		{
			EsitoImpegniPk esito = (EsitoImpegniPk) oggetto;
			return (esito._domanda.equals(_domanda) &&
					esito._intervento.equals(_intervento) && 
					esito._sottointervento.equals(_sottointervento) &&
					esito._controllo_estivo.equals(_controllo_estivo)
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
//		this._domanda.set_idDomanda(_domanda);
	}
	public String get_domanda() {
		return _domanda;
//		return _domanda.get_idDomanda();
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

	public String get_controllo_estivo() {
		return _controllo_estivo;
	}

	public void set_controllo_estivo(String _controllo_estivo) {
		this._controllo_estivo = _controllo_estivo;
	}
}
