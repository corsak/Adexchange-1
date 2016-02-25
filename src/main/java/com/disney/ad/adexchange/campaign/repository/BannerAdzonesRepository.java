package com.disney.ad.adexchange.campaign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.campaign.domain.BannerAdzones;


public interface BannerAdzonesRepository extends JpaRepository<BannerAdzones, Integer>,
		JpaSpecificationExecutor<BannerAdzones> {
}
