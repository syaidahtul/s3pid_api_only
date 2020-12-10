package com.acc.s3pid.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	
	private String token;
	private String type = "Bearer";
	private String username;
	private String email;
	private String roles;

	public JwtResponse(String accessToken, Long id, String username, String email, String roles) {
		this.token = accessToken;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

}
