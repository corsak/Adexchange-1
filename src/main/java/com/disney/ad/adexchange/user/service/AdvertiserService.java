package com.disney.ad.adexchange.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.user.domain.Advertiser;
import com.disney.ad.adexchange.user.domain.AdvertiserList;
import com.disney.ad.adexchange.user.domain.AdvertiserSearchRequest;
import com.disney.ad.adexchange.user.repository.AdvertiserRepository;
import com.disney.ad.adexchange.user.data.AdvertiserSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class AdvertiserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertiserService.class);
    
    @Autowired
    private final AdvertiserRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public Advertiser insert(@NotNull @Valid final Advertiser advertiser) {
    	handleAppCurrentTimeOnInsert(advertiser);
        LOGGER.debug("Creating {}", advertiser);
        Advertiser existing = repository.findOne(advertiser.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", advertiser.getId()));
        }
        return update(advertiser);
    }
    
    @Transactional
    public Advertiser update(@NotNull @Valid final Advertiser advertiser) {
        LOGGER.debug("Updating {}", advertiser);
        Advertiser updatedAdvertiser =  repository.save(advertiser);
        return updatedAdvertiser;    	         
    }        

    @Transactional(readOnly = true)
    public AdvertiserList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public AdvertiserList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all advertiser");
        Page<Advertiser> advertiserPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        AdvertiserList advertiserList = convertPageToList(advertiserPage);
        return advertiserList;
    }

    @Transactional(readOnly = true)
    public AdvertiserList search(AdvertiserSearchRequest advertiserSearchRequest, int pageNo, int pageSize) {
        return search(advertiserSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public AdvertiserList search(AdvertiserSearchRequest advertiserSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search advertiser");
        Page<Advertiser> advertiserPage = repository.findAll(AdvertiserSpecification.searchAnd(advertiserSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        AdvertiserList advertiserList = convertPageToList(advertiserPage); 
        return advertiserList;
    }    

    @Transactional(readOnly = true)
    public AdvertiserList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public AdvertiserList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search advertiser by keyword");
        Page<Advertiser> advertiserPage = repository.findAll(AdvertiserSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        AdvertiserList advertiserList = convertPageToList(advertiserPage); 
        return advertiserList;
    }    
    @Transactional(readOnly = true)
    public Advertiser getById(Integer id) {
    	Advertiser existing = repository.findOne(id);
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
 
 	private AdvertiserList convertPageToList(Page<Advertiser> advertiserPage) {
		long totalElements = advertiserPage.getTotalElements();
		List<Advertiser> listOfAdvertiser = advertiserPage.getContent();
		AdvertiserList advertiserList = new AdvertiserList(totalElements, listOfAdvertiser);
		return advertiserList;
	}
 
    private void handleAppCurrentTimeOnInsert(final Advertiser advertiser) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        advertiser.setCreatedTime(timestamp);
	}
	

}