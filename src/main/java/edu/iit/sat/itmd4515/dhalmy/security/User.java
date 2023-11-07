/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.security;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
@Entity
@Table(name = "SEC_USER")
@NamedQuery(name = "User.findAll", query = "select u from User u")
@EntityListeners(UserPasswordHash.class)
public class User {
    
    @Id
    @NotBlank(message = "Username field must not be left blank")
    private String username;
    @NotBlank(message = "Password field must not be left blank")
    private String password;
    private Boolean enabled;
    
    
    //owning side
    @ManyToMany
    @JoinTable(name = "SEC_USER_GROUPS",
            joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Group> groups = new ArrayList<>();



    
    public User() {
    }

    public User(String userName, String password, Boolean enabled) {
        this.username = userName;
        this.password = password;
        this.enabled = enabled;
    }
    
    //helper methods
    public void addGroup(Group g) {
        if (g != null && !this.groups.contains(g)) {
            this.groups.add(g);
            if (!g.getUsers().contains(this)) {
                g.getUsers().add(this);
            }
        }
    }
    
    public void removeGroup(Group g) {
        if (g != null && this.groups.contains(g)) {
            this.groups.remove(g);
            if (g.getUsers().contains(this)) {
                g.getUsers().remove(this);
            }
        }
    }
    

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }


    
}
