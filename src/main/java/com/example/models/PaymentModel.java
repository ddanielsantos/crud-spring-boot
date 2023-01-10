package com.example.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PaymentModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String CPFCNPJ;
  private int amountInCents;
  private PaymentMethod method;
  private PaymentStatus status;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String cardNumber;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCPFCNPJ() {
    return CPFCNPJ;
  }

  public void setCPFCNPJ(String cPFCNPJ) {
    CPFCNPJ = cPFCNPJ;
  }

  public int getAmountInCents() {
    return amountInCents;
  }

  public void setAmountInCents(int amountInCents) {
    this.amountInCents = amountInCents;
  }

  public PaymentMethod getMethod() {
    return method;
  }

  public void setMethod(PaymentMethod method) {
    this.method = method;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }
}