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

    public UserDto updateUser(Long id, UserDto userDto){

        // Kontrol Edilmeli...

        User user = userRepository.findById(id).orElse(null);
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setBirthDate(userDto.getBirthdate());
        List<Adres> liste = new ArrayList<>();
        userDto.getAdress().forEach(item -> {
            Adres adres = new Adres();
            adres.setAddress(item);
            adres.setActive(true);
            adres.setAddressType(Adres.AddressType.OTHER);
            liste.add(adres);
        });
        user.setAdress(liste);
        userRepository.save(user);
        return userDto;

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

}
