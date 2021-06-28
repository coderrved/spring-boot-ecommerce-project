package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity()
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class User implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_user_table", allocationSize = 1)
    @GeneratedValue(generator = "seq_user_table", strategy = GenerationType.SEQUENCE)
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

    @OneToMany
    @JoinColumn(name = "kisi_adres_id")
    private List<Adres> adress;

    @Column(length = 100, name = "birthdate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

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

    public List<Adres> getAdress() {
        return adress;
    }

    public void setAdress(List<Adres> adress) {
        this.adress = adress;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
