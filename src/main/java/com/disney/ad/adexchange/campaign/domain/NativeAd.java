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
@Table(name = "nativeAd")
public class NativeAd {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Title", nullable = false, length = 50)
    private String title;

    @Column(name = "Description", nullable = false, length = 250)
    private String description;

    @Column(name = "HighlightedText", nullable = false, length = 250)
    private String highlightedText;

    @Column(name = "AssetUrl", nullable = false, length = 100)
    private String icon;

    @Column(name = "button", nullable = false, length = 100)
    private String button;

    @Column(name = "ActionUrl", nullable = false, length = 100)
    private String actionUrl;

    @Column(name = "Price", nullable = false)
    private Float price;

    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @Column(name = "SponsoredText", nullable = false, length = 250)
    private String sponsoredText;

    @Column(name = "SponsoredImageUrl", nullable = false, length = 100)
    private String sponsoredImageUrl;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHighlightedText() {
        return highlightedText;
    }

    public void setHighlightedText(String highlightedText) {
        this.highlightedText = highlightedText;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getSponsoredText() {
        return sponsoredText;
    }

    public void setSponsoredText(String sponsoredText) {
        this.sponsoredText = sponsoredText;
    }

    public String getSponsoredImageUrl() {
        return sponsoredImageUrl;
    }

    public void setSponsoredImageUrl(String sponsoredImageUrl) {
        this.sponsoredImageUrl = sponsoredImageUrl;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("description", description)
                .add("highlightedText", highlightedText)
                .add("icon", icon)
                .add("button", button)
                .add("actionUrl", actionUrl)
                .add("price", price)
                .add("rating", rating)
                .add("sponsoredText", sponsoredText)
                .add("sponsoredImageUrl", sponsoredImageUrl)
                .toString();
    }

        
}
