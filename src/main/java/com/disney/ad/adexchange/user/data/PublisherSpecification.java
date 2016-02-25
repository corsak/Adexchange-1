package com.disney.ad.adexchange.user.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.user.domain.Publisher;
import com.disney.ad.adexchange.user.domain.PublisherSearchRequest;
import com.disney.ad.adexchange.user.domain.User;

public class PublisherSpecification {

	public static Specification<Publisher> searchAnd(final PublisherSearchRequest publisherSearchRequest, final boolean excludeZeroes) {
		return new Specification<Publisher>() {
			@Override
			public Predicate toPredicate(Root<Publisher> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| publisherSearchRequest.getPublisherId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("publisherId"), publisherSearchRequest.getPublisherId()));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getPublisherName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("publisherName"), publisherSearchRequest.getPublisherName()));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getCompany() != null
						) {
					predicateList.add(cb.equal(root.<String> get("company"), publisherSearchRequest.getCompany()));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getContactName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("contactName"), publisherSearchRequest.getContactName()));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getContactNumber() != null
						) {
					predicateList.add(cb.equal(root.<String> get("contactNumber"), publisherSearchRequest.getContactNumber()));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getCountry() != null
						) {
					predicateList.add(cb.equal(root.<String> get("country"), publisherSearchRequest.getCountry()));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getPrimaryDomain() != null
						) {
					predicateList.add(cb.equal(root.<String> get("primaryDomain"), publisherSearchRequest.getPrimaryDomain()));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getIabCategory() != null
						) {
					predicateList.add(cb.equal(root.<String> get("iabCategory"), publisherSearchRequest.getIabCategory()));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getAddress() != null
						) {
					predicateList.add(cb.equal(root.<String> get("address"), publisherSearchRequest.getAddress()));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("status"), publisherSearchRequest.getStatus()));
				}
				if (!excludeZeroes
						|| (publisherSearchRequest.getCreatedTimeStart() != null || publisherSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (publisherSearchRequest.getCreatedTimeStart() != null
							&& publisherSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								publisherSearchRequest.getCreatedTimeStart(),publisherSearchRequest
								.getCreatedTimeEnd()));
					} else if (publisherSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), publisherSearchRequest.getCreatedTimeStart()));
					} else if (publisherSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), publisherSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (publisherSearchRequest.getUpdatedTimeStart() != null || publisherSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (publisherSearchRequest.getUpdatedTimeStart() != null
							&& publisherSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								publisherSearchRequest.getUpdatedTimeStart(),publisherSearchRequest
								.getUpdatedTimeEnd()));
					} else if (publisherSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), publisherSearchRequest.getUpdatedTimeStart()));
					} else if (publisherSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), publisherSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), publisherSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), publisherSearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Publisher> searchOr(final PublisherSearchRequest publisherSearchRequest, final boolean excludeZeroes) {
		return new Specification<Publisher>() {
			@Override
			public Predicate toPredicate(Root<Publisher> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| publisherSearchRequest.getPublisherId() != null
						) {
					predicateList.add(cb.like(root.<String> get("publisherId"), ("%" + publisherSearchRequest.getPublisherId()+ "%")));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getPublisherName() != null
						) {
					predicateList.add(cb.like(root.<String> get("publisherName"), ("%" + publisherSearchRequest.getPublisherName()+ "%")));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getCompany() != null
						) {
					predicateList.add(cb.like(root.<String> get("company"), ("%" + publisherSearchRequest.getCompany()+ "%")));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getContactName() != null
						) {
					predicateList.add(cb.like(root.<String> get("contactName"), ("%" + publisherSearchRequest.getContactName()+ "%")));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getContactNumber() != null
						) {
					predicateList.add(cb.like(root.<String> get("contactNumber"), ("%" + publisherSearchRequest.getContactNumber()+ "%")));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getCountry() != null
						) {
					predicateList.add(cb.like(root.<String> get("country"), ("%" + publisherSearchRequest.getCountry()+ "%")));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getPrimaryDomain() != null
						) {
					predicateList.add(cb.like(root.<String> get("primaryDomain"), ("%" + publisherSearchRequest.getPrimaryDomain()+ "%")));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getIabCategory() != null
						) {
					predicateList.add(cb.like(root.<String> get("iabCategory"), ("%" + publisherSearchRequest.getIabCategory()+ "%")));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getAddress() != null
						) {
					predicateList.add(cb.like(root.<String> get("address"), ("%" + publisherSearchRequest.getAddress()+ "%")));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + publisherSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| publisherSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + publisherSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Publisher> searchByKeyword(final String keyword) {
		return new Specification<Publisher>() {
			@Override
			public Predicate toPredicate(Root<Publisher> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("publisherId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("publisherName"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("company"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("contactName"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("contactNumber"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("country"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("primaryDomain"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("iabCategory"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("address"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<Publisher> loadAssociations(
		final boolean loadUsers	) {
		return new Specification<Publisher>() {
			@Override
			public Predicate toPredicate(Root<Publisher> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				Join<Publisher, User> usersJoin = root.join("users", JoinType.LEFT);
				predicateList.add(usersJoin.getOn());
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
