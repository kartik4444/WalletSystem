package com.demo.fab.walletsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.fab.walletsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "select userId from user where username=?1",nativeQuery = true)
	public Long findUserIdByUserName(String username);
	
	@Query(value = "select * from user where username=?1",nativeQuery = true)
	public User getProfileByUserName(String username);
	
	@Query(value = "select count(1) from user where username=?1",nativeQuery = true)
	public Integer isUserNameAlreadyExist(String userName);
}
