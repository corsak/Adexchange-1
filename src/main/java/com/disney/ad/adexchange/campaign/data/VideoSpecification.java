package com.disney.ad.adexchange.campaign.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.disney.ad.adexchange.campaign.domain.Video;
import com.disney.ad.adexchange.campaign.domain.VideoSearchRequest;

public class VideoSpecification {

	public static Specification<Video> searchAnd(final VideoSearchRequest videoSearchRequest, final boolean excludeZeroes) {
		return new Specification<Video>() {
			@Override
			public Predicate toPredicate(Root<Video> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| videoSearchRequest.getTitle() != null
						) {
					predicateList.add(cb.equal(root.<String> get("title"), videoSearchRequest.getTitle()));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getVideoUrl() != null
						) {
					predicateList.add(cb.equal(root.<String> get("videoUrl"), videoSearchRequest.getVideoUrl()));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getVideoType() != null
						) {
					predicateList.add(cb.equal(root.<String> get("videoType"), videoSearchRequest.getVideoType()));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getDelivery() != null
						) {
					predicateList.add(cb.equal(root.<String> get("delivery"), videoSearchRequest.getDelivery()));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getBitrate() != null
						) {
					predicateList.add(cb.equal(root.<String> get("bitrate"), videoSearchRequest.getBitrate()));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getWidth() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("width"), videoSearchRequest.getWidth()));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getHeight() != null
						) {
					predicateList.add(cb.equal(root.<Integer> get("height"), videoSearchRequest.getHeight()));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getDuration() != null
						) {
					predicateList.add(cb.equal(root.<Float> get("duration"), videoSearchRequest.getDuration()));
				}
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Video> searchOr(final VideoSearchRequest videoSearchRequest, final boolean excludeZeroes) {
		return new Specification<Video>() {
			@Override
			public Predicate toPredicate(Root<Video> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				if (!excludeZeroes
						|| videoSearchRequest.getTitle() != null
						) {
					predicateList.add(cb.like(root.<String> get("title"), ("%" + videoSearchRequest.getTitle()+ "%")));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getVideoUrl() != null
						) {
					predicateList.add(cb.like(root.<String> get("videoUrl"), ("%" + videoSearchRequest.getVideoUrl()+ "%")));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getVideoType() != null
						) {
					predicateList.add(cb.like(root.<String> get("videoType"), ("%" + videoSearchRequest.getVideoType()+ "%")));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getDelivery() != null
						) {
					predicateList.add(cb.like(root.<String> get("delivery"), ("%" + videoSearchRequest.getDelivery()+ "%")));
				}
				if (!excludeZeroes
						|| videoSearchRequest.getBitrate() != null
						) {
					predicateList.add(cb.like(root.<String> get("bitrate"), ("%" + videoSearchRequest.getBitrate()+ "%")));
				}
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
	public static Specification<Video> searchByKeyword(final String keyword) {
		return new Specification<Video>() {
			@Override
			public Predicate toPredicate(Root<Video> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likeKeyword = "%" + keyword + "%";
				List<Predicate> predicateList = new ArrayList<Predicate>();
				predicateList.add(cb.like(root.<String> get("title"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("videoUrl"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("videoType"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("delivery"), likeKeyword));
				predicateList.add(cb.like(root.<String> get("bitrate"), likeKeyword));
				return cb.or(predicateList.toArray(new Predicate[] {}));
			}
		};
	}
	
/*	
	public static Specification<Video> loadAssociations(
	) {
		return new Specification<Video>() {
			@Override
			public Predicate toPredicate(Root<Video> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				return cb.and(predicateList.toArray(new Predicate[] {}));
			}
		};
	}		
*/				
}
