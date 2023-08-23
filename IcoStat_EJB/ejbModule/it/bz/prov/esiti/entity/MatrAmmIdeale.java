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
 * Entity che rappresenta le informazioni relative alla matrice di amissibilità ideale per un'azienda in una data campagna
 * 
 * @author bpettazzoni
 *
 */


@Entity
@IdClass(MatrAmmIdealePk.class)
@Table(name = "OPP_ESITI_DWH_MATR_AMM_IDEALE")
@NamedQueries({
		@NamedQuery(
				name = "MatrAmmIdeale.selectAll",
				query = "SELECT ma FROM MatrAmmIdeale ma ORDER BY ma._cuaa, ma._campagna"),
		@NamedQuery(
				name = "MatrAmmIdeale.selectLastCampagna",
				query = "SELECT ma FROM MatrAmmIdeale ma WHERE ma._campagna=(SELECT MAX(m._campagna) FROM MatrAmmIdeale m) ORDER BY ma._cuaa"),
		@NamedQuery(
				name = "MatrAmmIdeale.selectFilter",
				query = "SELECT DISTINCT ma FROM MatrAmmIdeale ma, Azienda a, CampioneAggr c " +
				"WHERE ma._cuaa=a._cuaa AND ma._cuaa=c._cuaa AND ma._campagna=c._campagna " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(ma._cuaa) LIKE lower(:cuaa) " +
				"AND ma._campagna LIKE :campagna " + 
				"AND c._misura LIKE :misura " +
				"AND c._domandaOpr LIKE :domanda " +
				"AND (c._flagCampione is null OR c._flagCampione LIKE :campione) " +
				"AND (c._flagExtraCampione is null OR c._flagExtraCampione LIKE :extraCampione) " +
				"ORDER BY ma._cuaa, ma._campagna"),
		@NamedQuery(
				name = "MatrAmmIdeale.selectFilterOnlyAzienda",
				query = "SELECT DISTINCT ma FROM MatrAmmIdeale ma, Azienda a " +
				"WHERE ma._cuaa=a._cuaa " + 
				"AND lower(a._ragioneSociale) LIKE lower(:ragione_sociale) " +
				"AND lower(ma._cuaa) LIKE lower(:cuaa) " +
				"AND ma._campagna LIKE :campagna " + 
				"ORDER BY ma._cuaa, ma._campagna"),
		
})
public class MatrAmmIdeale implements Serializable{

	private static final long serialVersionUID = -3355127333998576766L;

	@Id
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Id
	@Column(name = "CAMPAGNA")
	private String _campagna;

	@Column(name = "ATTO_A1")
	private String _a1;
	
	@Column(name = "ATTO_A2")
	private String _a2;
	
	@Column(name = "ATTO_A3")
	private String _a3;
	
	@Column(name = "ATTO_A4")
	private String _a4;
	
	@Column(name = "ATTO_FER")
	private String _fer;
	
	@Column(name = "ATTO_A5")
	private String _a5;
	
	@Column(name = "ATTO_A6")
	private String _a6;
	
	@Column(name = "ATTO_A7")
	private String _a7;
	
	@Column(name = "ATTO_A8")
	private String _a8;
	
	@Column(name = "ATTO_B9")
	private String _b9;
	
	@Column(name = "ATTO_FIT")
	private String _fit;
	
	@Column(name = "ATTO_B10")
	private String _b10;
	
	@Column(name = "ATTO_B11")
	private String _b11;
	
	@Column(name = "ATTO_B12")
	private String _b12;
	
	@Column(name = "ATTO_B13")
	private String _b13;
	
	@Column(name = "ATTO_B14")
	private String _b14;
	
	@Column(name = "ATTO_B15")
	private String _b15;
	
	@Column(name = "ATTO_C16")
	private String _c16;
	
	@Column(name = "ATTO_C17")
	private String _c17;
	
	@Column(name = "ATTO_C18")
	private String _c18;
	
	@Column(name = "ATTO_1_1")
	private String _1_1;
	
	@Column(name = "ATTO_1_2")
	private String _1_2;
	
