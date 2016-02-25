package com.disney.ad.adexchange.analytics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.analytics.domain.DailyImpressions;
import com.disney.ad.adexchange.analytics.domain.DailyImpressionsList;
import com.disney.ad.adexchange.analytics.domain.DailyImpressionsSearchRequest;
import com.disney.ad.adexchange.analytics.repository.DailyImpressionsRepository;
import com.disney.ad.adexchange.analytics.data.DailyImpressionsSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class DailyImpressionsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DailyImpressionsService.class);
    
    @Autowired
    private final DailyImpressionsRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public DailyImpressions insert(@NotNull @Valid final DailyImpressions dailyImpressions) {
    	handleAppCurrentTimeOnInsert(dailyImpressions);
        LOGGER.debug("Creating {}", dailyImpressions);
        DailyImpressions existing = repository.findOne(dailyImpressions.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", dailyImpressions.getId()));
        }
        return update(dailyImpressions);
    }
    
    @Transactional
    public DailyImpressions update(@NotNull @Valid final DailyImpressions dailyImpressions) {
        LOGGER.debug("Updating {}", dailyImpressions);
        DailyImpressions updatedDailyImpressions =  repository.save(dailyImpressions);
        return updatedDailyImpressions;    	         
    }        

    @Transactional(readOnly = true)
    public DailyImpressionsList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public DailyImpressionsList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all dailyImpressions");
        Page<DailyImpressions> dailyImpressionsPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        DailyImpressionsList dailyImpressionsList = convertPageToList(dailyImpressionsPage);
        return dailyImpressionsList;
    }

    @Transactional(readOnly = true)
    public DailyImpressionsList search(DailyImpressionsSearchRequest dailyImpressionsSearchRequest, int pageNo, int pageSize) {
        return search(dailyImpressionsSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public DailyImpressionsList search(DailyImpressionsSearchRequest dailyImpressionsSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search dailyImpressions");
        Page<DailyImpressions> dailyImpressionsPage = repository.findAll(DailyImpressionsSpecification.searchAnd(dailyImpressionsSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        DailyImpressionsList dailyImpressionsList = convertPageToList(dailyImpressionsPage); 
        return dailyImpressionsList;
    }    

    @Transactional(readOnly = true)
    public DailyImpressionsList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public DailyImpressionsList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search dailyImpressions by keyword");
        Page<DailyImpressions> dailyImpressionsPage = repository.findAll(DailyImpressionsSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        DailyImpressionsList dailyImpressionsList = convertPageToList(dailyImpressionsPage); 
        return dailyImpressionsList;
    }    
    @Transactional(readOnly = true)
    public DailyImpressions getById(Integer id) {
    	DailyImpressions existing = repository.findOne(id);
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
 
 	private DailyImpressionsList convertPageToList(Page<DailyImpressions> dailyImpressionsPage) {
		long totalElements = dailyImpressionsPage.getTotalElements();
		List<DailyImpressions> listOfDailyImpressions = dailyImpressionsPage.getContent();
		DailyImpressionsList dailyImpressionsList = new DailyImpressionsList(totalElements, listOfDailyImpressions);
		return dailyImpressionsList;
	}
 
    private void handleAppCurrentTimeOnInsert(final DailyImpressions dailyImpressions) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        dailyImpressions.setCreatedTime(timestamp);
	}
	

}