package it.bz.prov.esiti.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity che rappresenta la vista di ricerca
 * 
 * @author bpettazzoni
 *
 */


@Entity
@Table(name = "OPP_ESITI_APP_V_RICERCA")

public class CampioneAggr implements Serializable{

	private static final long serialVersionUID = -8416214038815524498L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;

	@Id
	@Column(name = "DOMANDA_OPR")
	private String _domandaOpr;
	
	@Column(name = "MISURA")
	private String _misura;
	
	@Column(name = "FLAG_CAMP")
	private String _flagCampione;
	
	@Column(name = "FLAG_CAMP_COND_EXTRA")
	private String _flagExtraCampione;
	
	
	public CampioneAggr()
	{
		_cuaa = "";
		_campagna = "";
		_domandaOpr = "";
		_misura = "";
		_flagCampione = "";
		_flagExtraCampione = "";
	}

	public synchronized String get_cuaa() {
		return _cuaa;
	}
	
	public synchronized void set_cuaa(String _cuaa) {
		_cuaa = _cuaa == null ? "" : _cuaa;
		this._cuaa = _cuaa;
	}

	public void set_campagna(String _campagna) {
		_campagna = _campagna == null ? "" : _campagna;
		this._campagna = _campagna;
	}

	public String get_campagna() {
		return _campagna;
	}

	public void set_domandaOpr(String _domandaOpr) {
		_domandaOpr = _domandaOpr == null ? "" : _domandaOpr;
		this._domandaOpr = _domandaOpr;
	}

	public String get_domandaOpr() {
		return _domandaOpr;
	}

	public void set_misura(String _misura) {
		_misura = _misura == null ? "" : _misura;
		this._misura = _misura;
	}

	public String get_misura() {
		return _misura;
	}

	public void set_flagCampione(String _flagCampione) {
		_flagCampione = _flagCampione == null ? "" : _flagCampione;
		this._flagCampione = _flagCampione;
	}

	public String get_flagCampione() {
		return _flagCampione;
	}

	public void set_flagExtraCampione(String _flagExtraCampione) {
		_flagExtraCampione = _flagExtraCampione == null ? "" : _flagExtraCampione;
		this._flagExtraCampione = _flagExtraCampione;
	}

	public String get_flagExtraCampione() {
		return _flagExtraCampione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_campagna == null) ? 0 : _campagna.hashCode());
		result = prime * result + ((_cuaa == null) ? 0 : _cuaa.hashCode());
		result = prime * result + ((_domandaOpr == null) ? 0 : _domandaOpr.hashCode());
		result = prime * result + ((_flagCampione == null) ? 0 : _flagCampione.hashCode());
		result = prime * result + ((_flagExtraCampione == null) ? 0 : _flagExtraCampione.hashCode());
		result = prime * result + ((_misura == null) ? 0 : _misura.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CampioneAggr))
			return false;
		CampioneAggr other = (CampioneAggr) obj;
		if (_campagna == null) {
			if (other._campagna != null)
				return false;
		} else if (!_campagna.equals(other._campagna))
			return false;
		if (_cuaa == null) {
			if (other._cuaa != null)
				return false;
		} else if (!_cuaa.equals(other._cuaa))
			return false;
		if (_domandaOpr == null) {
			if (other._domandaOpr != null)
				return false;
		} else if (!_domandaOpr.equals(other._domandaOpr))
			return false;
		if (_flagCampione == null) {
			if (other._flagCampione != null)
				return false;
		} else if (!_flagCampione.equals(other._flagCampione))
			return false;
		if (_flagExtraCampione == null) {
			if (other._flagExtraCampione != null)
				return false;
		} else if (!_flagExtraCampione.equals(other._flagExtraCampione))
			return false;
		if (_misura == null) {
			if (other._misura != null)
				return false;
		} else if (!_misura.equals(other._misura))
			return false;
		return true;
	}



	
}
