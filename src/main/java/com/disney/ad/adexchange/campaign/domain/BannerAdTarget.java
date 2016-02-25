package com.disney.ad.adexchange.campaign.domain;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Table;

@Entity
@Table(name = "BannerAdTarget")
public class BannerAdTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "BannerID", nullable = false, length = 50)
    private String bannerID;

    @Column(name = "CampaignID", nullable = false, length = 50)
    private String campaignID;

    @Column(name = "Geo", nullable = false, length = 50)
    private String geo;

    @Column(name = "State", nullable = false, length = 50)
    private String state;

    @Column(name = "City", nullable = false, length = 50)
    private String city;

    @Column(name = "AgeGroup", nullable = false, length = 50)
    private String ageGroup;

    @Column(name = "Gender", nullable = false, length = 50)
    private String gender;

    @Column(name = "Category", nullable = false, length = 50)
    private String category;

    @Column(name = "Keyword", nullable = false, length = 50)
    private String keyword;

    @Column(name = "Status", nullable = false)
    private Integer status;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
    	this.id = id; 
    }	

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
                .add("id", id)
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
