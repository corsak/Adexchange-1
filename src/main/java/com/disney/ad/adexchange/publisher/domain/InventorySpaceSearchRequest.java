package com.disney.ad.adexchange.publisher.domain;

import com.google.common.base.Objects;

public class InventorySpaceSearchRequest {
    private String inventoryId;
    private Float estimatedValue;
    private String units;


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
            .add("inventoryId", inventoryId)
            .add("estimatedValue", estimatedValue)
            .add("units", units)
                .toString();
    }
        
}
