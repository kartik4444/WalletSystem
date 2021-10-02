package com.demo.fab.walletsystem.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.demo.fab.walletsystem.model.User;

@Service
public interface SignUpService {
	public ResponseEntity<String> registerNewUser(User signUpData);
}
