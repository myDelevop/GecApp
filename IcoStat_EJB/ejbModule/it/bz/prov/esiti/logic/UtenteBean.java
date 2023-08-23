package it.bz.prov.esiti.logic;

import it.bz.prov.esiti.bobject.ElencoUtentiBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.UtenteBO;
import it.bz.prov.esiti.entity.Utente;
import it.bz.prov.esiti.ibusiness.IUtente;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.ExcelWriter;
import it.bz.prov.esiti.util.Utils;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * gestione delle informazioni relative agli utenti
 * @author bpettazzoni
 *
 */

@Stateful
public class UtenteBean implements IUtente{

	private ElencoUtentiBO elencoUtenti;
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	private static String COL_USERNAME = "Username";
	private static String COL_NOME_COMPLETO = "Nome Completo";
	private static String COL_AMM_UTENTE = "Amministratore Utenti";
	private static String COL_AMM_ANAGRAFICHE = "Amministratore Anagrafiche";
	private static String COL_LETTURA = "Lettura";
	private static String COL_WR_ESITI_IMPEGNI = "Scrittura Esiti Impegni";
	private static String COL_WR_COND = "Scrittura Condizionalita";
	private static String COL_WR_ESITI_SUP = "Scrittura Esiti Superficie";
	private static String COL_WR_TUTELA_ACQUE = "Scrittura Tutela Acque";
	private static String COL_WR_CAMPIONE = "Scrittura Campione";
	private static String COL_WR_UBA = "Scrittura UBA";
	private static String COL_WR_ZOOTECNIA = "Scrittura Zootecnia";
	private static String COL_WR_RMFITFER = "Scrittura RMFITFER";
	private static String COL_USER_CREAZIONE = "Utente Creazione";
	private static String COL_USER_MODIFICA = "Utente Modifica";
	private static String COL_DATA_CREAZIONE = "Data Creazione";
	private static String COL_DATA_MODIFICA = "Data Modifica";
	
	
	/**
	 * costruttore
	 */
	public UtenteBean()
	{	
		elencoUtenti = new ElencoUtentiBO();
	}

	/**
	 * aggiunge un utente alla lista
	 * @param utente
	 * @param userLogged
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiUtente(UtenteBO utente, String userLogged)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(utente, Costanti.ACTION_INSERT);
		if(!result.get_result()) return result;
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			utente.set_userInserimento(userLogged);
			utente.set_dataInserimento(Utils.todayDate());
			Utente utenteEntity = utente.getEntity();		
			_em.clear();
//			_em.getTransaction().begin();
			_em.persist(utenteEntity);
//			_em.getTransaction().commit();
			// inserimento in elenco
//			elencoUtenti.addUtente(utente);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(UtenteBean.class.getSimpleName() + " - aggiungiUtente: " + e.getMessage());
			return result;
		}
	}
	
	/**
	 * cancella un utente dalla lista
	 * @param utente
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaUtente(UtenteBO utente)
	{
		OperationResultBO result = new OperationResultBO();
		try {			
			_em.clear();
			Utente utenteFind = _em.find(Utente.class, utente.get_username());
			// se la riga non é stata trovata su db
			if(utenteFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			_em.remove(utenteFind);
//			_em.getTransaction().commit();
			// cancellazione dall'elenco
			elencoUtenti.removeUtente(utente);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(UtenteBean.class.getSimpleName() + " - cancellaUtente: " + e.getMessage());
			return result;
		}
	}
    
    /**
	 * modifica utenti
	 * @param userLogged
	 * @return OperationResultBO
	 */
    public OperationResultBO modificaUtenti(String userLogged){
		// se i controlli sono andati bene procedo con l'inserimento
		OperationResultBO result = new OperationResultBO();
		boolean user_not_found = false;
		try {
			_em.clear();
//			_em.getTransaction().begin();
			for (UtenteBO utente : elencoUtenti.get_listUtenti()) {
				if(!utente.is_isModified()) continue;
				Utils.getLog().info(UtenteBean.class.getSimpleName() + " - Modifica utente: " +  utente.get_username());
				// recupero l'entity
				Utente utenteFind = _em.find(Utente.class, utente.get_username());
				boolean user_changed = false;
				if(utenteFind == null) {
					user_not_found = true;
					utenteFind = new Utente();
					utente.set_userInserimento(userLogged);
					utente.set_dataInserimento(Utils.todayDate());
					user_changed =true;
				}
				else {
					
					// setto dati di modifica e inserisco
					utente.set_userModifica(userLogged);
					utente.set_dataModifica(Utils.todayDate());
					if(utente.isModify(utenteFind))user_changed =true;
				}	
				// se l'utente risulta modificato dai check sopra
				if(user_changed) utente.setEntity(utenteFind);			
					
				//annullo il set del flag di modifica sull'utente
				utente.set_isModified(false);
			}
//			_em.getTransaction().commit();
			// preparazione risultato
			if(user_not_found)
			{
				result.set_result(true);
				result.set_message(Costanti.MSG_MODIFY_KO_UTENTE);
				return result;
			}
			else 
			{
				result.set_result(true);
				result.set_message(Costanti.MSG_MODIFY_OK);
				return result;
			}
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(UtenteBean.class.getSimpleName() + " - modificaUtenti: " + e.getMessage());
			return result;
		}

    }
    
