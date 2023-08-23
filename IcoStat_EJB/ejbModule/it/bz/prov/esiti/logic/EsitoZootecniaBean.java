package it.bz.prov.esiti.logic;


import it.bz.prov.esiti.bobject.DomandaBO;
import it.bz.prov.esiti.bobject.ElencoEsitoZootecniaBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaControlloAmmBovBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaControlloAmmOviBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaControlloCondBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaEsitoAmmBovBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaEsitoAmmOviBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaStatoBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.entity.Azienda;
import it.bz.prov.esiti.entity.Domanda;
import it.bz.prov.esiti.entity.DomandaPk;
import it.bz.prov.esiti.entity.EsitoZootecniaControlloAmmBov;
import it.bz.prov.esiti.entity.EsitoZootecniaControlloAmmBovPk;
import it.bz.prov.esiti.entity.EsitoZootecniaControlloAmmOvi;
import it.bz.prov.esiti.entity.EsitoZootecniaControlloAmmOviPk;
import it.bz.prov.esiti.entity.EsitoZootecniaControlloCond;
import it.bz.prov.esiti.entity.EsitoZootecniaControlloCondPk;
import it.bz.prov.esiti.entity.EsitoZootecniaEsitoAmmBov;
import it.bz.prov.esiti.entity.EsitoZootecniaEsitoAmmOvi;
import it.bz.prov.esiti.entity.EsitoZootecniaEsitoAmmOviPk;
import it.bz.prov.esiti.entity.EsitoZootecniaStato;
import it.bz.prov.esiti.entity.EsitoZootecniaStatoPk;
import it.bz.prov.esiti.entity.Sottointervento;
import it.bz.prov.esiti.entity.ValoreAnagrafica;
import it.bz.prov.esiti.ibusiness.IEsitoZootecnia;
import it.bz.prov.esiti.util.CheckData;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


/**
 * Bean per la gestione back-end degli esiti impegni
 * 
 * @author bpettazzoni
 *
 */

@Stateful
@SuppressWarnings("unchecked")
@TransactionManagement(TransactionManagementType.BEAN)
public class EsitoZootecniaBean implements IEsitoZootecnia{
	
	public static int INSERT_ESITO = 0;
	public static int INSERT_DATI_CONTROLLO = 1;
	public static int UPDATE_STATO_COMPILAZIONE = 2;
	private ElencoEsitoZootecniaBO elencoEsitoZoot = new ElencoEsitoZootecniaBO();
	
	@PersistenceContext(unitName = "jpa_definition")
	private EntityManager _em;
	
	@Resource
	private UserTransaction tx;
	
	/********************************************************
	 * Esito e dati controllo
	 ********************************************************/
	
	/**
	 * salva i dati relativi al controllo
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaEsito(EsitoZootecniaStatoBO esito, int azione){
		//controlli lato server pre-inserimento
		OperationResultBO result = new OperationResultBO();
		if(azione == INSERT_ESITO) result = checkPreInsertDatiEsito(esito);
		else if(azione == INSERT_DATI_CONTROLLO) result = checkPreInsertDatiControllo(esito);
		else if(azione == UPDATE_STATO_COMPILAZIONE) result.set_result(true); // non faccio nessun controllo
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			_em.clear();
			this.tx.begin();

			EsitoZootecniaStatoPk pk = new EsitoZootecniaStatoPk();
			pk.set_campagna(esito.get_campagna());
			pk.set_cuaa(esito.get_cuaa());
			pk.set_domanda(esito.get_domanda());
			EsitoZootecniaStato esitoFind = _em.find(EsitoZootecniaStato.class, pk);
			
			// se sto inserendo una riga che esiste già e sono in modalità inserimento esito
			if(esitoFind != null && azione == INSERT_ESITO){
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_TROVATA);
				this.tx.rollback();
				return result;
			}

			if(esitoFind == null){
				// popolamento e salvataggio della entity
				esitoFind = esito.getEntity();
				esitoFind.set_user_inserimento(Utils.getCurrentUser());
				esitoFind.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoFind);
				// popolamento dell'oggetto BO
				esito.set_userInserimento(Utils.getCurrentUser());
				esito.set_dataInserimento(Utils.todayDate());
			}
			else {
				// popolamento e salvataggio della entity
				esito.setEntity(esitoFind);
				esitoFind.set_user_modifica(Utils.getCurrentUser());
				esitoFind.set_data_modifica(Utils.todayDate());
				// popolamento dell'oggetto BO
				esito.set_userModifica(Utils.getCurrentUser());
				esito.set_dataModifica(Utils.todayDate());
				
			}			

			this.tx.commit();
			// inserimento in elenco
			
			if(azione == INSERT_ESITO && !elencoEsitoZoot.get_listEsitoZoot().contains(esito)) 
				elencoEsitoZoot.addEsito(esito);
			
			// setting dei flag per gli interventi
			setFlagInterventi(esito);
			
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsito: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsito: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsito: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsito: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	/**
	 * cancella i dati relativi all'esito
	 * @param esito 
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsito(EsitoZootecniaStatoBO esito){
		//controlli lato server pre-inserimento
		OperationResultBO result = new OperationResultBO();
	
		try {
			_em.clear();
			// stato dell'esito di zootecnia
			EsitoZootecniaStatoPk pk = new EsitoZootecniaStatoPk();
			pk.set_campagna(esito.get_campagna());
			pk.set_cuaa(esito.get_cuaa());
			pk.set_domanda(esito.get_domanda());
			EsitoZootecniaStato esitoFind = _em.find(EsitoZootecniaStato.class, pk);
			if(esitoFind == null){
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}
			// dati del controlli di ammissibilità bovini
			EsitoZootecniaControlloAmmBovPk pkCtrAmmBov = new EsitoZootecniaControlloAmmBovPk();
			pkCtrAmmBov.set_campagna(esito.get_campagna());
			pkCtrAmmBov.set_cuaa(esito.get_cuaa());
			pkCtrAmmBov.set_domanda(esito.get_domanda());
			EsitoZootecniaControlloAmmBov ctrAmmBovFind = _em.find(EsitoZootecniaControlloAmmBov.class, pkCtrAmmBov);
			
			// dati del controlli di ammissibilità ovicaprini
			EsitoZootecniaControlloAmmOviPk pkCtrAmmOvi = new EsitoZootecniaControlloAmmOviPk();
			pkCtrAmmOvi.set_campagna(esito.get_campagna());
			pkCtrAmmOvi.set_cuaa(esito.get_cuaa());
			pkCtrAmmOvi.set_domanda(esito.get_domanda());
			EsitoZootecniaControlloAmmOvi ctrAmmOviFind = _em.find(EsitoZootecniaControlloAmmOvi.class, pkCtrAmmOvi);
			
			// dati del controlli di condizionalità
			EsitoZootecniaControlloCondPk pkCtrCond = new EsitoZootecniaControlloCondPk();
			pkCtrCond.set_campagna(esito.get_campagna());
			pkCtrCond.set_cuaa(esito.get_cuaa());
			pkCtrCond.set_domanda(esito.get_domanda());
			EsitoZootecniaControlloCond ctrCondFind = _em.find(EsitoZootecniaControlloCond.class, pkCtrCond);
			
			// dati dell'esito del controllo di amm. bovini
//			EsitoZootecniaEsitoAmmBovPk pkEsiBov = new EsitoZootecniaEsitoAmmBovPk();
//			pkEsiBov.set_campagna(Integer.parseInt(esito.get_campagna(), 10));
//			pkEsiBov.set_cuaa(esito.get_cuaa());
//			pkEsiBov.set_domanda(esito.get_domanda());
//			EsitoZootecniaEsitoAmmBov ctrEsiBovFind = _em.find(EsitoZootecniaEsitoAmmBov.class, pkEsiBov);
			
			// dati dell'esito del controllo di amm. bovini
			Query q = _em.createNamedQuery("EsitoZootecniaEsitoAmmBov.getEsitoBy_CUAA_Campagna_Domanda");
			q.setParameter("cuaa", esito.get_cuaa());
			q.setParameter("campagna", esito.get_campagna());
			q.setParameter("domanda", esito.get_domanda());
			ArrayList<EsitoZootecniaEsitoAmmBov> r = (ArrayList<EsitoZootecniaEsitoAmmBov>) q.getResultList();
			if(r.isEmpty()) r = new ArrayList<EsitoZootecniaEsitoAmmBov>();
			
			// dati dell'esito del controllo di amm. ovicaprini
			EsitoZootecniaEsitoAmmOviPk pkEsiOvi = new EsitoZootecniaEsitoAmmOviPk();
			pkEsiOvi.set_campagna(esito.get_campagna());
			pkEsiOvi.set_cuaa(esito.get_cuaa());
			pkEsiOvi.set_domanda(esito.get_domanda());
			EsitoZootecniaEsitoAmmOvi ctrEsiOviFind = _em.find(EsitoZootecniaEsitoAmmOvi.class, pkEsiOvi);


			this.tx.begin();
			_em.remove(esitoFind);
			if(ctrAmmBovFind != null) _em.remove(ctrAmmBovFind);
			if(ctrAmmOviFind != null) _em.remove(ctrAmmOviFind);
			if(ctrCondFind != null) _em.remove(ctrCondFind);
			for(EsitoZootecniaEsitoAmmBov row: r)_em.remove(row);
//			if(ctrEsiBovFind != null) _em.remove(ctrEsiBovFind);
			if(ctrEsiOviFind != null) _em.remove(ctrEsiOviFind);

			this.tx.commit();
			
			// cancellazione dall'elenco
			elencoEsitoZoot.remove(esito);
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;			
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsito: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsito: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsito: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsito: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	/**
	 * restituisce lo stato dell'esito relativo alla posizione data dall'indice nella lista
	 * @param index
	 * @return EsitoZootecniaStatoBO
	 */
	public EsitoZootecniaStatoBO getStatoFromIndex(int index){
		EsitoZootecniaStatoBO esito = getElencoEsitoZoot().get(index);
		setFlagInterventi(esito);
		return esito;
	}
	
