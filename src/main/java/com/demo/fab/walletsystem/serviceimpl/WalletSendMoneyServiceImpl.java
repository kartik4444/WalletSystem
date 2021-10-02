package com.demo.fab.walletsystem.serviceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fab.walletsystem.exceptions.TransactionCreationException;
import com.demo.fab.walletsystem.exceptions.UserProfileNotFoundException;
import com.demo.fab.walletsystem.exceptions.WalletException;
import com.demo.fab.walletsystem.repository.UserRepository;
import com.demo.fab.walletsystem.repository.WalletRepository;
import com.demo.fab.walletsystem.service.TransactionService;
import com.demo.fab.walletsystem.service.WalletSendMoneyService;

@Service
public class WalletSendMoneyServiceImpl implements WalletSendMoneyService{
	private Logger logger =LoggerFactory.getLogger(WalletSendMoneyServiceImpl.class);
	public static final String TXN_TYPE_CREDIT="Credit";
	public static final String TXN_TYPE_DEBIT="Debit";
	private WalletRepository walletRepository;
	private TransactionService transactionService;
	private UserRepository userRepository;
	
	@Autowired
	public WalletSendMoneyServiceImpl(WalletRepository walletRepository, TransactionService transactionService,
			UserRepository userRepository) {
		super();
		this.walletRepository = walletRepository;
		this.transactionService = transactionService;
		this.userRepository = userRepository;
	}


	@Override
	@Transactional
	public ResponseEntity<String> sendMoney(String fromUsername, String toUsername, String money) {
		Long senderUserId=userRepository.findUserIdByUserName(fromUsername);
		
		if(senderUserId==null) {
			logger.error("Cannot complete transaction :: Sender UserName {} not found in DB",fromUsername);
			throw new UserProfileNotFoundException("Cannot complete transaction :: Sender username [ "+fromUsername+" ] not found");
		}
		
		Long recieverUserId=userRepository.findUserIdByUserName(toUsername);
		if(recieverUserId==null) {
			logger.error("Cannot complete transaction :: Reciever UserName {} not found in DB",toUsername);
			throw new UserProfileNotFoundException("Cannot complete transaction :: Reciever username [ "+toUsername+" ] not found");
		}
		
		Float txnMoney= Float.valueOf(money);
		Float senderWalletAmount=walletRepository.findCurrentWalletAmount(senderUserId);
		Float recieverWalletAmount=walletRepository.findCurrentWalletAmount(recieverUserId);
		
		if(txnMoney<=0)
		{
			logger.warn("Send txn money must be greater than 0.0");
			throw new TransactionCreationException("Send txn money must be greater than 0.0", new RuntimeException("Send money must not be 0"));
		}
		
		if(txnMoney>senderWalletAmount) {
			logger.warn("Sender total wallet money found {} must be greater than or equal to txnmoney {}",senderWalletAmount,txnMoney);
			throw new WalletException("Sender wallet does not have enough money to transfer,First add money in wallet!", new RuntimeException("Sender total wallet amount not enough! consider adding money in wallet first"));
		}
		
			transactionService.createTransaction(senderUserId, txnMoney, TXN_TYPE_DEBIT,senderWalletAmount);
			walletRepository.debitMoneyInWalletByWalletId(senderUserId, txnMoney);	
			transactionService.createTransaction(recieverUserId, txnMoney, TXN_TYPE_CREDIT,recieverWalletAmount);
			walletRepository.creditMoneyInWalletByWalletId(recieverUserId, txnMoney);
	
		   return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

}
