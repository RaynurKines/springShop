package com.example.SpringShop.generator;

import com.example.SpringShop.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomersGenerator {
    public List<Customer> generateCustomers(){
        List<Customer> customers = new ArrayList<Customer>();

        customers.add(new Customer("Титов Александр Вадимович", "муж", 3000));
        customers.add(new Customer("Кинеспаев Райнур Манарбекович", "муж", 6000));
        customers.add(new Customer("Федоренко Анастасия Петровна", "жен", 1000));
        customers.add(new Customer("Жигалов Сергей Алексеевич", "муж", 2500));
        customers.add(new Customer("Каренина Анна Николаевна", "жен", 5000));
        customers.add(new Customer("Раскольников Радион Васильевич", "муж", 900));
        customers.add(new Customer("Утренняя Звезда Люцифер", "муж", 100));
        customers.add(new Customer("Сноу Джон Эддардович", "муж", 300));
        customers.add(new Customer("Валар Мелькор Илуватарович", "муж", 50000));
        customers.add(new Customer("Цирилла Фиона Элен Рианнон", "жен", 10000));

        return customers;
    }
}
