package com.disney.ad.adexchange.publisher.domain;


import java.util.List;

public class DigitalPropertyList {

    private long count;

    private List<DigitalProperty> DigitalPropertys;

	public DigitalPropertyList(long count, List<DigitalProperty> DigitalPropertys) {
		this.count = count;
		this.DigitalPropertys = DigitalPropertys;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<DigitalProperty> getDigitalPropertys() {
        return DigitalPropertys;
    }

    public void setDigitalPropertys(List<DigitalProperty> DigitalPropertys) {
        this.DigitalPropertys = DigitalPropertys;
    }
}

