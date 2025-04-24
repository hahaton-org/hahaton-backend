package com.trymad.hahaton.config.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trymad.hahaton.config.entity.MyRole;
import com.trymad.hahaton.config.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByName(MyRole name);

}
