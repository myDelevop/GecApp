package it.bz.prov.esiti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity che rappresenta l'azienda 
 * 
 * @author bpettazzoni
 *
 */


@Entity
@Table(name = "OPP_ESITI_APP_AZIENDA")
@NamedQueries({
		@NamedQuery(
				name = "Azienda.selectAll",
				query = "SELECT c FROM Azienda c ORDER BY c._cuaa"),
		@NamedQuery(
				name = "Azienda.selectFilter",
				query = "SELECT DISTINCT a FROM Azienda a, CampioneAggr b " +
						"WHERE a._cuaa=b._cuaa " + 
						"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
						"AND lower(a._cuaa) LIKE lower(:cuaa) " +
						"AND (b._misura is null OR b._misura LIKE :misura) " +
						"AND (b._domandaOpr is null OR b._domandaOpr LIKE :domanda) " +
						"AND (b._campagna is null OR b._campagna LIKE :campagna) " +
						"AND (b._flagCampione is null OR b._flagCampione LIKE :campione) " +
						"AND (b._flagExtraCampione is null OR b._flagExtraCampione LIKE :extraCampione) " +
						"ORDER BY a._cuaa " ),
		@NamedQuery(
				name = "Azienda.selectFilterOnlyAzienda",
				query = "SELECT DISTINCT a FROM Azienda a " +
						"WHERE lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
						"AND lower(a._cuaa) LIKE lower(:cuaa) " +
						"ORDER BY a._cuaa " ),
		@NamedQuery(
				name = "Azienda.selectFilterAziendaDomanda",
				query = "SELECT DISTINCT a FROM Azienda a, Domanda b " +
						"WHERE a._cuaa=b._cuaa " + 
						"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
						"AND lower(a._cuaa) LIKE lower(:cuaa) " +
						"AND (b._misura is null OR b._misura LIKE :misura) " +
						"AND (b._idDomanda is null OR b._idDomanda LIKE :domanda) " +
						"AND (b._campagna is null OR b._campagna LIKE :campagna) " +
						"ORDER BY a._cuaa " ),
		@NamedQuery(
				name = "Azienda.selectForCampagna",
				query = "SELECT DISTINCT c._cuaa FROM Azienda c, Domanda d " +
						"WHERE c._cuaa=d._cuaa " +
						"AND d._campagna= :campagna " + 
						"ORDER BY c._cuaa"),
		@NamedQuery(
				name = "Azienda.distinctCuaa",
				query = "SELECT DISTINCT c._cuaa " +
						"FROM Azienda c " +
						"WHERE SUBSTRING(c._cuaa, 1, :subqueryLength) = :startWith"),

		@NamedQuery(
				name = "Azienda.selectByPartialCUAA",
				query = "SELECT c FROM Azienda c WHERE LOWER(c._cuaa) LIKE LOWER(:partialCUAA)"),
		@NamedQuery(
				name = "Azienda.selectByPartialDenominazione",
				query = "SELECT c FROM Azienda c WHERE LOWER(c._ragioneSociale) LIKE LOWER(:partialDenominazione)")
})
public class Azienda implements Serializable {
	
	private static final long serialVersionUID = 2501893656950839138L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Column(name = "RAGIONE_SOCIALE")
	private String _ragioneSociale;

	@Column(name = "PROVINCIA")
	private String _provincia;
	
	@Column(name = "LOCALITA")
	private String _localita;
	
	@Column(name = "SORGENTE")
	private String _sorgente;
	
	@Column(name = "USER_INSERIMENTO")
	private String _userInserimento;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _dataInserimento;
	
	@Column(name = "USER_MODIFICA")
	private String _userModifica;
	
	@Column(name = "DATA_MODIFICA")
	private Date _dataModifica;
	
	@Column(name = "INDIRIZZO")
	private String _indirizzo;

	@Column(name = "CAP")
	private String _cap;

