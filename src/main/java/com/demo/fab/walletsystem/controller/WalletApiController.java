package com.demo.fab.walletsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.fab.walletsystem.model.Transactions;
import com.demo.fab.walletsystem.model.User;
import com.demo.fab.walletsystem.service.SignUpService;
import com.demo.fab.walletsystem.service.TransactionService;
import com.demo.fab.walletsystem.service.UserProfileService;
import com.demo.fab.walletsystem.service.WalletAddMoneyService;
import com.demo.fab.walletsystem.service.WalletSendMoneyService;

@RestController
public class WalletApiController {
	private Logger logger =LoggerFactory.getLogger(WalletApiController.class);
	
	private SignUpService signUpService;
	private UserProfileService userProfileService;
	private WalletAddMoneyService walletAddMoneyService;
	private WalletSendMoneyService walletSendMoneyService;
	private TransactionService transactionService;

	@Autowired
	public WalletApiController(SignUpService signUpService, UserProfileService userProfileService,
			WalletAddMoneyService walletAddMoneyService, WalletSendMoneyService walletSendMoneyService,
			TransactionService transactionService) {
		super();
		this.signUpService = signUpService;
		this.userProfileService = userProfileService;
		this.walletAddMoneyService = walletAddMoneyService;
		this.walletSendMoneyService = walletSendMoneyService;
		this.transactionService = transactionService;
	}

	@PostMapping(value="/signup" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> signUp(@RequestBody User signUpData)
	{
		logger.info("**Inside signUp process**");
		logger.info("signUp data {} ",signUpData);
		return signUpService.registerNewUser(signUpData);
	}
	
	@PutMapping(value = "/updateprofile/{username}")
	public ResponseEntity<String> updateUserProfile(@PathVariable(value = "username") String userName,@RequestBody User updateProfileData)
	{
		userProfileService.updateUserProfile(userName, updateProfileData);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@GetMapping(value = "/viewprofile/{username}")
	public ResponseEntity<User> viewUserProfile(@PathVariable(value = "username") String userName)
	{
		User user=userProfileService.fetchUserProfile(userName);
		return new ResponseEntity<User>(user, HttpStatus.OK);
    }
	
	@PostMapping(value = "/addmoney/{username}/{money}")
	public ResponseEntity<String> addMoney(@PathVariable(value = "username")String username,@PathVariable(value = "money") Float amount)
	{
		walletAddMoneyService.addmoney(amount, username);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@PostMapping(value = "/sendmoney/{fromusername}/{tousername}/{money}")
	public ResponseEntity<String> sendMoney(@PathVariable(value = "fromusername") String fromusername,@PathVariable(value = "tousername") String tousername,@PathVariable(value = "money") String money)
	{
		walletSendMoneyService.sendMoney(fromusername, tousername, money);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@GetMapping(value = "/viewpassbook/{username}")
	public ResponseEntity<List<Transactions>> viewAllTransactions(@PathVariable(value = "username") String username){
		List<Transactions> txnList=transactionService.viewPassbook(username);
		return new ResponseEntity<List<Transactions>>(txnList, HttpStatus.OK);
	}
	
}
