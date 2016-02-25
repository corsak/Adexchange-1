package com.disney.ad.adexchange.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.analytics.domain.HourlyImpressions;


public interface HourlyImpressionsRepository extends JpaRepository<HourlyImpressions, Integer>,
		JpaSpecificationExecutor<HourlyImpressions> {
}
