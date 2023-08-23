package it.bz.prov.esiti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity che rappresenta l'esito del controllo sulla tutela acque
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(TutelaAcquePk.class)
@Table(name = "OPP_ESITI_APP_TUTELA_ACQUE")
@NamedQueries({
		@NamedQuery(
				name = "TutelaAcque.selectAll",
				query = "SELECT ta FROM TutelaAcque ta ORDER BY ta._cuaa, ta._campagna"),
		@NamedQuery(
				name = "TutelaAcque.selectFilter",
				query = "SELECT DISTINCT t FROM TutelaAcque t, Azienda a, CampioneAggr c " +
				"WHERE t._cuaa=a._cuaa AND t._cuaa=c._cuaa AND t._campagna=c._campagna " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(t._cuaa) LIKE lower(:cuaa) " +
				"AND t._campagna LIKE :campagna " + 
				"AND c._misura LIKE :misura " +
				"AND c._domandaOpr LIKE :domanda " +
				"AND (c._flagCampione is null OR c._flagCampione LIKE :campione) " +
				"AND (c._flagExtraCampione is null OR c._flagExtraCampione LIKE :extraCampione) " +
				"ORDER BY t._cuaa, t._campagna"),
		@NamedQuery(
				name = "TutelaAcque.selectFilterOnlyAzienda",
				query = "SELECT DISTINCT t FROM TutelaAcque t, Azienda a " +
				"WHERE t._cuaa=a._cuaa " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(t._cuaa) LIKE lower(:cuaa) " +
				"AND t._campagna LIKE :campagna " + 
				"ORDER BY t._cuaa, t._campagna"),
})
public class TutelaAcque implements Serializable {

	private static final long serialVersionUID = -1790025547160252507L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
//	@Column(name = "CONDIZIONALITA")
//	private String _condizionalita;

//	@Column(name = "ALTITUDINE_AZIENDA")
//	private String _altitudine_azienda;
	
	@Column(name = "PUNTI_ALTITUDINE")
	private String _punti_altitudine;
	
	@Column(name = "UBA_ACCERTATE_TA")
	private String _uba_acc;
	
	@Column(name = "UBA_ACCERTATE_PSR")
	private String _uba_acc_psr;
	
//	@Column(name = "CARICO_BESTIAME_MASS_TA")
//	private String _carico_bestiame_max_ta;
	
	@Column(name = "CARICO_ALPEGGIO_PSR")
	private String _carico_alpeggio_psr;
	
	@Column(name = "CARICO_BESTIAME_ATTUALE_TA")
	private String _carico_bestiame_attuale_ta;
	
	@Column(name = "CARICO_BESTIAME_ATTUALE_PSR")
	private String _carico_bestiame_attuale_psr;
	
//	@Column(name = "UBA_ECCESSIVE_TA")
//	private String _uba_eccessive_ta;
	
//	@Column(name = "TOLLERANZA_CAR_BEST_MIN_PSR")
//	private String _tolleranza_car_best_min_psr;
	
	@Column(name = "TOLLERANZA_CAR_BEST_MASS_TA")
	private String _tolleranza_car_best_max_ta;
	
	@Column(name = "TOLLERANZA_CAR_BEST_MASS_PSR")
	private String _tolleranza_car_best_max_psr;
	
	@Column(name = "CAPACITA_STOCCAGGI")
	private String _capacita_stoccaggi;
	
	@Column(name = "STOCCAGGI_FUNZIONE_TENUTI")
	private String _stoccaggi_tenuti_in_funzione;
	
	@Column(name = "ESITO_STOCCAGGI")
	private String _esito_stoccaggi;
	
//	@Column(name = "SECONDO_CONTR_STOCCAGGIO")
//	private String _secondo_contr_stoccaggio;
	
//	@Column(name = "DATA_SECONDO_CONTR_STOCCAGGIO")
//	private String _data_secondo_contr_stoccaggio;
	
	@Column(name = "PRESENTE_DICHIARAZIONI")
	private String _dichiarazioni_presenti;
	
	@Column(name = "ESITO_CARICO_BESTIAME_TA")
	private String _esito_carico_bestiame_ta;
	
