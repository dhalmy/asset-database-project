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
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents a cubicle in an office environment. Each cubicle may contain
 * monitors, employees, and may be associated with a docking station. The
 * cubicle is identified by a unique ID. Cubicle numbers range from 415 to 635
 *
 * @author David
 */
@Entity
@Table(name = "Cubicles")
@XmlRootElement
@NamedQuery(name = "Cubicle.findAll", query = "select c from Cubicle c")
@NamedQuery(name = "Cubicle.findCubicleByDockingStationID", query = "select c from Cubicle c where c.dockingStation.dockID = :dockID")
@NamedQuery(name = "Cubicle.findAvailableForDockingStations", query = "SELECT c FROM Cubicle c WHERE c.dockingStation IS NULL OR c.dockingStation = :currentDock")
public class Cubicle {

    private static final Logger LOG = Logger.getLogger(Cubicle.class.getName());

    @Id
    @Column(name = "Cubicle_Number")
    @Min(value = 415, message = "Cubicle ID must be at least 415")
    @Max(value = 635, message = "Cubicle ID must be at most 635")
    private int cubicleID;

    // There's a pre-set list of room/cubicle numbers to choose from, so no need to autogenerate ID
    @OneToMany
    @JoinColumn(name = "cubicle_id")
    private List<Monitor> monitors = new ArrayList<>();

    @OneToMany(mappedBy = "cubicle")
    private List<Employee> employees = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "Dock_ID")
    private DockingStation dockingStation;

    /**
     * Constructs a new Cubicle object with default values.
     */
    public Cubicle() {
    }

    /**
     * Constructs a new Cubicle object with the specified cubicle ID.
     *
     * @param cubicleID The unique identifier for the cubicle.
     */
    public Cubicle(int cubicleID) {
        this.cubicleID = cubicleID;
    }

    // Helper methods
    /**
     * Adds a monitor to the list of monitors in this cubicle.
     *
     * @param m The monitor to be added.
     */
    public void addMonitor(Monitor m) {
        if (m != null && !this.monitors.contains(m)) {
            this.monitors.add(m);
            m.setCubicle(this);
        }
    }

    /**
     * Removes a monitor from the list of monitors in this cubicle.
     *
     * @param m The monitor to be removed.
     */
    public void removeMonitor(Monitor m) {
        if (m != null && this.monitors.contains(m)) {
            this.monitors.remove(m);
            m.setCubicle(null);
        }
    }

    /**
     * Adds a docking station to this cubicle.
     *
     * @param dockingStation The docking station to be added.
     */
    public void addDockingStation(DockingStation dockingStation) {
        if (dockingStation != null && this.dockingStation != dockingStation) {
            removeDockingStation();
            this.dockingStation = dockingStation;
        }
    }

    /**
     * Removes the docking station from this cubicle.
     */
    public void removeDockingStation() {
        if (this.dockingStation != null) {
            this.dockingStation = null;
        }
    }

    /**
     * Adds an employee to this cubicle.
     *
     * @param e The employee to be added.
     */
    public void addEmployee(Employee e) {
        if (e != null && !this.employees.contains(e)) {
            this.employees.add(e);
            e.setCubicle(this);
        }
    }

    /**
     * Removes an employee from this cubicle.
     *
     * @param e The employee to be removed.
     */
    public void removeEmployee(Employee e) {
        if (e != null && this.employees.contains(e)) {
            this.employees.remove(e);
            e.setCubicle(null);
        }
    }

    /**
     * Gets the cubicle's unique identifier.
     *
     * @return The cubicle's ID.
     */
    public int getCubicleID() {
        return cubicleID;
    }

    /**
     * Sets the cubicle's unique identifier.
     *
     * @param cubicleID The unique identifier to set.
     */
    public void setCubicleID(int cubicleID) {
        this.cubicleID = cubicleID;
    }

    /**
     * Gets the list of monitors in this cubicle.
     *
     * @return The list of monitors.
     */
    public List<Monitor> getMonitors() {
        return monitors;
    }

    /**
     * Sets the list of monitors in this cubicle.
     *
     * @param monitors The list of monitors to set.
     */
    public void setMonitors(List<Monitor> monitors) {
        this.monitors = monitors;
    }

    /**
     * Gets the list of employees associated with this cubicle.
     *
     * @return The list of employees.
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Sets the list of employees associated with this cubicle.
     *
     * @param employees The list of employees to set.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Gets the docking station associated with this cubicle.
     *
     * @return The docking station.
     */
    public DockingStation getDockingStation() {
        return dockingStation;
    }

    /**
     * Sets the docking station associated with this cubicle.
     *
     * @param dockingStation The docking station to set.
     */
    public void setDockingStation(DockingStation dockingStation) {
        this.dockingStation = dockingStation;
    }

    /**
     * Returns a string representation of this cubicle, including its ID, monitors, employees, and docking station.
     * 
     * @return A string representation of the cubicle.
     */
    @Override
    public String toString() {
        return "Cubicle{" + "cubicleID=" + cubicleID + ", monitors=" + monitors + ", employees=" + employees + ", dockingStation=" + dockingStation + '}';
    }
}
