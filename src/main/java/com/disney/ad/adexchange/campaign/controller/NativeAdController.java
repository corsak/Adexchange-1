package com.disney.ad.adexchange.campaign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.campaign.domain.NativeAd;
import com.disney.ad.adexchange.campaign.domain.NativeAdList;
import com.disney.ad.adexchange.campaign.domain.NativeAdSearchRequest;
import com.disney.ad.adexchange.campaign.service.NativeAdService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;

@RestController
public class NativeAdController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NativeAdController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final NativeAdService nativeAdService = null;

    @RequestMapping(value = "/nativeAd", method = RequestMethod.POST)
    public NativeAd createNativeAd(@RequestBody @Valid final NativeAd nativeAd) {
        LOGGER.debug("API: Create {}", nativeAd);
        return nativeAdService.insert(nativeAd);
    }
    
    @RequestMapping(value = "/nativeAd", method = RequestMethod.PUT)
    public NativeAd updateNativeAd(@RequestBody @Valid final NativeAd nativeAd) {
        LOGGER.debug("API: Update {}", nativeAd);
        return nativeAdService.update(nativeAd);
    }        

    @RequestMapping(value = "/nativeAd", method = RequestMethod.GET)
    public NativeAdList listNativeAds(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all nativeAd");
        NativeAdList nativeAdList = nativeAdService.getList(pageNo, pageSize);
        return nativeAdList;
    }
    
    @RequestMapping(value = "/nativeAd/search", method = RequestMethod.GET)
    public NativeAdList searchNativeAd(
			@RequestParam(required = false, value = "title") String title,
			@RequestParam(required = false, value = "description") String description,
			@RequestParam(required = false, value = "highlightedText") String highlightedText,
			@RequestParam(required = false, value = "icon") String icon,
			@RequestParam(required = false, value = "button") String button,
			@RequestParam(required = false, value = "actionUrl") String actionUrl,
			@RequestParam(required = false, value = "price") Float price,
			@RequestParam(required = false, value = "rating") Integer rating,
			@RequestParam(required = false, value = "sponsoredText") String sponsoredText,
			@RequestParam(required = false, value = "sponsoredImageUrl") String sponsoredImageUrl,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search nativeAd");
        NativeAdSearchRequest nativeAdSearchRequest = composeNativeAdSearchRequest(
			title,
			description,
			highlightedText,
			icon,
			button,
			actionUrl,
			price,
			rating,
			sponsoredText,
			sponsoredImageUrl
        );
		        
        NativeAdList nativeAdList = nativeAdService.search(nativeAdSearchRequest, pageNo, pageSize);
        return nativeAdList;
    }
    
    @RequestMapping(value = "/nativeAd/search/keyword", method = RequestMethod.GET)
    public NativeAdList searchNativeAdByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search nativeAd by keyword");
        NativeAdList nativeAdList = nativeAdService.searchByKeyword(keyword, pageNo, pageSize);
        return nativeAdList;
    }

    @RequestMapping(value = "/nativeAd/{id}", method = RequestMethod.GET)
    public NativeAd getNativeAd(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list nativeAd by Id");
        return nativeAdService.getById(id);
    }    

    @RequestMapping(value = "/nativeAd/{id}", method = RequestMethod.DELETE)
    public void deleteNativeAd(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete nativeAd by Id");
        nativeAdService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public NativeAdSearchRequest composeNativeAdSearchRequest(
			String title,
			String description,
			String highlightedText,
			String icon,
			String button,
			String actionUrl,
			Float price,
			Integer rating,
			String sponsoredText,
			String sponsoredImageUrl
) {
		NativeAdSearchRequest nativeAdSearchRequest = new NativeAdSearchRequest();
        nativeAdSearchRequest.setTitle(title);
        nativeAdSearchRequest.setDescription(description);
        nativeAdSearchRequest.setHighlightedText(highlightedText);
        nativeAdSearchRequest.setIcon(icon);
        nativeAdSearchRequest.setButton(button);
        nativeAdSearchRequest.setActionUrl(actionUrl);
        nativeAdSearchRequest.setPrice(price);
        nativeAdSearchRequest.setRating(rating);
        nativeAdSearchRequest.setSponsoredText(sponsoredText);
        nativeAdSearchRequest.setSponsoredImageUrl(sponsoredImageUrl);
		return nativeAdSearchRequest; 
	}    
}
