package com.iordache.persistence.repositories;

import com.iordache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = ?1")
    Optional<User> findUserByUsername(String username);


    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);


    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.phoneNumber = ?1")
    Optional<User> findUserByPhoneNumber(String phoneNumber);

}
