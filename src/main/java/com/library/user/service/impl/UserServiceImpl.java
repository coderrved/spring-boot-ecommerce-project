package com.library.user.service.impl;

import com.library.address.entity.Address;
import com.library.address.exceptions.AddressNotFound;
import com.library.address.mapper.AddressMapper;
import com.library.address.model.AddressResponseDto;
import com.library.address.repository.AddressRepository;
import com.library.user.entity.User;
import com.library.user.exceptions.UserNotFound;
import com.library.user.mapper.UserMapper;
import com.library.user.model.UserRequestDto;
import com.library.user.model.UserResponseDto;
import com.library.user.repository.UserRepository;
import com.library.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public UserResponseDto findUserById(Long id) {

        final User user = findUser(id);

        final UserResponseDto userResponseDto = UserMapper.INSTANCE.convertToUserResponseDto(user);

        final List<Address> addressesByUserID = findAddressesByUserID(id);

        final List<AddressResponseDto> addressesOfUser = AddressMapper.INSTANCE.convertFromAddressesToAddressResponseDtos(addressesByUserID);

        userResponseDto.setUserAddresses(addressesOfUser);

        logger.info("User : {} ", userResponseDto);

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> findAllUsers() {

        final List<User> users = userRepository.findAll();

        return getAllUsers(users);
    }

    @Transactional
    @Override
    public void saveUser(UserRequestDto userRequestDto) {

        final User user = populateAddressesOfUserById(userRequestDto);

        userRepository.save(user);

        logger.info("Created User : {} ", user);
    }

    @Override
    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {

        final User updatedUser = populateAddressesOfUserById(userRequestDto);

        final User user = findUser(id);

        updatedUser.setId(user.getId());

        userRepository.save(updatedUser);

        final UserResponseDto userResponseDto = UserMapper.INSTANCE.convertToUserResponseDto(userRequestDto);

        logger.info("Updated User : {} ", userResponseDto);

        return userResponseDto;
    }

    @Override
    public void deleteUserById(Long id) {

        final User user = findUser(id);

        userRepository.delete(user);

        logger.info("Deleted User : {} ", user);

    }

    @Override
    public void deleteAllUser() {

        userRepository.deleteAll();

        logger.info("Deleted All User");
    }

    private List<UserResponseDto> getAllUsers(List<User> users){

        final List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (User user : users) {

            final List<AddressResponseDto> addressResponseDtos = AddressMapper.INSTANCE.convertFromAddressesToAddressResponseDtos(user.getAddresses());

            UserResponseDto userResponseDto = UserMapper.INSTANCE.convertFromUserToUserResponseDto(user, addressResponseDtos);

            userResponseDtos.add(userResponseDto);
        }

        logger.info("All Users : {} ", userResponseDtos);

        return userResponseDtos;
    }

    private User populateAddressesOfUserById(UserRequestDto userRequestDto){

        final User user = UserMapper.INSTANCE.convertToUser(userRequestDto);

        final List<Address> addresses = AddressMapper.INSTANCE.convertFromAddressRequestDtosToAddresses(userRequestDto.getUserAddresses());

        addresses.forEach(address -> address.setUsers(user));

        user.setAddresses(addresses);

        return user;
    }

    private User findUser(Long id) {

        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFound("User with id :" + id + " could not be found."));
    }

    private List<Address> findAddressesByUserID(Long id){

        final List<Address> addressesByUserId = addressRepository.findAddressesByUserId(id);

        if (addressesByUserId.isEmpty()){
            throw new AddressNotFound("The address of the user with ID number " + id + " could not be found.");
        }

        return addressesByUserId;
    }

}
