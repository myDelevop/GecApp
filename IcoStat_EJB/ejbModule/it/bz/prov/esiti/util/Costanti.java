package it.bz.prov.esiti.util;

/**
 * gestisce tutte le costanti utilizzate nel progetto (WAR + EJB)
 * 
 * @author bpettazzoni
 *
 */

public class Costanti {
	
	/* RUOLI DEGLI UTENTI */
	public static final String ROLE_ADMIN_USER= "ROLE_ADMIN_USER";
	public static final String ROLE_ADMIN_DESCRIPTION= "ROLE_ADMIN_DESCRIPTION";
	public static final String ROLE_USER= "ROLE_USER";
	public static final String ROLE_WR_IMP= "ROLE_WR_IMP";
	public static final String ROLE_WR_COND= "ROLE_WR_COND";
	public static final String ROLE_WR_SUP= "ROLE_WR_SUP";
	public static final String ROLE_WR_ACQUE= "ROLE_WR_ACQUE";
	public static final String ROLE_WR_CAMP= "ROLE_WR_CAMP";
	public static final String ROLE_WR_UBA= "ROLE_WR_UBA";
	public static final String ROLE_WR_ZOOT= "ROLE_WR_ZOOT";
	public static final String ROLE_WR_RMFITFER= "ROLE_WR_RMFITFER";
	public static final String USERNAME = "USERNAME";
	public static final String NOME_COMPLETO = "NOME_COMPLETO";
	
	/* ANAGRAFICHE */
	public static final String ANAGR_ESITO= "ESITO";
	public static final String ANAGR_IMPEGNO = "IMPEGNO";
	public static final String ANAGR_STATO = "STATO";
	public static final String ANAGR_STATO_AZIONE = "STATO AZIONE";
	public static final String ANAGR_TUTELA_ACQUE_CAPACITA_STOCCAGGI = "TUTELA ACQUE - CAPACITA STOCCAGGI";
	public static final String ANAGR_TUTELA_ACQUE_STOCCAGGI_IN_FUNZIONE = "TUTELA ACQUE - STOCCAGGI IN FUNZIONE";
	public static final String ANAGR_TUTELA_ACQUE_CONDIZIONALITA = "TUTELA ACQUE - CONDIZIONALITA";
	public static final String ANAGR_TUTELA_ACQUE_SECONDO_CONTROLLO = "TUTELA ACQUE - SECONDO CONTROLLO STOCCAGGIO";
	public static final String ANAGR_TUTELA_ACQUE_ESITO_STOCCAGGI = "TUTELA ACQUE - ESITO STOCCAGGI";
	public static final String ANAGR_RESPONSABILE_CONTROLLO = "RESPONSABILE CONTROLLO";
	public static final String ANAGR_INFRAZIONE_RILEVATA= "INFRAZIONE RILEVATA";
	public static final String ANAGR_AZIONE_CORRETTIVA_RICHIESTA = "AZIONE CORRETTIVA RICHIESTA";
	public static final String ANAGR_AZIONE_CORRETTIVA_ESEGUITA = "AZIONE CORRETTIVA ESEGUITA";
	public static final String ANAGR_IMPEGNO_RIPRISTINO_RICHIESTO= "IMPEGNO DI RIPRISTINO RICHIESTO";
	public static final String ANAGR_IMPEGNO_RIPRISTINO_ESEGUITO = "IMPEGNO DI RIPRISTINO ESEGUITO";
	public static final String ANAGR_INADEMPIENZA = "INADEMPIENZA";
	public static final String ANAGR_NEGLIGENZA = "NEGLIGENZA";
	public static final String ANAGR_INTENZIONALITA = "INTENZIONALITA";
	public static final String ANAGR_REITERAZIONE = "REITERAZIONE";
	public static final String ANAGR_PROGR_REITERAZIONE = "PROGR. REITERAZIONE";
	public static final String ANAGR_INADEMPIENZA_GRAVE = "INADEMPIENZA GRAVE";
	public static final String ANAGR_PORTATA = "PORTATA";
	public static final String ANAGR_GRAVITA = "GRAVITA";
	public static final String ANAGR_DURATA = "DURATA";
	public static final String ANAGR_AMMONIZIONE = "AMMONIZIONE";
	public static final String ANAGR_AUTODICH_TRATT_CHIMICO = "AUTODICH TRATTAMENTO CHIMICO";
	public static final String ANAGR_AUTODICH_GASOLIO = "AUTODICH GASOLIO";
	public static final String ANAGR_SEGNALAZIONE = "SEGNALAZIONE";
	public static final String ANAGR_CAMPIONE = "CAMPIONE";
	public static final String ANAGR_TIPO_CONTROLLO = "TIPO CONTROLLO";
	public static final String ANAGR_TIPO_CAMPIONE = "TIPO CAMPIONE";
	public static final String ANAGR_DOMINIO_CAMPIONE = "DOMINIO CAMPIONE";
	public static final String ANAGR_CAMP_CATEGORIA= "CATEGORIA CAMPIONE";
	public static final String ANAGR_CAMP_STATO_DOMANDA_OPPAB= "STATO DOMANDA OPPAB";
	public static final String ANAGR_CAMP_ORIGINE= "ORIGINE CAMPIONE";
	public static final String ANAGR_CAMP_BOVINI= "CAMPIONE - BOVINI";
	public static final String ANAGR_CAMP_OVICAPRINI= "CAMPIONE - OVICAPRINI";
	public static final String ANAGR_PROGRESSIVO_ACCERTAMENTO = "PROGRESSIVO ACCERTAMENTO";
	public static final String ANAGR_COD_ATTI_COND = "CODICI-ATTI CONDIZIONALITA";
	public static final String ANAGR_A1 = "A1 PRESENTE";
	public static final String ANAGR_A5 = "A5 PRESENTE";
	public static final String ANAGR_SPECIE_PRESENTE = "SPECIE PRESENTE";
	public static final String ANAGR_SANZIONE_ANNULLATA = "SANZIONE ANNULLATA";
	public static final String ANAGR_TIPO_PREAVVISO = "TIPO PREAVVISO";
	public static final String ANAGR_PERSONA_PRESENTE = "PERSONA PRESENTE";
	public static final String ANAGR_TIPO_CONTROLLO_COND = "TIPO CONTROLLO COND";
	public static final String ANAGR_RUOLO_PERSONA_PRESENTE = "RUOLO PERSONA PRESENTE CONTROLLO";
	
