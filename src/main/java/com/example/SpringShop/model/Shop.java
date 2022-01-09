package com.example.SpringShop.model;

import java.util.List;

public class Shop{
    private List<Product> products;
    private List<Customer> customers;
    private List<Purchase> purchases;

    public Shop() {
    }
    public Shop(List<Product> products, List<Customer> customers, List<Purchase> purchases){
        this.products=products;
        this.customers=customers;
        this.purchases=purchases;
    }

    public List<Customer> getCustomers() { return customers; }

    public List<Product> getProducts() {
        return products;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Purchase createPurchase(Customer customer, List<Product> products){
        Purchase purchase = new Purchase(customer, products);
        purchases.add(purchase);

        return purchase;
    }
}
