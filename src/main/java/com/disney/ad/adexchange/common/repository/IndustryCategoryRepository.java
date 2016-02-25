package com.disney.ad.adexchange.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.common.domain.IndustryCategory;


public interface IndustryCategoryRepository extends JpaRepository<IndustryCategory, Integer>,
		JpaSpecificationExecutor<IndustryCategory> {
}
