package it.bz.prov.esiti.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * La classe permette la scrittura di un generico file excel.
 * 
 * @author bpettazzoni
 *
 */

public class ExcelWriter {

	
	
	/**
	 * Metodo che crea un file excel e lo scrive su uno stream
	 * E' necessario specificare i campi dell'intestazione e i dati che 
	 * andranno a comporre le righe del file. 
	 * Il file verrà poi scritto su un generico stream di output
	 * @param righe righe dell'excel
	 * @param intestazione intestazione dell'excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public static void writeFile(ArrayList<LinkedHashMap<Integer, String>> righe, 
			LinkedHashMap<Integer, String> intestazione, OutputStream stream)
	{
		Workbook wb = new HSSFWorkbook();
		wb.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
		Sheet sheet1 = wb.createSheet("Foglio1");
		
		// Creazione riga di intestazione
		Row row = sheet1.createRow((short)0);
		
		//int index =0;
		// creazione riga di intestazione
		for(int i=0; i<intestazione.size(); i++)
		{
			String nome_colonna = intestazione.get(i);
			Cell cell = row.createCell(i);
			cell.setCellValue(nome_colonna);
		}
				
		// creazione righe di dati
		int index_row=1;
		//int index_col=0;
		for (HashMap<Integer, String> riga : righe) 
		{
			row = sheet1.createRow((short)index_row);
			for(int i=0; i<intestazione.size()+1; i++)
			{
				String val = riga.get(i);
				Cell cell = row.createCell(i);
				cell.setCellValue(val);
			}
			index_row++;
		}
		
		// gestione del file
		try {
			// scrittura file
			wb.write(stream);
			// chiusura stream
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
