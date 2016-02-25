package com.disney.ad.adexchange.user.domain;

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
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Role_Id", nullable = false, length = 50)
    private String roleId;

    @Column(name = "Role_Name", nullable = false)
    private Integer roleName;

    @Column(name = "Role_Group", nullable = false)
    private Integer roleGroup;

    @Column(name = "Created_Time", nullable = false)
    private Timestamp createdTime;

    @Column(name = "Updated_Time", nullable = false)
    private Timestamp updatedTime;

    @Column(name = "Created_By_User", nullable = false, length = 50)
    private String createdByUser;

    @Column(name = "Updated_By_User", length = 50)
    private String updatedByUser;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
    	this.id = id; 
    }	

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleName() {
        return roleName;
    }

    public void setRoleName(Integer roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(Integer roleGroup) {
        this.roleGroup = roleGroup;
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
                .add("roleId", roleId)
                .add("roleName", roleName)
                .add("roleGroup", roleGroup)
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
    }

        
}
