package com.leon.ebaywebscraper.service;

import com.leon.ebaywebscraper.dto.ProductListing;

public interface WebScraper {

  ProductListing scrape(String url);
}
