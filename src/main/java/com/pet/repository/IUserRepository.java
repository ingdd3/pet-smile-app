package com.pet.repository;

import com.pet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}