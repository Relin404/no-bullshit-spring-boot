package com.example.demo;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.services.GetProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetProductServiceTest {
  // Arrange

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private GetProductService getProductService;

  // Act

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  // Assert

  @Test
  public void given_product_exists_when_get_product_service_return_product_dto() {
    Product product = new Product();
    product.setId(1L);
    product.setName("product");
    product.setDescription("product description");
    product.setPrice(100.0);

    when(productRepository.findById(1L)).thenReturn(Optional.of(product));

    ResponseEntity<ProductDTO> response = getProductService.execute(1L);

    assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
    verify(productRepository, times(1)).findById(1L);
  }

  @Test
  public void given_product_does_not_exist_when_get_product_service_throw_exception() {
    when(productRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1L));
    verify(productRepository, times(1)).findById(1L);
  }
}
