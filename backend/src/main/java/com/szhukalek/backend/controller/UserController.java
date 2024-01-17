package com.szhukalek.backend.controller;

import com.szhukalek.backend.dto.UserDTO;
import com.szhukalek.backend.model.User;
import com.szhukalek.backend.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/all")
    public List<UserDTO> listAll() {
        try {
            List<User> users = userRepository.findAll();
            List<UserDTO> dtos = new ArrayList<>();
            users.forEach(user -> {
                if(!user.getDeleted()) {
                    dtos.add(new UserDTO(user.getId(), user.getEmail(), user.getVorname(), user.getNachname(), user.getCreated(), user.getRoles()));
                }
            });
            return dtos;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("update/{id}")
    public @ResponseStatus ResponseEntity update(@PathVariable final Long id, @RequestBody UserDTO userDTO) {
        try {
            User user = userRepository.findById(id);
            if(user != null) {
                user.setVorname(userDTO.firstname());
                user.setNachname(userDTO.lastname());
                user.setEmail(userDTO.email());
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }


}
