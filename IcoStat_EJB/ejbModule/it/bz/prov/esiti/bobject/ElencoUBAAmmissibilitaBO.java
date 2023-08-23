package it.bz.prov.esiti.bobject;

import java.util.List;
import java.util.Vector;

import it.bz.prov.esiti.entity.UBAAmmissibilita;

public class ElencoUBAAmmissibilitaBO {

private Vector<UBAAmmissibilitaBO> _listUBA;
	
	public ElencoUBAAmmissibilitaBO(){
		_listUBA = new Vector<UBAAmmissibilitaBO>();
	}
	
	public void addEsito(UBAAmmissibilitaBO uba){
		_listUBA.add(uba);
	}
	
	public int size(){
		return _listUBA.size();
	}
	
	public Vector<UBAAmmissibilitaBO> get_listUba(){
		return _listUBA;
	}
	
	public void set_listUbaBO(Vector<UBAAmmissibilitaBO> list){
		_listUBA = list;
	}
	
	public void set_listUba(List<UBAAmmissibilita> list){
		_listUBA.clear();
		for (UBAAmmissibilita uba : list) {
			UBAAmmissibilitaBO ubaBO = new  UBAAmmissibilitaBO(uba);
			_listUBA.add(ubaBO);
		}
	}
	
	/**
	 *  cancella un uba dalla lista
	 * @param uba
	 */
	public void removeUba(UBAAmmissibilitaBO uba)
	{
		_listUBA.remove(uba);
	}
	
	public void remove(UBAAmmissibilitaBO uba)
	{
		_listUBA.remove(uba);
	}
}
