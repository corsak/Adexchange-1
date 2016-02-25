package com.disney.ad.adexchange.campaign.domain;

import com.google.common.base.Objects;

public class NativeAdSearchRequest {
    private String title;
    private String description;
    private String highlightedText;
    private String icon;
    private String button;
    private String actionUrl;
    private Float price;
    private Integer rating;
    private String sponsoredText;
    private String sponsoredImageUrl;


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
