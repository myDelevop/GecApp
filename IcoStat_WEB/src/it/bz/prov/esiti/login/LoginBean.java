package it.bz.prov.esiti.login;

import java.io.IOException;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.ui.AbstractProcessingFilter;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// Stessa funzionalità di ui.AbstractProcessingFilter
// dettagli -> https://issues.jasig.org/browse/CAS-1097
import org.springframework.security.web.WebAttributes;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {
	private String username = "";
    private String password = "";
    private Vector<GrantedAuthority> roleList = new Vector<GrantedAuthority>();
    private boolean rememberMe = false;
    private boolean loggedIn = false;

    /**
     * gestione dell'operazione di login dell'utente
     * @return String
     * @throws IOException
     * @throws ServletException
     */
    public String doLogin() throws IOException, ServletException
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();

        this.setUsername(context.getRequestParameterMap().get("j_username"));
        this.setPassword(context.getRequestParameterMap().get("j_password"));
        return null;
    }

    @PostConstruct
    private void handleErrorMessage()
    {
        Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
                //AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY
        		WebAttributes.AUTHENTICATION_EXCEPTION);

        if (e instanceof BadCredentialsException)
        {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    //AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY
            		WebAttributes.AUTHENTICATION_EXCEPTION
                    , null);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password not valid.", null));
        }
    }
    
    /*public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }*/

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(final String username)
    {
        this.username = username.trim();
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public synchronized Vector<GrantedAuthority> getRoleList() {
		return roleList;
	}

	public synchronized void setRoleList(Vector<GrantedAuthority> roleList) {
		this.roleList = roleList;
	}

	public boolean isRememberMe()
    {
        return this.rememberMe;
    }

    public void setRememberMe(final boolean rememberMe)
    {
        this.rememberMe = rememberMe;
    }

    public boolean isLoggedIn()
    {
        return this.loggedIn;
    }

    public void setLoggedIn(final boolean loggedIn)
    {
        this.loggedIn = loggedIn;
    }
}
