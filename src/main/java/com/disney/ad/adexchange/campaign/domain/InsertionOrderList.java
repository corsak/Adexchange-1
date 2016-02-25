package com.disney.ad.adexchange.campaign.domain;


import java.util.List;

public class InsertionOrderList {

    private long count;

    private List<InsertionOrder> InsertionOrders;

	public InsertionOrderList(long count, List<InsertionOrder> InsertionOrders) {
		this.count = count;
		this.InsertionOrders = InsertionOrders;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<InsertionOrder> getInsertionOrders() {
        return InsertionOrders;
    }

    public void setInsertionOrders(List<InsertionOrder> InsertionOrders) {
        this.InsertionOrders = InsertionOrders;
    }
}

