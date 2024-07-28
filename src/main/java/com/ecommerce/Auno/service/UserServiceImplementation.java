package com.ecommerce.Auno.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.Auno.Configuration.JwtProvider;
import com.ecommerce.Auno.exception.UserException;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	

	public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public User findUserById(Long userId) throws UserException {
	Optional<User> user=userRepository.findById(userId);
	if(user.isPresent()) {
		return user.get();
	}
		throw new UserException("User Not Found With id :"+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromToken(jwt);
		User  user=userRepository.findByEmail(email);
		if(user==null) {
			throw new UserException("user Not Found With Email :"+email);
		}
		return user;
	}

	
	}