	/* chiavi di ricerca */
	public static final String SEARCH_CUAA = "cuaa";
	public static final String SEARCH_RAG_SOC = "ragione_sociale";
	public static final String SEARCH_DOMANDA = "domanda";
	public static final String SEARCH_MISURA = "misura";
	public static final String SEARCH_CAMPAGNA = "campagna";
	public static final String SEARCH_CAMPIONE = "campione";
	public static final String SEARCH_SEGNALAZIONE = "segnalazione";
	
	/* UI ACTION */
	public static final String ACTION_INSERT = "aggiungi";
	public static final String ACTION_INSERT_ATTO = "aggiungiAtto";
	public static final String ACTION_MODIFY = "modifica";
	public static final String ACTION_MODIFY_ATTO = "modificaAtto";
	public static final String ACTION_VIEW = "visualizza";
	public static final String ACTION_IMPORT = "import";
	public static final String ACTION_EDIT_ZOOT_CONTR_AMM_BOVINI = "edit_contr_amm_bovini";
	public static final String ACTION_EDIT_ZOOT_CONTR_AMM_OVICAPRINI = "edit_contr_amm_ovicaprini";
	public static final String ACTION_EDIT_ZOOT_CONTR_COND = "edit_contr_cond";
	public static final String ACTION_EDIT_ZOOT_ESITO_AMM_BOVINI = "edit_esito_amm_bovini";
	public static final String ACTION_EDIT_ZOOT_ESITO_AMM_OVICAPRINI = "edit_esito_amm_ovicaprini";
	public static final String ACTION_EDIT_DATI_CONTROLLO = "edit_dati_controllo";
	
	
	/* VARIE */
	public static final int QUERY_TOP_K = 100;
	public static final int IMPORT_NUM_ROW_MAX = 200;
	public static final int CAMPAGNA_FINE_VAL = 9999;
	public static final String AZIENDA_SORGENTE_USER = "USER";
	public static final String AZIENDA_SORGENTE_FASCICOLO = "FASCICOLO";
	
