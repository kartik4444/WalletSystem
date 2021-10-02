package com.demo.fab.walletsystem.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userId",nullable = false)
	private Long userId;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "username",nullable = false)
	private String userName;
	
	@Column(name = "fullname",nullable = false)
	private String fullName;
	
	@Column(name = "city",nullable=false)
	private String city;
	
	@Column(name = "address",nullable=false)
	private String address;
	
	@Column(name = "email",nullable=false)
	private String emailId;
	
	@Column(name = "mobilenumber",nullable=false)
	private String mobileNumber;
	
	@Column(name = "account_number",nullable=false)
	private String accountNumber;
	
	@Column(name = "bankcardtype",nullable=false)
	private String bankCardType;
	
	@Column(name = "bankcardnumber",nullable=false)
	private String bankCardNumber;
	
	@Column(name = "pancard",nullable=false)
	private String panCard;
	
	@Column(name = "gender",nullable=false)
	private String gender;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "lastUpdatedAt")
	private Timestamp lastUpdatedAt;
	
}
