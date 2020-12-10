package com.acc.s3pid.configurations;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.acc.s3pid.services.impl.UserPrincipal;

public class AuditorAwareImpl implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		return Optional.of(-1L);
		/*
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userPrincipal == null) {
			return Optional.of(-1L);
		}
		return Optional.of(userPrincipal.getId());
		*/
	}

}
