package com.example.demo.controller;

import com.example.demo.domains.AppUser;
import com.example.demo.domains.Roles;
import com.example.demo.model.RoleRequestForm;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/")
public class UserControler {
    private final UserService userService;

    @GetMapping("getUsers")
    private ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("save")
    private ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("role/save")
    private ResponseEntity<Roles> saveRole(@RequestBody Roles roles) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(roles));
    }


    @PostMapping("user/addRole")
    private ResponseEntity<?> saveRoleToUser(@RequestBody RoleRequestForm form) {
        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }
}
