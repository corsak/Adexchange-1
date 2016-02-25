package com.disney.ad.adexchange.campaign.domain;


import java.util.List;

public class VideoList {

    private long count;

    private List<Video> Videos;

	public VideoList(long count, List<Video> Videos) {
		this.count = count;
		this.Videos = Videos;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Video> getVideos() {
        return Videos;
    }

    public void setVideos(List<Video> Videos) {
        this.Videos = Videos;
    }
}

