package com.leon.ebaywebscraper.service;

import com.leon.ebaywebscraper.dto.ProductListing;

public interface Messenger {

  void sendProductListingAlert(final ProductListing productListing);
}
