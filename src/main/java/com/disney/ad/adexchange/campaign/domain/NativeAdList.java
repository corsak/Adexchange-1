package com.disney.ad.adexchange.campaign.domain;


import java.util.List;

public class NativeAdList {

    private long count;

    private List<NativeAd> NativeAds;

	public NativeAdList(long count, List<NativeAd> NativeAds) {
		this.count = count;
		this.NativeAds = NativeAds;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<NativeAd> getNativeAds() {
        return NativeAds;
    }

    public void setNativeAds(List<NativeAd> NativeAds) {
        this.NativeAds = NativeAds;
    }
}

