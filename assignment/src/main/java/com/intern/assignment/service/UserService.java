package com.intern.assignment.service;

import com.intern.assignment.entity.User;
import com.intern.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save or update user
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Get all users with Search and Pagination functionality
    public Page<User> getAllUsers(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrMobileContainingIgnoreCase(
                    keyword, keyword, keyword, pageable);
        }
        return userRepository.findAll(pageable);
    }

    // Find user by ID for editing
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Check if email already exists
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Check if Mobile number already exists
    public boolean existsByMobile(String mobile) {
        return userRepository.existsByMobile(mobile);
    }
}