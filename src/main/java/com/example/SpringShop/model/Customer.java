package com.example.SpringShop.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "money")
    private double money;

    @Column(name = "regular")
    private boolean regular = false;

    @Column(name = "score")
    public int score = 0;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    private List<Purchase> purchases;

    public Customer() {
    }

    public Customer(String name, String sex, double money) {
        this.name = name;
        this.sex = sex;
        this.money = money;
    }
}
