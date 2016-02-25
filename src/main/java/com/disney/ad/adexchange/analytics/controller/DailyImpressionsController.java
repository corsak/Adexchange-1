package com.disney.ad.adexchange.analytics.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.analytics.domain.DailyImpressions;
import com.disney.ad.adexchange.analytics.domain.DailyImpressionsList;
import com.disney.ad.adexchange.analytics.domain.DailyImpressionsSearchRequest;
import com.disney.ad.adexchange.analytics.service.DailyImpressionsService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class DailyImpressionsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DailyImpressionsController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final DailyImpressionsService dailyImpressionsService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/dailyImpressions", method = RequestMethod.POST)
    public DailyImpressions createDailyImpressions(@RequestBody @Valid final DailyImpressions dailyImpressions) {
        LOGGER.debug("API: Create {}", dailyImpressions);
        return dailyImpressionsService.insert(dailyImpressions);
    }
    
    @RequestMapping(value = "/dailyImpressions", method = RequestMethod.PUT)
    public DailyImpressions updateDailyImpressions(@RequestBody @Valid final DailyImpressions dailyImpressions) {
        LOGGER.debug("API: Update {}", dailyImpressions);
        return dailyImpressionsService.update(dailyImpressions);
    }        

    @RequestMapping(value = "/dailyImpressions", method = RequestMethod.GET)
    public DailyImpressionsList listDailyImpressionss(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all dailyImpressions");
        DailyImpressionsList dailyImpressionsList = dailyImpressionsService.getList(pageNo, pageSize);
        return dailyImpressionsList;
    }
    
    @RequestMapping(value = "/dailyImpressions/search", method = RequestMethod.GET)
    public DailyImpressionsList searchDailyImpressions(
			@RequestParam(required = false, value = "publisherId") Integer publisherId,
			@RequestParam(required = false, value = "digitalPropertyid") Integer digitalPropertyid,
			@RequestParam(required = false, value = "lineItemId") Integer lineItemId,
			@RequestParam(required = false, value = "zoneId") Integer zoneId,
			@RequestParam(required = false, value = "noOfImpressions") Integer noOfImpressions,
			@RequestParam(required = false, value = "noOfClicks") Integer noOfClicks,
			@RequestParam(required = false, value = "date") Integer date,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search dailyImpressions");
        DailyImpressionsSearchRequest dailyImpressionsSearchRequest = composeDailyImpressionsSearchRequest(
			publisherId,
			digitalPropertyid,
			lineItemId,
			zoneId,
			noOfImpressions,
			noOfClicks,
			date,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        DailyImpressionsList dailyImpressionsList = dailyImpressionsService.search(dailyImpressionsSearchRequest, pageNo, pageSize);
        return dailyImpressionsList;
    }
    
    @RequestMapping(value = "/dailyImpressions/search/keyword", method = RequestMethod.GET)
    public DailyImpressionsList searchDailyImpressionsByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search dailyImpressions by keyword");
        DailyImpressionsList dailyImpressionsList = dailyImpressionsService.searchByKeyword(keyword, pageNo, pageSize);
        return dailyImpressionsList;
    }

    @RequestMapping(value = "/dailyImpressions/{id}", method = RequestMethod.GET)
    public DailyImpressions getDailyImpressions(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list dailyImpressions by Id");
        return dailyImpressionsService.getById(id);
    }    

    @RequestMapping(value = "/dailyImpressions/{id}", method = RequestMethod.DELETE)
    public void deleteDailyImpressions(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete dailyImpressions by Id");
        dailyImpressionsService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public DailyImpressionsSearchRequest composeDailyImpressionsSearchRequest(
			Integer publisherId,
			Integer digitalPropertyid,
			Integer lineItemId,
			Integer zoneId,
			Integer noOfImpressions,
			Integer noOfClicks,
			Integer date,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		DailyImpressionsSearchRequest dailyImpressionsSearchRequest = new DailyImpressionsSearchRequest();
        dailyImpressionsSearchRequest.setPublisherId(publisherId);
        dailyImpressionsSearchRequest.setDigitalPropertyid(digitalPropertyid);
        dailyImpressionsSearchRequest.setLineItemId(lineItemId);
        dailyImpressionsSearchRequest.setZoneId(zoneId);
        dailyImpressionsSearchRequest.setNoOfImpressions(noOfImpressions);
        dailyImpressionsSearchRequest.setNoOfClicks(noOfClicks);
        dailyImpressionsSearchRequest.setDate(date);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	dailyImpressionsSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	dailyImpressionsSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	dailyImpressionsSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	dailyImpressionsSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        dailyImpressionsSearchRequest.setCreatedByUser(createdByUser);
        dailyImpressionsSearchRequest.setUpdatedByUser(updatedByUser);
		return dailyImpressionsSearchRequest; 
	}    
}
