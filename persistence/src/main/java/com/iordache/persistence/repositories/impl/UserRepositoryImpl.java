package com.iordache.persistence.repositories.impl;

import com.iordache.entity.User;
import com.iordache.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;


    @Override
    public void createUser(User user){
        entityManager.persist(user);
    }


    @Override
    public Optional<User> findUserByUsername(String username){
        String jpql = "SELECT u FROM User u WHERE u.username =: username";

        return entityManager.createQuery(jpql, User.class)
                            .setParameter("username", username)
                            .getResultStream()
                            .findFirst();
    }


    @Override
    public Optional<User> findUserByEmail(String email){
        String jpql = "SELECT u FROM User u WHERE u.email =: email";

        return entityManager.createQuery(jpql, User.class)
                            .setParameter("email", email)
                            .getResultStream()
                            .findFirst();
    }


    @Override
    public Optional<User> findUserByPhoneNumber(String phoneNumber){
        String jpql = "SELECT u FROM User u WHERE u.phoneNumber =: phoneNumber";

        return entityManager.createQuery(jpql, User.class)
                            .setParameter("phoneNumber", phoneNumber)
                            .getResultStream()
                            .findFirst();
    }


}
