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
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Publisher_Id", nullable = false, length = 50)
    private String publisherId;

    @Column(name = "Name", nullable = false, length = 50)
    private String publisherName;

    @Column(name = "Company", length = 50)
    private String company;

    @Column(name = "Contact_Name", length = 50)
    private String contactName;

    @Column(name = "Contact_Number", length = 50)
    private String contactNumber;

    @Column(name = "Country", nullable = false, length = 50)
    private String country;

    @Column(name = "Primary_Domain", nullable = false, length = 50)
    private String primaryDomain;

    @Column(name = "IABCategory", nullable = false, length = 50)
    private String iabCategory;

    @Column(name = "Address", length = 250)
    private String address;

    @Column(name = "Status", nullable = false)
    private Integer status;

    @Column(name = "Created_Time", nullable = false)
    private Timestamp createdTime;

    @Column(name = "Updated_Time", nullable = false)
    private Timestamp updatedTime;

    @Column(name = "Created_By_User", length = 50)
    private String createdByUser;

    @Column(name = "Updated_By_User", length = 50)
    private String updatedByUser;
	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
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

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
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

	public String getPrimaryDomain() {
        return primaryDomain;
    }

    public void setPrimaryDomain(String primaryDomain) {
        this.primaryDomain = primaryDomain;
    }

    public String getIabCategory() {
        return iabCategory;
    }

    public void setIabCategory(String iabCategory) {
        this.iabCategory = iabCategory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
                .add("publisherId", publisherId)
                .add("publisherName", publisherName)
                .add("company", company)
                .add("contactName", contactName)
                .add("contactNumber", contactNumber)
                .add("country", country)
                .add("primaryDomain", primaryDomain)
                .add("iabCategory", iabCategory)
                .add("address", address)
                .add("status", status)
                .add("createdTime", createdTime)
                .add("updatedTime", updatedTime)
                .add("createdByUser", createdByUser)
                .add("updatedByUser", updatedByUser)
                .toString();
    }

        
}
