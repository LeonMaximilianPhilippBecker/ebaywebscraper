package com.leon.ebaywebscraper.config;

import com.leon.ebaywebscraper.service.WebScraper;
import com.leon.ebaywebscraper.service.mocks.MockWebScraper;
import org.springframework.context.annotation.Bean;

public class WebScraperConfiguration {

  @Bean
  public WebScraper webScraper() {
    return new MockWebScraper();
  }
}
