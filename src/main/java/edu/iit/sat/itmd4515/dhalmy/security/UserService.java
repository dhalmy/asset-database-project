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
 *
 * @author David
 */
@Stateless
public class UserService extends AbstractService<User> {

    /**
     *
     */
    public UserService() {
        super(User.class);
    }
    
    /**
     *
     * @return
     */
    public List<User> findAll(){
        return super.findAll("User.findAll");
    }
    
    /**
     *
     * @param username
     * @return
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
