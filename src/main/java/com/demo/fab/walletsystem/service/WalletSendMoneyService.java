package com.demo.fab.walletsystem.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface WalletSendMoneyService {

	public ResponseEntity<String> sendMoney(String fromUsername,String toUsername, String money);
}
