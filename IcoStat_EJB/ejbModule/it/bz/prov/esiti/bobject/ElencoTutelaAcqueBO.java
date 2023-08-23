package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.TutelaAcque;

import java.util.List;
import java.util.Vector;


/**
 * Business entity che rappresenta l'elenco degli esiti sui controlli della tutela acque
 * 
 * @author bpettazzoni
 *
 */

public class ElencoTutelaAcqueBO {

	private Vector<TutelaAcqueBO> _listTutelaAcque;
	
	public ElencoTutelaAcqueBO(){
		_listTutelaAcque = new Vector<TutelaAcqueBO>();
	}
	
	public void addTutelaAcque(TutelaAcqueBO tutelaAcque){
		_listTutelaAcque.add(tutelaAcque);
	}
	
	public int size(){
		return _listTutelaAcque.size();
	}
	
	public List<TutelaAcqueBO> get_listTutelaAcque(){
		return _listTutelaAcque;
	}
	
	public void set_listTutelaAcque(List<TutelaAcque> list){
		_listTutelaAcque.clear();
		for (TutelaAcque tutelaAcque : list) {
			_listTutelaAcque.add(new TutelaAcqueBO(tutelaAcque));
		}
	}
	
	public void remove(TutelaAcqueBO tutelaAcque)
	{
		_listTutelaAcque.remove(tutelaAcque);
	}
	
}
