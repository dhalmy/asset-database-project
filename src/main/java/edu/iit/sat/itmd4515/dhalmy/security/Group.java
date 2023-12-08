/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing user groups in the application.
 * Each group has a name and description and can be associated with multiple users.
 * 
 * @author David
 */
@Entity
@Table(name = "SEC_GROUP")
@NamedQuery(name = "Group.findAll", query = "select g from Group g")
public class Group {

    @Id
    private String groupName;
    private String groupDescription;
    
    @ManyToMany(mappedBy = "groups")
    private List<User> users = new ArrayList<>();

    /**
     * Default constructor for the Group entity.
     */
    public Group() {
    }

    /**
     * Constructor for creating a Group with a given name and description.
     *
     * @param groupName The name of the group.
     * @param groupDescription The description of the group.
     */
    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    /**
     * Get the name of the group.
     *
     * @return The name of the group.
     */
    public String getGroupName() {
        return groupName;
    }
    
    // Helper methods for managing users in the group.

    /**
     * Add a user to the group.
     *
     * @param u The user to be added.
     */
    public void addUser(User u) {
        if (u != null && !this.users.contains(u)) {
            this.users.add(u);
            if (!u.getGroups().contains(this)) {
                u.getGroups().add(this);
            }
        }
    }

    /**
     * Remove a user from the group.
     *
     * @param u The user to be removed.
     */
    public void removeUser(User u) {
        if (u != null && this.users.contains(u)) {
            this.users.remove(u);
            if (u.getGroups().contains(this)) {
                u.getGroups().remove(this);
            }
        }
    }

    /**
     * Set the name of the group.
     *
     * @param groupName The new name for the group.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Get the description of the group.
     *
     * @return The description of the group.
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * Set the description of the group.
     *
     * @param groupDescription The new description for the group.
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     * Get the list of users belonging to the group.
     *
     * @return The list of users in the group.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Set the list of users belonging to the group.
     *
     * @param users The list of users to set for the group.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
}

