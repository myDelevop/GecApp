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
 * Entity che rappresenta l'esito di condizionalità per atto
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(EsitoCondizionalitaAttoPk.class)
@Table(name = "OPP_ESITI_APP_ESITO_COND_ATTO")
@NamedQueries({
		@NamedQuery(
				name = "EsitoCondizionalitaAtto.selectAll",
				query = "SELECT eca FROM EsitoCondizionalitaAtto eca ORDER BY eca._cuaa, eca._campagna"),
		@NamedQuery(
				name = "EsitoCondizionalitaAtto.selectFilter",
				query = "SELECT DISTINCT e FROM EsitoCondizionalitaAtto e, Azienda a, CampioneAggr c " +
				"WHERE e._cuaa=a._cuaa AND a._cuaa=c._cuaa AND e._campagna=c._campagna " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(e._cuaa) LIKE lower(:cuaa) " +
				"AND e._campagna LIKE :campagna " +
				"AND c._misura LIKE :misura " +
				"AND c._domandaOpr LIKE :domanda " +
				"AND (c._flagCampione is null OR c._flagCampione LIKE :campione) " +
				"AND (c._flagExtraCampione is null OR c._flagExtraCampione LIKE :extraCampione) " +
				"ORDER BY e._cuaa, e._campagna"),
})
public class EsitoCondizionalitaAtto implements Serializable {
	
	private static final long serialVersionUID = 510436115195170383L;

	
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Id
	@Column(name = "COD_COND")
	private String _cod_cond;
	
	@Id
	@Column(name = "ATTO_CONTR")
	private String _atto_contr;
	
	@Id
	@Column(name = "TIPO_CONTROLLO")
	private String _tipoControllo;

	@Column(name = "RESP_CONTROLLO")
	private String _resp_controllo;
	
	@Column(name = "INFRAZIONE")
	private String _infrazione;
	
	@Column(name = "DATA_1_CONTR")
	private String _data_1_contr;
	
	@Column(name = "RICH_AZ_CORR")
	private String _rich_az_corr;
	
	@Column(name = "AZ_CORR_ESEG")
	private String _az_corr_eseguita;
	
	@Column(name = "RICH_IMP_RIPR")
	private String _rich_imp_ripr;
	
	@Column(name = "IMP_RIPR_ESEG")
	private String _imp_ripr_eseguito;
	
	@Column(name = "DATA_2_CONTR")
	private String _data_2_contr;
	
	@Column(name = "ESITO_2_CONTR")
	private String _esito_2_contr;
	
	@Column(name = "INADEMPIENZA")
	private String _inadempienza;
	
	@Column(name = "NEGLIGENZA")
	private String _negligenza;
	
	@Column(name = "INTENZIONALITA")
	private String _intenzionalita;
	
	@Column(name = "PROGR_ACCERT_INTENZ")
	private String _progr_accert_intenz;
	
	@Column(name = "REITERAZIONE")
	private String _reiterazione;
	
	@Column(name = "PROGR_ACC_REITERAZ")
	private String _progr_Accert_reit;
	
	@Column(name = "PORTATA")
	private String _portata;
	
	@Column(name = "PORTATA_NOTE")
	private String _portata_note;
	
	@Column(name = "GRAVITA")
	private String _gravita;
	
	@Column(name = "GRAVITA_NOTE")
	private String _gravita_note;
	
	@Column(name = "DURATA")
	private String _durata;
	
	@Column(name = "DURATA_NOTE")
	private String _durata_note;
	
	@Column(name = "PERC_RID")
	private String _perc_rid;
	
	@Column(name = "AMMONIZIONE")
	private String _ammonizione;
	
	@Column(name = "NOTE")
	private String _note;
		
	@Column(name = "INTENZIONALITA_NOTE")
	private String _intenzionalita_note;
	
	@Column(name = "SEGNALAZIONE")
	private String _segnalazione;
	
	@Column(name = "APPROVAZIONE")
	private String _approvazione;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _data_inserimento;
	
	@Column(name = "USER_INSERIMENTO")
	private String _user_inserimento;
	
	@Column(name = "DATA_MODIFICA")
	private Date _data_modifica;
	
	@Column(name = "USER_MODIFICA")
	private String _user_modifica;
	
	
	/**
	 * Costruttore
	 */
	public EsitoCondizionalitaAtto()
	{
		_cuaa = "";
		_campagna = "";
		_cod_cond = "";
		_atto_contr = "";
		_resp_controllo = "";
		_infrazione = "";
		_data_1_contr = "";
		_rich_az_corr = "";
		_az_corr_eseguita = "";
		_rich_imp_ripr = "";
		_imp_ripr_eseguito = "";
		_data_2_contr = "";
		_esito_2_contr = "";
		_inadempienza = "";
		_negligenza = "";
		_intenzionalita = "";
		_progr_accert_intenz = "";
		_reiterazione = "";
		_progr_Accert_reit = "";
		_portata = "";
		_portata_note= "";
		_gravita = "";
		_gravita_note = "";
		_durata = "";
		_durata_note = "";
		_ammonizione = "";
		_note = "";
		_perc_rid = "";
		_user_inserimento = "";
		_user_modifica = "";
		_tipoControllo = "";
		_intenzionalita_note = "";
		_segnalazione = "";
		_approvazione = "";
	}
	
