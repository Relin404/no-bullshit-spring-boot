package com.example.demo.product.services;

import com.example.demo.Command;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.model.UpdateProductCommand;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {
  private final ProductRepository productRepository;

  public UpdateProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  @CachePut(value = "productCache", key = "#command.getId()")
  public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
    Optional<Product> productOptional = productRepository.findById(command.getId());

    if (productOptional.isPresent()) {
      Product product = command.getProduct();
      product.setId(command.getId());

      productRepository.save(product);

      return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(product));
    }

    throw new ProductNotFoundException();
  }
}
