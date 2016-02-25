package com.disney.ad.adexchange.publisher.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.publisher.domain.InventorySpace;
import com.disney.ad.adexchange.publisher.domain.InventorySpaceSearchRequest;

public class InventorySpaceSpecification {

	public static Specification<InventorySpace> searchAnd(final InventorySpaceSearchRequest inventorySpaceSearchRequest, final boolean excludeZeroes) {
		return new Specification<InventorySpace>() {
			@Override
			public Predicate toPredicate(Root<InventorySpace> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| inventorySpaceSearchRequest.getInventoryId() != null
						) {
					predicateList.add(cb.equal(root.<String> get("inventoryId"), inventorySpaceSearchRequest.getInventoryId()));
				}
				if (!excludeZeroes
						|| inventorySpaceSearchRequest.getEstimatedValue() != null
						) {
					predicateList.add(cb.equal(root.<Float> get("estimatedValue"), inventorySpaceSearchRequest.getEstimatedValue()));
				}
				if (!excludeZeroes
						|| inventorySpaceSearchRequest.getUnits() != null
						) {
					predicateList.add(cb.equal(root.<String> get("units"), inventorySpaceSearchRequest.getUnits()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<InventorySpace> searchOr(final InventorySpaceSearchRequest inventorySpaceSearchRequest, final boolean excludeZeroes) {
		return new Specification<InventorySpace>() {
			@Override
			public Predicate toPredicate(Root<InventorySpace> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| inventorySpaceSearchRequest.getInventoryId() != null
						) {
					predicateList.add(cb.like(root.<String> get("inventoryId"), ("%" + inventorySpaceSearchRequest.getInventoryId()+ "%")));
				}
				if (!excludeZeroes
						|| inventorySpaceSearchRequest.getUnits() != null
						) {
					predicateList.add(cb.like(root.<String> get("units"), ("%" + inventorySpaceSearchRequest.getUnits()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<InventorySpace> searchByKeyword(final String keyword) {
		return new Specification<InventorySpace>() {
			@Override
			public Predicate toPredicate(Root<InventorySpace> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("inventoryId"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("units"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<InventorySpace> loadAssociations(
	) {
		return new Specification<InventorySpace>() {
			@Override
			public Predicate toPredicate(Root<InventorySpace> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
