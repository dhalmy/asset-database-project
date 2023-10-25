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
import java.util.Objects;

/**
 *
 * @author David
 */
@Entity
@Table(name = "Docks")
@NamedQuery(name = "DockingStation.findAll", query = "select d from DockingStation d")
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
    @Pattern(regexp = "^X[0-9]*", message = "Key must start with 'X' and contain only numbers")
    private String unlock_key;

    
    @OneToOne(mappedBy = "dockingStation")
    private Cubicle cubicle;

    public DockingStation() {
    }

    public DockingStation(String assetTag, String serialNum, String unlock_key) {
        this.assetTag = assetTag;
        this.serialNum = serialNum;
        this.unlock_key = unlock_key;
    }
    //only must have requirements to add a dock
    public DockingStation(String serialNum) {
        this.serialNum = serialNum;
    }

    public Long getDockID() {
        return dockID;
    }

    public void setDockID(Long dockID) {
        this.dockID = dockID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.dockID);
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
        final DockingStation other = (DockingStation) obj;
        
        //if we are relying on GeneratedValue for ID, we need to check
        //whether either ID is null in order to rely on the field
        //iff null, can't be equal
        if (this.dockID == null || other.dockID == null) {
            return false;
        }
        return Objects.equals(this.dockID, other.dockID);
    }
    public String getAssetTag() {
        return assetTag;
    }
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }
    public String getSerialNum() {
        return serialNum;
    }
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
    public String getKey() {
        return unlock_key;
    }
    public void setKey(String unlock_key) {
        this.unlock_key = unlock_key;
    }

//    public Cubicle getCubicle() {
//        return cubicle;
//    }
//
//    public void setCubicle(Cubicle cubicle) {
//        this.cubicle = cubicle;
//    }

    @Override
    public String toString() {
        return "DockingStation{" + "dockID=" + dockID + ", assetTag=" + assetTag + ", serialNum=" + serialNum + ", unlock_key=" + unlock_key + '}';
    }

    public String getUnlock_key() {
        return unlock_key;
    }

    public void setUnlock_key(String unlock_key) {
        this.unlock_key = unlock_key;
    }

    public Cubicle getCubicle() {
        return cubicle;
    }

    public void setCubicle(Cubicle cubicle) {
        this.cubicle = cubicle;
    }

}
