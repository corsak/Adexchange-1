package com.disney.ad.adexchange.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.user.domain.Advertiser;


public interface AdvertiserRepository extends JpaRepository<Advertiser, Integer>,
		JpaSpecificationExecutor<Advertiser> {
}
