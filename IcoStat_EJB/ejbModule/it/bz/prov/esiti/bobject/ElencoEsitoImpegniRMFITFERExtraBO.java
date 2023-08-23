package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.EsitoImpegniRMFITFERExtra;

import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco degli esiti impegni
 * 
 * @author lattana
 *
 */

public class ElencoEsitoImpegniRMFITFERExtraBO {

	private Vector<EsitoImpegniRMFITFERExtraBO> _listEsitoImpegniRMFITFERExtra;
	
	public ElencoEsitoImpegniRMFITFERExtraBO(){
		_listEsitoImpegniRMFITFERExtra = new Vector<EsitoImpegniRMFITFERExtraBO>();
	}
	
	public void addEsito(EsitoImpegniRMFITFERExtraBO esitoImpegni){
		_listEsitoImpegniRMFITFERExtra.add(esitoImpegni);
	}
	
	/**
	 * restituisce l'esito relativo ad una certa domanda e ad una certa misura.
	 * ci può essere solo un esito per sottointervento
	 * @param idDomanda
	 * @param sottointervento
	 * @return EsitoImpegniBO
	 */
	public EsitoImpegniRMFITFERExtraBO getEsito(String idDomanda, String misura){
		for (EsitoImpegniRMFITFERExtraBO esito : _listEsitoImpegniRMFITFERExtra) {
			if(esito.get_domanda().equals(idDomanda) && esito.get_misura().equals(misura))
				return esito;
		}
		return null;
	}
	
	public int size(){
		return _listEsitoImpegniRMFITFERExtra.size();
	}
	
	public List<EsitoImpegniRMFITFERExtraBO> get_listEsitoImpegniRMFITFER(){
		return _listEsitoImpegniRMFITFERExtra;
	}
	
	public void set_listEsitoImpegni(List<EsitoImpegniRMFITFERExtra> list)
	{
		_listEsitoImpegniRMFITFERExtra.clear();
		for (EsitoImpegniRMFITFERExtra esitoImpegni : list) {
			_listEsitoImpegniRMFITFERExtra.add(new EsitoImpegniRMFITFERExtraBO(esitoImpegni));
		}
	}
	
	public void remove(EsitoImpegniRMFITFERExtraBO esito)
	{
		_listEsitoImpegniRMFITFERExtra.remove(esito);
	}
	
}
