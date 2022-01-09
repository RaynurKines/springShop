package com.example.SpringShop.controllers;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.model.Product;
import com.example.SpringShop.model.Purchase;
import com.example.SpringShop.service.impl.CustomerServiceImpl;
import com.example.SpringShop.service.impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseServiceImpl purchaseServiceImpl;

    @GetMapping("/show-all")
    @ResponseBody
    public List<Purchase> getPurchasesList() {
        return purchaseServiceImpl.getAll();
    }

    @GetMapping("/show")
    @ResponseBody
    public Purchase getPurchase(@RequestParam("id") long id) {
        return purchaseServiceImpl.getPurchaseById(id);
    }

    @PostMapping("/create")
    public void createPurchase(@RequestParam("customer") Customer customer,
                               @RequestParam("products") List<Product> products) {
        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setProducts(products);
        purchaseServiceImpl.addPurchase(purchase);
    }

    @PostMapping("{id}/update")
    public void updatePurchase(@PathVariable("id") long id,
                               @RequestParam("customer") Customer customer,
                               @RequestParam("products") List<Product> products) {
        Purchase purchase = purchaseServiceImpl.getPurchaseById(id);
        purchase.setCustomer(customer);
        purchase.setProducts(products);
        purchaseServiceImpl.editPurchase(purchase);
    }

    @PostMapping("{id}/delete")
    public void deletePurchase(@PathVariable("id") long id) {
        Purchase purchase = purchaseServiceImpl.getPurchaseById(id);
        purchaseServiceImpl.deletePurchase(purchase);
    }
}
