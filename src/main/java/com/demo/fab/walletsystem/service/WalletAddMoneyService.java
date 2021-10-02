package com.demo.fab.walletsystem.service;

import org.springframework.stereotype.Service;

@Service
public interface WalletAddMoneyService {

	public void addmoney(Float amount,String username);
}
