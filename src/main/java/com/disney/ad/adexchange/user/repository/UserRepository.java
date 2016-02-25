package com.disney.ad.adexchange.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.disney.ad.adexchange.user.domain.User;


public interface UserRepository extends JpaRepository<User, Integer>,
		JpaSpecificationExecutor<User> {

}
