package com.acc.s3pid.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acc.s3pid.models.AppUser;
import com.acc.s3pid.repositories.AppUserRepository;
import com.acc.s3pid.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AppUserRepository appUserRepository;
	
	@Override
	public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserRepository.findByUsername(username);
		UserPrincipal principal = new UserPrincipal();
		if (appUser != null) {
			principal.setId(appUser.getId());
			principal.setUsername(appUser.getUsername());
			principal.setEmail(appUser.getEmail());
			principal.setPassword(appUser.getPassword());
			principal.setUsertype(appUser.getUserTypeCode());
		}
		
		return principal;
	}

}
