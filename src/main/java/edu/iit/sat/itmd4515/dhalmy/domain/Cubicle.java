/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

/**
 *
 * @author David
 */
@Entity
@Table(name = "Cubicles")
@NamedQuery(name = "Cubicle.findAll", query = "select c from Cubicle c")
@NamedQuery(name = "Cubicle.findCubicleByDockingStationID", query = "select c from Cubicle c where c.dockingStation.dockID = :dockID")
public class Cubicle {
    @Id
    @Column(name = "Cubicle_Number")
    @Min(value = 415, message = "Cubicle ID must be at least 415")
    @Max(value = 635, message = "Cubicle ID must be at most 635")
    private int cubicleID;
    //there's a pre-set list of room/cubicle numbers to choose from, so no need to autogenerate ID
    
    
    //not sure if this is the right setup. i dont think this follows database convention
    //but i dont know how else to make it so one cubicle contains two different monitors
    //i could make it a "monitor pair" but it wouldn't be very modular, say if one monitor needs replacement
    //i would need to insert the new pair instead of removing the one old monitor and replacing with the new
    
    
    
    @OneToMany
    @JoinColumn(name = "cubicle_id")
    private List<Monitor> monitors = new ArrayList<>();
    
//now inverse side?
//    @OneToMany(mappedBy = "cubicle")
//    private List<Monitor> monitors;

    //i want the center of the database to be focused around the employee. from the employee
    //you know their laptop, their cube, and from their cube you can find out the cube's information
    @OneToMany(mappedBy = "cubicle")
    private List<Employee> employees = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "Dock_ID")
    private DockingStation dockingStation;
 

    public Cubicle() {
    }

    public Cubicle(int cubicleID) {
        this.cubicleID = cubicleID;
    }
    
    //helper methods
    public void addMonitor(Monitor m){
        if(m != null && !this.monitors.contains(m)){
            this.monitors.add(m);            
        }
    }
    
    public void removeMonitor(Monitor m){
        if(m != null && this.monitors.contains(m)){
            this.monitors.remove(m);            
        }
    }
    
//    public void addDockingStation(DockingStation dockingStation) {
//        if (dockingStation != null && this.dockingStation != dockingStation) {
//            dockingStation.setCubicle(this);
//            this.dockingStation = dockingStation;
//        }
//    }

//    public void removeDockingStation() {
//        if (this.dockingStation != null) {
//            this.dockingStation.setCubicle(null);
//            this.dockingStation = null;
//        }
//    }
    
    public void addEmployee(Employee e) {
        if (e != null && !this.employees.contains(e)) {
            this.employees.add(e);
            e.setCubicle(this);
        }
    }
    public void removeEmployee(Employee e) {
        if (e != null && this.employees.contains(e)) {
            this.employees.remove(e);
            e.setCubicle(null);
        }
    }
    
    
    

    public int getCubicleID() {
        return cubicleID;
    }

    public void setCubicleID(int cubicleID) {
        this.cubicleID = cubicleID;
    }


    public List<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<Monitor> monitors) {
        this.monitors = monitors;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public DockingStation getDockingStation() {
        return dockingStation;
    }

    public void setDockingStation(DockingStation dockingStation) {
        this.dockingStation = dockingStation;
    }
    
    

    @Override
    public String toString() {
        return "Cubicle{" + "cubicleID=" + cubicleID + ", monitors=" + monitors + ", dockingStation=" + dockingStation + '}';
    }
    
}
