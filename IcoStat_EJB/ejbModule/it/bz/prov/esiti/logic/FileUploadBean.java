package it.bz.prov.esiti.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
//import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import it.bz.prov.esiti.ibusiness.ICampione;
import it.bz.prov.esiti.ibusiness.ICondizionalita;
import it.bz.prov.esiti.ibusiness.IControllo;
import it.bz.prov.esiti.ibusiness.IEsitoArt68;
import it.bz.prov.esiti.ibusiness.IEsitoImpegni;
import it.bz.prov.esiti.ibusiness.IEsitoImpegniExtra;
import it.bz.prov.esiti.ibusiness.IEsitoImpegniRMFITFER;
import it.bz.prov.esiti.ibusiness.IEsitoImpegniRMFITFERExtra;
import it.bz.prov.esiti.ibusiness.IEsitoSuperfici;
import it.bz.prov.esiti.ibusiness.IFileUpload;
import it.bz.prov.esiti.ibusiness.ISettings;
import it.bz.prov.esiti.ibusiness.ITutelaAcque;
import it.bz.prov.esiti.ibusiness.IUBAAmmissibilita;
import it.bz.prov.esiti.ibusiness.IUba;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.ExcelWriter;
//import it.bz.prov.esiti.util.Utils;

/**
 * Bean per la gestione back-end dell'upload dei file
 * 
 * @author bpettazzoni
 *
 */

@Stateful
public class FileUploadBean implements IFileUpload{
	
	@EJB
	ISettings _settingsEJBBean;
	public static String _UPLOAD_TEMP_PATH;
	private Part _uploadedFile;
	private int _sourcePage; 
	private Object _ejbBean;
	@EJB
	private ICondizionalita _condEJB;
	@EJB
	private ICampione _campEJB;
	@EJB
	private IControllo _contEJB;
	@EJB
	private IEsitoImpegni _esImpEJB;
	@EJB
	private IEsitoSuperfici _esSupEJB;
	@EJB
	private IUba _ubaEJB;
	@EJB
	private IUBAAmmissibilita _ubaAmmissibiltaEJB;
	@EJB
	private ITutelaAcque _tutAcqueEJB; 
	@EJB
	private IEsitoArt68 _esiArt68EJB;
	@EJB
	private IEsitoImpegniExtra _esImpExtraEJB;
	@EJB
	private IEsitoImpegniRMFITFER _esRmFITFEREJB;
	@EJB
	private IEsitoImpegniRMFITFERExtra _esRmFITFERExtraEJB;
	
	/**
	 * costruttore senza parametri
	 */
	public FileUploadBean()
	{
		_ejbBean = null;
	}
	
	@PostConstruct
	private void initFileUploadBean() {
		_UPLOAD_TEMP_PATH = _settingsEJBBean.get_tmp_folder();
	}
	
