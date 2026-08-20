package com.pm.security.service;

import com.pm.security.model.Users;
import com.pm.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public void registeruser(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        userRepo.save(users);
    }
}
