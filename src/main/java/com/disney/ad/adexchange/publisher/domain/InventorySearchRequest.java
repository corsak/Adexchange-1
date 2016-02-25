package com.disney.ad.adexchange.publisher.domain;

import com.google.common.base.Objects;
import java.sql.Timestamp;

public class InventorySearchRequest {
    private String zoneID;
    private String digitalPropertyId;
    private String inventoryName;
    private String description;
    private String zoneType;
    private String adType;
    private String adTemplateID;
    private Integer adWidth;
    private Integer adHeight;
    private String adInvocationTag;
    private String passbackAdTag;
    private Integer floorPrice;
    private Integer totalRequests;
    private Integer totalImpressions;
    private Float totalRevenues;
    private String keywords;
    private Integer status;
    private Timestamp createdTimeStart;
    private Timestamp createdTimeEnd;    
    private Timestamp updatedTimeStart;
    private Timestamp updatedTimeEnd;    
    private String createdByUser;
    private String updatedByUser;


    public String getZoneID() {
        return zoneID;
    }

    public void setZoneID(String zoneID) {
        this.zoneID = zoneID;
    }

    public String getDigitalPropertyId() {
        return digitalPropertyId;
    }

    public void setDigitalPropertyId(String digitalPropertyId) {
        this.digitalPropertyId = digitalPropertyId;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getAdTemplateID() {
        return adTemplateID;
    }

    public void setAdTemplateID(String adTemplateID) {
        this.adTemplateID = adTemplateID;
    }

    public Integer getAdWidth() {
        return adWidth;
    }

    public void setAdWidth(Integer adWidth) {
        this.adWidth = adWidth;
    }

    public Integer getAdHeight() {
        return adHeight;
    }

    public void setAdHeight(Integer adHeight) {
        this.adHeight = adHeight;
    }

    public String getAdInvocationTag() {
        return adInvocationTag;
    }

    public void setAdInvocationTag(String adInvocationTag) {
        this.adInvocationTag = adInvocationTag;
    }

    public String getPassbackAdTag() {
        return passbackAdTag;
    }

    public void setPassbackAdTag(String passbackAdTag) {
        this.passbackAdTag = passbackAdTag;
    }

    public Integer getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(Integer floorPrice) {
        this.floorPrice = floorPrice;
    }

    public Integer getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(Integer totalRequests) {
        this.totalRequests = totalRequests;
    }

    public Integer getTotalImpressions() {
        return totalImpressions;
    }

    public void setTotalImpressions(Integer totalImpressions) {
        this.totalImpressions = totalImpressions;
    }

    public Float getTotalRevenues() {
        return totalRevenues;
    }

    public void setTotalRevenues(Float totalRevenues) {
        this.totalRevenues = totalRevenues;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatedTimeStart() {
        return createdTimeStart;
    }

    public void setCreatedTimeStart(Timestamp createdTimeStart) {
        this.createdTimeStart = createdTimeStart;
    }

    public Timestamp getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    public void setCreatedTimeEnd(Timestamp createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }

    public Timestamp getUpdatedTimeStart() {
        return updatedTimeStart;
    }

    public void setUpdatedTimeStart(Timestamp updatedTimeStart) {
        this.updatedTimeStart = updatedTimeStart;
    }

    public Timestamp getUpdatedTimeEnd() {
        return updatedTimeEnd;
    }

    public void setUpdatedTimeEnd(Timestamp updatedTimeEnd) {
        this.updatedTimeEnd = updatedTimeEnd;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(String updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("zoneID", zoneID)
            .add("digitalPropertyId", digitalPropertyId)
            .add("inventoryName", inventoryName)
            .add("description", description)
            .add("zoneType", zoneType)
            .add("adType", adType)
            .add("adTemplateID", adTemplateID)
            .add("adWidth", adWidth)
            .add("adHeight", adHeight)
            .add("adInvocationTag", adInvocationTag)
            .add("passbackAdTag", passbackAdTag)
            .add("floorPrice", floorPrice)
            .add("totalRequests", totalRequests)
            .add("totalImpressions", totalImpressions)
            .add("totalRevenues", totalRevenues)
            .add("keywords", keywords)
            .add("status", status)
            .add("createdTimeStart", createdTimeStart)
            .add("createdTimeEnd", createdTimeEnd)            
            .add("updatedTimeStart", updatedTimeStart)
            .add("updatedTimeEnd", updatedTimeEnd)            
            .add("createdByUser", createdByUser)
            .add("updatedByUser", updatedByUser)
                .toString();
    }
        
}
