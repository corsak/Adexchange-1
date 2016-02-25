package com.disney.ad.adexchange.campaign.domain;


import java.util.List;

public class BannerAdTargetList {

    private long count;

    private List<BannerAdTarget> BannerAdTargets;

	public BannerAdTargetList(long count, List<BannerAdTarget> BannerAdTargets) {
		this.count = count;
		this.BannerAdTargets = BannerAdTargets;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<BannerAdTarget> getBannerAdTargets() {
        return BannerAdTargets;
    }

    public void setBannerAdTargets(List<BannerAdTarget> BannerAdTargets) {
        this.BannerAdTargets = BannerAdTargets;
    }
}

