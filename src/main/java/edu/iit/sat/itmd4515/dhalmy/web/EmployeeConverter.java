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
 *
 * @author David
 */
@FacesConverter(value = "employeeConverter", managed = true)
public class EmployeeConverter implements Converter<Employee>{
    
    @EJB EmployeeService emSvc;

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Employee getAsObject(FacesContext context, UIComponent component, String value) {
        return emSvc.read(Long.valueOf(value));
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Employee value) {
        return String.valueOf(value.getEmployeeID());
    }
    
}
