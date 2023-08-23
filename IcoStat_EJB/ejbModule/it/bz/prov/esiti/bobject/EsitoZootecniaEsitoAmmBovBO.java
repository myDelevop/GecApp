package it.bz.prov.esiti.bobject;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import it.bz.prov.esiti.entity.EsitoZootecniaEsitoAmmBov;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta Esito del controllo di ammissibilità zootecnia per i Bovini
 * 
 * @author bpettazzoni
 *
 */

public class EsitoZootecniaEsitoAmmBovBO {

	private String _cuaa;
	private String _domanda;
	private int _campagna;
	private String _intervento;
	private String _esito_controllo;
	private String _perc_rid_esito_controllo;
	private String _appl_perc_rid_determinata;
	private String _perc_rid_determinata;
	private String _appl_perc_rid_determinata_2x;
	private String _perc_rid_determinata_2x;
	private String _escl_pagamento;
	private String _perc_rid_escl_pagamento;
	private String _ulteriore_escl_pagamento;
	private String _note;
	private Date _dataModifica;
	private String _userModifica;
	private Date _dataInserimento;
	private String _userInserimento;	
	private ArrayList<String> _listNomeCampiEsiAmmBov = new ArrayList<String>(Arrays.asList(
			"ESITO_CONTROLLO", "PERC_RID_ESI_CONT", "APPL_PERC_RID", "PERC_RID_APPL", "APPL_PERC_RID_2X", "PERC_RID_APPL_2X", "ESCL_PAG", "PERC_RID_ESCL_PAG", "ULT_ESCL_PAG", "NOTE", "USER_CREAZIONE", "DATA_CREAZIONE", "USER_MODIFICA", "DATA_MODIFICA"
			));


	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoZootecniaEsitoAmmBovBO(){
		_cuaa = "";
		_domanda = "";
		_intervento = "";                  
		_esito_controllo = "";             
		_perc_rid_esito_controllo = "";    
		_appl_perc_rid_determinata = "";   
		_perc_rid_determinata = "";        
		_appl_perc_rid_determinata_2x = "";
		_perc_rid_determinata_2x = "";     
		_escl_pagamento = "";              
		_perc_rid_escl_pagamento = "";     
		_ulteriore_escl_pagamento = "";    
		_note = "";
		_userInserimento = "";
		_userModifica = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esito
	 */
	public EsitoZootecniaEsitoAmmBovBO(List<EsitoZootecniaEsitoAmmBov> esito){
		_cuaa = esito.get(0).get_cuaa();
		_domanda = esito.get(0).get_domanda();
		_campagna = esito.get(0).get_campagna();
		_intervento = esito.get(0).get_intervento();
		for(EsitoZootecniaEsitoAmmBov r: esito) {
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(0))) _esito_controllo = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(1))) _perc_rid_esito_controllo = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(2))) _appl_perc_rid_determinata = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(3))) _perc_rid_determinata = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(4))) _appl_perc_rid_determinata_2x = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(5))) _perc_rid_determinata_2x = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(6))) _escl_pagamento = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(7))) _perc_rid_escl_pagamento = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(8))) _ulteriore_escl_pagamento = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(9))) _note = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(10))) _userInserimento = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(11))) _dataInserimento = Utils.getDate(r.get_valore());
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(12))) _userModifica = r.get_valore();
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(13))) _dataModifica = Utils.getDate(r.get_valore());
		}
	}
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoZootecniaEsitoAmmBovBO
	 */
	public EsitoZootecniaEsitoAmmBovBO clone()
	{
		EsitoZootecniaEsitoAmmBovBO esito = new EsitoZootecniaEsitoAmmBovBO();
		esito.set_campagna(_campagna);
		esito.set_cuaa(_cuaa);
		esito.set_domanda(_domanda);
		
		return esito;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esito
	 */
	public void recovery(EsitoZootecniaEsitoAmmBovBO esito)
	{
		_campagna = esito.get_campagna();
		_cuaa = esito.get_cuaa();
		_domanda = esito.get_domanda();
		_intervento                   = esito.get_intervento                  ();
		_esito_controllo              = esito.get_esito_controllo             ();
		_perc_rid_esito_controllo     = esito.get_perc_rid_esito_controllo    ();
		_appl_perc_rid_determinata    = esito.get_appl_perc_rid_determinata   ();
		_perc_rid_determinata         = esito.get_perc_rid_determinata        ();
		_appl_perc_rid_determinata_2x = esito.get_appl_perc_rid_determinata_2x();
		_perc_rid_determinata_2x      = esito.get_perc_rid_determinata_2x     ();
		_escl_pagamento               = esito.get_escl_pagamento              ();
		_perc_rid_escl_pagamento      = esito.get_perc_rid_escl_pagamento     ();
		_ulteriore_escl_pagamento     = esito.get_ulteriore_escl_pagamento    ();
		_note = esito.get_note();
		_userInserimento = esito.get_userInserimento();
		_dataInserimento = esito.get_dataInserimento();
		_userModifica = esito.get_userModifica();
		_dataModifica = esito.get_dataModifica();
	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoZootecniaEsitoAmmBov
	 */
	public List<EsitoZootecniaEsitoAmmBov> getEntities()
	{
		ArrayList<EsitoZootecniaEsitoAmmBov> esito = new ArrayList<EsitoZootecniaEsitoAmmBov>();
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(0), _esito_controllo));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(1), _perc_rid_esito_controllo));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(2), _appl_perc_rid_determinata));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(3), _perc_rid_determinata));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(4), _appl_perc_rid_determinata_2x));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(5), _perc_rid_determinata_2x));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(6), _escl_pagamento));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(7), _perc_rid_escl_pagamento));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(8), _ulteriore_escl_pagamento));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(9), _note));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(10), _userInserimento));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(11), get_dataInserimentoStr()));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(12), _userModifica));
		esito.add(new EsitoZootecniaEsitoAmmBov(_cuaa, _campagna, _domanda, _intervento, get_listNomeCampiEsiAmmBov().get(13), get_dataModificaStr()));

		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntities(List<EsitoZootecniaEsitoAmmBov> esito)
	{
		for(EsitoZootecniaEsitoAmmBov r: esito) {
			r.set_cuaa(_cuaa);
			r.set_campagna(_campagna);
			r.set_domanda(_domanda);
			r.set_intervento(_intervento);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(0))) r.set_valore(_esito_controllo);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(1))) r.set_valore(_perc_rid_esito_controllo);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(2))) r.set_valore(_appl_perc_rid_determinata);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(3))) r.set_valore(_perc_rid_determinata);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(4))) r.set_valore(_appl_perc_rid_determinata_2x);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(5))) r.set_valore(_perc_rid_determinata_2x);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(6))) r.set_valore(_escl_pagamento);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(7))) r.set_valore(_perc_rid_escl_pagamento);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(8))) r.set_valore(_ulteriore_escl_pagamento);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(9))) r.set_valore(_note);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(10))) r.set_valore(_userInserimento);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(11))) r.set_valore(Utils.getDateString(_dataInserimento));
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(12))) r.set_valore(_userModifica);
			if(r.get_campo().equals(get_listNomeCampiEsiAmmBov().get(13))) r.set_valore(Utils.getDateString(_dataModifica));
		}
	}
	
	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/
	
	public String get_cuaa(){
		return _cuaa;
	}
	
	public void set_cuaa(String cuaa){
		_cuaa= cuaa.trim();
	}
	
	public String get_domanda(){
		return _domanda;
	}
	
	public void set_domanda(String domanda){
		_domanda= domanda;
	}
	
	public int get_campagna(){
		return _campagna;
	}
	
	public void set_campagna(int campagna){
		_campagna = campagna;
	}
		
	public void set_dataModifica(Date _dataModifica) {
		this._dataModifica = _dataModifica;
	}

	public Date get_dataModifica() {
		if(_userModifica == null || _userModifica.equals("") ) return _dataInserimento;
		else return _dataModifica;
	}
	
	public String get_dataModificaStr() {
		if(_userModifica == null || _userModifica.equals("") ) return Utils.getDateString(_dataInserimento);
		else return Utils.getDateString(_dataModifica);
	}
	
	public void set_userModifica(String _userModifica){
		this._userModifica = _userModifica;
	}
	
	public String get_userModifica(){
		if(_userModifica == null || _userModifica.equals("") ) return _userInserimento;
		else return _userModifica;
	}

	public void set_dataInserimento(Date _dataInserimento) {
		this._dataInserimento = _dataInserimento;
	}

	public Date get_dataInserimento() {
		return _dataInserimento;
	}

	public String get_dataInserimentoStr() {
		return Utils.getDateString(_dataInserimento);
	}
	
	public void set_userInserimento(String _userInserimento) {
		this._userInserimento = _userInserimento;
	}

	public String get_userInserimento() {
		return _userInserimento;
	}

	public void set_note(String _note) {
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

	public String get_intervento() {
		return _intervento;
	}

	public void set_intervento(String _intervento) {
		this._intervento = _intervento;
	}

	public String get_esito_controllo() {
		return _esito_controllo;
	}

	public void set_esito_controllo(String _esito_controllo) {
		this._esito_controllo = _esito_controllo;
	}

	public String get_perc_rid_esito_controllo() {
		return _perc_rid_esito_controllo;
	}

	public void set_perc_rid_esito_controllo(String _perc_rid_esito_controllo) {
		this._perc_rid_esito_controllo = _perc_rid_esito_controllo;
	}

	public String get_appl_perc_rid_determinata() {
		return _appl_perc_rid_determinata;
	}

	public void set_appl_perc_rid_determinata(String _appl_perc_rid_determinata) {
		this._appl_perc_rid_determinata = _appl_perc_rid_determinata;
	}

	public String get_perc_rid_determinata() {
		return _perc_rid_determinata;
	}

	public void set_perc_rid_determinata(String _perc_rid_determinata) {
		this._perc_rid_determinata = _perc_rid_determinata;
	}

	public String get_appl_perc_rid_determinata_2x() {
		return _appl_perc_rid_determinata_2x;
	}

	public void set_appl_perc_rid_determinata_2x(String _appl_perc_rid_determinata_2x) {
		this._appl_perc_rid_determinata_2x = _appl_perc_rid_determinata_2x;
	}

	public String get_perc_rid_determinata_2x() {
		return _perc_rid_determinata_2x;
	}

	public void set_perc_rid_determinata_2x(String _perc_rid_determinata_2x) {
		this._perc_rid_determinata_2x = _perc_rid_determinata_2x;
	}

	public String get_escl_pagamento() {
		return _escl_pagamento;
	}

	public void set_escl_pagamento(String _escl_pagamento) {
		this._escl_pagamento = _escl_pagamento;
	}

	public String get_perc_rid_escl_pagamento() {
		return _perc_rid_escl_pagamento;
	}

	public void set_perc_rid_escl_pagamento(String _perc_rid_escl_pagamento) {
		this._perc_rid_escl_pagamento = _perc_rid_escl_pagamento;
	}

	public String get_ulteriore_escl_pagamento() {
		return _ulteriore_escl_pagamento;
	}

	public void set_ulteriore_escl_pagamento(String _ulteriore_escl_pagamento) {
		this._ulteriore_escl_pagamento = _ulteriore_escl_pagamento;
	}

	public ArrayList<String> get_listNomeCampiEsiAmmBov() {
		return _listNomeCampiEsiAmmBov;
	}
}
