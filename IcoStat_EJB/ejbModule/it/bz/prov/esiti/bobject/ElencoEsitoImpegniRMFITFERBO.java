package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.EsitoImpegniRMFITFER;

import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco degli esiti impegni
 * 
 * @author lattana
 *
 */

public class ElencoEsitoImpegniRMFITFERBO {

	private Vector<EsitoImpegniRMFITFERBO> _listEsitoImpegniRMFITFER;
	
	public ElencoEsitoImpegniRMFITFERBO(){
		_listEsitoImpegniRMFITFER = new Vector<EsitoImpegniRMFITFERBO>();
	}
	
	public void addEsito(EsitoImpegniRMFITFERBO esitoImpegni){
		_listEsitoImpegniRMFITFER.add(esitoImpegni);
	}
	
	/**
	 * restituisce l'esito relativo ad una certa domanda e ad una certa misura.
	 * ci può essere solo un esito per sottointervento
	 * @param idDomanda
	 * @param sottointervento
	 * @return EsitoImpegniBO
	 */
	public EsitoImpegniRMFITFERBO getEsito(String idDomanda, String misura){
		for (EsitoImpegniRMFITFERBO esito : _listEsitoImpegniRMFITFER) {
			if(esito.get_domanda().equals(idDomanda) && esito.get_misura().equals(misura))
				return esito;
		}
		return null;
	}
	
	public int size(){
		return _listEsitoImpegniRMFITFER.size();
	}
	
	public List<EsitoImpegniRMFITFERBO> get_listEsitoImpegniRMFITFER(){
		return _listEsitoImpegniRMFITFER;
	}
	
	public void set_listEsitoImpegni(List<EsitoImpegniRMFITFER> list)
	{
		_listEsitoImpegniRMFITFER.clear();
		for (EsitoImpegniRMFITFER esitoImpegni : list) {
			_listEsitoImpegniRMFITFER.add(new EsitoImpegniRMFITFERBO(esitoImpegni));
		}
	}
	
	public void remove(EsitoImpegniRMFITFERBO esito)
	{
		_listEsitoImpegniRMFITFER.remove(esito);
	}
	
}
