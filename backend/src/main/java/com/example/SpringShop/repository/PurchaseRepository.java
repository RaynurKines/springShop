package com.example.SpringShop.repository;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("select p from Purchase p where p.customer_id = :customer_id")
    List<Purchase> findAllByCustomer(@Param("customer_id") long id);
}
