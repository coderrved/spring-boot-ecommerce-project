package com.example.bookstore.controller;

import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BookStoreController {


    private final UserRepository userRepository;

    public BookStoreController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("helloworld")
    public String helloWorld(){
        return "Hello World!";
    }

    @PostMapping("login")
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("alluser")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