	@Column(name = "ATTO_1_3")
	private String _1_3;
	
	@Column(name = "ATTO_2_1")
	private String _2_1;
	
	@Column(name = "ATTO_2_2")
	private String _2_2;
	
	@Column(name = "ATTO_3_1")
	private String _3_1;
	
	@Column(name = "ATTO_4_1")
	private String _4_1;
	
	@Column(name = "ATTO_4_2")
	private String _4_2;
	
	@Column(name = "ATTO_4_3")
	private String _4_3;

	@Column(name = "ATTO_4_4")
	private String _4_4;

	@Column(name = "ATTO_4_6")
	private String _4_6;
	
	@Column(name = "ATTO_5_1")
	private String _5_1;

	@Column(name = "ATTO_5_2")
	private String _5_2;	
	
	@Column(name = "USER_INSERIMENTO")
	private String _userInserimento;
	
	@Column(name = "DATA_INSERIMENTO")
	private Date _dataInserimento;
	
	@Column(name = "ATTO_CGO1")
	private String _cgo1;
	
	@Column(name = "ATTO_BCAA1")
	private String _bcaa1;
	
	@Column(name = "ATTO_BCAA2")
	private String _bcaa2;

	@Column(name = "ATTO_BCAA3")
	private String _bcaa3;
	
	@Column(name = "ATTO_BCAA4")
	private String _bcaa4;
	
	@Column(name = "ATTO_BCAA5")
	private String _bcaa5;
	
	@Column(name = "ATTO_BCAA6")
	private String _bcaa6;
	
	@Column(name = "ATTO_CGO2")
	private String _cgo2;
	
	@Column(name = "ATTO_CGO3")
	private String _cgo3;
	
	@Column(name = "ATTO_BCAA7")
	private String _bcaa7;
	
	@Column(name = "ATTO_CGO4")
	private String _cgo4;
	
	@Column(name = "ATTO_CGO5")
	private String _cgo5;
	
	@Column(name = "ATTO_CGO6")
	private String _cgo6;
	
	@Column(name = "ATTO_CGO7")
	private String _cgo7;
	
	@Column(name = "ATTO_CGO8")
	private String _cgo8;
	
	@Column(name = "ATTO_CGO9")
	private String _cgo9;
	
	@Column(name = "ATTO_CGO10")
	private String _cgo10;
	
	@Column(name = "ATTO_CGO11")
	private String _cgo11;
	
	@Column(name = "ATTO_CGO12")
	private String _cgo12;
	
	@Column(name = "ATTO_CGO13")
	private String _cgo13;
	
	@Column(name = "ATTO_BCAA8")
	private String _bcaa8;
	
	/**
	 * costruttore senza parametri
	 */
	public MatrAmmIdeale()
	{
		_cuaa = "";
		_userInserimento = "";
		_campagna = "";
		_1_1 = "";
		_1_2 = "";
		_1_3 = "";
		_2_1 = "";
		_2_2 = "";
		_3_1 = "";
		_4_1 = "";
		_4_2 = "";
		_4_3 = "";
		_4_4 = "";
		_4_6 = "";
		_5_1 = "";
		_5_2 = "";
		_a1 = "";
		_a2 = "";
		_a3 = "";
		_a4 = "";
		_a5 = "";
		_a6 = "";
		_a7 = "";
		_a8 = "";
		_b9 = "";
		_b10 = "";
		_b11 = "";
		_b12 = "";
		_b13 = "";
		_b14 = "";
		_b15 = "";
		_c16 = "";
		_c17 = "";
		_c18 = "";
		_fer = "";
		_fit = "";
		_cgo1 = "";
		_bcaa1 = "";
		_bcaa2 = "";
		_bcaa3 = "";
		_bcaa4 = "";
		_bcaa5 = "";
		_bcaa6 = "";
		_cgo2 = "";
		_cgo3 = "";
		_bcaa7 = "";
		_cgo4 = "";
		_cgo5 = "";
		_cgo6 = "";
		_cgo7 = "";
		_cgo8 = "";
		_cgo9 = "";
		_cgo10 = "";
		_cgo11 = "";
		_cgo12 = "";
		_cgo13 = "";
		_bcaa8 = "";
	}
	

