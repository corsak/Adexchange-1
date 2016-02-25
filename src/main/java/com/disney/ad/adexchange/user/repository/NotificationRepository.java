package com.disney.ad.adexchange.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.user.domain.Notification;


public interface NotificationRepository extends JpaRepository<Notification, Integer>,
		JpaSpecificationExecutor<Notification> {
}
