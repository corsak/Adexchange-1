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
@Table(name = "banner")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "CampaignId", nullable = false, length = 50)
    private String campaignId;

    @Column(name = "AdvertiserId", nullable = false, length = 50)
    private String advertiserId;

    @Column(name = "BannerName", nullable = false, length = 50)
    private String bannerName;

    @Column(name = "Description", nullable = false, length = 250)
    private String description;

    @Column(name = "AdType", nullable = false, length = 50)
    private String adType;

    @Column(name = "Weightage", nullable = false)
    private Integer weightage;

    @Column(name = "BannerDimension", nullable = false, length = 50)
    private String bannerDimension;

    @Column(name = "AssetUrl", nullable = false, length = 100)
    private String assetUrl;

    @Column(name = "AssetText", nullable = false, length = 100)
    private String assetText;

    @Column(name = "ClickUrl", nullable = false, length = 100)
    private String clickUrl;

    @Column(name = "CallbackUrl", nullable = false, length = 100)
    private String callbackUrl;

    @Column(name = "DeliveryChannel", nullable = false, length = 50)
    private String deliveryChannel;

    @Column(name = "AdTag", nullable = false, length = 50)
    private String adTag;

    @Column(name = "ImpressionsCounter", nullable = false)
    private Integer impressionsCounter;

    @Column(name = "BidsCounter", nullable = false)
    private Integer bidsCounter;

    @Column(name = "CurrentSpend", nullable = false)
    private Float currentSpend;

    @Column(name = "nativeId", length = 50)
    private String nativeId;

    @Column(name = "videoId", length = 50)
    private String videoId;

    @Column(name = "BannerStatus", nullable = false, length = 50)
    private String bannerStatus;

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
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
    }

        
}
