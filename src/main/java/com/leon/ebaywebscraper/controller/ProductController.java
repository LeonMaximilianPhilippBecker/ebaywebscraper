package com.leon.ebaywebscraper.controller;

import com.leon.ebaywebscraper.dto.Product;
import com.leon.ebaywebscraper.dto.ProductListing;
import com.leon.ebaywebscraper.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  ProductRepository productRepository;

  @PostMapping
  public Product createProduct(@PathVariable Product product) {
    return productRepository.insert(product);
  }

  @GetMapping
  public List<ProductListing> getProductListingsByProductName(@PathVariable String productName) {
    return productRepository.findProductByProductName(productName).getProductListings();
  }

  @GetMapping
  public Product getProductByName(@PathVariable String productName) {
    return productRepository.findProductByProductName(productName);
  }

  @DeleteMapping("/{productName}")
  public void deleteProductByName(@PathVariable String productName) {
    productRepository.deleteByProductName(productName);
  }
}
