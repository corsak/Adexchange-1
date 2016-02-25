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

import com.disney.ad.adexchange.campaign.domain.InsertionOrder;
import com.disney.ad.adexchange.campaign.domain.InsertionOrderSearchRequest;

public class InsertionOrderSpecification {

	public static Specification<InsertionOrder> searchAnd(final InsertionOrderSearchRequest insertionOrderSearchRequest, final boolean excludeZeroes) {
		return new Specification<InsertionOrder>() {
			@Override
			public Predicate toPredicate(Root<InsertionOrder> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getInsertionOrderId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("insertionOrderId"), insertionOrderSearchRequest.getInsertionOrderId()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCampaignName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("campaignName"), insertionOrderSearchRequest.getCampaignName()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getDescription() != null
						) {
					predicateList.add(cb.equal(root.<String> get("description"), insertionOrderSearchRequest.getDescription()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCampaignObjective() != null
						) {
					predicateList.add(cb.equal(root.<String> get("campaignObjective"), insertionOrderSearchRequest.getCampaignObjective()));
				}
				if (!excludeZeroes
						|| (insertionOrderSearchRequest.getCampaignStartDateStart() != null || insertionOrderSearchRequest
								.getCampaignStartDateEnd() != null)) {
					if (insertionOrderSearchRequest.getCampaignStartDateStart() != null
							&& insertionOrderSearchRequest.getCampaignStartDateEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("campaignStartDate"),
								insertionOrderSearchRequest.getCampaignStartDateStart(),insertionOrderSearchRequest
								.getCampaignStartDateEnd()));
					} else if (insertionOrderSearchRequest.getCampaignStartDateStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("campaignStartDate"), insertionOrderSearchRequest.getCampaignStartDateStart()));
					} else if (insertionOrderSearchRequest.getCampaignStartDateEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("campaignStartDate"), insertionOrderSearchRequest.getCampaignStartDateEnd()));
					}
				}
				if (!excludeZeroes
						|| (insertionOrderSearchRequest.getCampaignEndDateStart() != null || insertionOrderSearchRequest
								.getCampaignEndDateEnd() != null)) {
					if (insertionOrderSearchRequest.getCampaignEndDateStart() != null
							&& insertionOrderSearchRequest.getCampaignEndDateEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("campaignEndDate"),
								insertionOrderSearchRequest.getCampaignEndDateStart(),insertionOrderSearchRequest
								.getCampaignEndDateEnd()));
					} else if (insertionOrderSearchRequest.getCampaignEndDateStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("campaignEndDate"), insertionOrderSearchRequest.getCampaignEndDateStart()));
					} else if (insertionOrderSearchRequest.getCampaignEndDateEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("campaignEndDate"), insertionOrderSearchRequest.getCampaignEndDateEnd()));
					}
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getOrderType() != null
						) {
					predicateList.add(cb.equal(root.<String> get("orderType"), insertionOrderSearchRequest.getOrderType()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getSpend() != null
						) {
					predicateList.add(cb.equal(root.<Float> get("spend"), insertionOrderSearchRequest.getSpend()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCurrency() != null
						) {
					predicateList.add(cb.equal(root.<Float> get("currency"), insertionOrderSearchRequest.getCurrency()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getRevenueModel() != null
						) {
					predicateList.add(cb.equal(root.<String> get("revenueModel"), insertionOrderSearchRequest.getRevenueModel()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getMaximumImpressions() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("maximumImpressions"), insertionOrderSearchRequest.getMaximumImpressions()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getMaximumSpend() != null
						) {
					predicateList.add(cb.equal(root.<Float> get("maximumSpend"), insertionOrderSearchRequest.getMaximumSpend()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCurrentImpressions() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("currentImpressions"), insertionOrderSearchRequest.getCurrentImpressions()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCurrentSpend() != null
						) {
					predicateList.add(cb.equal(root.<Float> get("currentSpend"), insertionOrderSearchRequest.getCurrentSpend()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getMaximumSpendPerDay() != null
						) {
					predicateList.add(cb.equal(root.<Float> get("maximumSpendPerDay"), insertionOrderSearchRequest.getMaximumSpendPerDay()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getPixelTrackingEnabled() != null
						) {
					predicateList.add(cb.equal(root.<Boolean> get("pixelTrackingEnabled"), insertionOrderSearchRequest.getPixelTrackingEnabled()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCompanionCampaign() != null
						) {
					predicateList.add(cb.equal(root.<String> get("companionCampaign"), insertionOrderSearchRequest.getCompanionCampaign()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCampaignStatus() != null
						) {
					predicateList.add(cb.equal(root.<String> get("campaignStatus"), insertionOrderSearchRequest.getCampaignStatus()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getPriority() != null
						) {
					predicateList.add(cb.equal(root.<String> get("priority"), insertionOrderSearchRequest.getPriority()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getComments() != null
						) {
					predicateList.add(cb.equal(root.<String> get("comments"), insertionOrderSearchRequest.getComments()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getAdvertiserId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("advertiserId"), insertionOrderSearchRequest.getAdvertiserId()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("status"), insertionOrderSearchRequest.getStatus()));
				}
				else{
					predicateList.add(cb.greaterThanOrEqualTo(root.<Integer> get("status"), 1));
				}
				if (!excludeZeroes
						|| (insertionOrderSearchRequest.getCreatedTimeStart() != null || insertionOrderSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (insertionOrderSearchRequest.getCreatedTimeStart() != null
							&& insertionOrderSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								insertionOrderSearchRequest.getCreatedTimeStart(),insertionOrderSearchRequest
								.getCreatedTimeEnd()));
					} else if (insertionOrderSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), insertionOrderSearchRequest.getCreatedTimeStart()));
					} else if (insertionOrderSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), insertionOrderSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (insertionOrderSearchRequest.getUpdatedTimeStart() != null || insertionOrderSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (insertionOrderSearchRequest.getUpdatedTimeStart() != null
							&& insertionOrderSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								insertionOrderSearchRequest.getUpdatedTimeStart(),insertionOrderSearchRequest
								.getUpdatedTimeEnd()));
					} else if (insertionOrderSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), insertionOrderSearchRequest.getUpdatedTimeStart()));
					} else if (insertionOrderSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), insertionOrderSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), insertionOrderSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), insertionOrderSearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<InsertionOrder> searchOr(final InsertionOrderSearchRequest insertionOrderSearchRequest, final boolean excludeZeroes) {
		return new Specification<InsertionOrder>() {
			@Override
			public Predicate toPredicate(Root<InsertionOrder> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getInsertionOrderId() != null
						) {
					predicateList.add(cb.like(root.<String> get("insertionOrderId"), ("%" + insertionOrderSearchRequest.getInsertionOrderId()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCampaignName() != null
						) {
					predicateList.add(cb.like(root.<String> get("campaignName"), ("%" + insertionOrderSearchRequest.getCampaignName()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getDescription() != null
						) {
					predicateList.add(cb.like(root.<String> get("description"), ("%" + insertionOrderSearchRequest.getDescription()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCampaignObjective() != null
						) {
					predicateList.add(cb.like(root.<String> get("campaignObjective"), ("%" + insertionOrderSearchRequest.getCampaignObjective()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getOrderType() != null
						) {
					predicateList.add(cb.like(root.<String> get("orderType"), ("%" + insertionOrderSearchRequest.getOrderType()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getRevenueModel() != null
						) {
					predicateList.add(cb.like(root.<String> get("revenueModel"), ("%" + insertionOrderSearchRequest.getRevenueModel()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCompanionCampaign() != null
						) {
					predicateList.add(cb.like(root.<String> get("companionCampaign"), ("%" + insertionOrderSearchRequest.getCompanionCampaign()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCampaignStatus() != null
						) {
					predicateList.add(cb.like(root.<String> get("campaignStatus"), ("%" + insertionOrderSearchRequest.getCampaignStatus()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getPriority() != null
						) {
					predicateList.add(cb.like(root.<String> get("priority"), ("%" + insertionOrderSearchRequest.getPriority()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getComments() != null
						) {
					predicateList.add(cb.like(root.<String> get("comments"), ("%" + insertionOrderSearchRequest.getComments()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getAdvertiserId() != null
						) {
					predicateList.add(cb.like(root.<String> get("advertiserId"), ("%" + insertionOrderSearchRequest.getAdvertiserId()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + insertionOrderSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| insertionOrderSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + insertionOrderSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<InsertionOrder> searchByKeyword(final String keyword) {
		return new Specification<InsertionOrder>() {
			@Override
			public Predicate toPredicate(Root<InsertionOrder> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("insertionOrderId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("campaignName"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("description"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("campaignObjective"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("orderType"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("revenueModel"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("companionCampaign"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("campaignStatus"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("priority"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("comments"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("advertiserId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<InsertionOrder> loadAssociations(
	) {
		return new Specification<InsertionOrder>() {
			@Override
			public Predicate toPredicate(Root<InsertionOrder> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
