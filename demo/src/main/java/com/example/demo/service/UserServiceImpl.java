package com.example.demo.service;

import com.example.demo.domains.AppUser;
import com.example.demo.domains.Roles;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new IllegalStateException("User not found in the database");
        } else {
            log.info("User found in the database : {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Roles saveRole(Roles roles) {
        log.info("Saving roles {} to the database", roles.getName());
        return rolesRepository.save(roles);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("Adding roles {} to user {}  the database", rolename, username);
        AppUser user = userRepository.findByUsername(username);
        Roles roles = rolesRepository.findByName(rolename);
        user.getRoles().add(roles);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Getting user {} from database", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Getting users from database ");
        return userRepository.findAll();
    }


}
