package com.example.bookstore.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity()
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @DateTimeFormat(pattern = "hh:mm")
    private Date birthDate;


}
