/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author David
 */
@Stateless
public class EmployeeService {
    
    @PersistenceContext(name = "itm4515PU")
    protected EntityManager em;

    public EmployeeService() {
    }
    
    public void create(Employee e){
        em.persist(e);
    }
    
    public Employee read(Long employeeID){
        return em.find(Employee.class, employeeID);
    }
    
    public void update(Employee e){
        em.merge(e);
        
    }
    
    public void delete(Employee e){
        em.remove(em.merge(e));
    }
    
    public List<Employee> findAll(){
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }
}
