package com.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "COUNTRY_MASTER")
@Data
public class CountryMaster {

	
	
	@Id
	private Integer countryId;

	private Integer countryCode;

	private String contryName;

	
	public CountryMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CountryMaster(Integer countryId, Integer countryCode, String contryName) {
		super();
		this.countryId = countryId;
		this.countryCode = countryCode;
		this.contryName = contryName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public String getContryName() {
		return contryName;
	}

	public void setContryName(String contryName) {
		this.contryName = contryName;
	}


	

	
}
