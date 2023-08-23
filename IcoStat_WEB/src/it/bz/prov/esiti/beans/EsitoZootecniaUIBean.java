package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.bobject.DomandaBO;
import it.bz.prov.esiti.bobject.ElencoEsitoZootecniaBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaControlloAmmBovBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaControlloAmmOviBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaControlloCondBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaEsitoAmmBovBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaEsitoAmmOviBO;
import it.bz.prov.esiti.bobject.EsitoZootecniaStatoBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.ibusiness.IEsitoZootecnia;
import it.bz.prov.esiti.logic.EsitoZootecniaBean;
import it.bz.prov.esiti.util.Costanti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.openfaces.event.AjaxActionEvent;

/**
 * Bean UI (user interface) per la gestione degli esiti zootecnia
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "esitoZootBean")
@SessionScoped
public class EsitoZootecniaUIBean {
	@EJB
	private IEsitoZootecnia _esitoZootecniaEJBBean;
	private EsitoZootecniaStatoBO _esitoZootStato ;
	private EsitoZootecniaStatoBO _selectedValue;
	private EsitoZootecniaStatoBO _esitoZootStatoOld ; // serve per memorizzare la versione originale durante la modifica
	private int _selectedValueIndex;
	private String _action;
	private String _action_principale; // serve per ricordarsi se siamo in modifica o in aggiunta di un esito zootecnia complessivo
	private List<String> _listCampagna;
	private List<String> _listEsito;
	private List<String> _listInterventi;
	private List<String> _listStatoEsito;
	private List<String> _listRuoloPersonaPresente;
	private List<SelectItem> _listRadioButton;
	private String _titoloPagina;
	private boolean _messageVisible;
	private String _message;
	private boolean _readOnly;
	private EsitoZootecniaEsitoAmmBovBO _esitoZootEsitoAmmBov ;
	private EsitoZootecniaEsitoAmmOviBO _esitoZootEsitoAmmOvi ;
	private EsitoZootecniaControlloAmmBovBO _esitoZootControlloAmmBov ;
	private EsitoZootecniaControlloAmmOviBO _esitoZootControlloAmmOvi ;
	private EsitoZootecniaControlloCondBO _esitoZootControlloCond ;
	private boolean _esitoSaved; // serve quando sei in aggiungi, non hai ancora salvato l'esito e vuoi editare altre cose
	private boolean _selectInterventoDisable = true;
	
	private String _titoloAggiungiEsito;
	private String _titoloModificaEsito;
	private String _titoloControlloAmmBov;
	private String _titoloControlloAmmOvi;
	private String _titoloControlloCond;
	private String _titoloEsitoAmmBov;
	private String _titoloEsitoAmmOvi;
	private String _titoloDatiControllo;
	
	
	/**
	 * costruttore
	 */
	public EsitoZootecniaUIBean()
	{
		_selectedValue = new EsitoZootecniaStatoBO();
		_esitoZootStatoOld = new EsitoZootecniaStatoBO();
		_esitoZootStato = new EsitoZootecniaStatoBO();
		_action= Costanti.ACTION_VIEW;
		_listCampagna = new Vector<String>();
		_listEsito = new ArrayList<String>();
		_listInterventi = new ArrayList<String>();
		_listStatoEsito = new ArrayList<String>();
		_listStatoEsito.add("DEFINITIVO");
		_listStatoEsito.add("NON DEFINITIVO");
		_listRuoloPersonaPresente = new ArrayList<String>(); 
		_listRadioButton = new ArrayList<SelectItem>();
		_listRadioButton.add(new SelectItem("SI", "Si"));
		_listRadioButton.add(new SelectItem("NO", "No"));	
		_titoloPagina = "";
		_message = "";
		_messageVisible = false;
		_readOnly = false;
		_action_principale = Costanti.ACTION_VIEW;
		
		_esitoZootEsitoAmmBov = new EsitoZootecniaEsitoAmmBovBO();
		_esitoZootEsitoAmmOvi = new EsitoZootecniaEsitoAmmOviBO();
		_esitoZootControlloAmmBov = new EsitoZootecniaControlloAmmBovBO();
		_esitoZootControlloAmmOvi = new EsitoZootecniaControlloAmmOviBO();
		_esitoZootControlloCond = new EsitoZootecniaControlloCondBO();
		
		_titoloAggiungiEsito = "Creazione nuovo esito Zootecnia";
		_titoloModificaEsito = "Modifica Esito Zootecnia";
		_titoloControlloAmmBov = "Controllo Ammissibilita' Bovini";
		_titoloControlloAmmOvi = "Controllo Ammissibilita' Ovicaprini";
		_titoloControlloCond = "Controllo Condizionalità";
		_titoloEsitoAmmBov = "Esito Controllo Ammissibilita' Bovini";
		_titoloEsitoAmmOvi = "Esito Controllo Ammissibilita' Ovicaprini";
		_titoloDatiControllo = "Documentazione del Controllo";
		
		_esitoSaved = false; // si usa solo nel caso di inserimento di nuovo esito
		_selectInterventoDisable = true;
	}
	
	
	/************************************************************************
	 * 				ACTION MASCHERA VISUALIZZAZIONE LISTA ESITI
	 ***********************************************************************/
	
	/**
	 * aggiunge un esito alla lista
	 */
	public void aggiungiEsito()
	{
		_action = Costanti.ACTION_INSERT;
		_action_principale = Costanti.ACTION_INSERT;
		_esitoZootStato = new EsitoZootecniaStatoBO();
		_titoloPagina = _titoloAggiungiEsito;
		_readOnly = false;
		
		_selectedValue = new EsitoZootecniaStatoBO();
		_esitoZootStatoOld = new EsitoZootecniaStatoBO();
		_esitoZootStato = new EsitoZootecniaStatoBO();
		_esitoZootEsitoAmmBov = new EsitoZootecniaEsitoAmmBovBO();
		_esitoZootEsitoAmmOvi = new EsitoZootecniaEsitoAmmOviBO();
		_esitoZootControlloAmmBov = new EsitoZootecniaControlloAmmBovBO();
		_esitoZootControlloAmmOvi = new EsitoZootecniaControlloAmmOviBO();
		_esitoZootControlloCond = new EsitoZootecniaControlloCondBO();
		
		_esitoZootStato.set_contrAmmBovini(Costanti.STATO_NON_COMPILATO);
		_esitoZootStato.set_contrAmmOvicaprini(Costanti.STATO_NON_COMPILATO);
		_esitoZootStato.set_contrCond(Costanti.STATO_NON_COMPILATO);
		_esitoZootStato.set_esitoAmmBovini(Costanti.STATO_NON_COMPILATO);
		_esitoZootStato.set_esitoAmmOvicaprini(Costanti.STATO_NON_COMPILATO);
		_esitoZootStato.set_documentazioneControllo(Costanti.STATO_NON_COMPILATO);
		
		// se nel box di ricerca sono inseriti cuaa o campagna si pre-compila la form di inserimento
		FacesContext context = FacesContext.getCurrentInstance();
		SearchBean searchBean = (SearchBean) context.getApplication().evaluateExpressionGet(context, "#{searchBean}", Object.class);
		String cuaa= searchBean.getCuaa();
		String campagna= searchBean.getCampagna();
		// per evitare di prendere un cuaa che é una substring (anche così la stringa potrebbe non 
		//corrispondere a un cuaa, ma é una richiesta di Lars)
		/*
		if(cuaa.length()>10) _esitoZootStato.set_cuaa(cuaa);
		if(!campagna.equals("")) _esitoZootStato.set_campagna(campagna);
		if(cuaa.length()>10 && !campagna.equals("")){
			DomandaBO domanda  = _esitoZootecniaEJBBean.getDomandaDU(cuaa, campagna);
			if(domanda != null) _esitoZootStato.set_domanda(domanda.get_idDomanda());
		}
		*/
		if(cuaa.length() > 10 )	{
		if(campagna.equals("")) _esitoZootStato.set_cuaa(cuaa);
			if(!campagna.equals("")){
				DomandaBO domanda  = _esitoZootecniaEJBBean.getDomandaDU(cuaa, campagna);
				if(domanda == null){
					_message = "Nessuna domanda presente per il CUAA e Campagna indicati nella form di ricerca";
					_messageVisible = true;
				}
				else 
				{
					_esitoZootStato.set_cuaa(cuaa);
					_esitoZootStato.set_campagna(campagna);
					_esitoZootStato.set_domanda(domanda.get_idDomanda());
				}
			}
		}
	}
	
	/**
	 * modifica un esito nella lista
	 */
	public void modificaEsito()
	{		
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_domanda().equals("") || _selectedValueIndex == -1) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		_action = Costanti.ACTION_MODIFY;
		_action_principale = Costanti.ACTION_MODIFY;
		if(_selectedValue.get_cuaa().equals("")) return;
