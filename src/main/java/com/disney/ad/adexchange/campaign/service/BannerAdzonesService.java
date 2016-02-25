package com.disney.ad.adexchange.campaign.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.campaign.domain.BannerAdzones;
import com.disney.ad.adexchange.campaign.domain.BannerAdzonesList;
import com.disney.ad.adexchange.campaign.domain.BannerAdzonesSearchRequest;
import com.disney.ad.adexchange.campaign.repository.BannerAdzonesRepository;
import com.disney.ad.adexchange.campaign.data.BannerAdzonesSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class BannerAdzonesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BannerAdzonesService.class);
    
    @Autowired
    private final BannerAdzonesRepository repository = null;

	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public BannerAdzones insert(@NotNull @Valid final BannerAdzones bannerAdzones) {
        LOGGER.debug("Creating {}", bannerAdzones);
        BannerAdzones existing = repository.findOne(bannerAdzones.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", bannerAdzones.getId()));
        }
        return update(bannerAdzones);
    }
    
    @Transactional
    public BannerAdzones update(@NotNull @Valid final BannerAdzones bannerAdzones) {
        LOGGER.debug("Updating {}", bannerAdzones);
        BannerAdzones updatedBannerAdzones =  repository.save(bannerAdzones);
        return updatedBannerAdzones;    	         
    }        

    @Transactional(readOnly = true)
    public BannerAdzonesList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public BannerAdzonesList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all bannerAdzones");
        Page<BannerAdzones> bannerAdzonesPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        BannerAdzonesList bannerAdzonesList = convertPageToList(bannerAdzonesPage);
        return bannerAdzonesList;
    }

    @Transactional(readOnly = true)
    public BannerAdzonesList search(BannerAdzonesSearchRequest bannerAdzonesSearchRequest, int pageNo, int pageSize) {
        return search(bannerAdzonesSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public BannerAdzonesList search(BannerAdzonesSearchRequest bannerAdzonesSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search bannerAdzones");
        Page<BannerAdzones> bannerAdzonesPage = repository.findAll(BannerAdzonesSpecification.searchAnd(bannerAdzonesSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        BannerAdzonesList bannerAdzonesList = convertPageToList(bannerAdzonesPage); 
        return bannerAdzonesList;
    }    

    @Transactional(readOnly = true)
    public BannerAdzonesList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public BannerAdzonesList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search bannerAdzones by keyword");
        Page<BannerAdzones> bannerAdzonesPage = repository.findAll(BannerAdzonesSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        BannerAdzonesList bannerAdzonesList = convertPageToList(bannerAdzonesPage); 
        return bannerAdzonesList;
    }    
    @Transactional(readOnly = true)
    public BannerAdzones getById(Integer id) {
    	BannerAdzones existing = repository.findOne(id);
    	if (existing ==  null) {
    		throw new ApiException("Data does not exist");
    	}
        return existing;
    }
    
    @Transactional()
    public void deleteById(Integer id) {
    	getById(id); // to validate
        repository.delete(id);
    }        
 
 	private BannerAdzonesList convertPageToList(Page<BannerAdzones> bannerAdzonesPage) {
		long totalElements = bannerAdzonesPage.getTotalElements();
		List<BannerAdzones> listOfBannerAdzones = bannerAdzonesPage.getContent();
		BannerAdzonesList bannerAdzonesList = new BannerAdzonesList(totalElements, listOfBannerAdzones);
		return bannerAdzonesList;
	}
 
	

}