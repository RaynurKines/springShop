package com.example.SpringShop.generator;

import com.example.SpringShop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsGenerator {
    public List<Product> generateProducts() {
        List<Product> products = new ArrayList<Product>();

        products.add(new Product("Хлеб", 25));
        products.add(new Product("Сыр", 80));
        products.add(new Product("Масло", 10));
        products.add(new Product("Мороженное", 15));
        products.add(new Product("Молоко", 25));
        products.add(new Product("Арахисовая паста", 40));
        products.add(new Product("Замороженные пельмени", 60));
        products.add(new Product("Консервированная кукуруза", 40));
        products.add(new Product("Крабовые палочки", 35));
        products.add(new Product("Черная икра", 200));
        products.add(new Product("Сильмариль", 50000));
        products.add(new Product("Топор", 500));

        return products;
    }
}
