/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Cubicle;
import edu.iit.sat.itmd4515.dhalmy.domain.Monitor;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;
import java.util.logging.Logger;

/**
 * A service class for managing monitors. This class provides methods for CRUD operations
 * related to monitors and their relationships with cubicles.
 *
 * @author David
 */
@Named
@Stateless
public class MonitorService extends AbstractService<Monitor> {

    private static final Logger LOG = Logger.getLogger(MonitorService.class.getName());
    
    /**
     * Constructs a MonitorService instance.
     */
    public MonitorService() {
        super(Monitor.class);
    }
    
    /**
     * Retrieves a list of all monitors.
     *
     * @return A list of all monitors in the database.
     */
    public List<Monitor> findAll(){
        return super.findAll("Monitor.findAll");
    }
    
    /**
     * Retrieves a list of monitors available for a specific cubicle.
     *
     * @param cubicleID The ID of the cubicle to find available monitors for.
     * @return A list of monitors available for the specified cubicle.
     */
    public List<Monitor> findAvailableForCubicle(int cubicleID) {
        return em.createNamedQuery("Monitor.findAvailableForCubicle", Monitor.class)
             .setParameter("cubicleID", cubicleID)
             .getResultList();
    }
    
    /**
     * Deletes a monitor and its relationship with a cubicle.
     *
     * @param mon The monitor to delete.
     */
    public void deleteMonitorWRTRelationships(Monitor mon) {
        Monitor managedMonitorRef = em.getReference(Monitor.class, mon.getMonitorID());

        if (managedMonitorRef.getCubicle() != null) {
            Cubicle cubicle = managedMonitorRef.getCubicle();
            cubicle.removeMonitor(managedMonitorRef);
            em.merge(cubicle);
            LOG.info("Removing monitor: " + mon.getSerialNum() + " from cubicle: " + cubicle.getCubicleID());
        }

        em.remove(managedMonitorRef);
    }

    /**
     * Updates a monitor's information while maintaining relationships.
     *
     * @param mon The monitor to update.
     */
    public void updateMonitorWRTRelationships(Monitor mon){
        Monitor managedMonitorRef = em.getReference(Monitor.class, mon.getMonitorID());
        
        managedMonitorRef.setAssetTag(mon.getAssetTag());
        managedMonitorRef.setMakeModel(mon.getMakeModel());
        managedMonitorRef.setSerialNum(mon.getSerialNum());
        
        em.merge(managedMonitorRef);
    }
}
