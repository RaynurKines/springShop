package com.example.SpringShop.service;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.model.Purchase;

import java.util.List;

public interface PurchaseService {

    Purchase addPurchase(Purchase purchase);
    void deletePurchaseById(long id);
    void deletePurchase(Purchase purchase);
    List<Purchase> getAll();
    Purchase getPurchaseById(long id);
    void addPurchases(List<Purchase> purchases);
    List<Purchase> getAllByCustomer(long customerId);
}
