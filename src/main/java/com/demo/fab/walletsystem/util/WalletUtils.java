package com.demo.fab.walletsystem.util;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.demo.fab.walletsystem.model.User;
import com.demo.fab.walletsystem.model.Wallet;

public class  WalletUtils {

	 public static Wallet toWalletModel(User newSignedUpUser)
	 {
		 Wallet newWallet =new Wallet();
		 newWallet.setTotalWalletAmount(0.0);
		 newWallet.setWalletId(newSignedUpUser.getUserId());
		 newWallet.setCreatedAt(getDateTime());
		 newWallet.setLastUpdatedAt(getDateTime());
		 return newWallet;
	 }
	 
	 public static  Timestamp getDateTime() {
		return  new Timestamp(System.currentTimeMillis());
	 }
	 
	 public static void copyNonNullProperties(Object src, Object target) {
		    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
		}
	
	 public static String[] getNullPropertyNames (Object source) {
		    final BeanWrapper src = new BeanWrapperImpl(source);
		    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		    Set<String> emptyNames = new HashSet<String>();
		    for(java.beans.PropertyDescriptor pd : pds) {
		        Object srcValue = src.getPropertyValue(pd.getName());
		        if (srcValue == null) emptyNames.add(pd.getName());
		    }
		    String[] result = new String[emptyNames.size()];
		    return emptyNames.toArray(result);
		}
}
