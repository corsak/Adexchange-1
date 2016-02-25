package com.disney.ad.adexchange.publisher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.publisher.domain.Inventory;
import com.disney.ad.adexchange.publisher.domain.InventoryList;
import com.disney.ad.adexchange.publisher.domain.InventorySearchRequest;
import com.disney.ad.adexchange.publisher.repository.InventoryRepository;
import com.disney.ad.adexchange.publisher.data.InventorySpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);
    
    @Autowired
    private final InventoryRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public Inventory insert(@NotNull @Valid final Inventory inventory) {
    	handleAppCurrentTimeOnInsert(inventory);
        LOGGER.debug("Creating {}", inventory);
        Inventory existing = repository.findOne(inventory.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", inventory.getId()));
        }
        return update(inventory);
    }
    
    @Transactional
    public Inventory update(@NotNull @Valid final Inventory inventory) {
        LOGGER.debug("Updating {}", inventory);
        Inventory updatedInventory =  repository.save(inventory);
        return updatedInventory;    	         
    }        

    @Transactional(readOnly = true)
    public InventoryList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public InventoryList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all inventory");
        Page<Inventory> inventoryPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        InventoryList inventoryList = convertPageToList(inventoryPage);
        return inventoryList;
    }

    @Transactional(readOnly = true)
    public InventoryList search(InventorySearchRequest inventorySearchRequest, int pageNo, int pageSize) {
        return search(inventorySearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public InventoryList search(InventorySearchRequest inventorySearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search inventory");
        Page<Inventory> inventoryPage = repository.findAll(InventorySpecification.searchAnd(inventorySearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        InventoryList inventoryList = convertPageToList(inventoryPage); 
        return inventoryList;
    }    

    @Transactional(readOnly = true)
    public InventoryList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public InventoryList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search inventory by keyword");
        Page<Inventory> inventoryPage = repository.findAll(InventorySpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        InventoryList inventoryList = convertPageToList(inventoryPage); 
        return inventoryList;
    }    
    @Transactional(readOnly = true)
    public Inventory getById(Integer id) {
    	Inventory existing = repository.findOne(id);
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
 
 	private InventoryList convertPageToList(Page<Inventory> inventoryPage) {
		long totalElements = inventoryPage.getTotalElements();
		List<Inventory> listOfInventory = inventoryPage.getContent();
		InventoryList inventoryList = new InventoryList(totalElements, listOfInventory);
		return inventoryList;
	}
 
    private void handleAppCurrentTimeOnInsert(final Inventory inventory) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        inventory.setCreatedTime(timestamp);
	}
	

}