	public synchronized String get_cuaa() {
		return _cuaa;
	}
	
	public synchronized void set_cuaa(String _cuaa) {
		_cuaa = _cuaa == null ? "" : _cuaa;
		this._cuaa = _cuaa;
	}

	public void set_userInserimento(String _userInserimento) {
		_userInserimento = _userInserimento == null ? "" : _userInserimento;
		this._userInserimento = _userInserimento;
	}

	public String get_userInserimento() {
		return _userInserimento;
	}

	public void set_dataInserimento(Date _dataInserimento) {
		this._dataInserimento = _dataInserimento;
	}

	public Date get_dataInserimento() {
		return _dataInserimento;
	}

	public void set_campagna(String _campagna) {
		_campagna = _campagna == null ? "" : _campagna;
		this._campagna = _campagna;
	}

	public String get_campagna() {
		return _campagna;
	}


	public void set_a1(String _a1) {
		_a1 = _a1 == null ? "" : _a1;
		this._a1 = _a1;
	}


	public String get_a1() {
		return _a1;
	}


	public void set_a2(String _a2) {
		_a2 = _a2 == null ? "" : _a2;
		this._a2 = _a2;
	}


	public String get_a2() {
		return _a2;
	}


	public void set_a3(String _a3) {
		_a3 = _a3 == null ? "" : _a3;
		this._a3 = _a3;
	}


	public String get_a3() {
		return _a3;
	}


	public void set_a4(String _a4) {
		_a4 = _a4 == null ? "" : _a4;
		this._a4 = _a4;
	}


	public String get_a4() {
		return _a4;
	}


	public void set_fer(String _fer) {
		_fer = _fer == null ? "" : _fer;
		this._fer = _fer;
	}


	public String get_fer() {
		return _fer;
	}


	public void set_a5(String _a5) {
		_a5 = _a5 == null ? "" : _a5;
		this._a5 = _a5;
	}


	public String get_a5() {
		return _a5;
	}


	public void set_a6(String _a6) {
		_a6 = _a6 == null ? "" : _a6;
		this._a6 = _a6;
	}


	public String get_a6() {
		return _a6;
	}


	public void set_a7(String _a7) {
		_a7 = _a7 == null ? "" : _a7;
		this._a7 = _a7;
	}


	public String get_a7() {
		return _a7;
	}


	public void set_a8(String _a8) {
		_a8 = _a8 == null ? "" : _a8;
		this._a8 = _a8;
	}


	public String get_a8() {
		return _a8;
	}


	public void set_b9(String _b9) {
		_b9 = _b9 == null ? "" : _b9;
		this._b9 = _b9;
	}


	public String get_b9() {
		return _b9;
	}


	public void set_fit(String _fit) {
		_fit = _fit == null ? "" : _fit;
		this._fit = _fit;
	}


	public String get_fit() {
		return _fit;
	}


	public void set_b10(String _b10) {
		_b10 = _b10 == null ? "" : _b10;
		this._b10 = _b10;
	}


	public String get_b10() {
		return _b10;
	}


	public void set_b11(String _b11) {
		_b11 = _b11 == null ? "" : _b11;
		this._b11 = _b11;
	}


	public String get_b11() {
		return _b11;
	}


	public void set_b12(String _b12) {
		_b12 = _b12 == null ? "" : _b12;
		this._b12 = _b12;
	}


	public String get_b12() {
		return _b12;
	}


	public void set_b13(String _b13) {
		_b13 = _b13 == null ? "" : _b13;
		this._b13 = _b13;
	}


	public String get_b13() {
		return _b13;
	}


	public void set_b14(String _b14) {
		_b14 = _b14 == null ? "" : _b14;
		this._b14 = _b14;
	}


	public String get_b14() {
		return _b14;
	}


	public void set_b15(String _b15) {
		_b15 = _b15 == null ? "" : _b15;
		this._b15 = _b15;
	}


	public String get_b15() {
		return _b15;
	}


