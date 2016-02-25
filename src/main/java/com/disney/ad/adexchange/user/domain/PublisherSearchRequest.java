package com.disney.ad.adexchange.user.domain;

import com.google.common.base.Objects;
import java.sql.Timestamp;

public class PublisherSearchRequest {
    private String publisherId;
    private String publisherName;
    private String company;
    private String contactName;
    private String contactNumber;
    private String country;
    private String primaryDomain;
    private String iabCategory;
    private String address;
    private Integer status;
    private Timestamp createdTimeStart;
    private Timestamp createdTimeEnd;    
    private Timestamp updatedTimeStart;
    private Timestamp updatedTimeEnd;    
    private String createdByUser;
    private String updatedByUser;


    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrimaryDomain() {
        return primaryDomain;
    }

    public void setPrimaryDomain(String primaryDomain) {
        this.primaryDomain = primaryDomain;
    }

    public String getIabCategory() {
        return iabCategory;
    }

    public void setIabCategory(String iabCategory) {
        this.iabCategory = iabCategory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatedTimeStart() {
        return createdTimeStart;
    }

    public void setCreatedTimeStart(Timestamp createdTimeStart) {
        this.createdTimeStart = createdTimeStart;
    }

    public Timestamp getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    public void setCreatedTimeEnd(Timestamp createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }

    public Timestamp getUpdatedTimeStart() {
        return updatedTimeStart;
    }

    public void setUpdatedTimeStart(Timestamp updatedTimeStart) {
        this.updatedTimeStart = updatedTimeStart;
    }

    public Timestamp getUpdatedTimeEnd() {
        return updatedTimeEnd;
    }

    public void setUpdatedTimeEnd(Timestamp updatedTimeEnd) {
        this.updatedTimeEnd = updatedTimeEnd;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(String updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("publisherId", publisherId)
            .add("publisherName", publisherName)
            .add("company", company)
            .add("contactName", contactName)
            .add("contactNumber", contactNumber)
            .add("country", country)
            .add("primaryDomain", primaryDomain)
            .add("iabCategory", iabCategory)
            .add("address", address)
            .add("status", status)
            .add("createdTimeStart", createdTimeStart)
            .add("createdTimeEnd", createdTimeEnd)            
            .add("updatedTimeStart", updatedTimeStart)
            .add("updatedTimeEnd", updatedTimeEnd)            
            .add("createdByUser", createdByUser)
            .add("updatedByUser", updatedByUser)
                .toString();
    }
        
}
