package com.disney.ad.adexchange.user.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

@Entity
@Table(name = "advertiser")
public class Advertiser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;
    
    //changes made
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "Advertiser_Id", unique = true)
    private String advertiserId;
    //end
    
    @Column(name = "Advertiser_Name", nullable = false, length = 50)
    private String advertiserName;

    @Column(name = "Company", length = 50)
    private String company;

    @Column(name = "Contact_Name", length = 50)
    private String contactName;

    @Column(name = "Contact_Number", length = 50)
    private String contactNumber;

    @Column(name = "Country", length = 50)
    private String country;

    @Column(name = "Description", length = 250)
    private String description;

    @Column(name = "Partner_Type", nullable = false, length = 50)
    private String partnerType;

    @Column(name = "Status", nullable = false, length = 50)
    private String status;

    @Column(name = "Created_Time", nullable = false)
    private Timestamp createdTime;

    @Column(name = "Updated_Time", nullable = false)
    private Timestamp updatedTime;

    @Column(name = "Created_By_User", nullable = false, length = 50)
    private String createdByUser;

    @Column(name = "Updated_By_User", length = 50)
    private String updatedByUser;
	@OneToMany(mappedBy = "advertiser", cascade = CascadeType.ALL)
    private Set<User> users;
	
	//newly added  
	
	@ManyToOne
    @JoinColumn(name = "User_Id", referencedColumnName="id")
	@JsonBackReference
    private User user;
	// -- end of it
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
    	this.id = id; 
    }	

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
	@JsonIgnore
    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("advertiserId", advertiserId)
                .add("advertiserName", advertiserName)
                .add("company", company)
                .add("contactName", contactName)
                .add("contactNumber", contactNumber)
                .add("country", country)
                .add("description", description)
                .add("partnerType", partnerType)
                .add("status", status)
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
    }

        
}
