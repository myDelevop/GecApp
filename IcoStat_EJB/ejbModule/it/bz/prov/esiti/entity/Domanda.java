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
 * Entity che rappresenta la singola domanda
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(DomandaPk.class)
@Table(name = "OPP_ESITI_APP_DOMANDA")
@NamedQueries({
		@NamedQuery(
				name = "Domanda.selectAll",
				query = "SELECT d FROM Domanda d ORDER BY d._cuaa, d._campagna, d._idDomanda"),
				
		@NamedQuery(
				name = "Domanda.selectFilter",
				query = "SELECT d FROM Domanda d, Azienda a, CampioneAggr c " +
				"WHERE d._cuaa=a._cuaa AND d._idDomanda=c._domandaOpr AND d._misura=c._misura " + 
				"AND lower(a._ragioneSociale) LIKE LOWER(:ragione_sociale) " +
				"AND a._cuaa LIKE :cuaa " +
				"AND d._misura LIKE :misura " +
				"AND d._idDomanda LIKE :domanda " +
				"AND d._campagna LIKE :campagna " +
				"AND (c._flagCampione is null OR c._flagCampione LIKE :campione) " +
				"AND (c._flagExtraCampione is null OR c._flagExtraCampione LIKE :extraCampione) " +
				"ORDER BY a._cuaa, d._campagna, d._misura, d._idDomanda"),
		@NamedQuery(
				name = "Domanda.selectFilterNoCampione",
				query = "SELECT d FROM Domanda d, Azienda a " +
				"WHERE d._cuaa=a._cuaa " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(a._cuaa) LIKE lower(:cuaa) " +
				"AND d._misura LIKE :misura " +
				"AND d._idDomanda LIKE :domanda " +
				"AND d._campagna LIKE :campagna " +
				"ORDER BY a._cuaa, d._campagna, d._misura, d._idDomanda"),
		@NamedQuery(
				name = "Domanda.distinctCampagna",
				query = "SELECT DISTINCT d._campagna FROM Domanda d ORDER BY d._campagna " ),
		@NamedQuery(
				name = "Domanda.distinctMisura",
				query = "SELECT DISTINCT d._misura FROM Domanda d ORDER BY d._misura " ),
		@NamedQuery(
				name = "Domanda.hasIntervento",
				query = "SELECT DISTINCT d " +
						"FROM Domanda d, Sottointervento s " +
						"WHERE d._idDomanda=s._domanda " +
						"AND s._codiceIntervento= :intervento"),
		@NamedQuery(
				name = "Domanda.hasInterventoSottointervento",
				query = "SELECT DISTINCT d " +
						"FROM Domanda d, Sottointervento s " +
						"WHERE d._idDomanda=s._domanda " +
						"AND s._codiceIntervento= :intervento " +
						"AND s._codiceSottointervento= :sottointervento "),
		@NamedQuery(
				name = "Domanda.has214",
				query = "SELECT DISTINCT d " +
						"FROM Domanda d " +
						"WHERE d._campagna= :campagna " +
						"AND d._cuaa= :cuaa " +
						"AND d._misura='214'"),
		@NamedQuery(
				name = "Domanda.domandeForCuaa",
				query = "SELECT DISTINCT d._idDomanda " +
						"FROM Domanda d " +
						"WHERE d._campagna= :campagna " +
						"AND d._cuaa= :cuaa"),
		@NamedQuery(
				name = "Domanda.domandeForCuaaPSR",
				query = "SELECT DISTINCT d._idDomanda " +
						"FROM Domanda d " +
						"WHERE d._campagna= :campagna " +
						"AND d._cuaa= :cuaa " +
						"AND d._misura <> 'DU'"),
		@NamedQuery(
				name = "Domanda.domandaForCuaaCampagnaMisura",
				query = "SELECT DISTINCT d " +
						"FROM Domanda d " +
						"WHERE d._campagna= :campagna " +
						"AND d._cuaa= :cuaa " + 
						"AND d._misura= :misura"),		
		@NamedQuery(
				name = "Domanda.domandaForIDCuaaCampagna",
				query = "SELECT DISTINCT d " +
						"FROM Domanda d " +
						"WHERE d._campagna= :campagna " +
						"AND d._cuaa= :cuaa " + 
						"AND d._idDomanda = :idDomanda"),		
		@NamedQuery(
				name = "Domanda.findDomanda",
				query = "SELECT d._idDomanda " +
						"FROM Domanda d " +
						"WHERE d._idDomanda= :idDomanda"),	
		
		@NamedQuery(
				name = "Domanda.misuraForDomanda",
				query = "SELECT DISTINCT d " +
						"FROM Domanda d " +
						"WHERE d._idDomanda= :idDomanda " +
						"AND d._misura= :misura"),

})
public class Domanda implements Serializable {

