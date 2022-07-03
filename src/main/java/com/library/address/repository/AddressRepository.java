package com.library.address.repository;

import com.library.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE user_id = ?1")
    List<Address> findAddressesByUserId(Long id);

}
