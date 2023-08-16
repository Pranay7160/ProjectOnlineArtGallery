package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Orders;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.OrdersRepository;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository; // Assuming you have a repository for Orders

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();
        return ResponseEntity.ok(ordersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrdersById(@PathVariable Long id) throws ResourceNotFoundException {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orders not found with id: " + id));
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) {
        Orders createdOrders = ordersRepository.save(orders);
        return ResponseEntity.created(null).body(createdOrders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrders(@PathVariable Long id, @RequestBody Orders orders) throws ResourceNotFoundException {
        Orders existingOrders = ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orders not found with id: " + id));

        // Update fields of the existingOrders with values from orders

        Orders updatedOrders = ordersRepository.save(existingOrders);
        return ResponseEntity.ok(updatedOrders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrders(@PathVariable Long id) throws ResourceNotFoundException {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orders not found with id: " + id));

        ordersRepository.delete(orders);
        return ResponseEntity.noContent().build();
    }
}
