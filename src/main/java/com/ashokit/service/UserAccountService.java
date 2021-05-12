package com.ashokit.service;

import java.util.Map;


import com.ashokit.entity.UserAccount;


public interface UserAccountService {

	public Map<Integer, String> getAllCountries();

	public Map<Integer, String> getStates(Integer countryId);

	public Map<Integer, String> getCityes(Integer StateId);

	public boolean emailIsUnique(String EmailId);

	public boolean saveUser(UserAccount account);

	public boolean isTempPwdVaild(String email, String tempPwd);

	
	public UserAccount findUserByEmail(String emailId);

	public boolean unlockAccount(String email, String newPwd);
	
	public String recoverPassword(String email);

	public String loginCheck(String email,String pwd);

}
