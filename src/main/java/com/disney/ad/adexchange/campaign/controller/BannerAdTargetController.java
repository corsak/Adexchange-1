package com.disney.ad.adexchange.campaign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.campaign.domain.BannerAdTarget;
import com.disney.ad.adexchange.campaign.domain.BannerAdTargetList;
import com.disney.ad.adexchange.campaign.domain.BannerAdTargetSearchRequest;
import com.disney.ad.adexchange.campaign.service.BannerAdTargetService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;

@RestController
public class BannerAdTargetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BannerAdTargetController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final BannerAdTargetService bannerAdTargetService = null;

    @RequestMapping(value = "/bannerAdTarget", method = RequestMethod.POST)
    public BannerAdTarget createBannerAdTarget(@RequestBody @Valid final BannerAdTarget bannerAdTarget) {
        LOGGER.debug("API: Create {}", bannerAdTarget);
        return bannerAdTargetService.insert(bannerAdTarget);
    }
    
    @RequestMapping(value = "/bannerAdTarget", method = RequestMethod.PUT)
    public BannerAdTarget updateBannerAdTarget(@RequestBody @Valid final BannerAdTarget bannerAdTarget) {
        LOGGER.debug("API: Update {}", bannerAdTarget);
        return bannerAdTargetService.update(bannerAdTarget);
    }        

    @RequestMapping(value = "/bannerAdTarget", method = RequestMethod.GET)
    public BannerAdTargetList listBannerAdTargets(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all bannerAdTarget");
        BannerAdTargetList bannerAdTargetList = bannerAdTargetService.getList(pageNo, pageSize);
        return bannerAdTargetList;
    }
    
    @RequestMapping(value = "/bannerAdTarget/search", method = RequestMethod.GET)
    public BannerAdTargetList searchBannerAdTarget(
			@RequestParam(required = false, value = "bannerID") String bannerID,
			@RequestParam(required = false, value = "campaignID") String campaignID,
			@RequestParam(required = false, value = "geo") String geo,
			@RequestParam(required = false, value = "state") String state,
			@RequestParam(required = false, value = "city") String city,
			@RequestParam(required = false, value = "ageGroup") String ageGroup,
			@RequestParam(required = false, value = "gender") String gender,
			@RequestParam(required = false, value = "category") String category,
			@RequestParam(required = false, value = "keyword") String keyword,
			@RequestParam(required = false, value = "status") Integer status,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search bannerAdTarget");
        BannerAdTargetSearchRequest bannerAdTargetSearchRequest = composeBannerAdTargetSearchRequest(
			bannerID,
			campaignID,
			geo,
			state,
			city,
			ageGroup,
			gender,
			category,
			keyword,
			status
        );
		        
        BannerAdTargetList bannerAdTargetList = bannerAdTargetService.search(bannerAdTargetSearchRequest, pageNo, pageSize);
        return bannerAdTargetList;
    }
    
    @RequestMapping(value = "/bannerAdTarget/search/keyword", method = RequestMethod.GET)
    public BannerAdTargetList searchBannerAdTargetByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search bannerAdTarget by keyword");
        BannerAdTargetList bannerAdTargetList = bannerAdTargetService.searchByKeyword(keyword, pageNo, pageSize);
        return bannerAdTargetList;
    }

    @RequestMapping(value = "/bannerAdTarget/{id}", method = RequestMethod.GET)
    public BannerAdTarget getBannerAdTarget(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list bannerAdTarget by Id");
        return bannerAdTargetService.getById(id);
    }    

    @RequestMapping(value = "/bannerAdTarget/{id}", method = RequestMethod.DELETE)
    public void deleteBannerAdTarget(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete bannerAdTarget by Id");
        bannerAdTargetService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public BannerAdTargetSearchRequest composeBannerAdTargetSearchRequest(
			String bannerID,
			String campaignID,
			String geo,
			String state,
			String city,
			String ageGroup,
			String gender,
			String category,
			String keyword,
			Integer status
) {
		BannerAdTargetSearchRequest bannerAdTargetSearchRequest = new BannerAdTargetSearchRequest();
        bannerAdTargetSearchRequest.setBannerID(bannerID);
        bannerAdTargetSearchRequest.setCampaignID(campaignID);
        bannerAdTargetSearchRequest.setGeo(geo);
        bannerAdTargetSearchRequest.setState(state);
        bannerAdTargetSearchRequest.setCity(city);
        bannerAdTargetSearchRequest.setAgeGroup(ageGroup);
        bannerAdTargetSearchRequest.setGender(gender);
        bannerAdTargetSearchRequest.setCategory(category);
        bannerAdTargetSearchRequest.setKeyword(keyword);
        bannerAdTargetSearchRequest.setStatus(status);
		return bannerAdTargetSearchRequest; 
	}    
}
