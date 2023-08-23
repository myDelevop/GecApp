package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.EsitoImpegni;

import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco degli esiti impegni
 * 
 * @author bpettazzoni
 *
 */

public class ElencoEsitoImpegniBO {

	private Vector<EsitoImpegniBO> _listEsitoImpegni;
	
	public ElencoEsitoImpegniBO(){
		_listEsitoImpegni = new Vector<EsitoImpegniBO>();
	}
	
	public void addEsito(EsitoImpegniBO esitoImpegni){
		_listEsitoImpegni.add(esitoImpegni);
	}
	
	/**
	 * restituisce l'esito relativo ad una certa domanda e ad un certo sottointervento.
	 * ci può essere solo un esito per sottointervento
	 * @param idDomanda
	 * @param sottointervento
	 * @return EsitoImpegniBO
	 */
	public EsitoImpegniBO getEsito(String idDomanda, String sottointervento){
		for (EsitoImpegniBO esito : _listEsitoImpegni) {
			if(esito.get_domanda().equals(idDomanda) && esito.get_sottointervento().equals(sottointervento))
				return esito;
		}
		return null;
	}
	
	public int size(){
		return _listEsitoImpegni.size();
	}
	
	public List<EsitoImpegniBO> get_listEsitoImpegni(){
		return _listEsitoImpegni;
	}
	
	public void set_listEsitoImpegni(List<EsitoImpegni> list)
	{
		_listEsitoImpegni.clear();
		for (EsitoImpegni esitoImpegni : list) {
			_listEsitoImpegni.add(new EsitoImpegniBO(esitoImpegni));
		}
	}
	
	public void remove(EsitoImpegniBO esito)
	{
		_listEsitoImpegni.remove(esito);
	}
	
}
