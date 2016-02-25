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

import com.disney.ad.adexchange.user.domain.Role;
import com.disney.ad.adexchange.user.domain.RoleSearchRequest;

public class RoleSpecification {

	public static Specification<Role> searchAnd(final RoleSearchRequest roleSearchRequest, final boolean excludeZeroes) {
		return new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| roleSearchRequest.getRoleId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("roleId"), roleSearchRequest.getRoleId()));
				}
				if (!excludeZeroes
						|| roleSearchRequest.getRoleName() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("roleName"), roleSearchRequest.getRoleName()));
				}
				if (!excludeZeroes
						|| roleSearchRequest.getRoleGroup() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("roleGroup"), roleSearchRequest.getRoleGroup()));
				}
				if (!excludeZeroes
						|| (roleSearchRequest.getCreatedTimeStart() != null || roleSearchRequest
								.getCreatedTimeEnd() != null)) {
					if (roleSearchRequest.getCreatedTimeStart() != null
							&& roleSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("createdTime"),
								roleSearchRequest.getCreatedTimeStart(),roleSearchRequest
								.getCreatedTimeEnd()));
					} else if (roleSearchRequest.getCreatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("createdTime"), roleSearchRequest.getCreatedTimeStart()));
					} else if (roleSearchRequest.getCreatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("createdTime"), roleSearchRequest.getCreatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| (roleSearchRequest.getUpdatedTimeStart() != null || roleSearchRequest
								.getUpdatedTimeEnd() != null)) {
					if (roleSearchRequest.getUpdatedTimeStart() != null
							&& roleSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.between(root.<Timestamp> get("updatedTime"),
								roleSearchRequest.getUpdatedTimeStart(),roleSearchRequest
								.getUpdatedTimeEnd()));
					} else if (roleSearchRequest.getUpdatedTimeStart() != null) {
						predicateList.add(cb.greaterThan(root.<Timestamp> get("updatedTime"), roleSearchRequest.getUpdatedTimeStart()));
					} else if (roleSearchRequest.getUpdatedTimeEnd() != null) {
						predicateList.add(cb.lessThan(root.<Timestamp> get("updatedTime"), roleSearchRequest.getUpdatedTimeEnd()));
					}
				}
				if (!excludeZeroes
						|| roleSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("createdByUser"), roleSearchRequest.getCreatedByUser()));
				}
				if (!excludeZeroes
						|| roleSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.equal(root.<String> get("updatedByUser"), roleSearchRequest.getUpdatedByUser()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Role> searchOr(final RoleSearchRequest roleSearchRequest, final boolean excludeZeroes) {
		return new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| roleSearchRequest.getRoleId() != null
						) {
					predicateList.add(cb.like(root.<String> get("roleId"), ("%" + roleSearchRequest.getRoleId()+ "%")));
				}
				if (!excludeZeroes
						|| roleSearchRequest.getCreatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("createdByUser"), ("%" + roleSearchRequest.getCreatedByUser()+ "%")));
				}
				if (!excludeZeroes
						|| roleSearchRequest.getUpdatedByUser() != null
						) {
					predicateList.add(cb.like(root.<String> get("updatedByUser"), ("%" + roleSearchRequest.getUpdatedByUser()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Role> searchByKeyword(final String keyword) {
		return new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("roleId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("createdByUser"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("updatedByUser"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<Role> loadAssociations(
	) {
		return new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
