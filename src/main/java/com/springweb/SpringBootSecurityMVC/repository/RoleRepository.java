package com.springweb.SpringBootSecurityMVC.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springweb.SpringBootSecurityMVC.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByRole(String role);
	
	@Query("SELECT r FROM Role r WHERE r.role = ?1") //Call one by one
	Set<Role> findByRoles(String role);
	
	@Query(value = "SELECT r FROM Role r WHERE r.role IN :roleNames")
	Set<Role> findRolesByNameSet(@Param("roleNames") Set<String> roleNames);
}
