/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Cubicle;
import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.domain.Laptop;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * A service class for managing employees. This class provides methods for CRUD operations
 * related to employees and their relationships with cubicles and laptops.
 *
 * @author David
 */
@Named
@Stateless
public class EmployeeService {

    private static final Logger LOG = Logger.getLogger(EmployeeService.class.getName());

    /**
     * The entity manager for database interactions.
     */
    @PersistenceContext(name = "itm4515PU")
    protected EntityManager em;

    /**
     * Constructs an EmployeeService instance.
     */
    public EmployeeService() {
    }

    /**
     * Creates a new employee.
     *
     * @param e The employee to create.
     */
    public void create(Employee e) {
        em.persist(e);
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param employeeID The ID of the employee to retrieve.
     * @return The employee with the specified ID, or null if not found.
     */
    public Employee read(Long employeeID) {
        return em.find(Employee.class, employeeID);
    }

    /**
     * Updates an employee's information while maintaining relationships with laptops.
     *
     * @param e The employee to update.
     */
    public void update(Employee e) {
        em.merge(e);
    }

    /**
     * Finds employees available for a specific cubicle.
     *
     * @param cubicleId The ID of the cubicle to find available employees for.
     * @return A list of employees available for the specified cubicle.
     */
    public List<Employee> findAvailableForCubicle(int cubicleId) {
        return em.createNamedQuery("Employee.findAvailableForCubicle", Employee.class)
                 .setParameter("cubicleID", cubicleId)
                 .getResultList();
    }

    /**
     * Deletes an employee and their relationships with cubicles and laptops.
     *
     * @param e The employee to delete.
     */
    public void deleteEmployeeWRTRelationships(Employee e) {
        Employee managedEmployeeRef = em.getReference(Employee.class, e.getEmployeeID());
        Cubicle cb = managedEmployeeRef.getCubicle();
        LOG.info("Removing employee: " + managedEmployeeRef.getAuto_username() + " from cubicle:" + cb.getCubicleID());
        cb.removeEmployee(managedEmployeeRef);
        em.merge(cb);

        for (Laptop laptop : new ArrayList<>(managedEmployeeRef.getLaptops())) {
            LOG.info("Removing laptop from employee: " + laptop.getName());
            laptop.removeEmployee();
            em.merge(laptop);
        }
        em.remove(managedEmployeeRef);
    }

    /**
     * Updates an employee's information while maintaining relationships with laptops.
     *
     * @param e The employee to update.
     */
    public void updateEmployeeWRTRelationships(Employee e) {
        Employee managedEmployeeRef = em.getReference(Employee.class, e.getEmployeeID());

        managedEmployeeRef.setType(e.getType());
        managedEmployeeRef.setHireDate(e.getHireDate());
        managedEmployeeRef.setFirstName(e.getFirstName());
        managedEmployeeRef.setLastName(e.getLastName());
        managedEmployeeRef.setAuto_username(e.getAuto_username());
        managedEmployeeRef.setEmail(e.getEmail());
        managedEmployeeRef.setLaptops(e.getLaptops());

        List<Laptop> currentlyAssignedLaptops = em.createNamedQuery("Laptop.findByEmployeeID", Laptop.class)
                                              .setParameter("employeeID", managedEmployeeRef.getEmployeeID())
                                              .getResultList();

        for (Laptop laptop : currentlyAssignedLaptops) {
            if (!e.getLaptops().contains(laptop)) {
                laptop.removeEmployee();
                em.merge(laptop);
            }
        }

        for (Laptop laptop : e.getLaptops()) {
            if (!currentlyAssignedLaptops.contains(laptop)) {
                Laptop managedLaptopRef = em.find(Laptop.class, laptop.getLaptopID());
                managedLaptopRef.addEmployee(managedEmployeeRef);
                em.merge(managedLaptopRef);
            }
        }
        managedEmployeeRef.setLaptops(e.getLaptops());

        em.merge(managedEmployeeRef);
    }

    /**
     * Deletes an employee.
     *
     * @param e The employee to delete.
     */
    public void delete(Employee e) {
        em.remove(em.merge(e));
    }

    /**
     * Retrieves a list of all employees.
     *
     * @return A list of all employees in the database.
     */
    public List<Employee> findAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }
}