	/**
	 * recupera dal database la riga relativa ai dati registrati per i dati generici dell'esito
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaStatoBO getStato(String cuaa, String domanda, String campagna){
		_em.clear();
		EsitoZootecniaStatoPk pk = new EsitoZootecniaStatoPk();
		pk.set_campagna(campagna);
		pk.set_cuaa(cuaa);
		pk.set_domanda(domanda);
		EsitoZootecniaStato esitoFind = _em.find(EsitoZootecniaStato.class, pk);
		if(esitoFind == null) {
			EsitoZootecniaStatoBO esitoBO = new EsitoZootecniaStatoBO();
			esitoBO.set_campagna(campagna);
			esitoBO.set_cuaa(cuaa);
			esitoBO.set_domanda(domanda);
			setFlagInterventi(esitoBO);
			return esitoBO;
		}
		else {
			EsitoZootecniaStatoBO esito = new EsitoZootecniaStatoBO(esitoFind);
			setFlagInterventi(esito);
			Azienda aziendaFind = _em.find(Azienda.class, esito.get_cuaa());
			// imposto come persona presente al controllo il nome della ragione sociale
			if(aziendaFind != null && esito.get_personaPresente().equals(""))
				esito.set_personaPresente(aziendaFind.get_ragioneSociale());
			return esito;
		}
	}
	
	
	/**
	 * verifica la correttezza dell'inserimento
	 * @param esito
	 * @return OperationResultBO
	 */
	private OperationResultBO checkPreInsertDatiEsito(EsitoZootecniaStatoBO esito){
		OperationResultBO result = new OperationResultBO();
		
		// 1. cuaa non vuoto
		if(esito.get_cuaa().equals("")){
			result.set_result(false);
			result.set_message("Il CUAA deve essere valorizzato");
			return result;
		}
		
		// 2. campagna non vuoto
		if(esito.get_campagna().equals("")){
			result.set_result(false);
			result.set_message("La campagna deve essere valorizzata");
			return result;
		}
		
		// 3. domanda non vuoto
		if(esito.get_domanda().equals("")){
			result.set_result(false);
			result.set_message("La domanda deve essere valorizzata");
			return result;
		}
		
		// 4. dati di cuaa, domanda, campagna presenti nell'anagrafica domanda
		DomandaPk domPk = new DomandaPk();
		domPk.set_idDomanda(esito.get_domanda());
		domPk.set_misura("DU");
		Domanda domanda = _em.find(Domanda.class, domPk);
		if(domanda == null){
			result.set_result(false);
			result.set_message("La domanda non é presente in anagrafica");
			return result;
		}
		else if(!esito.get_cuaa().equals(domanda.get_cuaa()) || !esito.get_campagna().equals(domanda.get_campagna())){
			result.set_result(false);
			result.set_message("CUAA o campagna non coerente con la domanda");
			return result;
		}
		else if(!domanda.get_misura().equals("DU")){
			result.set_result(false);
			result.set_message("Domanda di misura non corretta. Inserire una domanda unica");
			return result;
		}
		
		result.set_result(true);
		return result;
	}
	
	/**
	 * verifica la correttezza dell'inserimento
	 * @param esito
	 * @return OperationResultBO
	 */
	private OperationResultBO checkPreInsertDatiControllo(EsitoZootecniaStatoBO esito){
		OperationResultBO result = new OperationResultBO();

		// 1. nome controllore
		if(esito.get_nomeControllore().equals("")){
			result.set_result(false);
			result.set_message("Il nome del controllore deve essere valorizzato");
			return result;
		}
		
		// 2. data del controllo
		if(esito.get_dataControlloStr().equals("")){
			result.set_result(false);
			result.set_message("La data del controllo deve essere valorizzata");
			return result;
		}
		
		// 3. flag firma del produttore
		if(esito.get_flgFirmaProduttore().equals("")){
			result.set_result(false);
			result.set_message("Il flag di firma del produttore deve essere valorizzato");
			return result;
		}
		
		// 4. flag firma del controllore
		if(esito.get_flgFirmaControllore().equals("")){
			result.set_result(false);
			result.set_message("Il flag di firma del controllore deve essere valorizzato");
			return result;
		}
		
		result.set_result(true);
		return result;
	}
	
	
	/**
	 * imposta nell'esito i flag di presenza degli interventi
	 * @param esito
	 */
	private void setFlagInterventi(EsitoZootecniaStatoBO esito){
		_em.clear();
		
		// 310
		Query query = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
		query.setParameter("domanda", esito.get_domanda());
		query.setParameter("intervento", "310");
		List<Sottointervento> list = query.getResultList();
		if(list.size() == 1) esito.set_flg310("SI");
		else esito.set_flg310("NO");
		
		// 311
		query = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
		query.setParameter("domanda", esito.get_domanda());
		query.setParameter("intervento", "311");
		list = query.getResultList();
		if(list.size() == 1) esito.set_flg311("SI");
		else esito.set_flg311("NO");
		
		// 313
		query = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
		query.setParameter("domanda", esito.get_domanda());
		query.setParameter("intervento", "313");
		list = query.getResultList();
		if(list.size() == 1) esito.set_flg313("SI");
		else  esito.set_flg313("NO");
		
		// 315
		query = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
		query.setParameter("domanda", esito.get_domanda());
		query.setParameter("intervento", "315");
		list = query.getResultList();
		if(list.size() == 1) esito.set_flg315("SI");
		else esito.set_flg315("NO");
		
		// 316
		query = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
		query.setParameter("domanda", esito.get_domanda());
		query.setParameter("intervento", "316");
		list = query.getResultList();
		if(list.size() == 1) esito.set_flg316("SI");
		else esito.set_flg316("NO");
		
		// 318
		query = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
		query.setParameter("domanda", esito.get_domanda());
		query.setParameter("intervento", "318");
		list = query.getResultList();
		if(list.size() == 1) esito.set_flg318("SI");
		else esito.set_flg318("NO");
		
		// 320
		query = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
		query.setParameter("domanda", esito.get_domanda());
		query.setParameter("intervento", "320");
		list = query.getResultList();
		if(list.size() == 1) esito.set_flg320("SI");
		else esito.set_flg320("NO");
		
		// 322
		query = _em.createNamedQuery("Sottointervento.domandaHasIntervento");
		query.setParameter("domanda", esito.get_domanda());
		query.setParameter("intervento", "322");
		list = query.getResultList();
		if(list.size() == 1) esito.set_flag322("SI");
		else esito.set_flag322("NO");
	}
	
	
	/********************************************************
	 * 	METODI PER CONTROLLO AMMISSIBILITA BOVINI
	 ********************************************************/
	
