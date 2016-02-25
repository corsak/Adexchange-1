package com.disney.ad.adexchange.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.disney.ad.adexchange.user.domain.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {

	private static final Logger LOGGER = Logger.getLogger(Helper.class);

	private static final String DEFAULT_PRNG = "SHA1PRNG"; // algorithm to
															// generate key

	public String getToken() throws NoSuchAlgorithmException {
		return getToken(DEFAULT_PRNG);
	}

	/**
	 * Generates a random token to use in CSRF with the default Crytopgrahically
	 * strong Pseudo-Number Random Generator
	 * 
	 * @param prng
	 *            Random Number generator to use
	 * @return A string with a random token
	 * @throws NoSuchAlgorithmException
	 *             if PRNG algorithm is not valid
	 */
	private String getToken(String prng) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance(prng);
		return "" + sr.nextLong();
	}

	public Timestamp getCurrentTimeStamp() {
		return new Timestamp(new Date().getTime());
	}

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

	public String composeOutputJson(User user, String tokenId,String deviceId , String notificationId) {
		Writer strWriter = new StringWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> outputJson = new HashMap<String, String>();
		try {
			outputJson.put("ID", String.valueOf(user.getId()));
			if (tokenId != null) {
				outputJson.put("token", tokenId);
			}
			if(user.getPublisher() != null){
				outputJson.put("PubliserId", String.valueOf(user.getPublisher().getPublisherId()));
				//System.out.println(user.getPublisher());
			}
			else{
				outputJson.put("PubliserId",null);
			}
			if(user.getAdvertiser() != null){
				outputJson.put("AdvertiserId", String.valueOf(user.getAdvertiser().getAdvertiserId()));
				//System.out.println(user.getAdvertiser());
			}
			else{
				outputJson.put("AdvertiserId",null);
			}
			outputJson.put("EmailId", user.getEmail());
			outputJson.put("IsAdmin", String.valueOf(user.getIsAdmin()));
			outputJson.put("UserType", String.valueOf(user.getUserType()));
			outputJson.put("Status", String.valueOf(user.getStatus()));
			objectMapper.writeValue(strWriter, outputJson);
		} catch (JsonGenerationException e) {
			LOGGER.error("JsonGenerationException : composeOutputJson"
					+ e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error("JsonMappingException : composeOutputJson"
					+ e.getMessage());
		} catch (IOException e) {
			LOGGER.error("IOException : composeOutputJson" + e.getMessage());
		}
		return strWriter.toString();
	}

	public String composeErrorOutputJson(Integer errorCode) {
		Writer strWriter = new StringWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Integer> outputJson = new HashMap<String, Integer>();
		try {
			switch (errorCode) {
			case UserConstant.AUTHENTICATIONFAILED:
				outputJson.put(UserConstant.AUTHENTICATIONFAILEDSTRING,
						UserConstant.AUTHENTICATIONFAILED);
				break;
			case UserConstant.NOCONTENT:
				outputJson.put(UserConstant.NOCONTENTSTRING, UserConstant.NOCONTENT);
				break;
			case UserConstant.BADREQUEST:
				outputJson
						.put(UserConstant.BADREQUESTSTRING, UserConstant.BADREQUEST);
				break;
			default:
				break;
			}
			objectMapper.writeValue(strWriter, outputJson);
		} catch (JsonGenerationException e) {
			LOGGER.error("JsonGenerationException : composeOutputJson"
					+ e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error("JsonMappingException : composeOutputJson"
					+ e.getMessage());
		} catch (IOException e) {
			LOGGER.error("IOException : composeOutputJson" + e.getMessage());
		}
		return strWriter.toString();
	}
	
	public JSONObject composeJsonForInterceptorOuput(String errorCode, String errorMessage){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(UserConstant.ERROR_CODE, errorCode);
		jsonObject.put(UserConstant.ERROR_MESSAGE, errorMessage);					
		return jsonObject;			
	}
}
