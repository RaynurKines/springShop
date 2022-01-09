package com.example.SpringShop.controllers;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping("/show")
    @ResponseBody
    public List<Customer> getCustomerList() {
        return customerServiceImpl.getAll();
    }

    @PostMapping("/create")
    public void createCustomer(String customerName, String customerSex, double customerMoney) {
        Customer customer = new Customer(customerName, customerSex, customerMoney);
        customerServiceImpl.addCustomer(customer);
    }

}
