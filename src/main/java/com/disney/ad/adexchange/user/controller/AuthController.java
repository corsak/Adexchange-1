package com.disney.ad.adexchange.user.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.disney.ad.adexchange.user.domain.User;
import com.disney.ad.adexchange.user.domain.UserSession;
import com.disney.ad.adexchange.user.repository.AuthDaoImpl;
import com.disney.ad.adexchange.user.service.AuthServiceImpl;
import com.disney.ad.adexchange.user.service.UserSessionService;
import com.disney.ad.adexchange.util.Helper;
import com.disney.ad.adexchange.util.HibernateUtil;
import com.disney.ad.adexchange.util.UserConstant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
@RequestMapping("/adExchange")
public class AuthController {

	private static final Logger LOGGER = Logger.getLogger(AuthController.class);
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	//PropertiesReader props = new PropertiesReader();
	
	@Autowired
    private final UserSessionService userSessionService = null;
	
	UserSession usersession = null;
	
	@Autowired
	AuthServiceImpl authService = new AuthServiceImpl();

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = UserConstant.CONTENTTYPE)
	public String login(@RequestBody String userCredentialsJson) {
		Helper helper = new Helper();
		LOGGER.debug("** Printing Incoming User Credentials Json **"
				+ userCredentialsJson);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readTree(userCredentialsJson);
			String emailId = rootNode.path("emailId").asText();
			String password = rootNode.path("password").asText();
			String notificationId = rootNode.path("notificationId").asText();
			String deviceOS = rootNode.path("deviceOS").asText();
			String deviceId = rootNode.path("deviceId").asText();
			String source = rootNode.path("source").asText();
			LOGGER.debug("Incoming token Json  : " + emailId+ password + notificationId + deviceOS + deviceId + source);
			if (emailId.isEmpty() || password.isEmpty()) {
				return helper.composeErrorOutputJson(UserConstant.BADREQUEST);
			}
			return login(emailId, password,notificationId,deviceOS,deviceId,source);
		} catch (Exception e) {
			LOGGER.error("Exception in login " + e.getMessage());
			return helper.composeErrorOutputJson(UserConstant.BADREQUEST);
		}
	}
		
	public String login(String emailId, String password, String notificationId, String deviceOS, String deviceId,
			String source) {
		Helper helper = new Helper();
		Session session = null;
		List<User> userList = null;
		User user = null;
		Map<String, String> finalJson = null;
		Map<String, String> authJson = null;
		System.out.println("authDaoImpls");
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("email", emailId));
			userList = criteria.list();
			System.out.println(userList);
			if (!userList.isEmpty()) {
				user = userList.get(0);
				System.out.println("userpass : "+user.getPassword());
			}
			System.out.println("hash pass : "+helper.getHash(password));
			LOGGER.debug("Login incoming password" + password);
			LOGGER.debug("Login Hashed password" + helper.getHash(password));
			LOGGER.debug("user get password" + user.getPassword());
			if (user != null && helper.getHash(password).equals(user.getPassword())) {
				LOGGER.debug("Login user password" + user.getPassword());
				System.out.println("we are here");
				UserSession usersession = AuthDaoImpl.createUserSession(0, user.getId(), helper.getToken());
				System.out.println(usersession);
				UserSession finaloutput = null;
				try{
				finaloutput = userSessionService.insert(usersession);
				System.out.println(finaloutput);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				return helper.composeOutputJson(user,finaloutput.getAuthToken(), " ", " ");
			} else {
				authJson = new HashMap<String, String>();
				authJson.put("errormessage", "Authentication Failed");
				authJson.put("errorcode", "US-09");
				return new Gson().toJson(authJson);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in login" + e.getMessage());

		} finally {
			if (session != null)
				session.close();
		}
		finalJson = new HashMap<String, String>();
		finalJson.put("errormessage", "Invalid Credentials");
		finalJson.put("errorcode", "US-08");

		return new Gson().toJson(finalJson);
	}
	/*
	 re-use for logout
	@RequestMapping(value = "/logout", method = RequestMethod.POST, headers = UserConstant.CONTENTTYPE)
	public String logout(@RequestHeader(value = "token") String token) {
		Helper helper = new Helper();
		LOGGER.debug("Incoming logout Json" + token);
		try {
			if (token.isEmpty()) {
				LOGGER.debug("Token is Required" + token);
				return helper.composeErrorOutputJson(UserConstant.BADREQUEST);
			}
			LOGGER.debug("Displaying token in logout" + token);
			return authService.logout(token);
		} catch (Exception e) {
			LOGGER.error("Exception in logout " + e.getMessage());
		}
		return null;
	}
	  
	 */
	public String tokenLogin(String token) {
		Helper helper = new Helper();
		Session session = null;
		Transaction tx = null;
		List<UserSession> userSessionList = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(UserSession.class);
			criteria.add(Restrictions.eq("tokenID", token));
			userSessionList = criteria.list();
			if (!userSessionList.isEmpty()) {
				UserSession userSession = userSessionList.get(0);
				LOGGER.debug("tokenLogin incoming token" + token);
				LOGGER.debug("tokenLogin table token" + userSession.getAuthToken());
				if (userSession != null && isTokenValid(userSession.getLastAccessTime().toString())) {
					userSession.setLastAccessTime(helper.getCurrentTimeStamp());
					tx = session.beginTransaction();
					session.save(userSession);
					tx.commit();
					return "";
				}
				//logout(token);
				LOGGER.debug(" @@ Token Expired @@ ");
				return new Helper()
						.composeJsonForInterceptorOuput(UserConstant.ERROR_CODE_AS03, UserConstant.TOKEN_EXPIRED)
						.toString();
			}
			LOGGER.debug(" @@ Authentication Failed @@ ");
			return new Helper().composeJsonForInterceptorOuput(UserConstant.ERROR_CODE_AS02,
					UserConstant.AUTHENTICATIONFAILEDSTRING).toString();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Exception in tokenLogin()" + e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		return null;
	}

	private boolean isTokenValid(String lastAccessTime) throws ParseException {
		boolean isTokenValid = false;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String TokenExpiryDuration = "3600";
		Date resDate = sdf.parse(lastAccessTime);
		LOGGER.debug("isTokenValid Table database date and todays date-->" + resDate + new Date());
		Long differenceInSeconds = (new Date().getTime() - resDate.getTime()) / 1000;
		LOGGER.debug("isTokenValid differenceInSeconds-->" + differenceInSeconds);
		if (differenceInSeconds <= Long.parseLong(TokenExpiryDuration)) {
			isTokenValid = true;
		}
		return isTokenValid;
	}
	
	
}
