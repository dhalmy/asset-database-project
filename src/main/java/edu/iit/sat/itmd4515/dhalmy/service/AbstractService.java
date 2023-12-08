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
 * An abstract base class for service classes that manage entities in the persistence context.
 * This class provides common CRUD (Create, Read, Update, Delete) operations for entity management.
 *
 * @param <T> The type of entity managed by the service.
 * @author David
 */
public abstract class AbstractService<T> {

    private static final Logger LOG = Logger.getLogger(AbstractService.class.getName());

    /**
     * The entity manager used to interact with the persistence context.
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;

    /**
     * The class representing the entity managed by the service.
     */
    protected Class<T> entityClass;

    /**
     * Constructs an AbstractService instance for managing entities of the specified type.
     *
     * @param entityClass The class representing the entity type managed by the service.
     */
    protected AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Creates a new entity in the persistence context.
     *
     * @param entity The entity to create.
     */
    public void create(T entity) {
        LOG.info("Inside the Abstract Service create method: " + entity.toString());
        em.persist(entity);
        LOG.info("Inside the Abstract Service create method after persisting: " + entity.toString());
    }

    /**
     * Reads an entity from the persistence context by its unique identifier.
     *
     * @param id The unique identifier of the entity to read.
     * @return The retrieved entity, or null if not found.
     */
    public T read(Long id) {
        return em.find(entityClass, id);
    }

    /**
     * Updates an existing entity in the persistence context.
     *
     * @param entity The entity to update.
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     * Deletes an entity from the persistence context.
     *
     * @param entity The entity to delete.
     */
    public void delete(T entity) {
        em.remove(em.merge(entity));
    }

    /**
     * Finds and returns a list of entities based on a named query.
     *
     * @param namedQueryName The name of the named query.
     * @return A list of entities that match the criteria defined by the named query.
     */
    protected List<T> findAll(String namedQueryName) {
        return em.createNamedQuery(namedQueryName, entityClass).getResultList();
    }
}
