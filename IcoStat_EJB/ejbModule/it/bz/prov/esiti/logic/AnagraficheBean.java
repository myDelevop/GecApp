package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.AttoCondizionalitaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.ValoreAnagraficaBO;
import it.bz.prov.esiti.entity.AttoCondizionalita;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.entity.ValoreAnagraficaPk;
import it.bz.prov.esiti.ibusiness.IAnagrafiche;
import it.bz.prov.esiti.util.CheckData;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Bean per la gestione back-end delle anagrafiche
 * 
 * @author bpettazzoni
 *
 */

@Stateful
@SuppressWarnings("unchecked")
public class AnagraficheBean implements IAnagrafiche {
	private List<String> listAnagrafiche =  new ArrayList<String>();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	/**
	 * costruttore
	 */
	public AnagraficheBean()
	{

	}
	
	@PostConstruct
	private void init() {
		// carico la lista dei campi presenti nell'anagrafica
		Query q = _em.createNamedQuery("ValoreAnagrafica.selectDistinctCampi", String.class);
		_em.clear();
		listAnagrafiche = q.getResultList();
		// aggiungo la voce dei codici di condizionalità che sono gestiti a parte
		listAnagrafiche.add(Costanti.ANAGR_COD_ATTI_COND);
	}

	public void setListAnagrafiche(final List<String> listAnagrafiche) {
		this.listAnagrafiche = listAnagrafiche;
	}

	public List<String> getListAnagrafiche() {
		return listAnagrafiche;
	}
	
	
	/**
	 * visualizza i valori dell'anagrafica selezionata
	 * @param anagrafica
	 * @return List<ValoreAnagrafica>
	 */
	public List<ValoreAnagraficaBO> visualizzaValori(String anagrafica)
	{
		// ottengo la lista dei valori per l'anagrafica scelta
		Query q = _em.createNamedQuery("ValoreAnagrafica.selectAnagrafica", ValoreAnagrafica.class);
		_em.clear();
		q.setParameter("campo", anagrafica);
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<ValoreAnagrafica> fornitura = q.getResultList();
		List<ValoreAnagraficaBO> list = new ArrayList<ValoreAnagraficaBO>(); 
		for (ValoreAnagrafica valoreAnagrafica : fornitura) {
			list.add(new ValoreAnagraficaBO(valoreAnagrafica));
		}
		return list;
	}
	
