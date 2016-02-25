package com.disney.ad.adexchange.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = Logger
			.getLogger(AuthenticationInterceptor.class);
	//AuthService authService = new AuthServiceImpl();
	
	private final String PING_URL = "/ping";
	private final String SOCIAL_SIGNIN = "/socialsignin";
	private final String LOGIN = "/login";
	private final String USERS = "/users";
	private final String VIDEOS = "/videos";
	private final String RESET = "/reset";
	private final String PUBLISHER = "/publisher";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String method = request.getMethod();
		boolean isStreamMonitor = Boolean.parseBoolean(request.getHeader("isStreamMonitor"));
		LOGGER.info(" Intercepting || URI: " + uri);
		LOGGER.info(" Intercepting || METHOD " + method);
		LOGGER.debug(" Intercepting || ServerName: " + request.getServerName());
		LOGGER.debug(" Intercepting || ServerPort: " + request.getServerPort());
		LOGGER.debug(" Intercepting || isStreamMonitor: " + isStreamMonitor);		
		if (isTokenAuthenticationRequired(uri, method, isStreamMonitor)) {
			String token = request.getHeader("token");
			
			LOGGER.debug("************* Printing token " + token);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			if (token != null) {
				String tokenResponse = "";
				if (!tokenResponse.isEmpty()) {
					LOGGER.debug("************* token in-valid "
							+ tokenResponse);	
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write(tokenResponse);
					return false;
				}				
			} else {		
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write(new Helper().composeJsonForInterceptorOuput(
						UserConstant.ERROR_CODE_AS01, UserConstant.UNAUTHORISED_SERVICE_CALL).toString());
				return false;
			}			
			LOGGER.debug("************* token valid ");
		}
		return true;
	}	
	
	private boolean isTokenAuthenticationRequired(String uri, String method, boolean isStreamMonitor) {
		if ((uri.contains(USERS) && method.equals(HttpPost.METHOD_NAME))
				|| (uri.contains(LOGIN))
				|| (uri.contains(SOCIAL_SIGNIN)) 
				|| (uri.contains(PING_URL))
				|| (uri.contains(VIDEOS) && method.equals(HttpDelete.METHOD_NAME) && isStreamMonitor) 
			    || (uri.contains(VIDEOS) && method.equals(HttpPut.METHOD_NAME) && isStreamMonitor)
			    || (uri.contains(PUBLISHER) && method.equals(HttpPut.METHOD_NAME))
			    || (uri.contains(RESET))) {			
			return false;
		}
		LOGGER.info("***** Authentication Required *****" + uri + " : " + method + " : " + isStreamMonitor);
		return true;
	}
}
