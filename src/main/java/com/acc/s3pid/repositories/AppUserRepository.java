package com.acc.s3pid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acc.s3pid.models.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	AppUser findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
