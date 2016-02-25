package com.disney.ad.adexchange.publisher.domain;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Table;

@Entity
@Table(name = "InventorySpace")
public class InventorySpace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "inventoryId", nullable = false, length = 50)
    private String inventoryId;

    @Column(name = "EstimatedValue", nullable = false)
    private Float estimatedValue;

    @Column(name = "Unit", nullable = false, length = 50)
    private String units;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
    	this.id = id; 
    }	

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Float getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(Float estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("inventoryId", inventoryId)
                .add("estimatedValue", estimatedValue)
                .add("units", units)
                .toString();
    }

        
}
