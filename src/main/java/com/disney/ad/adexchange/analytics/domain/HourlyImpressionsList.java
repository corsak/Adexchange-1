package com.disney.ad.adexchange.analytics.domain;


import java.util.List;

public class HourlyImpressionsList {

    private long count;

    private List<HourlyImpressions> HourlyImpressionss;

	public HourlyImpressionsList(long count, List<HourlyImpressions> HourlyImpressionss) {
		this.count = count;
		this.HourlyImpressionss = HourlyImpressionss;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<HourlyImpressions> getHourlyImpressionss() {
        return HourlyImpressionss;
    }

    public void setHourlyImpressionss(List<HourlyImpressions> HourlyImpressionss) {
        this.HourlyImpressionss = HourlyImpressionss;
    }
}

