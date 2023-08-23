package it.bz.prov.esiti.beans;

import it.bz.prov.esiti.util.Costanti;
import it.bz.prov.esiti.util.Utils;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
// GIMS
//import org.springframework.stereotype.Component;

/**
 * Bean UI (user interface) per la gestione del cambio dei tab e delle azioni correlate
 * 
 * @author bpettazzoni
 *
 */
// GIMS
//@Component("TPBean")
@ManagedBean(name = "TPBean")
@SessionScoped
public class TPBean implements Serializable
{	
	// indici di ogni tab (se si cambia posizione al tab bisogna cambiare l'indice)
	public static final int TAB_AZIENDA = 0;
	public static final int TAB_DOMANDA = 1;
	public static final int TAB_CAMPIONE = 2;
	public static final int TAB_CONDIZIONALITA = 3;
	public static final int TAB_IMPEGNI = 4;
	public static final int TAB_RMFITFER = 5;
	public static final int TAB_SUPERFICI = 6;
	public static final int TAB_UBA = 7;
	public static final int TAB_TUTELA_ACQUE = 8;
	public static final int TAB_ZOOTECNIA = 9;
	public static final int TAB_ART68 = 10;
	
	public static final int TAB_USER = 0;

	/**
	 * costruttore
	 */
	public TPBean()
	{
	}
	
	
    private static final long serialVersionUID = 1L;
    
    private int selectedIndex = 0;
    private int selectedIndexAdmin = 0;
    
	public synchronized int getSelectedIndex() {
		return selectedIndex;
	}

	public synchronized void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	/**
	 * Metodo in ascolto dell'evento di cambio di selezione del tab
	 * @param ev evento cambio di selezione
	 */
	public void processSelectionChange(org.openfaces.event.SelectionChangeEvent ev) {
		Utils.getLog().info("****************************** processSelectionChange");
		System.out.println("Old index: " + ev.getOldIndex() + " New index: " + ev.getNewIndex());
		
		// per discriminare i comportamente da tenere per ogni tab e per ogni finestra 
		//(amministrazione e visualizzazione) fare riferimento al codice sotto
		String view_name = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		System.out.println("************ view:" + FacesContext.getCurrentInstance().getViewRoot().getViewId());		
		
		// gestione delll'operazione da fare sulla base della pagina in cui ci si trova (index o admin)
		if(view_name.contains("index.xhtml")) 
		{
			setSelectedIndex(ev.getNewIndex());
			gestioneTabIndexPage(ev.getNewIndex(), ev.getOldIndex());
		}
		else if(view_name.contains("admin.xhtml")) 
		{
			setSelectedIndexAdmin(ev.getNewIndex());
			gestioneTabAdminPage(ev.getNewIndex());
		}
	}
	
	
	/**
	 * Funzione per la gestione del comportamento da tenere al cambio del tab 
	 * della pagina di aministrazione
	 * @param indexPage
	 */
	private void gestioneTabAdminPage(int indexPage)
	{
		switch (indexPage) 
		{
			case TAB_USER:
			{
				UtenteUIBean utentiBean = findBean("usersBean");
				utentiBean.loadData();
			}
		}
	}
	
