package com.library.user.service;

import com.library.user.model.UserRequestDto;
import com.library.user.model.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserResponseDto> findAllUsers();

    UserResponseDto findUserById(Long id);

    void saveUser(UserRequestDto userRequestDto);

    UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto);

    void deleteUserById(Long id);

    void deleteAllUser();
}
