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
 * JSF controller for managing employees in the application.
 * This controller handles the actions related to employee entities,
 * such as creating, reading, updating, and deleting employee records.
 * It also provides helper methods for generating usernames and emails.
 *
 * Author: David
 */
@Named
@SessionScoped
public class EmployeeController implements Serializable{

    private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());
    private Employee employee;

    @EJB EmployeeService emSvc;

    @Inject
    private SessionBean sb;

    /**
     * Default constructor for EmployeeController.
     */
    public EmployeeController() {
    }

    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside the EmployeeController.postConstruct method");
        employee = new Employee();
    }

    /**
     * A redundant method for generating actions on the employee.
     * It logs the invocation and calls the helper methods to generate a username and email.
     *
     * @return A string representing the success status.
     */
    public String redundantGenerateAction(){
        LOG.info("EmployeeController.redundantGenerateAction has been invoked with employee " + employee.toString());
        generateUsername();
        getGenerateEmail();
        return "success";
    }

    // MVC action methods

    /**
     * Displays the read employee page for a given employee.
     *
     * @param e The employee to be displayed.
     * @return The navigation outcome for reading the employee.
     */
    public String displayReadEmployeePage(Employee e){
        this.employee = e;
        LOG.info("EmployeeController.displayReadEmployeePage has been invoked with employee" + employee.toString());

        return "/read-entity/readEmployee.xhtml";
    }

    /**
     * Displays the update employee page for a given employee.
     *
     * @param e The employee to be updated.
     * @return The navigation outcome for updating the employee.
     */
    public String displayUpdateEmployeePage(Employee e){
        this.employee = e;
        LOG.info("EmployeeController.displayReadEmployeePage has been invoked with employee" + employee.toString());

        return "/update-entity/updateEmployee.xhtml";
    }

    /**
     * Displays the delete employee page for a given employee.
     *
     * @param e The employee to be deleted.
     * @return The navigation outcome for deleting the employee.
     */
    public String displayDeleteEmployeePage(Employee e){
        this.employee = e;
        LOG.info("EmployeeController.displayReadEmployeePage has been invoked with employee" + employee.toString());

        return "/delete-entity/deleteEmployee.xhtml";
    }

    /**
     * Gets the current employee.
     *
     * @return The current employee.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the current employee.
     *
     * @param employee The employee to set.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Updates an employee record.
     * It calls the redundantGenerateAction method to ensure consistent data.
     *
     * @return The navigation outcome after updating the employee.
     */
    public String updateEmployee(){
        redundantGenerateAction();
        LOG.info("EmployeeController.updateEmployee has been invoked with employee " + employee.toString());

        emSvc.updateEmployeeWRTRelationships(employee);

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    /**
     * Deletes an employee record.
     *
     * @return The navigation outcome after deleting the employee.
     */
    public String deleteEmployee(){
        LOG.info("EmployeeController.deleteEmployee has been invoked with employee " + employee.toString());

        emSvc.deleteEmployeeWRTRelationships(employee);

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    /**
     * Saves a new employee record.
     * It calls the redundantGenerateAction method to ensure consistent data.
     *
     * @return The navigation outcome after saving the employee.
     */
    public String saveEmployee(){
        redundantGenerateAction();
        LOG.info("EmployeeController.saveEmployee has been invoked with employee " + employee.toString());

        emSvc.create(employee);

        LOG.info("EmployeeController.saveEmployee has been invoked EJV call " + employee.toString());

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();

        return returnPage;
    }

    // Helper methods to auto-fill email and generate username

    /**
     * Initializes a new employee object.
     */
    public void initEmployee(){
        employee = new Employee();
    }

    /**
     * Generates the email for the employee based on their username.
     *
     * @return The generated email address.
     */
    public String getGenerateEmail() {
        if (employee.getAuto_username() != null && !employee.getAuto_username().isEmpty()) {
            employee.setEmail(employee.getAuto_username() + "@cats.illinois.gov");
            return employee.getAuto_username() + "@cats.illinois.gov";
        }
        return "";
    }

    /**
     * Generates a username for the employee based on their first name and last name.
     */
    public void generateUsername() {
        LOG.info("Inside the generateUsername method");
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()){
            employee.setAuto_username(firstName.substring(0, 1) + lastName);
        }
    }
}

