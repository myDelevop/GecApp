package it.bz.prov.esiti.ibusiness;
import javax.ejb.Local;

@Local
public interface ISettings {

	/* Settings per il server LDAP */
	public String get_ldapServerUrl();
	public String get_ldapSearchScope();
	public String get_ldapFilter();
	public String get_ldapCompleteName();
	public String get_ldapDomain();
	/**
	 * Funzione che ritorna il path dove salvare i file uplodati temporaneamente.
	 * @return Il path dove salvare i file uplodati temporaneamente. 
	 */
	public String get_tmp_folder();
	/**
	 * Funzione che ritorna la cartella dove viene salvato il log.
	 * @return Ritorna la cartella dove viene salvato il log.
	 */
	public String get_log_folder();
	/**
	 * Legge da DB se abilitare o meno la login page.
	 * @return true/false per abilitare la forma di login.
	 */
	public boolean get_enableLogin();
	/**
	 * Legge da DB l'ambiente in cui si trova l'applicazione che gira
	 * @return Una stringa che indica l'ambiente [PROD o DEMO-TEST]
	 */
	public String get_Environment();
}
