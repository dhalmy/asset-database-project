/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 *
 * @author David
 */
public class City {
    
    
    @NotNull
    @Pattern(regexp = "\\d+", message = "ID must contain only numbers")
    private String ID;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters are allowed for country name")
    @Size(min = 0, max = 35)
    private String name;
    @Size(min=0, max=3)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters are allowed for country code")
    private String countryCode;
    @Size(min = 0, max = 20)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters are allowed for district")
    private String district;
    @Pattern(regexp = "\\d+", message = "Population must contain only numbers")
    private String population;
    //Changed cityID and Population from Integer to String in order to apply pattern constraint to prevent letters from being entered where numbers belong.
    public City() {
    }

    public City(String ID, String name, String countryCode, String district, String population) {
        this.ID = ID;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }
    
    

    /**
     * Get the value of ID
     *
     * @return the value of ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Set the value of ID
     *
     * @param ID new value of ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getPopulation() {
        return population;
    }
    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" + "ID=" + ID + ", name=" + name + ", countryCode=" + countryCode + ", district=" + district + ", population=" + population + '}';
    }

    
    
    
}
