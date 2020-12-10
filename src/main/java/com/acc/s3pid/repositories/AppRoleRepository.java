package com.acc.s3pid.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acc.s3pid.models.AppRoles;


@Repository
public interface AppRoleRepository extends JpaRepository<AppRoles, String> {
	
	Optional<AppRoles> findByCode(String code);

}
