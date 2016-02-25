package com.disney.ad.adexchange.user.controller;

import java.sql.Timestamp;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.user.domain.Advertiser;
import com.disney.ad.adexchange.user.domain.AdvertiserList;
import com.disney.ad.adexchange.user.domain.AdvertiserSearchRequest;
import com.disney.ad.adexchange.user.domain.AdvertiserUser;
import com.disney.ad.adexchange.user.domain.User;
import com.disney.ad.adexchange.user.service.AdvertiserService;
import com.disney.ad.adexchange.user.service.UserService;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class AdvertiserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertiserController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final AdvertiserService advertiserService = null;
    //changes made
    @Autowired
    private final UserService userService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/advertiser", method = RequestMethod.POST)
    public Advertiser createAdvertiser(@RequestBody @Valid final Advertiser advertiser) {
        LOGGER.debug("API: Create {}", advertiser);
        return advertiserService.insert(advertiser);
    }
    
    @RequestMapping(value = "/advertiser", method = RequestMethod.PUT)
    public Advertiser updateAdvertiser(@RequestBody @Valid final Advertiser advertiser) {
        LOGGER.debug("API: Update {}", advertiser);
        return advertiserService.update(advertiser);
    }        

    @RequestMapping(value = "/advertiser", method = RequestMethod.GET)
    public AdvertiserList listAdvertisers(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all advertiser");
        AdvertiserList advertiserList = advertiserService.getList(pageNo, pageSize);
        return advertiserList;
    }
    
    @RequestMapping(value = "/advertiser/search", method = RequestMethod.GET)
    public AdvertiserList searchAdvertiser(
			@RequestParam(required = false, value = "advertiserId") String advertiserId,
			@RequestParam(required = false, value = "advertiserName") String advertiserName,
			@RequestParam(required = false, value = "company") String company,
			@RequestParam(required = false, value = "contactName") String contactName,
			@RequestParam(required = false, value = "contactNumber") String contactNumber,
			@RequestParam(required = false, value = "country") String country,
			@RequestParam(required = false, value = "description") String description,
			@RequestParam(required = false, value = "partnerType") String partnerType,
			@RequestParam(required = false, value = "status") String status,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search advertiser");
        AdvertiserSearchRequest advertiserSearchRequest = composeAdvertiserSearchRequest(
			advertiserId,
			advertiserName,
			company,
			contactName,
			contactNumber,
			country,
			description,
			partnerType,
			status,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        AdvertiserList advertiserList = advertiserService.search(advertiserSearchRequest, pageNo, pageSize);
        return advertiserList;
    }
    
    @RequestMapping(value = "/advertiser/search/keyword", method = RequestMethod.GET)
    public AdvertiserList searchAdvertiserByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search advertiser by keyword");
        AdvertiserList advertiserList = advertiserService.searchByKeyword(keyword, pageNo, pageSize);
        return advertiserList;
    }

    @RequestMapping(value = "/advertiser/{id}", method = RequestMethod.GET)
    public Advertiser getAdvertiser(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list advertiser by Id");
        return advertiserService.getById(id);
    }    

    @RequestMapping(value = "/advertiser/{id}", method = RequestMethod.DELETE)
    public void deleteAdvertiser(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete advertiser by Id");
        advertiserService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public AdvertiserSearchRequest composeAdvertiserSearchRequest(
			String advertiserId,
			String advertiserName,
			String company,
			String contactName,
			String contactNumber,
			String country,
			String description,
			String partnerType,
			String status,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		AdvertiserSearchRequest advertiserSearchRequest = new AdvertiserSearchRequest();
        advertiserSearchRequest.setAdvertiserId(advertiserId);
        advertiserSearchRequest.setAdvertiserName(advertiserName);
        advertiserSearchRequest.setCompany(company);
        advertiserSearchRequest.setContactName(contactName);
        advertiserSearchRequest.setContactNumber(contactNumber);
        advertiserSearchRequest.setCountry(country);
        advertiserSearchRequest.setDescription(description);
        advertiserSearchRequest.setPartnerType(partnerType);
        advertiserSearchRequest.setStatus(status);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	advertiserSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	advertiserSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	advertiserSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	advertiserSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        advertiserSearchRequest.setCreatedByUser(createdByUser);
        advertiserSearchRequest.setUpdatedByUser(updatedByUser);
		return advertiserSearchRequest; 
	}
    //changes made
	@RequestMapping(value = "/advertiser/signup", method = RequestMethod.POST)
	public Advertiser advertiserSign(@RequestBody @Valid final AdvertiserUser advertiserUser) {
        LOGGER.debug("API: Advertiser Sign up {}", advertiserUser.getAdvertiser());
        LOGGER.debug("API: User Sign up {}", advertiserUser.getUser());
        UUID uuid = UUID.randomUUID();
        long unixTime = System.currentTimeMillis();
        Advertiser tempAdvertiser = advertiserUser.getAdvertiser();
        if(tempAdvertiser.getId() == null){
        	tempAdvertiser.setId(0);
        }
        if(tempAdvertiser.getAdvertiserId() == null){
        	tempAdvertiser.setAdvertiserId(uuid.toString());
        }
        if(tempAdvertiser.getContactName() == null){
        	tempAdvertiser.setContactName(null);
        }
        if(tempAdvertiser.getContactNumber() == null){
        	tempAdvertiser.setContactNumber(null);
        }
        if(tempAdvertiser.getCountry() == null){
        	tempAdvertiser.setCountry(null);
        }
        if(tempAdvertiser.getDescription() == null){
        	tempAdvertiser.setDescription(null);
        }
        if(tempAdvertiser.getStatus() == null){
        	tempAdvertiser.setStatus("1");
        }
        if(tempAdvertiser.getCreatedTime() == null){
        	tempAdvertiser.setCreatedTime(new Timestamp(unixTime));
        }
        if(tempAdvertiser.getUpdatedTime() == null){
        	tempAdvertiser.setUpdatedTime(new Timestamp(unixTime));
        }
        if(tempAdvertiser.getCreatedByUser() == null){
        	tempAdvertiser.setCreatedByUser("admin");
        }
        if(tempAdvertiser.getUpdatedByUser() == null){
        	tempAdvertiser.setUpdatedByUser("admin");
        }
        //for user data
        User tempUser = tempAdvertiser.getUser();
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
        tempAdvertiser.setUser(null);
        Advertiser inbet = advertiserService.insert(tempAdvertiser);
        User finaluser = userService.insert(tempUser);
        finaluser.setAdvertiser(inbet);
        inbet.setUser(finaluser);
        return advertiserService.update(inbet);
    }
}
