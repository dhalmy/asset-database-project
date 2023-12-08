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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Represents a monitor within the organization.
 * Each monitor is uniquely identified by an ID and has attributes such as asset tag, serial number, make and model,
 * and an associated cubicle (location).
 * 
 * @author David
 */
@Entity
@Table(name = "Monitors")
@XmlRootElement
@NamedQuery(name = "Monitor.findAll", query = "select m from Monitor m")
@NamedQuery(name = "Monitor.findAvailableForCubicle", 
            query = "SELECT m FROM Monitor m WHERE m.cubicle IS NULL OR m.cubicle.cubicleID = :cubicleID")
@NamedQuery(name = "Monitor.findByCubicleID", 
            query = "SELECT m FROM Monitor m WHERE m.cubicle.cubicleID = :cubicleID")
public class Monitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Monitor_ID")
    private Long monitorID;
    
    @Pattern(regexp = "^[Fx][a-zA-Z0-9]*", message = "Asset tag must start with 'F' or 'x' and contain only letters and numbers")
    @Column(unique = true)
    private String assetTag;
    
    @NotBlank(message = "Serial number is required")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]*", message = "Serial number must contain only letters and numbers")
    private String serialNum;
    
    @NotBlank(message = "Make and model is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*", message = "Make and model must contain only letters and numbers")
    @Column(nullable = false, name = "Make_and_Model")
    private String makeModel;
    
    @ManyToOne
    @JoinColumn(name = "cubicle_id")
    private Cubicle cubicle;
    
    /**
     * Constructs a new Monitor object with default values.
     */
    public Monitor() {
    }

    /**
     * Constructs a new Monitor object with the specified asset tag, serial number, and make and model.
     * 
     * @param assetTag The asset tag of the monitor.
     * @param serialNum The serial number of the monitor.
     * @param makeModel The make and model of the monitor.
     */
    public Monitor(String assetTag, String serialNum, String makeModel) {
        this.assetTag = assetTag;
        this.serialNum = serialNum;
        this.makeModel = makeModel;
    }

    /**
     * Constructs a new Monitor object with the specified serial number and make and model.
     * 
     * @param serialNum The serial number of the monitor.
     * @param makeModel The make and model of the monitor.
     */
    public Monitor(String serialNum, String makeModel) {
        this.serialNum = serialNum;
        this.makeModel = makeModel;
    }

    /**
     * Gets the unique identifier (ID) of the monitor.
     * 
     * @return The ID of the monitor.
     */
    public Long getMonitorID() {
        return monitorID;
    }

    /**
     * Sets the unique identifier (ID) of the monitor.
     * 
     * @param monitorID The ID to set for the monitor.
     */
    public void setMonitorID(Long monitorID) {
        this.monitorID = monitorID;
    }

    /**
     * Computes the hash code of the monitor based on its ID.
     * 
     * @return The hash code of the monitor.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.monitorID);
        return hash;
    }

    /**
     * Compares this monitor to another object for equality.
     * Two monitors are considered equal if their IDs are equal.
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
        final Monitor other = (Monitor) obj;
        
        // If we are relying on GeneratedValue for ID, we need to check
        // whether either ID is null in order to rely on the field
        // iff null, can't be equal
        if (this.monitorID == null || other.monitorID == null) {
            return false;
        }
        return Objects.equals(this.monitorID, other.monitorID);
    }

    /**
     * Gets the serial number of the monitor.
     * 
     * @return The serial number.
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * Sets the serial number of the monitor.
     * 
     * @param serialNum The serial number to set.
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * Gets the make and model of the monitor.
     * 
     * @return The make and model.
     */
    public String getMakeModel() {
        return makeModel;
    }

    /**
     * Sets the make and model of the monitor.
     * 
     * @param makeModel The make and model to set.
     */
    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    /**
     * Gets the asset tag of the monitor.
     * 
     * @return The asset tag.
     */
    public String getAssetTag() {
        return assetTag;
    }

    /**
     * Sets the asset tag of the monitor.
     * 
     * @param assetTag The asset tag to set.
     */
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }
    
    /**
     * Gets the cubicle (location) associated with the monitor.
     * 
     * @return The associated cubicle or null if unassigned.
     */
    public Cubicle getCubicle() {
        return cubicle;
    }

    /**
     * Sets the cubicle (location) associated with the monitor.
     * 
     * @param cubicle The cubicle to set for the monitor.
     */
    public void setCubicle(Cubicle cubicle) {
        this.cubicle = cubicle;
    }

    /**
     * Returns a string representation of the monitor, including the ID, asset tag, serial number, and make and model.
     * 
     * @return A string representation of the monitor.
     */
    @Override
    public String toString() {
        return "Monitor{" + "monitorID=" + monitorID + ", assetTag=" + assetTag + ", serialNum=" + serialNum + ", makeModel=" + makeModel + '}';
    }    
}