	/**
	 * salva su database i dati relativi ai controlli sui bovini
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaControlloAmmBovini(EsitoZootecniaControlloAmmBovBO esito){
		//controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsertControlloAmmBovini(esito);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			_em.clear();
			EsitoZootecniaControlloAmmBovPk pk = new EsitoZootecniaControlloAmmBovPk();
			pk.set_campagna(esito.get_campagna());
			pk.set_cuaa(esito.get_cuaa());
			pk.set_domanda(esito.get_domanda());

			this.tx.begin();
			EsitoZootecniaControlloAmmBov esitoFind = _em.find(EsitoZootecniaControlloAmmBov.class, pk);

			if(esitoFind == null){
				// popolamento e salvataggio della entity
				esitoFind = esito.getEntity();
				esitoFind.set_user_inserimento(Utils.getCurrentUser());
				esitoFind.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoFind);
				// popolamento dell'oggetto BO
				esito.set_userInserimento(Utils.getCurrentUser());
				esito.set_dataInserimento(Utils.todayDate());
			}
			else {				
				// popolamento e salvataggio della entity
				esito.setEntity(esitoFind);
				esitoFind.set_user_modifica(Utils.getCurrentUser());
				esitoFind.set_data_modifica(Utils.todayDate());
				// popolamento dell'oggetto BO
				esito.set_userModifica(Utils.getCurrentUser());
				esito.set_dataModifica(Utils.todayDate());
			}			

			this.tx.commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloAmmBovini: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloAmmBovini: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloAmmBovini: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloAmmBovini: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	/**
	 * recupera dal database la riga relativa ai dati registrati per il controllo di ammissibilità bovini
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaControlloAmmBovBO getControlloAmmBovini(String cuaa, String domanda, String campagna){
		_em.clear();
		EsitoZootecniaControlloAmmBovPk pk = new EsitoZootecniaControlloAmmBovPk();
		pk.set_campagna(campagna);
		pk.set_cuaa(cuaa);
		pk.set_domanda(domanda);
		EsitoZootecniaControlloAmmBov esitoFind = _em.find(EsitoZootecniaControlloAmmBov.class, pk);
		if(esitoFind == null) {
			EsitoZootecniaControlloAmmBovBO esitoBO = new EsitoZootecniaControlloAmmBovBO();
			esitoBO.set_campagna(campagna);
			esitoBO.set_cuaa(cuaa);
			esitoBO.set_domanda(domanda);
			return esitoBO;
		}
		else return new EsitoZootecniaControlloAmmBovBO(esitoFind);
	}
	
	/**
	 * cancella su database i dati del controllo di ammissibilità bovini
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaControlloAmmBovini(String cuaa, String domanda, String campagna)
	{
		// operazioni da compiere: 
		// 1) cancellazione della riga di dato
		// 2) modifica dello stato da compilato a non compilato
		
		OperationResultBO result = new OperationResultBO();
		try {
			this.tx.begin();
			// 1)cancellazione della riga di dato
			EsitoZootecniaControlloAmmBovPk pk = new EsitoZootecniaControlloAmmBovPk();
			pk.set_campagna(campagna);
			pk.set_cuaa(cuaa);
			pk.set_domanda(domanda);
			_em.clear();
			EsitoZootecniaControlloAmmBov esitoFind = _em.find(EsitoZootecniaControlloAmmBov.class, pk);
			if(esitoFind == null) {
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				this.tx.rollback();
				return result;
			}
			
			// 2) modifica dello stato da compilato a non compilato
			EsitoZootecniaStatoPk pk2 = new EsitoZootecniaStatoPk();
			pk2.set_campagna(campagna);
			pk2.set_cuaa(cuaa);
			pk2.set_domanda(domanda);
			EsitoZootecniaStato esitoStatoFind = _em.find(EsitoZootecniaStato.class, pk2);
			if(esitoStatoFind == null){
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				this.tx.rollback();
				return result;
			}		
			// se entity stato presente la modifico
			esitoStatoFind.set_contrAmmBovini(Costanti.STATO_NON_COMPILATO);
			esitoStatoFind.set_user_modifica(Utils.getCurrentUser());
			esitoStatoFind.set_data_modifica(Utils.todayDate());
			
			// le modifiche alle tabelle le racchiudo in una transazione unica

			_em.remove(esitoFind); // cancellazione della riga 
			_em.persist(esitoStatoFind); // aggiornamento dello stato complessivo

			this.tx.commit();
			
			// se arrivo qui significa che le modifiche su DB sono state fatte
			// devo aggiornare l'oggetto utilizzato dalla UI
			EsitoZootecniaStatoBO esitoStato = elencoEsitoZoot.getEsito(domanda);
			esitoStato.set_contrAmmBovini(Costanti.STATO_NON_COMPILATO);

			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(ControlloBean.class.getSimpleName() + " - cancellaControlloAmmBovini: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaControlloAmmBovini: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaControlloAmmBovini: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaControlloAmmBovini: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	/**
	 * verifica la correttezza dell'inserimento per il controllo di ammissibilità bovini
	 * @param esito
	 * @return OperationResultBO
	 */
	private OperationResultBO checkPreInsertControlloAmmBovini(EsitoZootecniaControlloAmmBovBO esito){
		OperationResultBO result = new OperationResultBO();	
		
		// 1. capi in anagrafe: campo numerico se valorizzato
		if(!esito.get_capiAnagrafe().equals(""))
			if(!CheckData.checkInteger(esito.get_capiAnagrafe()))
			{
				result.set_result(false);
				result.set_message("Il campo -Capi in anagrafe- deve essere un valore numerico");
				return result;
			}
		
		// 2. capi sul registro: campo numerico se valorizzato
		if(!esito.get_capiRegistro().equals(""))
			if(!CheckData.checkInteger(esito.get_capiRegistro()))
			{
				result.set_result(false);
				result.set_message("Il campo -Capi sul registro- deve essere un valore numerico");
				return result;
			}
		
		// 3. capi presenti in azienda: campo numerico se valorizzato
		if(!esito.get_capiPresenti().equals(""))
			if(!CheckData.checkInteger(esito.get_capiPresenti()))
			{
				result.set_result(false);
				result.set_message("Il campo -Capi presenti in azienda- deve essere un valore numerico");
				return result;
			}
		
		// 4. totale capi non conformi... : campo numerico se valorizzato
		if(!esito.get_capiNonConformiTot().equals(""))
			if(!CheckData.checkInteger(esito.get_capiNonConformiTot()))
			{
				result.set_result(false);
				result.set_message("Il campo -Totale Capi non conformi- deve essere un valore numerico");
				return result;
			}
		
		// 5. capi richiesti Int. 310: campo numerico se valorizzato
		if(!esito.get_capi_richiesti_int_310().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_richiesti_int_310()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi richiesti 310 - deve essere un valore numerico");
				return result;
			}
		
		// 5. capi richiesti Int. 310: campo numerico se valorizzato
		if(!esito.get_capi_richiesti_int_311().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_richiesti_int_311()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi richiesti 311 - deve essere un valore numerico");
				return result;
			}
		
		// 6. capi richiesti Int. 313 : campo numerico se valorizzato
		if(!esito.get_capi_richiesti_int_313().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_richiesti_int_313()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi richiesti 313 - deve essere un valore numerico");
				return result;
			}
		
		// 7. capi richiest Int. 315 : campo numerico se valorizzato	
		if(!esito.get_capi_richiesti_int_315().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_richiesti_int_315()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi richiesti 315 - deve essere un valore numerico");
				return result;
			}
		
		// 7. capi richiest Int. 316 : campo numerico se valorizzato	
		if(!esito.get_capi_richiesti_int_316().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_richiesti_int_316()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi richiesti 316 - deve essere un valore numerico");
				return result;
			}
		
		// 7. capi richiest Int. 318 : campo numerico se valorizzato	
		if(!esito.get_capi_richiesti_int_318().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_richiesti_int_318()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi richiesti 318 - deve essere un valore numerico");
				return result;
			}
		
		// 5. capi irregolari Int. 310 : campo numerico se valorizzato
		if(!esito.get_capi_irreg_int_310().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_irreg_int_310()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi irregolari 310 - deve essere un valore numerico");
				return result;
			}

		// 5. capi irregolari Int. 311: campo numerico se valorizzato
		if(!esito.get_capi_irreg_int_311().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_irreg_int_311()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi irregolari 311 - deve essere un valore numerico");
				return result;
			}
		
		// 6. capi irregolari Int. 313 : campo numerico se valorizzato
		if(!esito.get_capi_irreg_int_313().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_irreg_int_313()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi irregolari 313 - deve essere un valore numerico");
				return result;
			}
		
		// 7. capi irregolari Int. 315 : campo numerico se valorizzato	
		if(!esito.get_capi_irreg_int_315().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_irreg_int_315()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi irregolari 315 - deve essere un valore numerico");
				return result;
			}

		// 7. capi irregolari Int. 316 : campo numerico se valorizzato	
		if(!esito.get_capi_irreg_int_316().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_irreg_int_316()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi irregolari 316 - deve essere un valore numerico");
				return result;
			}
		// 7. capi irregolari Int. 318 : campo numerico se valorizzato	
		if(!esito.get_capi_irreg_int_318().equals(""))
			if(!CheckData.checkInteger(esito.get_capi_irreg_int_318()))
			{
				result.set_result(false);
				result.set_message("Il campo - Totale Capi irregolari 318 - deve essere un valore numerico");
				return result;
			}
		result.set_result(true);
		return result;
	}
	
