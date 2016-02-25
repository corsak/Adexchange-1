package com.disney.ad.adexchange.campaign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.campaign.domain.BannerAdzones;
import com.disney.ad.adexchange.campaign.domain.BannerAdzonesList;
import com.disney.ad.adexchange.campaign.domain.BannerAdzonesSearchRequest;
import com.disney.ad.adexchange.campaign.service.BannerAdzonesService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;

@RestController
public class BannerAdzonesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BannerAdzonesController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final BannerAdzonesService bannerAdzonesService = null;

    @RequestMapping(value = "/bannerAdzones", method = RequestMethod.POST)
    public BannerAdzones createBannerAdzones(@RequestBody @Valid final BannerAdzones bannerAdzones) {
        LOGGER.debug("API: Create {}", bannerAdzones);
        return bannerAdzonesService.insert(bannerAdzones);
    }
    
    @RequestMapping(value = "/bannerAdzones", method = RequestMethod.PUT)
    public BannerAdzones updateBannerAdzones(@RequestBody @Valid final BannerAdzones bannerAdzones) {
        LOGGER.debug("API: Update {}", bannerAdzones);
        return bannerAdzonesService.update(bannerAdzones);
    }        

    @RequestMapping(value = "/bannerAdzones", method = RequestMethod.GET)
    public BannerAdzonesList listBannerAdzoness(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all bannerAdzones");
        BannerAdzonesList bannerAdzonesList = bannerAdzonesService.getList(pageNo, pageSize);
        return bannerAdzonesList;
    }
    
    @RequestMapping(value = "/bannerAdzones/search", method = RequestMethod.GET)
    public BannerAdzonesList searchBannerAdzones(
			@RequestParam(required = false, value = "bannerID") String bannerID,
			@RequestParam(required = false, value = "zoneID") String zoneID,
			@RequestParam(required = false, value = "campaignID") String campaignID,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search bannerAdzones");
        BannerAdzonesSearchRequest bannerAdzonesSearchRequest = composeBannerAdzonesSearchRequest(
			bannerID,
			zoneID,
			campaignID
        );
		        
        BannerAdzonesList bannerAdzonesList = bannerAdzonesService.search(bannerAdzonesSearchRequest, pageNo, pageSize);
        return bannerAdzonesList;
    }
    
    @RequestMapping(value = "/bannerAdzones/search/keyword", method = RequestMethod.GET)
    public BannerAdzonesList searchBannerAdzonesByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search bannerAdzones by keyword");
        BannerAdzonesList bannerAdzonesList = bannerAdzonesService.searchByKeyword(keyword, pageNo, pageSize);
        return bannerAdzonesList;
    }

    @RequestMapping(value = "/bannerAdzones/{id}", method = RequestMethod.GET)
    public BannerAdzones getBannerAdzones(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list bannerAdzones by Id");
        return bannerAdzonesService.getById(id);
    }    

    @RequestMapping(value = "/bannerAdzones/{id}", method = RequestMethod.DELETE)
    public void deleteBannerAdzones(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete bannerAdzones by Id");
        bannerAdzonesService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public BannerAdzonesSearchRequest composeBannerAdzonesSearchRequest(
			String bannerID,
			String zoneID,
			String campaignID
) {
		BannerAdzonesSearchRequest bannerAdzonesSearchRequest = new BannerAdzonesSearchRequest();
        bannerAdzonesSearchRequest.setBannerID(bannerID);
        bannerAdzonesSearchRequest.setZoneID(zoneID);
        bannerAdzonesSearchRequest.setCampaignID(campaignID);
		return bannerAdzonesSearchRequest; 
	}    
}
