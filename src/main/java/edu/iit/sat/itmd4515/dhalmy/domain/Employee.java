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
    
    //had difficulty implementing employee as the owner of laptops and keep it bidrectional. can't have
    //manyToOne and be the inverse
    
    @OneToMany(mappedBy = "employee")
//    @JoinColumn(name = "laptop_id")
    @XmlTransient
    private List<Laptop> laptops = new ArrayList<>();

    /**
     *
     */
    public Employee() {

    }
    
    /**
     *
     * @param firstName
     * @param lastName
     * @param username
     * @param hireDate
     * @param type
     */
    public Employee(String firstName, String lastName, String username, LocalDate hireDate, EmployeeDepartment type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.auto_username = username;
        this.hireDate = hireDate;
        this.type = type;
        this.email = username + "@cats.illinois.gov";
    }

    //only must have requirements to add a employee

    /**
     *
     * @param firstName
     * @param lastName
     * @param username
     */
    public Employee(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.auto_username = username;
        this.email = username + "@cats.illinois.gov";
    }

    
    //helper methods

    /**
     *
     * @param lt
     */
    public void addLaptop(Laptop lt) {
        if (lt != null && !this.laptops.contains(lt)) {
            this.laptops.add(lt);
            lt.addEmployee(this);  // Ensures the other side of the relationship is updated
        }
    }
    
    /**
     *
     * @param lt
     */
    public void removeLaptop(Laptop lt) {
        if (lt != null && this.laptops.contains(lt)) {
            this.laptops.remove(lt);
            lt.removeEmployee();
        }
    }
    
    /**
     *
     * @param c
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
     *
     */
    public void removeCubicle() {
        if (this.cubicle != null) {
            this.cubicle.removeEmployee(this);
            this.cubicle = null;
        }
    }
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getEmployeeID() {
        return employeeID;
    }

    /**
     * Set the value of id
     *
     * @param employeeID new value of id
     */
    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getAuto_username() {
        return auto_username;
    }

    /**
     *
     * @param auto_username
     */
    public void setAuto_username(String auto_username) {
        this.auto_username = auto_username;
    }

    /**
     *
     * @return
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     *
     * @param hireDate
     */
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    /**
     *
     * @return
     */
    public EmployeeDepartment getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(EmployeeDepartment type) {
        this.type = type;
    }
    
    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.employeeID);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
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

        //if we are relying on GeneratedValue for ID, we need to check
        //whether either ID is null in order to rely on the field
        //iff null, can't be equal
        if (this.employeeID == null || other.employeeID == null) {
            return false;
        }

        return Objects.equals(this.employeeID, other.employeeID);
    }

    /**
     *
     * @return
     */
    public List<Laptop> getLaptops() {
        return laptops;
    }

    /**
     *
     * @param laptops
     */
    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Employee{" + "employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + auto_username + ", hireDate=" + hireDate + ", type=" + type + ", email=" + email + ", cubicle=" + "not to show" + ", laptops=" + laptops + '}';
    }

    /**
     *
     * @return
     */
    public Cubicle getCubicle() {
        return cubicle;
    }

    /**
     *
     * @param newCubicle
     */
    public void setCubicle(Cubicle newCubicle) {
        if (this.cubicle != null) {
            this.cubicle.getEmployees().remove(this);
        }
        this.cubicle = newCubicle;
        if (newCubicle != null) {
            newCubicle.getEmployees().add(this);
        }
    }
    
    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

 


}
