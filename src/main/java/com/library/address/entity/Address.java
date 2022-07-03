package com.library.address.entity;

import com.library.address.enums.AddressTypeEnum;
import com.library.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String addressExplanation;

    @Enumerated(EnumType.STRING)
    private AddressTypeEnum addressType;

    private Boolean active;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;
}
