/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author David
 */
abstract class AbstractService<T> {
    
    @PersistenceContext(name = "itmd4515PU")
    public EntityManager em;
    
    public Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    
    
    public void create(T entity){
        em.persist(entity);
    }
    
    public T read(Long id){
        return em.find(entityClass, id);
    }
    
    public void update(T entity){
        em.merge(entity);
        
    }
    
    public void delete(T entity){
        em.remove(em.merge(entity));
    }
    
    protected List<T> findAll(String namedQueryName){
        return em.createNamedQuery(namedQueryName, entityClass).getResultList();
    }
    
}
