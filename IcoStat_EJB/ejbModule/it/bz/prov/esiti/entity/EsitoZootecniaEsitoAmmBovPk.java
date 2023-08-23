package it.bz.prov.esiti.entity;

import java.io.Serializable;

/**
 * Entity che rappresenta la primary key della tabella dell'esito del controllo zootecnia per ammissibilità bovini
 * 
 * @author bpettazzoni
 *
 */


public class EsitoZootecniaEsitoAmmBovPk implements Serializable {

	private static final long serialVersionUID = 8710974090862985176L;

	private String _domanda;
	private String _cuaa;
	private int _campagna;
	private String _intervento;
	private String _campo;
	
public EsitoZootecniaEsitoAmmBovPk(){}
	
	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof EsitoZootecniaEsitoAmmBovPk)
		{
			EsitoZootecniaEsitoAmmBovPk esito = (EsitoZootecniaEsitoAmmBovPk) oggetto;
			return (esito._domanda.equals(_domanda) 
					&& esito._cuaa.equals(_cuaa) 
					&& esito._campagna == _campagna
					&& esito._intervento.equals(_intervento)
					&& esito._campo.equals(_campo)
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

	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}

	public String get_cuaa() {
		return _cuaa;
	}

	public void set_campagna(int _campagna) {
		this._campagna = _campagna;
	}

	public int get_campagna() {
		return _campagna;
	}

	public String get_intervento() {
		return _intervento;
	}

	public void set_intervento(String _intervento) {
		this._intervento = _intervento;
	}

	public String get_campo() {
		return _campo;
	}

	public void set_campo(String _campo) {
		this._campo = _campo;
	}
}
