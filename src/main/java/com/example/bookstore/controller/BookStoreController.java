package com.example.bookstore.controller;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class BookStoreController {


    private final UserRepository userRepository;
    private final UserService userService;

    public BookStoreController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("helloworld")
    public String helloWorld(){
        return "Hello World!";
    }

    @PostMapping("adduser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("alluser")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("updateuser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        userService.updateUser((userDto));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("deleteuser/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
         return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("deletealluser")
    public ResponseEntity<String>  deleteAllUser(){
        return new ResponseEntity<>(userService.deleteAllUser(), HttpStatus.OK);
    }
}
