package it.bz.prov.esiti.bobject;

import it.bz.prov.esiti.entity.MatrAmmIdeale;

import java.util.Date;


/**
 * Rappresenta l'oggetto BO che contiene le informazioni relative alla matrice di amissibilità 
 * ideale per un'azienda in una data campagna
 * 
 * @author bpettazzoni
 *
 */

public class MatrAmmIdealeBO {
	
	private String _cuaa;
	private String _campagna;
	private String _a1;
	private String _a2;
	private String _a3;
	private String _a4;
	private String _fer;
	private String _a5;
	private String _a6;
	private String _a7;
	private String _a8;
	private String _b9;
	private String _fit;
	private String _b10;
	private String _b11;
	private String _b12;
	private String _b13;
	private String _b14;
	private String _b15;
	private String _c16;
	private String _c17;
	private String _c18;
	private String _1_1;
	private String _1_2;
	private String _1_3;
	private String _2_1;
	private String _2_2;
	private String _3_1;
	private String _4_1;
	private String _4_2;
	private String _4_3;
	private String _4_4;
	private String _4_6;
	private String _5_1;
	private String _5_2;	
	private String _userInserimento;
	private Date _dataInserimento;
	private String _cgo1;
	private String _bcaa1;
	private String _bcaa2;
	private String _bcaa3;
	private String _bcaa4;
	private String _bcaa5;
	private String _bcaa6;
	private String _cgo2;
	private String _cgo3;
	private String _bcaa7;
	private String _cgo4;
	private String _cgo5;
	private String _cgo6;
	private String _cgo7;
	private String _cgo8;
	private String _cgo9;
	private String _cgo10;
	private String _cgo11;
	private String _cgo12;
	private String _cgo13;
	private String _bcaa8;
	
	

	/************************************************************************
	 * 				COSTRUTTORI
	 ***********************************************************************/
	
	
	/**
	 * costruttore senza parametri
	 */
	public MatrAmmIdealeBO()
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
	

	/**
	 * costruttore con parametri
	 * @param m é l'oggetto entity
	 */
	public MatrAmmIdealeBO(MatrAmmIdeale m)
	{
		_cuaa = m.get_cuaa();
		_userInserimento = m.get_userInserimento();
		_campagna = m.get_campagna();
		_1_1 = m.get_1_1();
		_1_2 = m.get_1_2();
		_1_3 = m.get_1_3();
		_2_1 = m.get_2_1();
		_2_2 = m.get_2_2();
		_3_1 = m.get_3_1();
		_4_1 = m.get_4_1();
		_4_2 = m.get_4_2();
		_4_3 = m.get_4_3();
		_4_4 = m.get_4_4();
		_4_6 = m.get_4_6();
		_5_1 = m.get_5_1();
		_5_2 = m.get_5_2();
		_a1 = m.get_a1();
		_a2 = m.get_a2();
		_a3 = m.get_a3();
		_a4 = m.get_a4();
		_a5 = m.get_a5();
		_a6 = m.get_a6();
		_a7 = m.get_a7();
		_a8 = m.get_a8();
		_b9 = m.get_b9();
		_b10 = m.get_b10();
		_b11 = m.get_b11();
		_b12 = m.get_b12();
		_b13 = m.get_b13();
		_b14 = m.get_b14();
		_b15 = m.get_b15();
		_c16 = m.get_c16();
		_c17 = m.get_c17();
		_c18 = m.get_c18();
		_fer = m.get_fer();
		_fit = m.get_fit();
		_cgo1 = m.get_cgo1();
		_bcaa1 = m.get_bcaa1();
		_bcaa2 = m.get_bcaa2();
		_bcaa3 = m.get_bcaa3();
		_bcaa4 = m.get_bcaa4();
		_bcaa5 = m.get_bcaa5();
		_bcaa6 = m.get_bcaa6();
		_cgo2 = m.get_cgo2();
		_cgo3 = m.get_cgo3();
		_bcaa7 = m.get_bcaa7();
		_cgo4 = m.get_cgo4();
		_cgo5 = m.get_cgo5();
		_cgo6 = m.get_cgo6();
		_cgo7 = m.get_cgo7();
		_cgo8 = m.get_cgo8();
		_cgo9 = m.get_cgo9();
		_cgo10 = m.get_cgo10();
		_cgo11= m.get_cgo11();
		_cgo12 = m.get_cgo12();
		_cgo13 = m.get_cgo13();
		_bcaa8 = m.get_bcaa8();
	}
	
