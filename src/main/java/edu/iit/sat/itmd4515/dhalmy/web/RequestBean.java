/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;


/**
 *
 * @author David
 */
@Named
@RequestScoped
public class RequestBean {
    
    /**
     *
     */
    public RequestBean() {
    }

    /**
     *
     * @return
     */
    public boolean isUpdate() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURI();
        
        return url.endsWith("/it-dashboard.xhtml") || 
               url.endsWith("/admin-dashboard.xhtml");
    }
    
    /**
     *
     * @return
     */
    public boolean isDelete() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURI();
        
        return url.endsWith("/admin-dashboard.xhtml");
    }
}
