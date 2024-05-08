package org.example.lab09.controller;
import org.example.lab09.model.Orders;
import org.example.lab09.model.Product;
import org.example.lab09.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Orders addOrder(@RequestBody Orders order) {
        for (Product product: order.getProductList()){
            product.setOrder(order);
        }
        return orderService.addOrder(order);
    }

    @PutMapping("/{id}")
    public Orders updateOrder(@PathVariable Long id, @RequestBody Orders orderDetails) {
        return orderService.updateOrder(id, orderDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
