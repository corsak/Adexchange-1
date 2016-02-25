package com.disney.ad.adexchange.campaign.domain;


import java.util.List;

public class LineItemList {

    private long count;

    private List<LineItem> LineItems;

	public LineItemList(long count, List<LineItem> LineItems) {
		this.count = count;
		this.LineItems = LineItems;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<LineItem> getLineItems() {
        return LineItems;
    }

    public void setLineItems(List<LineItem> LineItems) {
        this.LineItems = LineItems;
    }
}

