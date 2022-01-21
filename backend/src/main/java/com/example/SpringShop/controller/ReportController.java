package com.example.SpringShop.controller;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.model.Purchase;
import com.example.SpringShop.service.impl.CustomerServiceImpl;
import com.example.SpringShop.service.impl.ProductServiceImpl;
import com.example.SpringShop.service.impl.PurchaseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/report")
@Api(value = "Report")
public class ReportController {
    @Autowired
    private PurchaseServiceImpl purchaseServiceImpl;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/spended-money")
    @ResponseBody
    @ApiOperation(value = "Show all spended money")
    public double getSumSpendedMoney(){
        List<Purchase> purchases = purchaseServiceImpl.getAll();
        return purchases.stream().map(Purchase::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @GetMapping("/remaining-money")
    @ResponseBody
    @ApiOperation(value = "Show all remaining money")
    public double getSumRemainingMoney(){
        List<Customer> customers = customerServiceImpl.getAll();
        return customers.stream().map(Customer::getMoney)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @GetMapping("/must-spending-customer")
    @ResponseBody
    @ApiOperation(value = "Show must spending customer")
    public Customer getMustSpendingCustomer(){
        List<Customer> customers = customerServiceImpl.getAll();
        double mostSpendedMoney = customers.stream()
                .map(customer -> customer
                        .getPurchases().stream()
                        .map(Purchase::getPrice)
                        .mapToDouble(Double::doubleValue)
                        .sum())
                .mapToDouble(Double::doubleValue)
                .max()
                .getAsDouble();
        return customers.stream()
                .filter(customer -> customer.getPurchases().stream()
                        .map(Purchase::getPrice)
                        .mapToDouble(Double::doubleValue)
                        .sum() == mostSpendedMoney)
                .findFirst()
                .get();
    }

    @GetMapping("/must-spending-customer-spent-money")
    @ResponseBody
    @ApiOperation(value = "Show must spending customer's spent money")
    public double getMustSpendingCustomersSpentMoney(){
        List<Customer> customers = customerServiceImpl.getAll();
        return customers.stream()
                .map(customer -> customer
                        .getPurchases().stream()
                        .map(Purchase::getPrice)
                        .mapToDouble(Double::doubleValue)
                        .sum())
                .mapToDouble(Double::doubleValue)
                .max()
                .getAsDouble();
    }

    @GetMapping("/show-grouped-customers")
    @ResponseBody
    @ApiOperation(value ="Show all customers grouped by sex")
    public List<Customer> getCustomersList() {
        return customerServiceImpl.getAllGroupBySex();
    }
}