	/************************************************************************
	 * 				METODI DI UTILITY
	 ***********************************************************************/
	
	
	public synchronized String get_cuaa() {
		return _cuaa;
	}
	
	public synchronized void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}

	public void set_userInserimento(String _userInserimento) {
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
		this._campagna = _campagna;
	}

	public String get_campagna() {
		return _campagna;
	}


	public void set_a1(String _a1) {
		this._a1 = _a1;
	}


	public String get_a1() {
		return _a1;
	}


	public void set_a2(String _a2) {
		this._a2 = _a2;
	}


	public String get_a2() {
		return _a2;
	}


	public void set_a3(String _a3) {
		this._a3 = _a3;
	}


	public String get_a3() {
		return _a3;
	}


	public void set_a4(String _a4) {
		this._a4 = _a4;
	}


	public String get_a4() {
		return _a4;
	}


	public void set_fer(String _fer) {
		this._fer = _fer;
	}


	public String get_fer() {
		return _fer;
	}


	public void set_a5(String _a5) {
		this._a5 = _a5;
	}


	public String get_a5() {
		return _a5;
	}


	public void set_a6(String _a6) {
		this._a6 = _a6;
	}


	public String get_a6() {
		return _a6;
	}


	public void set_a7(String _a7) {
		this._a7 = _a7;
	}


	public String get_a7() {
		return _a7;
	}


	public void set_a8(String _a8) {
		this._a8 = _a8;
	}


	public String get_a8() {
		return _a8;
	}


	public void set_b9(String _b9) {
		this._b9 = _b9;
	}


	public String get_b9() {
		return _b9;
	}


	public void set_fit(String _fit) {
		this._fit = _fit;
	}


	public String get_fit() {
		return _fit;
	}


	public void set_b10(String _b10) {
		this._b10 = _b10;
	}


	public String get_b10() {
		return _b10;
	}


	public void set_b11(String _b11) {
		this._b11 = _b11;
	}


	public String get_b11() {
		return _b11;
	}


	public void set_b12(String _b12) {
		this._b12 = _b12;
	}


	public String get_b12() {
		return _b12;
	}


	public void set_b13(String _b13) {
		this._b13 = _b13;
	}


	public String get_b13() {
		return _b13;
	}


	public void set_b14(String _b14) {
		this._b14 = _b14;
	}


	public String get_b14() {
		return _b14;
	}


	public void set_b15(String _b15) {
		this._b15 = _b15;
	}


	public String get_b15() {
		return _b15;
	}


	public void set_c16(String _c16) {
		this._c16 = _c16;
	}


	public String get_c16() {
		return _c16;
	}


	public void set_c17(String _c17) {
		this._c17 = _c17;
	}


	public String get_c17() {
		return _c17;
	}


	public void set_c18(String _c18) {
		this._c18 = _c18;
	}


	public String get_c18() {
		return _c18;
	}


	public void set_1_1(String _1_1) {
		this._1_1 = _1_1;
	}


	public String get_1_1() {
		return _1_1;
	}


	public void set_1_2(String _1_2) {
		this._1_2 = _1_2;
	}


	public String get_1_2() {
		return _1_2;
	}


	public void set_1_3(String _1_3) {
		this._1_3 = _1_3;
	}


	public String get_1_3() {
		return _1_3;
	}


	public void set_2_1(String _2_1) {
		this._2_1 = _2_1;
	}


	public String get_2_1() {
		return _2_1;
	}


	public void set_2_2(String _2_2) {
		this._2_2 = _2_2;
	}


	public String get_2_2() {
		return _2_2;
	}


	public void set_3_1(String _3_1) {
		this._3_1 = _3_1;
	}


	public String get_3_1() {
		return _3_1;
	}


	public void set_4_1(String _4_1) {
		this._4_1 = _4_1;
	}


	public String get_4_1() {
		return _4_1;
	}


	public void set_4_2(String _4_2) {
		this._4_2 = _4_2;
	}


	public String get_4_2() {
		return _4_2;
	}


	public void set_4_3(String _4_3) {
		this._4_3 = _4_3;
	}


	public String get_4_3() {
		return _4_3;
	}


	public void set_4_4(String _4_4) {
		this._4_4 = _4_4;
	}


	public String get_4_4() {
		return _4_4;
	}


	public void set_4_6(String _4_6) {
		this._4_6 = _4_6;
	}


	public String get_4_6() {
		return _4_6;
	}


	public void set_5_1(String _5_1) {
		this._5_1 = _5_1;
	}


	public String get_5_1() {
		return _5_1;
	}


	public void set_5_2(String _5_2) {
		this._5_2 = _5_2;
	}


	public String get_5_2() {
		return _5_2;
	}


	public String get_cgo1() {
		return _cgo1;
	}


	public void set_cgo1(String _cgo1) {
		this._cgo1 = _cgo1;
	}


	public String get_bcaa1() {
		return _bcaa1;
	}


	public void set_bcaa1(String _bcaa1) {
		this._bcaa1 = _bcaa1;
	}


	public String get_bcaa2() {
		return _bcaa2;
	}


	public void set_bcaa2(String _bcaa2) {
		this._bcaa2 = _bcaa2;
	}


	public String get_bcaa3() {
		return _bcaa3;
	}


	public void set_bcaa3(String _bcaa3) {
		this._bcaa3 = _bcaa3;
	}


	public String get_bcaa4() {
		return _bcaa4;
	}


	public void set_bcaa4(String _bcaa4) {
		this._bcaa4 = _bcaa4;
	}


	public String get_bcaa5() {
		return _bcaa5;
	}


	public void set_bcaa5(String _bcaa5) {
		this._bcaa5 = _bcaa5;
	}


	public String get_bcaa6() {
		return _bcaa6;
	}


	public void set_bcaa6(String _bcaa6) {
		this._bcaa6 = _bcaa6;
	}


	public String get_cgo2() {
		return _cgo2;
	}


	public void set_cgo2(String _cgo2) {
		this._cgo2 = _cgo2;
	}


	public String get_cgo3() {
		return _cgo3;
	}


	public void set_cgo3(String _cgo3) {
		this._cgo3 = _cgo3;
	}


	public String get_bcaa7() {
		return _bcaa7;
	}


	public void set_bcaa7(String _bcaa7) {
		this._bcaa7 = _bcaa7;
	}


	public String get_cgo4() {
		return _cgo4;
	}


	public void set_cgo4(String _cgo4) {
		this._cgo4 = _cgo4;
	}


	public String get_cgo5() {
		return _cgo5;
	}


	public void set_cgo5(String _cgo5) {
		this._cgo5 = _cgo5;
	}


	public String get_cgo6() {
		return _cgo6;
	}


	public void set_cgo6(String _cgo6) {
		this._cgo6 = _cgo6;
	}


	public String get_cgo7() {
		return _cgo7;
	}


	public void set_cgo7(String _cgo7) {
		this._cgo7 = _cgo7;
	}


	public String get_cgo8() {
		return _cgo8;
	}


	public void set_cgo8(String _cgo8) {
		this._cgo8 = _cgo8;
	}


	public String get_cgo9() {
		return _cgo9;
	}


	public void set_cgo9(String _cgo9) {
		this._cgo9 = _cgo9;
	}


	public String get_cgo10() {
		return _cgo10;
	}


	public void set_cgo10(String _cgo10) {
		this._cgo10 = _cgo10;
	}


	public String get_cgo11() {
		return _cgo11;
	}


	public void set_cgo11(String _cgo11) {
		this._cgo11 = _cgo11;
	}


	public String get_cgo12() {
		return _cgo12;
	}


	public void set_cgo12(String _cgo12) {
		this._cgo12 = _cgo12;
	}


	public String get_cgo13() {
		return _cgo13;
	}


	public void set_cgo13(String _cgo13) {
		this._cgo13 = _cgo13;
	}


	public String get_bcaa8() {
		return _bcaa8;
	}


	public void set_bcaa8(String _bcaa8) {
		this._bcaa8 = _bcaa8;
	}

}