	public void set_c16(String _c16) {
		_c16 = _c16 == null ? "" : _c16;
		this._c16 = _c16;
	}


	public String get_c16() {
		return _c16;
	}


	public void set_c17(String _c17) {
		_c17 = _c17 == null ? "" : _c17;
		this._c17 = _c17;
	}


	public String get_c17() {
		return _c17;
	}


	public void set_c18(String _c18) {
		_c18 = _c18 == null ? "" : _c18;
		this._c18 = _c18;
	}


	public String get_c18() {
		return _c18;
	}


	public void set_1_1(String _1_1) {
		_1_1 = _1_1 == null ? "" : _1_1;
		this._1_1 = _1_1;
	}


	public String get_1_1() {
		return _1_1;
	}


	public void set_1_2(String _1_2) {
		_1_2 = _1_2 == null ? "" : _1_2;
		this._1_2 = _1_2;
	}


	public String get_1_2() {
		return _1_2;
	}


	public void set_1_3(String _1_3) {
		_1_3 = _1_3 == null ? "" : _1_3;
		this._1_3 = _1_3;
	}


	public String get_1_3() {
		return _1_3;
	}


	public void set_2_1(String _2_1) {
		_2_1 = _2_1 == null ? "" : _2_1;
		this._2_1 = _2_1;
	}


	public String get_2_1() {
		return _2_1;
	}


	public void set_2_2(String _2_2) {
		_2_2 = _2_2 == null ? "" : _2_2;
		this._2_2 = _2_2;
	}


	public String get_2_2() {
		return _2_2;
	}


	public void set_3_1(String _3_1) {
		_3_1 = _3_1 == null ? "" : _3_1;
		this._3_1 = _3_1;
	}


	public String get_3_1() {
		return _3_1;
	}


	public void set_4_1(String _4_1) {
		_4_1 = _4_1 == null ? "" : _4_1;
		this._4_1 = _4_1;
	}


	public String get_4_1() {
		return _4_1;
	}


	public void set_4_2(String _4_2) {
		_4_2 = _4_2 == null ? "" : _4_2;
		this._4_2 = _4_2;
	}


	public String get_4_2() {
		return _4_2;
	}


	public void set_4_3(String _4_3) {
		_4_3 = _4_3 == null ? "" : _4_3;
		this._4_3 = _4_3;
	}


	public String get_4_3() {
		return _4_3;
	}


	public void set_4_4(String _4_4) {
		_4_4 = _4_4 == null ? "" : _4_4;
		this._4_4 = _4_4;
	}


	public String get_4_4() {
		return _4_4;
	}


	public void set_4_6(String _4_6) {
		_4_6 = _4_6 == null ? "" : _4_6;
		this._4_6 = _4_6;
	}


	public String get_4_6() {
		return _4_6;
	}


	public void set_5_1(String _5_1) {
		_5_1 = _5_1 == null ? "" : _5_1;
		this._5_1 = _5_1;
	}


	public String get_5_1() {
		return _5_1;
	}


	public void set_5_2(String _5_2) {
		_5_2 = _5_2 == null ? "" : _5_2;
		this._5_2 = _5_2;
	}


	public String get_5_2() {
		return _5_2;
	}


	public String get_cgo1() {
		return _cgo1;
	}


	public void set_cgo1(String _cgo1) {
		_cgo1 = _cgo1 == null ? "" : _cgo1;
		this._cgo1 = _cgo1;
	}


	public String get_bcaa1() {
		return _bcaa1;
	}


	public void set_bcaa1(String _bcaa1) {
		_bcaa1 = _bcaa1 == null ? "" : _bcaa1;
		this._bcaa1 = _bcaa1;
	}


	public String get_bcaa2() {
		return _bcaa2;
	}


	public void set_bcaa2(String _bcaa2) {
		_bcaa2 = _bcaa2 == null ? "" : _bcaa2;
		this._bcaa2 = _bcaa2;
	}


	public String get_bcaa3() {
		return _bcaa3;
	}


