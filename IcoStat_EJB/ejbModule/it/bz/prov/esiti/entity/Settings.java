package it.bz.prov.esiti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@IdClass(SettingsPK.class)
@Table(name = "OPP_ESITI_APP_SETTINGS")
@NamedQueries({
		@NamedQuery(
				name = "Settings.filter",
				query = "SELECT s FROM Settings s WHERE s._chiave = :chiave")
})
public class Settings implements Serializable{

	private static final long serialVersionUID = 4325968287448678222L;

	@Id
	@Column(name = "CHIAVE")
	private String _chiave;
	
	@Id
	@Column(name = "VALORE")
	private String _valore;

	public Settings() {
		_chiave = "";
		_valore = "";
	}
	
	public String get_chiave() {
		return _chiave;
	}

	public void set_chiave(String _chiave) {
		_chiave = _chiave == null ? "" : _chiave;
		this._chiave = _chiave;
	}

	public String get_valore() {
		return _valore;
	}

	public void set_valore(String _valore) {
		_valore = _valore == null ? "" : _valore;
		this._valore = _valore;
	}
}
