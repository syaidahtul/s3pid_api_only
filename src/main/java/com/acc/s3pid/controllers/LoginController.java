package com.acc.s3pid.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acc.s3pid.forms.LoginForms;
import com.acc.s3pid.forms.RegisterForm;
import com.acc.s3pid.models.AppRoles;
import com.acc.s3pid.models.AppUser;
import com.acc.s3pid.payload.response.JwtResponse;
import com.acc.s3pid.payload.response.MessageResponse;
import com.acc.s3pid.repositories.AppRoleRepository;
import com.acc.s3pid.repositories.AppUserRepository;
import com.acc.s3pid.security.JwtUtils;
import com.acc.s3pid.services.impl.UserPrincipal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AppUserRepository userRepository;

	@Autowired
	AppRoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@GetMapping()
	public ResponseEntity<?> test() {
		return ResponseEntity.ok(new MessageResponse("Well!"));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginForms loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), userDetails.getUsertype()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody RegisterForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		
		String email = signUpRequest.getEmail();
		String domain = email.substring(email .indexOf("@") + 1);
		
		if ("sabah.gov.my".equals(domain)) {
			signUpRequest.setUserType("GOV_STAFF");
		} else {
			signUpRequest.setUserType("PUBLIC");
		}

		Set<String> strRoles = signUpRequest.getRole();
		Set<AppRoles> roles = new HashSet<AppRoles>();

		if (strRoles == null) {
			AppRoles userRole = roleRepository.findByCode("USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			
		}
		
		// Create new user's account
		AppUser user = new AppUser(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getUserType(), true);
		
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
