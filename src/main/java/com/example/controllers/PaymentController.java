package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.PaymentModel;
import com.example.models.PaymentStatus;
import com.example.service.PaymentService;

@RestController
@RequestMapping("/pagamento")
public class PaymentController {
  private final PaymentService paymentService;

  @Autowired
  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping
  public PaymentModel createPayment(@RequestBody PaymentModel payment) {
    return paymentService.createPayment(payment);
  }

  @GetMapping
  public List<PaymentModel> getAllPayments() {
    return paymentService.getAllPayments();
  }

  @PutMapping("/{id}")
  public PaymentModel updateStatus(@PathVariable Long id, @RequestBody PaymentStatus newStatus) {
    // TODO: send better error message if invalid enum value is sent
    return paymentService.updateStatus(id, newStatus);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePayment(@PathVariable Long id) {
    paymentService.deletePayment(id);

    return new ResponseEntity<>("Payment deleted", HttpStatus.OK);
  }
}