	public void set_cuaa(String _cuaa) {
		_cuaa = _cuaa == null ? "" : _cuaa;
		this._cuaa = _cuaa;
	}
	public String get_cuaa() {
		return _cuaa;
	}
	public void set_campagna(String _campagna) {
		_campagna = _campagna == null ? "" : _campagna;
		this._campagna = _campagna;
	}
	public String get_campagna() {
		return _campagna;
	}
	
	public void set_cod_cond(String _cod_cond) {
		_cod_cond = _cod_cond == null ? "" : _cod_cond;
		this._cod_cond = _cod_cond;
	}
	
	public String get_cod_cond() {
		if(_cod_cond == null) return "";
		return _cod_cond;
	}
	
	public void set_atto_contr(String _atto_contr) {
		_atto_contr = _atto_contr == null ? "" : _atto_contr;
		this._atto_contr = _atto_contr;
	}
	
	public String get_atto_contr() {
		if(_atto_contr == null) return "";
		return _atto_contr;
	}
	
	public void set_resp_controllo(String _resp_controllo) {
		_resp_controllo = _resp_controllo == null ? "" : _resp_controllo;
		this._resp_controllo = _resp_controllo;
	}
	
	public String get_resp_controllo() {
		if(_resp_controllo == null) return "";
		return _resp_controllo;
	}
	
	public void set_infrazione(String _infrazione) {
		_infrazione = _infrazione == null ? "" : _infrazione;
		this._infrazione = _infrazione;
	}
	
	public String get_infrazione() {
		if(_infrazione == null) return "";
		return _infrazione;
	}
	
	public void set_data_1_contr(String _data_1_contr) {
		_data_1_contr = _data_1_contr == null ? "" : _data_1_contr;
		this._data_1_contr = _data_1_contr;
	}
	
	public String get_data_1_contr() {
		if(_data_1_contr == null) return "";
		return _data_1_contr;
	}
	
	public void set_rich_az_corr(String _rich_az_corr) {
		_rich_az_corr = _rich_az_corr == null ? "" : _rich_az_corr;
		this._rich_az_corr = _rich_az_corr;
	}
	
	public String get_rich_az_corr() {
		if(_rich_az_corr == null) return "";
		return _rich_az_corr;
	}
	
	public void set_az_corr_eseguita(String _az_corr_eseguita) {
		_az_corr_eseguita = _az_corr_eseguita == null ? "" : _az_corr_eseguita;
		this._az_corr_eseguita = _az_corr_eseguita;
	}
	
	public String get_az_corr_eseguita() {
		if(_az_corr_eseguita == null) return "";
		return _az_corr_eseguita;
	}
	
	public void set_rich_imp_ripr(String _rich_imp_ripr) {
		_rich_imp_ripr = _rich_imp_ripr == null ? "" : _rich_imp_ripr;
		this._rich_imp_ripr = _rich_imp_ripr;
	}
	
	public String get_rich_imp_ripr() {
		if(_rich_imp_ripr == null) return "";
		return _rich_imp_ripr;
	}
	
	public void set_imp_ripr_eseguito(String _imp_ripr_eseguito) {
		_imp_ripr_eseguito = _imp_ripr_eseguito == null ? "" : _imp_ripr_eseguito;
		this._imp_ripr_eseguito = _imp_ripr_eseguito;
	}
	
	public String get_imp_ripr_eseguito() {
		if(_imp_ripr_eseguito == null) return "";
		return _imp_ripr_eseguito;
	}
	
	public void set_data_2_contr(String _data_2_contr) {
		_data_2_contr = _data_2_contr == null ? "" : _data_2_contr;
		this._data_2_contr = _data_2_contr;
	}
	
	public String get_data_2_contr() {
		if(_data_2_contr == null) return "";
		return _data_2_contr;
	}
	
	public void set_esito_2_contr(String _esito_2_contr) {
		_esito_2_contr = _esito_2_contr == null ? "" : _esito_2_contr;
		this._esito_2_contr = _esito_2_contr;
	}
	
	public String get_esito_2_contr() {
		if(_esito_2_contr == null) return "";
		return _esito_2_contr;
	}
	
	public void set_inadempienza(String _inadempienza) {
		_inadempienza = _inadempienza == null ? "" : _inadempienza;
		this._inadempienza = _inadempienza;
	}
	
	public String get_inadempienza() {
		if(_inadempienza == null) return "";
		return _inadempienza;
	}
	
