/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author David
 */

//JSF controller for employee.xhtml
@Named
@SessionScoped
public class EmployeeController implements Serializable{


    private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());
    private Employee employee;
    
    @EJB EmployeeService emSvc;
    
    @Inject
    private SessionBean sb;

    /**
     *
     */
    public EmployeeController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside the EmployeeController.postConstruct method");
        employee = new Employee();
    }
    
    /**
     *
     * @return
     */
    public String demoAction(){
        LOG.info("EmployeeController.demoAction has been invoked with employee " + employee.toString());
        return "confirmation.xhtml";
    }
    
    
    //MVC action methods

    /**
     *
     * @param e
     * @return
     */
    public String displayReadEmployeePage(Employee e){
        this.employee = e;
        LOG.info("EmployeeController.displayReadEmployeePage has been invoked with employee" + employee.toString());
        
        return "/read-entity/readEmployee.xhtml";
    }
    
    /**
     *
     * @param e
     * @return
     */
    public String displayUpdateEmployeePage(Employee e){
        this.employee = e;
        LOG.info("EmployeeController.displayReadEmployeePage has been invoked with employee" + employee.toString());
        
        return "/update-entity/updateEmployee.xhtml";
    }
    
    /**
     *
     * @param e
     * @return
     */
    public String displayDeleteEmployeePage(Employee e){
        this.employee = e;
        LOG.info("EmployeeController.displayReadEmployeePage has been invoked with employee" + employee.toString());
        
        return "/delete-entity/deleteEmployee.xhtml";
    }
    
    /**
     *
     * @return
     */
    public Employee getEmployee() {
        return employee;
    }
    
    /**
     *
     * @param employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    /**
     *
     * @return
     */
    public String updateEmployee(){
        LOG.info("EmployeeController.updateEmployee has been invoked with employee " + employee.toString());
        
        emSvc.updateEmployeeWRTRelationships(employee);
        
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }
    
    /**
     *
     * @return
     */
    public String deleteEmployee(){
        LOG.info("EmployeeController.deleteEmployee has been invoked with employee " + employee.toString());
        
        emSvc.deleteEmployeeWRTRelationships(employee);
        
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }
    
    /**
     *
     * @return
     */
    public String saveEmployee(){
        LOG.info("EmployeeController.saveEmployee has been invoked with employee " + employee.toString());
        
        emSvc.create(employee);
        
        LOG.info("EmployeeController.saveEmployee has been invoked EJV call " + employee.toString());
        
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        
//        LOG.info("THIS IS MY RETURN PAGE FIND ME 123"+sb.getReturnPage());
        
        return returnPage;
    }
    
    
    //helper method to auto-fill email

    /**
     *
     */
    public void initEmployee(){
        employee = new Employee();
    }
    
    /**
     *
     * @return
     */
    public String getGeneratedEmail() {
    if (employee.getAuto_username() != null && !employee.getAuto_username().isEmpty()) {
        employee.setEmail(employee.getAuto_username() + "@cats.illinois.gov");
        return employee.getAuto_username() + "@cats.illinois.gov";
    }
    return "";
}
    //helper method to auto-fill username

    /**
     *
     */
    public void generateUsername() {
    LOG.info("Inside the generateUsername method");
    String firstName = employee.getFirstName();
    String lastName = employee.getLastName();
//    LOG.info("FirstName: " + firstName + ", LastName: " + lastName);
    if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()){
        employee.setAuto_username(firstName.substring(0, 1) + lastName);
//        LOG.info(employee.getUsername());  
        }
    }
    
}