	private static final long serialVersionUID = 8134836879850971090L;

	@Id
	@Column(name = "DOMANDA_OPR")
	private String _idDomanda;
	
	@Column(name = "DOMANDA_AGEA")
	private String _idDomandaAgea;
	
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Id
	@Column(name = "MISURA")
	private String _misura;
	
	@Column(name = "CUAA")
	private String _cuaa;

	@Column(name = "IMP_RICH")
	private String _impRich;

	@Column(name = "IMP_LIQ")
	private String _impLiq;
	
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
	public Domanda()
	{
		_idDomanda = "";
		_idDomandaAgea = "";
		_campagna = "";
		_misura = "";
		_cuaa = "";
		_impLiq = "";
		_impRich = "";
		_user_inserimento = "";
		_user_modifica = "";
	}
	
	public Domanda(String id){
		set_idDomanda(id);
	}

	/**
	 * @param _idDomanda the _idDomanda to set
	 */
	public void set_idDomanda(String _idDomanda) {
		_idDomanda = _idDomanda == null ? "" : _idDomanda;
		this._idDomanda = _idDomanda;
	}

	/**
	 * @return the _idDomanda
	 */
	public String get_idDomanda() {
		return _idDomanda;
	}
	
	public void set_campagna(String campagna){
		campagna = campagna == null ? "" : campagna;
		_campagna = campagna;
	}
	
	public String get_campagna(){
		return _campagna;
	}
	
	public void set_misura(String misura){
		misura = misura == null ? "" : misura;
		_misura = misura;
	}
	
	public String get_misura(){
		return _misura;
	}
	
	public void set_cuaa(String cuaa){
		cuaa = cuaa == null ? "" : cuaa;
		_cuaa = cuaa;
//		_azienda.set_cuaa(cuaa);
	}
	
	public String get_cuaa(){
//		if(_azienda == null) return "";
//		return _azienda.get_cuaa();
		 return _cuaa;
	}

	public void set_idDomandaAgea(String _idDomandaAgea) {
		_idDomandaAgea = _idDomandaAgea == null ? "" : _idDomandaAgea;
		this._idDomandaAgea = _idDomandaAgea;
	}

	public String get_idDomandaAgea() {
		return _idDomandaAgea;
	}

	public void set_impRich(String _impRich) {
		_impRich = _impRich == null ? "" : _impRich;
		this._impRich = _impRich;
	}

	public String get_impRich() {
		return _impRich;
	}

	public void set_impLiq(String _impLiq) {
		_impLiq = _impLiq == null ? "" : _impLiq;
		this._impLiq = _impLiq;
	}

	public String get_impLiq() {
		return _impLiq;
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

//	public void set_azienda(Azienda _azienda) {
//		this._azienda = _azienda;
//	}
//
//	public Azienda get_azienda() {
//		return _azienda;
//	}

//	public void set_listUba(List<Uba> _listUba) {
//		this._listUba = _listUba;
//	}
//
//	public List<Uba> get_listUba() {
//		return _listUba;
//	}

//	public void set_campione(Campione _campione) {
//		this._campione = _campione;
//	}
//
//	public Campione get_campione() {
//		return _campione;
//	}


}
