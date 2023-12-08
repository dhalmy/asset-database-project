/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Cubicle;
import edu.iit.sat.itmd4515.dhalmy.domain.DockingStation;
import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.domain.Laptop;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
@Named
@Stateless
public class DockingStationService extends AbstractService<DockingStation>{

    private static final Logger LOG = Logger.getLogger(DockingStationService.class.getName());
    
    /**
     *
     */
    public DockingStationService() {
        super(DockingStation.class);
    }
    
    /**
     *
     * @return
     */
    public List<DockingStation> findAll(){
        return super.findAll("DockingStation.findAll");
    }
    
    
    /**
     *
     * @param ds
     */
    public void updateDockingStationWRTRelationships(DockingStation ds){
        DockingStation managedDockRef = em.getReference(DockingStation.class, ds.getDockID());
        
        managedDockRef.setAssetTag(ds.getAssetTag());
        managedDockRef.setUnlock_key(ds.getUnlock_key());
        managedDockRef.setSerialNum(ds.getSerialNum());
        
        
        em.merge(managedDockRef);
    }
    
    /**
     *
     * @param ds
     */
    public void deleteDockingStationWRTRelationships(DockingStation ds) {
        try {
            Cubicle cube = em.createNamedQuery("Cubicle.findCubicleByDockingStationID", Cubicle.class)
                    .setParameter("dockID", ds.getDockID())
                    .getSingleResult();

            if (cube.getDockingStation() != null) {
                cube.setDockingStation(null);
                em.merge(cube);
                LOG.info("Removing docking station: " + ds.getAssetTag() + " from cubicle: " + cube.getCubicleID());
            }

            DockingStation managedDockRef = em.getReference(DockingStation.class, ds.getDockID());
            em.remove(managedDockRef);
            LOG.info("DockingStation removed: " + ds.getAssetTag());
        } catch (NoResultException e) {
            LOG.info("No cubicle found for docking station ID: " + ds.getDockID());
            DockingStation managedDockRef = em.getReference(DockingStation.class, ds.getDockID());
            em.remove(managedDockRef);
            LOG.info("DockingStation removed: " + ds.getAssetTag());
        }
    }
}

