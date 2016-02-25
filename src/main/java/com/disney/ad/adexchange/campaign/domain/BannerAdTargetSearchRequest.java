package com.disney.ad.adexchange.campaign.domain;

import com.google.common.base.Objects;

public class BannerAdTargetSearchRequest {
    private String bannerID;
    private String campaignID;
    private String geo;
    private String state;
    private String city;
    private String ageGroup;
    private String gender;
    private String category;
    private String keyword;
    private Integer status;


    public String getBannerID() {
        return bannerID;
    }

    public void setBannerID(String bannerID) {
        this.bannerID = bannerID;
    }

    public String getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(String campaignID) {
        this.campaignID = campaignID;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("bannerID", bannerID)
            .add("campaignID", campaignID)
            .add("geo", geo)
            .add("state", state)
            .add("city", city)
            .add("ageGroup", ageGroup)
            .add("gender", gender)
            .add("category", category)
            .add("keyword", keyword)
            .add("status", status)
                .toString();
    }
        
}
