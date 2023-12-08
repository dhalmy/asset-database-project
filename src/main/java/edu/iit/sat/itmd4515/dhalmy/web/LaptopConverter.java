/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.domain.Laptop;
import edu.iit.sat.itmd4515.dhalmy.service.LaptopService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 * JSF converter for converting between Laptop objects and their string representations.
 * Author: David
 */
@FacesConverter(value = "laptopConverter", managed = true)
public class LaptopConverter implements Converter<Laptop> {

    @EJB
    LaptopService ltSvc;

    /**
     * Converts a string value to a Laptop object.
     *
     * @param context   The FacesContext.
     * @param component The UIComponent.
     * @param value     The string value to convert.
     * @return The corresponding Laptop object.
     */
    @Override
    public Laptop getAsObject(FacesContext context, UIComponent component, String value) {
        return ltSvc.read(Long.valueOf(value));
    }

    /**
     * Converts a Laptop object to its string representation.
     *
     * @param context   The FacesContext.
     * @param component The UIComponent.
     * @param value     The Laptop object to convert.
     * @return The string representation of the Laptop's ID.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Laptop value) {
        return String.valueOf(value.getLaptopID());
    }
}

