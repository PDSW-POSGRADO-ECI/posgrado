/**
 * Very simple bean that authenticates the user via Apache Shiro, using JSF
 *
 * @author Daniel Mascarenhas
 */
package edu.eci.pdsw.posgrado.managebeans;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "LoginBean")
@ViewScoped
public class LoginBean implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(LoginBean.class);

    private String username;
    private String password;
    private boolean rememberMe;

    public LoginBean() {

    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * Try and authenticate the user
     *
     * @throws java.lang.Exception
     */
    public void doLogin() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword(), getRememberMe());
        try {
            subject.login(token);

            if (subject.hasRole("admin")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("admin/Menu.xhtml");
            } else if (subject.hasRole("assistant")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("assistant/Menu.xhtml");
            } else if (subject.hasRole("coordinator")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("coordinator/Menu.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Inicio.xhtml");
            }
        } catch (UnknownAccountException ex) {
            facesError("Unknown account");
            log.error(ex.getMessage(), ex);
        } catch (IncorrectCredentialsException ex) {
            facesError("Wrong password");
            log.error(ex.getMessage(), ex);
        } catch (LockedAccountException ex) {
            facesError("Locked account");
            log.error(ex.getMessage(), ex);
        } catch (AuthenticationException | IOException ex) {
            facesError("Unknown error: " + ex.getMessage());
            log.error(ex.getMessage(), ex);
        } finally {
            token.clear();
        }
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     *
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean lembrar) {
        this.rememberMe = lembrar;
    }
}
