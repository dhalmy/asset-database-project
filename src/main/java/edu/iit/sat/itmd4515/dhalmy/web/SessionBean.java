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
 *
 * @author David
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(SessionBean.class.getName());
    
    
    
    @Inject SecurityContext securityContext;
    String returnPage = "";

    public SessionBean() {
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
    
    public void returnHighestPrivilege(){
//        LOG.info("SESSION BEANS ARE BEING SET:");
        
        if(isAdmin()){
            setReturnPage("/admin/admin-dashboard.xhtml?faces-redirect=true");
            LOG.info(getReturnPage() + "this is what my session bean is set as");
        }
        else if(isIT()){
            setReturnPage("/it/it-dashboard.xhtml?faces-redirect=true");
        }
        else if(isHR()){
            setReturnPage("/hr/hr-dashboard.xhtml?faces-redirect=true");
        }
        else
            setReturnPage("/error.xhtml?faces-redirect=true");
    }

    public String getReturnPage() {
        return returnPage;
    }

    public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }
    
    
    
    
}
