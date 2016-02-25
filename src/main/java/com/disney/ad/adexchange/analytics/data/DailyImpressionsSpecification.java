package com.disney.ad.adexchange.analytics.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.analytics.domain.DailyImpressions;
import com.disney.ad.adexchange.analytics.domain.DailyImpressionsSearchRequest;

public class DailyImpressionsSpecification {

	public static Specification<DailyImpressions> searchAnd(final DailyImpressionsSearchRequest dailyImpressionsSearchRequest, final boolean excludeZeroes) {
		return new Specification<DailyImpressions>() {
			@Override
			public Predicate toPredicate(Root<DailyImpressions> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getPublisherId() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("publisherId"), dailyImpressionsSearchRequest.getPublisherId()));
				}
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getDigitalPropertyid() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("digitalPropertyid"), dailyImpressionsSearchRequest.getDigitalPropertyid()));
				}
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getLineItemId() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("lineItemId"), dailyImpressionsSearchRequest.getLineItemId()));
				}
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getZoneId() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("zoneId"), dailyImpressionsSearchRequest.getZoneId()));
				}
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getNoOfImpressions() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("noOfImpressions"), dailyImpressionsSearchRequest.getNoOfImpressions()));
				}
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getNoOfClicks() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("noOfClicks"), dailyImpressionsSearchRequest.getNoOfClicks()));
				}
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getDate() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("date"), dailyImpressionsSearchRequest.getDate()));
				}
				if (!excludeZeroes
						|| (dailyImpressionsSearchRequest.getCreatedTimeStart() != null || dailyImpressionsSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (dailyImpressionsSearchRequest.getCreatedTimeStart() != null
							&& dailyImpressionsSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								dailyImpressionsSearchRequest.getCreatedTimeStart(),dailyImpressionsSearchRequest
								.getCreatedTimeEnd()));
					} else if (dailyImpressionsSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), dailyImpressionsSearchRequest.getCreatedTimeStart()));
					} else if (dailyImpressionsSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), dailyImpressionsSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (dailyImpressionsSearchRequest.getUpdatedTimeStart() != null || dailyImpressionsSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (dailyImpressionsSearchRequest.getUpdatedTimeStart() != null
							&& dailyImpressionsSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								dailyImpressionsSearchRequest.getUpdatedTimeStart(),dailyImpressionsSearchRequest
								.getUpdatedTimeEnd()));
					} else if (dailyImpressionsSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), dailyImpressionsSearchRequest.getUpdatedTimeStart()));
					} else if (dailyImpressionsSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), dailyImpressionsSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), dailyImpressionsSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), dailyImpressionsSearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<DailyImpressions> searchOr(final DailyImpressionsSearchRequest dailyImpressionsSearchRequest, final boolean excludeZeroes) {
		return new Specification<DailyImpressions>() {
			@Override
			public Predicate toPredicate(Root<DailyImpressions> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + dailyImpressionsSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| dailyImpressionsSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + dailyImpressionsSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<DailyImpressions> searchByKeyword(final String keyword) {
		return new Specification<DailyImpressions>() {
			@Override
			public Predicate toPredicate(Root<DailyImpressions> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<DailyImpressions> loadAssociations(
	) {
		return new Specification<DailyImpressions>() {
			@Override
			public Predicate toPredicate(Root<DailyImpressions> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
