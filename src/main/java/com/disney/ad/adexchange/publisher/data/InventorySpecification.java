package com.disney.ad.adexchange.publisher.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.publisher.domain.Inventory;
import com.disney.ad.adexchange.publisher.domain.InventorySearchRequest;

public class InventorySpecification {

	public static Specification<Inventory> searchAnd(final InventorySearchRequest inventorySearchRequest, final boolean excludeZeroes) {
		return new Specification<Inventory>() {
			@Override
			public Predicate toPredicate(Root<Inventory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| inventorySearchRequest.getZoneID() != null
						) {
					predicateList.add(cb.equal(root.<String> get("zoneID"), inventorySearchRequest.getZoneID()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getDigitalPropertyId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("digitalPropertyId"), inventorySearchRequest.getDigitalPropertyId()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getInventoryName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("inventoryName"), inventorySearchRequest.getInventoryName()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getDescription() != null
						) {
					predicateList.add(cb.equal(root.<String> get("description"), inventorySearchRequest.getDescription()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getZoneType() != null
						) {
					predicateList.add(cb.equal(root.<String> get("zoneType"), inventorySearchRequest.getZoneType()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getAdType() != null
						) {
					predicateList.add(cb.equal(root.<String> get("adType"), inventorySearchRequest.getAdType()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getAdTemplateID() != null
						) {
					predicateList.add(cb.equal(root.<String> get("adTemplateID"), inventorySearchRequest.getAdTemplateID()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getAdWidth() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("adWidth"), inventorySearchRequest.getAdWidth()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getAdHeight() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("adHeight"), inventorySearchRequest.getAdHeight()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getAdInvocationTag() != null
						) {
					predicateList.add(cb.equal(root.<String> get("adInvocationTag"), inventorySearchRequest.getAdInvocationTag()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getPassbackAdTag() != null
						) {
					predicateList.add(cb.equal(root.<String> get("passbackAdTag"), inventorySearchRequest.getPassbackAdTag()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getFloorPrice() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("floorPrice"), inventorySearchRequest.getFloorPrice()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getTotalRequests() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("totalRequests"), inventorySearchRequest.getTotalRequests()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getTotalImpressions() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("totalImpressions"), inventorySearchRequest.getTotalImpressions()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getTotalRevenues() != null
						) {
					predicateList.add(cb.equal(root.<Float> get("totalRevenues"), inventorySearchRequest.getTotalRevenues()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getKeywords() != null
						) {
					predicateList.add(cb.equal(root.<String> get("keywords"), inventorySearchRequest.getKeywords()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("status"), inventorySearchRequest.getStatus()));
				}
				else{
					predicateList.add(cb.greaterThanOrEqualTo(root.<Integer> get("status"), 1));
				}
				if (!excludeZeroes
						|| (inventorySearchRequest.getCreatedTimeStart() != null || inventorySearchRequest
								.getCreatedTimeEnd() != null)) {
					if (inventorySearchRequest.getCreatedTimeStart() != null
							&& inventorySearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								inventorySearchRequest.getCreatedTimeStart(),inventorySearchRequest
								.getCreatedTimeEnd()));
					} else if (inventorySearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), inventorySearchRequest.getCreatedTimeStart()));
					} else if (inventorySearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), inventorySearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (inventorySearchRequest.getUpdatedTimeStart() != null || inventorySearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (inventorySearchRequest.getUpdatedTimeStart() != null
							&& inventorySearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								inventorySearchRequest.getUpdatedTimeStart(),inventorySearchRequest
								.getUpdatedTimeEnd()));
					} else if (inventorySearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), inventorySearchRequest.getUpdatedTimeStart()));
					} else if (inventorySearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), inventorySearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), inventorySearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), inventorySearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Inventory> searchOr(final InventorySearchRequest inventorySearchRequest, final boolean excludeZeroes) {
		return new Specification<Inventory>() {
			@Override
			public Predicate toPredicate(Root<Inventory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| inventorySearchRequest.getZoneID() != null
						) {
					predicateList.add(cb.like(root.<String> get("zoneID"), ("%" + inventorySearchRequest.getZoneID()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getDigitalPropertyId() != null
						) {
					predicateList.add(cb.like(root.<String> get("digitalPropertyId"), ("%" + inventorySearchRequest.getDigitalPropertyId()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getInventoryName() != null
						) {
					predicateList.add(cb.like(root.<String> get("inventoryName"), ("%" + inventorySearchRequest.getInventoryName()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getDescription() != null
						) {
					predicateList.add(cb.like(root.<String> get("description"), ("%" + inventorySearchRequest.getDescription()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getZoneType() != null
						) {
					predicateList.add(cb.like(root.<String> get("zoneType"), ("%" + inventorySearchRequest.getZoneType()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getAdType() != null
						) {
					predicateList.add(cb.like(root.<String> get("adType"), ("%" + inventorySearchRequest.getAdType()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getAdTemplateID() != null
						) {
					predicateList.add(cb.like(root.<String> get("adTemplateID"), ("%" + inventorySearchRequest.getAdTemplateID()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getAdInvocationTag() != null
						) {
					predicateList.add(cb.like(root.<String> get("adInvocationTag"), ("%" + inventorySearchRequest.getAdInvocationTag()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getPassbackAdTag() != null
						) {
					predicateList.add(cb.like(root.<String> get("passbackAdTag"), ("%" + inventorySearchRequest.getPassbackAdTag()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getKeywords() != null
						) {
					predicateList.add(cb.like(root.<String> get("keywords"), ("%" + inventorySearchRequest.getKeywords()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + inventorySearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + inventorySearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Inventory> searchByKeyword(final String keyword) {
		return new Specification<Inventory>() {
			@Override
			public Predicate toPredicate(Root<Inventory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("zoneID"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("digitalPropertyId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("inventoryName"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("description"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("zoneType"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("adType"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("adTemplateID"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("adInvocationTag"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("passbackAdTag"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("keywords"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<Inventory> loadAssociations(
	) {
		return new Specification<Inventory>() {
			@Override
			public Predicate toPredicate(Root<Inventory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
