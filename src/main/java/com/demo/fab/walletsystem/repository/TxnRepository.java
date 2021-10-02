package com.demo.fab.walletsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.fab.walletsystem.model.Transactions;

@Repository
public interface TxnRepository extends JpaRepository<Transactions, String> {

	@Query(value = "select * from transactions where txnId=?1 order by processed_date_time DESC",nativeQuery = true)
	public List<Transactions> fetchAllTxnsByTxnId(Long txnId);
}
