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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
@Entity
@Table(name = "SEC_USER")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = "User.findAll", query = "select u from User u")
@NamedQuery(name = "User.findByUsernameWithGroups", query = "select u from User u left join fetch u.groups where u.username = :username")
@EntityListeners(UserPasswordHash.class)
public class User {

    private static final Logger LOG = Logger.getLogger(User.class.getName());
    
    @Id
    @NotBlank(message = "Username field must not be left blank")
    private String username;
    @NotBlank(message = "Password field must not be left blank")
    private String password;
    private Boolean enabled;
    
    
    //owning side
    @XmlTransient
    @ManyToMany
    @JoinTable(name = "SEC_USER_GROUPS",
            joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Group> groups = new ArrayList<>();

    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param userName
     * @param password
     * @param enabled
     */
    public User(String userName, String password, Boolean enabled) {
        this.username = userName;
        this.password = password;
        this.enabled = enabled;
    }
    
    //helper methods

    /**
     *
     * @param g
     */
    public void addGroup(Group g) {
        if (g != null && !this.groups.contains(g)) {
            this.groups.add(g);
            if (!g.getUsers().contains(this)) {
                g.getUsers().add(this);
            }
        }
    }
    
    /**
     *
     * @param g
     */
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

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     *
     * @return
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     *
     * @param groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }


    
}
