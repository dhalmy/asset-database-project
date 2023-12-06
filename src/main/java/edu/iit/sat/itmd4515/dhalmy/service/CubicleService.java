/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Cubicle;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author David
 */
@Named
@Stateless
public class CubicleService extends AbstractService<Cubicle> {

    public CubicleService() {
        super(Cubicle.class);
    }
    
    public List<Cubicle> findAll(){
        return super.findAll("Cubicle.findAll");
    }
    
}
