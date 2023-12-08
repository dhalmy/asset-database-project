/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.config.Itmd4515SecurityRoles;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import java.io.Serializable;
import java.util.logging.Logger;


/**
 * JSF SessionBean responsible for managing user sessions and privileges.
 * This bean is used to determine the user's role and set the appropriate return page.
 * 
 * @author David
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(SessionBean.class.getName());
    
    @Inject SecurityContext securityContext;
    String returnPage = "";

    /**
     * Constructor for SessionBean.
     */
    public SessionBean() {
    }
    
    /**
     * Checks if the current user has an admin role.
     * 
     * @return True if the user is an admin, false otherwise.
     */
    public boolean isAdmin(){
        return securityContext.isCallerInRole(Itmd4515SecurityRoles.ADMIN_ROLE);
    }

    /**
     * Checks if the current user has an IT role.
     * 
     * @return True if the user is in IT, false otherwise.
     */
    public boolean isIT(){
        return securityContext.isCallerInRole(Itmd4515SecurityRoles.IT_ROLE);
    }

    /**
     * Checks if the current user has an HR role.
     * 
     * @return True if the user is in HR, false otherwise.
     */
    public boolean isHR(){
        return securityContext.isCallerInRole(Itmd4515SecurityRoles.HR_ROLE);
    }
    
    /**
     * Sets the return page based on the user's role.
     * If the user is an admin, it sets the return page to the admin dashboard.
     * If the user is in IT, it sets the return page to the IT dashboard.
     * If the user is in HR, it sets the return page to the HR dashboard.
     * If the user has no valid role, it sets the return page to an error page.
     */
    public void returnHighestPrivilege(){
        if(isAdmin()){
            setReturnPage("/admin/admin-dashboard.xhtml?faces-redirect=true");
        }
        else if(isIT()){
            setReturnPage("/it/it-dashboard.xhtml?faces-redirect=true");
        }
        else if(isHR()){
            setReturnPage("/hr/hr-dashboard.xhtml?faces-redirect=true");
        }
        else {
            setReturnPage("/error.xhtml?faces-redirect=true");
        }
    }

    /**
     * Gets the return page set based on the user's role.
     * 
     * @return The return page URL.
     */
    public String getReturnPage() {
        return returnPage;
    }

    /**
     * Sets the return page based on the user's role.
     * 
     * @param returnPage The return page URL.
     */
    public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }

}

