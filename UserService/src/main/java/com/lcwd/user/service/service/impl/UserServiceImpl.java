package com.lcwd.user.service.service.impl;

import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        try {
            String randamUserId = UUID.randomUUID().toString();
            user.setUserId(randamUserId);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving user: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUser() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving all users: " + e.getMessage());
        }
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found exception"+userId));
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(String userId, User user) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setAbout(user.getAbout());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User with given id " + userId + " not found"));
    }
}
