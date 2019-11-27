package com.maciejg.Liblary.services;

import com.maciejg.Liblary.DTOS.UserDTO;
import com.maciejg.Liblary.entity.Users;
import com.maciejg.Liblary.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //check if user from request exist in database
        Users user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    //register new user
    public Users save(UserDTO user) {
        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        //code password
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return usersRepository.save(newUser);

    }
}