	/**
	 * Funzione per la gestione del comportamento da tenere al cambio dei tab 
	 * della pagina di gestione dati
	 * @param indexPage
	 * @param oldIndexPage
	 */
	private void gestioneTabIndexPage(int indexPage, int oldIndexPage)
	{	
//		FacesContext context = FacesContext.getCurrentInstance();  	
//		_domandaBean= (DomandaBean) ((HttpServletRequest)context.getExternalContext().getRequest()).getAttribute("domandaBean");
//		_domandaBean = findBean("domandaBean");
		SearchBean searchBean = findBean("searchBean");
		boolean search = searchBean.isSearchActive();
//		System.out.println("gestioneTabIndexPage - Ricerca: " + search);
		//pulizia dello stato del bean del pagina abbandonata
		clearOldPage(oldIndexPage);
		switch (indexPage) 
		{
			case TAB_AZIENDA:
			{
				AziendaUIBean aziendaBean = findBean("aziendaBean");
				if(search) searchBean.doSearch();
				//else aziendaBean.loadData();
				// cancello le selezioni precedenti
				aziendaBean.clearSelectedValue();
				break;
			}
			case TAB_DOMANDA:
			{
				DomandaUIBean domandaBean = findBean("domandaBean");
				if(search) searchBean.doSearch();
				//else domandaBean.loadData();
				// cancello le selezioni precedenti
				domandaBean.clearSelectedValue();
				break;
			}
			case TAB_CAMPIONE:
			{
				CampioneUIBean campioneBean = findBean("campioneBean");
				ControlloUIBean controlloBean = findBean("controlloBean");				
				if(search) searchBean.doSearch();
//				else 
//				{
//					campioneBean.loadData();
//					controlloBean.loadData();
//				}
				campioneBean.clearSelectedValue();
				controlloBean.clearSelectedValue();
				break;
			}
			case TAB_CONDIZIONALITA:
			{
				CondizionalitaUIBean condBean = findBean("esitoCondBean");
				if(search) searchBean.doSearch();
				//else condBean.loadData();
				condBean.clearSelectedValue();
				break;
			}
			case TAB_IMPEGNI:
			{
				EsitoImpegniUIBean esitoImpegniBean = findBean("esitoImpegniBean");
				EsitoImpegniExtraUIBean esitoImpegniExtraBean = findBean("esitoImpegniExtraBean");
				if(search) searchBean.doSearch();
				esitoImpegniBean.clearSelectedValue();
				esitoImpegniExtraBean.clearSelectedValue();
				break;
			}
			case TAB_RMFITFER:
			{
				EsitoImpegniRMFITFERUIBean esitoImpegniRMFITFERBean = findBean("esitoImpegniRMFITFERBean");
				EsitoImpegniRMFITFERExtraUIBean esitoImpegniRMFITFERExtraBean = findBean("esitoImpegniRMFITFERExtraBean");
				
				if(search) searchBean.doSearch();
				esitoImpegniRMFITFERBean.clearSelectedValue();
				esitoImpegniRMFITFERExtraBean.clearSelectedValue();
				break;
			}
			case TAB_SUPERFICI:
			{
				EsitoSuperficiUIBean esitoSuperficiBean = findBean("esitoSupBean");
				if(search) searchBean.doSearch();
				//else esitoSuperficiBean.loadData();
				esitoSuperficiBean.clearSelectedValue();
				break;
			}
			case TAB_UBA:
			{
				UbaUIBean ubaBean = findBean("ubaBean");
				UBAAmmissibilitaUIBean ubaAmmBean = findBean("ubaAmmBean");
				if(search) searchBean.doSearch();
				//else ubaBean.loadData();
				ubaBean.clearSelectedValue();
				ubaAmmBean.clearSelectedValue();
				break;
			}
			case TAB_TUTELA_ACQUE:
			{
				TutelaAcqueUIBean tutelaAcqueBean = findBean("tutelaAcqueBean") ;
				if(search) searchBean.doSearch();
				//else tutelaAcqueBean.loadData();
				tutelaAcqueBean.clearSelectedValue();
				break;
			}
			case TAB_ZOOTECNIA:
			{
				EsitoZootecniaUIBean esitoZootBean = findBean("esitoZootBean") ;
				if(search) searchBean.doSearch();
				//else tutelaAcqueBean.loadData();
				esitoZootBean.clearSelectedValue();
				break;
			}
			case TAB_ART68:
			{
				EsitoArt68UIBean esitoArt68Bean = findBean("esitoArt68Bean");
				if(search) searchBean.doSearch();
				//else esitoImpegniBean.loadData();
				esitoArt68Bean.clearSelectedValue();
				break;
			}
		}
	}
	
