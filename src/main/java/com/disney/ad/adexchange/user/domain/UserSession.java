package com.disney.ad.adexchange.user.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.google.common.base.Objects;

@Entity
@Table(name = "usersession")
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "UserId", nullable = false, length = 50)
    private String userId;

    @Column(name = "UserSessionId", nullable = false, length = 50)
    private String userSessionId;

    @Column(name = "AuthToken", nullable = false, length = 250)
    private String authToken;

    @Column(name = "LastAccessTime", nullable = false)
    private Timestamp lastAccessTime;

    @Column(name = "lastLoggedInTime", nullable = false)
    private Timestamp lastLoggedInTime;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Timestamp getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Timestamp lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Timestamp getLastLoggedInTime() {
        return lastLoggedInTime;
    }

    public void setLastLoggedInTime(Timestamp lastLoggedInTime) {
        this.lastLoggedInTime = lastLoggedInTime;
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
                .add("userId", userId)
                .add("userSessionId", userSessionId)
                .add("authToken", authToken)
                .add("lastAccessTime", lastAccessTime)
                .add("lastLoggedInTime", lastLoggedInTime)
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
    }

        
}