	@Column(name = "ESITO_CARICO_BESTIAME_PSR")
	private String _esito_carico_bestiame_psr;
	
	@Column(name = "AVG_CARICO_BESTIAME")
	private String _avg_ann_carico_best;
	
	@Column(name = "NOTE")
	private String _note;
	
	@Column(name = "STATO")
	private String _stato;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _data_inserimento;
	
	@Column(name = "USER_INSERIMENTO")
	private String _user_inserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;
	
	@Column(name = "USER_MODIFICA")
	private String _user_modifica;
	
	// TA significa Tutela Acque, perchè il carico bestiame si calcola in modo diverso tra PSR e tutela acque
	
	
	/**
	 * costruttore
	 */
	public TutelaAcque(){
		set_cuaa("");
		set_capacita_stoccaggi("");
		set_stoccaggi_tenuti_in_funzione("");
		set_dichiarazioni_presenti("");
		set_esito_carico_bestiame_ta("");
		set_esito_carico_bestiame_psr("");
		set_note("");
		set_stato("");
		_esito_stoccaggi = "";
//		_secondo_contr_stoccaggio = "";
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
		_user_inserimento = "";
		_user_modifica = "";
	}

	public void set_cuaa(String _cuaa) {
		_cuaa = _cuaa == null ? "" : _cuaa;
		this._cuaa = _cuaa;
	}

	public String get_cuaa() {
		return _cuaa;
	}

//	public void set_condizionalita(String _condizionalita) {
//		this._condizionalita = _condizionalita;
//	}

//	public String get_condizionalita() {
//		return _condizionalita;
//	}
//
//	public void set_altitudine_azienda(String _altitudine_azienda) {
//		this._altitudine_azienda = _altitudine_azienda;
//	}
//
//	public String get_altitudine_azienda() {
////		if(_altitudine_azienda != null && !_altitudine_azienda.equals(""))
////			return "" + Utils.getNumber(_altitudine_azienda);
////		else return _altitudine_azienda;
//		return _altitudine_azienda;
//	}
	
	public void set_punti_altitudine(String _punti_altitudine) {
		_punti_altitudine = _punti_altitudine == null ? "" : _punti_altitudine;
		this._punti_altitudine = _punti_altitudine;
	}

	public String get_punti_altitudine() {
		return _punti_altitudine;
	}

	public void set_uba_acc(String _uba_acc) {
		_uba_acc = _uba_acc == null ? "" : _uba_acc;
		this._uba_acc = _uba_acc;
	}

	public String get_uba_acc() {
//		if(_uba_acc != null && !_uba_acc.equals(""))
//			return Utils.roundNumber(Float.parseFloat(_uba_acc));
//		else return _uba_acc;
		return _uba_acc;
	}

	public void set_uba_acc_psr(String _uba_acc_psr) {
		_uba_acc_psr = _uba_acc_psr == null ? "" : _uba_acc_psr;
		this._uba_acc_psr = _uba_acc_psr;
	}

	public String get_uba_acc_psr() {
		return _uba_acc_psr;
	}

//	public void set_carico_bestiame_max_ta(String _carico_bestiame_max_ta) {
//		this._carico_bestiame_max_ta = _carico_bestiame_max_ta;
//	}
//
//	public String get_carico_bestiame_max_ta() {
////		if(_carico_bestiame_max_ta != null && !_carico_bestiame_max_ta.equals(""))
////			return Utils.roundNumber(Float.parseFloat(_carico_bestiame_max_ta));
////		else return _carico_bestiame_max_ta;
//		return _carico_bestiame_max_ta;
//	}

	public void set_carico_alpeggio_psr(String _carico_alpeggio_psr) {
		_carico_alpeggio_psr = _carico_alpeggio_psr == null ? "" : _carico_alpeggio_psr;
		this._carico_alpeggio_psr = _carico_alpeggio_psr;
	}

	public String get_carico_alpeggio_psr() {
//		if(_carico_alpeggio_psr != null && !_carico_alpeggio_psr.equals(""))
//			return Utils.roundNumber(Float.parseFloat(_carico_alpeggio_psr));
//		else return _carico_alpeggio_psr;
		return _carico_alpeggio_psr;
	}

