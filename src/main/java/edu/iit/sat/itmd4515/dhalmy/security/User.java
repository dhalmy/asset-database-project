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
 * Entity class representing user information including username, password, and group associations.
 * This class is used for authentication and authorization in the application.
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

    // Owning side of the many-to-many relationship with groups
    @XmlTransient
    @ManyToMany
    @JoinTable(name = "SEC_USER_GROUPS",
            joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Group> groups = new ArrayList<>();

    /**
     * Default constructor for the User class.
     */
    public User() {
    }

    /**
     * Constructs a User object with the given username, password, and enabled status.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param enabled  The enabled status of the user.
     */
    public User(String username, String password, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    /**
     * Add a group association to the user.
     *
     * @param group The group to add.
     */
    public void addGroup(Group group) {
        if (group != null && !this.groups.contains(group)) {
            this.groups.add(group);
            if (!group.getUsers().contains(this)) {
                group.getUsers().add(this);
            }
        }
    }

    /**
     * Remove a group association from the user.
     *
     * @param group The group to remove.
     */
    public void removeGroup(Group group) {
        if (group != null && this.groups.contains(group)) {
            this.groups.remove(group);
            if (group.getUsers().contains(this)) {
                group.getUsers().remove(this);
            }
        }
    }

    /**
     * Get the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Check if the user is enabled.
     *
     * @return True if the user is enabled, false otherwise.
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the enabled status of the user.
     *
     * @param enabled The new enabled status.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Get the list of groups associated with the user.
     *
     * @return The list of groups.
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Set the list of groups associated with the user.
     *
     * @param groups The new list of groups.
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}

