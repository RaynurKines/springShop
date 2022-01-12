package com.example.SpringShop.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@ToString
@EqualsAndHashCode
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "purchase_product",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Singular
    private List<Product> products;

    private double price = 0;

    public Purchase() {
    }

    public Purchase(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;

        this.price = products.stream().map(Product::getPrice).mapToDouble(Double::doubleValue).sum();

        try {
            customer.setMoney(customer.getMoney() - price);
        }catch (Exception e) {
            System.out.println("NOT ENOUGH MANA(money)!!!" + e.getMessage());
            return;
        }
    }
}