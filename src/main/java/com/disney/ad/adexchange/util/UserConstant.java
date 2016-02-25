package com.disney.ad.adexchange.util;


public class UserConstant {

	public static final String MEDIATYPE = "IMAGE";	
	public static final String DEFAULT_SYSTEM_USER = "System";	
	public static final String DEFAULT_USER_SOURCE = "Arena";	
	public static final String DEFAULT_DESCRIPTION = "desc";
	public static final String STRING_EMPTY = "";
	public static final String JSON = ".json";
	public static final String USER = "user";	
	public static final String SESSION = "session";	
	public static final String TOKEN = "token";	
	public static final String TRUE_STRING = "true";

	/* Error Codes */
	
	public static final String AUTHENTICATIONFAILEDSTRING = "Authentication Failed";
	public static final int AUTHENTICATIONFAILED = 401;
	public static final String NOCONTENTSTRING = "No Content";
	public static final int NOCONTENT = 204;
	public static final String BADREQUESTSTRING = "Bad Request";
	public static final int BADREQUEST = 400;
	public static final String TOKEN_EXPIRED = "Token Expired";	
	public static final String DUPLICATE_USER_ERROR_CODE = "US-01";
	public static final String DUPLICATE_USER_ERROR_CODE_VALUE = "EmailId already exists.";
	public static final String ERROR_CODE_US02 = "US-02";
	public static final String USER_NOT_REGISTERED = "User is not registered.";
	public static final String UNAUTHORISED_SERVICE_CALL = "Unauthorised service call";
	public static final String ERROR_CODE_AS03 = "AS-03";	
	public static final String ERROR_CODE_AS02 = "AS-02";	
	public static final String ERROR_CODE_AS01 = "AS-01";
	
	/* ContentsTypes */
	
	public static final String APPLICATION_JSON = "application/json";
	public static final String CONTENTTYPE = "Accept=application/json";
	public static final String MULTIPART_CONTENTTYPE = "Accept=multipart/form-data";
	public static final String UTF_ENCODING = "UTF-8";
	
	/* Salts and Algorithms */
	
	public static final String DEFAULTSALT = "474ef0b7106064c5818dfc143b9467d4";
	public static final String SECRETKEYALGORITHM = "PBKDF2WithHmacSHA1";
	public static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
		
	/*Mail Constants*/
	public static final String MAIL_SMTP_HOST = "mail.smtp.host";
	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	
	public static final String COUNT = "Count";
	public static final String NOTIFICATION_ON = "NOTIFICATION_ON";
	public static final String NOTIFICATION_TRUE = "true";
	public static final String ERROR_CODE = "errorCode";
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String ERROR_CODE_US03 = "US-03";
	public static final String EMAIL_NOT_VERIFIED = "User mail id is not verified.";
	public static final String ERROR_CODE_US04 = "US-04";
	public static final String UNABLE_TO_CREATE_USER = "Unable to create a user.";
	public static final String ERROR_CODE_US05 = "US-05";
		
	public static final String INVALID_MAIL_ID = "Invalid mail id.";
	public static final String ERROR_CODE_US06 = "US-06";
	public static final String RESET_PWD_FAILED = "Unable to reset password.";
	public static final int PASSWORD_LENGTH = 6;
	public static final String ALL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	public static final String ACTIVE_FLAG_ZERO = "0";
	public static final String ACTIVE_FLAG_ONE = "1";
	public static final String ACTIVE_FLAG = "activeFlag";
}
