package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.PaymentModel;

public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {
}
