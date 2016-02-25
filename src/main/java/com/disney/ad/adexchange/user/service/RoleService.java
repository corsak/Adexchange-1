package com.disney.ad.adexchange.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.disney.ad.adexchange.user.domain.Role;
import com.disney.ad.adexchange.user.domain.RoleList;
import com.disney.ad.adexchange.user.domain.RoleSearchRequest;
import com.disney.ad.adexchange.user.repository.RoleRepository;
import com.disney.ad.adexchange.user.data.RoleSpecification;
import com.disney.ad.adexchange.exception.ApiException;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.ServiceUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Service
@Validated
public class RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
    
    @Autowired
    private final RoleRepository repository = null;

	@Autowired
	private DateUtil dateUtil = null;
	
	@Autowired
	private ServiceUtil serviceUtil = null;	

    @Transactional
    public Role insert(@NotNull @Valid final Role role) {
    	handleAppCurrentTimeOnInsert(role);
        LOGGER.debug("Creating {}", role);
        Role existing = repository.findOne(role.getId());
        if (existing != null) {
            throw new ApiException(
                    String.format("Duplicate insert with id=%s", role.getId()));
        }
        return update(role);
    }
    
    @Transactional
    public Role update(@NotNull @Valid final Role role) {
        LOGGER.debug("Updating {}", role);
        Role updatedRole =  repository.save(role);
        return updatedRole;    	         
    }        

    @Transactional(readOnly = true)
    public RoleList getList(int pageNo, int pageSize) {
        return getList(pageNo, pageSize, null, null);
    }

    @Transactional(readOnly = true)
    public RoleList getList(int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Retrieving the list of all role");
        Page<Role> rolePage = repository.findAll(serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        RoleList roleList = convertPageToList(rolePage);
        return roleList;
    }

    @Transactional(readOnly = true)
    public RoleList search(RoleSearchRequest roleSearchRequest, int pageNo, int pageSize) {
        return search(roleSearchRequest, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public RoleList search(RoleSearchRequest roleSearchRequest, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search role");
        Page<Role> rolePage = repository.findAll(RoleSpecification.searchAnd(roleSearchRequest, true), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        RoleList roleList = convertPageToList(rolePage); 
        return roleList;
    }    

    @Transactional(readOnly = true)
    public RoleList searchByKeyword(String keyword, int pageNo, int pageSize) {
        return searchByKeyword(keyword, pageNo, pageSize, null, null);
    }  
    
    @Transactional(readOnly = true)
    public RoleList searchByKeyword(String keyword, int pageNo, int pageSize, String sortField, String sortBy) {
        LOGGER.debug("Search role by keyword");
        Page<Role> rolePage = repository.findAll(RoleSpecification.searchByKeyword(keyword), serviceUtil.composePageable(pageNo, pageSize, sortField, sortBy));
        RoleList roleList = convertPageToList(rolePage); 
        return roleList;
    }    
    @Transactional(readOnly = true)
    public Role getById(Integer id) {
    	Role existing = repository.findOne(id);
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
 
 	private RoleList convertPageToList(Page<Role> rolePage) {
		long totalElements = rolePage.getTotalElements();
		List<Role> listOfRole = rolePage.getContent();
		RoleList roleList = new RoleList(totalElements, listOfRole);
		return roleList;
	}
 
    private void handleAppCurrentTimeOnInsert(final Role role) {
 		Timestamp timestamp = dateUtil.determineCurrentTimestamp();
        role.setCreatedTime(timestamp);
	}
	

}