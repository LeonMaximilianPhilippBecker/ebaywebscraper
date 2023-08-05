package com.leon.ebaywebscraper.service;

import com.leon.ebaywebscraper.dto.Product;
import com.leon.ebaywebscraper.dto.ProductListing;
import com.leon.ebaywebscraper.repositories.ProductRepository;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductListingConsumerRunnable implements Runnable {

  private final ProductRepository productRepository;
  private final WebScraper webScraper;
  private final boolean running = true;

  @Override
  @SneakyThrows
  public void run() {
    List<Product> products = productRepository.findAll();

    while (running) {
      try {
        for (Product product : products) {
          scrapeAndProcess(product);
        }
      } catch (Exception e) {
        // HTTP status exception occurred.
      } finally {
        TimeUnit.MINUTES.sleep(10);
      }
    }
  }

  private void scrapeAndProcess(Product product) {
    ProductListing newlyscrapedProductListing = webScraper.scrape(product.getProductUrl());
    ProductListing mostRecentlyScrapedProductListing = productRepository.findProductListingWithMostRecentListingDateForProduct(
        product.getProductId());
    if (newlyscrapedProductListing.getListingDate()
        .isAfter(mostRecentlyScrapedProductListing.getListingDate())) {
      product.getProductListings().add(newlyscrapedProductListing);
      productRepository.update(product);
    }
  }

  private
}
