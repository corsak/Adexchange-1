package com.disney.ad.adexchange.user.domain;


import java.util.List;

public class AdvertiserList {

    private long count;

    private List<Advertiser> Advertisers;

	public AdvertiserList(long count, List<Advertiser> Advertisers) {
		this.count = count;
		this.Advertisers = Advertisers;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Advertiser> getAdvertisers() {
        return Advertisers;
    }

    public void setAdvertisers(List<Advertiser> Advertisers) {
        this.Advertisers = Advertisers;
    }
}

