package it.bz.prov.esiti.util;

/**
 * classe che fa l'override della traduzione JPQL in dialeto Oracle. 
 * @author bpettazzoni
 *
 */

public class OracleCustomDialect extends org.hibernate.dialect.Oracle10gDialect {
	
	/**
	 * Rispetto all'originale, che crea un cross join, questa funzione sostituisce le parole cross join con
	 * una virgola, per realizzare un join normale 
	 */
	@Override
	public String getCrossJoinSeparator() {
		return ", ";
//		return super.getCrossJoinSeparator();
	}
	
	
}
