package com.featurevotes.repository;

import com.featurevotes.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
