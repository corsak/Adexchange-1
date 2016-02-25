package com.disney.ad.adexchange.publisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.publisher.domain.Inventory;


public interface InventoryRepository extends JpaRepository<Inventory, Integer>,
		JpaSpecificationExecutor<Inventory> {
}
