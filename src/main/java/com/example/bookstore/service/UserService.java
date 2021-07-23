package com.example.bookstore.service;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.exception.UserNotFoundException;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto save(UserDto userDto) {

    User user = new User();
    user.setName(userDto.getName());
    user.setSurname(userDto.getSurname());
    user.setEmail(userDto.getEmail());
    user.setPhone(userDto.getPhone());
    user.setBirthDate(userDto.getBirthdate());
    user.setImageUrl(userDto.getImageUrl());
    user.setJobTitle(userDto.getJobTitle());
    userRepository.save(user); // DB'ye ekledim.
    UUID a = UUID.randomUUID();
    user.setUserCode(a.toString());
    userDto.setUserCode(a.toString());
    return userDto;
    }

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(item -> {
            UserDto userDto = new UserDto();
            userDto.setName(item.getName());
            userDto.setSurname(item.getSurname());
            userDto.setEmail(item.getEmail());
            userDto.setJobTitle(item.getJobTitle());
            userDto.setPhone(item.getPhone());
            userDto.setImageUrl(item.getImageUrl());
            userDto.setBirthdate(item.getBirthDate());
            userDto.setUserCode(item.getUserCode().toString());
            userDtos.add(userDto);
        });

        return userDtos;
    }

    public String updateUser(Long id, UserDto userDto){

        try {
            User user = userRepository.getById(id);
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setPhone(userDto.getPhone());
            user.setEmail(userDto.getEmail());
            user.setJobTitle(userDto.getJobTitle());
            user.setImageUrl(userDto.getImageUrl());
            user.setBirthDate(userDto.getBirthdate());
            userRepository.save(user);
            return "User successfully updated";
        }catch (Exception e) {
            e.printStackTrace();
            return "User not found";
        }
    }


    public String deleteUserById(Long id){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found")));
        if (user != null){
            userRepository.deleteById(id);
            return "User successfully deleted";
        }else{
            return "User not found";
        }
    }

    public String deleteAllUser(){
        try {
            userRepository.deleteAll();
            return "Tüm kullanicilar silindi.";
        }catch (Exception e){
            return "Kullanicilari silerken hata alindi.";
        }
    }

    public UserDto findUserById(Long id) {
        try {
            User user = userRepository.getById(id);
            UserDto userDto = new UserDto();

            userDto.setName(user.getName());
            userDto.setSurname(user.getSurname());
            userDto.setPhone(user.getPhone());
            userDto.setEmail(user.getEmail());
            userDto.setBirthdate(user.getBirthDate());
            userDto.setImageUrl(user.getImageUrl());
            userDto.setJobTitle(user.getJobTitle());
            userDto.setUserCode(user.getUserCode());
            return userDto;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
