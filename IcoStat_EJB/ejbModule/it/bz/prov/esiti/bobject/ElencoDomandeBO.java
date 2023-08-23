package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.Domanda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Business entity che rappresenta l'elenco delle domande
 * 
 * @author bpettazzoni
 *
 */

public class ElencoDomandeBO {

	private HashMap<String, DomandaBO> _listDomande;
	
	public ElencoDomandeBO(){
		_listDomande = new HashMap<String, DomandaBO>();
	}
	
	public void addDomanda(DomandaBO domanda){
		String pk = domanda.get_idDomanda() + '_' + domanda.get_misura();
		_listDomande.put(pk, domanda);
	}
	
	public DomandaBO getDomanda(String idDomanda, String misura){
		String pk = idDomanda + '_' + misura;
		return _listDomande.get(pk);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public DomandaBO getDomanda(int index){
		return _listDomande.get(index);
	}
	
	public int size(){
		return _listDomande.size();
	}
	
	public List<DomandaBO> get_listDomande(){
		List<DomandaBO> lista = new ArrayList<DomandaBO>();
		for (DomandaBO domanda : _listDomande.values()) {
			lista.add(domanda);
		}
		return lista;
	}
	
	/**
	 * imposta l'elenco delle domande a partire da una lista
	 * @param listDomande
	 */
	public void set_listDomande(List<Domanda> listDomande){
		this._listDomande.clear();
		for (Domanda domanda : listDomande) {
			String pk = domanda.get_idDomanda() + '_' + domanda.get_misura();
			this._listDomande.put(pk, new DomandaBO(domanda));
		}
	}
	
	public void set_listDomande(HashMap<String, DomandaBO> listDomande){
		this._listDomande = listDomande;
	}
	
	
//	/**
//	 * Carica tutte le aziende dal database
//	 */
//	public void loadAllFromDB(){
//		_listDomande = DBManager.getListaDomande(null, false);
//	}
	
	public void remove(DomandaBO domanda)
	{
		String pk = domanda.get_idDomanda() + '_' + domanda.get_misura();
		_listDomande.remove(pk);
	}
}
