package com.kristina.patient_lab_order_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kristina.patient_lab_order_api.model.LabOrder;
import com.kristina.patient_lab_order_api.service.LabOrderService;

@RestController
@RequestMapping("/orders")
public class LabOrderController {

    private final LabOrderService service;

    public LabOrderController(LabOrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<LabOrder> getAllOrders() {
        return service.getAllOrders();
    }

    @PostMapping
    public LabOrder createOrder(@RequestBody LabOrder order) {
        return service.saveOrder(order);
    }

    @PutMapping("/{id}")
    public LabOrder updateOrder(
            @PathVariable Long id,
            @RequestBody LabOrder updatedOrder) {

        return service.updateOrder(id, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
    }
}