package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.EsitoImpegniExtra;

import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco degli esiti impegni
 * 
 * @author GIMS
 *
 */

public class ElencoEsitoImpegniExtraBO {

	private Vector<EsitoImpegniExtraBO> _listEsitoImpegni;
	
	public ElencoEsitoImpegniExtraBO(){
		_listEsitoImpegni = new Vector<EsitoImpegniExtraBO>();
	}
	
	public void addEsito(EsitoImpegniExtraBO esitoImpegni){
		_listEsitoImpegni.add(esitoImpegni);
	}
	
	/**
	 * restituisce l'esito relativo ad una certa domanda e ad un certo sottointervento.
	 * ci può essere solo un esito per sottointervento
	 * @param idDomanda
	 * @param sottointervento
	 * @return EsitoImpegniExtraBO
	 */
	public EsitoImpegniExtraBO getEsito(String idDomanda, String sottointervento){
		for (EsitoImpegniExtraBO esito : _listEsitoImpegni) {
			if(esito.get_domanda().equals(idDomanda) && esito.get_sottointervento().equals(sottointervento))
				return esito;
		}
		return null;
	}
	
	public int size(){
		return _listEsitoImpegni.size();
	}
	
	public List<EsitoImpegniExtraBO> get_listEsitoImpegni(){
		return _listEsitoImpegni;
	}
	
	public void set_listEsitoImpegni(List<EsitoImpegniExtra> list)
	{
		_listEsitoImpegni.clear();
		for (EsitoImpegniExtra esitoImpegni : list) {
			_listEsitoImpegni.add(new EsitoImpegniExtraBO(esitoImpegni));
		}
	}
	
	public void remove(EsitoImpegniExtraBO esito)
	{
		_listEsitoImpegni.remove(esito);
	}
	
}
