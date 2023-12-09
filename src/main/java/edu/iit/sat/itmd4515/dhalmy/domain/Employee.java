/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.domain;


import edu.iit.sat.itmd4515.dhalmy.security.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

/**
 * Represents an employee within the organization.
 * Each employee is uniquely identified by an ID and has attributes such as first name, last name, username,
 * hire date, department, and associated assets like laptops and cubicles.
 * 
 * @author David
 */
@Entity
@Table(name = "Employees")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = "Employee.findAvailableForCubicle", 
            query = "SELECT e FROM Employee e WHERE e.cubicle IS NULL OR e.cubicle.cubicleID = :cubicleID")
@NamedQuery(name = "Employee.findAll", query = "select e from Employee e")
@NamedQuery(name = "Employee.findByCubicleID", 
            query = "SELECT e FROM Employee e WHERE e.cubicle.cubicleID = :cubicleID")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeID;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain letters and not be empty")
    private String firstName;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain letters and not be empty")
    private String lastName;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must contain only letters and numbers")
    private String auto_username;

    @PastOrPresent
    private LocalDate hireDate;

    @Column(name = "DEPARTMENT")
    @Enumerated(EnumType.STRING)
    private EmployeeDepartment type;
    
//    @Email
    //validator not needed because email is statically generated based off of the auto_username
    private String email;
    
    @XmlTransient
    @OneToOne
    @JoinColumn(name = "user")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "Cubicle_Number")
    private Cubicle cubicle;
    
    //had difficulty implementing employee as the owner of laptops and keep it bidirectional. can't have
    //manyToOne and be the inverse
    
    @OneToMany(mappedBy = "employee")
