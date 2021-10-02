package com.demo.fab.walletsystem.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fab.walletsystem.exceptions.NewUserCreationException;
import com.demo.fab.walletsystem.exceptions.UserAlreadyExistsException;
import com.demo.fab.walletsystem.exceptions.WalletException;
import com.demo.fab.walletsystem.model.User;
import com.demo.fab.walletsystem.model.Wallet;
import com.demo.fab.walletsystem.repository.UserRepository;
import com.demo.fab.walletsystem.repository.WalletRepository;
import com.demo.fab.walletsystem.service.SignUpService;
import com.demo.fab.walletsystem.util.WalletUtils;

@Service
public class SignUpServiceImpl implements SignUpService {
	private Logger logger =LoggerFactory.getLogger(SignUpServiceImpl.class);
	private UserRepository userRepository;
	private WalletRepository walletRepository;

	@Autowired
	public SignUpServiceImpl(UserRepository userRepository, WalletRepository walletRepository) {
		this.userRepository = userRepository;
		this.walletRepository = walletRepository;
	}

	@Override
	@Transactional(rollbackFor =WalletException.class )
	public ResponseEntity<String> registerNewUser(User signUpData) {
		User newSignedUpUser=null;
		try {
		int count=userRepository.isUserNameAlreadyExist(signUpData.getUserName());
		if(count>0)
		{
			logger.error("Username {} already exists! Please try with different username",signUpData.getUserName());
			throw new UserAlreadyExistsException("UserName: "+signUpData.getUserName()+" already Exists! please try with different username", new RuntimeException("UserName already Exists!"+signUpData.getUserName()));
		}
		signUpData.setCreatedAt(WalletUtils.getDateTime());
		signUpData.setLastUpdatedAt(WalletUtils.getDateTime());
		newSignedUpUser= userRepository.save(signUpData);
		logger.info("New User Registered Successfully {}",newSignedUpUser);
		}
		catch(DataAccessException | TransactionException  e) {
			logger.error("Error while registering new User {}",signUpData.getUserName(),e);
			throw new NewUserCreationException("Error while saving new user profile!", e);
		}
		
		
		try {
		Wallet newWallet=WalletUtils.toWalletModel(newSignedUpUser);
		walletRepository.save(newWallet);
		logger.info("New Wallet created Successfully {}",newWallet);
		}
		catch(DataAccessException |TransactionException e) {
			logger.error("Error while creating wallet for User {}",signUpData.getUserName(),e);
			throw new WalletException("Error while creating wallet for User: "+signUpData.getUserName(), e);
		}
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}  

}
