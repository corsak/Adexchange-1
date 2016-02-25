package com.disney.ad.adexchange.publisher.domain;


import java.util.List;

public class InventoryList {

    private long count;

    private List<Inventory> Inventorys;

	public InventoryList(long count, List<Inventory> Inventorys) {
		this.count = count;
		this.Inventorys = Inventorys;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Inventory> getInventorys() {
        return Inventorys;
    }

    public void setInventorys(List<Inventory> Inventorys) {
        this.Inventorys = Inventorys;
    }
}