	/**
	 * aggiunge un nuovo valore alla lista
	 * @param valoreAnagrafica
	 * @return OperationResultBO
	 */
	@SuppressWarnings({ "unused", "unlikely-arg-type" })
	public OperationResultBO aggiungiValore(ValoreAnagraficaBO valoreAnagrafica)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsert(valoreAnagrafica, Costanti.ACTION_INSERT);
		if(!result.get_result()) 
		{
			// devo cancellare dall'elenco questo oggetto perchè non deve essere inserito
			boolean remove = listAnagrafiche.remove(valoreAnagrafica);
			return result;
		}
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			ValoreAnagrafica valoreAnagraficaEntity = valoreAnagrafica.getEntity();
			valoreAnagraficaEntity.set_userInserimento(Utils.getCurrentUser());
			valoreAnagraficaEntity.set_dataInserimento(Utils.todayDate());
//			_em.getTransaction().begin();
			_em.persist(valoreAnagraficaEntity);
//			_em.getTransaction().commit();
			// non aggiungo l'oggetto all'elenco prchè é già presente
			// imposto il risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + " - aggiungiValore: " + e.getMessage());
			return result;
		}
	}
	
	
	/**
	 * modifica un valore nella lista. 
	 * Se viene cambiata la campagna di inizio validità la riga viene storicizzata su database, 
	 * in modo da mantenere le informazioni valide per le campagne precedenti
	 * @param valoreAnagrafica
	 * @param valoreAnagraficaOld
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaValore(ValoreAnagraficaBO valoreAnagrafica, ValoreAnagraficaBO valoreAnagraficaOld)
	{
		OperationResultBO result = new OperationResultBO();
		// se non ci sono differenze tra i due oggetti significa che non sono state fatte modifiche
		// quindi non salvo
		if(valoreAnagrafica.get_valore().equals(valoreAnagraficaOld.get_valore()) &&
				valoreAnagrafica.get_campagnaInizioVal() == valoreAnagraficaOld.get_campagnaInizioVal())
		{
			//restituisco true perchè non é un errore ma non warning e voglio che ritorni alla maschera principale
			result.set_result(true);
			result.set_message(Costanti.MSG_CHECK_MODIFICA_FATTA);
			return result;
		}
		// controlli lato server pre-inserimento
		result = checkPreInsert(valoreAnagrafica, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con la modifica
		result = new OperationResultBO();
		
		// recupero l'oggetto relativo alla vecchia riga
		ValoreAnagraficaPk pk = new ValoreAnagraficaPk();
		pk.set_campo(valoreAnagraficaOld.get_campo());
		pk.set_valore(valoreAnagraficaOld.get_valore());
		pk.set_campagnaFineVal(valoreAnagraficaOld.get_campagnaFineVal());
		pk.set_campagnaInizioVal(valoreAnagraficaOld.get_campagnaInizioVal());
		//recupero il vecchio valore, che corrisponde all'oggetto old
		ValoreAnagrafica anagraficaOldFind = _em.getReference(ValoreAnagrafica.class, pk);
		if(anagraficaOldFind == null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
			return result;
		}
		
		// se la campagna di inizio validità é stata modificata storicizzo la riga 
		// vecchia e la modifica viene realizzata in una nuova riga
		if(valoreAnagrafica.get_campagnaInizioVal() != valoreAnagraficaOld.get_campagnaInizioVal())
		{
			// devo modificare la campagna di fine validità della riga vecchia (la setto in modo che 
			// sia precedente a quella di inizio validità di quella nuova)
			// per fare questo però devo cancellare fisicamente la riga vacchia (perchè modificando
			// la campagna di fine validità modifico la chive) e creo 2 righe, una che é quella valida
			// e una che corrisponde a quella storicizzata
			try {
				_em.clear();
//				_em.getTransaction().begin();

				//cancello fisicamente l'oggetto
				_em.remove(anagraficaOldFind);
				
				// clono l'oggetto originale
				ValoreAnagrafica va_copy = anagraficaOldFind.clone();
				
				// setto la campagna di fine validità precedente a quella di inizio della riga nuova 
				va_copy.set_campagnaFineVal(valoreAnagrafica.get_campagnaInizioVal()-1);
				va_copy.set_userModifica(Utils.getCurrentUser());
				va_copy.set_dataModifica(Utils.todayDate());

				// creo la nuova riga con l'oggetto da storicizzare
				_em.persist(va_copy);
				
				// clono l'oggetto originale
				ValoreAnagrafica vaNew_copy = va_copy.clone(); 
				
				// imposto i parametri per la riga valida
				vaNew_copy.set_valore(valoreAnagrafica.get_valore());
				vaNew_copy.set_campagnaInizioVal(valoreAnagrafica.get_campagnaInizioVal());
				vaNew_copy.set_campagnaFineVal(valoreAnagrafica.get_campagnaFineVal());

				// creo la nuova riga attualmente valida
				_em.persist(vaNew_copy);
//				_em.getTransaction().commit();
				// imposto il risultato
				result.set_result(true);
				result.set_message(Costanti.MSG_MODIFY_OK);
				return result;
			} catch (Exception e) {
				result.set_result(false);
				result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
				Utils.getLog().error(AnagraficheBean.class.getSimpleName() + " - modificaValore: " + e.getMessage());
				return result;
			}
		}
		else // é stata cambiata solo la descrizione
		{
			// in questo caso, visto che la descrizione fa parte della chiave, devo eliminare la riga vecchia
			// e la sostituisco con la nuova, mantenendo però i dati di creazione della riga vecchia
			// quindi non c'è storicizzazione ma solo sostituzione
			try {
				
				_em.clear();
//				_em.getTransaction().begin();
				//cancello fisicamente l'oggetto
				_em.remove(anagraficaOldFind);
				// clono l'oggetto originale
				ValoreAnagrafica va_copy = anagraficaOldFind.clone();
				// setto la campagna di fine validità precedente a quella di inizio della riga nuova 
				va_copy.set_valore(valoreAnagrafica.get_valore());
				va_copy.set_userModifica(Utils.getCurrentUser());
				va_copy.set_dataModifica(Utils.todayDate());
				// creo la nuova riga con l'oggetto da storicizzare
				_em.persist(va_copy);
//				_em.getTransaction().commit();
				// imposto il risultato
				result.set_result(true);
				result.set_message(Costanti.MSG_MODIFY_OK);
				return result;
			} catch (Exception e) {
				result.set_result(false);
				result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
				Utils.getLog().error(AnagraficheBean.class.getSimpleName() + " - modificaValore: " + e.getMessage());
				return result;
			}
		}
	}
	
	
	/**
	 * cancella un valore nella lista
	 * @param valoreAnagrafica
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaValore(ValoreAnagraficaBO valoreAnagrafica)
	{
		OperationResultBO result = new OperationResultBO();
		try {
			ValoreAnagraficaPk pk = new ValoreAnagraficaPk();
			pk.set_campo(valoreAnagrafica.get_campo());
			pk.set_valore(valoreAnagrafica.get_valore());
			pk.set_campagnaFineVal(valoreAnagrafica.get_campagnaFineVal());
			pk.set_campagnaInizioVal(valoreAnagrafica.get_campagnaInizioVal());
			ValoreAnagrafica anagraficaFind = _em.find(ValoreAnagrafica.class, pk);
			if(anagraficaFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			_em.remove(anagraficaFind);
//			_em.getTransaction().commit();
			// imposto il risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + " - cancellaValore: " + e.getMessage());
			return result;
		}
	}
	
	
	/**
	 * visualizza i valori dell'anagrafica dei codici e degli atti di condizionalità
	 * @return List<AttoCondizionalitaBO>
	 */
	public List<AttoCondizionalitaBO> visualizzaCodiciAttiCond()
	{
		// ottengo la lista dei valori per l'anagrafica scelta
		Query q = _em.createNamedQuery("AttoCondizionalita.selectAll", AttoCondizionalita.class);
		_em.clear();
		List<AttoCondizionalita> fornitura = q.getResultList();
		List<AttoCondizionalitaBO> list = new ArrayList<AttoCondizionalitaBO>(); 
		for (AttoCondizionalita atto : fornitura) {
			list.add(new AttoCondizionalitaBO(atto));
		}
		return list;
	}
	
	
	/**
	 * aggiunge un nuovo atto all'anagrafica di codici e atti di condizionalità
	 * @param atto
	 * @return OperationResultBO
	 */
	public OperationResultBO aggiungiAttoCond(AttoCondizionalitaBO atto)
	{
		// controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsertAttoCond(atto, Costanti.ACTION_INSERT);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			AttoCondizionalita attoEntity = atto.getEntity();
			attoEntity.set_user_inserimento(Utils.getCurrentUser());
			attoEntity.set_data_inserimento(Utils.todayDate());
//			_em.getTransaction().begin();
			_em.persist(attoEntity);
//			_em.getTransaction().commit();
			// imposto il risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + " - aggiungiAttoCond: " + e.getMessage());
			return result;
		}
	}
	
	
	/**
	 * modifica un valore nella lista di atti di condizionalita
	 * Se viene cambiato il valore dell'atto, visto che é PK viene cancellata la riga vecchia e inserita la riga 
	 * nuova con i dati di inserimento (user e data) vecchi, in modo da rendere l'idea di una riga modificata
	 * e non di una riga nuova
	 * @param atto
	 * @param attoOld
	 * @return OperationResultBO
	 */
	public OperationResultBO modificaAttoCond(AttoCondizionalitaBO atto, AttoCondizionalitaBO attoOld)
	{
		OperationResultBO result = new OperationResultBO();
		// se non ci sono differenze tra i due oggetti significa che non sono state fatte modifiche
		// quindi non salvo
		if(atto.get_codCond().equals(attoOld.get_codCond()) &&
				atto.get_attoCond() == attoOld.get_attoCond())
		{
			//restituisco true perchè non é un errore ma non warning e voglio che ritorni alla maschera principale
			result.set_result(true);
			result.set_message(Costanti.MSG_CHECK_MODIFICA_FATTA);
			return result;
		}
		// controlli lato server pre-inserimento
		result = checkPreInsertAttoCond(atto, Costanti.ACTION_MODIFY);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con la modifica
		result = new OperationResultBO();
		
		//recupero il vecchio valore, che corrisponde all'oggetto old
		AttoCondizionalita attoOldFind = _em.getReference(AttoCondizionalita.class, attoOld.get_attoCond());
		if(attoOldFind == null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
			return result;
		}

		// elimino la riga vecchia e la sostituisco con la nuova, mantenendo però 
		//i dati di creazione della riga vecchia
		// quindi non c'è storicizzazione ma solo sostituzione
		try {
			
			_em.clear();
//			_em.getTransaction().begin();
			//cancello fisicamente l'oggetto
			_em.remove(attoOldFind);
			// clono l'oggetto originale
			AttoCondizionalita atto_copy = attoOldFind.clone();
			// setto la campagna di fine validità precedente a quella di inizio della riga nuova 
			atto_copy.set_codCond(atto.get_codCond());
			atto_copy.set_attoCond(atto.get_attoCond());
			atto_copy.set_user_modifica(Utils.getCurrentUser());
			atto_copy.set_data_modifica(Utils.todayDate());
			// creo la nuova riga con l'oggetto da storicizzare
			_em.persist(atto_copy);
//			_em.getTransaction().commit();
			// imposto il risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_MODIFY_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + " - modificaAttoCond: " + e.getMessage());
			return result;
		}
	}
	
	
	/**
	 * cancella un valore dell'anagrafica degli atti di condizionalità
	 * @param atto
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaAttoCond(AttoCondizionalitaBO atto)
	{
		OperationResultBO result = new OperationResultBO();
		try {
			AttoCondizionalita attoFind = _em.find(AttoCondizionalita.class, atto.get_attoCond());
			if(attoFind == null)
			{
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
//			_em.getTransaction().begin();
			_em.remove(attoFind);
//			_em.getTransaction().commit();
			// imposto il risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(AnagraficheBean.class.getSimpleName() + " - cancellaAttoCond: " + e.getMessage());
			return result;
		}
	}
	
	
	 /***************************************************************************************
	 * 			METODI UTILITY
	 ***************************************************************************************/
	
	
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna()
	{
		_em.clear();
		Query q = _em.createNamedQuery("Domanda.distinctCampagna");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	
	 /***************************************************************************************
	 * 			METODI PRIVATI
	 ***************************************************************************************/
	
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update)
     * @param anagrafica
     * @param action
     * @return OperationResultBO
     */
    private OperationResultBO checkPreInsert(ValoreAnagraficaBO valoreAnagrafica, String action)
    {
    	OperationResultBO result = new OperationResultBO();
    	    	
    	//1. Campo anagrafica non vuoto o nullo
    	if(!CheckData.checkString(valoreAnagrafica.get_campo()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_ANAGR_NOME_NULLO);
			return result;
		}
    	
    	//2. Valore anagrafica non vuoto o nullo
    	if(!CheckData.checkString(valoreAnagrafica.get_valore()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_ANAGR_VALORE_NULLO);
			return result;
		}
    	
    	//3. campagna di inizio validità >= 2009
    	if(valoreAnagrafica.get_campagnaInizioVal() < 2009)
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_CAMPAGNA_INIZIO_VAL);
			return result;
		}
    	
    	//4. la coppia campo-valore-campagna_fine_val non deve essere già presente in anagrafica
    	_em.clear();
    	ValoreAnagraficaPk pk = new ValoreAnagraficaPk();
    	pk.set_campo(valoreAnagrafica.get_campo());
    	pk.set_valore(valoreAnagrafica.get_valore());
    	pk.set_campagnaFineVal(valoreAnagrafica.get_campagnaFineVal());
		ValoreAnagrafica vaFind = _em.find(ValoreAnagrafica.class, pk);
		if(vaFind != null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_ANAGR_PK_PRESENTE);
			return result;
		}
 
		// se arrivo qui ha superato i controlli
		result.set_result(true);
    	return result;
    }
    
    /**
     * Effettua dei controlli sulla coerenza dei dati prima di 
     * eseguire la scrittura su db (insert o update) sulla tabella dell'anagrafica degli 
     * atti di condizionalità
     * @param anagrafica
     * @param action
     * @return OperationResultBO
     */
    private OperationResultBO checkPreInsertAttoCond(AttoCondizionalitaBO atto, String action)
    {
    	OperationResultBO result = new OperationResultBO();
    	    	
    	//1. Campo codice di condizionalità non vuoto o nullo
    	if(!CheckData.checkString(atto.get_codCond()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_ANAGR_COD_COND_NULLO);
			return result;
		}
    	
    	//2. Valore atto di condizionalità non vuoto o nullo
    	if(!CheckData.checkString(atto.get_attoCond()))
    	{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_ANAGR_ATTO_COND_NULLO);
			return result;
		}
    	
    	//3. l'atto non deve essere già presente in anagrafica
    	_em.clear();
		AttoCondizionalita attoFind = _em.find(AttoCondizionalita.class, atto.get_attoCond());
		if(attoFind != null)
		{
			result.set_result(false);
			result.set_message(Costanti.MSG_CHECK_ANAGR_PK_PRESENTE);
			return result;
		}
 
		// se arrivo qui ha superato i controlli
		result.set_result(true);
    	return result;
    }
	
	
}
