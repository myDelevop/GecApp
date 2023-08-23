package it.bz.prov.esiti.beans;


import it.bz.prov.esiti.bobject.AttoCondizionalitaBO;
import it.bz.prov.esiti.bobject.OperationResultBO;
import it.bz.prov.esiti.bobject.ValoreAnagraficaBO;
import it.bz.prov.esiti.ibusiness.IAnagrafiche;
import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Bean UI (user interface) per la gestione delle anagrafiche
 * 
 * @author bpettazzoni
 *
 */
@ManagedBean(name = "anagraficheBean")
@SessionScoped
public class AnagraficheUIBean {
	@EJB
	private IAnagrafiche _anagraficheEJBBean;
	private String _action;
	private ValoreAnagraficaBO _selectedValue = null;
	private ValoreAnagraficaBO _valoreAnagrafica = null;
	private ValoreAnagraficaBO _valoreAnagraficaOld = null;
	private AttoCondizionalitaBO _selectedValueAtto = null;
	private AttoCondizionalitaBO _atto = null;
	private AttoCondizionalitaBO _attoOld = null;
	private String anagrafica = "";
	private List<ValoreAnagraficaBO> listValori;
	private List<AttoCondizionalitaBO> _listAtto;
	private List<String> _listCampagna;
	private boolean _aggiungiVisible;
	private boolean _modificaVisible;
	private boolean _cancellaVisible;
	private boolean _annullaVisible;
	private boolean _salvaVisible;
	private boolean _messageVisible;
	private String _message;
	private boolean _isSelectedAttiCond; // per sapere se ho selezionato gli atti di condizionalità
	
	/**
	 * costruttore
	 */
	public AnagraficheUIBean()
	{
//		_anagraficheEJBBean = new AnagraficheBean();
		_action="";
		_selectedValue = new ValoreAnagraficaBO(); 
		_valoreAnagrafica = new ValoreAnagraficaBO(); 
		_valoreAnagraficaOld = new ValoreAnagraficaBO(); 
		_selectedValueAtto = new AttoCondizionalitaBO();
		_atto = new AttoCondizionalitaBO();
		_attoOld = new AttoCondizionalitaBO();
		setListValori(new ArrayList<ValoreAnagraficaBO>());
		_listAtto = new ArrayList<AttoCondizionalitaBO>();
		_aggiungiVisible = false;
		_modificaVisible = false;
		_cancellaVisible = false;
		_annullaVisible = false;
		_salvaVisible = false;
		set_message("");
		set_messageVisible(false);
		_isSelectedAttiCond = false;
		_listCampagna = new ArrayList<String>();
	}
	
	/**
	 * visualizza i valori dell'anagrafica selezionata
	 * @return String
	 */
	public void visualizzaValori()
	{
		if(anagrafica.equals(Costanti.ANAGR_COD_ATTI_COND))
		{
			_listAtto = _anagraficheEJBBean.visualizzaCodiciAttiCond();
			_isSelectedAttiCond = true;
		}
		else
		{
			listValori = _anagraficheEJBBean.visualizzaValori(anagrafica);
			_isSelectedAttiCond = false;
		}
		_action = Costanti.ACTION_VIEW;
		_aggiungiVisible = true;
		_modificaVisible = true;
		_cancellaVisible = true;
		_annullaVisible = false;
		_salvaVisible = false;
	}
	
	/**
	 * aggiunge un valore alla lista
	 */
	public void aggiungiValore()
	{	
		if(_isSelectedAttiCond)
		{
			_atto = new AttoCondizionalitaBO();
			_selectedValueAtto = _atto;
			_listAtto.add(0, _atto);
		}
		else
		{
			_valoreAnagrafica = new ValoreAnagraficaBO();
			_valoreAnagrafica.set_campo(anagrafica);
			_valoreAnagrafica.set_valore("");
			_valoreAnagrafica.set_campagnaInizioVal(Utils.currentYear());
			_valoreAnagrafica.set_campagnaFineVal(Costanti.CAMPAGNA_FINE_VAL);
			_selectedValue = _valoreAnagrafica;
			listValori.add(_valoreAnagrafica);
		}
		_action = Costanti.ACTION_INSERT;
		// setting della visualizzazione dei bottoni
		_aggiungiVisible = false;
		_modificaVisible = false;
		_cancellaVisible = false;
		_annullaVisible = true;
		_salvaVisible = true;
	}
	
