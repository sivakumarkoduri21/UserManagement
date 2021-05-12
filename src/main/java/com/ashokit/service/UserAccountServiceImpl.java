package com.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.CityMaster;
import com.ashokit.entity.CountryMaster;
import com.ashokit.entity.StateMaster;
import com.ashokit.entity.UserAccount;
import com.ashokit.repository.CityRepository;
import com.ashokit.repository.CountryRepository;
import com.ashokit.repository.StateRepository;
import com.ashokit.repository.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private UserAccountRepository accountRepo;
	@Autowired
	private EmailService mailrepo;

	@Override
	public Map<Integer, String> getAllCountries() {

		List<CountryMaster> allCountries = countryRepo.findAll();
		Map<Integer, String> countries = new HashMap<Integer, String>();
		for (CountryMaster country : allCountries) {
			countries.put(country.getCountryId(), country.getContryName());
		}
		return countries;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {

		List<StateMaster> getallstates = stateRepo.findStatesByCountryId(countryId);
		Map<Integer, String> allstates = new HashMap<Integer, String>();
		for (StateMaster states : getallstates) {
			allstates.put(states.getStateId(), states.getStateName());
		}

		return allstates;
	}

	@Override
	public Map<Integer, String> getCityes(Integer stateId) {

		List<CityMaster> getallCityes = cityRepo.findByStateId(stateId);
		Map<Integer, String> allcityes = new HashMap<Integer, String>();

		for (CityMaster cityes : getallCityes) {
			allcityes.put(cityes.getCityId(), cityes.getCityName());
		}

		return allcityes;
	}

	@Override
	public boolean emailIsUnique(String emailId) {
		UserAccount user = accountRepo.findByEmail(emailId);
		if (user == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean saveUser(UserAccount account) {
		account.setPassword(passwordGenerator());
		account.setAccountStatus("LOCKED");
		UserAccount user = accountRepo.save(account);
		String emailbody = getUnlockAccEmailBody(user);
		String subject = "UNLOCK Your Account |IES";
		boolean isSent = mailrepo.sendAccountUnlockEmail(subject, emailbody, user.getEmail());
		return user.getUserId() != null && isSent;
	}

	@Override
	public boolean isTempPwdVaild(String email, String tempPwd) {
		UserAccount user = accountRepo.findByEmail(email);
		if (user.getPassword().equals(tempPwd)) {
			return true;
		}
		return false;
	}

	@Override
	public UserAccount findUserByEmail(String emailId) {
		return accountRepo.findByEmail(emailId);
	}

	private static String passwordGenerator() {

		String randomPassword = "";
		final String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random randomPwd = new Random();

		for (int i = 0; i <= 8; i++) {
			randomPassword = randomPassword + alphaNumeric.charAt(randomPwd.nextInt(alphaNumeric.length()));
		}
		return randomPassword;
	}

	private String getUnlockAccEmailBody(UserAccount user) {

		StringBuffer sb = new StringBuffer();
		String body = null;
		try {
			File f = new File("UNCLOCK-ACC-EMAIL-BODY.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			body = sb.toString();
			body = body.replace("{FNAME}", user.getFirstName());
			body = body.replace("{LNAME}", user.getLastName());
			body = body.replace("{TEMP-PWD}", user.getPassword());
			body = body.replace("{EMAIL}", user.getEmail());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return body;
	}

	@Override
	public boolean unlockAccount(String email, String newPwd) {
		UserAccount user = accountRepo.findByEmail(email);
		user.setPassword(newPwd);
		user.setAccountStatus("UNLOCKED");
		try {
			accountRepo.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public String recoverPassword(String email) {
		UserAccount user = accountRepo.findByEmail(email);
		if (user != null) {
			// send a email with password
			String emailbody = forgotPasswordEmail(user);
			String subject = "Forgot Passsword Your Account |IES";
			mailrepo.sendAccountUnlockEmail(subject, emailbody, user.getEmail());
			return user.getPassword();
		}
		return null;
	}

	private String forgotPasswordEmail(UserAccount user) {

		StringBuffer sb = new StringBuffer();
		String body = null;
		try {
			File f = new File("FORGOT-PASSWORD.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			body = sb.toString();
			body = body.replace("{FNAME}", user.getFirstName());
			body = body.replace("{LNAME}", user.getLastName());
			body = body.replace("{PASSWORD}", user.getPassword());
			body = body.replace("{EMAIL}", user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	@Override
	public String loginCheck(String email, String pwd) {
		UserAccount user = accountRepo.findByEmail(email);

		if (user != null) {

			if (user.getEmail().equals(email) && user.getPassword().equals(pwd)) {
				String accStatus = user.getAccountStatus();
				if ("LOCKED".equals(accStatus)) {
					return "Your Account is Locked";
				} else {
					return "Login Success";
				}
			} else
				return "Email or password Incorect!";
		}
		return "Invalid Credential";

	}
}
