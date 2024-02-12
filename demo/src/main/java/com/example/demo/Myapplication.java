package com.example.demo;

import com.example.demo.domains.AppUser;
import com.example.demo.domains.Roles;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Myapplication {

    public static void main(String[] args) {
        SpringApplication.run(Myapplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            userService.saveRole(new Roles(null, "ROLE_USER"));
            userService.saveRole(new Roles(null, "ROLE_MANAGER"));
            userService.saveRole(new Roles(null, "ROLE_ADMIN"));
            userService.saveRole(new Roles(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new AppUser(null, "shankar bhandari", "shankar", "12345", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "john Travolta", "john", "12345", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "Will Smith", "will", "12345", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "arnold Schwarzenegger", "arnold", "12345", new ArrayList<>()));

            userService.addRoleToUser("shankar", "ROLE_USER");
            userService.addRoleToUser("will", "ROLE_MANAGER");
            userService.addRoleToUser("arnold", "ROLE_ADMIN");
            userService.addRoleToUser("john", "ROLE_USER_ADMIN");


        };
    }

}
