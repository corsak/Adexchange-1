package com.disney.ad.adexchange.analytics.domain;

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
@Table(name = "dailyImpressions")
public class DailyImpressions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "PublisherId", nullable = false, length = 50)
    private Integer publisherId;

    @Column(name = "DigitalPropertyId", nullable = false, length = 50)
    private Integer digitalPropertyid;

    @Column(name = "lineItemId", nullable = false, length = 50)
    private Integer lineItemId;

    @Column(name = "ZoneId", nullable = false, length = 50)
    private Integer zoneId;

    @Column(name = "noOfImpressions", nullable = false)
    private Integer noOfImpressions;

    @Column(name = "noOfClicks", nullable = false)
    private Integer noOfClicks;

    @Column(name = "Date", nullable = false)
    private Integer date;

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

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getDigitalPropertyid() {
        return digitalPropertyid;
    }

    public void setDigitalPropertyid(Integer digitalPropertyid) {
        this.digitalPropertyid = digitalPropertyid;
    }

    public Integer getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(Integer lineItemId) {
        this.lineItemId = lineItemId;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getNoOfImpressions() {
        return noOfImpressions;
    }

    public void setNoOfImpressions(Integer noOfImpressions) {
        this.noOfImpressions = noOfImpressions;
    }

    public Integer getNoOfClicks() {
        return noOfClicks;
    }

    public void setNoOfClicks(Integer noOfClicks) {
        this.noOfClicks = noOfClicks;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
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
                .add("publisherId", publisherId)
                .add("digitalPropertyid", digitalPropertyid)
                .add("lineItemId", lineItemId)
                .add("zoneId", zoneId)
                .add("noOfImpressions", noOfImpressions)
                .add("noOfClicks", noOfClicks)
                .add("date", date)
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
    }

        
}
