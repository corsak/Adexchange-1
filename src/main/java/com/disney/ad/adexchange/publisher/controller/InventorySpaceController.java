package com.disney.ad.adexchange.publisher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.publisher.domain.InventorySpace;
import com.disney.ad.adexchange.publisher.domain.InventorySpaceList;
import com.disney.ad.adexchange.publisher.domain.InventorySpaceSearchRequest;
import com.disney.ad.adexchange.publisher.service.InventorySpaceService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;

@RestController
public class InventorySpaceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventorySpaceController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final InventorySpaceService inventorySpaceService = null;

    @RequestMapping(value = "/inventorySpace", method = RequestMethod.POST)
    public InventorySpace createInventorySpace(@RequestBody @Valid final InventorySpace inventorySpace) {
        LOGGER.debug("API: Create {}", inventorySpace);
        return inventorySpaceService.insert(inventorySpace);
    }
    
    @RequestMapping(value = "/inventorySpace", method = RequestMethod.PUT)
    public InventorySpace updateInventorySpace(@RequestBody @Valid final InventorySpace inventorySpace) {
        LOGGER.debug("API: Update {}", inventorySpace);
        return inventorySpaceService.update(inventorySpace);
    }        

    @RequestMapping(value = "/inventorySpace", method = RequestMethod.GET)
    public InventorySpaceList listInventorySpaces(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all inventorySpace");
        InventorySpaceList inventorySpaceList = inventorySpaceService.getList(pageNo, pageSize);
        return inventorySpaceList;
    }
    
    @RequestMapping(value = "/inventorySpace/search", method = RequestMethod.GET)
    public InventorySpaceList searchInventorySpace(
			@RequestParam(required = false, value = "inventoryId") String inventoryId,
			@RequestParam(required = false, value = "estimatedValue") Float estimatedValue,
			@RequestParam(required = false, value = "units") String units,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search inventorySpace");
        InventorySpaceSearchRequest inventorySpaceSearchRequest = composeInventorySpaceSearchRequest(
			inventoryId,
			estimatedValue,
			units
        );
		        
        InventorySpaceList inventorySpaceList = inventorySpaceService.search(inventorySpaceSearchRequest, pageNo, pageSize);
        return inventorySpaceList;
    }
    
    @RequestMapping(value = "/inventorySpace/search/keyword", method = RequestMethod.GET)
    public InventorySpaceList searchInventorySpaceByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search inventorySpace by keyword");
        InventorySpaceList inventorySpaceList = inventorySpaceService.searchByKeyword(keyword, pageNo, pageSize);
        return inventorySpaceList;
    }

    @RequestMapping(value = "/inventorySpace/{id}", method = RequestMethod.GET)
    public InventorySpace getInventorySpace(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list inventorySpace by Id");
        return inventorySpaceService.getById(id);
    }    

    @RequestMapping(value = "/inventorySpace/{id}", method = RequestMethod.DELETE)
    public void deleteInventorySpace(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete inventorySpace by Id");
        inventorySpaceService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public InventorySpaceSearchRequest composeInventorySpaceSearchRequest(
			String inventoryId,
			Float estimatedValue,
			String units
) {
		InventorySpaceSearchRequest inventorySpaceSearchRequest = new InventorySpaceSearchRequest();
        inventorySpaceSearchRequest.setInventoryId(inventoryId);
        inventorySpaceSearchRequest.setEstimatedValue(estimatedValue);
        inventorySpaceSearchRequest.setUnits(units);
		return inventorySpaceSearchRequest; 
	}    
}
