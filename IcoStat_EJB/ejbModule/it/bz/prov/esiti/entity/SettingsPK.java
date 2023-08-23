package it.bz.prov.esiti.entity;

import java.io.Serializable;

public class SettingsPK implements Serializable {

	private static final long serialVersionUID = -4400952572101651835L;
	private String _chiave;

	public SettingsPK() {
	}

	public int hashCode()
	{
		return super.hashCode();
	}

	public boolean equals(Object oggetto)
	{
		if (oggetto instanceof SettingsPK)
		{
			SettingsPK settings = (SettingsPK) oggetto;
			return settings._chiave.equals(_chiave);		
		}
		return false;
	}
	
	public String get_chiave() {
		return _chiave;
	}

	public void set_chiave(String _chiave) {
		this._chiave = _chiave;
	}

}
