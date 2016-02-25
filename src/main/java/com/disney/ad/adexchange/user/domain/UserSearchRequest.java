package com.disney.ad.adexchange.user.domain;

import com.google.common.base.Objects;
import java.sql.Timestamp;

public class UserSearchRequest {
    private String email;
    private String loginName;
    private String password;
    private Integer status;
    private Integer userType;
    private String hintQuestion1;
    private String hintAnswer1;
    private String hintQuestion2;
    private String hintAnswer2;
    private Boolean isAdmin;
    private Timestamp createdTimeStart;
    private Timestamp createdTimeEnd;    
    private Timestamp updatedTimeStart;
    private Timestamp updatedTimeEnd;    
    private String createdByUser;
    private String updatedByUser;
	private String advertiserId;
	private String publisherId;
	private String roleId;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getHintQuestion1() {
        return hintQuestion1;
    }

    public void setHintQuestion1(String hintQuestion1) {
        this.hintQuestion1 = hintQuestion1;
    }

    public String getHintAnswer1() {
        return hintAnswer1;
    }

    public void setHintAnswer1(String hintAnswer1) {
        this.hintAnswer1 = hintAnswer1;
    }

    public String getHintQuestion2() {
        return hintQuestion2;
    }

    public void setHintQuestion2(String hintQuestion2) {
        this.hintQuestion2 = hintQuestion2;
    }

    public String getHintAnswer2() {
        return hintAnswer2;
    }

    public void setHintAnswer2(String hintAnswer2) {
        this.hintAnswer2 = hintAnswer2;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
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

    public String getAdvertiserid() {
        return advertiserId;
    }

    public void setAdvertiserid(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getPublisherid() {
        return publisherId;
    }

    public void setPublisherid(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getRoleid() {
        return roleId;
    }

    public void setRoleid(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("email", email)
            .add("loginName", loginName)
            .add("password", password)
            .add("status", status)
            .add("userType", userType)
            .add("hintQuestion1", hintQuestion1)
            .add("hintAnswer1", hintAnswer1)
            .add("hintQuestion2", hintQuestion2)
            .add("hintAnswer2", hintAnswer2)
            .add("isAdmin", isAdmin)
            .add("createdTimeStart", createdTimeStart)
            .add("createdTimeEnd", createdTimeEnd)            
            .add("updatedTimeStart", updatedTimeStart)
            .add("updatedTimeEnd", updatedTimeEnd)            
            .add("createdByUser", createdByUser)
            .add("updatedByUser", updatedByUser)
                .toString();
    }
        
}
