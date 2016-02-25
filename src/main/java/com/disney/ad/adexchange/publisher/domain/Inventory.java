package com.disney.ad.adexchange.publisher.domain;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "ZoneId", nullable = false, length = 50)
    private String zoneID;

    @Column(name = "DigitalPropertyId", nullable = false, length = 50)
    private String digitalPropertyId;

    @Column(name = "InventoryName", nullable = false, length = 50)
    private String inventoryName;

    @Column(name = "Description", nullable = false, length = 250)
    private String description;

    @Column(name = "ZoneType", nullable = false, length = 50)
    private String zoneType;

    @Column(name = "AdType", nullable = false, length = 50)
    private String adType;

    @Column(name = "AdTemplatesID", nullable = false, length = 50)
    private String adTemplateID;

    @Column(name = "AdWidth", nullable = false)
    private Integer adWidth;

    @Column(name = "AdHeight", nullable = false)
    private Integer adHeight;

    @Column(name = "AdInvocationTag", nullable = false, length = 50)
    private String adInvocationTag;

    @Column(name = "PassbackAdTag", nullable = false, length = 50)
    private String passbackAdTag;

    @Column(name = "FloorPrice", nullable = false)
    private Integer floorPrice;

    @Column(name = "TotalRequests", nullable = false)
    private Integer totalRequests;

    @Column(name = "TotalImpressions", nullable = false)
    private Integer totalImpressions;

    @Column(name = "TotalRevenues", nullable = false)
    private Float totalRevenues;

    @Column(name = "keywords", nullable = false, length = 50)
    private String keywords;

    @Column(name = "Status", nullable = false)
    private Integer status;

    @Column(name = "CreatedTime", nullable = false)
    private Timestamp createdTime;

    @Column(name = "UpdatedTime", nullable = false)
    private Timestamp updatedTime;

    @Column(name = "CreatedByUser", nullable = false, length = 50)
    private String createdByUser;

    @Column(name = "UpdatedByUser", length = 50)
    private String updatedByUser;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
    	this.id = id; 
    }	

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

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
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
                .add("id", id)
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
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
    }

        
}
