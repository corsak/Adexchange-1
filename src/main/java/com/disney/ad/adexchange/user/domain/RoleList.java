package com.disney.ad.adexchange.user.domain;


import java.util.List;

public class RoleList {

    private long count;

    private List<Role> Roles;

	public RoleList(long count, List<Role> Roles) {
		this.count = count;
		this.Roles = Roles;
	}

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Role> getRoles() {
        return Roles;
    }

    public void setRoles(List<Role> Roles) {
        this.Roles = Roles;
    }
}

