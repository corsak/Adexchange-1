package com.disney.ad.adexchange.user.domain;


import java.util.List;

public class UserSessionList {

    private long count;

    private List<UserSession> UserSessions;

	public UserSessionList(long count, List<UserSession> UserSessions) {
		this.count = count;
		this.UserSessions = UserSessions;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<UserSession> getUserSessions() {
        return UserSessions;
    }

    public void setUserSessions(List<UserSession> UserSessions) {
        this.UserSessions = UserSessions;
    }
}

