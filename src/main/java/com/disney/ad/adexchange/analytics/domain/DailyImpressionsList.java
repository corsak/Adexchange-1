package com.disney.ad.adexchange.analytics.domain;


import java.util.List;

public class DailyImpressionsList {

    private long count;

    private List<DailyImpressions> DailyImpressionss;

	public DailyImpressionsList(long count, List<DailyImpressions> DailyImpressionss) {
		this.count = count;
		this.DailyImpressionss = DailyImpressionss;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<DailyImpressions> getDailyImpressionss() {
        return DailyImpressionss;
    }

    public void setDailyImpressionss(List<DailyImpressions> DailyImpressionss) {
        this.DailyImpressionss = DailyImpressionss;
    }
}

