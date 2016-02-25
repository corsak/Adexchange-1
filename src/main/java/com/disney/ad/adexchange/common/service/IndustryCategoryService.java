package com.disney.ad.adexchange.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.common.domain.IndustryCategory;
import com.disney.ad.adexchange.common.domain.IndustryCategoryList;
import com.disney.ad.adexchange.common.domain.IndustryCategorySearchRequest;
import com.disney.ad.adexchange.common.repository.IndustryCategoryRepository;
import com.disney.ad.adexchange.common.data.IndustryCategorySpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class IndustryCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndustryCategoryService.class);
    
    @Autowired
    private final IndustryCategoryRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public IndustryCategory insert(@NotNull @Valid final IndustryCategory industryCategory) {
    	handleAppCurrentTimeOnInsert(industryCategory);
        LOGGER.debug("Creating {}", industryCategory);
        IndustryCategory existing = repository.findOne(industryCategory.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", industryCategory.getId()));
        }
        return update(industryCategory);
    }
    
    @Transactional
    public IndustryCategory update(@NotNull @Valid final IndustryCategory industryCategory) {
        LOGGER.debug("Updating {}", industryCategory);
        IndustryCategory updatedIndustryCategory =  repository.save(industryCategory);
        return updatedIndustryCategory;    	         
    }        

    @Transactional(readOnly = true)
    public IndustryCategoryList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public IndustryCategoryList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all industryCategory");
        Page<IndustryCategory> industryCategoryPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        IndustryCategoryList industryCategoryList = convertPageToList(industryCategoryPage);
        return industryCategoryList;
    }

    @Transactional(readOnly = true)
    public IndustryCategoryList search(IndustryCategorySearchRequest industryCategorySearchRequest, int pageNo, int pageSize) {
        return search(industryCategorySearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public IndustryCategoryList search(IndustryCategorySearchRequest industryCategorySearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search industryCategory");
        Page<IndustryCategory> industryCategoryPage = repository.findAll(IndustryCategorySpecification.searchAnd(industryCategorySearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        IndustryCategoryList industryCategoryList = convertPageToList(industryCategoryPage); 
        return industryCategoryList;
    }    

    @Transactional(readOnly = true)
    public IndustryCategoryList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public IndustryCategoryList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search industryCategory by keyword");
        Page<IndustryCategory> industryCategoryPage = repository.findAll(IndustryCategorySpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        IndustryCategoryList industryCategoryList = convertPageToList(industryCategoryPage); 
        return industryCategoryList;
    }    
    @Transactional(readOnly = true)
    public IndustryCategory getById(Integer id) {
    	IndustryCategory existing = repository.findOne(id);
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
 
 	private IndustryCategoryList convertPageToList(Page<IndustryCategory> industryCategoryPage) {
		long totalElements = industryCategoryPage.getTotalElements();
		List<IndustryCategory> listOfIndustryCategory = industryCategoryPage.getContent();
		IndustryCategoryList industryCategoryList = new IndustryCategoryList(totalElements, listOfIndustryCategory);
		return industryCategoryList;
	}
 
    private void handleAppCurrentTimeOnInsert(final IndustryCategory industryCategory) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        industryCategory.setCreatedTime(timestamp);
	}
	

}