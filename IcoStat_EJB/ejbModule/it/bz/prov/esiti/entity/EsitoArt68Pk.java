package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella degli esiti impegni
 * 
 * @author bpettazzoni
 *
 */


public class EsitoArt68Pk implements Serializable {
	
	private static final long serialVersionUID = -2619168992626909636L;
	
	private String _cuaa;
	private String _domanda;
	private String _intervento;
	private String _campagna;	
public EsitoArt68Pk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof EsitoArt68Pk)
		{
			EsitoArt68Pk esito = (EsitoArt68Pk) oggetto;
			return (esito._cuaa.equals(_cuaa) && esito._domanda.equals(_domanda) &&
					esito._intervento.equals(_intervento) && esito._campagna.equals(_campagna));
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
	public void set_campagna(String _campagna) {
		this._campagna = _campagna;
	}
	public String get_campagna() {
		return _campagna;
	}
}
