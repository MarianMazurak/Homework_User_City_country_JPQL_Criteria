package com.mazurak.logos.Homework_U_C_C_JPQL_Criteria.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "city")
@Getter
@Setter
@NoArgsConstructor
public class City extends BaseEntity {

	@Column(name = "city_name", length = 55)
	private String cityName;

	@ManyToOne()
	@JoinColumn(name = "country_id")
	private Country country;
	

	@Override
	public String toString() {
		return "City [cityName=" + cityName + ", country=" + country + ", getId()=" + getId() + "]";
	}

}
