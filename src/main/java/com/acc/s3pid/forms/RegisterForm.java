package com.acc.s3pid.forms;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {

	private String name;

	private String username;
	
	private String email;

	private String password;

    private String userType;

    private Set<String> role;
}
