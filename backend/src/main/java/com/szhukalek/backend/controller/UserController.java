package com.szhukalek.backend.controller;

import com.szhukalek.backend.dto.UserDTO;
import com.szhukalek.backend.model.User;
import com.szhukalek.backend.persistence.UserRepository;
import com.szhukalek.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"})
public class UserController {

    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userService.loginUser(email, password);
    }

    @GetMapping("/all")
    public List<UserDTO> listAll() {
       return userService.fetchAllUsers();
    }

    @PutMapping("update/{id}")
    public @ResponseStatus ResponseEntity<?> update(@PathVariable final Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("delete/{id}")
    public @ResponseStatus ResponseEntity<?> delete(@PathVariable final Long id) {
        return userService.delete(id);
    }


}
