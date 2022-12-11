package com.shahian.springBootSecurity;

import com.shahian.springBootSecurity.model.RegistrationDTO;
import com.shahian.springBootSecurity.model.Role;
import com.shahian.springBootSecurity.servic.RoleService;
import com.shahian.springBootSecurity.servic.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService) {
        return args -> {

            roleService.addRole(Role.builder().name(Role.ROLE_ADMIN).build());
            roleService.addRole(Role.builder().name(Role.ROLE_USER).build());
            Role roleAdmin = roleService.getRoleByName(Role.ROLE_ADMIN);
            Role roleUser = roleService.getRoleByName(Role.ROLE_USER);
            List<Role> roles = new ArrayList<>();
            List<Role> roles2 = new ArrayList<>();
            roles.add(roleAdmin);
            roles2.add(roleUser);
            userService.registration(new RegistrationDTO("hamidreza", "shahian", 37, "shahian", "123456"), roles);
            userService.registration(new RegistrationDTO("dayan", "shahian", 1, "dayan", "654321"), roles2);
        };
    }
}
