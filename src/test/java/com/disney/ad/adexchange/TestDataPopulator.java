package com.disney.ad.adexchange;

import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.disney.ad.adexchange.util.DateUtil;
import com.disney.ad.adexchange.util.RestClient;

import com.disney.ad.adexchange.user.domain.User;
import com.disney.ad.adexchange.user.domain.UserSession;
import com.disney.ad.adexchange.user.domain.Notification;
import com.disney.ad.adexchange.user.domain.Role;
import com.disney.ad.adexchange.user.domain.Advertiser;
import com.disney.ad.adexchange.user.domain.Publisher;
import com.disney.ad.adexchange.campaign.domain.InsertionOrder;
import com.disney.ad.adexchange.campaign.domain.LineItem;
import com.disney.ad.adexchange.campaign.domain.NativeAd;
import com.disney.ad.adexchange.campaign.domain.Video;
import com.disney.ad.adexchange.publisher.domain.DigitalProperty;
import com.disney.ad.adexchange.publisher.domain.Inventory;
import com.disney.ad.adexchange.publisher.domain.InventorySpace;
import com.disney.ad.adexchange.common.domain.AdTemplates;
import com.disney.ad.adexchange.campaign.domain.BannerAdzones;
import com.disney.ad.adexchange.campaign.domain.BannerAdTarget;
import com.disney.ad.adexchange.common.domain.IndustryCategory;
import com.disney.ad.adexchange.analytics.domain.DailyImpressions;
import com.disney.ad.adexchange.analytics.domain.HourlyImpressions;

public class TestDataPopulator {

	private static final String SERVICE_BASE_URL = "http://localhost:8880";

	public static void main(String[] args) {
		TestDataPopulator testDataPopulator = new TestDataPopulator();
		testDataPopulator.execute();
	}

	private void execute() {
		RestClient restClient = new RestClient(SERVICE_BASE_URL);
		ObjectMapper objectMapper = new ObjectMapper();
		for (int i = 0; i < 100; i++) {
			Object advertiser = createAdvertiser(i);
			doPost("/advertiser", restClient, objectMapper, advertiser);
			Object userSession = createUserSession(i);
			doPost("/userSession", restClient, objectMapper, userSession);
			Object notification = createNotification(i);
			doPost("/notification", restClient, objectMapper, notification);
			Object role = createRole(i);
			doPost("/role", restClient, objectMapper, role);
			Object publisher = createPublisher(i);
			doPost("/publisher", restClient, objectMapper, publisher);
			Object user = createUser(i);
			doPost("/user", restClient, objectMapper, user);
			Object insertionOrder = createInsertionOrder(i);
			doPost("/insertionOrder", restClient, objectMapper, insertionOrder);
			Object lineItem = createLineItem(i);
			doPost("/lineItem", restClient, objectMapper, lineItem);
			Object nativeAd = createNativeAd(i);
			doPost("/nativeAd", restClient, objectMapper, nativeAd);
			Object video = createVideo(i);
			doPost("/video", restClient, objectMapper, video);
			Object digitalProperty = createDigitalProperty(i);
			doPost("/digitalProperty", restClient, objectMapper, digitalProperty);
			Object inventory = createInventory(i);
			doPost("/inventory", restClient, objectMapper, inventory);
			Object inventorySpace = createInventorySpace(i);
			doPost("/inventorySpace", restClient, objectMapper, inventorySpace);
			Object adTemplates = createAdTemplates(i);
			doPost("/adTemplates", restClient, objectMapper, adTemplates);
			Object bannerAdzones = createBannerAdzones(i);
			doPost("/bannerAdzones", restClient, objectMapper, bannerAdzones);
			Object bannerAdTarget = createBannerAdTarget(i);
			doPost("/bannerAdTarget", restClient, objectMapper, bannerAdTarget);
			Object industryCategory = createIndustryCategory(i);
			doPost("/industryCategory", restClient, objectMapper, industryCategory);
			Object dailyImpressions = createDailyImpressions(i);
			doPost("/dailyImpressions", restClient, objectMapper, dailyImpressions);
			Object hourlyImpressions = createHourlyImpressions(i);
			doPost("/hourlyImpressions", restClient, objectMapper, hourlyImpressions);
		}
	}

