package com.example.demo.product.services;

import com.example.demo.Query;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductService implements Query<Long, ProductDTO> {
  private final ProductRepository productRepository;

  public GetProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<ProductDTO> execute(Long id) {
    Optional<Product> product = productRepository.findById(Long.valueOf(id));

    if (product.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(product.get()));
    }

    throw new ProductNotFoundException();
  }
}
