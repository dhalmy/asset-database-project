/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.security;

import edu.iit.sat.itmd4515.dhalmy.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Service class for managing and interacting with user groups in the
 * application. This class provides methods to work with groups, such as finding
 * all groups.
 *
 * @author David
 */
@Stateless
public class GroupService extends AbstractService<Group> {

    /**
     * Default constructor for the GroupService. Initializes the service to work
     * with the Group entity.
     */
    public GroupService() {
        super(Group.class);
    }

    /**
     * Retrieve a list of all user groups in the application.
     *
     * @return A list of all user groups.
     */
    public List<Group> findAll() {
        return super.findAll("Group.findAll");
    }
}
