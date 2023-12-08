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
 *
 * @author David
 */
@Named
@Stateless
public class MonitorService extends AbstractService<Monitor>{

    private static final Logger LOG = Logger.getLogger(MonitorService.class.getName());
    
    /**
     *
     */
    public MonitorService() {
        super(Monitor.class);
    }
    
    /**
     *
     * @return
     */
    public List<Monitor> findAll(){
        return super.findAll("Monitor.findAll");
    }
    
    /**
     *
     * @param cubicleID
     * @return
     */
    public List<Monitor> findAvailableForCubicle(int cubicleID) {
    return em.createNamedQuery("Monitor.findAvailableForCubicle", Monitor.class)
             .setParameter("cubicleID", cubicleID)
             .getResultList();
}
    
    /**
     *
     * @param mon
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
     *
     * @param mon
     */
    public void updateMonitorWRTRelationships(Monitor mon){
        Monitor managedMonitorRef = em.getReference(Monitor.class, mon.getMonitorID());
        
        managedMonitorRef.setAssetTag(mon.getAssetTag());
        managedMonitorRef.setMakeModel(mon.getMakeModel());
        managedMonitorRef.setSerialNum(mon.getSerialNum());
        
        em.merge(managedMonitorRef);
    }
    
}
