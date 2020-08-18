package com.josh.usersrestapi.repository;

import com.josh.usersrestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Tejashree Surve
 * @Purpose : This is User Repository which extend JpaRepository.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    // User find By email
    Optional<User> findByEmail(String email);
}
