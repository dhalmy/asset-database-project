/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/**
 *
 * @author David
 */
@Named
@ApplicationScoped
@DeclareRoles({"ADMIN_ROLE","IT_ROLE","HR_ROLE"})
@DatabaseIdentityStoreDefinition(dataSourceLookup = "java:app/jdbc/itmd4515DS",
        callerQuery = "select PASSWORD from sec_user where USERNAME = ?",
        groupsQuery = "select GROUPNAME from sec_user_groups where USERNAME = ?")
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
            loginPage = "/login.xhtml",
            errorPage = "/error.xhtml"
        )
)
public class Itmd4515SecurityConfig {
    
}
