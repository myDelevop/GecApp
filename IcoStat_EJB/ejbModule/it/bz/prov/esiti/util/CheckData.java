package it.bz.prov.esiti.util;

import java.util.Date;

/**
 * effettua il check sui dati
 * 
 * @author bpettazzoni
 *
 */

public class CheckData {
	
	/****************************************************************************
	 * 						CHECK SU STRINGHE
	 ****************************************************************************/
	
	
	/**
	 * verifica se la stringa é valorizzata correttamente (non nulla e non con spazio)
	 * @param str
	 * @return boolean vale true se ok, false se valorizzata in modo errato
	 */
	public static boolean checkString(String str)
	{
		if(str == null || str.equals("") || str.replaceAll("\\s","").equals("") ) return false;
		else return true;
	}
	
	/**
	 * verifica la corretta dimensione del cuaa
	 * @param cuaa
	 * @return boolean vale true se ok, false se dimensione errata
	 */
	public static boolean checkCuaaLength(String cuaa)
	{
		if(cuaa.length()>=11 && cuaa.length()<=16) return true;
		else return false;
	}
	
	
	/**
	 * verifica se la stringa delle note é della dimensione corretta (<500 caratteri)
	 * @param str
	 * @return boolean vale true se ok, false se valorizzata in modo errato
	 */
	public static boolean checkNoteSize(String str)
	{
		if(str == null || str.equals("") ) return true;
		if(str.length()<500) return true;
		else return true;
	}
	
	
	/****************************************************************************
	 * 						CHECK SU DATE
	 ****************************************************************************/
	
	
	/**
	 * verifica che la data inizio sia prima della data fine
	 * @param data_inizio
	 * @param data_fine
	 * @return boolean vale true se la data di fine é successiva alla data di inizio
	 */
	public static boolean checkOrderDate(Date data_inizio, Date data_fine)
	{
		int value = data_fine.compareTo(data_inizio);
		if(value>=0) return true;
		else return false;
	}
	
	/**
	 * verifica se la stringa che contiene la data ha il formato DD/MM/YYYY
	 * @param data
	 * @return boolean vale true se la data di fine é successiva alla data di inizio
	 */
	public static boolean checkStringDateFormat(String data)
	{
		data.replace(".", "/");
		data.replace("-", "/");
		if(!data.contains("/")) {
			return false;
		}
		String[] str = data.split("/");
		try {
			int giorno = Integer.parseInt(str[0]);
			int mese = Integer.parseInt(str[1]);
			int anno = Integer.parseInt(str[2]);
			
			// giorno
			if(giorno<1 || giorno >31) return false;
			// mese
			if(mese<1 || mese >12) return false;
			//anno
			if(anno<2000 || mese >9000) return false;
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	/****************************************************************************
	 * 						CHECK SU NUMERI
	 ****************************************************************************/
	
	/**
	 * verifica se una stringa contiene un valore numerico Float con virgola
	 * @param number
	 * @return boolean
	 */
	public static boolean checkFloat(String number)
	{
		try {
			Float.parseFloat(number.replace(",", "."));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * verifica se una stringa contiene un valore numerico Integer
	 * @param number
	 * @return boolean
	 */
	public static boolean checkInteger(String number)
	{
		try {
			Integer.parseInt(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	


}
