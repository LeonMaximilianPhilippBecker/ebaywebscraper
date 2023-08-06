package com.leon.ebaywebscraper.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductListing {

  private String listingUrl;
  private LocalDateTime listingDate;
  private double price;
  private boolean isBuyable;

  @Override
  public String toString() {
    return "ProductListing{" +
        "listingUrl='" + listingUrl + '\'' +
        ", listingDate=" + listingDate +
        ", price=" + price +
        ", isBuyable=" + isBuyable +
        '}';
  }
}
