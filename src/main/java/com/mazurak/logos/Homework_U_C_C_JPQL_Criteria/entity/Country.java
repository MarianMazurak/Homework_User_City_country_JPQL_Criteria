package com.mazurak.logos.Homework_U_C_C_JPQL_Criteria.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor
public class Country extends BaseEntity {
	
	@Column(name = "name_country" , length = 64)
	private String nameCoutry;
	
	@OneToMany(mappedBy = "country",fetch = FetchType.EAGER)  // default @OneToMany have LAZY
	private List<City> citys = new ArrayList<>();
	
	
	
	@Override
	public String toString() {
		return "Country [nameCoutry=" + nameCoutry + ", getId()=" + getId() + "]";
	}
	
}
