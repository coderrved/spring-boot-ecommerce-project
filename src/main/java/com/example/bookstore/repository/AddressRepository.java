package com.example.bookstore.repository;

import com.example.bookstore.model.Adres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Adres, Long> {
}
