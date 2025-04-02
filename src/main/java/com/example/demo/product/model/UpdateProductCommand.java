package com.example.demo.product.model;

import lombok.Getter;

@Getter
public class UpdateProductCommand {
  private Long id;
  private Product product;

  public UpdateProductCommand(
          Long id,
          Product product
  ) {
    this.id = id;
    this.product = product;
  }
}