	public void set_negligenza(String _negligenza) {
		_negligenza = _negligenza == null ? "" : _negligenza;
		this._negligenza = _negligenza;
	}
	
	public String get_negligenza() {
		if(_negligenza == null) return "";
		return _negligenza;
	}
	
	public void set_intenzionalita(String _intenzionalita) {
		_intenzionalita = _intenzionalita == null ? "" : _intenzionalita;
		this._intenzionalita = _intenzionalita;
	}
	
	public String get_intenzionalita() {
		if(_intenzionalita == null) return "";
		return _intenzionalita;
	}
	
	public void set_progr_accert_intenz(String _progr_accert_intenz) {
		_progr_accert_intenz = _progr_accert_intenz == null ? "" : _progr_accert_intenz;
		this._progr_accert_intenz = _progr_accert_intenz;
	}
	
	public String get_progr_accert_intenz() {
		if(_progr_accert_intenz == null) return "";
		return _progr_accert_intenz;
	}
	
	public void set_reiterazione(String _reiterazione) {
		_reiterazione = _reiterazione == null ? "" : _reiterazione;
		this._reiterazione = _reiterazione;
	}
	
	public String get_reiterazione() {
		if(_reiterazione == null) return "";
		return _reiterazione;
	}
	
	public void set_progr_Accert_reit(String _progr_Accert_reit) {
		_progr_Accert_reit = _progr_Accert_reit == null ? "" : _progr_Accert_reit;
		this._progr_Accert_reit = _progr_Accert_reit;
	}
	
	public String get_progr_Accert_reit() {
		if(_progr_Accert_reit == null) return "";
		return _progr_Accert_reit;
	}
	
	public void set_portata(String _portata) {
		_portata = _portata == null ? "" : _portata;
		this._portata = _portata;
	}
	
	public String get_portata() {
		if(_portata == null) return "";
		return _portata;
	}
	
	public void set_gravita(String _gravita) {
		_gravita = _gravita == null ? "" : _gravita;
		this._gravita = _gravita;
	}
	
	public String get_gravita() {
		if(_gravita == null) return "";
		return _gravita;
	}
	
	public void set_durata(String _durata) {
		_durata = _durata == null ? "" : _durata;
		this._durata = _durata;
	}
	
	public String get_durata() {
		if(_durata == null) return "";
		return _durata;
	}
	
	public void set_ammonizione(String _ammonizione) {
		_ammonizione = _ammonizione == null ? "" : _ammonizione;
		this._ammonizione = _ammonizione;
	}
	
	public String get_ammonizione() {
		if(_ammonizione == null) return "";
		return _ammonizione;
	}
	
	public void set_note(String _note) {
		_note = _note == null ? "" : _note;
		this._note = _note;
	}
	
	public String get_note() {
		if(_note == null) return "";
		return _note;
	}
	

	public void set_perc_rid(String _perc_rid) {
		_perc_rid = _perc_rid == null ? "" : _perc_rid;
		this._perc_rid = _perc_rid;
	}

	public String get_perc_rid() {
		if(_perc_rid == null) return "";
		return _perc_rid;
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
		if(_user_inserimento == null) return "";
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
		if(_user_modifica == null) return "";
		return _user_modifica;
	}


	public void set_tipoControllo(String _tipoControllo) {
		_tipoControllo = _tipoControllo == null ? "" : _tipoControllo;
		this._tipoControllo = _tipoControllo;
	}

	public String get_tipoControllo() {
		if(_tipoControllo == null) return "";
		return _tipoControllo;
	}

	public void set_portata_note(String _portata_note) {
		_portata_note = _portata_note == null ? "" : _portata_note;
		this._portata_note = _portata_note;
	}

	public String get_portata_note() {
		return _portata_note;
	}

	public void set_gravita_note(String _gravita_note) {
		_gravita_note = _gravita_note == null ? "" : _gravita_note;
		this._gravita_note = _gravita_note;
	}

	public String get_gravita_note() {
		return _gravita_note;
	}

	public void set_durata_note(String _durata_note) {
		_durata_note = _durata_note == null ? "" : _durata_note;
		this._durata_note = _durata_note;
	}

	public String get_durata_note() {
		return _durata_note;
	}

	public String get_intenzionalita_note() {
		return _intenzionalita_note;
	}

	public void set_intenzionalita_note(String _intenzionalita_note) {
		_intenzionalita_note = _intenzionalita_note == null ? "" : _intenzionalita_note;
		this._intenzionalita_note = _intenzionalita_note;
	}

	public String get_segnalazione() {
		return _segnalazione;
	}

	public void set_segnalazione(String _segnalazione) {
		_segnalazione = _segnalazione == null ? "" : _segnalazione;
		this._segnalazione = _segnalazione;
	}

	public String get_approvazione() {
		return _approvazione;
	}

	public void set_approvazione(String _approvazione) {
		_approvazione = _approvazione == null ? "" : _approvazione;
		this._approvazione = _approvazione;
	}	
	
}
