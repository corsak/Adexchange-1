package com.disney.ad.adexchange.user.domain;

import com.google.common.base.Objects;
import java.sql.Timestamp;

public class UserSessionSearchRequest {
    private String userId;
    private String userSessionId;
    private String authToken;
    private Timestamp lastAccessTimeStart;
    private Timestamp lastAccessTimeEnd;    
    private Timestamp lastLoggedInTimeStart;
    private Timestamp lastLoggedInTimeEnd;    
    private Timestamp createdTimeStart;
    private Timestamp createdTimeEnd;    
    private Timestamp updatedTimeStart;
    private Timestamp updatedTimeEnd;    
    private String createdByUser;
    private String updatedByUser;


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

    public Timestamp getLastAccessTimeStart() {
        return lastAccessTimeStart;
    }

    public void setLastAccessTimeStart(Timestamp lastAccessTimeStart) {
        this.lastAccessTimeStart = lastAccessTimeStart;
    }

    public Timestamp getLastAccessTimeEnd() {
        return lastAccessTimeEnd;
    }

    public void setLastAccessTimeEnd(Timestamp lastAccessTimeEnd) {
        this.lastAccessTimeEnd = lastAccessTimeEnd;
    }

    public Timestamp getLastLoggedInTimeStart() {
        return lastLoggedInTimeStart;
    }

    public void setLastLoggedInTimeStart(Timestamp lastLoggedInTimeStart) {
        this.lastLoggedInTimeStart = lastLoggedInTimeStart;
    }

    public Timestamp getLastLoggedInTimeEnd() {
        return lastLoggedInTimeEnd;
    }

    public void setLastLoggedInTimeEnd(Timestamp lastLoggedInTimeEnd) {
        this.lastLoggedInTimeEnd = lastLoggedInTimeEnd;
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
            .add("userId", userId)
            .add("userSessionId", userSessionId)
            .add("authToken", authToken)
            .add("lastAccessTimeStart", lastAccessTimeStart)
            .add("lastAccessTimeEnd", lastAccessTimeEnd)            
            .add("lastLoggedInTimeStart", lastLoggedInTimeStart)
            .add("lastLoggedInTimeEnd", lastLoggedInTimeEnd)            
            .add("createdTimeStart", createdTimeStart)
            .add("createdTimeEnd", createdTimeEnd)            
            .add("updatedTimeStart", updatedTimeStart)
            .add("updatedTimeEnd", updatedTimeEnd)            
            .add("createdByUser", createdByUser)
            .add("updatedByUser", updatedByUser)
                .toString();
    }
        
}
