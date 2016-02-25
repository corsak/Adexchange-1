package com.disney.ad.adexchange.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.common.domain.AdTemplates;
import com.disney.ad.adexchange.common.domain.AdTemplatesList;
import com.disney.ad.adexchange.common.domain.AdTemplatesSearchRequest;
import com.disney.ad.adexchange.common.repository.AdTemplatesRepository;
import com.disney.ad.adexchange.common.data.AdTemplatesSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class AdTemplatesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdTemplatesService.class);
    
    @Autowired
    private final AdTemplatesRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public AdTemplates insert(@NotNull @Valid final AdTemplates adTemplates) {
    	handleAppCurrentTimeOnInsert(adTemplates);
        LOGGER.debug("Creating {}", adTemplates);
        AdTemplates existing = repository.findOne(adTemplates.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", adTemplates.getId()));
        }
        return update(adTemplates);
    }
    
    @Transactional
    public AdTemplates update(@NotNull @Valid final AdTemplates adTemplates) {
        LOGGER.debug("Updating {}", adTemplates);
        AdTemplates updatedAdTemplates =  repository.save(adTemplates);
        return updatedAdTemplates;    	         
    }        

    @Transactional(readOnly = true)
    public AdTemplatesList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public AdTemplatesList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all adTemplates");
        Page<AdTemplates> adTemplatesPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        AdTemplatesList adTemplatesList = convertPageToList(adTemplatesPage);
        return adTemplatesList;
    }

    @Transactional(readOnly = true)
    public AdTemplatesList search(AdTemplatesSearchRequest adTemplatesSearchRequest, int pageNo, int pageSize) {
        return search(adTemplatesSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public AdTemplatesList search(AdTemplatesSearchRequest adTemplatesSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search adTemplates");
        Page<AdTemplates> adTemplatesPage = repository.findAll(AdTemplatesSpecification.searchAnd(adTemplatesSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        AdTemplatesList adTemplatesList = convertPageToList(adTemplatesPage); 
        return adTemplatesList;
    }    

    @Transactional(readOnly = true)
    public AdTemplatesList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public AdTemplatesList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search adTemplates by keyword");
        Page<AdTemplates> adTemplatesPage = repository.findAll(AdTemplatesSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        AdTemplatesList adTemplatesList = convertPageToList(adTemplatesPage); 
        return adTemplatesList;
    }    
    @Transactional(readOnly = true)
    public AdTemplates getById(Integer id) {
    	AdTemplates existing = repository.findOne(id);
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
 
 	private AdTemplatesList convertPageToList(Page<AdTemplates> adTemplatesPage) {
		long totalElements = adTemplatesPage.getTotalElements();
		List<AdTemplates> listOfAdTemplates = adTemplatesPage.getContent();
		AdTemplatesList adTemplatesList = new AdTemplatesList(totalElements, listOfAdTemplates);
		return adTemplatesList;
	}
 
    private void handleAppCurrentTimeOnInsert(final AdTemplates adTemplates) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        adTemplates.setCreatedTime(timestamp);
	}
	

}