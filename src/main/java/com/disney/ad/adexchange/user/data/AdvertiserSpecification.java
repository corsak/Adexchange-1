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

import com.disney.ad.adexchange.user.domain.Advertiser;
import com.disney.ad.adexchange.user.domain.AdvertiserSearchRequest;
import com.disney.ad.adexchange.user.domain.User;

public class AdvertiserSpecification {

	public static Specification<Advertiser> searchAnd(final AdvertiserSearchRequest advertiserSearchRequest, final boolean excludeZeroes) {
		return new Specification<Advertiser>() {
			@Override
			public Predicate toPredicate(Root<Advertiser> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| advertiserSearchRequest.getAdvertiserId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("advertiserId"), advertiserSearchRequest.getAdvertiserId()));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getAdvertiserName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("advertiserName"), advertiserSearchRequest.getAdvertiserName()));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getCompany() != null
						) {
					predicateList.add(cb.equal(root.<String> get("company"), advertiserSearchRequest.getCompany()));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getContactName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("contactName"), advertiserSearchRequest.getContactName()));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getContactNumber() != null
						) {
					predicateList.add(cb.equal(root.<String> get("contactNumber"), advertiserSearchRequest.getContactNumber()));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getCountry() != null
						) {
					predicateList.add(cb.equal(root.<String> get("country"), advertiserSearchRequest.getCountry()));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getDescription() != null
						) {
					predicateList.add(cb.equal(root.<String> get("description"), advertiserSearchRequest.getDescription()));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getPartnerType() != null
						) {
					predicateList.add(cb.equal(root.<String> get("partnerType"), advertiserSearchRequest.getPartnerType()));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<String> get("status"), advertiserSearchRequest.getStatus()));
				}
				if (!excludeZeroes
						|| (advertiserSearchRequest.getCreatedTimeStart() != null || advertiserSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (advertiserSearchRequest.getCreatedTimeStart() != null
							&& advertiserSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								advertiserSearchRequest.getCreatedTimeStart(),advertiserSearchRequest
								.getCreatedTimeEnd()));
					} else if (advertiserSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), advertiserSearchRequest.getCreatedTimeStart()));
					} else if (advertiserSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), advertiserSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (advertiserSearchRequest.getUpdatedTimeStart() != null || advertiserSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (advertiserSearchRequest.getUpdatedTimeStart() != null
							&& advertiserSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								advertiserSearchRequest.getUpdatedTimeStart(),advertiserSearchRequest
								.getUpdatedTimeEnd()));
					} else if (advertiserSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), advertiserSearchRequest.getUpdatedTimeStart()));
					} else if (advertiserSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), advertiserSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), advertiserSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), advertiserSearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Advertiser> searchOr(final AdvertiserSearchRequest advertiserSearchRequest, final boolean excludeZeroes) {
		return new Specification<Advertiser>() {
			@Override
			public Predicate toPredicate(Root<Advertiser> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| advertiserSearchRequest.getAdvertiserId() != null
						) {
					predicateList.add(cb.like(root.<String> get("advertiserId"), ("%" + advertiserSearchRequest.getAdvertiserId()+ "%")));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getAdvertiserName() != null
						) {
					predicateList.add(cb.like(root.<String> get("advertiserName"), ("%" + advertiserSearchRequest.getAdvertiserName()+ "%")));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getCompany() != null
						) {
					predicateList.add(cb.like(root.<String> get("company"), ("%" + advertiserSearchRequest.getCompany()+ "%")));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getContactName() != null
						) {
					predicateList.add(cb.like(root.<String> get("contactName"), ("%" + advertiserSearchRequest.getContactName()+ "%")));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getContactNumber() != null
						) {
					predicateList.add(cb.like(root.<String> get("contactNumber"), ("%" + advertiserSearchRequest.getContactNumber()+ "%")));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getCountry() != null
						) {
					predicateList.add(cb.like(root.<String> get("country"), ("%" + advertiserSearchRequest.getCountry()+ "%")));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getDescription() != null
						) {
					predicateList.add(cb.like(root.<String> get("description"), ("%" + advertiserSearchRequest.getDescription()+ "%")));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getPartnerType() != null
						) {
					predicateList.add(cb.like(root.<String> get("partnerType"), ("%" + advertiserSearchRequest.getPartnerType()+ "%")));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getStatus() != null
						) {
					predicateList.add(cb.like(root.<String> get("status"), ("%" + advertiserSearchRequest.getStatus()+ "%")));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + advertiserSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| advertiserSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + advertiserSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Advertiser> searchByKeyword(final String keyword) {
		return new Specification<Advertiser>() {
			@Override
			public Predicate toPredicate(Root<Advertiser> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("advertiserId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("advertiserName"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("company"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("contactName"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("contactNumber"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("country"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("description"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("partnerType"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("status"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<Advertiser> loadAssociations(
		final boolean loadUsers	) {
		return new Specification<Advertiser>() {
			@Override
			public Predicate toPredicate(Root<Advertiser> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				Join<Advertiser, User> usersJoin = root.join("users", JoinType.LEFT);
				predicateList.add(usersJoin.getOn());
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
