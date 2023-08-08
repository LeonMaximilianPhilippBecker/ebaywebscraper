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

  @PostMapping(value = "/post/product/{product}")
  public Product createProduct(@PathVariable Product product) {
    return productRepository.insert(product);
  }

  @GetMapping(value = "/get/productlistings/{productname}")
  public List<ProductListing> getProductListingsByProductName(@PathVariable String productName) {
    return productRepository.findProductByProductname(productName).getProductlistings();
  }

  @GetMapping(value = "/get/product/{product}")
  public Product getProductByName(@PathVariable String productName) {
    return productRepository.findProductByProductname(productName);
  }

  @GetMapping(value = "/get/products")
  public List<Product> getProductListings() {
    return productRepository.findAll();
  }

  @DeleteMapping(value = "/delete/productname/{productname}")
  public void deleteProductByName(@PathVariable String productName) {
    productRepository.deleteByProductname(productName);
  }
}