	/********************************************************
	 * 	METODI PER ESITO/SANZIONE AMMISSIBILITA BOVINI
	 ********************************************************/
	
	/**
	 * salva su database l'esito e la sanzione dei controlli sui bovini
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaEsitoAmmBovini(EsitoZootecniaEsitoAmmBovBO esito){
		//controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsertEsitoAmmBovini(esito);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			_em.clear();

			this.tx.begin();
			Query q = _em.createNamedQuery("EsitoZootecniaEsitoAmmBov.getEsitoIntBy_CUAA_Campagna_Domanda_Int");
			q.setParameter("cuaa", esito.get_cuaa());
			q.setParameter("campagna", esito.get_campagna());
			q.setParameter("domanda", esito.get_domanda());
			q.setParameter("intervento", esito.get_intervento());
			ArrayList<EsitoZootecniaEsitoAmmBov> esitoFind = (ArrayList<EsitoZootecniaEsitoAmmBov>) q.getResultList();
			
			if(esitoFind.isEmpty()) {
				for(EsitoZootecniaEsitoAmmBov r: esito.getEntities()) {
					if(r.get_campo().equals("USER_CREAZIONE")) r.set_valore(Utils.getCurrentUser());
					if(r.get_campo().equals("DATA_CREAZIONE")) r.set_valore(Utils.getDateString(Utils.todayDate()));
					_em.persist(r);
				}
				esito.set_userInserimento(Utils.getCurrentUser());
				esito.set_dataInserimento(Utils.todayDate());
			}
			else {
				// popolamento e salvataggio della entity
				esito.setEntities(esitoFind);
				for(EsitoZootecniaEsitoAmmBov r: esitoFind) {
					if(r.get_campo().equals("USER_MODIFICA")) r.set_valore(Utils.getCurrentUser());
					if(r.get_campo().equals("DATA_MODIFICA")) r.set_valore(Utils.getDateString(Utils.todayDate()));
				}
				// popolamento dell'oggetto BO
				esito.set_userModifica(Utils.getCurrentUser());
				esito.set_dataModifica(Utils.todayDate());
			}		

			this.tx.commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsitoAmmBovini: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsitoAmmBovini: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsitoAmmBovini: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsitoAmmBovini: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	/**
	 * recupera dal database la riga relativa ai dati registrati per gli esiti del controllo di ammissibilità bovini
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaEsitoAmmBovBO getEsitoAmmBovini(String cuaa, String domanda, String campagna, String intervento){
		_em.clear();
		
		Query q = _em.createNamedQuery("EsitoZootecniaEsitoAmmBov.getEsitoIntBy_CUAA_Campagna_Domanda_Int");
		q.setParameter("cuaa",cuaa);
		q.setParameter("campagna", Integer.parseInt(campagna, 10));
		q.setParameter("domanda", domanda);
		q.setParameter("intervento", intervento);
		ArrayList<EsitoZootecniaEsitoAmmBov> esitoFind = (ArrayList<EsitoZootecniaEsitoAmmBov>) q.getResultList();
		EsitoZootecniaEsitoAmmBovBO esitoBO;
		
		if(esitoFind.isEmpty()){
			esitoBO = new EsitoZootecniaEsitoAmmBovBO();
			esitoBO.set_campagna(Integer.parseInt(campagna, 10));
			esitoBO.set_cuaa(cuaa);
			esitoBO.set_domanda(domanda);
			esitoBO.set_intervento(intervento);
		} else {
			esitoBO = new EsitoZootecniaEsitoAmmBovBO(esitoFind);
		}
		return esitoBO;
	}
	
	/**
	 * cancella su database i dati dell'esito del controllo di ammissibilità bovini
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsitoAmmBovini(String cuaa, String domanda, String campagna)
	{
		// operazioni da compiere: 
		// 1) cancellazione della riga di dato
		// 2) modifica dello stato da compilato a non compilato
		
		OperationResultBO result = new OperationResultBO();
		try {
			this.tx.begin();
			// 1)cancellazione della riga di dato
			_em.clear();
			Query q = _em.createNamedQuery("EsitoZootecniaEsitoAmmBov.getEsitoBy_CUAA_Campagna_Domanda");
			q.setParameter("cuaa", cuaa);
			q.setParameter("campagna", Integer.parseInt(campagna,10));
			q.setParameter("domanda", domanda);
			ArrayList<EsitoZootecniaEsitoAmmBov> esitoFind = (ArrayList<EsitoZootecniaEsitoAmmBov>) q.getResultList();
			if(esitoFind.isEmpty()) {
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				this.tx.rollback();
				return result;
			}
			
			// 2) modifica dello stato da compilato a non compilato
			EsitoZootecniaStatoPk pk2 = new EsitoZootecniaStatoPk();
			pk2.set_campagna(campagna);
			pk2.set_cuaa(cuaa);
			pk2.set_domanda(domanda);
			EsitoZootecniaStato esitoStatoFind = _em.find(EsitoZootecniaStato.class, pk2);
			if(esitoStatoFind == null){
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				this.tx.rollback();
				return result;
			}		
			// se entity stato presente la modifico
			esitoStatoFind.set_esitoAmmBovini(Costanti.STATO_NON_COMPILATO);
			esitoStatoFind.set_user_modifica(Utils.getCurrentUser());
			esitoStatoFind.set_data_modifica(Utils.todayDate());
			
			// le modifiche alle tabelle le racchiudo in una transazione unica
			for(EsitoZootecniaEsitoAmmBov row: esitoFind) _em.remove(row);
			_em.persist(esitoStatoFind); // aggiornamento dello stato complessivo

			this.tx.commit();
			
			// se arrivo qui significa che le modifiche su DB sono state fatte
			// devo aggiornare l'oggetto utilizzato dalla UI
			EsitoZootecniaStatoBO esitoStato = elencoEsitoZoot.getEsito(domanda);
			esitoStato.set_esitoAmmBovini(Costanti.STATO_NON_COMPILATO);

			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(ControlloBean.class.getSimpleName() + " - cancellaEsitoAmmBovini: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsitoAmmBovini: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsitoAmmBovini: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsitoAmmBovini: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	/**
	 * cancella su database i dati dell'esito dell'intervento per ammissibilità bovini
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @param intervento
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsitoIntAmmBovini(String cuaa, String domanda, int campagna, String intervento)
	{
		// operazioni da compiere: 
		// 1) cancellazione della riga di dato
		// 2) modifica dello stato da compilato a non compilato
		
		OperationResultBO result = new OperationResultBO();
		try {
			this.tx.begin();
			// 1)cancellazione della riga di dato
			_em.clear();
			Query q = _em.createNamedQuery("EsitoZootecniaEsitoAmmBov.getEsitoIntBy_CUAA_Campagna_Domanda_Int");
			q.setParameter("cuaa", cuaa);
			q.setParameter("campagna", campagna);
			q.setParameter("domanda", domanda);
			q.setParameter("intervento", intervento);
			ArrayList<EsitoZootecniaEsitoAmmBov> esitoFind = (ArrayList<EsitoZootecniaEsitoAmmBov>) q.getResultList();
			if(esitoFind.isEmpty()) {
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				this.tx.rollback();
				return result;
			}

			// le modifiche alle tabelle le racchiudo in una transazione unica
			for(EsitoZootecniaEsitoAmmBov row: esitoFind) _em.remove(row);
			// Cambio lo stato della tabella riepilogativa nel caso non ci siano più esiti per intervento
			q = _em.createNamedQuery("EsitoZootecniaEsitoAmmBov.getEsitoBy_CUAA_Campagna_Domanda");
			q.setParameter("cuaa", cuaa);
			q.setParameter("campagna", campagna);
			q.setParameter("domanda", domanda);
			ArrayList<EsitoZootecniaEsitoAmmBov> r = (ArrayList<EsitoZootecniaEsitoAmmBov>) q.getResultList();
			boolean flag = false;
			if(r.isEmpty()) flag = true;
			if(flag) {
				// 2) modifica dello stato da compilato a non compilato
				EsitoZootecniaStatoPk pk2 = new EsitoZootecniaStatoPk();
				pk2.set_campagna(String.valueOf(campagna));
				pk2.set_cuaa(cuaa);
				pk2.set_domanda(domanda);
				EsitoZootecniaStato esitoStatoFind = _em.find(EsitoZootecniaStato.class, pk2);
				if(esitoStatoFind != null){
					esitoStatoFind.set_esitoAmmBovini(Costanti.STATO_NON_COMPILATO);
					esitoStatoFind.set_user_modifica(Utils.getCurrentUser());
					esitoStatoFind.set_data_modifica(Utils.todayDate());
					EsitoZootecniaStatoBO esitoStato = elencoEsitoZoot.getEsito(domanda);
					esitoStato.set_esitoAmmBovini(Costanti.STATO_NON_COMPILATO);
				}
			}
			
			this.tx.commit();
			
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(ControlloBean.class.getSimpleName() + " - cancellaEsitoIntAmmBovini: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsitoIntAmmBovini: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsitoIntAmmBovini: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsitoIntAmmBovini: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	/**
	 * verifica la correttezza dell'inserimento per il controllo di ammissibilità bovini
	 * @param esito
	 * @return OperationResultBO
	 */
	private OperationResultBO checkPreInsertEsitoAmmBovini(EsitoZootecniaEsitoAmmBovBO esito){
		OperationResultBO result = new OperationResultBO();

		// 4. se l'esito vale NEGATIVO la percentuale deve essere valorizzata, e in questo caso dve avere al massimo 2 decimali
		// 4.a Controllo Int. 310 e 311 
		if(esito.get_esito_controllo().toUpperCase().equals("NEGATIVO")){
			if(esito.get_perc_rid_esito_controllo().equals("")){
				result.set_result(false);
				result.set_message("Il campo - Percentuale di riduzione - deve essere un valore numerico");
				return result;
			}
			else if(!CheckData.checkFloat(esito.get_perc_rid_esito_controllo())){
				result.set_result(false);
				result.set_message("Il campo - Percentuale di riduzione - deve essere un valore numerico");
				return result;
			}
		}
		
		// 1. per l'applicazione della sanzione int 310, 311, se uno dei campi é flaggato a SI la percentuale deve essere valorizzata e numerica
		// 1.a applicazione della percentuale di riduzione
		if(esito.get_appl_perc_rid_determinata().toUpperCase().equals("SI")){
			if(esito.get_perc_rid_determinata().equals("")){
				result.set_result(false);
				result.set_message("Il campo -Percentuale di riduzione determinata - deve essere valorizzato");
				return result;
			}
			else if(!CheckData.checkFloat(esito.get_perc_rid_determinata())){
				result.set_result(false);
				result.set_message("Il campo -Percentuale di riduzione determinata - deve essere un valore numerico");
				return result;
			}
		}
		
		// 1.b applicazione del doppio della percentuale di riduzione
		if(esito.get_appl_perc_rid_determinata_2x().toUpperCase().equals("SI")){
			if(esito.get_perc_rid_determinata_2x().equals("")){
				result.set_result(false);
				result.set_message("Il campo - Percentuale di riduzione doppia determinata - deve essere valorizzato");
				return result;
			}
			else if(!CheckData.checkFloat(esito.get_perc_rid_determinata_2x())){
				result.set_result(false);
				result.set_message("Il campo -Percentuale di riduzione doppia determinata - deve essere un valore numerico");
				return result;
			}
		}
		
		// 1.c esclusione del pagamento
		if(esito.get_escl_pagamento().toUpperCase().equals("SI")){
			if(esito.get_perc_rid_escl_pagamento().equals("")){
				result.set_result(false);
				result.set_message("Il campo - Percentuale di riduzione esclusione determinata - deve essere valorizzato");
				return result;
			}
			else if(!CheckData.checkFloat(esito.get_perc_rid_escl_pagamento())){
				result.set_result(false);
				result.set_message("Il campo -Percentuale di riduzione esclusione determinata - deve essere un valore numerico");
				return result;
			}
		}
		

		result.set_result(true);
		return result;
	}
	
