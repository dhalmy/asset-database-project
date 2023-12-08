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
     *
     */
    public Group() {
    }

    /**
     *
     * @param groupName
     * @param groupProperty
     */
    public Group(String groupName, String groupProperty) {
        this.groupName = groupName;
        this.groupDescription = groupProperty;
    }

    /**
     * Get the value of groupName
     *
     * @return the value of groupName
     */
    public String getGroupName() {
        return groupName;
    }
    
    //helper methods

    /**
     *
     * @param u
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
     *
     * @param u
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
     * Set the value of groupName
     *
     * @param groupName new value of groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     *
     * @return
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     *
     * @param groupDescription
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     *
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    
    
    
}
