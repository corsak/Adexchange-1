package com.disney.ad.adexchange.campaign.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.campaign.domain.BannerAdzones;
import com.disney.ad.adexchange.campaign.domain.BannerAdzonesSearchRequest;

public class BannerAdzonesSpecification {

	public static Specification<BannerAdzones> searchAnd(final BannerAdzonesSearchRequest bannerAdzonesSearchRequest, final boolean excludeZeroes) {
		return new Specification<BannerAdzones>() {
			@Override
			public Predicate toPredicate(Root<BannerAdzones> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| bannerAdzonesSearchRequest.getBannerID() != null
						) {
					predicateList.add(cb.equal(root.<String> get("bannerID"), bannerAdzonesSearchRequest.getBannerID()));
				}
				if (!excludeZeroes
						|| bannerAdzonesSearchRequest.getZoneID() != null
						) {
					predicateList.add(cb.equal(root.<String> get("zoneID"), bannerAdzonesSearchRequest.getZoneID()));
				}
				if (!excludeZeroes
						|| bannerAdzonesSearchRequest.getCampaignID() != null
						) {
					predicateList.add(cb.equal(root.<String> get("campaignID"), bannerAdzonesSearchRequest.getCampaignID()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<BannerAdzones> searchOr(final BannerAdzonesSearchRequest bannerAdzonesSearchRequest, final boolean excludeZeroes) {
		return new Specification<BannerAdzones>() {
			@Override
			public Predicate toPredicate(Root<BannerAdzones> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| bannerAdzonesSearchRequest.getBannerID() != null
						) {
					predicateList.add(cb.like(root.<String> get("bannerID"), ("%" + bannerAdzonesSearchRequest.getBannerID()+ "%")));
				}
				if (!excludeZeroes
						|| bannerAdzonesSearchRequest.getZoneID() != null
						) {
					predicateList.add(cb.like(root.<String> get("zoneID"), ("%" + bannerAdzonesSearchRequest.getZoneID()+ "%")));
				}
				if (!excludeZeroes
						|| bannerAdzonesSearchRequest.getCampaignID() != null
						) {
					predicateList.add(cb.like(root.<String> get("campaignID"), ("%" + bannerAdzonesSearchRequest.getCampaignID()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<BannerAdzones> searchByKeyword(final String keyword) {
		return new Specification<BannerAdzones>() {
			@Override
			public Predicate toPredicate(Root<BannerAdzones> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("bannerID"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("zoneID"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("campaignID"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<BannerAdzones> loadAssociations(
	) {
		return new Specification<BannerAdzones>() {
			@Override
			public Predicate toPredicate(Root<BannerAdzones> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
