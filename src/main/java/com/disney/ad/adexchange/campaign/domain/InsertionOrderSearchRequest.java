package com.disney.ad.adexchange.campaign.domain;

import com.google.common.base.Objects;
import java.sql.Timestamp;

public class InsertionOrderSearchRequest {
    private String insertionOrderId;
    private String campaignName;
    private String description;
    private String campaignObjective;
    private Timestamp campaignStartDateStart;
    private Timestamp campaignStartDateEnd;    
    private Timestamp campaignEndDateStart;
    private Timestamp campaignEndDateEnd;    
    private String orderType;
    private Float spend;
    private Float currency;
    private String revenueModel;
    private Integer maximumImpressions;
    private Float maximumSpend;
    private Integer currentImpressions;
    private Float currentSpend;
    private Float maximumSpendPerDay;
    private Boolean pixelTrackingEnabled;
    private String companionCampaign;
    private String campaignStatus;
    private String priority;
    private String comments;
    private String advertiserId;
    private Integer status;
    private Timestamp createdTimeStart;
    private Timestamp createdTimeEnd;    
    private Timestamp updatedTimeStart;
    private Timestamp updatedTimeEnd;    
    private String createdByUser;
    private String updatedByUser;


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

    public Timestamp getCampaignStartDateStart() {
        return campaignStartDateStart;
    }

    public void setCampaignStartDateStart(Timestamp campaignStartDateStart) {
        this.campaignStartDateStart = campaignStartDateStart;
    }

    public Timestamp getCampaignStartDateEnd() {
        return campaignStartDateEnd;
    }

    public void setCampaignStartDateEnd(Timestamp campaignStartDateEnd) {
        this.campaignStartDateEnd = campaignStartDateEnd;
    }

    public Timestamp getCampaignEndDateStart() {
        return campaignEndDateStart;
    }

    public void setCampaignEndDateStart(Timestamp campaignEndDateStart) {
        this.campaignEndDateStart = campaignEndDateStart;
    }

    public Timestamp getCampaignEndDateEnd() {
        return campaignEndDateEnd;
    }

    public void setCampaignEndDateEnd(Timestamp campaignEndDateEnd) {
        this.campaignEndDateEnd = campaignEndDateEnd;
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
            .add("insertionOrderId", insertionOrderId)
            .add("campaignName", campaignName)
            .add("description", description)
            .add("campaignObjective", campaignObjective)
            .add("campaignStartDateStart", campaignStartDateStart)
            .add("campaignStartDateEnd", campaignStartDateEnd)            
            .add("campaignEndDateStart", campaignEndDateStart)
            .add("campaignEndDateEnd", campaignEndDateEnd)            
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
            .add("createdTimeStart", createdTimeStart)
            .add("createdTimeEnd", createdTimeEnd)            
            .add("updatedTimeStart", updatedTimeStart)
            .add("updatedTimeEnd", updatedTimeEnd)            
            .add("createdByUser", createdByUser)
            .add("updatedByUser", updatedByUser)
                .toString();
    }
        
}
