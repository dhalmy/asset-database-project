/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
/**
 * JSF converter for converting Employee objects to and from their string representations.
 * This converter is used to convert Employee entities to and from strings in the user interface.
 * It is managed by the framework and registered with the name "employeeConverter".
 *
 * Author: David
 */
@FacesConverter(value = "employeeConverter", managed = true)
public class EmployeeConverter implements Converter<Employee>{

    @EJB EmployeeService emSvc;

    /**
     * Converts a string representation of an Employee ID into an actual Employee object.
     *
     * @param context The FacesContext for the conversion.
     * @param component The UIComponent that triggered the conversion.
     * @param value The string value to convert.
     * @return The corresponding Employee object.
     */
    @Override
    public Employee getAsObject(FacesContext context, UIComponent component, String value) {
        return emSvc.read(Long.valueOf(value));
    }

    /**
     * Converts an Employee object into its string representation (Employee ID).
     *
     * @param context The FacesContext for the conversion.
     * @param component The UIComponent that triggered the conversion.
     * @param value The Employee object to convert.
     * @return The string representation of the Employee's ID.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Employee value) {
        return String.valueOf(value.getEmployeeID());
    }
}

