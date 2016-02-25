package com.disney.ad.adexchange.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.user.domain.User;
import com.disney.ad.adexchange.user.domain.UserList;
import com.disney.ad.adexchange.user.domain.UserSearchRequest;
import com.disney.ad.adexchange.user.repository.UserRepository;
import com.disney.ad.adexchange.user.data.UserSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;
import com.disney.ad.adexchange.util.UserConstant;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private final UserRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public User insert(@NotNull @Valid final User user) {
    	handleAppCurrentTimeOnInsert(user);
        LOGGER.debug("Creating {}", user);
        User existing = repository.findOne(user.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", user.getId()));
        }
        return update(user);
    }
    
    @Transactional
    public User update(@NotNull @Valid final User user) {
        LOGGER.debug("Updating {}", user);
        User updatedUser =  repository.save(user);
    	maskPassword(updatedUser);
        return updatedUser;    	         
    }
    
    @Transactional
    public User userStatusUpdate(@NotNull @Valid final User user) {
        LOGGER.debug("Updating {}", user);
        User updatedUser =  repository.save(user);
        return updatedUser;    	         
    }  

    @Transactional(readOnly = true)
    public UserList getList(UserSearchRequest userSearchRequest,int pageNo, int pageSize) {
        return getList(userSearchRequest, pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public UserList getList(UserSearchRequest userSearchRequest,int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all user");
        Page<User> userPage = repository.findAll(UserSpecification.getList(userSearchRequest, true),serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        UserList userList = convertPageToList(userPage);
    	maskPassword(userList.getUsers());
        return userList;
    }

    @Transactional(readOnly = true)
    public UserList search(UserSearchRequest userSearchRequest, int pageNo, int pageSize) {
        return search(userSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public UserList search(UserSearchRequest userSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search user");
        Page<User> userPage = repository.findAll(UserSpecification.searchAnd(userSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        UserList userList = convertPageToList(userPage); 
        return userList;
    }    

    @Transactional(readOnly = true)
    public UserList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public UserList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search user by keyword");
        Page<User> userPage = repository.findAll(UserSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        UserList userList = convertPageToList(userPage); 
        return userList;
    }    
    @Transactional(readOnly = true)
    public User getById(Integer id) {
    	User existing = repository.findOne(id);
    	if (existing ==  null) {
    		throw new ApiException("Data does not exist");
    	}
    	//maskPassword(existing);
        return existing;
    }
    
    @Transactional()
    public void deleteById(Integer id) {
    	getById(id); // to validate
        repository.delete(id);
    }        
 
 	private UserList convertPageToList(Page<User> userPage) {
		long totalElements = userPage.getTotalElements();
		List<User> listOfUser = userPage.getContent();
		UserList userList = new UserList(totalElements, listOfUser);
		return userList;
	}
 
    private void handleAppCurrentTimeOnInsert(final User user) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        user.setCreatedTime(timestamp);
	}
	
    private void maskPassword(final List<User> userList) {
    	for(User user : userList) {
    		maskPassword(user);
    	}
    }   
    
    private void maskPassword(final User user) {
        String password = user.getPassword();
        System.out.println(password);
        System.out.println(getHash(password));
    	user.setPassword(getHash(password));
	}
    
    //hashing the password
    public String getHash(String password) {
		byte[] salt = UserConstant.DEFAULTSALT.getBytes();
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		byte[] hash = null;
		SecretKeyFactory f;
		try {
			f = SecretKeyFactory.getInstance(UserConstant.SECRETKEYALGORITHM);
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.debug("Exception in getHash" + e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOGGER.debug("Exception in getHash" + e.getMessage());
		}
		return new BigInteger(1, hash).toString(16);
	}

}