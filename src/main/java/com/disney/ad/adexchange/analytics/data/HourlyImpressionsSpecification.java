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

import com.disney.ad.adexchange.analytics.domain.HourlyImpressions;
import com.disney.ad.adexchange.analytics.domain.HourlyImpressionsSearchRequest;

public class HourlyImpressionsSpecification {

	public static Specification<HourlyImpressions> searchAnd(final HourlyImpressionsSearchRequest hourlyImpressionsSearchRequest, final boolean excludeZeroes) {
		return new Specification<HourlyImpressions>() {
			@Override
			public Predicate toPredicate(Root<HourlyImpressions> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getPublisherId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("publisherId"), hourlyImpressionsSearchRequest.getPublisherId()));
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getDigitalPropertyid() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("digitalPropertyid"), hourlyImpressionsSearchRequest.getDigitalPropertyid()));
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getLineItemId() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("lineItemId"), hourlyImpressionsSearchRequest.getLineItemId()));
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getZoneId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("zoneId"), hourlyImpressionsSearchRequest.getZoneId()));
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getNoOfImpressions() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("noOfImpressions"), hourlyImpressionsSearchRequest.getNoOfImpressions()));
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getNoOfClicks() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("noOfClicks"), hourlyImpressionsSearchRequest.getNoOfClicks()));
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getDateTime() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("dateTime"), hourlyImpressionsSearchRequest.getDateTime()));
				}
				if (!excludeZeroes
						|| (hourlyImpressionsSearchRequest.getCreatedTimeStart() != null || hourlyImpressionsSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (hourlyImpressionsSearchRequest.getCreatedTimeStart() != null
							&& hourlyImpressionsSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								hourlyImpressionsSearchRequest.getCreatedTimeStart(),hourlyImpressionsSearchRequest
								.getCreatedTimeEnd()));
					} else if (hourlyImpressionsSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), hourlyImpressionsSearchRequest.getCreatedTimeStart()));
					} else if (hourlyImpressionsSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), hourlyImpressionsSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (hourlyImpressionsSearchRequest.getUpdatedTimeStart() != null || hourlyImpressionsSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (hourlyImpressionsSearchRequest.getUpdatedTimeStart() != null
							&& hourlyImpressionsSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								hourlyImpressionsSearchRequest.getUpdatedTimeStart(),hourlyImpressionsSearchRequest
								.getUpdatedTimeEnd()));
					} else if (hourlyImpressionsSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), hourlyImpressionsSearchRequest.getUpdatedTimeStart()));
					} else if (hourlyImpressionsSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), hourlyImpressionsSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), hourlyImpressionsSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), hourlyImpressionsSearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<HourlyImpressions> searchOr(final HourlyImpressionsSearchRequest hourlyImpressionsSearchRequest, final boolean excludeZeroes) {
		return new Specification<HourlyImpressions>() {
			@Override
			public Predicate toPredicate(Root<HourlyImpressions> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getPublisherId() != null
						) {
					predicateList.add(cb.like(root.<String> get("publisherId"), ("%" + hourlyImpressionsSearchRequest.getPublisherId()+ "%")));
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getZoneId() != null
						) {
					predicateList.add(cb.like(root.<String> get("zoneId"), ("%" + hourlyImpressionsSearchRequest.getZoneId()+ "%")));
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + hourlyImpressionsSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| hourlyImpressionsSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + hourlyImpressionsSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<HourlyImpressions> searchByKeyword(final String keyword) {
		return new Specification<HourlyImpressions>() {
			@Override
			public Predicate toPredicate(Root<HourlyImpressions> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("publisherId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("zoneId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<HourlyImpressions> loadAssociations(
	) {
		return new Specification<HourlyImpressions>() {
			@Override
			public Predicate toPredicate(Root<HourlyImpressions> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
