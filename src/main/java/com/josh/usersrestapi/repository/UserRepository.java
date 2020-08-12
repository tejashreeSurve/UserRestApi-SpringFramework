package com.josh.usersrestapi.repository;

import com.josh.usersrestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tejashree Surve
 * @Purpose : This is User Repository which extend JpaRepository.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    // User find By email
    User findByEmail(String email);
}
