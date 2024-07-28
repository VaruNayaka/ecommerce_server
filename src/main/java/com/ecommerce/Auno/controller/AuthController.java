package com.ecommerce.Auno.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Auno.Configuration.JwtProvider;
import com.ecommerce.Auno.exception.UserException;
import com.ecommerce.Auno.model.Cart;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.repository.UserRepository;
import com.ecommerce.Auno.request.LoginRequest;
import com.ecommerce.Auno.response.AuthResponse;
import com.ecommerce.Auno.service.CartService;
import com.ecommerce.Auno.service.CustomServiceImplementation;



@RestController
@RequestMapping("/auth")
public class AuthController {
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CustomServiceImplementation customServiceImplementation;
	private CartService cartService;

	public AuthController(UserRepository userRepository, CustomServiceImplementation customServiceImplementation,
			PasswordEncoder passwordEncoder,JwtProvider jwtProvider,CartService cartService) {
		this.userRepository = userRepository;
		this.customServiceImplementation = customServiceImplementation;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider=jwtProvider;
		this.cartService=cartService;
	}

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
		String email = user.getEmail();
		String password = user.getPassword();
		String firstname = user.getFirstName();
		String lastname = user.getLastName();

		User isEmailExist = userRepository.findByEmail(email);
		if (isEmailExist != null) {
			throw new UserException("email already exists");
		}

		User cratedUser = new User();
		cratedUser.setEmail(email);
		cratedUser.setPassword(passwordEncoder.encode(password));
		cratedUser.setFirstName(firstname);
		cratedUser.setLastName(lastname);

		User savedUser = userRepository.save(cratedUser);
		Cart cart=cartService.createCart(savedUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("signup success...");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException {
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("signin success...");
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customServiceImplementation.loadUserByUsername(username);
		if (userDetails == null) {
			throw new BadCredentialsException("Invalid user name...");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password...");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
