package com.disney.ad.adexchange.campaign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.campaign.domain.InsertionOrder;
import com.disney.ad.adexchange.campaign.domain.InsertionOrderList;
import com.disney.ad.adexchange.campaign.domain.InsertionOrderSearchRequest;
import com.disney.ad.adexchange.campaign.service.InsertionOrderService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class InsertionOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertionOrderController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final InsertionOrderService insertionOrderService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/insertionOrder", method = RequestMethod.POST)
    public InsertionOrder createInsertionOrder(@RequestBody @Valid final InsertionOrder insertionOrder) {
        LOGGER.debug("API: Create {}", insertionOrder);
        return insertionOrderService.insert(insertionOrder);
    }
    
    @RequestMapping(value = "/insertionOrder", method = RequestMethod.PUT)
    public InsertionOrder updateInsertionOrder(@RequestBody @Valid final InsertionOrder insertionOrder) {
        LOGGER.debug("API: Update {}", insertionOrder);
        return insertionOrderService.update(insertionOrder);
    }        

    @RequestMapping(value = "/insertionOrder", method = RequestMethod.GET)
    public InsertionOrderList listInsertionOrders(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all insertionOrder");
        InsertionOrderList insertionOrderList = insertionOrderService.getList(pageNo, pageSize);
        return insertionOrderList;
    }
    
    @RequestMapping(value = "/insertionOrder/search", method = RequestMethod.GET)
    public InsertionOrderList searchInsertionOrder(
			@RequestParam(required = false, value = "insertionOrderId") String insertionOrderId,
			@RequestParam(required = false, value = "campaignName") String campaignName,
			@RequestParam(required = false, value = "description") String description,
			@RequestParam(required = false, value = "campaignObjective") String campaignObjective,
			@RequestParam(required = false, value = "campaignStartDateStart") String campaignStartDateStart,
			@RequestParam(required = false, value = "campaignStartDateEnd") String campaignStartDateEnd,			
			@RequestParam(required = false, value = "campaignEndDateStart") String campaignEndDateStart,
			@RequestParam(required = false, value = "campaignEndDateEnd") String campaignEndDateEnd,			
			@RequestParam(required = false, value = "orderType") String orderType,
			@RequestParam(required = false, value = "spend") Float spend,
			@RequestParam(required = false, value = "currency") Float currency,
			@RequestParam(required = false, value = "revenueModel") String revenueModel,
			@RequestParam(required = false, value = "maximumImpressions") Integer maximumImpressions,
			@RequestParam(required = false, value = "maximumSpend") Float maximumSpend,
			@RequestParam(required = false, value = "currentImpressions") Integer currentImpressions,
			@RequestParam(required = false, value = "currentSpend") Float currentSpend,
			@RequestParam(required = false, value = "maximumSpendPerDay") Float maximumSpendPerDay,
			@RequestParam(required = false, value = "pixelTrackingEnabled") Boolean pixelTrackingEnabled,
			@RequestParam(required = false, value = "companionCampaign") String companionCampaign,
			@RequestParam(required = false, value = "campaignStatus") String campaignStatus,
			@RequestParam(required = false, value = "priority") String priority,
			@RequestParam(required = false, value = "comments") String comments,
			@RequestParam(required = false, value = "advertiserId") String advertiserId,
			@RequestParam(required = false, value = "status") Integer status,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search insertionOrder");
        InsertionOrderSearchRequest insertionOrderSearchRequest = composeInsertionOrderSearchRequest(
			insertionOrderId,
			campaignName,
			description,
			campaignObjective,
			campaignStartDateStart,
			campaignStartDateEnd,			
			campaignEndDateStart,
			campaignEndDateEnd,			
			orderType,
			spend,
			currency,
			revenueModel,
			maximumImpressions,
			maximumSpend,
			currentImpressions,
			currentSpend,
			maximumSpendPerDay,
			pixelTrackingEnabled,
			companionCampaign,
			campaignStatus,
			priority,
			comments,
			advertiserId,
			status,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        InsertionOrderList insertionOrderList = insertionOrderService.search(insertionOrderSearchRequest, pageNo, pageSize);
        return insertionOrderList;
    }
    
    @RequestMapping(value = "/insertionOrder/search/keyword", method = RequestMethod.GET)
    public InsertionOrderList searchInsertionOrderByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search insertionOrder by keyword");
        InsertionOrderList insertionOrderList = insertionOrderService.searchByKeyword(keyword, pageNo, pageSize);
        return insertionOrderList;
    }

    @RequestMapping(value = "/insertionOrder/{id}", method = RequestMethod.GET)
    public InsertionOrder getInsertionOrder(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list insertionOrder by Id");
        return insertionOrderService.getById(id);
    }    

    @RequestMapping(value = "/insertionOrder/{id}", method = RequestMethod.DELETE)
    public void deleteInsertionOrder(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete insertionOrder by Id");
        insertionOrderService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public InsertionOrderSearchRequest composeInsertionOrderSearchRequest(
			String insertionOrderId,
			String campaignName,
			String description,
			String campaignObjective,
			String campaignStartDateStart,
			String campaignStartDateEnd,			
			String campaignEndDateStart,
			String campaignEndDateEnd,			
			String orderType,
			Float spend,
			Float currency,
			String revenueModel,
			Integer maximumImpressions,
			Float maximumSpend,
			Integer currentImpressions,
			Float currentSpend,
			Float maximumSpendPerDay,
			Boolean pixelTrackingEnabled,
			String companionCampaign,
			String campaignStatus,
			String priority,
			String comments,
			String advertiserId,
			Integer status,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		InsertionOrderSearchRequest insertionOrderSearchRequest = new InsertionOrderSearchRequest();
        insertionOrderSearchRequest.setInsertionOrderId(insertionOrderId);
        insertionOrderSearchRequest.setCampaignName(campaignName);
        insertionOrderSearchRequest.setDescription(description);
        insertionOrderSearchRequest.setCampaignObjective(campaignObjective);
		if (campaignStartDateStart != null) {
			Timestamp campaignStartDateStartTimestamp = dateUtil.convertDateAsStringToTimestamp(campaignStartDateStart);
        	insertionOrderSearchRequest.setCampaignStartDateStart(campaignStartDateStartTimestamp);
        }
        if (campaignStartDateEnd != null) {
        	Timestamp campaignStartDateEndTimestamp = dateUtil.convertDateAsStringToTimestamp(campaignStartDateEnd);
        	insertionOrderSearchRequest.setCampaignStartDateEnd(campaignStartDateEndTimestamp);
        }
        
		if (campaignEndDateStart != null) {
			Timestamp campaignEndDateStartTimestamp = dateUtil.convertDateAsStringToTimestamp(campaignEndDateStart);
        	insertionOrderSearchRequest.setCampaignEndDateStart(campaignEndDateStartTimestamp);
        }
        if (campaignEndDateEnd != null) {
        	Timestamp campaignEndDateEndTimestamp = dateUtil.convertDateAsStringToTimestamp(campaignEndDateEnd);
        	insertionOrderSearchRequest.setCampaignEndDateEnd(campaignEndDateEndTimestamp);
        }
        
        insertionOrderSearchRequest.setOrderType(orderType);
        insertionOrderSearchRequest.setSpend(spend);
        insertionOrderSearchRequest.setCurrency(currency);
        insertionOrderSearchRequest.setRevenueModel(revenueModel);
        insertionOrderSearchRequest.setMaximumImpressions(maximumImpressions);
        insertionOrderSearchRequest.setMaximumSpend(maximumSpend);
        insertionOrderSearchRequest.setCurrentImpressions(currentImpressions);
        insertionOrderSearchRequest.setCurrentSpend(currentSpend);
        insertionOrderSearchRequest.setMaximumSpendPerDay(maximumSpendPerDay);
        insertionOrderSearchRequest.setPixelTrackingEnabled(pixelTrackingEnabled);
        insertionOrderSearchRequest.setCompanionCampaign(companionCampaign);
        insertionOrderSearchRequest.setCampaignStatus(campaignStatus);
        insertionOrderSearchRequest.setPriority(priority);
        insertionOrderSearchRequest.setComments(comments);
        insertionOrderSearchRequest.setAdvertiserId(advertiserId);
        insertionOrderSearchRequest.setStatus(status);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	insertionOrderSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	insertionOrderSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	insertionOrderSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	insertionOrderSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        insertionOrderSearchRequest.setCreatedByUser(createdByUser);
        insertionOrderSearchRequest.setUpdatedByUser(updatedByUser);
		return insertionOrderSearchRequest; 
	}    
}
