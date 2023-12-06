/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.domain.Laptop;
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
public class LaptopService extends AbstractService<Laptop> {

    private static final Logger LOG = Logger.getLogger(LaptopService.class.getName());
    
    
    
    public LaptopService(){
        super(Laptop.class);
    }
    
    public List<Laptop> findAll(){
        return super.findAll("Laptop.findAll");
    }
    
    public void deleteLaptopWRTRelationships(Laptop lt) {
        Laptop managedLaptopRef = em.getReference(Laptop.class, lt.getLaptopID());
        if(managedLaptopRef.getEmployee() != null){
            Employee e = managedLaptopRef.getEmployee();
            LOG.info("removing laptop: " + lt.getName() + " from employee:" + e.getAuto_username());
            e.removeLaptop(managedLaptopRef);
            em.merge(e);
        }
        
        
        em.remove(managedLaptopRef); 
    }
    
    public void updateLaptopWRTRelationships(Laptop lt){
        Laptop managedLaptopRef = em.getReference(Laptop.class, lt.getLaptopID());
        
        managedLaptopRef.setAssetTag(lt.getAssetTag());
        managedLaptopRef.setMakeModel(lt.getMakeModel());
        managedLaptopRef.setName(lt.getName());
        managedLaptopRef.setSerialNum(lt.getSerialNum());
        
        
        em.merge(managedLaptopRef);
    }
}
