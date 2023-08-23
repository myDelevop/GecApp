package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.TutelaAcque;
import it.bz.prov.esiti.util.Utils;

import java.util.Date;


/**
 * Business entity che rappresenta il singolo esito del controllo sulla tutela acque
 * 
 * @author bpettazzoni
 *
 */

public class TutelaAcqueBO {
	
	private String _cuaa;
	private String _campagna;
//	private String _condizionalita;
//	private String _altitudine_azienda;
	private String _punti_altitudine;
	private String _uba_acc;
	private String _uba_acc_psr;
//	private String _carico_bestiame_max_ta;
	private String _carico_alpeggio_psr;
	private String _carico_bestiame_attuale_ta;
	private String _carico_bestiame_attuale_psr;
//	private String _uba_eccessive_ta;
//	private String _tolleranza_car_best_min_psr;
	private String _tolleranza_car_best_max_ta;
	private String _tolleranza_car_best_max_psr;
	private String _capacita_stoccaggi;
	private String _stoccaggi_tenuti_in_funzione;
	private String _dichiarazioni_presenti;
	private String _esito_carico_bestiame_ta;
	private String _esito_carico_bestiame_psr;
	private String _avg_ann_carico_best;
	private String _note;
	private String _stato;
	private String _esito_stoccaggi;
//	private String _secondo_contr_stoccaggio;
//	private Date _data_secondo_contr_stoccaggio;
	private String _user_modifica;
	private Date _data_modifica;
	private String _user_inserimento;
	private Date _data_inserimento;
	
	// TA significa Tutela Acque, perchè il carico bestiame si calcola in modo diverso tra PSR e tutela acque
	
	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
	
	
	/**
	 * costruttore
	 */
	public TutelaAcqueBO(){
		_cuaa = "";
		_capacita_stoccaggi = "";
		_stoccaggi_tenuti_in_funzione = "";
		_dichiarazioni_presenti = "";
		_esito_carico_bestiame_ta = "";
		_esito_carico_bestiame_psr = "";
		_note = "";
		_stato = "";
//		_condizionalita = "";
//		_altitudine_azienda = "";
		_punti_altitudine = "";
		_uba_acc = "";
		_uba_acc_psr = "";
//		_carico_bestiame_max_ta = "";
		_carico_alpeggio_psr = "";
		_carico_bestiame_attuale_ta = "";
		_carico_bestiame_attuale_psr = "";
//		_uba_eccessive_ta = "";
//		_tolleranza_car_best_min_psr = "";
		_tolleranza_car_best_max_ta = "";
		_tolleranza_car_best_max_psr = "";
		_avg_ann_carico_best = "";
		_campagna= "";
		_esito_stoccaggi = "";
//		_secondo_contr_stoccaggio = "";
		_user_modifica = "";
		_user_inserimento = "";
	}
	
