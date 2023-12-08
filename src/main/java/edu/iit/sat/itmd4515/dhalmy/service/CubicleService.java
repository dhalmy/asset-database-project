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
 *
 * @author David
 */
@Named
@Stateless
public class CubicleService extends AbstractService<Cubicle> {

    private static final Logger LOG = Logger.getLogger(CubicleService.class.getName());

    /**
     *
     */
    public CubicleService() {
        super(Cubicle.class);
    }
    
    /**
     *
     * @return
     */
    public List<Cubicle> findAll(){
        return super.findAll("Cubicle.findAll");
    }
    
    /**
     *
     * @param currentDockId
     * @return
     */
    public List<DockingStation> findAvailableDockingStations(Long currentDockId) {
        List<DockingStation> availableDocks = em.createQuery("SELECT d FROM DockingStation d", DockingStation.class)
                .getResultList();
        
        List<Cubicle> cubicles = em.createQuery("SELECT c FROM Cubicle c WHERE c.dockingStation IS NOT NULL AND c.dockingStation.dockID != :currentDockId", Cubicle.class)
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
     *
     * @param cb
     */
    public void updateCubicleWRTRelationships(Cubicle cb) {
        Cubicle managedCubicleRef = em.getReference(Cubicle.class, cb.getCubicleID());

        // updates docking station
        managedCubicleRef.setDockingStation(cb.getDockingStation());

        // update monitors
        List<Monitor> currentlyAssignedMonitors = em.createNamedQuery("Monitor.findByCubicleID", Monitor.class)
                .setParameter("cubicleID", managedCubicleRef.getCubicleID())
                .getResultList();

        // removes outdated monitors
        for (Monitor monitor : currentlyAssignedMonitors) {
            if (!cb.getMonitors().contains(monitor)) {
                monitor.setCubicle(null);
                em.merge(monitor);
            }
        }

        // updates / adds new monitors
        for (Monitor monitor : cb.getMonitors()) {
            if (!currentlyAssignedMonitors.contains(monitor)) {
                Monitor managedMonitorRef = em.find(Monitor.class, monitor.getMonitorID());
                managedMonitorRef.setCubicle(managedCubicleRef);
                em.merge(managedMonitorRef);
            }
        }
        
        managedCubicleRef.setMonitors(cb.getMonitors());

        // updates employees
        List<Employee> currentlyAssignedEmployees = em.createNamedQuery("Employee.findByCubicleID", Employee.class)
                .setParameter("cubicleID", managedCubicleRef.getCubicleID())
                .getResultList();

        // removes outdated employees
        for (Employee employee : currentlyAssignedEmployees) {
            if (!cb.getEmployees().contains(employee)) {
                employee.setCubicle(null);
                em.merge(employee);
            }
        }

        // updates / adds new employees
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
