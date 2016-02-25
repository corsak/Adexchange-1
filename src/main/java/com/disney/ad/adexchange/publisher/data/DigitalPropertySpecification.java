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

import com.disney.ad.adexchange.publisher.domain.DigitalProperty;
import com.disney.ad.adexchange.publisher.domain.DigitalPropertySearchRequest;

public class DigitalPropertySpecification {

	public static Specification<DigitalProperty> searchAnd(final DigitalPropertySearchRequest digitalPropertySearchRequest, final boolean excludeZeroes) {
		return new Specification<DigitalProperty>() {
			@Override
			public Predicate toPredicate(Root<DigitalProperty> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getPropertyName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("propertyName"), digitalPropertySearchRequest.getPropertyName()));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getDescription() != null
						) {
					predicateList.add(cb.equal(root.<String> get("description"), digitalPropertySearchRequest.getDescription()));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getType() != null
						) {
					predicateList.add(cb.equal(root.<String> get("type"), digitalPropertySearchRequest.getType()));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getDomainURL() != null
						) {
					predicateList.add(cb.equal(root.<String> get("domainURL"), digitalPropertySearchRequest.getDomainURL()));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getPublisherId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("publisherId"), digitalPropertySearchRequest.getPublisherId()));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getIABCategory() != null
						) {
					predicateList.add(cb.equal(root.<String> get("IABCategory"), digitalPropertySearchRequest.getIABCategory()));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getIABSubCategory() != null
						) {
					predicateList.add(cb.equal(root.<String> get("IABSubCategory"), digitalPropertySearchRequest.getIABSubCategory()));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getCountry() != null
						) {
					predicateList.add(cb.equal(root.<String> get("country"), digitalPropertySearchRequest.getCountry()));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getLanguage() != null
						) {
					predicateList.add(cb.equal(root.<String> get("language"), digitalPropertySearchRequest.getLanguage()));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("status"), digitalPropertySearchRequest.getStatus()));
				}
				else{
					predicateList.add(cb.greaterThanOrEqualTo(root.<Integer> get("status"), 1));
				}
				if (!excludeZeroes
						|| (digitalPropertySearchRequest.getCreatedTimeStart() != null || digitalPropertySearchRequest
								.getCreatedTimeEnd() != null)) {
					if (digitalPropertySearchRequest.getCreatedTimeStart() != null
							&& digitalPropertySearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								digitalPropertySearchRequest.getCreatedTimeStart(),digitalPropertySearchRequest
								.getCreatedTimeEnd()));
					} else if (digitalPropertySearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), digitalPropertySearchRequest.getCreatedTimeStart()));
					} else if (digitalPropertySearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), digitalPropertySearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (digitalPropertySearchRequest.getUpdatedTimeStart() != null || digitalPropertySearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (digitalPropertySearchRequest.getUpdatedTimeStart() != null
							&& digitalPropertySearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								digitalPropertySearchRequest.getUpdatedTimeStart(),digitalPropertySearchRequest
								.getUpdatedTimeEnd()));
					} else if (digitalPropertySearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), digitalPropertySearchRequest.getUpdatedTimeStart()));
					} else if (digitalPropertySearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), digitalPropertySearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), digitalPropertySearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), digitalPropertySearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<DigitalProperty> searchOr(final DigitalPropertySearchRequest digitalPropertySearchRequest, final boolean excludeZeroes) {
		return new Specification<DigitalProperty>() {
			@Override
			public Predicate toPredicate(Root<DigitalProperty> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getPropertyName() != null
						) {
					predicateList.add(cb.like(root.<String> get("propertyName"), ("%" + digitalPropertySearchRequest.getPropertyName()+ "%")));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getDescription() != null
						) {
					predicateList.add(cb.like(root.<String> get("description"), ("%" + digitalPropertySearchRequest.getDescription()+ "%")));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getType() != null
						) {
					predicateList.add(cb.like(root.<String> get("type"), ("%" + digitalPropertySearchRequest.getType()+ "%")));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getDomainURL() != null
						) {
					predicateList.add(cb.like(root.<String> get("domainURL"), ("%" + digitalPropertySearchRequest.getDomainURL()+ "%")));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getPublisherId() != null
						) {
					predicateList.add(cb.like(root.<String> get("publisherId"), ("%" + digitalPropertySearchRequest.getPublisherId()+ "%")));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getIABCategory() != null
						) {
					predicateList.add(cb.like(root.<String> get("IABCategory"), ("%" + digitalPropertySearchRequest.getIABCategory()+ "%")));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getIABSubCategory() != null
						) {
					predicateList.add(cb.like(root.<String> get("IABSubCategory"), ("%" + digitalPropertySearchRequest.getIABSubCategory()+ "%")));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getCountry() != null
						) {
					predicateList.add(cb.like(root.<String> get("country"), ("%" + digitalPropertySearchRequest.getCountry()+ "%")));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getLanguage() != null
						) {
					predicateList.add(cb.like(root.<String> get("language"), ("%" + digitalPropertySearchRequest.getLanguage()+ "%")));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + digitalPropertySearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| digitalPropertySearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + digitalPropertySearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<DigitalProperty> searchByKeyword(final String keyword) {
		return new Specification<DigitalProperty>() {
			@Override
			public Predicate toPredicate(Root<DigitalProperty> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("propertyName"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("description"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("type"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("domainURL"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("publisherId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("IABCategory"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("IABSubCategory"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("country"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("language"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<DigitalProperty> loadAssociations(
	) {
		return new Specification<DigitalProperty>() {
			@Override
			public Predicate toPredicate(Root<DigitalProperty> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
