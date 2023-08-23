package it.bz.prov.esiti.ibusiness;

import java.io.OutputStream;

import it.bz.prov.esiti.bobject.ElencoUtentiBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.UtenteBO;

import javax.ejb.Local;

/**
 * Interfaccia esposta verso lo strato presentation per la gestione utenti
 * 
 * @author bpettazzoni
 *
 */

@Local
public interface IUtente {
	
	/**
	 * aggiunge un utente alla lista
	 * @param utente
	 * @param userLogged
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiUtente(UtenteBO utente, String userLogged);
	
	/**
	 * cancella un utente dalla lista
	 * @param utente
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaUtente(UtenteBO utente);
    
    /**
	 * modifica utenti
	 * @param userLogged
	 * @return OperationResultBO
	 */
    public OperationResultBO modificaUtenti(String userLogged);
    
    /**
	 * carica i dati degli esiti UBA 
	 */
	public void loadData();
	
	/**
	 * trova un utente su db a partire dallo username (che é la chiave della tabella)
	 * @param username
	 * @return UtenteBO. Se non é stato trovato l'utente vale null
	 */
	public UtenteBO findUtente(String username);
	

    /**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream);
	
	
    /***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
	
    
    /**
	 * 
	 * @return ElencoUtentiBO
	 */
    public ElencoUtentiBO getElencoUtenti();
    
    /**
     * imposta l'elenco degli utenti
     * @param elencoUtenti
     */
    public void setElencoUtenti(final ElencoUtentiBO elencoUtenti);
	
}
