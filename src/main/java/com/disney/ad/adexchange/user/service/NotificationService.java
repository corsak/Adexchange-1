package com.disney.ad.adexchange.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.user.domain.Notification;
import com.disney.ad.adexchange.user.domain.NotificationList;
import com.disney.ad.adexchange.user.domain.NotificationSearchRequest;
import com.disney.ad.adexchange.user.repository.NotificationRepository;
import com.disney.ad.adexchange.user.data.NotificationSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    
    @Autowired
    private final NotificationRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public Notification insert(@NotNull @Valid final Notification notification) {
    	handleAppCurrentTimeOnInsert(notification);
        LOGGER.debug("Creating {}", notification);
        Notification existing = repository.findOne(notification.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", notification.getId()));
        }
        return update(notification);
    }
    
    @Transactional
    public Notification update(@NotNull @Valid final Notification notification) {
        LOGGER.debug("Updating {}", notification);
        Notification updatedNotification =  repository.save(notification);
        return updatedNotification;    	         
    }        

    @Transactional(readOnly = true)
    public NotificationList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public NotificationList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all notification");
        Page<Notification> notificationPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        NotificationList notificationList = convertPageToList(notificationPage);
        return notificationList;
    }

    @Transactional(readOnly = true)
    public NotificationList search(NotificationSearchRequest notificationSearchRequest, int pageNo, int pageSize) {
        return search(notificationSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public NotificationList search(NotificationSearchRequest notificationSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search notification");
        Page<Notification> notificationPage = repository.findAll(NotificationSpecification.searchAnd(notificationSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        NotificationList notificationList = convertPageToList(notificationPage); 
        return notificationList;
    }    

    @Transactional(readOnly = true)
    public NotificationList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public NotificationList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search notification by keyword");
        Page<Notification> notificationPage = repository.findAll(NotificationSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        NotificationList notificationList = convertPageToList(notificationPage); 
        return notificationList;
    }    
    @Transactional(readOnly = true)
    public Notification getById(Integer id) {
    	Notification existing = repository.findOne(id);
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
 
 	private NotificationList convertPageToList(Page<Notification> notificationPage) {
		long totalElements = notificationPage.getTotalElements();
		List<Notification> listOfNotification = notificationPage.getContent();
		NotificationList notificationList = new NotificationList(totalElements, listOfNotification);
		return notificationList;
	}
 
    private void handleAppCurrentTimeOnInsert(final Notification notification) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        notification.setCreatedTime(timestamp);
	}
	

}