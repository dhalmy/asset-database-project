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
 * JSF RequestBean for managing request-related operations.
 * This bean is used to act as a way to retrieve the URL and display certain things accordingly.
 * 
 * @author David
 */
@Named
@RequestScoped
public class RequestBean {
    
    /**
     * Constructor for RequestBean.
     */
    public RequestBean() {
    }

    /**
     * Checks if the current request is for an update operation.
     * 
     * @return True if the request is for an update, false otherwise.
     */
    public boolean isUpdate() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURI();
        
        return url.endsWith("/it-dashboard.xhtml") || 
               url.endsWith("/admin-dashboard.xhtml");
    }
    
    /**
     * Checks if the current request is for a delete operation.
     * 
     * @return True if the request is for a delete, false otherwise.
     */
    public boolean isDelete() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURI();
        
        return url.endsWith("/admin-dashboard.xhtml");
    }
}
