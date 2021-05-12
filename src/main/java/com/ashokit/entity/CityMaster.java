package com.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CITY_MASTER")
public class CityMaster {
	
	@Id
	private Integer cityId;
	private String cityName;
	private Integer stateId;

	public CityMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CityMaster(Integer cityId, String cityName, Integer stateId) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.stateId = stateId;
	}

	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	
	
	

}
