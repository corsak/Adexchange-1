package com.disney.ad.adexchange.user.domain;


import java.util.List;

public class PublisherList {

    private long count;

    private List<Publisher> Publishers;

	public PublisherList(long count, List<Publisher> Publishers) {
		this.count = count;
		this.Publishers = Publishers;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Publisher> getPublishers() {
        return Publishers;
    }

    public void setPublishers(List<Publisher> Publishers) {
        this.Publishers = Publishers;
    }
}

