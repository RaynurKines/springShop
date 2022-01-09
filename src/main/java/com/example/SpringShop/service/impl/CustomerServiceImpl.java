package com.example.SpringShop.service.impl;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.repository.CustomerRepository;
import com.example.SpringShop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void deleteCustomerById(long id){
        customerRepository.deleteById(id);
    }

    @Override
    public Customer editCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public Customer getCustomerById(long id){
        return customerRepository.findById(id).get();
    }
}
