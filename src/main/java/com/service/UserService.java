package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.model.User;

public interface UserService {

	public List<User> findAll();

	public Optional<User> findById(Long id);
	
	public List<User> findByCriteria(String criteria, String searchItem);
	
	public void add(User user);
	
	public Optional<User> update(Long id, User user);
	
	public Optional<User> delete(Long id);
}
