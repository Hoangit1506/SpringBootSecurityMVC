package com.springweb.SpringBootSecurityMVC.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springweb.SpringBootSecurityMVC.model.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByUsername(String username);
}
