package com.mazurak.logos.Homework_U_C_C_JPQL_Criteria.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;


@MappedSuperclass
@Data
public class BaseEntity {
	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Id
	private int id;

	
	
}
