package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.Campione;

import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco delle domande a campione
 * 
 * @author bpettazzoni
 *
 */

public class ElencoCampioneBO {

	private Vector<CampioneBO> _listCampione;
	
	
	public ElencoCampioneBO(){
		_listCampione = new Vector<CampioneBO>();
	}
	
	public void addEsito(CampioneBO campione){
		_listCampione.add(campione);
	}
	
	public int size(){
		return _listCampione.size();
	}
	
	public List<CampioneBO> get_listCampione(){
		return _listCampione;
	}
	
	public void set_listCampione(List<Campione> list){
		_listCampione.clear();
		for (Campione campione : list) {
			_listCampione.add(new CampioneBO(campione));
		}
	}
	
	public void remove(CampioneBO campione)
	{
		_listCampione.remove(campione);
	}
	
}
