package com.josh.usersrestapi.repository;

import com.josh.usersrestapi.model.BlackListedToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Tejashree Surve
 * @Purpose : This is BlackListToken Repository which extend JpaRepository.
 */
public interface BlackListTokenRepository extends JpaRepository<BlackListedToken,Integer> {

     Optional<BlackListedToken> findByToken(String token);
}
