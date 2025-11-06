package com.service.impl;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	//@Autowired
	//private PasswordEncoder passwordEncoder;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void add(User user) {
		user.setPassword(user.getPassword());
		userRepository.save(user);
	}

	@Override
	public Optional<User> update(User user) {
		Optional<User> userOpt = userRepository.findById(user.getId());

		if (userOpt.isPresent()) {
			User existingUser = userOpt.get();

			if (user.getUsername() != null) {
				existingUser.setUsername(user.getUsername());
			}

			if (user.getPassword() != null) {
				existingUser.setPassword(user.getPassword());
			}

			if (user.getFirstName() != null) {
				existingUser.setFirstName(user.getFirstName());
			}

			if (user.getLastName() != null) {
				existingUser.setLastName(user.getLastName());
			}

			if (user.getAge() != null) {
				existingUser.setAge(user.getAge());
			}

			if (user.getCountry() != null) {
				existingUser.setCountry(user.getCountry());
			}

			userRepository.save(existingUser);

			return Optional.of(existingUser);
		}

		return Optional.empty();
	}

	@Override
	public Optional<User> delete(Long id) {
		Optional<User> userOpt = userRepository.findById(id);

		if (userOpt.isPresent()) {
			userRepository.delete(userOpt.get());
			return userOpt;
		}

		return Optional.empty();
	}

	@Override
	public List<User> findByCriteria(String criteria, String searchItem) {

		switch (criteria) {
		case "username":
			return this.userRepository.findByUsername(searchItem);
		case "firstName":
			return this.userRepository.findByFirstName(searchItem);
		case "lastName":
			return this.userRepository.findByLastName(searchItem);
		case "age":
			try {
				Integer age = Integer.valueOf(searchItem);
				return this.userRepository.findByAge(age);
			} catch (NumberFormatException e) {
				System.out.println("Could not convert age to number...");
			}
			return new ArrayList<>();
		case "country":
			return this.userRepository.findByCountry(searchItem);
		}
		return new ArrayList<>();
	}

}
