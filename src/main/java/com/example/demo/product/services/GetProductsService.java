package com.example.demo.product.services;

import com.example.demo.Query;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsService implements Query<Void, List<ProductDTO>> {
  private final ProductRepository productRepository;

  public GetProductsService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ResponseEntity<List<ProductDTO>> execute(Void input) {
    List<Product> products = productRepository.findAll();
    List<ProductDTO> productDTOs = products.stream().map(ProductDTO::new).toList();

    return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
  }
}
