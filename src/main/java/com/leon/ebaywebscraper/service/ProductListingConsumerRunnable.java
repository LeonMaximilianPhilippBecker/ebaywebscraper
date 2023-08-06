package com.leon.ebaywebscraper.service;

import com.leon.ebaywebscraper.dto.Product;
import com.leon.ebaywebscraper.dto.ProductListing;
import com.leon.ebaywebscraper.repositories.ProductRepository;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class ProductListingConsumerRunnable implements Runnable {

  private final ProductRepository productRepository;
  private final WebScraper webScraper;
  private final Messenger messenger;
  private boolean running = true;

  public ProductListingConsumerRunnable(final ProductRepository productRepository,
      final WebScraper webScraper,
      final Messenger messenger) {
    this.productRepository = productRepository;
    this.webScraper = webScraper;
    this.messenger = messenger;
  }

  @Override
  @SneakyThrows
  public void run() {
    final List<Product> products = productRepository.findAll();

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

  public void stopConsuming() {
    running = false;
  }

  private void scrapeAndProcess(final Product product) {
    final ProductListing newlyscrapedProductListing = webScraper.scrape(product.getProductUrl());
    final ProductListing mostRecentlyScrapedProductListing = productRepository
        .findProductByProductName(product.getProductName())
        .getProductListings()
        .stream()
        .max(Comparator.comparing(ProductListing::getListingDate)).get();

    if (!newlyscrapedProductListing.getListingDate()
        .isAfter(mostRecentlyScrapedProductListing.getListingDate())) {
      return;
    }
    product.getProductListings().add(newlyscrapedProductListing);
    productRepository.save(product);
    if (newlyscrapedProductListing.getPrice() <= product.getMaxViablePrice()) {
      messenger.sendProductListingAlert(newlyscrapedProductListing);
    }

  }


}
