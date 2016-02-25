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

import com.disney.ad.adexchange.user.domain.User;
import com.disney.ad.adexchange.user.domain.UserList;
import com.disney.ad.adexchange.user.domain.UserSearchRequest;
import com.disney.ad.adexchange.user.domain.Advertiser;
import com.disney.ad.adexchange.user.domain.Publisher;
import com.disney.ad.adexchange.user.domain.Role;

public class UserSpecification {

	public static Specification<User> searchAnd(final UserSearchRequest userSearchRequest, final boolean excludeZeroes) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| userSearchRequest.getEmail() != null
						) {
					predicateList.add(cb.equal(root.<String> get("email"), userSearchRequest.getEmail()));
				}
				if (!excludeZeroes
						|| userSearchRequest.getLoginName() != null
						) {
					predicateList.add(cb.equal(root.<String> get("loginName"), userSearchRequest.getLoginName()));
				}
				if (!excludeZeroes
						|| userSearchRequest.getPassword() != null
						) {
					predicateList.add(cb.equal(root.<String> get("password"), userSearchRequest.getPassword()));
				}
				if (!excludeZeroes
						|| userSearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("status"), userSearchRequest.getStatus()));
				}
				else{
					predicateList.add(cb.greaterThanOrEqualTo(root.<Integer> get("status"), 1));
				}
				if (!excludeZeroes
						|| userSearchRequest.getUserType() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("userType"), userSearchRequest.getUserType()));
				}
				if (!excludeZeroes
						|| userSearchRequest.getHintQuestion1() != null
						) {
					predicateList.add(cb.equal(root.<String> get("hintQuestion1"), userSearchRequest.getHintQuestion1()));
				}
				if (!excludeZeroes
						|| userSearchRequest.getHintAnswer1() != null
						) {
					predicateList.add(cb.equal(root.<String> get("hintAnswer1"), userSearchRequest.getHintAnswer1()));
				}
				if (!excludeZeroes
						|| userSearchRequest.getHintQuestion2() != null
						) {
					predicateList.add(cb.equal(root.<String> get("hintQuestion2"), userSearchRequest.getHintQuestion2()));
				}
				if (!excludeZeroes
						|| userSearchRequest.getHintAnswer2() != null
						) {
					predicateList.add(cb.equal(root.<String> get("hintAnswer2"), userSearchRequest.getHintAnswer2()));
				}
				if (!excludeZeroes
						|| userSearchRequest.getIsAdmin() != null
						) {
					predicateList.add(cb.equal(root.<Boolean> get("isAdmin"), userSearchRequest.getIsAdmin()));
				}
				if (!excludeZeroes
						|| (userSearchRequest.getCreatedTimeStart() != null || userSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (userSearchRequest.getCreatedTimeStart() != null
							&& userSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								userSearchRequest.getCreatedTimeStart(),userSearchRequest
								.getCreatedTimeEnd()));
					} else if (userSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), userSearchRequest.getCreatedTimeStart()));
					} else if (userSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), userSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (userSearchRequest.getUpdatedTimeStart() != null || userSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (userSearchRequest.getUpdatedTimeStart() != null
							&& userSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								userSearchRequest.getUpdatedTimeStart(),userSearchRequest
								.getUpdatedTimeEnd()));
					} else if (userSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), userSearchRequest.getUpdatedTimeStart()));
					} else if (userSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), userSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| userSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), userSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| userSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), userSearchRequest.getUpdatedByUser()));
				}
				if (!excludeZeroes
						||  userSearchRequest.getAdvertiserid() != null) {
					final Path<Advertiser> advertiser = root.<Advertiser> get("advertiser");
					predicateList.add(cb.equal(advertiser.<String> get("advertiserId"), userSearchRequest.getAdvertiserid()));
				}
				if (!excludeZeroes
						||  userSearchRequest.getPublisherid() != null) {
					final Path<Publisher> publisher = root.<Publisher> get("publisher");
					predicateList.add(cb.equal(publisher.<String> get("publisherId"), userSearchRequest.getPublisherid()));
				}
				if (!excludeZeroes
						||  userSearchRequest.getRoleid() != null) {
					final Path<Role> role = root.<Role> get("role");
					predicateList.add(cb.equal(role.<String> get("roleId"), userSearchRequest.getRoleid()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<User> searchOr(final UserSearchRequest userSearchRequest, final boolean excludeZeroes) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| userSearchRequest.getEmail() != null
						) {
					predicateList.add(cb.like(root.<String> get("email"), ("%" + userSearchRequest.getEmail()+ "%")));
				}
				if (!excludeZeroes
						|| userSearchRequest.getLoginName() != null
						) {
					predicateList.add(cb.like(root.<String> get("loginName"), ("%" + userSearchRequest.getLoginName()+ "%")));
				}
				if (!excludeZeroes
						|| userSearchRequest.getPassword() != null
						) {
					predicateList.add(cb.like(root.<String> get("password"), ("%" + userSearchRequest.getPassword()+ "%")));
				}
				if (!excludeZeroes
						|| userSearchRequest.getHintQuestion1() != null
						) {
					predicateList.add(cb.like(root.<String> get("hintQuestion1"), ("%" + userSearchRequest.getHintQuestion1()+ "%")));
				}
				if (!excludeZeroes
						|| userSearchRequest.getHintAnswer1() != null
						) {
					predicateList.add(cb.like(root.<String> get("hintAnswer1"), ("%" + userSearchRequest.getHintAnswer1()+ "%")));
				}
				if (!excludeZeroes
						|| userSearchRequest.getHintQuestion2() != null
						) {
					predicateList.add(cb.like(root.<String> get("hintQuestion2"), ("%" + userSearchRequest.getHintQuestion2()+ "%")));
				}
				if (!excludeZeroes
						|| userSearchRequest.getHintAnswer2() != null
						) {
					predicateList.add(cb.like(root.<String> get("hintAnswer2"), ("%" + userSearchRequest.getHintAnswer2()+ "%")));
				}
				if (!excludeZeroes
						|| userSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + userSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| userSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + userSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<User> searchByKeyword(final String keyword) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("email"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("loginName"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("password"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("hintQuestion1"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("hintAnswer1"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("hintQuestion2"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("hintAnswer2"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	/*
	 * for user to get above 0
	 */
	public static Specification<User> getList(final UserSearchRequest userSearchRequest, final boolean excludeZeroes) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				
				if (!excludeZeroes
						|| userSearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("status"), userSearchRequest.getStatus()));
				}
				else{
					predicateList.add(cb.greaterThanOrEqualTo(root.<Integer> get("status"), 1));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<User> loadAssociations(
		final boolean loadAdvertiser,
		final boolean loadPublisher,
		final boolean loadRole	) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				Join<User, Advertiser> advertiserJoin = root.join("advertiser", JoinType.LEFT);
				predicateList.add(advertiserJoin.getOn());
				Join<User, Publisher> publisherJoin = root.join("publisher", JoinType.LEFT);
				predicateList.add(publisherJoin.getOn());
				Join<User, Role> roleJoin = root.join("role", JoinType.LEFT);
				predicateList.add(roleJoin.getOn());
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
