package com.disney.ad.adexchange.campaign.domain;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Table;

@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Title", nullable = false, length = 50)
    private String title;

    @Column(name = "VideoUrl", nullable = false, length = 50)
    private String videoUrl;

    @Column(name = "VideoType", nullable = false, length = 250)
    private String videoType;

    @Column(name = "Delivery", nullable = false, length = 250)
    private String delivery;

    @Column(name = "BitRate", nullable = false, length = 100)
    private String bitrate;

    @Column(name = "width", nullable = false, length = 100)
    private Integer width;

    @Column(name = "height", nullable = false, length = 100)
    private Integer height;

    @Column(name = "Duration", nullable = false)
    private Float duration;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
    	this.id = id; 
    }	

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
                .add("id", id)
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
