package com.example.orderservice.service.impl;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order createOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        return repository.findById(id).map(existing -> {
            existing.setCustomerName(order.getCustomerName());
            existing.setItems(order.getItems());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}