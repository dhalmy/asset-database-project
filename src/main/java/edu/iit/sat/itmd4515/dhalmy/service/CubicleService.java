/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Cubicle;
import edu.iit.sat.itmd4515.dhalmy.domain.DockingStation;
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
    public void updateCubicleWRTRelationships(Cubicle cb){
        Cubicle managedCubicleRef = em.getReference(Cubicle.class, cb.getCubicleID());
        LOG.info("managedCubicleRef docking station: " + cb.toString());
        managedCubicleRef.addDockingStation(cb.getDockingStation());
        //todo monitor
        //todo employee
        em.merge(managedCubicleRef);
    }
    
}
