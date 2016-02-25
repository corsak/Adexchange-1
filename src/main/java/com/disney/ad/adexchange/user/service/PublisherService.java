package com.disney.ad.adexchange.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.user.domain.Publisher;
import com.disney.ad.adexchange.user.domain.PublisherList;
import com.disney.ad.adexchange.user.domain.PublisherSearchRequest;
import com.disney.ad.adexchange.user.repository.PublisherRepository;
import com.disney.ad.adexchange.user.data.PublisherSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class PublisherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherService.class);
    
    @Autowired
    private final PublisherRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public Publisher insert(@NotNull @Valid final Publisher publisher) {
    	handleAppCurrentTimeOnInsert(publisher);
        LOGGER.debug("Creating {}", publisher);
        Publisher existing = repository.findOne(publisher.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", publisher.getId()));
        }
        return update(publisher);
    }
    
    @Transactional
    public Publisher update(@NotNull @Valid final Publisher publisher) {
        LOGGER.debug("Updating {}", publisher);
        Publisher updatedPublisher =  repository.save(publisher);
        return updatedPublisher;    	         
    }        

    @Transactional(readOnly = true)
    public PublisherList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public PublisherList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all publisher");
        Page<Publisher> publisherPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        PublisherList publisherList = convertPageToList(publisherPage);
        return publisherList;
    }

    @Transactional(readOnly = true)
    public PublisherList search(PublisherSearchRequest publisherSearchRequest, int pageNo, int pageSize) {
        return search(publisherSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public PublisherList search(PublisherSearchRequest publisherSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search publisher");
        Page<Publisher> publisherPage = repository.findAll(PublisherSpecification.searchAnd(publisherSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        PublisherList publisherList = convertPageToList(publisherPage); 
        return publisherList;
    }    

    @Transactional(readOnly = true)
    public PublisherList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public PublisherList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search publisher by keyword");
        Page<Publisher> publisherPage = repository.findAll(PublisherSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        PublisherList publisherList = convertPageToList(publisherPage); 
        return publisherList;
    }    
    @Transactional(readOnly = true)
    public Publisher getById(Integer id) {
    	Publisher existing = repository.findOne(id);
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
 
 	private PublisherList convertPageToList(Page<Publisher> publisherPage) {
		long totalElements = publisherPage.getTotalElements();
		List<Publisher> listOfPublisher = publisherPage.getContent();
		PublisherList publisherList = new PublisherList(totalElements, listOfPublisher);
		return publisherList;
	}
 
    private void handleAppCurrentTimeOnInsert(final Publisher publisher) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        publisher.setCreatedTime(timestamp);
	}
	

}