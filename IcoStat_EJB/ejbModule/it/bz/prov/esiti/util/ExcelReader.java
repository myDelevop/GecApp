package it.bz.prov.esiti.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


/**
 * Classe che effettua la lettura di un file excel
 * 
 * @author bpettazzoni
 *
 */

public class ExcelReader {

	private static String _errorType;
	
	/**
	 * Lettura di file excel
	 * @param path é il percorso assoluto del file
	 * @return {@link FileExcel} é l'oggetto che contiene tutti i dati del file excel
	 **/
	public static FileExcel readFile(String path){
		Utils.getLog().info(ExcelReader.class.toString()+": lettura del file: "+path);
				
		List<List<HSSFCell>> sheetData = new ArrayList<List<HSSFCell>>();
		FileInputStream fis = null;
		HSSFWorkbook workbook = null;
		
		try {
			fis = new FileInputStream(path);
			
			workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();
				Iterator<Cell> cells = row.cellIterator();
				List<HSSFCell> data = new ArrayList<HSSFCell>();
				while (cells.hasNext()) {
				HSSFCell cell = (HSSFCell) cells.next();
				data.add(cell);
				}
				sheetData.add(data);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			Utils.getLog().error(ExcelReader.class.getSimpleName() + " - readFile: " + e.getMessage());
			return null;
		}
		finally{
			try {
				if(fis != null)
					fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
				
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>();
		
		LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>(); 
		int index=0;	// serve per sapere quando processo la prima riga di intestazione
		
		// per ogni riga
		for (List<HSSFCell> cellList : sheetData) {
			
			// l'intestazione viene messa in una struttura a parte
			if(index==0)
			{
				// per ogni cella della riga (scansione per colonna)
				for (HSSFCell cell : cellList) {
					int num_colonna = cell.getColumnIndex();
					int cellType = cell.getCellType();
					String value = "";
					// controllo sul tipo della colonna
					if(cellType== Cell.CELL_TYPE_STRING) value = cell.getStringCellValue();
					else if(cellType== Cell.CELL_TYPE_NUMERIC) value = "" + cell.getNumericCellValue();
					intestazione.put(num_colonna, value);
				}
			}
			else // é una cella che contiene i dati
			{
				// creo la hashmap per contenere la riga
				LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
				
				// per ogni cella della riga (scansione per colonna)
				for (HSSFCell cell : cellList) 
				{
					int num_colonna = cell.getColumnIndex();
					int cellType = cell.getCellType();
					
					
					
					
					String dataFormatString = cell.getCellStyle().getDataFormatString();
					String value = "";
					// controllo sul tipo della colonna
					if(cellType== Cell.CELL_TYPE_STRING) value = cell.getStringCellValue();
					else if(cellType== Cell.CELL_TYPE_NUMERIC)
					{
						if(dataFormatString.equals("m/d/yy") || dataFormatString.equals("dd\\.mm\\.yyyy;@") || dataFormatString.equals("dd\\.mm\\.yy;@") || dataFormatString.equals("dd/mm/yy;@")) {
							Date data =cell.getDateCellValue();
							value = getDate(data);
						}
						else if(dataFormatString=="General") {							
							value = "" + cell.getNumericCellValue();
							value = formattaDati(value, intestazione.get(num_colonna));	
						}
						else {
							// dataFormatString=="@"
							cell.setCellType(Cell.CELL_TYPE_STRING);
							value = cell.getStringCellValue();
//							value = formattaDati(value, intestazione.get(num_colonna));	
						}
					}
					else if(cellType== Cell.CELL_TYPE_BLANK /*&& dataFormatString=="m/d/yy" */ )
					{
					}
					else {
						Utils.getLog().info(ExcelReader.class.toString()+": dato non gestito.. formato:"+dataFormatString + " celltype:" + cellType);
					}
					
					//System.out.println("++++++++++ index:" + index + " num_colonna:" + num_colonna + " cellType:" + cellType + " value:" + value);
					
					//String nome_colonna = intestazione.get(num_colonna);
					riga.put(num_colonna, value);		
				}
				
				righe.add(riga);				
			}			
			index++;
		}
		
		FileExcel fileXls = new FileExcel();
		fileXls.setIntestazione(intestazione);
		fileXls.setRighe(righe);
		return fileXls;
	}
	

//	/**
//	 * funzione che restituisce una stringa conenente il numero del titolo a partire dalla cella che lo contiene
//	 * @param numTit
//	 * @return
//	 */
//	private static String getStringValueFromNumber(Cell numTit) {
//		String num_titolo = "";
//		try {
//			if(numTit.getStringCellValue() != null)
//				num_titolo = numTit.getStringCellValue();
//		} catch (Exception e) {
//			num_titolo = "" + new BigDecimal("" + numTit.getNumericCellValue()).intValue() ;
//		}
//		return num_titolo;
//	}

	/**
	 * a partire da un Date restituisce una data in stringa nel formato DD/MM/YYYY
	 * @param data
	 * @return String
	 */
	private static String getDate(Date data) {
		String value;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(data);
		int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
		int month = cal.get(GregorianCalendar.MONTH)+1;
		int year = cal.get(GregorianCalendar.YEAR);
		String day_str = "";
		String month_str = "";
		if(day<10) day_str = "0" + day;
		else day_str = "" + day;
		if(month<10) month_str = "0" + month;
		else month_str = "" + month;
		value = "" + day_str + "/" + month_str + "/" + year;
		return value;
	}

	/**
	 * converte un dato secondo il formato visualizzato nel file
	 * @param value é il valore da convertire
	 * @param intestazione é il nome della colonna
	 * @return String
	 */
	public static String formattaDati(String value, String intestazione)
	{
		String valore = value;
		if(intestazione.equals("Campagna") || intestazione.equals("N° fattispecie"))
		{
			 valore = value.replace(".0", "");
		}
		// controllo sulla lunghezza del numero del titolo
		if(intestazione.equals("N° titolo"))
		{
			if(valore.length()<12)
			{
				String filler ="";
				for (int i = 0; i < 12-valore.length(); i++) {
					filler = filler + "0";
				}
				valore = filler + valore;
			}
			//System.out.println("valore titolo:" + valore + " size:" + valore.length());
		}
		
		return valore;
	}

	public static synchronized String get_errorType() {
		return _errorType;
	}

}

