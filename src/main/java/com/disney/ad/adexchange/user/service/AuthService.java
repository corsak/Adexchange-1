package com.disney.ad.adexchange.user.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.disney.ad.adexchange.user.domain.UserProfileMapper;

@Service
@Validated
public interface AuthService {

	///public String login(String username, String password);
	public String login(String username, String password, String notificationId, String deviceOS , String deviceId,String source);

	public String tokenLogin(String token);	
	
	public String logout(String token);
	
	public String socialSignIn(UserProfileMapper userMapper);
	
	public String forgotPassword(String emaild);
	
	public String getUdidFromtoken(String token);
}
