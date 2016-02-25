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

import com.disney.ad.adexchange.common.domain.AdTemplates;
import com.disney.ad.adexchange.common.domain.AdTemplatesSearchRequest;

public class AdTemplatesSpecification {

	public static Specification<AdTemplates> searchAnd(final AdTemplatesSearchRequest adTemplatesSearchRequest, final boolean excludeZeroes) {
		return new Specification<AdTemplates>() {
			@Override
			public Predicate toPredicate(Root<AdTemplates> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getAdTemplateID() != null
						) {
					predicateList.add(cb.equal(root.<String> get("adTemplateID"), adTemplatesSearchRequest.getAdTemplateID()));
				}
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getTemplateName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("templateName"), adTemplatesSearchRequest.getTemplateName()));
				}
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getIsMobileFlag() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("isMobileFlag"), adTemplatesSearchRequest.getIsMobileFlag()));
				}
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getWidth() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("width"), adTemplatesSearchRequest.getWidth()));
				}
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getHeight() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("height"), adTemplatesSearchRequest.getHeight()));
				}
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("status"), adTemplatesSearchRequest.getStatus()));
				}
				if (!excludeZeroes
						|| (adTemplatesSearchRequest.getCreatedTimeStart() != null || adTemplatesSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (adTemplatesSearchRequest.getCreatedTimeStart() != null
							&& adTemplatesSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								adTemplatesSearchRequest.getCreatedTimeStart(),adTemplatesSearchRequest
								.getCreatedTimeEnd()));
					} else if (adTemplatesSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), adTemplatesSearchRequest.getCreatedTimeStart()));
					} else if (adTemplatesSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), adTemplatesSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (adTemplatesSearchRequest.getUpdatedTimeStart() != null || adTemplatesSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (adTemplatesSearchRequest.getUpdatedTimeStart() != null
							&& adTemplatesSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								adTemplatesSearchRequest.getUpdatedTimeStart(),adTemplatesSearchRequest
								.getUpdatedTimeEnd()));
					} else if (adTemplatesSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), adTemplatesSearchRequest.getUpdatedTimeStart()));
					} else if (adTemplatesSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), adTemplatesSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), adTemplatesSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), adTemplatesSearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<AdTemplates> searchOr(final AdTemplatesSearchRequest adTemplatesSearchRequest, final boolean excludeZeroes) {
		return new Specification<AdTemplates>() {
			@Override
			public Predicate toPredicate(Root<AdTemplates> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getAdTemplateID() != null
						) {
					predicateList.add(cb.like(root.<String> get("adTemplateID"), ("%" + adTemplatesSearchRequest.getAdTemplateID()+ "%")));
				}
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getTemplateName() != null
						) {
					predicateList.add(cb.like(root.<String> get("templateName"), ("%" + adTemplatesSearchRequest.getTemplateName()+ "%")));
				}
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + adTemplatesSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| adTemplatesSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + adTemplatesSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<AdTemplates> searchByKeyword(final String keyword) {
		return new Specification<AdTemplates>() {
			@Override
			public Predicate toPredicate(Root<AdTemplates> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("adTemplateID"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("templateName"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<AdTemplates> loadAssociations(
	) {
		return new Specification<AdTemplates>() {
			@Override
			public Predicate toPredicate(Root<AdTemplates> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
