package com.demo.fab.walletsystem.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;

import com.demo.fab.walletsystem.exceptions.UserProfileNotFoundException;
import com.demo.fab.walletsystem.exceptions.UserProfileUpdateException;
import com.demo.fab.walletsystem.exceptions.UserProfileViewException;
import com.demo.fab.walletsystem.model.User;
import com.demo.fab.walletsystem.repository.UserRepository;
import com.demo.fab.walletsystem.service.UserProfileService;
import com.demo.fab.walletsystem.util.WalletUtils;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	private Logger logger =LoggerFactory.getLogger(SignUpServiceImpl.class);
	private UserRepository userRepository;
	
	
	@Autowired
	public UserProfileServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void updateUserProfile(String username,User updateProfileData) {
		try {
		logger.info("**Inside updateUserProfile method**");
		User existingUser=userRepository.getProfileByUserName(username);
		
		if(existingUser==null) {
			logger.error("Existing username {} not found in DB,provide valid username ",username);
			throw new UserProfileNotFoundException("Existing username "+username+" not found in DB,provide valid username");
		}
		logger.info("Updating user profile for userId {} ",existingUser.getUserId());
		existingUser.setLastUpdatedAt(WalletUtils.getDateTime());
		WalletUtils.copyNonNullProperties(updateProfileData, existingUser);
		userRepository.save(existingUser);
		logger.info("User profile updated successfully {} for id {}",updateProfileData.getUserId());
		logger.info("**Exit from updateUserProfile method**");	
		}
		catch(DataAccessException |TransactionException | BeansException e) {
			logger.error("Error while updating User {} profile information ",username,e);
			throw new UserProfileUpdateException("User Profile update operation failed for user: "+username+" Try Again later!", e);
		}
	}

	@Override
	public User fetchUserProfile(String userName)
	{
		logger.info("**Inside updateUserProfile method**");
		User user=null;
		try {
		 user=userRepository.getProfileByUserName(userName);
		 if(user==null)
		 {
			 logger.error("username {} not found in DB",userName);
			 throw new UserProfileNotFoundException("Username "+userName+" not found in DB, Please provide valid username");
		 }
		logger.info("User Profile found success {}",user);
		   }
	   catch(DataAccessException | TransactionException e)
		{
		   logger.error("Error while fetching user profile from DB for user {}",userName,e);
		   throw new UserProfileViewException("Error while fetching user profile from DB for user "+userName, e);		   
		}
		return user;	
	}

}