package com.disney.ad.adexchange.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.user.domain.Publisher;


public interface PublisherRepository extends JpaRepository<Publisher, Integer>,
		JpaSpecificationExecutor<Publisher> {
}
