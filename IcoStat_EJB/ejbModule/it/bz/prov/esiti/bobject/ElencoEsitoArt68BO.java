package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.EsitoArt68;
import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco degli esiti impegni
 * 
 * @author bpettazzoni
 *
 */

public class ElencoEsitoArt68BO {

	private Vector<EsitoArt68BO> _listEsitoArt68;
	
	public ElencoEsitoArt68BO(){
		_listEsitoArt68 = new Vector<EsitoArt68BO>();
	}
	
	public void addEsito(EsitoArt68BO esitoArt68){
		_listEsitoArt68.add(esitoArt68);
	}
	
	/**
	 * restituisce l'esito relativo ad una certa domanda e ad un certo intervento.
	 * ci può essere solo un esito per sottointervento
	 * @param idDomanda
	 * @param intervento
	 * @return EsitoArt68BO
	 */
	public EsitoArt68BO getEsito(String idDomanda, String intervento){
		for (EsitoArt68BO esito : _listEsitoArt68) {
			if(esito.get_domanda().equals(idDomanda) && esito.get_intervento().equals(intervento))
				return esito;
		}
		return null;
	}
	
	public int size(){
		return _listEsitoArt68.size();
	}
	
	public List<EsitoArt68BO> get_listEsitoArt68(){
		return _listEsitoArt68;
	}
	
	public void set_listEsitoArt68(List<EsitoArt68> list)
	{
		_listEsitoArt68.clear();
		for (EsitoArt68 esitoArt68 : list) {
			_listEsitoArt68.add(new EsitoArt68BO(esitoArt68));
		}
	}
	
	public void remove(EsitoArt68BO esitoArt68)
	{
		_listEsitoArt68.remove(esitoArt68);
	}
	
}
