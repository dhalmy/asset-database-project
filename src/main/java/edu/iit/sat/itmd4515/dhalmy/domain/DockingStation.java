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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Represents a docking station for electronic devices. Each docking station is
 * uniquely identified by its ID and may have an associated asset tag, serial
 * number, and unlock key. Docking stations can be used to connect electronic
 * devices to other peripherals.
 *
 * @author David
 */
@Entity
@Table(name = "Docks")
@XmlRootElement
@NamedQuery(name = "DockingStation.findAll", query = "select d from DockingStation d")
//@NamedQuery(name = "DockingStation.findAvailable", query = "SELECT d FROM DockingStation d WHERE d.cubicle IS NULL OR d = :currentDock")
public class DockingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Dock_ID")
    private Long dockID;

    @Column(unique = true)
    @Pattern(regexp = "^[Fx][a-zA-Z0-9]*", message = "Asset tag must start with 'F' or 'x' and contain only letters and numbers")
    private String assetTag;

    @NotBlank(message = "Serial number is required")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]*", message = "Serial number must contain only letters and numbers")
    private String serialNum;

    @Column(unique = true)
    @Pattern(regexp = "^X[0-9]*|^$", message = "Key must start with 'X' and contain only numbers or be blank")
    private String unlock_key;

    /**
     * Constructs a new DockingStation object with default values.
     */
    public DockingStation() {
    }

    /**
     * Constructs a new DockingStation object with the specified asset tag,
     * serial number, and unlock key.
     *
     * @param assetTag The asset tag for the docking station.
     * @param serialNum The serial number for the docking station.
     * @param unlock_key The unlock key for the docking station.
     */
    public DockingStation(String assetTag, String serialNum, String unlock_key) {
        this.assetTag = assetTag;
        this.serialNum = serialNum;
        this.unlock_key = unlock_key;
    }
    
    /**
     * Constructs a new DockingStation object with the specified asset tag and
     * serial number.
     *
     * @param assetTag The asset tag for the docking station.
     * @param serialNum The serial number for the docking station.
     */
    public DockingStation(String assetTag, String serialNum) {
        this.assetTag = assetTag;
        this.serialNum = serialNum;
    }

    /**
     * Constructs a new DockingStation object with the specified serial number.
     *
     * @param serialNum The serial number for the docking station.
     */
    public DockingStation(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * Gets the unique identifier (ID) of the docking station.
     *
     * @return The ID of the docking station.
     */
    public Long getDockID() {
        return dockID;
    }

    /**
     * Sets the unique identifier (ID) of the docking station.
     *
     * @param dockID The ID to set for the docking station.
     */
    public void setDockID(Long dockID) {
        this.dockID = dockID;
    }

    /**
     * Computes the hash code of the docking station based on its ID.
     *
     * @return The hash code of the docking station.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.dockID);
        return hash;
    }

    /**
     * Compares this docking station to another object for equality. Two docking
     * stations are considered equal if their IDs are equal.
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
        final DockingStation other = (DockingStation) obj;

        // If we are relying on GeneratedValue for ID, we need to check
        // whether either ID is null in order to rely on the field
        // iff null, can't be equal
        if (this.dockID == null || other.dockID == null) {
            return false;
        }
        return Objects.equals(this.dockID, other.dockID);
    }

    /**
     * Gets the asset tag of the docking station.
     *
     * @return The asset tag.
     */
    public String getAssetTag() {
        return assetTag;
    }

    /**
     * Sets the asset tag for the docking station.
     *
     * @param assetTag The asset tag to set.
     */
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    /**
     * Gets the serial number of the docking station.
     *
     * @return The serial number.
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * Sets the serial number for the docking station.
     *
     * @param serialNum The serial number to set.
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * Gets the unlock key of the docking station.
     *
     * @return The unlock key.
     */
    public String getKey() {
        return unlock_key;
    }

    /**
     * Sets the unlock key for the docking station.
     *
     * @param unlock_key The unlock key to set.
     */
    public void setKey(String unlock_key) {
        this.unlock_key = unlock_key;
    }

    /**
     * Gets the unlock key of the docking station.
     *
     * @return The unlock key.
     */
    public String getUnlock_key() {
        return unlock_key;
    }

    /**
     * Sets the unlock key for the docking station.
     *
     * @param unlock_key The unlock key to set.
     */
    public void setUnlock_key(String unlock_key) {
        this.unlock_key = unlock_key;
    }

    /**
     * Returns a string representation of the docking station, including its ID,
     * asset tag, serial number, and unlock key.
     *
     * @return A string representation of the docking station.
     */
    @Override
    public String toString() {
        return "DockingStation{" + "dockID=" + dockID + ", assetTag=" + assetTag + ", serialNum=" + serialNum + ", unlock_key=" + unlock_key + '}';
    }
}
