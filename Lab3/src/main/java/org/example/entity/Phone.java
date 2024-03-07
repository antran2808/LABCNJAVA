package org.example;

import javax.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "MobilePhone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "country")
    private String country;

    @Column(name = "quantity")
    private int quantity;

    @Setter
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacture_id", nullable = false, referencedColumnName = "id")
    private Manufacture manufacture;

    public Phone() {
    }

    public Phone(String name, double price, String color){
        this.name = name;
        this.price = price;
        this.color = color;
    }

    public Phone(String name, double price, String color, String country, int quantity) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Manufacture getManufacture() { return manufacture; }

    public void setManufacture(Manufacture manufacture) {this.manufacture = manufacture;}
}
