package com.disney.ad.adexchange.campaign.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.campaign.domain.NativeAd;
import com.disney.ad.adexchange.campaign.domain.NativeAdSearchRequest;

public class NativeAdSpecification {

	public static Specification<NativeAd> searchAnd(final NativeAdSearchRequest nativeAdSearchRequest, final boolean excludeZeroes) {
		return new Specification<NativeAd>() {
			@Override
			public Predicate toPredicate(Root<NativeAd> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| nativeAdSearchRequest.getTitle() != null
						) {
					predicateList.add(cb.equal(root.<String> get("title"), nativeAdSearchRequest.getTitle()));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getDescription() != null
						) {
					predicateList.add(cb.equal(root.<String> get("description"), nativeAdSearchRequest.getDescription()));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getHighlightedText() != null
						) {
					predicateList.add(cb.equal(root.<String> get("highlightedText"), nativeAdSearchRequest.getHighlightedText()));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getIcon() != null
						) {
					predicateList.add(cb.equal(root.<String> get("icon"), nativeAdSearchRequest.getIcon()));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getButton() != null
						) {
					predicateList.add(cb.equal(root.<String> get("button"), nativeAdSearchRequest.getButton()));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getActionUrl() != null
						) {
					predicateList.add(cb.equal(root.<String> get("actionUrl"), nativeAdSearchRequest.getActionUrl()));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getPrice() != null
						) {
					predicateList.add(cb.equal(root.<Float> get("price"), nativeAdSearchRequest.getPrice()));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getRating() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("rating"), nativeAdSearchRequest.getRating()));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getSponsoredText() != null
						) {
					predicateList.add(cb.equal(root.<String> get("sponsoredText"), nativeAdSearchRequest.getSponsoredText()));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getSponsoredImageUrl() != null
						) {
					predicateList.add(cb.equal(root.<String> get("sponsoredImageUrl"), nativeAdSearchRequest.getSponsoredImageUrl()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<NativeAd> searchOr(final NativeAdSearchRequest nativeAdSearchRequest, final boolean excludeZeroes) {
		return new Specification<NativeAd>() {
			@Override
			public Predicate toPredicate(Root<NativeAd> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| nativeAdSearchRequest.getTitle() != null
						) {
					predicateList.add(cb.like(root.<String> get("title"), ("%" + nativeAdSearchRequest.getTitle()+ "%")));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getDescription() != null
						) {
					predicateList.add(cb.like(root.<String> get("description"), ("%" + nativeAdSearchRequest.getDescription()+ "%")));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getHighlightedText() != null
						) {
					predicateList.add(cb.like(root.<String> get("highlightedText"), ("%" + nativeAdSearchRequest.getHighlightedText()+ "%")));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getIcon() != null
						) {
					predicateList.add(cb.like(root.<String> get("icon"), ("%" + nativeAdSearchRequest.getIcon()+ "%")));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getButton() != null
						) {
					predicateList.add(cb.like(root.<String> get("button"), ("%" + nativeAdSearchRequest.getButton()+ "%")));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getActionUrl() != null
						) {
					predicateList.add(cb.like(root.<String> get("actionUrl"), ("%" + nativeAdSearchRequest.getActionUrl()+ "%")));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getSponsoredText() != null
						) {
					predicateList.add(cb.like(root.<String> get("sponsoredText"), ("%" + nativeAdSearchRequest.getSponsoredText()+ "%")));
				}
				if (!excludeZeroes
						|| nativeAdSearchRequest.getSponsoredImageUrl() != null
						) {
					predicateList.add(cb.like(root.<String> get("sponsoredImageUrl"), ("%" + nativeAdSearchRequest.getSponsoredImageUrl()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<NativeAd> searchByKeyword(final String keyword) {
		return new Specification<NativeAd>() {
			@Override
			public Predicate toPredicate(Root<NativeAd> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("title"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("description"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("highlightedText"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("icon"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("button"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("actionUrl"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("sponsoredText"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("sponsoredImageUrl"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<NativeAd> loadAssociations(
	) {
		return new Specification<NativeAd>() {
			@Override
			public Predicate toPredicate(Root<NativeAd> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
