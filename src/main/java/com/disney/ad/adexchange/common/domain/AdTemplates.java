package com.disney.ad.adexchange.common.domain;

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
@Table(name = "AdTemplates")
public class AdTemplates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "AdTemplateID", nullable = false, length = 50)
    private String adTemplateID;

    @Column(name = "TemplateName", nullable = false, length = 50)
    private String templateName;

    @Column(name = "IsMobileFlag", nullable = false)
    private Integer isMobileFlag;

    @Column(name = "Width", nullable = false)
    private Integer width;

    @Column(name = "Height", nullable = false)
    private Integer height;

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

    public String getAdTemplateID() {
        return adTemplateID;
    }

    public void setAdTemplateID(String adTemplateID) {
        this.adTemplateID = adTemplateID;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Integer getIsMobileFlag() {
        return isMobileFlag;
    }

    public void setIsMobileFlag(Integer isMobileFlag) {
        this.isMobileFlag = isMobileFlag;
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
                .add("adTemplateID", adTemplateID)
                .add("templateName", templateName)
                .add("isMobileFlag", isMobileFlag)
                .add("width", width)
                .add("height", height)
                .add("status", status)
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
    }

        
}
