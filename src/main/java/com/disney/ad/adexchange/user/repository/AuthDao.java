package com.disney.ad.adexchange.user.repository;

import com.disney.ad.adexchange.user.domain.UserProfileMapper;




public interface AuthDao {

	///public String login(String username, String password);
	public String login(String username, String password,String notificationId, String deviceOS,String deviceId,String source);

	public String tokenLogin(String token);
	
	public String logout(String token);
	
	public String socialSignIn(UserProfileMapper userMapper);
	
	public String forgotPassword(String emaild);
	
	public String getUdidFromtoken(String token);
}
