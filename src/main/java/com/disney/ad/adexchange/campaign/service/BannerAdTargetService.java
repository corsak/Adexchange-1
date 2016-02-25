package com.disney.ad.adexchange.campaign.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.campaign.domain.BannerAdTarget;
import com.disney.ad.adexchange.campaign.domain.BannerAdTargetList;
import com.disney.ad.adexchange.campaign.domain.BannerAdTargetSearchRequest;
import com.disney.ad.adexchange.campaign.repository.BannerAdTargetRepository;
import com.disney.ad.adexchange.campaign.data.BannerAdTargetSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class BannerAdTargetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BannerAdTargetService.class);
    
    @Autowired
    private final BannerAdTargetRepository repository = null;

	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public BannerAdTarget insert(@NotNull @Valid final BannerAdTarget bannerAdTarget) {
        LOGGER.debug("Creating {}", bannerAdTarget);
        BannerAdTarget existing = repository.findOne(bannerAdTarget.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", bannerAdTarget.getId()));
        }
        return update(bannerAdTarget);
    }
    
    @Transactional
    public BannerAdTarget update(@NotNull @Valid final BannerAdTarget bannerAdTarget) {
        LOGGER.debug("Updating {}", bannerAdTarget);
        BannerAdTarget updatedBannerAdTarget =  repository.save(bannerAdTarget);
        return updatedBannerAdTarget;    	         
    }        

    @Transactional(readOnly = true)
    public BannerAdTargetList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public BannerAdTargetList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all bannerAdTarget");
        Page<BannerAdTarget> bannerAdTargetPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        BannerAdTargetList bannerAdTargetList = convertPageToList(bannerAdTargetPage);
        return bannerAdTargetList;
    }

    @Transactional(readOnly = true)
    public BannerAdTargetList search(BannerAdTargetSearchRequest bannerAdTargetSearchRequest, int pageNo, int pageSize) {
        return search(bannerAdTargetSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public BannerAdTargetList search(BannerAdTargetSearchRequest bannerAdTargetSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search bannerAdTarget");
        Page<BannerAdTarget> bannerAdTargetPage = repository.findAll(BannerAdTargetSpecification.searchAnd(bannerAdTargetSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        BannerAdTargetList bannerAdTargetList = convertPageToList(bannerAdTargetPage); 
        return bannerAdTargetList;
    }    

    @Transactional(readOnly = true)
    public BannerAdTargetList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public BannerAdTargetList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search bannerAdTarget by keyword");
        Page<BannerAdTarget> bannerAdTargetPage = repository.findAll(BannerAdTargetSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        BannerAdTargetList bannerAdTargetList = convertPageToList(bannerAdTargetPage); 
        return bannerAdTargetList;
    }    
    @Transactional(readOnly = true)
    public BannerAdTarget getById(Integer id) {
    	BannerAdTarget existing = repository.findOne(id);
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
 
 	private BannerAdTargetList convertPageToList(Page<BannerAdTarget> bannerAdTargetPage) {
		long totalElements = bannerAdTargetPage.getTotalElements();
		List<BannerAdTarget> listOfBannerAdTarget = bannerAdTargetPage.getContent();
		BannerAdTargetList bannerAdTargetList = new BannerAdTargetList(totalElements, listOfBannerAdTarget);
		return bannerAdTargetList;
	}
 
	

}