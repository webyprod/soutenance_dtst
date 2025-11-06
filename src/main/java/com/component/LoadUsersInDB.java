package com.component;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	//@Autowired
	//private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if (userRepository.count() > 0) {
			return;
		}
		
		User user1 = new User("alexR", UUID.randomUUID().toString(), "Alex", "Rosu", 30, "ROMANIA");
		User user2 = new User("georgianaO", UUID.randomUUID().toString(), "Georgina", "Ortega", 24, "Brazil");
		User user3 = new User("rosaS", UUID.randomUUID().toString(), "Rosa", "Sparks", 34, "Mexic");
		User user4 = new User("orlaM", UUID.randomUUID().toString(), "Orla", "Mccoy", 19, "USA");
		User user5 = new User("jerryH", UUID.randomUUID().toString(), "Jerry", "Hanna", 42, "CANADA");
		User user6 = new User("savannahD", UUID.randomUUID().toString(), "Savannah", "Daniel", 19, "USA");
		User user7 = new User("abbyG", UUID.randomUUID().toString(), "Abby", "Green", 32, "USA");
		User user8 = new User("velmaG", UUID.randomUUID().toString(), "Velma", "Griffin", 42, "USA");
		User user9 = new User("serenaS", UUID.randomUUID().toString(), "Serena", "Singh", 36, "CANADA");
		User user10 = new User("veronimaC", UUID.randomUUID().toString(), "Veronima", "Cooper", 24, "CANADA");

		List<User> usersList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);

		usersList = usersList.stream().map(user -> {
			user.setPassword(user.getPassword());
			return user;
		}).collect(Collectors.toList());
		
		userRepository.saveAll(usersList);

	}

}
