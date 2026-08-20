package com.pm.security.service;

import com.pm.security.model.UserCurrent;
import com.pm.security.model.Users;
import com.pm.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Load user called" + username);
        try {


            Users users = userRepo.findByUsername(username);
            System.out.println("After database call");
            System.out.println("users" + users);

            if (users == null) {
                System.out.println("user not found");
                throw new UsernameNotFoundException("user not found");
            }

            System.out.println("username from postman = " + username);
            System.out.println("pass from db  " + users.getPassword());
            return new UserCurrent(users);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
