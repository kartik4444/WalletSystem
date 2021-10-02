package com.demo.fab.walletsystem.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
public class Transactions {

	@Id
	@Column(name = "txnreference")
	private String txnReference;
	
	@Column(name = "txnId")
	private Long txnId;
	
	@Column(name = "amount")
	private Float txnAmount;
	
	@Column(name = "txntype")
	private String txnType;
	
	@Column(name = "closingWalletAmount")
	private Float closingWalletAmount;
	
	@Column(name = "processed_date_time")
	private Timestamp processedDateTime;
}
