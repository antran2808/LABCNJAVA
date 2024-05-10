package org.example;

import org.springframework.stereotype.Component;

import java.util.PrimitiveIterator;

@Component
//@Named("myClass")
public class Product {
    private int id;
    private String name;
    private int price;
    private String description;

    public Product(int id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public Product(Product p){
        this.name = p.getName();
    }
    public Product(String name) {
        this.name = name;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