	/**
	 * setta la action della pagina abbandonata
	 * in questo modo quando ci ritorno riparto dalla visualizzazione della tabella e non dall'ultimo
	 * stato visualizzato (es. se stavo editando un record 
	 * @param indexOldPage
	 */
	private void clearOldPage(int indexOldPage)
	{
		switch (indexOldPage) 
		{
			case TAB_AZIENDA:
			{
				AziendaUIBean aziendaBean = findBean("aziendaBean");
				aziendaBean.set_action(Costanti.ACTION_VIEW);
				aziendaBean.clearData();
				break;
			}
			case TAB_DOMANDA:
			{
				DomandaUIBean domandaBean = findBean("domandaBean");
				domandaBean.set_action(Costanti.ACTION_VIEW);
				domandaBean.clearData();
				break;
			}
			case TAB_CAMPIONE:
			{
				CampioneUIBean campioneBean = findBean("campioneBean");
				campioneBean.set_action(Costanti.ACTION_VIEW);
				campioneBean.clearData();
				ControlloUIBean controlloBean = findBean("controlloBean");				
				controlloBean.set_action(Costanti.ACTION_VIEW);
				controlloBean.clearData();
				break;
			}
			case TAB_CONDIZIONALITA:
			{
				CondizionalitaUIBean condBean = findBean("esitoCondBean");
				condBean.set_action(Costanti.ACTION_VIEW);
				condBean.clearData();
				MatrAmmIdealeUIBean matrAmmBean = findBean("matrAmmIdealeBean");
				matrAmmBean.set_action(Costanti.ACTION_VIEW);
				matrAmmBean.clearData();
				break;
			}
			case TAB_IMPEGNI:
			{
				EsitoImpegniUIBean esitoImpegniBean = findBean("esitoImpegniBean");
				EsitoImpegniExtraUIBean esitoImpegniExtraBean = findBean("esitoImpegniExtraBean");
				esitoImpegniBean.set_action(Costanti.ACTION_VIEW);
				esitoImpegniExtraBean.set_action(Costanti.ACTION_VIEW);
				esitoImpegniBean.clearData();
				esitoImpegniExtraBean.clearData();
				break;
			}
			case TAB_RMFITFER:
			{
				EsitoImpegniRMFITFERUIBean esitoImpegniRMFITFERBean = findBean("esitoImpegniRMFITFERBean");
				esitoImpegniRMFITFERBean.set_action(Costanti.ACTION_VIEW);
				esitoImpegniRMFITFERBean.clearData();
				
				EsitoImpegniRMFITFERExtraUIBean esitoImpegniRMFITFERExtraBean = findBean("esitoImpegniRMFITFERExtraBean");
				esitoImpegniRMFITFERExtraBean.set_action(Costanti.ACTION_VIEW);
				esitoImpegniRMFITFERExtraBean.clearData();
				break;
			}
			case TAB_ART68:
			{
				EsitoArt68UIBean esitoArt68Bean = findBean("esitoArt68Bean");
				esitoArt68Bean.set_action(Costanti.ACTION_VIEW);
				esitoArt68Bean.clearData();
				break;
			}
			case TAB_SUPERFICI:
			{
				EsitoSuperficiUIBean esitoSuperficiBean = findBean("esitoSupBean");
				esitoSuperficiBean.set_action(Costanti.ACTION_VIEW);
				esitoSuperficiBean.clearData();
				break;
			}
			case TAB_UBA:
			{
				UbaUIBean ubaBean = findBean("ubaBean");
				UBAAmmissibilitaUIBean ubaAmmBean = findBean("ubaAmmBean");
				ubaBean.set_action(Costanti.ACTION_VIEW);
				ubaAmmBean.set_action(Costanti.ACTION_VIEW);
				ubaBean.clearData();
				ubaAmmBean.clearData();
				break;
			}
			case TAB_TUTELA_ACQUE:
			{
				TutelaAcqueUIBean tutelaAcqueBean = findBean("tutelaAcqueBean") ;
				tutelaAcqueBean.set_action(Costanti.ACTION_VIEW);
				tutelaAcqueBean.clearData();
				break;
			}	
			case TAB_ZOOTECNIA:
			{
				EsitoZootecniaUIBean esitoZootBean = findBean("esitoZootBean") ;
				esitoZootBean.set_action(Costanti.ACTION_VIEW);
				esitoZootBean.clearData();
				esitoZootBean.set_messageVisible(false);
				break;
			}	
		}
	}
	
	
	/**
	 * Restituisce l'istanza del bean dalla sessione
	 * @param <T>
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T findBean(String beanName) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    return (T) context.getApplication().evaluateExpressionGet(context, "#" + "{" + beanName + "}", Object.class);
	}

	public void setSelectedIndexAdmin(int selectedIndexAdmin) {
		this.selectedIndexAdmin = selectedIndexAdmin;
	}

	public int getSelectedIndexAdmin() {
		return selectedIndexAdmin;
	}
	
	/**
	 * cancellazione dei dati visualizzati nella tabella
	 */
	public void clearDataVisualized()
	{
		clearOldPage(selectedIndex);
	}
	
}