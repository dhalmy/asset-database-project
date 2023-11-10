/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.config.Itmd4515SecurityRoles;
import edu.iit.sat.itmd4515.dhalmy.security.Group;
import edu.iit.sat.itmd4515.dhalmy.security.User;
import edu.iit.sat.itmd4515.dhalmy.security.UserService;
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
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    
    private User user;
    
    @Inject SecurityContext securityContext;
    @Inject FacesContext facesContext;
    @EJB UserService userSvc;
    
    String returnPage = "";


    
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
    
    public boolean isAdmin(){
        return securityContext.isCallerInRole(Itmd4515SecurityRoles.ADMIN_ROLE);
    }
    public boolean isIT(){
        return securityContext.isCallerInRole(Itmd4515SecurityRoles.IT_ROLE);
    }
    public boolean isHR(){
        return securityContext.isCallerInRole(Itmd4515SecurityRoles.HR_ROLE);
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
        
        this.returnPage = "";
        return returnHighestPrivilege();

    }
    
    //this was created before isCallerInRole was demod to us in the lecture.
    //i now recognize there exists a better way to implement this, i may change it next deliverable
    //QUESTION: this workaround requires this bean to be SessionScoped. will this possibly introduce more issues down the line?
    public String returnHighestPrivilege(){
        
        //this ensures the whole process only needs to run once
        if(!returnPage.isEmpty()){
            return returnPage;
        }
        
        User currGroups = userSvc.findUserWithGroupsByUsername(this.getCurrentUser());
            LOG.info(currGroups.getGroups().get(0).getGroupName());
            // checks for highest priority group the user belongs to, redirects to proper welcome page
            ArrayList<String> allGroups = new ArrayList<>();
            for(Group group : currGroups.getGroups()) {
                allGroups.add(group.getGroupName());
            }
            
            if(allGroups.contains("ADMIN_GROUP")){
                returnPage = "/admin/admin-dashboard.xhtml?faces-redirect=true";
                return "/admin/admin-dashboard.xhtml?faces-redirect=true";
            }
                
            else if(allGroups.contains("IT_GROUP")){
                returnPage = "/it/it-dashboard.xhtml?faces-redirect=true";
                return "/it/it-dashboard.xhtml?faces-redirect=true";
            }
                
            else if(allGroups.contains("HR_GROUP")){
                returnPage = "/hr/hr-dashboard.xhtml?faces-redirect=true";
                return "/hr/hr-dashboard.xhtml?faces-redirect=true";
            }
                
            
        return "/error.xhtml?faces-redirect=true";
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

    public String getReturnPage() {
        return returnPage;
    }

    public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }
}
