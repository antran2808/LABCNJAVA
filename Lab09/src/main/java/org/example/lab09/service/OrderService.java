package org.example.lab09.service;

import org.example.lab09.model.Orders;
import org.example.lab09.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Orders addOrder(Orders order) {
        return orderRepository.save(order);
    }

    public Orders updateOrder(Long id, Orders orderDetails) {
        Orders order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            // Update order details
            order.setOrderNumber(orderDetails.getOrderNumber());
            order.setTotalSellingPrice(orderDetails.getTotalSellingPrice());
            order.setProductList(orderDetails.getProductList());
            return orderRepository.save(order);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
