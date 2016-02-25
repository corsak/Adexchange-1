package com.disney.ad.adexchange.campaign.domain;

import com.google.common.base.Objects;

public class BannerAdzonesSearchRequest {
    private String bannerID;
    private String zoneID;
    private String campaignID;


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
            .add("bannerID", bannerID)
            .add("zoneID", zoneID)
            .add("campaignID", campaignID)
                .toString();
    }
        
}
