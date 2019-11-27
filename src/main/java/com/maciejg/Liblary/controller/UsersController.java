package com.maciejg.Liblary.controller;

import com.maciejg.Liblary.entity.Users;
import com.maciejg.Liblary.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("login")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity getUsersInfo() {
        return new ResponseEntity(usersRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public void addNewUser(@Valid @RequestBody Users users) {
        usersRepository.save(users);
    }

    @GetMapping("/hello")
    public String hello() {
        return  "hello World";
    }
}
