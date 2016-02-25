package com.disney.ad.adexchange.campaign.domain;

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
@Table(name = "insertionOrder")
public class InsertionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "CampaignId", nullable = false, length = 50)
    private String insertionOrderId;

    @Column(name = "campaignName", nullable = false, length = 50)
    private String campaignName;

    @Column(name = "Description", nullable = false, length = 250)
    private String description;

    @Column(name = "CampaignObjective", length = 250)
    private String campaignObjective;

    @Column(name = "CampaignStartDate", nullable = false)
    private Timestamp campaignStartDate;

    @Column(name = "CampaignEndDate", nullable = false)
    private Timestamp campaignEndDate;

    @Column(name = "OrderType", nullable = false, length = 50)
    private String orderType;

    @Column(name = "Spend")
    private Float spend;

    @Column(name = "Currency")
    private Float currency;

    @Column(name = "RevenueModel", length = 50)
    private String revenueModel;

    @Column(name = "MaximumImpressions", nullable = false)
    private Integer maximumImpressions;

    @Column(name = "MaximumSpend", nullable = false)
    private Float maximumSpend;

    @Column(name = "CurrentImpressions", nullable = false)
    private Integer currentImpressions;

    @Column(name = "CurrentSpend", nullable = false)
    private Float currentSpend;

    @Column(name = "MaximumSpendPerDay")
    private Float maximumSpendPerDay;

    @Column(name = "PixelTrackingEnabled", nullable = false)
    private Boolean pixelTrackingEnabled;

    @Column(name = "CompanionCampaign", nullable = false, length = 50)
    private String companionCampaign;

    @Column(name = "CampaignStatus", nullable = false, length = 50)
    private String campaignStatus;

    @Column(name = "Priority", nullable = false, length = 50)
    private String priority;

    @Column(name = "Comments", nullable = false, length = 250)
    private String comments;

    @Column(name = "AdvertiserId", length = 50)
    private String advertiserId;

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

    public String getInsertionOrderId() {
        return insertionOrderId;
    }

    public void setInsertionOrderId(String insertionOrderId) {
        this.insertionOrderId = insertionOrderId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCampaignObjective() {
        return campaignObjective;
    }

    public void setCampaignObjective(String campaignObjective) {
        this.campaignObjective = campaignObjective;
    }

    public Timestamp getCampaignStartDate() {
        return campaignStartDate;
    }

    public void setCampaignStartDate(Timestamp campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    public Timestamp getCampaignEndDate() {
        return campaignEndDate;
    }

    public void setCampaignEndDate(Timestamp campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Float getSpend() {
        return spend;
    }

    public void setSpend(Float spend) {
        this.spend = spend;
    }

    public Float getCurrency() {
        return currency;
    }

    public void setCurrency(Float currency) {
        this.currency = currency;
    }

    public String getRevenueModel() {
        return revenueModel;
    }

    public void setRevenueModel(String revenueModel) {
        this.revenueModel = revenueModel;
    }

    public Integer getMaximumImpressions() {
        return maximumImpressions;
    }

    public void setMaximumImpressions(Integer maximumImpressions) {
        this.maximumImpressions = maximumImpressions;
    }

    public Float getMaximumSpend() {
        return maximumSpend;
    }

    public void setMaximumSpend(Float maximumSpend) {
        this.maximumSpend = maximumSpend;
    }

    public Integer getCurrentImpressions() {
        return currentImpressions;
    }

    public void setCurrentImpressions(Integer currentImpressions) {
        this.currentImpressions = currentImpressions;
    }

    public Float getCurrentSpend() {
        return currentSpend;
    }

    public void setCurrentSpend(Float currentSpend) {
        this.currentSpend = currentSpend;
    }

    public Float getMaximumSpendPerDay() {
        return maximumSpendPerDay;
    }

    public void setMaximumSpendPerDay(Float maximumSpendPerDay) {
        this.maximumSpendPerDay = maximumSpendPerDay;
    }

    public Boolean getPixelTrackingEnabled() {
        return pixelTrackingEnabled;
    }

    public void setPixelTrackingEnabled(Boolean pixelTrackingEnabled) {
        this.pixelTrackingEnabled = pixelTrackingEnabled;
    }

    public String getCompanionCampaign() {
        return companionCampaign;
    }

    public void setCompanionCampaign(String companionCampaign) {
        this.companionCampaign = companionCampaign;
    }

    public String getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(String campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
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
                .add("insertionOrderId", insertionOrderId)
                .add("campaignName", campaignName)
                .add("description", description)
                .add("campaignObjective", campaignObjective)
                .add("campaignStartDate", campaignStartDate)
                .add("campaignEndDate", campaignEndDate)
                .add("orderType", orderType)
                .add("spend", spend)
                .add("currency", currency)
                .add("revenueModel", revenueModel)
                .add("maximumImpressions", maximumImpressions)
                .add("maximumSpend", maximumSpend)
                .add("currentImpressions", currentImpressions)
                .add("currentSpend", currentSpend)
                .add("maximumSpendPerDay", maximumSpendPerDay)
                .add("pixelTrackingEnabled", pixelTrackingEnabled)
                .add("companionCampaign", companionCampaign)
                .add("campaignStatus", campaignStatus)
                .add("priority", priority)
                .add("comments", comments)
                .add("advertiserId", advertiserId)
                .add("status", status)
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
    }

        
}
