package com.leon.ebaywebscraper.config;

import com.leon.ebaywebscraper.repositories.ProductRepository;
import com.leon.ebaywebscraper.service.Messenger;
import com.leon.ebaywebscraper.service.ProductListingConsumer;
import com.leon.ebaywebscraper.service.WebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  Messenger messenger;

  @Autowired
  WebScraper webScraper;

  @Bean
  public ProductListingConsumer productListingConsumer() {
    return new ProductListingConsumer(productRepository, messenger, webScraper);
  }
}
