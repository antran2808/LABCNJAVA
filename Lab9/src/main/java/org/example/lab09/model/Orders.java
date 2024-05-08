package org.example.lab09.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private double totalSellingPrice;

    @JsonIgnore
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Product> productList;



    // Getters and setters

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotalSellingPrice() {
        return totalSellingPrice;
    }

    public void setTotalSellingPrice(double totalSellingPrice) {
        this.totalSellingPrice = totalSellingPrice;
    }


    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
