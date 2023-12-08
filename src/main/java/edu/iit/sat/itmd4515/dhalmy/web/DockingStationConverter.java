/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.domain.DockingStation;
import edu.iit.sat.itmd4515.dhalmy.service.DockingStationService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 * JSF converter for converting DockingStation objects to and from their string representations.
 * This converter is responsible for handling the conversion of DockingStation instances
 * to and from their unique identifiers (IDs) as strings.
 *
 * @author David
 */
@FacesConverter(value = "dockingStationConverter", managed = true)
public class DockingStationConverter implements Converter<DockingStation>{
    
    @EJB DockingStationService dockSvc;

    /**
     * Converts a string representation of a DockingStation ID into a DockingStation object.
     *
     * @param context   The current FacesContext.
     * @param component The current UIComponent.
     * @param value     The string value representing the DockingStation ID.
     * @return          The DockingStation object corresponding to the given ID.
     */
    @Override
    public DockingStation getAsObject(FacesContext context, UIComponent component, String value) {
        return dockSvc.read(Long.valueOf(value));
    }

    /**
     * Converts a DockingStation object into its unique identifier (ID) as a string.
     *
     * @param context   The current FacesContext.
     * @param component The current UIComponent.
     * @param value     The DockingStation object to be converted.
     * @return          The string representation of the DockingStation's ID.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, DockingStation value) {
        return String.valueOf(value.getDockID());
    }
}

