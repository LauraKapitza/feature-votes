package com.featurevotes.service;

import com.featurevotes.domain.Authority;
import com.featurevotes.domain.User;
import com.featurevotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Authority userAuthority = new Authority();
        userAuthority.setAuthority("ROLE_USER");
        userAuthority.setUser(user);
        user.getAuthorities().add(userAuthority);
        userRepository.save(user);
    }
}