	/**
	 * esegue l'upload del file
	 * @param List<String> é la lista contenente i log dell'operazione
	 * @return boolean é l'esito complessivo dell'operazione
	 */
	public boolean executeUploadFile(List<String> listError)
	{
		//1. copia del file nella cartella temporanea di JBoss
		String pathFile = copyFile(listError);
		if(pathFile.equals("ERROR")) return false;
		pathFile = _UPLOAD_TEMP_PATH + "//" + pathFile;
		
		//eseguo l'inserimento dei dati sulla base della sorgente che ha richiesto l'upload
		return executeInsert(pathFile, listError);
		
	}
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 * @param listError
	 */
	public void exportFile(List<String> listError, OutputStream stream)
	{
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
		intestazione.put(0, "Messaggio");
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (String log : listError) {
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, log);
			righe.add(riga);
		}
		
		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}
	
	
	/************************************************************************
	 * 				METODI PRIVATI
	 ************************************************************************/
	
	 /**
     * esegue il caricamento del file excel
     * @return STring é il nome del file temporaneo copiato
     */
    @SuppressWarnings("finally")
	private String copyFile(List<String> listError) 
    {
    	listError.add("INFO: richiesto l'upload del file " + _uploadedFile.getSubmittedFileName());
        // Prepare filename prefix and suffix for an unique filename in upload folder.
        String prefix = FilenameUtils.getBaseName(_uploadedFile.getSubmittedFileName());
        String suffix = FilenameUtils.getExtension(_uploadedFile.getSubmittedFileName());
        
        // verifica sull'estensione (se non sto caricando un excel non posso eseguire l'operazione)
        if(!suffix.equals("xls") && !suffix.equals("xlsx"))
        {
        	listError.add("ERROR: é possibile effettuare l'upload solo di file excel");
        	return "ERROR";
        }
        
        // Prepare file and outputstream.
        File tempFile = null;
        OutputStream output = null;
        String outputMessage = "";
        String fileName = "";
        try {
            // Create file with unique name in upload folder and write to it.
        	tempFile = File.createTempFile(prefix + "_", "." + suffix, new File(_UPLOAD_TEMP_PATH));
            output = new FileOutputStream(tempFile);
            IOUtils.copy(_uploadedFile.getInputStream(), output);

            fileName = tempFile.getName();

            // Show succes message.
            outputMessage = "INFO: Upload del file '"+_uploadedFile.getSubmittedFileName()+"' avvenuto con successo";
        } catch (IOException e) {
            // Cleanup.
            if (tempFile != null) tempFile.delete();

            // Show error message.
            outputMessage = "ERROR: Upload fallito a causa di errori di I/O.";
            fileName = "ERROR";
            // Always log stacktraces (with a real logger).
            e.printStackTrace();
        } finally {
        	listError.add(outputMessage);
            IOUtils.closeQuietly(output);
            return fileName;
        }
    }
	
    /**
     * richiama il metodo di insert from file del bean EJB che ha richiesto l'insert
     * @param pathFile é il percorso del file temporaneo
     * @param listError é la lista degli errori
     * @return boolean é l'esito dell'operazione
     */
    private boolean executeInsert(String pathFile, List<String> listError)
    {
    	switch (_sourcePage) {
			case Costanti.TABELLA_CAMPIONE: return _campEJB.insertFromFile(pathFile, listError);
			case Costanti.TABELLA_CONTROLLO: return _contEJB.insertFromFile(pathFile, listError);
			case Costanti.TABELLA_ESITO_COND_AZIENDA: return _condEJB.insertFromFile(pathFile, listError, Costanti.TABELLA_ESITO_COND_AZIENDA);
			case Costanti.TABELLA_ESITO_COND_ATTO: return _condEJB.insertFromFile(pathFile, listError, Costanti.TABELLA_ESITO_COND_ATTO);
			case Costanti.TABELLA_ESITO_IMPEGNI: return _esImpEJB.insertFromFile(pathFile, listError);
			case Costanti.TABELLA_ESITO_IMPEGNI_EXTRACAMPIONE: return _esImpExtraEJB.insertFromFile(pathFile, listError);
			case Costanti.TABELLA_ESITO_SUP: return _esSupEJB.insertFromFile(pathFile, listError);
			case Costanti.TABELLA_ESITO_UBA: return _ubaEJB.insertFromFile(pathFile, listError);		
			case Costanti.TABELLA_ESITO_TUTELA_ACQUE: return _tutAcqueEJB.insertFromFile(pathFile, listError);
			case Costanti.TABELLA_ESITO_ART_68: return _esiArt68EJB.insertFromFile(pathFile, listError);
			case Costanti.TABELLA_ESITO_RMFITFER: return _esRmFITFEREJB.insertFromFile(pathFile, listError);
			case Costanti.TABELLA_ESITO_RMFITFER_EXTRACAMPIONE: return _esRmFITFERExtraEJB.insertFromFile(pathFile, listError);
			case Costanti.TABELLA_ESITO_UBA_AMM: return _ubaAmmissibiltaEJB.insertFromFile(pathFile, listError);
			
			default: return false;
		}
    }
    
    
	/************************************************************************
	 * 				GETTER E SETTER
	 ************************************************************************/

	public void set_uploadedFile(Part _uploadedFile) {
		this._uploadedFile = _uploadedFile;
	}

	public Part get_uploadedFile() {
		return _uploadedFile;
	}


	public void set_sourcePage(int _sourcePage) {
		this._sourcePage = _sourcePage;
	}


	public int get_sourcePage() {
		return _sourcePage;
	}


	public void set_ejbBean(Object _ejbBean) {
		this._ejbBean = _ejbBean;
	}


	public Object get_ejbBean() {
		return _ejbBean;
	}
	
//	/**
//	 * lettura dei parametri dal file stat.properties per la connessione a ldap
//	 */
//	private static void readParameters(){
//		try
//		{
//
//			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Costanti.PROPERTIES_FILE);
//			Properties path = new Properties();
//			path.load(inputStream);
//			String hostname = Utils.getHostName().toLowerCase();
//			if(hostname.equals(Costanti.SERVER_VALAR))
//				_UPLOAD_TEMP_PATH = path.getProperty(Costanti.SET_JBOSS_TMP_FOLDER_VALAR);
//			else if(hostname.equals(Costanti.SERVER_VALAR_DEV)) 
//				_UPLOAD_TEMP_PATH = path.getProperty(Costanti.SET_JBOSS_TMP_FOLDER_VALAR_DEV);
//			else if(hostname.equals(Costanti.SERVER_DEVELUX)) 
//				_UPLOAD_TEMP_PATH = path.getProperty(Costanti.SET_JBOSS_TMP_FOLDER_DEVELUX);
//			else 
//				_UPLOAD_TEMP_PATH = path.getProperty(Costanti.SET_JBOSS_TMP_FOLDER);
//		}
//		catch(IOException e){
//			Utils.getLog().error("file stat.properties non trovato");
//		}
//	}
	
}
