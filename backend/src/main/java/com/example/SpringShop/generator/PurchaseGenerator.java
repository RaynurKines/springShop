package com.example.SpringShop.generator;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.model.Product;
import com.example.SpringShop.model.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseGenerator {
    public List<Purchase> generatePurchases(List<Customer> customers, List<Product> products) {
        List<Purchase> purchases = new ArrayList<Purchase>();

        purchases.add(new Purchase(customers.get(2), List.of(products.get(2), products.get(3), products.get(4), products.get(5))));
        purchases.add(new Purchase(customers.get(8), List.of(products.get(10))));
        purchases.add(new Purchase(customers.get(5), List.of(products.get(11))));

        return purchases;
    }
}
