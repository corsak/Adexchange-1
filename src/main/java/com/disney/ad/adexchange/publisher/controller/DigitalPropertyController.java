package com.disney.ad.adexchange.publisher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.publisher.domain.DigitalProperty;
import com.disney.ad.adexchange.publisher.domain.DigitalPropertyList;
import com.disney.ad.adexchange.publisher.domain.DigitalPropertySearchRequest;
import com.disney.ad.adexchange.publisher.service.DigitalPropertyService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class DigitalPropertyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DigitalPropertyController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final DigitalPropertyService digitalPropertyService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/digitalProperty", method = RequestMethod.POST)
    public DigitalProperty createDigitalProperty(@RequestBody @Valid final DigitalProperty digitalProperty) {
        LOGGER.debug("API: Create {}", digitalProperty);
        return digitalPropertyService.insert(digitalProperty);
    }
    
    @RequestMapping(value = "/digitalProperty", method = RequestMethod.PUT)
    public DigitalProperty updateDigitalProperty(@RequestBody @Valid final DigitalProperty digitalProperty) {
        LOGGER.debug("API: Update {}", digitalProperty);
        return digitalPropertyService.update(digitalProperty);
    }        

    @RequestMapping(value = "/digitalProperty", method = RequestMethod.GET)
    public DigitalPropertyList listDigitalPropertys(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all digitalProperty");
        DigitalPropertyList digitalPropertyList = digitalPropertyService.getList(pageNo, pageSize);
        return digitalPropertyList;
    }
    
    @RequestMapping(value = "/digitalProperty/search", method = RequestMethod.GET)
    public DigitalPropertyList searchDigitalProperty(
			@RequestParam(required = false, value = "propertyName") String propertyName,
			@RequestParam(required = false, value = "description") String description,
			@RequestParam(required = false, value = "type") String type,
			@RequestParam(required = false, value = "domainURL") String domainURL,
			@RequestParam(required = false, value = "publisherId") String publisherId,
			@RequestParam(required = false, value = "IABCategory") String IABCategory,
			@RequestParam(required = false, value = "IABSubCategory") String IABSubCategory,
			@RequestParam(required = false, value = "country") String country,
			@RequestParam(required = false, value = "language") String language,
			@RequestParam(required = false, value = "status") Integer status,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search digitalProperty");
        DigitalPropertySearchRequest digitalPropertySearchRequest = composeDigitalPropertySearchRequest(
			propertyName,
			description,
			type,
			domainURL,
			publisherId,
			IABCategory,
			IABSubCategory,
			country,
			language,
			status,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        DigitalPropertyList digitalPropertyList = digitalPropertyService.search(digitalPropertySearchRequest, pageNo, pageSize);
        return digitalPropertyList;
    }
    
    @RequestMapping(value = "/digitalProperty/search/keyword", method = RequestMethod.GET)
    public DigitalPropertyList searchDigitalPropertyByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search digitalProperty by keyword");
        DigitalPropertyList digitalPropertyList = digitalPropertyService.searchByKeyword(keyword, pageNo, pageSize);
        return digitalPropertyList;
    }

    @RequestMapping(value = "/digitalProperty/{id}", method = RequestMethod.GET)
    public DigitalProperty getDigitalProperty(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list digitalProperty by Id");
        return digitalPropertyService.getById(id);
    }    

    @RequestMapping(value = "/digitalProperty/{id}", method = RequestMethod.DELETE)
    public void deleteDigitalProperty(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete digitalProperty by Id");
        digitalPropertyService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public DigitalPropertySearchRequest composeDigitalPropertySearchRequest(
			String propertyName,
			String description,
			String type,
			String domainURL,
			String publisherId,
			String IABCategory,
			String IABSubCategory,
			String country,
			String language,
			Integer status,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		DigitalPropertySearchRequest digitalPropertySearchRequest = new DigitalPropertySearchRequest();
        digitalPropertySearchRequest.setPropertyName(propertyName);
        digitalPropertySearchRequest.setDescription(description);
        digitalPropertySearchRequest.setType(type);
        digitalPropertySearchRequest.setDomainURL(domainURL);
        digitalPropertySearchRequest.setPublisherId(publisherId);
        digitalPropertySearchRequest.setIABCategory(IABCategory);
        digitalPropertySearchRequest.setIABSubCategory(IABSubCategory);
        digitalPropertySearchRequest.setCountry(country);
        digitalPropertySearchRequest.setLanguage(language);
        digitalPropertySearchRequest.setStatus(status);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	digitalPropertySearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	digitalPropertySearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	digitalPropertySearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	digitalPropertySearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        digitalPropertySearchRequest.setCreatedByUser(createdByUser);
        digitalPropertySearchRequest.setUpdatedByUser(updatedByUser);
		return digitalPropertySearchRequest; 
	}    
}
