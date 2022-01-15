package com.example.SpringShop.service.impl;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.model.Purchase;
import com.example.SpringShop.repository.PurchaseRepository;
import com.example.SpringShop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase addPurchase(Purchase purchase) {
        Purchase savedPurchase = purchaseRepository.saveAndFlush(purchase);
        return savedPurchase;
    }

    @Override
    public void deletePurchase(Purchase purchase) {
        purchaseRepository.delete(purchase);
    }

    @Override
    public void deletePurchaseById(long id) {
        purchaseRepository.deleteById(id);
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase getPurchaseById(long id) {
        return purchaseRepository.findById(id).get();
    }

    @Override
    public void addPurchases(List<Purchase> purchases){
        purchaseRepository.saveAll(purchases);
    }

    @Override
    public List<Purchase> getAllByCustomer(Customer customer) {
        return purchaseRepository.findAllByCustomer(customer);
    }
}
