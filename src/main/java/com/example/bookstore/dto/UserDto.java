package com.example.bookstore.dto;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class UserDto {

    private Long id;

    @NotNull
    private String name;

    private String surname;

    private String phone;

    private String email;

    private String jobTitle;

    private Date birthdate;

    private String imageUrl;

    private String userCode;

}
