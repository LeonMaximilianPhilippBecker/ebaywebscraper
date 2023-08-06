package com.leon.ebaywebscraper.repositories;

import com.leon.ebaywebscraper.dto.Product;
import com.leon.ebaywebscraper.dto.ProductListing;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class ProductRepositoryTest {

  @Autowired
  ProductRepository productRepository;

  @AfterEach
  void setUp() {
    productRepository.deleteAll();
  }

  @Test
  public void insert_Works() {
    Product insertedProduct = getProductFromIndex(1);
    productRepository.insert(insertedProduct);

    Optional<Product> fetchedProduct = productRepository.findById(insertedProduct.getProductId());

    Assertions.assertTrue(fetchedProduct.isPresent());
    Assertions.assertEquals(insertedProduct, fetchedProduct.get());
  }

  @Test
  public void save_Works() {
    Product insertedProduct = getProductFromIndex(1);
    productRepository.insert(insertedProduct);
    insertedProduct.setProductName("changed Name");
    productRepository.save(insertedProduct);

    Product updatedProduct = productRepository.findById(insertedProduct.getProductId()).get();

    Assertions.assertEquals("changed Name", updatedProduct.getProductName());
  }

  @Test
  public void findAll_Works() {
    List<Product> insertedProducts = getNProducts(2);
    productRepository.saveAll(insertedProducts);

    List<Product> fetchedProducts = productRepository.findAll();

    Assertions.assertEquals(insertedProducts, fetchedProducts);
  }

  @Test
  public void deleteByProductName_Works() {
    Product insertedProduct = getProductFromIndex(1);
    productRepository.insert(insertedProduct);

    productRepository.deleteByProductName(insertedProduct.getProductName());
    Optional<Product> nonExistentProduct = productRepository.findById(
        insertedProduct.getProductId());

    Assertions.assertTrue(nonExistentProduct.isEmpty());
  }

  @Test
  public void findProductByProductName_Works() {
    Product insertedProduct = getProductFromIndex(1);
    productRepository.insert(insertedProduct);

    Product fetchedProduct = productRepository.findProductByProductName(
        insertedProduct.getProductName());

    Assertions.assertNotNull(fetchedProduct);
    Assertions.assertEquals(insertedProduct, fetchedProduct);
  }

  private List<Product> getNProducts(final int numberOfProducts) {
    List<Product> products = new ArrayList<>();
    for (int i = 0; i < numberOfProducts; i++) {
      products.add(getProductFromIndex(i));
    }
    return products;
  }

  private Product getProductFromIndex(final int index) {
    return new Product(String.valueOf(index), String.valueOf(index), String.valueOf(index),
        LocalDateTime.of(0, 1, 1, 0, 0),
        List.of(), List.of(), index, index, getNListings(10));
  }

  private List<ProductListing> getNListings(final int numberOfListings) {
    List<ProductListing> productListings = new ArrayList<>();
    for (int i = 0; i < numberOfListings; i++) {
      productListings.add(getProductListingFromIndex(i));
    }
    return productListings;
  }

  private ProductListing getProductListingFromIndex(final int index) {
    return new ProductListing(String.valueOf(index), LocalDateTime.of(index, 1, 1, 0, 0), index,
        true);
  }
}
