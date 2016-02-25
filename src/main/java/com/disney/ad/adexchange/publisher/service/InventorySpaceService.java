package com.disney.ad.adexchange.publisher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.publisher.domain.InventorySpace;
import com.disney.ad.adexchange.publisher.domain.InventorySpaceList;
import com.disney.ad.adexchange.publisher.domain.InventorySpaceSearchRequest;
import com.disney.ad.adexchange.publisher.repository.InventorySpaceRepository;
import com.disney.ad.adexchange.publisher.data.InventorySpaceSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class InventorySpaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventorySpaceService.class);
    
    @Autowired
    private final InventorySpaceRepository repository = null;

	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public InventorySpace insert(@NotNull @Valid final InventorySpace inventorySpace) {
        LOGGER.debug("Creating {}", inventorySpace);
        InventorySpace existing = repository.findOne(inventorySpace.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", inventorySpace.getId()));
        }
        return update(inventorySpace);
    }
    
    @Transactional
    public InventorySpace update(@NotNull @Valid final InventorySpace inventorySpace) {
        LOGGER.debug("Updating {}", inventorySpace);
        InventorySpace updatedInventorySpace =  repository.save(inventorySpace);
        return updatedInventorySpace;    	         
    }        

    @Transactional(readOnly = true)
    public InventorySpaceList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public InventorySpaceList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all inventorySpace");
        Page<InventorySpace> inventorySpacePage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        InventorySpaceList inventorySpaceList = convertPageToList(inventorySpacePage);
        return inventorySpaceList;
    }

    @Transactional(readOnly = true)
    public InventorySpaceList search(InventorySpaceSearchRequest inventorySpaceSearchRequest, int pageNo, int pageSize) {
        return search(inventorySpaceSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public InventorySpaceList search(InventorySpaceSearchRequest inventorySpaceSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search inventorySpace");
        Page<InventorySpace> inventorySpacePage = repository.findAll(InventorySpaceSpecification.searchAnd(inventorySpaceSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        InventorySpaceList inventorySpaceList = convertPageToList(inventorySpacePage); 
        return inventorySpaceList;
    }    

    @Transactional(readOnly = true)
    public InventorySpaceList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public InventorySpaceList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search inventorySpace by keyword");
        Page<InventorySpace> inventorySpacePage = repository.findAll(InventorySpaceSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        InventorySpaceList inventorySpaceList = convertPageToList(inventorySpacePage); 
        return inventorySpaceList;
    }    
    @Transactional(readOnly = true)
    public InventorySpace getById(Integer id) {
    	InventorySpace existing = repository.findOne(id);
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
 
 	private InventorySpaceList convertPageToList(Page<InventorySpace> inventorySpacePage) {
		long totalElements = inventorySpacePage.getTotalElements();
		List<InventorySpace> listOfInventorySpace = inventorySpacePage.getContent();
		InventorySpaceList inventorySpaceList = new InventorySpaceList(totalElements, listOfInventorySpace);
		return inventorySpaceList;
	}
 
	

}