	/* APPLICATION SETTINGS */
	public static final String PROPERTIES_FILE  = "stat.properties";
	public static final String SET_LDAP_SERVER_URL = "user.OppabLdapUserSearch.ldapServerUrl";
	public static final String SET_LDAP_DOMAIN = "user.OppabLdapContextSource.domain";
	public static final String SET_LDAP_SEARCH_SCOPE = "user.OppabLdapUserSearch.searchScope";
	public static final String SET_LDAP_SEARCH_FILTER = "user.OppabLdapUserSearch.filter";
	public static final String SET_LDAP_COMPLETE_NAME = "user.OppabLdapUserSearch.completeName";
	public static final String SET_JBOSS_TMP_FOLDER = "file.jboss.tmp_folder";
	public static final String SET_JBOSS_TMP_FOLDER_DEVELUX = "file.jboss.tmp_folder.develux";
	public static final String SET_JBOSS_TMP_FOLDER_VALAR = "file.jboss.tmp_folder.valar";
	public static final String SET_JBOSS_TMP_FOLDER_VALAR_DEV = "file.jboss.tmp_folder.valar_dev";
	public static final String HEADER_PRO = "/images/header_pro.png";
	public static final String HEADER_DEV = "/images/header_dev.png";
	
	
	/* MESSAGGI UTENTE */
	public static final String MSG_INSERT_OK = "Inserimento avvenuto con successo!";
	public static final String MSG_MODIFY_OK = "Modifica avvenuta con successo!";
	public static final String MSG_MODIFY_KO_FASCICOLO = "Azienda proveniente dal fascicolo: modifica non consentita";
	public static final String MSG_MODIFY_KO_UTENTE = "Modifica eseguita con parziale successo. Alcuni utenti non risultano presenti su database";
	public static final String MSG_DELETE_OK = "Cancellazione avvenuta con successo!";
	public static final String MSG_DELETE_KO_FASCICOLO = "L'azienda proviene dal fascicolo. Cancellazione non consentita";
	public static final String MSG_OPERAZIONE_NON_ESEGUITA = "Non é stato possibile eseguire l'operazione";
	public static final String MSG_RIGA_NON_TROVATA = "La riga selezionata non é stata trovata su database";
	public static final String MSG_RIGA_TROVATA = "La riga risulta già presente. Non é possibile completare l'inserimento";
	public static final String MSG_CHECK_CAMPAGNA = "Campagna non valorizzata correttamente";
	public static final String MSG_CHECK_CUAA = "CUAA non valorizzato correttamente";
	public static final String MSG_CHECK_CUAA_LENGTH = "Il CUAA deve avere una lunghezza compresa tra gli 11 ed i 16 caratteri";
	public static final String MSG_CHECK_CUAA_NO_CAMPIONE = "Il cuaa non risulta a campione per la campagna selezionata";
	public static final String MSG_CHECK_CUAA_CAMPAGNA_PRESENTI = "Il CUAA risulta già inserito per la campagna indicata.";
	public static final String MSG_CHECK_CUAA_CAMPAGNA_COD_COND_ATTO_PRESENTI = "Il CUAA, la campagna, il codice di condizionalità, l'atto e il tipo controllo risultano già presenti";
	public static final String MSG_CHECK_CUAA_PRESENTE = "Il CUAA risulta già inserito in anagrafica.";
	public static final String MSG_CHECK_CUAA_MANCANTE = "Il CUAA non risulta presente in anagrafica.";
	public static final String MSG_CHECK_DATA_INIZIO_FINE_CONTROLLO = "La data di fine controllo non può essere precedente a quella di inizio";
	public static final String MSG_CHECK_TIPO_CONTROLLO = "Tipo controllo non valorizzato correttamente";
	public static final String MSG_CHECK_DOMANDA_NULLA = "Il numero domanda deve essere valorizzato";
	public static final String MSG_CHECK_DATA_NULLA = "La Data del Controllo deve essere valorizzata";
	public static final String MSG_CHECK_DOMANDA_MISURA_DU_ERRATA = "Possono essere inserite solo domande di misura DU";
	public static final String MSG_CHECK_DOMANDA_MANCANTE = "La domanda non risulta presente in anagrafica.";
	public static final String MSG_CHECK_DOMANDA_PRESENTE = "La domanda risulta già presente in anagrafica.";
	public static final String MSG_CHECK_DOMANDA_MISURA_ERRATA = "La domanda non risulta della misura corretta";
	public static final String MSG_CHECK_DOMANDA_MISURA_NON_CORRETTA = "La domanda non risulta avere la misura indicata";
	public static final String MSG_CHECK_DOMANDA_INTERVENTO_MANCANTE = "La domanda non risulta possedere l'intervento corretto";
	public static final String MSG_CHECK_DOMANDA_MISURA_PRESENTI = "La domanda e la misura indicate risultano già presenti";
	public static final String MSG_CHECK_DOMANDA_INTERVENTO_PRESENTI = "La domanda e l'intervento indicati risultano già presenti";
	public static final String MSG_CHECK_DOMANDA_INTERVENTO_SOTTOINTERVENTO_PRESENTI = "La domanda, l'intervento e il sottointervento indicati risultano già presenti";
	public static final String MSG_CHECK_DOMANDA_INTERVENTO_SOTTOINTERVENTO_MANCANTI = "La domanda non dispone dell'intervento o del sottointervento indicati";
	public static final String MSG_CHECK_DOMANDA_NO_CANC = "La domanda non può essere cancellata.";
	public static final String MSG_NO_RIGA_SELEZIONATA = "Non é stata selezionata nessuna riga!";
	public static final String MSG_CHECK_RAG_SOC = "Ragione sociale non valorizzata correttamente";
	public static final String MSG_CHECK_PROVINCIA = "Provincia non valorizzata correttamente";
	public static final String MSG_CHECK_MISURA_NULLA = "La misura deve essere valorizzata";
	public static final String MSG_CHECK_INTERVENTO_NULLO = "L'intervento deve essere valorizzato";
	public static final String MSG_CHECK_SOTTOINTERVENTO_NULLO = "Il sottointervento deve essere valorizzato";
	public static final String MSG_CHECK_IMPEGNO_NULLO = "L'impegno deve essere valorizzato";
	public static final String MSG_CHECK_COD_COND_NULLO = "Il codice di condizionalità deve essere valorizzato";
	public static final String MSG_CHECK_ATTO_COND_NULLO = "L'atto di condizionalità deve essere valorizzato";
	public static final String MSG_CHECK_UBA_DICH = "Uba richieste: valore non numerico";
	public static final String MSG_CHECK_UBA_ACC = "Uba accertate: valore non numerico";
	public static final String MSG_CHECK_UBA_DIFF = "Differenza uba: valore non numerico";
	public static final String MSG_CHECK_UBA_IRREG = "UBA Irregolari: valore non numerico";
	public static final String MSG_CHECK_CAPI_DICH = "Capi richiesti: valore non numerico";
	public static final String MSG_CHECK_CAPI_ACC = "Capi accertati: valore non numerico";
	public static final String MSG_CHECK_DATA_CONTROLLO = "La data del controllo deve essere successiva alla data del campione";
	public static final String MSG_CHECK_PER_RID_NUMERIC_VALUE = "La percentuale di riduzione deve essere un valore numerico";
	public static final String MSG_CHECK_RESP_CONTROLLO_NULLO = "Responsabile controllo non valorizzato";
	public static final String MSG_CHECK_DATA_1_CONTROLLO_NULLO = "Data primo controllo non valorizzata";
	public static final String MSG_CHECK_INFRAZIONE_NULLO = "Infrazione non valorizzato";
	public static final String MSG_CHECK_DATA_2_CONTROLLO_ERRATA = "La data del secondo controllo deve essere successiva a quella del primo controllo";
	public static final String MSG_CHECK_ATTO_COND = "L'atto di condizionalità selezionato non corrisponde al codice scelto";
	public static final String MSG_CHECK_IMP_RICH = "Importo richiesto: valore non numerico";
	public static final String MSG_CHECK_IMP_LIQ = "Importo Liquidabile: valore non numerico";
	public static final String MSG_CHECK_IMP_RID = "Importo Riduzione: valore non numerico";
	public static final String MSG_CHECK_USER_FOUND = "Utente già presente nel sistema";
	public static final String MSG_CHECK_ANAGR_NOME_NULLO = "Il nome dell'anagrafica deve essere valorizzato";
	public static final String MSG_CHECK_ANAGR_VALORE_NULLO = "Il valore dell'anagrafica deve essere valorizzato";
	public static final String MSG_CHECK_CAMPAGNA_INIZIO_VAL = "La campagna di inizio validità deve essere successiva al 2008";
	public static final String MSG_CHECK_ANAGR_PK_PRESENTE = "Il valore indicato è già presente per l'anagrafica selezionata";
	public static final String MSG_CHECK_MODIFICA_FATTA = "Non é stata effettuata nessuna modifica. La riga non verrà salvata";
	public static final String MSG_CHECK_ANAGR_COD_COND_NULLO = "Il codice di condizionalità non può essere nullo";
	public static final String MSG_CHECK_ANAGR_ATTO_COND_NULLO = "I'atto di condizionalità non può essere nullo";
	public static final String MSG_CHECK_CARICO_ALPEGGIO_NUMERIC_VALUE = "Il carico di alpeggio deve essere un valore numerico";
	public static final String MSG_CHECK_SUP_DICH_NUMERIC_VALUE = "La superficie dichiarata deve essere un valore numerico";
	public static final String MSG_CHECK_SUP_ACC_NUMERIC_VALUE = "La superficie accertata deve essere un valore numerico";
	public static final String MSG_CHECK_DIFF_NUMERIC_VALUE = "La differenza deve essere un valore numerico";
	public static final String MSG_CHECK_PERC_SCOST_NUMERIC_VALUE = "La percentuale di scostamento deve essere un valore numerico";
	public static final String MSG_CHECK_ALTITUDINE_NUMERIC_VALUE = "L'altitudine azienda deve essere un valore numerico";
	public static final String MSG_CHECK_CAR_BEST_MAX_NUMERIC_VALUE = "Il carico bestiame massimo deve essere un valore numerico";
	public static final String MSG_CHECK_CAR_BEST_ATT_NUMERIC_VALUE = "Il carico bestiame attuale deve essere un valore numerico";
	public static final String MSG_CHECK_UBA_ECC_NUMERIC_VALUE = "Il valore delle uba eccessive deve essere numerico";
	public static final String MSG_CHECK_TOLL_CAR_BEST_NUMERIC_VALUE = "Il valore della tolleranza di carico bestiame deve essere numerico";
	public static final String MSG_CHECK_PRESENZA_214 = "Il cuaa non ha presentato domanda 214 per la campagna indicata";
	public static final String MSG_CHECK_NOTE_TOO_LONG = "Il valore nel campo note é troppo lungo.";
	public static final String MSG_CHECK_CAMP_CATEGORIA = "La categoria deve essere presente";
	public static final String MSG_CHECK_CAMP_ORIGINE = "L'origine campione deve essere presente";
	public static final String MSG_CHECK_CAMP_TIPO = "Il tipo campione deve essere presente";	
	public static final String MSG_CHECK_CAMP_DATA = "La data del campione deve essere presente";
	public static final String MSG_CHECK_COND_TIPO_CONTROLLO = "Colonna Tipo Controllo deve essere valorizzata";
	public static final String MSG_CHECK_PERC_RID_PREMIO_TRASPORTO = "Perc. Riduzione Premio Trasporto: valore non numerico";
	public static final String MSG_CHECK_PERC_RID_PREMIO_TRASPORTO_EMPTY = "Perc. Riduzione Premio Trasporto: deve essere valorizzato se esito negativo";
	public static final String MSG_CHECK_IMPORTO_RID_RINUNCIA_INSILATO = "Importo Rid. Rinuncia Insilato: valore non numerico";	
	public static final String MSG_CHECK_IMPORTO_RID_RINUNCIA_INSILATO_EMPTY = "Importo Rid. Rinuncia Insilato: deve essere valorizzato se esito negativo";	
	public static final String MSG_NO_DATA_DEFAULT = "Per visualizzare i dati effettuare una ricerca";
	public static final String MSG_NO_DATA_FIND = "Nessun dato trovato per la ricerca effettuata";
	public static final String MSG_CHECK_DOMANDA_NON_A_CAMPIONE = "CUAA non a campione per DU o Domanda non corretta";
	public static final String MSG_CHECK_DATA_CONTROLLO_IMPEGNI_RMFITFER_NULLO = "Data del controllo non valorizzata";
	
	
	/* MESSAGGI WARNINGS */
	public static final String REITER_SI_PROGR_REIT_VUOTO = "Il campo Reiterazione è impostato a \"SI\" mentre il progressivo della reiterazione è vuoto";
	public static final String REITER_NO_PROGR_REIT_VALORIZ = "Il campo Reiterazione è impostato a \"NO\" mentre il progressivo della reiterazione è valorizzato";
	public static final String PROGR_REIT_MAGG2_AND_PORT_GRAV_DUR_VALORIZ = "Se il progressivo della reiterazione è >=2, allora non è previsto un valore per Portata, Portata Note, Gravità, Gravità Note, Durata, Durata Note";
	public static final String ESCL_SI_NOTE_NO = "Esclusione impostato a \"SI\" ma nessune valore per il campo Esclusione note";
	public static final String ESCL_NO_NOTE_SI = "Esclusione impostato a \"NO\" ma presente un valore per il campo Esclusione note";
	public static final String ESCL_SI_AND_PORT_GRAV_DUR_VALORIZ = "Esclusione impostato a \"SI\", non è previsto un valore per Portata, Portata Note, Gravità, Gravità Note, Durata, Durata Note";
	public static final String PERC_RID_EMPTY = "Il campo Percentuale riduzione deve essere valorizzato";
	public static final String APPR_NO_NUM_DECRETO_SI = "Campo Approvazione impostato a \"NO\" ma presente un valore per il campo Numero decreto";
	public static final String APPR_NO_DATA_DECRETO_SI = "Campo Approvazione impostato a \"NO\" ma presente un valore per il campo Data decreto";
	public static final String NOTE_VUOTE = "Il campo note deve essere compilato";
	public static final String INADEM_SI_REIT_NO = "Il campo inadempienza grave è impostato a SI e il campo Reiterazione impostato a NO";
	public static final String INADEM_SI_PORT_GRAV_DUR_NO_5 = "Il campo inadempienza grave è impostato a \"SI\", è previsto un valore uguale a 5 per Portata, Gravità e Durata";
	public static final String RESP_CONTR_SSVV_TIPO_CONTR_NO_CAMPCOND = "Il campo Resp. Controllo è impsotato a \"SSVV\" e il tipo controllo diverso da \"1 - Controlli campione condizionalità\" ";
	public static final String RESP_CONTR_OP_TIPO_CONTR_NO_CAMPCOND_OR_ALTRICONTR = "Il campo Resp. Controllo è impsotato a \"OP\" e il tipo controllo diverso da \"2 - Controlli campione di ammissibilità\" o \"4-Altri Controlli\" ";
	public static final String AZ_CORR_NON_RICHIESTA_NO_DATA_2CONTROLLO = "Il campo Azione Correttiva Eseguita è diverso da \"NON RICHIESTA\", è previsto pertanto un valore per la data del secondo controllo";
	public static final String IM_RIPR_ESEGUITO_NON_RICHIESTA_NO_DATA_2CONTROLLO = "Il campo Impegno di Ripristino Eseguito è diverso da \"NON RICHIESTO\", è previsto pertanto un valore per la data del secondo controllo";
	public static final String NEGL_SI_INT_SI = "Se il campo Negligenza è impostato a \"SI\" allora l'Intenzionalità deve essere impostato a \"NO\"";
	public static final String NEGL_NO_INT_NO = "Se il campo Negligenza è impostato a \"NO\" allora l'Intenzionalità deve essere impostato a \"SI\"";
	public static final String NEGL_NO_INADEMP_MIN_SI = "Se il campo Negligenza è impostato a \"NO\" allora l'Indadempienza minore deve essere impostato a \"NO\"";
	public static final String AZ_CORR_NO_NON_RICHIESTA_INADEMP_MIN_NO = "Se il campo Azione Correttiva Eseguita è diverso da \"NON RICHIESTA\", allora l'Indadempienza minore deve essere impostato a \"SI\"";
	public static final String AZ_CORR_NON_RICHIESTA_INADEMP_MIN_SI = "Se il campo Azione Correttiva Eseguita è impostato a \"NON RICHIESTA\", allora l'Indadempienza minore deve essere impostato a \"NO\"";
	public static final String AMM_SI_REIT_NO = "Se il campo Ammonizione è impostato a \"SI\" allora anche la Reiteraione deve essere impostata a \"SI\"";
	public static final String INADMIN_SI_AND_PORT_GRAV_DUR_DIV_1 = "Se l'Inadempienza Min. è impostata a \"SI\", allora i campi Portata, Gravità e Durata devono valere 1";
	public static final String INTENZ_SI_PROGR_ACCERT_INTENZ_VUOTO = "Se il campo Intenzionalità è impostato a \"SI\" allora è previsto un valore anche per Progr. Accertamento Intenzionalità";
	public static final String INTENZ_NO_PROGR_ACCERT_INTENZ_NON_VUOTO = "Se il campo Intenzionalità è impostato a \"NO\" allora non è previsto alcun valore per Progr. Accertamento Intenzionalità";
	public static final String INTENZ_SI_NOTE_VUOTO = "Se il campo Intenzionalità è impostato a \"SI\" allora è previsto un valore anche per Intenzionalità note";
	public static final String INTENZ_NO_NOTE_NON_VUOTO = "Se il campo Intenzionalità è impostato a \"NO\" allora non è previsto alcun valore per Intenzionalità note";
	public static final String INTENZ_SI_AND_PORT_GRAV_DUR_VALORIZ = "Se il campo Intenzionalità è impostato a \"SI\", non è previsto un valore per Portata, Portata Note, Gravità, Gravità Note, Durata, Durata Note";
	public static final String PERC_RID_FER_EMPTY = "Il campo Percentuale riduzione FER deve essere valorizzato";

	
	
	
	/* NOME DEL FILE DA ESPORTARE */
	public static final String FILE_UBA = "UBA.xls";
	public static final String FILE_UBA_AMM = "UBA_AMMISSIBILITA.xls";
	public static final String FILE_TUTELA_ACQUE = "TUTELA_ACQUE.xls";
	public static final String FILE_ESITO_IMPEGNI = "ESITO_IMPEGNI.xls";
	public static final String FILE_ESITO_IMPEGNI_EXTRA = "ESITO_IMPEGNI_EXTRA.xls";
	public static final String FILE_ESITO_RMFITFER = "ESITO_RMFITFER.xls";
	public static final String FILE_ESITO_RMFITFER_EXTRA = "ESITO_RMFITFER_EXTRA.xls";
	public static final String FILE_ESITO_SUPERFICI = "ESITO_SUPERFICI.xls";
	public static final String FILE_ESITO_COND_AZIENDA = "ESITO_COND_AZIENDA.xls";
	public static final String FILE_ESITO_COND_ATTO = "ESITO_COND_ATTO.xls";
	public static final String FILE_ESITO_COND_MATR = "ESITO_COND_MATR.xls";
	public static final String FILE_ESITO_COND_ATTO_VET = "ESITO_COND_ATTO_VET.xls";
	public static final String FILE_CONTROLLO = "CONTROLLO.xls";
	public static final String FILE_DOMANDA = "DOMANDA.xls";
	public static final String FILE_CAMPIONE = "CAMPIONE.xls";
	public static final String FILE_CAMPIONE_VET = "CAMPIONE_VET.xls";
	public static final String FILE_AZIENDA = "AZIENDA.xls";
	public static final String FILE_NATURA_2000 = "NATURA_2000.xls";
	public static final String FILE_CAPI_ALLEVATI = "CAPI_ALLEVATI.xls";
	public static final String FILE_MATR_AMM_IDEALE = "MATR_AMMISS_IDEALE.xls";
	public static final String FILE_LOG = "LOG.xls";
	public static final String FILE_LISTA_UTENTI = "LISTA_UTENTI.xls";
	public static final String FILE_ESITO_ART_68 = "ESITO_ART_68.xls";
		
	
	/* DATI CONDIZIONALITA DA VISUALIZZARE */
	public static final String DATI_COND_ESITI = "ESITI CONDIZIONALITA";
	public static final String DATI_COND_MATR_AMM_IDEALE = "MATRICE AMMISSIBILITA IDEALE";
	
