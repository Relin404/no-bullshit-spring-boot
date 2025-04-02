package com.example.demo.product.model;

import lombok.Data;

@Data
public class ProductDTO {
  private Long id;
  private String name;
  private String description;
  private Double price;

  public ProductDTO(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.description = product.getDescription();
    this.price = product.getPrice();
  }

  
}
