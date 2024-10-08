package com.springweb.SpringBootSecurityMVC.service;

import java.util.Optional;

import com.springweb.SpringBootSecurityMVC.model.UserEntity;

public interface UserEntityService {
	Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

	//Để chỗ này HỢP lý //////
    void createNewUserEntity(UserEntity userEntity) ;
}
