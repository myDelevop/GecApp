package it.bz.prov.esiti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity che rappresenta l'esito del controllo di ammissibilità zootecnia per bovini
 * 
 * @author bpettazzoni
 *
 */

@Entity
@IdClass(EsitoZootecniaEsitoAmmBovPk.class)
@Table(name = "APP_ZOOT_ESI_AMM_BOV_INT")
@NamedQueries({
	@NamedQuery(
			name = "EsitoZootecniaEsitoAmmBov.selectAll",
			query = "SELECT d FROM EsitoZootecniaEsitoAmmBov d ORDER BY d._cuaa, d._campagna, d._domanda"),
	@NamedQuery(
			name = "EsitoZootecniaEsitoAmmBov.getEsitoBy_CUAA_Campagna_Domanda",
			query = "SELECT d " +
			"FROM EsitoZootecniaEsitoAmmBov d " +
			"WHERE d._cuaa=:cuaa " +
			"AND d._campagna= :campagna " +
			"AND d._domanda= :domanda " +
			"ORDER BY d._cuaa, d._campagna, d._domanda"),
	@NamedQuery(
			name = "EsitoZootecniaEsitoAmmBov.getEsitoIntBy_CUAA_Campagna_Domanda_Int",
			query = "SELECT d " +
			"FROM EsitoZootecniaEsitoAmmBov d " +
			"WHERE d._cuaa=:cuaa " +
			"AND d._campagna= :campagna " +
			"AND d._domanda= :domanda " +
			"AND d._intervento= :intervento " +
			"ORDER BY d._cuaa, d._campagna, d._domanda, d._intervento")
})
public class EsitoZootecniaEsitoAmmBov implements Serializable {

	private static final long serialVersionUID = 8325269350530007000L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;

	@Id
	@Column(name = "DOMANDA")
	private String _domanda;
	
	@Id
	@Column(name = "CAMPAGNA")
	private int _campagna;
	
	@Id
	@Column(name = "INTERVENTO")
	private String _intervento;
	
	@Id
	@Column(name = "CAMPO")
	private String _campo;
	
	@Column(name = "VALORE")
	private String _valore;
	
	public EsitoZootecniaEsitoAmmBov() {
		_cuaa = "";
		_domanda = "";
	}

	public EsitoZootecniaEsitoAmmBov(String cuaa, int campagna, String domanda, String intervento, String campo, String valore) {
		_cuaa = cuaa;
		_campagna = campagna;
		_domanda = domanda;
		_intervento = intervento;
		_campo = campo;
		_valore = valore;
	}
	
	public String get_cuaa() {
		return _cuaa;
	}

	public void set_cuaa(String _cuaa) {
		_cuaa = _cuaa == null ? "" : _cuaa;
		this._cuaa = _cuaa;
	}

	public String get_domanda() {
		return _domanda;
	}

	public void set_domanda(String _domanda) {
		_domanda = _domanda == null ? "" : _domanda;
		this._domanda = _domanda;
	}

	public int get_campagna() {
		return _campagna;
	}

	public void set_campagna(int _campagna) {
		this._campagna = _campagna;
	}

	public String get_intervento() {
		return _intervento;
	}

	public void set_intervento(String _intervento) {
		_intervento = _intervento == null ? "" : _intervento;
		this._intervento = _intervento;
	}

	public String get_campo() {
		return _campo;
	}

	public void set_campo(String _campo) {
		_campo = _campo == null ? "" : _campo;
		this._campo = _campo;
	}

	public String get_valore() {
		return _valore;
	}

	public void set_valore(String _valore) {
		_valore = _valore == null ? "" : _valore;
		this._valore = _valore;
	}
	
}
