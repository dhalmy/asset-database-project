/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.security.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    
    private User user;
    
    @Inject SecurityContext securityContext;
    @Inject FacesContext facesContext;


    
    public LoginController() {
    }
    
    
    
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside LoginController.postConstruct()");
        user = new User();
    }
    
    //helper method
    public String getCurrentUser(){
        return facesContext.getExternalContext().getRemoteUser();
    }
    
    
    
    //action methods
    public String login(){
        LOG.info("LoginController.login() with " + user.getUsername());
        
        //auth JSF-375
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        
        Credential cred = new UsernamePasswordCredential(this.user.getUsername(), new Password(this.user.getPassword()));
        
        
        AuthenticationStatus status =
                securityContext.authenticate(request, response, AuthenticationParameters.withParams().credential(cred));
   
        
        
        switch(status){
            case SUCCESS:
            case SEND_CONTINUE:
                LOG.info(status.toString());
                break;
            case SEND_FAILURE:
            case NOT_DONE:
                return "/error.xhtml";
        }
        
        return "/welcome.xhtml?faces-redirect=true";
    }
    
    public String logout() {
    
        LOG.info("LoginController.logout() with " + this.getCurrentUser());
    
        try {
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            request.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    
        return "/login.xhtml?faces-redirect=true";
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}