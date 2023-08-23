package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.EsitoCondizionalitaAtto;

import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco degli esiti di condizionalità per atto
 * 
 * @author bpettazzoni
 *
 */

public class ElencoEsitoCondizionalitaAttoBO {
	private Vector<EsitoCondizionalitaAttoBO> _listCondEsitoAtto;
	
	/**
	 * Costruttore
	 */
	public ElencoEsitoCondizionalitaAttoBO()
	{
		_listCondEsitoAtto = new Vector<EsitoCondizionalitaAttoBO>();
	}

	public void set_listCondEsitoAtto(List<EsitoCondizionalitaAtto> _listCondEsitoAtto) {
		this._listCondEsitoAtto.clear();
		for (EsitoCondizionalitaAtto esitoCondizionalitaAtto : _listCondEsitoAtto) {
			this._listCondEsitoAtto.add(new EsitoCondizionalitaAttoBO(esitoCondizionalitaAtto));
		}
	}

	public Vector<EsitoCondizionalitaAttoBO> get_listCondEsitoAtto() {
		return _listCondEsitoAtto;
	}


	public void addEsitoAtto(EsitoCondizionalitaAttoBO esito){
		_listCondEsitoAtto.add(esito);
	}
	
	/**
	 * Restituisce l'esito di condizionalita richiesto per cuaa e atto controllato
	 * @param cuaa
	 * @param atto
	 * @return EsitoCondizionalitaAttoBO
	 */
	public EsitoCondizionalitaAttoBO getEsitoAtto(String cuaa, String atto)
	{
		for (EsitoCondizionalitaAttoBO esito : _listCondEsitoAtto) {
			if(esito.get_cuaa().equals(cuaa) && esito.get_atto_contr().equals(atto)) return esito;
		}
		return null; 
	}
	
	public void remove(EsitoCondizionalitaAttoBO esito)
	{
		_listCondEsitoAtto.remove(esito);		
	}
	
}
