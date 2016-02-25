package com.disney.ad.adexchange.user.service;

import com.disney.ad.adexchange.user.domain.UserProfileMapper;
import com.disney.ad.adexchange.user.repository.AuthDao;
import com.disney.ad.adexchange.user.repository.AuthDaoImpl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
@Service
@Validated
public class AuthServiceImpl implements AuthService {

	private static final Logger LOGGER = Logger
			.getLogger(AuthServiceImpl.class);
	private AuthDao authDao = new AuthDaoImpl();

	
///	public String login(String emailId, String password) {
	@Override
	public String login(String emailId, String password , String notificationId, String deviceOS , String deviceId, String source) {
		if (emailId != null && password != null) {
			LOGGER.debug("login" + emailId + password);
			return authDao.login(emailId, password,notificationId,deviceOS,deviceId,source);
		}
		return null;
	}

	@Override
	public String tokenLogin(String token) {
		if (token != null) {
			LOGGER.debug("tokenLogin" + token);
			return authDao.tokenLogin(token);
		}
		return null;
	}

	@Override
	public String logout(String token) {
		if (token != null) {
			LOGGER.debug("logout" + token);
			return authDao.logout(token);
		}
		return null;
	}
	/*
	@Override
	public String socialSignIn(UserProfileMapper userMapper) {
		return authDao.socialSignIn(userMapper);
	}

	@Override
	public String forgotPassword(String emailId) {
		String status = null;
		String password = authDao.forgotPassword(emailId);
	    if (password == null) {
	    	JSONObject jsonObject = new JSONObject();
			jsonObject.put(UserConstant.ERROR_CODE, UserConstant.ERROR_CODE_US02);
			jsonObject.put(UserConstant.ERROR_MESSAGE, UserConstant.USER_NOT_REGISTERED);
			status =  jsonObject.toString();
	    } else {
	    	status = EmailService.sendEmailToUser(emailId,password);
	    }
		return status;
	}

	public String getUdidFromtoken(String token) {
		return authDao.getUdidFromtoken(token);
	}
	*/

	@Override
	public String socialSignIn(UserProfileMapper userMapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String forgotPassword(String emaild) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUdidFromtoken(String token) {
		// TODO Auto-generated method stub
		return null;
	}
}
