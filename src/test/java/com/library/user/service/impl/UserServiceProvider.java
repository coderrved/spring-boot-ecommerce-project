package com.library.user.service.impl;

import com.library.address.entity.Address;
import com.library.address.enums.AddressTypeEnum;
import com.library.address.mapper.AddressMapper;
import com.library.address.model.AddressRequestDto;
import com.library.address.model.AddressResponseDto;
import com.library.user.entity.User;
import com.library.user.model.UserRequestDto;
import com.library.user.model.UserResponseDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class UserServiceProvider {

    protected static User getUser() {

        final User user = new User();

        user.setName("Vedat");
        user.setId(1L);
        user.setSurname("Bilaloglu");
        user.setEmail("vedatbilaloglu@hotmail.com");
        user.setDateOfBirthday(LocalDate.now());
        user.setPhoneNumber("321123");
        user.setAddresses(getAddresses());

        return user;
    }

    protected static List<User> getUsers() {

        final List<User> users = new ArrayList<>();

        final User firstUser = getUser();

        final User secondUser = getUser();

        users.add(firstUser);
        users.add(secondUser);

        return users;
    }

    protected static UserRequestDto getUserRequestDto() {

        final UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setName("Vedat");
        userRequestDto.setSurname("Bilaloglu");
        userRequestDto.setEmail("vedatbilaloglu@hotmail.com");
        userRequestDto.setPhoneNumber("321123");
        userRequestDto.setDateOfBirthday(LocalDate.now());

        final List<AddressRequestDto> addressRequestDtos = AddressMapper.INSTANCE.convertFromAddressesToAddressRequestDtos(getAddresses());

        userRequestDto.setUserAddresses(addressRequestDtos);

        return userRequestDto;
    }

    protected static List<UserRequestDto> getUserRequestDtos() {

        final List<UserRequestDto> userRequestDtos = new ArrayList<>();

        final UserRequestDto firstUserRequestDto = getUserRequestDto();

        final UserRequestDto secondUserRequestDto = getUserRequestDto();

        userRequestDtos.add(firstUserRequestDto);
        userRequestDtos.add(secondUserRequestDto);

        return userRequestDtos;
    }

    protected static UserResponseDto getUserResponseDto() {

        final UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setName("Vedat");

        userResponseDto.setName("Vedat");
        userResponseDto.setSurname("Bilaloglu");
        userResponseDto.setEmail("vedatbilaloglu@hotmail.com");
        userResponseDto.setPhoneNumber("321123");
        userResponseDto.setDateOfBirthday(LocalDate.now());

        final List<AddressResponseDto> addressResponseDtos = AddressMapper.INSTANCE.convertFromAddressesToAddressResponseDtos(getAddresses());

        userResponseDto.setUserAddresses(addressResponseDtos);

        return userResponseDto;
    }

    protected static List<UserResponseDto> getUserResponseDtos() {

        final List<UserResponseDto> userResponseDtos = new ArrayList<>();

        final UserResponseDto firstUserResponseDto = getUserResponseDto();

        final UserResponseDto secondUserResponseDto = getUserResponseDto();

        userResponseDtos.add(firstUserResponseDto);
        userResponseDtos.add(secondUserResponseDto);

        return userResponseDtos;
    }

    protected static List<Address> getAddresses() {

        final Address address = new Address();

        address.setActive(true);
        address.setAddressExplanation("abc");
        address.setAddressType(AddressTypeEnum.HOME_ADDRESS);

        return Collections.singletonList(address);
    }

}
