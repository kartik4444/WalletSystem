package com.demo.fab.walletsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.fab.walletsystem.model.Transactions;

@Service
public interface TransactionService {

	public void createTransaction(Long txnId,Float money,String txntype,Float senderWalletAmount);
	
	public List<Transactions> viewPassbook(String username);
}