	/********************************************************
	 * 	METODI PER CONTROLLO AMMISSIBILITA OVICAPRINI
	 ********************************************************/
	
	/**
	 * salva su database i dati relativi al controllo sugli ovicaprini
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaControlloAmmOvicaprini(EsitoZootecniaControlloAmmOviBO esito){
		//controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsertControlloAmmOvicaprini(esito);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			_em.clear();
			EsitoZootecniaControlloAmmOviPk pk = new EsitoZootecniaControlloAmmOviPk();
			pk.set_campagna(esito.get_campagna());
			pk.set_cuaa(esito.get_cuaa());
			pk.set_domanda(esito.get_domanda());

			this.tx.begin();
			EsitoZootecniaControlloAmmOvi esitoFind = _em.find(EsitoZootecniaControlloAmmOvi.class, pk);
			if(esitoFind == null){
				// popolamento e salvataggio della entity
				esitoFind = esito.getEntity();
				esitoFind.set_user_inserimento(Utils.getCurrentUser());
				esitoFind.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoFind);
				// popolamento dell'oggetto BO
				esito.set_userInserimento(Utils.getCurrentUser());
				esito.set_dataInserimento(Utils.todayDate());
			}
			else {
				// popolamento e salvataggio della entity
				esito.setEntity(esitoFind);
				esitoFind.set_user_modifica(Utils.getCurrentUser());
				esitoFind.set_data_modifica(Utils.todayDate());
				// popolamento dell'oggetto BO
				esito.set_userModifica(Utils.getCurrentUser());
				esito.set_dataModifica(Utils.todayDate());
			}			

			this.tx.commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloAmmOvicaprini: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloAmmOvicaprini: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloAmmOvicaprini: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloAmmOvicaprini: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	
	/**
	 * recupera dal database la riga relativa ai dati registrati per il controllo di ammissibilità ovicaprini
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaControlloAmmOviBO getControlloAmmOvi(String cuaa, String domanda, String campagna){
		_em.clear();
		EsitoZootecniaControlloAmmOviPk pk = new EsitoZootecniaControlloAmmOviPk();
		pk.set_campagna(campagna);
		pk.set_cuaa(cuaa);
		pk.set_domanda(domanda);
		EsitoZootecniaControlloAmmOvi esitoFind = _em.find(EsitoZootecniaControlloAmmOvi.class, pk);
		if(esitoFind == null) {
			EsitoZootecniaControlloAmmOviBO esitoBO = new EsitoZootecniaControlloAmmOviBO();
			esitoBO.set_campagna(campagna);
			esitoBO.set_cuaa(cuaa);
			esitoBO.set_domanda(domanda);
			return esitoBO;
		}
		else return new EsitoZootecniaControlloAmmOviBO(esitoFind);
	}
	
	/**
	 * cancella su database i dati del controllo di ammissibilità ovicaprini
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaControlloAmmOvicaprini(String cuaa, String domanda, String campagna)
	{
		// operazioni da compiere: 
		// 1) cancellazione della riga di dato
		// 2) modifica dello stato da compilato a non compilato
		
		OperationResultBO result = new OperationResultBO();
		try {
			this.tx.begin();
			// 1)cancellazione della riga di dato
			EsitoZootecniaControlloAmmOviPk pk = new EsitoZootecniaControlloAmmOviPk();
			pk.set_campagna(campagna);
			pk.set_cuaa(cuaa);
			pk.set_domanda(domanda);
			_em.clear();
			EsitoZootecniaControlloAmmOvi esitoFind = _em.find(EsitoZootecniaControlloAmmOvi.class, pk);
			if(esitoFind == null) {
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				this.tx.rollback();
				return result;
			}
			
			// 2) modifica dello stato da compilato a non compilato
			EsitoZootecniaStatoPk pk2 = new EsitoZootecniaStatoPk();
			pk2.set_campagna(campagna);
			pk2.set_cuaa(cuaa);
			pk2.set_domanda(domanda);
			EsitoZootecniaStato esitoStatoFind = _em.find(EsitoZootecniaStato.class, pk2);
			if(esitoStatoFind == null){
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				this.tx.rollback();
				return result;
			}		
			// se entity stato presente la modifico
			esitoStatoFind.set_contrAmmOvicaprini(Costanti.STATO_NON_COMPILATO);
			esitoStatoFind.set_user_modifica(Utils.getCurrentUser());
			esitoStatoFind.set_data_modifica(Utils.todayDate());
			
			// le modifiche alle tabelle le racchiudo in una transazione unica

			_em.remove(esitoFind); // cancellazione della riga 
			_em.persist(esitoStatoFind); // aggiornamento dello stato complessivo

			this.tx.commit();
			
			// se arrivo qui significa che le modifiche su DB sono state fatte
			// devo aggiornare l'oggetto utilizzato dalla UI
			EsitoZootecniaStatoBO esitoStato = elencoEsitoZoot.getEsito(domanda);
			esitoStato.set_contrAmmOvicaprini(Costanti.STATO_NON_COMPILATO);

			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(ControlloBean.class.getSimpleName() + " - cancellaControlloAmmOvicaprini: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaControlloAmmOvicaprini: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaControlloAmmOvicaprini: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaControlloAmmOvicaprini: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	
	
	/**
	 * verifica la correttezza dell'inserimento per il controllo di ammissibilità ovicaprini
	 * @param esito
	 * @return OperationResultBO
	 */
	private OperationResultBO checkPreInsertControlloAmmOvicaprini(EsitoZootecniaControlloAmmOviBO esito){
		OperationResultBO result = new OperationResultBO();	

		// 1. capi in anagrafe tot se valorizzato deve essere numerico
		if(!esito.get_capiAnagrafeTot().equals(""))
			if(!CheckData.checkInteger(esito.get_capiAnagrafeTot()))
			{
				result.set_result(false);
				result.set_message("Il campo -Capi in anagrafe di cui:- deve essere un valore numerico");
				return result;
			}
		
		// 4. capi sul registro tot se valorizzato deve essere numerico
		if(!esito.get_capiRegistroTot().equals(""))
			if(!CheckData.checkInteger(esito.get_capiRegistroTot()))
			{
				result.set_result(false);
				result.set_message("Il campo -Capi sul registro di cui:- deve essere un valore numerico");
				return result;
			}
		
		// 7. capi in azienda tot se valorizzato deve essere numerico
		if(!esito.get_capiAziendaTot().equals(""))
			if(!CheckData.checkInteger(esito.get_capiAziendaTot()))
			{
				result.set_result(false);
				result.set_message("Il campo -Capi tot contati in azienda- deve essere un valore numerico");
				return result;
			}
		
		// 10. dichiarazione produttore deve essere al massimo di 200 caratteri
		if(esito.get_dichiarazioniProduttore().length()>=200)
		{
			result.set_result(false);
			result.set_message("Dichiarazioni produttore - testo troppo lungo. Dimensione massima 200 caratteri");
			return result;
		}
		
		// 11. dichiarazione controllore deve essere al massimo di 200 caratteri
		if(esito.get_dichiarazioniControllore().length()>=200)
		{
			result.set_result(false);
			result.set_message("Dichiarazioni controllore - testo troppo lungo. Dimensione massima 200 caratteri");
			return result;
		}
		
		result.set_result(true);
		return result;
	}
	
