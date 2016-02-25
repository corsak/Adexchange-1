package com.disney.ad.adexchange.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.common.domain.AdTemplates;


public interface AdTemplatesRepository extends JpaRepository<AdTemplates, Integer>,
		JpaSpecificationExecutor<AdTemplates> {
}
