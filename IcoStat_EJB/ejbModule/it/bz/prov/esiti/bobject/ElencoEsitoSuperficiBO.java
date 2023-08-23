package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.EsitoSuperfici;

import java.util.List;
import java.util.Vector;


/**
 * Business entity che rappresenta l'elenco degli esiti superficie 
 * 
 * @author bpettazzoni
 *
 */

public class ElencoEsitoSuperficiBO {

	private Vector<EsitoSuperficiBO> _listEsitoSup;
	
	public ElencoEsitoSuperficiBO(){
		_listEsitoSup = new Vector<EsitoSuperficiBO>();
	}
	
	public void addEsito(EsitoSuperficiBO esitoSup){
		_listEsitoSup.add(esitoSup);
	}
	
	/**
	 * restituisce l'esito relativo ad una certa domanda e ad un certo sottointervento.
	 * ci può essere solo un esito per sottointervento
	 * @param idDomanda
	 * @param sottointervento
	 * @return EsitoSuperficiBO
	 */
	public EsitoSuperficiBO getEsito(String idDomanda, String sottointervento){
		for (EsitoSuperficiBO esito : _listEsitoSup) {
			if(esito.get_domanda().equals(idDomanda) && esito.get_sottointervento().equals(sottointervento))
				return esito;
		}
		return null;
	}
	
	public int size(){
		return _listEsitoSup.size();
	}
	
	public List<EsitoSuperficiBO> get_listEsitoSuperfici(){
		return _listEsitoSup;
	}
	
	public void set_listEsitoSuperfici(List<EsitoSuperfici> list){
		_listEsitoSup.clear();
		for (EsitoSuperfici esitoSuperfici : list) {
			_listEsitoSup.add(new EsitoSuperficiBO(esitoSuperfici));
		}
	}
	
	public void remove(EsitoSuperficiBO esito) 
	{
		_listEsitoSup.remove(esito);
	}
	
}
