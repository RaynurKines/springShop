package com.example.SpringShop.repository;

import com.example.SpringShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.name = :name")
    Product findByName(@Param("name") String name);
}
