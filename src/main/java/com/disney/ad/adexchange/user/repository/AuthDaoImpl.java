package com.disney.ad.adexchange.user.repository;

import java.sql.Timestamp;
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

import com.disney.ad.adexchange.user.domain.User;
import com.disney.ad.adexchange.user.domain.UserProfileMapper;
import com.disney.ad.adexchange.user.domain.UserSession;
import com.disney.ad.adexchange.user.service.UserSessionService;
import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.Helper;
import com.disney.ad.adexchange.util.HibernateUtil;
import com.disney.ad.adexchange.util.UserConstant;
import com.google.gson.Gson;

public class AuthDaoImpl implements AuthDao {

	private static final Logger LOGGER = Logger.getLogger(AuthDaoImpl.class);
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	//PropertiesReader props = new PropertiesReader();
	
	@Autowired
    private final UserSessionService userSessionService = null;
	
	public AuthDaoImpl() {

	}

	
	@Override
	public String logout(String token) {
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
				tx = session.beginTransaction();
				session.delete(userSession);
				tx.commit();
				return "";
			}
			return helper.composeErrorOutputJson(UserConstant.NOCONTENT);
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

	@Override
	public String tokenLogin(String token) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public static UserSession createUserSession(int i, Integer userId, String token) {
	    UserSession userSession = new UserSession();
	    userSession.setId(0);  // TODO generalize
	    Object value = null;
	    userSession.setUserId(String.valueOf(userId));
		value = "userSessionId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    userSession.setUserSessionId((String)value);
	    userSession.setAuthToken(token);
		value = new DateUtil().determineCurrentTimestamp();
	    userSession.setLastAccessTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    userSession.setLastLoggedInTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    userSession.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    userSession.setUpdatedTime((Timestamp)value);
	    userSession.setCreatedByUser("admin");
	    userSession.setUpdatedByUser("admin");
		return userSession;
	}

	@Override
	public String login(String username, String password,
			String notificationId, String deviceOS, String deviceId,
			String source) {
		// TODO Auto-generated method stub
		return null;
	}
}
