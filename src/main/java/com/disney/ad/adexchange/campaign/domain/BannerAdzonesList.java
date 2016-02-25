package com.disney.ad.adexchange.campaign.domain;


import java.util.List;

public class BannerAdzonesList {

    private long count;

    private List<BannerAdzones> BannerAdzoness;

	public BannerAdzonesList(long count, List<BannerAdzones> BannerAdzoness) {
		this.count = count;
		this.BannerAdzoness = BannerAdzoness;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<BannerAdzones> getBannerAdzoness() {
        return BannerAdzoness;
    }

    public void setBannerAdzoness(List<BannerAdzones> BannerAdzoness) {
        this.BannerAdzoness = BannerAdzoness;
    }
}

