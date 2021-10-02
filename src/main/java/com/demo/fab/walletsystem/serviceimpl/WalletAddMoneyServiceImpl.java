package com.demo.fab.walletsystem.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fab.walletsystem.exceptions.WalletException;
import com.demo.fab.walletsystem.repository.UserRepository;
import com.demo.fab.walletsystem.repository.WalletRepository;
import com.demo.fab.walletsystem.service.WalletAddMoneyService;

@Service
public class WalletAddMoneyServiceImpl  implements WalletAddMoneyService{

	private Logger logger =LoggerFactory.getLogger(WalletAddMoneyServiceImpl.class);
	private WalletRepository walletRepository;
	private UserRepository userRepository;
	
	@Autowired
	public WalletAddMoneyServiceImpl(WalletRepository walletRepository, UserRepository userRepository) {
		super();
		this.walletRepository = walletRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional
	public void addmoney(Float amount,String username) {
		logger.info("**Inside addmoney method**");
		if(amount<=0) {
			logger.error("Add Money found {} must be greater than 0",amount);
			throw new WalletException("Add Money must be greater than 0", new RuntimeException("Add Money must be greater than 0"));
		}
		try {
		Long userId=userRepository.findUserIdByUserName(username);
		walletRepository.creditMoneyInWalletByWalletId(userId, amount);
		logger.info("**Money {} added succesfully for user {}**",amount,username);	
		}
		catch(DataAccessException | TransactionException e) {
			logger.error("Error while Adding Money {} in wallet for user {}",amount,username,e);
			throw new WalletException("Error while Adding Money "+amount+" in wallet for user "+username+" Try again later!", new RuntimeException("Add money operation failed for user "+username));
		}
	}

	

}
