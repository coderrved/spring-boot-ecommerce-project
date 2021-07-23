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
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
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

    public User(String name, String surname, String phone, String email, String jobTitle, Date birthDate, String imageUrl) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.jobTitle = jobTitle;
        this.birthDate = birthDate;
        this.imageUrl = imageUrl;
    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
