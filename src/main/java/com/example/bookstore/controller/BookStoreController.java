package com.example.bookstore.controller;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BookStoreController {

    private final UserRepository userRepository;
    private final UserService userService;

    public BookStoreController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
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
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @DeleteMapping("deletealluser")
    public void deleteAllUser() {
        userService.deleteAllUser();
    }
}
