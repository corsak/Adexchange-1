package com.disney.ad.adexchange.campaign.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.campaign.domain.BannerAdTarget;
import com.disney.ad.adexchange.campaign.domain.BannerAdTargetSearchRequest;

public class BannerAdTargetSpecification {

	public static Specification<BannerAdTarget> searchAnd(final BannerAdTargetSearchRequest bannerAdTargetSearchRequest, final boolean excludeZeroes) {
		return new Specification<BannerAdTarget>() {
			@Override
			public Predicate toPredicate(Root<BannerAdTarget> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getBannerID() != null
						) {
					predicateList.add(cb.equal(root.<String> get("bannerID"), bannerAdTargetSearchRequest.getBannerID()));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getCampaignID() != null
						) {
					predicateList.add(cb.equal(root.<String> get("campaignID"), bannerAdTargetSearchRequest.getCampaignID()));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getGeo() != null
						) {
					predicateList.add(cb.equal(root.<String> get("geo"), bannerAdTargetSearchRequest.getGeo()));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getState() != null
						) {
					predicateList.add(cb.equal(root.<String> get("state"), bannerAdTargetSearchRequest.getState()));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getCity() != null
						) {
					predicateList.add(cb.equal(root.<String> get("city"), bannerAdTargetSearchRequest.getCity()));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getAgeGroup() != null
						) {
					predicateList.add(cb.equal(root.<String> get("ageGroup"), bannerAdTargetSearchRequest.getAgeGroup()));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getGender() != null
						) {
					predicateList.add(cb.equal(root.<String> get("gender"), bannerAdTargetSearchRequest.getGender()));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getCategory() != null
						) {
					predicateList.add(cb.equal(root.<String> get("category"), bannerAdTargetSearchRequest.getCategory()));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getKeyword() != null
						) {
					predicateList.add(cb.equal(root.<String> get("keyword"), bannerAdTargetSearchRequest.getKeyword()));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getStatus() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("status"), bannerAdTargetSearchRequest.getStatus()));
				}
				else{
					predicateList.add(cb.greaterThanOrEqualTo(root.<Integer> get("status"), 1));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<BannerAdTarget> searchOr(final BannerAdTargetSearchRequest bannerAdTargetSearchRequest, final boolean excludeZeroes) {
		return new Specification<BannerAdTarget>() {
			@Override
			public Predicate toPredicate(Root<BannerAdTarget> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getBannerID() != null
						) {
					predicateList.add(cb.like(root.<String> get("bannerID"), ("%" + bannerAdTargetSearchRequest.getBannerID()+ "%")));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getCampaignID() != null
						) {
					predicateList.add(cb.like(root.<String> get("campaignID"), ("%" + bannerAdTargetSearchRequest.getCampaignID()+ "%")));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getGeo() != null
						) {
					predicateList.add(cb.like(root.<String> get("geo"), ("%" + bannerAdTargetSearchRequest.getGeo()+ "%")));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getState() != null
						) {
					predicateList.add(cb.like(root.<String> get("state"), ("%" + bannerAdTargetSearchRequest.getState()+ "%")));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getCity() != null
						) {
					predicateList.add(cb.like(root.<String> get("city"), ("%" + bannerAdTargetSearchRequest.getCity()+ "%")));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getAgeGroup() != null
						) {
					predicateList.add(cb.like(root.<String> get("ageGroup"), ("%" + bannerAdTargetSearchRequest.getAgeGroup()+ "%")));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getGender() != null
						) {
					predicateList.add(cb.like(root.<String> get("gender"), ("%" + bannerAdTargetSearchRequest.getGender()+ "%")));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getCategory() != null
						) {
					predicateList.add(cb.like(root.<String> get("category"), ("%" + bannerAdTargetSearchRequest.getCategory()+ "%")));
				}
				if (!excludeZeroes
						|| bannerAdTargetSearchRequest.getKeyword() != null
						) {
					predicateList.add(cb.like(root.<String> get("keyword"), ("%" + bannerAdTargetSearchRequest.getKeyword()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<BannerAdTarget> searchByKeyword(final String keyword) {
		return new Specification<BannerAdTarget>() {
			@Override
			public Predicate toPredicate(Root<BannerAdTarget> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("bannerID"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("campaignID"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("geo"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("state"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("city"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("ageGroup"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("gender"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("category"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("keyword"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<BannerAdTarget> loadAssociations(
	) {
		return new Specification<BannerAdTarget>() {
			@Override
			public Predicate toPredicate(Root<BannerAdTarget> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
