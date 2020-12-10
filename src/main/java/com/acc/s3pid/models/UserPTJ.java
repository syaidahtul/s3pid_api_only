package com.acc.s3pid.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
@Table(	name = "user_ptj", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = {"userId","ptjCode"})
		})
public class UserPTJ {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "user_ptj_seq", allocationSize = 1, initialValue = 10000)
	private Long id;
	
	private Long userId;
	
	private String ptjCode;

}
