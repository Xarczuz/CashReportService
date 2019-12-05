package com.CashReportSystem;

import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		User user = new User();

		user.setUsername("test");
		user.setPassword("test");

		applicationContext.getBean(UserRepository.class).save(user);
		User user2 = new User();

		user2.setUsername("test");
		user2.setPassword("test");

		applicationContext.getBean(UserRepository.class).save(user2);
		applicationContext.getBean(UserRepository.class).deleteById(1L);
		User user3 = new User();

		user3.setUsername("test");
		user3.setPassword("test");

		applicationContext.getBean(UserRepository.class).save(user3);
	}

}
