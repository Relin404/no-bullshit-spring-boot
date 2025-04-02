package com.example.demo.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "name")
  private String name;

  @Size(min = 20, message = "Description must be at least 20 characters long")
  @Column(name = "description")
  private String description;

  @Positive(message = "Price must be positive")
  @Column(name = "price")
  private Double price;
}
