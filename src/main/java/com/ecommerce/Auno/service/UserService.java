package com.ecommerce.Auno.service;

import com.ecommerce.Auno.exception.UserException;
import com.ecommerce.Auno.model.User;

public interface UserService {
	public User findUserById(Long userId) throws UserException;

	public User findUserProfileByJwt(String jwt) throws UserException;



}
