/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Cubicle;
import edu.iit.sat.itmd4515.dhalmy.domain.DockingStation;
import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.domain.Laptop;
import edu.iit.sat.itmd4515.dhalmy.domain.Monitor;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * A service class for managing cubicles. It extends the AbstractService class
 * and provides methods for CRUD operations and custom queries related to
 * cubicles.
 *
 * @author David
 */
@Named
@Stateless
public class CubicleService extends AbstractService<Cubicle> {

    private static final Logger LOG = Logger.getLogger(CubicleService.class.getName());

    /**
     * Constructs a CubicleService instance.
     */
    public CubicleService() {
        super(Cubicle.class);
    }

    /**
     * Retrieves a list of all cubicles.
     *
     * @return A list of all cubicles in the persistence context.
     */
    public List<Cubicle> findAll() {
        return super.findAll("Cubicle.findAll");
    }

    /**
     * Finds and returns a list of available docking stations based on the
     * current docking station ID. This method ensures that a cubicle's current
     * docking station is excluded from the available options.
     *
     * @param currentDockId The ID of the current docking station.
     * @return A list of available docking stations.
     */
    public List<DockingStation> findAvailableDockingStations(Long currentDockId) {
        List<DockingStation> availableDocks = em.createQuery("SELECT d FROM DockingStation d", DockingStation.class)
                .getResultList();

        List<Cubicle> cubicles = em.createQuery("SELECT c FROM Cubicle c WHERE c.dockingStation IS NOT NULL "
                + "AND c.dockingStation.dockID != :currentDockId", Cubicle.class)
                .setParameter("currentDockId", currentDockId == null ? -1 : currentDockId)
                .getResultList();

        for (Cubicle c : cubicles) {
            availableDocks.remove(c.getDockingStation());
        }

        if (currentDockId != null) {
            DockingStation currentDock = em.find(DockingStation.class, currentDockId);
            if (!availableDocks.contains(currentDock)) {
                availableDocks.add(currentDock);
            }
        }

        return availableDocks;
    }

    /**
     * Updates the relationships of a cubicle with its associated docking
     * station, monitors, and employees.
     *
     * @param cb The cubicle to update.
     */
    public void updateCubicleWRTRelationships(Cubicle cb) {
        Cubicle managedCubicleRef = em.getReference(Cubicle.class, cb.getCubicleID());

        // Updates docking station
        managedCubicleRef.setDockingStation(cb.getDockingStation());

        // Updates monitors
        List<Monitor> currentlyAssignedMonitors = em.createNamedQuery("Monitor.findByCubicleID", Monitor.class)
                .setParameter("cubicleID", managedCubicleRef.getCubicleID())
                .getResultList();

        // Removes outdated monitors
        for (Monitor monitor : currentlyAssignedMonitors) {
            if (!cb.getMonitors().contains(monitor)) {
                monitor.setCubicle(null);
                em.merge(monitor);
            }
        }

        // Updates/adds new monitors
        for (Monitor monitor : cb.getMonitors()) {
            if (!currentlyAssignedMonitors.contains(monitor)) {
                Monitor managedMonitorRef = em.find(Monitor.class, monitor.getMonitorID());
                managedMonitorRef.setCubicle(managedCubicleRef);
                em.merge(managedMonitorRef);
            }
        }

        managedCubicleRef.setMonitors(cb.getMonitors());

        // Updates employees
        List<Employee> currentlyAssignedEmployees = em.createNamedQuery("Employee.findByCubicleID", Employee.class)
                .setParameter("cubicleID", managedCubicleRef.getCubicleID())
                .getResultList();

        // Removes outdated employees
        for (Employee employee : currentlyAssignedEmployees) {
            if (!cb.getEmployees().contains(employee)) {
                employee.setCubicle(null);
                em.merge(employee);
            }
        }

        // Updates/adds new employees
        for (Employee employee : cb.getEmployees()) {
            if (!currentlyAssignedEmployees.contains(employee)) {
                Employee managedEmployeeRef = em.find(Employee.class, employee.getEmployeeID());
                managedEmployeeRef.setCubicle(managedCubicleRef);
                em.merge(managedEmployeeRef);
            }
        }

        managedCubicleRef.setEmployees(cb.getEmployees());

        em.merge(managedCubicleRef);
    }
}
