package com.mazurak.logos.Homework_U_C_C_JPQL_Criteria.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

	@Column(name = "full_name", length = 100)
	private String fullName;
	@Column(name = "age")
	private int age;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // default @OneToOne EAGER
	@JoinColumn(name = "city_id")
	private City city;

	public User(String fullName, int age, City city) {
		super();
		this.fullName = fullName;
		this.age = age;
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [fullName=" + fullName + ", age=" + age + ", city=" + city + ", getId()=" + getId() + "]";
	}

	

	
}