//    @JoinColumn(name = "laptop_id")
    @XmlTransient
    private List<Laptop> laptops = new ArrayList<>();

    /**
     * Constructs a new Employee object with default values.
     */
    public Employee() {

    }
    
    /**
     * Constructs a new Employee object with the specified attributes.
     * 
     * @param firstName The first name of the employee.
     * @param lastName The last name of the employee.
     * @param username The username of the employee.
     * @param hireDate The hire date of the employee.
     * @param type The department of the employee.
     */
    public Employee(String firstName, String lastName, String username, LocalDate hireDate, EmployeeDepartment type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.auto_username = username;
        this.hireDate = hireDate;
        this.type = type;
        this.email = username + "@cats.illinois.gov";
    }

    /**
     * Constructs a new Employee object with the specified first name, last name, and username.
     * 
     * @param firstName The first name of the employee.
     * @param lastName The last name of the employee.
     * @param username The username of the employee.
     */
    public Employee(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.auto_username = username;
        this.email = username + "@cats.illinois.gov";
    }
    
    /**
     * Constructs a new Employee object with the specified first name, last name, and username without taking username parameter.
     * 
     * @param firstName The first name of the employee.
     * @param lastName The last name of the employee.
     */
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        String user = (firstName.substring(0, 1) + lastName).toLowerCase();
        this.auto_username = user;
        this.email = user + "@cats.illinois.gov";
    }

    //helper methods

    /**
     * Adds a laptop to the list of laptops associated with this employee.
     * 
     * @param lt The laptop to add.
     */
    public void addLaptop(Laptop lt) {
        if (lt != null && !this.laptops.contains(lt)) {
            this.laptops.add(lt);
            lt.addEmployee(this);  // Ensures the other side of the relationship is updated
        }
    }
    
    /**
     * Removes a laptop from the list of laptops associated with this employee.
     * 
     * @param lt The laptop to remove.
     */
    public void removeLaptop(Laptop lt) {
        if (lt != null && this.laptops.contains(lt)) {
            this.laptops.remove(lt);
            lt.removeEmployee();
        }
    }
    
    /**
     * Associates this employee with a cubicle, replacing any existing association.
     * 
     * @param c The cubicle to associate with.
     */
    public void addCubicle(Cubicle c){
        //checks if there's already a cubicle attached; if yes remove then add. if not just add.
        if(c != null && (this.cubicle == null || !this.cubicle.equals(c))){
            if(this.cubicle != null){
                this.cubicle.getEmployees().remove(this);
            }
            c.getEmployees().add(this);
            this.cubicle = c;
        }
    }
    
    /**
     * Disassociates this employee from any cubicle.
     */
    public void removeCubicle() {
        if (this.cubicle != null) {
            this.cubicle.removeEmployee(this);
            this.cubicle = null;
        }
    }
    /**
     * Gets the unique identifier (ID) of the employee.
     * 
     * @return The ID of the employee.
     */
    public Long getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the unique identifier (ID) of the employee.
     * 
     * @param employeeID The ID to set for the employee.
     */
    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Gets the first name of the employee.
     * 
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     * 
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the employee.
     * 
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     * 
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the username of the employee.
     * 
     * @return The username.
     */
    public String getAuto_username() {
        return auto_username;
    }

    /**
     * Sets the username of the employee.
     * 
     * @param auto_username The username to set.
     */
    public void setAuto_username(String auto_username) {
        this.auto_username = auto_username;
    }

    /**
     * Gets the hire date of the employee.
     * 
     * @return The hire date.
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * Sets the hire date of the employee.
     * 
     * @param hireDate The hire date to set.
     */
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Gets the department of the employee.
     * 
     * @return The department.
     */
    public EmployeeDepartment getType() {
        return type;
    }

    /**
     * Sets the department of the employee.
     * 
     * @param type The department to set.
     */
    public void setType(EmployeeDepartment type) {
        this.type = type;
    }
    
    /**
     * Gets the email address of the employee.
     * 
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the employee.
     * 
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Computes the hash code of the employee based on its ID.
     * 
     * @return The hash code of the employee.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.employeeID);
        return hash;
    }

    /**
     * Compares this employee to another object for equality.
     * Two employees are considered equal if their IDs are equal.
     * 
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;

        // If we are relying on GeneratedValue for ID, we need to check
        // whether either ID is null in order to rely on the field
        // iff null, can't be equal
        if (this.employeeID == null || other.employeeID == null) {
            return false;
        }

        return Objects.equals(this.employeeID, other.employeeID);
    }

    /**
     * Gets the list of laptops associated with the employee.
     * 
     * @return The list of laptops.
     */
    public List<Laptop> getLaptops() {
        return laptops;
    }

    /**
     * Sets the list of laptops associated with the employee.
     * 
     * @param laptops The list of laptops to set.
     */
    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    /**
     * Returns a string representation of the employee, including the ID, first name, last name, username, hire date,
     * department, email, and associated laptops (excluding cubicle details because of overflow error).
     * 
     * @return A string representation of the employee.
     */
    @Override
    public String toString() {
        return "Employee{" + "employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + auto_username + ", hireDate=" + hireDate + ", type=" + type + ", email=" + email + ", cubicle=" + "not to show" + ", laptops=" + laptops + '}';
    }

    /**
     * Gets the cubicle associated with the employee.
     * 
     * @return The associated cubicle.
     */
    public Cubicle getCubicle() {
        return cubicle;
    }

    /**
     * Sets the cubicle associated with the employee, managing both sides of the
     * relationship.
     *
     * @param newCubicle The cubicle to associate with.
     */
    public void setCubicle(Cubicle newCubicle) {
        // If currently associated with a different cubicle, remove the association.
        if (this.cubicle != null && !this.cubicle.equals(newCubicle)) {
            this.cubicle.getEmployees().remove(this);
        }

        // Update the cubicle reference.
        this.cubicle = newCubicle;

        // If the new cubicle is not null and does not already contain this employee, add the employee.
        if (newCubicle != null && !newCubicle.getEmployees().contains(this)) {
            newCubicle.getEmployees().add(this);
        }
    }
    
    /**
     * Gets the user associated with the employee.
     * 
     * @return The associated user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the employee.
     * 
     * @param user The user to associate with.
     */
    public void setUser(User user) {
        this.user = user;
    }
}