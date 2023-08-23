package it.bz.prov.esiti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity che rappresenta il dettaglio del sottointervento per una data domanda
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(SottointerventoPk.class)
@Table(name = "OPP_ESITI_DWH_INT_SOTTOINT")
@NamedQueries({
		@NamedQuery(
				name = "Sottointervento.selectAllForDomanda",
				query = "SELECT DISTINCT s._codiceIntervento, s._descrizioneIntervento, " +
						"s._codiceSottointervento, s._descrizioneSottointervento " +
						"FROM Sottointervento s " +
						"WHERE s._domanda= :domanda " +
						"ORDER BY s._codiceIntervento, s._codiceSottointervento"),
		@NamedQuery(
				name = "Sottointervento.distinctIntervento",
				query = "SELECT DISTINCT s._codiceIntervento FROM Sottointervento s ORDER BY s._codiceIntervento"),
				
		@NamedQuery(
				name = "Sottointervento.distinctMisura",
				query = "SELECT DISTINCT s._misura FROM Sottointervento s WHERE s._misura <> 'XXX' ORDER BY s._misura"),
						
		@NamedQuery(
				name = "Sottointervento.distinctSottointerventoForIntervento",
				query = "SELECT DISTINCT s._codiceSottointervento " +
						"FROM Sottointervento s " +
						"WHERE s._codiceIntervento= :intervento " +
						"ORDER BY s._codiceSottointervento"),
		@NamedQuery(
				name = "Sottointervento.distinctSottointervento",
				query = "SELECT DISTINCT s._codiceSottointervento FROM Sottointervento s ORDER BY s._codiceSottointervento"),
		@NamedQuery(
				name = "Sottointervento.domandaHasIntervento",
				query = "SELECT DISTINCT s._domanda FROM Sottointervento s " +
						"WHERE s._domanda= :domanda " +
						"AND s._codiceIntervento= :intervento "),
		@NamedQuery(
				name = "Sottointervento.domandaHasInterventoAndSottointervento",
				query = "SELECT DISTINCT s._domanda FROM Sottointervento s " +
						"WHERE s._domanda= :domanda " +
						"AND s._codiceIntervento= :intervento " +
						"AND s._codiceSottointervento= :sottointervento "),
		@NamedQuery(
				name = "Sottointervento.domandaHasInterventoAndSottointerventoo",
				query = "SELECT DISTINCT s._domanda FROM Sottointervento s " +
						"WHERE s._domanda= :domanda " +
						"AND s._codiceIntervento= :intervento " +
						"AND s._codiceSottointervento= :sottointervento "),
		@NamedQuery(
				name = "Sottointervento.InterventoForCampagna",
				query = "SELECT DISTINCT s._codiceIntervento " +
				        "FROM Sottointervento s " + 
						"where s._campagna= :campagna  " + 
				        "ORDER BY s._codiceIntervento"),
		@NamedQuery(
				name = "Sottointervento.SottointerventoForCampagna",
				query = "SELECT DISTINCT s._codiceSottointervento " +
					    "FROM Sottointervento s " + 
						"where s._campagna= :campagna  " + 
						"ORDER BY s._codiceSottointervento"),
		@NamedQuery(
				name = "Sottointervento.InterventoForCampagnaWithoutDU",
				query = "SELECT DISTINCT s._codiceIntervento " +
						"FROM Sottointervento s " + 
						"where s._campagna= :campagna  " + 
						"and s._misura <> 'DU' " + 
						"ORDER BY s._codiceIntervento"),
		@NamedQuery(
				name = "Sottointervento.SottointerventoForCampagnaWithoutDU",
				query = "SELECT DISTINCT s._codiceSottointervento " +
						"FROM Sottointervento s " + 
						"where s._campagna= :campagna  " + 
						"and s._misura <> 'DU'  " + 
						"ORDER BY s._codiceSottointervento"),
		
		@NamedQuery(
				name = "Sottointervento.MisuraForCampagnaWithoutDU",
				query = "SELECT DISTINCT s._misura " +
						"FROM Sottointervento s " + 
						"where s._campagna= :campagna " + 
						"and s._misura not in ('DU', 'XXX') " + 
						"ORDER BY s._misura"),					
						
})
public class Sottointervento implements Serializable {
	
	private static final long serialVersionUID = -7198651090332331055L;
//	"SELECT DISTINCT s FROM Sottointervento s WHERE s._domanda= :domanda ORDER BY s._codiceIntervento, s._codiceSottointervento

	@Id
	@Column(name = "DOMANDA_OPR")
	private String _domanda;
	
	@Id
	@Column(name = "INTERVENTO")
	private String _codiceIntervento;
	
	@Column(name = "DESCR_INTERVENTO")
	private String _descrizioneIntervento;
	
	@Id
	@Column(name = "SOTTOINTERVENTO")
	private String _codiceSottointervento;
	
	@Column(name = "DESCR_SOTTOINT")
	private String _descrizioneSottointervento;
	
	@Column(name = "CAMPAGNA")
	private String _campagna;
	
	@Column(name = "MISURA")
	private String _misura;
	
	/**
	 * Costruttore
	 */
	public Sottointervento() {
		_codiceIntervento = "";
		_descrizioneIntervento = "";
		_codiceSottointervento = "";
		_descrizioneSottointervento = "";
		_domanda = "";
		_campagna = "";
		_misura="";
	}

	public void set_codiceIntervento(String _codiceIntervento) {
		_codiceIntervento = _codiceIntervento == null ? "" : _codiceIntervento;
		this._codiceIntervento = _codiceIntervento;
	}

	public String get_codiceIntervento() {
		return _codiceIntervento;
	}

	public void set_descrizioneIntervento(String _descrizioneIntervento) {
		_descrizioneIntervento = _descrizioneIntervento == null ? "" : _descrizioneIntervento;
		this._descrizioneIntervento = _descrizioneIntervento;
	}

	public String get_descrizioneIntervento() {
		return _descrizioneIntervento;
	}

	public void set_codiceSottointervento(String _codiceSottointervento) {
		_codiceSottointervento = _codiceSottointervento == null ? "" : _codiceSottointervento;
		this._codiceSottointervento = _codiceSottointervento;
	}

	public String get_codiceSottointervento() {
		return _codiceSottointervento;
	}

	public void set_descrizioneSottointervento(
			String _descrizioneSottointervento) {
		_descrizioneSottointervento = _descrizioneSottointervento == null ? "" : _descrizioneSottointervento;
		this._descrizioneSottointervento = _descrizioneSottointervento;
	}

	public String get_descrizioneSottointervento() {
		return _descrizioneSottointervento;
	}

	public void set_domanda(String _domanda) {
		_domanda = _domanda == null ? "" : _domanda;
		this._domanda = _domanda;
	}

	public String get_domanda() {
		return _domanda;
	}	

	public void set_campagna(String _campagna) {
		_campagna = _campagna == null ? "" : _campagna;
		this._campagna = _campagna;
	}

	public String get_campagna() {
		return _campagna;
	}	
	
	public void set_misura(String _misura) {
		_misura = _misura == null ? "" : _misura;
		this._campagna = _misura;
	}

	public String get_misura() {
		return _misura;
	}

}
