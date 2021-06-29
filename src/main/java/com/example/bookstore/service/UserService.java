package com.example.bookstore.service;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.exception.UserNotFoundException;
import com.example.bookstore.model.Adres;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.AddressRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public UserDto save(UserDto userDto) {

    User user = new User(); // User nesnesi olusturup Dto'dan gelen verileri işledim.
    user.setName(userDto.getName());
    user.setSurname(userDto.getSurname());
    user.setEmail(userDto.getEmail());
    user.setPhone(userDto.getPhone());
    user.setBirthDate(userDto.getBirthdate());
    User userDb = userRepository.save(user); // DB'ye ekledim

    List<Adres> liste = new ArrayList<>();
    userDto.getAdress().forEach(item -> {
        Adres adres = new Adres();
        adres.setAddress(item);
        adres.setActive(true);
        adres.setAddressType(Adres.AddressType.OTHER);
        liste.add(adres);
    });
    addressRepository.saveAll(liste);
    return userDto;
    }

    public List<UserDto> getAll(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(item -> {
            UserDto userDto = new UserDto();
            userDto.setName(item.getName());
            userDto.setSurname(item.getSurname());
            userDto.setEmail(item.getEmail());
            userDto.setPhone(item.getPhone());
            userDto.setBirthdate(item.getBirthDate());
            userDto.setAdress(
                    item.getAdress() != null ?
                            item.getAdress().
                                    stream().
                                    map(Adres::getAddress).
                                    collect(Collectors.toList())
                            : null);
            userDtos.add(userDto);
        });

        return userDtos;
    }

    public String updateUser(Long id, UserDto userDto){

        try {
            User user1 = userRepository.getById(id);
            user1.setName(userDto.getName());
            user1.setSurname(userDto.getSurname());
            user1.setPhone(userDto.getPhone());
            user1.setEmail(userDto.getEmail());
            List<Adres> liste = new ArrayList<>();
            userDto.getAdress().forEach(item -> {
                Adres adres = new Adres();
                adres.setAddress(item);
                adres.setActive(true);
                adres.setAddressType(Adres.AddressType.OTHER);
                liste.add(adres);
            });
            user1.setAdress(liste);
            userRepository.save(user1);
            return "User successfully updated";
        }catch (Exception e) {
            e.printStackTrace();
            return "User not found";
        }
    }


    public String deleteUser(Long id){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found")));
        if (user != null){
            userRepository.deleteById(id);
            return "User successfully deleted";
        }else{
            return "User not found";
        }
    }

    public UserDto getUser(Long id) {
        try {
            User user1 = userRepository.getById(id);
            UserDto userDto = new UserDto();

            userDto.setName(user1.getName());
            userDto.setSurname(user1.getSurname());
            userDto.setPhone(user1.getPhone());
            userDto.setEmail(user1.getEmail());
            userDto.setBirthdate(user1.getBirthDate());
            userDto.setAdress(
                    user1.getAdress() != null ?
                            user1.getAdress().
                                    stream().
                                    map(Adres::getAddress).
                                    collect(Collectors.toList())
                            : null);
            return userDto;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
