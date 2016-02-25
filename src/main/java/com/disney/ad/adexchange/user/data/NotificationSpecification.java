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

import com.disney.ad.adexchange.user.domain.Notification;
import com.disney.ad.adexchange.user.domain.NotificationSearchRequest;

public class NotificationSpecification {

	public static Specification<Notification> searchAnd(final NotificationSearchRequest notificationSearchRequest, final boolean excludeZeroes) {
		return new Specification<Notification>() {
			@Override
			public Predicate toPredicate(Root<Notification> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| notificationSearchRequest.getNotificationId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("notificationId"), notificationSearchRequest.getNotificationId()));
				}
				if (!excludeZeroes
						|| notificationSearchRequest.getUserId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("userId"), notificationSearchRequest.getUserId()));
				}
				if (!excludeZeroes
						|| notificationSearchRequest.getDeviceOS() != null
						) {
					predicateList.add(cb.equal(root.<String> get("deviceOS"), notificationSearchRequest.getDeviceOS()));
				}
				if (!excludeZeroes
						|| notificationSearchRequest.getSource() != null
						) {
					predicateList.add(cb.equal(root.<String> get("source"), notificationSearchRequest.getSource()));
				}
				if (!excludeZeroes
						|| (notificationSearchRequest.getCreatedTimeStart() != null || notificationSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (notificationSearchRequest.getCreatedTimeStart() != null
							&& notificationSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								notificationSearchRequest.getCreatedTimeStart(),notificationSearchRequest
								.getCreatedTimeEnd()));
					} else if (notificationSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), notificationSearchRequest.getCreatedTimeStart()));
					} else if (notificationSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), notificationSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (notificationSearchRequest.getUpdatedTimeStart() != null || notificationSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (notificationSearchRequest.getUpdatedTimeStart() != null
							&& notificationSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								notificationSearchRequest.getUpdatedTimeStart(),notificationSearchRequest
								.getUpdatedTimeEnd()));
					} else if (notificationSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), notificationSearchRequest.getUpdatedTimeStart()));
					} else if (notificationSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), notificationSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| notificationSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), notificationSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| notificationSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), notificationSearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Notification> searchOr(final NotificationSearchRequest notificationSearchRequest, final boolean excludeZeroes) {
		return new Specification<Notification>() {
			@Override
			public Predicate toPredicate(Root<Notification> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| notificationSearchRequest.getNotificationId() != null
						) {
					predicateList.add(cb.like(root.<String> get("notificationId"), ("%" + notificationSearchRequest.getNotificationId()+ "%")));
				}
				if (!excludeZeroes
						|| notificationSearchRequest.getUserId() != null
						) {
					predicateList.add(cb.like(root.<String> get("userId"), ("%" + notificationSearchRequest.getUserId()+ "%")));
				}
				if (!excludeZeroes
						|| notificationSearchRequest.getDeviceOS() != null
						) {
					predicateList.add(cb.like(root.<String> get("deviceOS"), ("%" + notificationSearchRequest.getDeviceOS()+ "%")));
				}
				if (!excludeZeroes
						|| notificationSearchRequest.getSource() != null
						) {
					predicateList.add(cb.like(root.<String> get("source"), ("%" + notificationSearchRequest.getSource()+ "%")));
				}
				if (!excludeZeroes
						|| notificationSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + notificationSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| notificationSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + notificationSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Notification> searchByKeyword(final String keyword) {
		return new Specification<Notification>() {
			@Override
			public Predicate toPredicate(Root<Notification> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("notificationId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("userId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("deviceOS"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("source"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<Notification> loadAssociations(
	) {
		return new Specification<Notification>() {
			@Override
			public Predicate toPredicate(Root<Notification> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