	@Column(name = "PEC")
	private String _pec;

//	@OneToMany(mappedBy="_azienda")
//	private Collection<Domanda> _domande = new ArrayList<Domanda>();
	
//	@OneToMany
//	@JoinColumn(name = "CUAA")
//	private List<Campione> _campioni;
	
//	@OneToMany(mappedBy="_azienda")
//	private List<TutelaAcque> _listTutelaAcque;
	
	public Azienda()
	{
		_cuaa = "";
		_ragioneSociale = "";
		_sorgente = "";
		_provincia = "";
		_localita = "";
		_userInserimento = "";
		_userModifica = "";
		_indirizzo = "";
		_cap = "";
		_pec = "";
	}
	
	public Azienda(String cuaa, String ragioneSociale){
		cuaa = cuaa == null ? "" : cuaa;
		ragioneSociale = ragioneSociale == null ? "" : ragioneSociale;
		this._cuaa = cuaa;
		this._ragioneSociale = ragioneSociale;
	}

	public synchronized String get_cuaa() {
		return _cuaa;
	}

	public synchronized String get_ragioneSociale() {
		return _ragioneSociale;
	}
	
	public synchronized void set_cuaa(String _cuaa) {
		_cuaa = _cuaa == null ? "" : _cuaa;
		this._cuaa = _cuaa;
	}

	public synchronized void set_ragioneSociale(String _ragioneSociale) {
		_ragioneSociale = _ragioneSociale == null ? "" : _ragioneSociale;
		this._ragioneSociale = _ragioneSociale;
	}
	
	public synchronized String get_sorgente() {
		return _sorgente;
	}
	
	public synchronized void set_sorgente(String sorgente) {
		sorgente = sorgente == null ? "" : sorgente;
		this._sorgente = sorgente;
	}

	public void set_provincia(String _provincia) {
		_provincia = _provincia == null ? "" : _provincia;
		this._provincia = _provincia;
	}

	public String get_provincia() {
		return _provincia;
	}

	public void set_userInserimento(String _userInserimento) {
		_userInserimento = _userInserimento == null ? "" : _userInserimento;
		this._userInserimento = _userInserimento;
	}

	public String get_userInserimento() {
		return _userInserimento;
	}

	public void set_userModifica(String _userModifica) {
		_userModifica = _userModifica == null ? "" : _userModifica;
		this._userModifica = _userModifica;
	}

	public String get_userModifica() {
		return _userModifica;
	}

	public void set_dataModifica(Date _dataModifica) {
		this._dataModifica = _dataModifica;
	}

	public Date get_dataModifica() {
		return _dataModifica;
	}

	public void set_dataInserimento(Date _dataInserimento) {
		this._dataInserimento = _dataInserimento;
	}

	public Date get_dataInserimento() {
		return _dataInserimento;
	}

	public void set_localita(String _localita) {
		_localita = _localita == null ? "" : _localita;
		this._localita = _localita;
	}

	public String get_localita() {
		return _localita;
	}


	public String get_indirizzo() {
		return _indirizzo;
	}

	public void set_indirizzo(String _indirizzo) {
		this._indirizzo = _indirizzo;
	}

	public String get_cap() {
		return _cap;
	}

	public void set_cap(String _cap) {
		this._cap = _cap;
	}

	public String get_pec() {
		return _pec;
	}

	public void set_pec(String _pec) {
		this._pec = _pec;
	}

//	public void set_domande(Collection<Domanda> _domande) {
//		this._domande = _domande;
//	}
//
//	public Collection<Domanda> get_domande() {
//		return _domande;
//	}

//	public void set_campioni(List<Campione> _campioni) {
//		this._campioni = _campioni;
//	}
//
//	public List<Campione> get_campioni() {
//		return _campioni;
//	}

//	public void set_listTutelaAcque(List<TutelaAcque> _listTutelaAcque) {
//		this._listTutelaAcque = _listTutelaAcque;
//	}
//
//	public List<TutelaAcque> get_listTutelaAcque() {
//		return _listTutelaAcque;
//	}


	
}
