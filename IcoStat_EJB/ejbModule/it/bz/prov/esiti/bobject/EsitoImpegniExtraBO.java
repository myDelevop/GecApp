package it.bz.prov.esiti.bobject;


import java.util.Date;

import it.bz.prov.esiti.entity.EsitoImpegniExtra;
import it.bz.prov.esiti.util.Utils;

/**
 * Business entity che rappresenta il singolo esito del controllo impegni
 * 
 * @author bpettazzoni
 *
 */

public class EsitoImpegniExtraBO {

	private String _cuaa;
	private String _domanda;
	private String _campagna;
	private String _intervento;
	private String _sottointervento;
	private String _impegno;
	private String _esito;
	private String _percRid;
	private String _stato;
	private String _esitoRinunciaInsilato;
	private String _importoRidRinunciaInsilato;
	private String _esclusione;
	private String _esclusioneNote;
	private String _reiterazione;
	private String _progr_accert_reiteraz;
	private String _inadempienza_grave;
	private String _portata;
	private String _gravita;
	private String _durata;
	private String _segnalazione;
	private String _approvazione;
	private String _portata_note;
	private String _gravita_note;
	private String _durata_note;
	private String _numero_decreto;
	private Date _data_decreto;
	private Date _data_inserimento;
	private String _user_inserimento;
	private Date _data_modifica;
	private String _user_modifica;
	private String _note;
	private Date _data_controllo;


	/*****************************************************************
	 * 		COSTRUTTORI
	 *****************************************************************/
	
