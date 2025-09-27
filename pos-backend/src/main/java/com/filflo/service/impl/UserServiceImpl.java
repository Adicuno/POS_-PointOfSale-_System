package com.filflo.service.impl;


import com.filflo.configrations.JwtProvider;
import com.filflo.domain.UserRole;
import com.filflo.exception.UserException;


import com.filflo.repository.PasswordResetTokenRepository;
import com.filflo.repository.UserRepository;

import com.filflo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


import com.filflo.modal.User;
import com.filflo.repository.BranchRepository;
import com.filflo.repository.StoreRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//	private final OtpRepository otpRepository;
	private final UserRepository userRepository;
	private final StoreRepository storeRepository;
	private final BranchRepository branchRepository;
//	private final EmailUtil emailUtil;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final PasswordResetTokenRepository passwordResetTokenRepository;


	@Override
	public User getUserByEmail(String email) throws UserException {
		User user=userRepository.findByEmail(email);
		if(user==null){
			throw new UserException("User not found with email: "+email);
		}
		return user;
	}

	@Override
	public User getUserFromJwtToken(String jwt) throws UserException {
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		if(user==null) throw new UserException("user not exist with email "+email);
		return user;
	}

	@Override
	public User getUserById(Long id) throws UserException {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public Set<User> getUserByRole(UserRole role) throws UserException {
		return userRepository.findByRole(role);
	}

	@Override
	public User getCurrentUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user= userRepository.findByEmail(email);
		if(user == null) {
			throw new EntityNotFoundException("User not found");
		}
		return user;
	}

	@Override
	public List<User> getUsers() throws UserException {
		return userRepository.findAll();
	}


}