	/********************************************************
	 * 	METODI PER ESITO/SANZIONE AMMISSIBILITA OVICAPRINI
	 ********************************************************/
	
	/**
	 * salva su database i dati di esito e sanzioni relativi al controllo sugli ovicaprini
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaEsitoAmmOvicaprini(EsitoZootecniaEsitoAmmOviBO esito){
		//controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsertEsitoAmmOvicaprini(esito);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		try {
			_em.clear();
			EsitoZootecniaEsitoAmmOviPk pk = new EsitoZootecniaEsitoAmmOviPk();
			pk.set_campagna(esito.get_campagna());
			pk.set_cuaa(esito.get_cuaa());
			pk.set_domanda(esito.get_domanda());

			this.tx.begin();
			EsitoZootecniaEsitoAmmOvi esitoFind = _em.find(EsitoZootecniaEsitoAmmOvi.class, pk);
			if(esitoFind == null){
				// popolamento e salvataggio della entity
				esitoFind = esito.getEntity();
				esitoFind.set_user_inserimento(Utils.getCurrentUser());
				esitoFind.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoFind);
				// popolamento dell'oggetto BO
				esito.set_userInserimento(Utils.getCurrentUser());
				esito.set_dataInserimento(Utils.todayDate());
			}
			else {
				// popolamento e salvataggio della entity
				esito.setEntity(esitoFind);
				esitoFind.set_user_modifica(Utils.getCurrentUser());
				esitoFind.set_data_modifica(Utils.todayDate());
				// popolamento dell'oggetto BO
				esito.set_userModifica(Utils.getCurrentUser());
				esito.set_dataModifica(Utils.todayDate());
			}			

			this.tx.commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsitoAmmOvicaprini: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsitoAmmOvicaprini: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsitoAmmOvicaprini: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaEsitoAmmOvicaprini: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	
	/**
	 * recupera dal database la riga relativa ai dati registrati per gli esiti del controllo di ammissibilità ovicaprini
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaEsitoAmmOviBO getEsitoAmmOvi(String cuaa, String domanda, String campagna){
		_em.clear();
		EsitoZootecniaEsitoAmmOviPk pk = new EsitoZootecniaEsitoAmmOviPk();
		pk.set_campagna(campagna);
		pk.set_cuaa(cuaa);
		pk.set_domanda(domanda);
		EsitoZootecniaEsitoAmmOvi esitoFind = _em.find(EsitoZootecniaEsitoAmmOvi.class, pk);
		if(esitoFind == null) {
			EsitoZootecniaEsitoAmmOviBO esitoBO = new EsitoZootecniaEsitoAmmOviBO();
			esitoBO.set_campagna(campagna);
			esitoBO.set_cuaa(cuaa);
			esitoBO.set_domanda(domanda);
			return esitoBO;
		}
		else return new EsitoZootecniaEsitoAmmOviBO(esitoFind);
	}
	
	/**
	 * cancella su database i dati dell'esito del controllo di ammissibilità ovicaprini
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaEsitoAmmOvicaprini(String cuaa, String domanda, String campagna)
	{
		// operazioni da compiere: 
		// 1) cancellazione della riga di dato
		// 2) modifica dello stato da compilato a non compilato
		
		OperationResultBO result = new OperationResultBO();
		try {
			this.tx.begin();
			// 1)cancellazione della riga di dato
			EsitoZootecniaEsitoAmmOviPk pk = new EsitoZootecniaEsitoAmmOviPk();
			pk.set_campagna(campagna);
			pk.set_cuaa(cuaa);
			pk.set_domanda(domanda);
			_em.clear();
			EsitoZootecniaEsitoAmmOvi esitoFind = _em.find(EsitoZootecniaEsitoAmmOvi.class, pk);
			if(esitoFind == null) {
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				this.tx.rollback();
				return result;
			}
			
			// 2) modifica dello stato da compilato a non compilato
			EsitoZootecniaStatoPk pk2 = new EsitoZootecniaStatoPk();
			pk2.set_campagna(campagna);
			pk2.set_cuaa(cuaa);
			pk2.set_domanda(domanda);
			EsitoZootecniaStato esitoStatoFind = _em.find(EsitoZootecniaStato.class, pk2);
			if(esitoStatoFind == null){
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				return result;
			}		
			// se entity stato presente la modifico
			esitoStatoFind.set_esitoAmmOvicaprini(Costanti.STATO_NON_COMPILATO);
			esitoStatoFind.set_user_modifica(Utils.getCurrentUser());
			esitoStatoFind.set_data_modifica(Utils.todayDate());
			
			// le modifiche alle tabelle le racchiudo in una transazione unica

			_em.remove(esitoFind); // cancellazione della riga 
			_em.persist(esitoStatoFind); // aggiornamento dello stato complessivo

			this.tx.commit();
			
			// se arrivo qui significa che le modifiche su DB sono state fatte
			// devo aggiornare l'oggetto utilizzato dalla UI
			EsitoZootecniaStatoBO esitoStato = elencoEsitoZoot.getEsito(domanda);
			esitoStato.set_esitoAmmOvicaprini(Costanti.STATO_NON_COMPILATO);

			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(ControlloBean.class.getSimpleName() + " - cancellaEsitoAmmOvicaprini: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsitoAmmOvicaprini: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsitoAmmOvicaprini: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaEsitoAmmOvicaprini: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	

	/**
	 * verifica la correttezza dell'inserimento per il controllo di ammissibilità ovicaprini
	 * @param esito
	 * @return OperationResultBO
	 */
	private OperationResultBO checkPreInsertEsitoAmmOvicaprini(EsitoZootecniaEsitoAmmOviBO esito){
		OperationResultBO result = new OperationResultBO();

		// 3. se non é settato nessun esito non si deve salvare (compare compilato anche se non é stato compilato nulla)
		if(esito.get_esi_contr_320().equals("")){
			result.set_result(false);
			result.set_message("Nessun esito compilato");
			return result;
		}
		
		// 5.a Controllo Int. 320
		if(esito.get_esi_contr_320().toUpperCase().equals("NEGATIVO")){
			if(esito.get_perc_rid_320().equals("")){
				result.set_result(false);
				result.set_message("Il campo - Percentuale di riduzione Int.320 - deve essere un valore numerico");
				return result;
			}
			else if(!CheckData.checkFloat(esito.get_perc_rid_320())){
				result.set_result(false);
				result.set_message("Il campo - Percentuale di riduzione Int.320 - deve essere un valore numerico");
				return result;
			}
		}
		
		// 2. per l'applicazione della sanzione int 320, se uno dei campi é flaggato a SI la percentuale deve essere valorizzata e numerica
		// 2.a applicazione della percentuale di riduzione
		if(esito.get_flg_appl_perc_rid_320().toUpperCase().equals("SI")){
			if(esito.get_perc_rid_esi_320().equals("")){
				result.set_result(false);
				result.set_message("Il campo -Percentuale di riduzione determinata (Int 320)- deve essere valorizzato");
				return result;
			}
			else if(!CheckData.checkFloat(esito.get_perc_rid_esi_320())){
				result.set_result(false);
				result.set_message("Il campo -Percentuale di riduzione determinata (Int 320)- deve essere un valore numerico");
				return result;
			}
		}
		
		// 2.b applicazione del doppio della percentuale di riduzione
		if(esito.get_flg_2x_perc_rid_320().toUpperCase().equals("SI")){
			if(esito.get_perc_rid_2x_320().equals("")){
				result.set_result(false);
				result.set_message("Il campo -Percentuale di riduzione determinata (Int 320)- deve essere valorizzato");
				return result;
			}
			else if(!CheckData.checkFloat(esito.get_perc_rid_2x_320())){
				result.set_result(false);
				result.set_message("Il campo -Percentuale di riduzione determinata (Int 320)- deve essere un valore numerico");
				return result;
			}
		}
		
		// 2.c esclusione del pagamento
		if(esito.get_flg_esc_pag_320().toUpperCase().equals("SI")){
			if(esito.get_perc_rid_esc_pag_320().equals("")){
				result.set_result(false);
				result.set_message("Il campo -Percentuale di riduzione determinata (Int 320)- deve essere valorizzato");
				return result;
			}
			else if(!CheckData.checkFloat(esito.get_perc_rid_esc_pag_320())){
				result.set_result(false);
				result.set_message("Il campo -Percentuale di riduzione determinata (Int 320)- deve essere un valore numerico");
				return result;
			}
		}
		result.set_result(true);
		return result;
	}
	
