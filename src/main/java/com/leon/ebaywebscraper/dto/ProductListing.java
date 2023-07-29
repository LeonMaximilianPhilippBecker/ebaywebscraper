package com.leon.ebaywebscraper.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductListing {

  private String listingUrl;
  private Date listingDate;
  private double price;
  private boolean isBuyable;
}
