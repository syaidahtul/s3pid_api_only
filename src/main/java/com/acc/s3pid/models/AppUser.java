package com.acc.s3pid.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.acc.s3pid.models.core.EntityHistory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
@Table(	name = "app_user", 
		uniqueConstraints = { 
			@UniqueConstraint(name = "app_user_username_uni", columnNames = "username"),
			@UniqueConstraint(name = "", columnNames = "email") 
		})
public class AppUser extends EntityHistory<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "app_user_seq", allocationSize = 1, initialValue = 10000)
	private Long id;

	private String username;

	private String email;

	private String password;
	
	private String userTypeCode;

	private Boolean isActive;
	
	public AppUser() {
	}

	public AppUser(String username, String email, String password, String userTypeCode, Boolean isActive) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.userTypeCode = userTypeCode;
		this.isActive = isActive;
	}

}
