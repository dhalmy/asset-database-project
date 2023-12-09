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
 * A service class for managing laptops. This class provides methods for CRUD operations
 * related to laptops and their relationships with employees.
 *
 * @author David
 */
@Named
@Stateless
public class LaptopService extends AbstractService<Laptop> {

    private static final Logger LOG = Logger.getLogger(LaptopService.class.getName());
    
    /**
     * Constructs a LaptopService instance.
     */
    public LaptopService(){
        super(Laptop.class);
    }
    
    /**
     * Retrieves a list of all laptops.
     *
     * @return A list of all laptops in the database.
     */
    public List<Laptop> findAll(){
        return super.findAll("Laptop.findAll");
    }
    
    /**
     * Retrieves a list of all unused laptops.
     *
     * @return A list of all unused laptops in the database.
     */
    public List<Laptop> findAllUnused() {
        return em.createNamedQuery("Laptop.findAllUnused", Laptop.class).getResultList();
    }
    
    /**
     * Retrieves a list of employee's current laptops.
     *
     * @return a list of employee's current laptops in the database.
     */
    public List<Laptop> findByEmployeeID(Long employeeID) {
        return em.createNamedQuery("Laptop.findByEmployeeID", Laptop.class)
                .setParameter("employeeID", employeeID)
                .getResultList();
    }
    
    
    
    /**
     * Retrieves a list of laptops available for a specific employee.
     *
     * @param employeeID The ID of the employee to find available laptops for.
     * @return A list of laptops available for the specified employee.
     */
    public List<Laptop> findAllAvailableForEmployee(Long employeeID) {
        return em.createNamedQuery("Laptop.findAllAvailableForEmployee", Laptop.class)
                .setParameter("employeeID", employeeID)
                .getResultList();
    }
    
    /**
     * Deletes a laptop and its relationship with an employee.
     *
     * @param lt The laptop to delete.
     */
    public void deleteLaptopWRTRelationships(Laptop lt) {
        Laptop managedLaptopRef = em.getReference(Laptop.class, lt.getLaptopID());
        if(managedLaptopRef.getEmployee() != null){
            Employee e = managedLaptopRef.getEmployee();
            LOG.info("Removing laptop: " + lt.getName() + " from employee:" + e.getAuto_username());
            e.removeLaptop(managedLaptopRef);
            em.merge(e);
        }
        
        em.remove(managedLaptopRef); 
    }
    
    /**
     * Updates a laptop's information while maintaining relationships.
     *
     * @param lt The laptop to update.
     */
    public void updateLaptopWRTRelationships(Laptop lt){
        Laptop managedLaptopRef = em.getReference(Laptop.class, lt.getLaptopID());
        
        managedLaptopRef.setAssetTag(lt.getAssetTag());
        managedLaptopRef.setMakeModel(lt.getMakeModel());
        managedLaptopRef.setName(lt.getName());
        managedLaptopRef.setSerialNum(lt.getSerialNum());
        
        em.merge(managedLaptopRef);
    }
}
