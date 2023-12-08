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
 * JSF converter for converting Monitor objects to and from their String representations.
 * This converter is used to handle Monitor entities in JSF views.
 *
 * @author David
 */
@FacesConverter(value = "monitorConverter", managed = true)
public class MonitorConverter implements Converter<Monitor>{
    
    @EJB MonitorService monSvc;

    /**
     * Converts a String value to a Monitor object.
     *
     * @param context The FacesContext.
     * @param component The UIComponent.
     * @param value The String value to convert.
     * @return The corresponding Monitor object.
     */
    @Override
    public Monitor getAsObject(FacesContext context, UIComponent component, String value) {
        return monSvc.read(Long.valueOf(value));
    }

    /**
     * Converts a Monitor object to its String representation.
     *
     * @param context The FacesContext.
     * @param component The UIComponent.
     * @param value The Monitor object to convert.
     * @return The String representation of the Monitor's ID.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Monitor value) {
        return String.valueOf(value.getMonitorID());
    }
    
}