	/**
	 * modifica un valore nella lista
	 */
	public void modificaValore()
	{
		if(_isSelectedAttiCond)
		{
			// se non é stato selezionato nulla
			if(_selectedValueAtto == null || _selectedValueAtto.get_codCond().equals("")) 
			{
				_messageVisible=true; 
				_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
				return;
			}
			_atto = _selectedValueAtto;
			_attoOld = _atto.clone();
		}
		else
		{
			// se non é stato selezionato nulla
			if(_selectedValue == null || _selectedValue.get_campo().equals("")) 
			{
				_messageVisible=true; 
				_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
				return;
			}			
			_valoreAnagrafica = _selectedValue;
			_valoreAnagraficaOld = _valoreAnagrafica.clone();
		}
		_action = Costanti.ACTION_MODIFY;
		// setting visualizzazione bottoni
		_aggiungiVisible = false;
		_modificaVisible = false;
		_cancellaVisible = false;
		_annullaVisible = true;
		_salvaVisible = true;
	}
	
	
	/**
	 * cancella un valore nella lista
	 */
	public void cancellaValore()
	{
		OperationResultBO result = null;
		if(_isSelectedAttiCond)
		{
			// se non é stato selezionato nulla
			if(_selectedValueAtto == null || _selectedValueAtto.get_codCond().equals("")) 
			{
				_messageVisible=true; 
				_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
				return;
			}
			_atto = _selectedValueAtto;
			result = _anagraficheEJBBean.cancellaAttoCond(_atto);
		}
		else
		{
			// se non é stato selezionato nulla
			if(_selectedValue == null || _selectedValue.get_campo().equals("")) 
			{
				_messageVisible=true; 
				_message=Costanti.MSG_NO_RIGA_SELEZIONATA; 
				return;
			}
			_valoreAnagrafica = _selectedValue;
			result = _anagraficheEJBBean.cancellaValore(_valoreAnagrafica);
		}
		// se l'esito é positivo resetto i valori di selezione
		if(result.get_result()) clearSelectedValue();
		// visualizzo il messaggio a video 
		_messageVisible = true;
		_message = result.get_message();
		_action = Costanti.ACTION_VIEW;
		// aggiornamento della lista dei valori da visualizzare
		if(_isSelectedAttiCond)
			_listAtto = _anagraficheEJBBean.visualizzaCodiciAttiCond();
		else 
			listValori = _anagraficheEJBBean.visualizzaValori(anagrafica);
	}
	
	
	/**
	 * salva su database le operazioni effettuate
	 */
	public void salva()
	{
		OperationResultBO result = null;
		if(_isSelectedAttiCond)
		{
			if(_action.equals(Costanti.ACTION_INSERT))  
				result = _anagraficheEJBBean.aggiungiAttoCond(_atto);
			else if(_action.equals(Costanti.ACTION_MODIFY))  
				result = _anagraficheEJBBean.modificaAttoCond(_atto, _attoOld);
		}
		else
		{
			if(_action.equals(Costanti.ACTION_INSERT))  
				result = _anagraficheEJBBean.aggiungiValore(_valoreAnagrafica);
			else if(_action.equals(Costanti.ACTION_MODIFY))  
				result = _anagraficheEJBBean.modificaValore(_valoreAnagrafica, _valoreAnagraficaOld);
		}		
		//imposto il messaggio da visualizzare a video
		_message = result.get_message();
		_messageVisible = true;
		// operazione eseguita con successo
		if(result.get_result())
		{
			_action = Costanti.ACTION_VIEW;
			//pulisce i valori selezionati
			clearSelectedValue();
			// setting della visualizzazione dei bottoni
			_aggiungiVisible = true;
			_modificaVisible = true;
			_cancellaVisible = true;
			_annullaVisible = false;
			_salvaVisible = false;
			// aggiornamento dei dati da visualizzare
			if(_isSelectedAttiCond)
				_listAtto = _anagraficheEJBBean.visualizzaCodiciAttiCond();
			else 
				listValori = _anagraficheEJBBean.visualizzaValori(anagrafica);
		}
	}
	
	
	/**
	 * annulla l'operazione in corso
	 */
	public void annulla()
	{
		if(_isSelectedAttiCond)
		{
			if(_action.equals(Costanti.ACTION_INSERT))
				_listAtto.remove(_atto);
			else if(_action.equals(Costanti.ACTION_MODIFY))
				if(_attoOld != null) _atto.recovery(_attoOld);
		}
		else 
		{
			if(_action.equals(Costanti.ACTION_INSERT))
				listValori.remove(_valoreAnagrafica);
			else if(_action.equals(Costanti.ACTION_MODIFY))
				if(_valoreAnagraficaOld != null) _valoreAnagrafica.recovery(_valoreAnagraficaOld);
		}
		
		clearSelectedValue();
		_action = Costanti.ACTION_VIEW;
		// setting della visualizzazione dei bottoni
		_aggiungiVisible = true;
		_modificaVisible = true;
		_cancellaVisible = true;
		_annullaVisible = false;
		_salvaVisible = false;
	}
	
