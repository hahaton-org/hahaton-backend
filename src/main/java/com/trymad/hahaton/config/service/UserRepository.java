package com.trymad.hahaton.config.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trymad.hahaton.config.entity.MyUser;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, UUID> {
	
	@Transactional
	Optional<MyUser> findByMail(String mail);
	
}
