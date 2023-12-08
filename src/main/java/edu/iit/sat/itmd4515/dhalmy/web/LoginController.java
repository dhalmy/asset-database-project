/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.config.Itmd4515SecurityRoles;
import edu.iit.sat.itmd4515.dhalmy.security.Group;
import edu.iit.sat.itmd4515.dhalmy.security.User;
import edu.iit.sat.itmd4515.dhalmy.security.UserService;
import edu.iit.sat.itmd4515.dhalmy.web.SessionBean;
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
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    
    private User user;
    
    @Inject SessionBean sb;
    @Inject SecurityContext securityContext;
    @Inject FacesContext facesContext;
    @EJB UserService userSvc;

    /**
     *
     */
    public LoginController() {
    }
    
    /**
     *
     * @return
     */
    public boolean isAdmin(){
        return securityContext.isCallerInRole(Itmd4515SecurityRoles.ADMIN_ROLE);
    }

    /**
     *
     * @return
     */
    public boolean isIT(){
        return securityContext.isCallerInRole(Itmd4515SecurityRoles.IT_ROLE);
    }

    /**
     *
     * @return
     */
    public boolean isHR(){
        return securityContext.isCallerInRole(Itmd4515SecurityRoles.HR_ROLE);
    }
    
    
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside LoginController.postConstruct()");
        user = new User();
    }
    
    //helper method

    /**
     *
     * @return
     */
    public String getCurrentUser(){
        return facesContext.getExternalContext().getRemoteUser();
    }
    
    
    
    
    
    //action methods

    /**
     *
     * @return
     */
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
        
        sb.returnHighestPrivilege();
        return sb.getReturnPage();

    }
    

    
        
        //this was created before isCallerInRole was demod to us in the lecture. it required this bean's scope to change from RequestScoped to SessionScoped
        //this workaround required this bean to be SessionScoped. this created a problem with form data persisting after submission
        
//        User currGroups = userSvc.findUserWithGroupsByUsername(this.getCurrentUser());
//            LOG.info(currGroups.getGroups().get(0).getGroupName());
//            // checks for highest priority group the user belongs to, redirects to proper welcome page
//            ArrayList<String> allGroups = new ArrayList<>();
//            for(Group group : currGroups.getGroups()) {
//                allGroups.add(group.getGroupName());
//            }
//            
//            if(allGroups.contains("ADMIN_GROUP")){
//                returnPage = "/admin/admin-dashboard.xhtml?faces-redirect=true";
//                return "/admin/admin-dashboard.xhtml?faces-redirect=true";
//            }
//                
//            else if(allGroups.contains("IT_GROUP")){
//                returnPage = "/it/it-dashboard.xhtml?faces-redirect=true";
//                return "/it/it-dashboard.xhtml?faces-redirect=true";
//            }
//                
//            else if(allGroups.contains("HR_GROUP")){
//                returnPage = "/hr/hr-dashboard.xhtml?faces-redirect=true";
//                return "/hr/hr-dashboard.xhtml?faces-redirect=true";
//            }
//                
//            
//        return "/error.xhtml?faces-redirect=true";

    /**
     *
     * @return
     */


    
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
    
    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
