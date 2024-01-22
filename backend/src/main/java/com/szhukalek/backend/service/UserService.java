package com.szhukalek.backend.service;

import com.szhukalek.backend.dto.UserDTO;
import com.szhukalek.backend.model.*;
import com.szhukalek.backend.persistence.RoleRepository;
import com.szhukalek.backend.persistence.UserRepository;
import com.szhukalek.backend.persistence.UserToRolesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserToRolesRepository userToRolesRepository;

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

    @Transactional
    public ResponseEntity<String> updateUser(Long id, UserDTO updatedUser) {
        try{
            User user = userRepository.findById(id);
            if(user != null) {
                user.setVorname(updatedUser.firstname());
                user.setNachname(updatedUser.lastname());
                user.setEmail(updatedUser.email());
                user.setPassword(updatedUser.password());
                List<UserToRoles> updatedRoles = new ArrayList<>();
                userToRolesRepository.deleteByUser(user);
                for(String roleName : updatedUser.roles()) {
                    try {
                        ERoles roleEnum = ERoles.valueOf(roleName);
                        Role role = roleRepository.findByRole(roleEnum);

                        UserToRoles newUserToRoles = UserToRoles.builder()
                                .UserToRolesId(new UserToRolesId(user.getId(), role.getId()))
                                .user(user)
                                .role(role)
                                .build();

                        updatedRoles.add(newUserToRoles);
                        userToRolesRepository.save(newUserToRoles);
                    } catch (IllegalArgumentException e) {
                        return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
                    }
                }
                user.setRoles(updatedRoles);
                userRepository.save(user);
            }
        } catch (Exception e) {
            return new ResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("OK", HttpStatusCode.valueOf(200));
    }


    public UserDTO fetchUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? UserDTO.fromEntity(user) : null;
    }

}
