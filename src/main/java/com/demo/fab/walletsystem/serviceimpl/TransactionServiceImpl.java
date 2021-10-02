package com.demo.fab.walletsystem.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;

import com.demo.fab.walletsystem.exceptions.TransactionCreationException;
import com.demo.fab.walletsystem.exceptions.UserPassbookViewException;
import com.demo.fab.walletsystem.exceptions.UserProfileNotFoundException;
import com.demo.fab.walletsystem.model.Transactions;
import com.demo.fab.walletsystem.repository.TxnRepository;
import com.demo.fab.walletsystem.repository.UserRepository;
import com.demo.fab.walletsystem.service.TransactionService;
import com.demo.fab.walletsystem.util.WalletUtils;

@Service
public class TransactionServiceImpl implements TransactionService {
	private Logger logger =LoggerFactory.getLogger(TransactionServiceImpl.class);
	public static final String TXN_TYPE_CREDIT="Credit";
	public static final String TXN_TYPE_DEBIT="Debit";
	private TxnRepository txnRepository;
	private UserRepository userRepository;
	
	
	@Autowired
	public TransactionServiceImpl(TxnRepository txnRepository, UserRepository userRepository) {
		super();
		this.txnRepository = txnRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void createTransaction(Long txnId, Float money, String txntype,Float senderWalletAmount) {
		Transactions transaction =new Transactions();
		UUID uuid = UUID.randomUUID();
		transaction.setTxnReference(uuid.toString().substring(0, 20));
		transaction.setTxnId(txnId);
		transaction.setTxnType(txntype);
		if(txntype.equalsIgnoreCase(TXN_TYPE_CREDIT)) 
		{			
		transaction.setClosingWalletAmount(senderWalletAmount+money);
		}
		else 
		{
		transaction.setClosingWalletAmount(senderWalletAmount-money);
		}
		transaction.setTxnAmount(money);
		transaction.setProcessedDateTime((WalletUtils.getDateTime()));
		
		try {
		txnRepository.save(transaction);
		}
		catch(DataAccessException | TransactionException e) 
		{
			logger.error("Error while storing new transaction in DB :: userId {} txnType {} amount {}",txnId,txntype,money,e);
			throw new TransactionCreationException("Error while saving new transaction in DB :: userId "+txnId+" txnType "+txntype+" amount "+money, e);
		}
	}
	
	
	@Override
	public List<Transactions> viewPassbook(String username) {
		Long userId=userRepository.findUserIdByUserName(username);
		List<Transactions> txnList=null;
		if(userId==null) {
			logger.error("UserName {} not found in DB :: Cannot Display Passbook statements",username);
			throw new UserProfileNotFoundException("Cannot Display Passbook statements :: userName "+username+" Not found in DB, please provide valid username");
		}
		try {
		txnList= txnRepository.fetchAllTxnsByTxnId(userId);
		}
		catch(DataAccessException | TransactionException e) {
			logger.error("Error while fetching passbook statements for username {}",username);
			throw new UserPassbookViewException("Error while fetching passbook statements for username "+username,e);
		}	
		return txnList;
	}
	

}
