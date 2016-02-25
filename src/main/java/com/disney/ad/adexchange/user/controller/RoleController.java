package com.disney.ad.adexchange.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.disney.ad.adexchange.user.domain.Role;
import com.disney.ad.adexchange.user.domain.RoleList;
import com.disney.ad.adexchange.user.domain.RoleSearchRequest;
import com.disney.ad.adexchange.user.service.RoleService;
import com.disney.ad.adexchange.exception.ApiException;

import javax.validation.Valid;
import java.sql.Timestamp;
import com.disney.ad.adexchange.util.DateUtil;

@RestController
public class RoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    
    private static final String DEFAULT_PAGE_SIZE = "10";
    
    @Autowired
    private final RoleService roleService = null;

    @Autowired
	private DateUtil dateUtil = null;
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Role createRole(@RequestBody @Valid final Role role) {
        LOGGER.debug("API: Create {}", role);
        return roleService.insert(role);
    }
    
    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public Role updateRole(@RequestBody @Valid final Role role) {
        LOGGER.debug("API: Update {}", role);
        return roleService.update(role);
    }        

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public RoleList listRoles(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: list all role");
        RoleList roleList = roleService.getList(pageNo, pageSize);
        return roleList;
    }
    
    @RequestMapping(value = "/role/search", method = RequestMethod.GET)
    public RoleList searchRole(
			@RequestParam(required = false, value = "roleId") String roleId,
			@RequestParam(required = false, value = "roleName") Integer roleName,
			@RequestParam(required = false, value = "roleGroup") Integer roleGroup,
			@RequestParam(required = false, value = "createdTimeStart") String createdTimeStart,
			@RequestParam(required = false, value = "createdTimeEnd") String createdTimeEnd,			
			@RequestParam(required = false, value = "updatedTimeStart") String updatedTimeStart,
			@RequestParam(required = false, value = "updatedTimeEnd") String updatedTimeEnd,			
			@RequestParam(required = false, value = "createdByUser") String createdByUser,
			@RequestParam(required = false, value = "updatedByUser") String updatedByUser,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search role");
        RoleSearchRequest roleSearchRequest = composeRoleSearchRequest(
			roleId,
			roleName,
			roleGroup,
			createdTimeStart,
			createdTimeEnd,			
			updatedTimeStart,
			updatedTimeEnd,			
			createdByUser,
			updatedByUser
        );
		        
        RoleList roleList = roleService.search(roleSearchRequest, pageNo, pageSize);
        return roleList;
    }
    
    @RequestMapping(value = "/role/search/keyword", method = RequestMethod.GET)
    public RoleList searchRoleByKeyword(
    		@RequestParam(required = true, value = "keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        LOGGER.debug("API: search role by keyword");
        RoleList roleList = roleService.searchByKeyword(keyword, pageNo, pageSize);
        return roleList;
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public Role getRole(@PathVariable("id") Integer id) {
        LOGGER.debug("API: list role by Id");
        return roleService.getById(id);
    }    

    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public void deleteRole(@PathVariable("id") Integer id) {
        LOGGER.debug("API: delete role by Id");
        roleService.deleteById(id);
    }       

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(ApiException e) {
        return e.getMessage();
    }

	public RoleSearchRequest composeRoleSearchRequest(
			String roleId,
			Integer roleName,
			Integer roleGroup,
			String createdTimeStart,
			String createdTimeEnd,			
			String updatedTimeStart,
			String updatedTimeEnd,			
			String createdByUser,
			String updatedByUser
) {
		RoleSearchRequest roleSearchRequest = new RoleSearchRequest();
        roleSearchRequest.setRoleId(roleId);
        roleSearchRequest.setRoleName(roleName);
        roleSearchRequest.setRoleGroup(roleGroup);
		if (createdTimeStart != null) {
			Timestamp createdTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeStart);
        	roleSearchRequest.setCreatedTimeStart(createdTimeStartTimestamp);
        }
        if (createdTimeEnd != null) {
        	Timestamp createdTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(createdTimeEnd);
        	roleSearchRequest.setCreatedTimeEnd(createdTimeEndTimestamp);
        }
        
		if (updatedTimeStart != null) {
			Timestamp updatedTimeStartTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeStart);
        	roleSearchRequest.setUpdatedTimeStart(updatedTimeStartTimestamp);
        }
        if (updatedTimeEnd != null) {
        	Timestamp updatedTimeEndTimestamp = dateUtil.convertDateAsStringToTimestamp(updatedTimeEnd);
        	roleSearchRequest.setUpdatedTimeEnd(updatedTimeEndTimestamp);
        }
        
        roleSearchRequest.setCreatedByUser(createdByUser);
        roleSearchRequest.setUpdatedByUser(updatedByUser);
		return roleSearchRequest; 
	}    
}
