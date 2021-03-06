package com.example.SpringShop.service;

import com.example.SpringShop.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);
    void deleteCustomerById(long id);
    void deleteCustomer(Customer customer);
    List<Customer> getAllGroupBySex();
    Customer getCustomerByName(String name);
    Customer editCustomer(Customer customer);
    List<Customer> getAll();
    Customer getCustomerById(long id);
    void addCustomers(List<Customer> customers);
}
