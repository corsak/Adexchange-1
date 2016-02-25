package com.disney.ad.adexchange.campaign.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.campaign.domain.LineItem;
import com.disney.ad.adexchange.campaign.domain.LineItemSearchRequest;

public class LineItemSpecification {

	public static Specification<LineItem> searchAnd(final LineItemSearchRequest lineItemSearchRequest, final boolean excludeZeroes) {
		return new Specification<LineItem>() {
			@Override
			public Predicate toPredicate(Root<LineItem> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| lineItemSearchRequest.getCampaignId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("campaignId"), lineItemSearchRequest.getCampaignId()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getAdvertiserId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("advertiserId"), lineItemSearchRequest.getAdvertiserId()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getBannerName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("bannerName"), lineItemSearchRequest.getBannerName()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getDescription() != null
						) {
					predicateList.add(cb.equal(root.<String> get("description"), lineItemSearchRequest.getDescription()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getAdType() != null
						) {
					predicateList.add(cb.equal(root.<String> get("adType"), lineItemSearchRequest.getAdType()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getWeightage() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("weightage"), lineItemSearchRequest.getWeightage()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getBannerDimension() != null
						) {
					predicateList.add(cb.equal(root.<String> get("bannerDimension"), lineItemSearchRequest.getBannerDimension()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getAssetUrl() != null
						) {
					predicateList.add(cb.equal(root.<String> get("assetUrl"), lineItemSearchRequest.getAssetUrl()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getAssetText() != null
						) {
					predicateList.add(cb.equal(root.<String> get("assetText"), lineItemSearchRequest.getAssetText()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getClickUrl() != null
						) {
					predicateList.add(cb.equal(root.<String> get("clickUrl"), lineItemSearchRequest.getClickUrl()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getCallbackUrl() != null
						) {
					predicateList.add(cb.equal(root.<String> get("callbackUrl"), lineItemSearchRequest.getCallbackUrl()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getDeliveryChannel() != null
						) {
					predicateList.add(cb.equal(root.<String> get("deliveryChannel"), lineItemSearchRequest.getDeliveryChannel()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getAdTag() != null
						) {
					predicateList.add(cb.equal(root.<String> get("adTag"), lineItemSearchRequest.getAdTag()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getImpressionsCounter() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("impressionsCounter"), lineItemSearchRequest.getImpressionsCounter()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getBidsCounter() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("bidsCounter"), lineItemSearchRequest.getBidsCounter()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getCurrentSpend() != null
						) {
					predicateList.add(cb.equal(root.<Float> get("currentSpend"), lineItemSearchRequest.getCurrentSpend()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getNativeId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("nativeId"), lineItemSearchRequest.getNativeId()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getVideoId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("videoId"), lineItemSearchRequest.getVideoId()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getBannerStatus() != null
						) {
					predicateList.add(cb.equal(root.<String> get("bannerStatus"), lineItemSearchRequest.getBannerStatus()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("status"), lineItemSearchRequest.getStatus()));
				}
				else{
					predicateList.add(cb.greaterThanOrEqualTo(root.<Integer> get("status"), 1));
				}
				if (!excludeZeroes
						|| (lineItemSearchRequest.getCreatedTimeStart() != null || lineItemSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (lineItemSearchRequest.getCreatedTimeStart() != null
							&& lineItemSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								lineItemSearchRequest.getCreatedTimeStart(),lineItemSearchRequest
								.getCreatedTimeEnd()));
					} else if (lineItemSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), lineItemSearchRequest.getCreatedTimeStart()));
					} else if (lineItemSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), lineItemSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (lineItemSearchRequest.getUpdatedTimeStart() != null || lineItemSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (lineItemSearchRequest.getUpdatedTimeStart() != null
							&& lineItemSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								lineItemSearchRequest.getUpdatedTimeStart(),lineItemSearchRequest
								.getUpdatedTimeEnd()));
					} else if (lineItemSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), lineItemSearchRequest.getUpdatedTimeStart()));
					} else if (lineItemSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), lineItemSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), lineItemSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), lineItemSearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<LineItem> searchOr(final LineItemSearchRequest lineItemSearchRequest, final boolean excludeZeroes) {
		return new Specification<LineItem>() {
			@Override
			public Predicate toPredicate(Root<LineItem> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| lineItemSearchRequest.getCampaignId() != null
						) {
					predicateList.add(cb.like(root.<String> get("campaignId"), ("%" + lineItemSearchRequest.getCampaignId()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getAdvertiserId() != null
						) {
					predicateList.add(cb.like(root.<String> get("advertiserId"), ("%" + lineItemSearchRequest.getAdvertiserId()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getBannerName() != null
						) {
					predicateList.add(cb.like(root.<String> get("bannerName"), ("%" + lineItemSearchRequest.getBannerName()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getDescription() != null
						) {
					predicateList.add(cb.like(root.<String> get("description"), ("%" + lineItemSearchRequest.getDescription()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getAdType() != null
						) {
					predicateList.add(cb.like(root.<String> get("adType"), ("%" + lineItemSearchRequest.getAdType()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getBannerDimension() != null
						) {
					predicateList.add(cb.like(root.<String> get("bannerDimension"), ("%" + lineItemSearchRequest.getBannerDimension()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getAssetUrl() != null
						) {
					predicateList.add(cb.like(root.<String> get("assetUrl"), ("%" + lineItemSearchRequest.getAssetUrl()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getAssetText() != null
						) {
					predicateList.add(cb.like(root.<String> get("assetText"), ("%" + lineItemSearchRequest.getAssetText()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getClickUrl() != null
						) {
					predicateList.add(cb.like(root.<String> get("clickUrl"), ("%" + lineItemSearchRequest.getClickUrl()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getCallbackUrl() != null
						) {
					predicateList.add(cb.like(root.<String> get("callbackUrl"), ("%" + lineItemSearchRequest.getCallbackUrl()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getDeliveryChannel() != null
						) {
					predicateList.add(cb.like(root.<String> get("deliveryChannel"), ("%" + lineItemSearchRequest.getDeliveryChannel()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getAdTag() != null
						) {
					predicateList.add(cb.like(root.<String> get("adTag"), ("%" + lineItemSearchRequest.getAdTag()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getNativeId() != null
						) {
					predicateList.add(cb.like(root.<String> get("nativeId"), ("%" + lineItemSearchRequest.getNativeId()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getVideoId() != null
						) {
					predicateList.add(cb.like(root.<String> get("videoId"), ("%" + lineItemSearchRequest.getVideoId()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getBannerStatus() != null
						) {
					predicateList.add(cb.like(root.<String> get("bannerStatus"), ("%" + lineItemSearchRequest.getBannerStatus()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + lineItemSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| lineItemSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + lineItemSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<LineItem> searchByKeyword(final String keyword) {
		return new Specification<LineItem>() {
			@Override
			public Predicate toPredicate(Root<LineItem> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("campaignId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("advertiserId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("bannerName"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("description"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("adType"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("bannerDimension"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("assetUrl"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("assetText"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("clickUrl"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("callbackUrl"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("deliveryChannel"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("adTag"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("nativeId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("videoId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("bannerStatus"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<LineItem> loadAssociations(
	) {
		return new Specification<LineItem>() {
			@Override
			public Predicate toPredicate(Root<LineItem> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