	/********************************************************
	 * 	METODI PER CONTROLLO CONDIZIONALITA
	 ********************************************************/

	/**
	 * salva su database i dati del controllo di condizionalità zootecnia
	 * @param esito
	 * @return OperationResultBO
	 */
	public OperationResultBO salvaControlloCond(EsitoZootecniaControlloCondBO esito){
		//controlli lato server pre-inserimento
		OperationResultBO result = checkPreInsertControlloCond(esito);
		if(!result.get_result()) return result;
		
		// se i controlli sono andati bene procedo con l'inserimento
		result = new OperationResultBO();
		
		try {
			_em.clear();
			EsitoZootecniaControlloCondPk pk = new EsitoZootecniaControlloCondPk();
			pk.set_campagna(esito.get_campagna());
			pk.set_cuaa(esito.get_cuaa());
			pk.set_domanda(esito.get_domanda());

			this.tx.begin();
			EsitoZootecniaControlloCond esitoFind = _em.find(EsitoZootecniaControlloCond.class, pk);
			if(esitoFind == null){
				// popolamento e salvataggio della entity
				esitoFind = esito.getEntity();
				esitoFind.set_user_inserimento(Utils.getCurrentUser());
				esitoFind.set_data_inserimento(Utils.todayDate());
				_em.persist(esitoFind);
				// popolamento dell'oggetto BO
				esito.set_userInserimento(Utils.getCurrentUser());
				esito.set_dataInserimento(Utils.todayDate());
			}
			else {
				// popolamento e salvataggio della entity
				esito.setEntity(esitoFind);
				esitoFind.set_user_modifica(Utils.getCurrentUser());
				esitoFind.set_data_modifica(Utils.todayDate());
				// popolamento dell'oggetto BO
				esito.set_userModifica(Utils.getCurrentUser());
				esito.set_dataModifica(Utils.todayDate());
			}			

			this.tx.commit();
			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_INSERT_OK);
			return result;
		} catch (Exception e) {
			// preparazione risultato
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloCond: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloCond: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloCond: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - salvaControlloCond: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	/**
	 * recupera dal database la riga relativa ai dati registrati per il controllo di condizionalità
	 * per ogni [cuaa, domanda, campagna] ci deve essere una sola riga
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return
	 */
	public EsitoZootecniaControlloCondBO getControlloCond(String cuaa, String domanda, String campagna){
		_em.clear();
		EsitoZootecniaControlloCondPk pk = new EsitoZootecniaControlloCondPk();
		pk.set_campagna(campagna);
		pk.set_cuaa(cuaa);
		pk.set_domanda(domanda);
		EsitoZootecniaControlloCond esitoFind = _em.find(EsitoZootecniaControlloCond.class, pk);
		if(esitoFind == null) {
			EsitoZootecniaControlloCondBO esitoBO = new EsitoZootecniaControlloCondBO();
			esitoBO.set_campagna(campagna);
			esitoBO.set_cuaa(cuaa);
			esitoBO.set_domanda(domanda);
			return esitoBO;
		}
		else return new EsitoZootecniaControlloCondBO(esitoFind);
	}
	
	/**
	 * cancella su database i dati del controllo di condizionalità zootecnia
	 * @param cuaa
	 * @param domanda
	 * @param campagna
	 * @return OperationResultBO
	 */
	public OperationResultBO cancellaControlloCond(String cuaa, String domanda, String campagna)
	{
		// operazioni da compiere: 
		// 1) cancellazione della riga di dato
		// 2) modifica dello stato da compilato a non compilato
		
		OperationResultBO result = new OperationResultBO();
		try {
			this.tx.begin();
			// 1)cancellazione della riga di dato
			EsitoZootecniaControlloCondPk pk = new EsitoZootecniaControlloCondPk();
			pk.set_campagna(campagna);
			pk.set_cuaa(cuaa);
			pk.set_domanda(domanda);
			_em.clear();
			EsitoZootecniaControlloCond esitoFind = _em.find(EsitoZootecniaControlloCond.class, pk);
			if(esitoFind == null) {
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				this.tx.rollback();
				return result;
			}
			
			// 2) modifica dello stato da compilato a non compilato
			EsitoZootecniaStatoPk pk2 = new EsitoZootecniaStatoPk();
			pk2.set_campagna(campagna);
			pk2.set_cuaa(cuaa);
			pk2.set_domanda(domanda);
			EsitoZootecniaStato esitoStatoFind = _em.find(EsitoZootecniaStato.class, pk2);
			if(esitoStatoFind == null){
				result.set_result(false);
				result.set_message(Costanti.MSG_RIGA_NON_TROVATA);
				this.tx.rollback();
				return result;
			}		
			// se entity stato presente la modifico
			esitoStatoFind.set_contrCond(Costanti.STATO_NON_COMPILATO);
			esitoStatoFind.set_user_modifica(Utils.getCurrentUser());
			esitoStatoFind.set_data_modifica(Utils.todayDate());
			
			// le modifiche alle tabelle le racchiudo in una transazione unica

			_em.remove(esitoFind); // cancellazione della riga 
			_em.persist(esitoStatoFind); // aggiornamento dello stato complessivo

			this.tx.commit();
			
			// se arrivo qui significa che le modifiche su DB sono state fatte
			// devo aggiornare l'oggetto utilizzato dalla UI
			EsitoZootecniaStatoBO esitoStato = elencoEsitoZoot.getEsito(domanda);
			esitoStato.set_contrCond(Costanti.STATO_NON_COMPILATO);

			// preparazione risultato
			result.set_result(true);
			result.set_message(Costanti.MSG_DELETE_OK);
			return result;
		} catch (Exception e) {
			result.set_result(false);
			result.set_message(Costanti.MSG_OPERAZIONE_NON_ESEGUITA);
			Utils.getLog().error(ControlloBean.class.getSimpleName() + " - cancellaControlloCond: " + e.getMessage());
			try {
				this.tx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaControlloCond: " + e1.getMessage());
				return result;
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaControlloCond: " + e1.getMessage());
				return result;
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				Utils.getLog().error(EsitoZootecniaBean.class.getSimpleName() + " - cancellaControlloCond: " + e1.getMessage());
				return result;
			}
			return result;
		}
	}
	
	/**
	 * verifica la correttezza dell'inserimento per il controllo di condizionalità
	 * @param esito
	 * @return OperationResultBO
	 */
	private OperationResultBO checkPreInsertControlloCond(EsitoZootecniaControlloCondBO esito){
		OperationResultBO result = new OperationResultBO();
		
		// 1. capi anomali
		// 1.a capi anomali bovini
		if(!esito.get_capiAnomaliBovini().equals("") && !CheckData.checkInteger(esito.get_capiAnomaliBovini())){
			result.set_result(false);
			result.set_message("Il campo -Capi anomali bovini- deve essere un valore numerico");
			return result;
		}
		// 1.b capi anomali ovini
		if(!esito.get_capiAnomaliOvini().equals("") && !CheckData.checkInteger(esito.get_capiAnomaliOvini())){
			result.set_result(false);
			result.set_message("Il campo -Capi anomali ovini- deve essere un valore numerico");
			return result;
		}
		// 1.c capi anomali suini
		if(!esito.get_capiAnomaliSuini().equals("") && !CheckData.checkInteger(esito.get_capiAnomaliSuini())){
			result.set_result(false);
			result.set_message("Il campo -Capi anomali suini- deve essere un valore numerico");
			return result;
		}
		
		// 2. consistenza capi 
		// 2.a consistenza capi bovini
		if(!esito.get_capiConsistenzaBovini().equals("") && !CheckData.checkInteger(esito.get_capiConsistenzaBovini())){
			result.set_result(false);
			result.set_message("Il campo -Consistenza capi bovini- deve essere un valore numerico");
			return result;
		}
		// 2.b consistenza capi ovini
		if(!esito.get_capiConsistenzaOvini().equals("") && !CheckData.checkInteger(esito.get_capiConsistenzaOvini())){
			result.set_result(false);
			result.set_message("Il campo -Consistenza capi ovini- deve essere un valore numerico");
			return result;
		}
		// 2.c consistenza capi suini
		if(!esito.get_capiConsistenzaSuini().equals("") && !CheckData.checkInteger(esito.get_capiConsistenzaSuini())){
			result.set_result(false);
			result.set_message("Il campo -Consistenza capi suini- deve essere un valore numerico");
			return result;
		}
		
		// 3. capi controllati
		// 3.a capi controllati bovini
		if(!esito.get_capiControllatiBovini().equals("") && !CheckData.checkInteger(esito.get_capiControllatiBovini())){
			result.set_result(false);
			result.set_message("Il campo -Capi controllati bovini- deve essere un valore numerico");
			return result;
		}
		// 3.b capi controllati ovini
		if(!esito.get_capiControllatiOvini().equals("") && !CheckData.checkInteger(esito.get_capiControllatiOvini())){
			result.set_result(false);
			result.set_message("Il campo -Capi controllati ovini- deve essere un valore numerico");
			return result;
		}
		// 3.c capi controllati suini
		if(!esito.get_capiControllatiSuini().equals("") && !CheckData.checkInteger(esito.get_capiControllatiSuini())){
			result.set_result(false);
			result.set_message("Il campo -Capi controllati suini- deve essere un valore numerico");
			return result;
		}
				
		result.set_result(true);
		return result;
	}
	
	

	/********************************************************
	 * 	METODI GENERICI
	 ********************************************************/
	
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData()
	{
		Query q = _em.createNamedQuery("EsitoZootecniaStato.selectAll");
		_em.clear();
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoZootecniaStato> fornitura = q.getResultList();
		elencoEsitoZoot.set_listEsitoZoot(fornitura);		
	}
	
	/**
	 * restituisce la lista di tutte le campagne presenti
	 * @return List<String>
	 */
	public List<String> getListCampagna(){
		Query q = _em.createNamedQuery("Domanda.distinctCampagna");
		List<String> fornitura = q.getResultList();
		return fornitura;
	}
	
	
	/**
	 * restituisce la lista dei valori distinti per l'anagrafica indicata
	 * @param valoriAnagrafica
	 * @return List<String>
	 */
	public List<String> getListaValori(String valoriAnagrafica)
	{
		_em.clear();
		Query q = _em.createNamedQuery("ValoreAnagrafica.selectAnagrafica");
		q.setParameter("campo", valoriAnagrafica);
		List<ValoreAnagrafica> fornitura = q.getResultList();
		List<String> lista = new ArrayList<String>();
		for (ValoreAnagrafica valoreAnagrafica : fornitura) {
			lista.add(valoreAnagrafica.get_valore());
		}
		lista.add(0, "");
		return lista;
	}
	
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
	public void filter(HashMap<String, String> parametersList)
    {
		_em.clear();
    	Query q = _em.createNamedQuery("EsitoZootecniaStato.selectFilter");
		// imposto i parametri della query
		Utils.setQueryParameter(parametersList, q);		
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoZootecniaStato> fornitura = q.getResultList();
		elencoEsitoZoot.set_listEsitoZoot(fornitura);
    }
	
	public EsitoZootecniaEsitoAmmBov getEsitoBovFromOvi(String campagna, String cuaa, String domanda){
		_em.clear();
    	Query q = _em.createNamedQuery("EsitoZootecniaEsitoAmmBov.selectFilter");
		// imposto i parametri della query
    	q.setParameter("campagna", campagna);
    	q.setParameter("cuaa", cuaa);
    	q.setParameter("domanda", domanda);	
		q.setMaxResults(Costanti.QUERY_TOP_K);
		List<EsitoZootecniaEsitoAmmBov> fornitura = q.getResultList();
		return fornitura.get(0);		
	}
	
	
	/**
	 * trova la domanda DU a partire dal CUAA e dalla campagna
	 * @param cuaa
	 * @param campagna
	 * @return DomandaBO
	 */
	public DomandaBO getDomandaDU(String cuaa, String campagna)
	{
		_em.clear();
    	Query q = _em.createNamedQuery("Domanda.domandaForCuaaCampagnaMisura");
		// imposto i parametri della query
    	q.setParameter("cuaa", cuaa);
    	q.setParameter("campagna", campagna);
    	q.setParameter("misura", "DU");
		List<Domanda> fornitura = q.getResultList();
		if(fornitura.size()>0) return new DomandaBO(fornitura.get(0));
		else return null;
	}
	
	
	/**
	 * cancella a livello logico i dati presenti nella lista
	 */
	public void clearList()
	{
		elencoEsitoZoot = new ElencoEsitoZootecniaBO();
	}
	
	public void setElencoEsitoZoot(ElencoEsitoZootecniaBO elencoEsitoZoot) {
		this.elencoEsitoZoot = elencoEsitoZoot;
	}

	public List<EsitoZootecniaStatoBO> getElencoEsitoZoot() {
		return elencoEsitoZoot.get_listEsitoZoot();
	}

}
