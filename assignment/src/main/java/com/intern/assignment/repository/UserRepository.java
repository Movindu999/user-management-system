package com.intern.assignment.repository;

import com.intern.assignment.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Search by Name, Email, or Mobile (Case Insensitive) with Pagination support
    Page<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrMobileContainingIgnoreCase(
            String name, String email, String mobile, Pageable pageable);
}