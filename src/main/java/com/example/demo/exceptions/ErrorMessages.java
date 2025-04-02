package com.example.demo.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {
  PRODUCT_NOT_FOUND("Product not found"),
  WRONG_E("SAD");

  private final String message;

  ErrorMessages(String message) {
    this.message = message;
  }
}
