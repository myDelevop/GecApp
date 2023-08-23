package it.bz.prov.esiti.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;


import it.bz.prov.esiti.util.BuildInfo;


/**
 * Bean UI (user interface) per la gestione delle informazioni tecniche sull'applicazione
 * 
 * @author bpettazzoni
 *
 */

@ManagedBean(name = "infoAppBean")
@SessionScoped
public class InfoAppUIBean {
	private int _responseStatusCode;
	private String _responseStatusDescr;
	private String _responseStatusTitle;
	
	public InfoAppUIBean(){
		_responseStatusDescr = "";
		_responseStatusTitle = "";
	}

	public String get_timestampRelease() {
		return BuildInfo.timeStamp;
	}
	
	public String get_svnRevisionNumber() {
		return BuildInfo.revisionNumber;
	}

	public void set_responseStatusCode(int _responseStatusCode) {
		this._responseStatusCode = _responseStatusCode;
	}

	public int get_responseStatusCode() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		_responseStatusCode = response.getStatus();
		switch(_responseStatusCode)
		{
		   case 206: {
			   _responseStatusTitle="Invio parziale"; 
			   _responseStatusDescr = "Si e' verificato un invio parziale dei dati. Per caso è stato bloccato il caricamento di una pagina?"; 
			   break;
		   }
		   case 300: {
			   _responseStatusTitle="Servizio spostato"; 
			   _responseStatusDescr = "Il servizio web cui si sta tentando di accedere e' stato temporaneamente o definitivamente spostato. Si prega di contattare i responsabili del servizio"; 
			   break;
		   }
		   case 301: {
			   _responseStatusTitle="Servizio spostato"; 
			   _responseStatusDescr = "Il servizio web cui si sta tentando di accedere e' stato temporaneamente o definitivamente spostato. Si prega di contattare i responsabili del servizio"; 
			   break;
		   }
		   case 400: {
			   _responseStatusTitle="Richiesta errata"; 
			   _responseStatusDescr = "La richiesta inviata presenta una sintassi non corretta oppure non e' stato possibile soddisfarla"; 
			   break;
		   }
		   case 401: {
			   _responseStatusTitle="Autorizzazione negata"; 
			   _responseStatusDescr = "Non si e' autorizzati ad utilizzare questo servizio"; 
			   break;
		   }
		   case 402: {
			   _responseStatusTitle="Pagamento richiesto"; 
			   _responseStatusDescr = "Il servizio richiesto e' a pagamento"; 
			   break;
		   }
		   case 403: {
			   _responseStatusTitle="Operazione non autorizzata"; 
			   _responseStatusDescr = "Richiesta non specificata correttamente, o mancata autorizzazione ad eseguire l'operazione richiesta"; 
			   break;
		   }
		   case 404: {
			   _responseStatusTitle="Non trovato"; 
			   _responseStatusDescr = "Il file richiesto non e' stato trovato"; 
			   break;
		   }
		   case 405: {
			   _responseStatusTitle="Metodo non consentito"; 
			   _responseStatusDescr = "Non e' possibile richiamare il metodo richiesto"; 
			   break;
		   }
		   case 408: {
			   _responseStatusTitle="Time out"; 
			   _responseStatusDescr = "La richiesta inoltrata e' andata in timeout. Si prega di riprovare piu' tardi"; 
			   break;
		   }
		   case 500: {
			   _responseStatusTitle="Errore del server"; 
			   _responseStatusDescr = "Errore generico del server. Si prega di riprovare piu' tardi"; 
			   break;
		   }
		   case 501: {
			   _responseStatusTitle="Servizio non implementato"; 
			   _responseStatusDescr = "Il server non supporta ancora il servizio richiesto"; 
			   break;
		   }
		   case 503: {
			   _responseStatusTitle="Mancanza di risorse"; 
			   _responseStatusDescr = "Il server non puo' processare la richiesta a causa di un numero eccessivo di connessioni. Si prega di riprovare piu' tardi"; 
			   break;
		   }
		   default: {
			   _responseStatusTitle="Errore generico"; 
			   _responseStatusDescr="Contattare l'assistenza per maggiori informazioni"; 
			   break;
		   }
		}
		return _responseStatusCode;
	}

	public void set_responseStatusDescr(String _responseStatusDescr) {
		this._responseStatusDescr = _responseStatusDescr;
	}

	public String get_responseStatusDescr() {		
		return _responseStatusDescr;
	}

	public void set_responseStatusTitle(String _responseStatusTitle) {
		this._responseStatusTitle = _responseStatusTitle;
	}

	public String get_responseStatusTitle() {
		return _responseStatusTitle;
	}
	
	

	
}
