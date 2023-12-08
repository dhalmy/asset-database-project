/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.domain.Monitor;
import edu.iit.sat.itmd4515.dhalmy.service.MonitorService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author David
 */
@FacesConverter(value = "monitorConverter", managed = true)
public class MonitorConverter implements Converter<Monitor>{
    
    @EJB MonitorService monSvc;

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Monitor getAsObject(FacesContext context, UIComponent component, String value) {
        return monSvc.read(Long.valueOf(value));
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Monitor value) {
        return String.valueOf(value.getMonitorID());
    }
    
}
