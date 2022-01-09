package com.example.SpringShop.service;

import com.example.SpringShop.model.Purchase;

import java.util.List;

public interface PurchaseService {

    Purchase addPurchase(Purchase purchase);
    void deletePurchaseById(long id);
    void deletePurchase(Purchase purchase);
    Purchase editPurchase(Purchase purchase);
    List<Purchase> getAll();
    Purchase getPurchaseById(long id);
}
