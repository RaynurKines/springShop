package com.example.SpringShop.util;

import com.example.SpringShop.generator.CustomersGenerator;
import com.example.SpringShop.generator.ProductsGenerator;
import com.example.SpringShop.generator.PurchasesGenerator;
import com.example.SpringShop.model.Customer;
import com.example.SpringShop.model.Product;
import com.example.SpringShop.model.Purchase;
import com.example.SpringShop.service.impl.CustomerServiceImpl;
import com.example.SpringShop.service.impl.ProductServiceImpl;
import com.example.SpringShop.service.impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitiateUtils implements CommandLineRunner {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private PurchaseServiceImpl purchaseServiceImpl;

    @Override
    public void run(String... args) throws Exception {
        CustomersGenerator customersGenerator = new CustomersGenerator();
        ProductsGenerator productsGenerator = new ProductsGenerator();
        PurchasesGenerator purchasesGenerator = new PurchasesGenerator();

        List<Customer> customers = customersGenerator.generateCustomers();
        List<Product> products = productsGenerator.generateProducts();
        List<Purchase> purchases = purchasesGenerator.generatePurchases(customers, products);

        customerServiceImpl.addCustomers(customers);
        productServiceImpl.addProducts(products);
        purchaseServiceImpl.addPurchases(purchases);
    }
}
