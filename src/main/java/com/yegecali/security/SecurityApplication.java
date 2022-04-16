package com.yegecali.security;

import com.yegecali.security.model.Role;
import com.yegecali.security.model.UserAuth;
import com.yegecali.security.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserServiceImpl userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN	"));

			userService.saveUser(new UserAuth(null, "genderson", "genderson", "1234", new ArrayList<>()));
			userService.saveUser(new UserAuth(null, "yegecali", "yegecali", "1234", new ArrayList<>()));
			userService.saveUser(new UserAuth(null, "greyco", "greyco", "1234", new ArrayList<>()));
			userService.saveUser(new UserAuth(null, "carolay", "carolay", "1234", new ArrayList<>()));
			userService.saveUser(new UserAuth(null, "emily", "emily", "1234", new ArrayList<>()));

			userService.addRoleToUser("genderson", "ROLE_USER");
			userService.addRoleToUser("yegecali", "ROLE_MANAGER");
			userService.addRoleToUser("greyco", "ROLE_ADMIN");
			userService.addRoleToUser("carolay", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("emily", "ROLE_USER");
		};
	}
}
