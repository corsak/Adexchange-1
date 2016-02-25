package com.disney.ad.adexchange.user.domain;


import java.util.List;

public class UserList {

    private long count;

    private List<User> Users;

	public UserList(long count, List<User> Users) {
		this.count = count;
		this.Users = Users;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<User> getUsers() {
    	return Users;
    }

    public void setUsers(List<User> Users) {
        this.Users = Users;
    }
}

