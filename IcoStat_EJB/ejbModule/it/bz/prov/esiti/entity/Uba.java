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
 * Entity che rappresenta l'esito sul controllo delle uba
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(UbaPk.class)
@Table(name = "OPP_ESITI_APP_UBA")
@NamedQueries({
		@NamedQuery(
				name = "UBA.selectAll",
				query = "SELECT u FROM Uba u ORDER BY u._cuaa, u._campagna, u._domanda"),
		@NamedQuery(
				name = "UBA.selectFilter",
				query = "SELECT DISTINCT u FROM Uba u, Azienda a, CampioneAggr c " +
				"WHERE u._cuaa=a._cuaa AND u._domanda=c._domandaOpr AND c._campagna=u._campagna " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(u._cuaa) LIKE lower(:cuaa) " +
				"AND u._campagna LIKE :campagna " +
				"AND c._misura LIKE :misura " +
				"AND c._domandaOpr LIKE :domanda " +
				"AND (c._flagCampione is null OR c._flagCampione LIKE :campione) " +
				"AND (c._flagExtraCampione is null OR c._flagExtraCampione LIKE :extraCampione) " +
				"ORDER BY u._cuaa, u._campagna, u._domanda"),
})
public class Uba implements Serializable {
	
	private static final long serialVersionUID = -6157109528086693789L;

//	@Id
//	@ManyToOne
//	@JoinColumn(name = "DOMANDA_OPR", referencedColumnName = "DOMANDA_OPR")
//	private Domanda _domanda;
	
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Id
	@Column(name = "DOMANDA_OPR")
	private String _domanda;
	
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Id
	@Column(name = "INTERVENTO")
	private String _intervento;
	
	@Column(name = "ESITO")
	private String _esito;
	
	@Column(name = "UBA_RICHIESTI")
	private Float _ubaRich;
	
	@Column(name = "UBA_ACCERTATI")
	private Float _ubaAcc;
	
	@Column(name = "DIFFERENZA")
	private Float _ubaDiff;
	
	@Column(name = "CAPI_RICHIESTI")
	private Float _capiRich;
	
	@Column(name = "CAPI_ACCERTATI")
	private Float _capiAcc;
	
	@Column(name = "NOTE")
	private String _note;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _data_inserimento;
	
	@Column(name = "USER_INSERIMENTO")
	private String _user_inserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;
	
	@Column(name = "USER_MODIFICA")
	private String _user_modifica;
	
	/**
	 * costruttore
	 */
	public Uba(){
		_cuaa = "";
		_domanda = "";
		_intervento = "";
		_esito = "";
		_note = "";
		_user_inserimento = "";
		_user_modifica = "";
	}
	
	public void set_cuaa(String cuaa){
		cuaa = cuaa == null ? "" : cuaa;
		_cuaa = cuaa;
	}
	
	public String get_cuaa(){
		return _cuaa ;
	}
	
	public void set_domanda(String domanda){
		domanda = domanda == null ? "" : domanda;
		_domanda = domanda;
	}
	
	public String get_domanda(){
		return _domanda ;
	}
	
	public void set_campagna(int campagna){
		_campagna = "" + campagna;
	}
	
	public String get_campagna(){
		return "" + _campagna ;
	}
	
	public void set_intervento(String intervento){
		intervento = intervento == null ? "" : intervento;
		_intervento=intervento;
	}
	
	public String get_intervento(){
		return _intervento ;
	}
	
	public void set_note(String note){
		note = note == null ? "" : note;
		_note = note;
	}
	
	public String get_note(){
		return _note ;
	}


	public void set_esito(String _esito) {
		_esito = _esito == null ? "" : _esito;
		this._esito = _esito;
	}


	public String get_esito() {
		return _esito;
	}


	public void set_ubaRich(Float _ubaRich) {
		this._ubaRich = _ubaRich;
	}

	public String get_ubaRich() {
		String value = "";
		if(_ubaRich != null) value = "" + _ubaRich;
		return value;
	}


	public void set_ubaAcc(Float _ubaAcc) {
		this._ubaAcc = _ubaAcc;
	}


	public String get_ubaAcc() {
		String value = "";
		if(_ubaAcc != null) value = "" + _ubaAcc;
		return value;
	}


	public void set_ubaDiff(Float _ubaDiff) {
		this._ubaDiff = _ubaDiff;
	}


	public String get_ubaDiff() {
		String value = "";
		if(_ubaDiff != null) value = "" + _ubaDiff;
		return value;
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

	public void set_capiRich(Float _capiRich) {
		this._capiRich = _capiRich;
	}

	public String get_capiRich() {
		String value = "";
		if(_capiRich != null) value = "" + _capiRich;
		return value;
	}

	public void set_capiAcc(Float _capiAcc) {
		this._capiAcc = _capiAcc;
	}

	public String get_capiAcc() {
		String value = "";
		if(_capiAcc != null) value = "" + _capiAcc;
		return value;
	}
	
}

