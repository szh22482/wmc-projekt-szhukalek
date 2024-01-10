package com.szhukalek.backend.controller;

import com.szhukalek.backend.model.User;
import com.szhukalek.backend.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "localhost:3000", allowCredentials = "true")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = userRepository.findByEmail(email);
        System.out.println(user);

        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email");
        }

        if(!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Password");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Login Successful");
    }
}
