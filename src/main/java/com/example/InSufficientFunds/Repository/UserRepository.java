package com.example.InSufficientFunds.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InSufficientFunds.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
