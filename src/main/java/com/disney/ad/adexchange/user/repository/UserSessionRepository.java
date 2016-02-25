package com.disney.ad.adexchange.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.user.domain.UserSession;


public interface UserSessionRepository extends JpaRepository<UserSession, Integer>,
		JpaSpecificationExecutor<UserSession> {
}
