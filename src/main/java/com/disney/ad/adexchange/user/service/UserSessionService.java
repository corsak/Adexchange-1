package com.disney.ad.adexchange.user.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.user.domain.User;
import com.disney.ad.adexchange.user.domain.UserSession;
import com.disney.ad.adexchange.user.domain.UserSessionList;
import com.disney.ad.adexchange.user.domain.UserSessionSearchRequest;
import com.disney.ad.adexchange.user.repository.AuthDaoImpl;
import com.disney.ad.adexchange.user.repository.UserSessionRepository;
import com.disney.ad.adexchange.user.data.UserSessionSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.Helper;
import com.disney.ad.adexchange.util.HibernateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;
import com.google.gson.Gson;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class UserSessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionService.class);
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Autowired
    private final UserSessionRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public UserSession insert(@NotNull @Valid final UserSession userSession) {
    	handleAppCurrentTimeOnInsert(userSession);
        LOGGER.debug("Creating {}", userSession);
        UserSession existing = repository.findOne(userSession.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", userSession.getId()));
        }
        return update(userSession);
    }
    
    @Transactional
    public UserSession update(@NotNull @Valid final UserSession userSession) {
        LOGGER.debug("Updating {}", userSession);
        UserSession updatedUserSession =  repository.save(userSession);
        return updatedUserSession;    	         
    }        

    @Transactional(readOnly = true)
    public UserSessionList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public UserSessionList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all userSession");
        Page<UserSession> userSessionPage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        UserSessionList userSessionList = convertPageToList(userSessionPage);
        return userSessionList;
    }

    @Transactional(readOnly = true)
    public UserSessionList search(UserSessionSearchRequest userSessionSearchRequest, int pageNo, int pageSize) {
        return search(userSessionSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public UserSessionList search(UserSessionSearchRequest userSessionSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search userSession");
        Page<UserSession> userSessionPage = repository.findAll(UserSessionSpecification.searchAnd(userSessionSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        UserSessionList userSessionList = convertPageToList(userSessionPage); 
        return userSessionList;
    }    

    @Transactional(readOnly = true)
    public UserSessionList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public UserSessionList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search userSession by keyword");
        Page<UserSession> userSessionPage = repository.findAll(UserSessionSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        UserSessionList userSessionList = convertPageToList(userSessionPage); 
        return userSessionList;
    }    
    @Transactional(readOnly = true)
    public UserSession getById(Integer id) {
    	UserSession existing = repository.findOne(id);
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
 
 	private UserSessionList convertPageToList(Page<UserSession> userSessionPage) {
		long totalElements = userSessionPage.getTotalElements();
		List<UserSession> listOfUserSession = userSessionPage.getContent();
		UserSessionList userSessionList = new UserSessionList(totalElements, listOfUserSession);
		return userSessionList;
	}
 
    private void handleAppCurrentTimeOnInsert(final UserSession userSession) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        userSession.setCreatedTime(timestamp);
	}
    
}