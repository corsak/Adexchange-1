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
@Table(name = "bannerAdZones")
public class BannerAdzones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "BannerID", nullable = false, length = 50)
    private String bannerID;

    @Column(name = "ZoneID", nullable = false, length = 50)
    private String zoneID;

    @Column(name = "CampaignID", nullable = false, length = 50)
    private String campaignID;

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

    public String getZoneID() {
        return zoneID;
    }

    public void setZoneID(String zoneID) {
        this.zoneID = zoneID;
    }

    public String getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(String campaignID) {
        this.campaignID = campaignID;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("bannerID", bannerID)
                .add("zoneID", zoneID)
                .add("campaignID", campaignID)
                .toString();
    }

        
}