	public void set_carico_bestiame_attuale_ta(String _carico_bestiame_attuale_ta) {
		_carico_bestiame_attuale_ta = _carico_bestiame_attuale_ta == null ? "" : _carico_bestiame_attuale_ta;
		this._carico_bestiame_attuale_ta = _carico_bestiame_attuale_ta;
	}

	public String get_carico_bestiame_attuale_ta() {
//		if(_carico_bestiame_attuale_ta != null && !_carico_bestiame_attuale_ta.equals(""))
//			return Utils.roundNumber(Float.parseFloat(_carico_bestiame_attuale_ta));
//		else return _carico_bestiame_attuale_ta;
		return _carico_bestiame_attuale_ta;
	}

	public void set_carico_bestiame_attuale_psr(String _carico_bestiame_attuale_psr) {
		_carico_bestiame_attuale_psr = _carico_bestiame_attuale_psr == null ? "" : _carico_bestiame_attuale_psr;
		this._carico_bestiame_attuale_psr = _carico_bestiame_attuale_psr;
	}

	public String get_carico_bestiame_attuale_psr() {
//		if(_carico_bestiame_attuale_psr != null && !_carico_bestiame_attuale_psr.equals(""))
//			return Utils.roundNumber(Float.parseFloat(_carico_bestiame_attuale_psr));
//		else return _carico_bestiame_attuale_psr;
		return _carico_bestiame_attuale_psr;
	}

//	public void set_uba_eccessive_ta(String _uba_eccessive_ta) {
//		this._uba_eccessive_ta = _uba_eccessive_ta;
//	}
//
//	public String get_uba_eccessive_ta() {
////		if(_uba_eccessive_ta != null && !_uba_eccessive_ta.equals(""))
////			return Utils.roundNumber(Float.parseFloat(_uba_eccessive_ta)); 
////		else return _uba_eccessive_ta;
//		return _uba_eccessive_ta;
//	}

//	public void set_tolleranza_car_best_min_psr(String _tolleranza_car_best_min_psr) {
//		this._tolleranza_car_best_min_psr = _tolleranza_car_best_min_psr;
//	}
//
//	public String get_tolleranza_car_best_min_psr() {
////		if(_tolleranza_car_best_min_psr != null && !_tolleranza_car_best_min_psr.equals(""))
////			return Utils.roundNumber(Float.parseFloat(_tolleranza_car_best_min_psr));
////		else return _tolleranza_car_best_min_psr;
//		return _tolleranza_car_best_min_psr;
//	}

	public void set_tolleranza_car_best_max_ta(String _tolleranza_car_best_max_ta) {
		_tolleranza_car_best_max_ta = _tolleranza_car_best_max_ta == null ? "" : _tolleranza_car_best_max_ta;
		this._tolleranza_car_best_max_ta = _tolleranza_car_best_max_ta;
	}

	public String get_tolleranza_car_best_max_ta() {
		return _tolleranza_car_best_max_ta;
	}
	
	public void set_tolleranza_car_best_max_psr(String _tolleranza_car_best_max_psr) {
		_tolleranza_car_best_max_psr = _tolleranza_car_best_max_psr == null ? "" : _tolleranza_car_best_max_psr;
		this._tolleranza_car_best_max_psr = _tolleranza_car_best_max_psr;
	}

	public String get_tolleranza_car_best_max_psr() {
//		if(_tolleranza_car_best_max_psr != null && !_tolleranza_car_best_max_psr.equals(""))
//			return Utils.roundNumber(Float.parseFloat(_tolleranza_car_best_max_psr));
//		else return _tolleranza_car_best_max_psr;
		return _tolleranza_car_best_max_psr;
	}

	public void set_capacita_stoccaggi(String _capacita_stoccaggi) {
		_capacita_stoccaggi = _capacita_stoccaggi == null ? "" : _capacita_stoccaggi;
		this._capacita_stoccaggi = _capacita_stoccaggi;
	}

	public String get_capacita_stoccaggi() {
		return _capacita_stoccaggi;
	}

	public void set_stoccaggi_tenuti_in_funzione(String _stoccaggi_tenuti_in_funzione) {
		_stoccaggi_tenuti_in_funzione = _stoccaggi_tenuti_in_funzione == null ? "" : _stoccaggi_tenuti_in_funzione;
		this._stoccaggi_tenuti_in_funzione = _stoccaggi_tenuti_in_funzione;
	}

