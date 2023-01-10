package com.example.service;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.PaymentMethod;
import com.example.models.PaymentModel;
import com.example.models.PaymentStatus;
import com.example.models.PaymentStatusUpdateBody;
import com.example.repositories.PaymentRepository;
import com.example.utils.BadRequestException;
import com.example.utils.NotFoundException;

@Service
public class PaymentService {
  private final PaymentRepository paymentRepository;

  @Autowired
  public PaymentService(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  public PaymentModel createPayment(PaymentModel payment) {
    payment.setStatus(PaymentStatus.PENDING);

    PaymentMethod[] cardPaymentMethods = { PaymentMethod.cartao_credito, PaymentMethod.cartao_debito };

    if (List.of(cardPaymentMethods).contains(payment.getMethod()) && payment.getCardNumber() == null) {
      throw new BadRequestException("Card payments need card number");
    }

    return paymentRepository.save(payment);
  }

  public PaymentModel updateStatus(Long id, PaymentStatusUpdateBody newStatus) {
    PaymentModel payment = paymentRepository.findById(id).orElse(null);

    if (payment == null) {
      throw new NotFoundException("Payment not found");
    }

    // TODO: better handling
    // TODO: pass error message to controller

    if (payment.getStatus().equals(PaymentStatus.PROCESSED_SUCCESS)) {
      throw new BadRequestException("Cant change successful payment");
    }

    if (payment.getStatus().equals(PaymentStatus.PROCESSED_ERROR) && !newStatus.equals(PaymentStatus.PENDING)) {
      throw new BadRequestException("Failed processing can only change to pending");
    }

    if (payment.getStatus().compareTo(newStatus.getStatus()) == 0) {
      throw new BadRequestException("Unchanged status");
    }

    payment.setStatus(newStatus.getStatus());

    return paymentRepository.save(payment);
  }

  public List<PaymentModel> getAllPayments() {
    // TODO: handle filters
    return paymentRepository.findAll();
  }

  public void deletePayment(Long id) {
    PaymentModel payment = paymentRepository.findById(id).orElse(null);

    if (payment == null) {
      throw new NotFoundException("Payment not found");
    }

    if (payment.getStatus() == PaymentStatus.PENDING) {
      paymentRepository.deleteById(id);
    } else {
      throw new BadRequestException("Cannot delete payment that is not pending");
    }
  }
}
