package com.disney.ad.adexchange.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.user.domain.UserSession;
import com.disney.ad.adexchange.user.domain.UserSessionList;
import com.disney.ad.adexchange.user.domain.UserSessionSearchRequest;
import com.disney.ad.adexchange.user.service.UserSessionService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class UserSessionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final UserSessionService userSessionService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/userSession", method = RequestMethod.POST)
    public UserSession createUserSession(@RequestBody @Valid final UserSession userSession) {
        LOGGER.debug("API: Create {}", userSession);
        return userSessionService.insert(userSession);
    }
    
    @RequestMapping(value = "/userSession", method = RequestMethod.PUT)
    public UserSession updateUserSession(@RequestBody @Valid final UserSession userSession) {
        LOGGER.debug("API: Update {}", userSession);
        return userSessionService.update(userSession);
    }        

    @RequestMapping(value = "/userSession", method = RequestMethod.GET)
    public UserSessionList listUserSessions(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all userSession");
        UserSessionList userSessionList = userSessionService.getList(pageNo, pageSize);
        return userSessionList;
    }
    
    @RequestMapping(value = "/userSession/search", method = RequestMethod.GET)
    public UserSessionList searchUserSession(
			@RequestParam(required = false, value = "userId") String userId,
			@RequestParam(required = false, value = "userSessionId") String userSessionId,
			@RequestParam(required = false, value = "authToken") String authToken,
			@RequestParam(required = false, value = "lastAccessTimeStart") String lastAccessTimeStart,
			@RequestParam(required = false, value = "lastAccessTimeEnd") String lastAccessTimeEnd,			
			@RequestParam(required = false, value = "lastLoggedInTimeStart") String lastLoggedInTimeStart,
			@RequestParam(required = false, value = "lastLoggedInTimeEnd") String lastLoggedInTimeEnd,			
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search userSession");
        UserSessionSearchRequest userSessionSearchRequest = composeUserSessionSearchRequest(
			userId,
			userSessionId,
			authToken,
			lastAccessTimeStart,
			lastAccessTimeEnd,			
			lastLoggedInTimeStart,
			lastLoggedInTimeEnd,			
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        UserSessionList userSessionList = userSessionService.search(userSessionSearchRequest, pageNo, pageSize);
        return userSessionList;
    }
    
    @RequestMapping(value = "/userSession/search/keyword", method = RequestMethod.GET)
    public UserSessionList searchUserSessionByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search userSession by keyword");
        UserSessionList userSessionList = userSessionService.searchByKeyword(keyword, pageNo, pageSize);
        return userSessionList;
    }

    @RequestMapping(value = "/userSession/{id}", method = RequestMethod.GET)
    public UserSession getUserSession(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list userSession by Id");
        return userSessionService.getById(id);
    }    

    @RequestMapping(value = "/userSession/{id}", method = RequestMethod.DELETE)
    public void deleteUserSession(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete userSession by Id");
        userSessionService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public UserSessionSearchRequest composeUserSessionSearchRequest(
			String userId,
			String userSessionId,
			String authToken,
			String lastAccessTimeStart,
			String lastAccessTimeEnd,			
			String lastLoggedInTimeStart,
			String lastLoggedInTimeEnd,			
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		UserSessionSearchRequest userSessionSearchRequest = new UserSessionSearchRequest();
        userSessionSearchRequest.setUserId(userId);
        userSessionSearchRequest.setUserSessionId(userSessionId);
        userSessionSearchRequest.setAuthToken(authToken);
		if (lastAccessTimeStart != null) {
			Timestamp lastAccessTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(lastAccessTimeStart);
        	userSessionSearchRequest.setLastAccessTimeStart(lastAccessTimeStartTimestamp);
        }
        if (lastAccessTimeEnd != null) {
        	Timestamp lastAccessTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(lastAccessTimeEnd);
        	userSessionSearchRequest.setLastAccessTimeEnd(lastAccessTimeEndTimestamp);
        }
        
		if (lastLoggedInTimeStart != null) {
			Timestamp lastLoggedInTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(lastLoggedInTimeStart);
        	userSessionSearchRequest.setLastLoggedInTimeStart(lastLoggedInTimeStartTimestamp);
        }
        if (lastLoggedInTimeEnd != null) {
        	Timestamp lastLoggedInTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(lastLoggedInTimeEnd);
        	userSessionSearchRequest.setLastLoggedInTimeEnd(lastLoggedInTimeEndTimestamp);
        }
        
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	userSessionSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	userSessionSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	userSessionSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	userSessionSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        userSessionSearchRequest.setCreatedByUser(createdByUser);
        userSessionSearchRequest.setUpdatedByUser(updatedByUser);
		return userSessionSearchRequest; 
	}    
}
