package com.ecommerce.Auno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Auno.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);

}