    /**
	 * carica i dati degli esiti UBA 
	 */
    @SuppressWarnings("unchecked")
	public void loadData()
	{
		Query q = _em.createNamedQuery("Utente.selectAll");
		_em.clear();
		List<Utente> fornitura = q.getResultList();
		elencoUtenti.set_listUtenti(fornitura);
	}
	
	/**
	 * trova un utente su db a partire dallo username (che é la chiave della tabella)
	 * @param username
	 * @return UtenteBO. Se non é stato trovato l'utente vale null
	 */
	public UtenteBO findUtente(String username)
	{
		Utente utente = _em.find(Utente.class, username);
		if(utente == null) return null;
		return new UtenteBO(utente);
	}
    

    /**
	 * esporta i dati visualizzati su file excel
	 * @param stream é lo stream su cui scrivere il file
	 */
	public void exportFile(OutputStream stream)
	{
		// preparazione intestazione
		LinkedHashMap<Integer, String> intestazione = getListaIntestazioni();
		
		// preparazione dati
		ArrayList<LinkedHashMap<Integer, String>> righe = new ArrayList<LinkedHashMap<Integer,String>>(); 
		for (UtenteBO utente : elencoUtenti.get_listUtenti()) {
			
			String flg_admin_user = "NO";
			String flg_admin_desc = "NO";
			String flg_role_read = "NO";
			String flg_role_wr_imp = "NO";
			String flg_role_wr_cond = "NO";
			String flg_role_wr_sup = "NO";
			String flg_role_wr_acque = "NO";
			String flg_role_wr_camp = "NO";
			String flg_role_wr_uba = "NO";
			String flg_role_wr_zoot = "NO";
			String flg_role_wr_RMFITFER = "NO";			

			
			//COL_AMM_UTENTE
			if(utente.get_roleAdminUser()) flg_admin_user="SI"; 
			// COL_AMM_ANAGRAFICHE
			if(utente.get_roleAdminDescription()) flg_admin_desc="SI"; 
			// COL_LETTURA
			if(utente.get_roleUser()) flg_role_read="SI"; 
			// COL_WR_ESITI_IMPEGNI
			if(utente.get_roleWrImp()) flg_role_wr_imp="SI"; 
			// COL_WR_COND
			if(utente.get_roleWrCond()) flg_role_wr_cond="SI"; 
			// COL_WR_ESITI_SUP
			if(utente.get_roleWrSup()) flg_role_wr_sup="SI"; 
			// COL_WR_TUTELA_ACQUE
			if(utente.get_roleWrAcque()) flg_role_wr_acque="SI"; 
			// COL_WR_CAMPIONE
			if(utente.get_roleWrCamp()) flg_role_wr_camp="SI"; 
			// COL_WR_UBA
			if(utente.get_roleWrUba()) flg_role_wr_uba="SI";
			// COL_WR_ZOOTECNIA
			if(utente.get_roleWrZoot()) flg_role_wr_zoot="SI";
			// COL_WR_RMFITFER
			if(utente.get_roleWrRMFITFER()) flg_role_wr_RMFITFER="SI";
			
			LinkedHashMap<Integer, String> riga = new LinkedHashMap<Integer, String>();
			riga.put(0, utente.get_username()); //COL_USERNAME
			riga.put(1, utente.get_completeName()); //COL_NOME_COMPLETO
			riga.put(2, flg_admin_user); //COL_AMM_UTENTE
			riga.put(3, flg_admin_desc); //COL_AMM_ANAGRAFICHE
			riga.put(4, flg_role_read); // COL_LETTURA
			riga.put(5, flg_role_wr_imp); //COL_WR_ESITI_IMPEGNI
			riga.put(6, flg_role_wr_RMFITFER); // COL_WR_RMFITFER
			riga.put(7, flg_role_wr_cond); //COL_WR_COND
			riga.put(8, flg_role_wr_sup); // COL_WR_ESITI_SUP
			riga.put(9, flg_role_wr_acque); // COL_WR_TUTELA_ACQUE
			riga.put(10, flg_role_wr_camp); // COL_WR_CAMPIONE
			riga.put(11, flg_role_wr_uba); // COL_WR_UBA
			riga.put(12, flg_role_wr_zoot); // COL_WR_ZOOTECNIA
			riga.put(13, utente.get_userInserimento()); // COL_USER_CREAZIONE
			riga.put(14, utente.get_dataInserimentoStr()); // COL_DATA_CREAZIONE
			riga.put(15, utente.get_userModifica()); // COL_USER_MODIFICA
			riga.put(16, utente.get_dataModificaStr()); //COL_DATA_MODIFICA			
			righe.add(riga);
		}
		
		// creazione dell'oggetto excel e scrittura su stream
		ExcelWriter.writeFile(righe, intestazione, stream);
	}
	
