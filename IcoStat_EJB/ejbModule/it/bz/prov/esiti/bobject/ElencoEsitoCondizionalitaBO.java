package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.EsitoCondizionalita;

import java.util.List;
import java.util.Vector;


/**
 * Business entity che rappresenta l'elenco degli esiti di condizionalità per azienda
 * 
 * @author bpettazzoni
 *
 */

public class ElencoEsitoCondizionalitaBO {
	private Vector<EsitoCondizionalitaBO> _listCondEsito;
	
	/**
	 * Costruttore
	 */
	public ElencoEsitoCondizionalitaBO()
	{
		_listCondEsito = new Vector<EsitoCondizionalitaBO>();
	}

	public void set_listCondEsito(List<EsitoCondizionalita> _listCondEsito) {
		this._listCondEsito.clear();
		for (EsitoCondizionalita esitoCondizionalita : _listCondEsito) {
			this._listCondEsito.add(new EsitoCondizionalitaBO(esitoCondizionalita));
		}
	}

	public Vector<EsitoCondizionalitaBO> get_listCondEsito() {
		return _listCondEsito;
	}


	public void addEsito(EsitoCondizionalitaBO esito){
		_listCondEsito.add(esito);
	}
	
	public void remove(EsitoCondizionalitaBO esito)
	{
		_listCondEsito.remove(esito);
	}
	
	
}
