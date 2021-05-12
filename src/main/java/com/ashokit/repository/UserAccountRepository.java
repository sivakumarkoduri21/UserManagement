package com.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	UserAccount findByEmail(String email);

}
