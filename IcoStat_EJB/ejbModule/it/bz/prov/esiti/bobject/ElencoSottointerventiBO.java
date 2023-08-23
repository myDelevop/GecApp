package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.Sottointervento;

import java.util.ArrayList;


public class ElencoSottointerventiBO {

	private ArrayList<SottointerventoBO> _listSottoint;
	
	
	/**
	 * Costruttore 
	 */
	public ElencoSottointerventiBO(){
		_listSottoint = new ArrayList<SottointerventoBO>();	
	}


	public void set_listSottoint(ArrayList<Sottointervento> _listSottoint) {
		this._listSottoint.clear();
		for (Sottointervento sottointervento : _listSottoint) {
			this._listSottoint.add(new SottointerventoBO(sottointervento));
		}
	}


	public ArrayList<SottointerventoBO> get_listSottoint() {
		return _listSottoint;
	}
	

}
