/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.DockingStation;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author David
 */
@Stateless
public class DockingStationService extends AbstractService<DockingStation>{

    public DockingStationService() {
        super(DockingStation.class);
    }
    
    public List<DockingStation> findAll(){
        return super.findAll("DockingStation.findAll");
    }
    
    
    
}
