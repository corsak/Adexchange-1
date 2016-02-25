package com.disney.ad.adexchange.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.common.domain.IndustryCategory;
import com.disney.ad.adexchange.common.domain.IndustryCategoryList;
import com.disney.ad.adexchange.common.domain.IndustryCategorySearchRequest;
import com.disney.ad.adexchange.common.service.IndustryCategoryService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class IndustryCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndustryCategoryController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final IndustryCategoryService industryCategoryService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/industryCategory", method = RequestMethod.POST)
    public IndustryCategory createIndustryCategory(@RequestBody @Valid final IndustryCategory industryCategory) {
        LOGGER.debug("API: Create {}", industryCategory);
        return industryCategoryService.insert(industryCategory);
    }
    
    @RequestMapping(value = "/industryCategory", method = RequestMethod.PUT)
    public IndustryCategory updateIndustryCategory(@RequestBody @Valid final IndustryCategory industryCategory) {
        LOGGER.debug("API: Update {}", industryCategory);
        return industryCategoryService.update(industryCategory);
    }        

    @RequestMapping(value = "/industryCategory", method = RequestMethod.GET)
    public IndustryCategoryList listIndustryCategorys(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all industryCategory");
        IndustryCategoryList industryCategoryList = industryCategoryService.getList(pageNo, pageSize);
        return industryCategoryList;
    }
    
    @RequestMapping(value = "/industryCategory/search", method = RequestMethod.GET)
    public IndustryCategoryList searchIndustryCategory(
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "description") String description,
			@RequestParam(required = false, value = "status") Integer status,
			@RequestParam(required = false, value = "parentID") String parentID,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search industryCategory");
        IndustryCategorySearchRequest industryCategorySearchRequest = composeIndustryCategorySearchRequest(
			name,
			description,
			status,
			parentID,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        IndustryCategoryList industryCategoryList = industryCategoryService.search(industryCategorySearchRequest, pageNo, pageSize);
        return industryCategoryList;
    }
    
    @RequestMapping(value = "/industryCategory/search/keyword", method = RequestMethod.GET)
    public IndustryCategoryList searchIndustryCategoryByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search industryCategory by keyword");
        IndustryCategoryList industryCategoryList = industryCategoryService.searchByKeyword(keyword, pageNo, pageSize);
        return industryCategoryList;
    }

    @RequestMapping(value = "/industryCategory/{id}", method = RequestMethod.GET)
    public IndustryCategory getIndustryCategory(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list industryCategory by Id");
        return industryCategoryService.getById(id);
    }    

    @RequestMapping(value = "/industryCategory/{id}", method = RequestMethod.DELETE)
    public void deleteIndustryCategory(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete industryCategory by Id");
        industryCategoryService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public IndustryCategorySearchRequest composeIndustryCategorySearchRequest(
			String name,
			String description,
			Integer status,
			String parentID,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		IndustryCategorySearchRequest industryCategorySearchRequest = new IndustryCategorySearchRequest();
        industryCategorySearchRequest.setName(name);
        industryCategorySearchRequest.setDescription(description);
        industryCategorySearchRequest.setStatus(status);
        industryCategorySearchRequest.setParentID(parentID);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	industryCategorySearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	industryCategorySearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	industryCategorySearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	industryCategorySearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        industryCategorySearchRequest.setCreatedByUser(createdByUser);
        industryCategorySearchRequest.setUpdatedByUser(updatedByUser);
		return industryCategorySearchRequest; 
	}    
}