    /***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update)
     * @param utente
     * @param action
     * @return OperationResultBO
     */
    private OperationResultBO checkPreInsert(UtenteBO utente, String action)
    {
    	OperationResultBO result = new OperationResultBO();
    	
    	//1. l'utente non deve essere già presente su DB (solo insert)
    	if(action.equals(Costanti.ACTION_INSERT))
		{
			_em.clear();
			Utente utenteFind = _em.find(Utente.class, utente.get_username());
			if(utenteFind != null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_CHECK_USER_FOUND);
				return result;
			}
		}
		
		// se arrivo qui ha superato i controlli
		result.set_result(true);
    	return result;
    }
    
    
    /**
     * prepara la lista di tutte le intestazioni delle colonne presenti nel file
     * @return LinkedHashMap<Integer, String>
     */
    private static LinkedHashMap<Integer, String> getListaIntestazioni()
    {
    	LinkedHashMap<Integer, String> intestazione = new LinkedHashMap<Integer, String>();
    	intestazione.put(0, COL_USERNAME);
		intestazione.put(1, COL_NOME_COMPLETO);
		intestazione.put(2, COL_AMM_UTENTE);
		intestazione.put(3, COL_AMM_ANAGRAFICHE);
		intestazione.put(4, COL_LETTURA);
		intestazione.put(5, COL_WR_ESITI_IMPEGNI);
		intestazione.put(6, COL_WR_RMFITFER);
		intestazione.put(7, COL_WR_COND);
		intestazione.put(8, COL_WR_ESITI_SUP);
		intestazione.put(9, COL_WR_TUTELA_ACQUE);
		intestazione.put(10, COL_WR_CAMPIONE);
		intestazione.put(11, COL_WR_UBA);
		intestazione.put(12, COL_WR_ZOOTECNIA);
		intestazione.put(13, COL_USER_CREAZIONE);
		intestazione.put(14, COL_DATA_CREAZIONE);
		intestazione.put(15, COL_USER_MODIFICA);
		intestazione.put(16, COL_DATA_MODIFICA);		
    	return intestazione;
    }
	
    
    /***************************************************************************************
	 * 			GETTER E SETTER
	 ***************************************************************************************/
	
    
    /**
	 * 
	 * @return ElencoUtentiBO
	 */
    public ElencoUtentiBO getElencoUtenti()
    {
        return this.elencoUtenti;
    }

    /**
     * imposta l'elenco degli utenti
     * @param elencoUtenti
     */
    public void setElencoUtenti(final ElencoUtentiBO elencoUtenti)
    {
        this.elencoUtenti = elencoUtenti;
    }
	

}
