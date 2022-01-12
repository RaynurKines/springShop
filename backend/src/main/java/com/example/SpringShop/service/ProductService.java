package com.example.SpringShop.service;

import com.example.SpringShop.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product customer);
    void deleteProductById(long id);
    void deleteProduct(Product customer);
    Product getProductByName(String name);
    Product editProduct(Product customer);
    List<Product> getAll();
    Product getProductById(long id);
    void addProducts(List<Product> products);
}
