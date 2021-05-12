package com.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ashokit.entity.CountryMaster;
public interface CountryRepository extends JpaRepository<CountryMaster, Integer> {
	
	

}
