package com.disney.ad.adexchange.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.user.domain.Notification;
import com.disney.ad.adexchange.user.domain.NotificationList;
import com.disney.ad.adexchange.user.domain.NotificationSearchRequest;
import com.disney.ad.adexchange.user.service.NotificationService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class NotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final NotificationService notificationService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/notification", method = RequestMethod.POST)
    public Notification createNotification(@RequestBody @Valid final Notification notification) {
        LOGGER.debug("API: Create {}", notification);
        return notificationService.insert(notification);
    }
    
    @RequestMapping(value = "/notification", method = RequestMethod.PUT)
    public Notification updateNotification(@RequestBody @Valid final Notification notification) {
        LOGGER.debug("API: Update {}", notification);
        return notificationService.update(notification);
    }        

    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    public NotificationList listNotifications(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all notification");
        NotificationList notificationList = notificationService.getList(pageNo, pageSize);
        return notificationList;
    }
    
    @RequestMapping(value = "/notification/search", method = RequestMethod.GET)
    public NotificationList searchNotification(
			@RequestParam(required = false, value = "notificationId") String notificationId,
			@RequestParam(required = false, value = "userId") String userId,
			@RequestParam(required = false, value = "deviceOS") String deviceOS,
			@RequestParam(required = false, value = "source") String source,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search notification");
        NotificationSearchRequest notificationSearchRequest = composeNotificationSearchRequest(
			notificationId,
			userId,
			deviceOS,
			source,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        NotificationList notificationList = notificationService.search(notificationSearchRequest, pageNo, pageSize);
        return notificationList;
    }
    
    @RequestMapping(value = "/notification/search/keyword", method = RequestMethod.GET)
    public NotificationList searchNotificationByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search notification by keyword");
        NotificationList notificationList = notificationService.searchByKeyword(keyword, pageNo, pageSize);
        return notificationList;
    }

    @RequestMapping(value = "/notification/{id}", method = RequestMethod.GET)
    public Notification getNotification(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list notification by Id");
        return notificationService.getById(id);
    }    

    @RequestMapping(value = "/notification/{id}", method = RequestMethod.DELETE)
    public void deleteNotification(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete notification by Id");
        notificationService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public NotificationSearchRequest composeNotificationSearchRequest(
			String notificationId,
			String userId,
			String deviceOS,
			String source,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		NotificationSearchRequest notificationSearchRequest = new NotificationSearchRequest();
        notificationSearchRequest.setNotificationId(notificationId);
        notificationSearchRequest.setUserId(userId);
        notificationSearchRequest.setDeviceOS(deviceOS);
        notificationSearchRequest.setSource(source);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	notificationSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	notificationSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	notificationSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	notificationSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        notificationSearchRequest.setCreatedByUser(createdByUser);
        notificationSearchRequest.setUpdatedByUser(updatedByUser);
		return notificationSearchRequest; 
	}    
}
