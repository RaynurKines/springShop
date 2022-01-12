package com.example.SpringShop.repository;

import com.example.SpringShop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c where c.name = :name")
    Customer findByName(@Param("name") String name);
    @Query("select c from Customer c group by c.sex, c.id")
    List<Customer> findAllGroupBySex();
}
