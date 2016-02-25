package com.disney.ad.adexchange.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.user.domain.Advertiser;
import com.disney.ad.adexchange.user.domain.AdvertiserUser;
import com.disney.ad.adexchange.user.domain.Publisher;
import com.disney.ad.adexchange.user.domain.PublisherList;
import com.disney.ad.adexchange.user.domain.PublisherSearchRequest;
import com.disney.ad.adexchange.user.domain.PublisherUser;
import com.disney.ad.adexchange.user.domain.User;
import com.disney.ad.adexchange.user.service.PublisherService;
import com.disney.ad.adexchange.user.service.UserService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;

import java.sql.Timestamp;
import java.util.UUID;

import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class PublisherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final PublisherService publisherService = null;

  //changes made
    @Autowired
    private final UserService userService = null;
    
    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/publisher", method = RequestMethod.POST)
    public Publisher createPublisher(@RequestBody @Valid final Publisher publisher) {
        LOGGER.debug("API: Create {}", publisher);
        return publisherService.insert(publisher);
    }
    
    @RequestMapping(value = "/publisher", method = RequestMethod.PUT)
    public Publisher updatePublisher(@RequestBody @Valid final Publisher publisher) {
        LOGGER.debug("API: Update {}", publisher);
        return publisherService.update(publisher);
    }        

    @RequestMapping(value = "/publisher", method = RequestMethod.GET)
    public PublisherList listPublishers(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all publisher");
        PublisherList publisherList = publisherService.getList(pageNo, pageSize);
        return publisherList;
    }
    
    @RequestMapping(value = "/publisher/search", method = RequestMethod.GET)
    public PublisherList searchPublisher(
			@RequestParam(required = false, value = "publisherId") String publisherId,
			@RequestParam(required = false, value = "publisherName") String publisherName,
			@RequestParam(required = false, value = "company") String company,
			@RequestParam(required = false, value = "contactName") String contactName,
			@RequestParam(required = false, value = "contactNumber") String contactNumber,
			@RequestParam(required = false, value = "country") String country,
			@RequestParam(required = false, value = "primaryDomain") String primaryDomain,
			@RequestParam(required = false, value = "iabCategory") String iabCategory,
			@RequestParam(required = false, value = "address") String address,
			@RequestParam(required = false, value = "status") Integer status,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search publisher");
        PublisherSearchRequest publisherSearchRequest = composePublisherSearchRequest(
			publisherId,
			publisherName,
			company,
			contactName,
			contactNumber,
			country,
			primaryDomain,
			iabCategory,
			address,
			status,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        PublisherList publisherList = publisherService.search(publisherSearchRequest, pageNo, pageSize);
        return publisherList;
    }
    
    @RequestMapping(value = "/publisher/search/keyword", method = RequestMethod.GET)
    public PublisherList searchPublisherByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search publisher by keyword");
        PublisherList publisherList = publisherService.searchByKeyword(keyword, pageNo, pageSize);
        return publisherList;
    }

    @RequestMapping(value = "/publisher/{id}", method = RequestMethod.GET)
    public Publisher getPublisher(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list publisher by Id");
        return publisherService.getById(id);
    }    

    @RequestMapping(value = "/publisher/{id}", method = RequestMethod.DELETE)
    public void deletePublisher(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete publisher by Id");
        publisherService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public PublisherSearchRequest composePublisherSearchRequest(
			String publisherId,
			String publisherName,
			String company,
			String contactName,
			String contactNumber,
			String country,
			String primaryDomain,
			String iabCategory,
			String address,
			Integer status,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		PublisherSearchRequest publisherSearchRequest = new PublisherSearchRequest();
        publisherSearchRequest.setPublisherId(publisherId);
        publisherSearchRequest.setPublisherName(publisherName);
        publisherSearchRequest.setCompany(company);
        publisherSearchRequest.setContactName(contactName);
        publisherSearchRequest.setContactNumber(contactNumber);
        publisherSearchRequest.setCountry(country);
        publisherSearchRequest.setPrimaryDomain(primaryDomain);
        publisherSearchRequest.setIabCategory(iabCategory);
        publisherSearchRequest.setAddress(address);
        publisherSearchRequest.setStatus(status);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	publisherSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	publisherSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	publisherSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	publisherSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        publisherSearchRequest.setCreatedByUser(createdByUser);
        publisherSearchRequest.setUpdatedByUser(updatedByUser);
		return publisherSearchRequest; 
	}
	
    //changes made
	@RequestMapping(value = "/publisher/signup", method = RequestMethod.POST)
	public Publisher publisherSign(@RequestBody @Valid final PublisherUser publisherUser) {
        LOGGER.debug("API: Advertiser Sign up {}", publisherUser.getPublisher());
        LOGGER.debug("API: User Sign up {}", publisherUser.getUser());
        UUID uuid = UUID.randomUUID();
        Publisher tempPublisher = publisherUser.getPublisher();
        long unixTime = System.currentTimeMillis();
        if(tempPublisher.getId() == null){
        	tempPublisher.setId(0);
        }
        if(tempPublisher.getPublisherId() == null){
        	tempPublisher.setPublisherId(uuid.toString());
        }
        if(tempPublisher.getContactName() == null){
        	tempPublisher.setContactName(null);
        }
        if(tempPublisher.getContactNumber() == null){
        	tempPublisher.setContactNumber(null);
        }
        if(tempPublisher.getCountry() == null){
        	tempPublisher.setCountry(" ");
        }
        if(tempPublisher.getStatus() == null){
        	tempPublisher.setStatus(1);
        }
        if(tempPublisher.getAddress() == null){
        	tempPublisher.setAddress(null);
        }
        if(tempPublisher.getCreatedTime() == null){
        	tempPublisher.setCreatedTime(new Timestamp(unixTime));
        }
        if(tempPublisher.getUpdatedTime() == null){
        	tempPublisher.setUpdatedTime(new Timestamp(unixTime));
        }
        if(tempPublisher.getCreatedByUser() == null){
        	tempPublisher.setCreatedByUser("admin");
        }
        if(tempPublisher.getUpdatedByUser() == null){
        	tempPublisher.setUpdatedByUser("admin");
        }
        //for user data
        User tempUser = tempPublisher.getUser();
        if(tempUser.getId() == null){
        	tempUser.setId(0);
        }
        if(tempUser.getLoginName() == null){
        	tempUser.setLoginName(tempUser.getEmail());
        }
        if(tempUser.getStatus() == null){
        	tempUser.setStatus(1);
        }
        if(tempUser.getUserType() == null){
        	tempUser.setUserType(1);
        }
        if(tempUser.getCreatedTime() == null){
        	tempUser.setCreatedTime(new Timestamp(unixTime));
        }
        if(tempUser.getUpdatedTime() == null){
        	tempUser.setUpdatedTime(new Timestamp(unixTime));
        }
        if(tempUser.getCreatedByUser() == null){
        	tempUser.setCreatedByUser("admin");
        }
        if(tempUser.getUpdatedByUser() == null){
        	tempUser.setUpdatedByUser("admin");
        }
        if(tempUser.getIsAdmin() == null){
        	tempUser.setIsAdmin(false);
        }
        if(tempUser.getHintQuestion1() == null){
        	tempUser.setHintQuestion1(null);
        }
        if(tempUser.getHintQuestion2() == null){
        	tempUser.setHintQuestion2(null);
        }
        if(tempUser.getHintAnswer1() == null){
        	tempUser.setHintAnswer1(null);
        }
        if(tempUser.getHintAnswer2() == null){
        	tempUser.setHintAnswer2(null);
        }
        tempPublisher.setUser(null);
        Publisher finalPub = publisherService.insert(tempPublisher);
        System.out.println("inbed value : "+finalPub);
        User finaluser = userService.insert(tempUser);
        finaluser.setPublisher(finalPub);
        finalPub.setUser(finaluser);
        return publisherService.update(finalPub);
    }
	
}
