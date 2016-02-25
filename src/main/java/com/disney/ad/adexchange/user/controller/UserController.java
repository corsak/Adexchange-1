package com.disney.ad.adexchange.user.controller;

import java.sql.Timestamp;

import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.user.domain.User;
import com.disney.ad.adexchange.user.domain.UserList;
import com.disney.ad.adexchange.user.domain.UserSearchRequest;
import com.disney.ad.adexchange.user.service.UserService;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.HibernateUtil;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final UserService userService = null;
    
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody @Valid final User user) {
        LOGGER.debug("API: Create {}", user);
        return userService.insert(user);
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public User updateUser(@RequestBody @Valid final User user) {
        LOGGER.debug("API: Update {}", user);
        return userService.update(user);
    }        

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserList listUsers(@RequestParam(required = false, value = "status") Integer status,
    		@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all user");
        UserSearchRequest userSearchRequest = composeUserStatusRequest(
    			status  );
        UserList userList = userService.getList(userSearchRequest,pageNo, pageSize);
        return userList;
    }
    
    @RequestMapping(value = "/user/search", method = RequestMethod.GET)
    public UserList searchUser(
			@RequestParam(required = false, value = "email") String email,
			@RequestParam(required = false, value = "loginName") String loginName,
			@RequestParam(required = false, value = "password") String password,
			@RequestParam(required = false, value = "status") Integer status,
			@RequestParam(required = false, value = "userType") Integer userType,
			@RequestParam(required = false, value = "hintQuestion1") String hintQuestion1,
			@RequestParam(required = false, value = "hintAnswer1") String hintAnswer1,
			@RequestParam(required = false, value = "hintQuestion2") String hintQuestion2,
			@RequestParam(required = false, value = "hintAnswer2") String hintAnswer2,
			@RequestParam(required = false, value = "isAdmin") Boolean isAdmin,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(required = false, value = "advertiserId") String advertiserId,
			@RequestParam(required = false, value = "publisherId") String publisherId,
			@RequestParam(required = false, value = "roleId") String roleId,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search user");
        UserSearchRequest userSearchRequest = composeUserSearchRequest(
			email,
			loginName,
			password,
			status,
			userType,
			hintQuestion1,
			hintAnswer1,
			hintQuestion2,
			hintAnswer2,
			isAdmin,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser,
			advertiserId,
			publisherId,
			roleId        );
		        
        UserList userList = userService.search(userSearchRequest, pageNo, pageSize);
        return userList;
    }
    
    @RequestMapping(value = "/user/search/keyword", method = RequestMethod.GET)
    public UserList searchUserByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search user by keyword");
        UserList userList = userService.searchByKeyword(keyword, pageNo, pageSize);
        return userList;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list user by Id");
        return userService.getById(id);
    }    

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete user by Id");
        userService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public UserSearchRequest composeUserSearchRequest(
			String email,
			String loginName,
			String password,
			Integer status,
			Integer userType,
			String hintQuestion1,
			String hintAnswer1,
			String hintQuestion2,
			String hintAnswer2,
			Boolean isAdmin,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser,
			String advertiserId,
			String publisherId,
			String roleId) {
		UserSearchRequest userSearchRequest = new UserSearchRequest();
        userSearchRequest.setEmail(email);
        userSearchRequest.setLoginName(loginName);
        userSearchRequest.setPassword(password);
        userSearchRequest.setStatus(status);
        userSearchRequest.setUserType(userType);
        userSearchRequest.setHintQuestion1(hintQuestion1);
        userSearchRequest.setHintAnswer1(hintAnswer1);
        userSearchRequest.setHintQuestion2(hintQuestion2);
        userSearchRequest.setHintAnswer2(hintAnswer2);
        userSearchRequest.setIsAdmin(isAdmin);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	userSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	userSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	userSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	userSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        userSearchRequest.setCreatedByUser(createdByUser);
        userSearchRequest.setUpdatedByUser(updatedByUser);
		userSearchRequest.setAdvertiserid(advertiserId);
		userSearchRequest.setPublisherid(publisherId);
		userSearchRequest.setRoleid(roleId);
		return userSearchRequest; 
	}
	
	public UserSearchRequest composeUserStatusRequest(Integer status){
		UserSearchRequest userSearchRequest = new UserSearchRequest();
		userSearchRequest.setStatus(status);
		return userSearchRequest;
		
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
	public User updateVideo(@PathVariable("userId") int userId, @RequestBody User userId1) {
	
		User user = null;
		
		try {
			user = userService.getById(userId);
			System.out.println(user);
			user.setStatus(userId1.getStatus());
			user = userService.userStatusUpdate(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
