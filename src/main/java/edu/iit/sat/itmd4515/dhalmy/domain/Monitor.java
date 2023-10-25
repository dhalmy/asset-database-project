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
@Table(name = "Monitors")
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
    @Pattern(regexp = "^[a-zA-Z0-9]*", message = "Make and model must contain only letters and numbers")
    @Column(nullable = false, name = "Make_and_Model")
    private String makeModel;
    
//    @ManyToOne
//    @JoinColumn(name = "cubicle_id")
//    private Cubicle cubicle;
//    
    


    

    public Monitor() {
    }

    public Monitor(String assetTag, String serialNum, String makeModel) {
        this.assetTag = assetTag;
        this.serialNum = serialNum;
        this.makeModel = makeModel;
    }

    //only must have requirements to add a monitor
    public Monitor(String serialNum, String makeModel) {
        this.serialNum = serialNum;
        this.makeModel = makeModel;
    }

    public Long getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(Long monitorID) {
        this.monitorID = monitorID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.monitorID);
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
        final Monitor other = (Monitor) obj;
        
        //if we are relying on GeneratedValue for ID, we need to check
        //whether either ID is null in order to rely on the field
        //iff null, can't be equal
        if (this.monitorID == null || other.monitorID == null) {
            return false;
        }
        return Objects.equals(this.monitorID, other.monitorID);
    }
    public String getSerialNum() {
        return serialNum;
    }
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
    public String getMakeModel() {
        return makeModel;
    }
    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }
    public String getAssetTag() {
        return assetTag;
    }
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
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
        return "Monitor{" + "monitorID=" + monitorID + ", assetTag=" + assetTag + ", serialNum=" + serialNum + ", makeModel=" + makeModel + '}';
    }
    
}
