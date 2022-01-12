package com.example.SpringShop.controllers;

import com.example.SpringShop.model.Product;
import com.example.SpringShop.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/products")
@Api(value = "Products")
public class ProductsController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/show-all")
    @ResponseBody
    @ApiOperation(value = "Show all products")
    public List<Product> getProductsList() {
        return productServiceImpl.getAll();
    }

    @GetMapping("/show/{id}")
    @ResponseBody
    @ApiOperation(value = "Show one product bwy id")
    public Product getProduct(@PathVariable("id") long id) {
        return productServiceImpl.getProductById(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create product with name and price")
    public void createProduct(@RequestParam("name") String name,
                              @RequestParam("price") double price) {
        if(productServiceImpl.getProductByName(name) == null) {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            productServiceImpl.addProduct(product);
        }
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Update product by id")
    public void updateProduct(@PathVariable("id") long id,
                               @RequestParam("name") String name,
                               @RequestParam("price") double price) {
        Product product = productServiceImpl.getProductById(id);
        product.setName(name);
        product.setPrice(price);
        productServiceImpl.editProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete product by id")
    public void deleteProduct(@PathVariable("id") long id) {
        Product product = productServiceImpl.getProductById(id);
        productServiceImpl.deleteProduct(product);
    }
}
