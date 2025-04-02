package com.example.demo.product.services;

import com.example.demo.Command;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductService implements Command<Long, Void> {
  private ProductRepository productRepository;

  public DeleteProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<Void> execute(Long id) {
    Optional<Product> productOptional = productRepository.findById(id);

    if (productOptional.isPresent()) {
      productRepository.deleteById(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    throw new ProductNotFoundException();
  }
}
