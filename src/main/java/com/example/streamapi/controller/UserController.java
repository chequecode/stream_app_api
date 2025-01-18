package com.example.streamapi.controller;

import com.example.streamapi.DTO.UserDTO;
import com.example.streamapi.entities.User;
import com.example.streamapi.errors.ErrorMessage;
import com.example.streamapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
        try {
            User createdUser = userService.createUser(convertToEntity(userDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); //201
        } catch (DataIntegrityViolationException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage); //400
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage); //500
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(this::convertToDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            User updatedUser = userService.updateUser(id, convertToEntity(userDTO));
            return ResponseEntity.ok(updatedUser);
        } catch (DataIntegrityViolationException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (RuntimeException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setId(userDTO.getId());
        user.setSubscriptionStart(userDTO.getSubscriptionStart());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setSubscriptionStart(user.getSubscriptionStart());
        userDTO.setName(user.getName());
        return userDTO;
    }
}
