package com.disney.ad.adexchange.campaign.domain;

import com.google.common.base.Objects;

public class VideoSearchRequest {
    private String title;
    private String videoUrl;
    private String videoType;
    private String delivery;
    private String bitrate;
    private Integer width;
    private Integer height;
    private Float duration;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("title", title)
            .add("videoUrl", videoUrl)
            .add("videoType", videoType)
            .add("delivery", delivery)
            .add("bitrate", bitrate)
            .add("width", width)
            .add("height", height)
            .add("duration", duration)
                .toString();
    }
        
}
