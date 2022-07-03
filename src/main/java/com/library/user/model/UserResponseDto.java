package com.library.user.model;

import com.library.address.model.AddressResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String name;

    private String surname;

    private LocalDate dateOfBirthday;

    private String email;

    private String phoneNumber;

    private List<AddressResponseDto> userAddresses;
}
