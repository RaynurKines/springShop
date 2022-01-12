package com.example.SpringShop.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "money")
    private double money;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    private List<Purchase> purchases;

    public Customer() {
    }

    public Customer(String name, String sex, double money) {
        this.name = name;
        this.sex = sex;
        this.money = money;
    }

    public void setMoney(double money){
        if(money >= 0)
            this.money = money;
        else
            throw new IllegalArgumentException("Твой кошелек пробил дыру!");
    }
}
