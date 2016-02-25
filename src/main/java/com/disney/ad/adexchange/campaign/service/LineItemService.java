package com.disney.ad.adexchange.campaign.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.campaign.domain.LineItem;
import com.disney.ad.adexchange.campaign.domain.LineItemList;
import com.disney.ad.adexchange.campaign.domain.LineItemSearchRequest;
import com.disney.ad.adexchange.campaign.repository.LineItemRepository;
import com.disney.ad.adexchange.campaign.data.LineItemSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class LineItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LineItemService.class);
    
    @Autowired
    private final LineItemRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public LineItem insert(@NotNull @Valid final LineItem lineItem) {
    	handleAppCurrentTimeOnInsert(lineItem);
        LOGGER.debug("Creating {}", lineItem);
        LineItem existing = repository.findOne(lineItem.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", lineItem.getId()));
        }
        return update(lineItem);
    }
    
    @Transactional
    public LineItem update(@NotNull @Valid final LineItem lineItem) {
        LOGGER.debug("Updating {}", lineItem);
        LineItem updatedLineItem =  repository.save(lineItem);
        return updatedLineItem;    	         
    }        

    @Transactional(readOnly = true)
    public LineItemList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public LineItemList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all lineItem");
        Page<LineItem> lineItemPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        LineItemList lineItemList = convertPageToList(lineItemPage);
        return lineItemList;
    }

    @Transactional(readOnly = true)
    public LineItemList search(LineItemSearchRequest lineItemSearchRequest, int pageNo, int pageSize) {
        return search(lineItemSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public LineItemList search(LineItemSearchRequest lineItemSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search lineItem");
        Page<LineItem> lineItemPage = repository.findAll(LineItemSpecification.searchAnd(lineItemSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        LineItemList lineItemList = convertPageToList(lineItemPage); 
        return lineItemList;
    }    

    @Transactional(readOnly = true)
    public LineItemList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public LineItemList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search lineItem by keyword");
        Page<LineItem> lineItemPage = repository.findAll(LineItemSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        LineItemList lineItemList = convertPageToList(lineItemPage); 
        return lineItemList;
    }    
    @Transactional(readOnly = true)
    public LineItem getById(Integer id) {
    	LineItem existing = repository.findOne(id);
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
 
 	private LineItemList convertPageToList(Page<LineItem> lineItemPage) {
		long totalElements = lineItemPage.getTotalElements();
		List<LineItem> listOfLineItem = lineItemPage.getContent();
		LineItemList lineItemList = new LineItemList(totalElements, listOfLineItem);
		return lineItemList;
	}
 
    private void handleAppCurrentTimeOnInsert(final LineItem lineItem) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        lineItem.setCreatedTime(timestamp);
	}
	

}