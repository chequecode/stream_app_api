package com.example.streamapi.service;

import com.example.streamapi.entities.User;
import com.example.streamapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setSubscriptionStart(userDetails.getSubscriptionStart());
        user.setId(userDetails.getId());
        user.setName(userDetails.getName());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        userRepository.delete(user);
    }
}
