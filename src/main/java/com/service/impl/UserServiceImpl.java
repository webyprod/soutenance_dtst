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
	public Optional<User> update(Long id, User user) {
		return userRepository.findById(id)
				.map(existing -> {
					existing.setUsername(user.getUsername());
					existing.setFirstName(user.getFirstName());
					existing.setLastName(user.getLastName());
					existing.setPassword(user.getPassword());
					existing.setAge(user.getAge());
					existing.setCountry(user.getCountry());
					return userRepository.save(existing);
				});
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
