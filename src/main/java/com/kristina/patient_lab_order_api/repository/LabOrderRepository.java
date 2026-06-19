package com.kristina.patient_lab_order_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kristina.patient_lab_order_api.model.LabOrder;

public interface LabOrderRepository extends JpaRepository<LabOrder, Long> {
}