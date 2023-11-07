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
import java.util.Objects;

/**
 *
 * @author David
 */
@Entity
@Table(name = "Laptops")
@NamedQuery(name = "Laptop.findAll", query = "select a from Laptop a")
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
    
    
    
    
    public Laptop() {
    }

    public Laptop(String assetTag, String name, String makeModel, String serialNum, Employee employee) {
        this.assetTag = assetTag;
        this.name = name;
        this.makeModel = makeModel;
        this.serialNum = serialNum;
        this.employee = employee;
    }

    public Laptop(String name, String makeModel, String serialNum) {
        this.name = name;
        this.makeModel = makeModel;
        this.serialNum = serialNum;
    }






    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.laptopID);
        return hash;
    }

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
        
        //if we are relying on GeneratedValue for ID, we need to check
        //whether either ID is null in order to rely on the field
        //iff null, can't be equal
        if (this.laptopID == null || other.laptopID == null) {
            return false;
        }
        return Objects.equals(this.laptopID, other.laptopID);
    }

    public Long getLaptopID() {
        return laptopID;
    }

    public void setLaptopID(Long laptopID) {
        this.laptopID = laptopID;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }


    public String getSerialNum() {
        return serialNum;
    }
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
    public Employee getEmployee() {
        return this.employee;
    }
    
    public void addEmployee(Employee employee) {
        if(this.employee != null){
            this.employee.getLaptops().remove(this);
        }
        this.employee = employee;
        if(employee != null && !employee.getLaptops().contains(this)){
            employee.getLaptops().add(this);
        }
    }
    
    public void removeEmployee(){
        if(this.employee != null){
            Employee tempEmployee = this.employee;
            this.employee = null;
            tempEmployee.getLaptops().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Laptop{" + "laptopID=" + laptopID + ", assetTag=" + assetTag + ", name=" + name + ", makeModel=" + makeModel + ", serialNum=" + serialNum + ", employeeID=" + (employee != null ? employee.getEmployeeID() : "null") + '}';
    }

    
}
