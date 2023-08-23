package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.Settings;

public class SettingsBO {

	private String _chiave;
	private String _valore;
	
	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
	
	public SettingsBO() {
		_chiave = "";
		_valore = "";
	}
	
	public SettingsBO(Settings settings) {
		_chiave = settings.get_chiave();
		_valore = settings.get_valore();
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	public SettingsBO clone() {
		SettingsBO s = new SettingsBO();
		s.set_chiave(_chiave);
		s.set_valore(_valore);
		return s;
	}
	
	public void recovery(SettingsBO s) {
		_chiave = s.get_chiave();
		_valore = s.get_valore();
	}
	
	public Settings getEntity() {
		Settings s = new Settings();
		s.set_chiave(_chiave);
		s.set_valore(_valore);
		return s;
	}
	
	public void setEntity(Settings s){
		s.set_chiave(_chiave);
		s.set_valore(_valore);
	}
	
	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/
	
	public String get_chiave() {
		return _chiave;
	}

	public void set_chiave(String _chiave) {
		this._chiave = _chiave;
	}

	public String get_valore() {
		return _valore;
	}

	public void set_valore(String _valore) {
		this._valore = _valore;
	}

}