	private void doPost(String uri, RestClient restClient, ObjectMapper objectMapper,
			Object object) {
		String jsonPayload = null;
		try {
			jsonPayload = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		restClient.post(uri, jsonPayload);
	}

	private User createUser(int i) {
	    User user = new User();
	    user.setId(0);  // TODO generalize
	    Object value = null;
		value = "email" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    user.setEmail((String)value);
		value = "loginName" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    user.setLoginName((String)value);
		value = "password" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    user.setPassword((String)value);
		value = new Integer(1);
	    user.setStatus((Integer)value);
		value = new Integer(1);
	    user.setUserType((Integer)value);
		value = "hintQuestion1" + i;
	    user.setHintQuestion1((String)value);
		value = "hintAnswer1" + i;
	    user.setHintAnswer1((String)value);
		value = "hintQuestion2" + i;
	    user.setHintQuestion2((String)value);
		value = "hintAnswer2" + i;
	    user.setHintAnswer2((String)value);
		value = new Boolean(true);
	    user.setIsAdmin((Boolean)value);
		value = new DateUtil().determineCurrentTimestamp();
	    user.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    user.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    user.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    user.setUpdatedByUser((String)value);
		Advertiser advertiser = new Advertiser();
		advertiser.setId(i/5 +1);
		user.setAdvertiser(advertiser);
		Publisher publisher = new Publisher();
		publisher.setId(i/5 +1);
		user.setPublisher(publisher);
		Role role = new Role();
		role.setId(i/5 +1);
		user.setRole(role);
		return user;
	}
	private UserSession createUserSession(int i) {
	    UserSession userSession = new UserSession();
	    userSession.setId(0);  // TODO generalize
	    Object value = null;
		value = "userId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    userSession.setUserId((String)value);
		value = "userSessionId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    userSession.setUserSessionId((String)value);
		value = "authToken" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    userSession.setAuthToken((String)value);
		value = new DateUtil().determineCurrentTimestamp();
	    userSession.setLastAccessTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    userSession.setLastLoggedInTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    userSession.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    userSession.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    userSession.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    userSession.setUpdatedByUser((String)value);
		return userSession;
	}
	private Notification createNotification(int i) {
	    Notification notification = new Notification();
	    notification.setId(0);  // TODO generalize
	    Object value = null;
		value = "notificationId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    notification.setNotificationId((String)value);
		value = "userId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    notification.setUserId((String)value);
		value = "deviceOS" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    notification.setDeviceOS((String)value);
		value = "source" + i;
	    notification.setSource((String)value);
		value = new DateUtil().determineCurrentTimestamp();
	    notification.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    notification.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    notification.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    notification.setUpdatedByUser((String)value);
		return notification;
	}
	private Role createRole(int i) {
	    Role role = new Role();
	    role.setId(0);  // TODO generalize
	    Object value = null;
		value = "roleId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    role.setRoleId((String)value);
		value = new Integer(1);
	    role.setRoleName((Integer)value);
		value = new Integer(1);
	    role.setRoleGroup((Integer)value);
		value = new DateUtil().determineCurrentTimestamp();
	    role.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    role.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    role.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    role.setUpdatedByUser((String)value);
		return role;
	}
	private Advertiser createAdvertiser(int i) {
	    Advertiser advertiser = new Advertiser();
	    advertiser.setId(0);  // TODO generalize
	    Object value = null;
		value = "advertiserId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    advertiser.setAdvertiserId((String)value);
		value = "advertiserName" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    advertiser.setAdvertiserName((String)value);
		value = "company" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    advertiser.setCompany((String)value);
		value = "contactName" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    advertiser.setContactName((String)value);
		value = "contactNumber" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    advertiser.setContactNumber((String)value);
		value = "country" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    advertiser.setCountry((String)value);
		value = "description" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    advertiser.setDescription((String)value);
		value = "partnerType" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    advertiser.setPartnerType((String)value);
		value = "status" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    advertiser.setStatus((String)value);
		value = new DateUtil().determineCurrentTimestamp();
	    advertiser.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    advertiser.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    advertiser.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    advertiser.setUpdatedByUser((String)value);
		return advertiser;
	}
	private Publisher createPublisher(int i) {
	    Publisher publisher = new Publisher();
	    publisher.setId(0);  // TODO generalize
	    Object value = null;
		value = "publisherId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    publisher.setPublisherId((String)value);
		value = "publisherName" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    publisher.setPublisherName((String)value);
		value = "company" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    publisher.setCompany((String)value);
		value = "contactName" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    publisher.setContactName((String)value);
		value = "contactNumber" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    publisher.setContactNumber((String)value);
		value = "country" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    publisher.setCountry((String)value);
		value = "primaryDomain" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    publisher.setPrimaryDomain((String)value);
		value = "iabCategory" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    publisher.setIabCategory((String)value);
		value = "address" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    publisher.setAddress((String)value);
		value = new Integer(1);
	    publisher.setStatus((Integer)value);
		value = new DateUtil().determineCurrentTimestamp();
	    publisher.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    publisher.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    publisher.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    publisher.setUpdatedByUser((String)value);
		return publisher;
	}
	private InsertionOrder createInsertionOrder(int i) {
	    InsertionOrder insertionOrder = new InsertionOrder();
	    insertionOrder.setId(0);  // TODO generalize
	    Object value = null;
		value = "insertionOrderId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    insertionOrder.setInsertionOrderId((String)value);
		value = "campaignName" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    insertionOrder.setCampaignName((String)value);
		value = "description" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    insertionOrder.setDescription((String)value);
		value = "campaignObjective" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    insertionOrder.setCampaignObjective((String)value);
		value = new DateUtil().determineCurrentTimestamp();
	    insertionOrder.setCampaignStartDate((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    insertionOrder.setCampaignEndDate((Timestamp)value);
		value = "orderType" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    insertionOrder.setOrderType((String)value);
		value = new Float(1.0);
	    insertionOrder.setSpend((Float)value);
		value = new Float(1.0);
	    insertionOrder.setCurrency((Float)value);
		value = "revenueModel" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    insertionOrder.setRevenueModel((String)value);
		value = new Integer(1);
	    insertionOrder.setMaximumImpressions((Integer)value);
		value = new Float(1.0);
	    insertionOrder.setMaximumSpend((Float)value);
		value = new Integer(1);
	    insertionOrder.setCurrentImpressions((Integer)value);
		value = new Float(1.0);
	    insertionOrder.setCurrentSpend((Float)value);
		value = new Float(1.0);
	    insertionOrder.setMaximumSpendPerDay((Float)value);
		value = new Boolean(true);
	    insertionOrder.setPixelTrackingEnabled((Boolean)value);
		value = "companionCampaign" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    insertionOrder.setCompanionCampaign((String)value);
		value = "campaignStatus" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    insertionOrder.setCampaignStatus((String)value);
		value = "priority" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    insertionOrder.setPriority((String)value);
		value = "comments" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    insertionOrder.setComments((String)value);
		value = "advertiserId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    insertionOrder.setAdvertiserId((String)value);
		value = new Integer(1);
	    insertionOrder.setStatus((Integer)value);
		value = new DateUtil().determineCurrentTimestamp();
	    insertionOrder.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    insertionOrder.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    insertionOrder.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    insertionOrder.setUpdatedByUser((String)value);
		return insertionOrder;
	}
	private LineItem createLineItem(int i) {
	    LineItem lineItem = new LineItem();
	    lineItem.setId(0);  // TODO generalize
	    Object value = null;
		value = "campaignId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setCampaignId((String)value);
		value = "advertiserId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setAdvertiserId((String)value);
		value = "bannerName" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setBannerName((String)value);
		value = "description" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    lineItem.setDescription((String)value);
		value = "adType" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setAdType((String)value);
		value = new Integer(1);
	    lineItem.setWeightage((Integer)value);
		value = "bannerDimension" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setBannerDimension((String)value);
		value = "assetUrl" + i;
		if (((String)value).length() > 100) {
			value = ((String)value).substring(0, 100);
		}
	    lineItem.setAssetUrl((String)value);
		value = "assetText" + i;
		if (((String)value).length() > 100) {
			value = ((String)value).substring(0, 100);
		}
	    lineItem.setAssetText((String)value);
		value = "clickUrl" + i;
		if (((String)value).length() > 100) {
			value = ((String)value).substring(0, 100);
		}
	    lineItem.setClickUrl((String)value);
		value = "callbackUrl" + i;
		if (((String)value).length() > 100) {
			value = ((String)value).substring(0, 100);
		}
	    lineItem.setCallbackUrl((String)value);
		value = "deliveryChannel" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setDeliveryChannel((String)value);
		value = "adTag" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setAdTag((String)value);
		value = new Integer(1);
	    lineItem.setImpressionsCounter((Integer)value);
		value = new Integer(1);
	    lineItem.setBidsCounter((Integer)value);
		value = new Float(1.0);
	    lineItem.setCurrentSpend((Float)value);
		value = "nativeId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setNativeId((String)value);
		value = "videoId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setVideoId((String)value);
		value = "bannerStatus" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setBannerStatus((String)value);
		value = new Integer(1);
	    lineItem.setStatus((Integer)value);
		value = new DateUtil().determineCurrentTimestamp();
	    lineItem.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    lineItem.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    lineItem.setUpdatedByUser((String)value);
		return lineItem;
	}
	private NativeAd createNativeAd(int i) {
	    NativeAd nativeAd = new NativeAd();
	    nativeAd.setId(0);  // TODO generalize
	    Object value = null;
		value = "title" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    nativeAd.setTitle((String)value);
		value = "description" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    nativeAd.setDescription((String)value);
		value = "highlightedText" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    nativeAd.setHighlightedText((String)value);
		value = "icon" + i;
		if (((String)value).length() > 100) {
			value = ((String)value).substring(0, 100);
		}
	    nativeAd.setIcon((String)value);
		value = "button" + i;
		if (((String)value).length() > 100) {
			value = ((String)value).substring(0, 100);
		}
	    nativeAd.setButton((String)value);
		value = "actionUrl" + i;
		if (((String)value).length() > 100) {
			value = ((String)value).substring(0, 100);
		}
	    nativeAd.setActionUrl((String)value);
		value = new Float(1.0);
	    nativeAd.setPrice((Float)value);
		value = new Integer(1);
	    nativeAd.setRating((Integer)value);
		value = "sponsoredText" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    nativeAd.setSponsoredText((String)value);
		value = "sponsoredImageUrl" + i;
		if (((String)value).length() > 100) {
			value = ((String)value).substring(0, 100);
		}
	    nativeAd.setSponsoredImageUrl((String)value);
		return nativeAd;
	}
	private Video createVideo(int i) {
	    Video video = new Video();
	    video.setId(0);  // TODO generalize
	    Object value = null;
		value = "title" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    video.setTitle((String)value);
		value = "videoUrl" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    video.setVideoUrl((String)value);
		value = "videoType" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    video.setVideoType((String)value);
		value = "delivery" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    video.setDelivery((String)value);
		value = "bitrate" + i;
		if (((String)value).length() > 100) {
			value = ((String)value).substring(0, 100);
		}
	    video.setBitrate((String)value);
		value = new Integer(1);
	    video.setWidth((Integer)value);
		value = new Integer(1);
	    video.setHeight((Integer)value);
		value = new Float(1.0);
	    video.setDuration((Float)value);
		return video;
	}
	private DigitalProperty createDigitalProperty(int i) {
	    DigitalProperty digitalProperty = new DigitalProperty();
	    digitalProperty.setId(0);  // TODO generalize
	    Object value = null;
		value = "propertyName" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    digitalProperty.setPropertyName((String)value);
		value = "description" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    digitalProperty.setDescription((String)value);
		value = "type" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    digitalProperty.setType((String)value);
		value = "domainURL" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    digitalProperty.setDomainURL((String)value);
		value = "publisherId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    digitalProperty.setPublisherId((String)value);
		value = "IABCategory" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    digitalProperty.setIABCategory((String)value);
		value = "IABSubCategory" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    digitalProperty.setIABSubCategory((String)value);
		value = "country" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    digitalProperty.setCountry((String)value);
		value = "language" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    digitalProperty.setLanguage((String)value);
		value = new Integer(1);
	    digitalProperty.setStatus((Integer)value);
		value = new DateUtil().determineCurrentTimestamp();
	    digitalProperty.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    digitalProperty.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    digitalProperty.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    digitalProperty.setUpdatedByUser((String)value);
		return digitalProperty;
	}
	private Inventory createInventory(int i) {
	    Inventory inventory = new Inventory();
	    inventory.setId(0);  // TODO generalize
	    Object value = null;
		value = "zoneID" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setZoneID((String)value);
		value = "digitalPropertyId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setDigitalPropertyId((String)value);
		value = "inventoryName" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setInventoryName((String)value);
		value = "description" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    inventory.setDescription((String)value);
		value = "zoneType" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setZoneType((String)value);
		value = "adType" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setAdType((String)value);
		value = "adTemplateID" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setAdTemplateID((String)value);
		value = new Integer(1);
	    inventory.setAdWidth((Integer)value);
		value = new Integer(1);
	    inventory.setAdHeight((Integer)value);
		value = "adInvocationTag" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setAdInvocationTag((String)value);
		value = "passbackAdTag" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setPassbackAdTag((String)value);
		value = new Integer(1);
	    inventory.setFloorPrice((Integer)value);
		value = new Integer(1);
	    inventory.setTotalRequests((Integer)value);
		value = new Integer(1);
	    inventory.setTotalImpressions((Integer)value);
		value = new Float(1.0);
	    inventory.setTotalRevenues((Float)value);
		value = "keywords" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setKeywords((String)value);
		value = new Integer(1);
	    inventory.setStatus((Integer)value);
		value = new DateUtil().determineCurrentTimestamp();
	    inventory.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    inventory.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventory.setUpdatedByUser((String)value);
		return inventory;
	}
	private InventorySpace createInventorySpace(int i) {
	    InventorySpace inventorySpace = new InventorySpace();
	    inventorySpace.setId(0);  // TODO generalize
	    Object value = null;
		value = "inventoryId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventorySpace.setInventoryId((String)value);
		value = new Float(1.0);
	    inventorySpace.setEstimatedValue((Float)value);
		value = "units" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    inventorySpace.setUnits((String)value);
		return inventorySpace;
	}
	private AdTemplates createAdTemplates(int i) {
	    AdTemplates adTemplates = new AdTemplates();
	    adTemplates.setId(0);  // TODO generalize
	    Object value = null;
		value = "adTemplateID" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    adTemplates.setAdTemplateID((String)value);
		value = "templateName" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    adTemplates.setTemplateName((String)value);
		value = new Integer(1);
	    adTemplates.setIsMobileFlag((Integer)value);
		value = new Integer(1);
	    adTemplates.setWidth((Integer)value);
		value = new Integer(1);
	    adTemplates.setHeight((Integer)value);
		value = new Integer(1);
	    adTemplates.setStatus((Integer)value);
		value = new DateUtil().determineCurrentTimestamp();
	    adTemplates.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    adTemplates.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    adTemplates.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    adTemplates.setUpdatedByUser((String)value);
		return adTemplates;
	}
	private BannerAdzones createBannerAdzones(int i) {
	    BannerAdzones bannerAdzones = new BannerAdzones();
	    bannerAdzones.setId(0);  // TODO generalize
	    Object value = null;
		value = "bannerID" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdzones.setBannerID((String)value);
		value = "zoneID" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdzones.setZoneID((String)value);
		value = "campaignID" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdzones.setCampaignID((String)value);
		return bannerAdzones;
	}
	private BannerAdTarget createBannerAdTarget(int i) {
	    BannerAdTarget bannerAdTarget = new BannerAdTarget();
	    bannerAdTarget.setId(0);  // TODO generalize
	    Object value = null;
		value = "bannerID" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdTarget.setBannerID((String)value);
		value = "campaignID" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdTarget.setCampaignID((String)value);
		value = "geo" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdTarget.setGeo((String)value);
		value = "state" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdTarget.setState((String)value);
		value = "city" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdTarget.setCity((String)value);
		value = "ageGroup" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdTarget.setAgeGroup((String)value);
		value = "gender" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdTarget.setGender((String)value);
		value = "category" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdTarget.setCategory((String)value);
		value = "keyword" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    bannerAdTarget.setKeyword((String)value);
		value = new Integer(1);
	    bannerAdTarget.setStatus((Integer)value);
		return bannerAdTarget;
	}
	private IndustryCategory createIndustryCategory(int i) {
	    IndustryCategory industryCategory = new IndustryCategory();
	    industryCategory.setId(0);  // TODO generalize
	    Object value = null;
		value = "name" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    industryCategory.setName((String)value);
		value = "description" + i;
		if (((String)value).length() > 250) {
			value = ((String)value).substring(0, 250);
		}
	    industryCategory.setDescription((String)value);
		value = new Integer(1);
	    industryCategory.setStatus((Integer)value);
		value = "parentID" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    industryCategory.setParentID((String)value);
		value = new DateUtil().determineCurrentTimestamp();
	    industryCategory.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    industryCategory.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    industryCategory.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    industryCategory.setUpdatedByUser((String)value);
		return industryCategory;
	}
	private DailyImpressions createDailyImpressions(int i) {
	    DailyImpressions dailyImpressions = new DailyImpressions();
	    dailyImpressions.setId(0);  // TODO generalize
	    Object value = null;
		value = new Integer(1);
	    dailyImpressions.setPublisherId((Integer)value);
		value = new Integer(1);
	    dailyImpressions.setDigitalPropertyid((Integer)value);
		value = new Integer(1);
	    dailyImpressions.setLineItemId((Integer)value);
		value = new Integer(1);
	    dailyImpressions.setZoneId((Integer)value);
		value = new Integer(1);
	    dailyImpressions.setNoOfImpressions((Integer)value);
		value = new Integer(1);
	    dailyImpressions.setNoOfClicks((Integer)value);
		value = new Integer(1);
	    dailyImpressions.setDate((Integer)value);
		value = new DateUtil().determineCurrentTimestamp();
	    dailyImpressions.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    dailyImpressions.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    dailyImpressions.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    dailyImpressions.setUpdatedByUser((String)value);
		return dailyImpressions;
	}
	private HourlyImpressions createHourlyImpressions(int i) {
	    HourlyImpressions hourlyImpressions = new HourlyImpressions();
	    hourlyImpressions.setId(0);  // TODO generalize
	    Object value = null;
		value = "publisherId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    hourlyImpressions.setPublisherId((String)value);
		value = new Integer(1);
	    hourlyImpressions.setDigitalPropertyid((Integer)value);
		value = new Integer(1);
	    hourlyImpressions.setLineItemId((Integer)value);
		value = "zoneId" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    hourlyImpressions.setZoneId((String)value);
		value = new Integer(1);
	    hourlyImpressions.setNoOfImpressions((Integer)value);
		value = new Integer(1);
	    hourlyImpressions.setNoOfClicks((Integer)value);
		value = new Integer(1);
	    hourlyImpressions.setDateTime((Integer)value);
		value = new DateUtil().determineCurrentTimestamp();
	    hourlyImpressions.setCreatedTime((Timestamp)value);
		value = new DateUtil().determineCurrentTimestamp();
	    hourlyImpressions.setUpdatedTime((Timestamp)value);
		value = "createdByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    hourlyImpressions.setCreatedByUser((String)value);
		value = "updatedByUser" + i;
		if (((String)value).length() > 50) {
			value = ((String)value).substring(0, 50);
		}
	    hourlyImpressions.setUpdatedByUser((String)value);
		return hourlyImpressions;
	}

}