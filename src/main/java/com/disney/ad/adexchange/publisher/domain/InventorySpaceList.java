package com.disney.ad.adexchange.publisher.domain;


import java.util.List;

public class InventorySpaceList {

    private long count;

    private List<InventorySpace> InventorySpaces;

	public InventorySpaceList(long count, List<InventorySpace> InventorySpaces) {
		this.count = count;
		this.InventorySpaces = InventorySpaces;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<InventorySpace> getInventorySpaces() {
        return InventorySpaces;
    }

    public void setInventorySpaces(List<InventorySpace> InventorySpaces) {
        this.InventorySpaces = InventorySpaces;
    }
}

