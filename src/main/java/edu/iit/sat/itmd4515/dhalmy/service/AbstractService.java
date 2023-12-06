/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public abstract class AbstractService<T> {

    private static final Logger LOG = Logger.getLogger(AbstractService.class.getName());
    
    
    
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;
    
    protected Class<T> entityClass;

    protected AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void create(T entity){
        LOG.info("INSIDE THE ABSTRACT SERVICE CREATE METHOD" + entity.toString());
        em.persist(entity);
//        LOG.info("INSIDE THE ABSTRACT SERVICE CREATE METHOD AFTER PERSISTING " + entity.toString() );
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
