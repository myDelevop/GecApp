package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.Controllo;

import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco dei controlli 
 * 
 * @author bpettazzoni
 *
 */

public class ElencoControlloBO {

	private Vector<ControlloBO> _listControlli;
	
	public ElencoControlloBO(){
		_listControlli = new Vector<ControlloBO>();
	}
	
	public void addControllo(ControlloBO controllo){
		_listControlli.add(controllo);
	}
	
	public int size(){
		return _listControlli.size();
	}

	public void set_listControlli(List<Controllo> list) {
		_listControlli.clear();
		for (Controllo controllo : list) {
			_listControlli.add(new ControlloBO(controllo));
		}
	}

	public Vector<ControlloBO> get_listControlli() {
		return _listControlli;
	}
	
	public void remove(ControlloBO controllo)
	{
		_listControlli.remove(controllo);
	}
	
	

}
