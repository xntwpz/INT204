package com.example.test2405.repositories;

import com.example.test2405.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product , Integer> {
    // for what ?
    List<Product> findBySupplierId(Integer supplierId);

}
