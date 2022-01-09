package com.example.SpringShop.controllers;

import com.example.SpringShop.model.Customer;
import com.example.SpringShop.model.Product;
import com.example.SpringShop.service.impl.CustomerServiceImpl;
import com.example.SpringShop.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/show-all")
    @ResponseBody
    public List<Product> getProductsList() {
        return productServiceImpl.getAll();
    }

    @GetMapping("/show")
    @ResponseBody
    public Product getProduct(@RequestParam("id") long id) {
        return productServiceImpl.getProductById(id);
    }

    @PostMapping("/create")
    public void createProduct(@RequestParam("name") String name,
                              @RequestParam("price") double price) {
        if(productServiceImpl.getProductByName(name) == null) {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            productServiceImpl.addProduct(product);
        }
    }

    @PostMapping("{id}/update")
    public void updateProduct(@PathVariable("id") int id,
                               @RequestParam("name") String name,
                               @RequestParam("price") double price) {
        Product product = productServiceImpl.getProductById(id);
        product.setName(name);
        product.setPrice(price);
        productServiceImpl.editProduct(product);
    }

    @PostMapping("{id}/delete")
    public void deleteProduct(@PathVariable("id") long id) {
        Product product = productServiceImpl.getProductById(id);
        productServiceImpl.deleteProduct(product);
    }
}