	/* TABELLE */
	public static final int TABELLA_AZIENDA = 1;
	public static final int TABELLA_DOMANDA = 2;
	public static final int TABELLA_CAMPIONE = 3;
	public static final int TABELLA_CONTROLLO = 4;
	public static final int TABELLA_ESITO_COND_AZIENDA = 5;
	public static final int TABELLA_ESITO_COND_ATTO = 6;
	public static final int TABELLA_NATURA_2000 = 7;
	public static final int TABELLA_CAPI_ALLEVATI = 8;
	public static final int TABELLA_ESITO_IMPEGNI = 9;
	public static final int TABELLA_ESITO_SUP = 10;
	public static final int TABELLA_ESITO_UBA = 11;
	public static final int TABELLA_ESITO_TUTELA_ACQUE = 12;
	public static final int TABELLA_ESITO_ART_68 = 13;
	public static final int TABELLA_ESITO_IMPEGNI_EXTRACAMPIONE = 14;
	public static final int TABELLA_ESITO_RMFITFER = 15;
	public static final int TABELLA_ESITO_RMFITFER_EXTRACAMPIONE = 16;
	public static final int TABELLA_ESITO_UBA_AMM = 17;

	
	/* LAYOUT */
	public static final String LAYOUT_CAMPIONE_ALL = "Campione completo";
	public static final String LAYOUT_CAMPIONE_VET = "Campione veterinari";
	public static final String LAYOUT_COND_ATTO_ESITO = "Esiti Atti di condizionalita";
	public static final String LAYOUT_COND_ATTO_CONTR = "Matrice Atti controllati";
	public static final String LAYOUT_COND_ATTO_CONTR_VET = "Atti controllati VET";
	
