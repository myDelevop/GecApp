package it.bz.prov.esiti.ibusiness;

import java.io.OutputStream;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.Part;

//import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione dell'upload dei file
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IFileUpload {
	
	
	/**
	 * esegue l'upload del file
	 * @param List<String> é la lista contenente i log dell'operazione
	 * @return boolean é l'esito complessivo dell'operazione
	 */
	public boolean executeUploadFile(List<String> listError);
	
	/**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 * @param listError
	 */
	public void exportFile(List<String> listError, OutputStream stream);
	
	/************************************************************************
	 * 				GETTER E SETTER
	 ************************************************************************/

	public void set_uploadedFile(Part _uploadedFile);

	public Part get_uploadedFile() ;
	
	public void set_sourcePage(int _sourcePage) ;

	public int get_sourcePage() ;
	
	public void set_ejbBean(Object _ejbBean) ;
	
	public Object get_ejbBean() ;

}
