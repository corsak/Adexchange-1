package com.disney.ad.adexchange.campaign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.campaign.domain.Video;


public interface VideoRepository extends JpaRepository<Video, Integer>,
		JpaSpecificationExecutor<Video> {
}
