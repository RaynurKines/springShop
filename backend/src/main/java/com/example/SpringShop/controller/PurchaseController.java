package com.example.SpringShop.controller;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.model.Product;
import com.example.SpringShop.model.Purchase;
import com.example.SpringShop.service.impl.CustomerServiceImpl;
import com.example.SpringShop.service.impl.ProductServiceImpl;
import com.example.SpringShop.service.impl.PurchaseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/purchases")
@Api(value = "Purchases")
public class PurchaseController {
    @Autowired
    private PurchaseServiceImpl purchaseServiceImpl;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/show-all")
    @ResponseBody
    @ApiOperation(value = "Show all purchases")
    public List<Purchase> getPurchasesList() {
        return purchaseServiceImpl.getAll();
    }

    @GetMapping("/show/{id}")
    @ResponseBody
    @ApiOperation(value = "Show purchase by id")
    public Purchase getPurchase(@PathVariable("id") long id) {
        return purchaseServiceImpl.getPurchaseById(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create purchase")
    public void createPurchase(@RequestParam("customer_id") long customerId,
                               @RequestParam("products_id") String stringProductsId) {
        Customer customer = customerServiceImpl.getCustomerById(customerId);
        List<Product> products = new ArrayList<>();
        List<String> splitedStringProductsId = Arrays.asList(stringProductsId.split(","));
        splitedStringProductsId.stream().mapToLong(Long::parseLong).forEach(
                (productId -> {
            products.add(productServiceImpl.getProductById(productId));
        }));
        Purchase purchase = new Purchase(customer, products);

        purchaseServiceImpl.addPurchase(purchase);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete purchase by id")
    public void deletePurchase(@PathVariable("id") long id) {
        Purchase purchase = purchaseServiceImpl.getPurchaseById(id);
        purchaseServiceImpl.deletePurchase(purchase);
    }

    @GetMapping("/{customer_id}/get-purchases")
    @ApiOperation(value ="Get purchases by customer_id")
    public List<Purchase> getPurchasesByCustomerId(@PathVariable("customer_id") long customerId) {
        Customer customer = customerServiceImpl.getCustomerById(customerId);
        return purchaseServiceImpl.getAllByCustomer(customer);
    }
}
