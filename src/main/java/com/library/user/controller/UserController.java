package com.library.user.controller;

import com.library.user.model.UserRequestDto;
import com.library.user.model.UserResponseDto;
import com.library.user.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id){

        final UserResponseDto userResponseDto = userServiceImpl.findUserById(id);

        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping
    public List<UserResponseDto> findAllUser(){

        return userServiceImpl.findAllUsers();
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserRequestDto userRequestDto){

        userServiceImpl.saveUser(userRequestDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserById(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){

        final UserResponseDto userResponseDto = userServiceImpl.updateUserById(id, userRequestDto);

        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){

        userServiceImpl.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUser(){

        userServiceImpl.deleteAllUser();

        return ResponseEntity.noContent().build();
    }
}