	/**
	 * Costruttore con parametri.
	 * Prende in input un oggetto (jpa) e lo trasforma in un business object (BO)
	 * @param tutelaAcque
	 */
	public TutelaAcqueBO(TutelaAcque tutelaAcque){
		_cuaa = tutelaAcque.get_cuaa();
		_capacita_stoccaggi = tutelaAcque.get_capacita_stoccaggi();
		_stoccaggi_tenuti_in_funzione = tutelaAcque.get_stoccaggi_tenuti_in_funzione();
		_dichiarazioni_presenti = tutelaAcque.get_dichiarazioni_presenti();
		_esito_carico_bestiame_ta = tutelaAcque.get_esito_carico_bestiame_ta();
		_esito_carico_bestiame_psr = tutelaAcque.get_esito_carico_bestiame_psr();
		_note = tutelaAcque.get_note();
		_stato = tutelaAcque.get_stato();
//		_condizionalita = tutelaAcque.get_condizionalita();
//		_altitudine_azienda = tutelaAcque.get_altitudine_azienda();
		_punti_altitudine = tutelaAcque.get_punti_altitudine();
		_uba_acc = tutelaAcque.get_uba_acc();
		_uba_acc_psr = tutelaAcque.get_uba_acc_psr();
//		_carico_bestiame_max_ta = tutelaAcque.get_carico_bestiame_max_ta();
		_carico_alpeggio_psr = tutelaAcque.get_carico_alpeggio_psr();
		_carico_bestiame_attuale_ta = tutelaAcque.get_carico_bestiame_attuale_ta();
		_carico_bestiame_attuale_psr = tutelaAcque.get_carico_bestiame_attuale_psr();
//		_uba_eccessive_ta = tutelaAcque.get_uba_eccessive_ta();
//		_tolleranza_car_best_min_psr = tutelaAcque.get_tolleranza_car_best_min_psr();
		_tolleranza_car_best_max_ta = tutelaAcque.get_tolleranza_car_best_max_ta();
		_tolleranza_car_best_max_psr = tutelaAcque.get_tolleranza_car_best_max_psr();
		_avg_ann_carico_best = tutelaAcque.get_avg_ann_carico_best();
		_campagna= tutelaAcque.get_campagna();
		_esito_stoccaggi = tutelaAcque.get_esito_stoccaggi();
//		_secondo_contr_stoccaggio = tutelaAcque.get_secondo_contr_stoccaggio();
//		if(tutelaAcque.get_data_secondo_contr_stoccaggio() != null) _data_secondo_contr_stoccaggio = Utils.getDate(tutelaAcque.get_data_secondo_contr_stoccaggio());
		_user_modifica = tutelaAcque.get_user_modifica();
		_data_modifica = tutelaAcque.get_data_modifica();
		_user_inserimento = tutelaAcque.get_user_inserimento();
		_data_inserimento = tutelaAcque.get_data_inserimento();
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	/**
	 * clona l'oggetto in una nuova istanza
	 * @return TutelaAcqueBO
	 */
	public TutelaAcqueBO clone()
	{
		TutelaAcqueBO tutelaAcque = new TutelaAcqueBO();
//		tutelaAcque.set_altitudine_azienda(_altitudine_azienda);
		tutelaAcque.set_punti_altitudine(_punti_altitudine);
		tutelaAcque.set_campagna(_campagna);
		tutelaAcque.set_capacita_stoccaggi(_capacita_stoccaggi);
		tutelaAcque.set_carico_alpeggio_psr(_carico_alpeggio_psr);
		tutelaAcque.set_carico_bestiame_attuale_psr(_carico_bestiame_attuale_psr);
		tutelaAcque.set_carico_bestiame_attuale_ta(_carico_bestiame_attuale_ta);
//		tutelaAcque.set_carico_bestiame_max_ta(_carico_bestiame_max_ta);
//		tutelaAcque.set_condizionalita(_condizionalita);
		tutelaAcque.set_cuaa(_cuaa);
		tutelaAcque.set_dichiarazioni_presenti(_dichiarazioni_presenti);
		tutelaAcque.set_esito_carico_bestiame_ta(_esito_carico_bestiame_ta);
		tutelaAcque.set_esito_carico_bestiame_psr(_esito_carico_bestiame_psr);
		tutelaAcque.set_note(_note);
		tutelaAcque.set_stato(_stato);
		tutelaAcque.set_stoccaggi_tenuti_in_funzione(_stoccaggi_tenuti_in_funzione);
		tutelaAcque.set_tolleranza_car_best_max_ta(_tolleranza_car_best_max_ta);
		tutelaAcque.set_tolleranza_car_best_max_psr(_tolleranza_car_best_max_psr);
//		tutelaAcque.set_tolleranza_car_best_min_psr(_tolleranza_car_best_min_psr);
		tutelaAcque.set_uba_acc(_uba_acc);
		tutelaAcque.set_uba_acc_psr(_uba_acc_psr);
		tutelaAcque.set_avg_ann_carico_best(_avg_ann_carico_best);
//		tutelaAcque.set_uba_eccessive_ta(_uba_eccessive_ta);
		tutelaAcque.set_esito_stoccaggi(_esito_stoccaggi);
//		tutelaAcque.set_secondo_contr_stoccaggio(_secondo_contr_stoccaggio);
//		tutelaAcque.set_data_secondo_contr_stoccaggio(_data_secondo_contr_stoccaggio);
		tutelaAcque.set_data_modifica(_data_modifica);
		tutelaAcque.set_user_modifica(_user_modifica);
		tutelaAcque.set_data_inserimento(_data_inserimento);
		tutelaAcque.set_user_inserimento(_user_inserimento);
		return tutelaAcque;
	}
	
	/**
	 * fa rollback dell'oggetto con una version precedente
	 * @param tutelaAcque
	 */
	public void recovery(TutelaAcqueBO tutelaAcque)
	{
//		_altitudine_azienda = tutelaAcque.get_altitudine_azienda();
		_punti_altitudine = tutelaAcque.get_punti_altitudine();
		_campagna = tutelaAcque.get_campagna();
		_capacita_stoccaggi = tutelaAcque.get_capacita_stoccaggi();
		_carico_alpeggio_psr = tutelaAcque.get_carico_alpeggio_psr();
		_carico_bestiame_attuale_psr = tutelaAcque.get_carico_bestiame_attuale_psr();
		_carico_bestiame_attuale_ta = tutelaAcque.get_carico_bestiame_attuale_ta();
//		_carico_bestiame_max_ta = tutelaAcque.get_carico_bestiame_attuale_ta();
//		_condizionalita = tutelaAcque.get_condizionalita();
		_cuaa = tutelaAcque.get_cuaa();
		_dichiarazioni_presenti = tutelaAcque.get_dichiarazioni_presenti();
		_esito_carico_bestiame_ta = tutelaAcque.get_esito_carico_bestiame_ta();
		_esito_carico_bestiame_psr = tutelaAcque.get_esito_carico_bestiame_psr();
		_note = tutelaAcque.get_note();
		_stato = tutelaAcque.get_stato();
		_stoccaggi_tenuti_in_funzione = tutelaAcque.get_stoccaggi_tenuti_in_funzione();
		_tolleranza_car_best_max_ta = tutelaAcque.get_tolleranza_car_best_max_ta();
		_tolleranza_car_best_max_psr = tutelaAcque.get_tolleranza_car_best_max_psr();
//		_tolleranza_car_best_min_psr = tutelaAcque.get_tolleranza_car_best_min_psr();
		_uba_acc = tutelaAcque.get_uba_acc();
		_uba_acc_psr = tutelaAcque.get_uba_acc_psr();
		_avg_ann_carico_best = tutelaAcque.get_avg_ann_carico_best();
//		_uba_eccessive_ta = tutelaAcque.get_uba_eccessive_ta();
		_esito_stoccaggi = tutelaAcque.get_esito_stoccaggi();
//		_secondo_contr_stoccaggio = tutelaAcque.get_secondo_contr_stoccaggio();
//		_data_secondo_contr_stoccaggio = tutelaAcque.get_data_secondo_contr_stoccaggio();
		_data_modifica = tutelaAcque.get_data_modifica();
		_user_modifica = tutelaAcque.get_user_modifica();
		_data_inserimento = tutelaAcque.get_data_inserimento();
		_user_inserimento = tutelaAcque.get_user_inserimento();
	}
	
	
	/**
	 * restituisce l'oggetto entity
	 * @return TutelaAcque
	 */
	public TutelaAcque getEntity()
	{
		TutelaAcque tutelaAcque = new TutelaAcque();
//		tutelaAcque.set_altitudine_azienda(_altitudine_azienda);
		tutelaAcque.set_punti_altitudine(_punti_altitudine);
		tutelaAcque.set_campagna(_campagna);
		tutelaAcque.set_capacita_stoccaggi(_capacita_stoccaggi);
		tutelaAcque.set_carico_alpeggio_psr(_carico_alpeggio_psr);
		tutelaAcque.set_carico_bestiame_attuale_psr(_carico_bestiame_attuale_psr);
		tutelaAcque.set_carico_bestiame_attuale_ta(_carico_bestiame_attuale_ta);
//		tutelaAcque.set_carico_bestiame_max_ta(_carico_bestiame_max_ta);
//		tutelaAcque.set_condizionalita(_condizionalita);
		tutelaAcque.set_cuaa(_cuaa);
		tutelaAcque.set_dichiarazioni_presenti(_dichiarazioni_presenti);
		tutelaAcque.set_esito_carico_bestiame_ta(_esito_carico_bestiame_ta);
		tutelaAcque.set_esito_carico_bestiame_psr(_esito_carico_bestiame_psr);
		tutelaAcque.set_note(_note);
		tutelaAcque.set_stato(_stato);
		tutelaAcque.set_stoccaggi_tenuti_in_funzione(_stoccaggi_tenuti_in_funzione);
		tutelaAcque.set_tolleranza_car_best_max_ta(_tolleranza_car_best_max_ta);
		tutelaAcque.set_tolleranza_car_best_max_psr(_tolleranza_car_best_max_psr);
//		tutelaAcque.set_tolleranza_car_best_min_psr(_tolleranza_car_best_min_psr);
		tutelaAcque.set_uba_acc(_uba_acc);
		tutelaAcque.set_uba_acc_psr(_uba_acc_psr);
		tutelaAcque.set_avg_ann_carico_best(_avg_ann_carico_best);
//		tutelaAcque.set_uba_eccessive_ta(_uba_eccessive_ta);
		tutelaAcque.set_esito_stoccaggi(_esito_stoccaggi);
//		tutelaAcque.set_secondo_contr_stoccaggio(_secondo_contr_stoccaggio);
//		tutelaAcque.set_data_secondo_contr_stoccaggio(Utils.getDateString(_data_secondo_contr_stoccaggio));
		tutelaAcque.set_data_inserimento(_data_inserimento);
		tutelaAcque.set_user_inserimento(_user_inserimento);
		tutelaAcque.set_data_modifica(_data_modifica);
		tutelaAcque.set_user_modifica(_user_modifica);
		return tutelaAcque;
	}
	
	
	/**
	 * imposta gli elementi dentro l'oggetto Entity. In questo modo posso fare l'update
	 * @param tutelaAcque
	 */
	public void setEntity(TutelaAcque tutelaAcque)
	{
//		tutelaAcque.set_altitudine_azienda(_altitudine_azienda);
		tutelaAcque.set_punti_altitudine(_punti_altitudine);
		tutelaAcque.set_campagna(_campagna);
		tutelaAcque.set_capacita_stoccaggi(_capacita_stoccaggi);
		tutelaAcque.set_carico_alpeggio_psr(_carico_alpeggio_psr);
		tutelaAcque.set_carico_bestiame_attuale_psr(_carico_bestiame_attuale_psr);
		tutelaAcque.set_carico_bestiame_attuale_ta(_carico_bestiame_attuale_ta);
//		tutelaAcque.set_carico_bestiame_max_ta(_carico_bestiame_max_ta);
//		tutelaAcque.set_condizionalita(_condizionalita);
		tutelaAcque.set_cuaa(_cuaa);
		tutelaAcque.set_dichiarazioni_presenti(_dichiarazioni_presenti);
		tutelaAcque.set_esito_carico_bestiame_ta(_esito_carico_bestiame_ta);
		tutelaAcque.set_esito_carico_bestiame_psr(_esito_carico_bestiame_psr);
		tutelaAcque.set_note(_note);
		tutelaAcque.set_stato(_stato);
		tutelaAcque.set_stoccaggi_tenuti_in_funzione(_stoccaggi_tenuti_in_funzione);
		tutelaAcque.set_tolleranza_car_best_max_ta(_tolleranza_car_best_max_ta);
		tutelaAcque.set_tolleranza_car_best_max_psr(_tolleranza_car_best_max_psr);
//		tutelaAcque.set_tolleranza_car_best_min_psr(_tolleranza_car_best_min_psr);
		tutelaAcque.set_uba_acc(_uba_acc);
		tutelaAcque.set_uba_acc_psr(_uba_acc_psr);
		tutelaAcque.set_avg_ann_carico_best(_avg_ann_carico_best);
//		tutelaAcque.set_uba_eccessive_ta(_uba_eccessive_ta);
		tutelaAcque.set_esito_stoccaggi(_esito_stoccaggi);
//		tutelaAcque.set_secondo_contr_stoccaggio(_secondo_contr_stoccaggio);
//		tutelaAcque.set_data_secondo_contr_stoccaggio(Utils.getDateString(_data_secondo_contr_stoccaggio));
		tutelaAcque.set_data_inserimento(_data_inserimento);
		tutelaAcque.set_user_inserimento(_user_inserimento);
		tutelaAcque.set_data_modifica(_data_modifica);
		tutelaAcque.set_user_modifica(_user_modifica);
	}
	
	
	/************************************************************************
	 * 				GETTER & SETTER
	 ***********************************************************************/

	public void set_cuaa(String _cuaa) {
		if(_cuaa == null) _cuaa = "";
		this._cuaa = _cuaa.trim();
	}

	public String get_cuaa() {
		return _cuaa;
	}

//	public void set_condizionalita(String _condizionalita) {
//		if(_condizionalita == null) _condizionalita = "";
//		this._condizionalita = _condizionalita;
//	}

//	public String get_condizionalita() {
//		return _condizionalita;
//	}

//	public void set_altitudine_azienda(String _altitudine_azienda) {
//		if(_altitudine_azienda == null) _altitudine_azienda = "";
//		this._altitudine_azienda = _altitudine_azienda.replace(".", ",");
//	}

//	public String get_altitudine_azienda() {
//		return _altitudine_azienda;
//	}
	
	public void set_punti_altitudine(String _punti_altitudine) {
		if(_punti_altitudine == null) _punti_altitudine = "";
		this._punti_altitudine = _punti_altitudine.replace(".", ",");
	}

	public String get_punti_altitudine() {
		return _punti_altitudine;
	}

	public void set_uba_acc(String _uba_acc) {
		if(_uba_acc == null) _uba_acc = "";
		this._uba_acc = _uba_acc.replace(".", ",");
	}

	public String get_uba_acc() {
		return _uba_acc;
	}
	
	public void set_uba_acc_psr(String _uba_acc_psr) {
		if(_uba_acc_psr == null) _uba_acc_psr = "";
		this._uba_acc_psr = _uba_acc_psr.replace(".", ",");
	}

	public String get_uba_acc_psr() {
		return _uba_acc_psr;
	}

//	public void set_carico_bestiame_max_ta(String _carico_bestiame_max_ta) {
//		if(_carico_bestiame_max_ta == null) _carico_bestiame_max_ta = "";
//		this._carico_bestiame_max_ta = _carico_bestiame_max_ta.replace(".", ",");
//	}

//	public String get_carico_bestiame_max_ta() {
//		return _carico_bestiame_max_ta;
//	}

	public void set_carico_alpeggio_psr(String _carico_alpeggio_psr) {
		if(_carico_alpeggio_psr == null) _carico_alpeggio_psr = "";
		this._carico_alpeggio_psr = _carico_alpeggio_psr.replace(".", ",");
	}

	public String get_carico_alpeggio_psr() {
		return _carico_alpeggio_psr;
	}

	public void set_carico_bestiame_attuale_ta(String _carico_bestiame_attuale_ta) {
		if(_carico_bestiame_attuale_ta == null) _carico_bestiame_attuale_ta = "";
		this._carico_bestiame_attuale_ta = _carico_bestiame_attuale_ta.replace(".", ",");
	}

	public String get_carico_bestiame_attuale_ta() {
		return _carico_bestiame_attuale_ta;
	}

	public void set_carico_bestiame_attuale_psr(String _carico_bestiame_attuale_psr) {
		if(_carico_bestiame_attuale_psr == null) _carico_bestiame_attuale_psr = "";
		this._carico_bestiame_attuale_psr = _carico_bestiame_attuale_psr.replace(".", ",");
	}

	public String get_carico_bestiame_attuale_psr() {
		return _carico_bestiame_attuale_psr;
	}

//	public void set_uba_eccessive_ta(String _uba_eccessive_ta) {
//		if(_uba_eccessive_ta == null) _uba_eccessive_ta = "";
//		this._uba_eccessive_ta = _uba_eccessive_ta.replace(".", ",");
//	}

//	public String get_uba_eccessive_ta() {
//		return _uba_eccessive_ta;
//	}

//	public void set_tolleranza_car_best_min_psr(String _tolleranza_car_best_min_psr) {
//		if(_tolleranza_car_best_min_psr == null) _tolleranza_car_best_min_psr = "";
//		this._tolleranza_car_best_min_psr = _tolleranza_car_best_min_psr.replace(".", ",");
//	}

//	public String get_tolleranza_car_best_min_psr() {
//		return _tolleranza_car_best_min_psr;
//	}

	public void set_tolleranza_car_best_max_ta(String _tolleranza_car_best_max_ta) {
		if(_tolleranza_car_best_max_ta == null) _tolleranza_car_best_max_ta = "";
		this._tolleranza_car_best_max_ta = _tolleranza_car_best_max_ta.replace(".", ",");
	}

	public String get_tolleranza_car_best_max_ta() {
		return _tolleranza_car_best_max_ta;
	}
	
	public void set_tolleranza_car_best_max_psr(String _tolleranza_car_best_max_psr) {
		if(_tolleranza_car_best_max_psr == null) _tolleranza_car_best_max_psr = "";
		this._tolleranza_car_best_max_psr = _tolleranza_car_best_max_psr.replace(".", ",");
	}

	public String get_tolleranza_car_best_max_psr() {
		return _tolleranza_car_best_max_psr;
	}

	public void set_capacita_stoccaggi(String _capacita_stoccaggi) {
		if(_capacita_stoccaggi == null) _capacita_stoccaggi = "";
		this._capacita_stoccaggi = _capacita_stoccaggi.trim();
	}

	public String get_capacita_stoccaggi() {
		return _capacita_stoccaggi;
	}

	public void set_stoccaggi_tenuti_in_funzione(String _stoccaggi_tenuti_in_funzione) {
		if(_stoccaggi_tenuti_in_funzione == null) _stoccaggi_tenuti_in_funzione = "";
		this._stoccaggi_tenuti_in_funzione = _stoccaggi_tenuti_in_funzione.trim();
	}

	public String get_stoccaggi_tenuti_in_funzione() {
		return _stoccaggi_tenuti_in_funzione;
	}

	public void set_dichiarazioni_presenti(String _dichiarazioni_presenti) {
		if(_dichiarazioni_presenti == null) _dichiarazioni_presenti = "";
		this._dichiarazioni_presenti = _dichiarazioni_presenti.trim();
	}

	public String get_dichiarazioni_presenti() {
		return _dichiarazioni_presenti;
	}

	public void set_esito_carico_bestiame_ta(String _esito_carico_bestiame_ta) {
		if(_esito_carico_bestiame_ta == null) _esito_carico_bestiame_ta = "";
		this._esito_carico_bestiame_ta = _esito_carico_bestiame_ta.trim();
	}

	public String get_esito_carico_bestiame_ta() {
		return _esito_carico_bestiame_ta;
	}
	
	public void set_esito_carico_bestiame_psr(String _esito_carico_bestiame_psr) {
		if(_esito_carico_bestiame_psr == null) _esito_carico_bestiame_psr = "";
		this._esito_carico_bestiame_psr = _esito_carico_bestiame_psr.trim();
	}

	public String get_esito_carico_bestiame_psr() {
		return _esito_carico_bestiame_psr;
	}
	
	public void set_avg_ann_carico_best(String _avg_ann_carico_best) {
		if(_avg_ann_carico_best == null) _avg_ann_carico_best = "";
		this._avg_ann_carico_best = _avg_ann_carico_best.trim();
	}

	public String get_avg_ann_carico_best() {
		return _avg_ann_carico_best;
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

	public void set_campagna(String _campagna) {
		if(_campagna == null) _campagna = "";
		this._campagna = _campagna.trim();
	}

	public String get_campagna() {
		return _campagna;
	}
	
	public void set_esito_stoccaggi(String _esito_stoccaggi){
		this._esito_stoccaggi = _esito_stoccaggi;
	}
	
	public String get_esito_stoccaggi(){
		return this._esito_stoccaggi;
	}
	
//	public void set_secondo_contr_stoccaggio(String _secondo_contr_stoccaggio){
//		this._secondo_contr_stoccaggio = _secondo_contr_stoccaggio;
//	}
	
//	public String get_secondo_contr_stoccaggio(){
//		return this._secondo_contr_stoccaggio;
//	}
	
//	public void set_data_secondo_contr_stoccaggio(Date _data_secondo_contr_stoccaggio){
//		this._data_secondo_contr_stoccaggio = _data_secondo_contr_stoccaggio;
//	}
	
//	public Date get_data_secondo_contr_stoccaggio(){
//		return this._data_secondo_contr_stoccaggio;
//	}
	
//	public String get_data_secondo_contr_stoccaggioStr(){
//		return Utils.getDateString(_data_secondo_contr_stoccaggio);
//	}
	
	public void set_user_modifica(String _user_modifica){
		this._user_modifica = _user_modifica;
	}
	
	public String get_user_modifica(){
		if( _user_modifica == null || _user_modifica.equals("")) return _user_inserimento;
		else return _user_modifica;
	}
	
	public void set_data_modifica(Date _data_modifica){
		this._data_modifica = _data_modifica;
	}
	
	public Date get_data_modifica(){
		return this._data_modifica;
	}
	
	public String get_data_modificaStr(){
		if(_user_modifica == null || _user_modifica.equals("") ) return Utils.getDateString(_data_inserimento);
		else return Utils.getDateString(_data_modifica);
	}

	public void set_user_inserimento(String _user_inserimento) {
		this._user_inserimento = _user_inserimento;
	}

	public String get_user_inserimento() {
		return _user_inserimento;
	}

	public void set_data_inserimento(Date _data_inserimento) {
		this._data_inserimento = _data_inserimento;
	}

	public Date get_data_inserimento() {
		return _data_inserimento;
	}

}
