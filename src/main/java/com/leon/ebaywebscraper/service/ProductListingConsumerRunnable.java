package com.leon.ebaywebscraper.service;

import com.leon.ebaywebscraper.dto.Product;
import com.leon.ebaywebscraper.dto.ProductListing;
import com.leon.ebaywebscraper.repositories.ProductRepository;
import java.time.LocalDateTime;
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
    final ProductListing newlyscrapedProductListing = webScraper.scrape(product.getUrl());
    final LocalDateTime mostRecentlyScrapedProductListingDate = productRepository
        .findProductByProductname(product.getProductname())
        .getMostrecentlistingdate();

    if (newlyscrapedProductListing == null) {
      return;
    }

    if (newlyscrapedProductListing.getListingdate()
        .isAfter(mostRecentlyScrapedProductListingDate)) {
      product.getProductlistings().add(newlyscrapedProductListing);
      product.setMostrecentlistingdate(newlyscrapedProductListing.getListingdate());
      productRepository.save(product);

      if (newlyscrapedProductListing.getPrice() <= product.getMaxviableprice()) {
        messenger.sendProductListingAlert(newlyscrapedProductListing);
      }
    }
  }


}
