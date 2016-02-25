package com.disney.ad.adexchange.publisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.publisher.domain.InventorySpace;


public interface InventorySpaceRepository extends JpaRepository<InventorySpace, Integer>,
		JpaSpecificationExecutor<InventorySpace> {
}
