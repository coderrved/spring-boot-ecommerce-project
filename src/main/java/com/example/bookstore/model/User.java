package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity()
@Table(name = "user_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 100, name = "name")
    private String name;

    @Column(length = 100, name = "surname")
    private String surname;

    @Column(length = 100, name = "phone")
    private String phone;

    @Column(length = 100, name = "email")
    private String email;

    @Column(length = 100, name = "job_title")
    private String jobTitle;

    @Column(length = 100, name = "birthdate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

    @Column(length = 100, name = "image_url")
    private String imageUrl;

    @Column(length = 100, name = "user_code")
    private String userCode;
}
