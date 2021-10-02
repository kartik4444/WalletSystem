package com.demo.fab.walletsystem.service;

import org.springframework.stereotype.Service;

import com.demo.fab.walletsystem.model.User;

@Service
public interface UserProfileService  {
public void updateUserProfile(String username,User updateProfileData);
public User fetchUserProfile(String userName);
}
