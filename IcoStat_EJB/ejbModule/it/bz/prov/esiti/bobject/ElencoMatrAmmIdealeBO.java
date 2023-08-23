package it.bz.prov.esiti.bobject;


import it.bz.prov.esiti.entity.MatrAmmIdeale;
import java.util.List;
import java.util.Vector;

/**
 * Business entity che rappresenta l'elenco delle matrici di ammissibilità ideale
 * 
 * @author bpettazzoni
 *
 */

public class ElencoMatrAmmIdealeBO {

	private Vector<MatrAmmIdealeBO> _listMatrAmmIdeale;
	
	
	public ElencoMatrAmmIdealeBO(){
		_listMatrAmmIdeale = new Vector<MatrAmmIdealeBO>();
	}
	
	public void addMatrAmmIdeale(MatrAmmIdealeBO matr){
		_listMatrAmmIdeale.add(matr);
	}
	
	public int size(){
		return _listMatrAmmIdeale.size();
	}
	
	public void remove(MatrAmmIdealeBO matr)
	{
		_listMatrAmmIdeale.remove(matr);
	}

	public void set_listMatrAmmIdeale(List<MatrAmmIdeale> list) {
		_listMatrAmmIdeale.clear();
		for (MatrAmmIdeale matr : list) {
			_listMatrAmmIdeale.add(new MatrAmmIdealeBO(matr));
		}
	}

	public Vector<MatrAmmIdealeBO> get_listMatrAmmIdeale() {
		return _listMatrAmmIdeale;
	}


	
}
