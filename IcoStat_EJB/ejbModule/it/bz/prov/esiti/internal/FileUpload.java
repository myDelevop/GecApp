/**********************************************************************
* FILE:  FileUploadBean.java                                         
* DEVELOPED BY ICONSULTING SRL                                                 
* AUTORS..: BARBARA BONOLI, BARBARA PETTAZZONI
* CREATED: 17/feb/2012                                                      
**********************************************************************/
package it.bz.prov.esiti.internal;

import it.bz.prov.esiti.util.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;



/**********************************************************************
 * FileUploadBean
 * DESCRIZIONE: 
 * Classe dedicata all'upload del file excel.
 * Viene utilizzato un file temporaneo di appoggio.
 **********************************************************************/

public class FileUpload {


	// Percorso di salvataggio del file temporaneo
	public static String _UPLOAD_TEMP_PATH = System.getProperty("jboss.server.temp.dir");
	//public static String _UPLOAD_TEMP_PATH = "D:\\";
	
	private HashMap<String, String> _erroriSollevati = null;
	
    private Part uploadedFile;
    private String fileName;
    private File tempFile;
    
    /**
     * costruttore
     */
    public FileUpload(){
    	//System.out.println(_infoMsg+" Rilevata la directory temporanea di JBoss: "
    		//	+_UPLOAD_TEMP_PATH);
		_erroriSollevati = new HashMap<String, String>();

    }

    /**
     * esegue il caricamento del file excel
     */
    public void submit() {

        // Just to demonstrate what information you can get from the uploaded file.
//        System.out.println("File type: " + uploadedFile.getContentType());
//        System.out.println("File name: " + uploadedFile.getName());
//        System.out.println("File size: " + uploadedFile.getSize() + " bytes");

        // Prepare filename prefix and suffix for an unique filename in upload folder.
        String prefix = FilenameUtils.getBaseName(uploadedFile.getName());
        String suffix = FilenameUtils.getExtension(uploadedFile.getName());
        
        // Prepare file and outputstream.
        tempFile = null;
        OutputStream output = null;
        String outputMessage = "";
        
        try {
            // Create file with unique name in upload folder and write to it.
            tempFile = File.createTempFile(prefix + "_", "." + suffix, new File(_UPLOAD_TEMP_PATH));
            Utils.getLog().info(FileUpload.class.toString()+": File temporaneo da salvare in: "+ _UPLOAD_TEMP_PATH);
            output = new FileOutputStream(tempFile);
            IOUtils.copy(uploadedFile.getInputStream(), output);
            fileName = tempFile.getName();

            // Show succes message.
            outputMessage = "Upload del file '"+uploadedFile.getName()+"' avvenuto con successo";
        } catch (IOException e) {
            // Cleanup.
            if (tempFile != null) tempFile.delete();

            // Show error message.
            outputMessage = "Upload fallito a causa di errori di I/O.";
            Utils.getLog().error(FileUpload.class.toString()+": File temporaneo da salvare in: "+ _UPLOAD_TEMP_PATH);

            // Always log stacktraces (with a real logger).
            e.printStackTrace();
        } finally {
        	_erroriSollevati.put("SEVERITY_INFO", outputMessage);
            IOUtils.closeQuietly(output);
            tempFile.delete();
        }
    }

    // Getters ------------------------------------------------------------------------------------

    /**
     * restituisce l'oggetto che rappresenta il file caricato
     */
    public Part getUploadedFile() {
        return uploadedFile;
    }

    /**
     * restituisce il nome del file caricato
     * @return String
     */
    public String getFileName() {
        return fileName;
    }

    // Setters ------------------------------------------------------------------------------------

    /**
     * imposta l'oggetto che rappresenta il file caricato
     */
    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    /**
     * cancella il file temporaneo creato nella directory temporanea di jboss
     * per essere utilizzato come fonte dati per l'upload
     */
    public void deleteTempFile(){
    	tempFile.delete();
    }

    /**
     * restituisce gli errori sollevati durante l'esecuzione
     * @return HashMap<String, String>
     */
	public HashMap<String, String> collectGeneratedErrors() {
		return _erroriSollevati;
	}
	
	/**
	 * cancella gli errori generati
	 */
	public void clearGeneratedMessages(){
		 _erroriSollevati.clear();
	}

}