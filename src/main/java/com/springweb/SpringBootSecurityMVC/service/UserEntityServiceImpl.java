package com.springweb.SpringBootSecurityMVC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springweb.SpringBootSecurityMVC.model.UserEntity;
import com.springweb.SpringBootSecurityMVC.repository.UserEntityRepository;

@Service
public class UserEntityServiceImpl implements UserEntityService{
	@Autowired
	private UserEntityRepository userEntityRepository;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Override
	public Optional<UserEntity> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userEntityRepository.findByUsername(username);
	}

	@Override
	public Optional<UserEntity> findByEmail(String email) {
		// TODO Auto-generated method stub
		return userEntityRepository.findByEmail(email);
	}

	@Override
	public void createNewUserEntity(UserEntity userEntity) {
		// TODO Auto-generated method stub
		userEntity.setPassword(pwdEncoder.encode(userEntity.getPassword()));
		userEntityRepository.save(userEntity);
	}
	
}
