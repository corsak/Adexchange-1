package com.disney.ad.adexchange.user.domain;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import com.disney.ad.adexchange.user.domain.Advertiser;
import com.disney.ad.adexchange.user.domain.Publisher;
import com.disney.ad.adexchange.user.domain.Role;

@Entity

@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "Login_Name", nullable = false, length = 50)
    private String loginName;

    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Column(name = "Status", nullable = false)
    private Integer status;

    @Column(name = "User_Type", nullable = false)
    private Integer userType;

    @Column(name = "Hint_Question1")
    private String hintQuestion1;

    @Column(name = "Hint_Answer1")
    private String hintAnswer1;

    @Column(name = "Hint_Question2")
    private String hintQuestion2;

    @Column(name = "Hint_Answer2")
    private String hintAnswer2;

    @Column(name = "Is_Admin", nullable = false)
    private Boolean isAdmin;

    @Column(name = "Created_Time", nullable = false)
    private Timestamp createdTime;

    @Column(name = "Updated_Time", nullable = false)
    private Timestamp updatedTime;

    @Column(name = "Created_By_User", nullable = false, length = 50)
    private String createdByUser;

    @Column(name = "Updated_By_User", length = 50)
    private String updatedByUser;

	@ManyToOne
    @JoinColumn(name = "Advertiser_Id")
	//@JsonIgnore
    private Advertiser advertiser;

	@ManyToOne
    @JoinColumn(name = "Publisher_Id")
    private Publisher publisher;

	@ManyToOne
    @JoinColumn(name = "Role_Id")
    private Role role;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
    	this.id = id; 
    }	

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

    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(Advertiser advertiser) {
        this.advertiser = advertiser;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        
    	return Objects.toStringHelper(this)
                .add("id", id)
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
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
       
    }

        
}
