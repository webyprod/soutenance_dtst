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

	@Override
	public void run(String... args) throws Exception {

		// Supprime tous les utilisateurs existants
		userRepository.deleteAll();
		
		User user1 = new User("FoxM", UUID.randomUUID().toString(), "Fox", "Mccloud", 35, "US");
		User user2 = new User("FullC", UUID.randomUUID().toString(), "Full", "Coast", 24, "BRA");
		User user3 = new User("JefferS", UUID.randomUUID().toString(), "Jeffer", "Son", 34, "MEX");
		User user4 = new User("HenriD", UUID.randomUUID().toString(), "Henri", "Dubois", 19, "USA");
		User user5 = new User("AlexM", UUID.randomUUID().toString(), "Alex", "Macleod", 42, "CAN");
		User user6 = new User("FernandoC", UUID.randomUUID().toString(), "Fernando", "Cruz", 19, "USA");
		User user7 = new User("BearC", UUID.randomUUID().toString(), "Bear", "Creazy", 32, "USA");
		User user8 = new User("JohnC", UUID.randomUUID().toString(), "John", "Cho", 42, "USA");
		User user9 = new User("JohnD", UUID.randomUUID().toString(), "John", "Doe", 36, "CAN");
		User user10 = new User("ChrisR", UUID.randomUUID().toString(), "Claire", "Redfield", 24, "CAN");

		List<User> usersList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);

		usersList = usersList.stream().map(user -> {
			user.setPassword(user.getPassword());
			return user;
		}).collect(Collectors.toList());
		
		userRepository.saveAll(usersList);

	}

}
