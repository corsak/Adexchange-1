package com.disney.ad.adexchange.campaign.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.campaign.domain.InsertionOrder;
import com.disney.ad.adexchange.campaign.domain.InsertionOrderList;
import com.disney.ad.adexchange.campaign.domain.InsertionOrderSearchRequest;
import com.disney.ad.adexchange.campaign.repository.InsertionOrderRepository;
import com.disney.ad.adexchange.campaign.data.InsertionOrderSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class InsertionOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertionOrderService.class);
    
    @Autowired
    private final InsertionOrderRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public InsertionOrder insert(@NotNull @Valid final InsertionOrder insertionOrder) {
    	handleAppCurrentTimeOnInsert(insertionOrder);
        LOGGER.debug("Creating {}", insertionOrder);
        InsertionOrder existing = repository.findOne(insertionOrder.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", insertionOrder.getId()));
        }
        return update(insertionOrder);
    }
    
    @Transactional
    public InsertionOrder update(@NotNull @Valid final InsertionOrder insertionOrder) {
        LOGGER.debug("Updating {}", insertionOrder);
        InsertionOrder updatedInsertionOrder =  repository.save(insertionOrder);
        return updatedInsertionOrder;    	         
    }        

    @Transactional(readOnly = true)
    public InsertionOrderList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public InsertionOrderList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all insertionOrder");
        Page<InsertionOrder> insertionOrderPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        InsertionOrderList insertionOrderList = convertPageToList(insertionOrderPage);
        return insertionOrderList;
    }

    @Transactional(readOnly = true)
    public InsertionOrderList search(InsertionOrderSearchRequest insertionOrderSearchRequest, int pageNo, int pageSize) {
        return search(insertionOrderSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public InsertionOrderList search(InsertionOrderSearchRequest insertionOrderSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search insertionOrder");
        Page<InsertionOrder> insertionOrderPage = repository.findAll(InsertionOrderSpecification.searchAnd(insertionOrderSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        InsertionOrderList insertionOrderList = convertPageToList(insertionOrderPage); 
        return insertionOrderList;
    }    

    @Transactional(readOnly = true)
    public InsertionOrderList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public InsertionOrderList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search insertionOrder by keyword");
        Page<InsertionOrder> insertionOrderPage = repository.findAll(InsertionOrderSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        InsertionOrderList insertionOrderList = convertPageToList(insertionOrderPage); 
        return insertionOrderList;
    }    
    @Transactional(readOnly = true)
    public InsertionOrder getById(Integer id) {
    	InsertionOrder existing = repository.findOne(id);
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
 
 	private InsertionOrderList convertPageToList(Page<InsertionOrder> insertionOrderPage) {
		long totalElements = insertionOrderPage.getTotalElements();
		List<InsertionOrder> listOfInsertionOrder = insertionOrderPage.getContent();
		InsertionOrderList insertionOrderList = new InsertionOrderList(totalElements, listOfInsertionOrder);
		return insertionOrderList;
	}
 
    private void handleAppCurrentTimeOnInsert(final InsertionOrder insertionOrder) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        insertionOrder.setCreatedTime(timestamp);
	}
	

}