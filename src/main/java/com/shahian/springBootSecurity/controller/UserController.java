package com.shahian.springBootSecurity.controller;

import com.shahian.springBootSecurity.model.User;
import com.shahian.springBootSecurity.servic.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/User")
public class UserController {
    private final UserService userService;

    public UserController(UserService personService) {
        this.userService = personService;
    }

    @GetMapping("/v1/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.status(HttpStatus.OK).body("OK!...");
    }

    @GetMapping("/v1/getAll")
    public ResponseEntity<?> getAll() {
        List<User> allPerson = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(allPerson);
    }

    @GetMapping("/v1/getById")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        User Person = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Person);
    }

    @PostMapping("/v1/add")
    public ResponseEntity<?> add(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("user created");
    }

    @PutMapping("/v1/update")
    public ResponseEntity<?> updatePerson(@RequestParam Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("user updated");
    }

    @PutMapping("/v1/delete")
    public ResponseEntity<?> deletePerson(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("user deleted");
    }
}
