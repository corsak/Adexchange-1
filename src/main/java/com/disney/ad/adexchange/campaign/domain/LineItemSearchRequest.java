package com.disney.ad.adexchange.campaign.domain;

import com.google.common.base.Objects;
import java.sql.Timestamp;

public class LineItemSearchRequest {
    private String campaignId;
    private String advertiserId;
    private String bannerName;
    private String description;
    private String adType;
    private Integer weightage;
    private String bannerDimension;
    private String assetUrl;
    private String assetText;
    private String clickUrl;
    private String callbackUrl;
    private String deliveryChannel;
    private String adTag;
    private Integer impressionsCounter;
    private Integer bidsCounter;
    private Float currentSpend;
    private String nativeId;
    private String videoId;
    private String bannerStatus;
    private Integer status;
    private Timestamp createdTimeStart;
    private Timestamp createdTimeEnd;    
    private Timestamp updatedTimeStart;
    private Timestamp updatedTimeEnd;    
    private String createdByUser;
    private String updatedByUser;


    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public Integer getWeightage() {
        return weightage;
    }

    public void setWeightage(Integer weightage) {
        this.weightage = weightage;
    }

    public String getBannerDimension() {
        return bannerDimension;
    }

    public void setBannerDimension(String bannerDimension) {
        this.bannerDimension = bannerDimension;
    }

    public String getAssetUrl() {
        return assetUrl;
    }

    public void setAssetUrl(String assetUrl) {
        this.assetUrl = assetUrl;
    }

    public String getAssetText() {
        return assetText;
    }

    public void setAssetText(String assetText) {
        this.assetText = assetText;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getDeliveryChannel() {
        return deliveryChannel;
    }

    public void setDeliveryChannel(String deliveryChannel) {
        this.deliveryChannel = deliveryChannel;
    }

    public String getAdTag() {
        return adTag;
    }

    public void setAdTag(String adTag) {
        this.adTag = adTag;
    }

    public Integer getImpressionsCounter() {
        return impressionsCounter;
    }

    public void setImpressionsCounter(Integer impressionsCounter) {
        this.impressionsCounter = impressionsCounter;
    }

    public Integer getBidsCounter() {
        return bidsCounter;
    }

    public void setBidsCounter(Integer bidsCounter) {
        this.bidsCounter = bidsCounter;
    }

    public Float getCurrentSpend() {
        return currentSpend;
    }

    public void setCurrentSpend(Float currentSpend) {
        this.currentSpend = currentSpend;
    }

    public String getNativeId() {
        return nativeId;
    }

    public void setNativeId(String nativeId) {
        this.nativeId = nativeId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getBannerStatus() {
        return bannerStatus;
    }

    public void setBannerStatus(String bannerStatus) {
        this.bannerStatus = bannerStatus;
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
            .add("campaignId", campaignId)
            .add("advertiserId", advertiserId)
            .add("bannerName", bannerName)
            .add("description", description)
            .add("adType", adType)
            .add("weightage", weightage)
            .add("bannerDimension", bannerDimension)
            .add("assetUrl", assetUrl)
            .add("assetText", assetText)
            .add("clickUrl", clickUrl)
            .add("callbackUrl", callbackUrl)
            .add("deliveryChannel", deliveryChannel)
            .add("adTag", adTag)
            .add("impressionsCounter", impressionsCounter)
            .add("bidsCounter", bidsCounter)
            .add("currentSpend", currentSpend)
            .add("nativeId", nativeId)
            .add("videoId", videoId)
            .add("bannerStatus", bannerStatus)
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
