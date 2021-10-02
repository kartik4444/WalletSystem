package com.demo.fab.walletsystem.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "wallet")
@Data
public class Wallet {

	@Id
	@Column(name = "walletId")
	private Long walletId;
	
	@Column(name = "totalWalletAmount")
	private Double totalWalletAmount;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	private Timestamp lastUpdatedAt;
}