	public void set_bcaa3(String _bcaa3) {
		_bcaa3 = _bcaa3 == null ? "" : _bcaa3;
		this._bcaa3 = _bcaa3;
	}


	public String get_bcaa4() {
		return _bcaa4;
	}


	public void set_bcaa4(String _bcaa4) {
		_bcaa4 = _bcaa4 == null ? "" : _bcaa4;
		this._bcaa4 = _bcaa4;
	}


	public String get_bcaa5() {
		return _bcaa5;
	}


	public void set_bcaa5(String _bcaa5) {
		_bcaa5 = _bcaa5 == null ? "" : _bcaa5;
		this._bcaa5 = _bcaa5;
	}


	public String get_bcaa6() {
		return _bcaa6;
	}


	public void set_bcaa6(String _bcaa6) {
		_bcaa6 = _bcaa6 == null ? "" : _bcaa6;
		this._bcaa6 = _bcaa6;
	}


	public String get_cgo2() {
		return _cgo2;
	}


	public void set_cgo2(String _cgo2) {
		_cgo2 = _cgo2 == null ? "" : _cgo2;
		this._cgo2 = _cgo2;
	}


	public String get_cgo3() {
		return _cgo3;
	}


	public void set_cgo3(String _cgo3) {
		_cgo3 = _cgo3 == null ? "" : _cgo3;
		this._cgo3 = _cgo3;
	}


	public String get_bcaa7() {
		return _bcaa7;
	}


	public void set_bcaa7(String _bcaa7) {
		_bcaa7 = _bcaa7 == null ? "" : _bcaa7;
		this._bcaa7 = _bcaa7;
	}


	public String get_cgo4() {
		return _cgo4;
	}


	public void set_cgo4(String _cgo4) {
		_cgo4 = _cgo4 == null ? "" : _cgo4;
		this._cgo4 = _cgo4;
	}


	public String get_cgo5() {
		return _cgo5;
	}


	public void set_cgo5(String _cgo5) {
		_cgo5 = _cgo5 == null ? "" : _cgo5;
		this._cgo5 = _cgo5;
	}


	public String get_cgo6() {
		return _cgo6;
	}


	public void set_cgo6(String _cgo6) {
		_cgo6 = _cgo6 == null ? "" : _cgo6;
		this._cgo6 = _cgo6;
	}


	public String get_cgo7() {
		return _cgo7;
	}


	public void set_cgo7(String _cgo7) {
		_cgo7 = _cgo7 == null ? "" : _cgo7;
		this._cgo7 = _cgo7;
	}


	public String get_cgo8() {
		return _cgo8;
	}


	public void set_cgo8(String _cgo8) {
		_cgo8 = _cgo8 == null ? "" : _cgo8;
		this._cgo8 = _cgo8;
	}


	public String get_cgo9() {
		return _cgo9;
	}


	public void set_cgo9(String _cgo9) {
		_cgo9 = _cgo9 == null ? "" : _cgo9;
		this._cgo9 = _cgo9;
	}


	public String get_cgo10() {
		return _cgo10;
	}


	public void set_cgo10(String _cgo10) {
		_cgo10 = _cgo10 == null ? "" : _cgo10;
		this._cgo10 = _cgo10;
	}


	public String get_cgo11() {
		return _cgo11;
	}


	public void set_cgo11(String _cgo11) {
		_cgo11 = _cgo11 == null ? "" : _cgo11;
		this._cgo11 = _cgo11;
	}


	public String get_cgo12() {
		return _cgo12;
	}


	public void set_cgo12(String _cgo12) {
		_cgo12 = _cgo12 == null ? "" : _cgo12;
		this._cgo12 = _cgo12;
	}


	public String get_cgo13() {
		return _cgo13;
	}


	public void set_cgo13(String _cgo13) {
		_cgo13 = _cgo13 == null ? "" : _cgo13;
		this._cgo13 = _cgo13;
	}


	public String get_bcaa8() {
		return _bcaa8;
	}


	public void set_bcaa8(String _bcaa8) {
		_bcaa8 = _bcaa8 == null ? "" : _bcaa8;
		this._bcaa8 = _bcaa8;
	}

}