	public String get_stoccaggi_tenuti_in_funzione() {
		return _stoccaggi_tenuti_in_funzione;
	}

	public void set_dichiarazioni_presenti(String _dichiarazioni_presenti) {
		_dichiarazioni_presenti = _dichiarazioni_presenti == null ? "" : _dichiarazioni_presenti;
		this._dichiarazioni_presenti = _dichiarazioni_presenti;
	}

	public String get_dichiarazioni_presenti() {
		return _dichiarazioni_presenti;
	}

	public void set_esito_carico_bestiame_ta(String _esito_carico_bestiame_ta) {
		_esito_carico_bestiame_ta = _esito_carico_bestiame_ta == null ? "" : _esito_carico_bestiame_ta;
		this._esito_carico_bestiame_ta = _esito_carico_bestiame_ta;
	}

	public String get_esito_carico_bestiame_ta() {
		return _esito_carico_bestiame_ta;
	}
	
	public void set_esito_carico_bestiame_psr(String _esito_carico_bestiame_psr) {
		_esito_carico_bestiame_psr = _esito_carico_bestiame_psr == null ? "" : _esito_carico_bestiame_psr;
		this._esito_carico_bestiame_psr = _esito_carico_bestiame_psr;
	}

	public String get_esito_carico_bestiame_psr() {
		return _esito_carico_bestiame_psr;
	}
	
	public void set_avg_ann_carico_best(String _avg_ann_carico_best) {
		_avg_ann_carico_best = _avg_ann_carico_best == null ? "" : _avg_ann_carico_best;
		this._avg_ann_carico_best = _avg_ann_carico_best;
	}

	public String get_avg_ann_carico_best() {
		return _avg_ann_carico_best;
	}

	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}

	public String get_note() {
		return _note;
	}

	public void set_stato(String _stato) {
		_stato = _stato == null ? "" : _stato;
		this._stato = _stato;
	}

	public String get_stato() {
		return _stato;
	}

	public void set_campagna(String _campagna) {
		_campagna = _campagna == null ? "" : _campagna;
		this._campagna = _campagna;
	}

	public String get_campagna() {
		return _campagna;
	}

	public void set_data_inserimento(Date _data_inserimento) {
		this._data_inserimento = _data_inserimento;
	}

	public Date get_data_inserimento() {
		return _data_inserimento;
	}

	public void set_user_inserimento(String _user_inserimento) {
		_user_inserimento = _user_inserimento == null ? "" : _user_inserimento;
		this._user_inserimento = _user_inserimento;
	}

	public String get_user_inserimento() {
		return _user_inserimento;
	}

	public void set_data_modifica(Date _data_modifica) {
		this._data_modifica = _data_modifica;
	}

	public Date get_data_modifica() {
		return _data_modifica;
	}

	public void set_user_modifica(String _user_modifica) {
		_user_modifica = _user_modifica == null ? "" : _user_modifica;
		this._user_modifica = _user_modifica;
	}

	public String get_user_modifica() {
		return _user_modifica;
	}
	
	public void set_esito_stoccaggi(String _esito_stoccaggi){
		_esito_stoccaggi = _esito_stoccaggi == null ? "" : _esito_stoccaggi;
		this._esito_stoccaggi = _esito_stoccaggi;
	}
	
	public String get_esito_stoccaggi(){
		return this._esito_stoccaggi;
	}
	
//	public void set_secondo_contr_stoccaggio(String _secondo_contr_stoccaggio){
//		this._secondo_contr_stoccaggio = _secondo_contr_stoccaggio;
//	}
//
//	public String get_secondo_contr_stoccaggio(){
//		return this._secondo_contr_stoccaggio;
//	}
//	
//	public void set_data_secondo_contr_stoccaggio(String _data_secondo_contr_stoccaggio){
//		this._data_secondo_contr_stoccaggio = _data_secondo_contr_stoccaggio;
//	}
//	
//	public String get_data_secondo_contr_stoccaggio(){
//		return this._data_secondo_contr_stoccaggio;
//	}


}
