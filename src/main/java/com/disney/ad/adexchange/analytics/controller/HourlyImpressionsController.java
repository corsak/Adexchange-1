package com.disney.ad.adexchange.analytics.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.analytics.domain.HourlyImpressions;
import com.disney.ad.adexchange.analytics.domain.HourlyImpressionsList;
import com.disney.ad.adexchange.analytics.domain.HourlyImpressionsSearchRequest;
import com.disney.ad.adexchange.analytics.service.HourlyImpressionsService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class HourlyImpressionsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HourlyImpressionsController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final HourlyImpressionsService hourlyImpressionsService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/hourlyImpressions", method = RequestMethod.POST)
    public HourlyImpressions createHourlyImpressions(@RequestBody @Valid final HourlyImpressions hourlyImpressions) {
        LOGGER.debug("API: Create {}", hourlyImpressions);
        return hourlyImpressionsService.insert(hourlyImpressions);
    }
    
    @RequestMapping(value = "/hourlyImpressions", method = RequestMethod.PUT)
    public HourlyImpressions updateHourlyImpressions(@RequestBody @Valid final HourlyImpressions hourlyImpressions) {
        LOGGER.debug("API: Update {}", hourlyImpressions);
        return hourlyImpressionsService.update(hourlyImpressions);
    }        

    @RequestMapping(value = "/hourlyImpressions", method = RequestMethod.GET)
    public HourlyImpressionsList listHourlyImpressionss(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all hourlyImpressions");
        HourlyImpressionsList hourlyImpressionsList = hourlyImpressionsService.getList(pageNo, pageSize);
        return hourlyImpressionsList;
    }
    
    @RequestMapping(value = "/hourlyImpressions/search", method = RequestMethod.GET)
    public HourlyImpressionsList searchHourlyImpressions(
			@RequestParam(required = false, value = "publisherId") String publisherId,
			@RequestParam(required = false, value = "digitalPropertyid") Integer digitalPropertyid,
			@RequestParam(required = false, value = "lineItemId") Integer lineItemId,
			@RequestParam(required = false, value = "zoneId") String zoneId,
			@RequestParam(required = false, value = "noOfImpressions") Integer noOfImpressions,
			@RequestParam(required = false, value = "noOfClicks") Integer noOfClicks,
			@RequestParam(required = false, value = "dateTime") Integer dateTime,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search hourlyImpressions");
        HourlyImpressionsSearchRequest hourlyImpressionsSearchRequest = composeHourlyImpressionsSearchRequest(
			publisherId,
			digitalPropertyid,
			lineItemId,
			zoneId,
			noOfImpressions,
			noOfClicks,
			dateTime,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        HourlyImpressionsList hourlyImpressionsList = hourlyImpressionsService.search(hourlyImpressionsSearchRequest, pageNo, pageSize);
        return hourlyImpressionsList;
    }
    
    @RequestMapping(value = "/hourlyImpressions/search/keyword", method = RequestMethod.GET)
    public HourlyImpressionsList searchHourlyImpressionsByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search hourlyImpressions by keyword");
        HourlyImpressionsList hourlyImpressionsList = hourlyImpressionsService.searchByKeyword(keyword, pageNo, pageSize);
        return hourlyImpressionsList;
    }

    @RequestMapping(value = "/hourlyImpressions/{id}", method = RequestMethod.GET)
    public HourlyImpressions getHourlyImpressions(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list hourlyImpressions by Id");
        return hourlyImpressionsService.getById(id);
    }    

    @RequestMapping(value = "/hourlyImpressions/{id}", method = RequestMethod.DELETE)
    public void deleteHourlyImpressions(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete hourlyImpressions by Id");
        hourlyImpressionsService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public HourlyImpressionsSearchRequest composeHourlyImpressionsSearchRequest(
			String publisherId,
			Integer digitalPropertyid,
			Integer lineItemId,
			String zoneId,
			Integer noOfImpressions,
			Integer noOfClicks,
			Integer dateTime,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		HourlyImpressionsSearchRequest hourlyImpressionsSearchRequest = new HourlyImpressionsSearchRequest();
        hourlyImpressionsSearchRequest.setPublisherId(publisherId);
        hourlyImpressionsSearchRequest.setDigitalPropertyid(digitalPropertyid);
        hourlyImpressionsSearchRequest.setLineItemId(lineItemId);
        hourlyImpressionsSearchRequest.setZoneId(zoneId);
        hourlyImpressionsSearchRequest.setNoOfImpressions(noOfImpressions);
        hourlyImpressionsSearchRequest.setNoOfClicks(noOfClicks);
        hourlyImpressionsSearchRequest.setDateTime(dateTime);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	hourlyImpressionsSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	hourlyImpressionsSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	hourlyImpressionsSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	hourlyImpressionsSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        hourlyImpressionsSearchRequest.setCreatedByUser(createdByUser);
        hourlyImpressionsSearchRequest.setUpdatedByUser(updatedByUser);
		return hourlyImpressionsSearchRequest; 
	}    
}
