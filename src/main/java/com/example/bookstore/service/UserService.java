package com.example.bookstore.service;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
    userDto.setId(user.getId());
    return userDto;
    }

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(item -> {
            UserDto userDto = new UserDto();
            userDto.setId(item.getId());
            userDto.setName(item.getName());
            userDto.setSurname(item.getSurname());
            userDto.setEmail(item.getEmail());
            userDto.setJobTitle(item.getJobTitle());
            userDto.setPhone(item.getPhone());
            userDto.setImageUrl(item.getImageUrl());
            userDto.setBirthdate(item.getBirthDate());
            userDto.setUserCode(item.getUserCode());
            userDtos.add(userDto);
        });

        return userDtos;
    }

    public void updateUser(UserDto userDto){

        try {
            User user = new User();
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setPhone(userDto.getPhone());
            user.setEmail(userDto.getEmail());
            user.setJobTitle(userDto.getJobTitle());
            user.setImageUrl(userDto.getImageUrl());
            user.setBirthDate(userDto.getBirthdate());
            user.setUserCode(userDto.getUserCode());
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void deleteAllUser() {
        try {
            userRepository.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserDto findUserById(Long id) {
        try {
            User user = userRepository.getById(id);
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
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
