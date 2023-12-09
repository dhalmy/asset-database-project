/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Represents a laptop within the organization.
 * Each laptop is uniquely identified by an ID and has attributes such as asset tag, name, make and model,
 * serial number, and an associated employee.
 * 
 * @author David
 */
@Entity
@Table(name = "Laptops")
@XmlRootElement
@NamedQuery(name = "Laptop.findAll", query = "select a from Laptop a")
@NamedQuery(name = "Laptop.findAllUnused", query = "SELECT l FROM Laptop l WHERE l.employee IS NULL")
@NamedQuery(name = "Laptop.findAllAvailableForEmployee", 
            query = "SELECT l FROM Laptop l WHERE l.employee IS NULL OR l.employee.employeeID = :employeeID")
@NamedQuery(name = "Laptop.findByEmployeeID", 
            query = "SELECT l FROM Laptop l WHERE l.employee.employeeID = :employeeID")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Laptop_ID")
    private Long laptopID;
    
    @Pattern(regexp = "^[Fx][a-zA-Z0-9]*", message = "Asset tag must start with 'F' or 'x' and contain only letters and numbers")
    @Column(unique = true)
    private String assetTag;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Name must contain only letters, numbers, and hyphens")
    private String name;

    @NotBlank(message = "Make and model is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*", message = "Make and model must contain only letters and numbers")
    @Column(nullable = false, name = "Make_and_Model")
    private String makeModel;

    @NotBlank(message = "Serial number is required")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]*", message = "Serial number must contain only letters and numbers")
    private String serialNum;
    
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    /**
     * Constructs a new Laptop object with default values.
     */
    public Laptop() {
    }

    /**
     * Constructs a new Laptop object with the specified attributes.
     * 
     * @param assetTag The asset tag of the laptop.
     * @param name The name of the laptop.
     * @param makeModel The make and model of the laptop.
     * @param serialNum The serial number of the laptop.
     * @param employee The employee associated with the laptop.
     */
    public Laptop(String assetTag, String name, String makeModel, String serialNum, Employee employee) {
        this.assetTag = assetTag;
        this.name = name;
        this.makeModel = makeModel;
        this.serialNum = serialNum;
        this.employee = null;
    }

    /**
     * Constructs a new Laptop object with the specified name, make and model, and serial number.
     * 
     * @param name The name of the laptop.
     * @param makeModel The make and model of the laptop.
     * @param serialNum The serial number of the laptop.
     */
    public Laptop(String name, String makeModel, String serialNum) {
        this.name = name;
        this.makeModel = makeModel;
        this.serialNum = serialNum;
    }
    
    
    /**
     * Constructs a new Laptop object with the specified name, make and model, and serial number.
     * 
     * @param assetTag The asset tag of the laptop.
     * @param name The name of the laptop.
     * @param makeModel The make and model of the laptop.
     * @param serialNum The serial number of the laptop.
     */
    public Laptop(String assetTag, String serialNum, String name, String makeModel) {
        this.assetTag = assetTag;
        this.name = name;
        this.makeModel = makeModel;
        this.serialNum = serialNum;
    }

    /**
     * Computes the hash code of the laptop based on its ID.
     * 
     * @return The hash code of the laptop.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.laptopID);
        return hash;
    }

    /**
     * Compares this laptop to another object for equality.
     * Two laptops are considered equal if their IDs are equal.
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
        final Laptop other = (Laptop) obj;
        
        // If we are relying on GeneratedValue for ID, we need to check
        // whether either ID is null in order to rely on the field
        // iff null, can't be equal
        if (this.laptopID == null || other.laptopID == null) {
            return false;
        }
        return Objects.equals(this.laptopID, other.laptopID);
    }

    /**
     * Gets the unique identifier (ID) of the laptop.
     * 
     * @return The ID of the laptop.
     */
    public Long getLaptopID() {
        return laptopID;
    }

    /**
     * Sets the unique identifier (ID) of the laptop.
     * 
     * @param laptopID The ID to set for the laptop.
     */
    public void setLaptopID(Long laptopID) {
        this.laptopID = laptopID;
    }

    /**
     * Gets the asset tag of the laptop.
     * 
     * @return The asset tag.
     */
    public String getAssetTag() {
        return assetTag;
    }

    /**
     * Sets the asset tag of the laptop.
     * 
     * @param assetTag The asset tag to set.
     */
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    /**
     * Gets the name of the laptop.
     * 
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the laptop.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the make and model of the laptop.
     * 
     * @return The make and model.
     */
    public String getMakeModel() {
        return makeModel;
    }

    /**
     * Sets the make and model of the laptop.
     * 
     * @param makeModel The make and model to set.
     */
    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    /**
     * Gets the serial number of the laptop.
     * 
     * @return The serial number.
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * Sets the serial number of the laptop.
     * 
     * @param serialNum The serial number to set.
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * Gets the employee associated with the laptop.
     * 
     * @return The associated employee or null if unassigned.
     */
    public Employee getEmployee() {
        return this.employee;
    }
    
    /**
     * Associates the laptop with an employee.
     * If the laptop was previously assigned to another employee, it is removed from their list of laptops.
     * 
     * @param employee The employee to associate with the laptop.
     */
    public void addEmployee(Employee employee) {
        if(this.employee != null){
            this.employee.getLaptops().remove(this);
        }
        this.employee = employee;
        if(employee != null && !employee.getLaptops().contains(this)){
            employee.getLaptops().add(this);
        }
    }
    
    /**
     * Disassociates the laptop from its current employee, if any.
     */
    public void removeEmployee(){
        if(this.employee != null){
            Employee tempEmployee = this.employee;
            this.employee = null;
            tempEmployee.getLaptops().remove(this);
        }
    }

    /**
     * Returns a string representation of the laptop, including the ID, asset tag, name, make and model,
     * serial number, and employee ID (if assigned).
     * 
     * @return A string representation of the laptop.
     */
    @Override
    public String toString() {
        return "Laptop{" + "laptopID=" + laptopID + ", assetTag=" + assetTag + ", name=" + name + ", makeModel=" + makeModel + ", serialNum=" + serialNum + ", employeeID=" + (employee != null ? employee.getEmployeeID() : "null") + '}';
    }
}
