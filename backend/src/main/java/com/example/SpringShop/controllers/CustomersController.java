package com.example.SpringShop.controllers;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.service.impl.CustomerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/customers")
@Api(value = "Customers")
public class CustomersController {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping("/show-all")
    @ResponseBody
    @ApiOperation(value ="Show all customers")
    public List<Customer> getCustomersList() {
        return customerServiceImpl.getAll();
    }

    @GetMapping("/show/{id}")
    @ResponseBody
    @ApiOperation(value ="Show customer by id")
    public Customer getCustomer(@PathVariable("id") long id) {
        return customerServiceImpl.getCustomerById(id);
    }

    @PostMapping("/create")
    @ApiOperation(value ="Create customer")
    public void createCustomer(@RequestParam("name") String name,
                               @RequestParam("sex") String sex,
                               @RequestParam("money") double money) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSex(sex);
        customer.setMoney(money);
        customerServiceImpl.addCustomer(customer);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value ="Update customer by id")
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

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value ="Delete customer by id")
    public void deleteCustomer(@PathVariable("id") long id) {
        Customer customer = customerServiceImpl.getCustomerById(id);
        customerServiceImpl.deleteCustomer(customer);
    }
}
