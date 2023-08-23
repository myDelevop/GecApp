package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.Uba;

import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco degli esiti uba
 * 
 * @author bpettazzoni
 *
 */

public class ElencoUbaBO {
	private Vector<UbaBO> _listUBA;
	
	public ElencoUbaBO(){
		_listUBA = new Vector<UbaBO>();
	}
	
	public void addEsito(UbaBO uba){
		_listUBA.add(uba);
	}
	
	public int size(){
		return _listUBA.size();
	}
	
	public Vector<UbaBO> get_listUba(){
		return _listUBA;
	}
	
	public void set_listUbaBO(Vector<UbaBO> list){
		_listUBA = list;
	}
	
	public void set_listUba(List<Uba> list){
		_listUBA.clear();
		for (Uba uba : list) {
			UbaBO ubaBO = new  UbaBO(uba);
			_listUBA.add(ubaBO);
		}
	}
	
	/**
	 *  cancella un uba dalla lista
	 * @param uba
	 */
	public void removeUba(UbaBO uba)
	{
		_listUBA.remove(uba);
	}
	
	public void remove(UbaBO uba)
	{
		_listUBA.remove(uba);
	}

}