//		_selectedValue= _esitoZootecniaEJBBean.getElencoEsitoZoot().get(_selectedValueIndex);
		_selectedValue = _esitoZootecniaEJBBean.getStatoFromIndex(_selectedValueIndex);
		
		_esitoZootStato = _selectedValue;
		_esitoZootStatoOld = _esitoZootStato.clone();
		_titoloPagina = _titoloModificaEsito;
		_readOnly = true;
	}
	
	/**
	 * cancella un esito nella lista
	 */
	public void cancellaEsito(){
		// se non é stato selezionato nulla
		if(_selectedValue == null || _selectedValue.get_domanda().equals("") || _selectedValueIndex == -1) 
		{
			_messageVisible=true; 
			_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
			return;
		}
		if(_selectedValue.get_cuaa().equals("")) return;
		_selectedValue= _esitoZootecniaEJBBean.getElencoEsitoZoot().get(_selectedValueIndex);
		OperationResultBO result = _esitoZootecniaEJBBean.cancellaEsito(_selectedValue);
		_messageVisible=true; 
		_message= result.get_message();
	}
	
	
	
	/************************************************************************
	 * 				ACTION MASCHERA EDIT PRINCIPALE
	 ***********************************************************************/
	
	/**
	 * modifica o compilazione delle informazioni sul controllo di ammissibilità per i bovini
	 */
	public void salvaEsito()
	{
		OperationResultBO result = new OperationResultBO();
		if(_action == Costanti.ACTION_INSERT )
			result = _esitoZootecniaEJBBean.salvaEsito(_esitoZootStato, EsitoZootecniaBean.INSERT_ESITO);
		else if(_action == Costanti.ACTION_MODIFY )
			result = _esitoZootecniaEJBBean.salvaEsito(_esitoZootStato, EsitoZootecniaBean.UPDATE_STATO_COMPILAZIONE);
		_message = result.get_message();
		_messageVisible = true;
		// se siamo in inserimento di nuovo esito e il risultato dell'inserimento é true setto il flag
		if(_action == Costanti.ACTION_INSERT && result.get_result()) _esitoSaved = true;
	}
	
	/**
	 * annulla la creazione di un nuovo esito
	 */
	public void annullaEsito()
	{
		// commentato perchè genera la riga vuota nella tabella principale degli esiti
//		if(_esitoZootStatoOld != null) _esitoZootStato.recovery(_esitoZootStatoOld);
		_action = Costanti.ACTION_VIEW;
	}
	
	/**
	 * modifica o compilazione delle informazioni sul controllo di ammissibilità per i bovini
	 */
	public void editControlloAmmBov()
	{
		// se siamo in inserimento di nuovo esito e non é ancora stato salvato lo stato non faccio continuare
		if(_action == Costanti.ACTION_INSERT &&  !_esitoSaved){
			_message = "Prima di procedere salvare il nuovo esito";
			_messageVisible = true;
			return;
		}		
		_action = Costanti.ACTION_EDIT_ZOOT_CONTR_AMM_BOVINI;
		_titoloPagina = _titoloControlloAmmBov;
		// verificare se l'esito é già su DB, in questo caso ottenere i dati, altrimenti nuova istanza di classe
		_esitoZootControlloAmmBov = _esitoZootecniaEJBBean.getControlloAmmBovini(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna());
		// impostazione valori di default
		if(_esitoZootControlloAmmBov.get_flgAnomalie().equals(""))
			_esitoZootControlloAmmBov.set_flgAnomalie(Costanti.VAL_NO);
		if(_esitoZootControlloAmmBov.get_flgRegistroAssente().equals(""))
			_esitoZootControlloAmmBov.set_flgRegistroAssente(Costanti.VAL_NO);
		if(_esitoZootControlloAmmBov.get_flgInosservanzaNorme().equals(""))
			_esitoZootControlloAmmBov.set_flgInosservanzaNorme(Costanti.VAL_NO);
		if(_esitoZootControlloAmmBov.get_capi_anom_amm_scarico_bdn() == null || 
			(_esitoZootControlloAmmBov.get_capi_anom_amm_scarico_bdn() != null && _esitoZootControlloAmmBov.get_capi_anom_amm_scarico_bdn().equals("")))
			_esitoZootControlloAmmBov.set_capi_anom_amm_scarico_bdn(Costanti.VAL_NO);
		// non visualizzo il messaggio in caso di cambio tab
		if(_messageVisible)_messageVisible = false;
	}
	
	/**
	 * cancella le informazioni sul controllo di ammissibilità per i bovini
	 */
	public void cancellaControlloAmmBov()
	{
		OperationResultBO result = _esitoZootecniaEJBBean.cancellaControlloAmmBovini(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna());
		if(result.get_result()) {
			_esitoZootControlloAmmBov = new EsitoZootecniaControlloAmmBovBO();
		}
		
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * cancella le informazioni sul controllo di ammissibilità per i bovini
	 */
	public void cancellaEsitoIntAmmBov()
	{
		OperationResultBO result = _esitoZootecniaEJBBean.cancellaEsitoIntAmmBovini(_esitoZootEsitoAmmBov.get_cuaa(), _esitoZootEsitoAmmBov.get_domanda(), _esitoZootEsitoAmmBov.get_campagna(), _esitoZootEsitoAmmBov.get_intervento());
		if(result.get_result()) {
			_esitoZootEsitoAmmBov = new EsitoZootecniaEsitoAmmBovBO();
			_selectInterventoDisable = false;
		}
		
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * modifica o compilazione delle informazioni sull'esito di ammissibilità per i bovini
	 */
	public void editEsitoAmmBov()
	{
		// se siamo in inserimento di nuovo esito e non é ancora stato salvato lo stato non faccio continuare
		if(_action == Costanti.ACTION_INSERT &&  !_esitoSaved){
			_message = "Prima di procedere salvare il nuovo esito";
			_messageVisible = true;
			return;
		}
		_action = Costanti.ACTION_EDIT_ZOOT_ESITO_AMM_BOVINI;
		_titoloPagina = _titoloEsitoAmmBov;
		_esitoZootEsitoAmmBov = new EsitoZootecniaEsitoAmmBovBO();
		_selectInterventoDisable = false;
		_listInterventi.clear();
		_listInterventi.add("");
		if(_esitoZootStato.get_flg310().equals("SI")) _listInterventi.add("310");
		if(_esitoZootStato.get_flg311().equals("SI")) _listInterventi.add("311");
		if(_esitoZootStato.get_flg313().equals("SI")) _listInterventi.add("313");
		if(_esitoZootStato.get_flg315().equals("SI")) _listInterventi.add("315");
		if(_esitoZootStato.get_flg316().equals("SI")) _listInterventi.add("316");
		if(_esitoZootStato.get_flg318().equals("SI")) _listInterventi.add("318");
		if(_esitoZootStato.get_flag322().equals("SI")) _listInterventi.add("322");
		if(_messageVisible)_messageVisible = false;
	}
	
	/**
	 * cancella l'esito di ammissibilità per i bovini
	 */
	public void cancellaEsitoAmmBov()
	{
		OperationResultBO result = _esitoZootecniaEJBBean.cancellaEsitoAmmBovini(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna());
		if(result.get_result()) {
			_esitoZootEsitoAmmBov = new EsitoZootecniaEsitoAmmBovBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * modifica o compilazione delle informazioni sul controllo di ammissibilità per gli ovicaprini
	 */
	public void editControlloAmmOvi()
	{
		// se siamo in inserimento di nuovo esito e non é ancora stato salvato lo stato non faccio continuare
		if(_action == Costanti.ACTION_INSERT &&  !_esitoSaved){
			_message = "Prima di procedere salvare il nuovo esito";
			_messageVisible = true;
			return;
		}
		_action = Costanti.ACTION_EDIT_ZOOT_CONTR_AMM_OVICAPRINI;
		_titoloPagina = _titoloControlloAmmOvi;
		// verificare se l'esito é già su DB, in questo caso ottenere i dati, altrimenti nuova istanza di classe
		_esitoZootControlloAmmOvi = _esitoZootecniaEJBBean.getControlloAmmOvi(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna());
		// impostazione valori di default
		if(_esitoZootControlloAmmOvi.get_flgInosservanzaNorme().equals(""))
			_esitoZootControlloAmmOvi.set_flgInosservanzaNorme(Costanti.VAL_NO);
		if(_esitoZootControlloAmmOvi.get_flgAnomalie().equals(""))
			_esitoZootControlloAmmOvi.set_flgAnomalie(Costanti.VAL_NO);
		if(_esitoZootControlloAmmOvi.get_flgRegAziAssNonComp().equals(""))
			_esitoZootControlloAmmOvi.set_flgRegAziAssNonComp(Costanti.VAL_NO);
		if(_esitoZootControlloAmmOvi.get_flgRegAziNonCompSitSod().equals(""))
			_esitoZootControlloAmmOvi.set_flgRegAziNonCompSitSod(Costanti.VAL_NO);
		if(_esitoZootControlloAmmOvi.get_flgRegAziComp().equals(""))
			_esitoZootControlloAmmOvi.set_flgRegAziComp(Costanti.VAL_SI);
		if(_esitoZootControlloAmmOvi.get_capi_anom_amm_scarico_bdn() == null || 
				(_esitoZootControlloAmmOvi.get_capi_anom_amm_scarico_bdn() != null && _esitoZootControlloAmmOvi.get_capi_anom_amm_scarico_bdn().equals("")))
			_esitoZootControlloAmmOvi.set_capi_anom_amm_scarico_bdn(Costanti.VAL_NO);
		// non visualizzo il messaggio in caso di cambio tab
		if(_messageVisible)_messageVisible = false;
	}
	
	/**
	 * cancella il controllo di ammissibilità per gli ovicaprini
	 */
	public void cancellaControlloAmmOvi()
	{
		OperationResultBO result = _esitoZootecniaEJBBean.cancellaControlloAmmOvicaprini(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna());
		if(result.get_result()) {
			_esitoZootControlloAmmOvi = new EsitoZootecniaControlloAmmOviBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * modifica o compilazione delle informazioni sull'esito di ammissibilità per gli ovicaprini
	 */
	public void editEsitoAmmOvi()
	{
		// se siamo in inserimento di nuovo esito e non é ancora stato salvato lo stato non faccio continuare
		if(_action == Costanti.ACTION_INSERT &&  !_esitoSaved){
			_message = "Prima di procedere salvare il nuovo esito";
			_messageVisible = true;
			return;
		}
		_action = Costanti.ACTION_EDIT_ZOOT_ESITO_AMM_OVICAPRINI;
		_titoloPagina = _titoloEsitoAmmOvi;
		// verificare se l'esito é già su DB, in questo caso ottenere i dati, altrimenti nuova istanza di classe
		_esitoZootEsitoAmmOvi = _esitoZootecniaEJBBean.getEsitoAmmOvi(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna());
		_esitoZootEsitoAmmOvi.set_esi_contr_320("POSITIVO");
		// non visualizzo il messaggio in caso di cambio tab
		if(_messageVisible)_messageVisible = false;
	}
	
	/**
	 * cancella l'esito inserito per ovicaprini
	 */
	public void cancellaEsitoAmmOvi()
	{
		OperationResultBO result = _esitoZootecniaEJBBean.cancellaEsitoAmmOvicaprini(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna());
		if(result.get_result()) {
			_esitoZootEsitoAmmOvi = new EsitoZootecniaEsitoAmmOviBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * modifica o compilazione delle informazioni sul controllo di condizionalità zootecnia
	 */
	public void editControlloCond()
	{
		// se siamo in inserimento di nuovo esito e non é ancora stato salvato lo stato non faccio continuare
		if(_action == Costanti.ACTION_INSERT &&  !_esitoSaved){
			_message = "Prima di procedere salvare il nuovo esito";
			_messageVisible = true;
			return;
		}
		_action = Costanti.ACTION_EDIT_ZOOT_CONTR_COND;
		_titoloPagina = _titoloControlloCond;
		// verificare se l'esito é già su DB, in questo caso ottenere i dati, altrimenti nuova istanza di classe
		_esitoZootControlloCond = _esitoZootecniaEJBBean.getControlloCond(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna());
		// impostazione valori di default
		if(_esitoZootControlloCond.get_flgAnomalie().equals(""))
			_esitoZootControlloCond.set_flgAnomalie(Costanti.VAL_NO);
		if(_esitoZootControlloCond.get_flgAziNonRegBDN().equals(""))
			_esitoZootControlloCond.set_flgAziNonRegBDN(Costanti.VAL_NO);
		if(_esitoZootControlloCond.get_flgBovBufNoPassMarchiDoc().equals(""))
			_esitoZootControlloCond.set_flgBovBufNoPassMarchiDoc(Costanti.VAL_NO);
		if(_esitoZootControlloCond.get_flgBovBufOviIdentNoConf().equals(""))
			_esitoZootControlloCond.set_flgBovBufOviIdentNoConf(Costanti.VAL_NO);
		if(_esitoZootControlloCond.get_flgBovBufOviNoReg().equals(""))
			_esitoZootControlloCond.set_flgBovBufOviNoReg(Costanti.VAL_NO);
		if(_esitoZootControlloCond.get_flgOviSuiMarcNoConf().equals(""))
			_esitoZootControlloCond.set_flgOviSuiMarcNoConf(Costanti.VAL_NO);
		if(_esitoZootControlloCond.get_flgOviSuiNoMarcDoc().equals(""))
			_esitoZootControlloCond.set_flgOviSuiNoMarcDoc(Costanti.VAL_NO);
		if(_esitoZootControlloCond.get_flgRegAziAss().equals(""))
			_esitoZootControlloCond.set_flgRegAziAss(Costanti.VAL_NO);
		if(_esitoZootControlloCond.get_flgRegAziNonConforme().equals(""))
			_esitoZootControlloCond.set_flgRegAziNonConforme(Costanti.VAL_NO);
		// non visualizzo il messaggio in caso di cambio tab
		if(_messageVisible)_messageVisible = false;
	}
	
	/**
	 * cancella i dati relativi al controllo di condizionalità
	 */
	public void cancellaControlloCond()
	{		
		OperationResultBO result = _esitoZootecniaEJBBean.cancellaControlloCond(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna());
		if(result.get_result()) {
			_esitoZootControlloCond = new EsitoZootecniaControlloCondBO();
		}
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
	}
	
	/**
	 * modifica o compilazione delle informazioni sulla documentazione del controllo
	 */
	public void editDatiControllo()
	{
		// se siamo in inserimento di nuovo esito e non é ancora stato salvato lo stato non faccio continuare
		if(_action == Costanti.ACTION_INSERT &&  !_esitoSaved){
			_message = "Prima di procedere salvare il nuovo esito";
			_messageVisible = true;
			return;
		}
		_action = Costanti.ACTION_EDIT_DATI_CONTROLLO;
		_titoloPagina = _titoloDatiControllo;
		// verificare se l'esito é già su DB, in questo caso ottenere i dati, altrimenti nuova istanza di classe
		_esitoZootStato = _esitoZootecniaEJBBean.getStato(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna());
		// se non c'è nessun ruolo impostato propongo il ruolo di intestatario
		if(_esitoZootStato.get_personaPresenteRuolo().equals(""))
			_esitoZootStato.set_personaPresenteRuolo("INTESTATARIO");
		_esitoZootStatoOld = _esitoZootStato.clone();
		// non visualizzo il messaggio in caso di cambio tab
		if(_messageVisible)_messageVisible = false;
	}
	
	
	/**
	 * funzione che trova la domanda DU a partire da cuaa e campagna
	 * @param e
	 */
	public void findDomanda(AjaxActionEvent e){
		DomandaBO domandaDU = _esitoZootecniaEJBBean.getDomandaDU(_esitoZootStato.get_cuaa(), _esitoZootStato.get_campagna());
		if(domandaDU == null) return;
		_esitoZootStato.set_domanda(domandaDU.get_idDomanda());
	}
	
	
	/************************************************************************
	 * 				ACTION MASCHERA EDIT CONTROLLO AMM BOVINI
	 ***********************************************************************/
	
	/**
	 * Salva i dati del controllo ammissibilità bovini
	 */
	public void salvaContrAmmBov(){
		OperationResultBO result1 = _esitoZootecniaEJBBean.salvaControlloAmmBovini(_esitoZootControlloAmmBov);
		// se il salvataggio dei dati é andato a buon fine aggiorno il resto
		if(result1.get_result()){
			// cambio dello stato nel caso in cui lo stato attuale sia diverso
			if(_esitoZootStato.get_contrAmmBovini().equals(Costanti.STATO_NON_COMPILATO)){
				_esitoZootStato.set_contrAmmBovini(Costanti.STATO_COMPILATO);
				OperationResultBO result2 = _esitoZootecniaEJBBean.salvaEsito(_esitoZootStato, EsitoZootecniaBean.UPDATE_STATO_COMPILAZIONE);
				// se l'azione é andata a buon fine torno nella maschera precedente
				if(result2.get_result()){
					_action = _action_principale;
					if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
					else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
					_esitoZootStatoOld = _esitoZootStato;
				}
			}
			else // caso in cui non va cambiato lo stato di compilazione
			{
				_action = _action_principale;
				if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
				else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
				_esitoZootStatoOld = _esitoZootStato;
			}			
		}			
		_message = result1.get_message();
		_messageVisible = true;		
	}
	
	/**
	 * annulla l'operazione di editing del controllo di ammissibilità bovini
	 */
	public void annullaContrAmmBov(){
		_action = _action_principale;
		if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
		else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
	}
	
	/************************************************************************
	 * 				ACTION MASCHERA EDIT CONTROLLO AMM OVICAPRINI
	 ***********************************************************************/
	
	/**
	 * Salva i dati del controllo ammissibilità ovicaprini
	 */
	public void salvaContrAmmOvi(){
		OperationResultBO result1 = _esitoZootecniaEJBBean.salvaControlloAmmOvicaprini(_esitoZootControlloAmmOvi);
		// se il salvataggio dei dati é andato a buon fine aggiorno il resto
		if(result1.get_result()){
			// cambio dello stato di compilazione
			if(_esitoZootStato.get_contrAmmOvicaprini().equals(Costanti.STATO_NON_COMPILATO)){
				_esitoZootStato.set_contrAmmOvicaprini(Costanti.STATO_COMPILATO);
				OperationResultBO result2 = _esitoZootecniaEJBBean.salvaEsito(_esitoZootStato, EsitoZootecniaBean.UPDATE_STATO_COMPILAZIONE);
				// se l'azione é andata a buon fine torno nella maschera precedente
				if(result2.get_result()){
					_action = _action_principale;
					if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
					else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
					_esitoZootStatoOld = _esitoZootStato;
				}
			}
			else { // non é necessario cambiare lo stato di compilazione
				_action = _action_principale;
				if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
				else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
				_esitoZootStatoOld = _esitoZootStato;
			}			
		}			
		_message = result1.get_message();
		_messageVisible = true;		
	}
	
	/**
	 * annulla l'operazione di editing del controllo di ammissibilità ovicaprini
	 */
	public void annullaContrAmmOvi(){
		_action = _action_principale;
		if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
		else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
	}
	
	/************************************************************************
	 * 				ACTION MASCHERA EDIT CONTROLLO COND
	 ***********************************************************************/
	
	/**
	 * Salva i dati del controllo di condizionalità
	 */
	public void salvaContrCond(){
		OperationResultBO result1 = _esitoZootecniaEJBBean.salvaControlloCond(_esitoZootControlloCond);
		// se il salvataggio dei dati é andato a buon fine aggiorno il resto
		if(result1.get_result()){
			// cambio dello stato di compilazione
			if(_esitoZootStato.get_contrCond().equals(Costanti.STATO_NON_COMPILATO)){
				_esitoZootStato.set_contrCond(Costanti.STATO_COMPILATO);
				OperationResultBO result2 = _esitoZootecniaEJBBean.salvaEsito(_esitoZootStato, EsitoZootecniaBean.UPDATE_STATO_COMPILAZIONE);
				// se l'azione é andata a buon fine torno nella maschera precedente
				if(result2.get_result()){
					_action = _action_principale;
					if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
					else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
					_esitoZootStatoOld = _esitoZootStato;
				}
			}
			else { // non serve il cambiamento dello stato di compilazione
				_action = _action_principale;
				if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
				else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
				_esitoZootStatoOld = _esitoZootStato;
			}			
		}			
		_message = result1.get_message();
		_messageVisible = true;		
	}
	
	/**
	 * annulla l'operazione di editing del controllo di condizionalita
	 */
	public void annullaContrCond(){
		_action = _action_principale;
		if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
		else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
	}
	
	/************************************************************************
	 * 				ACTION MASCHERA EDIT ESITO AMM OVICAPRINI
	 ***********************************************************************/
	
	/**
	 * Salva i dati dell'esito del controllo di ammissibilità ovicaprini
	 */
	public void salvaEsitoAmmOvi(){
		OperationResultBO result1 = _esitoZootecniaEJBBean.salvaEsitoAmmOvicaprini(_esitoZootEsitoAmmOvi);
		// se il salvataggio dei dati é andato a buon fine aggiorno il resto
		if(result1.get_result()){
			// cambio dello stato di compilazione
			if(_esitoZootStato.get_esitoAmmOvicaprini().equals(Costanti.STATO_NON_COMPILATO)){
				_esitoZootStato.set_esitoAmmOvicaprini(Costanti.STATO_COMPILATO);
				OperationResultBO result2 = _esitoZootecniaEJBBean.salvaEsito(_esitoZootStato, EsitoZootecniaBean.UPDATE_STATO_COMPILAZIONE);
				// se l'azione é andata a buon fine torno nella maschera precedente
				if(result2.get_result()){
					_action = _action_principale;
					if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
					else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
					_esitoZootStatoOld = _esitoZootStato;
				}
			}
			else { // non serve cambiare lo stato di compilazione
				_action = _action_principale;
				if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
				else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
				_esitoZootStatoOld = _esitoZootStato;
			}			
		}			
		_message = result1.get_message();
		_messageVisible = true;	
	}
	
	/**
	 * annulla l'operazione di editing dell'esito del controllo di ammissibilità ovicaprini
	 */
	public void annullaEsitoAmmOvi(){
		_action = _action_principale;
		if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
		else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
	}
	
	
	/************************************************************************
	 * 				ACTION MASCHERA EDIT ESITO AMM BOVINI
	 ***********************************************************************/
	
	/**
	 * Salva i dati generici dell'esito del controllo di ammissibilità bovini
	 */
	public void salvaEsitoAmmBov(){
		OperationResultBO result1 = _esitoZootecniaEJBBean.salvaEsitoAmmBovini(_esitoZootEsitoAmmBov);
		// se il salvataggio dei dati é andato a buon fine aggiorno il resto
		if(result1.get_result()){
			// cambio dello stato di compilazione
			if(_esitoZootStato.get_esitoAmmBovini().equals(Costanti.STATO_NON_COMPILATO)){
				_esitoZootStato.set_esitoAmmBovini(Costanti.STATO_COMPILATO);
				OperationResultBO result2 = _esitoZootecniaEJBBean.salvaEsito(_esitoZootStato, EsitoZootecniaBean.UPDATE_STATO_COMPILAZIONE);
				// se l'azione é andata a buon fine torno nella maschera precedente
				if(result2.get_result()){
//					_action = _action_principale;
//					if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
//					else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
					_esitoZootStatoOld = _esitoZootStato;
					_esitoZootEsitoAmmBov = new EsitoZootecniaEsitoAmmBovBO();
				}
			}
			else { // non serve cambiare lo stato di compilazione
//				_action = _action_principale;
//				if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
//				else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
				_esitoZootStatoOld = _esitoZootStato;
				_esitoZootEsitoAmmBov = new EsitoZootecniaEsitoAmmBovBO();
			}
		_selectInterventoDisable = false;
		}			
		_message = result1.get_message();
		_messageVisible = true;	
	}
	
	/**
	 * annulla l'operazione di editing dell'esito del controllo di ammissibilità bovini
	 */
	public void annullaEsitoAmmBov(){
		_esitoZootEsitoAmmBov = new EsitoZootecniaEsitoAmmBovBO();
		_selectInterventoDisable = false;
	}
	
	/**
	 * annulla l'operazione di editing dell'esito del controllo di ammissibilità bovini
	 */
	public void indietroEsitoAmmBov(){
		_action = _action_principale;
		if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
		else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
		_esitoZootEsitoAmmBov = new EsitoZootecniaEsitoAmmBovBO();
		_selectInterventoDisable = false;
	}
	
	/************************************************************************
	 * 				ACTION MASCHERA EDIT DOCUMENTAZIONE CONTROLLO
	 ***********************************************************************/
	
	/**
	 * Salva i dati generici del controllo di zootecnia
	 */
	public void salvaDatiControllo(){		
		// cambio dello stato
		if(_esitoZootStato.get_documentazioneControllo().equals(Costanti.STATO_NON_COMPILATO))
			_esitoZootStato.set_documentazioneControllo(Costanti.STATO_COMPILATO);
		OperationResultBO result = _esitoZootecniaEJBBean.salvaEsito(_esitoZootStato, EsitoZootecniaBean.INSERT_DATI_CONTROLLO);
		_message = result.get_message();
		_messageVisible = true;
		// se l'azione é andata a buon fine torno nella maschera precedente
		if(result.get_result()){
			_action = _action_principale;
			if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
			else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
			_esitoZootStatoOld = _esitoZootStato;
		}
	}
	
	
	/**
	 * annulla l'operazione di editing dei dati generici del controllo di zootecnia
	 */
	public void annullaDatiControllo(){
		_action = _action_principale;
		if(_action_principale.equals(Costanti.ACTION_INSERT)) _titoloPagina = _titoloAggiungiEsito;
		else if(_action_principale.equals(Costanti.ACTION_MODIFY)) _titoloPagina = _titoloModificaEsito;
		_esitoZootStato = _esitoZootStatoOld;
	}
	
	/**
	 * Interviene per recuperare da DB i dati dell'intervento, se presenti. Inoltre mostra i campi ad compilare/editare in caso di modifica
	 */
	public void selectInterventoChange(AjaxActionEvent e) {
		if(_esitoZootEsitoAmmBov.get_intervento() == null || _esitoZootEsitoAmmBov.get_intervento().equals("")) {
			_selectInterventoDisable = false;
		} else {
			_selectInterventoDisable = true;
			_esitoZootEsitoAmmBov = _esitoZootecniaEJBBean.getEsitoAmmBovini(_esitoZootStato.get_cuaa(), _esitoZootStato.get_domanda(), _esitoZootStato.get_campagna(), _esitoZootEsitoAmmBov.get_intervento());
			if(_esitoZootEsitoAmmBov.get_esito_controllo() == null || _esitoZootEsitoAmmBov.get_esito_controllo().equals("")) _esitoZootEsitoAmmBov.set_esito_controllo("POSITIVO");
			if(_esitoZootEsitoAmmBov.get_appl_perc_rid_determinata() == null || _esitoZootEsitoAmmBov.get_appl_perc_rid_determinata().equals("")) _esitoZootEsitoAmmBov.set_appl_perc_rid_determinata(Costanti.VAL_NO);
			if(_esitoZootEsitoAmmBov.get_appl_perc_rid_determinata_2x() == null || _esitoZootEsitoAmmBov.get_appl_perc_rid_determinata_2x().equals("")) _esitoZootEsitoAmmBov.set_appl_perc_rid_determinata_2x(Costanti.VAL_NO);
			if(_esitoZootEsitoAmmBov.get_escl_pagamento() == null || _esitoZootEsitoAmmBov.get_escl_pagamento().equals("")) _esitoZootEsitoAmmBov.set_escl_pagamento(Costanti.VAL_NO);
			if(_esitoZootEsitoAmmBov.get_ulteriore_escl_pagamento() == null || _esitoZootEsitoAmmBov.get_ulteriore_escl_pagamento().equals("")) _esitoZootEsitoAmmBov.set_ulteriore_escl_pagamento(Costanti.VAL_NO);
		
		}
	}
	
	/************************************************************************
	 * 				GETTER E SETTER
	 ***********************************************************************/
	
	
    public List<EsitoZootecniaStatoBO> getElencoEsitoZoot()
    {
        return _esitoZootecniaEJBBean.getElencoEsitoZoot();
    }

    public void setElencoEsitoZoot(final ElencoEsitoZootecniaBO elencoEsito)
    {
    	_esitoZootecniaEJBBean.setElencoEsitoZoot(elencoEsito);
    }    
    
    /**
	 * carica i dati degli esiti impegni 
	 */
	public void loadData()
	{
		_esitoZootecniaEJBBean.loadData();
	}

	public void set_selectedValue(EsitoZootecniaStatoBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public EsitoZootecniaStatoBO get_selectedValue() {
		return _selectedValue;
	}

	public void set_action(String _action) {
		this._action = _action;
	}

	public String get_action() {
		return _action;
	}

	public void set_listCampagna(Vector<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	public List<String> get_listCampagna() {
		if(_listCampagna.size()==0)
			_listCampagna = _esitoZootecniaEJBBean.getListCampagna();
		return _listCampagna;
	}
	
	public void set_listEsito(List<String> _listEsito) {
		this._listEsito = _listEsito;
	}

	public List<String> get_listEsito() {
		_listEsito = _esitoZootecniaEJBBean.getListaValori(Costanti.ANAGR_ESITO);
		return _listEsito;
	}

	public void set_titoloPagina(String _titoloPagina) {
		this._titoloPagina = _titoloPagina;
	}

	public String get_titoloPagina() {
		return _titoloPagina;
	}
	
	/**
     * Filtra i dati in base ai parametri di ricerca
     * @param parametersList
     */
    public void filter(HashMap<String, String> parametersList){
    	_esitoZootecniaEJBBean.filter(parametersList);
    }
    
    /**
     * cancella i valori delle selezioni correnti
     */
	public void clearSelectedValue()
	{
		_selectedValue = new EsitoZootecniaStatoBO();
		_esitoZootStato = new EsitoZootecniaStatoBO();
		_esitoZootStatoOld = new EsitoZootecniaStatoBO();
	}
	
	/**
     * cancella i dati visualizzati 
     */
    public void clearData()
    {
    	_esitoZootecniaEJBBean.clearList();
    }

	public void set_messageVisible(boolean _messageVisible) {
		this._messageVisible = _messageVisible;
	}

	public boolean is_messageVisible() {
		return _messageVisible;
	}

	public void set_message(String _message) {
		this._message = _message;
	}

	public String get_message() {
		return _message;
	}

	public void set_readOnly(boolean _readOnly) {
		this._readOnly = _readOnly;
	}

	public boolean is_readOnly() {
		return _readOnly;
	}

	public void set_selectedValueIndex(int _selectedValueIndex) {
		this._selectedValueIndex = _selectedValueIndex;
	}

	public int get_selectedValueIndex() {
		return _selectedValueIndex;
	}

	public void set_esitoZootStato(EsitoZootecniaStatoBO _esitoZootStato) {
		this._esitoZootStato = _esitoZootStato;
	}

	public EsitoZootecniaStatoBO get_esitoZootStato() {
		return _esitoZootStato;
	}

	public void set_esitoZootStatoOld(EsitoZootecniaStatoBO _esitoZootStatoOld) {
		this._esitoZootStatoOld = _esitoZootStatoOld;
	}

	public EsitoZootecniaStatoBO get_esitoZootStatoOld() {
		return _esitoZootStatoOld;
	}


	public void set_esitoZootEsitoAmmBov(EsitoZootecniaEsitoAmmBovBO _esitoZootEsitoAmmBov) {
		this._esitoZootEsitoAmmBov = _esitoZootEsitoAmmBov;
	}


	public EsitoZootecniaEsitoAmmBovBO get_esitoZootEsitoAmmBov() {
		return _esitoZootEsitoAmmBov;
	}


	public void set_listRadioButton(List<SelectItem> _listRadioButton) {
		this._listRadioButton = _listRadioButton;
	}


	public List<SelectItem> get_listRadioButton() {
		return _listRadioButton;
	}


	public void set_esitoZootEsitoAmmOvi(EsitoZootecniaEsitoAmmOviBO _esitoZootEsitoAmmOvi) {
		this._esitoZootEsitoAmmOvi = _esitoZootEsitoAmmOvi;
	}


	public EsitoZootecniaEsitoAmmOviBO get_esitoZootEsitoAmmOvi() {
		return _esitoZootEsitoAmmOvi;
	}


	public void set_esitoZootControlloAmmBov(EsitoZootecniaControlloAmmBovBO _esitoZootControlloAmmBov) {
		this._esitoZootControlloAmmBov = _esitoZootControlloAmmBov;
	}


	public EsitoZootecniaControlloAmmBovBO get_esitoZootControlloAmmBov() {
		return _esitoZootControlloAmmBov;
	}


	public void set_esitoZootControlloAmmOvi(EsitoZootecniaControlloAmmOviBO _esitoZootControlloAmmOvi) {
		this._esitoZootControlloAmmOvi = _esitoZootControlloAmmOvi;
	}


	public EsitoZootecniaControlloAmmOviBO get_esitoZootControlloAmmOvi() {
		return _esitoZootControlloAmmOvi;
	}


	public void set_esitoZootControlloCond(EsitoZootecniaControlloCondBO _esitoZootControlloCond) {
		this._esitoZootControlloCond = _esitoZootControlloCond;
	}


	public EsitoZootecniaControlloCondBO get_esitoZootControlloCond() {
		return _esitoZootControlloCond;
	}
	
	public void set_action_principale(String action_principale) {
		this._action_principale = action_principale;
	}

	public String get_action_principale() {
		return _action_principale;
	}


	public void set_listRuoloPersonaPresente(
			List<String> _listRuoloPersonaPresente) {
		this._listRuoloPersonaPresente = _listRuoloPersonaPresente;
	}


	public List<String> get_listRuoloPersonaPresente() {
		_listRuoloPersonaPresente = _esitoZootecniaEJBBean.getListaValori(Costanti.ANAGR_RUOLO_PERSONA_PRESENTE);
		return _listRuoloPersonaPresente;
	}


	public void set_listStatoEsito(List<String> _listStatoEsito) {
		this._listStatoEsito = _listStatoEsito;
	}


	public List<String> get_listStatoEsito() {
		return _listStatoEsito;
	}


	public List<String> get_listInterventi() {
		return _listInterventi;
	}


	public void set_listInterventi(List<String> _listInterventi) {
		this._listInterventi = _listInterventi;
	}

	public boolean is_selectInterventoDisable() {
		return _selectInterventoDisable;
	}


	public void set_selectInterventoDisable(boolean _selectInterventoDisable) {
		this._selectInterventoDisable = _selectInterventoDisable;
	}
	
	
	
	
}
