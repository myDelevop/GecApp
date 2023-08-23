package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.EsitoZootecniaStato;

import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco degli esiti dei controlli zootecnia
 * 
 * @author bpettazzoni
 *
 */

public class ElencoEsitoZootecniaBO {

	private Vector<EsitoZootecniaStatoBO> _listEsitoZoot;
	
	public ElencoEsitoZootecniaBO(){
		_listEsitoZoot = new Vector<EsitoZootecniaStatoBO>();
	}
	
	public void addEsito(EsitoZootecniaStatoBO esito){
		_listEsitoZoot.add(esito);
	}
	
	/**
	 * restituisce l'esito relativo ad una certa domanda 
	 * @param idDomanda
	 * @return EsitoZootecniaStatoBO
	 */
	public EsitoZootecniaStatoBO getEsito(String idDomanda){
		for (EsitoZootecniaStatoBO esito : _listEsitoZoot) {
			if(esito.get_domanda().equals(idDomanda))
				return esito;
		}
		return null;
	}
	
	public int size(){
		return _listEsitoZoot.size();
	}
	
	public void remove(EsitoZootecniaStatoBO esito)
	{
		_listEsitoZoot.remove(esito);
	}

	public void set_listEsitoZoot(List<EsitoZootecniaStato> list) {
		_listEsitoZoot.clear();
		for (EsitoZootecniaStato esito : list) {
			_listEsitoZoot.add(new EsitoZootecniaStatoBO(esito));
		}
	}

	public Vector<EsitoZootecniaStatoBO> get_listEsitoZoot() {
		return _listEsitoZoot;
	}
	
}
