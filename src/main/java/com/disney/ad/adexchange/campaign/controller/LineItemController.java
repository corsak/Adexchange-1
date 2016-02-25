package com.disney.ad.adexchange.campaign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.campaign.domain.LineItem;
import com.disney.ad.adexchange.campaign.domain.LineItemList;
import com.disney.ad.adexchange.campaign.domain.LineItemSearchRequest;
import com.disney.ad.adexchange.campaign.service.LineItemService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class LineItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LineItemController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final LineItemService lineItemService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/lineItem", method = RequestMethod.POST)
    public LineItem createLineItem(@RequestBody @Valid final LineItem lineItem) {
        LOGGER.debug("API: Create {}", lineItem);
        return lineItemService.insert(lineItem);
    }
    
    @RequestMapping(value = "/lineItem", method = RequestMethod.PUT)
    public LineItem updateLineItem(@RequestBody @Valid final LineItem lineItem) {
        LOGGER.debug("API: Update {}", lineItem);
        return lineItemService.update(lineItem);
    }        

    @RequestMapping(value = "/lineItem", method = RequestMethod.GET)
    public LineItemList listLineItems(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all lineItem");
        LineItemList lineItemList = lineItemService.getList(pageNo, pageSize);
        return lineItemList;
    }
    
    @RequestMapping(value = "/lineItem/search", method = RequestMethod.GET)
    public LineItemList searchLineItem(
			@RequestParam(required = false, value = "campaignId") String campaignId,
			@RequestParam(required = false, value = "advertiserId") String advertiserId,
			@RequestParam(required = false, value = "bannerName") String bannerName,
			@RequestParam(required = false, value = "description") String description,
			@RequestParam(required = false, value = "adType") String adType,
			@RequestParam(required = false, value = "weightage") Integer weightage,
			@RequestParam(required = false, value = "bannerDimension") String bannerDimension,
			@RequestParam(required = false, value = "assetUrl") String assetUrl,
			@RequestParam(required = false, value = "assetText") String assetText,
			@RequestParam(required = false, value = "clickUrl") String clickUrl,
			@RequestParam(required = false, value = "callbackUrl") String callbackUrl,
			@RequestParam(required = false, value = "deliveryChannel") String deliveryChannel,
			@RequestParam(required = false, value = "adTag") String adTag,
			@RequestParam(required = false, value = "impressionsCounter") Integer impressionsCounter,
			@RequestParam(required = false, value = "bidsCounter") Integer bidsCounter,
			@RequestParam(required = false, value = "currentSpend") Float currentSpend,
			@RequestParam(required = false, value = "nativeId") String nativeId,
			@RequestParam(required = false, value = "videoId") String videoId,
			@RequestParam(required = false, value = "bannerStatus") String bannerStatus,
			@RequestParam(required = false, value = "status") Integer status,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search lineItem");
        LineItemSearchRequest lineItemSearchRequest = composeLineItemSearchRequest(
			campaignId,
			advertiserId,
			bannerName,
			description,
			adType,
			weightage,
			bannerDimension,
			assetUrl,
			assetText,
			clickUrl,
			callbackUrl,
			deliveryChannel,
			adTag,
			impressionsCounter,
			bidsCounter,
			currentSpend,
			nativeId,
			videoId,
			bannerStatus,
			status,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        LineItemList lineItemList = lineItemService.search(lineItemSearchRequest, pageNo, pageSize);
        return lineItemList;
    }
    
    @RequestMapping(value = "/lineItem/search/keyword", method = RequestMethod.GET)
    public LineItemList searchLineItemByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search lineItem by keyword");
        LineItemList lineItemList = lineItemService.searchByKeyword(keyword, pageNo, pageSize);
        return lineItemList;
    }

    @RequestMapping(value = "/lineItem/{id}", method = RequestMethod.GET)
    public LineItem getLineItem(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list lineItem by Id");
        return lineItemService.getById(id);
    }    

    @RequestMapping(value = "/lineItem/{id}", method = RequestMethod.DELETE)
    public void deleteLineItem(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete lineItem by Id");
        lineItemService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public LineItemSearchRequest composeLineItemSearchRequest(
			String campaignId,
			String advertiserId,
			String bannerName,
			String description,
			String adType,
			Integer weightage,
			String bannerDimension,
			String assetUrl,
			String assetText,
			String clickUrl,
			String callbackUrl,
			String deliveryChannel,
			String adTag,
			Integer impressionsCounter,
			Integer bidsCounter,
			Float currentSpend,
			String nativeId,
			String videoId,
			String bannerStatus,
			Integer status,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		LineItemSearchRequest lineItemSearchRequest = new LineItemSearchRequest();
        lineItemSearchRequest.setCampaignId(campaignId);
        lineItemSearchRequest.setAdvertiserId(advertiserId);
        lineItemSearchRequest.setBannerName(bannerName);
        lineItemSearchRequest.setDescription(description);
        lineItemSearchRequest.setAdType(adType);
        lineItemSearchRequest.setWeightage(weightage);
        lineItemSearchRequest.setBannerDimension(bannerDimension);
        lineItemSearchRequest.setAssetUrl(assetUrl);
        lineItemSearchRequest.setAssetText(assetText);
        lineItemSearchRequest.setClickUrl(clickUrl);
        lineItemSearchRequest.setCallbackUrl(callbackUrl);
        lineItemSearchRequest.setDeliveryChannel(deliveryChannel);
        lineItemSearchRequest.setAdTag(adTag);
        lineItemSearchRequest.setImpressionsCounter(impressionsCounter);
        lineItemSearchRequest.setBidsCounter(bidsCounter);
        lineItemSearchRequest.setCurrentSpend(currentSpend);
        lineItemSearchRequest.setNativeId(nativeId);
        lineItemSearchRequest.setVideoId(videoId);
        lineItemSearchRequest.setBannerStatus(bannerStatus);
        lineItemSearchRequest.setStatus(status);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	lineItemSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	lineItemSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	lineItemSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	lineItemSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        lineItemSearchRequest.setCreatedByUser(createdByUser);
        lineItemSearchRequest.setUpdatedByUser(updatedByUser);
		return lineItemSearchRequest; 
	}    
}
