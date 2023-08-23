package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.ElencoMatrAmmIdealeBO;
import it.bz.prov.esiti.entity.MatrAmmIdeale;
import it.bz.prov.esiti.ibusiness.IMatrAmmIdeale;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.ExcelWriter;
import it.bz.prov.esiti.util.Utils;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Bean per la gestione back-end delle matrici di ammissibilità ideale
 * 
 * @author bpettazzoni
 *
 */

@Stateful
@SuppressWarnings("unchecked")
public class MatrAmmIdealeBean implements IMatrAmmIdeale {
	
	private static String COL_CUAA = "CUAA";
	private static String COL_CAMPAGNA = "Campagna";
	private static String COL_A1 = "A1";
	private static String COL_A2 = "A2";
	private static String COL_A3 = "A3";
	private static String COL_A4 = "A4";
	private static String COL_A5 = "A5";
	private static String COL_A6 = "A6";
	private static String COL_A7 = "A7";
	private static String COL_A8 = "A8";
	private static String COL_FER = "FER";
	private static String COL_FIT = "FIT";
	private static String COL_B9 = "B9";
	private static String COL_B10 = "B10";
	private static String COL_B11 = "B11";
	private static String COL_B12 = "B12";
	private static String COL_B13 = "B13";
	private static String COL_B14 = "B14";
	private static String COL_B15 = "B15";
	private static String COL_C16 = "C16";
	private static String COL_C17 = "C17";
	private static String COL_C18 = "C18";
	private static String COL_1_1 = "1_1";
	private static String COL_1_2 = "1_2";
	private static String COL_1_3 = "1_3";
	private static String COL_2_1 = "2_1";
	private static String COL_2_2 = "2_2";
	private static String COL_3_1 = "3_1";
	private static String COL_4_1 = "4_1";
	private static String COL_4_2 = "4_2";
	private static String COL_4_3 = "4_3";
	private static String COL_4_4 = "4_4";
	private static String COL_4_6 = "4_6";
	private static String COL_5_1 = "5_1";
	private static String COL_5_2 = "5_2";
	private static String COL_CGO1 = "CGO1";
	private static String COL_BCAA1 = "BCAA1";
	private static String COL_BCAA2 = "BCAA2";
	private static String COL_BCAA3 = "BCAA3";
	private static String COL_BCAA4 = "BCAA4";
	private static String COL_BCAA5 = "BCAA5";
	private static String COL_BCAA6 = "BCAA6";
	private static String COL_CGO2 = "CGO2";
	private static String COL_CGO3 = "CGO3";
	private static String COL_BCAA7 = "BCAA7";
	private static String COL_CGO4 = "CGO4";
	private static String COL_CGO5 = "CGO5";
	private static String COL_CGO6 = "CGO6";
	private static String COL_CGO7 = "CGO7";
	private static String COL_CGO8 = "CGO8";
	private static String COL_CGO9 = "CGO9";
	private static String COL_CGO10 = "CGO10";
	private static String COL_CGO11 = "CGO11";
	private static String COL_CGO12 = "CGO12";
	private static String COL_CGO13 = "CGO13";
	private static String COL_BCAA8 = "BCAA8";
	
	
	private ElencoMatrAmmIdealeBO elencoMatrAmmIdeale = new ElencoMatrAmmIdealeBO();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	
	/***************************************************************************************
	 * 			GESTIONE DATI 
	 ***************************************************************************************/

	
    /**
     * Carica i dati in tabella
     */
    public void loadData()
    {
    	_em.clear();
    	Query q = _em.createNamedQuery("MatrAmmIdeale.selectLastCampagna");
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<MatrAmmIdeale> fornitura = q.getResultList();
		elencoMatrAmmIdeale.set_listMatrAmmIdeale(fornitura);
    }
    
    
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList)
    {
		_em.clear();
    	Query q ;
    	
    	for (String key : parametersList.keySet()) {
			System.out.println("------key:" + key + " value:" + parametersList.get(key));
		}
    	
    	if(filterByAziendaAttribute(parametersList))
		{
			q = _em.createNamedQuery("MatrAmmIdeale.selectFilterOnlyAzienda");
			setQueryParameterAzienda(parametersList, q);
		}
		else 
		{
			q = _em.createNamedQuery("MatrAmmIdeale.selectFilter");
			Utils.setQueryParameter(parametersList, q);
		}
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<MatrAmmIdeale> fornitura = q.getResultList();
		elencoMatrAmmIdeale.set_listMatrAmmIdeale(fornitura);
    }
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream)
	{	
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = getListaIntestazioni();
		
		_em.clear();
    	Query q = _em.createNamedQuery("MatrAmmIdeale.selectLastCampagna");
		//q.setMaxResults(Costanti.QUERY_TOP_K);
		List<MatrAmmIdeale> fornitura = q.getResultList();
//		elencoMatrAmmIdeale.set_listMatrAmmIdeale(fornitura);
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
//		for (MatrAmmIdealeBO matr : elencoMatrAmmIdeale.get_listMatrAmmIdeale()) {
		for (MatrAmmIdeale matr : fornitura) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, matr.get_cuaa());
			riga.put(1, matr.get_campagna());
			riga.put(2, matr.get_a1());
			riga.put(3, matr.get_a2());
			riga.put(4, matr.get_a3());
			riga.put(5, matr.get_a4());
			riga.put(6, matr.get_fer());
			riga.put(7, matr.get_a5());
			riga.put(8, matr.get_a6());
			riga.put(9, matr.get_a7());
			riga.put(10, matr.get_a8());
			riga.put(11, matr.get_b9());
			riga.put(12, matr.get_b10());
			riga.put(13, matr.get_b11());
			riga.put(14, matr.get_b12());
			riga.put(15, matr.get_b13());
			riga.put(16, matr.get_b14());
			riga.put(17, matr.get_b15());
			riga.put(18, matr.get_c16());
			riga.put(19, matr.get_c17());
			riga.put(20, matr.get_c18());
			riga.put(21, matr.get_fit());
			riga.put(22, matr.get_1_1());
			riga.put(23, matr.get_1_2());
			riga.put(24, matr.get_1_3());
			riga.put(25, matr.get_2_1());
			riga.put(26, matr.get_2_2());
			riga.put(27, matr.get_3_1());
			riga.put(28, matr.get_4_1());
			riga.put(29, matr.get_4_2());
			riga.put(30, matr.get_4_3());
			riga.put(31, matr.get_4_4());
			riga.put(32, matr.get_4_6());
			riga.put(33, matr.get_5_1());
			riga.put(34, matr.get_5_2());
			riga.put(35, matr.get_cgo1());
			riga.put(36, matr.get_bcaa1());
			riga.put(37, matr.get_bcaa3());
			riga.put(38, matr.get_bcaa4());
			riga.put(39, matr.get_bcaa5());
			riga.put(40, matr.get_bcaa6());
			riga.put(41, matr.get_cgo2());
			riga.put(42, matr.get_cgo3());
			riga.put(43, matr.get_bcaa7());
			riga.put(44, matr.get_cgo4());
			riga.put(45, matr.get_cgo5());
			riga.put(46, matr.get_cgo6());
			riga.put(47, matr.get_cgo7());
			riga.put(48, matr.get_cgo8());
			riga.put(49, matr.get_cgo9());
			riga.put(50, matr.get_cgo10());
			riga.put(51, matr.get_cgo11());
			riga.put(52, matr.get_cgo12());
			riga.put(53, matr.get_cgo13());
			riga.put(54, matr.get_bcaa8());
			
			righe.add(riga);
		}
		
		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}
	
	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList()
	{
		elencoMatrAmmIdeale = new ElencoMatrAmmIdealeBO();
	}
	
	 /**
     * sulla base dei parametri di ricerca inseriti imposta i parametri nella query di ricerca
     * Imposta solo i parametri per azienda 
     * @param parametersList
     * @param q
     */
	public static void setQueryParameterAzienda(HashMap<String, String> parametersList, Query q) {
		if(parametersList.containsKey("cuaa") && !parametersList.get("cuaa").equals("%")) 
			q.setParameter("cuaa", "%" + parametersList.get("cuaa") + "%");
		else 
			q.setParameter("cuaa", "%");
		
		if(parametersList.containsKey("ragione_sociale") && !parametersList.get("ragione_sociale").equals("%")) 
			q.setParameter("ragione_sociale", "%" + parametersList.get("ragione_sociale") + "%");
		else 
			q.setParameter("ragione_sociale", "%");
		
		if(parametersList.containsKey("campagna") && !parametersList.get("campagna").equals("%"))
			q.setParameter("campagna", "%" + parametersList.get("campagna") + "%");
		else 
			q.setParameter("campagna", "%");
	}
	
	
	/***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	
	
	/**
     * verifica se gli unici filtri settati sono quelli dell'azienda (escludo quindi il campione e la domanda)
     * (serve per poter applicare una query dedicata)
     * @param parametersList
     * @return
     */
    private boolean filterByAziendaAttribute(HashMap<String, String> parametersList)
    {
    	// se sono impostati i filtri di cuaa o ragione sociale restituisce true
    	if(!parametersList.get(Costanti.SEARCH_CAMPAGNA).equals("%") || 
    			!parametersList.get(Costanti.SEARCH_CAMPIONE).equals("%") ||
    			!parametersList.get(Costanti.SEARCH_DOMANDA).equals("%") ||
    			!parametersList.get(Costanti.SEARCH_MISURA).equals("%") ||
    			!parametersList.get(Costanti.SEARCH_SEGNALAZIONE).equals("%"))
    		return false;
    	else 
    		return true;
    }
    
	
    /**
     * prepara la lista di tutte le intestazioni delle colonne presenti nel file
     * @return LinkedHashMap<Integer, String>
     */
    private static LinkedHashMap<Integer, String> getListaIntestazioni()
    {
    	LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
    	intestazione.put(0, COL_CUAA);
		intestazione.put(1, COL_CAMPAGNA);
		intestazione.put(2, COL_A1);
		intestazione.put(3, COL_A2);
		intestazione.put(4, COL_A3);
		intestazione.put(5, COL_A4);
		intestazione.put(6, COL_FER);
		intestazione.put(7, COL_A5);
		intestazione.put(8, COL_A6);
		intestazione.put(9, COL_A7);
		intestazione.put(10, COL_A8);
		intestazione.put(11, COL_B9);
		intestazione.put(12, COL_B10);
		intestazione.put(13, COL_B11);
		intestazione.put(14, COL_B12);
		intestazione.put(15, COL_B13);
		intestazione.put(16, COL_B14);
		intestazione.put(17, COL_B15);
		intestazione.put(18, COL_C16);
		intestazione.put(19, COL_C17);
		intestazione.put(20, COL_C18);
		intestazione.put(21, COL_FIT);
		intestazione.put(22, COL_1_1);
		intestazione.put(23, COL_1_2);
		intestazione.put(24, COL_1_3);
		intestazione.put(25, COL_2_1);
		intestazione.put(26, COL_2_2);
		intestazione.put(27, COL_3_1);
		intestazione.put(28, COL_4_1);
		intestazione.put(29, COL_4_2);
		intestazione.put(30, COL_4_3);
		intestazione.put(31, COL_4_4);
		intestazione.put(32, COL_4_6);
		intestazione.put(33, COL_5_1);
		intestazione.put(34, COL_5_2);
		intestazione.put(35, COL_CGO1);
		intestazione.put(36, COL_BCAA1);
		intestazione.put(37, COL_BCAA2);
		intestazione.put(38, COL_BCAA3);
		intestazione.put(39, COL_BCAA4);
		intestazione.put(40, COL_BCAA5);
		intestazione.put(41, COL_BCAA6);
		intestazione.put(42, COL_CGO2);
		intestazione.put(43, COL_CGO3);
		intestazione.put(44, COL_BCAA7);
		intestazione.put(45, COL_CGO4);
		intestazione.put(46, COL_CGO5);
		intestazione.put(47, COL_CGO6);
		intestazione.put(48, COL_CGO7);
		intestazione.put(49, COL_CGO8);
		intestazione.put(50, COL_CGO9);
		intestazione.put(51, COL_CGO10);
		intestazione.put(52, COL_CGO11);
		intestazione.put(53, COL_CGO12);
		intestazione.put(54, COL_CGO13);
		intestazione.put(55, COL_BCAA8);
    	return intestazione;
    }
    
    	
	/***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/

	public void setElencoMatrAmmIdeale(ElencoMatrAmmIdealeBO elencoMatrAmmIdeale) {
		this.elencoMatrAmmIdeale = elencoMatrAmmIdeale;
	}

	public ElencoMatrAmmIdealeBO getElencoMatrAmmIdeale() {
		return elencoMatrAmmIdeale;
	}
	

	
}