	/**
	 * Costruttore
	 */
	public EsitoImpegniExtraBO(){
		_cuaa = "";
		_domanda = "";
		_campagna = "";
		_intervento = "";
		_sottointervento = "";
		_impegno = "";
		_esito = "";
		_percRid = ""; 
		_stato = ""; 
		_esitoRinunciaInsilato = ""; 
		_importoRidRinunciaInsilato = ""; 
		_esclusione = ""; 
		_esclusioneNote = ""; 
		_reiterazione = ""; 
		_progr_accert_reiteraz = ""; 
		_inadempienza_grave = "";
		_portata = ""; 
		_gravita = ""; 
		_durata = ""; 
		_segnalazione = ""; 
		_approvazione = ""; 
		_portata_note = ""; 
		_gravita_note = ""; 
		_durata_note = ""; 
		_numero_decreto = ""; 
		_data_decreto = null; 
		_data_inserimento = null; 
		_user_inserimento = ""; 
		_data_modifica = null; 
		_user_modifica = ""; 
		_note = ""; 
		_data_controllo = null;
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param esitoImpegni
	 */
	public EsitoImpegniExtraBO(EsitoImpegniExtra esitoImpegni){
		
		_cuaa                         = esitoImpegni.get_cuaa                          ();
		_domanda                      = esitoImpegni.get_domanda                       ();
		_campagna                     = esitoImpegni.get_campagna                      ();
		_intervento                   = esitoImpegni.get_intervento                    ();
		_sottointervento              = esitoImpegni.get_sottointervento               ();
		_impegno                      = esitoImpegni.get_impegno                       ();
		_esito                        = esitoImpegni.get_esito                         ();
		_percRid                      = esitoImpegni.get_percRidStr                    ();
		_stato                        = esitoImpegni.get_stato                         ();
		_esitoRinunciaInsilato        = esitoImpegni.get_esitoRinunciaInsilato         ();
		_importoRidRinunciaInsilato   = esitoImpegni.get_importoRidRinunciaInsilatoStr ();
		_esclusione                   = esitoImpegni.get_esclusione                    ();
		_esclusioneNote               = esitoImpegni.get_esclusioneNote                ();
		_reiterazione                 = esitoImpegni.get_reiterazione                  ();
		_progr_accert_reiteraz        = esitoImpegni.get_progr_accert_reiteraz         ();
		_inadempienza_grave 		  = esitoImpegni.get_inadempienza_grave 		   ();
		_portata                      = esitoImpegni.get_portata                       ();
		_gravita                      = esitoImpegni.get_gravita                       ();
		_durata                       = esitoImpegni.get_durata                        ();
		_segnalazione                 = esitoImpegni.get_segnalazione                  ();
		_approvazione                 = esitoImpegni.get_approvazione                  ();
		_portata_note                 = esitoImpegni.get_portata_note                  ();
		_gravita_note                 = esitoImpegni.get_gravita_note                  ();
		_durata_note                  = esitoImpegni.get_durata_note                   ();
		_numero_decreto               = esitoImpegni.get_numero_decreto                ();
		_data_decreto                 = esitoImpegni.get_data_decreto                  ();
		_data_inserimento             = esitoImpegni.get_data_inserimento              ();
		_user_inserimento             = esitoImpegni.get_user_inserimento              ();
		_data_modifica                = esitoImpegni.get_data_modifica                 ();
		_user_modifica                = esitoImpegni.get_user_modifica                 ();
		_note                         = esitoImpegni.get_note                          ();
		_data_controllo				  = esitoImpegni.get_data_controllo				   ();
	}                                                                    
	
	/*****************************************************************
	 * 		UTILITY
	 *****************************************************************/
	
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return EsitoImpegniBO
	 */
	public EsitoImpegniExtraBO clone()
	{
		EsitoImpegniExtraBO esitoImpegni = new EsitoImpegniExtraBO();
		esitoImpegni.set_cuaa                      (_cuaa                        );
		esitoImpegni.set_domanda                   (_domanda                     );
		esitoImpegni.set_campagna                  (_campagna                    );
		esitoImpegni.set_intervento                (_intervento                  );
		esitoImpegni.set_sottointervento           (_sottointervento             );
		esitoImpegni.set_impegno 				   (_impegno 		             );
		esitoImpegni.set_esito                     (_esito                       );
		esitoImpegni.set_percRid                   (_percRid                     );
		esitoImpegni.set_stato                     (_stato                       );
		esitoImpegni.set_esitoRinunciaInsilato     (_esitoRinunciaInsilato       );
		esitoImpegni.set_importoRidRinunciaInsilato(_importoRidRinunciaInsilato  );
		esitoImpegni.set_esclusione                (_esclusione                  );
		esitoImpegni.set_esclusioneNote            (_esclusioneNote              );
		esitoImpegni.set_reiterazione              (_reiterazione                );
		esitoImpegni.set_progr_accert_reiteraz     (_progr_accert_reiteraz       );
		esitoImpegni.set_inadempienza_grave		   (_inadempienza_grave);
		esitoImpegni.set_portata                   (_portata                     );
		esitoImpegni.set_gravita                   (_gravita                     );
		esitoImpegni.set_durata                    (_durata                      );
		esitoImpegni.set_segnalazione              (_segnalazione                );
		esitoImpegni.set_approvazione              (_approvazione                );
		esitoImpegni.set_portata_note              (_portata_note                );
		esitoImpegni.set_gravita_note              (_gravita_note                );
		esitoImpegni.set_durata_note               (_durata_note                 );
		esitoImpegni.set_numero_decreto            (_numero_decreto              );
		esitoImpegni.set_data_decreto              (_data_decreto                );
		esitoImpegni.set_data_inserimento          (_data_inserimento            );
		esitoImpegni.set_user_inserimento          (_user_inserimento            );
		esitoImpegni.set_data_modifica             (_data_modifica               );
		esitoImpegni.set_user_modifica             (_user_modifica               );
		esitoImpegni.set_note                      (_note                        );
		esitoImpegni.set_data_controllo			   (_data_controllo				 );
		return esitoImpegni;                       
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param esitoImpegni
	 */
	public void recovery(EsitoImpegniExtraBO esitoImpegni)
	{
		_cuaa                        = esitoImpegni.get_cuaa                          ();
		_domanda                     = esitoImpegni.get_domanda                       ();
		_campagna                    = esitoImpegni.get_campagna                      ();
		_intervento                  = esitoImpegni.get_intervento                    ();
		_sottointervento             = esitoImpegni.get_sottointervento               ();		
		_impegno            		 = esitoImpegni.get_impegno						  ();
		_esito                       = esitoImpegni.get_esito                         ();
		_percRid                     = esitoImpegni.get_percRid                       ();
		_stato                       = esitoImpegni.get_stato                         ();
		_esitoRinunciaInsilato       = esitoImpegni.get_esitoRinunciaInsilato         ();
		_importoRidRinunciaInsilato  = esitoImpegni.get_importoRidRinunciaInsilato    ();
		_esclusione                  = esitoImpegni.get_esclusione                    ();
		_esclusioneNote              = esitoImpegni.get_esclusioneNote                ();
		_reiterazione                = esitoImpegni.get_reiterazione                  ();
		_progr_accert_reiteraz       = esitoImpegni.get_progr_accert_reiteraz         ();
		_inadempienza_grave			 = esitoImpegni.get_inadempienza_grave			  ();
		_portata                     = esitoImpegni.get_portata                       ();
		_gravita                     = esitoImpegni.get_gravita                       ();
		_durata                      = esitoImpegni.get_durata                        ();
		_segnalazione                = esitoImpegni.get_segnalazione                  ();
		_approvazione                = esitoImpegni.get_approvazione                  ();
		_portata_note                = esitoImpegni.get_portata_note                  ();
		_gravita_note                = esitoImpegni.get_gravita_note                  ();
		_durata_note                 = esitoImpegni.get_durata_note                   ();
		_numero_decreto              = esitoImpegni.get_numero_decreto                ();
		_data_decreto                = esitoImpegni.get_data_decreto                  ();
		_data_inserimento            = esitoImpegni.get_data_inserimento              ();
		_user_inserimento            = esitoImpegni.get_user_inserimento              ();
		_data_modifica               = esitoImpegni.get_data_modifica                 ();
		_user_modifica               = esitoImpegni.get_user_modifica                 ();
		_note                        = esitoImpegni.get_note                          ();
		_data_controllo				  = esitoImpegni.get_data_controllo				  ();

	}
	
	/**
	 * restituisce l'oggetto entity
	 * @return EsitoImpegni
	 */
	public EsitoImpegniExtra getEntity()
	{
		EsitoImpegniExtra esito = new EsitoImpegniExtra();	                                                                 
		esito.set_cuaa                       (_cuaa                      );
		esito.set_domanda                    (_domanda                   );
		esito.set_campagna                   (Integer.parseInt(_campagna));
		esito.set_intervento                 (_intervento                );
		esito.set_sottointervento            (_sottointervento           );
		esito.set_impegno            		 (_impegno					 );
		esito.set_esito                      (_esito                     );
		if(!_percRid.equals(""))
			esito.set_percRid                    (Float.parseFloat(_percRid.replace(',', '.')));
		esito.set_stato                      (_stato                     );
		esito.set_esitoRinunciaInsilato      (_esitoRinunciaInsilato     );
		if(!_importoRidRinunciaInsilato.equals(""))
			esito.set_importoRidRinunciaInsilato (Float.parseFloat(_importoRidRinunciaInsilato.replace(",", ".")));
		esito.set_esclusione                 (_esclusione                );
		esito.set_esclusioneNote             (_esclusioneNote            );
		esito.set_reiterazione               (_reiterazione              );
		esito.set_progr_accert_reiteraz      (_progr_accert_reiteraz     );
		esito.set_inadempienza_grave		 (_inadempienza_grave		 );
		esito.set_portata                    (_portata                   );
		esito.set_gravita                    (_gravita                   );
		esito.set_durata                     (_durata                    );
		esito.set_segnalazione               (_segnalazione              );
		esito.set_approvazione               (_approvazione              );
		esito.set_portata_note               (_portata_note              );
		esito.set_gravita_note               (_gravita_note              );
		esito.set_durata_note                (_durata_note               );
		esito.set_numero_decreto             (_numero_decreto            );
		esito.set_data_decreto               (_data_decreto              );
		esito.set_data_inserimento           (_data_inserimento          );
		esito.set_user_inserimento           (_user_inserimento          );
		esito.set_data_modifica              (_data_modifica             );
		esito.set_user_modifica              (_user_modifica             );
		esito.set_note                       (_note                      );
		esito.set_data_controllo			 (_data_controllo			 );
		
		return esito;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param esito
	 */
	public void setEntity(EsitoImpegniExtra esito)
	{
		esito.set_cuaa                      (_cuaa                      );
		esito.set_domanda                   (_domanda                   );
		esito.set_campagna                  (Integer.parseInt(_campagna));
		esito.set_intervento                (_intervento                );
		esito.set_sottointervento           (_sottointervento           );
		esito.set_impegno         			(_impegno					);
		esito.set_esito                     (_esito                     );
		if(!_percRid.equals(""))
			esito.set_percRid                    (Float.parseFloat(_percRid.replace(',', '.')));
		esito.set_stato                     (_stato                     );
		esito.set_esitoRinunciaInsilato     (_esitoRinunciaInsilato     );
		if(!_importoRidRinunciaInsilato.equals(""))
			esito.set_importoRidRinunciaInsilato(Float.parseFloat(_importoRidRinunciaInsilato.replace(",", ".")));
		else 
			esito.set_importoRidRinunciaInsilato(null);
		esito.set_esclusione                (_esclusione                );
		esito.set_esclusioneNote            (_esclusioneNote            );
		esito.set_reiterazione              (_reiterazione              );
		esito.set_progr_accert_reiteraz     (_progr_accert_reiteraz     );
		esito.set_inadempienza_grave		(_inadempienza_grave		);
		esito.set_portata                   (_portata                   );
		esito.set_gravita                   (_gravita                   );
		esito.set_durata                    (_durata                    );
		esito.set_segnalazione              (_segnalazione              );
		esito.set_approvazione              (_approvazione              );
		esito.set_portata_note              (_portata_note              );
		esito.set_gravita_note              (_gravita_note              );
		esito.set_durata_note               (_durata_note               );
		esito.set_numero_decreto            (_numero_decreto            );
		esito.set_data_decreto              (_data_decreto              );
		esito.set_data_inserimento          (_data_inserimento          );
		esito.set_user_inserimento          (_user_inserimento          );
		esito.set_data_modifica             (_data_modifica             );
		esito.set_user_modifica             (_user_modifica             );
		esito.set_note                      (_note                      );
		esito.set_data_controllo			(_data_controllo			);

		
	}
	
	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/

	public String get_intervento(){
		return _intervento;
	}
	
	public void set_intervento(String intervento){
		if(intervento == null) intervento = "";
		_intervento = intervento.trim();
	}
	
	public String get_sottointervento(){
		return _sottointervento;
	}
	
	public void set_sottointervento(String sottoIntervento){
		if(sottoIntervento == null) sottoIntervento = "";
		_sottointervento = sottoIntervento.trim();
	}
	
	public String get_impegno() {
		return _impegno;
	}

	public void set_impegno(String impegno) {
		if(impegno == null) impegno = "";
		this._impegno = impegno.trim();
	}

	public String get_esito(){
		return _esito;
	}
	
	public void set_esito(String esito){
		if(esito == null) esito = "";
		_esito = esito.trim();
	}
	
	public String get_percRid(){
		return _percRid;
	}
	
	public void set_percRid(String percRid){
		if(percRid == null) percRid = "";
		_percRid = percRid.replace(",", ".");
	}
	
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
	
	public String get_campagna(){
		return "" + _campagna;
		
	}
	
	public void set_campagna(String campagna){
		if(campagna == null) campagna = "";
		_campagna = campagna.trim();
	}

	public void set_note(String _note) {
		if(_note == null) _note = "";
		this._note = _note.trim();
	}

	public String get_note() {
		return _note;
	}

	public void set_stato(String _stato) {
		if(_stato == null) _stato = "";
		this._stato = _stato.trim();
	}

	public String get_stato() {
		return _stato;
	}
	
	public void set_data_modifica(Date _data_modifica) {
		this._data_modifica = _data_modifica;
	}

	public Date get_data_modifica() {
		if(_user_modifica == null || _user_modifica.equals("") ) return _data_inserimento;
		else return _data_modifica;
	}
	
	public String get_data_modificaStr() {
		if(_user_modifica == null || _user_modifica.equals("") ) return Utils.getDateString(_data_inserimento);
		else return Utils.getDateString(_data_modifica);
	}
	
	public void set_user_modifica(String _user_modifica){
		this._user_modifica = _user_modifica;
	}
	
	public String get_user_modifica(){
		if(_user_modifica == null || _user_modifica.equals("") ) return _user_inserimento;
		else return _user_modifica;
	}

	public void set_data_inserimento(Date _data_inserimento) {
		this._data_inserimento = _data_inserimento;
	}

	public Date get_data_inserimento() {
		return _data_inserimento;
	}

	public void set_user_inserimento(String _user_inserimento) {
		this._user_inserimento = _user_inserimento;
	}

	public String get_user_inserimento() {
		return _user_inserimento;
	}

	public void set_esitoRinunciaInsilato(String _esitoRinunciaInsilato) {
		this._esitoRinunciaInsilato = _esitoRinunciaInsilato;
	}

	public String get_esitoRinunciaInsilato() {
		return _esitoRinunciaInsilato;
	}

	public void set_importoRidRinunciaInsilato(String _importoRidRinunciaInsilato) {
		this._importoRidRinunciaInsilato = _importoRidRinunciaInsilato;
	}

	public String get_importoRidRinunciaInsilato() {
		return _importoRidRinunciaInsilato;
	}

	public String get_segnalazione() {
		return _segnalazione;
	}

	public void set_segnalazione(String _segnalazione) {
		this._segnalazione = _segnalazione;
	}

	public String get_esclusione() {
		return _esclusione;
	}

	public void set_esclusione(String _esclusione) {
		this._esclusione = _esclusione;
	}

	public String get_esclusioneNote() {
		return _esclusioneNote;
	}

	public void set_esclusioneNote(String _esclusioneNote) {
		this._esclusioneNote = _esclusioneNote;
	}
	
	public String get_reiterazione() {
		return _reiterazione;
	}

	public void set_reiterazione(String _reiterazione) {
		this._reiterazione = _reiterazione;
	}

	public String get_progr_accert_reiteraz() {
		return _progr_accert_reiteraz;
	}

	public void set_progr_accert_reiteraz(String _progr_accert_reiteraz) {
		if(_progr_accert_reiteraz == null || _progr_accert_reiteraz.equalsIgnoreCase("on"))
			_progr_accert_reiteraz="";
		this._progr_accert_reiteraz = _progr_accert_reiteraz;
	}

	public String get_inadempienza_grave() {
		return _inadempienza_grave;
	}

	public void set_inadempienza_grave(String _inadempienza_grave) {
		this._inadempienza_grave = _inadempienza_grave;
	}

	public String get_portata() {
		return _portata;
	}

	public void set_portata(String _portata) {
		if(_portata == null || _portata.equalsIgnoreCase("on"))
			_portata="";
		this._portata = _portata;
	}

	public String get_gravita() {
		return _gravita;
	}

	public void set_gravita(String _gravita) {
		if(_gravita == null || _gravita.equalsIgnoreCase("on"))
			_gravita="";
		this._gravita = _gravita;
	}

	public String get_durata() {
		return _durata;
	}

	public void set_durata(String _durata) {
		if(_durata == null || _durata.equalsIgnoreCase("on"))
			_durata="";
		this._durata = _durata;
	}

	public String get_approvazione() {
		return _approvazione;
	}

	public void set_approvazione(String _approvazione) {
		this._approvazione = _approvazione;
	}

	public String get_portata_note() {
		return _portata_note;
	}

	public void set_portata_note(String _portata_note) {
		this._portata_note = _portata_note;
	}

	public String get_gravita_note() {
		return _gravita_note;
	}

	public void set_gravita_note(String _gravita_note) {
		this._gravita_note = _gravita_note;
	}

	public String get_durata_note() {
		return _durata_note;
	}

	public void set_durata_note(String _durata_note) {
		this._durata_note = _durata_note;
	}

	public String get_numero_decreto() {
		return _numero_decreto;
	}

	public void set_numero_decreto(String _numero_decreto) {
		this._numero_decreto = _numero_decreto;
	}
	
	public Date get_data_decreto() {
		return _data_decreto;
	}

	public void set_data_decreto(Date _data_decreto) {
		this._data_decreto = _data_decreto;
	}
	
	public String get_data_decretoString(){
		return Utils.getDateString(_data_decreto);
	}

	public Date get_data_controllo() {
		return _data_controllo;
	}

	public void set_data_controllo(Date _data_controllo) {
		this._data_controllo = _data_controllo;
	}
	
	public String get_data_controlloStr() {
		return Utils.getDateString(_data_controllo);
	}
	
}
