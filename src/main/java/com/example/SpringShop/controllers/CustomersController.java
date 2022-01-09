package com.example.SpringShop.controllers;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.model.Product;
import com.example.SpringShop.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomersController {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping("/show-all")
    @ResponseBody
    public List<Customer> getCustomersList() {
        return customerServiceImpl.getAll();
    }

    @GetMapping("/show")
    @ResponseBody
    public Customer getCustomer(@RequestParam("id") long id) {
        return customerServiceImpl.getCustomerById(id);
    }

    @PostMapping("/create")
    public void createCustomer(@RequestParam("name") String name,
                               @RequestParam("sex") String sex,
                               @RequestParam("money") double money) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSex(sex);
        customer.setMoney(money);
        customerServiceImpl.addCustomer(customer);
    }

    @PostMapping("{id}/update")
    public void updateCustomer(@PathVariable("id") long id,
                               @RequestParam("name") String name,
                               @RequestParam("sex") String sex,
                               @RequestParam("money") double money) {
        Customer customer = customerServiceImpl.getCustomerById(id);
        customer.setName(name);
        customer.setSex(sex);
        customer.setMoney(money);
        customerServiceImpl.editCustomer(customer);
    }

    @PostMapping("{id}/delete")
    public void deleteCustomer(@PathVariable("id") long id) {
        Customer customer = customerServiceImpl.getCustomerById(id);
        customerServiceImpl.deleteCustomer(customer);
    }
}
