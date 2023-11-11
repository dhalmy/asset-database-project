/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Laptop;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author David
 */
@Named
@Stateless
public class LaptopService extends AbstractService<Laptop> {
    
    public LaptopService(){
        super(Laptop.class);
    }
    
    public List<Laptop> findAll(){
        return super.findAll("Laptop.findAll");
    }
}
