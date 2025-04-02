package com.example.demo.product;

import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.model.UpdateProductCommand;
import com.example.demo.product.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
  private final CreateProductService createProductService;

  private final GetProductsService getProductsService;

  private final GetProductService getProductService;

  private final UpdateProductService updateProductService;

  private final DeleteProductService deleteProductService;

  public ProductController(
          CreateProductService createProductService,
          GetProductsService getProductsService,
          GetProductService getProductService,
          UpdateProductService updateProductService,
          DeleteProductService deleteProductService
  ) {
    this.createProductService = createProductService;
    this.getProductsService = getProductsService;
    this.getProductService = getProductService;
    this.updateProductService = updateProductService;
    this.deleteProductService = deleteProductService;
  }

  @PostMapping
  public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
    return createProductService.execute(product);
  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getProducts() {
    return getProductsService.execute(null);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
    return getProductService.execute(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> updateProduct(
          @PathVariable Long id,
          @RequestBody Product product
  ) {
    return updateProductService.execute(new UpdateProductCommand(id, product));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    return deleteProductService.execute(id);
  }
}
