package com.disney.ad.adexchange.user.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.user.domain.UserSession;
import com.disney.ad.adexchange.user.domain.UserSessionSearchRequest;

public class UserSessionSpecification {

	public static Specification<UserSession> searchAnd(final UserSessionSearchRequest userSessionSearchRequest, final boolean excludeZeroes) {
		return new Specification<UserSession>() {
			@Override
			public Predicate toPredicate(Root<UserSession> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| userSessionSearchRequest.getUserId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("userId"), userSessionSearchRequest.getUserId()));
				}
				if (!excludeZeroes
						|| userSessionSearchRequest.getUserSessionId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("userSessionId"), userSessionSearchRequest.getUserSessionId()));
				}
				if (!excludeZeroes
						|| userSessionSearchRequest.getAuthToken() != null
						) {
					predicateList.add(cb.equal(root.<String> get("authToken"), userSessionSearchRequest.getAuthToken()));
				}
				if (!excludeZeroes
						|| (userSessionSearchRequest.getLastAccessTimeStart() != null || userSessionSearchRequest
								.getLastAccessTimeEnd() != null)) {
					if (userSessionSearchRequest.getLastAccessTimeStart() != null
							&& userSessionSearchRequest.getLastAccessTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("lastAccessTime"),
								userSessionSearchRequest.getLastAccessTimeStart(),userSessionSearchRequest
								.getLastAccessTimeEnd()));
					} else if (userSessionSearchRequest.getLastAccessTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("lastAccessTime"), userSessionSearchRequest.getLastAccessTimeStart()));
					} else if (userSessionSearchRequest.getLastAccessTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("lastAccessTime"), userSessionSearchRequest.getLastAccessTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (userSessionSearchRequest.getLastLoggedInTimeStart() != null || userSessionSearchRequest
								.getLastLoggedInTimeEnd() != null)) {
					if (userSessionSearchRequest.getLastLoggedInTimeStart() != null
							&& userSessionSearchRequest.getLastLoggedInTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("lastLoggedInTime"),
								userSessionSearchRequest.getLastLoggedInTimeStart(),userSessionSearchRequest
								.getLastLoggedInTimeEnd()));
					} else if (userSessionSearchRequest.getLastLoggedInTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("lastLoggedInTime"), userSessionSearchRequest.getLastLoggedInTimeStart()));
					} else if (userSessionSearchRequest.getLastLoggedInTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("lastLoggedInTime"), userSessionSearchRequest.getLastLoggedInTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (userSessionSearchRequest.getCreatedTimeStart() != null || userSessionSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (userSessionSearchRequest.getCreatedTimeStart() != null
							&& userSessionSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								userSessionSearchRequest.getCreatedTimeStart(),userSessionSearchRequest
								.getCreatedTimeEnd()));
					} else if (userSessionSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), userSessionSearchRequest.getCreatedTimeStart()));
					} else if (userSessionSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), userSessionSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (userSessionSearchRequest.getUpdatedTimeStart() != null || userSessionSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (userSessionSearchRequest.getUpdatedTimeStart() != null
							&& userSessionSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								userSessionSearchRequest.getUpdatedTimeStart(),userSessionSearchRequest
								.getUpdatedTimeEnd()));
					} else if (userSessionSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), userSessionSearchRequest.getUpdatedTimeStart()));
					} else if (userSessionSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), userSessionSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| userSessionSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), userSessionSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| userSessionSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), userSessionSearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<UserSession> searchOr(final UserSessionSearchRequest userSessionSearchRequest, final boolean excludeZeroes) {
		return new Specification<UserSession>() {
			@Override
			public Predicate toPredicate(Root<UserSession> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| userSessionSearchRequest.getUserId() != null
						) {
					predicateList.add(cb.like(root.<String> get("userId"), ("%" + userSessionSearchRequest.getUserId()+ "%")));
				}
				if (!excludeZeroes
						|| userSessionSearchRequest.getUserSessionId() != null
						) {
					predicateList.add(cb.like(root.<String> get("userSessionId"), ("%" + userSessionSearchRequest.getUserSessionId()+ "%")));
				}
				if (!excludeZeroes
						|| userSessionSearchRequest.getAuthToken() != null
						) {
					predicateList.add(cb.like(root.<String> get("authToken"), ("%" + userSessionSearchRequest.getAuthToken()+ "%")));
				}
				if (!excludeZeroes
						|| userSessionSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + userSessionSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| userSessionSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + userSessionSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<UserSession> searchByKeyword(final String keyword) {
		return new Specification<UserSession>() {
			@Override
			public Predicate toPredicate(Root<UserSession> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("userId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("userSessionId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("authToken"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<UserSession> loadAssociations(
	) {
		return new Specification<UserSession>() {
			@Override
			public Predicate toPredicate(Root<UserSession> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
