/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.api;

import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author David
 */
@Path("/employees")
public class EmployeeServiceREST {
    
    @EJB EmployeeService emSvc;
    
    @GET
    @Path("/version")
    @Produces(MediaType.TEXT_PLAIN)
    public String employeeApiVersionInfo(){
        return "v1";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Employee> getAllEmployees(){
        return emSvc.findAll();
    }
    
}
