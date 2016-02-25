package com.disney.ad.adexchange.analytics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.analytics.domain.HourlyImpressions;
import com.disney.ad.adexchange.analytics.domain.HourlyImpressionsList;
import com.disney.ad.adexchange.analytics.domain.HourlyImpressionsSearchRequest;
import com.disney.ad.adexchange.analytics.repository.HourlyImpressionsRepository;
import com.disney.ad.adexchange.analytics.data.HourlyImpressionsSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class HourlyImpressionsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HourlyImpressionsService.class);
    
    @Autowired
    private final HourlyImpressionsRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public HourlyImpressions insert(@NotNull @Valid final HourlyImpressions hourlyImpressions) {
    	handleAppCurrentTimeOnInsert(hourlyImpressions);
        LOGGER.debug("Creating {}", hourlyImpressions);
        HourlyImpressions existing = repository.findOne(hourlyImpressions.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", hourlyImpressions.getId()));
        }
        return update(hourlyImpressions);
    }
    
    @Transactional
    public HourlyImpressions update(@NotNull @Valid final HourlyImpressions hourlyImpressions) {
        LOGGER.debug("Updating {}", hourlyImpressions);
        HourlyImpressions updatedHourlyImpressions =  repository.save(hourlyImpressions);
        return updatedHourlyImpressions;    	         
    }        

    @Transactional(readOnly = true)
    public HourlyImpressionsList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public HourlyImpressionsList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all hourlyImpressions");
        Page<HourlyImpressions> hourlyImpressionsPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        HourlyImpressionsList hourlyImpressionsList = convertPageToList(hourlyImpressionsPage);
        return hourlyImpressionsList;
    }

    @Transactional(readOnly = true)
    public HourlyImpressionsList search(HourlyImpressionsSearchRequest hourlyImpressionsSearchRequest, int pageNo, int pageSize) {
        return search(hourlyImpressionsSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public HourlyImpressionsList search(HourlyImpressionsSearchRequest hourlyImpressionsSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search hourlyImpressions");
        Page<HourlyImpressions> hourlyImpressionsPage = repository.findAll(HourlyImpressionsSpecification.searchAnd(hourlyImpressionsSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        HourlyImpressionsList hourlyImpressionsList = convertPageToList(hourlyImpressionsPage); 
        return hourlyImpressionsList;
    }    

    @Transactional(readOnly = true)
    public HourlyImpressionsList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public HourlyImpressionsList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search hourlyImpressions by keyword");
        Page<HourlyImpressions> hourlyImpressionsPage = repository.findAll(HourlyImpressionsSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        HourlyImpressionsList hourlyImpressionsList = convertPageToList(hourlyImpressionsPage); 
        return hourlyImpressionsList;
    }    
    @Transactional(readOnly = true)
    public HourlyImpressions getById(Integer id) {
    	HourlyImpressions existing = repository.findOne(id);
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
 
 	private HourlyImpressionsList convertPageToList(Page<HourlyImpressions> hourlyImpressionsPage) {
		long totalElements = hourlyImpressionsPage.getTotalElements();
		List<HourlyImpressions> listOfHourlyImpressions = hourlyImpressionsPage.getContent();
		HourlyImpressionsList hourlyImpressionsList = new HourlyImpressionsList(totalElements, listOfHourlyImpressions);
		return hourlyImpressionsList;
	}
 
    private void handleAppCurrentTimeOnInsert(final HourlyImpressions hourlyImpressions) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        hourlyImpressions.setCreatedTime(timestamp);
	}
	

}