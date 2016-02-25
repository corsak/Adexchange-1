package com.disney.ad.adexchange.publisher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.publisher.domain.DigitalProperty;
import com.disney.ad.adexchange.publisher.domain.DigitalPropertyList;
import com.disney.ad.adexchange.publisher.domain.DigitalPropertySearchRequest;
import com.disney.ad.adexchange.publisher.repository.DigitalPropertyRepository;
import com.disney.ad.adexchange.publisher.data.DigitalPropertySpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class DigitalPropertyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DigitalPropertyService.class);
    
    @Autowired
    private final DigitalPropertyRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public DigitalProperty insert(@NotNull @Valid final DigitalProperty digitalProperty) {
    	handleAppCurrentTimeOnInsert(digitalProperty);
        LOGGER.debug("Creating {}", digitalProperty);
        DigitalProperty existing = repository.findOne(digitalProperty.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", digitalProperty.getId()));
        }
        return update(digitalProperty);
    }
    
    @Transactional
    public DigitalProperty update(@NotNull @Valid final DigitalProperty digitalProperty) {
        LOGGER.debug("Updating {}", digitalProperty);
        DigitalProperty updatedDigitalProperty =  repository.save(digitalProperty);
        return updatedDigitalProperty;    	         
    }        

    @Transactional(readOnly = true)
    public DigitalPropertyList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public DigitalPropertyList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all digitalProperty");
        Page<DigitalProperty> digitalPropertyPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        DigitalPropertyList digitalPropertyList = convertPageToList(digitalPropertyPage);
        return digitalPropertyList;
    }

    @Transactional(readOnly = true)
    public DigitalPropertyList search(DigitalPropertySearchRequest digitalPropertySearchRequest, int pageNo, int pageSize) {
        return search(digitalPropertySearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public DigitalPropertyList search(DigitalPropertySearchRequest digitalPropertySearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search digitalProperty");
        Page<DigitalProperty> digitalPropertyPage = repository.findAll(DigitalPropertySpecification.searchAnd(digitalPropertySearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        DigitalPropertyList digitalPropertyList = convertPageToList(digitalPropertyPage); 
        return digitalPropertyList;
    }    

    @Transactional(readOnly = true)
    public DigitalPropertyList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public DigitalPropertyList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search digitalProperty by keyword");
        Page<DigitalProperty> digitalPropertyPage = repository.findAll(DigitalPropertySpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        DigitalPropertyList digitalPropertyList = convertPageToList(digitalPropertyPage); 
        return digitalPropertyList;
    }    
    @Transactional(readOnly = true)
    public DigitalProperty getById(Integer id) {
    	DigitalProperty existing = repository.findOne(id);
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
 
 	private DigitalPropertyList convertPageToList(Page<DigitalProperty> digitalPropertyPage) {
		long totalElements = digitalPropertyPage.getTotalElements();
		List<DigitalProperty> listOfDigitalProperty = digitalPropertyPage.getContent();
		DigitalPropertyList digitalPropertyList = new DigitalPropertyList(totalElements, listOfDigitalProperty);
		return digitalPropertyList;
	}
 
    private void handleAppCurrentTimeOnInsert(final DigitalProperty digitalProperty) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        digitalProperty.setCreatedTime(timestamp);
	}
	

}