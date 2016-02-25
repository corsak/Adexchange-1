package com.disney.ad.adexchange.publisher.domain;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "digitalProperty")
public class DigitalProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "PropertyName", nullable = false, length = 50)
    private String propertyName;

    @Column(name = "Description", length = 250)
    private String description;

    @Column(name = "Type", nullable = false, length = 50)
    private String type;

    @Column(name = "DomainURL", nullable = false, length = 50)
    private String domainURL;

    @Column(name = "PublisherId", nullable = false, length = 50)
    private String publisherId;

    @Column(name = "iabCategory", nullable = false, length = 50)
    private String IABCategory;

    @Column(name = "iabSubCategory", length = 50)
    private String IABSubCategory;

    @Column(name = "Country", length = 50)
    private String country;

    @Column(name = "Language", length = 50)
    private String language;

    @Column(name = "Status", nullable = false)
    private Integer status;

    @Column(name = "CreatedTime", nullable = false)
    private Timestamp createdTime;

    @Column(name = "UpdatedTime", nullable = false)
    private Timestamp updatedTime;

    @Column(name = "CreatedByUser", nullable = false, length = 50)
    private String createdByUser;

    @Column(name = "UpdatedByUser", length = 50)
    private String updatedByUser;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
    	this.id = id; 
    }	

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDomainURL() {
        return domainURL;
    }

    public void setDomainURL(String domainURL) {
        this.domainURL = domainURL;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getIABCategory() {
        return IABCategory;
    }

    public void setIABCategory(String IABCategory) {
        this.IABCategory = IABCategory;
    }

    public String getIABSubCategory() {
        return IABSubCategory;
    }

    public void setIABSubCategory(String IABSubCategory) {
        this.IABSubCategory = IABSubCategory;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
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
                .add("id", id)
                .add("propertyName", propertyName)
                .add("description", description)
                .add("type", type)
                .add("domainURL", domainURL)
                .add("publisherId", publisherId)
                .add("IABCategory", IABCategory)
                .add("IABSubCategory", IABSubCategory)
                .add("country", country)
                .add("language", language)
                .add("status", status)
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
    }

        
}
