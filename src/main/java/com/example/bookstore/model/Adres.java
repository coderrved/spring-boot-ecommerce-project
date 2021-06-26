package com.example.bookstore.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Adres implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_user_address", allocationSize = 1)
    @GeneratedValue(generator = "seq_user_address", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 500, name = "address")
    private String address;

    @Enumerated
    private AddressType addressType;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "kisi_adres_id")
    private User user;

    public enum AddressType {
        HOME_ADDRESS,
        WORK_ADDRESS,
        OTHER
    }
}