	/* VALORI CABLATI */
	public static final String VAL_CAMP_CAT_AMM_IMP = "AMM IMPEGNI";
	public static final String VAL_CAMP_CAT_AMM_ZOOT = "AMM ZOOTECNIA";
	public static final String VAL_CAMP_CAT_COND_SUP = "COND SUPERFICI";
	public static final String VAL_CAMP_CAT_COND_ZOOT = "COND ZOOTECNIA";
	public static final String VAL_CAMP_CAT_COND_EXTRA = "COND EXTRA CAMPIONE";
	public static final String VAL_CAMP_ORIG_DA_ROMA = "DA ROMA";
	public static final String VAL_CAMP_ORIG_AGG = "AGGIUNTIVO";
	public static final String VAL_CAMP_DOMINIO_CAMP_NO_CAMPIONE = "0 - Non a campione";
	public static final String VAL_CAMP_DOMINIO_CAMP_PER_VET = "1 - Tutti gli atti/norme applicabili all azienda";
	public static final String VAL_CAMP_DOMINIO_CAMP_PER_NO_VET = "2 - un sottoinsieme tra tutti gli atti/norme applicabili all azienda";
	public static final String VAL_CAMP_DOMINIO_CAMP_ALTRI_CONTR = "4 - Altri controlli";
	public static final String VAL_CAMP_ORIG_CAMP_VET_DA_DU = "VET DA DU";
	public static final String VAL_CAMP_TIPO_CAMP_NO_CAMPIONE = "NON CAMPIONE";
	public static final String VAL_CAMP_STATO_DOMANDA_OPPAB_A_CAMPIONE = "A CAMPIONE";
	public static final String VAL_CAMP_ORIGINE_CAMP_SEG_VET = "SEG VET";
	public static final String VAL_RESP_CONTR_OP = "OP";
	public static final String VAL_RESP_CONTR_SSVV = "SSVV";
	public static final String VAL_SI = "SI";
	public static final String VAL_NO = "NO";
	public static final String VAL_20 = "20";
	public static final String VAL_100 = "100";

	/* SERVER NAME */
	public static final String SERVER_DEVELUX = "develux";
	public static final String SERVER_VALAR = "valar";
	public static final String SERVER_VALAR_DEV = "valar-dev";
	public static final String SERVER_VALAR1_DEV = "valar1-dev";
	public static final String SERVER_VALAR2_DEV = "valar2-dev";
	
	/**/
	public static final String STATO_COMPILATO = "COMPILATO";
	public static final String STATO_NON_COMPILATO = "NON COMPILATO";
	
	

	
	
}
