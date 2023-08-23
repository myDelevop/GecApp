/********************************************************************************
* FILE:  FileExcel.java                                         
* DEVELOPED BY ICONSULTING SRL                                                 
* AUTORS..: BARBARA BONOLI, BARBARA PETTAZZONI
* CREATED: 17/feb/2012                                                      
********************************************************************************/
package it.bz.prov.esiti.util;
import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * Oggetto che rappresenta il generico file excel con riga di intestazione e dati
 * 
 * @author bpettazzoni
 *
 */

public class FileExcel {
	
	private LinkedHashMap<Integer, String> _intestazione;
	private ArrayList<LinkedHashMap<Integer, String>> _righe;
	
	/**
	 * costruttore
	 **/
	public FileExcel()
	{
		
	}
	
	/**
	 * imposta la struttura che contiene i dati dell'intestazione della tabella
	 * @param intestazione 
	 **/
	public void setIntestazione(LinkedHashMap<Integer, String> intestazione)
	{
		_intestazione = intestazione;
	}
	
	
	/**
	 * restituisce la struttura che contiene i dati dell'intestazione della tabella
	 * @return HashMap<Integer, String>
	 **/
	public LinkedHashMap<Integer, String> getIntestazione()
	{
		return _intestazione;
	}
	
	/**
	 * imposta la struttura che contiene i dati della tabella 
	 * @param righe
	 **/
	public void setRighe(ArrayList<LinkedHashMap<Integer, String>> righe)
	{
		_righe = righe;
	}
	
	
	/**
	 * restituisce la struttura che contiene i dati della tabella 
	 * @return ArrayList<HashMap<String, String>>
	 **/
	public ArrayList<LinkedHashMap<Integer, String>> getRighe()
	{
		return _righe;
	}
	
	/**
	 * controlla se la colonna indicata é presente come intestazione (é case insensitive)
	 * @param nomeColonna
	 * @return boolean vale true se il file contiene quell'intestazione, false altrimenti
	 */
	public boolean hasColonna(String nomeColonna)
	{
		for (String colonna : _intestazione.values()) {
			if(colonna.toLowerCase().equals(nomeColonna.toLowerCase())) return true;
		}
		return false;
	}

}
