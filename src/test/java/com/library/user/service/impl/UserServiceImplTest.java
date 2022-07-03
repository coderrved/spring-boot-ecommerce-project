package com.library.user.service.impl;

import com.library.address.entity.Address;
import com.library.address.exceptions.AddressNotFound;
import com.library.address.repository.AddressRepository;
import com.library.user.entity.User;
import com.library.user.exceptions.UserNotFound;
import com.library.user.model.UserRequestDto;
import com.library.user.model.UserResponseDto;
import com.library.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        userServiceImpl = new UserServiceImpl(userRepository, addressRepository);
    }

    @Test
    void shouldFindUserById() {

        // Given

        final UserResponseDto userResponseDto = UserServiceProvider.getUserResponseDto();

        final User user = UserServiceProvider.getUser();


        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        final List<Address> addresses = UserServiceProvider.getAddresses();

        when(addressRepository.findAddressesByUserId(1L)).thenReturn(addresses);

        // When


        final UserResponseDto userById = userServiceImpl.findUserById(1L);

        //Then

        Assertions.assertEquals(userResponseDto, userById);
    }

    @Test
    void shouldNotFindUserByIdWhenUserNotExist() {

        when(userRepository.findById(2L)).thenThrow(new UserNotFound("User not found"));

        Assertions.assertThrows(UserNotFound.class, () -> userServiceImpl.findUserById(2L));
    }

    @Test
    void shouldFindAllUsers() {

        // Given

        final List<UserResponseDto> userResponseDtos = UserServiceProvider.getUserResponseDtos();

        final List<User> users = UserServiceProvider.getUsers();

        // When

        when(userRepository.findAll()).thenReturn(users);

        final List<UserResponseDto> allUsers = userServiceImpl.findAllUsers();

        //Then

        Assertions.assertEquals(allUsers, userResponseDtos);

    }

    @Test
    void shouldSaveUser() {

        final UserRequestDto userRequestDto = UserServiceProvider.getUserRequestDto();

        userServiceImpl.saveUser(userRequestDto);

    }

    @Test
    void shouldUpdateUserById() {

        // Given

        final UserRequestDto userRequestDto = UserServiceProvider.getUserRequestDto();

        final UserResponseDto userResponseDto = UserServiceProvider.getUserResponseDto();

        final User user = UserServiceProvider.getUser();

        // When

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        when(userRepository.save(user)).thenReturn(user);

        final UserResponseDto updateUserById = userServiceImpl.updateUserById(1L, userRequestDto);

        // Then

        Assertions.assertEquals(userResponseDto, updateUserById);
    }

    @Test
    void shouldNotUpdateUserByIdWhenUserNotExist() {

        // Given

        final UserRequestDto userRequestDto = UserServiceProvider.getUserRequestDto();

        final User user = UserServiceProvider.getUser();

        // When

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        when(userRepository.save(user)).thenReturn(user);

        // Then

        Assertions.assertThrows(UserNotFound.class, () -> userServiceImpl.updateUserById(2L, userRequestDto));
    }

    @Test
    void shouldDeleteUserById() {

        // Given

        final User user = UserServiceProvider.getUser();

        // When

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        userServiceImpl.deleteUserById(user.getId());

        // Then

        Mockito.verify(userRepository).delete(user);
    }

    @Test
    void deleteAllUser() {

        // Given

        final List<User> allUser = UserServiceProvider.getUsers();

        // When

        when(userRepository.findAll()).thenReturn(allUser);

        // Then

        userServiceImpl.deleteAllUser();

        Mockito.verify(userRepository).deleteAll();

    }

    @Test
    void shouldNotFindAddressesByUserIDWhenAddressNotExistOfUser() {

        // Given

        final User user = UserServiceProvider.getUser();

        user.setAddresses(null);

        // When

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Then

        Assertions.assertThrows(AddressNotFound.class, () -> userServiceImpl.findUserById(1L));
    }
}