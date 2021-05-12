package com.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.StateMaster;

public interface StateRepository extends JpaRepository<StateMaster, Integer> {

	List<StateMaster> findStatesByCountryId(Integer countryId);

}
