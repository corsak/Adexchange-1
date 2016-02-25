package com.disney.ad.adexchange.campaign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.campaign.domain.NativeAd;


public interface NativeAdRepository extends JpaRepository<NativeAd, Integer>,
		JpaSpecificationExecutor<NativeAd> {
}
