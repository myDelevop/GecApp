package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.Utente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Oggetto che mappa l'elenco degli utenti 
 * @author bpettazzoni
 *
 */

public class ElencoUtentiBO {

	private HashMap<String, UtenteBO> _listUtenti;
	
	/**
	 * Costruttore pubblico
	 */
	public ElencoUtentiBO(){
		_listUtenti = new HashMap<String, UtenteBO>();	
	}
	
	/**
	 * aggiunta di un utente
	 * @param utente
	 */
	public void addUtente(UtenteBO utente){
		_listUtenti.put(utente.get_username(), utente);
	}
	
	/**
	 * restituzione di un utente tramite username
	 * @param username
	 * @return
	 */
	public UtenteBO getUtente(String username){
		return _listUtenti.get(username);
	}
	
	/**
	 * restituzione di un utente tramite indice
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public UtenteBO getUtente(int index){
		return _listUtenti.get(index);
	}
	
	/**
	 * aggiornamento utenti
	 */
	public void updateUtenti(){
	}
	
	/**
	 * cancellazione utente
	 * @param utente
	 */
	public void removeUtente(UtenteBO utente){
		_listUtenti.remove(utente.get_username());
	}
	
	/**
	 * cancellazione utente per username
	 * @param username
	 */
	public void removeUtente(String username){
		_listUtenti.remove(username);
	}
	
	/**
	 * numero di utenti gestiti
	 * @return
	 */
	public int size(){
		return _listUtenti.size();
	}
	
	/**
	 * lista degli utenti
	 * @return List<Utente>
	 */
	public List<UtenteBO> get_listUtenti(){
		List<UtenteBO> lista = new ArrayList<UtenteBO>();
		for (UtenteBO utente : _listUtenti.values()) {
			lista.add(utente);
		}
		return lista;
	}
	
	public void set_listUtenti(List<Utente> list){
		_listUtenti.clear();
		for (Utente utente : list) {
			UtenteBO utenteBO = new  UtenteBO(utente);
			_listUtenti.put(utenteBO.get_username(), utenteBO);
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for (UtenteBO utente : _listUtenti.values()) {
			str = str + "\n" + "utente:" + utente.get_username() + " is_mod:" + utente.is_isModified();
		}
		return str;
	}
	

	
}
