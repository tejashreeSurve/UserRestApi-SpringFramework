package com.josh.usersrestapi.repository;

import com.josh.usersrestapi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tejashree Surve
 * @Purpose : This is User Repository which extend JpaRepository.
 */
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    // User find By email
    UserEntity findByEmail(String email);
}
