package com.disney.ad.adexchange.user.domain;


import java.util.List;

public class NotificationList {

    private long count;

    private List<Notification> Notifications;

	public NotificationList(long count, List<Notification> Notifications) {
		this.count = count;
		this.Notifications = Notifications;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Notification> getNotifications() {
        return Notifications;
    }

    public void setNotifications(List<Notification> Notifications) {
        this.Notifications = Notifications;
    }
}

