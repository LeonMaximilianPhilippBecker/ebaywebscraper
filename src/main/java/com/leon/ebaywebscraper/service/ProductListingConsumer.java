package com.leon.ebaywebscraper.service;

import com.leon.ebaywebscraper.repositories.ProductRepository;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.DisposableBean;

public class ProductListingConsumer implements DisposableBean {

  private final ProductListingConsumerRunnable productListingConsumerRunnable;
  private final ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);

  public ProductListingConsumer(ProductRepository productRepository, Messenger messenger,
      WebScraper webScraper) {
    this.productListingConsumerRunnable = new ProductListingConsumerRunnable(productRepository,
        webScraper, messenger);
    threadPoolExecutor.schedule(productListingConsumerRunnable, 0, TimeUnit.SECONDS);
  }

  @Override
  public void destroy() throws Exception {
    productListingConsumerRunnable.stopConsuming();
    threadPoolExecutor.shutdown();
  }
}
