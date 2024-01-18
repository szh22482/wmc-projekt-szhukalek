package com.szhukalek.backend.service;

import com.szhukalek.backend.dto.UserDTO;
import com.szhukalek.backend.model.User;
import com.szhukalek.backend.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    public List<UserDTO> fetchAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::fromEntity)
                .toList();

    }

    public ResponseEntity<String> loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Login, Email wrong");
        }

        if(!checkPassword(user, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Login, Password wrong");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Login Successful!");
    }

    private boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public UserDTO updateUser(Long id, UserDTO updatedUser) {
        User existingUser = userRepository.findById(id);

        if(existingUser == null) {
            return null;
        }

        return null;
    }

    public UserDTO fetchUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? UserDTO.fromEntity(user) : null;
    }

}
