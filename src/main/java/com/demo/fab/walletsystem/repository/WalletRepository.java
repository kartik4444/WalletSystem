package com.demo.fab.walletsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.fab.walletsystem.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

	@Modifying
	@Query(value = "update wallet set totalWalletAmount= totalWalletAmount+?2 where walletId=?1", nativeQuery = true)
	public void creditMoneyInWalletByWalletId(Long walletId,Float money);
	
	@Query(value = "select totalWalletAmount from wallet where walletId=?1",nativeQuery = true)
	public Float findCurrentWalletAmount(Long walletId);
	
	@Modifying
	@Query(value = "update wallet set totalWalletAmount= totalWalletAmount-?2 where walletId=?1", nativeQuery = true)
	public void debitMoneyInWalletByWalletId(Long walletId,Float money);
}