	/**
	 * resetta i valori memorizzati
	 */
	public void clearSelectedValue()
	{
		if(_isSelectedAttiCond)
		{
			_selectedValueAtto = new AttoCondizionalitaBO();
			_atto = new AttoCondizionalitaBO();
			_attoOld = new AttoCondizionalitaBO();
		}
		else 
		{
			_selectedValue = new ValoreAnagraficaBO();
			_valoreAnagrafica = new ValoreAnagraficaBO();
			_valoreAnagraficaOld = new ValoreAnagraficaBO();
		}
	}

	public void setListAnagrafiche(final List<String> listAnagrafiche) {
		_anagraficheEJBBean.setListAnagrafiche(listAnagrafiche);
	}

	public List<String> getListAnagrafiche() {
		return _anagraficheEJBBean.getListAnagrafiche();
	}

	public void setAnagrafica(final String anagrafica) {
		this.anagrafica = anagrafica;
	}

	public String getAnagrafica() {
		return this.anagrafica;
	}

	public void setListValori(List<ValoreAnagraficaBO> listValori) {
		this.listValori = listValori;
	}

	public List<ValoreAnagraficaBO> getListValori() {
		return listValori;
	}

	public void set_selectedValue(ValoreAnagraficaBO _selectedValue) {
		this._selectedValue = _selectedValue;
	}

	public ValoreAnagraficaBO get_selectedValue() {
		return _selectedValue;
	}

	public void set_action(String _action) {
		this._action = _action;
	}

	public String get_action() {
		return _action;
	}

	public void set_aggiungiVisible(boolean _aggiungiVisible) {
		this._aggiungiVisible = _aggiungiVisible;
	}

	public boolean is_aggiungiVisible() {
		return _aggiungiVisible;
	}

	public void set_modificaVisible(boolean _modificaVisible) {
		this._modificaVisible = _modificaVisible;
	}

	public boolean is_modificaVisible() {
		return _modificaVisible;
	}

	public void set_cancellaVisible(boolean _cancellaVisible) {
		this._cancellaVisible = _cancellaVisible;
	}

	public boolean is_cancellaVisible() {
		return _cancellaVisible;
	}

	public void set_annullaVisible(boolean _annullaVisible) {
		this._annullaVisible = _annullaVisible;
	}

	public boolean is_annullaVisible() {
		return _annullaVisible;
	}

	public void set_salvaVisible(boolean _salvaVisible) {
		this._salvaVisible = _salvaVisible;
	}

	public boolean is_salvaVisible() {
		return _salvaVisible;
	}

	public void set_valoreAnagrafica(ValoreAnagraficaBO _valoreAnagrafica) {
		this._valoreAnagrafica = _valoreAnagrafica;
	}

	public ValoreAnagraficaBO get_valoreAnagrafica() {
		return _valoreAnagrafica;
	}

	public void set_valoreAnagraficaOld(ValoreAnagraficaBO _valoreAnagraficaOld) {
		this._valoreAnagraficaOld = _valoreAnagraficaOld;
	}

	public ValoreAnagraficaBO get_valoreAnagraficaOld() {
		return _valoreAnagraficaOld;
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

	public void set_listAtto(List<AttoCondizionalitaBO> _listAtto) {
		this._listAtto = _listAtto;
	}

	public List<AttoCondizionalitaBO> get_listAtto() {
		return _listAtto;
	}

	public void set_isSelectedAttiCond(boolean _isSelectedAttiCond) {
		this._isSelectedAttiCond = _isSelectedAttiCond;
	}

	public boolean is_isSelectedAttiCond() {
		return _isSelectedAttiCond;
	}

	public void set_selectedValueAtto(AttoCondizionalitaBO _selectedValueAtto) {
		this._selectedValueAtto = _selectedValueAtto;
	}

	public AttoCondizionalitaBO get_selectedValueAtto() {
		return _selectedValueAtto;
	}

	public void set_atto(AttoCondizionalitaBO _atto) {
		this._atto = _atto;
	}

	public AttoCondizionalitaBO get_atto() {
		return _atto;
	}

	public void set_attoOld(AttoCondizionalitaBO _attoOld) {
		this._attoOld = _attoOld;
	}

	public AttoCondizionalitaBO get_attoOld() {
		return _attoOld;
	}

	public void set_listCampagna(List<String> _listCampagna) {
		this._listCampagna = _listCampagna;
	}

	public List<String> get_listCampagna() {
		_listCampagna = _anagraficheEJBBean.getListCampagna();
		return _listCampagna;
	}

	
	
}
