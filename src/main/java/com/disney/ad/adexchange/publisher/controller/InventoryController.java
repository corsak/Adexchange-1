package com.disney.ad.adexchange.publisher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.publisher.domain.Inventory;
import com.disney.ad.adexchange.publisher.domain.InventoryList;
import com.disney.ad.adexchange.publisher.domain.InventorySearchRequest;
import com.disney.ad.adexchange.publisher.service.InventoryService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class InventoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final InventoryService inventoryService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    public Inventory createInventory(@RequestBody @Valid final Inventory inventory) {
        LOGGER.debug("API: Create {}", inventory);
        return inventoryService.insert(inventory);
    }
    
    @RequestMapping(value = "/inventory", method = RequestMethod.PUT)
    public Inventory updateInventory(@RequestBody @Valid final Inventory inventory) {
        LOGGER.debug("API: Update {}", inventory);
        return inventoryService.update(inventory);
    }        

    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public InventoryList listInventorys(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all inventory");
        InventoryList inventoryList = inventoryService.getList(pageNo, pageSize);
        return inventoryList;
    }
    
    @RequestMapping(value = "/inventory/search", method = RequestMethod.GET)
    public InventoryList searchInventory(
			@RequestParam(required = false, value = "zoneID") String zoneID,
			@RequestParam(required = false, value = "digitalPropertyId") String digitalPropertyId,
			@RequestParam(required = false, value = "inventoryName") String inventoryName,
			@RequestParam(required = false, value = "description") String description,
			@RequestParam(required = false, value = "zoneType") String zoneType,
			@RequestParam(required = false, value = "adType") String adType,
			@RequestParam(required = false, value = "adTemplateID") String adTemplateID,
			@RequestParam(required = false, value = "adWidth") Integer adWidth,
			@RequestParam(required = false, value = "adHeight") Integer adHeight,
			@RequestParam(required = false, value = "adInvocationTag") String adInvocationTag,
			@RequestParam(required = false, value = "passbackAdTag") String passbackAdTag,
			@RequestParam(required = false, value = "floorPrice") Integer floorPrice,
			@RequestParam(required = false, value = "totalRequests") Integer totalRequests,
			@RequestParam(required = false, value = "totalImpressions") Integer totalImpressions,
			@RequestParam(required = false, value = "totalRevenues") Float totalRevenues,
			@RequestParam(required = false, value = "keywords") String keywords,
			@RequestParam(required = false, value = "status") Integer status,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search inventory");
        InventorySearchRequest inventorySearchRequest = composeInventorySearchRequest(
			zoneID,
			digitalPropertyId,
			inventoryName,
			description,
			zoneType,
			adType,
			adTemplateID,
			adWidth,
			adHeight,
			adInvocationTag,
			passbackAdTag,
			floorPrice,
			totalRequests,
			totalImpressions,
			totalRevenues,
			keywords,
			status,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        InventoryList inventoryList = inventoryService.search(inventorySearchRequest, pageNo, pageSize);
        return inventoryList;
    }
    
    @RequestMapping(value = "/inventory/search/keyword", method = RequestMethod.GET)
    public InventoryList searchInventoryByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search inventory by keyword");
        InventoryList inventoryList = inventoryService.searchByKeyword(keyword, pageNo, pageSize);
        return inventoryList;
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
    public Inventory getInventory(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list inventory by Id");
        return inventoryService.getById(id);
    }    

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.DELETE)
    public void deleteInventory(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete inventory by Id");
        inventoryService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public InventorySearchRequest composeInventorySearchRequest(
			String zoneID,
			String digitalPropertyId,
			String inventoryName,
			String description,
			String zoneType,
			String adType,
			String adTemplateID,
			Integer adWidth,
			Integer adHeight,
			String adInvocationTag,
			String passbackAdTag,
			Integer floorPrice,
			Integer totalRequests,
			Integer totalImpressions,
			Float totalRevenues,
			String keywords,
			Integer status,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		InventorySearchRequest inventorySearchRequest = new InventorySearchRequest();
        inventorySearchRequest.setZoneID(zoneID);
        inventorySearchRequest.setDigitalPropertyId(digitalPropertyId);
        inventorySearchRequest.setInventoryName(inventoryName);
        inventorySearchRequest.setDescription(description);
        inventorySearchRequest.setZoneType(zoneType);
        inventorySearchRequest.setAdType(adType);
        inventorySearchRequest.setAdTemplateID(adTemplateID);
        inventorySearchRequest.setAdWidth(adWidth);
        inventorySearchRequest.setAdHeight(adHeight);
        inventorySearchRequest.setAdInvocationTag(adInvocationTag);
        inventorySearchRequest.setPassbackAdTag(passbackAdTag);
        inventorySearchRequest.setFloorPrice(floorPrice);
        inventorySearchRequest.setTotalRequests(totalRequests);
        inventorySearchRequest.setTotalImpressions(totalImpressions);
        inventorySearchRequest.setTotalRevenues(totalRevenues);
        inventorySearchRequest.setKeywords(keywords);
        inventorySearchRequest.setStatus(status);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	inventorySearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	inventorySearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	inventorySearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	inventorySearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        inventorySearchRequest.setCreatedByUser(createdByUser);
        inventorySearchRequest.setUpdatedByUser(updatedByUser);
		return inventorySearchRequest; 
	}    
}
