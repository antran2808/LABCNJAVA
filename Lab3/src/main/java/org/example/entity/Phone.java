package org.example.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Generated;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Phone")
public class Phone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false, length = 128)
    public String name;
    @Column(nullable = false)
    public int price;
    @Column(nullable = false)
    public String color;
    @Column
    public int quantity;

    @Setter
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacture_id", nullable = false, referencedColumnName = "id")
    private Manufacture manufacture;

    public Phone(Long id, String name, int price, String color, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.quantity = quantity;
    }
    public Phone(String name, int price, String color, int quantity) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.quantity = quantity;
    }
    public Phone() {

    }
    public String toString(){
        return this.id + "/" + this.name + "/" + this.price + "/" + this.color + "/" + this.quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }
}
