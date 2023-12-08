/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.security;

import jakarta.inject.Inject;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 * A class responsible for hashing user passwords using the Pbkdf2PasswordHash
 * algorithm. This class is used as an entity listener to automatically hash
 * passwords before persistence.
 *
 * @author David
 */
public class UserPasswordHash {

    @Inject
    private Pbkdf2PasswordHash hash;

    /**
     * Hashes the user's password before persisting it.
     *
     * @param u The user entity for which to hash the password.
     */
    @PrePersist
    @PreUpdate
    private void passwordHash(User u) {
        u.setPassword(hash.generate(u.getPassword().toCharArray()));
    }
}
