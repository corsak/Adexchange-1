package com.disney.ad.adexchange.campaign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.campaign.domain.BannerAdTarget;


public interface BannerAdTargetRepository extends JpaRepository<BannerAdTarget, Integer>,
		JpaSpecificationExecutor<BannerAdTarget> {
}
