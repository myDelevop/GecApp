package it.bz.prov.esiti.bobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Business entity che rappresenta l'elenco delle aziende
 * 
 * @author bpettazzoni
 *
 */

public class ElencoAziendeBO {

	private HashMap<String, AziendaBO> _listAziende;
	
	
	/**
	 * Costruttore privato
	 */
	public ElencoAziendeBO(){
		_listAziende = new HashMap<String, AziendaBO>();		
	}
	
	public ElencoAziendeBO(HashMap<String, AziendaBO> _listAziende){
		this._listAziende = _listAziende;	
	}
	
	public void addAzienda(AziendaBO azienda){
		_listAziende.put(azienda.get_cuaa(), azienda);
	}
	
	public AziendaBO getAzienda(String cuaa){
		return _listAziende.get(cuaa);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public AziendaBO getAzienda(int index){
		return _listAziende.get(index);
	}
	
	public int size(){
		return _listAziende.size();
	}
	
	public List<AziendaBO> get_listAziende(){
		List<AziendaBO> lista = new ArrayList<AziendaBO>();
		for (AziendaBO azienda : _listAziende.values()) {
			lista.add(azienda);
		}
		return lista;
	}
	
	public void set_listAziende(HashMap<String, AziendaBO> listAziende){
		_listAziende = listAziende;
	}
	
//	/**
//	 * Carica tutte le aziende dal database
//	 */
//	public void loadAllFromDB(){
//		_listAziende = DBManager.getListaAziende(null, false);
//	}
	
	public void remove(AziendaBO azienda)
	{
		_listAziende.remove(azienda.get_cuaa());
	}
	
	public void removeAll() {
		_listAziende.clear();
	}
}
