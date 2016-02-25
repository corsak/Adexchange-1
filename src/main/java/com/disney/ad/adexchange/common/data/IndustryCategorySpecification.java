package com.disney.ad.adexchange.common.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.common.domain.IndustryCategory;
import com.disney.ad.adexchange.common.domain.IndustryCategorySearchRequest;

public class IndustryCategorySpecification {

	public static Specification<IndustryCategory> searchAnd(final IndustryCategorySearchRequest industryCategorySearchRequest, final boolean excludeZeroes) {
		return new Specification<IndustryCategory>() {
			@Override
			public Predicate toPredicate(Root<IndustryCategory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| industryCategorySearchRequest.getName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("name"), industryCategorySearchRequest.getName()));
				}
				if (!excludeZeroes
						|| industryCategorySearchRequest.getDescription() != null
						) {
					predicateList.add(cb.equal(root.<String> get("description"), industryCategorySearchRequest.getDescription()));
				}
				if (!excludeZeroes
						|| industryCategorySearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("status"), industryCategorySearchRequest.getStatus()));
				}
				if (!excludeZeroes
						|| industryCategorySearchRequest.getParentID() != null
						) {
					predicateList.add(cb.equal(root.<String> get("parentID"), industryCategorySearchRequest.getParentID()));
				}
				if (!excludeZeroes
						|| (industryCategorySearchRequest.getCreatedTimeStart() != null || industryCategorySearchRequest
								.getCreatedTimeEnd() != null)) {
					if (industryCategorySearchRequest.getCreatedTimeStart() != null
							&& industryCategorySearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								industryCategorySearchRequest.getCreatedTimeStart(),industryCategorySearchRequest
								.getCreatedTimeEnd()));
					} else if (industryCategorySearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), industryCategorySearchRequest.getCreatedTimeStart()));
					} else if (industryCategorySearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), industryCategorySearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (industryCategorySearchRequest.getUpdatedTimeStart() != null || industryCategorySearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (industryCategorySearchRequest.getUpdatedTimeStart() != null
							&& industryCategorySearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								industryCategorySearchRequest.getUpdatedTimeStart(),industryCategorySearchRequest
								.getUpdatedTimeEnd()));
					} else if (industryCategorySearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), industryCategorySearchRequest.getUpdatedTimeStart()));
					} else if (industryCategorySearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), industryCategorySearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| industryCategorySearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), industryCategorySearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| industryCategorySearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), industryCategorySearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<IndustryCategory> searchOr(final IndustryCategorySearchRequest industryCategorySearchRequest, final boolean excludeZeroes) {
		return new Specification<IndustryCategory>() {
			@Override
			public Predicate toPredicate(Root<IndustryCategory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| industryCategorySearchRequest.getName() != null
						) {
					predicateList.add(cb.like(root.<String> get("name"), ("%" + industryCategorySearchRequest.getName()+ "%")));
				}
				if (!excludeZeroes
						|| industryCategorySearchRequest.getDescription() != null
						) {
					predicateList.add(cb.like(root.<String> get("description"), ("%" + industryCategorySearchRequest.getDescription()+ "%")));
				}
				if (!excludeZeroes
						|| industryCategorySearchRequest.getParentID() != null
						) {
					predicateList.add(cb.like(root.<String> get("parentID"), ("%" + industryCategorySearchRequest.getParentID()+ "%")));
				}
				if (!excludeZeroes
						|| industryCategorySearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + industryCategorySearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| industryCategorySearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + industryCategorySearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<IndustryCategory> searchByKeyword(final String keyword) {
		return new Specification<IndustryCategory>() {
			@Override
			public Predicate toPredicate(Root<IndustryCategory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("name"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("description"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("parentID"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<IndustryCategory> loadAssociations(
	) {
		return new Specification<IndustryCategory>() {
			@Override
			public Predicate toPredicate(Root<IndustryCategory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
