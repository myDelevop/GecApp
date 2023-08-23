package it.bz.prov.esiti.util;

/**
 * classe che contiene metodi statici di generica utilità
 * 
 * @author bpettazzoni
 *
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
	
	/**
	 * Restituisce il nome dell'utente attualmente loggato
	 * @return username
	 */
	public static String getCurrentUser()
	{
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	/**
	 * arrotonda il numero alle prime 2 cifre decimali
	 * @param number
	 * @return String
	 */
	public static String roundNumber(float number)
	{
		try {
			DecimalFormat df = new DecimalFormat("#0.00");
			return df.format(number);
		} catch (Exception e) {
			return "";
		}
	}

	 /**
     * sulla base dei parametri di ricerca inseriti imposta i parametri nella query di ricerca
     * @param parametersList
     * @param q
     */
	public static void setQueryParameter(HashMap<String, String> parametersList, Query q) {
		if(parametersList.containsKey("domanda") && !parametersList.get("domanda").equals("%")) 
			q.setParameter("domanda", "%" + parametersList.get("domanda") + "%");
		else 
			q.setParameter("domanda", "%");
		
		if(parametersList.containsKey("cuaa") && !parametersList.get("cuaa").equals("%")) 
			q.setParameter("cuaa", "%" + parametersList.get("cuaa") + "%");
		else 
			q.setParameter("cuaa", "%");
		
		if(parametersList.containsKey("ragione_sociale") && !parametersList.get("ragione_sociale").equals("%")) 
			q.setParameter("ragione_sociale", "%" + parametersList.get("ragione_sociale") + "%");
		else 
			q.setParameter("ragione_sociale", "%");
		
		if(parametersList.containsKey("misura") && !parametersList.get("misura").equals("%")) 
			q.setParameter("misura", parametersList.get("misura") );
			//q.setParameter("misura", "%" + parametersList.get("misura") + "%"); // commentato perchè deve cercare la stringa esatta e non un like
		else 
			q.setParameter("misura", "%");
		
		if(parametersList.containsKey("campagna") && !parametersList.get("campagna").equals("%"))
			q.setParameter("campagna", "%" + parametersList.get("campagna") + "%");
		else 
			q.setParameter("campagna", "%");
		
		if(parametersList.containsKey("campione") && !parametersList.get("campione").equals("%")) 
			q.setParameter("campione", "%" + parametersList.get("campione") + "%");
		else 
			q.setParameter("campione", "%");
		
		if(parametersList.containsKey("segnalazione") && !parametersList.get("segnalazione").equals("%")) 
			q.setParameter("extraCampione", "%" + parametersList.get("segnalazione") + "%");
		else 
			q.setParameter("extraCampione", "%");	
	}
	
	/**
	 * legge una data in formato stringa DD/MM/YYYY e restituisce l'oggetto Date
	 * @param dataStr
	 * @return Date
	 */
	public static Date getDate(String dataStr)
	{
		if(dataStr == null || dataStr.equals("")) return null;
		// decommentata perchè ci sono casi in cui lo split con il . non funziona. può dipendere dal formato della colonna sul file excel. 
		if(dataStr.contains(".")) dataStr= dataStr.replace(".", "/"); 
		String[] dataArray = null;
		if(dataStr.contains("/")) dataArray = dataStr.split("/");
		else if(dataStr.contains("-")) dataArray = dataStr.split("-");
		else if(dataStr.contains(".")) dataArray = dataStr.split(".");
		
//		System.out.println("+++++++ dataStr:" + dataStr);
//		if(dataStr.contains(".")) System.out.println("+++++++ contiene .");		
//		System.out.println("+++++ getDate dataArray size:" + dataArray.length);
//		if(dataArray.length>0) 
//			System.out.println("+++++ getDate dataArray content:" + dataArray.toString());		
		
		if(dataArray.length==0) return null;
		int giorno = Integer.parseInt(dataArray[0]);
		int mese = Integer.parseInt(dataArray[1])-1;
		int anno = Integer.parseInt(dataArray[2]);
		GregorianCalendar calendar = new GregorianCalendar(anno, mese, giorno);
		return calendar.getTime();
	}
	
	/**
	 * da una data in formato Date restituisce la stringa nel formato DD/MM/YYYY
	 * @param date
	 * @return String
	 */
	public static String getDateString(Date date)
	{
		if(date == null) return "";
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = format.format(date);
		if(dataStr == null) return "";
		return dataStr;
	}
	
	/**
	 * restituisce la data attuale
	 * @return Date
	 */
	public static Date todayDate() 
	{
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.getTime();
	}
	
	/**
	 * restituisce l'anno attuale
	 * @return int
	 */
	public static int currentYear() 
	{
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.get(GregorianCalendar.YEAR);
	}
	
	/**
	 * Stampa a video i parametri inseriti
	 * @param q
	 */
	public static void printQueryParameter(Query q)
	{
		System.out.println("*************************************");
		System.out.println("cuaa:" + q.getParameterValue("cuaa"));
		System.out.println("campagna:" + q.getParameterValue("domanda"));
		System.out.println("campagna:" + q.getParameterValue("misura"));
		System.out.println("campagna:" + q.getParameterValue("campagna"));
		System.out.println("ragione_sociale:" + q.getParameterValue("ragione_sociale"));
		System.out.println("campione" + q.getParameterValue("campione"));
		System.out.println("extraCampione:" + q.getParameterValue("extraCampione"));
		System.out.println("*************************************");
	}
	
	/**
	 * Restituisce il logger dell'applicazione (logga sul log dedicato)
	 * @return Logger
	 */
	public static Logger getLog()
	{
		return Logger.getLogger("OPPAB_GESTIONE_ESITI");
	}
	
	/**
	 * conversione da stringa a number
	 * @param number
	 * @return Number
	 */
	public static Number getNumber(String number)
	{
		NumberFormat formatter = new DecimalFormat("#0.00");
		try {
			return formatter.parse(number);
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * conversione da stringa a float
	 * @param number
	 * @return Float
	 */
	public static Float getFloat(String number)
	{
		try {
			return Float.parseFloat(number.replace(",", "."));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new Float(-1);
		}
	}
	

	
	/**
	 * restituisce l'hostname della macchina su cui viene eseguita l'applicazione 
	 * @return
	 */
	public static String getHostName()
	{
		String hostname = "Unknown";

		try
		{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		    Utils.getLog().info(Utils.class.toString() + ": hostname:" + hostname);
		}
		catch (UnknownHostException ex)
		{
			Utils.getLog().error(Utils.class.toString() + ": hostname non risolto");
		}
		return hostname;
	}
	

}
