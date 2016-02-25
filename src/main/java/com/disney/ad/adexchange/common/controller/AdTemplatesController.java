package com.disney.ad.adexchange.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.common.domain.AdTemplates;
import com.disney.ad.adexchange.common.domain.AdTemplatesList;
import com.disney.ad.adexchange.common.domain.AdTemplatesSearchRequest;
import com.disney.ad.adexchange.common.service.AdTemplatesService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class AdTemplatesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdTemplatesController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final AdTemplatesService adTemplatesService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/adTemplates", method = RequestMethod.POST)
    public AdTemplates createAdTemplates(@RequestBody @Valid final AdTemplates adTemplates) {
        LOGGER.debug("API: Create {}", adTemplates);
        return adTemplatesService.insert(adTemplates);
    }
    
    @RequestMapping(value = "/adTemplates", method = RequestMethod.PUT)
    public AdTemplates updateAdTemplates(@RequestBody @Valid final AdTemplates adTemplates) {
        LOGGER.debug("API: Update {}", adTemplates);
        return adTemplatesService.update(adTemplates);
    }        

    @RequestMapping(value = "/adTemplates", method = RequestMethod.GET)
    public AdTemplatesList listAdTemplatess(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all adTemplates");
        AdTemplatesList adTemplatesList = adTemplatesService.getList(pageNo, pageSize);
        return adTemplatesList;
    }
    
    @RequestMapping(value = "/adTemplates/search", method = RequestMethod.GET)
    public AdTemplatesList searchAdTemplates(
			@RequestParam(required = false, value = "adTemplateID") String adTemplateID,
			@RequestParam(required = false, value = "templateName") String templateName,
			@RequestParam(required = false, value = "isMobileFlag") Integer isMobileFlag,
			@RequestParam(required = false, value = "width") Integer width,
			@RequestParam(required = false, value = "height") Integer height,
			@RequestParam(required = false, value = "status") Integer status,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search adTemplates");
        AdTemplatesSearchRequest adTemplatesSearchRequest = composeAdTemplatesSearchRequest(
			adTemplateID,
			templateName,
			isMobileFlag,
			width,
			height,
			status,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        AdTemplatesList adTemplatesList = adTemplatesService.search(adTemplatesSearchRequest, pageNo, pageSize);
        return adTemplatesList;
    }
    
    @RequestMapping(value = "/adTemplates/search/keyword", method = RequestMethod.GET)
    public AdTemplatesList searchAdTemplatesByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search adTemplates by keyword");
        AdTemplatesList adTemplatesList = adTemplatesService.searchByKeyword(keyword, pageNo, pageSize);
        return adTemplatesList;
    }

    @RequestMapping(value = "/adTemplates/{id}", method = RequestMethod.GET)
    public AdTemplates getAdTemplates(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list adTemplates by Id");
        return adTemplatesService.getById(id);
    }    

    @RequestMapping(value = "/adTemplates/{id}", method = RequestMethod.DELETE)
    public void deleteAdTemplates(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete adTemplates by Id");
        adTemplatesService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public AdTemplatesSearchRequest composeAdTemplatesSearchRequest(
			String adTemplateID,
			String templateName,
			Integer isMobileFlag,
			Integer width,
			Integer height,
			Integer status,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		AdTemplatesSearchRequest adTemplatesSearchRequest = new AdTemplatesSearchRequest();
        adTemplatesSearchRequest.setAdTemplateID(adTemplateID);
        adTemplatesSearchRequest.setTemplateName(templateName);
        adTemplatesSearchRequest.setIsMobileFlag(isMobileFlag);
        adTemplatesSearchRequest.setWidth(width);
        adTemplatesSearchRequest.setHeight(height);
        adTemplatesSearchRequest.setStatus(status);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	adTemplatesSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	adTemplatesSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	adTemplatesSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	adTemplatesSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        adTemplatesSearchRequest.setCreatedByUser(createdByUser);
        adTemplatesSearchRequest.setUpdatedByUser(updatedByUser);
		return adTemplatesSearchRequest; 
	}    
}
