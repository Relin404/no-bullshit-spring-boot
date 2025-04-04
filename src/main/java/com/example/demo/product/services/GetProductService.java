package com.example.demo.product.services;

import com.example.demo.Query;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductService implements Query<Long, ProductDTO> {
  private final ProductRepository productRepository;
  private static final Logger logger = LoggerFactory.getLogger(GetProductService.class);

  public GetProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  @Cacheable("productCache")
  public ResponseEntity<ProductDTO> execute(Long id) {
    logger.info("Executing {} input: {}", getClass(), id);
    Optional<Product> product = productRepository.findById(Long.valueOf(id));

    if (product.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(product.get()));
    }

    throw new ProductNotFoundException();
  }
}
