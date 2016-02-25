package com.disney.ad.adexchange.campaign.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.campaign.domain.NativeAd;
import com.disney.ad.adexchange.campaign.domain.NativeAdList;
import com.disney.ad.adexchange.campaign.domain.NativeAdSearchRequest;
import com.disney.ad.adexchange.campaign.repository.NativeAdRepository;
import com.disney.ad.adexchange.campaign.data.NativeAdSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class NativeAdService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NativeAdService.class);
    
    @Autowired
    private final NativeAdRepository repository = null;

	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public NativeAd insert(@NotNull @Valid final NativeAd nativeAd) {
        LOGGER.debug("Creating {}", nativeAd);
        NativeAd existing = repository.findOne(nativeAd.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", nativeAd.getId()));
        }
        return update(nativeAd);
    }
    
    @Transactional
    public NativeAd update(@NotNull @Valid final NativeAd nativeAd) {
        LOGGER.debug("Updating {}", nativeAd);
        NativeAd updatedNativeAd =  repository.save(nativeAd);
        return updatedNativeAd;    	         
    }        

    @Transactional(readOnly = true)
    public NativeAdList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public NativeAdList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all nativeAd");
        Page<NativeAd> nativeAdPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        NativeAdList nativeAdList = convertPageToList(nativeAdPage);
        return nativeAdList;
    }

    @Transactional(readOnly = true)
    public NativeAdList search(NativeAdSearchRequest nativeAdSearchRequest, int pageNo, int pageSize) {
        return search(nativeAdSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public NativeAdList search(NativeAdSearchRequest nativeAdSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search nativeAd");
        Page<NativeAd> nativeAdPage = repository.findAll(NativeAdSpecification.searchAnd(nativeAdSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        NativeAdList nativeAdList = convertPageToList(nativeAdPage); 
        return nativeAdList;
    }    

    @Transactional(readOnly = true)
    public NativeAdList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public NativeAdList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search nativeAd by keyword");
        Page<NativeAd> nativeAdPage = repository.findAll(NativeAdSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        NativeAdList nativeAdList = convertPageToList(nativeAdPage); 
        return nativeAdList;
    }    
    @Transactional(readOnly = true)
    public NativeAd getById(Integer id) {
    	NativeAd existing = repository.findOne(id);
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
 
 	private NativeAdList convertPageToList(Page<NativeAd> nativeAdPage) {
		long totalElements = nativeAdPage.getTotalElements();
		List<NativeAd> listOfNativeAd = nativeAdPage.getContent();
		NativeAdList nativeAdList = new NativeAdList(totalElements, listOfNativeAd);
		return nativeAdList;
	}
 
	

}