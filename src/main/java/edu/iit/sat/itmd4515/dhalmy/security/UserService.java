/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.security;

import edu.iit.sat.itmd4515.dhalmy.service.AbstractService;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import java.util.List;

/**
 * This stateless session bean provides services related to the User entity.
 * It extends the AbstractService class for common CRUD operations.
 *
 * @author David
 */
@Stateless
public class UserService extends AbstractService<User> {

    /**
     * Initializes the UserService by setting the entity class type.
     */
    public UserService() {
        super(User.class);
    }
    
    /**
     * Retrieves a list of all users in the system.
     *
     * @return A list of User entities representing all users in the system.
     */
    public List<User> findAll(){
        return super.findAll("User.findAll");
    }
    
    /**
     * Retrieves a User entity with associated groups based on the provided username.
     *
     * @param username The username of the user to retrieve.
     * @return A User entity with associated groups if found, or null if not found.
     */
    public User findUserWithGroupsByUsername(String username) {
        try {
            return em.createNamedQuery("User.findByUsernameWithGroups", User.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

